package com.r6overwatch.overwatchapi.services.entities.players;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.enums.SortOrder;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.players.player.PlayerRepository;
import com.r6overwatch.overwatchapi.services.entities.OverwatchEntityService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the {@link OverwatchEntityService} architecture for {@link Player}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class PlayerService implements OverwatchEntityService<Player> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

    @Resource(name = "playerRepository")
    private PlayerRepository playerRepository;


    //  METHODS

    /**
     * Finds players for the given {@link Player} attribute and {@link SortOrder}. A {@link Season} is used
     * to refine results as well as a {@link Squad} to limit results to team members
     *
     * @param squad the {@link Squad} that this {@link Player} belongs to
     * @param attribute the {@link Player} attribute that we wish to sort by
     * @param season the {@link Season}
     * @param sortOrder the {@link SortOrder} for the result list
     * @return list of {@link Player}s in the given {@link SortOrder}
     */
    public List<Player> findPlayersBySquadAndSeasonSortedByAttribute(Squad squad, String attribute, Season season, SortOrder sortOrder) {

        if (squad == null || StringUtils.isEmpty(attribute) || season == null || sortOrder == null) {
            LOGGER.error("One or more of the required parameters was null or empty: squad {}, attribute {}, season {}, sortOrder {}", squad, attribute, season, sortOrder);
            return new ArrayList<>();
        }

        return this.playerRepository.findPlayersBySquadAndSeasonSortedByAttribute(squad, attribute, season, sortOrder);
    }

    @Override
    public void refresh(Player entity) {
        this.playerRepository.refresh(entity);
    }

    @Override
    public Optional<Player> find(Long id) {
        return this.playerRepository.findById(id);
    }

    @Override
    public List<Player> findAll() {
        return Lists.newArrayList(this.playerRepository.findAll());
    }

    @Override
    public Player save(Player entity) {
        return this.playerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        find(id).ifPresent(player -> this.playerRepository.deleteById(player.getId()));
    }

    @Override
    public Player create(Map<String, Object> params) {
        //  TODO: implement this method once we're ready to include POST
        return null;
    }
}
