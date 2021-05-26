package com.epam.web.service;

import com.epam.web.dao.ExerciseDao;
import com.epam.web.dao.ProgramDao;
import com.epam.web.entity.Exercise;
import com.epam.web.entity.Program;
import com.epam.web.entity.Status;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProgramService {
    public static final Logger LOGGER = LogManager.getLogger(ProgramService.class);
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

    public boolean hasProgram(Long clientId) throws ServiceException {
        Optional<Program> optionalProgram = getProgram(clientId);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            Status programStatus = program.getStatus();
            return programStatus != Status.DECLINED;
        }
        return false;
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

    public boolean addProgram(Long clientId, Long instructorId, String[] programExercisesList, String startDate, String endDate) throws ServiceException {
        List<Long> exercisesIds = Arrays.stream(programExercisesList)
                .map(Long::valueOf)
                .collect(Collectors.toList());
        List<Exercise> newExerciseList = exercisesIds.stream()
                .map(exerciseId -> {
                    Optional<Exercise> optionalExercise = getExerciseById(exerciseId);
                    return optionalExercise.orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Program newProgram = new Program();
        newProgram.setExercises(newExerciseList);
        newProgram.setInstructorId(instructorId);
        newProgram.setClientId(clientId);
        LocalDate startDateLocal = LocalDate.parse(startDate);
        LocalDate endDateLocal = LocalDate.parse(endDate);
        newProgram.setStartDate(startDateLocal);
        newProgram.setEndDate(endDateLocal);
        newProgram.setStatus(Status.AWAITING_CLIENT_ANSWER);
        try {
            programDao.create(newProgram);
            Optional<Program> optionalProgram = programDao.findProgramByClientIdAndStartEndDates(clientId, startDateLocal, endDateLocal);
            if (optionalProgram.isPresent()) {
                Long programId = optionalProgram.get().getId();
                newExerciseList.stream().forEach(exercise -> {
                    addAssignedExercise(programId, exercise.getId());
                });
            }
            return true;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    public void addAssignedExercise(Long programId, Long exerciseId) {
        try {
            exerciseDao.createAssignedExercise(programId, exerciseId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
    }

    public Optional<Exercise> getExerciseById(Long exerciseId) {
        try {
            return exerciseDao.getById(exerciseId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
        return Optional.empty();
    }

    public List<Exercise> getAllExercises() throws ServiceException {
        try {
            return exerciseDao.getAll();
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    public boolean addExercise(String newExerciseName) throws ServiceException {
        Exercise exercise = new Exercise();
        exercise.setName(newExerciseName);
        try {
            exerciseDao.create(exercise);
            return true;
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

    public List<Exercise> getExerciseSublist(int firstRow, int rowCount) throws ServiceException {
        try {
            return exerciseDao.getSublist(firstRow, rowCount);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    public int getExerciseCount() throws ServiceException {
        try {
            return exerciseDao.getExerciseCount();
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
