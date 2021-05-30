package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Program;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ProgramRowMapper;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Data access object class for Program entity. Overrides all CRUD operations.
 *
 */
public class ProgramDao extends AbstractDao<Program> implements Dao<Program> {
    public static final String TABLE_NAME = "program";
    public static final String FIND_PROGRAM_BY_ID = "SELECT * FROM program WHERE id = ?";
    public static final String FIND_PROGRAM_BY_CLIENT_ID = "SELECT * FROM program WHERE client_id = ?";
    public static final String FIND_PROGRAM_BY_CLIENT_ID_AND_START_END_DATES = "SELECT * FROM program WHERE client_id = ? AND start_date = ? AND end_date = ?";
    private static final String CREATE = "INSERT INTO program (client_id, instructor_id, start_date, end_date, status) VALUE (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE program SET client_id = ?, instructor_id = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM program WHERE id = ?";

    public ProgramDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public void create(Program item) throws DaoException {
        executeUpdate(CREATE, item.getClientId(), item.getInstructorId(), item.getStartDate(), item.getEndDate(), item.getStatus().toString());
    }

    @Override
    public Optional<Program> update(Program item) throws DaoException {
        Optional<Program> programToUpdate = getById(item.getId());
        if (!programToUpdate.isPresent()) {
            throw new DaoException("Program  " + item.getId() + " not found.");
        }
        executeUpdate(UPDATE, item.getClientId(), item.getInstructorId(), item.getStartDate(), item.getEndDate(), item.getStatus().toString(), item.getId());
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
        executeUpdate(REMOVE_BY_ID, id);
    }

    /**
     * Searches for the program with given client id in the database.
     *
     * @param clientId id of the client
     * @return Optional<Program>
     */
    public Optional<Program> findProgramByClientId(Long clientId) throws DaoException {
        return executeForSingleResult(FIND_PROGRAM_BY_CLIENT_ID, new ProgramRowMapper(), clientId);
    }

    /**
     * Searches for the program with given client id, starting and ending dates the in database.
     *
     * @param clientId id of the client
     * @param startDate starting date
     * @param endDate ending date
     * @return Optional<Program>
     */
    public Optional<Program> findProgramByClientIdAndStartEndDates(Long clientId, LocalDate startDate, LocalDate endDate) throws DaoException {
        return executeForSingleResult(FIND_PROGRAM_BY_CLIENT_ID_AND_START_END_DATES, new ProgramRowMapper(), clientId, startDate.toString(), endDate.toString());
    }
}
