import java.util.List;

public class WorkoutPlan {
    private int id;
    private List<String> exercises;
    private String goal;
    private int duration; // Duration in weeks
    private WorkoutPlanStrategy strategy;

    public WorkoutPlan(int id, List<String> exercises, String goal, int duration) {
        this.id = id;
        this.exercises = exercises;
        this.goal = goal;
        this.duration = duration;
    }

    public void setStrategy(WorkoutPlanStrategy strategy) {
        this.strategy = strategy;
    }

    public void generatePlan() {
        if (strategy != null) {
            strategy.generatePlan(this);
        } else {
            System.out.println("No strategy set. Please set a strategy first.");
        }
    }

    public void adjustPlan(String adjustment) {
        System.out.println("Adjusting plan: " + adjustment);
        if (adjustment.contains("Increase duration")) {
            duration += 2;
            System.out.println("Duration increased to " + duration + " weeks.");
        }
    }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }

    public void displayPlan() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Workout Plan ID: " + id);
        System.out.println("Goal: " + goal);
        System.out.println("Duration: " + duration + " weeks");
        System.out.println("Exercises:");
        for (String exercise : exercises) {
            System.out.println(" - " + exercise);
        }
        System.out.println("---------------------------------------------------------------------------");
    }


    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getExercises() {
        return exercises;
    }

    public WorkoutPlanStrategy getStrategy() {
        return strategy;
    }

    public String getGoal() {
        return goal;
    }
}
