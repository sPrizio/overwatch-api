package com.r6overwatch.overwatchapi.resources.entities.players;

import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import com.r6overwatch.overwatchapi.resources.entities.players.statistics.PlayerSeasonStatisticsResource;
import com.r6overwatch.overwatchapi.resources.entities.season.SeasonResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * A DTO for {@link Player}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class PlayerResource implements OverwatchResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String alias;

    @Getter
    @Setter
    private Set<PlayerSeasonStatisticsResource> playerSeasons;

    @Getter
    @Setter
    private SeasonResource mostRecentSeason;

    @Getter
    @Setter
    private PlayerSeasonStatisticsResource currentSeason;

    @Getter
    @Setter
    private PlayerSeasonStatisticsResource highlightedSeason;


    //  METHODS

    @Override
    public boolean isPresent() {
        return
                StringUtils.isNotEmpty(this.name) &&
                StringUtils.isNotEmpty(this.alias) &&
                CollectionUtils.isNotEmpty(this.playerSeasons) &&
                this.mostRecentSeason.isPresent() &&
                this.currentSeason.isPresent();
    }
}
