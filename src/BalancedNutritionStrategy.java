import java.util.ArrayList;
import java.util.List;

public class BalancedNutritionStrategy implements NutritionStrategy {
    @Override
    public List<Meal> generateMeals(double targetCalories) {
        List<Meal> meals = new ArrayList<>();
        double remainingCalories = targetCalories;

        while (remainingCalories > 0) {
            Meal meal = MealFactory.createMeal(
                    "balanced",
                    meals.size() + 1,
                    "Balanced Meal " + (meals.size() + 1),
                    List.of("Chicken", "Rice", "Broccoli"),
                    600
            );
            meals.add(meal);
            remainingCalories -= meal.getCalories();
        }
        return meals;
    }
}
