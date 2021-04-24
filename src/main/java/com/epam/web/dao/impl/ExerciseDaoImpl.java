package com.epam.web.dao.impl;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.ExerciseDao;
import com.epam.web.entity.Exercise;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ExerciseRowMapper;

import java.util.Optional;

public class ExerciseDaoImpl extends AbstractDao<Exercise> implements ExerciseDao {
    public static final String TABLE_NAME = "exercise";
    public static final String FIND_BY_ID = "select * from exercise where id = ?";

    public ExerciseDaoImpl(ProxyConnection connection) {
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
}
