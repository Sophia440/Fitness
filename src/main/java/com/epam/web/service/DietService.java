package com.epam.web.service;

import com.epam.web.dao.AssignedDishDao;
import com.epam.web.dao.DietDao;
import com.epam.web.dao.DishDao;
import com.epam.web.entity.*;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DietService {
    private final DietDao dietDao;
    private final DishDao dishDao;
    private final AssignedDishDao assignedDishDao;

    public DietService(DietDao dietDao, DishDao dishDao, AssignedDishDao assignedDishDao) {
        this.dietDao = dietDao;
        this.dishDao = dishDao;
        this.assignedDishDao = assignedDishDao;
    }

    public Optional<Diet> getDiet(Long clientId) throws ServiceException {
        try {
            Optional<Diet> optionalDiet = dietDao.findDietByClientId(clientId);
            if (optionalDiet.isPresent()) {
                optionalDiet = setAssignedDishes(optionalDiet);
            }
            return optionalDiet;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    private Optional<Diet> setAssignedDishes(Optional<Diet> diet) throws DaoException {
        Optional<Diet> updatedDiet = diet;
        Long dietId = diet.get().getId();
        List<AssignedDish> assignedDishes = assignedDishDao.findAllAssignedDishesByDietId(dietId);
        List<Dish> dishes = new ArrayList<>();
        for (AssignedDish assignedDish : assignedDishes) {
            Optional<Dish> dish = dishDao.getById(assignedDish.getDishId());
            if (dish.isPresent()) {
                dishes.add(dish.get());
            }
        }
        updatedDiet.get().setDishes(dishes);
        return updatedDiet;
    }
}
