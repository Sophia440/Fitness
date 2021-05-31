package com.epam.web.command;

import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This command handles buying a new membership.
 *
 */
public class BuyMembershipCommand implements Command {
    private final UserService userService;
    private static final String CLIENT_ID = "userId";
    private static final String MESSAGE_PAGE = "/view/fragments/message_page.jsp";
    public static final String ERROR_PAGE = "/controller?command=error";
    private static final String MESSAGE = "message";
    private static final String ERROR_MESSAGE = "errorMessage";

    public BuyMembershipCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles membership purchase.
     *
     * @param request the request from Controller
     * @param response the response from Controller
     * @return CommandResult with the message or error page
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(CLIENT_ID);
        String membershipDuration = request.getParameter("membershipDuration");
        long monthsNumber = Long.parseLong(membershipDuration);
        boolean isBought = userService.buyMembership(clientId, monthsNumber);
        if (isBought) {
            session.setAttribute(MESSAGE, "paymentConfirmed");
            return CommandResult.forward(MESSAGE_PAGE);
        } else {
            session.setAttribute(ERROR_MESSAGE, "Membership purchase was not successful!");
            return CommandResult.forward(ERROR_PAGE);
        }
    }
}
