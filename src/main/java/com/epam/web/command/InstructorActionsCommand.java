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
import com.epam.web.validator.Validator;
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
    private static final String MESSAGE = "message";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ADD_EXERCISE_PAGE = "/view/instructor_pages/add_exercise.jsp";
    private static final String ADD_DISH_PAGE = "/view/instructor_pages/add_dish.jsp";
    private static final String ADD_PROGRAM_PAGE = "/view/instructor_pages/add_program.jsp";
    private static final String CHOOSE_CLIENT_FOR_PROGRAM = "/view/instructor_pages/choose_client_for_program.jsp";
    private static final String ADD_DIET_PAGE = "/view/instructor_pages/add_diet.jsp";
    private static final String CHOOSE_CLIENT_FOR_DIET = "/view/instructor_pages/choose_client_for_diet.jsp";
    private static final String MESSAGE_PAGE = "/view/fragments/message_page.jsp";
    public static final String ERROR_PAGE = "/controller?command=error";

    private UserService userService;
    private ProgramService programService;
    private DietService dietService;
    private Validator validator;

    public InstructorActionsCommand(UserService userService, ProgramService programService, DietService dietService) {
        this.userService = userService;
        this.programService = programService;
        this.dietService = dietService;
        this.validator = new Validator();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String action = request.getParameter(ACTION);
        HttpSession session = request.getSession();
        Long instructorId = (Long) session.getAttribute(INSTRUCTOR_ID);
        switch (action) {
            case "addExerciseForm":
                return CommandResult.forward(ADD_EXERCISE_PAGE);
            case "addExercise":
                String newExerciseName = request.getParameter("newExerciseName");
                if (newExerciseName.isEmpty()) {
                    session.setAttribute(MESSAGE, "emptyName");
                    return CommandResult.forward(MESSAGE_PAGE);
                }
                if (programService.addExercise(newExerciseName)) {
                    session.setAttribute(MESSAGE, "exerciseAdded");
                    return CommandResult.forward(MESSAGE_PAGE);
                } else {
                    session.setAttribute(ERROR_MESSAGE, "Adding new exercise has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            case "addDishForm":
                return CommandResult.forward(ADD_DISH_PAGE);
            case "addDish":
                String newDishName = request.getParameter("newDishName");
                if (newDishName.isEmpty()) {
                    session.setAttribute(MESSAGE, "emptyName");
                    return CommandResult.forward(MESSAGE_PAGE);
                }
                String newDishMeal = request.getParameter("newDishMeal");
                if (dietService.addDish(newDishName, newDishMeal)) {
                    session.setAttribute(MESSAGE, "dishAdded");
                    return CommandResult.forward(MESSAGE_PAGE);
                } else {
                    session.setAttribute(MESSAGE, "Adding new dish has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            case "chooseClientForProgram":
                session.setAttribute("allClients", getAllClients());
                session.setAttribute("client", new UserDto());
                return CommandResult.forward(CHOOSE_CLIENT_FOR_PROGRAM);
            case "checkProgram":
                String clientToAddProgramString = request.getParameter("clientToAddProgram");
                Long clientToAddProgram = Long.parseLong(clientToAddProgramString);
                session.setAttribute("clientToAddProgram", clientToAddProgram);
                boolean hasProgram;
                try {
                    hasProgram = programService.hasProgram(clientToAddProgram);
                } catch (ServiceException exception) {
                    session.setAttribute(ERROR_MESSAGE, "Checking clients program has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
                if (!hasProgram) {
                    List<Exercise> allExercises = programService.getAllExercises();
                    session.setAttribute("allExercises", allExercises);
                    session.setAttribute("exercise", new ExerciseDto());
                    return CommandResult.forward(ADD_PROGRAM_PAGE);
                } else {
                    session.setAttribute(MESSAGE, "clientHasProgram");
                    return CommandResult.forward(MESSAGE_PAGE);
                }
            case "addProgram":
                String[] programExercisesList = request.getParameterValues("programExercisesList");
                if (programExercisesList == null) {
                    session.setAttribute(MESSAGE, "noSelectedExercises");
                    return CommandResult.forward(MESSAGE_PAGE);
                }
                String programStartDate = request.getParameter("programStartDate");
                String programEndDate = request.getParameter("programEndDate");
                if (!validator.validateDates(programStartDate, programEndDate)) {
                    session.setAttribute(MESSAGE, "invalidDate");
                    return CommandResult.forward(MESSAGE_PAGE);
                }
                Long clientToAddProgramId = (Long) session.getAttribute("clientToAddProgram");
                if (programService.addProgram(clientToAddProgramId, instructorId, programExercisesList,
                        programStartDate, programEndDate)) {
                    session.setAttribute(MESSAGE, "programAdded");
                    return CommandResult.forward(MESSAGE_PAGE);
                } else {
                    session.setAttribute(ERROR_MESSAGE, "Adding new program has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            case "chooseClientForDiet":
                session.setAttribute("allClients", getAllClients());
                session.setAttribute("client", new UserDto());
                return CommandResult.forward(CHOOSE_CLIENT_FOR_DIET);
            case "checkDiet":
                String clientToAddDietString = request.getParameter("clientToAddDiet");
                Long clientToAddDietId = Long.parseLong(clientToAddDietString);
                session.setAttribute("clientToAddDiet", clientToAddDietId);
                boolean hasDiet;
                try {
                    hasDiet = dietService.hasDiet(clientToAddDietId);
                } catch (ServiceException exception) {
                    session.setAttribute(ERROR_MESSAGE, "Checking clients diet has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
                if (!hasDiet) {
                    List<Dish> allDishes = dietService.getAllDishes();
                    session.setAttribute("allDishes", allDishes);
                    session.setAttribute("dish", new DishDto());
                    return CommandResult.forward(ADD_DIET_PAGE);
                } else {
                    session.setAttribute(MESSAGE, "clientHasDiet");
                    return CommandResult.forward(MESSAGE_PAGE);
                }
            case "addDiet":
                String[] dietDishesList = request.getParameterValues("dietDishesList");
                if (dietDishesList == null) {
                    session.setAttribute(MESSAGE, "noSelectedDishes");
                    return CommandResult.forward(MESSAGE_PAGE);
                }
                String dietStartDate = request.getParameter("dietStartDate");
                String dietEndDate = request.getParameter("dietEndDate");
                if (!validator.validateDates(dietStartDate, dietEndDate)) {
                    session.setAttribute(MESSAGE, "invalidDate");
                    return CommandResult.forward(MESSAGE_PAGE);
                }
                Long clientToAddDiet = (Long) session.getAttribute("clientToAddDiet");
                if (dietService.addDiet(clientToAddDiet, instructorId, dietDishesList,
                        dietStartDate, dietEndDate)) {
                    session.setAttribute(MESSAGE, "dietAdded");
                    return CommandResult.forward(MESSAGE_PAGE);
                } else {
                    session.setAttribute(ERROR_MESSAGE, "Adding new diet has failed!");
                    return CommandResult.forward(ERROR_PAGE);
                }
            default:
                throw new IllegalArgumentException("Unknown type of Command " + action);
        }
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