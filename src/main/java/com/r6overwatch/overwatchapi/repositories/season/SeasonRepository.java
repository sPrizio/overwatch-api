package com.r6overwatch.overwatchapi.repositories.season;

import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.OverwatchRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for {@link Season}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface SeasonRepository extends OverwatchRepository, CrudRepository<Season, Long> {

    /**
     * Looks for a {@link Season} who's season year and season number match the given input
     * Examples of inputs would be 5, 1 for Y5 S1
     *
     * @param seasonYear season year since inception of r6 siege
     * @param seasonNumber season number meaning which numerical delineation of the year we're in
     * @return {@link Season} if found, null otherwise
     */
    Season findBySeasonYearAndSeasonNumber(Integer seasonYear, Integer seasonNumber);
}
