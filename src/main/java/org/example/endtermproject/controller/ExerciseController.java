package org.example.endtermproject.controller;

import org.example.endtermproject.dto.*;
import org.example.endtermproject.model.Exercise;
import org.example.endtermproject.patterns.ExerciseBuilder;
import org.example.endtermproject.service.IExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseController {

    private final IExerciseService exerciseService;

    public ExerciseController(IExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/api/workouts/{workoutId}/exercises")
    @ResponseStatus(HttpStatus.CREATED)
    public Exercise add(@PathVariable int workoutId, @RequestBody ExerciseCreateRequest req) throws Exception {
        Exercise e = new ExerciseBuilder()
                .workoutId(workoutId)
                .name(req.name)
                .sets(req.sets)
                .reps(req.reps)
                .build();

        exerciseService.addExercise(workoutId, e);
        return e;
    }

    @GetMapping("/api/workouts/{workoutId}/exercises")
    public List<Exercise> list(@PathVariable int workoutId) throws Exception {
        return exerciseService.getExercisesByWorkout(workoutId);
    }

    @GetMapping("/api/exercises/{id}")
    public Exercise getById(@PathVariable int id) throws Exception {
        return exerciseService.getExerciseById(id);
    }

    @PutMapping("/api/exercises/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody ExerciseUpdateRequest req) throws Exception {
        // workoutId preserved by your service logic
        Exercise updated = new ExerciseBuilder()
                .workoutId(1) // ignored/overwritten in service
                .name(req.name)
                .sets(req.sets)
                .reps(req.reps)
                .build();

        exerciseService.updateExercise(id, updated);
    }

    @DeleteMapping("/api/exercises/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws Exception {
        exerciseService.deleteExercise(id);
    }
}
