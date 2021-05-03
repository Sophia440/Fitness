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

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Membership> getLastMembership(Long clientId) {
        List<Membership> memberships = getAllMemberships(clientId);
        List<Membership> sortedMemberships = memberships.stream()
                .sorted(Comparator.comparing(Membership::getEndDate))
                .collect(Collectors.toList());
        Membership lastMembership = sortedMemberships.get(sortedMemberships.size() - 1);
        return Optional.of(lastMembership);
    }

    private List<Membership> getAllMemberships(Long clientId) {
        List<Membership> memberships = null;
        try {
            memberships = membershipDao.findMembershipsByClientId(clientId);
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
        return memberships;
    }

    public boolean buyMembership(Long clientId, long monthsNumber) {
        Membership membership = new Membership();
        membership.setClientId(clientId);
        LocalDate startDate;
        Optional<Membership> currentMembershipOptional = getLastMembership(clientId);
        if (currentMembershipOptional.isPresent()) {
            Membership currentMembership = currentMembershipOptional.get();
            LocalDate currentMembershipEndDate = currentMembership.getEndDate();
            startDate = currentMembershipEndDate.plusDays(1);
        } else {
            startDate = LocalDate.now();
        }
        membership.setStartDate(startDate);
        LocalDate endDate = startDate.plusMonths(monthsNumber);
        membership.setEndDate(endDate);
        membership.setPaymentDate(LocalDate.now());
        boolean isBought = false;
        try {
            membershipDao.create(membership);
            isBought = true;
        } catch (DaoException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
        return isBought;
    }
}
