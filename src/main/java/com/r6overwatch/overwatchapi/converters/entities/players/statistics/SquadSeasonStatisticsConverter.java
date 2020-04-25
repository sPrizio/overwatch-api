package com.r6overwatch.overwatchapi.converters.entities.players.statistics;

import com.r6overwatch.overwatchapi.converters.entities.OverwatchConverter;
import com.r6overwatch.overwatchapi.converters.entities.season.SeasonConverter;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadSeasonStatistics;
import com.r6overwatch.overwatchapi.resources.entities.players.statistics.SquadSeasonStatisticsResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Converter for {@link SquadSeasonStatistics} entities into {@link SquadSeasonStatisticsResource} objects
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SquadSeasonStatisticsConverter implements OverwatchConverter<SquadSeasonStatistics, SquadSeasonStatisticsResource> {

    @Resource(name = "seasonConverter")
    private SeasonConverter seasonConverter;


    //  METHODS

    @Override
    public SquadSeasonStatisticsResource convert(SquadSeasonStatistics entity) {

        SquadSeasonStatisticsResource resource = new SquadSeasonStatisticsResource();

        if (entity != null) {
            resource.setCode(entity.getId());
            resource.setSeason(this.seasonConverter.convert(entity.getSeason()));
            resource.setWins(entity.getWins());
            resource.setLosses(entity.getLosses());
        }

        return resource;
    }

    @Override
    public Collection<SquadSeasonStatisticsResource> convertAll(Collection<SquadSeasonStatistics> entity) {
        if (CollectionUtils.isNotEmpty(entity)) {
            return entity.stream().map(this::convert).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
