package com.epam.web.validator;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateValidatorTest {
    private final DateValidator dateValidator = new DateValidator();
    private static final String VALID_START_DATE = LocalDate.MAX.minusMonths(1).toString();
    private static final String VALID_END_DATE = LocalDate.MAX.toString();
    private static final String INVALID_DATE = LocalDate.MIN.toString();
    private static final String NULL_DATE = null;
    private static final String EMPTY_DATE = "";

    @Test
    public void testValidateDates_withValidInput_ShouldReturnTrue() {
        boolean actual = dateValidator.validate(VALID_START_DATE, VALID_END_DATE);
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateDates_withInvalidInput_ShouldReturnFalse() {
        boolean actualNullStart = dateValidator.validate(NULL_DATE, VALID_END_DATE);
        boolean actualNullEnd = dateValidator.validate(VALID_START_DATE, NULL_DATE);
        boolean actualStartEarlierThanNow = dateValidator.validate(INVALID_DATE, VALID_END_DATE);
        boolean actualEndEarlierThanStart = dateValidator.validate(VALID_START_DATE, INVALID_DATE);
        boolean actualEmptyData = dateValidator.validate(VALID_START_DATE, EMPTY_DATE);
        Assert.assertFalse(actualNullStart);
        Assert.assertFalse(actualNullEnd);
        Assert.assertFalse(actualStartEarlierThanNow);
        Assert.assertFalse(actualEndEarlierThanStart);
        Assert.assertFalse(actualEmptyData);
    }
}
