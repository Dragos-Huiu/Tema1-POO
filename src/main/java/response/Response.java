package response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class Response {
    private final String command;
    private final String timestamp;
    private final ResponseStatus status;

    protected Response(final String command, final String timestamp,
                       final ResponseStatus status) {
        this.command = command;
        this.timestamp = timestamp;
        this.status = status;
    }

    public final ObjectNode toJson(final ObjectMapper mapper) {
        ObjectNode node = mapper.createObjectNode();
        node.put("command", command);
        node.put("timestamp", timestamp);
        node.put("status", status.name());
        writeDetails(mapper, node);
        return node;
    }

    protected abstract void writeDetails(ObjectMapper mapper, ObjectNode node);
}
