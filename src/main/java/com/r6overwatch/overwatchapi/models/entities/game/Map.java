package com.r6overwatch.overwatchapi.models.entities.game;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import lombok.*;

import javax.persistence.*;

/**
 * Class representation of a map, a location for a game to take place
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Map implements OverwatchEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NonNull
    @Column
    private String name;
}
