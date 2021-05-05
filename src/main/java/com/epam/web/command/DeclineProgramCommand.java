package com.epam.web.command;

import com.epam.web.entity.Status;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeclineProgramCommand implements Command {
    private static final String CLIENT_ACCOUNT_PAGE = "/view/client_pages/client_account.jsp";
    private static final String CLIENT_ID = "userId";

    private ProgramService programService;

    public DeclineProgramCommand(ProgramService programService) {
        this.programService = programService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(CLIENT_ID);
        programService.setProgramStatus(clientId, Status.DECLINED);
        session.setAttribute("programStatus", Status.DECLINED.toString());
        session.removeAttribute("exerciseList");
        return CommandResult.forward(CLIENT_ACCOUNT_PAGE);
    }
}
