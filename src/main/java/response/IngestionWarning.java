package response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.DataType;
import model.TimeSlot;

public final class IngestionWarning extends Response {
    private final DataType type;
    private final TimeSlot slot;
    private final String message;

    public IngestionWarning(final String command, final String timestamp, final DataType type,
                            final TimeSlot slot, final String message) {
        super(command, timestamp, ResponseStatus.WARNING);
        this.type = type;
        this.slot = slot;
        this.message = message;
    }

    @Override
    protected void writeDetails(final ObjectMapper mapper, final ObjectNode node) {
        node.put("id", type.name());
        node.put("date", slot.getDate().toString());
        node.put("interval", slot.label());
        node.put("intervalIndex", slot.getIndex());
        node.put("message", message);
    }
}
