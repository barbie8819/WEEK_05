package com.AdvancedProblems.JSONToCSV;

import org.json.*;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        String jsonFilePath = "students.json";  // Path to the input JSON file
        String csvFilePath = "students.csv";    // Path to the output CSV file

        // Convert JSON to CSV
        convertJsonToCsv(jsonFilePath, csvFilePath);
    }

    // Method to convert JSON to CSV
    public static void convertJsonToCsv(String jsonFilePath, String csvFilePath) {
        try {
            // Read the JSON file
            BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();

            // Parse JSON content
            JSONArray jsonArray = new JSONArray(jsonString.toString());

            // Create a CSV writer
            CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath));

            // Write header (first record keys)
            JSONObject firstStudent = jsonArray.getJSONObject(0);
            Iterator<String> keys = firstStudent.keys();
            String[] header = new String[firstStudent.length()];
            int i = 0;
            while (keys.hasNext()) {
                header[i++] = keys.next();
            }
            csvWriter.writeNext(header);

            // Write student data
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject student = jsonArray.getJSONObject(j);
                String[] studentData = new String[firstStudent.length()];
                i = 0;
                keys = student.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    studentData[i++] = student.get(key).toString();
                }
                csvWriter.writeNext(studentData);
            }

            csvWriter.close();
            System.out.println("JSON converted to CSV successfully!");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}