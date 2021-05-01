package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.Dao;
import com.epam.web.entity.Diet;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.DietRowMapper;
import com.epam.web.mapper.ProgramRowMapper;

import java.util.Optional;

public class DietDao extends AbstractDao<Diet> implements Dao<Diet> {
    public static final String TABLE_NAME = "diet";
    public static final String FIND_DIET_BY_CLIENT_ID = "SELECT * FROM diet WHERE client_id = ?";

    public DietDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(Diet item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Optional<Diet> update(Diet item) throws DaoException {
        return Optional.empty();
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Diet> getById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public Optional<Diet> findDietByClientId(Long clientId) throws DaoException {
        return executeForSingleResult(FIND_DIET_BY_CLIENT_ID, new DietRowMapper(), clientId);
    }
}
