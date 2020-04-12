package com.r6overwatch.overwatchapi.controllers.api.season;

import com.r6overwatch.overwatchapi.controllers.AbstractOverwatchController;
import com.r6overwatch.overwatchapi.controllers.response.StandardJsonResponse;
import com.r6overwatch.overwatchapi.facades.entities.season.SeasonFacade;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.season.SeasonResource;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Controller that exposes various endpoints for information about {@link Season}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/season")
@Api(description = "Handles all operations relating to a season and/or seasons in the system")
public class SeasonApiController extends AbstractOverwatchController<SeasonResource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeasonApiController.class);

    @Resource(name = "seasonFacade")
    private SeasonFacade seasonFacade;


    //  METHODS

    //  *************** GET ***************

    /**
     * Finds a {@link Season} for the given code
     *
     * @param code code of {@link Season}
     * @return json response with data based on whether the {@link Season} was found
     */
    @GetMapping("/{code}")
    @ApiOperation("Fetches a season by season code")
    public StandardJsonResponse getSeason(final @PathVariable("code") String code) {

        Long id = OverwatchUtils.parseLong(code);

        if (id != null) {
            return findEntity(id, this.seasonFacade.find(id));
        }

        LOGGER.error("Invalid season code given {}", code);
        return new StandardJsonResponse(false, null, "Invalid season code given: " + code);
    }

    /**
     * Finds all the {@link Season}s in the system
     *
     * @return list of all {@link Season}s
     */
    @GetMapping("/all")
    @ApiOperation("Fetches all seasons in the system")
    public StandardJsonResponse getAllSeasons() {
        return new StandardJsonResponse(true, this.seasonFacade.findAll(), StringUtils.EMPTY);
    }

    /**
     * Finds the {@link Season} who's release date is closest to today's date
     *
     * @return {@link SeasonResource} of today's closest season
     */
    @GetMapping("/current-season")
    @ApiOperation("Fetches the current season, i.e. the season who's release date is closest to today's date")
    public StandardJsonResponse getCurrentSeason() {
        SeasonResource resource = this.seasonFacade.getCurrentSeason();
        if (resource.isPresent()) {
            return new StandardJsonResponse(true, resource, StringUtils.EMPTY);
        }

        return new StandardJsonResponse(false, null, "No current season was found");
    }

    /**
     * Finds the {@link Season} for the given year and season number
     *
     * @param year season year since inception of r6 siege
     * @param number season number meaning which numerical delineation of the year we're in
     * @return {@link SeasonResource}
     */
    @GetMapping("/searchable")
    @ApiOperation("Fetches the season for the given season year and number, ex: 5 and 1 for Season Void Edge (Y5, S1)")
    public StandardJsonResponse getSeasonForNumberAndYear(
            final @RequestParam("seasonYear") @ApiParam("The year number from the start of siege (current year minus 2015)") String year,
            final @RequestParam("seasonNumber") @ApiParam("The season number which roughly corresponds to the current quarter") String number
    ) {

        Integer yearNum = OverwatchUtils.parseInteger(year);
        Integer numberNum = OverwatchUtils.parseInteger(number);

        if (yearNum != null && numberNum != null) {
            SeasonResource resource = this.seasonFacade.getSeasonForSeasonYearAndSeasonNumber(yearNum, numberNum);
            if (resource.isPresent()) {
                return new StandardJsonResponse(true, resource, StringUtils.EMPTY);
            }

            LOGGER.error("No season found for year {} and season number {}", yearNum, numberNum);
            return new StandardJsonResponse(false, null, "No season found for year " + yearNum + " and season number " + numberNum);
        }

        LOGGER.error("One or more of the required params was null or empty: year {}, number {}", year, number);
        return new StandardJsonResponse(false, null, "One or more of the required params was null or empty: year " + year + ", number " + number);
    }
}
