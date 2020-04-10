package com.r6overwatch.overwatchapi.facades.entities.season;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.converters.entities.season.SeasonConverter;
import com.r6overwatch.overwatchapi.facades.entities.OverwatchFacade;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.season.SeasonResource;
import com.r6overwatch.overwatchapi.services.entities.season.SeasonService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Facade for {@link SeasonResource}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SeasonFacade implements OverwatchFacade<SeasonResource> {

    @Resource(name = "seasonConverter")
    private SeasonConverter seasonConverter;

    @Resource(name = "seasonService")
    private SeasonService seasonService;


    //  METHODS

    /**
     * Obtains a {@link SeasonResource} who's season year and season number match the given input
     * Examples of inputs would be 5, 1 for Y5 S1
     *
     * @param seasonYear season year since inception of r6 siege
     * @param seasonNumber season number meaning which numerical delineation of the year we're in
     * @return {@link Season} if found, null otherwise
     */
    public SeasonResource getSeasonForSeasonYearAndSeasonNumber(Integer seasonYear, Integer seasonNumber) {

        Optional<Season> season = this.seasonService.getSeasonForSeasonYearAndSeasonNumber(seasonYear, seasonNumber);

        if (season.isPresent()) {
            return this.seasonConverter.convert(season.get());
        }

        return new SeasonResource();
    }

    /**
     * Obtains the current season, i.e. the season of siege that is currently going on for today's date
     *
     * @return {@link SeasonResource} object who's release date is closest to today's date
     */
    public SeasonResource getCurrentSeason() {

        Optional<Season> season = this.seasonService.getCurrentSeason();

        if (season.isPresent()) {
            return this.seasonConverter.convert(season.get());
        }

        return new SeasonResource();
    }

    @Override
    public SeasonResource find(Long id) {

        Optional<Season> season = this.seasonService.find(id);

        if (season.isPresent()) {
            return this.seasonConverter.convert(season.get());
        }

        return new SeasonResource();
    }

    @Override
    public List<SeasonResource> findAll() {
        return Lists.newArrayList(this.seasonConverter.convertAll(this.seasonService.findAll()));
    }

    @Override
    public SeasonResource create(Map<String, Object> params) {

        Season season = this.seasonService.create(params);

        if (season != null) {
            return this.seasonConverter.convert(season);
        }

        return new SeasonResource();
    }

    @Override
    public void delete(Long id) {
        this.seasonService.delete(id);
    }
}
