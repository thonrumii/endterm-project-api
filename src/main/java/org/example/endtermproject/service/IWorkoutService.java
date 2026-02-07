package org.example.endtermproject.service;


import org.example.endtermproject.exception.DatabaseOperationException;
import org.example.endtermproject.exception.InvalidInputException;
import org.example.endtermproject.exception.ResourceNotFoundException;
import org.example.endtermproject.model.Workout;

import java.util.List;

public interface IWorkoutService {

    void createWorkout(Workout workout)
            throws InvalidInputException, DatabaseOperationException;

    List<Workout> getAllWorkouts() throws DatabaseOperationException;

    List<Workout> getAllWorkoutsSortedByDuration()
            throws DatabaseOperationException;

    Workout getWorkoutById(int id)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException;

    Workout getShortestC() throws DatabaseOperationException;

    Workout getShortestS() throws DatabaseOperationException;

    void updateWorkout(int id, Workout workout)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException;

    void deleteWorkout(int id)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException;
}