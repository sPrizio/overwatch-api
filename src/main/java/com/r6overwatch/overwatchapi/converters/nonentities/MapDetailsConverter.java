package com.r6overwatch.overwatchapi.converters.nonentities;

import com.r6overwatch.overwatchapi.converters.entities.games.MapConverter;
import com.r6overwatch.overwatchapi.enums.MapResult;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import com.r6overwatch.overwatchapi.resources.nonentities.MapDetails;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converts a list of {@link Game} entities into a {@link MapDetails} resource
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class MapDetailsConverter {

    @Resource(name = "mapConverter")
    private MapConverter mapConverter;


    //  METHODS

    /**
     * Converts a list of {@link Game}s with the given {@link Map} into a {@link MapDetails} resource
     *
     * @param games list of {@link Game}s to search through
     * @param map {@link Map} to consider
     * @return {@link MapDetails}
     */
    public MapDetails convert(List<Game> games, Map map) {

        MapDetails details = new MapDetails();

        if (CollectionUtils.isNotEmpty(games) && map != null) {
            List<Game> filtered =
                    games
                            .stream()
                            .filter(game -> game.getMap() != null)
                            .filter(game -> game.getMap().getId().equals(map.getId()))
                            .collect(Collectors.toList());

            details.setMap(this.mapConverter.convert(map));
            details.setGamesPlayed((long) filtered.size());
            details.setWins(filtered.stream().map(Game::getSquadGameStatistics).map(SquadGameStatistics::getMapResult).filter(res -> res.equals(MapResult.WIN)).count());
            details.setLosses(filtered.stream().map(Game::getSquadGameStatistics).map(SquadGameStatistics::getMapResult).filter(res -> res.equals(MapResult.LOSS)).count());
            details.setDifferential(details.getWins() - details.getLosses());
            details.setWinPercentage(details.getPercentage());
            details.setRoundsWon(filtered.stream().map(Game::getSquadGameStatistics).mapToLong(SquadGameStatistics::getRoundsWon).sum());
            details.setRoundsLost(filtered.stream().map(Game::getSquadGameStatistics).mapToLong(SquadGameStatistics::getRoundsLost).sum());
            details.setRoundDifferential(details.getRoundsWon() - details.getRoundsLost());
        }

        return details;
    }
}
