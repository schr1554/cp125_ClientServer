package com.scg.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * Utility class for processing TimeCard lists.
 * 
 * @author chq-alexs
 *
 */
public final class TimeCardListUtil {

	private final static List<TimeCard> consultantTimeCard = new ArrayList<TimeCard>();
	private final static List<TimeCard> startTimeCard = new ArrayList<TimeCard>();

	/**
	 * Get a list of TimeCards for the specified consultant.
	 * 
	 * @param timeCards
	 *            - the list of time cards to extract the sub set from
	 * @param consultant
	 *            - the consultant whose TimeCards will be obtained.
	 * @return a list of TimeCards for the specified consultant.
	 */
	public static List<TimeCard> getTimeCardsForConsultant(List<TimeCard> timeCards, Consultant consultant) {

		timeCards.stream().filter(tCard -> consultant.equals(tCard.getConsultant()))
				.forEach(tCard -> consultantTimeCard.add(tCard));

		sortByConsultantName(consultantTimeCard);

		return consultantTimeCard;

	}

	/**
	 * Get a list of TimeCards that cover dates that fall within a date range.
	 * Each time may have time entries through out one week beginning with the
	 * time card start date.
	 * 
	 * @param timeCards
	 *            - the list of time cards to extract the sub set from
	 * @param dateRange
	 *            - The DateRange within which the dates of the returned
	 *            TimeCards must fall.
	 * @return a list of TimeCards having dates fall within the date range.
	 * 
	 */
	public static List<TimeCard> getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange) {

		timeCards.stream().filter(tCard -> dateRange.isInRange(tCard.getWeekStartingDay().plusDays(6)))
				.forEach(tCard -> startTimeCard.add(tCard));

		return startTimeCard;
	}

	/**
	 * Sorts this list into ascending order by consultant name..
	 * 
	 * @param timeCards
	 *            - the list of time cards to sort
	 */
	public static void sortByConsultantName(List<TimeCard> timeCards) {
		timeCards.sort(new Comparator<TimeCard>() {
			@Override
			public int compare(TimeCard tCard1, TimeCard tCard2) {
				return tCard1.getConsultant().compareTo(tCard2.getConsultant());
			}

		});

	}

	/**
	 * Sorts this list into ascending order, by the start date.
	 * 
	 * @param timeCards
	 *            - the list of time cards to sort
	 */
	public static void sortByStartDate(List<TimeCard> timeCards) {
		timeCards.sort(new Comparator<TimeCard>() {
			@Override
			public int compare(TimeCard tCard1, TimeCard tCard2) {
				return tCard1.getWeekStartingDay().compareTo(tCard2.getWeekStartingDay());
			}

		});

	}

}
