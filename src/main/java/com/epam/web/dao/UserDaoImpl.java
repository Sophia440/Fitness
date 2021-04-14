package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public static final String FIND = "select * from user";
    public static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from user where login = ? and password = ?";
    // WHERE login = ? AND password = MD5(?)


    public UserDaoImpl() {
        super();
    }

    public UserDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(FIND, new UserRowMapper());
    }

    @Override
    public void save(User item) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }
}
