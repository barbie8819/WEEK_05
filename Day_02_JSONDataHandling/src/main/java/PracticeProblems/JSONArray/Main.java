package PracticeProblems.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        try{
            List<Student> students= Arrays.asList(
                    new Student("Anshi","anshi@gmail.com",21),
                    new Student("Ansh","anshi@gmail.com",21),
                    new Student("Anshika","anshi@gmail.com",21));
            ObjectMapper mapper = new ObjectMapper();
            String jsonArray = mapper.writeValueAsString(students);
            System.out.println(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
