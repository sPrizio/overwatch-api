package com.r6overwatch.overwatchapi.converters.nonentities;

import com.r6overwatch.overwatchapi.models.nonentities.StatResource;
import com.r6overwatch.overwatchapi.models.nonentities.Stats;
import com.r6overwatch.overwatchapi.resources.nonentities.PlayerStatsResource;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;
import java.util.Map;

/**
 * Converts {@link Stats} into {@link PlayerStatsResource}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class PlayerStatsConverter {

    /**
     * Converts a non-entity into a resource
     *
     * @param stats stats to convert
     * @return newly created resource based off of the stats
     */
    public PlayerStatsResource convert(Stats stats) {

        PlayerStatsResource resource = new PlayerStatsResource();
        Map<String, DoubleSummaryStatistics> map = stats.getStatsMap();

        resource.setWins(safeConvert("wins", map.get("wins")));
        resource.setLosses(safeConvert("losses", map.get("losses")));
        resource.setKills(safeConvert("kills", map.get("kills")));
        resource.setAssists(safeConvert("assists", map.get("assists")));
        resource.setDeaths(safeConvert("deaths", map.get("deaths")));
        resource.setKd(safeConvert("kd", map.get("kd")));
        resource.setWinPercentage(safeConvert("winPercentage", map.get("winPercentage")));
        resource.setKillsPerGame(safeConvert("killsPerGame", map.get("killsPerGame")));
        resource.setDeathsPerGame(safeConvert("deathsPerGame", map.get("deathsPerGame")));

        return resource;
    }


    //  HELPERS

    /**
     * Safely converts {@link DoubleSummaryStatistics} into a {@link StatResource}
     *
     * @param attr attribute that we're converter
     * @param summaryStatistics {@link DoubleSummaryStatistics} input
     * @return {@link StatResource}
     */
    private StatResource safeConvert(String attr, DoubleSummaryStatistics summaryStatistics) {
        if (summaryStatistics != null) {
            return new StatResource(attr, summaryStatistics.getSum(), summaryStatistics.getAverage(), summaryStatistics.getMin(), summaryStatistics.getMax());
        }

        return new StatResource(attr, 0.0, 0.0, 0.0, 0.0);
    }
}
