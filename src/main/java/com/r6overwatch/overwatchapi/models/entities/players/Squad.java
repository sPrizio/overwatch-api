package com.r6overwatch.overwatchapi.models.entities.players;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadSeasonStatistics;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
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
    private Set<SquadSeasonStatistics> squadSeasons;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Player> players;


    //  METHODS

    /**
     * Obtains the squad's most recently played season
     *
     * @return {@link Season} who'se release date is closest to today's date
     */
    public Season getMostRecentSeason() {
        if (CollectionUtils.isNotEmpty(this.squadSeasons)) {
            return this.squadSeasons
                    .stream()
                    .map(SquadSeasonStatistics::getSeason)
                    .filter(season -> season.getReleaseDate().isBefore(LocalDate.now()))
                    .max(Comparator.comparing(Season::getReleaseDate))
                    .orElse(new Season());
        }

        return null;
    }

    /**
     * Obtains the squad's most recently played season statistics
     *
     * @return {@link SquadSeasonStatistics} who's release date is closest to today's date
     */
    public SquadSeasonStatistics getCurrentSeason() {
        Season season = getMostRecentSeason();
        return this.squadSeasons.stream().filter(s -> s.getSeason().getId().equals(season.getId())).findFirst().orElse(null);
    }

    /**
     * Finds the season for the given {@link Season}
     *
     * @param season {@link Season}
     * @return {@link SquadSeasonStatistics}
     */
    public SquadSeasonStatistics getSeasonStatisticsForSeason(Season season) {

        if (season != null) {
            return this.squadSeasons.stream().filter(s -> s.getSeason().getId().equals(season.getId())).findFirst().orElse(null);
        }

        return null;
    }
}
