package com.epam.web.mapper;

import com.epam.web.entity.Identifiable;
import com.epam.web.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {
    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Identifiable> create(String table) {
        switch (table) {
            case User.TABLE:
                return new UserRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table " + table);
        }
    }
}
