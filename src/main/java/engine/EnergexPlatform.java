package engine;

import command.Command;
import command.CommandFactory;
import fileio.CommandInput;
import repository.MarketDataRepository;
import response.Response;

import java.util.ArrayList;
import java.util.List;

public final class EnergexPlatform {
    private final SimulationClock clock = new SimulationClock();
    private final MarketDataRepository repository = new MarketDataRepository();
    private final List<Response> responses = new ArrayList<>();

    public SimulationClock getClock() {
        return clock;
    }

    public MarketDataRepository getRepository() {
        return repository;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void addResponse(final Response response) {
        responses.add(response);
    }

    public void process(final CommandInput input) {
        Command command = CommandFactory.create(input);
        clock.advanceTo(command.getMoment());
        command.execute(this);
    }
}
