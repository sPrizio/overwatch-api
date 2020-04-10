package com.r6overwatch.overwatchapi.models.entities.season;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Class representation of a Season in r6 siege
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Season implements OverwatchEntity {

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
    @Column
    @NonNull
    private Integer seasonYear;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer seasonNumber;

    @Getter
    @Setter
    @Column
    @NonNull
    private LocalDate releaseDate;
}
