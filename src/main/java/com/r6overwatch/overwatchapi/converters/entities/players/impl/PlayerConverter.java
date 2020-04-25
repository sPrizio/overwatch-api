package com.r6overwatch.overwatchapi.converters.entities.players.impl;

import com.r6overwatch.overwatchapi.converters.entities.players.PlayersConverter;
import com.r6overwatch.overwatchapi.converters.entities.players.statistics.PlayerSeasonStatisticsConverter;
import com.r6overwatch.overwatchapi.converters.entities.season.SeasonConverter;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.players.PlayerResource;
import com.r6overwatch.overwatchapi.resources.entities.players.statistics.PlayerSeasonStatisticsResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Converter for {@link Player} entities into {@link PlayerResource} objects
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class PlayerConverter implements PlayersConverter<Player, PlayerResource> {

    @Resource(name = "playerSeasonStatisticsConverter")
    private PlayerSeasonStatisticsConverter playerSeasonStatisticsConverter;

    @Resource(name = "seasonConverter")
    private SeasonConverter seasonConverter;


    //  METHODS

    @Override
    public PlayerResource convertHighlighted(Player entity, Season season) {

        PlayerResource resource = convert(entity);

        if (season != null) {
            Optional<PlayerSeasonStatisticsResource> highlightedSeason =
                    resource.getPlayerSeasons()
                            .stream()
                            .filter(s -> s.getSeason().getId().equals(season.getId()))
                            .findFirst();

            highlightedSeason.ifPresent(resource::setHighlightedSeason);
        }

        return resource;
    }

    @Override
    public List<PlayerResource> convertAllHighlighted(List<Player> entity, Season season) {
        if (CollectionUtils.isNotEmpty(entity) && season != null) {
            return entity.stream().map(e -> convertHighlighted(e, season)).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public PlayerResource convert(Player entity) {

        PlayerResource resource = new PlayerResource();

        if (entity != null) {
            resource.setCode(entity.getId());
            resource.setAlias(entity.getAlias());
            resource.setName(entity.getName());
            resource.setPlayerSeasons(new TreeSet<>(this.playerSeasonStatisticsConverter.convertAll(entity.getPlayerSeasons())));
            resource.setMostRecentSeason(this.seasonConverter.convert(entity.getMostRecentSeason()));
            resource.setCurrentSeason(this.playerSeasonStatisticsConverter.convert(entity.getCurrentSeason()));
        }

        return resource;
    }

    @Override
    public Collection<PlayerResource> convertAll(Collection<Player> entity) {
        if (CollectionUtils.isNotEmpty(entity)) {
            return entity.stream().map(this::convert).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
