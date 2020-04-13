package com.r6overwatch.overwatchapi.commandlinerunners;

import com.google.common.collect.Sets;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerSeasonStatistics;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadSeasonStatistics;
import com.r6overwatch.overwatchapi.repositories.players.player.PlayerRepository;
import com.r6overwatch.overwatchapi.repositories.players.squad.SquadRepository;
import com.r6overwatch.overwatchapi.repositories.season.SeasonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Loads test data for {@link Player}, {@link Squad}, {@link PlayerSeasonStatistics} and {@link PlayerGameStatistics}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
@Order(3)
@SuppressWarnings("DuplicatedCode")
public class PlayersDataLoader implements CommandLineRunner {

    @Resource(name = "playerRepository")
    private PlayerRepository playerRepository;

    @Resource(name = "seasonRepository")
    private SeasonRepository seasonRepository;

    @Resource(name = "squadRepository")
    private SquadRepository squadRepository;


    //  METHODS

    @Override
    public void run(String... args) {
        loadPlayerData();
        loadSquadData();
        loadPlayerSeasonStatistics();
        loadSquadSeasonStatistics();
    }


    //  HELPERS

    /**
     * Loads {@link Player} data
     */
    private void loadPlayerData() {
        this.playerRepository.save(new Player("Stephen Prizio", "xxPlazmaBurstxx"));
        this.playerRepository.save(new Player("Paolo Drago", "KushMonster55"));
        this.playerRepository.save(new Player("Alessandro Drago", "Odysseus155"));
        this.playerRepository.save(new Player("Vince Morello", "BrutalKilla47"));
        this.playerRepository.save(new Player("Anthony Frenza", "XMonsterAntX"));
    }

    /**
     * Loads {@link Squad} data
     */
    private void loadSquadData() {
        Squad squad = new Squad("Team Pay2Win");
        squad = this.squadRepository.save(squad);
        squad.setPlayers(Sets.newHashSet(this.playerRepository.findAll()));
        this.squadRepository.save(squad);
    }

