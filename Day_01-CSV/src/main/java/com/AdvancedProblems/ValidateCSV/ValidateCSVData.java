package com.AdvancedProblems.ValidateCSV;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
public class ValidateCSVData {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");

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

        // Validate and print CSV data
        validateCSVData(filePath);
    }

    // Method to write sample employee data to the CSV file
    private static void writeEmployeeData(String filePath) {
        String data = "ID,Name,Email,Phone,Department,Salary\n"
                + "201,John Smith,john.smith@example.com,1234567890,Sales,55000\n"
                + "202,Jane Doe,jane.doe@invalid,email.com,0987654321,Marketing,60000\n"
                + "203,Emily Johnson,emily.johnson@example.com,12345,HR,52000\n"
                + "204,Michael Brown,michael.brown@example.com,1122334455,IT,75000\n"
                + "205,Sarah Davis,sarah.davis@company.org,6677889900,Finance,67000\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
            System.out.println("Employee records successfully written.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    // Method to validate email and phone number fields
    private static void validateCSVData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String header = reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 6) {
                    String email = details[2];
                    String phone = details[3];

                    boolean validEmail = EMAIL_PATTERN.matcher(email).matches();
                    boolean validPhone = PHONE_PATTERN.matcher(phone).matches();

                    if (!validEmail || !validPhone) {
                        System.out.printf("Invalid record: %s. Error: %s%s%n",
                                line,
                                validEmail ? "" : "Invalid Email. ",
                                validPhone ? "" : "Invalid Phone Number."
                        );
                    }
                } else {
                    System.out.println("Invalid row format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + filePath);
            e.printStackTrace();
        }
    }
}
