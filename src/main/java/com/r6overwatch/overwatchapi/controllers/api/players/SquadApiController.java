package com.r6overwatch.overwatchapi.controllers.api.players;

import com.r6overwatch.overwatchapi.controllers.AbstractOverwatchController;
import com.r6overwatch.overwatchapi.controllers.response.StandardJsonResponse;
import com.r6overwatch.overwatchapi.facades.entities.players.SquadFacade;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.resources.entities.players.SquadResource;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Controller that exposes various endpoints for information about {@link Squad}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/squad")
@Api(description = "Handles all operations relating to a squad and/or squads in the system")
public class SquadApiController extends AbstractOverwatchController<SquadResource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SquadApiController.class);

    @Resource(name = "squadFacade")
    private SquadFacade squadFacade;


    //  METHODS

    //  *************** GET ***************

    /**
     * Finds a {@link Squad} for the given code
     *
     * @param code code of {@link Squad}
     * @return json response with data based on whether the {@link Squad} was found
     */
    @GetMapping("/{code}")
    @ApiOperation("Fetches a squad by squad code")
    public StandardJsonResponse getSquad(final @PathVariable("code") String code) {

        Long id = OverwatchUtils.parseLong(code);

        if (id != null) {
            return findEntity(id, this.squadFacade.find(id));
        }

        LOGGER.error("Invalid squad code given {}", code);
        return new StandardJsonResponse(false, null, "Invalid squad code given: " + code);
    }

    /**
     * Finds all the {@link Squad}s in the system
     *
     * @return list of all {@link Squad}s
     */
    @GetMapping("/all")
    @ApiOperation("Fetches all the squads in the system")
    public StandardJsonResponse getAllSquads() {
        return new StandardJsonResponse(true, this.squadFacade.findAll(), StringUtils.EMPTY);
    }
}
