package com.BasicProblems.IntermediateProblems.filterRecords;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilterRecordCSV {
    public static void main(String[] args) {
        String filePath = "students.csv";

        // Write student records to CSV file
        writeStudentData(filePath);

        // Confirm file creation
        if (Files.exists(Path.of(filePath))) {
            System.out.println("Student data written to " + filePath);
        } else {
            System.out.println("Failed to write student data.");
        }

        // Filter and print students with marks > 80
        List<String> qualifyingRecords = filterHighScorers(filePath);
        System.out.println("Students with marks > 80:");
        qualifyingRecords.forEach(System.out::println);
    }

    // Method to write sample student data to the CSV file
    private static void writeStudentData(String filePath) {
        String data = "ID,Name,Age,Marks\n"
                + "101,John Smith,20,75\n"
                + "102,Jane Doe,21,88\n"
                + "103,Emily Johnson,19,92\n"
                + "104,Michael Brown,22,67\n"
                + "105,Sarah Davis,20,85\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
            System.out.println("Student records successfully written.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    // Method to filter and return students who scored more than 80 marks
    private static List<String> filterHighScorers(String filePath) {
        List<String> highScorers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) {
                    int marks = Integer.parseInt(details[3].trim());
                    if (marks > 80) {
                        highScorers.add(line);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading or processing the file: " + filePath);
            e.printStackTrace();
        }
        return highScorers;
    }
}
