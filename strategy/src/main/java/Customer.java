import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Customer {

    private final List<Integer> drinks = new ArrayList<>();

    private BillingStrategy strategy;

    public Customer(BillingStrategy strategy) {
        this.strategy = strategy;
    }

    public void add(int price, int quantity) {
        this.drinks.add(this.strategy.getActualPrice(price * quantity));
    }


    public void printBill() {
        out.println("total due: " + this.drinks.stream().mapToInt(v -> v).sum());
        this.drinks.clear();
    }

    public void setStrategy(BillingStrategy strategy) {
        this.strategy = strategy;
    }
}
