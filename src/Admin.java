import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Admin implements Subject{
    private static Admin instance;
    private String id;
    private String name;
    private String email;
    private String planName;
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

    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // User Management
    public void addUser(User user) {
        if (!users.contains(user)) {  // Prevent duplicate users
            users.add(user);
            addObserver(user);
            System.out.println("User added: " + user.getName());
        } else {
            System.out.println("User with this ID already exists.");
        }
    }

    public void removeUser(User user) {
        users.remove(user);
        removeObserver(user);
        System.out.println("User removed with ID: " + user.getId());
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


    // Exercise Management
    public void addExercise(Exercise exercise) {
        if (exercises.stream().noneMatch(e -> e.getId() == exercise.getId())) {  // Prevent duplicate exercises
            exercises.add(exercise);
            notifyObservers("Exercise added: " + exercise.getName());
        } else {
            System.out.println("Duplicate exercise not allowed: " + exercise.getName());
        }
    }


    public void removeExercise(int exerciseId) {
        notifyObservers("Exercise removed: ID " + exerciseId);
        exercises.removeIf(exercise -> exercise.getId() == exerciseId);
    }

    public void updateExercise(int exerciseId, String name, double caloriesBurnedPerMinute, boolean equipmentRequired) {
        for (Exercise exercise : exercises) {
            if (exercise.getId() == exerciseId) {
                exercise.setName(name);
                exercise.setCaloriesBurnedPerMinute(caloriesBurnedPerMinute);
                exercise.setEquipmentRequired(equipmentRequired);
                notifyObservers("Exercise updated: ID " + exerciseId);
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
        notifyObservers("WorkoutPlan Added :id  " + workoutPlan.getId());
    }

    public void removeWorkoutPlan(int planId) {
        workoutPlans.removeIf(workoutPlan -> workoutPlan.getId() == planId);
        notifyObservers("WorkoutPlan removed :id  " + planId);

    }

    public void updateWorkoutPlan(int planId, List<String> exercises, String goal, int duration) {
        for (WorkoutPlan plan : workoutPlans) {
            if (plan.getId() == planId) {
                plan.setExercises(exercises);
                notifyObservers("WorkoutPlan updated :id  " + planId);
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


    private Meal createMeal(String type, int id, String name, List<String> ingredients, int calories) {
        return MealFactory.createMeal(type, id, name, ingredients, calories);
    }

    public void addNutritionPlan(NutritionPlan nutritionPlan) {
        List<Meal> meals = new ArrayList<>();
        switch (nutritionPlan.getDietType().toLowerCase()) {
            case "keto":
                planName = "keto";
                meals.add(createMeal("keto", 1, "Keto Salad", Arrays.asList("Avocado", "Cheese", "Lettuce"), 400));
                break;
            case "vegan":
                planName = "vegan";
                meals.add(createMeal("vegan", 2, "Vegan Buddha Bowl", Arrays.asList("Quinoa", "Tofu", "Veggies"), 350));
                break;
            case "vegetarian":
                planName = "vegetarian";
                meals.add(createMeal("vegetarian", 3, "Vegetarian Pasta", Arrays.asList("Pasta", "Tomatoes", "Basil"), 500));
                break;
            case "balanced":
                planName = "balanced";
                meals.add(createMeal("balanced", 4, "Balanced Meal", Arrays.asList("Chicken", "Brown Rice", "Broccoli"), 600));
                break;
            default:
                System.out.println("No matching diet type found for the Nutrition Plan.");
                return;
        }
        nutritionPlan.addMeals(meals);
        nutritionPlans.add(nutritionPlan);
        notifyObservers("Nutrition Plan : " + planName + " Added");
    }



    public void updateNutritionPlan(int id, double newCalories) {
        for (NutritionPlan plan : nutritionPlans) {
            if (plan.getId() == id) {
                plan.updatePlan(newCalories);
                notifyObservers("Nutrition plan id: " + id  + "  updated successfully.");
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
            notifyObservers("Nutrition plan id : " + id +" removed successfully.");
        } else {
            System.out.println("Nutrition plan not found.");
        }
    }
}

