package com.scg.util;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a range of two dates, inclusive of the start date and end date.
 * 
 * @author chq-alexs
 *
 */
public final class DateRange implements Serializable {

	/**
	 * Serializable version ID
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate startDate;
	private LocalDate endDate;

	/**
	 * Construct a DateRange given two dates.
	 * 
	 * @param startDate
	 *            - the start date for this DateRange.
	 * @param endDate
	 *            - the end date for this DateRange.
	 */
	public DateRange(java.time.LocalDate startDate, java.time.LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Construct a DateRange given two date strings in the correct format.
	 * 
	 * @param start
	 *            - String representing the start date, of the form yyyy-MM-dd.
	 * @param end
	 *            - String representing the end date, of the form yyyy-MM-dd.
	 */
	public DateRange(String start, String end) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.startDate = LocalDate.parse(start, formatter);
		this.endDate = LocalDate.parse(end, formatter);
	}

	/**
	 * Construct a DateRange for the given month.
	 * 
	 * @param month
	 *            - the month for which this DateRange should be constructed.
	 * @param year
	 *            - the calendar year
	 */
	public DateRange(java.time.Month month, int year) {
		this.startDate = LocalDate.of(year, month, 1);
		this.endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
	}

	/**
	 * Returns the start date for this DateRange.
	 * 
	 * @return the start date for this DateRange.
	 */
	public java.time.LocalDate getStartDate() {
		return this.startDate;

	}

	/**
	 * Returns the end date for this DateRange.
	 * 
	 * @return the end date for this DateRange.
	 */
	public java.time.LocalDate getEndDate() {
		return this.endDate;

	}

	/**
	 * @param date
	 *            - the date to check for being within this DateRange.
	 * 
	 * @return Returns true if the specified date is within the range start date
	 *         <= date <= end date.
	 */
	public boolean isInRange(java.time.LocalDate date) {

		boolean inRange = ((date.isAfter(this.startDate) || date.isEqual(this.startDate))
				&& (date.isBefore(this.endDate) || date.isEqual(this.endDate)));

		return inRange;

	}

}
