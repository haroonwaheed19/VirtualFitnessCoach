import java.util.Arrays;

public class GeneralFitnessStrategy implements WorkoutPlanStrategy {
    @Override
    public void generatePlan(WorkoutPlan workoutPlan) {
        System.out.println("Generating General Fitness Plan...");
        workoutPlan.setExercises(Arrays.asList(
                "Walking (30 minutes)",
                "Yoga (20 minutes)",
                "Bodyweight Exercises (15 minutes)",
                "Stretching (10 minutes)"
        ));
    }
}
