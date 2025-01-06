import java.util.ArrayList;
import java.util.List;

public class NutritionPlan {
    private int id;
    private String dietType;
    private double calories;
    private List<Meal> meals;  // List of meals
    private NutritionStrategy strategy;

    // Constructor to initialize the NutritionPlan
    public NutritionPlan(int id, String dietType, double calories) {
        this.id = id;
        this.dietType = dietType;
        this.calories = calories;
        this.meals = new ArrayList<>();
    }

    // Add a method to associate meals with this nutrition plan
    public void addMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public String getDietType() {
        return dietType;
    }

    public int getId() {
        return id;
    }

    public double getCalories() {
        return calories;
    }

    public void setStrategy(NutritionStrategy strategy) {
        this.strategy = strategy;
    }

    public void generatePlan() {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set for NutritionPlan");
        }
        meals = strategy.generateMeals(calories);  // Populating the meals list
    }

    public void updatePlan(double newCalories) {
        this.calories = newCalories;
        generatePlan();
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void displayPlan() {
        // Check if meals list is empty before trying to display
        if (meals == null || meals.isEmpty()) {
            System.out.println("No meals available in this nutrition plan.");
        } else {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("Nutritional Plan Id : " + getId());
            System.out.println("Nutrition Plan (Diet: " + dietType + ", Total Calories: " + calories + "):");
            for (Meal meal : meals) {
                System.out.println(" - " + meal.getName() + ": " + meal.getNutritionalInfo());
                System.out.println("---------------------------------------------------------------------------");
            }

        }
    }
}
