package com.scg.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.scg.util.Name;

/**
 * Approves or rejects compensation changes.
 * 
 * @author chq-alexs
 *
 */
public final class CompensationManager implements PropertyChangeListener, VetoableChangeListener {

	/**
	 * Logger
	 */
	private static final Logger log = Logger.getLogger(HumanResourceManager.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.VetoableChangeListener#vetoableChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
		int oldValue = (int) evt.getOldValue();
		int newValue = (int) evt.getNewValue();
		String approval = "APPROVED";

		//DO ALL LOGGING FOR THIS IN HERE NOT IN STAFF CONSULTANT. 
		
		boolean veto = false;
		veto = ((((double) newValue - (double) oldValue) / (double) oldValue) > .05);

		if (veto == true) {
			approval = "REJECTED";
		}

		log.log(Level.INFO,
				approval + " pay rate change, from " + oldValue + " to " + newValue + " for " + evt.getSource());

		if (veto) {
			throw new PropertyVetoException("Payraise Exceeds Allowable Amount.", evt);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		int oldValue = (int) evt.getOldValue();
		int newValue = (int) evt.getNewValue();
		if (("payRate").equals(evt.getPropertyName())) {
			log.log(Level.INFO, "Pay rate changed, from " + oldValue + " to " + newValue + " for " + evt.getSource());

		}

	}

}
