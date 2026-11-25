package model;

import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class MarketPoint {
    private final int intervalIndex;

    protected MarketPoint(final int intervalIndex) {
        this.intervalIndex = intervalIndex;
    }

    public final int getIntervalIndex() {
        return intervalIndex;
    }

    public final void writeTo(final ObjectNode node, final TimeSlot slot) {
        node.put("interval", slot.label());
        node.put("intervalIndex", intervalIndex);
        writeValues(node);
    }

    protected abstract void writeValues(ObjectNode node);
}
