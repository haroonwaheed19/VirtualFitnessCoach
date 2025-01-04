import java.util.List;

public class MealFactory {
    public static Meal createMeal(String type, int id, String name, List<String> ingredients, double calories) {
        switch (type.toLowerCase()) {
            case "keto":
                return new KetoMeal(id, name, ingredients, calories);
            case "vegan":
                return new VeganMeal(id, name, ingredients, calories);
            case "vegetarian":
                return new VegetarianMeal(id, name, ingredients, calories);
            case "balanced":
                return new BalancedMeal(id, name, ingredients, calories);
            default:
                throw new IllegalArgumentException("Unknown meal type: " + type);
        }
    }
}
