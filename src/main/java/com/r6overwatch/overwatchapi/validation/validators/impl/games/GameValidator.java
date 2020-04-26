package com.r6overwatch.overwatchapi.validation.validators.impl.games;

import com.r6overwatch.overwatchapi.enums.ValidationResponseResult;
import com.r6overwatch.overwatchapi.models.entities.games.Game;
import com.r6overwatch.overwatchapi.services.entities.games.MapService;
import com.r6overwatch.overwatchapi.services.entities.season.SeasonService;
import com.r6overwatch.overwatchapi.validation.result.ValidationResult;
import com.r6overwatch.overwatchapi.validation.validators.OverwatchValidator;
import com.r6overwatch.overwatchapi.validation.validators.impl.AbstractOverwatchValidator;
import com.r6overwatch.overwatchapi.validation.validators.impl.players.statistics.SquadGameStatisticsValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link OverwatchValidator} for {@link Game} entities
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
@SuppressWarnings("unchecked")
public class GameValidator extends AbstractOverwatchValidator implements OverwatchValidator {

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("season", "map", "gameDateTime", "squadGameStatistics");

    @Resource(name = "mapService")
    private MapService mapService;

    @Resource(name = "seasonService")
    private SeasonService seasonService;

    @Resource(name = "squadGameStatisticsValidator")
    private SquadGameStatisticsValidator squadGameStatisticsValidator;


    //  METHODS

    @Override
    public ValidationResult validate(Map<String, Object> values) {

        //  check for missing params
        if (StringUtils.isNotEmpty(super.isMissingParam(values, EXPECTED_PARAMS))) {
            return new ValidationResult(ValidationResponseResult.FAILED, super.isMissingParam(values, EXPECTED_PARAMS));
        }

        String seasonId = values.get("season").toString();
        String mapId = values.get("map").toString();
        String gameDateTime = values.get("gameDateTime").toString();
        Map<String, Object> squadGameStatistics = (Map<String, Object>) values.get("squadGameStatistics");

        if (super.isTooLarge(seasonId) || !super.isNumber(seasonId) || super.isOverflow(Long.parseLong(seasonId))) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given season id was invalid");
        }

        if (this.seasonService.find(Long.parseLong(seasonId)).isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "No season exists for the given id");
        }

        if (super.isTooLarge(mapId) || !super.isNumber(mapId) || super.isOverflow(Long.parseLong(mapId))) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given map id was invalid");
        }

        if (this.mapService.find(Long.parseLong(mapId)).isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "No map exists for the given id");
        }

        if (super.isInvalidDateForFormat(gameDateTime, "yyyy-MM-dd hh:mm:ss")) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Invalid date format. Date must be of the format 'yyyy-MM-dd HH:mm:ss'");
        }

        ValidationResult sgs = this.squadGameStatisticsValidator.validate(squadGameStatistics);
        if (!sgs.isValid()) {
            return sgs;
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }
}
