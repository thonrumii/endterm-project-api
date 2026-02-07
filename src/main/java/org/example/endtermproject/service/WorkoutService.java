package org.example.endtermproject.service;


import org.example.endtermproject.exception.*;
import org.example.endtermproject.model.Workout;
import org.example.endtermproject.repository.CrudRepository;
import org.example.endtermproject.repository.WorkoutLookup;
import org.example.endtermproject.repository.WorkoutQueries;
import org.example.endtermproject.repository.WorkoutRepository;
import org.example.endtermproject.utils.SortUtils;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkoutService implements IWorkoutService {

    private final CrudRepository<Workout> workoutRepository;
    private final WorkoutQueries workoutQueries;
    private final WorkoutLookup workoutLookup;


    public WorkoutService(WorkoutRepository repo) {
        this.workoutRepository = repo;
        this.workoutQueries = repo;
        this.workoutLookup = repo;
    }

    // CREATE
    public void createWorkout(Workout workout)
            throws InvalidInputException, DatabaseOperationException {

        validateWorkout(workout);
        if (workoutLookup.existsByName(workout.getName())) {
            throw new DuplicateResourceException("Workout with name '" + workout.getName() + "' already exists");
        }
        workoutRepository.create(workout);
    }

    // GET ALL
    public List<Workout> getAllWorkouts() throws DatabaseOperationException {
        return workoutRepository.getAll();
    }
    //GET ALL SORTED BY DURATION
    public List<Workout> getAllWorkoutsSortedByDuration() throws DatabaseOperationException {
        List<Workout> workouts = workoutRepository.getAll();
        SortUtils.sortWorkoutsByDuration(workouts);
        return workouts;
    }

    public Workout getShortestC() throws DatabaseOperationException {
        if (workoutQueries == null) {
            throw new DatabaseOperationException("Shortest cardio not supported by this repository", null);
        }
        return workoutQueries.getShortestC();
    }

    public Workout getShortestS() throws DatabaseOperationException {
        if (workoutQueries == null) {
            throw new DatabaseOperationException("Shortest strength not supported by this repository", null);
        }
        return workoutQueries.getShortestS();
    }

    // GET BY ID
    public Workout getWorkoutById(int id)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {

        if (id <= 0) {
            throw new InvalidInputException("Invalid workout id");
        }

        Workout workout = workoutRepository.getById(id);

        if (workout == null) {
            throw new ResourceNotFoundException("Workout with id " + id + " not found");
        }

        return workout;
    }

    // UPDATE
    public void updateWorkout(int id, Workout workout)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {

        if (id <= 0) {
            throw new InvalidInputException("Invalid workout id");
        }

        validateWorkout(workout);

        if (workoutRepository.getById(id) == null) {
            throw new ResourceNotFoundException("Workout not found");
        }

        if (workoutLookup.existsByNameExceptId(workout.getName(), id)) {
            throw new DuplicateResourceException("Workout with name '" + workout.getName() + "' already exists");
        }

        workoutRepository.update(id, workout);
    }


    // DELETE
    public void deleteWorkout(int id)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {

        if (id <= 0) {
            throw new InvalidInputException("Invalid workout id");
        }

        if (workoutRepository.getById(id) == null) {
            throw new ResourceNotFoundException("Workout not found");
        }

        workoutRepository.delete(id);
    }

    private void validateWorkout(Workout workout) throws InvalidInputException {
        if (workout == null) throw new InvalidInputException("Workout cannot be null");
        if (workout.getName() == null || workout.getName().isBlank())
            throw new InvalidInputException("Workout name cannot be empty");
        if (workout.getDuration() <= 0)
            throw new InvalidInputException("Duration must be greater than 0");
    }
}