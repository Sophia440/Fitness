package com.epam.web.mapper;

import com.epam.web.entity.Program;
import com.epam.web.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * This class maps Program entities from database information.
 *
 */
public class ProgramRowMapper implements RowMapper<Program> {

    /**
     * Creates a Program object.
     *
     * @param resultSet with information from the database
     * @return Program
     */
    @Override
    public Program map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Program.ID);
        Long instructorId = resultSet.getLong(Program.INSTRUCTOR_ID);
        Long clientId = resultSet.getLong(Program.CLIENT_ID);
        LocalDate startDate = resultSet.getDate(Program.START_DATE).toLocalDate();
        LocalDate endDate = resultSet.getDate(Program.END_DATE).toLocalDate();
        String statusString = resultSet.getString(Program.STATUS);
        Status status = Status.valueOf(statusString.toUpperCase());
        return new Program(id, instructorId, clientId, startDate, endDate, status);
    }
}
