package com.scg.domain;

import java.io.Serializable;

/**
 * Encapsulates the concept of a non-billable account, such as sick leave,
 * vacation, or business development.
 * 
 * @author chq-alexs
 *
 */
public enum NonBillableAccount implements Account, Serializable {

	/**
	 * Business development.
	 */
	BUSINESS_DEVELOPMENT("Business Development"),
	/**
	 * Sick Leave.
	 */
	SICK_LEAVE("Sick Leave"),
	/**
	 * Vacation
	 */
	VACATION("Vacation");

	// ADD A PRIVATE CONSTRUCTOR
	private final String friendlyName;

	private NonBillableAccount(final String name) {
		this.friendlyName = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scg.domain.Account#getName()
	 */
	@Override
	public String getName() {
		return this.friendlyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scg.domain.Account#isBillable()
	 */
	@Override
	public boolean isBillable() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {

		return this.friendlyName;
	}

}
