package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.Dao;
import com.epam.web.entity.Dish;
import com.epam.web.entity.Exercise;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.DishRowMapper;
import com.epam.web.mapper.ExerciseRowMapper;

import java.util.List;
import java.util.Optional;

public class DishDao extends AbstractDao<Dish> implements Dao<Dish> {
    public static final String TABLE_NAME = "dish";
    public static final String FIND_BY_ID = "SELECT * FROM dish WHERE id = ?";
    public static final String FIND_BY_DIET_ID = "SELECT dish.id, dish.name, dish.meal FROM dish INNER JOIN assigned_dish ON dish.id=assigned_dish.dish_id WHERE diet_id = ?";

    public DishDao(ProxyConnection connection) {
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
        return TABLE_NAME;
    }

    @Override
    public Optional<Dish> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new DishRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public List<Dish> getDishesByDietId(Long dietId) throws DaoException {
        return executeQuery(FIND_BY_DIET_ID, new DishRowMapper(), dietId);
    }
}
