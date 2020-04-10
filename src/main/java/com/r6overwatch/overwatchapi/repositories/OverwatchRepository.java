package com.r6overwatch.overwatchapi.repositories;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;

/**
 * Parent-level repository for this system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface OverwatchRepository {

    /**
     * Refreshes an entity, meaning to update all of its information and relations
     *
     * @param entity entity that we want to have its data refreshed
     */
    void refresh(OverwatchEntity entity);
}
