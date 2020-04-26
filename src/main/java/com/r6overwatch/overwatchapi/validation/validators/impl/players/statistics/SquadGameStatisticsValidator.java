package com.r6overwatch.overwatchapi.validation.validators.impl.players.statistics;

import com.r6overwatch.overwatchapi.enums.GameSide;
import com.r6overwatch.overwatchapi.enums.MapResult;
import com.r6overwatch.overwatchapi.enums.ValidationResponseResult;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.SquadGameStatistics;
import com.r6overwatch.overwatchapi.services.entities.players.SquadService;
import com.r6overwatch.overwatchapi.validation.result.ValidationResult;
import com.r6overwatch.overwatchapi.validation.validators.OverwatchValidator;
import com.r6overwatch.overwatchapi.validation.validators.impl.AbstractOverwatchValidator;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of {@link OverwatchValidator} for {@link SquadGameStatistics} entities
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SquadGameStatisticsValidator extends AbstractOverwatchValidator implements OverwatchValidator {

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("squadId", "gameSide", "mapResult", "roundsWon", "roundsLost", "playerGameStatistics");

    @Resource(name = "playerGameStatisticsValidator")
    private PlayerGameStatisticsValidator playerGameStatisticsValidator;

    @Resource(name = "squadService")
    private SquadService squadService;


    //  METHODS

    @Override
    public ValidationResult validate(Map<String, Object> values) {

        //  check for missing params
        if (StringUtils.isNotEmpty(super.isMissingParam(values, EXPECTED_PARAMS))) {
            return new ValidationResult(ValidationResponseResult.FAILED, super.isMissingParam(values, EXPECTED_PARAMS));
        }

        String squadId = values.get("squadId").toString();
        String gameSide = values.get("gameSide").toString();
        String mapResult = values.get("mapResult").toString();
        String roundsWon = values.get("roundsWon").toString();
        String roundsLost = values.get("roundsLost").toString();
        List<Map<String, Object>> playerGameStatistics = (List<Map<String, Object>>) values.get("playerGameStatistics");

        if (super.isTooLarge(squadId) || !super.isNumber(squadId) || super.isOverflow(Long.parseLong(squadId))) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given squad id was invalid");
        }

        if (this.squadService.find(Long.parseLong(squadId)).isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "No squad exists for the given id");
        }

        if (EnumUtils.isValidEnum(GameSide.class, gameSide)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given status was not a valid option. Valid options are: " + Arrays.stream(GameSide.values()).collect(Collectors.toList()));
        }

        if (EnumUtils.isValidEnum(MapResult.class, mapResult)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given status was not a valid option. Valid options are: " + Arrays.stream(MapResult.values()).collect(Collectors.toList()));
        }

        if (!super.isNumber(roundsWon) || !super.isNumber(roundsLost)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given values were not numbers");
        }

        if (super.isTooLarge(roundsWon) || super.isTooLarge(roundsLost)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given values were too large");
        }

        Integer rW = Integer.parseInt(roundsWon);
        Integer rL = Integer.parseInt(roundsLost);

        if (super.isNegative(rW) || super.isNegative(rL)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Negative values are not allowed");
        }

        if (super.isTooLarge(rW) || super.isTooLarge(rL)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given values were too large");
        }

        ValidationResult resPlayer = super.validateListOfDetails(playerGameStatistics, this.playerGameStatisticsValidator);
        if (!resPlayer.isValid()) {
            return resPlayer;
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }
}
