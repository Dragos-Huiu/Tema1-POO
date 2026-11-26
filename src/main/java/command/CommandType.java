package command;

public enum CommandType {
    START_IMPORT("startImport"),
    STOP_IMPORT("stopImport"),
    IMPORT("import"),
    PATCH("patch"),
    GET("get");

    private final String commandName;

    CommandType(final String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public static CommandType fromName(final String name) {
        for (CommandType type : values()) {
            if (type.commandName.equals(name)) {
                return type;
            }
        }
        return null;
    }
}
