package repository;

import model.DailyDocument;
import model.DataType;
import model.MarketPoint;
import model.TimeSlot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class MarketDataRepository {
    private final Map<DataType, TreeMap<LocalDate, DailyDocument>> documents =
            new EnumMap<>(DataType.class);

    public MarketDataRepository() {
        for (DataType type : DataType.values()) {
            documents.put(type, new TreeMap<>());
        }
    }

    public void insert(final DataType type, final TimeSlot slot, final MarketPoint point) {
        DailyDocument document = getOrCreateDocument(type, slot.getDate());
        document.put(point);
    }

    public void update(final DataType type, final TimeSlot slot, final MarketPoint point) {
        DailyDocument document = getOrCreateDocument(type, slot.getDate());
        document.put(point);
    }

    public void remove(final DataType type, final TimeSlot slot) {
        DailyDocument document = documents.get(type).get(slot.getDate());
        if (document != null) {
            document.clear(slot.getIndex());
        }
    }

    public MarketPoint findPoint(final DataType type, final TimeSlot slot) {
        DailyDocument document = documents.get(type).get(slot.getDate());
        if (document == null) {
            return null;
        }
        return document.get(slot.getIndex());
    }

    public MarketPoint findPrevious(final DataType type, final TimeSlot slot) {
        TreeMap<LocalDate, DailyDocument> byDate = documents.get(type);
        DailyDocument document = byDate.get(slot.getDate());
        if (document != null) {
            for (int index = slot.getIndex() - 1; index >= 1; index--) {
                if (document.get(index) != null) {
                    return document.get(index);
                }
            }
        }
        LocalDate date = byDate.lowerKey(slot.getDate());
        while (date != null) {
            DailyDocument earlier = byDate.get(date);
            for (int index = TimeSlot.SLOTS_PER_DAY; index >= 1; index--) {
                if (earlier.get(index) != null) {
                    return earlier.get(index);
                }
            }
            date = byDate.lowerKey(date);
        }
        return null;
    }

    public TimeSlot findLatest(final DataType type) {
        TreeMap<LocalDate, DailyDocument> byDate = documents.get(type);
        if (byDate.isEmpty()) {
            return null;
        }
        LocalDate date = byDate.lastKey();
        while (date != null) {
            DailyDocument document = byDate.get(date);
            for (int index = TimeSlot.SLOTS_PER_DAY; index >= 1; index--) {
                if (document.get(index) != null) {
                    return new TimeSlot(date, index);
                }
            }
            date = byDate.lowerKey(date);
        }
        return null;
    }

    public List<TimeSlot> select(final DataType type, final LocalDateTime from,
                                 final LocalDateTime to) {
        List<TimeSlot> found = new ArrayList<>();
        for (DailyDocument document : documents.get(type).values()) {
            for (int index = 1; index <= TimeSlot.SLOTS_PER_DAY; index++) {
                TimeSlot slot = new TimeSlot(document.getDate(), index);
                boolean inside = !slot.start().isBefore(from) && slot.start().isBefore(to);
                if (inside && document.get(index) != null) {
                    found.add(slot);
                }
            }
        }
        return found;
    }

    private DailyDocument getOrCreateDocument(final DataType type, final LocalDate date) {
        TreeMap<LocalDate, DailyDocument> byDate = documents.get(type);
        if (!byDate.containsKey(date)) {
            byDate.put(date, new DailyDocument(type, date));
        }
        return byDate.get(date);
    }
}
