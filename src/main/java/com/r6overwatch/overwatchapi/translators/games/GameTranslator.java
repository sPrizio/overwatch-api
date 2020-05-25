package com.r6overwatch.overwatchapi.translators.games;

import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.services.entities.games.GameService;
import com.r6overwatch.overwatchapi.services.entities.games.MapService;
import com.r6overwatch.overwatchapi.services.entities.season.SeasonService;
import com.r6overwatch.overwatchapi.translators.OverwatchTranslator;
import com.r6overwatch.overwatchapi.translators.players.statistics.SquadGameStatisticsTranslator;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Translates a map into a {@link Game} entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GameTranslator implements OverwatchTranslator<Game> {

    @Resource(name = "gameService")
    private GameService gameService;

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
        LocalDateTime dateTime = parseLocalDateTime(values.get("gameDateTime").toString(), "yyyy-MM-dd HH:mm:ss");

        if (dateTime != null && this.gameService.findGameByDateTime(dateTime).isEmpty() && map.isPresent() && season.isPresent()) {
            try {
                return new Game(
                        season.get(),
                        map.get(),
                        dateTime,
                        this.squadGameStatisticsTranslator.translate((java.util.Map<String, Object>) values.get("squadGameStatistics"))
                );
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }


    //  HELPERS

    /**
     * Safely parses a {@link LocalDateTime} from the given string in the given format
     *
     * @param dateTime date string
     * @param format format the string is in
     * @return {@link LocalDateTime}
     */
    private LocalDateTime parseLocalDateTime(String dateTime, String format) {

        if (StringUtils.isNotEmpty(dateTime) && StringUtils.isNotEmpty(format)) {
            try {
                return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }
}
