package com.epam.web.dao.impl;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DishDao;
import com.epam.web.entity.Dish;
import com.epam.web.exception.DaoException;

import java.util.Optional;

public class DishDaoImpl extends AbstractDao<Dish> implements DishDao {

    public DishDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(Dish item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Optional<Dish> update(Dish item) throws DaoException {
        return Optional.empty();
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public Optional<Dish> getById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
