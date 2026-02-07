package org.example.endtermproject.controller;

import org.example.endtermproject.dto.*;
import org.example.endtermproject.model.Workout;
import org.example.endtermproject.patterns.WorkoutFactory;
import org.example.endtermproject.service.IWorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final IWorkoutService workoutService;

    public WorkoutController(IWorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Workout create(@RequestBody WorkoutCreateRequest req) throws Exception {
        Workout w = WorkoutFactory.create(req.type, req.name, req.duration);
        workoutService.createWorkout(w);
        return w;
    }

    @GetMapping
    public List<Workout> getAll() throws Exception {
        return workoutService.getAllWorkouts();
    }

    @GetMapping("/{id}")
    public Workout getById(@PathVariable int id) throws Exception {
        return workoutService.getWorkoutById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody WorkoutUpdateRequest req) throws Exception {
        Workout existing = workoutService.getWorkoutById(id);
        existing.setName(req.name);
        existing.setDuration(req.duration);
        workoutService.updateWorkout(id, existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws Exception {
        workoutService.deleteWorkout(id);
    }

    @GetMapping("/shortest/cardio")
    public Workout shortestCardio() throws Exception {
        return workoutService.getShortestC();
    }

    @GetMapping("/shortest/strength")
    public Workout shortestStrength() throws Exception {
        return workoutService.getShortestS();
    }

    @GetMapping("/sorted/duration")
    public List<Workout> sortedByDuration() throws Exception {
        return workoutService.getAllWorkoutsSortedByDuration();
    }
}
