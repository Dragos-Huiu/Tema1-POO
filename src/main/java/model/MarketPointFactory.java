package model;

import fileio.MarketDataInput;

public final class MarketPointFactory {
    private MarketPointFactory() {
    }

    public static MarketPoint create(final DataType type, final MarketDataInput input,
                                     final int index) {
        switch (type) {
            case DAM_PRICE:
                return new DamPricePoint(index, input.priceAmount());
            case DAM_PRICE_PREDICTION:
                return new DamPricePredictionPoint(index, input.predictedPriceEurMwh(),
                        input.spikeProbability());
            case GRID_LOAD:
                return new GridLoadPoint(index, input.quantity(), input.reservesRequiredMW());
            case GRID_LOAD_PREDICTION:
                return new GridLoadPredictionPoint(index, input.quantity(),
                        input.reservesRequiredMW());
            case IMBALANCE_PRICE:
                return new ImbalancePricePoint(index, input.positiveImbalancePrice(),
                        input.negativeImbalancePrice());
            case IMBALANCE_VOLUME:
                return new ImbalanceVolumePoint(index, input.positiveImbalanceVol(),
                        input.negativeImbalanceVol());
            default:
                return null;
        }
    }
}
