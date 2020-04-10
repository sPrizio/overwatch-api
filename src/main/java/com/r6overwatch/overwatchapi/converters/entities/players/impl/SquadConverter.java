package com.r6overwatch.overwatchapi.converters.entities.players.impl;

import com.google.common.collect.Sets;
import com.r6overwatch.overwatchapi.converters.entities.players.PlayersConverter;
import com.r6overwatch.overwatchapi.converters.entities.players.statistics.SquadSeasonStatisticsConverter;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.players.SquadResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter for {@link Squad} entities into {@link SquadResource} objects
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SquadConverter implements PlayersConverter<Squad, SquadResource> {

    @Resource(name = "playerConverter")
    private PlayerConverter playerConverter;

    @Resource(name = "squadSeasonStatisticsConverter")
    private SquadSeasonStatisticsConverter squadSeasonStatisticsConverter;


    //  METHODS

    @Override
    public SquadResource convertHighlighted(Squad entity, Season season) {
        return null;
    }

    @Override
    public List<SquadResource> convertAllHighlighted(List<Squad> entity, Season season) {
        return null;
    }

    @Override
    public SquadResource convert(Squad entity) {

        SquadResource resource = new SquadResource();

        if (entity != null) {
            resource.setCode(entity.getId());
            resource.setName(entity.getName());
            resource.setPlayers(Sets.newHashSet(this.playerConverter.convertAll(entity.getPlayers())));
            resource.setSquadSeasonStatistics(Sets.newHashSet(this.squadSeasonStatisticsConverter.convertAll(entity.getSquadSeasonStatistics())));
        }

        return resource;
    }

    @Override
    public Collection<SquadResource> convertAll(Collection<Squad> entity) {

        if (CollectionUtils.isNotEmpty(entity)) {
            return entity.stream().map(this::convert).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
