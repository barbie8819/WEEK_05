package com.AdvancedProblems.LargeFile;
import java.io.*;
import java.util.*;

public class Main {

        public static void main(String[] args) {
            String largeFile = "large_dataset.csv";
            processLargeCSVInChunks(largeFile, 100);
        }

        public static void processLargeCSVInChunks(String fileName, int chunkSize) {
            int recordCount = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                int lineCounter = 0;

                while ((line = br.readLine()) != null) {
                    lineCounter++;
                    recordCount++;

                    if (lineCounter == chunkSize) {
                        System.out.println("Processed " + recordCount + " records so far.");
                        lineCounter = 0;
                    }
                }
                System.out.println("Total records processed: " + recordCount);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
