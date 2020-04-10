package com.r6overwatch.overwatchapi.repositories.players.player;

import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.repositories.OverwatchRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for {@link Player}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface PlayerRepository extends OverwatchRepository, PlayerRepositoryCustom, CrudRepository<Player, Long> {
}
