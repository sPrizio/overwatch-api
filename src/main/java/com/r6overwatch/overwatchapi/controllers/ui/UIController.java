package com.r6overwatch.overwatchapi.controllers.ui;

import com.r6overwatch.overwatchapi.enums.SortOrder;
import com.r6overwatch.overwatchapi.facades.entities.games.GameFacade;
import com.r6overwatch.overwatchapi.facades.entities.games.MapFacade;
import com.r6overwatch.overwatchapi.facades.entities.players.PlayerFacade;
import com.r6overwatch.overwatchapi.facades.entities.players.SquadFacade;
import com.r6overwatch.overwatchapi.facades.entities.season.SeasonFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Test
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/ui")
public class UIController {

    @Resource(name = "gameFacade")
    private GameFacade gameFacade;

    @Resource(name = "mapFacade")
    private MapFacade mapFacade;

    @Resource(name = "playerFacade")
    private PlayerFacade playerFacade;

    @Resource(name = "seasonFacade")
    private SeasonFacade seasonFacade;

    @Resource(name = "squadFacade")
    private SquadFacade squadFacade;


    //  METHODS

    @GetMapping("/players")
    public String index(final Model model) {
        model.addAttribute("players", this.playerFacade.findPlayersBySquadAndSeasonSortedByAttribute(1L, "kd", this.seasonFacade.getCurrentSeason().getId(), SortOrder.DESC));
        model.addAttribute("squad", this.squadFacade.find(1L));
        model.addAttribute("season", this.seasonFacade.getCurrentSeason());

        return "index";
    }

    @GetMapping("/squad/{id}")
    public String squad(final @PathVariable("id") Long id, final Model model) {
        model.addAttribute("squad", this.squadFacade.find(id));
        model.addAttribute("currentSeason", this.seasonFacade.getCurrentSeason());
        model.addAttribute("recentGames", this.gameFacade.findGamesBySquadAndSeasonSortedByDate(1L, 21L, 10));

        return "squad";
    }

    @GetMapping("/maps")
    public String maps(final Model model) {
        model.addAttribute("mapDetails", this.mapFacade.findMapDetailsForSquad(1L, 21L));
        model.addAttribute("squad", this.squadFacade.find(1L));

        return "maps";
    }
}
