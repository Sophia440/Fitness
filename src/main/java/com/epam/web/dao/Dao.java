package com.epam.web.dao;

import com.epam.web.entity.Identifiable;
import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> getById(Long id);

    List<T> getAll() throws DaoException;

    void save(T item);

    void removeById(Long id);
}
