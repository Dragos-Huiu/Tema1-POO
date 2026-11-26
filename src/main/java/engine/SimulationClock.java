package engine;

import java.time.LocalDateTime;

public final class SimulationClock {
    private LocalDateTime currentTime;
    private boolean importActive;

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public boolean isImportActive() {
        return importActive;
    }

    public void advanceTo(final LocalDateTime moment) {
        currentTime = moment;
    }

    public void startImport() {
        importActive = true;
    }

    public void stopImport() {
        importActive = false;
    }
}
