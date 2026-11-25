package model;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class DamPricePoint extends MarketPoint {
    private final double priceAmount;

    public DamPricePoint(final int intervalIndex, final double priceAmount) {
        super(intervalIndex);
        this.priceAmount = priceAmount;
    }

    @Override
    protected void writeValues(final ObjectNode node) {
        node.put("priceAmount", priceAmount);
    }
}
