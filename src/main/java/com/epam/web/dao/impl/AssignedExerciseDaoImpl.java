package com.epam.web.dao.impl;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.AssignedExerciseDao;
import com.epam.web.entity.AssignedExercise;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.AssignedExerciseRowMapper;

import java.util.List;
import java.util.Optional;

public class AssignedExerciseDaoImpl extends AbstractDao<AssignedExercise> implements AssignedExerciseDao {
    public static final String TABLE_NAME = "assigned_exercise";
    public static final String FIND_EXERCISES_BY_PROGRAM_ID = "select * from assigned_exercise where program_id = ?";

    public AssignedExerciseDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(AssignedExercise item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Optional<AssignedExercise> update(AssignedExercise item) throws DaoException {
        return Optional.empty();
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<AssignedExercise> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AssignedExercise> findAllAssignedExercisesByProgramId(Long id) throws DaoException {
        return executeQuery(FIND_EXERCISES_BY_PROGRAM_ID, new AssignedExerciseRowMapper(), id);
    }
}
