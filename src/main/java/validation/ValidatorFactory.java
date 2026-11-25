package validation;

import model.DataType;

public final class ValidatorFactory {
    private ValidatorFactory() {
    }

    public static PointValidator create(final DataType type) {
        switch (type) {
            case DAM_PRICE:
                return new DamPriceValidator();
            case DAM_PRICE_PREDICTION:
                return new DamPricePredictionValidator();
            case GRID_LOAD:
                return new GridLoadValidator();
            case GRID_LOAD_PREDICTION:
                return new GridLoadPredictionValidator();
            case IMBALANCE_PRICE:
                return new ImbalancePriceValidator();
            case IMBALANCE_VOLUME:
                return new ImbalanceVolumeValidator();
            default:
                return null;
        }
    }
}
