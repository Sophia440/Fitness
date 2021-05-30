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

/**
 * This class handles operations with ProgramDao and ExerciseDao classes.
 *
 */
public class ProgramService {
    public static final Logger LOGGER = LogManager.getLogger(ProgramService.class);
    private final ProgramDao programDao;
    private final ExerciseDao exerciseDao;

    public ProgramService(ProgramDao programDao, ExerciseDao exerciseDao) {
        this.programDao = programDao;
        this.exerciseDao = exerciseDao;
    }

    /**
     * Gets a Program from the database.
     *
     * @param clientId id of the client
     * @return Optional<Program>
     */
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

    /**
     * Gets the assigned exercises from the database.
     *
     * @param program to set exercises
     */
    private void setAssignedExercises(Optional<Program> program) throws DaoException {
        Long programId = program.get().getId();
        List<Exercise> exercises = exerciseDao.getExercisesByProgramId(programId);
        program.get().setExercises(exercises);
    }

    /**
     * Checks if client has an active or awaiting program.
     *
     * @param clientId id of the client
     * @return boolean true if client has a program
     */
    public boolean hasProgram(Long clientId) throws ServiceException {
        Optional<Program> optionalProgram = getProgram(clientId);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            Status programStatus = program.getStatus();
            return programStatus != Status.DECLINED;
        }
        return false;
    }

    /**
     * Sets program status.
     *
     * @param clientId id of the client
     * @param status to set
     */
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

    /**
     * Adds a new program to the database.
     *
     * @param clientId id of the client
     * @param instructorId id of the instructor
     * @param programExercisesList list of exercises in new program
     * @param startDate starting date
     * @param endDate ending date
     * @return boolean true if program added successfully
     */
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

    /**
     * Adds an assigned exercise to the database.
     *
     * @param programId id of the program
     * @param exerciseId id of the exercise
     */
    public void addAssignedExercise(Long programId, Long exerciseId) {
        try {
            exerciseDao.createAssignedExercise(programId, exerciseId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
    }

    /**
     * Gets exercise by id from the database.
     *
     * @param exerciseId id of the exercise
     * @return Optional<Exercise>
     */
    public Optional<Exercise> getExerciseById(Long exerciseId) {
        try {
            return exerciseDao.getById(exerciseId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
        return Optional.empty();
    }

    /**
     * Gets all exercises from the database.
     *
     * @return list of all exercises
     */
    public List<Exercise> getAllExercises() throws ServiceException {
        try {
            return exerciseDao.getAll();
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Adds a exercise to the database.
     *
     * @param newExerciseName name of the new exercise
     * @return boolean true if added successfully
     */
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

    /**
     * Deletes a exercise by id from the database.
     *
     * @param exerciseId id of the exercise
     */
    public void deleteExercise(Long exerciseId) throws ServiceException {
        try {
            exerciseDao.removeById(exerciseId);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Gets a sublist of exercises of given size.
     *
     * @param firstRow starting point of the sublist
     * @param rowCount number of rows to skip
     * @return sublist of exercises
     */
    public List<Exercise> getExerciseSublist(int firstRow, int rowCount) throws ServiceException {
        try {
            return exerciseDao.getSublist(firstRow, rowCount);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Counts the number of exercises in the database.
     *
     * @return number of exercises
     */
    public int getExerciseCount() throws ServiceException {
        try {
            return exerciseDao.getExerciseCount();
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
