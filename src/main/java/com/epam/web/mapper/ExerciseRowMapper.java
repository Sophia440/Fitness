package com.epam.web.mapper;

import com.epam.web.entity.Exercise;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class maps Exercise entities from database information.
 *
 */
public class ExerciseRowMapper implements RowMapper<Exercise> {

    /**
     * Creates a Exercise object.
     *
     * @param resultSet with information from the database
     * @return Exercise
     */
    @Override
    public Exercise map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Exercise.ID);
        String name = resultSet.getString(Exercise.NAME);
        return new Exercise(id, name);
    }
}
