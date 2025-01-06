import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = Admin.getInstance("F2022065033", "Haroon Waheed", "haroonwaheed002@gmail.com");
        User u1 = new User("F2022065033", "Haroon Waheed", "haroonwaheed002@gmail.com", "testing", "WeightLoss", 20, 72.80, 5.80);
        User u2 = new User("F2022065362", "Maryam Habib", "maryamhabib@gmail.com", "testing", "General Fitness", 19, 55.5, 5.80);
        User u3 = new User("F2022065259", "Malaika", "malaika@gmail.com", "testing", "General Fitness", 19, 55.5, 5.50);
        admin.addUser(u1);
        admin.addUser(u2);
        admin.addUser(u3);

        // Adding Exercises
        Exercise running = ExerciseFactory.createExercise("cardio", 1, "Running", 8.5, false);
        Exercise cycling = ExerciseFactory.createExercise("cardio", 2, "Cycling", 7.0, false);
        Exercise swimming = ExerciseFactory.createExercise("cardio", 3, "Swimming", 9.0, true);

        admin.addExercise(running);
        admin.addExercise(cycling);
        admin.addExercise(swimming);
        admin.addExercise(running); // Duplicate upload

        WorkoutPlan weightLossPlan = new WorkoutPlan(1, Arrays.asList("Running", "Cycling", "Swimming"), "Weight Loss", 8);
        weightLossPlan.setStrategy(new WeightLossStrategy());

        WorkoutPlan muscleGainPlan = new WorkoutPlan(2, Arrays.asList("Weight Lifting", "Push-Ups", "Plank"), "Muscle Gain", 12);
        muscleGainPlan.setStrategy(new MuscleGainStrategy());

        WorkoutPlan generalFitnessPlan = new WorkoutPlan(3, Arrays.asList("Walking", "Yoga", "Stretching"), "General Fitness", 10);
        generalFitnessPlan.setStrategy(new GeneralFitnessStrategy());

        WorkoutPlan advancedFitnessPlan = new WorkoutPlan(4, Arrays.asList("HIIT", "Deadlift", "Pull-Ups"), "Advanced Fitness", 14);
        advancedFitnessPlan.setStrategy(new GeneralFitnessStrategy());

        admin.addWorkoutPlan(weightLossPlan);
        admin.addWorkoutPlan(muscleGainPlan);
        admin.addWorkoutPlan(generalFitnessPlan);
        admin.addWorkoutPlan(advancedFitnessPlan);

        // Creating meals
        Meal ketoMeal = MealFactory.createMeal("keto", 1, "Keto Salad", Arrays.asList("Avocado", "Cheese", "Lettuce"), 400);
        Meal veganMeal = MealFactory.createMeal("vegan", 2, "Vegan Buddha Bowl", Arrays.asList("Quinoa", "Tofu", "Veggies"), 350);
        Meal vegetarianMeal = MealFactory.createMeal("vegetarian", 3, "Vegetarian Pasta", Arrays.asList("Pasta", "Tomatoes", "Basil"), 500);
        Meal balancedMeal = MealFactory.createMeal("balanced", 4, "Balanced Meal", Arrays.asList("Chicken", "Brown Rice", "Broccoli"), 600);

        // Adding meals to the nutrition plans
        NutritionPlan ketoPlan = new NutritionPlan(1, "Keto", 1500);
        ketoPlan.setStrategy(new KetoNutritionStrategy());
        ketoPlan.addMeals(Arrays.asList(ketoMeal)); // Add meals to the nutrition plan

        NutritionPlan veganPlan = new NutritionPlan(2, "Vegan", 1600);
        veganPlan.setStrategy(new VeganNutritionStrategy());
        veganPlan.addMeals(Arrays.asList(veganMeal));

        NutritionPlan balancedPlan = new NutritionPlan(3, "Balanced", 1800);
        balancedPlan.setStrategy(new BalancedNutritionStrategy());
        balancedPlan.addMeals(Arrays.asList(balancedMeal));

        NutritionPlan vegetarianPlan = new NutritionPlan(4, "Vegetarian", 1700);
        vegetarianPlan.setStrategy(new VegetarianNutritionStrategy());
        vegetarianPlan.addMeals(Arrays.asList(vegetarianMeal));

        // Adding to the admin
        admin.addNutritionPlan(ketoPlan);
        admin.addNutritionPlan(veganPlan);
        admin.addNutritionPlan(balancedPlan);
        admin.addNutritionPlan(vegetarianPlan);

        while (true) {
            System.out.println("\n----- Main Menu -----");
            System.out.println("1. Admin Menu");
            System.out.println("2. User Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (mainChoice) {
                case 1: // Admin Menu
                    adminMenu(scanner, admin);
                    break;
                case 2: // User Menu
                    userMenu(scanner, admin);
                    break;
                case 3: // Exit
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static User login(Scanner scanner, Admin admin) {
        System.out.println("Enter your ID: ");
        String id = scanner.nextLine().toLowerCase(); // Convert entered ID to lowercase
        System.out.println("Enter your password: ");
        String password = scanner.nextLine().toLowerCase(); // Convert entered password to lowercase

        // Get the user by ID
        User user = admin.getUserById(id);

        // Check if the user exists and the password matches
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Invalid ID or password. Registering user...");
            return addNewUser(scanner, admin); // If login fails, register as a new user
        }
    }



    public static User addNewUser(Scanner scanner, Admin admin) {
        System.out.println("Enter User ID:");
        String userId = scanner.nextLine();
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        System.out.println("Enter Fitness Goal:");
        String fitnessGoal = scanner.nextLine();
        System.out.println("Enter Age:");
        int age = scanner.nextInt();
        System.out.println("Enter Weight (kg):");
        double weight = scanner.nextDouble();
        System.out.println("Enter Height (m):");
        double height = scanner.nextDouble();
        User user = new User(userId, name, email, password, fitnessGoal, age, weight, height);
        admin.addUser(user);
        return user;
    }

    public static void userMenu(Scanner scanner, Admin admin) {
        User user = login(scanner, admin); // User login
        int choice;
        do {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. View Workout Plan");
            System.out.println("4. Log Workout Progress");
            System.out.println("5. View Nutritional Plan");
            System.out.println("6. Log Nutritional Progress");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewProfile(user);
                case 2 -> updateProfile(scanner, user);
                case 3 -> admin.listWorkoutPlans();
                case 4 -> logWorkoutProgress(scanner, user,admin);
                case 5 -> admin.listNutritionPlans();
                case 6 -> logNutritionalProgress(scanner, user,admin);
                case 7 -> System.out.println("Exiting User Menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    public static void logNutritionalProgress(Scanner scanner, User user, Admin admin) {

        System.out.println("\nAvailable Nutritional Plans:");
        admin.listNutritionPlans();

        // Log the nutritional progress
        System.out.println("\nLog Nutritional Progress:");
        System.out.print("Enter the Nutrition Plan ID you followed: ");
        scanner.nextLine();
        String nutritionPlanId = scanner.nextLine();
        System.out.print("Enter the name of the food item consumed: ");
        String foodItem = scanner.nextLine();
        System.out.print("Enter the calories consumed: ");
        double caloriesConsumed = scanner.nextDouble();

        // Log the progress for the selected nutritional plan
        user.logProgress("Nutrition Plan ID: " + nutritionPlanId + ", Food Item: " + foodItem, caloriesConsumed);
        System.out.println("Nutritional progress logged successfully.");
    }




    public static void viewProfile(User user) {
        System.out.println("\nYour Profile:");
        user.printInfo();
    }

    public static void updateProfile(Scanner scanner, User user) {
        System.out.println("\nUpdate Profile:");
        System.out.print("Enter new name: ");
        String name = scanner.next();
        System.out.print("Enter new email: ");
        String email = scanner.next();
        System.out.print("Enter new fitness goal: ");
        String fitnessGoal = scanner.next();
        System.out.print("Enter new age: ");
        int age = scanner.nextInt();
        System.out.print("Enter new weight: ");
        double weight = scanner.nextDouble();
        System.out.print("Enter new height: ");
        double height = scanner.nextDouble();

        user.updateProfile(user.getId(), name, email, user.getPassword(), fitnessGoal, age, weight, height);
        System.out.println("Profile updated successfully.");
    }

    public static void logWorkoutProgress(Scanner scanner, User user, Admin admin) {
        // Display all workout plans from Admin
        System.out.println("\nAvailable Workout Plans:");
        if (admin.getWorkoutPlans().isEmpty()) {
            System.out.println("No workout plans available.");
            return;
        }
        for (WorkoutPlan workoutPlan : admin.getWorkoutPlans()) {
            workoutPlan.displayPlan();
        }

        // Log the workout progress
        System.out.println("\nLog Workout Progress:");
        System.out.print("Enter the Workout Plan ID you followed: ");
        scanner.nextLine();
        String workoutPlanId = scanner.nextLine();
        System.out.print("Enter the name of the specific exercise performed: ");
        String exercise = scanner.nextLine();
        System.out.print("Enter the calories burned: ");
        double caloriesBurned = scanner.nextDouble();

        // Log the progress for the selected workout plan
        user.logProgress("Workout Plan ID: " + workoutPlanId + ", Exercise: " + exercise, caloriesBurned);
        System.out.println("Workout progress logged successfully.");
    }


    public static void viewProgress(Scanner sc, User user) {
        System.out.println("\nYour Progress:");
        for (String progress : user.getProgress()) {
            System.out.println(progress);
        }
    }

    public static void adminMenu(Scanner scanner, Admin admin)
    {
        while (true) {
            System.out.println("\n----- Admin Menu -----");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Exercises");
            System.out.println("3. Manage Workout Plans");
            System.out.println("4. Manage Nutrition Plans");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Manage Users
                    manageUsers(scanner, admin);
                    break;
                case 2: // Manage Exercises
                    manageExercises(scanner, admin);
                    break;
                case 3: // Manage Workout Plans
                    manageWorkoutPlans(scanner, admin);
                    break;
                case 4: // Manage Nutrition Plans
                    manageNutritionPlans(scanner, admin);
                    break;
                case 5: // Exit
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageUsers(Scanner scanner, Admin admin) {
        System.out.println("\n1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. Update User");
        System.out.println("4. List Users");
        System.out.println("5. Go Back");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1: // Add User
                System.out.println("Enter User ID:");
                String userId = scanner.nextLine();
                System.out.println("Enter Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Email:");
                String email = scanner.nextLine();
                System.out.println("Enter Password:");
                String password = scanner.nextLine();
                System.out.println("Enter Fitness Goal:");
                String fitnessGoal = scanner.nextLine();
                System.out.println("Enter Age:");
                int age = scanner.nextInt();
                System.out.println("Enter Weight (kg):");
                double weight = scanner.nextDouble();
                System.out.println("Enter Height (m):");
                double height = scanner.nextDouble();
                User user = new User(userId, name, email, password, fitnessGoal, age, weight, height);
                admin.addUser(user);
                break;
            case 2: // Remove User
                System.out.println("Enter User ID to Remove:");
                String removeUserId = scanner.nextLine();
                admin.removeUser(removeUserId);
                break;
            case 3: // Update User
                System.out.println("Enter User ID to Update:");
                String updateUserId = scanner.nextLine();
                System.out.println("Enter new Name:");
                String newName = scanner.nextLine();
                System.out.println("Enter new Email:");
                String newEmail = scanner.nextLine();
                System.out.println("Enter new Password:");
                String newPassword = scanner.nextLine();
                System.out.println("Enter new Fitness Goal:");
                String newFitnessGoal = scanner.nextLine();
                System.out.println("Enter new Age:");
                int newAge = scanner.nextInt();
                System.out.println("Enter new Weight (kg):");
                double newWeight = scanner.nextDouble();
                System.out.println("Enter new Height (m):");
                double newHeight = scanner.nextDouble();
                admin.updateUser(updateUserId, newName, newEmail, newPassword, newFitnessGoal, newAge, newWeight, newHeight);
                break;
            case 4: // List Users
                admin.listUsers();
                break;
            case 5: // Go Back
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }


    public static void manageExercises(Scanner scanner, Admin admin) {
        System.out.println("\n----- Exercise Management -----");
        System.out.println("1. Add Exercise");
        System.out.println("2. Remove Exercise");
        System.out.println("3. Update Exercise");
        System.out.println("4. List Exercises");
        System.out.println("5. Go Back");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: // Add Exercise
                System.out.print("Enter exercise ID: ");
                int exerciseId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter exercise name: ");
                String exerciseName = scanner.nextLine();
                System.out.print("Enter calories burned per minute: ");
                double caloriesBurned = scanner.nextDouble();
                System.out.print("Does it require equipment? (true/false): ");
                boolean equipmentRequired = scanner.nextBoolean();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter exercise type (cardio, strength, flexibility): ");
                String exerciseType = scanner.nextLine();

                // Create exercise using the ExerciseFactory based on the type
                Exercise exercise = ExerciseFactory.createExercise(exerciseType, exerciseId, exerciseName, caloriesBurned, equipmentRequired);
                admin.addExercise(exercise);
                break;
            case 2: // Remove Exercise
                System.out.print("Enter exercise ID to remove: ");
                int removeExerciseId = scanner.nextInt();
                admin.removeExercise(removeExerciseId);
                break;
            case 3: // Update Exercise
                System.out.print("Enter exercise ID to update: ");
                int updateExerciseId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new exercise name: ");
                String updateName = scanner.nextLine();
                System.out.print("Enter new calories burned per minute: ");
                double updateCalories = scanner.nextDouble();
                System.out.print("Does it require equipment? (true/false): ");
                boolean updateEquipment = scanner.nextBoolean();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new exercise type (cardio, strength, flexibility): ");
                String updateType = scanner.nextLine();

                // Create exercise using the ExerciseFactory based on the type
                Exercise updatedExercise = ExerciseFactory.createExercise(updateType, updateExerciseId, updateName, updateCalories, updateEquipment);
                admin.updateExercise(updateExerciseId,updateName,updateCalories,updateEquipment);
                break;
            case 4: // List Exercises
                admin.listExercises();
                break;
            case 5: // Go Back
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }


    // Workout Plan management
    public static void manageWorkoutPlans(Scanner scanner, Admin admin) {
        System.out.println("\n----- Workout Plan Management -----");
        System.out.println("1. Add Workout Plan");
        System.out.println("2. Remove Workout Plan");
        System.out.println("3. Update Workout Plan");
        System.out.println("4. List Workout Plans");
        System.out.println("5. Go Back");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: // Add Workout Plan
                System.out.print("Enter workout plan ID: ");
                int planId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter goal for the workout plan: ");
                String goal = scanner.nextLine();
                System.out.print("Enter the duration: ");
                int duration = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                List<String> exercisesList = new ArrayList<>();
                System.out.print("Enter the exercises (comma-separated): ");
                String exercisesInput = scanner.nextLine();
                String[] exercisesArray = exercisesInput.split(",");
                Collections.addAll(exercisesList, exercisesArray);
                WorkoutPlan workoutPlan = new WorkoutPlan(planId, exercisesList, goal, duration);
                admin.addWorkoutPlan(workoutPlan);
                break;
            case 2: // Remove Workout Plan
                System.out.print("Enter workout plan ID to remove: ");
                int removePlanId = scanner.nextInt();
                admin.removeWorkoutPlan(removePlanId);
                break;
            case 3: // Update Workout Plan
                System.out.print("Enter workout plan ID to update: ");
                int updatePlanId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new exercises (comma-separated): ");
                String updateExercises = scanner.nextLine();
                List<String> updatedExercises = List.of(updateExercises.split(","));
                System.out.print("Enter new goal: ");
                String updateGoal = scanner.nextLine();
                System.out.print("Enter new duration: ");
                int updateDuration = scanner.nextInt();
                admin.updateWorkoutPlan(updatePlanId, updatedExercises, updateGoal, updateDuration);
                break;
            case 4: // List Workout Plans
                admin.listWorkoutPlans();
                break;
            case 5: // Go Back
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    //Nutrition Plan Management
    public static void manageNutritionPlans(Scanner scanner, Admin admin) {
        while (true) {
            System.out.println("\n----- Nutrition Plan Management -----");
            System.out.println("1. Add Nutrition Plan");
            System.out.println("2. Remove Nutrition Plan");
            System.out.println("3. Update Nutrition Plan");
            System.out.println("4. List Nutrition Plans");
            System.out.println("5. Go Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter nutrition plan ID: ");
                    int nutritionPlanId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter diet type: ");
                    String dietType = scanner.nextLine();
                    System.out.print("Enter calories: ");
                    double calories = scanner.nextDouble();
                    NutritionPlan plan = new NutritionPlan(nutritionPlanId, dietType, calories);
                    admin.addNutritionPlan(plan);
                    break;
                case 2:
                    System.out.print("Enter nutrition plan ID to remove: ");
                    int removeId = scanner.nextInt();
                    admin.removeNutritionPlan(removeId);
                    break;
                case 3:
                    System.out.print("Enter nutrition plan ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new calories: ");
                    double updateCalories = scanner.nextDouble();
                    admin.updateNutritionPlan(updateId, updateCalories);
                    break;
                case 4:
                    admin.listNutritionPlans();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

}
