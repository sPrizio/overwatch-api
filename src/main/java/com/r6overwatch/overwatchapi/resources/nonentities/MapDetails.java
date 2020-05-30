package com.r6overwatch.overwatchapi.resources.nonentities;

import com.r6overwatch.overwatchapi.resources.entities.games.MapResource;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Class representation of a squad's performance for a given map
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public class MapDetails {

    @Getter
    @Setter
    private MapResource map;

    @Getter
    @Setter
    private Long gamesPlayed;

    @Getter
    @Setter
    private Long wins;

    @Getter
    @Setter
    private Long losses;

    @Getter
    @Setter
    private Double winPercentage;

    @Getter
    @Setter
    private Long differential;

    @Getter
    @Setter
    private Long roundsWon;

    @Getter
    @Setter
    private Long roundsLost;

    @Getter
    @Setter
    private Long roundDifferential;


    //  METHODS

    /**
     * Returns the wins / total matches ratio
     *
     * @return returns wins divided by total matches rounded to 2 decimal places
     */
    public Double getPercentage() {
        if (getTotalMatches() > 0) {
            return
                    new BigDecimal(this.wins)
                            .divide(BigDecimal.valueOf(getTotalMatches()), new MathContext(10))
                            .multiply(new BigDecimal(100))
                            .setScale(2, RoundingMode.HALF_EVEN)
                            .doubleValue();
        }

        return 0.0;
    }


    //  HELPERS

    /**
     * Returns the total number of matches played
     *
     * @return sum of wins and losses
     */
    private Double getTotalMatches() {
        return (this.wins * 1.0) + (this.losses * 1.0);
    }
}
