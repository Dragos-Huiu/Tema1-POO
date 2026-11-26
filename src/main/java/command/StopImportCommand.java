package command;

import engine.EnergexPlatform;
import fileio.CommandInput;

public final class StopImportCommand extends Command {
    public StopImportCommand(final CommandInput input) {
        super(input);
    }

    @Override
    public void execute(final EnergexPlatform platform) {
        platform.getClock().stopImport();
    }
}
