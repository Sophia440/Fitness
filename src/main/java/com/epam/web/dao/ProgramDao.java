package com.epam.web.dao;

import com.epam.web.entity.Program;
import com.epam.web.exception.DaoException;

import java.util.Optional;

public interface ProgramDao extends Dao<Program> {

    Optional<Program> findProgramByClientId(Long id) throws DaoException;
}
