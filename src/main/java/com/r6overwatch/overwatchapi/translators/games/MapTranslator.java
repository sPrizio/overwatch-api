package com.r6overwatch.overwatchapi.translators.games;

import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.translators.OverwatchTranslator;
import org.springframework.stereotype.Component;

/**
 * Translates a map into a {@link Map} entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class MapTranslator implements OverwatchTranslator<Map> {


    //  METHODS

    @Override
    public Map translate(java.util.Map<String, Object> values) {
        return new Map(values.get("name").toString());
    }
}
