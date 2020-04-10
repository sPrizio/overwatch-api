package com.r6overwatch.overwatchapi.repositories.players.player;

import com.r6overwatch.overwatchapi.enums.SortOrder;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;

import java.util.List;

/**
 * Custom repository methods for {@link Player}. We define custom methods for functionality that cannot be handled by the OOTB spring repository system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface PlayerRepositoryCustom {

    /**
     * Finds players for the given attribute and sort order
     *
     * @param squad the {@link Squad} that this {@link Player} belongs to
     * @param attribute the {@link Player} attribute that we wish to sort by
     * @param season the {@link Season}
     * @param sortOrder the {@link SortOrder} for the result list
     * @return list of {@link Player}s in the given {@link SortOrder}
     */
    List<Player> findPlayersBySquadAndSeasonSortedByAttribute(Squad squad, String attribute, Season season, SortOrder sortOrder);
}
