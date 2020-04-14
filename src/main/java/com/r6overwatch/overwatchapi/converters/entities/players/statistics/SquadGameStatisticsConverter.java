package com.r6overwatch.overwatchapi.converters.entities.players.statistics;

import com.google.common.collect.Sets;
import com.r6overwatch.overwatchapi.converters.entities.OverwatchConverter;
import com.r6overwatch.overwatchapi.converters.entities.players.impl.SquadConverter;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import com.r6overwatch.overwatchapi.resources.entities.players.statistics.SquadGameStatisticsResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Converter for {@link SquadGameStatistics} entities into {@link SquadGameStatisticsResource} objects
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SquadGameStatisticsConverter implements OverwatchConverter<SquadGameStatistics, SquadGameStatisticsResource> {

    @Resource(name = "squadConverter")
    private SquadConverter squadConverter;

    @Resource(name = "playerGameStatisticsConverter")
    private PlayerGameStatisticsConverter playerGameStatisticsConverter;

    //  METHODS

    @Override
    public SquadGameStatisticsResource convert(SquadGameStatistics entity) {

        SquadGameStatisticsResource resource = new SquadGameStatisticsResource();

        resource.setCode(entity.getId());
        resource.setSquad(this.squadConverter.convert(entity.getSquad()));
        resource.setMapResult(entity.getMapResult().toString().toLowerCase());
        resource.setRoundsWon(entity.getRoundsWon());
        resource.setRoundsLost(entity.getRoundsLost());
        resource.setPlayerGameStatistics(Sets.newHashSet(this.playerGameStatisticsConverter.convertAll(entity.getPlayerGameStatistics())));

        return resource;
    }

    @Override
    public Collection<SquadGameStatisticsResource> convertAll(Collection<SquadGameStatistics> entity) {
        if (CollectionUtils.isNotEmpty(entity)) {
            return entity.stream().map(this::convert).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
