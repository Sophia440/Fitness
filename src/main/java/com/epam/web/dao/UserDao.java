package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.UserRowMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Data access object class for User entity. Overrides all CRUD operations.
 *
 */
public class UserDao extends AbstractDao<User> implements Dao<User> {

    public static final String TABLE_NAME = "user";
    public static final String FIND = "SELECT * FROM user";
    public static final String FIND_BY_ID = "SELECT * FROM user WHERE id = ?";
    public static final String FIND_BY_ROLE = "SELECT * FROM user WHERE role = ?";
    public static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM user WHERE id = ?";
    private static final String CREATE = "INSERT INTO user (login, password, role) VALUE (?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET login = ?, password = ?, role = ?, discount = ? WHERE id = ?";
    public static final Logger LOGGER = LogManager.getLogger(UserDao.class);

    UserDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<User> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new UserRowMapper(), id);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(FIND, new UserRowMapper());
    }

    @Override
    public void create(User item) throws DaoException {
        executeUpdate(CREATE, item.getLogin(), item.getPassword(), item.getRole().toString());
    }

    @Override
    public Optional<User> update(User item) throws DaoException {
        Optional<User> userToUpdate = getById(item.getId());
        if (!userToUpdate.isPresent()) {
            throw new DaoException("User " + item.getId() + " not found.");
        }
        executeUpdate(UPDATE, item.getLogin(), item.getPassword(), item.getRole().toString(), item.getDiscount(), item.getId());
        return userToUpdate;
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }

    /**
     * Searches for the user with given login and password in the database.
     *
     * @param login of the user
     * @param password of the user
     * @return Optional<User>
     */
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    /**
     * Gets a list of users by a given role from the database.
     *
     * @param role given role
     * @return list of users
     */
    public List<User> getUsersByRole(Role role) throws DaoException {
        return executeQuery(FIND_BY_ROLE, new UserRowMapper(), role.toString());
    }
}
