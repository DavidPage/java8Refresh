package transaction;

import java.util.Objects;

public class Currency {

    private String currencySymbol;

    public Currency(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }


    public static final class Builder {
        private String currencySymbol;

        private Builder() {
        }

        public static Builder aCurrency() {
            return new Builder();
        }

        public Builder withCurrencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
            return this;
        }

        public Currency build() {
            return new Currency(currencySymbol);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return currencySymbol.equals(currency.currencySymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencySymbol);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencySymbol='" + currencySymbol + '\'' +
                '}';
    }
}
