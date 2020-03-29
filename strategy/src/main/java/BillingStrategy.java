interface BillingStrategy {

    int getActualPrice(int rawPrice);

    static BillingStrategy normalStrategy() {
        return rawPrice -> rawPrice;
    }

    static BillingStrategy happyHourStrategy() {
        return rawPrice -> rawPrice / 2;
    }
}
