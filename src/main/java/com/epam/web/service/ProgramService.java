package com.epam.web.service;

import com.epam.web.dao.AssignedExerciseDao;
import com.epam.web.dao.ExerciseDao;
import com.epam.web.dao.ProgramDao;
import com.epam.web.entity.AssignedExercise;
import com.epam.web.entity.Exercise;
import com.epam.web.entity.Program;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgramService {
    private final ProgramDao programDao;
    private final AssignedExerciseDao assignedExerciseDao;
    private final ExerciseDao exerciseDao;

    public ProgramService(ProgramDao programDao, AssignedExerciseDao assignedExerciseDao, ExerciseDao exerciseDao) {
        this.programDao = programDao;
        this.assignedExerciseDao = assignedExerciseDao;
        this.exerciseDao = exerciseDao;
    }

    public Optional<Program> getProgram(Long clientId) throws ServiceException {
        try {
            Optional<Program> optionalProgram = programDao.findProgramByClientId(clientId);
            if (optionalProgram.isPresent()) {
                optionalProgram = setAssignedExercises(optionalProgram);
            }
            return optionalProgram;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    private Optional<Program> setAssignedExercises(Optional<Program> program) throws DaoException {
        Optional<Program> updatedProgram = program;
        Long programId = program.get().getId();
        List<AssignedExercise> assignedExercises = assignedExerciseDao.findAllAssignedExercisesByProgramId(programId);
        List<Exercise> exercises = new ArrayList<>();
        for (AssignedExercise assignedExercise : assignedExercises) {
            Optional<Exercise> exercise = exerciseDao.getById(assignedExercise.getExerciseId());
            if (exercise.isPresent()) {
                exercises.add(exercise.get());
            }
        }
        updatedProgram.get().setExercises(exercises);
        return updatedProgram;
    }
}
