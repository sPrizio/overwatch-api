package com.r6overwatch.overwatchapi.models.entities.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Class representation of a squad's seasonal stats
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SquadSeasonStatistics implements OverwatchEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "season_id")
    @NonNull
    private Season season;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer wins;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer losses;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer roundsWon;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer roundsLost;


    //  METHODS

    /**
     * Increments the number wins by 1 for this {@link SquadSeasonStatistics}
     *
     * @param invert if true, subtract
     */
    public void incrementWins(boolean invert) {
        this.wins += invert ? -1 : 1;
    }

    /**
     * Increments the number losses by 1 for this {@link SquadSeasonStatistics}
     *
     * @param invert if true, subtract
     */
    public void incrementLosses(boolean invert) {
        this.losses += invert ? -1 : 1;
    }

    /**
     * Increments rounds won by specified increment amount
     *
     * @param increment amount to increment rounds won by
     */
    public void incrementRoundsWon(Integer increment) {
        this.roundsWon += increment;
    }

    /**
     * Increments rounds lost by specified increment amount
     *
     * @param increment amount to increment rounds lost by
     */
    public void incrementRoundsLost(Integer increment) {
        this.roundsLost += increment;
    }

    /**
     * Returns the wins / total matches ratio
     *
     * @return returns wins divided by total matches rounded to 2 decimal places
     */
    public Double getWinPercentage() {
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

    /**
     * Returns the difference of rounds win and rounds lost
     *
     * @return rounds won - rounds lost
     */
    public Integer getDifferential() {
        return this.roundsWon - this.roundsLost;
    }

    /**
     * Returns the total number of games played
     *
     * @return wins + losses
     */
    public Integer getGamesPlayed() {
        return this.wins + this.losses;
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
