// Abstract class
abstract class Exercise {
    protected int id;
    protected String name;
    protected double caloriesBurnedPerMinute;
    protected boolean equipmentRequired;

    public Exercise(int id, String name, double caloriesBurnedPerMinute, boolean equipmentRequired) {
        this.id = id;
        this.name = name;
        this.caloriesBurnedPerMinute = caloriesBurnedPerMinute;
        this.equipmentRequired = equipmentRequired;
    }

    public abstract void performExercise();

    public double calculateCaloriesBurned(int durationInMinutes) {
        return caloriesBurnedPerMinute * durationInMinutes;
    }

    public String getExerciseDetails() {
        return "Exercise: " + name + ", Calories Burned/Minute: " + caloriesBurnedPerMinute
                + ", Equipment Required: " + equipmentRequired;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCaloriesBurnedPerMinute(double caloriesBurnedPerMinute) {
        this.caloriesBurnedPerMinute = caloriesBurnedPerMinute;
    }

    public void setEquipmentRequired(boolean equipmentRequired) {
        this.equipmentRequired = equipmentRequired;
    }

    public boolean isEquipmentRequired() {
        return equipmentRequired;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getCaloriesBurnedPerMinute() {
        return caloriesBurnedPerMinute;
    }

}

