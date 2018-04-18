package com.scg.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a time card capable of storing a consultant's billable and
 * non-billable hours for a week.
 * 
 * @author chq-alexs
 *
 */
public final class TimeCard implements Comparable<TimeCard>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Hash Code
	 */
	private Integer hashCode;

	/**
	 * The Consultant whose information this TimeCard records.
	 */
	private final Consultant consultant;

	/**
	 * weekStartingDay - The date of the first work day of the week this
	 * TimeCard records information for.
	 */
	private final LocalDate weekStartingDay;

	/**
	 * billableHours - The number of billable hours on a timecard.
	 */
	private int totalBillableHours;

	/**
	 * totalNonBillableHours - The number of nonbillable hours on a timecard.
	 */
	private int totalNonBillableHours;

	/**
	 * consultingHours - List of consulting hours in timecard.
	 */
	private List<ConsultantTime> consultingHours = new ArrayList<ConsultantTime>();

	/**
	 * totalHours - Total hours in a timecard as a int.
	 */
	private int totalHours;

	/**
	 * Creates a new instance of TimeCard
	 * 
	 * @param consultant
	 *            - The Consultant whose information this TimeCard records.
	 * 
	 * @param weekStartingDay
	 *            - The date of the first work day of the week this TimeCard
	 *            records information for.
	 * 
	 */
	public TimeCard(Consultant consultant, LocalDate weekStartingDay) {
		this.consultant = consultant;
		this.weekStartingDay = weekStartingDay;

	}

	/**
	 * Getter for property consultant.
	 * 
	 * @return Value of property consultant.
	 */
	public Consultant getConsultant() {
		return this.consultant;
	}

	/**
	 * Getter for property billableHours.
	 * 
	 * @return Value of property billableHours.
	 * 
	 */
	public int getTotalBillableHours() {
		return this.totalBillableHours;
	}

	/**
	 * Getter for property totalNonBillableHours.
	 * 
	 * @return Value of property totalNonBillableHours.
	 * 
	 */
	public int getTotalNonBillableHours() {
		return this.totalNonBillableHours;
	}

	/**
	 * Getter for property consultingHours.
	 * 
	 * @return Value of property consultingHours.
	 * 
	 */
	public List<ConsultantTime> getConsultingHours() {
		return Collections.unmodifiableList(this.consultingHours);
	}

	/**
	 * Add a ConsultantTime object to this TimeCard.
	 * 
	 * @param consultantTime
	 *            - The ConsultantTime to add.
	 */
	public void addConsultantTime(ConsultantTime consultantTime) {

		consultingHours.add(consultantTime);
		final int addedHours = consultantTime.getHours();
		if (consultantTime.isBillable()) {
			totalBillableHours += addedHours;
		} else {
			totalNonBillableHours += addedHours;
		}
	}

	/**
	 * Getter for property totalHours.
	 * 
	 * @return Value of property totalHours.
	 * 
	 */
	public int getTotalHours() {
		return this.totalBillableHours + this.totalNonBillableHours;
	}

	/**
	 * Getter for property weekStartingDay.
	 * 
	 * @return Value of property weekStartingDay.
	 * 
	 */
	public LocalDate getWeekStartingDay() {
		return this.weekStartingDay;
	}

	/**
	 * Returns the billable hours (if any) in this TimeCard for the specified
	 * Client.
	 * 
	 * @param clientName
	 *            - name of the client to extract hours for.
	 * 
	 * @return list of billable hours for the client.
	 * 
	 */
	public List<ConsultantTime> getBillableHoursForClient(final String clientName) {
		final ArrayList<ConsultantTime> billableConsultingHours = new ArrayList<ConsultantTime>();

		consultingHours.stream().filter(cHours -> clientName.equals(cHours.getAccount().getName()))
				.filter(cHours -> cHours.isBillable()).forEach(cHours -> billableConsultingHours.add(cHours));

		return billableConsultingHours;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = this.consultant.getName().toString() + " "
				+ getWeekStartingDay().format(DateTimeFormatter.ofPattern("yyy MM dd"));
		return str;

	}

	/**
	 * Create a string representation of this object, suitable for printing the
	 * entire time card.
	 * 
	 * @return this TimeCard as a formatted String.
	 * 
	 */
	public String toReportString() {

		String headerSplitter = "====================================================================";
		String consultantHeader = "Consultant: ";
		String weekHeader = "Week Starting: ";
		String billableTimeHeader = "Billable Time:";
		String accountHeader = "Account";
		String accountSplitter = "---------------------------";
		String dateHeader = "Date";
		String dateSplitter = "----------";
		String hourHeader = "Hours";
		String hourSplitter = "-----";
		String skillHeader = "Skill";
		String skillSplitter = "--------------------";
		String nonBillableTimeHeader = "Non-billable Time:";
		String summaryHeader = "Summary:";
		String totalBillable = "Total Billable:";
		String totalNonBillable = "Total Non-Billable:";
		String totalHours = "Total Hours:";

		StringBuilder reportBuilder = new StringBuilder();

		reportBuilder.append(headerSplitter + "\n");

		reportBuilder.append(String.format("%-34s %34s %n", consultantHeader + getConsultant(),
				weekHeader + getWeekStartingDay().format(DateTimeFormatter.ofPattern("MMM dd, yyy")) + "\n"));

		reportBuilder.append(billableTimeHeader + "\n");

		reportBuilder
				.append(String.format("%-28s %-11s %-6s %-21s %n", accountHeader, dateHeader, hourHeader, skillHeader));

		reportBuilder.append(
				String.format("%-28s %-11s %-6s %-21s %n", accountSplitter, dateSplitter, hourSplitter, skillSplitter));

		for (ConsultantTime temp : this.consultingHours) {

			if (temp.getAccount().isBillable()) {
				reportBuilder.append(String.format("%-28s %-11s %5s %-21s %n", temp.getAccount().getName(),
						temp.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyy")), temp.getHours(),
						temp.getSkill()));
			}
		}

		reportBuilder.append(nonBillableTimeHeader + "\n");

		reportBuilder
				.append(String.format("%-28s %-11s %-6s %-21s %n", accountHeader, dateHeader, hourHeader, skillHeader));

		reportBuilder.append(
				String.format("%-28s %-11s %-6s %-21s %n", accountSplitter, dateSplitter, hourSplitter, skillSplitter));

		for (ConsultantTime temp : this.consultingHours) {

			if (!temp.getAccount().isBillable()) {

				reportBuilder.append(String.format("%-28s %-11s %5s %-21s %n", temp.getAccount().getName(),
						temp.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyy")), temp.getHours(),
						temp.getSkill()));

			}
		}

		reportBuilder.append("\n");

		reportBuilder.append(summaryHeader + "\n");

		reportBuilder.append(String.format("%-20s %25s %n", totalBillable, getTotalBillableHours()));

		reportBuilder.append(String.format("%-20s %25s %n", totalNonBillable, getTotalNonBillableHours()));

		reportBuilder.append(
				String.format("%-20s %25s %n", totalHours, getTotalBillableHours() + getTotalNonBillableHours()));

		reportBuilder.append(headerSplitter + "\n");

		String report = reportBuilder.toString();
		return report;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + totalBillableHours;
		result = prime * result + ((consultant == null) ? 0 : consultant.hashCode());
		result = prime * result + ((consultingHours == null) ? 0 : consultingHours.hashCode());
		result = prime * result + ((hashCode == null) ? 0 : hashCode.hashCode());
		result = prime * result + totalHours;
		result = prime * result + totalNonBillableHours;
		result = prime * result + ((weekStartingDay == null) ? 0 : weekStartingDay.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeCard other = (TimeCard) obj;
		if (totalBillableHours != other.totalBillableHours)
			return false;
		if (consultant == null) {
			if (other.consultant != null)
				return false;
		} else if (!consultant.equals(other.consultant))
			return false;
		if (consultingHours == null) {
			if (other.consultingHours != null)
				return false;
		} else if (!consultingHours.equals(other.consultingHours))
			return false;
		if (hashCode == null) {
			if (other.hashCode != null)
				return false;
		} else if (!hashCode.equals(other.hashCode))
			return false;
		if (totalHours != other.totalHours)
			return false;
		if (totalNonBillableHours != other.totalNonBillableHours)
			return false;
		if (weekStartingDay == null) {
			if (other.weekStartingDay != null)
				return false;
		} else if (!weekStartingDay.equals(other.weekStartingDay))
			return false;
		return true;
	}

	@Override
	public int compareTo(TimeCard secondTimeCard) {

		int diff = 0;

		diff = (this.getWeekStartingDay().compareTo(secondTimeCard.getWeekStartingDay()));

		if (diff == 0) {
			diff = this.getConsultant().compareTo(secondTimeCard.getConsultant());
		}

		if (diff == 0) {

			if (this.getTotalHours() > secondTimeCard.getTotalHours()) {
				diff = 1;
			} else if (this.getTotalHours() < secondTimeCard.getTotalHours()) {
				diff = -1;
			}
		}
		if (diff == 0) {

			if (this.getTotalBillableHours() > secondTimeCard.getTotalBillableHours()) {
				diff = 1;
			} else if (this.getTotalBillableHours() < secondTimeCard.getTotalBillableHours()) {
				diff = -1;
			}
		}
		if (diff == 0) {

			if (this.getTotalNonBillableHours() > secondTimeCard.getTotalNonBillableHours()) {
				diff = 1;
			} else if (this.getTotalNonBillableHours() < secondTimeCard.getTotalNonBillableHours()) {
				diff = -1;
			}
		}

		return diff;
	}
}
