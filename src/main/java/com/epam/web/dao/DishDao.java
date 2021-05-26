package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Dish;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.DishRowMapper;

import java.util.List;
import java.util.Optional;

public class DishDao extends AbstractDao<Dish> implements Dao<Dish> {
    public static final String TABLE_NAME = "dish";
    public static final String COUNT_DISHES = "SELECT COUNT(id) FROM dish";
    public static final String COUNT_COLUMN_NAME = "COUNT(id)";
    public static final String SELECT_SUBLIST = "SELECT * FROM dish ORDER BY id LIMIT ? OFFSET ?";
    public static final String FIND_BY_ID = "SELECT * FROM dish WHERE id = ?";
    public static final String FIND_BY_DIET_ID = "SELECT dish.id, dish.name, dish.meal FROM dish INNER JOIN assigned_dish ON dish.id=assigned_dish.dish_id WHERE diet_id = ?";
    private static final String CREATE = "INSERT INTO dish (name, meal) VALUE (?, ?)";
    private static final String CREATE_ASSIGNED_DISH = "INSERT INTO assigned_dish (diet_id, dish_id) VALUE (?, ?)";
    private static final String REMOVE_BY_ID = "DELETE FROM dish WHERE id = ?";
    private static final String REMOVE_BY_ID_FROM_ASSIGNED_DISH = "DELETE FROM assigned_dish WHERE dish_id = ?";

    public DishDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public void create(Dish item) throws DaoException {
        executeUpdate(CREATE, item.getName(), item.getMeal().toString());
    }

    @Override
    public Optional<Dish> update(Dish item) throws DaoException {
        return Optional.empty();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Dish> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new DishRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_ID_FROM_ASSIGNED_DISH, id);
        executeUpdate(REMOVE_BY_ID, id);
    }

    public List<Dish> getDishesByDietId(Long dietId) throws DaoException {
        return executeQuery(FIND_BY_DIET_ID, new DishRowMapper(), dietId);
    }

    public void createAssignedDish(Long dietId, Long dishId) throws DaoException {
        executeUpdate(CREATE_ASSIGNED_DISH, dietId, dishId);
    }

    public List<Dish> getSublist(int firstRow, int rowCount) throws DaoException {
        return executeQuery(SELECT_SUBLIST, new DishRowMapper(), rowCount, firstRow);
    }

    public int getDishCount() throws DaoException {
        return executeQuery(COUNT_DISHES, COUNT_COLUMN_NAME);
    }
}
