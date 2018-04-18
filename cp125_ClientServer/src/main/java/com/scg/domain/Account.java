package com.scg.domain;

import java.io.Serializable;

/**
 * Interface all accounts must implement.
 * 
 * @author chq-alexs
 *
 */
public interface Account extends Serializable {

	/**
	 * Getter for the name of this account.
	 * 
	 * @return the name of this account.
	 */
	String getName();

	/**
	 * Determines if this account is billable.
	 * 
	 * @return true if the account is billable otherwise false.
	 */
	boolean isBillable();

}
