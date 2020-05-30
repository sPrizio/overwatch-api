package com.r6overwatch.overwatchapi.services.entities.players;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.enums.MapResult;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadSeasonStatistics;
import com.r6overwatch.overwatchapi.models.entities.season.Season;
import com.r6overwatch.overwatchapi.repositories.players.squad.SquadRepository;
import com.r6overwatch.overwatchapi.repositories.players.statistics.SquadSeasonStatisticsRepository;
import com.r6overwatch.overwatchapi.services.entities.OverwatchEntityService;
import com.r6overwatch.overwatchapi.translators.players.SquadTranslator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the {@link OverwatchEntityService} architecture for {@link Squad}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class SquadService implements OverwatchEntityService<Squad> {

    @Resource(name = "squadSeasonStatisticsRepository")
    private SquadSeasonStatisticsRepository squadSeasonStatisticsRepository;

    @Resource(name = "squadRepository")
    private SquadRepository squadRepository;

    @Resource(name = "squadTranslator")
    private SquadTranslator squadTranslator;


    //  METHODS

    /**
     * Updates the {@link SquadSeasonStatistics} with the given {@link SquadGameStatistics} for the given {@link Season}
     *
     * @param statistics {@link SquadGameStatistics}
     * @param season {@link Season}
     * @param invert flag to mark inversion. inversion means we subtract instead of adding stats
     */
    public void updateStats(SquadGameStatistics statistics, Season season, boolean invert) {

        if (statistics != null && season != null) {
            SquadSeasonStatistics seasonStatistics = statistics.getSquad().getSeasonStatisticsForSeason(season);

            if (seasonStatistics != null) {
                if (statistics.getMapResult().equals(MapResult.WIN)) {
                    seasonStatistics.incrementWins(invert);
                } else if (statistics.getMapResult().equals(MapResult.LOSS)) {
                    seasonStatistics.incrementLosses(invert);
                }

                seasonStatistics.incrementRoundsWon(handleInversion(statistics.getRoundsWon(), invert));
                seasonStatistics.incrementRoundsLost(handleInversion(statistics.getRoundsLost(), invert));

                this.squadSeasonStatisticsRepository.save(seasonStatistics);
            }
        }
    }

    @Override
    public void refresh(Squad entity) {
        this.squadRepository.refresh(entity);
    }

    @Override
    public Optional<Squad> find(Long id) {
        return this.squadRepository.findById(id);
    }

    @Override
    public List<Squad> findAll() {
        return Lists.newArrayList(this.squadRepository.findAll());
    }

    @Override
    public Squad save(Squad entity) {
        return this.squadRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        find(id).ifPresent(squad -> this.squadRepository.deleteById(squad.getId()));
    }

    @Override
    public Squad create(Map<String, Object> params) {

        Squad squad = this.squadTranslator.translate(params);

        if (squad != null) {
            return this.squadRepository.save(squad);
        }

        return null;
    }


    //  HELPERS

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
