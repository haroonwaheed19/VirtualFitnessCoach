import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Admin and Users
        Admin a1 = Admin.getInstance("F2022065033", "Haroon Waheed", "haroonwaheed002@gmail.com");
        User u1 = new User("F2022065033", "Haroon Waheed", "haroonwaheed002@gmail.com", "testing", "WeightLoss", 20, 72.80, 5.80);
        User u2 = new User("F2022065362", "Maryam Habib", "maryamhabib@gmail.com", "testing", "General Fitness", 19, 55.5, 5.80);
        a1.manageUsers("add", u1);
        a1.manageUsers("add", u2);
        a1.printUserDetails();
        a1.viewAnalytics();

        // Testing Exercise Functionality
        System.out.println("\n--- Testing Exercise Functionality ---");

        // Adding Exercises
        System.out.println("\n--- Adding Exercises ---");
        Exercise running = ExerciseFactory.createExercise("cardio",1, "Running", 8.5, false);
        Exercise cycling = ExerciseFactory.createExercise("cardio",2, "Cycling", 7.0, false);
        Exercise swimming = ExerciseFactory.createExercise("cardio",3, "Swimming", 9.0, true);

        a1.uploadExercises(running);
        a1.uploadExercises(cycling);
        a1.uploadExercises(swimming);
        a1.uploadExercises(running); // Duplicate upload

        // View exercises
        a1.viewExercises();

        // Testing Exercise
        System.out.println("\n--- Testing an Exercise ---");
        Exercise exerciseToTest = a1.findExerciseById(1); // Find exercise by ID


        if (exerciseToTest != null) {
            exerciseToTest.performExercise();
            int duration = 30; // 30 minutes
            double caloriesBurned = exerciseToTest.calculateCaloriesBurned(duration);
            System.out.println("Calories burned in " + duration + " minutes: " + caloriesBurned);

            // Log progress for user
            u1.logProgress(exerciseToTest.getName(), caloriesBurned);
        } else {
            System.out.println("Exercise not found.");
        }

        // View user progress
        System.out.println("\nUser Progress:");
        System.out.println(u1.getProgress());


        Meal ketoMeal = MealFactory.createMeal("keto", 1, "Keto Salad", Arrays.asList("Avocado", "Cheese", "Lettuce"), 400);
        Meal veganMeal = MealFactory.createMeal("vegan", 2, "Vegan Buddha Bowl", Arrays.asList("Quinoa", "Tofu", "Veggies"), 350);
        Meal vegetarianMeal = MealFactory.createMeal("vegetarian", 3, "Vegetarian Pasta", Arrays.asList("Pasta", "Tomatoes", "Basil"), 500);
        Meal balancedMeal = MealFactory.createMeal("balanced", 4, "Balanced Meal", Arrays.asList("Chicken", "Brown Rice", "Broccoli"), 600);

        // Displaying nutritional information
        System.out.println(ketoMeal.getNutritionalInfo());
        System.out.println(veganMeal.getNutritionalInfo());
        System.out.println(vegetarianMeal.getNutritionalInfo());
        System.out.println(balancedMeal.getNutritionalInfo());

        // Preparing the meals
        ketoMeal.prepareMeal();
        veganMeal.prepareMeal();
        vegetarianMeal.prepareMeal();
        balancedMeal.prepareMeal();
    }
}
