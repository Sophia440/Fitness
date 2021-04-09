package com.epam.web.dao;

import com.epam.web.entity.Identifiable;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {
    private Connection connection;

    private static final String MYSQL_URL = "jdbc:mysql://localhost:3307/fitness_db?useSSL=false&serverTimezone=Europe/Minsk";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "root";

    public AbstractDao() {
        Driver driver = null;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            this.connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery(query);
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
            //throw new DaoException(exception.getMessage() + "\nthrown from AbstractDao", exception);
        }
    }

    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    public List<T> getAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("select * from " + table, mapper);
    }

    protected Optional<T> executeForSingleResult(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        List<T> items = executeQuery(query, mapper, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        }
        return Optional.empty();
    }

    protected abstract String getTableName();
}
