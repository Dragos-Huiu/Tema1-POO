package model;

import java.math.BigDecimal;

public final class GridLoadPoint extends GridPoint {
    public GridLoadPoint(final int intervalIndex, final BigDecimal quantity,
                         final Double reservesRequiredMW) {
        super(intervalIndex, quantity, reservesRequiredMW);
    }
}
