package com.scg.util;

import java.util.Comparator;

import com.scg.domain.TimeCard;

/**
 * Compares two TimeCard objects by ascending consultant, timecard week,
 * totalHours, totalBillableHours and totalNonBillableHours.
 * 
 * @author chq-alexs
 *
 */
public final class TimeCardConsultantComparator implements Comparator<TimeCard> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */

	@Override
	public int compare(final TimeCard firstTimeCard, final TimeCard secondTimeCard) {

		int diff = 0;

		diff = (firstTimeCard.getConsultant().compareTo(secondTimeCard.getConsultant()));

		if (diff == 0) {
			diff = firstTimeCard.getWeekStartingDay().compareTo(secondTimeCard.getWeekStartingDay());
		}

		if (diff == 0) {

			if (firstTimeCard.getTotalHours() > secondTimeCard.getTotalHours()) {
				diff = 1;
			} else if (firstTimeCard.getTotalHours() < secondTimeCard.getTotalHours()) {
				diff = -1;
			}
		}
		if (diff == 0) {

			if (firstTimeCard.getTotalBillableHours() > secondTimeCard.getTotalBillableHours()) {
				diff = 1;
			} else if (firstTimeCard.getTotalBillableHours() < secondTimeCard.getTotalBillableHours()) {
				diff = -1;
			}
		}
		if (diff == 0) {

			if (firstTimeCard.getTotalNonBillableHours() > secondTimeCard.getTotalNonBillableHours()) {
				diff = 1;
			} else if (firstTimeCard.getTotalNonBillableHours() < secondTimeCard.getTotalNonBillableHours()) {
				diff = -1;
			}
		}

		return diff;
	}

}
