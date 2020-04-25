package com.r6overwatch.overwatchapi.converters.entities.players.statistics;

import com.r6overwatch.overwatchapi.converters.entities.OverwatchConverter;
import com.r6overwatch.overwatchapi.converters.entities.players.impl.PlayerConverter;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.resources.entities.players.statistics.PlayerGameStatisticsResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Converter for {@link PlayerGameStatistics} entities into {@link PlayerGameStatisticsResource} objects
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class PlayerGameStatisticsConverter implements OverwatchConverter<PlayerGameStatistics, PlayerGameStatisticsResource> {

    @Resource(name = "playerConverter")
    private PlayerConverter playerConverter;


    //  METHODS

    @Override
    public PlayerGameStatisticsResource convert(PlayerGameStatistics entity) {

        PlayerGameStatisticsResource resource = new PlayerGameStatisticsResource();

        resource.setCode(entity.getId());
        resource.setPlayer(this.playerConverter.convert(entity.getPlayer()));
        resource.setScore(entity.getScore());
        resource.setKills(entity.getKills());
        resource.setAssists(entity.getAssists());
        resource.setDeaths(entity.getDeaths());

        return resource;
    }

    @Override
    public Collection<PlayerGameStatisticsResource> convertAll(Collection<PlayerGameStatistics> entity) {
        if (CollectionUtils.isNotEmpty(entity)) {
            return entity.stream().map(this::convert).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
