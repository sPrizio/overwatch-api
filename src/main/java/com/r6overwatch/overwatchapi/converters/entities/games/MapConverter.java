package com.r6overwatch.overwatchapi.converters.entities.games;

import com.r6overwatch.overwatchapi.converters.entities.OverwatchConverter;
import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.resources.entities.games.MapResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Converter for {@link Map} entities into {@link MapResource} objects
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class MapConverter implements OverwatchConverter<Map, MapResource> {


    //  METHODS

    @Override
    public MapResource convert(Map entity) {

        MapResource resource = new MapResource();

        resource.setCode(entity.getId());
        resource.setName(entity.getName());

        return resource;
    }

    @Override
    public Collection<MapResource> convertAll(Collection<Map> entity) {
        if (CollectionUtils.isNotEmpty(entity)) {
            return entity.stream().map(this::convert).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
