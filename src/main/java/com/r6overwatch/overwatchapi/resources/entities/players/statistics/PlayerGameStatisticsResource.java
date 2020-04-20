package com.r6overwatch.overwatchapi.resources.entities.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import com.r6overwatch.overwatchapi.resources.entities.players.PlayerResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * A DTO for {@link PlayerGameStatistics}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class PlayerGameStatisticsResource implements OverwatchResource, Comparable<PlayerGameStatisticsResource> {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private PlayerResource player;

    @Getter
    @Setter
    private Integer score;

    @Getter
    @Setter
    private Integer kills;

    @Getter
    @Setter
    private Integer assists;

    @Getter
    @Setter
    private Integer deaths;


    //  METHODS

    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.player.isPresent() &&
                this.score != null &&
                this.kills != null &&
                this.assists != null &&
                this.deaths != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlayerGameStatisticsResource resource = (PlayerGameStatisticsResource) o;
        return this.code.equals(resource.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

    @Override
    public int compareTo(PlayerGameStatisticsResource o) {
        return o.score.compareTo(this.score);
    }
}
