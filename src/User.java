import java.util.*;
import java.util.ArrayList;

public class User {
    private String id, name, email, password, fitnessGoal;
    private int age;
    private double weight, height;
    private List<String> progress = new ArrayList<>();

    public User(String id, String name, String email, String password, String fitnessGoal, int age, double weight, double height) {
        if (!isValidAge(age) || !isValidWeight(weight) || !isValidHeight(height)) {
            throw new IllegalArgumentException("Invalid input values for User attributes.");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.fitnessGoal = fitnessGoal;
    }

    private boolean isValidAge(int age) {
        return age > 0;
    }

    private boolean isValidWeight(double weight) {
        return weight > 0;
    }

    private boolean isValidHeight(double height) {
        return height > 0;
    }

    public void logProgress(String activity, double caloriesBurned) {
        progress.add(new Date() + ": " + activity + " - " + caloriesBurned + " Calories Burned.");
    }

    public void printInfo() {
        System.out.println("Name : " + name);
        System.out.println("Id : " + id);
        System.out.println("Email : " + email);
        System.out.println("Fitness Goal : " + fitnessGoal);
        System.out.println("Age : " + age);
        System.out.println("Weight : " + weight + " kg");
        System.out.println("Height : " + height + " feets");
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
        this.id = id.toLowerCase();
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.fitnessGoal = fitnessGoal;
    }

    public String getUserDetails()
    {
        return "Name : " + name + " Id : " + id + " Fitness Goal : " + fitnessGoal + " Age : " + age + " Height : " + height + " ft Weight : " + weight + " Kg";
    }

}
