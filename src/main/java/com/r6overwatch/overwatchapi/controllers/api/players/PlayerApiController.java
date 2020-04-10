package com.r6overwatch.overwatchapi.controllers.api.players;

import com.r6overwatch.overwatchapi.controllers.AbstractOverwatchController;
import com.r6overwatch.overwatchapi.controllers.response.StandardJsonResponse;
import com.r6overwatch.overwatchapi.enums.SortOrder;
import com.r6overwatch.overwatchapi.facades.entities.players.PlayerFacade;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.players.PlayerResource;
import com.r6overwatch.overwatchapi.services.nonentities.enums.EnumService;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller that exposes various endpoints for information about {@link Player}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/player")
public class PlayerApiController extends AbstractOverwatchController<PlayerResource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerApiController.class);

    @Resource(name = "enumService")
    private EnumService<SortOrder> enumService;

    @Resource(name = "playerFacade")
    private PlayerFacade playerFacade;


    //  METHODS

    //  *************** GET ***************

    /**
     * Finds a {@link Player} for the given code
     *
     * @param code code of {@link Player}
     * @return json response with data based on whether the {@link Player} was found
     */
    @GetMapping("/{code}")
    public StandardJsonResponse getPlayer(final @PathVariable("code") String code) {

        Long id = OverwatchUtils.parseLong(code);

        if (id != null) {
            return findEntity(id, this.playerFacade.find(id));
        }

        LOGGER.error("Invalid player code given {}", code);
        return new StandardJsonResponse(false, null, "Invalid player code given: " + code);
    }

    /**
     * Finds all the {@link Player}s in the system
     *
     * @return list of all {@link Player}s
     */
    @GetMapping("/all")
    public StandardJsonResponse getAllPlayers() {
        return new StandardJsonResponse(true, this.playerFacade.findAll(), StringUtils.EMPTY);
    }

    /**
     * Finds all {@link Player}s by {@link Squad} and {@link Season}, sorted by a specific attribute
     *
     * @param squadCode id of the {@link Player}'s {@link Squad}
     * @param attribute specific attribute to sort by, example {@link Player} names
     * @param seasonCode id of the given {@link Season}
     * @param sortCode {@link SortOrder} for the result set
     * @return {@link List} of {@link Player}s sorted by attribute in the given order for the squad and season
     */
    @GetMapping("/players")
    public StandardJsonResponse getAllPlayersForTeamAndSeasonAndAttributeSorted(
            final @RequestParam("squadCode") String squadCode,
            final @RequestParam("seasonCode") String seasonCode,
            final @RequestParam("attribute") String attribute,
            final @RequestParam("sortCode") String sortCode
    ) {
        if (StringUtils.isEmpty(attribute)) {
            LOGGER.error("No attribute specified for sorting");
            return new StandardJsonResponse(false, null, "No attribute specified for sorting");
        }

        Long squadId = OverwatchUtils.parseLong(squadCode);
        Long seasonId = OverwatchUtils.parseLong(seasonCode);
        SortOrder sortOrder = this.enumService.getEnum(SortOrder.class, sortCode);

        if (OverwatchUtils.areNonNull(squadId, seasonId, sortOrder)) {
            List<PlayerResource> players = this.playerFacade.findPlayersBySquadAndSeasonSortedByAttribute(squadId, attribute, seasonId, sortOrder);

            if (CollectionUtils.isNotEmpty(players)) {
                return new StandardJsonResponse(true, players, StringUtils.EMPTY);
            }
        } else {
            LOGGER.error("One or more of the required params was null or empty. squadCode {}, seasonCode {}, sortCode {}", squadCode, seasonCode, sortCode);
            return new StandardJsonResponse(false, null, "One or more of the required params was null or empty. squadCode " + squadCode + ", seasonCode " + seasonCode + ", sortCode " + sortCode);
        }

        LOGGER.error("No results were found for squadCode {}, seasonCode {}, sortCode {}", squadCode, seasonCode, sortCode);
        return new StandardJsonResponse(false, null, "No results were found for squadCode " + squadCode + ", seasonCode " + seasonCode + ", sortCode " + sortCode);
    }
}
