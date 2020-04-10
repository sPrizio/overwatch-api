package com.r6overwatch.overwatchapi.controllers.ui;

import com.r6overwatch.overwatchapi.enums.SortOrder;
import com.r6overwatch.overwatchapi.facades.entities.players.PlayerFacade;
import com.r6overwatch.overwatchapi.facades.entities.players.SquadFacade;
import com.r6overwatch.overwatchapi.facades.entities.season.SeasonFacade;
import com.r6overwatch.overwatchapi.facades.nonentities.impl.PlayerStatsFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Resource(name = "playerFacade")
    private PlayerFacade playerFacade;

    @Resource(name = "playerStatsFacade")
    private PlayerStatsFacade playerStatsFacade;

    @Resource(name = "seasonFacade")
    private SeasonFacade seasonFacade;

    @Resource(name = "squadFacade")
    private SquadFacade squadFacade;


    //  METHODS

    @GetMapping
    public String index(final Model model) {
        model.addAttribute("players", this.playerFacade.findPlayersBySquadAndSeasonSortedByAttribute(1L, "kd", this.seasonFacade.getCurrentSeason().getId(), SortOrder.DESC));
        model.addAttribute("stephen", this.playerFacade.find(1L));
        model.addAttribute("stephenCareer", this.playerStatsFacade.obtainStats(1L));
        model.addAttribute("squad", this.squadFacade.find(1L));
        model.addAttribute("season", this.seasonFacade.getCurrentSeason());
        model.addAttribute("paolo", this.playerFacade.find(2L));
        model.addAttribute("alex", this.playerFacade.find(3L));
        model.addAttribute("vince", this.playerFacade.find(4L));
        model.addAttribute("ant", this.playerFacade.find(5L));
        return "index";
    }
}
