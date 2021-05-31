package com.epam.web.service;

import com.epam.web.dao.DietDao;
import com.epam.web.dao.DishDao;
import com.epam.web.entity.Diet;
import com.epam.web.entity.Dish;
import com.epam.web.entity.Meal;
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
 * This class handles operations with DietDao and DishDao classes.
 *
 */
public class DietService {
    public static final Logger LOGGER = LogManager.getLogger(DietService.class);
    private final DietDao dietDao;
    private final DishDao dishDao;

    public DietService(DietDao dietDao, DishDao dishDao) {
        this.dietDao = dietDao;
        this.dishDao = dishDao;
    }

    /**
     * Gets a Diet from the database.
     *
     * @param clientId id of the client
     * @return Optional<Diet>
     */
    public Optional<Diet> getDiet(Long clientId) throws ServiceException {
        try {
            Optional<Diet> optionalDiet = dietDao.findDietByClientId(clientId);
            if (optionalDiet.isPresent()) {
                setAssignedDishes(optionalDiet);
            }
            return optionalDiet;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Gets the assigned dishes from the database.
     *
     * @param diet to set dishes
     */
    private void setAssignedDishes(Optional<Diet> diet) throws DaoException {
        Long dietId = diet.get().getId();
        List<Dish> dishes = dishDao.getDishesByDietId(dietId);
        diet.get().setDishes(dishes);
    }

    /**
     * Checks if client has an active or awaiting diet.
     *
     * @param clientId id of the client
     * @return boolean true if client has a diet
     */
    public boolean hasDiet(Long clientId) throws ServiceException {
        Optional<Diet> optionalDiet = getDiet(clientId);
        if (optionalDiet.isPresent()) {
            Diet diet = optionalDiet.get();
            Status dietStatus = diet.getStatus();
            return dietStatus != Status.DECLINED;
        }
        return false;
    }

    /**
     * Sets diet status.
     *
     * @param clientId id of the client
     * @param status to set
     */
    public void setDietStatus(Long clientId, Status status) throws ServiceException {
        try {
            Optional<Diet> optionalDiet = getDiet(clientId);
            if (optionalDiet.isPresent()) {
                Diet diet = optionalDiet.get();
                diet.setStatus(status);
                dietDao.update(diet);
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Adds a new diet to the database.
     *
     * @param clientId id of the client
     * @param instructorId id of the instructor
     * @param dietDishList list of dishes in new diet
     * @param startDate starting date
     * @param endDate ending date
     * @return boolean true if diet added successfully
     */
    public boolean addDiet(Long clientId, Long instructorId, String[] dietDishList, String startDate, String endDate) throws ServiceException {
        List<Long> dishesIds = Arrays.stream(dietDishList)
                .map(Long::valueOf)
                .collect(Collectors.toList());
        List<Dish> newDishList = dishesIds.stream()
                .map(dishId -> {
                    Optional<Dish> optionalDish = getDishById(dishId);
                    return optionalDish.orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Diet newDiet = new Diet();
        newDiet.setDishes(newDishList);
        newDiet.setInstructorId(instructorId);
        newDiet.setClientId(clientId);
        LocalDate startDateLocal = LocalDate.parse(startDate);
        LocalDate endDateLocal = LocalDate.parse(endDate);
        newDiet.setStartDate(startDateLocal);
        newDiet.setEndDate(endDateLocal);
        newDiet.setStatus(Status.AWAITING_CLIENT_ANSWER);
        try {
            dietDao.create(newDiet);
            Optional<Diet> optionalDiet = dietDao.findDietByClientIdAndStartEndDates(clientId, startDateLocal, endDateLocal);
            if (optionalDiet.isPresent()) {
                Long dietId = optionalDiet.get().getId();
                newDishList.stream().forEach(dish -> {
                    addAssignedDish(dietId, dish.getId());
                });
            }
            return true;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Adds an assigned dish to the database.
     *
     * @param dietId id of the diet
     * @param dishId id of the dish
     */
    public void addAssignedDish(Long dietId, Long dishId) {
        try {
            dishDao.createAssignedDish(dietId, dishId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
    }

    /**
     * Gets dish by id from the database.
     *
     * @param dishId id of the dish
     * @return Optional<Dish>
     */
    public Optional<Dish> getDishById(Long dishId) {
        try {
            return dishDao.getById(dishId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
        return Optional.empty();
    }

    /**
     * Gets all dishes from the database.
     *
     * @return list of all dishes
     */
    public List<Dish> getAllDishes() throws ServiceException {
        try {
            return dishDao.getAll();
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Adds a dish to the database.
     *
     * @param newDishName name of the new dish
     * @param newDishMeal meal of the new dish
     * @return boolean true if added successfully
     */
    public boolean addDish(String newDishName, String newDishMeal) throws ServiceException {
        Dish dish = new Dish();
        dish.setName(newDishName);
        dish.setMeal(Meal.valueOf(newDishMeal.toUpperCase()));
        try {
            dishDao.create(dish);
            return true;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Deletes a dish by id from the database.
     *
     * @param dishId id of the dish
     */
    public void deleteDish(Long dishId) throws ServiceException {
        try {
            dishDao.removeById(dishId);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Gets a sublist of dishes of given size.
     *
     * @param firstRow starting point of the sublist
     * @param rowCount number of rows to skip
     * @return sublist of dishes
     */
    public List<Dish> getDishSublist(int firstRow, int rowCount) throws ServiceException {
        try {
            return dishDao.getSublist(firstRow, rowCount);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Counts the number of dishes in the database.
     *
     * @return number of dishes
     */
    public int getDishCount() throws ServiceException {
        try {
            return dishDao.getDishCount();
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
