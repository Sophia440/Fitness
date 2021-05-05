package com.epam.web.mapper;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID);
        String login = resultSet.getString(User.LOGIN);
        String password = resultSet.getString(User.PASSWORD);
        String roleString = resultSet.getString(User.ROLE);
        Role role = Role.valueOf(roleString.toUpperCase());
        int discount = resultSet.getInt(User.DISCOUNT);
        return new User(id, login, password, role, discount);
    }
}
