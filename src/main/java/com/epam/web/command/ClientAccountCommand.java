package com.epam.web.command;

import com.epam.web.dto.DishDto;
import com.epam.web.dto.ExerciseDto;
import com.epam.web.dto.MembershipDto;
import com.epam.web.entity.*;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.DietService;
import com.epam.web.service.ProgramService;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ClientAccountCommand implements Command {
    private static final String CLIENT_ACCOUNT_PAGE = "/view/account.jsp";
    private static final String CLIENT_ID = "user_id";

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
        Optional<Membership> optionalMembership = userService.getMembership(clientId);
        Membership membership = null;
        if (optionalMembership.isPresent()) {
            membership = optionalMembership.get();
        }
        request.setAttribute("membershipEndDate", membership.getEndDate());
        Optional<Program> optionalProgram = programService.getProgram(clientId);
        Program program = null;
        if (optionalProgram.isPresent()) {
            program = optionalProgram.get();
        }
        List<Exercise> exerciseList = program.getExercises();
        request.setAttribute("exerciseList", exerciseList);
        request.setAttribute("exercise", new ExerciseDto());
        Optional<Diet> optionalDiet = dietService.getDiet(clientId);
        Diet diet = null;
        if (optionalDiet.isPresent()) {
            diet = optionalDiet.get();
        }
        List<Dish> dishList = diet.getDishes();
        request.setAttribute("dishList", dishList);
        request.setAttribute("dish", new DishDto());
        return CommandResult.forward(CLIENT_ACCOUNT_PAGE);
    }
}
