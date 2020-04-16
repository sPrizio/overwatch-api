package com.r6overwatch.overwatchapi.repositories.games.game;

import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of {@link GameRepositoryCustom}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Repository
public class GameRepositoryImpl implements GameRepositoryCustom {

    @Resource(name = "gameRepository")
    private GameRepository gameRepository;

    @PersistenceContext
    private EntityManager entityManager;


    //  METHODS

    @Override
    public List<Game> findGamesBySquadAndSeasonSortedByDate(Squad squad, Season season) {

        List<Game> seasonGames = this.gameRepository.findBySeasonOrderByGameDateTimeDesc(season);

        if (CollectionUtils.isNotEmpty(seasonGames)) {
            List<Game> blue =
                    seasonGames.stream().filter(game -> game.getBlueSquadStatistics().getSquad().getId().equals(squad.getId())).collect(Collectors.toList());

            List<Game> orange =
                    seasonGames.stream().filter(game -> game.getOrangeSquadStatistics().getSquad().getId().equals(squad.getId())).collect(Collectors.toList());

            blue.addAll(orange);
            return blue.stream().sorted(Comparator.comparing(Game::getGameDateTime).reversed()).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public List<Game> findGamesByPlayerAndSeasonSortedByDateLimited(Player player, Season season, Integer limit) {

        List<Game> seasonGames = this.gameRepository.findBySeasonOrderByGameDateTimeDesc(season);

        if (CollectionUtils.isNotEmpty(seasonGames)) {
            List<Game> blue =
                    seasonGames.stream().filter(game -> containsPlayer(game.getBlueSquadStatistics().getPlayerGameStatistics(), player)).collect(Collectors.toList());

            List<Game> orange =
                    seasonGames.stream().filter(game -> containsPlayer(game.getOrangeSquadStatistics().getPlayerGameStatistics(), player)).collect(Collectors.toList());

            blue.addAll(orange);
            return blue.stream().sorted(Comparator.comparing(Game::getGameDateTime).reversed()).limit(limit).collect(Collectors.toList());
        }

        return new ArrayList<>();
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
