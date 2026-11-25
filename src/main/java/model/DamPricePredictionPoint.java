package model;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class DamPricePredictionPoint extends MarketPoint {
    private final double predictedPriceEurMwh;
    private final double spikeProbability;

    public DamPricePredictionPoint(final int intervalIndex, final double predictedPriceEurMwh,
                                   final double spikeProbability) {
        super(intervalIndex);
        this.predictedPriceEurMwh = predictedPriceEurMwh;
        this.spikeProbability = spikeProbability;
    }

    @Override
    protected void writeValues(final ObjectNode node) {
        node.put("predictedPriceEurMwh", predictedPriceEurMwh);
        node.put("spikeProbability", spikeProbability);
    }
}
