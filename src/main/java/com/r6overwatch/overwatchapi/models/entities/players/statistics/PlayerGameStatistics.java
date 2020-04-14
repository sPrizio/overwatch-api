package com.r6overwatch.overwatchapi.models.entities.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import lombok.*;

import javax.persistence.*;

/**
 * Class representation of a player's statistics for a game
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class PlayerGameStatistics implements OverwatchEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    @NonNull
    private Player player;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer score;

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
}
