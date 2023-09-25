package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ExpenseTest {




    @Test
    public void testExpenseOnlyUSD() throws Exception {
        String filename = "foo.txt";
        // write an array of strings into a file, in line being an element of the array
        List<String> lines = List.of("2016-01-02 -34.01 USD", "2016-01-03 2.59 USD", "2016-01-03 -2.72 USD");
        java.nio.file.Files.write(Paths.get(filename), lines);
        assertEquals(-34.01 + 2.59 -2.72, new ExpenseComputation().compute_expense(filename), 0.1);
    }

    @Test
    public void testExpense() throws Exception {
        String filename = "foo.txt";
        // write an array of strings into a file, in line being an element of the array
        List<String> lines = List.of("2016-01-02 -34.01 USD", "2016-01-03 2.59 DKK", "2016-01-03 -2.72 DKK");
        java.nio.file.Files.write(Paths.get(filename), lines);
        assertEquals(-34.03, new ExpenseComputation().compute_expense(filename), 0.1);
    }

    @Test
    public void testExpenseBis() throws Exception {
        String filename = "foo.txt";
        // write an array of strings into a file, in line being an element of the array
        List<String> lines = List.of("2016-01-02 -34.01 EUR", "2016-01-03 2.59 DKK", "2016-01-03 -2.72 DKK");
        java.nio.file.Files.write(Paths.get(filename), lines);
        assertEquals(-37.81, new ExpenseComputation().compute_expense(filename), 0.1);
    }


    @Test
    public void testExpenseErrorDevise() throws Exception {
        String filename = "foo.txt";
        // write an array of strings into a file, in line being an element of the array
        List<String> lines = List.of("2016-01-02 -34.01", "2016-01-03 2.59 DKK", "2016-01-03 -2.72 DKK");
        java.nio.file.Files.write(Paths.get(filename), lines);
        assertEquals(-37.81, new ExpenseComputation().compute_expense(filename), 0.1); // should fail!
    }

    @Test
    public void testExpenseErrorMontant() throws Exception {
        String filename = "foo.txt";
        // write an array of strings into a file, in line being an element of the array
        List<String> lines = List.of("2016-01-02 EUR", "2016-01-03 2.59 DKK", "2016-01-03 -2.72 DKK");
        java.nio.file.Files.write(Paths.get(filename), lines);
        assertThrows(IllegalArgumentException.class, () -> new ExpenseComputation().compute_expense(filename));
    }



}

