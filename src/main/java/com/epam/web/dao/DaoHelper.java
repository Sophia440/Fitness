package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private final ProxyConnection connection;

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public UserDao createUserDao() {
        return new UserDao(connection);
    }

    public MembershipDao createMembershipDao() {
        return new MembershipDao(connection);
    }

    public ProgramDao createProgramDao() {
        return new ProgramDao(connection);
    }

    public ExerciseDao createExerciseDao() {
        return new ExerciseDao(connection);
    }

    public DietDao createDietDao() {
        return new DietDao(connection);
    }

    public DishDao createDishDao() {
        return new DishDao(connection);
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
        }
    }
}


