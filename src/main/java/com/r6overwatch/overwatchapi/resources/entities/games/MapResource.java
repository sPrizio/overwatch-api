package com.r6overwatch.overwatchapi.resources.entities.games;

import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * A DTO for {@link Map}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public class MapResource implements OverwatchResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private String name;


    //  METHODS

    @Override
    public boolean isPresent() {
        return this.code != null && StringUtils.isNotEmpty(this.name);
    }
}
