package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService userService;
    public static final String MAIN_PAGE = "/controller?command=main";
    public static final String LOGIN_PAGE = "/controller?command=login";
    public static final String ERROR_PAGE = "/controller?command=error";

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> optionalUser = userService.login(username, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("name", user.getLogin());
            request.getSession().setAttribute("password", user.getPassword());
            request.getSession().setAttribute("role", user.getRole());
            request.getSession().setAttribute("discount", user.getDiscount());
            return CommandResult.forward(MAIN_PAGE);
        } else {
            request.getSession().setAttribute("errorMessage", "Invalid username or password");
            return CommandResult.forward(ERROR_PAGE);
        }
    }
}
