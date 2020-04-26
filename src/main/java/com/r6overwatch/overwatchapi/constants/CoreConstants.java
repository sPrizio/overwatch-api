package com.r6overwatch.overwatchapi.constants;

import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerSeasonStatistics;

import java.util.AbstractMap;
import java.util.DoubleSummaryStatistics;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A class that contains constants used for core functionality. We want to keep the system dynamic and logically compartmentalized, as such
 * we localize constants to this class as much as possible so changes need only be made in one spot
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public class CoreConstants {

    private CoreConstants() {
        throw new UnsupportedOperationException("Utility classes should not be instantiated");
    }

    public static final Locale OVERWATCH_LOCALE = Locale.CANADA;

    //  statistical collections

    public static final Map<String, Collector<PlayerSeasonStatistics, ?, DoubleSummaryStatistics>> PLAYER_SEASON_COLLECTORS = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("wins", Collectors.summarizingDouble(PlayerSeasonStatistics::getWins)),
            new AbstractMap.SimpleEntry<>("losses", Collectors.summarizingDouble(PlayerSeasonStatistics::getLosses)),
            new AbstractMap.SimpleEntry<>("kills", Collectors.summarizingDouble(PlayerSeasonStatistics::getKills)),
            new AbstractMap.SimpleEntry<>("assists", Collectors.summarizingDouble(PlayerSeasonStatistics::getAssists)),
            new AbstractMap.SimpleEntry<>("deaths", Collectors.summarizingDouble(PlayerSeasonStatistics::getDeaths)),
            new AbstractMap.SimpleEntry<>("kd", Collectors.summarizingDouble(PlayerSeasonStatistics::getKD)),
            new AbstractMap.SimpleEntry<>("winPercentage", Collectors.summarizingDouble(PlayerSeasonStatistics::getWinPercentage)),
            new AbstractMap.SimpleEntry<>("killsPerGame", Collectors.summarizingDouble(PlayerSeasonStatistics::getKillsPerGame)),
            new AbstractMap.SimpleEntry<>("deathsPerGame", Collectors.summarizingDouble(PlayerSeasonStatistics::getDeathsPerGame))
    );
}
