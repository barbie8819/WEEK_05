package HandsOnProblems.JSONToXML;
import org.json.JSONObject;
import org.json.XML;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Main {
    public static void main(String[] args) {
        String jsonFile = "data.json";

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFile)));

            JSONObject json = new JSONObject(jsonContent);
            String xml = XML.toString(json);

            System.out.println("Converted XML:\n" + xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

