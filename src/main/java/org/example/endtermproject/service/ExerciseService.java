package org.example.endtermproject.service;

import org.example.endtermproject.exception.DatabaseOperationException;
import org.example.endtermproject.exception.InvalidInputException;
import org.example.endtermproject.exception.ResourceNotFoundException;
import org.example.endtermproject.model.Exercise;
import org.example.endtermproject.model.Workout;
import org.example.endtermproject.repository.ExerciseQueries;
import org.example.endtermproject.repository.ExerciseRepository;
import org.example.endtermproject.repository.CrudRepository;
import org.example.endtermproject.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExerciseService implements IExerciseService {

    private final CrudRepository<Exercise> exerciseRepository;
    private final ExerciseQueries exerciseQueries;
    private final CrudRepository<Workout> workoutRepository;

    public ExerciseService(ExerciseRepository repo, WorkoutRepository wrepo) {
        this.exerciseRepository = repo;
        this.workoutRepository = wrepo;
        this.exerciseQueries = repo;
    }

    // CREATE
    public void addExercise(int workoutId, Exercise exercise)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {

        if (workoutId <= 0) {
            throw new InvalidInputException("Invalid workout id");
        }

        if (exercise == null) {
            throw new InvalidInputException("Exercise cannot be null");
        }

        if (exercise.getName() == null || exercise.getName().isBlank()) {
            throw new InvalidInputException("Exercise name cannot be empty");
        }

        if (exercise.getSets() <= 0 || exercise.getReps() <= 0) {
            throw new InvalidInputException("Sets and reps must be greater than 0");
        }

        if (workoutRepository.getById(workoutId) == null) {
            throw new ResourceNotFoundException("Workout not found");
        }

        exercise.setWorkoutId(workoutId);
        exerciseRepository.create(exercise);

    }

    // UPDATE
    public void updateExercise(int id, Exercise exercise)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {

        if (id <= 0) {
            throw new InvalidInputException("Invalid exercise id");
        }

        if (exercise == null) {
            throw new InvalidInputException("Exercise cannot be null");
        }

        if (exercise.getName() == null || exercise.getName().isBlank()) {
            throw new InvalidInputException("Exercise name cannot be empty");
        }

        if (exercise.getSets() <= 0 || exercise.getReps() <= 0) {
            throw new InvalidInputException("Sets and reps must be greater than 0");
        }
        Exercise existing = exerciseRepository.getById(id);

        if (existing == null) {
            throw new ResourceNotFoundException("Exercise not found");
        }
        exercise.setWorkoutId(existing.getWorkoutId());
        exerciseRepository.update(id, exercise);
    }


    // GET BY ID
    public Exercise getExerciseById(int id)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {

        if (id <= 0) {
            throw new InvalidInputException("Invalid exercise id");
        }

        Exercise exercise = exerciseRepository.getById(id);

        if (exercise == null) {
            throw new ResourceNotFoundException("Exercise not found");
        }

        return exercise;
    }

    // GET BY WORKOUT
    public List<Exercise> getExercisesByWorkout(int workoutId)
            throws InvalidInputException, DatabaseOperationException {

        if (workoutId <= 0) {
            throw new InvalidInputException("Invalid workout id");
        }

        return exerciseQueries.getByWorkoutId(workoutId);
    }

    // DELETE
    public void deleteExercise(int id)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {

        if (id <= 0) {
            throw new InvalidInputException("Invalid exercise id");
        }

        if (exerciseRepository.getById(id) == null) {
            throw new ResourceNotFoundException("Exercise not found");
        }

        exerciseRepository.delete(id);
    }
}