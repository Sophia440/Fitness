package com.epam.web.mapper;

import com.epam.web.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is an base interface for row mapper classes, that are used by dao classes
 * to map entities from database information.
 *
 */
public interface RowMapper<T extends Identifiable> {
    T map(ResultSet resultSet) throws SQLException;

    /**
     * Creates a RowMapper class based on a 'table' parameter.
     *
     * @param table represents a type of RowMapper to return
     * @return RowMapper for an Identifiable entity
     */
    static RowMapper<? extends Identifiable> create(String table) {
        switch (table) {
            case User.TABLE:
                return new UserRowMapper();
            case Exercise.TABLE:
                return new ExerciseRowMapper();
            case Dish.TABLE:
                return new DishRowMapper();
            case Diet.TABLE:
                return new DietRowMapper();
            case Program.TABLE:
                return new ProgramRowMapper();
            case Membership.TABLE:
                return new MembershipRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table " + table);
        }
    }
}
