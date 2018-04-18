package com.scg.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logs changes in benefits.
 * 
 * @author chq-alexs
 *
 */
public final class BenefitManager implements PropertyChangeListener {

	/**
	 * Logger
	 */
	private static final Logger log = Logger.getLogger(HumanResourceManager.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		final int oldValue = (int) evt.getOldValue();
		final int newValue = (int) evt.getNewValue();
		if (!("payRate").equals(evt.getPropertyName())) {
			log.log(Level.INFO, evt.getPropertyName() + " changed from " + oldValue + " to " + newValue + " for "
					+ evt.getSource());

		}

		// log.log(Level.INFO,
		// evt.getPropertyName() + " changed from " + oldValue + " to " +
		// newValue + " for " + evt.getSource());
		// ((StaffConsultant)evt.getSource()).getName())

	}

}
