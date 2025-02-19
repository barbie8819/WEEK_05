package PracticeProblems.MergeTwoJSON;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
public class Main {
    public static void main(String[] args) {
        try {
            String user1Content = new String(Files.readAllBytes(Paths.get("user1.json")));
            String user2Content = new String(Files.readAllBytes(Paths.get("user2.json")));

            JSONObject user1 = new JSONObject(user1Content);
            JSONObject user2 = new JSONObject(user2Content);

            for(String key : user2.keySet()){
                user1.put(key,user2.get(key));
            }

            // Print merged JSON
            System.out.println("Merged JSON: " + user1.toString(4));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
