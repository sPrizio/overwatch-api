package com.r6overwatch.overwatchapi.converters.entities.season;

import com.r6overwatch.overwatchapi.converters.entities.OverwatchConverter;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.season.SeasonResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Converter for {@link Season} entities into {@link SeasonResource} objects
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SeasonConverter implements OverwatchConverter<Season, SeasonResource> {


    //  METHODS

    @Override
    public SeasonResource convert(Season entity) {

        SeasonResource resource = new SeasonResource();

        if (entity != null) {
            resource.setId(entity.getId());
            resource.setName(entity.getName());
            resource.setReleaseDate(entity.getReleaseDate());
            resource.setSeasonYear(entity.getSeasonYear());
            resource.setSeasonNumber(entity.getSeasonNumber());
        }

        return resource;
    }

    @Override
    public Collection<SeasonResource> convertAll(Collection<Season> entity) {
        if (CollectionUtils.isNotEmpty(entity)) {
            return entity.stream().map(this::convert).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
