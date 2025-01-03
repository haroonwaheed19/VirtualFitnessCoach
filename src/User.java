import java.util.*;
import java.util.ArrayList;

public class User {
    String id, name, email,password,fitnessGoal;
    int age;
    double weight, height;
    List<String> progress = new ArrayList<>();

    public User(String id, String name, String email, String password, String fitnessGoal, int age, double weight, double height)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.fitnessGoal = fitnessGoal;
    }

    //Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProgress(List<String> progress) {
        this.progress = progress;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    //Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public List<String> getProgress() {
        return progress;
    }

    public String getId() {
        return id;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void updateProfile(String id, String name, String email, String password, String fitnessGoal, int age, double weight, double height)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.fitnessGoal = fitnessGoal;
    }

    public void logProgress(String activity, double caloriesBurned)
    {
        progress.add(activity + " - " + caloriesBurned + " Calories Burned.");
    }

    public void printInfo()
    {
        System.out.println("Name : " + name);
        System.out.println("Id : " + id);
        System.out.println("email : " + email);
        System.out.println("fitness goal : " + fitnessGoal);
        System.out.println("age : " + age);
        System.out.println("weight : " + weight + " kg");
        System.out.println("height : " + height + " feet");

    }
}
