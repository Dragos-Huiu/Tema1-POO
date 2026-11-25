package model;

public enum ClientTier {
    FREE(false),
    PREMIUM(true);

    private final boolean premiumAccess;

    ClientTier(final boolean premiumAccess) {
        this.premiumAccess = premiumAccess;
    }

    public boolean canAccess(final DataType type) {
        if (type.isPremiumOnly()) {
            return premiumAccess;
        }
        return true;
    }
}
