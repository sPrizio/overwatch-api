package com.r6overwatch.overwatchapi.services.nonentities.stats;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.nonentities.Stats;

/**
 * Parent-level statistic interface for obtaining statistic for a particular entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface StatsService<E extends OverwatchEntity> {

    /**
     * Calculates the statistics for an entity's seasonal stats
     *
     * @param entity entity that we're looking at
     * @return a statistic object used to obtain statistical measures for an entity's stats
     */
    Stats calculate(E entity);
}
