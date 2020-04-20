package com.r6overwatch.overwatchapi.commandlinerunners;

import com.google.common.collect.Sets;
import com.r6overwatch.overwatchapi.enums.GameSide;
import com.r6overwatch.overwatchapi.enums.MapResult;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.games.game.GameRepository;
import com.r6overwatch.overwatchapi.repositories.games.map.MapRepository;
import com.r6overwatch.overwatchapi.repositories.players.player.PlayerRepository;
import com.r6overwatch.overwatchapi.repositories.players.squad.SquadRepository;
import com.r6overwatch.overwatchapi.repositories.players.statistics.SquadGameStatisticsRepository;
import com.r6overwatch.overwatchapi.repositories.season.SeasonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Loads test data for {@link Game}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
@Order(4)
@SuppressWarnings("DuplicatedCode")
public class GameDataLoader implements CommandLineRunner {

    @Resource(name = "gameRepository")
    private GameRepository gameRepository;

    @Resource(name = "mapRepository")
    private MapRepository mapRepository;

    @Resource(name = "playerRepository")
    private PlayerRepository playerRepository;

    @Resource(name = "seasonRepository")
    private SeasonRepository seasonRepository;

    @Resource(name = "squadGameStatisticsRepository")
    private SquadGameStatisticsRepository squadGameStatisticsRepository;

    @Resource(name = "squadRepository")
    private SquadRepository squadRepository;


    //  METHODS

