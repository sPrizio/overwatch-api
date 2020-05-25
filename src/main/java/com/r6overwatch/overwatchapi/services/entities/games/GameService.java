package com.r6overwatch.overwatchapi.services.entities.games;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.games.game.GameRepository;
import com.r6overwatch.overwatchapi.repositories.players.statistics.SquadGameStatisticsRepository;
import com.r6overwatch.overwatchapi.services.entities.OverwatchEntityService;
import com.r6overwatch.overwatchapi.services.entities.players.PlayerService;
import com.r6overwatch.overwatchapi.services.entities.players.SquadService;
import com.r6overwatch.overwatchapi.translators.games.GameTranslator;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link OverwatchEntityService} architecture for {@link Game}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GameService implements OverwatchEntityService<Game> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Resource(name = "gameRepository")
    private GameRepository gameRepository;

    @Resource(name = "gameTranslator")
    private GameTranslator gameTranslator;

    @Resource(name = "playerService")
    private PlayerService playerService;

    @Resource(name = "squadGameStatisticsRepository")
    private SquadGameStatisticsRepository squadGameStatisticsRepository;

    @Resource(name = "squadService")
    private SquadService squadService;


    //  METHODS

    /**
     * Finds a {@link Game} by its game date time
     *
     * @param dateTime date & time to search for
     * @return {@link Game} with the matching {@link LocalDateTime}
     */
    public Optional<Game> findGameByDateTime(LocalDateTime dateTime) {

        if (dateTime == null) {
            LOGGER.error("dateTime was null or empty");
            return Optional.empty();
        }

        try {
            return Optional.ofNullable(this.gameRepository.findByGameDateTime(dateTime));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    /**
     * Finds games for the given {@link Squad} and {@link Season}
     *
     * @param squad {@link Squad}'s games to obtain
     * @param season {@link Season} desired season
     * @return list of {@link Game}s sorted by recency with regards to date time
     */
    public List<Game> findGamesBySquadAndSeasonSortedByDate(Squad squad, Season season) {

        if (squad == null || season == null) {
            LOGGER.error("One or more of the required parameters was null or empty: squad {}, season {}", squad, season);
            return new ArrayList<>();
        }

        List<Game> seasonGames = this.gameRepository.findBySeasonOrderByGameDateTimeDesc(season);

        if (CollectionUtils.isNotEmpty(seasonGames)) {
            List<Game> blue = seasonGames.stream().filter(game -> game.getSquadGameStatistics().getSquad().getId().equals(squad.getId())).collect(Collectors.toList());
            return blue.stream().sorted(Comparator.comparing(Game::getGameDateTime).reversed()).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    /**
     * Finds games for the given {@link Player} and {@link Season}
     *
     * @param player {@link Player}'s games to obtain
     * @param season {@link Season} desired season
     * @param limit the number of results to return, if null, no limit will be applied
     * @return list of {@link Game}s sorted by recency with regards to date time
     */
    public List<Game> findGamesByPlayerAndSeasonSortedByDateLimited(Player player, Season season, Integer limit) {

        if (player == null || season == null) {
            LOGGER.error("One or more of the required parameters was null or empty: player {}, season {},", player, season);
            return new ArrayList<>();
        }

        List<Game> seasonGames = this.gameRepository.findBySeasonOrderByGameDateTimeDesc(season);

        if (CollectionUtils.isNotEmpty(seasonGames)) {
            List<Game> blue = seasonGames.stream().filter(game -> containsPlayer(game.getSquadGameStatistics().getPlayerGameStatistics(), player)).collect(Collectors.toList());
            return limit != null ? blue.stream().sorted(Comparator.comparing(Game::getGameDateTime).reversed()).limit(limit).collect(Collectors.toList()) : blue.stream().sorted(Comparator.comparing(Game::getGameDateTime).reversed()).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public void refresh(Game entity) {
        this.gameRepository.refresh(entity);
    }

    @Override
    public Optional<Game> find(Long id) {
        return this.gameRepository.findById(id);
    }

    @Override
    public List<Game> findAll() {
        return Lists.newArrayList(this.gameRepository.findAll());
    }

    @Override
    public Game save(Game entity) {
        return this.gameRepository.save(entity);
    }

    @Override
    public void delete(Long id) {

        Optional<Game> game = find(id);
        if (game.isPresent()) {
            SquadGameStatistics squadGameStatistics = game.get().getSquadGameStatistics();
            Set<PlayerGameStatistics> playerGameStatistics = squadGameStatistics.getPlayerGameStatistics();

            this.squadService.updateStats(squadGameStatistics, game.get().getSeason(), true);
            if (CollectionUtils.isNotEmpty(playerGameStatistics)) {
                playerGameStatistics.forEach(stats -> this.playerService.updateStats(stats, squadGameStatistics, game.get().getSeason(), true));
            }

            this.gameRepository.deleteById(game.get().getId());
        }
    }

    @Override
    public Game create(Map<String, Object> params) {

        Game game = this.gameTranslator.translate(params);

        if (game != null) {
            //  save the squad game statistics before continuing
            this.squadGameStatisticsRepository.save(game.getSquadGameStatistics());

            SquadGameStatistics squadGameStatistics = game.getSquadGameStatistics();
            Set<PlayerGameStatistics> playerGameStatistics = squadGameStatistics.getPlayerGameStatistics();

            this.squadService.updateStats(squadGameStatistics, game.getSeason(), false);
            if (CollectionUtils.isNotEmpty(playerGameStatistics)) {
                playerGameStatistics.forEach(stats -> this.playerService.updateStats(stats, squadGameStatistics, game.getSeason(), false));
            }

            return this.gameRepository.save(game);
        }

        return null;
    }


    //  HELPERS

    /**
     * Checks if the given list ot {@link PlayerGameStatistics} contains the given {@link Player}
     *
     * @param playerGameStatistics list of {@link PlayerGameStatistics} to consider
     * @param player desired {@link Player} to match
     * @return true if the given {@link Player} appears in the list
     */
    private boolean containsPlayer(Set<PlayerGameStatistics> playerGameStatistics, Player player) {
        return playerGameStatistics.stream().anyMatch(stats -> stats.getPlayer().getId().equals(player.getId()));
    }
}
