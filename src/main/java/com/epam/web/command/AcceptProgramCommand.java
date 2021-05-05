package com.epam.web.command;

import com.epam.web.entity.Status;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AcceptProgramCommand implements Command {
    private static final String CLIENT_ACCOUNT_PAGE = "/view/client_pages/client_account.jsp";
    private static final String CLIENT_ID = "userId";

    private ProgramService programService;

    public AcceptProgramCommand(ProgramService programService) {
        this.programService = programService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(CLIENT_ID);
        programService.setProgramStatus(clientId, Status.ACTIVE);
        session.setAttribute("programStatus", Status.ACTIVE.toString());
        return CommandResult.forward(CLIENT_ACCOUNT_PAGE);
    }
}
