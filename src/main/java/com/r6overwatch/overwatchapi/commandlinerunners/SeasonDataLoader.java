package com.r6overwatch.overwatchapi.commandlinerunners;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.season.SeasonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * Loads test data for {@link Season}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
@Order(2)
public class SeasonDataLoader implements CommandLineRunner {

    @Resource(name = "seasonRepository")
    private SeasonRepository seasonRepository;


    //  METHODS

    @Override
    public void run(String... args) {
        Season season11 = new Season("Operation Black Ice", 1, 1, LocalDate.of(2016, 2, 1));
        Season season12 = new Season("Operation Dust Line", 1, 2, LocalDate.of(2016, 5, 1));
        Season season13 = new Season("Operation Skull Rain", 1, 3, LocalDate.of(2016, 8, 1));
        Season season14 = new Season("Operation Red Crow", 1, 4, LocalDate.of(2016, 11, 1));

        Season season21 = new Season("Operation Velvet Shell", 2, 1, LocalDate.of(2017, 2, 1));
        Season season22 = new Season("Operation Health", 2, 2, LocalDate.of(2017, 5, 1));
        Season season23 = new Season("Operation Blood Orchid", 2, 3, LocalDate.of(2017, 8, 1));
        Season season24 = new Season("Operation White Noise", 2, 4, LocalDate.of(2017, 11, 1));

        Season season31 = new Season("Operation Chimera", 3, 1, LocalDate.of(2018, 3, 1));
        Season season32 = new Season("Operation Para Bellum", 3, 2, LocalDate.of(2018, 6, 1));
        Season season33 = new Season("Operation Grim Sky", 3, 3, LocalDate.of(2018, 9, 1));
        Season season34 = new Season("Operation Wind Bastion", 3, 4, LocalDate.of(2018, 12, 1));

        Season season41 = new Season("Operation Burnt Horizon", 4, 1, LocalDate.of(2019, 3, 1));
        Season season42 = new Season("Operation Phantom Sight", 4, 2, LocalDate.of(2019, 6, 1));
        Season season43 = new Season("Operation Ember Rise", 4, 3, LocalDate.of(2019, 9, 1));
        Season season44 = new Season("Operation Shifting Tides", 4, 4, LocalDate.of(2019, 12, 1));

        Season season51 = new Season("Operation Void Edge", 5, 1, LocalDate.of(2020, 3, 1));

        this.seasonRepository.saveAll(Lists.newArrayList(
                season11,
                season12,
                season13,
                season14,
                season21,
                season22,
                season23,
                season24,
                season31,
                season32,
                season33,
                season34,
                season41,
                season42,
                season43,
                season44,
                season51
        ));
    }
}
