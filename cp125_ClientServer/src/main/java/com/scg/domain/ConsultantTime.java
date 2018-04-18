package com.scg.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A consultants time, maintains date, skill, account and hours data.
 * 
 * @author chq-alexs
 *
 */
public final class ConsultantTime implements Serializable {

	/**
	 * Serializable version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Hash Code for object
	 */
	private Integer hashCode;

	/**
	 * Date
	 */
	private LocalDate date;

	/**
	 * Account
	 */
	private Account account;

	/**
	 * Skil Type
	 */
	private Skill skillType;

	/**
	 * Hours
	 */
	private int hours;

	/**
	 * Creates a new instance of ConsultantTime.
	 * 
	 * @param date
	 *            - The date this instance occurred.
	 * @param account
	 *            - The account to charge the hours to; either a Client or
	 *            NonBillableAccount.
	 * @param skillType
	 *            - The skill type.
	 * @param hours
	 *            - The number of hours, which must be positive.
	 * 
	 * @throws IllegalArgumentException
	 *             - if the hours are <= 0.
	 */
	public ConsultantTime(LocalDate date, Account account, Object skillType, int hours)
			throws IllegalArgumentException {
		setDate(date);
		this.account = account;
		this.skillType = (Skill) skillType;
		setHours(hours);
		this.hours = hours;
	}

	/**
	 * Getter for property date.
	 * 
	 * @return Value of property date
	 * 
	 */
	public LocalDate getDate() {
		return this.date;

	}

	/**
	 * Setter for property date.
	 * 
	 * @param date
	 *            - New value of property date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Getter for property account.
	 * 
	 * @return Value of property account
	 */
	public Account getAccount() {
		return this.account;
	}

	/**
	 * Setter for property account.
	 * 
	 * @param account
	 *            - New value of property account.
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * Determines if the time is billable.
	 * 
	 * @return true if the time is billable otherwise false.
	 * 
	 */
	public boolean isBillable() {

		return this.account.isBillable();
	}

	/**
	 * Getter for property hours.
	 * 
	 * @return Value of property hours.
	 * 
	 */
	public int getHours() {
		return this.hours;
	}

	/**
	 * Setter for property hours.
	 * 
	 * @param hours
	 *            - New value of property hours must be > 0.
	 * @throws IllegalArgumentException
	 *             - if the hours are <= 0.
	 */
	public void setHours(int hours) throws IllegalArgumentException {
		if (hours < 0) {
			throw new IllegalArgumentException("Hours must be a positive integer.");
		}

		this.hours = hours;
	}

	/**
	 * Getter for property skill.
	 * 
	 * @return Value of property skill.
	 */
	public Skill getSkill() {
		return this.skillType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((hashCode == null) ? 0 : hashCode.hashCode());
		result = prime * result + hours;
		result = prime * result + ((skillType == null) ? 0 : skillType.hashCode());
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
		ConsultantTime other = (ConsultantTime) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (hashCode == null) {
			if (other.hashCode != null)
				return false;
		} else if (!hashCode.equals(other.hashCode))
			return false;
		if (hours != other.hours)
			return false;
		if (skillType != other.skillType)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// Formate this using what is done in reportToString in TimeCard.
		return this.date.format(DateTimeFormatter.ofPattern("yyy MM dd")) + " " + this.account.getName() + " "
				+ this.skillType.toString() + " " + this.hours;
	}

}
