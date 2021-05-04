package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Diet;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.DietRowMapper;

import java.util.Optional;

public class DietDao extends AbstractDao<Diet> implements Dao<Diet> {
    public static final String TABLE_NAME = "diet";
    public static final String FIND_DIET_BY_ID = "SELECT * FROM diet WHERE id = ?";
    public static final String FIND_DIET_BY_CLIENT_ID = "SELECT * FROM diet WHERE client_id = ?";
    private static final String UPDATE = "UPDATE diet SET client_id = ?, instructor_id = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";

    public DietDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public void create(Diet item) throws DaoException {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public Optional<Diet> findDietByClientId(Long clientId) throws DaoException {
        return executeForSingleResult(FIND_DIET_BY_CLIENT_ID, new DietRowMapper(), clientId);
    }
}
