package validation;

import fileio.MarketDataInput;
import model.MarketPoint;

public abstract class PointValidator {
    protected static final double MARKET_MIN = -500;
    protected static final double MARKET_MAX = 4000;
    protected static final double IMBALANCE_LIMIT = 15000;
    protected static final double MAX_LOAD_VARIATION = 10000;

    public abstract String validate(MarketDataInput input, MarketPoint previous);

    protected final String checkRange(final String field, final Double value,
                                      final double min, final double max,
                                      final Violation outside) {
        if (value == null) {
            return Violation.MISSING_VALUE.describe(field);
        }
        if (value < min || value > max) {
            return outside.describe(field, value);
        }
        return null;
    }

    protected final String checkMinimum(final String field, final Double value) {
        if (value == null) {
            return Violation.MISSING_VALUE.describe(field);
        }
        if (value < 0) {
            return Violation.BELOW_MINIMUM.describe(field, value);
        }
        return null;
    }

    protected final String checkPositive(final String field, final Double value) {
        if (value == null) {
            return Violation.MISSING_VALUE.describe(field);
        }
        if (value <= 0) {
            return Violation.NOT_POSITIVE.describe(field, value);
        }
        return null;
    }
}
