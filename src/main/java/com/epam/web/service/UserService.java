package com.epam.web.service;

import com.epam.web.dao.MembershipDao;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.Membership;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
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

    public Optional<Membership> getLastMembership(Long clientId) throws ServiceException {
        List<Membership> memberships = getAllMemberships(clientId);
        if (memberships.isEmpty()) {
            return Optional.empty();
        } else {
            List<Membership> sortedMemberships = memberships.stream()
                    .sorted(Comparator.comparing(Membership::getEndDate))
                    .collect(Collectors.toList());
            Membership lastMembership = sortedMemberships.get(sortedMemberships.size() - 1);
            return Optional.of(lastMembership);
        }
    }

    private List<Membership> getAllMemberships(Long clientId) throws ServiceException {
        List<Membership> memberships;
        try {
            memberships = membershipDao.findMembershipsByClientId(clientId);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
        return memberships;
    }

    public boolean buyMembership(Long clientId, long monthsNumber) throws ServiceException {
        Membership membership = getNewMembershipInfo(clientId, monthsNumber);
        boolean isBought = false;
        try {
            membershipDao.create(membership);
            isBought = true;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
        return isBought;
    }

    private Membership getNewMembershipInfo(Long clientId, long monthsNumber) throws ServiceException {
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
        return membership;
    }

    public List<User> getUsersByRole(Role role) throws ServiceException {
        try {
            return userDao.getUsersByRole(role);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    public int getDiscount(Long clientId) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.getById(clientId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return user.getDiscount();
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
        return 0;
    }

    public void setDiscount(Long clientId, int newDiscount) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.getById(clientId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setDiscount(newDiscount);
                userDao.update(user);
            }
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
