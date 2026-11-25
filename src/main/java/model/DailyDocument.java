package model;

import java.time.LocalDate;

public final class DailyDocument {
    private final DataType type;
    private final LocalDate date;
    private final MarketPoint[] points = new MarketPoint[TimeSlot.SLOTS_PER_DAY];

    public DailyDocument(final DataType type, final LocalDate date) {
        this.type = type;
        this.date = date;
    }

    public DataType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void put(final MarketPoint point) {
        points[point.getIntervalIndex() - 1] = point;
    }

    public MarketPoint get(final int intervalIndex) {
        return points[intervalIndex - 1];
    }
}
