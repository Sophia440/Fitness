package com.epam.web.command;

import com.epam.web.dto.DishDto;
import com.epam.web.dto.ExerciseDto;
import com.epam.web.entity.*;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.DietService;
import com.epam.web.service.ProgramService;
import com.epam.web.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ClientAccountCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(ClientAccountCommand.class);
    private static final String CLIENT_ACCOUNT_PAGE = "/view/client_pages/client_account.jsp";
    private static final String CLIENT_ID = "userId";

    private UserService userService;
    private ProgramService programService;
    private DietService dietService;

    public ClientAccountCommand(UserService userService, ProgramService programService, DietService dietService) {
        this.userService = userService;
        this.programService = programService;
        this.dietService = dietService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(CLIENT_ID);
        int clientDiscount = userService.getDiscount(clientId);
        session.setAttribute("clientDiscount", clientDiscount);
        Optional<Membership> optionalMembership = null;
        optionalMembership = userService.getLastMembership(clientId);
        if (optionalMembership.isPresent()) {
            Membership membership = optionalMembership.get();
            session.setAttribute("membershipEndDate", membership.getEndDate());
        }
        Optional<Program> optionalProgram = programService.getProgram(clientId);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            List<Exercise> exerciseList = program.getExercises();
            session.setAttribute("exerciseList", exerciseList);
            session.setAttribute("exercise", new ExerciseDto());
            String programStatus = program.getStatus().toString();
            session.setAttribute("programStatus", programStatus);
        }
        Optional<Diet> optionalDiet = dietService.getDiet(clientId);
        if (optionalDiet.isPresent()) {
            Diet diet = optionalDiet.get();
            List<Dish> dishList = diet.getDishes();
            session.setAttribute("dishList", dishList);
            session.setAttribute("dish", new DishDto());
            String dietStatus = diet.getStatus().toString();
            session.setAttribute("dietStatus", dietStatus);
        }
        return CommandResult.forward(CLIENT_ACCOUNT_PAGE);
    }
}
