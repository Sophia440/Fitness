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

/**
 * This class handles operations with UserDao and MembershipDao classes.
 *
 */
public class UserService {
    private final UserDao userDao;
    private final MembershipDao membershipDao;

    public UserService(UserDao userDao, MembershipDao membershipDao) {
        this.userDao = userDao;
        this.membershipDao = membershipDao;
    }

    /**
     * Checks if user's credentials are in the database.
     *
     * @param login of the user
     * @param password of the user
     * @return Optional<User>
     */
    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Gets client's last membership from the database.
     *
     * @param clientId id of the client
     * @return Optional<Membership>
     */
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

    /**
     * Gets all client's memberships from the database.
     *
     * @param clientId id of the client
     * @return list of all clients memberships
     */
    private List<Membership> getAllMemberships(Long clientId) throws ServiceException {
        List<Membership> memberships;
        try {
            memberships = membershipDao.findMembershipsByClientId(clientId);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
        return memberships;
    }

    /**
     * Handles membership purchase.
     *
     * @param clientId id of the client
     * @param monthsNumber membership duration
     * @return boolean true if bought successfully
     */
    public boolean buyMembership(Long clientId, long monthsNumber) throws ServiceException {
        Membership membership = getNewMembershipInfo(clientId, monthsNumber);
        try {
            membershipDao.create(membership);
            return true;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Creates a new membership when it is purchased.
     *
     * @param clientId id of the client
     * @param monthsNumber membership duration
     * @return membership
     */
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

    /**
     * Gets a list of users by a given role from the database.
     *
     * @param role given role
     * @return list of users
     */
    public List<User> getUsersByRole(Role role) throws ServiceException {
        try {
            return userDao.getUsersByRole(role);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * Gets discount by client id from the database.
     *
     * @param clientId id of the client
     * @return discount
     */
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

    /**
     * Sets a discount to a client.
     *
     * @param clientId id of the client
     * @param newDiscount discount to add
     */
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
