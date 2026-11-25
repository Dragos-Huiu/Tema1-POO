package validation;

import fileio.MarketDataInput;
import model.MarketPoint;

public final class GridLoadPredictionValidator extends PointValidator {
    @Override
    public String validate(final MarketDataInput input, final MarketPoint previous) {
        Double quantity = null;
        if (input.quantity() != null) {
            quantity = input.quantity().doubleValue();
        }
        String error = checkMinimum("Quantity", quantity);
        if (error != null) {
            return error;
        }
        return checkPositive("ReservesRequiredMW", input.reservesRequiredMW());
    }
}
