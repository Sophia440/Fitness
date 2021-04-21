package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.impl.AssignedExerciseDaoImpl;
import com.epam.web.dao.impl.ExerciseDaoImpl;
import com.epam.web.dao.impl.ProgramDaoImpl;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.entity.AssignedExercise;
import com.epam.web.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private final ProxyConnection connection;

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public ProgramDao createProgramDao() {
        return new ProgramDaoImpl(connection);
    }

    public AssignedExerciseDao createAssignedExerciseDao() {
        return new AssignedExerciseDaoImpl(connection);
    }

    public ExerciseDao createExerciseDao() {
        return new ExerciseDaoImpl(connection);
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


