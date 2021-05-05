package com.epam.web.service;

import com.epam.web.dao.ExerciseDao;
import com.epam.web.dao.ProgramDao;
import com.epam.web.entity.Exercise;
import com.epam.web.entity.Program;
import com.epam.web.entity.Status;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void addProgram(Long clientId, Long instructorId, String[] programExercisesList, HttpSession session) throws ServiceException {
        Optional<Program> optionalProgram = getProgram(clientId);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            if (program.getStatus() == Status.ACTIVE) {
                session.setAttribute("confirmation", "User already has an active program!");
            } else {
                List<Long> exercisesIds = Arrays.stream(programExercisesList)
                        .map(Long::valueOf)
                        .collect(Collectors.toList());
                List<Exercise> newExerciseList = exercisesIds.stream()
                        .map(exerciseId -> {
                            try {
                                Optional<Exercise> optionalExercise = getExerciseById(exerciseId);
                                if (optionalExercise.isPresent()) {
                                    return optionalExercise.get();
                                }
                            } catch (ServiceException exception) {
                                exception.printStackTrace();
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                Program newProgram = new Program();
                newProgram.setExercises(newExerciseList);
                newProgram.setInstructorId(instructorId);
                newProgram.setClientId(clientId);
                newProgram.setStatus(Status.AWAITING_CLIENT_ANSWER);
                try {
                    programDao.create(program);
                } catch (DaoException exception) {
                    throw new ServiceException(exception);
                }
            }
        }
    }

    public Optional<Exercise> getExerciseById(Long exerciseId) throws ServiceException {
        try {
            return exerciseDao.getById(exerciseId);
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

    public void addExercise(String newExerciseName) throws ServiceException {
        Exercise exercise = new Exercise();
        exercise.setName(newExerciseName);
        try {
            exerciseDao.create(exercise);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    public void deleteExercise(Long id) throws ServiceException {
        try {
            exerciseDao.removeById(id);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
