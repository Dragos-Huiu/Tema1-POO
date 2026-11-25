package validation;

import fileio.MarketDataInput;
import model.MarketPoint;

public final class DamPriceValidator extends PointValidator {
    @Override
    public String validate(final MarketDataInput input, final MarketPoint previous) {
        return checkRange("PriceAmount", input.priceAmount(), MARKET_MIN, MARKET_MAX,
                Violation.OUTSIDE_MARKET_RANGE);
    }
}
