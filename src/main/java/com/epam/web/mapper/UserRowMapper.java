package com.epam.web.mapper;

import com.epam.web.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID);
        String name = resultSet.getString(User.NAME);
        return new User(id, name);
    }
}
