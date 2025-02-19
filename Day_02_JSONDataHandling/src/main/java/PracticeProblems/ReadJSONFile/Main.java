package PracticeProblems.ReadJSONFile;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("user.json")));
            JSONObject userJ = new JSONObject(content);
            String name = userJ.getString("name");
            String email = userJ.getString("email");
            System.out.println("Name : "+ name);
            System.out.println("Email : "+ email);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
