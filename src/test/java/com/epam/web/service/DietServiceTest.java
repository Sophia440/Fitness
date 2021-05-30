package com.epam.web.service;

import com.epam.web.connection.ConnectionException;
import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.DaoHelper;
import com.epam.web.entity.Diet;
import com.epam.web.entity.Dish;
import com.epam.web.entity.Meal;
import com.epam.web.entity.Status;
import com.epam.web.exception.ServiceException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DietServiceTest {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final Long VALID_ID = 2L;
    private static final Long INVALID_ID = -2L;
    private static final Long CLIENT_WITHOUT_DIET_ID = 6L;

    private static final List<Dish> DISH_LIST = Arrays.asList(
            new Dish(2L, "Omelette", Meal.BREAKFAST),
            new Dish(3L, "Greek yogurt", Meal.BREAKFAST),
            new Dish(4L, "Fruit snack", Meal.SNACK),
            new Dish(8L, "Baked chicken breasts", Meal.DINNER),
            new Dish(9L, "Brown rice", Meal.LUNCH),
            new Dish(13L, "Mixed nuts", Meal.SNACK),
            new Dish(6L, "Vegetable salad", Meal.SNACK),
            new Dish(15L, "Baked fish", Meal.LUNCH));
    private static final Diet DIET = new Diet(1L, 3L, 2L,
            LocalDate.of(2021, 5, 1), LocalDate.of(2021, 6, 1),
            Status.AWAITING_CLIENT_ANSWER, DISH_LIST);
    private static final Optional<Diet> EXPECTED_DIET = Optional.of(DIET);
    private static final Optional<Diet> EMPTY_DIET = Optional.empty();
    private static final Dish DISH = new Dish(2L, "Omelette", Meal.BREAKFAST);
    private static final Optional<Dish> EXPECTED_DISH = Optional.of(DISH);
    private static final Optional<Dish> EMPTY_DISH = Optional.empty();

    @Test
    public void testGetDiet_withValidClientId_shouldReturnOptionalDiet() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        DietService dietService = new DietService(helper.createDietDao(), helper.createDishDao());
        Optional<Diet> actual = dietService.getDiet(VALID_ID);
        Assert.assertEquals(EXPECTED_DIET, actual);
    }

    @Test
    public void testGetDiet_withInvalidClientId_shouldReturnOptionalEmpty() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        DietService dietService = new DietService(helper.createDietDao(), helper.createDishDao());
        Optional<Diet> actual = dietService.getDiet(INVALID_ID);
        Assert.assertEquals(EMPTY_DIET, actual);
    }

    @Test
    public void testHasDiet_withClientWithAwaitingDiet_shouldReturnTrue() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        DietService dietService = new DietService(helper.createDietDao(), helper.createDishDao());
        boolean actual = dietService.hasDiet(VALID_ID);
        Assert.assertTrue(actual);
    }

    @Test
    public void testHasDiet_withClientWithoutDiet_shouldReturnFalse() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        DietService dietService = new DietService(helper.createDietDao(), helper.createDishDao());
        boolean actual = dietService.hasDiet(CLIENT_WITHOUT_DIET_ID);
        Assert.assertFalse(actual);
    }

    @Test
    public void testGetDishById_withValidDishId_shouldReturnOptionalDish() throws ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        DietService dietService = new DietService(helper.createDietDao(), helper.createDishDao());
        Optional<Dish> actual = dietService.getDishById(VALID_ID);
        Assert.assertEquals(EXPECTED_DISH, actual);
    }

    @Test
    public void testGetDishById_withInvalidDishId_shouldReturnOptionalEmpty() throws ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        DietService dietService = new DietService(helper.createDietDao(), helper.createDishDao());
        Optional<Dish> actual = dietService.getDishById(INVALID_ID);
        Assert.assertEquals(EMPTY_DISH, actual);
    }

}
