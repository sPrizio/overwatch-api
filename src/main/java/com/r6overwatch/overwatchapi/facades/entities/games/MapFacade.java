package com.r6overwatch.overwatchapi.facades.entities.games;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.converters.entities.games.MapConverter;
import com.r6overwatch.overwatchapi.converters.nonentities.MapDetailsConverter;
import com.r6overwatch.overwatchapi.facades.entities.OverwatchFacade;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.games.MapResource;
import com.r6overwatch.overwatchapi.resources.nonentities.MapDetails;
import com.r6overwatch.overwatchapi.services.entities.games.GameService;
import com.r6overwatch.overwatchapi.services.entities.games.MapService;
import com.r6overwatch.overwatchapi.services.entities.players.SquadService;
import com.r6overwatch.overwatchapi.services.entities.season.SeasonService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Facade for {@link MapResource}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class MapFacade implements OverwatchFacade<MapResource> {

    @Resource(name = "gameService")
    private GameService gameService;

    @Resource(name = "mapConverter")
    private MapConverter mapConverter;

    @Resource(name = "mapDetailsConverter")
    private MapDetailsConverter mapDetailsConverter;

    @Resource(name = "mapService")
    private MapService mapService;

    @Resource(name = "seasonService")
    private SeasonService seasonService;

    @Resource(name = "squadService")
    private SquadService squadService;


    //  METHODS

    /**
     * Obtains a list of {@link MapDetails} for the given squad id
     *
     * @param squadId id of {@link Squad}
     * @param seasonId id of {@link Season}
     * @return list of {@link MapDetails}, without {@link com.r6overwatch.overwatchapi.models.entities.games.Map}s that don't have games played
     */
    public List<MapDetails> findMapDetailsForSquad(Long squadId, Long seasonId) {

        if (squadId != null) {
            Optional<Squad> squad = this.squadService.find(squadId);
            Optional<Season> season = this.seasonService.find(seasonId);

            if (squad.isPresent() && season.isPresent()) {
                List<Game> games = this.gameService.findGamesBySquadAndSeasonSortedByDate(squad.get(), season.get(), null);
                List<com.r6overwatch.overwatchapi.models.entities.games.Map> maps = this.mapService.findAll();

                if (CollectionUtils.isNotEmpty(games) && CollectionUtils.isNotEmpty(maps)) {
                    return
                            maps
                                    .stream()
                                    .map(map -> this.mapDetailsConverter.convert(games, map))
                                    .filter(details -> details.getGamesPlayed() > 0L)
                                    .sorted(Comparator.comparing(MapDetails::getWinPercentage).reversed())
                                    .collect(Collectors.toList());
                }
            }
         }

        return new ArrayList<>();
    }


    /**
     * Obtains a {@link MapResource} for the given map name
     *
     * @param name name of map
     * @return {@link MapResource} who's name matches the given string
     */
    public MapResource findMapForName(String name) {
        Optional<com.r6overwatch.overwatchapi.models.entities.games.Map> map = this.mapService.findMapForName(name);

        if (map.isPresent()) {
            return this.mapConverter.convert(map.get());
        }

        return new MapResource();
    }

    @Override
    public MapResource find(Long id) {

        Optional<com.r6overwatch.overwatchapi.models.entities.games.Map> map = this.mapService.find(id);

        if (map.isPresent()) {
            return this.mapConverter.convert(map.get());
        }

        return new MapResource();
    }

    @Override
    public List<MapResource> findAll() {
        return Lists.newArrayList(this.mapConverter.convertAll(this.mapService.findAll()));
    }

    @Override
    public MapResource create(Map<String, Object> params) {

        com.r6overwatch.overwatchapi.models.entities.games.Map map = this.mapService.create(params);

        if (map != null) {
            return this.mapConverter.convert(map);
        }

        return new MapResource();
    }

    @Override
    public void delete(Long id) {
        this.mapService.delete(id);
    }
}
