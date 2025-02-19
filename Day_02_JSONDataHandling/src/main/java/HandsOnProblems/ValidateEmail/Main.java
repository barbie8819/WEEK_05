package HandsOnProblems.ValidateEmail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        String schemaFile = "email_schema.json";
        String jsonFile = "email_data.json";

        try {
            Schema schema = loadSchema(schemaFile);

            JSONObject jsonData = loadJson(jsonFile);

            schema.validate(jsonData);
            System.out.println("✅ Email is valid!");

        } catch (Exception e) {
            System.out.println("❌ Validation failed: " + e.getMessage());
        }
    }

    private static Schema loadSchema(String schemaPath) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(new File(schemaPath))) {
            JSONObject schemaJson = new JSONObject(new JSONTokener(inputStream));
            return SchemaLoader.load(schemaJson);
        }
    }

    private static JSONObject loadJson(String jsonPath) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(new File(jsonPath))) {
            return new JSONObject(new JSONTokener(inputStream));
        }
    }

}
