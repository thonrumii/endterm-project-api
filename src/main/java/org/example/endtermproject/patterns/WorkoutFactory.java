package org.example.endtermproject.patterns;

import org.example.endtermproject.model.*;

public class WorkoutFactory {
    private WorkoutFactory() {}

    public static Workout create(String type, String name, int duration) {
        if (type == null) throw new IllegalArgumentException("type is required");
        return switch (type.toUpperCase()) {
            case "CARDIO" -> new CardioWorkout(name, duration);
            case "STRENGTH" -> new StrengthWorkout(name, duration);
            default -> throw new IllegalArgumentException("Unknown workout type: " + type);
        };
    }
}