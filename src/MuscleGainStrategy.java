import java.util.Arrays;

public class MuscleGainStrategy implements WorkoutPlanStrategy {
    @Override
    public void generatePlan(WorkoutPlan workoutPlan) {
        System.out.println("Generating Muscle Gain Plan...");
        workoutPlan.setExercises(Arrays.asList(
                "Weight Lifting (45 minutes)",
                "Push-Ups (15 minutes)",
                "Plank (5 minutes)"
        ));
    }
}
