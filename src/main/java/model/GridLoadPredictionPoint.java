package model;

import java.math.BigDecimal;

public final class GridLoadPredictionPoint extends GridPoint {
    public GridLoadPredictionPoint(final int intervalIndex, final BigDecimal quantity,
                                   final Double reservesRequiredMW) {
        super(intervalIndex, quantity, reservesRequiredMW);
    }
}
