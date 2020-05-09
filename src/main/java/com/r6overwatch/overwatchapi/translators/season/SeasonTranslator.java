package com.r6overwatch.overwatchapi.translators.season;

import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.translators.OverwatchTranslator;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Translates a map into a {@link Season} entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SeasonTranslator implements OverwatchTranslator<Season> {


    //  METHODS

    @Override
    public Season translate(Map<String, Object> values) {
        try {
            return new Season(
                    values.get("name").toString(),
                    OverwatchUtils.parseInteger(values.get("seasonYear").toString()),
                    OverwatchUtils.parseInteger(values.get("seasonNumber").toString()),
                    LocalDate.parse(values.get("releaseDate").toString(), DateTimeFormatter.ISO_DATE)
            );
        } catch (Exception e) {
            return null;
        }
    }
}
