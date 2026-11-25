package model;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class ImbalancePricePoint extends MarketPoint {
    private final double positiveImbalancePrice;
    private final double negativeImbalancePrice;

    public ImbalancePricePoint(final int intervalIndex, final double positiveImbalancePrice,
                               final double negativeImbalancePrice) {
        super(intervalIndex);
        this.positiveImbalancePrice = positiveImbalancePrice;
        this.negativeImbalancePrice = negativeImbalancePrice;
    }

    @Override
    protected void writeValues(final ObjectNode node) {
        node.put("positiveImbalancePrice", positiveImbalancePrice);
        node.put("negativeImbalancePrice", negativeImbalancePrice);
    }
}
