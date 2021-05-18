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

public class DietService {
    public static final Logger LOGGER = LogManager.getLogger(DietService.class);
    private final DietDao dietDao;
    private final DishDao dishDao;

    public DietService(DietDao dietDao, DishDao dishDao) {
        this.dietDao = dietDao;
        this.dishDao = dishDao;
    }

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

    private void setAssignedDishes(Optional<Diet> diet) throws DaoException {
        Long dietId = diet.get().getId();
        List<Dish> dishes = dishDao.getDishesByDietId(dietId);
        diet.get().setDishes(dishes);
    }

    public boolean hasDiet(Long clientId) throws ServiceException {
        Optional<Diet> optionalDiet = getDiet(clientId);
        if (optionalDiet.isPresent()) {
            Diet diet = optionalDiet.get();
            Status dietStatus = diet.getStatus();
            return dietStatus != Status.DECLINED;
        }
        return false;
    }

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

    public void addAssignedDish(Long dietId, Long dishId) {
        try {
            dishDao.createAssignedDish(dietId, dishId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
    }

    public Optional<Dish> getDishById(Long dishId) {
        try {
            return dishDao.getById(dishId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
        return Optional.empty();
    }

    public List<Dish> getAllDishes() throws ServiceException {
        try {
            return dishDao.getAll();
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

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

    public void deleteDish(Long id) throws ServiceException {
        try {
            dishDao.removeById(id);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
