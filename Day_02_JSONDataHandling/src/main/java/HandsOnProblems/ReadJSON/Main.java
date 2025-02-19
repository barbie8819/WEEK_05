package HandsOnProblems.ReadJSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        String filePath = "students.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            printJson(rootNode, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printJson(JsonNode node, String prefix) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                printJson(field.getValue(), prefix + field.getKey() + ".");
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                printJson(node.get(i), prefix + "[" + i + "].");
            }
        } else {
            System.out.println(prefix.substring(0, prefix.length() - 1) + " : " + node.asText());
        }
    }
}
