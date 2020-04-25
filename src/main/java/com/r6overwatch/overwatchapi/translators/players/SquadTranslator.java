package com.r6overwatch.overwatchapi.translators.players;

import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.translators.OverwatchTranslator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Translates a map into a {@link Squad} entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SquadTranslator implements OverwatchTranslator<Squad> {


    //  METHODS

    @Override
    public Squad translate(Map<String, Object> values) {
        return new Squad(values.get("name").toString());
    }
}
