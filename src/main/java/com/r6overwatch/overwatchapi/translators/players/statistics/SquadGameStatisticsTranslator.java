package com.r6overwatch.overwatchapi.translators.players.statistics;

import com.r6overwatch.overwatchapi.enums.GameSide;
import com.r6overwatch.overwatchapi.enums.MapResult;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import com.r6overwatch.overwatchapi.services.entities.players.SquadService;
import com.r6overwatch.overwatchapi.translators.OverwatchTranslator;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Translates a map into a {@link SquadGameStatistics} entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SquadGameStatisticsTranslator implements OverwatchTranslator<SquadGameStatistics> {

    @Resource(name = "playerGameStatisticsTranslator")
    private PlayerGameStatisticsTranslator playerGameStatisticsTranslator;

    @Resource(name = "squadService")
    private SquadService squadService;


    //  METHODS

    @Override
    public SquadGameStatistics translate(Map<String, Object> values) {

        Optional<Squad> squad = this.squadService.find(OverwatchUtils.parseLong(values.get("squadId").toString()));
        return squad.map(s -> new SquadGameStatistics(
                s,
                GameSide.valueOf(values.get("gameSide").toString()),
                MapResult.valueOf(values.get("mapResult").toString()),
                OverwatchUtils.parseInteger(values.get("roundsWon").toString()),
                OverwatchUtils.parseInteger(values.get("roundsLost").toString()),
                obtainPlayerStats(values)
        )).orElse(null);
    }


    //  HELPERS

    /**
     * Obtains a map of player stats from the given values
     *
     * @param values value map (request)
     * @return set of {@link PlayerGameStatistics}
     */
    private Set<PlayerGameStatistics> obtainPlayerStats(Map<String, Object> values) {
        if (MapUtils.isNotEmpty(values)) {
            try {
                List<Map<String, Object>> playerGameStats = (List<Map<String, Object>>) values.get("playerGameStatistics");
                return playerGameStats.stream().map(m -> this.playerGameStatisticsTranslator.translate(m)).collect(Collectors.toSet());
            } catch (Exception e) {
                return new HashSet<>();
            }
        }

        return new HashSet<>();
    }
}
