package command;

import engine.EnergexPlatform;
import fileio.CommandInput;
import fileio.CommandParametersInput;
import model.ClientTier;
import model.DataType;
import model.MarketPoint;
import model.TimeSlot;
import repository.MarketDataRepository;
import response.QueryError;
import response.QuerySuccess;
import response.QueryWarning;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class GetCommand extends Command {
    private static final String LATEST = "LATEST";

    private final CommandParametersInput parameters;

    public GetCommand(final CommandInput input) {
        super(input);
        this.parameters = input.getParameters();
    }

    @Override
    public void execute(final EnergexPlatform platform) {
        ClientTier tier = ClientTier.valueOf(parameters.getClientTier());
        DataType type = DataType.valueOf(parameters.getDataType());
        MarketDataRepository repository = platform.getRepository();

        LocalDateTime from;
        LocalDateTime to;
        if (LATEST.equals(parameters.getTarget())) {
            TimeSlot latest = repository.findLatest(type);
            if (latest == null) {
                from = getMoment();
                to = getMoment();
            } else {
                from = latest.start();
                to = latest.end();
            }
        } else if (parameters.getTarget() != null) {
            from = LocalDateTime.parse(parameters.getTarget());
            to = from.plusMinutes(TimeSlot.SLOT_MINUTES);
        } else {
            from = LocalDateTime.parse(parameters.getStart());
            to = LocalDateTime.parse(parameters.getEnd());
        }

        if (!tier.canAccess(type)) {
            platform.addResponse(new QueryError(getName(), getTimestamp(), from, to, type));
            return;
        }

        List<TimeSlot> slots = repository.select(type, from, to);
        if (slots.isEmpty()) {
            platform.addResponse(new QueryWarning(getName(), getTimestamp(), from, to));
            return;
        }
        Map<TimeSlot, MarketPoint> points = new LinkedHashMap<>();
        for (TimeSlot slot : slots) {
            points.put(slot, repository.findPoint(type, slot));
        }
        platform.addResponse(new QuerySuccess(getName(), getTimestamp(), from, to, type,
                points));
    }
}