    @Override
    public void run(String... args) throws Exception {

        java.util.Map<String, Map> mapMap = new HashMap<>();
        this.mapRepository.findAll().forEach(m -> mapMap.put(m.getName(), m));

        Squad p2w = this.squadRepository.findById(1L).get();

        Player stephen = this.playerRepository.findById(1L).get();
        Player paolo = this.playerRepository.findById(2L).get();
        Player alex = this.playerRepository.findById(3L).get();
        Player vince = this.playerRepository.findById(4L).get();
        Player ant = this.playerRepository.findById(5L).get();

        PlayerGameStatistics stephenPgs1 = new PlayerGameStatistics(stephen, 6802, 10, 0, 1);
        PlayerGameStatistics stephenPgs2 = new PlayerGameStatistics(stephen, 1203, 2, 0, 4);
        PlayerGameStatistics stephenPgs3 = new PlayerGameStatistics(stephen, 6127, 6, 1, 1);
        PlayerGameStatistics stephenPgs4 = new PlayerGameStatistics(stephen, 3921, 13, 1, 6);
        PlayerGameStatistics stephenPgs5 = new PlayerGameStatistics(stephen, 5580, 5, 0, 3);
        PlayerGameStatistics stephenPgs6 = new PlayerGameStatistics(stephen, 5407, 1, 5, 3);
        PlayerGameStatistics stephenPgs7 = new PlayerGameStatistics(stephen, 4980, 1, 0, 4);
        PlayerGameStatistics stephenPgs8 = new PlayerGameStatistics(stephen, 3180, 3, 4, 7);
        PlayerGameStatistics stephenPgs9 = new PlayerGameStatistics(stephen, 7804, 13, 1, 3);
        PlayerGameStatistics stephenPgs10 = new PlayerGameStatistics(stephen, 3667, 7, 3, 5);

        PlayerGameStatistics stephenPgs11 = new PlayerGameStatistics(stephen, 3435, 5, 5, 7);
        PlayerGameStatistics stephenPgs12 = new PlayerGameStatistics(stephen, 5715, 4, 1, 3);
        PlayerGameStatistics stephenPgs13 = new PlayerGameStatistics(stephen, 1287, 4, 0, 5);
        PlayerGameStatistics stephenPgs14 = new PlayerGameStatistics(stephen, 6727, 9, 3, 1);
        PlayerGameStatistics stephenPgs15 = new PlayerGameStatistics(stephen, 3682, 6, 0, 2);
        PlayerGameStatistics stephenPgs16 = new PlayerGameStatistics(stephen, 3563, 4, 0, 2);
        PlayerGameStatistics stephenPgs17 = new PlayerGameStatistics(stephen, 1477, 2, 0, 4);
        PlayerGameStatistics stephenPgs18 = new PlayerGameStatistics(stephen, 1470, 4, 0, 5);
        PlayerGameStatistics stephenPgs19 = new PlayerGameStatistics(stephen, 983, 3, 1, 4);
        PlayerGameStatistics stephenPgs20 = new PlayerGameStatistics(stephen, 7230, 10, 3, 2);

        PlayerGameStatistics stephenPgs21 = new PlayerGameStatistics(stephen, 1095, 2, 0, 4);
        PlayerGameStatistics stephenPgs22 = new PlayerGameStatistics(stephen, 2025, 6, 3, 6);
        PlayerGameStatistics stephenPgs23 = new PlayerGameStatistics(stephen, 1200, 2, 1, 5);
        PlayerGameStatistics stephenPgs24 = new PlayerGameStatistics(stephen, 4567, 5, 5, 5);
        PlayerGameStatistics stephenPgs25 = new PlayerGameStatistics(stephen, 1830, 9, 0, 4);
        PlayerGameStatistics stephenPgs26 = new PlayerGameStatistics(stephen, 3775, 5, 2, 5);
        PlayerGameStatistics stephenPgs27 = new PlayerGameStatistics(stephen, 3825, 3, 0, 5);
        PlayerGameStatistics stephenPgs28 = new PlayerGameStatistics(stephen, 1970, 6, 0, 6);
        PlayerGameStatistics stephenPgs29 = new PlayerGameStatistics(stephen, 4380, 7, 2, 5);
        PlayerGameStatistics stephenPgs30 = new PlayerGameStatistics(stephen, 1350, 4, 0, 5);

        PlayerGameStatistics stephenPgs31 = new PlayerGameStatistics(stephen, 4525, 7, 1, 3);
        PlayerGameStatistics stephenPgs32 = new PlayerGameStatistics(stephen, 1645, 4, 1, 6);
        PlayerGameStatistics stephenPgs33 = new PlayerGameStatistics(stephen, 3875, 6, 2, 3);
        PlayerGameStatistics stephenPgs34 = new PlayerGameStatistics(stephen, 3710, 2, 1, 4);
        PlayerGameStatistics stephenPgs35 = new PlayerGameStatistics(stephen, 1567, 3, 1, 4);
        PlayerGameStatistics stephenPgs36 = new PlayerGameStatistics(stephen, 1455, 5, 0, 4);
        PlayerGameStatistics stephenPgs37 = new PlayerGameStatistics(stephen, 1395, 2, 0, 4);
        PlayerGameStatistics stephenPgs38 = new PlayerGameStatistics(stephen, 4295, 5, 1, 4);
        PlayerGameStatistics stephenPgs39 = new PlayerGameStatistics(stephen, 1055, 2, 2, 4);
        PlayerGameStatistics stephenPgs40 = new PlayerGameStatistics(stephen, 3695, 4, 2, 2);

        PlayerGameStatistics stephenPgs41 = new PlayerGameStatistics(stephen, 1120, 3, 2, 5);
        PlayerGameStatistics stephenPgs42 = new PlayerGameStatistics(stephen, 2101, 10, 0, 3);
        PlayerGameStatistics stephenPgs43 = new PlayerGameStatistics(stephen, 3705, 11, 1, 6);

        PlayerGameStatistics paoloPgs1 = new PlayerGameStatistics(paolo, 5821, 4, 1, 1);
        PlayerGameStatistics paoloPgs2 = new PlayerGameStatistics(paolo, 1320, 1, 1, 4);
        PlayerGameStatistics paoloPgs3 = new PlayerGameStatistics(paolo, 5550, 3, 0, 2);
        PlayerGameStatistics paoloPgs4 = new PlayerGameStatistics(paolo, 3480, 8, 2, 7);
        PlayerGameStatistics paoloPgs5 = new PlayerGameStatistics(paolo, 6481, 8, 0, 4);
        PlayerGameStatistics paoloPgs6 = new PlayerGameStatistics(paolo, 5574, 5, 1, 4);
        PlayerGameStatistics paoloPgs7 = new PlayerGameStatistics(paolo, 6525, 8, 2, 3);
        PlayerGameStatistics paoloPgs8 = new PlayerGameStatistics(paolo, 3787, 7, 1, 5);
        PlayerGameStatistics paoloPgs9 = new PlayerGameStatistics(paolo, 7080, 4, 7, 6);
        PlayerGameStatistics paoloPgs10 = new PlayerGameStatistics(paolo, 3450, 5, 2, 5);

        PlayerGameStatistics paoloPgs11 = new PlayerGameStatistics(paolo, 3285, 3, 2, 8);
        PlayerGameStatistics paoloPgs14 = new PlayerGameStatistics(paolo, 5670, 4, 2, 1);
        PlayerGameStatistics paoloPgs15 = new PlayerGameStatistics(paolo, 3845, 3, 2, 1);
        PlayerGameStatistics paoloPgs16 = new PlayerGameStatistics(paolo, 4060, 5, 4, 2);
        PlayerGameStatistics paoloPgs17 = new PlayerGameStatistics(paolo, 1195, 0, 1, 5);
        PlayerGameStatistics paoloPgs18 = new PlayerGameStatistics(paolo, 1085, 3, 1, 4);
        PlayerGameStatistics paoloPgs19 = new PlayerGameStatistics(paolo, 970, 3, 1, 5);
        PlayerGameStatistics paoloPgs20 = new PlayerGameStatistics(paolo, 8167, 13, 3, 7);

        PlayerGameStatistics paoloPgs21 = new PlayerGameStatistics(paolo, 1290, 3, 2, 4);
        PlayerGameStatistics paoloPgs23 = new PlayerGameStatistics(paolo, 1590, 6, 1, 5);
        PlayerGameStatistics paoloPgs24 = new PlayerGameStatistics(paolo, 5135, 6, 6, 4);
        PlayerGameStatistics paoloPgs26 = new PlayerGameStatistics(paolo, 4235, 7, 2, 4);
        PlayerGameStatistics paoloPgs27 = new PlayerGameStatistics(paolo, 4395, 10, 1, 3);
        PlayerGameStatistics paoloPgs28 = new PlayerGameStatistics(paolo, 2180, 8, 2, 6);
        PlayerGameStatistics paoloPgs29 = new PlayerGameStatistics(paolo, 4385, 6, 3, 7);
        PlayerGameStatistics paoloPgs30 = new PlayerGameStatistics(paolo, 1210, 4, 0, 4);

        PlayerGameStatistics paoloPgs31 = new PlayerGameStatistics(paolo, 4430, 3, 4, 5);
        PlayerGameStatistics paoloPgs32 = new PlayerGameStatistics(paolo, 2225, 5, 2, 7);
        PlayerGameStatistics paoloPgs33 = new PlayerGameStatistics(paolo, 4110, 5, 4, 5);
        PlayerGameStatistics paoloPgs34 = new PlayerGameStatistics(paolo, 4145, 6, 0, 2);
        PlayerGameStatistics paoloPgs35 = new PlayerGameStatistics(paolo, 1635, 3, 1, 4);
        PlayerGameStatistics paoloPgs38 = new PlayerGameStatistics(paolo, 4485, 4, 5, 4);
        PlayerGameStatistics paoloPgs39 = new PlayerGameStatistics(paolo, 1532, 4, 2, 5);
        PlayerGameStatistics paoloPgs40 = new PlayerGameStatistics(paolo, 4550, 9, 0, 3);

        PlayerGameStatistics paoloPgs41 = new PlayerGameStatistics(paolo, 1065, 2, 0, 5);
        PlayerGameStatistics paoloPgs42 = new PlayerGameStatistics(paolo, 1400, 4, 1, 5);
        PlayerGameStatistics paoloPgs43 = new PlayerGameStatistics(paolo, 3250, 6, 1, 6);

        PlayerGameStatistics alexPgs4 = new PlayerGameStatistics(alex, 2767, 2, 4, 7);

        PlayerGameStatistics alexPgs17 = new PlayerGameStatistics(alex, 1562, 4, 2, 4);
        PlayerGameStatistics alexPgs18 = new PlayerGameStatistics(alex, 960, 2, 1, 4);
        PlayerGameStatistics alexPgs19 = new PlayerGameStatistics(alex, 5520, 2, 1, 8);
        PlayerGameStatistics alexPgs20 = new PlayerGameStatistics(alex, 1410, 4, 1, 4);

        PlayerGameStatistics alexPgs25 = new PlayerGameStatistics(alex, 3630, 3, 2, 4);
        PlayerGameStatistics alexPgs26 = new PlayerGameStatistics(alex, 3715, 3, 1, 4);
        PlayerGameStatistics alexPgs27 = new PlayerGameStatistics(alex, 1744, 2, 3, 8);
        PlayerGameStatistics alexPgs28 = new PlayerGameStatistics(alex, 4565, 8, 2, 6);
        PlayerGameStatistics alexPgs29 = new PlayerGameStatistics(alex, 1205, 3, 2, 4);
        PlayerGameStatistics alexPgs30 = new PlayerGameStatistics(alex, 4010, 2, 3, 4);

        PlayerGameStatistics alexPgs31 = new PlayerGameStatistics(alex, 2065, 5, 2, 6);
        PlayerGameStatistics alexPgs32 = new PlayerGameStatistics(alex, 4065, 3, 2, 5);
        PlayerGameStatistics alexPgs33 = new PlayerGameStatistics(alex, 3710, 2, 1, 4);
        PlayerGameStatistics alexPgs34 = new PlayerGameStatistics(alex, 1245, 0, 1, 4);

        PlayerGameStatistics vincePgs1 = new PlayerGameStatistics(vince, 5730, 2, 3, 3);
        PlayerGameStatistics vincePgs2 = new PlayerGameStatistics(vince, 1290, 2, 1, 4);
        PlayerGameStatistics vincePgs3 = new PlayerGameStatistics(vince, 6172, 6, 0, 0);
        PlayerGameStatistics vincePgs4 = new PlayerGameStatistics(vince, 2281, 2, 1, 7);
        PlayerGameStatistics vincePgs5 = new PlayerGameStatistics(vince, 4830, 2, 0, 4);
        PlayerGameStatistics vincePgs6 = new PlayerGameStatistics(vince, 6517, 10, 0, 1);
        PlayerGameStatistics vincePgs7 = new PlayerGameStatistics(vince, 6060, 7, 0, 3);
        PlayerGameStatistics vincePgs8 = new PlayerGameStatistics(vince, 3052, 4, 2, 7);
        PlayerGameStatistics vincePgs9 = new PlayerGameStatistics(vince, 6180, 3, 0, 5);
        PlayerGameStatistics vincePgs10 = new PlayerGameStatistics(vince, 3960, 5, 5, 7);

        PlayerGameStatistics vincePgs11 = new PlayerGameStatistics(vince, 2745, 2, 2, 7);
        PlayerGameStatistics vincePgs12 = new PlayerGameStatistics(vince, 5925, 3, 2, 5);
        PlayerGameStatistics vincePgs13 = new PlayerGameStatistics(vince, 1720, 6, 0, 4);
        PlayerGameStatistics vincePgs14 = new PlayerGameStatistics(vince, 5670, 2, 3, 3);
        PlayerGameStatistics vincePgs15 = new PlayerGameStatistics(vince, 3245, 3, 0, 4);
        PlayerGameStatistics vincePgs16 = new PlayerGameStatistics(vince, 3502, 3, 0, 2);
        PlayerGameStatistics vincePgs17 = new PlayerGameStatistics(vince, 1230, 1, 0, 5);
        PlayerGameStatistics vincePgs18 = new PlayerGameStatistics(vince, 1340, 3, 1, 5);
        PlayerGameStatistics vincePgs19 = new PlayerGameStatistics(vince, 1175, 3, 2, 4);
        PlayerGameStatistics vincePgs20 = new PlayerGameStatistics(vince, 6954, 7, 4, 6);

        PlayerGameStatistics vincePgs22 = new PlayerGameStatistics(vince, 2060, 7, 2, 6);
        PlayerGameStatistics vincePgs23 = new PlayerGameStatistics(vince, 1075, 1, 0, 5);
        PlayerGameStatistics vincePgs24 = new PlayerGameStatistics(vince, 4330, 7, 0, 5);
        PlayerGameStatistics vincePgs25 = new PlayerGameStatistics(vince, 1495, 1, 4, 5);
        PlayerGameStatistics vincePgs26 = new PlayerGameStatistics(vince, 4100, 5, 4, 4);
        PlayerGameStatistics vincePgs27 = new PlayerGameStatistics(vince, 4260, 5, 2, 3);
        PlayerGameStatistics vincePgs28 = new PlayerGameStatistics(vince, 2615, 9, 2, 7);
        PlayerGameStatistics vincePgs29 = new PlayerGameStatistics(vince, 3818, 5, 1, 6);
        PlayerGameStatistics vincePgs30 = new PlayerGameStatistics(vince, 927, 2, 0, 5);

        PlayerGameStatistics vincePgs32 = new PlayerGameStatistics(vince, 1915, 2, 4, 6);
        PlayerGameStatistics vincePgs33 = new PlayerGameStatistics(vince, 3725, 5, 1, 5);
        PlayerGameStatistics vincePgs34 = new PlayerGameStatistics(vince, 3860, 4, 1, 2);
        PlayerGameStatistics vincePgs35 = new PlayerGameStatistics(vince, 1207, 1, 1, 4);

        PlayerGameStatistics antPgs17 = new PlayerGameStatistics(ant, 1095, 3, 1, 5);
        PlayerGameStatistics antPgs18 = new PlayerGameStatistics(ant, 980, 1, 1, 4);
        PlayerGameStatistics antPgs19 = new PlayerGameStatistics(ant, 6375, 2, 6, 6);
        PlayerGameStatistics antPgs20 = new PlayerGameStatistics(ant, 1560, 4, 2, 3);

        PlayerGameStatistics antPgs25 = new PlayerGameStatistics(ant, 3950, 6, 2, 5);
        PlayerGameStatistics antPgs26 = new PlayerGameStatistics(ant, 3785, 2, 3, 3);
        PlayerGameStatistics antPgs27 = new PlayerGameStatistics(ant, 1880, 3, 4, 8);
        PlayerGameStatistics antPgs28 = new PlayerGameStatistics(ant, 4421, 8, 3, 5);
        PlayerGameStatistics antPgs29 = new PlayerGameStatistics(ant, 1035, 3, 0, 3);
        PlayerGameStatistics antPgs30 = new PlayerGameStatistics(ant, 3825, 3, 1, 4);

        PlayerGameStatistics antPgs31 = new PlayerGameStatistics(ant, 2600, 8, 1, 4);
        PlayerGameStatistics antPgs32 = new PlayerGameStatistics(ant, 4100, 6, 4, 4);
        PlayerGameStatistics antPgs33 = new PlayerGameStatistics(ant, 3920, 1, 3, 2);
        PlayerGameStatistics antPgs34 = new PlayerGameStatistics(ant, 1395, 3, 0, 4);

        SquadGameStatistics sgs1 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 4, 0, Sets.newHashSet(stephenPgs1, paoloPgs1, vincePgs1));
        SquadGameStatistics sgs2 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 0, 4, Sets.newHashSet(stephenPgs2, paoloPgs2, vincePgs2));
        SquadGameStatistics sgs3 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 5, 3, Sets.newHashSet(stephenPgs3, paoloPgs3, vincePgs3));
        SquadGameStatistics sgs4 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 4, 5, Sets.newHashSet(stephenPgs4, paoloPgs4, alexPgs4, vincePgs4));
        SquadGameStatistics sgs5 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 5, 3, Sets.newHashSet(stephenPgs5, paoloPgs5, vincePgs5));
        SquadGameStatistics sgs6 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 4, 1, Sets.newHashSet(stephenPgs6, paoloPgs6, vincePgs6));
        SquadGameStatistics sgs7 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 4, 2, Sets.newHashSet(stephenPgs7, paoloPgs7, vincePgs7));
        SquadGameStatistics sgs8 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.LOSS, 3, 5, Sets.newHashSet(stephenPgs8, paoloPgs8, vincePgs8));
        SquadGameStatistics sgs9 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 5, 4, Sets.newHashSet(stephenPgs9, paoloPgs9, vincePgs9));
        SquadGameStatistics sgs10 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 4, 5, Sets.newHashSet(stephenPgs10, paoloPgs10, vincePgs10));

        SquadGameStatistics sgs11 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 3, 5, Sets.newHashSet(stephenPgs11, paoloPgs11, vincePgs11));
        SquadGameStatistics sgs12 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 4, 2, Sets.newHashSet(stephenPgs12, vincePgs12));
        SquadGameStatistics sgs13 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 1, 4, Sets.newHashSet(stephenPgs13, vincePgs13));
        SquadGameStatistics sgs14 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 4, 0, Sets.newHashSet(stephenPgs14, paoloPgs14, vincePgs14));
        SquadGameStatistics sgs15 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 4, 1, Sets.newHashSet(stephenPgs15, paoloPgs15, vincePgs15));
        SquadGameStatistics sgs16 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 4, 0, Sets.newHashSet(stephenPgs16, paoloPgs16, vincePgs16));
        SquadGameStatistics sgs17 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.LOSS, 1, 4, Sets.newHashSet(stephenPgs17, paoloPgs17, alexPgs17, vincePgs17, antPgs17));
        SquadGameStatistics sgs18 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 2, 4, Sets.newHashSet(stephenPgs18, paoloPgs18, alexPgs18, vincePgs18, antPgs18));
        SquadGameStatistics sgs19 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 1, 4, Sets.newHashSet(stephenPgs19, paoloPgs19, alexPgs19, vincePgs19, antPgs19));
        SquadGameStatistics sgs20 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 5, 4, Sets.newHashSet(stephenPgs20, paoloPgs20, alexPgs20, vincePgs20, antPgs20));

        SquadGameStatistics sgs21 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 1, 4, Sets.newHashSet(stephenPgs21, paoloPgs21));
        SquadGameStatistics sgs22 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.LOSS, 3, 5, Sets.newHashSet(stephenPgs22, vincePgs22));
        SquadGameStatistics sgs23 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.LOSS, 2, 4, Sets.newHashSet(stephenPgs23, paoloPgs23, vincePgs23));
        SquadGameStatistics sgs24 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 5, 3, Sets.newHashSet(stephenPgs24, paoloPgs24, vincePgs24));
        SquadGameStatistics sgs25 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 2, 4, Sets.newHashSet(stephenPgs25, alexPgs25, vincePgs25, antPgs25));
        SquadGameStatistics sgs26 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 4, 2, Sets.newHashSet(stephenPgs26, paoloPgs26, alexPgs26, vincePgs26, antPgs26));
        SquadGameStatistics sgs27 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 4, 2, Sets.newHashSet(stephenPgs27, paoloPgs27, alexPgs27, vincePgs27, antPgs27));
        SquadGameStatistics sgs28 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 3, 5, Sets.newHashSet(stephenPgs28, paoloPgs28, alexPgs28, vincePgs28, antPgs28));
        SquadGameStatistics sgs29 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 5, 3, Sets.newHashSet(stephenPgs29, paoloPgs29, alexPgs29, vincePgs29, antPgs29));
        SquadGameStatistics sgs30 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.LOSS, 1, 4, Sets.newHashSet(stephenPgs30, paoloPgs30, alexPgs30, vincePgs30, antPgs30));

        SquadGameStatistics sgs31 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 5, 3, Sets.newHashSet(stephenPgs31, paoloPgs31, alexPgs31, antPgs31));
        SquadGameStatistics sgs32 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 3, 5, Sets.newHashSet(stephenPgs32, paoloPgs32, alexPgs32, vincePgs32, antPgs32));
        SquadGameStatistics sgs33 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 4, 2, Sets.newHashSet(stephenPgs33, paoloPgs33, alexPgs33, vincePgs33, antPgs33));
        SquadGameStatistics sgs34 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.WIN, 4, 0, Sets.newHashSet(stephenPgs34, paoloPgs34, alexPgs34, vincePgs34, antPgs34));
        SquadGameStatistics sgs35 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 0, 4, Sets.newHashSet(stephenPgs35, paoloPgs35, vincePgs35));
        SquadGameStatistics sgs36 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 2, 4, Sets.newHashSet(stephenPgs36));
        SquadGameStatistics sgs37 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.LOSS, 1, 4, Sets.newHashSet(stephenPgs37));
        SquadGameStatistics sgs38 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 5, 3, Sets.newHashSet(stephenPgs38, paoloPgs38));
        SquadGameStatistics sgs39 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.LOSS, 1, 4, Sets.newHashSet(stephenPgs39, paoloPgs39));
        SquadGameStatistics sgs40 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.WIN, 4, 1, Sets.newHashSet(stephenPgs40, paoloPgs40));

        SquadGameStatistics sgs41 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 1, 4, Sets.newHashSet(stephenPgs41, paoloPgs41));
        SquadGameStatistics sgs42 = new SquadGameStatistics(p2w, GameSide.BLUE, MapResult.LOSS, 2, 4, Sets.newHashSet(stephenPgs42, paoloPgs42));
        SquadGameStatistics sgs43 = new SquadGameStatistics(p2w, GameSide.ORANGE, MapResult.LOSS, 3, 5, Sets.newHashSet(stephenPgs43, paoloPgs43));

        this.squadGameStatisticsRepository.saveAll(Arrays.asList(
                sgs1, sgs2, sgs3, sgs4, sgs5, sgs6, sgs7, sgs8, sgs9, sgs10,
                sgs11, sgs12, sgs13, sgs14, sgs15, sgs16, sgs17, sgs18, sgs19, sgs20,
                sgs21, sgs22, sgs23, sgs24, sgs25, sgs26, sgs27, sgs28, sgs29, sgs30,
                sgs31, sgs32, sgs33, sgs34, sgs35, sgs36, sgs37, sgs38, sgs39, sgs40,
                sgs41, sgs42, sgs43
        ));

        Season season = this.seasonRepository.findBySeasonYearAndSeasonNumber(5, 1);

        Game game1 = new Game(season, mapMap.get("Chalet"), LocalDate.of(2020, 4, 3).atTime(21, 0, 0), sgs1);
        Game game2 = new Game(season, mapMap.get("Kanal"), LocalDate.of(2020, 4, 3).atTime(21, 30, 0), sgs2);
        Game game3 = new Game(season, mapMap.get("Theme Park"), LocalDate.of(2020, 4, 3).atTime(22, 0, 0), sgs3);
        Game game4 = new Game(season, mapMap.get("Club House"), LocalDate.of(2020, 4, 3).atTime(22, 30, 0), sgs4);
        Game game5 = new Game(season, mapMap.get("Theme Park"), LocalDate.of(2020, 4, 4).atTime(21, 0, 0), sgs5);
        Game game6 = new Game(season, mapMap.get("Theme Park"), LocalDate.of(2020, 4, 4).atTime(21, 30, 0), sgs6);
        Game game7 = new Game(season, mapMap.get("Kafe Dostoyevsky"), LocalDate.of(2020, 4, 4).atTime(22, 0, 0), sgs7);
        Game game8 = new Game(season, mapMap.get("Bank"), LocalDate.of(2020, 4, 4).atTime(22, 30, 0), sgs8);
        Game game9 = new Game(season, mapMap.get("Club House"), LocalDate.of(2020, 4, 5).atTime(21, 0, 0), sgs9);
        Game game10 = new Game(season, mapMap.get("Border"), LocalDate.of(2020, 4, 5).atTime(21, 30, 0), sgs10);

        Game game11 = new Game(season, mapMap.get("Villa"), LocalDate.of(2020, 4, 5).atTime(22, 0, 0), sgs11);
        Game game12 = new Game(season, mapMap.get("Chalet"), LocalDate.of(2020, 4, 7).atTime(21, 0, 0), sgs12);
        Game game13 = new Game(season, mapMap.get("Border"), LocalDate.of(2020, 4, 7).atTime(21, 30, 0), sgs13);
        Game game14 = new Game(season, mapMap.get("Chalet"), LocalDate.of(2020, 4, 7).atTime(22, 0, 0), sgs14);
        Game game15 = new Game(season, mapMap.get("Club House"), LocalDate.of(2020, 4, 7).atTime(22, 30, 0), sgs15);
        Game game16 = new Game(season, mapMap.get("Club House"), LocalDate.of(2020, 4, 7).atTime(23, 0, 0), sgs16);
        Game game17 = new Game(season, mapMap.get("Chalet"), LocalDate.of(2020, 4, 7).atTime(23, 30, 0), sgs17);
        Game game18 = new Game(season, mapMap.get("Theme Park"), LocalDate.of(2020, 4, 8).atTime(21, 0, 0), sgs18);
        Game game19 = new Game(season, mapMap.get("Club House"), LocalDate.of(2020, 4, 8).atTime(21, 30, 0), sgs19);
        Game game20 = new Game(season, mapMap.get("Coastline"), LocalDate.of(2020, 4, 8).atTime(22, 0, 0), sgs20);

        Game game21 = new Game(season, mapMap.get("Outback"), LocalDate.of(2020, 4, 9).atTime(21, 0, 0), sgs21);
        Game game22 = new Game(season, mapMap.get("Border"), LocalDate.of(2020, 4, 9).atTime(21, 30, 0), sgs22);
        Game game23 = new Game(season, mapMap.get("Bank"), LocalDate.of(2020, 4, 9).atTime(22, 0, 0), sgs23);
        Game game24 = new Game(season, mapMap.get("Chalet"), LocalDate.of(2020, 4, 9).atTime(22, 30, 0), sgs24);
        Game game25 = new Game(season, mapMap.get("Theme Park"), LocalDate.of(2020, 4, 10).atTime(21, 0, 0), sgs25);
        Game game26 = new Game(season, mapMap.get("Theme Park"), LocalDate.of(2020, 4, 10).atTime(21, 30, 0), sgs26);
        Game game27 = new Game(season, mapMap.get("Outback"), LocalDate.of(2020, 4, 10).atTime(22, 0, 0), sgs27);
        Game game28 = new Game(season, mapMap.get("Border"), LocalDate.of(2020, 4, 10).atTime(22, 30, 0), sgs28);
        Game game29 = new Game(season, mapMap.get("Coastline"), LocalDate.of(2020, 4, 10).atTime(23, 0, 0), sgs29);
        Game game30 = new Game(season, mapMap.get("Theme Park"), LocalDate.of(2020, 4, 11).atTime(21, 0, 0), sgs30);

        Game game31 = new Game(season, mapMap.get("Villa"), LocalDate.of(2020, 4, 11).atTime(21, 30, 0), sgs31);
        Game game32 = new Game(season, mapMap.get("Villa"), LocalDate.of(2020, 4, 11).atTime(22, 0, 0), sgs32);
        Game game33 = new Game(season, mapMap.get("Border"), LocalDate.of(2020, 4, 11).atTime(22, 30, 0), sgs33);
        Game game34 = new Game(season, mapMap.get("Bank"), LocalDate.of(2020, 4, 12).atTime(21, 0, 0), sgs34);
        Game game35 = new Game(season, mapMap.get("Border"), LocalDate.of(2020, 4, 12).atTime(21, 30, 0), sgs35);
        Game game36 = new Game(season, mapMap.get("Theme Park"), LocalDate.of(2020, 4, 13).atTime(21, 0, 0), sgs36);
        Game game37 = new Game(season, mapMap.get("Border"), LocalDate.of(2020, 4, 13).atTime(21, 30, 0), sgs37);
        Game game38 = new Game(season, mapMap.get("Outback"), LocalDate.of(2020, 4, 13).atTime(22, 0, 0), sgs38);
        Game game39 = new Game(season, mapMap.get("Bank"), LocalDate.of(2020, 4, 13).atTime(22, 30, 0), sgs39);
        Game game40 = new Game(season, mapMap.get("Theme Park"), LocalDate.of(2020, 4, 14).atTime(0, 0, 0), sgs40);

        Game game41 = new Game(season, mapMap.get("Kanal"), LocalDate.of(2020, 4, 14).atTime(22, 30, 0), sgs41);
        Game game42 = new Game(season, mapMap.get("Club House"), LocalDate.of(2020, 4, 14).atTime(23, 0, 0), sgs42);
        Game game43 = new Game(season, mapMap.get("Kafe Dostoyevsky"), LocalDate.of(2020, 4, 15).atTime(0, 0, 0), sgs43);

        this.gameRepository.saveAll(Arrays.asList(
                game1, game2, game3, game4, game5, game6, game7, game8, game9, game10,
                game11, game12, game13, game14, game15, game16, game17, game18, game19, game20,
                game21, game22, game23, game24, game25, game26, game27, game28, game29, game30,
                game31, game32, game33, game34, game35, game36, game37, game38, game39, game40,
                game41, game42, game43
        ));
    }
}
