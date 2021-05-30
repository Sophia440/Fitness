package com.epam.web.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validator {

    public boolean validateDates(String startDate, String endDate) {
        LocalDate startDateLocal;
        LocalDate endDateLocal;
        try {
            startDateLocal = LocalDate.parse(startDate);
            endDateLocal = LocalDate.parse(endDate);
        } catch (DateTimeParseException | NullPointerException exception) {
            return false;
        }
        LocalDate today = LocalDate.now();
        return startDateLocal.isBefore(endDateLocal) && today.isBefore(startDateLocal);
    }
}
