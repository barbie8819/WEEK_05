package com.BasicProblems;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CSVReaderDemo {
    public static void main(String[] args) {
        String filePath = "students.csv";

        // Check if the file exists
        if (!Files.exists(Path.of(filePath))) {
            System.out.println("File not found: " + filePath);
            System.out.println("Creating sample file...");
            createSampleCSV(filePath);
        }

        // Read and print CSV data
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) {
                    String id = details[0].trim();
                    String name = details[1].trim();
                    String age = details[2].trim();
                    String marks = details[3].trim();

                    System.out.println("Student Record:");
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                    System.out.println("Marks: " + marks);
                    System.out.println("----------------------");
                } else {
                    System.out.println("Invalid record: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createSampleCSV(String filePath) {
        String sampleData = "101,John Doe,20,85\n102,Jane Smith,22,90\n103,Mark Lee,21,78\n";
        try {
            Files.write(Path.of(filePath), sampleData.getBytes(), StandardOpenOption.CREATE);
            System.out.println("Sample file created successfully.");
        } catch (IOException e) {
            System.out.println("Failed to create sample file.");
            e.printStackTrace();
        }
    }
}
