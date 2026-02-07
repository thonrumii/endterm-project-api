package org.example.endtermproject.model;
public class StrengthWorkout extends Workout {

    public StrengthWorkout( String name, int duration) {
        super(name, duration);
    }
    public StrengthWorkout(int id, String name, int duration) {
        super(name, duration);
        setId(id);
    }

    @Override
    public double calculateCalories(){
        return getDuration()*3.5;
    }

    @Override
    public String getWorkoutType(){
        return "STRENGTH";
    }


}
