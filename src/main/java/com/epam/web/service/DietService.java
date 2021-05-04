package com.epam.web.service;

import com.epam.web.dao.DietDao;
import com.epam.web.dao.DishDao;
import com.epam.web.entity.Diet;
import com.epam.web.entity.Dish;
import com.epam.web.entity.Status;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class DietService {
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
}
