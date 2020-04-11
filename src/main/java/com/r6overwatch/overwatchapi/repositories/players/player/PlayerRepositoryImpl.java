package com.r6overwatch.overwatchapi.repositories.players.player;

import com.r6overwatch.overwatchapi.enums.SortOrder;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.players.squad.SquadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link PlayerRepositoryCustom}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Repository
@SuppressWarnings("unchecked")
public class PlayerRepositoryImpl implements PlayerRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    //  METHODS

    @Override
    public List<Player> findPlayersBySquadAndSeasonSortedByAttribute(Squad squad, String attribute, Season season, SortOrder sortOrder) {

        List<Player> players = new ArrayList<>();
        Map<Long, Player> playersMap = new HashMap<>();
        squad.getPlayers().forEach(p -> playersMap.put(p.getId(), p));

        String queryString =
                "SELECT p.id, p.alias, p.name, pss.wins, pss.losses, pss.kills, pss.assists, pss.deaths, " +
                "       (pss.kills / NULLIF(pss.deaths, 0)) as kd, (pss.wins / NULLIF((pss.wins + pss.losses), 0)) as wp, (pss.kills / NULLIF((pss.wins + pss.losses), 0)) as kpg, (pss.kills / NULLIF((pss.wins + pss.losses), 0)) as dpg " +
                "FROM player as p, player_seasons as pss, player_player_seasons as rel " +
                "WHERE p.id = rel.player_id" +
                "   AND pss.id = rel.player_seasons_id " +
                "   AND pss.season_id = " + season.getId() + " " +
                "ORDER BY " + attribute + " " + sortOrder.toString() + ";";

        try {
            Query query = this.entityManager.createNativeQuery(queryString);

            query.getResultList().forEach(o -> {
                Long id = Long.parseLong(new BigInteger(((Object[]) o)[0].toString()).toString());
                players.add(playersMap.get(id));
            });
        } catch (Exception e) {
            LOGGER.error("Error executing query \n{}\n in PlayerRepositoryImpl with message {}", queryString, e.getMessage());
            return new ArrayList<>();
        }

        return players;
    }
}
