package com.epam.web.command;

import com.epam.web.connection.ConnectionException;
import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.DaoHelper;
import com.epam.web.service.DietService;
import com.epam.web.service.ProgramService;
import com.epam.web.service.UserService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CommandFactory {

    public static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);
    public static final String MAIN_PAGE = "/view/main.jsp";
    public static final String ACCOUNT_PAGE = "/view/account.jsp";
    public static final String BUY_MEMBERSHIP_PAGE = "/view/buy_membership.jsp";
    public static final String TRAINERS_PAGE = "/view/trainers.jsp";
    public static final String SERVICES_PAGE = "/view/services.jsp";
    public static final String ABOUT_PAGE = "/view/about.jsp";
    private static final String ERROR_PAGE = "/view/error_page.jsp";
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
                return new LoginCommand(new UserService(helper.createUserDao(), helper.createMembershipDao()));
            case "main":
                return new ShowPageCommand(MAIN_PAGE);
            case "changeLanguage":
                return new ChangeLanguageCommand();
            case "account":
                return new ClientAccountCommand(new UserService(helper.createUserDao(), helper.createMembershipDao()), new ProgramService(helper.createProgramDao(), helper.createExerciseDao()), new DietService(helper.createDietDao(), helper.createDishDao()));
            case "trainers":
                return new ShowPageCommand(TRAINERS_PAGE);
            case "services":
                return new ShowPageCommand(SERVICES_PAGE);
            case "chooseDuration":
                return new ShowPageCommand(BUY_MEMBERSHIP_PAGE);
            case "buyMembership":
                return new BuyMembershipCommand(new UserService(helper.createUserDao(), helper.createMembershipDao()));
            case "about":
                return new ShowPageCommand(ABOUT_PAGE);
            case "error":
                return new ShowPageCommand(ERROR_PAGE);
            case "logout":
                return new LogoutCommand();
            default:
                throw new IllegalArgumentException("Unknown type of Command " + type);
        }
    }
}
