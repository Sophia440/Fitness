package com.epam.web.service;

import com.epam.web.connection.ConnectionException;
import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.DaoHelper;
import com.epam.web.entity.Membership;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final Long VALID_ID = 2L;
    private static final Long INVALID_ID = -2L;
    private static final int EXPECTED_DISCOUNT = 20;
    private static final List<User> USER_LIST = Collections.singletonList(new User(1L, "admin", "admin", Role.ADMIN, 0));
    private static final Membership MEMBERSHIP = new Membership(1L, 2L,
            LocalDate.of(2021, 5, 1), LocalDate.of(2021, 6, 1),
            LocalDate.of(2021, 5, 1));
    private static final Optional<Membership> EMPTY_MEMBERSHIP = Optional.empty();
    private static final Optional<Membership> EXPECTED_MEMBERSHIP = Optional.of(MEMBERSHIP);

    @Test
    public void testGetLastMembership_withValidClientId_shouldReturnOptionalMembership() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        UserService userService = new UserService(helper.createUserDao(), helper.createMembershipDao());
        Optional<Membership> actual = userService.getLastMembership(VALID_ID);
        Assert.assertEquals(EXPECTED_MEMBERSHIP, actual);
    }

    @Test
    public void testGetLastMembership_withInvalidClientId_shouldReturnOptionalEmpty() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        UserService userService = new UserService(helper.createUserDao(), helper.createMembershipDao());
        Optional<Membership> actual = userService.getLastMembership(INVALID_ID);
        Assert.assertEquals(EMPTY_MEMBERSHIP, actual);
    }

    @Test
    public void testGetDiscount_withValidClientId_shouldReturnClientsDiscount() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        UserService userService = new UserService(helper.createUserDao(), helper.createMembershipDao());
        int actual = userService.getDiscount(VALID_ID);
        Assert.assertEquals(EXPECTED_DISCOUNT, actual);
    }

    @Test
    public void testGetDiscount_withInvalidClientId_shouldReturnZero() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        UserService userService = new UserService(helper.createUserDao(), helper.createMembershipDao());
        int actual = userService.getDiscount(INVALID_ID);
        Assert.assertEquals(0, actual);
    }

    @Test
    public void testGetUsersByRole_withValidRole_shouldReturnUserList() throws ServiceException, ConnectionException {
        DaoHelper helper = new DaoHelper(pool.getConnection());
        UserService userService = new UserService(helper.createUserDao(), helper.createMembershipDao());
        List<User> actual = userService.getUsersByRole(Role.ADMIN);
        Assert.assertEquals(USER_LIST, actual);
    }

}
