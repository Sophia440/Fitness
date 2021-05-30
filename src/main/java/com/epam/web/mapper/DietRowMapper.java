package com.epam.web.mapper;

import com.epam.web.entity.Diet;
import com.epam.web.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * This class maps Diet entities from database information.
 *
 */
public class DietRowMapper implements RowMapper<Diet> {

    /**
     * Creates a Diet object.
     *
     * @param resultSet with information from the database
     * @return Diet
     */
    @Override
    public Diet map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Diet.ID);
        Long instructorId = resultSet.getLong(Diet.INSTRUCTOR_ID);
        Long clientId = resultSet.getLong(Diet.CLIENT_ID);
        LocalDate startDate = resultSet.getDate(Diet.START_DATE).toLocalDate();
        LocalDate endDate = resultSet.getDate(Diet.END_DATE).toLocalDate();
        String statusString = resultSet.getString(Diet.STATUS);
        Status status = Status.valueOf(statusString.toUpperCase());
        return new Diet(id, instructorId, clientId, startDate, endDate, status);
    }
}
