package com.r6overwatch.overwatchapi.models.entities.players;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadSeasonStatistics;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Class representation of an r6 squad
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Squad implements OverwatchEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column
    @NonNull
    private String name;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SquadSeasonStatistics> squadSeasonStatistics;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Player> players;
}
