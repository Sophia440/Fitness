package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.Dao;
import com.epam.web.entity.Program;

import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ProgramRowMapper;

import java.util.Optional;

public class ProgramDao extends AbstractDao<Program> implements Dao<Program> {
    public static final String TABLE_NAME = "program";
    public static final String FIND_PROGRAM_BY_CLIENT_ID = "SELECT * FROM program WHERE client_id = ?";

    public ProgramDao(ProxyConnection connection) {
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

    public Optional<Program> findProgramByClientId(Long clientId) throws DaoException {
        return executeForSingleResult(FIND_PROGRAM_BY_CLIENT_ID, new ProgramRowMapper(), clientId);
    }
}
