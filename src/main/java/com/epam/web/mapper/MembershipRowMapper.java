package com.epam.web.mapper;

import com.epam.web.entity.Membership;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * This class maps Membership entities from database information.
 *
 */
public class MembershipRowMapper implements RowMapper<Membership> {

    /**
     * Creates a Membership object.
     *
     * @param resultSet with information from the database
     * @return Membership
     */
    @Override
    public Membership map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Membership.ID);
        Long clientId = resultSet.getLong(Membership.CLIENT_ID);
        LocalDate startDate = resultSet.getDate(Membership.START_DATE).toLocalDate();
        LocalDate endDate = resultSet.getDate(Membership.END_DATE).toLocalDate();
        LocalDate paymentDate = resultSet.getDate(Membership.PAYMENT_DATE).toLocalDate();
        return new Membership(id, clientId, startDate, endDate, paymentDate);
    }
}
