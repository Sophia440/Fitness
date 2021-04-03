package com.epam.web.service;

import com.epam.web.dao.UserDao;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.Optional;

public class UserService {
    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
