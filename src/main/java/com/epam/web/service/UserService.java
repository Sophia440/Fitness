package com.epam.web.service;

import com.epam.web.connection.ConnectionException;
import com.epam.web.connection.ConnectionPool;
import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.MembershipDao;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.Membership;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserService {
    public static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private final UserDao userDao;
    private final MembershipDao membershipDao;

    public UserService(UserDao userDao, MembershipDao membershipDao) {
        this.userDao = userDao;
        this.membershipDao = membershipDao;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    public Optional<Membership> getMembership(Long clientId) {
        Optional<Membership> membership = null;
        try {
            membership = membershipDao.findMembershipByClientId(clientId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
        return membership;
    }
}
