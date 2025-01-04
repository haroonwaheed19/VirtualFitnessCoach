class CardioExercise extends Exercise {
    public CardioExercise(int id, String name, double caloriesBurnedPerMinute, boolean equipmentRequired) {
        super(id, name, caloriesBurnedPerMinute, equipmentRequired);
    }

    @Override
    public void performExercise() {
        System.out.println("Performing Cardio Exercise: " + name);
    }
}