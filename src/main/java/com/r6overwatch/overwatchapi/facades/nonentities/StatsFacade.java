package com.r6overwatch.overwatchapi.facades.nonentities;

import com.r6overwatch.overwatchapi.models.nonentities.Stats;
import com.r6overwatch.overwatchapi.resources.nonentities.PlayerStatsResource;

/**
 * Facade layer for {@link Stats}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface StatsFacade {

    /**
     * Calculates an entity's statistics
     *
     * @param id entity id
     * @return a statistical object that houses typical statistics for each entity stat attribute category
     */
    PlayerStatsResource obtainStats(Long id);
}
