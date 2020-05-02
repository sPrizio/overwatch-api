package com.r6overwatch.overwatchapi.translators.games;

import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.services.entities.games.MapService;
import com.r6overwatch.overwatchapi.services.entities.season.SeasonService;
import com.r6overwatch.overwatchapi.translators.OverwatchTranslator;
import com.r6overwatch.overwatchapi.translators.players.statistics.SquadGameStatisticsTranslator;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Translates a map into a {@link Game} entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GameTranslator implements OverwatchTranslator<Game> {

    @Resource(name = "mapService")
    private MapService mapService;

    @Resource(name = "seasonService")
    private SeasonService seasonService;

    @Resource(name = "squadGameStatisticsTranslator")
    private SquadGameStatisticsTranslator squadGameStatisticsTranslator;


    //  METHODS

    @Override
    public Game translate(java.util.Map<String, Object> values) {

        Optional<Map> map = this.mapService.find(OverwatchUtils.parseLong(values.get("mapId").toString()));
        Optional<Season> season = this.seasonService.find(OverwatchUtils.parseLong(values.get("seasonId").toString()));

        if (map.isPresent() && season.isPresent()) {
            try {
                return new Game(
                        season.get(),
                        map.get(),
                        LocalDateTime.parse(values.get("gameDateTime").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        this.squadGameStatisticsTranslator.translate((java.util.Map<String, Object>) values.get("squadGameStatistics"))
                );
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }
}
