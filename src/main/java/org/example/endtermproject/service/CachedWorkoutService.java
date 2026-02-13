package org.example.endtermproject.service;

import org.example.endtermproject.exception.*;
import org.example.endtermproject.model.Workout;
import org.example.endtermproject.patterns.cache.CacheKeys;
import org.example.endtermproject.patterns.cache.CacheSingleton;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CachedWorkoutService implements IWorkoutService {

    private final WorkoutService delegate;
    private final CacheSingleton cache = CacheSingleton.getInstance();

    public CachedWorkoutService(WorkoutService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void createWorkout(Workout workout) throws InvalidInputException, DatabaseOperationException {
        delegate.createWorkout(workout);
        invalidateWorkoutCaches();
    }

    @Override
    public List<Workout> getAllWorkouts() throws DatabaseOperationException {
        @SuppressWarnings("unchecked")
        List<Workout> cached = (List<Workout>) cache.get(CacheKeys.WORKOUTS_ALL);

        if (cached != null) return cached;

        List<Workout> fresh = delegate.getAllWorkouts();
        cache.put(CacheKeys.WORKOUTS_ALL, fresh);
        return fresh;
    }

    @Override
    public List<Workout> getAllWorkoutsSortedByDuration() throws DatabaseOperationException {
        @SuppressWarnings("unchecked")
        List<Workout> cached = (List<Workout>) cache.get(CacheKeys.WORKOUTS_SORTED);

        if (cached != null) return cached;

        List<Workout> fresh = delegate.getAllWorkoutsSortedByDuration();
        cache.put(CacheKeys.WORKOUTS_SORTED, fresh);
        return fresh;
    }

    @Override
    public Workout getWorkoutById(int id)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {
        return delegate.getWorkoutById(id);
    }

    @Override
    public Workout getShortestC() throws DatabaseOperationException {
        return delegate.getShortestC();
    }

    @Override
    public Workout getShortestS() throws DatabaseOperationException {
        return delegate.getShortestS();
    }

    @Override
    public void updateWorkout(int id, Workout workout)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {
        delegate.updateWorkout(id, workout);
        invalidateWorkoutCaches();
    }

    @Override
    public void deleteWorkout(int id)
            throws InvalidInputException, ResourceNotFoundException, DatabaseOperationException {
        delegate.deleteWorkout(id);
        invalidateWorkoutCaches();
    }

    private void invalidateWorkoutCaches() {
        cache.remove(CacheKeys.WORKOUTS_ALL);
        cache.remove(CacheKeys.WORKOUTS_SORTED);
    }

    public void clearCache() {
        cache.clear();
    }
}
