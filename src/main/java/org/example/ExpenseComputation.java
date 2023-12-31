package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ExpenseComputation {

    private ExpenseParser parser;
    private Collection<Expense> exps ;


    public double compute_expense(String path) throws IOException {

        // Given a textual file "foo.txt" containing the expenses of a person,
        // your goal is to compute the total amount of money spent by this person.
        // The format of the file is the following:
        // 2016-01-02 -34.01 USD
        // 2016-01-03 2.59 DKK
        // 2016-01-03 -2.72 EUR

        // generated by Copilot!
        // The first column is the date of the expense, the second column is the amount of money spent,
        // and the third column is the currency in which the expense was made.
        // The amount of money can be either positive or negative.
        // The currency can be either USD, DKK, or EUR.
        // The exchange rate is the following:
        // 1 USD = 6.5 DKK
        // 1 EUR = 7.5 DKK
        // The goal is to compute the total amount of money spent by this person in DKK.
        // In the example above, the total amount of money spent is -34.01 + 2.59 * 6.5 - 2.72 * 7.5 = -37.81 DKK.
        // The result should be rounded to the nearest integer.
        // The result should be a double.
        // If the file does not exist, the method should throw an IOException.

        this.parser = new ExpenseParser();

        List<String> strs = parser.parse(new File(path));
        this.exps = new ArrayList<>();
        for (String s: strs) {
            Expense ex = parser.parse(s);
            exps.add(ex);
        }

        double total = 0;
        for (Expense e: exps) {
            total += e.amountExpense.expense();
        }

        return total;
    }

    public double compute_expense_basic(String path) throws IOException {


        List<String> lines = java.nio.file.Files.readAllLines(Paths.get(path));
        double total = 0.0;
        for (String line : lines) {
            // each line is a string containing the date, amount, and currency of an expense
            // you can split the line into its components using the following code:
            String[] parts = line.split(" ");

            if (parts.length != 3) {
                throw new IllegalArgumentException("Wrong format");
            }

            // TODO refactoring
            String date = parts[0];
            double amount = Double.parseDouble(parts[1]);
            String currency = parts[2];

            // total amount of money spent by this person in USD


            //
            // TODO refactoring
            // https://jcp.org/en/jsr/detail?id=354
            if (currency.equals("USD")) {
                total += amount;
            } else if (currency.equals("DKK")) {
                total += amount / 6.5;
            } else if (currency.equals("EUR")) {
                total += amount / 0.9;
            }
        }
        return total;

     }
}

class Expense {
    String date;
    AmountExpense amountExpense;

    public Expense(String date, AmountExpense amountExpense) {
        this.date = date;
        this.amountExpense = amountExpense;
    }

    // complete !
    // input data generation
    // assertion



}

class AmountExpense {
    double amount;
    Currency currency;

    public AmountExpense(double amount, String currency) {
        this.amount = amount;
        this.currency = Currency.valueOf(currency);
    }

    public double expense() {
        if (currency.equals(Currency.USD)) {
            return amount;
        } else if (currency.equals(Currency.DKK)) {
            return amount / 6.5;
        } else if (currency.equals(Currency.EUR)) {
            return amount / 0.9;
        }
        return Double.NaN;
    }
}

enum Currency {
    USD, DKK, EUR
}

class ExpenseParser {

    public List<String> parse(File f) throws IOException {
        return java.nio.file.Files.readAllLines(f.toPath());
    }

    public Expense parse(String line) {

        String[] parts = line.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Wrong format");
        }

        // TODO refactoring
        String date = parts[0];
        double amount = Double.parseDouble(parts[1]);
        String currency = parts[2];

        return new Expense(date, new AmountExpense(amount, currency));
    }
}