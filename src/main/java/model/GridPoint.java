package model;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.BigDecimal;

public abstract class GridPoint extends MarketPoint {
    private final BigDecimal quantity;
    private final Double reservesRequiredMW;

    protected GridPoint(final int intervalIndex, final BigDecimal quantity,
                        final Double reservesRequiredMW) {
        super(intervalIndex);
        this.quantity = quantity;
        this.reservesRequiredMW = reservesRequiredMW;
    }

    public final BigDecimal getQuantity() {
        return quantity;
    }

    public final Double getReservesRequiredMW() {
        return reservesRequiredMW;
    }

    @Override
    protected final void writeValues(final ObjectNode node) {
        node.put("quantity", quantity.doubleValue());
    }
}
