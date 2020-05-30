package com.r6overwatch.overwatchapi.resources.entities.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import com.r6overwatch.overwatchapi.resources.entities.players.SquadResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * A DTO for {@link SquadGameStatistics}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class SquadGameStatisticsResource implements OverwatchResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private SquadResource squad;

    @Getter
    @Setter
    private String gameSide;

    @Getter
    @Setter
    private String mapResult;

    @Getter
    @Setter
    private Integer roundsWon;

    @Getter
    @Setter
    private Integer roundsLost;

    @Getter
    @Setter
    private Set<PlayerGameStatisticsResource> playerGameStatistics;


    //  METHODS

    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.squad.isPresent() &&
                StringUtils.isNotEmpty(this.gameSide) &&
                StringUtils.isNotEmpty(this.mapResult) &&
                this.roundsWon != null &&
                this.roundsLost != null &&
                CollectionUtils.isNotEmpty(this.playerGameStatistics);
    }
}
