package com.r6overwatch.overwatchapi.controllers.api.games;

import com.r6overwatch.overwatchapi.controllers.AbstractOverwatchController;
import com.r6overwatch.overwatchapi.controllers.response.StandardJsonResponse;
import com.r6overwatch.overwatchapi.facades.entities.games.GameFacade;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.games.GameResource;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller that exposes various endpoints for information about {@link Game}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/game")
@Api(description = "Handles all operations relating to a player and/or players in the system")
public class GameApiController extends AbstractOverwatchController<GameResource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameApiController.class);

    @Resource(name = "gameFacade")
    private GameFacade gameFacade;


    //  METHODS

    //  *************** GET ***************

    /**
     * Finds a {@link Game} for the given code
     *
     * @param code code of {@link Game}
     * @return json response with data based on whether the {@link Game} was found
     */
    @GetMapping("/{code}")
    @ApiOperation("Fetches a game by game code")
    public StandardJsonResponse getGame(final @PathVariable("code") String code) {

        Long id = OverwatchUtils.parseLong(code);

        if (id != null) {
            return findEntity(id, this.gameFacade.find(id));
        }

        LOGGER.error("Invalid game code given {}", code);
        return new StandardJsonResponse(false, null, "Invalid game code given " + code);
    }

    /**
     * Finds all the {@link Game}s in the system
     *
     * @return list of all {@link Game}s
     */
    @GetMapping("/all")
    @ApiOperation("Fetches all games in the system")
    public StandardJsonResponse getAllGames() {
        return new StandardJsonResponse(true, this.gameFacade.findAll(), StringUtils.EMPTY);
    }

    /**
     * Finds all {@link Game}s for the given {@link Squad} code and for the specific {@link Season} code
     *
     * @param squadCode code of the desired {@link Squad}
     * @param seasonCode code of the desired {@link Season}
     * @return list of {@link Game}s sorted by their date in descending order
     */
    @GetMapping("/games")
    @ApiOperation("Fetches all games by their squad and for a specific season")
    public StandardJsonResponse getAllGamesForSquadAndSeason(
            final @RequestParam("squadCode") @ApiParam("Code for the desired squad") String squadCode,
            final @RequestParam("seasonCode") @ApiParam("Code for the season to be obtained") String seasonCode
    ) {


        Long squadId = OverwatchUtils.parseLong(squadCode);
        Long seasonId = OverwatchUtils.parseLong(seasonCode);

        if (OverwatchUtils.areNonNull(squadId, seasonId)) {
            List<GameResource> games = this.gameFacade.findGamesBySquadAndSeasonSortedByDate(squadId, seasonId);

            if (CollectionUtils.isNotEmpty(games)) {
                return new StandardJsonResponse(true, games, StringUtils.EMPTY);
            }
        } else {
            LOGGER.error("One or more of the required params was null or empty. squadCode {}, seasonCode {}", squadCode, seasonCode);
            return new StandardJsonResponse(false, null, "One or more of the required params was null or empty. squadCode " + squadCode + ", seasonCode " + seasonCode);
        }

        LOGGER.error("No results were found for squadCode {}, seasonCode {}", squadCode, seasonCode);
        return new StandardJsonResponse(false, null, "No results were found for squadCode " + squadCode + ", seasonCode " + seasonCode);
    }
}