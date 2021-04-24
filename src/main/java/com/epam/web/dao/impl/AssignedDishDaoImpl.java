package com.epam.web.dao.impl;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.AssignedDishDao;
import com.epam.web.entity.AssignedDish;
import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class AssignedDishDaoImpl extends AbstractDao<AssignedDish> implements AssignedDishDao {

    public AssignedDishDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected void create(AssignedDish item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Optional<AssignedDish> update(AssignedDish item) throws DaoException {
        return Optional.empty();
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public Optional<AssignedDish> getById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AssignedDish> findAllAssignedDishesByDietId(Long dietId) {
        throw new UnsupportedOperationException();
    }
}
