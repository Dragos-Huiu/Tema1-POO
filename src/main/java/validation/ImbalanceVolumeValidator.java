package validation;

import fileio.MarketDataInput;
import model.MarketPoint;

public final class ImbalanceVolumeValidator extends PointValidator {
    @Override
    public String validate(final MarketDataInput input, final MarketPoint previous) {
        String error = checkMinimum("PositiveImbalanceVol", input.positiveImbalanceVol());
        if (error != null) {
            return error;
        }
        return checkMinimum("NegativeImbalanceVol", input.negativeImbalanceVol());
    }
}
