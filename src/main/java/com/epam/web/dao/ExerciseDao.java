package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.Dao;
import com.epam.web.entity.Exercise;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ExerciseRowMapper;

import java.util.List;
import java.util.Optional;

public class ExerciseDao extends AbstractDao<Exercise> implements Dao<Exercise> {
    public static final String TABLE_NAME = "exercise";
    public static final String FIND_BY_ID = "SELECT * FROM exercise WHERE id = ?";
    public static final String FIND_BY_PROGRAM_ID = "SELECT exercise.id, exercise.name FROM exercise INNER JOIN assigned_exercise ON exercise.id=assigned_exercise.exercise_id WHERE program_id = ?";

    public ExerciseDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(Exercise item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Optional<Exercise> update(Exercise item) throws DaoException {
        return Optional.empty();
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Exercise> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new ExerciseRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public List<Exercise> getExercisesByProgramId(Long programId) throws DaoException {
        return executeQuery(FIND_BY_PROGRAM_ID, new ExerciseRowMapper(), programId);
    }
}
