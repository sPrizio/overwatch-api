package com.r6overwatch.overwatchapi.models.entities.players.statistics;

import com.r6overwatch.overwatchapi.enums.MapResult;
import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.game.Map;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Class representation of a squad's game result
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SquadGameStatistics implements OverwatchEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "map_id")
    @NonNull
    private Map map;

    @Getter
    @Setter
    @Column
    @NonNull
    private MapResult mapResult;

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

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PlayerGameStatistics> playerGameStatistics;
}
