package com.epam.web.command;

import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private static final String MAIN_PAGE_COMMAND = "/controller?command=main";
    private static final String LANGUAGE = "language";
    private static final String LOCAL = "local";

    public ChangeLanguageCommand() {
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String language = request.getParameter(LANGUAGE);
        HttpSession session = request.getSession();
        session.setAttribute(LOCAL, language);
        return CommandResult.forward(MAIN_PAGE_COMMAND);
    }
}
