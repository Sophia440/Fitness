package com.epam.web.command;

import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuyMembershipCommand implements Command {
    private final UserService userService;
    private static final String CLIENT_ID = "userId";
    private static final String PURCHASE_CONFIRMED = "/view/purchase_confirmed.jsp";
    public static final String ERROR_PAGE = "/controller?command=error";

    public BuyMembershipCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(CLIENT_ID);
        String membershipDuration = request.getParameter("membershipDuration");
        long monthsNumber = Long.parseLong(membershipDuration);
        boolean isBought = userService.buyMembership(clientId, monthsNumber);
        if (isBought) {
            return CommandResult.forward(PURCHASE_CONFIRMED);
        } else {
            session.setAttribute("errorMessage", "Membership purchase was not successful!");
            return CommandResult.forward(ERROR_PAGE);
        }
    }
}
