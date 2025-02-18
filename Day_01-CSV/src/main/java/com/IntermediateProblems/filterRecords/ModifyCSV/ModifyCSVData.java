package com.IntermediateProblems.filterRecords.ModifyCSV;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
public class ModifyCSVData {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        String updatedFilePath = "updated_employees.csv";

        // Write employee records to CSV file
        writeEmployeeData(filePath);

        // Confirm file creation
        if (Files.exists(Path.of(filePath))) {
            System.out.println("Employee data written to " + filePath);
        } else {
            System.out.println("Failed to write employee data.");
        }

        // Modify salaries for IT department
        modifySalaries(filePath, updatedFilePath);
        System.out.println("Updated employee records saved to " + updatedFilePath);
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

    // Method to modify salaries for employees in the IT department by 10%
    private static void modifySalaries(String inputFilePath, String outputFilePath) {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String header = reader.readLine();
            if (header != null) {
                updatedLines.add(header);

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",");
                    if (details.length == 4) {
                        String department = details[2];
                        double salary = Double.parseDouble(details[3]);

                        // Increase salary by 10% if department is IT
                        if ("IT".equalsIgnoreCase(department)) {
                            salary *= 1.10;
                        }
                        details[3] = String.format("%.2f", salary);
                        updatedLines.add(String.join(",", details));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error processing the file: " + inputFilePath);
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String line : updatedLines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Salaries updated and written to: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + outputFilePath);
            e.printStackTrace();
        }
    }
}
