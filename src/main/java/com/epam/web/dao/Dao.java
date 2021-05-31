package com.epam.web.dao;

import com.epam.web.entity.Identifiable;
import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Base interface for all data access object classes.
 *
 */
public interface Dao<T extends Identifiable> {
    Optional<T> getById(Long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void save(T item) throws DaoException;

    void removeById(Long id) throws DaoException;
}
