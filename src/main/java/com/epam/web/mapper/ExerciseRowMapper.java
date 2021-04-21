package com.epam.web.mapper;

import com.epam.web.entity.Exercise;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseRowMapper implements RowMapper<Exercise> {

    @Override
    public Exercise map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Exercise.ID);
        String name = resultSet.getString(Exercise.NAME);
        return new Exercise(id, name);
    }
}
