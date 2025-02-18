package com.AdvancedProblems.CSVToObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Student {
    int id;
    String name;
    int age;
    double marks;

    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", marks=" + marks +
                '}';
    }
}
public class Main {
    public static void main(String[] args) {
        String filePath = "students.csv";

        // Write sample student data to CSV file
        writeStudentData(filePath);

        // Convert CSV records to Student objects
        List<Student> students = readStudentsFromCSV(filePath);

        // Print student objects
        students.forEach(System.out::println);
    }

    // Method to write sample student data to the CSV file
    private static void writeStudentData(String filePath) {
        String data = "ID,Name,Age,Marks\n"
                + "101,John Doe,20,85.5\n"
                + "102,Jane Smith,22,78.0\n"
                + "103,Emily Johnson,19,92.3\n"
                + "104,Michael Brown,21,88.7\n"
                + "105,Sarah Davis,23,81.4\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
            System.out.println("Student records successfully written.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    // Method to read students from CSV and convert to objects
    private static List<Student> readStudentsFromCSV(String filePath) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String header = reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) {
                    int id = Integer.parseInt(details[0]);
                    String name = details[1];
                    int age = Integer.parseInt(details[2]);
                    double marks = Double.parseDouble(details[3]);
                    students.add(new Student(id, name, age, marks));
                } else {
                    System.out.println("Invalid row format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading the file: " + filePath);
            e.printStackTrace();
        }

        return students;
    }
}


