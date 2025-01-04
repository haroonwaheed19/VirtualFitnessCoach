class ExerciseFactory {
    public static Exercise createExercise(String type, int id, String name, double caloriesBurnedPerMinute, boolean equipmentRequired) {
        // Convert type to lowercase to avoid case-sensitivity issues
        type = type.toLowerCase();

        // Create exercise based on the type
        switch (type) {
            case "cardio":
                    return new CardioExercise(id, name, caloriesBurnedPerMinute, equipmentRequired);
            case "strength":
                return new StrengthExercise(id, name, caloriesBurnedPerMinute, equipmentRequired);
            case "flexibility":
                return new FlexibilityExercise(id, name, caloriesBurnedPerMinute, equipmentRequired);
            default:
                throw new IllegalArgumentException("Unknown exercise type: " + type);
        }
    }
}
