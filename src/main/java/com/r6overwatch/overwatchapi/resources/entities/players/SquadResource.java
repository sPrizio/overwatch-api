package com.r6overwatch.overwatchapi.resources.entities.players;

import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import com.r6overwatch.overwatchapi.resources.entities.players.statistics.SquadSeasonStatisticsResource;
import com.r6overwatch.overwatchapi.resources.entities.season.SeasonResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * A DTO for {@link Squad}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class SquadResource implements OverwatchResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Set<SquadSeasonStatisticsResource> squadSeasons;

    @Getter
    @Setter
    private Set<PlayerResource> players;

    @Getter
    @Setter
    private SeasonResource mostRecentSeason;

    @Getter
    @Setter
    private SquadSeasonStatisticsResource currentSeason;

    @Getter
    @Setter
    private SquadSeasonStatisticsResource highlightedSeason;


    //  METHODS

    @Override
    public boolean isPresent() {
        return
                StringUtils.isNotEmpty(this.name) &&
                CollectionUtils.isNotEmpty(this.squadSeasons) &&
                CollectionUtils.isNotEmpty(this.players);
    }
}
