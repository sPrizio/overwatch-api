package com.r6overwatch.overwatchapi.repositories.games.game;

import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.repositories.OverwatchRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for {@link Game}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface GameRepository extends OverwatchRepository, GameRepositoryCustom, CrudRepository<Game, Long> {
}
