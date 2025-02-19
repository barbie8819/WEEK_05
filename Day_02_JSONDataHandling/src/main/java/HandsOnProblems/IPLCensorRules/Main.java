package HandsOnProblems.IPLCensorRules;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String jsonInputFile = "ipl_data.json";
        String csvInputFile = "ipl_data.csv";
        String jsonOutputFile = "ipl_data_censored.json";
        String csvOutputFile = "ipl_data_censored.csv";

        try {
            processJson(jsonInputFile, jsonOutputFile);

            processCsv(csvInputFile, csvOutputFile);

            System.out.println("✅ Data processed and saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void processJson(String inputFile, String outputFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode jsonArray = (ArrayNode) objectMapper.readTree(new File(inputFile));

        for (JsonNode match : jsonArray) {
            ((ObjectNode) match).put("team1", censorTeamName(match.get("team1").asText()));
            ((ObjectNode) match).put("team2", censorTeamName(match.get("team2").asText()));
            ((ObjectNode) match).put("winner", censorTeamName(match.get("winner").asText()));
            ((ObjectNode) match).put("player_of_match", "REDACTED");

            ObjectNode scoreNode = (ObjectNode) match.get("score");
            ObjectNode newScoreNode = objectMapper.createObjectNode();
            scoreNode.fields().forEachRemaining(entry ->
                    newScoreNode.put(censorTeamName(entry.getKey()), entry.getValue().asInt()));

            ((ObjectNode) match).set("score", newScoreNode);
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), jsonArray);
        System.out.println("✅ Censored JSON saved: " + outputFile);
    }


    private static void processCsv(String inputFile, String outputFile) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(inputFile));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        List<CSVRecord> records = csvParser.getRecords();
        List<String> headers = new ArrayList<>(csvParser.getHeaderNames());

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])));

        for (CSVRecord record : records) {
            List<String> newRow = new ArrayList<>();

            for (String header : headers) {
                String value = record.get(header);

                if (header.equalsIgnoreCase("team1") || header.equalsIgnoreCase("team2") || header.equalsIgnoreCase("winner")) {
                    newRow.add(censorTeamName(value));
                } else if (header.equalsIgnoreCase("player_of_match")) {
                    newRow.add("REDACTED");
                } else {
                    newRow.add(value);
                }
            }
            csvPrinter.printRecord(newRow);
        }

        csvPrinter.flush();
        csvPrinter.close();
        System.out.println("✅ Censored CSV saved: " + outputFile);
    }


    private static String censorTeamName(String teamName) {
        String[] words = teamName.split(" ");
        if (words.length > 1) {
            return words[0] + " ***";
        }
        return teamName;
    }
}
