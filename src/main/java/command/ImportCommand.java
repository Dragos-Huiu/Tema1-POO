package command;

import engine.EnergexPlatform;
import fileio.CommandInput;
import fileio.MarketDataInput;
import model.DataType;
import model.MarketPoint;
import model.TimeSlot;

public final class ImportCommand extends IngestionCommand {
    public ImportCommand(final CommandInput input) {
        super(input);
    }

    @Override
    protected boolean isAllowed(final EnergexPlatform platform) {
        return platform.getClock().isImportActive();
    }

    @Override
    protected DataType resolveType(final MarketDataInput entry) {
        return DataType.valueOf(entry.id());
    }

    @Override
    protected void store(final EnergexPlatform platform, final DataType type,
                         final TimeSlot slot, final MarketPoint point) {
        platform.getRepository().insert(type, slot, point);
    }

    @Override
    protected void onRejected(final EnergexPlatform platform, final DataType type,
                              final TimeSlot slot) {
        platform.getRepository().remove(type, slot);
    }
}
