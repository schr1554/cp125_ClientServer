package com.scg.domain;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a single billable item to be included in an invoice.
 * 
 * @author chq-alexs
 *
 */
public final class InvoiceLineItem {

	private final LocalDate date;
	private final Consultant consultant;
	private final Skill skill;
	private final int hours;

	/**
	 * Construct an InvoiceLineItem.
	 * 
	 * @param date
	 *            - The date of this line item.
	 * @param consultant
	 *            - Consultant for this line item.
	 * @param skill
	 *            - Skill for this line item.
	 * @param hours
	 *            - Hours for this line item.
	 */
	public InvoiceLineItem(LocalDate date, Consultant consultant, Skill skill, int hours) {

		if (hours <= 0) {
			throw new IllegalArgumentException("");
		}
		this.date = date;
		this.consultant = consultant;
		this.skill = skill;
		this.hours = hours;
	}

	/**
	 * Get the skill for this line item.
	 * 
	 * @return The skill.
	 * 
	 */
	public Consultant getConsultant() {
		return this.consultant;

	}

	/**
	 * Get the hours for this line item.
	 * 
	 * @return The hours.
	 */
	public int getHours() {
		return this.hours;

	}

	/**
	 * Get the charge for this line item.
	 * 
	 * @return The charge.
	 */
	public int getCharge() {

		int charge = (this.hours * this.skill.getRate());

		return charge;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String date = this.date.format(DateTimeFormatter.ofPattern("MM/dd/yyy"));
		int charge = this.hours * this.skill.getRate();
		StringBuilder report = new StringBuilder();

		report.append(String.format("%-11s %-28s %-21s  %3s %11s %n", date, consultant.getName(), this.skill.toString(),
				this.hours, new DecimalFormat("#,###.00").format(charge)));

		return report.toString();

	}

}
