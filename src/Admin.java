import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Admin {
    private static Admin instance;
    private String id;
    private String name;
    private String email;

    private List<User> userList;
    private List<Exercise> exerciseList;
    private List<NutritionPlan> nutritionPlanList;

    private Admin(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userList = Collections.synchronizedList(new ArrayList<>());
        this.exerciseList = Collections.synchronizedList(new ArrayList<>());
        this.nutritionPlanList = Collections.synchronizedList(new ArrayList<>());
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

    public void removeExercise(int exerciseId) {
        synchronized (exerciseList) {
            exerciseList.removeIf(exercise -> exercise.getId() == exerciseId);
            System.out.println("Exercise removed with ID: " + exerciseId);
        }
    }

    public void removeNutritionPlan(String dietType) {
        synchronized (nutritionPlanList) {
            nutritionPlanList.removeIf(plan -> plan.getDietType().equalsIgnoreCase(dietType));
            System.out.println("Nutrition plan removed: " + dietType);
        }
    }

    public void viewAnalytics() {
        synchronized (userList) {
            System.out.println("Viewing analytics...");
            for (User user : userList) {
                System.out.println("User: " + user.getName() + ", Progress: " + user.getProgress());
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void setInstance(Admin instance) {
        Admin.instance = instance;
    }

    public void setNutritionPlanList(List<NutritionPlan> nutritionPlanList) {
        this.nutritionPlanList = nutritionPlanList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public List<NutritionPlan> getNutritionPlanList() {
        return nutritionPlanList;
    }

    public List<User> getUserList() {
        return userList;
    }

    // Method to manage users (add, remove, update)
    public void manageUsers(String action, User user) {
        switch (action.toLowerCase()) {
            case "add":
                userList.add(user);
                System.out.println("User added: " + user.getName());
                break;

            case "remove":
                userList.removeIf(existingUser -> existingUser.getId().toLowerCase().equals(user.getId()));
                System.out.println("User removed: " + user.getName());
                break;

            case "update":
                for (User existingUser : userList) {
                    if (existingUser.getId().toLowerCase().equals(user.getId())) {
                        // Use updateProfile to update the user details
                        existingUser.updateProfile(
                                user.getId(),
                                user.getName(),
                                user.getEmail(),
                                user.getPassword(),
                                user.getFitnessGoal(),
                                user.getAge(),
                                user.getWeight(),
                                user.getHeight()
                        );
                        System.out.println("User updated: " + user.getName());
                        break;
                    }
                }
                break;

            default:
                System.out.println("Invalid action for managing users.");
        }
    }

    public void printUserDetails()
    {
        for (User existingUser : userList) {
            existingUser.printInfo();
        }
    }

    // Method to upload exercises
    public void uploadExercises(Exercise exercise) {
        synchronized (exerciseList) {
            boolean exists = exerciseList.stream().anyMatch(e -> e.getId() == exercise.getId());
            if (!exists) {
                exerciseList.add(exercise);
                System.out.println("Exercise uploaded: " + exercise.getName());
            } else {
                System.out.println("Exercise with ID " + exercise.getId() + " already exists.");
            }
        }
    }

    // View exercises
    public void viewExercises() {
        synchronized (exerciseList) {
            System.out.println("\n--- Exercise List ---");
            if (exerciseList.isEmpty()) {
                System.out.println("No exercises available.");
            } else {
                for (Exercise exercise : exerciseList) {
                    System.out.println(exercise.getExerciseDetails());
                }
            }
        }
    }

    // Find exercise by ID
    public Exercise findExerciseById(int id) {
        synchronized (exerciseList) {
            for (Exercise exercise : exerciseList) {
                if (exercise.getId() == id) {
                    return exercise;
                }
            }
            return null; // Return null if not found
        }
    }

    // Method to edit or create new nutrition plans
    public void editNutritionPlans(NutritionPlan plan, String action) {
        synchronized (nutritionPlanList) {
            switch (action.toLowerCase()) {
                case "add":
                    nutritionPlanList.add(plan);
                    System.out.println("Nutrition plan added: " + plan.getDietType());
                    break;
                case "update":
                    // Logic to update an existing plan
                    System.out.println("Nutrition plan updated: " + plan.getDietType());
                    break;
                default:
                    System.out.println("Invalid action for nutrition plans.");
            }
        }
    }
}

class NutritionPlan {
    private String dietType;

    public NutritionPlan(String dietType) {
        this.dietType = dietType;
    }

    public String getDietType() {
        return dietType;
    }
}
