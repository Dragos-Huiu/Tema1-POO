package response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.DataType;
import model.MarketPoint;
import model.TimeSlot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public final class QuerySuccess extends QueryResponse {
    private final DataType type;
    private final Map<TimeSlot, MarketPoint> points;

    public QuerySuccess(final String command, final String timestamp, final LocalDateTime from,
                        final LocalDateTime to, final DataType type,
                        final Map<TimeSlot, MarketPoint> points) {
        super(command, timestamp, ResponseStatus.SUCCESS, from, to);
        this.type = type;
        this.points = points;
    }

    @Override
    protected void writeBody(final ObjectMapper mapper, final ObjectNode node) {
        ArrayNode days = node.putArray("data");
        ArrayNode currentPoints = null;
        LocalDate currentDate = null;
        for (Map.Entry<TimeSlot, MarketPoint> entry : points.entrySet()) {
            TimeSlot slot = entry.getKey();
            if (!slot.getDate().equals(currentDate)) {
                ObjectNode day = days.addObject();
                day.put("id", type.name());
                day.put("date", slot.getDate().toString());
                currentPoints = day.putArray("data");
                currentDate = slot.getDate();
            }
            entry.getValue().writeTo(currentPoints.addObject(), slot);
        }
    }
}
