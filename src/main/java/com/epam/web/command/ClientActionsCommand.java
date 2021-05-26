package com.epam.web.command;

import com.epam.web.entity.Status;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.DietService;
import com.epam.web.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClientActionsCommand implements Command{
    private static final String CLIENT_DIET_PAGE = "/view/client_pages/client_diet.jsp";
    private static final String CLIENT_PROGRAM_PAGE = "/view/client_pages/client_program.jsp";
    private static final String CLIENT_ID = "userId";
    private static final String ACTION = "action";

    private DietService dietService;
    private ProgramService programService;

    public ClientActionsCommand(DietService dietService, ProgramService programService) {
        this.dietService = dietService;
        this.programService = programService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String action = request.getParameter(ACTION);
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(CLIENT_ID);
        switch (action) {
            case "acceptProgram":
                programService.setProgramStatus(clientId, Status.ACTIVE);
                session.setAttribute("programStatus", Status.ACTIVE.toString());
                return CommandResult.forward(CLIENT_PROGRAM_PAGE);
            case "declineProgram":
                programService.setProgramStatus(clientId, Status.DECLINED);
                session.setAttribute("programStatus", Status.DECLINED.toString());
                session.removeAttribute("exerciseList");
                return CommandResult.forward(CLIENT_PROGRAM_PAGE);
            case "acceptDiet":
                dietService.setDietStatus(clientId, Status.ACTIVE);
                session.setAttribute("dietStatus", Status.ACTIVE.toString());
                return CommandResult.forward(CLIENT_DIET_PAGE);
            case "declineDiet":
                dietService.setDietStatus(clientId, Status.DECLINED);
                session.setAttribute("dietStatus", Status.DECLINED.toString());
                session.removeAttribute("dishList");
                return CommandResult.forward(CLIENT_DIET_PAGE);
            default:
                throw new IllegalArgumentException("Unknown type of Command " + action);
        }
    }
}
