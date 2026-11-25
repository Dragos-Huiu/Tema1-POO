package model;

public enum DataType {
    DAM_PRICE(false),
    DAM_PRICE_PREDICTION(true),
    GRID_LOAD(false),
    GRID_LOAD_PREDICTION(false),
    IMBALANCE_PRICE(false),
    IMBALANCE_VOLUME(false);

    private final boolean premiumOnly;

    DataType(final boolean premiumOnly) {
        this.premiumOnly = premiumOnly;
    }

    public boolean isPremiumOnly() {
        return premiumOnly;
    }
}
