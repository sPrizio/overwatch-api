package com.r6overwatch.overwatchapi.models.nonentities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.DoubleSummaryStatistics;

/**
 * A mapping from {@link DoubleSummaryStatistics}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class StatResource {

    @Getter
    @Setter
    private String stat;

    @Getter
    @Setter
    private Double sum;

    @Getter
    @Setter
    private Double average;

    @Getter
    @Setter
    private Double min;

    @Getter
    @Setter
    private Double max;

    /**
     * Standard constructor that handles rounding values as needed
     */
    public StatResource(String stat, Double sum, Double average, Double min, Double max) {
        this.stat = stat;
        this.sum = new BigDecimal(sum).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        this.average = new BigDecimal(average).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        this.min = new BigDecimal(min).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        this.max = new BigDecimal(max).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}
