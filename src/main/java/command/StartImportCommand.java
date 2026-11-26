package command;

import engine.EnergexPlatform;
import fileio.CommandInput;

public final class StartImportCommand extends Command {
    public StartImportCommand(final CommandInput input) {
        super(input);
    }

    @Override
    public void execute(final EnergexPlatform platform) {
        platform.getClock().startImport();
    }
}
