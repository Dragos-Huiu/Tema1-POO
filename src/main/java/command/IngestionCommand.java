package command;

import engine.EnergexPlatform;
import fileio.CommandInput;
import fileio.MarketDataInput;
import model.DataType;
import model.MarketPoint;
import model.MarketPointFactory;
import model.TimeSlot;
import response.IngestionWarning;
import validation.PointValidator;
import validation.ValidatorFactory;

import java.util.List;

public abstract class IngestionCommand extends Command {
    private final CommandInput input;

    protected IngestionCommand(final CommandInput input) {
        super(input);
        this.input = input;
    }

    protected final CommandInput getInput() {
        return input;
    }

    @Override
    public final void execute(final EnergexPlatform platform) {
        if (!isAllowed(platform)) {
            return;
        }
        List<MarketDataInput> entries = input.getParameters().getData();
        for (MarketDataInput entry : entries) {
            ingest(platform, entry);
        }
    }

    private void ingest(final EnergexPlatform platform, final MarketDataInput entry) {
        DataType type = resolveType(entry);
        TimeSlot slot = TimeSlot.fromInput(entry.date(), entry.interval());
        MarketPoint previous = null;
        if (type == DataType.GRID_LOAD) {
            previous = platform.getRepository().findPrevious(type, slot);
        }
        PointValidator validator = ValidatorFactory.create(type);
        String error = validator.validate(entry, previous);
        if (error != null) {
            onRejected(platform, type, slot);
            platform.addResponse(new IngestionWarning(getName(), getTimestamp(), type, slot,
                    error));
            return;
        }
        MarketPoint point = MarketPointFactory.create(type, entry, slot.getIndex());
        store(platform, type, slot, point);
    }

    protected abstract void onRejected(EnergexPlatform platform, DataType type, TimeSlot slot);

    protected abstract boolean isAllowed(EnergexPlatform platform);

    protected abstract DataType resolveType(MarketDataInput entry);

    protected abstract void store(EnergexPlatform platform, DataType type, TimeSlot slot,
                                  MarketPoint point);
}
