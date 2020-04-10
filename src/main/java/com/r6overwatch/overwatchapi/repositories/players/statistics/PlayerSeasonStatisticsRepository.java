package com.r6overwatch.overwatchapi.repositories.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerSeasonStatistics;
import com.r6overwatch.overwatchapi.repositories.OverwatchRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for {@link PlayerSeasonStatistics}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface PlayerSeasonStatisticsRepository extends OverwatchRepository, CrudRepository<PlayerSeasonStatistics, Long> {
}
