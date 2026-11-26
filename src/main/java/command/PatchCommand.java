package command;

import engine.EnergexPlatform;
import fileio.CommandInput;
import fileio.MarketDataInput;
import model.DataType;
import model.MarketPoint;
import model.TimeSlot;

public final class PatchCommand extends IngestionCommand {
    public PatchCommand(final CommandInput input) {
        super(input);
    }

    @Override
    protected boolean isAllowed(final EnergexPlatform platform) {
        return true;
    }

    @Override
    protected DataType resolveType(final MarketDataInput entry) {
        return DataType.valueOf(getInput().getParameters().getDataType());
    }

    @Override
    protected void store(final EnergexPlatform platform, final DataType type,
                         final TimeSlot slot, final MarketPoint point) {
        platform.getRepository().update(type, slot, point);
    }

    @Override
    protected void onRejected(final EnergexPlatform platform, final DataType type,
                              final TimeSlot slot) {
    }
}
