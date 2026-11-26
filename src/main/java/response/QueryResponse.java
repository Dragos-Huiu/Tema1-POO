package response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDateTime;

public abstract class QueryResponse extends Response {
    private final LocalDateTime from;
    private final LocalDateTime to;

    protected QueryResponse(final String command, final String timestamp,
                            final ResponseStatus status, final LocalDateTime from,
                            final LocalDateTime to) {
        super(command, timestamp, status);
        this.from = from;
        this.to = to;
    }

    @Override
    protected final void writeDetails(final ObjectMapper mapper, final ObjectNode node) {
        node.put("from", from.toString());
        node.put("to", to.toString());
        writeBody(mapper, node);
    }

    protected abstract void writeBody(ObjectMapper mapper, ObjectNode node);
}
