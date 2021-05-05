package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Program;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ProgramRowMapper;

import java.util.Optional;

public class ProgramDao extends AbstractDao<Program> implements Dao<Program> {
    public static final String TABLE_NAME = "program";
    public static final String FIND_PROGRAM_BY_ID = "SELECT * FROM program WHERE id = ?";
    public static final String FIND_PROGRAM_BY_CLIENT_ID = "SELECT * FROM program WHERE client_id = ?";
    private static final String CREATE = "INSERT INTO program (client_id, instructor_id, status) VALUE (?, ?, ?)";
    private static final String UPDATE = "UPDATE program SET client_id = ?, instructor_id = ?, status = ? WHERE id = ?";

    public ProgramDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public void create(Program item) throws DaoException {
        executeUpdate(CREATE, item.getClientId(), item.getInstructorId(), item.getStatus().toString());
    }

    @Override
    public Optional<Program> update(Program item) throws DaoException {
        Optional<Program> programToUpdate = getById(item.getId());
        if (!programToUpdate.isPresent()) {
            throw new DaoException("Program  " + item.getId() + " not found.");
        }
        executeUpdate(UPDATE, item.getClientId(), item.getInstructorId(), item.getStatus().toString(), item.getId());
        return programToUpdate;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Program> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_PROGRAM_BY_ID, new ProgramRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public Optional<Program> findProgramByClientId(Long clientId) throws DaoException {
        return executeForSingleResult(FIND_PROGRAM_BY_CLIENT_ID, new ProgramRowMapper(), clientId);
    }
}
