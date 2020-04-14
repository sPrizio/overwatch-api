package com.r6overwatch.overwatchapi.repositories.games.game;

import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link GameRepositoryCustom}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Repository
public class GameRepositoryImpl implements GameRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    //  METHODS

    @Override
    public List<Game> findGamesBySquadAndSeasonSortedByDate(Squad squad, Season season) {
        LOGGER.error("findGamesBySquadAndSeasonSortedByDate() is not yet implemented!");
        //  TODO: implement this method
        return new ArrayList<>();
    }
}
