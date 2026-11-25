package validation;

import fileio.MarketDataInput;
import model.MarketPoint;

public final class ImbalancePriceValidator extends PointValidator {
    @Override
    public String validate(final MarketDataInput input, final MarketPoint previous) {
        String error = checkRange("PositiveImbalancePrice", input.positiveImbalancePrice(),
                -IMBALANCE_LIMIT, IMBALANCE_LIMIT, Violation.ABOVE_MAXIMUM);
        if (error != null) {
            return error;
        }
        return checkRange("NegativeImbalancePrice", input.negativeImbalancePrice(),
                -IMBALANCE_LIMIT, IMBALANCE_LIMIT, Violation.ABOVE_MAXIMUM);
    }
}
