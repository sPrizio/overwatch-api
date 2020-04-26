package com.r6overwatch.overwatchapi.validation.validators.impl.players.statistics;

import com.r6overwatch.overwatchapi.enums.ValidationResponseResult;
import com.r6overwatch.overwatchapi.models.entities.players.statistics.PlayerGameStatistics;
import com.r6overwatch.overwatchapi.services.entities.players.PlayerService;
import com.r6overwatch.overwatchapi.validation.result.ValidationResult;
import com.r6overwatch.overwatchapi.validation.validators.OverwatchValidator;
import com.r6overwatch.overwatchapi.validation.validators.impl.AbstractOverwatchValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link OverwatchValidator} for {@link PlayerGameStatistics} entities
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class PlayerGameStatisticsValidator extends AbstractOverwatchValidator implements OverwatchValidator {

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("playerId", "score", "kills", "assists", "deaths");

    @Resource(name = "playerService")
    private PlayerService playerService;


    //  METHODS

    @Override
    public ValidationResult validate(Map<String, Object> values) {

        //  check for missing params
        if (StringUtils.isNotEmpty(super.isMissingParam(values, EXPECTED_PARAMS))) {
            return new ValidationResult(ValidationResponseResult.FAILED, super.isMissingParam(values, EXPECTED_PARAMS));
        }

        String playerId = values.get("playerId").toString();
        String score = values.get("score").toString();
        String kills = values.get("kills").toString();
        String assists = values.get("assists").toString();
        String deaths = values.get("deaths").toString();

        if (super.isTooLarge(playerId) || !super.isNumber(playerId) || super.isOverflow(Long.parseLong(playerId))) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given player id was invalid");
        }

        if (this.playerService.find(Long.parseLong(playerId)).isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "No player exists for the given id");
        }

        if (!super.isNumber(score) || !super.isNumber(kills) || !super.isNumber(assists) || !super.isNumber(deaths)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given values were not numbers");
        }

        if (super.isTooLarge(score) || super.isTooLarge(kills) || super.isTooLarge(assists) || super.isTooLarge(deaths)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given values were too large");
        }

        Integer s = Integer.parseInt(score);
        Integer k = Integer.parseInt(kills);
        Integer a = Integer.parseInt(assists);
        Integer d = Integer.parseInt(deaths);

        if (super.isNegative(s) || super.isNegative(k) || super.isNegative(a) || super.isNegative(d)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Negative values are not allowed");
        }

        if (super.isTooLarge(s) || super.isTooLarge(k) || super.isTooLarge(a) || super.isTooLarge(d)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given values were too large");
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }
}
