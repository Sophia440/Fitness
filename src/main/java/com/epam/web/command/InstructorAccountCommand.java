package com.epam.web.command;

import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InstructorAccountCommand implements Command{
    private static final String INSTRUCTOR_ACCOUNT_PAGE = "/view/instructor_account.jsp";
    private static final String INSTRUCTOR_ID = "userId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long instructorId = (Long) session.getAttribute(INSTRUCTOR_ID);
        return CommandResult.forward(INSTRUCTOR_ACCOUNT_PAGE);
    }
}
