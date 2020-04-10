package com.r6overwatch.overwatchapi.services.nonentities.stats.impl;

import com.r6overwatch.overwatchapi.constants.CoreConstants;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerSeasonStatistics;
import com.r6overwatch.overwatchapi.models.nonentities.Stats;
import com.r6overwatch.overwatchapi.services.nonentities.stats.AbstractStatsService;
import com.r6overwatch.overwatchapi.services.nonentities.stats.StatsService;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link StatsService} for {@link Player}s
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class PlayerStatsService extends AbstractStatsService<PlayerSeasonStatistics> implements StatsService<Player> {


    //  METHODS

    @Override
    public Stats calculate(Player entity) {

        Map<String, DoubleSummaryStatistics> statisticsMap = new HashMap<>();

        CoreConstants.PLAYER_SEASON_COLLECTORS.forEach(
                (attribute, collector) -> statisticsMap.put(attribute, calculateStatistics(attribute, collector, entity.getPlayerSeasons()))
        );

        return new Stats(statisticsMap);
    }
}
