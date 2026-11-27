package validation;

public enum Violation {
    MISSING_VALUE("FIELD is null; interval skipped."),
    OUTSIDE_MARKET_RANGE("FIELD VALUE is outside the market range [-500, 4000] EUR/MWh; "
            + "interval skipped."),
    OUTSIDE_PROBABILITY_RANGE("FIELD VALUE is outside the valid range [0.0, 1.0]; "
            + "interval skipped."),
    BELOW_MINIMUM("FIELD VALUE is below the minimum allowed value 0; interval skipped."),
    NOT_POSITIVE("FIELD VALUE is not positive; interval skipped."),
    ABOVE_MAXIMUM("FIELD VALUE exceeds the maximum allowed value 15000 EUR/MWh; "
            + "interval skipped."),
    EXCESSIVE_VARIATION("FIELD change exceeds 10000 MW compared with the previous interval; "
            + "interval skipped.");

    private final String template;

    Violation(final String template) {
        this.template = template;
    }

    String describe(final String field, final double value) {
        String message = template.replace("FIELD", field);
        return message.replace("VALUE", String.valueOf(value));
    }

    String describe(final String field) {
        return template.replace("FIELD", field);
    }
}
