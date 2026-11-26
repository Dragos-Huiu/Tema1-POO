package command;

import engine.EnergexPlatform;
import fileio.CommandInput;

import java.time.LocalDateTime;

public abstract class Command {
    private final String name;
    private final String timestamp;
    private final LocalDateTime moment;

    protected Command(final CommandInput input) {
        this.name = input.getCommand();
        this.timestamp = input.getTimestamp();
        this.moment = LocalDateTime.parse(input.getTimestamp());
    }

    public final String getName() {
        return name;
    }

    public final String getTimestamp() {
        return timestamp;
    }

    public final LocalDateTime getMoment() {
        return moment;
    }

    public abstract void execute(EnergexPlatform platform);
}
