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

        // Creating a CardioExercise
        Exercise cardio = new CardioExercise(1, "Running", 8.5, false); // 8.5 calories burned per minute, no equipment

        // Print exercise details
        System.out.println(cardio.getExerciseDetails());

        // Perform the exercise
        cardio.performExercise();

        // Calculate calories burned
        int duration = 30; // 30 minutes of exercise
        double caloriesBurned = cardio.calculateCaloriesBurned(duration);
        System.out.println("Calories burned in " + duration + " minutes: " + caloriesBurned);

        // Log progress for user u1
        u1.logProgress("Running", caloriesBurned);

        // View progress
        System.out.println("\nUser Progress:");
        System.out.println(u1.getProgress());
    }
}
