import java.util.Arrays;

public class WeightLossStrategy implements WorkoutPlanStrategy {
    @Override
    public void generatePlan(WorkoutPlan workoutPlan) {
        System.out.println("Generating Weight Loss Plan...");
        workoutPlan.setExercises(Arrays.asList(
                "Running (30 minutes)",
                "Cycling (20 minutes)",
                "Swimming (15 minutes)"
        ));
    }
}
