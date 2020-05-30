package com.r6overwatch.overwatchapi.controllers.api.games;

import com.r6overwatch.overwatchapi.controllers.AbstractOverwatchController;
import com.r6overwatch.overwatchapi.controllers.response.StandardJsonResponse;
import com.r6overwatch.overwatchapi.facades.entities.games.GameFacade;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.games.GameResource;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import com.r6overwatch.overwatchapi.validation.result.ValidationResult;
import com.r6overwatch.overwatchapi.validation.validators.impl.games.GameValidator;
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
import java.util.Map;

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

    @Resource(name = "gameValidator")
    private GameValidator gameValidator;


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
     * Finds all {@link Game}s for the given {@link Player} code and for the specific {@link Season} code
     *
     * @param playerCode code of the desired {@link Player}
     * @param seasonCode code of the desired {@link Season}
     * @param limitCode limits the result set to the given size
     * @return list of {@link Game}s sorted by their date in descending order
     */
    @GetMapping("/games/player")
    @ApiOperation("Fetches games by player and for a specific season. Primary use case is for obtaining a player's most recent games")
    public StandardJsonResponse getRecentGamesForPlayer(
            final @RequestParam("playerCode") @ApiParam("Code for the desired player") String playerCode,
            final @RequestParam("seasonCode") @ApiParam("Code for the season to be obtained") String seasonCode,
            final @RequestParam("limitCode") @ApiParam("Numerical count for the results, i.e. how many results should be returned") String limitCode
    ) {

        Long playerId = OverwatchUtils.parseLong(playerCode);
        Long seasonId = OverwatchUtils.parseLong(seasonCode);
        Integer limit = OverwatchUtils.parseInteger(limitCode);

        if (OverwatchUtils.areNonNull(playerId, seasonId, limit)) {
            List<GameResource> games = this.gameFacade.findGamesByPlayerAndSeasonSortedByDateLimited(playerId, seasonId, limit);

            if (CollectionUtils.isNotEmpty(games)) {
                return new StandardJsonResponse(true, games, StringUtils.EMPTY);
            }
        }  else {
            LOGGER.error("One or more of the required params was null or empty. playerCode {}, seasonCode {}, limitCode {}", playerCode, seasonCode, limitCode);
            return new StandardJsonResponse(false, null, "One or more of the required params was null or empty. playerCode " + playerCode + ", seasonCode " + seasonCode + ", limitCode " + limitCode);
        }

        LOGGER.error("No results were found for playerCode {}, seasonCode {}, limitCode {}", playerCode, seasonCode, limit);
        return new StandardJsonResponse(false, null, "No results were found for playerCode " + playerCode + ", seasonCode " + seasonCode + ", limitCode " + limitCode);
    }

    /**
     * Finds all {@link Game}s for the given {@link Squad} code and for the specific {@link Season} code
     *
     * @param squadCode code of the desired {@link Squad}
     * @param seasonCode code of the desired {@link Season}
     * @param limitCode limits the result set to the given size
     * @return list of {@link Game}s sorted by their date in descending order
     */
    @GetMapping("/games/squad")
    @ApiOperation("Fetches games by squad and for a specific season. Primary use case is for obtaining a squad's most recent games")
    public StandardJsonResponse getRecentGamesForSquad(
            final @RequestParam("squadCode") @ApiParam("Code for the desired squad") String squadCode,
            final @RequestParam("seasonCode") @ApiParam("Code for the season to be obtained") String seasonCode,
            final @RequestParam("limitCode") @ApiParam("Numerical count for the results, i.e. how many results should be returned") String limitCode
    ) {

        Long squadId = OverwatchUtils.parseLong(squadCode);
        Long seasonId = OverwatchUtils.parseLong(seasonCode);
        Integer limit = OverwatchUtils.parseInteger(limitCode);

        if (OverwatchUtils.areNonNull(squadId, seasonId, limit)) {
            List<GameResource> games = this.gameFacade.findGamesBySquadAndSeasonSortedByDate(squadId, seasonId, limit);

            if (CollectionUtils.isNotEmpty(games)) {
                return new StandardJsonResponse(true, games, StringUtils.EMPTY);
            }
        }  else {
            LOGGER.error("One or more of the required params was null or empty. squadCode {}, seasonCode {}, limitCode {}", squadId, seasonCode, limitCode);
            return new StandardJsonResponse(false, null, "One or more of the required params was null or empty. squadCode " + squadId + ", seasonCode " + seasonCode + ", limitCode " + limitCode);
        }

        LOGGER.error("No results were found for squadCode {}, seasonCode {}, limitCode {}", squadId, seasonCode, limit);
        return new StandardJsonResponse(false, null, "No results were found for squadCode " + squadId + ", seasonCode " + seasonCode + ", limitCode " + limitCode);

    }


    //  *************** POST ***************

    /**
     * Creates a new {@link Game} in the system
     *
     * @param params request params containing information for a new {@link Game}
     * @return newly created {@link Game}
     */
    @PostMapping(value = "/enter")
    @ApiOperation("Creates a new game for the given request body. Creating a game also updates all statistical information associated with the given players and squad(s)")
    public StandardJsonResponse enterGame(final @RequestBody Map<String, Object> params) {

        ValidationResult result = this.gameValidator.validate(params);

        if (result.isValid()) {
            GameResource resource = this.gameFacade.create(params);

            if (resource != null) {
                return new StandardJsonResponse(true, resource, StringUtils.EMPTY);
            }

            LOGGER.error("The game could not be created");
            return new StandardJsonResponse(false, null, "The game could not be created");
        }

        LOGGER.error("Validation failed with message {}", result.getMessage());
        return new StandardJsonResponse(false, null, result.getMessage());
    }


    //  *************** DELETE ***************

    /**
     * Deletes a game by its id. Based on our data model, all related details classes and scoring plays
     * will also be deleted
     *
     * @param id game id
     * @return result of the deletion
     */
    @DeleteMapping("/delete/{id}")
    public StandardJsonResponse deleteGame(final @PathVariable("id") Long id) {
        this.gameFacade.delete(id);
        return new StandardJsonResponse(true, null, "Deleted game with id " + id);
    }
}
