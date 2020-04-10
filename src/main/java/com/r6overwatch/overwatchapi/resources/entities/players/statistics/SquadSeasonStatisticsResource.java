package com.r6overwatch.overwatchapi.resources.entities.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadSeasonStatistics;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import com.r6overwatch.overwatchapi.resources.entities.season.SeasonResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for {@link SquadSeasonStatistics}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class SquadSeasonStatisticsResource implements OverwatchResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private SeasonResource season;

    @Getter
    @Setter
    private Integer wins;

    @Getter
    @Setter
    private Integer losses;


    //  METHODS

    @Override
    public boolean isPresent() {
        return
                this.season.isPresent() &&
                this.wins != null &&
                this.losses != null;
    }
}
