package com.epam.web.command;

import com.epam.web.connection.ConnectionException;
import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.DaoHelper;
import com.epam.web.service.UserService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CommandFactory {

    //    public static final String LOGIN = "/view/login.jsp";
    public static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);
    public static final String MAIN = "/view/main.jsp";
    private ConnectionPool pool;
    private DaoHelper helper;

    public CommandFactory() {
        pool = ConnectionPool.getInstance();
        try {
            helper = new DaoHelper(pool.getConnection());
        } catch (ConnectionException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
    }

    public Command create(String type) {
        switch (type) {
            case "login":
                return new LoginCommand(new UserService(helper.createUserDao()));
            default:
                throw new IllegalArgumentException("Unknown type of Command " + type);
        }
    }
}
