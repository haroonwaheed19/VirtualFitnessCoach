import java.util.Arrays;
import java.util.List;

public class KetoMeal implements Meal {
    private int id;
    private String name;
    private List<String> ingredients;
    private double calories;
    private String dietType;

    public KetoMeal(int id, String name, List<String> ingredients, double calories) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.dietType = "Keto";
    }

    @Override
    public String getNutritionalInfo() {
        return "Calories: " + calories + " | Ingredients: " + String.join(", ", ingredients);
    }

    @Override
    public void prepareMeal() {
        System.out.println("Preparing Keto Meal: " + name);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public double getCalories() {
        return calories;
    }

    @Override
    public String getDietType() {
        return dietType;
    }
}
