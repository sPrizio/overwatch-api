package com.r6overwatch.overwatchapi.repositories.games.game;

import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;

import java.util.List;

/**
 * Custom repository methods for {@link Game}. We define custom methods for functionality that cannot be handled by the OOTB spring repository system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface GameRepositoryCustom {

    /**
     * Finds games for the given {@link Squad} and {@link Season}
     *
     * @param squad {@link Squad}'s games to obtain
     * @param season {@link Season} desired season
     * @return list of {@link Game}s sorted by recency with regards to date time
     */
    List<Game> findGamesBySquadAndSeasonSortedByDate(Squad squad, Season season);

    /**
     * Finds games for the given {@link Player} and {@link Season}
     *
     * @param player {@link Player}'s games to obtain
     * @param season {@link Season} desired season
     * @return list of {@link Game}s sorted by recency with regards to date time
     */
    List<Game> findGamesByPlayerAndSeasonSortedByDateLimited(Player player, Season season, Integer limit);
}
