package com.r6overwatch.overwatchapi.facades.entities.games;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.converters.entities.games.GameConverter;
import com.r6overwatch.overwatchapi.facades.entities.OverwatchFacade;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.games.GameResource;
import com.r6overwatch.overwatchapi.services.entities.games.GameService;
import com.r6overwatch.overwatchapi.services.entities.players.PlayerService;
import com.r6overwatch.overwatchapi.services.entities.players.SquadService;
import com.r6overwatch.overwatchapi.services.entities.season.SeasonService;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Facade for {@link GameResource}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GameFacade implements OverwatchFacade<GameResource> {

    @Resource(name = "gameConverter")
    private GameConverter gameConverter;

    @Resource(name = "gameService")
    private GameService gameService;

    @Resource(name = "playerService")
    private PlayerService playerService;

    @Resource(name = "seasonService")
    private SeasonService seasonService;

    @Resource(name = "squadService")
    private SquadService squadService;


    //  METHODS

    /**
     * Finds games for the given {@link Squad} and {@link Season}
     *
     * @param squadId {@link Squad}'s id
     * @param seasonId id of desired {@link Season}
     * @param limit number of results to return
     * @return list of {@link Game}s sorted by recency with regards to date time
     */
    public List<GameResource> findGamesBySquadAndSeasonSortedByDate(Long squadId, Long seasonId, Integer limit) {

        if (OverwatchUtils.areNonNull(squadId, seasonId)) {
            Optional<Squad> squad = this.squadService.find(squadId);
            Optional<Season> season = this.seasonService.find(seasonId);

            if (squad.isPresent() && season.isPresent()) {
                return Lists.newArrayList(this.gameConverter.convertAll(this.gameService.findGamesBySquadAndSeasonSortedByDate(squad.get(), season.get(), limit)));
            }
        }

        return new ArrayList<>();
    }

    /**
     * Finds games for the given {@link Player} and {@link Season}
     *
     * @param playerId {@link Player}'s games to obtain
     * @param seasonId {@link Season} desired season
     * @param limit number of results to return
     * @return list of {@link Game}s sorted by recency with regards to date time
     */
    public List<GameResource> findGamesByPlayerAndSeasonSortedByDateLimited(Long playerId, Long seasonId, Integer limit) {

        if (OverwatchUtils.areNonNull(playerId, seasonId)) {
            Optional<Player> player = this.playerService.find(playerId);
            Optional<Season> season = this.seasonService.find(seasonId);

            if (player.isPresent() && season.isPresent()) {
                return Lists.newArrayList(this.gameConverter.convertAll(this.gameService.findGamesByPlayerAndSeasonSortedByDateLimited(player.get(), season.get(), limit)));
            }
        }

        return new ArrayList<>();
    }

    @Override
    public GameResource find(Long id) {

        Optional<Game> game = this.gameService.find(id);

        if (game.isPresent()) {
            return this.gameConverter.convert(game.get());
        }

        return new GameResource();
    }

    @Override
    public List<GameResource> findAll() {
        return Lists.newArrayList(this.gameConverter.convertAll(this.gameService.findAll()));
    }

    @Override
    public GameResource create(Map<String, Object> params) {

        Game game = this.gameService.create(params);

        if (game != null) {
            return this.gameConverter.convert(game);
        }

        return new GameResource();
    }

    @Override
    public void delete(Long id) {
        this.gameService.delete(id);
    }
}
