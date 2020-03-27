package transaction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static transaction.Currency.Builder.aCurrency;
import static transaction.Transaction.Builder.aTransaction;

public class CurrencyFilterAndCollect {

    public static void main(String[] args) {


        final Currency pounds = aCurrency().withCurrencySymbol("pound").build();
        final Currency euro = aCurrency().withCurrencySymbol("€").build();

        final Transaction pounds1000 = aTransaction().withAmount(1000).withCurrency(pounds).build();
        final Transaction pounds2000 = aTransaction().withAmount(2000).withCurrency(pounds).build();
        final Transaction pounds500 = aTransaction().withAmount(500).withCurrency(pounds).build();

        final Transaction euro1000 = aTransaction().withAmount(1000).withCurrency(euro).build();
        final Transaction euro2000 = aTransaction().withAmount(2000).withCurrency(euro).build();
        final Transaction euro500 = aTransaction().withAmount(500).withCurrency(euro).build();

        final List<Transaction> transactions = asList(pounds500, pounds1000, pounds2000, euro500, euro1000, euro2000);

        Map<Currency, List<Transaction>> transactionsByCurrency = new HashMap<>();

        long start = System.currentTimeMillis();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 500) {
                final Currency currency = transaction.getCurrency();

                List<Transaction> transactionForCurrency = transactionsByCurrency.get(currency);

                if (transactionForCurrency == null) {
                    transactionForCurrency = new ArrayList<>();
                    transactionForCurrency.add(transaction);
                    transactionsByCurrency.put(currency, transactionForCurrency);
                } else {
                    transactionForCurrency.add(transaction);
                    transactionsByCurrency.put(currency, transactionForCurrency);
                }
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("regular took: " + (end - start) + " grouped transactions" + transactionsByCurrency.toString());


        start = System.currentTimeMillis();
        Map<Currency, List<Transaction>> collect = transactions.parallelStream().filter(t -> t.getAmount() > 500).collect(Collectors.groupingBy(Transaction::getCurrency));
        end = System.currentTimeMillis();

        transactions.sort(Comparator.comparingInt(Transaction::getAmount));

        System.out.println("streams took: " + (end - start) + " grouped transactions" + collect.toString());
    }
}
