package model;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class ImbalanceVolumePoint extends MarketPoint {
    private final double positiveImbalanceVol;
    private final double negativeImbalanceVol;

    public ImbalanceVolumePoint(final int intervalIndex, final double positiveImbalanceVol,
                                final double negativeImbalanceVol) {
        super(intervalIndex);
        this.positiveImbalanceVol = positiveImbalanceVol;
        this.negativeImbalanceVol = negativeImbalanceVol;
    }

    @Override
    protected void writeValues(final ObjectNode node) {
        node.put("positiveImbalanceVol", positiveImbalanceVol);
        node.put("negativeImbalanceVol", negativeImbalanceVol);
    }
}
