import java.util.List;

public interface NutritionStrategy {
    List<Meal> generateMeals(double targetCalories);
}
