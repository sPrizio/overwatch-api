package com.r6overwatch.overwatchapi.models.nonentities;

import lombok.*;

/**
 * A class representation of an X,Y point on a graph
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class StatsGraphWrapper {

    @Getter
    @Setter
    @NonNull
    private String x;

    @Getter
    @Setter
    @NonNull
    private Double y;
}
