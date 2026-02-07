package org.example.endtermproject.patterns;

import org.example.endtermproject.model.Exercise;

public class ExerciseBuilder {
    private int workoutId;
    private String name;
    private int sets = 1; // default
    private int reps = 1; // default

    public ExerciseBuilder workoutId(int workoutId) { this.workoutId = workoutId; return this; }
    public ExerciseBuilder name(String name) { this.name = name; return this; }
    public ExerciseBuilder sets(Integer sets) { if (sets != null) this.sets = sets; return this; }
    public ExerciseBuilder reps(Integer reps) { if (reps != null) this.reps = reps; return this; }

    public Exercise build() {
        return new Exercise(workoutId, name, sets, reps);
    }
}
