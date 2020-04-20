package com.r6overwatch.overwatchapi.repositories.games.game;

import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.OverwatchRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO access-layer for {@link Game}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface GameRepository extends OverwatchRepository, GameRepositoryCustom, CrudRepository<Game, Long> {

    /**
     * Finds {@link Game}s by {@link Season}
     *
     * @param season {@link Season} to consider
     * @return list of {@link Game}s order by their recency
     */
    List<Game> findBySeasonOrderByGameDateTimeDesc(Season season);
}
