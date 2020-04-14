package com.r6overwatch.overwatchapi.converters.entities.games;

import com.r6overwatch.overwatchapi.converters.entities.OverwatchConverter;
import com.r6overwatch.overwatchapi.converters.entities.players.statistics.SquadGameStatisticsConverter;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.resources.entities.games.GameResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Converter for {@link Game} entities into {@link GameResource} objects
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GameConverter implements OverwatchConverter<Game, GameResource> {

    @Resource(name = "mapConverter")
    private MapConverter mapConverter;

    @Resource(name = "squadGameStatisticsConverter")
    private SquadGameStatisticsConverter squadGameStatisticsConverter;


    //  METHODS

    @Override
    public GameResource convert(Game entity) {

        GameResource resource = new GameResource();

        resource.setCode(entity.getId());
        resource.setMap(this.mapConverter.convert(entity.getMap()));
        resource.setGameDateTime(entity.getGameDateTime());
        resource.setBlueTeamStatistics(this.squadGameStatisticsConverter.convert(entity.getBlueTeamStatistics()));
        resource.setOrangeTeamStatistics(this.squadGameStatisticsConverter.convert(entity.getOrangeTeamStatistics()));

        return resource;
    }

    @Override
    public Collection<GameResource> convertAll(Collection<Game> entity) {
        if (CollectionUtils.isNotEmpty(entity)) {
            return entity.stream().map(this::convert).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
