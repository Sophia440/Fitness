package com.epam.web.dao;

import com.epam.web.entity.AssignedDish;

import java.util.List;

public interface AssignedDishDao extends Dao<AssignedDish> {

    List<AssignedDish> findAllAssignedDishesByDietId(Long dietId);
}
