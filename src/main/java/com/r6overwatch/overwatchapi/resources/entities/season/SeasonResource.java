package com.r6overwatch.overwatchapi.resources.entities.season;

import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

/**
 * A DTO for {@link Season}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class SeasonResource implements OverwatchResource {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer seasonYear;

    @Getter
    @Setter
    private Integer seasonNumber;

    @Getter
    @Setter
    private LocalDate releaseDate;


    //  METHODS

    @Override
    public boolean isPresent() {
        return
                StringUtils.isNotEmpty(this.name) &&
                this.seasonYear != null &&
                this.seasonNumber != null &&
                this.releaseDate != null;
    }
}
