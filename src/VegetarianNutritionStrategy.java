import java.util.ArrayList;
import java.util.List;

public class VegetarianNutritionStrategy implements NutritionStrategy {
    @Override
    public List<Meal> generateMeals(double targetCalories) {
        List<Meal> meals = new ArrayList<>();
        double remainingCalories = targetCalories;

        while (remainingCalories > 0) {
            Meal meal = MealFactory.createMeal(
                    "vegetarian",
                    meals.size() + 1,
                    "Vegetarian Meal " + (meals.size() + 1),
                    List.of("Pasta", "Tomatoes", "Cheese"),
                    450
            );
            meals.add(meal);
            remainingCalories -= meal.getCalories();
        }
        return meals;
    }
}
