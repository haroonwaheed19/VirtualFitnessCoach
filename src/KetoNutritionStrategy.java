import java.util.ArrayList;
import java.util.List;

public class KetoNutritionStrategy implements NutritionStrategy {
    @Override
    public List<Meal> generateMeals(double targetCalories) {
        List<Meal> meals = new ArrayList<>();
        double remainingCalories = targetCalories;

        while (remainingCalories > 0) {
            Meal meal = MealFactory.createMeal(
                    "keto",
                    meals.size() + 1,
                    "Keto Meal " + (meals.size() + 1),
                    List.of("Avocado", "Eggs", "Chicken"),
                    500
            );
            meals.add(meal);
            remainingCalories -= meal.getCalories();
        }
        return meals;
    }
}
