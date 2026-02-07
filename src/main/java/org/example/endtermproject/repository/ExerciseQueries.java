package org.example.endtermproject.repository;

import java.util.List;
import org.example.endtermproject.model.Exercise;
import org.example.endtermproject.exception.DatabaseOperationException;
public interface ExerciseQueries {
    List<Exercise> getByWorkoutId(int workoutId) throws DatabaseOperationException;
}