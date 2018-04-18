package com.scg.beans;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.EventListenerList;

import com.scg.domain.Consultant;

/**
 * Responsible for modifying the pay rate and sick leave and vacation hours of
 * staff consultants.
 * 
 * @author chq-alexs
 *
 */
public final class HumanResourceManager {

	/**
	 * Logger for logging
	 */
	private static final Logger log = Logger.getLogger(HumanResourceManager.class.getName());

	/**
	 * Listener List
	 */
	private EventListenerList listenerList = new EventListenerList();

	/**
	 * The EEOC monitors SCG's terminations.
	 * 
	 */
	Eeoc eeoc = new Eeoc();

	/**
	 * Constructor.
	 * 
	 * @author chq-alexs
	 *
	 */
	public HumanResourceManager() {

	}

	/**
	 * Sets the pay rate for a staff consultant.
	 * 
	 * @param c
	 *            - the consultant
	 * @param newPayRate
	 *            - the new pay rate for the consultant
	 * @throws PropertyVetoException
	 */
	public void adjustPayRate(StaffConsultant c, int newPayRate) {

		String approval = "Approved";

		try {
			log.log(Level.INFO,
					"% change = (" + newPayRate + " - " + c.getPayRate() + ")/" + c.getPayRate() + " = "
							+ String.format("%.6f",
									(((double) newPayRate - (double) c.getPayRate()) / (double) c.getPayRate())));

			c.setPayRate(newPayRate);
		} catch (PropertyVetoException e) {
			approval = "Denied";
		}
		log.log(Level.INFO, approval + " pay adjustment for " + c.getName().toString());

	}

	/**
	 * Sets the sick leave hours for a staff consultant.
	 * 
	 * @param c
	 *            - the consultant
	 * @param newSickLeaveHours
	 *            - the new sick leave hours for the consultant
	 */
	public void adjustSickLeaveHours(StaffConsultant c, int newSickLeaveHours) {
		c.setSickLeaveHours(newSickLeaveHours);
	}

	/**
	 * Sets the vacation hours for a staff consultant.
	 * 
	 * @param c
	 *            - the consultant
	 * @param newVacationHours
	 *            - the new vacation hours for the consultant
	 */
	public void adjustVacationHours(StaffConsultant c, int newVacationHours) {
		c.setVacationHours(newVacationHours);
	}

	/**
	 * Fires a voluntary termination event for the staff consultant.
	 * 
	 * @param c
	 *            - the consultant resigning
	 */
	public void acceptResignation(Consultant c) {
		fireTerminationEvent(new TerminationEvent(this.getClass(), c, true));
	}

	/**
	 * Fires an involuntary termination event for the staff consultant.
	 * 
	 * @param c
	 *            - the consultant being terminated
	 */
	public void terminate(Consultant c) {
		fireTerminationEvent(new TerminationEvent(this.getClass(), c, false));
	}

	/**
	 * Adds a termination listener.
	 * 
	 * @param l
	 */
	public void addTerminationListener(TerminationListener l) {
		listenerList.add(TerminationListener.class, l);
	}

	/**
	 * Removes a termination listener.
	 * 
	 * @param l
	 *            - the listener to remove
	 * 
	 */
	public void removeTerminationListener(TerminationListener l) {
		listenerList.remove(TerminationListener.class, l);
	}

	private void fireTerminationEvent(TerminationEvent evnt) {
		TerminationListener[] listeners;
		listeners = listenerList.getListeners(TerminationListener.class);
		for (TerminationListener listener : listeners) {
			if (evnt.isVoluntary()) {
				listener.voluntaryTermination(evnt);
			} else {
				listener.forcedTermination(evnt);
			}

		}
	}

}
