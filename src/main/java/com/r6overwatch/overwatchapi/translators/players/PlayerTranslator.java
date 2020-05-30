package com.r6overwatch.overwatchapi.translators.players;

import com.r6overwatch.overwatchapi.enums.PlayerRole;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.translators.OverwatchTranslator;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Translates a map into a {@link Player} entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class PlayerTranslator implements OverwatchTranslator<Player> {


    //  METHODS

    @Override
    public Player translate(Map<String, Object> values) {
        PlayerRole role = EnumUtils.isValidEnum(PlayerRole.class, values.get("role").toString()) ? PlayerRole.valueOf(values.get("role").toString()) : PlayerRole.FLEX;
        return new Player(values.get("name").toString(), values.get("alias").toString(), role);
    }
}
