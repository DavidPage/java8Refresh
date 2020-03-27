package transaction;


import java.util.Objects;

public class Transaction {

    private Currency currency;
    private int amount;

    public Transaction(Currency currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    public static final class Builder {
        private Currency currency;
        private int amount;

        private Builder() {
        }

        public static Builder aTransaction() {
            return new Builder();
        }

        public Builder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Transaction build() {
            return new Transaction(currency, amount);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount &&
                currency.equals(that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                " amount=" + amount +
                '}';
    }
}
