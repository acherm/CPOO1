package org.example;

public class TableExtractor {



    public static void main(String[] args) {

        // extract a table from an URL and convert it into CSV
        // the table is identified by its class name
        // the table is converted into CSV by using the first row as header
        // the CSV is printed to the standard output
        // the CSV is also written into a file named "table.csv"
        // the URL is given as a command line argument
        // the class name is given as a command line argument
        // the CSV is printed to the standard output


        // the URL is given as a command line argument
        String url = args[0];
        // the class name is given as a command line argument
        String className = args[1];

        // the CSV is printed to the standard output
        System.out.println("URL: " + url);
        System.out.println("Class name: " + className);


        // the CSV is also written into a file named "table.csv"
        String filename = "table.csv";
        // the CSV is printed to the standard output
        System.out.println("CSV file: " + filename);












    }
}
