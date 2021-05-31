package com.epam.web.command;

import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command handles showing a page.
 *
 */
public class ShowPageCommand implements Command {
    private final String page;

    public ShowPageCommand(final String page) {
        this.page = page;
    }

    /**
     * Handles chosen info page section.
     *
     * @param request the request from Controller
     * @param response the response from Controller
     * @return CommandResult with a page from the constructor
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResult.forward(page);
    }
}