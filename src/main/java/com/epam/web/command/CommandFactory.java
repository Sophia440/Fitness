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

    public static final String ABOUT_PAGE = "/view/about.jsp";
    private static final String ERROR_PAGE = "/view/error_page.jsp";

    public static final String INSTRUCTOR_MAIN_PAGE = "/view/instructor_pages/instructor_main.jsp";
    private static final String INSTRUCTOR_ACCOUNT_PAGE = "/view/instructor_pages/instructor_account.jsp";

    public static final String CLIENT_MAIN_PAGE = "/view/client_pages/client_main.jsp";
    public static final String BUY_MEMBERSHIP_PAGE = "/view/client_pages/buy_membership.jsp";
    public static final String SERVICES_PAGE = "/view/client_pages/services.jsp";

    public static final String ADMIN_MAIN_PAGE = "/view/admin_pages/admin_main.jsp";
    private static final String ADMIN_ACCOUNT_PAGE = "/view/admin_pages/admin_account.jsp";

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
            case "clientMain":
                return new ShowPageCommand(CLIENT_MAIN_PAGE);
            case "instructorMain":
                return new ShowPageCommand(INSTRUCTOR_MAIN_PAGE);
            case "adminMain":
                return new ShowPageCommand(ADMIN_MAIN_PAGE);
            case "changeLanguage":
                return new ChangeLanguageCommand();
            case "clientAccount":
                return new ClientAccountCommand(new UserService(helper.createUserDao(), helper.createMembershipDao()), new ProgramService(helper.createProgramDao(), helper.createExerciseDao()), new DietService(helper.createDietDao(), helper.createDishDao()));
            case "adminAccount":
                return new ShowPageCommand(ADMIN_ACCOUNT_PAGE);
            case "adminActions":
                return new AdminActionsCommand(new UserService(helper.createUserDao(), helper.createMembershipDao()), new ProgramService(helper.createProgramDao(), helper.createExerciseDao()), new DietService(helper.createDietDao(), helper.createDishDao()));
            case "instructorActions":   //todo do sth with all helper.createDao params
                return new InstructorActionsCommand(new UserService(helper.createUserDao(), helper.createMembershipDao()), new ProgramService(helper.createProgramDao(), helper.createExerciseDao()), new DietService(helper.createDietDao(), helper.createDishDao()));
            case "instructorAccount":
                return new ShowPageCommand(INSTRUCTOR_ACCOUNT_PAGE);
            case "acceptProgram":   //todo refactor accept and decline commands into one changeStatus command
                return new AcceptProgramCommand(new ProgramService(helper.createProgramDao(), helper.createExerciseDao()));
            case "acceptDiet":
                return new AcceptDietCommand(new DietService(helper.createDietDao(), helper.createDishDao()));
            case "declineProgram":
                return new DeclineProgramCommand(new ProgramService(helper.createProgramDao(), helper.createExerciseDao()));
            case "declineDiet":
                return new DeclineDietCommand(new DietService(helper.createDietDao(), helper.createDishDao()));
            case "info":
                return new InfoCommand(new ProgramService(helper.createProgramDao(), helper.createExerciseDao()), new DietService(helper.createDietDao(), helper.createDishDao()));
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