    /**
     * Loads {@link PlayerSeasonStatistics} data
     */
    private void loadPlayerSeasonStatistics() {
        Optional<Player> stephen = this.playerRepository.findById(1L);
        if (stephen.isPresent()) {
            PlayerSeasonStatistics s31 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 1), 16, 16, 194, 59, 179);
            PlayerSeasonStatistics s32 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 2), 155, 131, 1750, 458, 1606);
            PlayerSeasonStatistics s33 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 3), 156, 140, 938, 477, 737);
            PlayerSeasonStatistics s34 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 4), 189, 163, 1624, 561, 1194);

            PlayerSeasonStatistics s41 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 1), 163, 140, 1563, 486, 1147);
            PlayerSeasonStatistics s42 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 2), 133, 132, 1333, 426, 1053);
            PlayerSeasonStatistics s43 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 3), 81, 78, 586, 258, 504);
            PlayerSeasonStatistics s44 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 4), 74, 70, 686, 235, 610);

            PlayerSeasonStatistics s51 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(5, 1), 45, 44, 493, 149, 367);

            stephen.get().setPlayerSeasons(Sets.newHashSet(s31, s32, s33, s34, s41, s42, s43, s44, s51));
            this.playerRepository.save(stephen.get());
        }

        Optional<Player> paolo = this.playerRepository.findById(2L);
        if (paolo.isPresent()) {
            PlayerSeasonStatistics s22 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(2, 2), 129, 127, 1087, 452, 1116);
            PlayerSeasonStatistics s23 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(2, 3), 1, 0, 4, 2, 4);
            PlayerSeasonStatistics s24 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(2, 4), 2, 3, 25, 11, 26);

            PlayerSeasonStatistics s31 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 1), 72, 74, 633, 263, 650);
            PlayerSeasonStatistics s32 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 2), 239, 224, 1966, 818, 2019);
            PlayerSeasonStatistics s33 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 3), 145, 116, 797, 461, 763);
            PlayerSeasonStatistics s34 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 4), 139, 129, 755, 473, 788);

            PlayerSeasonStatistics s41 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 1), 147, 141, 852, 510, 1037);
            PlayerSeasonStatistics s42 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 2), 149, 137, 1045, 509, 1210);
            PlayerSeasonStatistics s43 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 3), 80, 79, 541, 281, 520);
            PlayerSeasonStatistics s44 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 4), 73, 65, 712, 244, 626);

            PlayerSeasonStatistics s51 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(5, 1), 40, 39, 384, 140, 367);

            paolo.get().setPlayerSeasons(Sets.newHashSet(s22, s23, s24, s31, s32, s33, s34, s41, s42, s43, s44, s51));
            this.playerRepository.save(paolo.get());
        }

        Optional<Player> alex = this.playerRepository.findById(3L);
        if (alex.isPresent()) {
            PlayerSeasonStatistics s22 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(2, 2), 201, 180, 1518, 451, 1445);
            PlayerSeasonStatistics s23 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(2, 3), 32, 28, 243, 72, 231);
            PlayerSeasonStatistics s24 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(2, 4), 16, 8, 96, 28, 91);

            PlayerSeasonStatistics s31 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 1), 103, 100, 813, 241, 774);
            PlayerSeasonStatistics s32 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 2), 52, 46, 390, 116, 372);
            PlayerSeasonStatistics s33 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 3), 14, 13, 117, 32, 103);
            PlayerSeasonStatistics s34 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 4), 28, 18, 183, 54, 174);

            PlayerSeasonStatistics s41 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 1), 18, 9, 108, 32, 103);
            PlayerSeasonStatistics s42 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 2), 73, 67, 502, 167, 555);
            PlayerSeasonStatistics s43 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 3), 83, 74, 385, 186, 323);
            PlayerSeasonStatistics s44 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 4), 46, 41, 361, 103, 397);

            PlayerSeasonStatistics s51 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(5, 1), 25, 23, 199, 57, 207);

            alex.get().setPlayerSeasons(Sets.newHashSet(s22, s23, s24, s31, s32, s33, s34, s41, s42, s43, s44, s51));
            this.playerRepository.save(alex.get());
        }

        Optional<Player> vince = this.playerRepository.findById(4L);
        if (vince.isPresent()) {
            PlayerSeasonStatistics s22 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(2, 2), 87, 93, 656, 300, 802);

            PlayerSeasonStatistics s31 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 1), 88, 84, 631, 289, 771);
            PlayerSeasonStatistics s32 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 2), 85, 84, 355, 163, 434);
            PlayerSeasonStatistics s33 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 3), 62, 68, 471, 216, 576);
            PlayerSeasonStatistics s34 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 4), 115, 107, 804, 368, 984);

            PlayerSeasonStatistics s41 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 1), 107, 105, 768, 351, 940);
            PlayerSeasonStatistics s42 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 2), 78, 83, 554, 267, 689);
            PlayerSeasonStatistics s43 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 3), 87, 85, 353, 287, 453);
            PlayerSeasonStatistics s44 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 4), 55, 55, 399, 182, 525);

            PlayerSeasonStatistics s51 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(5, 1), 34, 28, 229, 103, 287);

            vince.get().setPlayerSeasons(Sets.newHashSet(s22, s31, s32, s33, s34, s41, s42, s43, s44, s51));
            this.playerRepository.save(vince.get());
        }

        Optional<Player> ant = this.playerRepository.findById(5L);
        if (ant.isPresent()) {
            PlayerSeasonStatistics s22 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(2, 2), 13, 13, 94, 39, 108);
            PlayerSeasonStatistics s24 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(2, 4), 1, 2, 11, 4, 12);

            PlayerSeasonStatistics s31 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 1), 28, 25, 192, 79, 220);
            PlayerSeasonStatistics s32 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 2), 95, 85, 651, 269, 746);
            PlayerSeasonStatistics s33 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 3), 31, 25, 157, 84, 197);
            PlayerSeasonStatistics s34 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(3, 4), 70, 63, 481, 199, 551);

            PlayerSeasonStatistics s41 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 1), 67, 62, 467, 193, 534);
            PlayerSeasonStatistics s42 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 2), 49, 53, 372, 153, 429);
            PlayerSeasonStatistics s43 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 3), 33, 31, 100, 96, 133);
            PlayerSeasonStatistics s44 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(4, 4), 38, 32, 261, 105, 299);

            PlayerSeasonStatistics s51 = new PlayerSeasonStatistics(this.seasonRepository.findBySeasonYearAndSeasonNumber(5, 1), 17, 16, 132, 49, 144);

            ant.get().setPlayerSeasons(Sets.newHashSet(s22, s24, s31, s32, s33, s34, s41, s42, s43, s44, s51));
            this.playerRepository.save(ant.get());
        }
    }

    /**
     * Loads {@link SquadSeasonStatistics} data
     */
    private void loadSquadSeasonStatistics() {
        //  TODO: implement this method
    }
}
