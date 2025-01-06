import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Admin {
    private static Admin instance;
    private String id;
    private String name;
    private String email;

    private List<User> users;
    private List<Exercise> exercises;
    private List<WorkoutPlan> workoutPlans;
    private List<NutritionPlan> nutritionPlans;

    private Admin(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.users = Collections.synchronizedList(new ArrayList<>());
        this.exercises = Collections.synchronizedList(new ArrayList<>());
        this.nutritionPlans = Collections.synchronizedList(new ArrayList<>());
        this.workoutPlans = Collections.synchronizedList(new ArrayList<>());
    }

    public static Admin getInstance(String id, String name, String email) {
        if (instance == null) {
            synchronized (Admin.class) {
                if (instance == null) {
                    instance = new Admin(id, name, email);
                }
            }
        }
        return instance;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exercises = exerciseList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void setInstance(Admin instance) {
        Admin.instance = instance;
    }

    public void setNutritionPlanList(List<NutritionPlan> nutritionPlanList) {
        this.nutritionPlans = nutritionPlanList;
    }

    public void setUserList(List<User> userList) {
        this.users = userList;
    }

    public String getName() {
        return name;
    }

    public List<Exercise> getExerciseList() {
        return exercises;
    }

    public List<NutritionPlan> getNutritionPlanList() {
        return nutritionPlans;
    }

    public List<User> getUserList() {
        return users;
    }

    public List<WorkoutPlan> getWorkoutPlans() {
        return workoutPlans;
    }

    public User getUserById(String id){
        for (User existingUser : users) {
            // Compare IDs in a case-insensitive manner without modifying the original data
            if (existingUser.getId().equalsIgnoreCase(id)) {
                return existingUser;
            }
        }
        return null;
    }

    // User Management
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.getName());
    }

    public void removeUser(String userId) {
        users.removeIf(user -> user.getId().equals(userId));
        System.out.println("User removed with ID: " + userId);
    }

    public void updateUser(String userId, String name, String email, String password, String fitnessGoal, int age, double weight, double height) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                user.updateProfile(userId, name, email, password, fitnessGoal, age, weight, height);
                System.out.println("User updated: " + user.getName());
                return;
            }
        }
        System.out.println("User not found.");
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        for (User existingUser : users) {
            existingUser.printInfo();
        }
    }

    // In Admin class
    public void addExercise(Exercise exercise) {
        // Check if the exercise already exists (based on the exercise name or ID)
        for (Exercise e : exercises) {
            if (e.getId() == exercise.getId()) {
                System.out.println("Duplicate exercise not allowed: " + exercise.getName());
                return;  // Avoid adding duplicate exercise
            }
        }
        exercises.add(exercise);
        System.out.println("Exercise added: " + exercise.getName());
    }


    public void removeExercise(int exerciseId) {
        exercises.removeIf(exercise -> exercise.getId() == exerciseId);
    }

    public void updateExercise(int exerciseId, String name, double caloriesBurnedPerMinute, boolean equipmentRequired) {
        for (Exercise exercise : exercises) {
            if (exercise.getId() == exerciseId) {
                exercise.setName(name);
                exercise.setCaloriesBurnedPerMinute(caloriesBurnedPerMinute);
                exercise.setEquipmentRequired(equipmentRequired);
                break;
            }
        }
    }

    public void listExercises() {
        for (Exercise exercise : exercises) {
            System.out.println(exercise.getExerciseDetails());
        }
    }

    // Workout Plan management
    public void addWorkoutPlan(WorkoutPlan workoutPlan) {
        workoutPlans.add(workoutPlan);
    }

    public void removeWorkoutPlan(int planId) {
        workoutPlans.removeIf(workoutPlan -> workoutPlan.getId() == planId);
    }

    public void updateWorkoutPlan(int planId, List<String> exercises, String goal, int duration) {
        for (WorkoutPlan plan : workoutPlans) {
            if (plan.getId() == planId) {
                plan.setExercises(exercises);
                plan.displayPlan();
                break;
            }
        }
    }

    public void listWorkoutPlans() {
        for (WorkoutPlan plan : workoutPlans) {
            plan.displayPlan();
        }
    }


    public void addNutritionPlan(NutritionPlan nutritionPlan) {
        Meal ketoMeal = MealFactory.createMeal("keto", 1, "Keto Salad", Arrays.asList("Avocado", "Cheese", "Lettuce"), 400);
        Meal veganMeal = MealFactory.createMeal("vegan", 2, "Vegan Buddha Bowl", Arrays.asList("Quinoa", "Tofu", "Veggies"), 350);
        Meal vegetarianMeal = MealFactory.createMeal("vegetarian", 3, "Vegetarian Pasta", Arrays.asList("Pasta", "Tomatoes", "Basil"), 500);
        Meal balancedMeal = MealFactory.createMeal("balanced", 4, "Balanced Meal", Arrays.asList("Chicken", "Brown Rice", "Broccoli"), 600);

        if (nutritionPlan.getDietType().equalsIgnoreCase("keto")) {
            nutritionPlan.addMeals(Arrays.asList(ketoMeal));
        } else if (nutritionPlan.getDietType().equalsIgnoreCase("vegan")) {
            nutritionPlan.addMeals(Arrays.asList(veganMeal));
        } else if (nutritionPlan.getDietType().equalsIgnoreCase("vegetarian")) {
            nutritionPlan.addMeals(Arrays.asList(vegetarianMeal));
        } else if (nutritionPlan.getDietType().equalsIgnoreCase("balanced")) {
            nutritionPlan.addMeals(Arrays.asList(balancedMeal));
        } else {
            System.out.println("No matching diet type found for the Nutrition Plan.");
        }
        nutritionPlans.add(nutritionPlan);
    }

    public void removeNutritionPlan(int id) {
        NutritionPlan planToRemove = null;
        for (NutritionPlan plan : nutritionPlans) {
            if (plan.getId() == id) {
                planToRemove = plan;
                break;
            }
        }
        if (planToRemove != null) {
            nutritionPlans.remove(planToRemove);
            System.out.println("Nutrition plan removed successfully.");
        } else {
            System.out.println("Nutrition plan not found.");
        }
    }

    public void updateNutritionPlan(int id, double newCalories) {
        for (NutritionPlan plan : nutritionPlans) {
            if (plan.getId() == id) {
                plan.updatePlan(newCalories);
                System.out.println("Nutrition plan updated successfully.");
                return;
            }
        }
        System.out.println("Nutrition plan not found.");
    }

    public void listNutritionPlans() {
        if (nutritionPlans.isEmpty()) {
            System.out.println("No nutrition plans available.");
        } else {
            for (NutritionPlan plan : nutritionPlans) {
                plan.displayPlan();
            }
        }
    }

}

