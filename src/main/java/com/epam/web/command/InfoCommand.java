package com.epam.web.command;

import com.epam.web.dto.DishDto;
import com.epam.web.dto.ExerciseDto;
import com.epam.web.entity.Dish;
import com.epam.web.entity.Exercise;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.DietService;
import com.epam.web.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class InfoCommand implements Command {
    private static final String INFO_PAGE = "/view/info.jsp";

    private ProgramService programService;
    private DietService dietService;

    public InfoCommand(ProgramService programService, DietService dietService) {
        this.programService = programService;
        this.dietService = dietService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        List<Exercise> allExercises = programService.getAllExercises();
        session.setAttribute("allExercises", allExercises);
        session.setAttribute("exercise", new ExerciseDto());
        List<Dish> allDishes = dietService.getAllDishes();
        session.setAttribute("allDishes", allDishes);
        session.setAttribute("dish", new DishDto());
        return CommandResult.forward(INFO_PAGE);
    }
}
