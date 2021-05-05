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

public class InstructorActionsCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(InstructorActionsCommand.class);
    private static final String INSTRUCTOR_ID = "userId";
    private static final String ACTION = "action";
    private static final String ADD_EXERCISE_PAGE = "/view/instructor_pages/add_exercise.jsp";
    private static final String ADD_DISH_PAGE = "/view/instructor_pages/add_dish.jsp";
    private static final String ADD_PROGRAM_PAGE = "/view/instructor_pages/add_program.jsp";
    private static final String ADD_DIET_PAGE = "/view/instructor_pages/add_diet.jsp";
    private static final String ACTION_CONFIRMED_PAGE = "/view/fragments/action_confirmed.jsp";
    public static final String ERROR_PAGE = "/controller?command=error";

    private UserService userService;
    private ProgramService programService;
    private DietService dietService;

    public InstructorActionsCommand(UserService userService, ProgramService programService, DietService dietService) {
        this.userService = userService;
        this.programService = programService;
        this.dietService = dietService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String action = request.getParameter(ACTION);
        HttpSession session = request.getSession();
        switch (action) {
            case "addExerciseForm":
                return CommandResult.forward(ADD_EXERCISE_PAGE);
            case "addDishForm":
                return CommandResult.forward(ADD_DISH_PAGE);
            case "addProgramForm":
                List<Exercise> allExercises = programService.getAllExercises();
                session.setAttribute("allExercises", allExercises);
                session.setAttribute("exercise", new ExerciseDto());
                session.setAttribute("allClients", getAllClients());
                session.setAttribute("client", new UserDto());
                return CommandResult.forward(ADD_PROGRAM_PAGE);
            case "addDietForm":
                List<Dish> allDishes = dietService.getAllDishes();
                session.setAttribute("allDishes", allDishes);
                session.setAttribute("dish", new DishDto());
                session.setAttribute("allClients", getAllClients());
                session.setAttribute("client", new UserDto());
                return CommandResult.forward(ADD_DIET_PAGE);
            case "addExercise":
                if (isExerciseAdded(request, session)) {
                    return CommandResult.forward(ACTION_CONFIRMED_PAGE);
                } else {
                    session.setAttribute("errorMessage", "Adding new exercise has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            case "addDish":
                if (isDishAdded(request, session)) {
                    return CommandResult.forward(ACTION_CONFIRMED_PAGE);
                } else {
                    session.setAttribute("errorMessage", "Adding new dish has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            case "addProgram":
                if (isProgramAdded(request, session)) {
                    return CommandResult.forward(ACTION_CONFIRMED_PAGE);
                } else {
                    session.setAttribute("errorMessage", "Adding new program has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            case "addDiet":
                if (isDietAdded(request, session)) {
                    return CommandResult.forward(ACTION_CONFIRMED_PAGE);
                } else {
                    session.setAttribute("errorMessage", "Adding new diet has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            default:
                throw new IllegalArgumentException("Unknown type of Command " + action);
        }
    }

    private boolean isExerciseAdded(HttpServletRequest request, HttpSession session) {
        String newExerciseName = request.getParameter("newExerciseName");
        try {
            programService.addExercise(newExerciseName);
            session.setAttribute("confirmation", "Exercise has been added successfully!");
            return true;
        } catch (ServiceException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
            return false;
        }
    }

    private boolean isDishAdded(HttpServletRequest request, HttpSession session) {
        String newDishName = request.getParameter("newDishName");
        String newDishMeal = request.getParameter("newDishMeal");
        try {
            dietService.addDish(newDishName, newDishMeal);
            session.setAttribute("confirmation", "Dish has been added successfully!");
            return true;
        } catch (ServiceException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
            return false;
        }
    }

    private boolean isProgramAdded(HttpServletRequest request, HttpSession session) {
        String[] programExercisesList = request.getParameterValues("programExercisesList");
        String clientToAddProgram = request.getParameter("clientToAddProgram");
        Long clientId = Long.parseLong(clientToAddProgram);
        Long instructorId = (Long) session.getAttribute(INSTRUCTOR_ID);
//        try {
//            //todo add assigned_exercises first
//            programService.addProgram(clientId, instructorId, programExercisesList, session);
//        } catch (ServiceException exception) {
//            LOGGER.fatal(exception.getMessage(), exception);
//            return false;
//        }
        session.setAttribute("confirmation", "Program has been added successfully!");
        return true;
    }

    private boolean isDietAdded(HttpServletRequest request, HttpSession session) {
        String[] dietDishesList = request.getParameterValues("dietDishesList");
        String clientToAddDiet = request.getParameter("clientToAddDiet");
        session.setAttribute("confirmation", "Diet has been added successfully!");
        return true;
    }

    private List<User> getAllClients() {
        List<User> allClients = null;
        try {
            allClients = userService.getUsersByRole(Role.CLIENT);
        } catch (ServiceException exception) {
            LOGGER.fatal(exception.getMessage(), exception);
        }
        return allClients;
    }
}