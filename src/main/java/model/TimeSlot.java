package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class TimeSlot implements Comparable<TimeSlot> {
    public static final int SLOT_MINUTES = 15;
    public static final int SLOTS_PER_DAY = 96;
    private static final int MINUTES_PER_HOUR = 60;

    private final LocalDate date;
    private final int index;

    public TimeSlot(final LocalDate date, final int index) {
        this.date = date;
        this.index = index;
    }

    public static TimeSlot fromInput(final String date, final String interval) {
        String[] parts = interval.split("-");
        LocalTime time = LocalTime.parse(parts[0].trim());
        return new TimeSlot(LocalDate.parse(date), indexOf(time));
    }

    public static TimeSlot startingAt(final LocalDateTime moment) {
        return new TimeSlot(moment.toLocalDate(), indexOf(moment.toLocalTime()));
    }

    private static int indexOf(final LocalTime time) {
        int minutes = time.getHour() * MINUTES_PER_HOUR + time.getMinute();
        return minutes / SLOT_MINUTES + 1;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getIndex() {
        return index;
    }

    public LocalDateTime start() {
        return date.atStartOfDay().plusMinutes((index - 1) * SLOT_MINUTES);
    }

    public LocalDateTime end() {
        return start().plusMinutes(SLOT_MINUTES);
    }

    public String label() {
        return start().toLocalTime() + " - " + end().toLocalTime();
    }

    @Override
    public int compareTo(final TimeSlot other) {
        return start().compareTo(other.start());
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof TimeSlot)) {
            return false;
        }
        TimeSlot slot = (TimeSlot) other;
        return start().equals(slot.start());
    }

    @Override
    public int hashCode() {
        return start().hashCode();
    }
}
