package com.BasicProblems.IntermediateProblems.SortCSV;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortCSVBYCol {
    public static void main(String[] args) {
        String filePath = "employees.csv";

        // Write employee records to CSV file
        writeEmployeeData(filePath);

        // Confirm file creation
        if (Files.exists(Path.of(filePath))) {
            System.out.println("Employee data written to " + filePath);
        } else {
            System.out.println("Failed to write employee data.");
        }

        // Sort records by Salary and print the top 5 highest-paid employees
        sortAndPrintTopSalaries(filePath);
    }

    // Method to write sample employee data to the CSV file
    private static void writeEmployeeData(String filePath) {
        String data = "ID,Name,Department,Salary\n"
                + "201,John Smith,Sales,55000\n"
                + "202,Jane Doe,Marketing,60000\n"
                + "203,Emily Johnson,HR,52000\n"
                + "204,Michael Brown,IT,75000\n"
                + "205,Sarah Davis,Finance,67000\n"
                + "206,David White,IT,80000\n"
                + "207,Linda Green,Sales,72000\n"
                + "208,James Black,HR,50000\n"
                + "209,Emma Wilson,Marketing,65000\n"
                + "210,Robert Brown,Finance,77000\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
            System.out.println("Employee records successfully written.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    // Method to sort records by Salary in descending order and print the top 5 highest-paid employees
    private static void sortAndPrintTopSalaries(String filePath) {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String header = reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) {
                    records.add(details);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + filePath);
            e.printStackTrace();
            return;
        }

        // Sort records by salary in descending order
        records.sort((a, b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])));

        // Print the top 5 highest-paid employees
        System.out.println("Top 5 highest-paid employees:");
        records.stream().limit(5).forEach(record ->
                System.out.printf("Name: %s, Department: %s, Salary: %s%n", record[1], record[2], record[3])
        );
    }
}
