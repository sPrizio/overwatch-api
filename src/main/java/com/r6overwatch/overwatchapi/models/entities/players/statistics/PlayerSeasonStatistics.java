package com.r6overwatch.overwatchapi.models.entities.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Class representation of a player's seasonal stats
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class PlayerSeasonStatistics implements OverwatchEntity, Comparable<PlayerSeasonStatistics> {

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
    private Integer kills;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer assists;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer deaths;


    //  METHODS

    /**
     * Returns the kill/death ratio
     *
     * @return returns kills divided by deaths rounded to 2 decimal places
     */
    public Double getKD() {
        if (this.deaths > 0) {
            return
                    new BigDecimal(this.kills)
                            .divide(new BigDecimal(this.deaths), new MathContext(10))
                            .setScale(2, RoundingMode.HALF_EVEN)
                            .doubleValue();
        }

        return 0.0;
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
     * Returns the kills / total matches ratio
     *
     * @return returns kills divided by total matches rounded to 2 decimal places
     */
    public Double getKillsPerGame() {
        if (getTotalMatches() > 0) {
            return
                    new BigDecimal(this.kills)
                            .divide(BigDecimal.valueOf(getTotalMatches()), new MathContext(10))
                            .setScale(2, RoundingMode.HALF_EVEN)
                            .doubleValue();
        }

        return 0.0;
    }

    /**
     * Returns the deaths / total matches ratio
     *
     * @return returns deaths divided by total matches rounded to 2 decimal places
     */
    public Double getDeathsPerGame() {
        if (getTotalMatches() > 0) {
            return
                    new BigDecimal(this.deaths)
                    .divide(BigDecimal.valueOf(getTotalMatches()), new MathContext(10))
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .doubleValue();
        }

        return 0.0;
    }

    @Override
    public int compareTo(PlayerSeasonStatistics seasonStatistics) {
        String seasonString = this.season.getSeasonYear().toString() + this.season.getSeasonNumber().toString();
        String thatString = seasonStatistics.getSeason().getSeasonYear().toString() + seasonStatistics.getSeason().getSeasonNumber().toString();
        return seasonString.compareTo(thatString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSeasonStatistics that = (PlayerSeasonStatistics) o;
        return wins.equals(that.wins) &&
                losses.equals(that.losses) &&
                kills.equals(that.kills) &&
                assists.equals(that.assists) &&
                deaths.equals(that.deaths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, losses, kills, assists, deaths);
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
