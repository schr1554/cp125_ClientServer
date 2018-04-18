package com.scg.util;

import java.io.Serializable;

/**
 * Encapsulates the first, middle and last name of a person.
 * 
 * @author chq-alexs
 *
 */
public class Name implements Comparable<Name>, Serializable {
	/**
	 * Serializable version ID
	 */
	private static final long serialVersionUID = 1L;
	private Integer hashCode;
	private String lastName;
	private String middleName;
	private String firstName;

	/**
	 * Creates a new instance of Name
	 * 
	 */
	public Name() {

	}

	/**
	 * Construct a Name.
	 * 
	 * @param lastName
	 *            - Value for the last name.
	 * @param firstName
	 *            - Value for the first name.
	 */
	public Name(String lastName, String firstName) {
		setLastName(lastName);
		setFirstName(firstName);
		setMiddleName("NMN");
	}

	/**
	 * Construct a Name.
	 * 
	 * @param lastName
	 *            - Value for the last name.
	 * @param firstName
	 *            - Value for the first name.
	 * @param middleName
	 *            - Value for the middle name.
	 */
	public Name(String lastName, String firstName, String middleName) {
		setLastName(lastName);
		setFirstName(firstName);
		setMiddleName(middleName);

		// this.hash = calcHashCode();
	}

	/**
	 * Getter for property firstName.
	 * 
	 * @return Value of property firstName.
	 * 
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Setter for property first.
	 * 
	 * @param firstName
	 *            - New value of property firstName.
	 * 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for property lastName.
	 * 
	 * @return Value of property lastName.
	 * 
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Setter for property lastName.
	 * 
	 * @param lastName
	 *            - New value of property lastName.
	 * 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for property middleName.
	 * 
	 * @return Value of property middleName.
	 * 
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Setter for property middleName.
	 * 
	 * @param middleName
	 *            - New value of property middleName.
	 * 
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hashCode == null) ? 0 : hashCode.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
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
		Name other = (Name) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hashCode == null) {
			if (other.hashCode != null)
				return false;
		} else if (!hashCode.equals(other.hashCode))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String out = this.lastName + ", " + this.firstName + " " + this.middleName;
		return out;
		// return name.toString();

	}

	public int compareTo(Name secondName) {

		int diff = 0;

		diff = (this.lastName.compareTo(secondName.lastName));

		if (diff == 0) {
			diff = this.firstName.compareTo(secondName.firstName);
		}

		if (diff == 0) {
			diff = this.middleName.compareTo(secondName.middleName);
		}

		return diff;

	}

}
