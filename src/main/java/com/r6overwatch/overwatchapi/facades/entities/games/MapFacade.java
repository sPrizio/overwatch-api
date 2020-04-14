package com.r6overwatch.overwatchapi.facades.entities.games;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.converters.entities.games.MapConverter;
import com.r6overwatch.overwatchapi.facades.entities.OverwatchFacade;
import com.r6overwatch.overwatchapi.resources.entities.game.MapResource;
import com.r6overwatch.overwatchapi.services.entities.games.MapService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Facade for {@link MapResource}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class MapFacade implements OverwatchFacade<MapResource> {

    @Resource(name = "mapConverter")
    private MapConverter mapConverter;

    @Resource(name = "mapService")
    private MapService mapService;


    //  METHODS

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
