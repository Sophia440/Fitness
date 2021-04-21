package com.epam.web.dao;

import com.epam.web.entity.AssignedExercise;
import com.epam.web.exception.DaoException;

import java.util.List;

public interface AssignedExerciseDao extends Dao<AssignedExercise> {
    List<AssignedExercise> findAllAssignedExercisesByProgramId(Long id) throws DaoException;
}
