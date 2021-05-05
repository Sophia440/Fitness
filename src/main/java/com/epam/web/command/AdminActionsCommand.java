package com.epam.web.command;

import com.epam.web.dto.DishDto;
import com.epam.web.dto.ExerciseDto;
import com.epam.web.dto.UserDto;
import com.epam.web.entity.Dish;
import com.epam.web.entity.Exercise;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
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

public class AdminActionsCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(AdminActionsCommand.class);
    private static final String ACTION = "action";
    private static final String ADD_DISCOUNT_PAGE = "/view/admin_pages/add_discount.jsp";
    private static final String DELETE_EXERCISE_PAGE = "/view/admin_pages/delete_exercise.jsp";
    private static final String DELETE_DISH_PAGE = "/view/admin_pages/delete_dish.jsp";
    private static final String ACTION_CONFIRMED_PAGE = "/view/fragments/action_confirmed.jsp";
    public static final String ERROR_PAGE = "/controller?command=error";

    private UserService userService;
    private ProgramService programService;
    private DietService dietService;

    public AdminActionsCommand(UserService userService, ProgramService programService, DietService dietService) {
        this.userService = userService;
        this.programService = programService;
        this.dietService = dietService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String action = request.getParameter(ACTION);
        HttpSession session = request.getSession();
        switch (action) {
            case "addDiscountForm":
                List<User> allClients = null;
                try {
                    allClients = userService.getUsersByRole(Role.CLIENT);
                } catch (ServiceException exception) {
                    LOGGER.fatal(exception.getMessage(), exception);
                }
                session.setAttribute("allClients", allClients);
                session.setAttribute("client", new UserDto());
                return CommandResult.forward(ADD_DISCOUNT_PAGE);
            case "deleteExerciseForm":
                List<Exercise> allExercises = programService.getAllExercises();
                session.setAttribute("allExercises", allExercises);
                session.setAttribute("exercise", new ExerciseDto());
                return CommandResult.forward(DELETE_EXERCISE_PAGE);
            case "deleteDishForm":
                List<Dish> allDishes = dietService.getAllDishes();
                session.setAttribute("allDishes", allDishes);
                session.setAttribute("dish", new DishDto());
                return CommandResult.forward(DELETE_DISH_PAGE);
            case "addDiscount":
                if (isDiscountAdded(request, session)) {
                    return CommandResult.forward(ACTION_CONFIRMED_PAGE);
                } else {
                    session.setAttribute("errorMessage", "Adding new discount has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            case "deleteExercise":
                if (isExerciseDeleted(request, session)) {
                    return CommandResult.forward(ACTION_CONFIRMED_PAGE);
                } else {
                    session.setAttribute("errorMessage", "Deleting exercise has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            case "deleteDish":
                if (isDishDeleted(request, session)) {
                    return CommandResult.forward(ACTION_CONFIRMED_PAGE);
                } else {
                    session.setAttribute("errorMessage", "Deleting dish has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            default:
                throw new IllegalArgumentException("Unknown type of Command " + action);
        }
    }


    private boolean isDiscountAdded(HttpServletRequest request, HttpSession session) {
        String newDiscount = request.getParameter("newDiscount");
        int discount = Integer.parseInt(newDiscount);
        String clientToAddDiscount = request.getParameter("clientToAddDiscount");
        Long clientId = Long.parseLong(clientToAddDiscount);
        try {
            userService.setDiscount(clientId, discount);
            session.setAttribute("confirmation", "Discount has been added successfully!");
            return true;
        } catch (ServiceException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
            return false;
        }
    }

    private boolean isExerciseDeleted(HttpServletRequest request, HttpSession session) {
        String exerciseToDelete = request.getParameter("exerciseToDelete");
        Long idToDelete = Long.parseLong(exerciseToDelete);
        try {
            programService.deleteExercise(idToDelete);
            session.setAttribute("confirmation", "Exercise has been deleted successfully!");
            return true;
        } catch (ServiceException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
            return false;
        }
    }

    private boolean isDishDeleted(HttpServletRequest request, HttpSession session) {
        String dishToDelete = request.getParameter("dishToDelete");
        Long idToDelete = Long.parseLong(dishToDelete);
        try {
            dietService.deleteDish(idToDelete);
            session.setAttribute("confirmation", "Dish has been deleted successfully!");
            return true;
        } catch (ServiceException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
            return false;
        }
    }
}
