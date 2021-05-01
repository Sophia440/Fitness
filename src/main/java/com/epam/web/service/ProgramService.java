package com.epam.web.service;

import com.epam.web.dao.ExerciseDao;
import com.epam.web.dao.ProgramDao;
import com.epam.web.entity.Exercise;
import com.epam.web.entity.Program;
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
}
