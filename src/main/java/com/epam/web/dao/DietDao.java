package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Diet;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.DietRowMapper;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Data access object class for Diet entity. Overrides all CRUD operations.
 *
 */
public class DietDao extends AbstractDao<Diet> implements Dao<Diet> {
    public static final String TABLE_NAME = "diet";
    public static final String FIND_DIET_BY_ID = "SELECT * FROM diet WHERE id = ?";
    public static final String FIND_DIET_BY_CLIENT_ID = "SELECT * FROM diet WHERE client_id = ?";
    public static final String FIND_DIET_BY_CLIENT_ID_AND_START_END_DATES = "SELECT * FROM diet WHERE client_id = ? AND start_date = ? AND end_date = ?";
    private static final String CREATE = "INSERT INTO diet (client_id, instructor_id, start_date, end_date, status) VALUE (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE diet SET client_id = ?, instructor_id = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM diet WHERE id = ?";

    public DietDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public void create(Diet item) throws DaoException {
        executeUpdate(CREATE, item.getClientId(), item.getInstructorId(), item.getStartDate(), item.getEndDate(), item.getStatus().toString());
    }

    @Override
    public Optional<Diet> update(Diet item) throws DaoException {
        Optional<Diet> dietToUpdate = getById(item.getId());
        if (!dietToUpdate.isPresent()) {
            throw new DaoException("Diet  " + item.getId() + " not found.");
        }
        executeUpdate(UPDATE, item.getClientId(), item.getInstructorId(), item.getStartDate(), item.getEndDate(), item.getStatus().toString(), item.getId());
        return dietToUpdate;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Diet> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_DIET_BY_ID, new DietRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }

    /**
     * Searches for the diet with given client id in the database.
     *
     * @param clientId id of the client
     * @return Optional<Diet>
     */
    public Optional<Diet> findDietByClientId(Long clientId) throws DaoException {
        return executeForSingleResult(FIND_DIET_BY_CLIENT_ID, new DietRowMapper(), clientId);
    }

    /**
     * Searches for the diet with given client id, starting and ending dates the in database.
     *
     * @param clientId id of the client
     * @param startDate starting date
     * @param endDate ending date
     * @return Optional<Diet>
     */
    public Optional<Diet> findDietByClientIdAndStartEndDates(Long clientId, LocalDate startDate, LocalDate endDate) throws DaoException {
        return executeForSingleResult(FIND_DIET_BY_CLIENT_ID_AND_START_END_DATES, new DietRowMapper(), clientId, startDate.toString(), endDate.toString());
    }
}
