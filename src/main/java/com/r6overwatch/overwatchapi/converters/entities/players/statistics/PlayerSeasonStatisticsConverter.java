package com.r6overwatch.overwatchapi.converters.entities.players.statistics;

import com.r6overwatch.overwatchapi.converters.entities.OverwatchConverter;
import com.r6overwatch.overwatchapi.converters.entities.season.SeasonConverter;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerSeasonStatistics;
import com.r6overwatch.overwatchapi.resources.entities.players.statistics.PlayerSeasonStatisticsResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Converter for {@link PlayerSeasonStatistics} entities into {@link PlayerSeasonStatisticsResource} objects
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class PlayerSeasonStatisticsConverter implements OverwatchConverter<PlayerSeasonStatistics, PlayerSeasonStatisticsResource> {

    @Resource(name = "seasonConverter")
    private SeasonConverter seasonConverter;


    //  METHODS

    @Override
    public PlayerSeasonStatisticsResource convert(PlayerSeasonStatistics entity) {

        PlayerSeasonStatisticsResource resource = new PlayerSeasonStatisticsResource();

        if (entity != null) {
            resource.setCode(entity.getId());
            resource.setSeason(this.seasonConverter.convert(entity.getSeason()));
            resource.setGamesPlayed(entity.getGamesPlayed());
            resource.setKills(entity.getKills());
            resource.setAssists(entity.getAssists());
            resource.setDeaths(entity.getDeaths());
            resource.setKd(entity.getKD());
            resource.setWins(entity.getWins());
            resource.setLosses(entity.getLosses());
            resource.setWinPercentage(entity.getWinPercentage());
            resource.setKillsPerGame(entity.getKillsPerGame());
            resource.setDeathsPerGame(entity.getDeathsPerGame());
        }

        return resource;
    }

    @Override
    public Collection<PlayerSeasonStatisticsResource> convertAll(Collection<PlayerSeasonStatistics> entity) {
        if (CollectionUtils.isNotEmpty(entity)) {
            return entity.stream().map(this::convert).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
