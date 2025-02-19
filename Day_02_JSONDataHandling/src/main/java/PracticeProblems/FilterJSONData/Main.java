package PracticeProblems.FilterJSONData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Student> students = mapper.readValue(new File("students.json"), new TypeReference<List<Student>>() {
            });
            List<Student> filtered = students.stream()
            .filter(student -> student.getAge()>25)
                    .collect(Collectors.toList());
            System.out.println("Students with Age > 25:");
            for (Student student : filtered) {
                System.out.println(student.getName() + " - " + student.getAge() + " - " + student.getEmail());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
