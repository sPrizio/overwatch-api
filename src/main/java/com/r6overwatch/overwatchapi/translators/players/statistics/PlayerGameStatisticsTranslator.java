package com.r6overwatch.overwatchapi.translators.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.services.entities.players.PlayerService;
import com.r6overwatch.overwatchapi.translators.OverwatchTranslator;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * Translates a map into a {@link PlayerGameStatistics} entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class PlayerGameStatisticsTranslator implements OverwatchTranslator<PlayerGameStatistics> {

    @Resource(name = "playerService")
    private PlayerService playerService;


    //  METHODS

    @Override
    public PlayerGameStatistics translate(Map<String, Object> values) {

        Optional<Player> player = this.playerService.find(OverwatchUtils.parseLong(values.get("playerId").toString()));

        return player.map(p -> new PlayerGameStatistics(
                p,
                OverwatchUtils.parseInteger(values.get("score").toString()),
                OverwatchUtils.parseInteger(values.get("kills").toString()),
                OverwatchUtils.parseInteger(values.get("assists").toString()),
                OverwatchUtils.parseInteger(values.get("deaths").toString())
        )).orElse(null);
    }
}
