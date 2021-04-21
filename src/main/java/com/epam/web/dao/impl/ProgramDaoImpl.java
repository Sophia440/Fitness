package com.epam.web.dao.impl;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.ProgramDao;
import com.epam.web.entity.Exercise;
import com.epam.web.entity.Program;

import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ProgramRowMapper;

import java.util.Optional;

public class ProgramDaoImpl extends AbstractDao<Program> implements ProgramDao {
    public static final String TABLE_NAME = "program";
    public static final String FIND = "select * from program";
    public static final String FIND_PROGRAM_BY_CLIENT_ID = "select * from program where client_id = ?";

    public ProgramDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(Program item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Optional<Program> update(Program item) throws DaoException {
        return Optional.empty();
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Program> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Program> findProgramByClientId(Long id) throws DaoException {
        return executeForSingleResult(FIND_PROGRAM_BY_CLIENT_ID, new ProgramRowMapper(), id);
    }
}
