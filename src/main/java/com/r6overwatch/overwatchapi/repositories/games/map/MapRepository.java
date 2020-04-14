package com.r6overwatch.overwatchapi.repositories.games.map;

import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.repositories.OverwatchRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for {@link Map}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface MapRepository extends OverwatchRepository, CrudRepository<Map, Long> {

    /**
     * Obtains a {@link Map} for the given map name
     *
     * @param name name of map
     * @return {@link Map} who's name matches the given string
     */
    Map findByName(String name);
}
