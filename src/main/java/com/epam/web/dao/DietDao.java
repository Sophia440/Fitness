package com.epam.web.dao;

import com.epam.web.entity.Diet;

import java.util.Optional;

public interface DietDao extends Dao<Diet> {

    Optional<Diet> findDietByClientId(Long clientId);
}
