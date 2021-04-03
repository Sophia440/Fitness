package com.epam.web;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private final CommandFactory commandFactory = new CommandFactory();

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
        Command command = commandFactory.create(commandType);
        String view = null;
        try {
            view = command.execute(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(view).forward(request, response);
    }
}
