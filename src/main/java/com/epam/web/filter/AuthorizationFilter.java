package com.epam.web.filter;

import com.epam.web.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static java.util.Objects.nonNull;

public class AuthorizationFilter implements Filter {
    private static final Map<String, List<Role>> COMMANDS_WITH_ROLES = new HashMap<>();
    private static final List<Role> ALL_ROLES = Arrays.asList(Role.ADMIN, Role.INSTRUCTOR, Role.CLIENT, Role.UNKNOWN);
    private static final List<Role> CLIENT = Collections.singletonList(Role.CLIENT);
    private static final List<Role> INSTRUCTOR = Collections.singletonList(Role.INSTRUCTOR);
    private static final List<Role> ADMIN = Collections.singletonList(Role.ADMIN);
    private static final List<Role> INSTRUCTOR_ADMIN = Arrays.asList(Role.INSTRUCTOR, Role.ADMIN);
    private FilterConfig filterConfig;

    public AuthorizationFilter() {
        if (COMMANDS_WITH_ROLES.isEmpty()) {
            COMMANDS_WITH_ROLES.put("login", ALL_ROLES);
            COMMANDS_WITH_ROLES.put("logout", ALL_ROLES);
            COMMANDS_WITH_ROLES.put("error", ALL_ROLES);
            COMMANDS_WITH_ROLES.put("about", ALL_ROLES);
            COMMANDS_WITH_ROLES.put("changeLanguage", ALL_ROLES);
            COMMANDS_WITH_ROLES.put("info", ALL_ROLES);

            COMMANDS_WITH_ROLES.put("clientMain", CLIENT);
            COMMANDS_WITH_ROLES.put("clientAccount", CLIENT);
            COMMANDS_WITH_ROLES.put("clientActions", CLIENT);
            COMMANDS_WITH_ROLES.put("services", CLIENT);
            COMMANDS_WITH_ROLES.put("chooseDuration", CLIENT);
            COMMANDS_WITH_ROLES.put("buyMembership", CLIENT);

            COMMANDS_WITH_ROLES.put("instructorMain", INSTRUCTOR);
            COMMANDS_WITH_ROLES.put("instructorAccount", INSTRUCTOR);
            COMMANDS_WITH_ROLES.put("instructorActions", INSTRUCTOR_ADMIN);
            COMMANDS_WITH_ROLES.put("adminMain", ADMIN);
            COMMANDS_WITH_ROLES.put("adminAccount", ADMIN);
            COMMANDS_WITH_ROLES.put("adminActions", ADMIN);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter("command");
        boolean exist = COMMANDS_WITH_ROLES.containsKey(command);

        if (exist) {
            HttpSession session = request.getSession();
            String roleString = (String) session.getAttribute("role");
            Role userRole;
            if (!nonNull(roleString)) {
                userRole = Role.UNKNOWN;
            } else {
                userRole = Role.valueOf(roleString);
            }
            List<Role> roles = COMMANDS_WITH_ROLES.get(command);
            boolean validCommand = roles.contains(userRole);
            if (!validCommand) {
                response.sendRedirect(request.getRequestURI());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        COMMANDS_WITH_ROLES.clear();
    }
}
