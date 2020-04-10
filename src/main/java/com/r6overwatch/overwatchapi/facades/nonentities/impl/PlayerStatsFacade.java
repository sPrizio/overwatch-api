package com.r6overwatch.overwatchapi.facades.nonentities.impl;

import com.r6overwatch.overwatchapi.converters.nonentities.PlayerStatsConverter;
import com.r6overwatch.overwatchapi.facades.nonentities.StatsFacade;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.resources.nonentities.PlayerStatsResource;
import com.r6overwatch.overwatchapi.services.entities.players.PlayerService;
import com.r6overwatch.overwatchapi.services.nonentities.stats.impl.PlayerStatsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Obtains statistics for {@link Player}s for use in controllers
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class PlayerStatsFacade implements StatsFacade {

    @Resource(name = "playerService")
    private PlayerService playerService;

    @Resource(name = "playerStatsConverter")
    private PlayerStatsConverter playerStatsConverter;

    @Resource(name = "playerStatsService")
    private PlayerStatsService playerStatsService;


    //  METHODS

    @Override
    public PlayerStatsResource obtainStats(Long id) {
        Optional<Player> player = this.playerService.find(id);
        return player.isPresent() ? this.playerStatsConverter.convert(this.playerStatsService.calculate(player.get())) : new PlayerStatsResource();
    }
}
