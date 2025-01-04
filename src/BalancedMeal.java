import java.util.List;

public class BalancedMeal implements Meal {
    private int id;
    private String name;
    private List<String> ingredients;
    private double calories;
    private String dietType;

    public BalancedMeal(int id, String name, List<String> ingredients, double calories) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.dietType = "Balanced";
    }

    @Override
    public String getNutritionalInfo() {
        return "Calories: " + calories + " | Ingredients: " + String.join(", ", ingredients);
    }

    @Override
    public void prepareMeal() {
        System.out.println("Preparing Balanced Meal: " + name);
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
