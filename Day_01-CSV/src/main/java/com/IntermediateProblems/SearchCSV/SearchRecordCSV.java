package com.IntermediateProblems.SearchCSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
public class SearchRecordCSV {
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

        // Search for an employee by name
        String searchName = "Jane Doe";
        String result = searchEmployeeByName(filePath, searchName);
        System.out.println(result);
    }

    // Method to write sample employee data to the CSV file
    private static void writeEmployeeData(String filePath) {
        String data = "ID,Name,Department,Salary\n"
                + "201,John Smith,Sales,55000\n"
                + "202,Jane Doe,Marketing,60000\n"
                + "203,Emily Johnson,HR,52000\n"
                + "204,Michael Brown,IT,75000\n"
                + "205,Sarah Davis,Finance,67000\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
            System.out.println("Employee records successfully written.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    // Method to search for an employee by name and print their department and salary
    private static String searchEmployeeByName(String filePath, String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Skip the header
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4 && details[1].equalsIgnoreCase(name)) {
                    return "Employee Found: Department - " + details[2] + ", Salary - " + details[3];
                }
            }
        } catch (IOException e) {
            return "Error reading the file: " + filePath;
        }
        return "Employee not found: " + name;
    }
}
