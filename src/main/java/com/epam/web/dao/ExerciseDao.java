package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Exercise;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.ExerciseRowMapper;

import java.util.List;
import java.util.Optional;

/**
 * Data access object class for Exercise entity. Overrides all CRUD operations.
 *
 */
public class ExerciseDao extends AbstractDao<Exercise> implements Dao<Exercise> {
    public static final String TABLE_NAME = "exercise";
    public static final String COUNT_EXERCISES = "SELECT COUNT(id) FROM exercise";
    public static final String COUNT_COLUMN_NAME = "COUNT(id)";
    public static final String SELECT_SUBLIST = "SELECT * FROM exercise ORDER BY id LIMIT ? OFFSET ?";
    public static final String FIND_BY_ID = "SELECT * FROM exercise WHERE id = ?";
    public static final String FIND_BY_PROGRAM_ID = "SELECT exercise.id, exercise.name FROM exercise INNER JOIN assigned_exercise ON exercise.id=assigned_exercise.exercise_id WHERE program_id = ?";
    private static final String CREATE = "INSERT INTO exercise (name) VALUE (?)";
    private static final String CREATE_ASSIGNED_EXERCISE = "INSERT INTO assigned_exercise (program_id, exercise_id) VALUE (?, ?)";
    private static final String REMOVE_BY_ID = "DELETE FROM exercise WHERE id = ?";
    private static final String REMOVE_BY_ID_FROM_ASSIGNED_EXERCISE = "DELETE FROM assigned_exercise WHERE exercise_id = ?";

    public ExerciseDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public void create(Exercise item) throws DaoException {
        executeUpdate(CREATE, item.getName());
    }

    @Override
    public Optional<Exercise> update(Exercise item) throws DaoException {
        return Optional.empty();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Exercise> getById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new ExerciseRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_ID_FROM_ASSIGNED_EXERCISE, id);
        executeUpdate(REMOVE_BY_ID, id);
    }

    /**
     * Searches for exercises with given program id in the database.
     *
     * @param programId id of the program
     * @return list of exercises
     */
    public List<Exercise> getExercisesByProgramId(Long programId) throws DaoException {
        return executeQuery(FIND_BY_PROGRAM_ID, new ExerciseRowMapper(), programId);
    }

    /**
     * Creates an assigned exercise database entry that connects an exercise with a program,
     * that it is assigned to.
     *
     * @param programId id of the program
     * @param exerciseId id of the exercise
     */
    public void createAssignedExercise(Long programId, Long exerciseId) throws DaoException {
        executeUpdate(CREATE_ASSIGNED_EXERCISE, programId, exerciseId);
    }

    /**
     * Gets a sublist of exercises of given size.
     *
     * @param firstRow starting point of the sublist
     * @param rowCount number of rows to skip
     * @return sublist of exercises
     */
    public List<Exercise> getSublist(int firstRow, int rowCount) throws DaoException {
        return executeQuery(SELECT_SUBLIST, new ExerciseRowMapper(), rowCount, firstRow);
    }

    /**
     * Counts the number of exercises in the database.
     *
     * @return number of exercises
     */
    public int getExerciseCount() throws DaoException {
        return executeQuery(COUNT_EXERCISES, COUNT_COLUMN_NAME);
    }
}
