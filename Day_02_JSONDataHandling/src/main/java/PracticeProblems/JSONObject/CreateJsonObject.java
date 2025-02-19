package PracticeProblems.JSONObject;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class CreateJsonObject {


    public static void main(String[] args) {
        JSONObject student = new JSONObject();
        student.put("name","Anshi");
        student.put("age",21);
        JSONArray subjects = new JSONArray();
        subjects.put("Maths");
        subjects.put("Hindi");
        subjects.put("English");

        student.put("subjects",subjects);
        System.out.println(student);
    }
}
