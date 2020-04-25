package com.r6overwatch.overwatchapi.models.entities.players.statistics;

import com.r6overwatch.overwatchapi.enums.GameSide;
import com.r6overwatch.overwatchapi.enums.MapResult;
import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
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
    @JoinColumn(name = "squad_id")
    @NonNull
    private Squad squad;

    @Getter
    @Setter
    @Column
    @NonNull
    private GameSide gameSide;

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
    @NonNull
    private Set<PlayerGameStatistics> playerGameStatistics;
}
