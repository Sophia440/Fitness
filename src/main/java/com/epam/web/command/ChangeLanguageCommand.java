package com.epam.web.command;

import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    public static final String LOGIN_PAGE = "/controller?command=loginPage";
    public static final String CLIENT_MAIN_PAGE = "/controller?command=clientMain";
    public static final String INSTRUCTOR_MAIN_PAGE = "/controller?command=instructorMain";
    public static final String ADMIN_MAIN_PAGE = "/controller?command=adminMain";
    public static final String ERROR_PAGE = "/controller?command=error";
    private static final String LANGUAGE = "language";
    private static final String LOCAL = "local";

    public ChangeLanguageCommand() {
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String language = request.getParameter(LANGUAGE);
        HttpSession session = request.getSession();
        session.setAttribute(LOCAL, language);
        Object userRole = session.getAttribute("role");
        if (userRole != null) {
            String userRoleString = userRole.toString();
            switch (userRoleString.toUpperCase()) {
                case "ADMIN":
                    return CommandResult.forward(ADMIN_MAIN_PAGE);
                case "CLIENT":
                    return CommandResult.forward(CLIENT_MAIN_PAGE);
                case "INSTRUCTOR":
                    return CommandResult.forward(INSTRUCTOR_MAIN_PAGE);
                default:
                    session.setAttribute("errorMessage", "User role identification error");
                    return CommandResult.forward(ERROR_PAGE);
            }
        }
        return CommandResult.forward(LOGIN_PAGE);
    }
}
