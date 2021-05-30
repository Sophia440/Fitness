package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command handles logging out and invalidating the session.
 *
 */
public class LogoutCommand implements Command {
    private static final String LOGIN_PAGE = "/index.jsp";

    /**
     * Handles chosen info page section.
     *
     * @param request the request from Controller
     * @param response the response from Controller
     * @return CommandResult with the login page
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return CommandResult.forward(LOGIN_PAGE);
    }
}
