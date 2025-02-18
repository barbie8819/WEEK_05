package com.AdvancedProblems.DetectDuplicates;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "large_dataset.csv";
        detectDuplicateRecords(fileName);
    }

    public static void detectDuplicateRecords(String fileName) {
        Map<String, String> recordMap = new HashMap<>();
        Set<String> duplicates = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Read header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    String id = parts[0];
                    if (recordMap.containsKey(id)) {
                        duplicates.add(line);
                    } else {
                        recordMap.put(id, line);
                    }
                }
            }

            if (duplicates.isEmpty()) {
                System.out.println("No duplicate records found.");
            } else {
                System.out.println("Duplicate records found:");
                for (String record : duplicates) {
                    System.out.println(record);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
