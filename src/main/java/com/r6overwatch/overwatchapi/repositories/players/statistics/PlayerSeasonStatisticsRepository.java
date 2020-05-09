package com.r6overwatch.overwatchapi.repositories.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerSeasonStatistics;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.OverwatchRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO access-layer for {@link PlayerSeasonStatistics}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface PlayerSeasonStatisticsRepository extends OverwatchRepository, CrudRepository<PlayerSeasonStatistics, Long> {

    /**
     * Finds a list of {@link PlayerSeasonStatistics} by {@link Season}
     *
     * @param season {@link Season} to consider
     * @return list of {@link PlayerSeasonStatistics} for the given {@link Season}
     */
    List<PlayerSeasonStatistics> findBySeason(Season season);
}
