package org.example.endtermproject.repository;

import org.example.endtermproject.exception.DatabaseOperationException;
import org.example.endtermproject.model.Workout;

public interface WorkoutQueries {
    Workout getShortestC() throws DatabaseOperationException;
    Workout getShortestS() throws DatabaseOperationException;
}