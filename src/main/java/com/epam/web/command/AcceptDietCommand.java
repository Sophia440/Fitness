package com.epam.web.command;

import com.epam.web.entity.Status;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.DietService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AcceptDietCommand implements Command {
    private static final String CLIENT_ACCOUNT_PAGE = "/view/client_pages/client_account.jsp";
    private static final String CLIENT_ID = "userId";

    private DietService dietService;

    public AcceptDietCommand(DietService dietService) {
        this.dietService = dietService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(CLIENT_ID);
        dietService.setDietStatus(clientId, Status.ACTIVE);
        session.setAttribute("dietStatus", Status.ACTIVE.toString());
        return CommandResult.forward(CLIENT_ACCOUNT_PAGE);
    }
}
