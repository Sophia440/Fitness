package com.epam.web;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandResult;
import com.epam.web.connection.ConnectionPool;
import com.epam.web.exception.ConnectionException;
import com.epam.web.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private final CommandFactory commandFactory = new CommandFactory();
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    private static final String ERROR_PAGE = "/view/error_page.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandType = request.getParameter("command");
        Command command;
        String page;

        boolean isRedirect = false;
        try {
            command = commandFactory.create(commandType);
            CommandResult result = command.execute(request, response);
            page = result.getPage();
            isRedirect = result.isRedirect();
        } catch (ServiceException exception) {
            request.setAttribute("errorMessage", exception.getMessage());
            LOGGER.error(exception.getMessage(), exception);
            page = ERROR_PAGE;
        }
        if (!isRedirect) {
            forward(request, response, page);
        } else {
            redirect(request, response, page);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
        response.sendRedirect(request.getContextPath() + page);
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            ConnectionPool.getInstance().closeAllConnections();
        } catch (ConnectionException exception) {
            LOGGER.error(exception.getMessage(), exception);
        }
    }
}
