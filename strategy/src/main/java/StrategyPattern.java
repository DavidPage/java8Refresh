public class StrategyPattern {

    public static void main(String[] args) {

        final BillingStrategy normalStrategy = BillingStrategy.normalStrategy();
        final BillingStrategy happyHourStrategy = BillingStrategy.happyHourStrategy();

        final Customer firstCustomer = new Customer(normalStrategy);
        final Customer secondCustomer = new Customer(happyHourStrategy);

        firstCustomer.setStrategy(normalStrategy);

        firstCustomer.add(100, 1);
        firstCustomer.add(100, 2);

        firstCustomer.printBill();

        secondCustomer.setStrategy(happyHourStrategy);
        secondCustomer.add(80, 1);

        secondCustomer.setStrategy(normalStrategy);
        secondCustomer.add(130, 2);
        secondCustomer.add(250, 1);

        secondCustomer.printBill();
    }
}
