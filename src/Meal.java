import java.util.List;

public interface Meal {

    String getNutritionalInfo();

    void prepareMeal();

    int getId();

    String getName();

    List<String> getIngredients();

    double getCalories();

    String getDietType();
}
