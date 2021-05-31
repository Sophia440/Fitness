package com.epam.web.command;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * This command checks username and password and logs in the user.
 *
 */
public class LoginCommand implements Command {
    private final UserService userService;
    public static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    public static final String CLIENT_MAIN_PAGE = "/controller?command=clientMain";
    public static final String INSTRUCTOR_MAIN_PAGE = "/controller?command=instructorMain";
    public static final String ADMIN_MAIN_PAGE = "/controller?command=adminMain";
    public static final String ERROR_PAGE = "/controller?command=error";

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles checking users credentials.
     *
     * @param request the request from Controller
     * @param response the response from Controller
     * @return CommandResult with the main page
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> optionalUser = null;
        try {
            optionalUser = userService.login(username, password);
        } catch (ServiceException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
        HttpSession session = request.getSession(false);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            session.setAttribute("userId", user.getId());
            session.setAttribute("name", user.getLogin());
            session.setAttribute("password", user.getPassword());
            Role userRole = user.getRole();
            String userRoleString = userRole.name();
            session.setAttribute("role", userRoleString);
            int discount = user.getDiscount();
            session.setAttribute("discount", discount);
            switch (userRole) {
                case ADMIN:
                    return CommandResult.forward(ADMIN_MAIN_PAGE);
                case CLIENT:
                    return CommandResult.forward(CLIENT_MAIN_PAGE);
                case INSTRUCTOR:
                    return CommandResult.forward(INSTRUCTOR_MAIN_PAGE);
                default:
                    session.setAttribute("errorMessage", "Invalid user role");
                    return CommandResult.forward(ERROR_PAGE);
            }
        } else {
            session.setAttribute("errorMessage", "Invalid username or password");
            return CommandResult.forward(ERROR_PAGE);
        }
    }
}
