package PracticeProblems.ObjectToJSON;

import org.json.JSONObject;

public class Main {


    public static void main(String[] args) {
        Car car  = new Car("Scorpio","XUV-N",2023);
        JSONObject carObject = new JSONObject();
        carObject.put("brand",car.getBrand());
        carObject.put("model",car.getModel());
        carObject.put("year",car.getYear());
        System.out.println(carObject.toString(4));
    }
}
