package com.epam.web.command;

import com.epam.web.dao.UserDao;
import com.epam.web.dao.UserDaoImpl;
import com.epam.web.service.UserService;

public class CommandFactory {

//    public static final String LOGIN = "/view/login.jsp";
    public static final String MAIN = "/view/main.jsp";

    public Command create(String type) {
        switch (type) {
            case "login":
                return new LoginCommand(new UserService(new UserDaoImpl()));
            default:
                throw new IllegalArgumentException("Unknown type of Command " + type);
        }
    }
}
