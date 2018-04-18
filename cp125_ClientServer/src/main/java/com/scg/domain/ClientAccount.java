package com.scg.domain;

import java.io.Serializable;

import com.scg.util.Address;
import com.scg.util.Name;

/**
 * Encapsulates the information for a billable client account.
 * 
 * @author chq-alexs
 *
 */
public final class ClientAccount implements Account, Serializable, Comparable<ClientAccount> {

	/**
	 * Serializable version ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Hash Code
	 */
	private Integer hashCode;
	/**
	 * contact - Name of the contact person for this client.
	 */
	private Name contact;

	/**
	 * name - String with the name of the client.
	 */
	private final String name;

	/**
	 * address - Address of this client.
	 */
	private Address address;

	/**
	 * Creates a new instance of ClientAccount.
	 * 
	 * @param string
	 *            client company name.
	 * @param name
	 *            client name.
	 * @param address
	 *            Address of this client.
	 */

	public ClientAccount(String name, Name contact, Address address) {

		this.name = name;
		setContact(contact);
		setAddress(address);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scg.domain.Account#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the contact for this account.
	 * 
	 * @return Value of property contact.
	 * 
	 */
	public Name getContact() {
		return this.contact;
	}

	/**
	 * Setter for property contact.
	 * 
	 * @param contact
	 *            - New value of property contact.
	 */
	public void setContact(Name contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return this.address;
	}

	/**
	 * Setter for property address.
	 * 
	 * @param address
	 *            - New value of property address.
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scg.domain.Account#isBillable()
	 */
	@Override
	public boolean isBillable() {
		return true;
	}

	public String toString() {

		StringBuilder clientAccount = new StringBuilder();
		clientAccount.append(this.name + "\n");
		clientAccount.append(this.address.toString() + "\n");
		clientAccount.append(this.contact.toString() + "\n");

		return clientAccount.toString();

	}

	@Override
	public int compareTo(ClientAccount secondClientAccount) {

		int diff = 0;

		diff = (this.name.compareTo(secondClientAccount.name));

		if (diff == 0) {
			this.contact.compareTo(secondClientAccount.contact);
		}

		if (diff == 0) {
			this.address.compareTo(secondClientAccount.address);
		}

		return diff;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((hashCode == null) ? 0 : hashCode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ClientAccount other = (ClientAccount) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (hashCode == null) {
			if (other.hashCode != null)
				return false;
		} else if (!hashCode.equals(other.hashCode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
