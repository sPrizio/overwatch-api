package com.r6overwatch.overwatchapi.models.entities.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import lombok.*;

import javax.persistence.*;

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


    //  METHODS

    /**
     * Increments the number wins by 1 for this {@link SquadSeasonStatistics}
     */
    public void incrementWins() {
        this.wins += 1;
    }

    /**
     * Increments the number losses by 1 for this {@link SquadSeasonStatistics}
     */
    public void incrementLosses() {
        this.losses += 1;
    }
}
