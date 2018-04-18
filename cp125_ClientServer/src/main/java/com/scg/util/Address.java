package com.scg.util;

import java.io.Serializable;

/**
 * A simple mailing address. Does no validity checking for things like valid
 * states or postal codes. Instances of this class are immutable.
 * 
 * @author chq-alexs
 *
 */
public final class Address implements Serializable, Comparable<Address> {
	/**
	 * Serializable version ID
	 */
	private static final long serialVersionUID = 1L;
	/** Factor used in calculating hashCode. */
	/**
	 * Streetnumber
	 */
	private final String streetNumber;
	/**
	 * City
	 */
	private final String city;
	/**
	 * State
	 */
	private final StateCode state;
	/**
	 * Postal Code
	 */
	private final String postalCode;

	/** Hash code value. */
	private Integer hashCode;

	/**
	 * Construct an Address.
	 * 
	 * @param streetNumber
	 *            - the street number.
	 * @param city
	 *            - the city.
	 * @param state
	 *            - the state.
	 * @param postalCode
	 *            - the postal code.
	 */
	public Address(String streetNumber, String city, StateCode state, String postalCode) {
		this.streetNumber = streetNumber;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;

	}

	/**
	 * Get the street number number for this address.
	 * 
	 * @return the street number.
	 * 
	 */
	public String getStreetNumber() {
		return this.streetNumber;

	}

	/**
	 * Gets the city for this address.
	 * 
	 * @return the city.
	 */
	public String getCity() {
		return this.city;

	}

	/**
	 * Get the state for this address.
	 * 
	 * @return the state.
	 */
	public StateCode getState() {

		return this.state;

	}

	/**
	 * Gets the postal code for this address.
	 * 
	 * @return the postal code.
	 */
	public String getPostalCode() {
		return this.postalCode;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((hashCode == null) ? 0 : hashCode.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (hashCode == null) {
			if (other.hashCode != null)
				return false;
		} else if (!hashCode.equals(other.hashCode))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (state != other.state)
			return false;
		if (streetNumber == null) {
			if (other.streetNumber != null)
				return false;
		} else if (!streetNumber.equals(other.streetNumber))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		StringBuilder addressString = new StringBuilder();
		addressString.append(getStreetNumber() + "\n");
		addressString.append(getCity() + ", " + getState().name() + " " + getPostalCode());

		return addressString.toString();

	}

	public int compareTo(Address secondAddress) {
		/*
		 * this.streetNumber = streetNumber; this.city = city; this.state =
		 * state; this.postalCode = postalCode;
		 */

		int diff = 0;

		diff = (this.state.compareTo(secondAddress.state));

		if (diff == 0) {
			this.postalCode.compareTo(secondAddress.postalCode);
		}

		if (diff == 0) {
			this.city.compareTo(secondAddress.city);
		}

		if (diff == 0) {
			this.streetNumber.compareTo(secondAddress.streetNumber);
		}

		return diff;

	}

}
