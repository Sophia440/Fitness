package com.epam.web.command;

import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminAccountCommand implements Command{
    private static final String ADMIN_ACCOUNT_PAGE = "/view/admin_account.jsp";
    private static final String ADMIN_ID = "userId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long adminId = (Long) session.getAttribute(ADMIN_ID);
        return CommandResult.forward(ADMIN_ACCOUNT_PAGE);
    }
}
