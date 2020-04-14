package com.r6overwatch.overwatchapi.models.entities.games;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class representation of a game between 2 teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Game implements OverwatchEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_map_id")
    @NonNull
    private Map map;

    @Getter
    @Setter
    @Column
    @NonNull
    private LocalDateTime gameDateTime;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blueTeamStatistics_id")
    @NonNull
    private SquadGameStatistics blueTeamStatistics;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orangeTeamStatistics_id")
    @NonNull
    private SquadGameStatistics orangeTeamStatistics;

}
