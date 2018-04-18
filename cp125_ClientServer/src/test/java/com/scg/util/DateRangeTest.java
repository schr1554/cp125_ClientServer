package com.scg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for the DateRange class.
 *
 * @author Russ Moul
 */
public final class DateRangeTest {
    /** Test month. */
    private static final Month TEST_MONTH = Month.MARCH;

    /** Test year. */
    private static final int TEST_YEAR = 2006;

    /** Test start date. */
    private LocalDate startDate;

    /** Test end date. */
    private LocalDate endDate;

    /**
     * Test the various constructors.
     */
    @Test
    public void testConstructors() {
        DateRange dateRange = new DateRange(startDate, endDate);
        assertNotNull("DateRange(Date, Date) failed.", dateRange);
        assertEquals(startDate, dateRange.getStartDate());
        assertEquals(endDate, dateRange.getEndDate());

        dateRange = new DateRange(TEST_MONTH, TEST_YEAR);
        assertEquals(startDate, dateRange.getStartDate());
        assertEquals(endDate, dateRange.getEndDate());
    }

    /**
     * Initialize start and end month dates.
     */
    @Before
    public void setUp() {
        startDate = LocalDate.of(TEST_YEAR, TEST_MONTH, 1);
    	endDate = startDate.plusDays(startDate.lengthOfMonth()-1);
    }

    /**
     * Test the isInDange method.
     */
    @Test
    public void testIsInRange() {
        final DateRange dateRange = new DateRange(startDate, endDate);
        assertTrue(dateRange.isInRange(startDate));

        DateRange testDateRange = new DateRange(Month.FEBRUARY, TEST_YEAR);
        assertFalse(testDateRange.isInRange(startDate));

        testDateRange = new DateRange("2006-02-01", "2006-02-28");
        assertFalse(testDateRange.isInRange(startDate));

        testDateRange = new DateRange("2006-02-26", "2006-03-04");
        assertTrue(testDateRange.isInRange(startDate));
    }
}
