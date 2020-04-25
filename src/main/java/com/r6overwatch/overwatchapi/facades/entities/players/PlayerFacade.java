package com.r6overwatch.overwatchapi.facades.entities.players;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.converters.entities.players.impl.PlayerConverter;
import com.r6overwatch.overwatchapi.enums.DateInterval;
import com.r6overwatch.overwatchapi.enums.SortOrder;
import com.r6overwatch.overwatchapi.facades.entities.OverwatchFacade;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.models.nonentities.StatsGraphWrapper;
import com.r6overwatch.overwatchapi.resources.entities.players.PlayerResource;
import com.r6overwatch.overwatchapi.services.entities.players.PlayerService;
import com.r6overwatch.overwatchapi.services.entities.players.SquadService;
import com.r6overwatch.overwatchapi.services.entities.season.SeasonService;
import com.r6overwatch.overwatchapi.utils.OverwatchUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Facade for {@link PlayerResource}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class PlayerFacade implements OverwatchFacade<PlayerResource> {

    @Resource(name = "playerConverter")
    private PlayerConverter playerConverter;

    @Resource(name = "playerService")
    private PlayerService playerService;

    @Resource(name = "seasonService")
    private SeasonService seasonService;

    @Resource(name = "squadService")
    private SquadService squadService;


    //  METHODS

    /**
     * Returns a list of {@link StatsGraphWrapper} objects for graphing of a player's recent performance for the given attribute
     *
     * @param stat stat to consider, ex: kills
     * @param playerId id of {@link Player} who's stats to obtain
     * @param dateInterval {@link DateInterval} timeline to consider
     * @return list of {@link StatsGraphWrapper}
     */
    public List<StatsGraphWrapper> findPlayerStatsForRecentGamesByStat(String stat, Long playerId, DateInterval dateInterval) {

        if (OverwatchUtils.areNonNull(playerId, dateInterval)) {
            Optional<Player> player = this.playerService.find(playerId);

            if (player.isPresent()) {
                return this.playerService.findPlayerStatsForRecentGamesByStat(stat, player.get(), dateInterval);
            }
        }

        return new ArrayList<>();
    }

    /**
     * Obtains all {@link Player}s by {@link Squad} and {@link Season}, sorted by a specific attribute
     *
     * @param squadId id of the {@link Player}'s {@link Squad}
     * @param attribute specific attribute to sort by, example {@link Player} names
     * @param seasonId id of the given {@link Season}
     * @param sortOrder {@link SortOrder} for the result set
     * @return {@link List} of {@link Player}s sorted by attribute in the given order for the squad and season
     */
    public List<PlayerResource> findPlayersBySquadAndSeasonSortedByAttribute(Long squadId, String attribute, Long seasonId, SortOrder sortOrder) {

        if (OverwatchUtils.areNonNull(squadId, seasonId, sortOrder)) {
            Optional<Squad> squad = this.squadService.find(squadId);
            Optional<Season> season = this.seasonService.find(seasonId);

            if (squad.isPresent() && season.isPresent()) {
                return Lists.newArrayList(this.playerConverter.convertAllHighlighted(this.playerService.findPlayersBySquadAndSeasonSortedByAttribute(squad.get(), attribute, season.get(), sortOrder), season.get()));
            }
        }

        return new ArrayList<>();
    }

    @Override
    public PlayerResource find(Long id) {

        Optional<Player> player = this.playerService.find(id);

        if (player.isPresent()) {
            return this.playerConverter.convert(player.get());
        }

        return new PlayerResource();
    }

    @Override
    public List<PlayerResource> findAll() {
        return Lists.newArrayList(this.playerConverter.convertAll(this.playerService.findAll()));
    }

    @Override
    public PlayerResource create(Map<String, Object> params) {

        Player player = this.playerService.create(params);

        if (player != null) {
            return this.playerConverter.convert(player);
        }

        return new PlayerResource();
    }

    @Override
    public void delete(Long id) {
        this.playerService.delete(id);
    }
}
