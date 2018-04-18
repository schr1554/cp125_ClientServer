package com.scg.domain;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.slf4j.LoggerFactory;

import com.scg.util.Name;

/**
 * 
 * A consultant.
 * 
 * @author chq-alexs
 *
 */
public class Consultant implements Serializable, Comparable<Consultant> {

	/**
	 * Serializable version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This class' logger.
	 */
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(Consultant.class);
	/**
	 * Consultant name.
	 */
	private Name name;

	/**
	 * Hash Code for object
	 */
	private Integer hashCode;

	/**
	 * Creates new consultant.
	 * 
	 * @param name
	 *            name of consultant.
	 */
	public Consultant(Name name) {
		this.name = name;
	}

	/**
	 * Getter for property name.
	 * 
	 * @return Value of property name.
	 */
	public final Name getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public final String toString() {
		return this.name.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Consultant other = (Consultant) obj;
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

	@Override
	public int compareTo(Consultant secondConsultant) {

		int diff = 0;

		diff = this.name.compareTo(secondConsultant.getName());

		return diff;
	}

	/**
	 * Write the serialized obejct using a proxy.
	 * 
	 * @return Consultant object serialized.
	 */
	private Object writeReplace() {
		return new SerializationProxy(this);
	}

	/**
	 * @param ois
	 *            object input stream.
	 * @throws InvalidObjectException
	 *             if proxy is not used when deserialzing.
	 */
	private void readObject(ObjectInputStream ois) throws InvalidObjectException {
		throw new InvalidObjectException("Proxy required");
	}

	/**
	 * Serialization Proxy for consultant object.
	 * 
	 * @author chq-alexs
	 *
	 */
	private static class SerializationProxy implements Serializable {
		/**
		 * Serializable version ID
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * First name.
		 */
		String y;
		/**
		 * Last name.
		 */
		String x;
		/**
		 * Middle name.
		 */
		String z;

		/**
		 * @param consultant
		 */
		SerializationProxy(final Consultant consultant) {
			y = consultant.name.getFirstName();
			x = consultant.name.getLastName();
			z = consultant.name.getMiddleName();
			final String msg = String.format("De-Serialized consultant: %s, %s, %s", x, y, z);
			log.info(msg);

		}

		/**
		 * Read proxy in.
		 * 
		 * @return Consultant object.
		 */
		private Object readResolve() {
			return new Consultant(new Name(x, y, z));
		}
	}

}
