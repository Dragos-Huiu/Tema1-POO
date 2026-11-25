package validation;

import fileio.MarketDataInput;
import model.GridPoint;
import model.MarketPoint;

public final class GridLoadValidator extends PointValidator {
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
        if (previous != null) {
            GridPoint previousPoint = (GridPoint) previous;
            double change = Math.abs(quantity - previousPoint.getQuantity().doubleValue());
            if (change > MAX_LOAD_VARIATION) {
                return Violation.EXCESSIVE_VARIATION.describe("Quantity");
            }
        }
        return null;
    }
}
