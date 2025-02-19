package PracticeProblems.ValidateJSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(new File("user.json"), User.class);
            System.out.println("Valid JSON Structure!");
            System.out.println("User Name: " + user.getName());
            System.out.println("User Email: " + user.getEmail());
            System.out.println("User Age: " + user.getAge());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
