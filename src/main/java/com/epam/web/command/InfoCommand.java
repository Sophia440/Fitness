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

/**
 * This command redirects client to the appropriate info page depending on the 'section' parameter.
 *
 */
public class InfoCommand implements Command {
    private static final String INFO_EXERCISES_PAGE = "/view/info_exercises.jsp";
    private static final String INFO_DISHES_PAGE = "/view/info_dishes.jsp";
    private static final String SECTION = "section";
    private static final String PAGE = "page";
    private static final String NEXT = "next";
    private static final String PREVIOUS = "previous";
    private static final int DEFAULT_FIRST_ROW = 0;
    private static final int DEFAULT_ROW_COUNT = 4;

    private ProgramService programService;
    private DietService dietService;

    public InfoCommand(ProgramService programService, DietService dietService) {
        this.programService = programService;
        this.dietService = dietService;
    }

    /**
     * Handles chosen info page section.
     *
     * @param request the request from Controller
     * @param response the response from Controller
     * @return CommandResult with the chosen info page section
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String section = request.getParameter(SECTION);
        HttpSession session = request.getSession();
        String page;
        int firstRow;
        int rowCount;
        int pageCount;
        int exerciseCount;
        int dishCount;
        List<Exercise> exerciseSublist;
        List<Dish> dishSublist;
        switch (section) {
            case "exercisesFirst":
                exerciseSublist = programService.getExerciseSublist(DEFAULT_FIRST_ROW, DEFAULT_ROW_COUNT);
                exerciseCount = programService.getExerciseCount();
                int totalExercisePageCount = exerciseCount / DEFAULT_ROW_COUNT;
                if (exerciseCount % DEFAULT_ROW_COUNT != 0) {
                    totalExercisePageCount++;
                }
                session.setAttribute("firstRow", DEFAULT_FIRST_ROW);
                session.setAttribute("rowCount", DEFAULT_ROW_COUNT);
                session.setAttribute("exerciseSublist", exerciseSublist);
                session.setAttribute("exercise", new ExerciseDto());
                session.setAttribute("pageCount", 1);
                session.setAttribute("totalExercisePageCount", totalExercisePageCount);
                return CommandResult.forward(INFO_EXERCISES_PAGE);
            case "exercisesChangePage":
                firstRow = Integer.parseInt(request.getParameter("firstRow"));
                rowCount = Integer.parseInt(request.getParameter("rowCount"));
                pageCount = Integer.parseInt(request.getParameter("pageCount"));
                exerciseCount = programService.getExerciseCount();
                page = request.getParameter(PAGE);
                if (page.equals(NEXT)) {
                    if (firstRow + rowCount >= exerciseCount) {
                        rowCount = exerciseCount - firstRow;
                    } else {
                        firstRow = firstRow + rowCount;
                    }
                    session.setAttribute("pageCount", ++pageCount);
                } else if (page.equals(PREVIOUS)) {
                    if (firstRow - rowCount < DEFAULT_FIRST_ROW) {
                        firstRow = DEFAULT_FIRST_ROW;
                        rowCount = DEFAULT_ROW_COUNT;
                    } else {
                        firstRow = firstRow - rowCount;
                    }
                    session.setAttribute("pageCount", --pageCount);
                }
                exerciseSublist = programService.getExerciseSublist(firstRow, rowCount);
                session.setAttribute("firstRow", firstRow);
                session.setAttribute("rowCount", DEFAULT_ROW_COUNT);
                session.setAttribute("exerciseSublist", exerciseSublist);
                session.setAttribute("exercise", new ExerciseDto());
                return CommandResult.forward(INFO_EXERCISES_PAGE);
            case "dishesFirst":
                dishSublist = dietService.getDishSublist(DEFAULT_FIRST_ROW, DEFAULT_ROW_COUNT);
                dishCount = dietService.getDishCount();
                int totalDishPageCount = dishCount / DEFAULT_ROW_COUNT;
                if (dishCount % DEFAULT_ROW_COUNT != 0) {
                    totalDishPageCount++;
                }
                session.setAttribute("firstRow", DEFAULT_FIRST_ROW);
                session.setAttribute("rowCount", DEFAULT_ROW_COUNT);
                session.setAttribute("dishSublist", dishSublist);
                session.setAttribute("dish", new DishDto());
                session.setAttribute("pageCount", 1);
                session.setAttribute("totalDishPageCount", totalDishPageCount);
                return CommandResult.forward(INFO_DISHES_PAGE);
            case "dishesChangePage":
                firstRow = Integer.parseInt(request.getParameter("firstRow"));
                rowCount = Integer.parseInt(request.getParameter("rowCount"));
                pageCount = Integer.parseInt(request.getParameter("pageCount"));
                dishCount = dietService.getDishCount();
                page = request.getParameter(PAGE);
                if (page.equals(NEXT)) {
                    if (firstRow + rowCount >= dishCount) {
                        rowCount = dishCount - firstRow;
                    } else {
                        firstRow = firstRow + rowCount;
                    }
                    session.setAttribute("pageCount", ++pageCount);
                } else if (page.equals(PREVIOUS)) {
                    if (firstRow - rowCount < DEFAULT_FIRST_ROW) {
                        firstRow = DEFAULT_FIRST_ROW;
                        rowCount = DEFAULT_ROW_COUNT;
                    } else {
                        firstRow = firstRow - rowCount;
                    }
                    session.setAttribute("pageCount", --pageCount);
                }
                dishSublist = dietService.getDishSublist(firstRow, rowCount);
                session.setAttribute("firstRow", firstRow);
                session.setAttribute("rowCount", DEFAULT_ROW_COUNT);
                session.setAttribute("dishSublist", dishSublist);
                session.setAttribute("dish", new DishDto());
                return CommandResult.forward(INFO_DISHES_PAGE);
            default:
                throw new IllegalArgumentException("Unknown page: " + section);
        }
    }
}
