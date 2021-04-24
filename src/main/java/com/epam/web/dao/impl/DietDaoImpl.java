package com.epam.web.dao.impl;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DietDao;
import com.epam.web.entity.Diet;
import com.epam.web.exception.DaoException;

import java.util.Optional;

public class DietDaoImpl extends AbstractDao<Diet> implements DietDao {

    public DietDaoImpl(ProxyConnection connection) {
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
        return null;
    }

    @Override
    public Optional<Diet> getById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Diet> findDietByClientId(Long clientId) {
        return Optional.empty();
    }
}
