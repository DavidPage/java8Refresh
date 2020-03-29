package transaction;

import java.util.*;
import java.util.stream.Collectors;

import static transaction.Currency.Builder.aCurrency;
import static transaction.Transaction.Builder.aTransaction;

public class CurrencyFilterAndCollect {

    public static void main(String[] args) {

        final Currency pounds = aCurrency().withCurrencySymbol("pound").build();
        final Currency euro = aCurrency().withCurrencySymbol("€").build();

        List<Transaction> transactions = new LinkedList();

        for (int poundsTransactions = 0; poundsTransactions < 1000000; poundsTransactions++) {
            transactions.add(aTransaction().withAmount(poundsTransactions).withCurrency(pounds).build());
        }

        for (int euroTransactions = 0; euroTransactions < 1000000; euroTransactions++) {
            transactions.add(aTransaction().withAmount(euroTransactions).withCurrency(euro).build());
        }

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

        System.out.println("regular took: " + (end - start) + " grouped transactions");


        start = System.currentTimeMillis();
        Map<Currency, List<Transaction>> collect = transactions.parallelStream().filter(t -> t.getAmount() > 500).collect(Collectors.groupingBy(Transaction::getCurrency));
        end = System.currentTimeMillis();

        transactions.sort(Comparator.comparingInt(Transaction::getAmount));

        System.out.println("parallel took: " + (end - start) + " grouped transactions");
    }
}
