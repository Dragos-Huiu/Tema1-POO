package validation;

import fileio.MarketDataInput;
import model.MarketPoint;

public final class DamPricePredictionValidator extends PointValidator {
    @Override
    public String validate(final MarketDataInput input, final MarketPoint previous) {
        String error = checkRange("PredictedPriceEurMwh", input.predictedPriceEurMwh(),
                MARKET_MIN, MARKET_MAX, Violation.OUTSIDE_MARKET_RANGE);
        if (error != null) {
            return error;
        }
        return checkRange("SpikeProbability", input.spikeProbability(), 0.0, 1.0,
                Violation.OUTSIDE_PROBABILITY_RANGE);
    }
}
