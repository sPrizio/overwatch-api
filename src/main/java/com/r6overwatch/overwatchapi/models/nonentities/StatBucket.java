package com.r6overwatch.overwatchapi.models.nonentities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;

/**
 * Hold stat information similar to {@link DoubleSummaryStatistics}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public class StatBucket {

    @Getter
    @Setter
    @NonNull
    private LocalDateTime start;

    @Getter
    @Setter
    @NonNull
    private LocalDateTime end;

    @Getter
    private int count;

    @Getter
    private Double sum;

    @Getter
    private Double average;


    //  CONSTRUCTORS

    /**
     * Default constructor
     */
    public StatBucket() {
        this.start = LocalDateTime.now();
        this.end = LocalDateTime.now();
        this.count = 0;
        this.sum = 0.0;
        this.average = 0.0;
    }

    /**
     * Initializes interval dates and all numerical fields to 0
     *
     * @param start interval start date
     * @param end interval end date
     */
    public StatBucket(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        this.count = 0;
        this.sum = 0.0;
        this.average = 0.0;
    }


    //  METHODS

    /**
     * Adds an entry to this bucket
     *
     * @param d number to add
     */
    public void add(Double d) {
        this.count += 1;
        this.sum += d;
        this.average =
                new BigDecimal(this.sum)
                        .divide(new BigDecimal(this.count), new MathContext(10))
                        .setScale(2, RoundingMode.HALF_EVEN)
                        .doubleValue();
    }
}
