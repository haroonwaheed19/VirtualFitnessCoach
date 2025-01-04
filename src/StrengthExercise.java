// Strength Exercise
class StrengthExercise extends Exercise {
    public StrengthExercise(int id, String name, double caloriesBurnedPerMinute, boolean equipmentRequired) {
        super(id, name, caloriesBurnedPerMinute, equipmentRequired);
    }

    @Override
    public void performExercise() {
        System.out.println("Performing Strength Exercise: " + name);
    }
}