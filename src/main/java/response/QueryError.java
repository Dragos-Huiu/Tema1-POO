package response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.DataType;

import java.time.LocalDateTime;

public final class QueryError extends QueryResponse {
    private final DataType type;

    public QueryError(final String command, final String timestamp, final LocalDateTime from,
                      final LocalDateTime to, final DataType type) {
        super(command, timestamp, ResponseStatus.ERROR, from, to);
        this.type = type;
    }

    @Override
    protected void writeBody(final ObjectMapper mapper, final ObjectNode node) {
        node.put("message", "Access denied: " + type.name() + " requires PREMIUM subscription.");
    }
}
