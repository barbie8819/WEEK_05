package com.AdvancedProblems.MergeFiles;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String file1 = "students1.csv";
        String file2 = "students2.csv";
        String outputFile = "merged_students.csv";

        createSampleFiles(file1, file2);
        mergeCSVFiles(file1, file2, outputFile);
    }

    public static void createSampleFiles(String file1, String file2) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file1))) {
            bw.write("ID,Name,Age\n");
            bw.write("1,Alice,20\n");
            bw.write("2,Bob,22\n");
            bw.write("3,Charlie,21\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file2))) {
            bw.write("ID,Marks,Grade\n");
            bw.write("1,85,A\n");
            bw.write("2,78,B\n");
            bw.write("3,90,A\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergeCSVFiles(String file1, String file2, String outputFile) {
        Map<String, String[]> studentData = new HashMap<>();

        // Read first CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    studentData.put(parts[0], new String[]{parts[1], parts[2], "", ""});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read second CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && studentData.containsKey(parts[0])) {
                    String[] data = studentData.get(parts[0]);
                    data[2] = parts[1];
                    data[3] = parts[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write merged data to the output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("ID,Name,Age,Marks,Grade\n");
            for (Map.Entry<String, String[]> entry : studentData.entrySet()) {
                String id = entry.getKey();
                String[] data = entry.getValue();
                bw.write(id + "," + String.join(",", data) + "\n");
            }
            System.out.println("Merged file created successfully: " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
