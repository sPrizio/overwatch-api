package com.r6overwatch.overwatchapi.converters.entities.players;

import com.r6overwatch.overwatchapi.converters.entities.OverwatchConverter;
import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;

import java.util.List;

/**
 * For participants in games, we want to be able to highlight a particular season statistics
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface PlayersConverter<E extends OverwatchEntity, R extends OverwatchResource> extends OverwatchConverter<E, R> {

    /**
     * Converts an {@link OverwatchEntity} into an {@link OverwatchResource} and includes a highlighted seasonal statistic resource
     *
     * @param entity {@link OverwatchEntity} to convert
     * @param season {@link Season} that we're using to highlight
     * @return {@link OverwatchResource} with highlighted season set
     */
    R convertHighlighted(E entity, Season season);

    /**
     * Converts a list of {@link OverwatchEntity} into a list of {@link OverwatchResource} and includes a highlighted seasonal statistic resource
     *
     * @param entity list of {@link OverwatchEntity} to convert
     * @param season {@link Season} that we're using to highlight
     * @return list of {@link OverwatchResource} with highlighted season set
     */
    List<R> convertAllHighlighted(List<E> entity, Season season);
}
