package com.r6overwatch.overwatchapi.resources.entities.games;

import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import com.r6overwatch.overwatchapi.resources.entities.players.statistics.SquadGameStatisticsResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A DTO for {@link Game}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class GameResource implements OverwatchResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private MapResource map;

    @Getter
    @Setter
    private LocalDateTime gameDateTime;

    @Getter
    @Setter
    private SquadGameStatisticsResource squadGameStatistics;


    //  METHODS

    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.map.isPresent() &&
                this.gameDateTime != null &&
                this.squadGameStatistics.isPresent();
    }
}
