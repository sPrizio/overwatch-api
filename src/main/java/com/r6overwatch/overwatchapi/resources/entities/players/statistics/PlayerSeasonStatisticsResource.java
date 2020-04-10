package com.r6overwatch.overwatchapi.resources.entities.players.statistics;

import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerSeasonStatistics;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import com.r6overwatch.overwatchapi.resources.entities.season.SeasonResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * A DTO for {@link PlayerSeasonStatistics}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class PlayerSeasonStatisticsResource implements OverwatchResource, Comparable<PlayerSeasonStatisticsResource> {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private SeasonResource season;

    @Getter
    @Setter
    private Integer wins;

    @Getter
    @Setter
    private Integer losses;

    @Getter
    @Setter
    private Integer kills;

    @Getter
    @Setter
    private Integer assists;

    @Getter
    @Setter
    private Integer deaths;

    @Getter
    @Setter
    private Double kd;

    @Getter
    @Setter
    private Double winPercentage;

    @Getter
    @Setter
    private Double killsPerGame;

    @Getter
    @Setter
    private Double deathsPerGame;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSeasonStatisticsResource resource = (PlayerSeasonStatisticsResource) o;
        return wins.equals(resource.wins) &&
                losses.equals(resource.losses) &&
                kills.equals(resource.kills) &&
                assists.equals(resource.assists) &&
                deaths.equals(resource.deaths) &&
                kd.equals(resource.kd) &&
                winPercentage.equals(resource.winPercentage) &&
                killsPerGame.equals(resource.killsPerGame) &&
                deathsPerGame.equals(resource.deathsPerGame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, losses, kills, assists, deaths, kd, winPercentage, killsPerGame, deathsPerGame);
    }

//  METHODS

    @Override
    public boolean isPresent() {
        return
                this.season.isPresent() &&
                this.wins != null &&
                this.losses != null &&
                this.kills != null &&
                this.assists != null &&
                this.deaths != null &&
                this.kd != null &&
                this.winPercentage != null &&
                this.killsPerGame != null &&
                this.deathsPerGame != null;
    }

    @Override
    public int compareTo(PlayerSeasonStatisticsResource o) {
        String seasonString = this.season.getSeasonYear().toString() + this.season.getSeasonNumber().toString();
        String thatString = o.getSeason().getSeasonYear().toString() + o.getSeason().getSeasonNumber().toString();
        return seasonString.compareTo(thatString);
    }
}
