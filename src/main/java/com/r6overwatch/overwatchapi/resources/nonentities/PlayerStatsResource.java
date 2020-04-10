package com.r6overwatch.overwatchapi.resources.nonentities;

import com.r6overwatch.overwatchapi.models.nonentities.StatResource;
import com.r6overwatch.overwatchapi.models.nonentities.Stats;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Front-end object for {@link Stats}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class PlayerStatsResource {

    @Getter
    @Setter
    private StatResource wins;

    @Getter
    @Setter
    private StatResource losses;

    @Getter
    @Setter
    private StatResource kills;

    @Getter
    @Setter
    private StatResource assists;

    @Getter
    @Setter
    private StatResource deaths;

    @Getter
    @Setter
    private StatResource kd;

    @Getter
    @Setter
    private StatResource winPercentage;

    @Getter
    @Setter
    private StatResource killsPerGame;

    @Getter
    @Setter
    private StatResource deathsPerGame;
}
