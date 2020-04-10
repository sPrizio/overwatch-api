package com.r6overwatch.overwatchapi.models.entities.players;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerSeasonStatistics;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;

/**
 * Class representation of an r6 player
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Player implements OverwatchEntity {

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
    private String alias;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PlayerSeasonStatistics> playerSeasons;


    //  METHODS

    /**
     * Obtains the player's most recently played season
     *
     * @return {@link Season} who'se release date is closest to today's date
     */
    public Season getMostRecentSeason() {
        if (CollectionUtils.isNotEmpty(this.playerSeasons)) {
            return this.playerSeasons
                    .stream()
                    .map(PlayerSeasonStatistics::getSeason)
                    .filter(season -> season.getReleaseDate().isBefore(LocalDate.now()))
                    .max(Comparator.comparing(Season::getReleaseDate))
                    .orElse(new Season());
        }

        return null;
    }

    /**
     * Obtains the player's most recently played season statistics
     *
     * @return {@link PlayerSeasonStatistics} who's release date is closest to today's date
     */
    public PlayerSeasonStatistics getCurrentSeason() {
        Season season = getMostRecentSeason();
        return this.playerSeasons.stream().filter(s -> s.getSeason().getId().equals(season.getId())).findFirst().orElse(null);
    }
}
