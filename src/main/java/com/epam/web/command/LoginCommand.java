package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService userService;
    public static final String MAIN_PAGE = "/controller?command=main";
    public static final String ERROR_PAGE = "/controller?command=error";

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> optionalUser = userService.login(username, password);
        HttpSession session = request.getSession(false);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            session.setAttribute("userId", user.getId());
            session.setAttribute("name", user.getLogin());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("role", user.getRole());
            BigDecimal discount = user.getDiscount();
            if (discount == null) {
                session.setAttribute("discount", BigDecimal.ZERO);
            } else {
                session.setAttribute("discount", discount);
            }
            return CommandResult.forward(MAIN_PAGE);
        } else {
            session.setAttribute("errorMessage", "Invalid username or password");
            return CommandResult.forward(ERROR_PAGE);
        }
    }
}
