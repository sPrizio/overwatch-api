package com.r6overwatch.overwatchapi.controllers.api.season;

import com.r6overwatch.overwatchapi.controllers.AbstractOverwatchController;
import com.r6overwatch.overwatchapi.controllers.response.StandardJsonResponse;
import com.r6overwatch.overwatchapi.facades.entities.season.SeasonFacade;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.season.SeasonResource;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
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
    public StandardJsonResponse getAllSeasons() {
        return new StandardJsonResponse(true, this.seasonFacade.findAll(), StringUtils.EMPTY);
    }

    /**
     * Finds the {@link Season} who's release date is closest to today's date
     *
     * @return {@link SeasonResource} of today's closest season
     */
    @GetMapping("/current-season")
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
    public StandardJsonResponse getSeasonForNumberAndYear(final @RequestParam("seasonYear") String year, final @RequestParam("seasonNumber") String number) {

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
        return new StandardJsonResponse(false, number, "One or more of the required params was null or empty: year " + year + ", number " + number);
    }
}
