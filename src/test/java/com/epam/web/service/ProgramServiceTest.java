package com.epam.web.service;

import com.epam.web.connection.ConnectionException;
import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.DaoHelper;
import com.epam.web.entity.Exercise;
import com.epam.web.entity.Program;
import com.epam.web.entity.Status;
import com.epam.web.exception.ServiceException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProgramServiceTest {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final Long VALID_ID = 2L;
    private static final Long INVALID_ID = -2L;
    private static final Long CLIENT_WITHOUT_PROGRAM_ID = 6L;

    private static final List<Exercise> EXERCISE_LIST = Arrays.asList(
            new Exercise(1L, "Warm-up"),
            new Exercise(3L, "Treadmill, low speed, 10 minutes"),
            new Exercise(9L, "Bodyweight squats, 10"),
            new Exercise(10L, "Push-ups (on knees or regular), 10"),
            new Exercise(11L, "One arm dumbbell rows (10 per arm)"),
            new Exercise(2L, "Final stretching"));
    private static final Program PROGRAM = new Program(1L, 3L, 2L,
            LocalDate.of(2021, 5, 1), LocalDate.of(2021, 6, 1),
            Status.AWAITING_CLIENT_ANSWER, EXERCISE_LIST);
    private static final Optional<Program> EXPECTED_PROGRAM = Optional.of(PROGRAM);
    private static final Optional<Program> EMPTY_PROGRAM = Optional.empty();
    private static final Exercise EXERCISE = new Exercise(2L, "Final stretching");
    private static final Optional<Exercise> EXPECTED_EXERCISE = Optional.of(EXERCISE);
    private static final Optional<Exercise> EMPTY_EXERCISE = Optional.empty();


    @Test
    public void testGetProgram_withValidClientId_shouldReturnOptionalProgram() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        ProgramService programService = new ProgramService(helper.createProgramDao(), helper.createExerciseDao());
        Optional<Program> actual = programService.getProgram(VALID_ID);
        Assert.assertEquals(EXPECTED_PROGRAM, actual);
    }

    @Test
    public void testGetProgram_withInvalidClientId_shouldReturnOptionalEmpty() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        ProgramService programService = new ProgramService(helper.createProgramDao(), helper.createExerciseDao());
        Optional<Program> actual = programService.getProgram(INVALID_ID);
        Assert.assertEquals(EMPTY_PROGRAM, actual);
    }

    @Test
    public void testHasProgram_withClientWithAwaitingProgram_shouldReturnTrue() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        ProgramService programService = new ProgramService(helper.createProgramDao(), helper.createExerciseDao());
        boolean actual = programService.hasProgram(VALID_ID);
        Assert.assertTrue(actual);
    }

    @Test
    public void testHasProgram_withClientWithoutProgram_shouldReturnFalse() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        ProgramService programService = new ProgramService(helper.createProgramDao(), helper.createExerciseDao());
        boolean actual = programService.hasProgram(CLIENT_WITHOUT_PROGRAM_ID);
        Assert.assertFalse(actual);
    }

    @Test
    public void testGetExerciseById_withValidExerciseId_shouldReturnOptionalExercise() throws ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        ProgramService programService = new ProgramService(helper.createProgramDao(), helper.createExerciseDao());
        Optional<Exercise> actual = programService.getExerciseById(VALID_ID);
        Assert.assertEquals(EXPECTED_EXERCISE, actual);
    }

    @Test
    public void testGetExerciseById_withInvalidExerciseId_shouldReturnOptionalEmpty() throws ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        ProgramService programService = new ProgramService(helper.createProgramDao(), helper.createExerciseDao());
        Optional<Exercise> actual = programService.getExerciseById(INVALID_ID);
        Assert.assertEquals(EMPTY_EXERCISE, actual);
    }

}
