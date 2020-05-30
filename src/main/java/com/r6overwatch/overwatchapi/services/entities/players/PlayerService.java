package com.r6overwatch.overwatchapi.services.entities.players;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.enums.DateInterval;
import com.r6overwatch.overwatchapi.enums.MapResult;
import com.r6overwatch.overwatchapi.enums.SortOrder;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.models.entities.players.Player;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerSeasonStatistics;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.models.nonentities.StatBucket;
import com.r6overwatch.overwatchapi.models.nonentities.StatsGraphWrapper;
import com.r6overwatch.overwatchapi.repositories.players.player.PlayerRepository;
import com.r6overwatch.overwatchapi.repositories.players.statistics.PlayerSeasonStatisticsRepository;
import com.r6overwatch.overwatchapi.services.entities.OverwatchEntityService;
import com.r6overwatch.overwatchapi.services.entities.games.GameService;
import com.r6overwatch.overwatchapi.services.entities.season.SeasonService;
import com.r6overwatch.overwatchapi.translators.players.PlayerTranslator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link OverwatchEntityService} architecture for {@link Player}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class PlayerService implements OverwatchEntityService<Player> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

    @Resource(name = "gameService")
    private GameService gameService;

    @Resource(name = "playerRepository")
    private PlayerRepository playerRepository;

    @Resource(name = "playerSeasonStatisticsRepository")
    private PlayerSeasonStatisticsRepository playerSeasonStatisticsRepository;

    @Resource(name = "playerTranslator")
    private PlayerTranslator playerTranslator;

    @Resource(name = "seasonService")
    private SeasonService seasonService;


    //  METHODS

    /**
     * Updates the {@link PlayerSeasonStatistics} with the given {@link PlayerGameStatistics} for the given {@link SquadGameStatistics}
     * and {@link Season}
     *
     * @param statistics {@link PlayerSeasonStatistics}
     * @param squadGameStatistics {@link SquadGameStatistics}
     * @param season {@link Season}
     * @param invert flag to mark inversion. inversion means we subtract instead of adding stats
     */
    public void updateStats(PlayerGameStatistics statistics, SquadGameStatistics squadGameStatistics, Season season, boolean invert) {

        if (statistics != null && season != null) {
            PlayerSeasonStatistics seasonStatistics = statistics.getPlayer().getSeasonStatisticsForSeason(season);

            if (seasonStatistics != null) {
                if (squadGameStatistics.getMapResult().equals(MapResult.WIN)) {
                    seasonStatistics.incrementWins(invert);
                } else if (squadGameStatistics.getMapResult().equals(MapResult.LOSS)) {
                    seasonStatistics.incrementLosses(invert);
                }

                seasonStatistics.incrementKills(handleInversion(statistics.getKills(), invert));
                seasonStatistics.incrementAssists(handleInversion(statistics.getAssists(), invert));
                seasonStatistics.incrementDeaths(handleInversion(statistics.getDeaths(), invert));

                this.playerSeasonStatisticsRepository.save(seasonStatistics);
            }
        }
    }

    /**
     * Returns a list of {@link StatsGraphWrapper} objects for graphing of a player's recent performance for the given attribute
     *
     * @param stat         stat to consider, ex: kills
     * @param player       {@link Player} who's stats to obtain
     * @param dateInterval {@link DateInterval} timeline to consider
     * @return list of {@link StatsGraphWrapper}
     */
    public List<StatsGraphWrapper> findPlayerStatsForRecentGamesByStat(String stat, Player player, DateInterval dateInterval) {

        if (StringUtils.isEmpty(stat) || player == null || dateInterval == null) {
            LOGGER.error("One or more the required parameters was null or empty: stat {}, player: {}, dateInterval {}", stat, player, dateInterval);
            return new ArrayList<>();
        }

        Optional<Season> season = this.seasonService.getCurrentSeason();
        List<StatsGraphWrapper> graphWrappers = new ArrayList<>();

        if (season.isPresent()) {
            List<Game> games = this.gameService.findGamesByPlayerAndSeasonSortedByDateLimited(player, season.get(), null);
            List<StatBucket> buckets = generateBucketList(generateEntryList(games, player, stat), dateInterval);
            buckets.forEach(bucket -> graphWrappers.add(new StatsGraphWrapper(formatDisplayForGraphWrapper(bucket, dateInterval), stat.equals("kd") ? bucket.getAverage() : bucket.getSum())));
        }

        return graphWrappers;
    }

    /**
     * Finds players for the given {@link Player} attribute and {@link SortOrder}. A {@link Season} is used
     * to refine results as well as a {@link Squad} to limit results to team members
     *
     * @param squad     the {@link Squad} that this {@link Player} belongs to
     * @param attribute the {@link Player} attribute that we wish to sort by
     * @param season    the {@link Season}
     * @param sortOrder the {@link SortOrder} for the result list
     * @return list of {@link Player}s in the given {@link SortOrder}
     */
    public List<Player> findPlayersBySquadAndSeasonSortedByAttribute(Squad squad, String attribute, Season season, SortOrder sortOrder) {

        if (squad == null || StringUtils.isEmpty(attribute) || season == null || sortOrder == null) {
            LOGGER.error("One or more of the required parameters was null or empty: squad {}, attribute {}, season {}, sortOrder {}", squad, attribute, season, sortOrder);
            return new ArrayList<>();
        }

        return this.playerRepository.findPlayersBySquadAndSeasonSortedByAttribute(squad, attribute, season, sortOrder);
    }

    @Override
    public void refresh(Player entity) {
        this.playerRepository.refresh(entity);
    }

    @Override
    public Optional<Player> find(Long id) {
        return this.playerRepository.findById(id);
    }

    @Override
    public List<Player> findAll() {
        return Lists.newArrayList(this.playerRepository.findAll());
    }

    @Override
    public Player save(Player entity) {
        return this.playerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        find(id).ifPresent(player -> this.playerRepository.deleteById(player.getId()));
    }

    @Override
    public Player create(Map<String, Object> params) {

        Player player = this.playerTranslator.translate(params);

        if (player != null) {
            return this.playerRepository.save(player);
        }

        return null;
    }


    //  HELPERS

    /**
     * Generates a map of date times and stats
     *
     * @param games  {@link Game}s list
     * @param player {@link Player}'s game list
     * @param stat   stat to organize for graphing
     * @return map of dates and stats for those games
     */
    private List<Map.Entry<LocalDateTime, Double>> generateEntryList(List<Game> games, Player player, String stat) {

        if (CollectionUtils.isNotEmpty(games)) {
            //  sort backwards
            games = games.stream().sorted(Comparator.comparing(Game::getGameDateTime)).collect(Collectors.toList());
            Map<LocalDateTime, Double> map = new HashMap<>();
            switch (stat) {
                case "score":
                    games.forEach(game -> map.put(game.getGameDateTime(), getPlayerStats(game, player).getScore().doubleValue()));
                    break;
                case "kills":
                    games.forEach(game -> map.put(game.getGameDateTime(), getPlayerStats(game, player).getKills().doubleValue()));
                    break;
                case "assists":
                    games.forEach(game -> map.put(game.getGameDateTime(), getPlayerStats(game, player).getAssists().doubleValue()));
                    break;
                case "deaths":
                    games.forEach(game -> map.put(game.getGameDateTime(), getPlayerStats(game, player).getDeaths().doubleValue()));
                    break;
                case "kd":
                    games.forEach(game -> map.put(game.getGameDateTime(), calculateKD(getPlayerStats(game, player))));
                    break;
                default:
                    return new ArrayList<>();
            }

            return map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    /**
     * Obtains {@link PlayerGameStatistics} for the given game and player
     *
     * @param game   {@link Game}
     * @param player {@link Player}
     * @return {@link PlayerGameStatistics}
     */
    private PlayerGameStatistics getPlayerStats(Game game, Player player) {
        Optional<PlayerGameStatistics> blue = game.getSquadGameStatistics().getPlayerGameStatistics().stream().filter(stats -> stats.getPlayer().getId().equals(player.getId())).findFirst();
        return blue.orElse(null);
    }

    /**
     * Obtains the k/d for {@link PlayerGameStatistics}
     *
     * @param stats {@link PlayerGameStatistics}
     * @return k/d
     */
    private Double calculateKD(PlayerGameStatistics stats) {

        if (stats != null) {
            if (stats.getDeaths() == 0) {
                return 0.0;
            }

            return new BigDecimal(stats.getKills())
                    .divide(new BigDecimal(stats.getDeaths()), new MathContext(10))
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .doubleValue();
        }

        return 0.0;
    }

    /**
     * Generates the {@link StatBucket} list
     *
     * @param entries      date and value entries
     * @param dateInterval date delineation format
     * @return list of {@link StatBucket}s
     */
    private List<StatBucket> generateBucketList(List<Map.Entry<LocalDateTime, Double>> entries, DateInterval dateInterval) {

        if (CollectionUtils.isEmpty(entries)) {
            return new ArrayList<>();
        }

        List<StatBucket> buckets = new ArrayList<>();
        LocalDateTime start = entries.get(0).getKey().toLocalDate().atStartOfDay();
        LocalDateTime end = entries.get(entries.size() - 1).getKey().plusDays(1).toLocalDate().atStartOfDay().minusSeconds(1);
        LocalDateTime current = start.toLocalDate().atStartOfDay();

        while ((current.isEqual(start) || current.isAfter(start)) && (current.isBefore(end))) {
            buckets.add(new StatBucket(current, calculateIncrement(current, dateInterval)));

            if (dateInterval.equals(DateInterval.DAILY)) {
                current = current.plusDays(1);
            } else if (dateInterval.equals(DateInterval.WEEKLY)) {
                current = current.plusWeeks(1);
            } else if (dateInterval.equals(DateInterval.MONTHLY)) {
                current = current.plusMonths(1);
            }
        }

        buckets.forEach(bucket -> entries.forEach(entry -> {
            if ((entry.getKey().isEqual(bucket.getStart()) || entry.getKey().isAfter(bucket.getStart())) && (entry.getKey().isEqual(bucket.getEnd()) || entry.getKey().isBefore(bucket.getEnd()))) {
                bucket.add(entry.getValue());
            }
        }));

        return buckets;
    }

    /**
     * Calculates the datetime increment for the {@link DateInterval}
     *
     * @param localDateTime date time to increment
     * @param dateInterval  {@link DateInterval}
     * @return increment local date time
     */
    private LocalDateTime calculateIncrement(LocalDateTime localDateTime, DateInterval dateInterval) {
        switch (dateInterval) {
            case MONTHLY:
                return localDateTime.plusDays(1).plusMonths(1).toLocalDate().atStartOfDay().minusSeconds(1);
            case WEEKLY:
                return localDateTime.plusDays(1).plusWeeks(1).toLocalDate().atStartOfDay().minusSeconds(1);
            default:
                return localDateTime.plusDays(1).toLocalDate().atStartOfDay().minusSeconds(1);
        }
    }

    /**
     * Formats a date string for the {@link StatBucket}
     *
     * @param bucket       {@link StatBucket}
     * @param dateInterval {@link DateInterval}
     * @return formatted date string
     */
    private String formatDisplayForGraphWrapper(StatBucket bucket, DateInterval dateInterval) {
        switch (dateInterval) {
            case MONTHLY:
                return bucket.getStart().format(DateTimeFormatter.ofPattern("MMMM"));
            case WEEKLY:
                return (bucket.getStart().format(DateTimeFormatter.ofPattern("MMM dd")) + "-" + bucket.getEnd().format(DateTimeFormatter.ofPattern("dd"))).replace(".", StringUtils.EMPTY);
            default:
                return bucket.getStart().format(DateTimeFormatter.ofPattern("MMM dd")).replace(".", StringUtils.EMPTY);
        }
    }

    /**
     * Inverts an integer's sign
     *
     * @param integer integer to invert
     * @param invert if true, invert
     * @return integer
     */
    private Integer handleInversion(Integer integer, boolean invert) {
        if (invert) {
            return integer * -1;
        }

        return integer;
    }
}
