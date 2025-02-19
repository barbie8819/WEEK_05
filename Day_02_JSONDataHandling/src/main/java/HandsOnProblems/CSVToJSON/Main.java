package HandsOnProblems.CSVToJSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        String csvFile = "data.csv";
        String jsonFile = "output.json";

        try {
            List<String> lines = Files.readAllLines(Paths.get(csvFile));

            if (lines.isEmpty()) {
                System.out.println("CSV file is empty!");
                return;
            }

            String[] headers = lines.get(0).split(",");
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();

            for (int i = 1; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                ObjectNode jsonObject = objectMapper.createObjectNode();

                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j].trim(), values[j].trim());
                }
                jsonArray.add(jsonObject);
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFile), jsonArray);
            System.out.println("✅ CSV converted to JSON: " + jsonFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
