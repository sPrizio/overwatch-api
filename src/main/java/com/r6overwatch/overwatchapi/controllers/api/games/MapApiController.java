package com.r6overwatch.overwatchapi.controllers.api.games;

import com.r6overwatch.overwatchapi.controllers.AbstractOverwatchController;
import com.r6overwatch.overwatchapi.controllers.response.StandardJsonResponse;
import com.r6overwatch.overwatchapi.facades.entities.games.MapFacade;
import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.resources.entities.games.MapResource;
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
 * Controller that exposes various endpoints for information about {@link Map}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/map")
@Api(description = "Handles all operations relating to a map and/or maps in the system")
public class MapApiController extends AbstractOverwatchController<MapResource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapApiController.class);

    @Resource(name = "mapFacade")
    private MapFacade mapFacade;


    //  METHODS

    //  *************** GET ***************

    /**
     * Finds a {@link Map} for the given code
     *
     * @param code code of {@link Map}
     * @return json response with data based on whether the {@link Map} was found
     */
    @GetMapping("/{code}")
    @ApiOperation("Fetches a map by map code")
    public StandardJsonResponse getMap(final @PathVariable("code") String code) {

        Long id = OverwatchUtils.parseLong(code);

        if (id != null) {
            return findEntity(id, this.mapFacade.find(id));
        }

        LOGGER.error("Invalid map code given {}", code);
        return new StandardJsonResponse(false, null, "Invalid map code given: " + code);
    }

    /**
     * Finds all the {@link Map}s in the system
     *
     * @return list of all {@link Map}s
     */
    @GetMapping("/all")
    @ApiOperation("Fetches all maps in the system")
    public StandardJsonResponse getAllMaps() {
        return new StandardJsonResponse(true, this.mapFacade.findAll(), StringUtils.EMPTY);
    }

    /**
     * Finds a {@link Map} by name
     *
     * @param name name of {@link Map}
     * @return {@link Map} who's name matches the given string
     */
    @GetMapping("/name")
    @ApiOperation("Fetches a map by its name")
    public StandardJsonResponse getMapForName(final @RequestParam("name") @ApiParam("Name of the desired map") String name) {

        if (StringUtils.isEmpty(name)) {
            LOGGER.error("The required param 'name' was null or empty");
            return new StandardJsonResponse(false, null, "The required param 'name' was null or empty");
        }

        MapResource resource = this.mapFacade.findMapForName(name);

        if (resource.isPresent()) {
            return new StandardJsonResponse(true, resource, StringUtils.EMPTY);
        }

        LOGGER.error("No map found for name {}", name);
        return new StandardJsonResponse(false, null, "No map found for name " + name);
    }
}
