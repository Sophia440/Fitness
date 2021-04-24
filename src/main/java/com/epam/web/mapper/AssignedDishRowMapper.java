package com.epam.web.mapper;

import com.epam.web.entity.AssignedDish;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignedDishRowMapper implements RowMapper<AssignedDish> {

    @Override
    public AssignedDish map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(AssignedDish.ID);
        Long dietId = resultSet.getLong(AssignedDish.DIET_ID);
        Long dishId = resultSet.getLong(AssignedDish.DISH_ID);
        return new AssignedDish(id, dietId, dishId);
    }
}
