package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Membership;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.MembershipRowMapper;

import java.util.List;
import java.util.Optional;

/**
 * Data access object class for Membership entity. Overrides all CRUD operations.
 *
 */
public class MembershipDao extends AbstractDao<Membership> implements Dao<Membership> {
    public static final String TABLE_NAME = "membership";
    public static final String FIND_BY_ID = "SELECT * FROM membership WHERE id = ?";
    public static final String FIND_BY_CLIENT_ID = "SELECT * FROM membership WHERE client_id = ?";
    private static final String CREATE = "INSERT INTO membership (client_id, start_date, end_date, payment_date) VALUE (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE membership SET client_id = ?, start_date = ?, end_date = ?, payment_date = ? WHERE id = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM membership WHERE id = ?";

    public MembershipDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public void create(Membership item) throws DaoException {
        executeUpdate(CREATE, item.getClientId(), item.getStartDate(), item.getEndDate(), item.getPaymentDate());
    }

    @Override
    public Optional<Membership> update(Membership item) throws DaoException {
        Optional<Membership> membershipToUpdate = getById(item.getId());
        if (!membershipToUpdate.isPresent()) {
            throw new DaoException("Membership " + item.getId() + " not found.");
        }
        executeUpdate(UPDATE, item.getClientId(), item.getStartDate(), item.getEndDate(), item.getPaymentDate(), item.getId());
        return membershipToUpdate;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Membership> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new MembershipRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }

    /**
     * Searches for the memberships with given client id in the database.
     *
     * @param clientId id of the client
     * @return list of memberships
     */
    public List<Membership> findMembershipsByClientId(Long clientId) throws DaoException {
        return executeQuery(FIND_BY_CLIENT_ID, new MembershipRowMapper(), clientId);
    }
}
