import java.util.ArrayList;
import java.util.List;

public class VeganNutritionStrategy implements NutritionStrategy {
    @Override
    public List<Meal> generateMeals(double targetCalories) {
        List<Meal> meals = new ArrayList<>();
        double remainingCalories = targetCalories;

        while (remainingCalories > 0) {
            Meal meal = MealFactory.createMeal(
                    "vegan",
                    meals.size() + 1,
                    "Vegan Meal " + (meals.size() + 1),
                    List.of("Quinoa", "Spinach", "Tofu"),
                    400
            );
            meals.add(meal);
            remainingCalories -= meal.getCalories();
        }
        return meals;
    }
}
