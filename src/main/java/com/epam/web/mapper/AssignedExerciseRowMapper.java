package com.epam.web.mapper;

import com.epam.web.entity.AssignedExercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AssignedExerciseRowMapper implements RowMapper<AssignedExercise> {

    @Override
    public AssignedExercise map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(AssignedExercise.ID);
        Long programId = resultSet.getLong(AssignedExercise.PROGRAM_ID);
        Long exerciseId = resultSet.getLong(AssignedExercise.EXERCISE_ID);
        LocalDate startDate = resultSet.getDate(AssignedExercise.START_DATE).toLocalDate();
        LocalDate endDate = resultSet.getDate(AssignedExercise.END_DATE).toLocalDate();
        return new AssignedExercise(id, programId, exerciseId, startDate, endDate);
    }
}
