// Flexibility Exercise
class FlexibilityExercise extends Exercise {
    public FlexibilityExercise(int id, String name, double caloriesBurnedPerMinute, boolean equipmentRequired) {
        super(id, name, caloriesBurnedPerMinute, equipmentRequired);
    }

    @Override
    public void performExercise() {
        System.out.println("Performing Flexibility Exercise: " + name);
    }
}