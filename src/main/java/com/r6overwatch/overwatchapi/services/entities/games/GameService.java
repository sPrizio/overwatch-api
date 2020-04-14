package com.r6overwatch.overwatchapi.services.entities.games;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.games.game.GameRepository;
import com.r6overwatch.overwatchapi.services.entities.OverwatchEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the {@link OverwatchEntityService} architecture for {@link Game}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GameService implements OverwatchEntityService<Game> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Resource(name = "gameRepository")
    private GameRepository gameRepository;


    //  METHODS

    /**
     * Finds games for the given {@link Squad} and {@link Season}
     *
     * @param squad {@link Squad}'s games to obtain
     * @param season {@link Season} desired season
     * @return list of {@link Game}s sorted by recency with regards to date time
     */
    public List<Game> findGamesBySquadAndSeasonSortedByDate(Squad squad, Season season) {

        if (squad == null || season == null) {
            LOGGER.error("One or more of the required parameters was null or empty: squad {}, season {}", squad, season);
            return new ArrayList<>();
        }

        return this.gameRepository.findGamesBySquadAndSeasonSortedByDate(squad, season);
    }

    @Override
    public void refresh(Game entity) {
        this.gameRepository.refresh(entity);
    }

    @Override
    public Optional<Game> find(Long id) {
        return this.gameRepository.findById(id);
    }

    @Override
    public List<Game> findAll() {
        return Lists.newArrayList(this.gameRepository.findAll());
    }

    @Override
    public Game save(Game entity) {
        return this.gameRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        find(id).ifPresent(game -> this.gameRepository.deleteById(game.getId()));
    }

    @Override
    public Game create(Map<String, Object> params) {
        //  TODO: implement this method once we're ready to include POST
        return null;
    }
}
