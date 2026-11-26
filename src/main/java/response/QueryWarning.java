package response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDateTime;

public final class QueryWarning extends QueryResponse {
    public QueryWarning(final String command, final String timestamp,
                        final LocalDateTime from, final LocalDateTime to) {
        super(command, timestamp, ResponseStatus.WARNING, from, to);
    }

    @Override
    protected void writeBody(final ObjectMapper mapper, final ObjectNode node) {
        node.putNull("data");
        node.put("message", "Data is not yet available. Try again later.");
    }
}
