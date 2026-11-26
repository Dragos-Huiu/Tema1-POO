package command;

import fileio.CommandInput;

public final class CommandFactory {
    private CommandFactory() {
    }

    public static Command create(final CommandInput input) {
        CommandType type = CommandType.fromName(input.getCommand());
        switch (type) {
            case START_IMPORT:
                return new StartImportCommand(input);
            case STOP_IMPORT:
                return new StopImportCommand(input);
            case IMPORT:
                return new ImportCommand(input);
            case PATCH:
                return new PatchCommand(input);
            case GET:
                return new GetCommand(input);
            default:
                return null;
        }
    }
}
