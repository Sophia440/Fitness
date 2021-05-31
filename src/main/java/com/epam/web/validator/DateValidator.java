package com.epam.web.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class validates the dates.
 *
 */
public class DateValidator {

    /**
     * Checks if start data is earlier than end and later than current data.
     *
     * @param startDate as String
     * @param endDate as String
     * @return boolean true if valid
     */
    public boolean validate(String startDate, String endDate) {
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
