package com.BasicProblems.CountRows;
import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
public class CountRowsCSV {



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

            // Read and count rows in the CSV file
            int recordCount = countRecords(filePath);
            System.out.println("Number of records (excluding header): " + recordCount);
        }

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

        private static int countRecords(String filePath) {
            int count = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                // Skip the header line
                reader.readLine();
                while (reader.readLine() != null) {
                    count++;
                }
            } catch (IOException e) {
                System.out.println("Error reading the file: " + filePath);
                e.printStackTrace();
            }
            return count;
        }
}
