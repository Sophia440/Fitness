package com.epam.web.mapper;

import com.epam.web.entity.Dish;
import com.epam.web.entity.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishRowMapper implements RowMapper<Dish> {

    @Override
    public Dish map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Dish.ID);
        String name = resultSet.getString(Dish.NAME);
        String mealString = resultSet.getString(Dish.MEAL);
        Meal meal = Meal.valueOf(mealString.toUpperCase());
        return new Dish(id, name, meal);
    }
}
