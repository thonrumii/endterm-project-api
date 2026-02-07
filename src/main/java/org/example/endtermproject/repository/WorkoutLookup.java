package org.example.endtermproject.repository;

import org.example.endtermproject.exception.DatabaseOperationException;
public interface WorkoutLookup {
    boolean existsByName(String name) throws DatabaseOperationException;
    boolean existsByNameExceptId(String name, int excludeId) throws DatabaseOperationException;
}