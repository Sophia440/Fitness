package com.epam.web.service;

import com.epam.web.dao.ExerciseDao;
import com.epam.web.dao.ProgramDao;
import com.epam.web.entity.Exercise;
import com.epam.web.entity.Program;
import com.epam.web.entity.Status;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ProgramService {
    private final ProgramDao programDao;
    private final ExerciseDao exerciseDao;

    public ProgramService(ProgramDao programDao, ExerciseDao exerciseDao) {
        this.programDao = programDao;
        this.exerciseDao = exerciseDao;
    }

    public Optional<Program> getProgram(Long clientId) throws ServiceException {
        try {
            Optional<Program> optionalProgram = programDao.findProgramByClientId(clientId);
            if (optionalProgram.isPresent()) {
                setAssignedExercises(optionalProgram);
            }
            return optionalProgram;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    private void setAssignedExercises(Optional<Program> program) throws DaoException {
        Long programId = program.get().getId();
        List<Exercise> exercises = exerciseDao.getExercisesByProgramId(programId);
        program.get().setExercises(exercises);
    }

    public void setProgramStatus(Long clientId, Status status) throws ServiceException {
        try {
            Optional<Program> optionalProgram = getProgram(clientId);
            if (optionalProgram.isPresent()) {
                Program program = optionalProgram.get();
                program.setStatus(status);
                programDao.update(program);
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    public List<Exercise> getAllExercises() throws ServiceException {
        try {
            return exerciseDao.getAll();
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
