package com.scg.beans;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

import com.scg.util.Name;

/**
 * A consultant who is kept on staff (receives benefits).
 * 
 * @author chq-alexs
 *
 */
public final class StaffConsultant extends com.scg.domain.Consultant implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Pay rate property name.
	 */
	public static final String PAY_RATE_PROPERTY_NAME = "payRate";

	/**
	 * Vacation hours property name.
	 */
	public static final String VACATION_HOURS_PROPERTY_NAME = "vacationHours";

	/**
	 * Pay rate property name.
	 */
	public static final String SICK_LEAVE_HOURS_PROPERTY_NAME = "sickLeaveHours";

	/**
	 * Vetoable Change Support
	 */
	private VetoableChangeSupport vceListeners = new VetoableChangeSupport(this);

	/**
	 * The consultant's name
	 */
	private Name name;

	/**
	 * The pay rate in cents
	 */
	private int rate;

	/**
	 * The sick leave hours
	 */
	private int sickLeave;

	/**
	 * The vacation hours
	 */
	private int vacation;

	/**
	 * Event Listener List
	 */
	private EventListenerList listenerList = new EventListenerList();

	/**
	 * Action Event
	 */
	ActionEvent actionEvent;

	/**
	 * Change Event
	 */
	ChangeEvent changeEvent;

	/**
	 * Property Change Support
	 */
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Creates a new instance of StaffConsultant
	 * 
	 * @param name
	 *            - the consultant's name
	 * @param rate
	 *            - the pay rate in cents
	 * @param sickLeave
	 *            - the sick leave hours
	 * @param vacation
	 *            - the vacation hours
	 * @throws PropertyVetoException
	 */
	public StaffConsultant(Name name, int rate, int sickLeave, int vacation) {
		super(name);
		this.rate = rate;
		this.sickLeave = sickLeave;
		this.vacation = vacation;
	}

	/**
	 * Get the current pay rate.
	 * 
	 * @return the pay rate in cents
	 */
	public int getPayRate() {
		return this.rate;

	}

	/**
	 * Set the pay rate. Fires the VetoableChange event.
	 * 
	 * @param payRate
	 *            - the pay rate in cents
	 * 
	 * @throws PropertyVetoException
	 *             - if a veto occurs
	 */
	public synchronized void setPayRate(int payRate) throws PropertyVetoException {
		vceListeners.fireVetoableChange("payRate", this.rate, payRate);
		pcs.firePropertyChange("payRate", this.rate, payRate);
		this.rate = payRate;

	}

	/**
	 * Adds a general property change listener.
	 * 
	 * @param l
	 *            - the listener
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}

	/**
	 * Remove a general property change listener.
	 * 
	 * @param l
	 *            - the listener
	 * 
	 */
	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener(l);
	}

	/**
	 * Adds a payRate property change listener.
	 * 
	 * @param l
	 *            - the listener
	 */
	public synchronized void addPayRateListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener("payRate", l);
	}

	/**
	 * Removes a payRate property change listener.
	 * 
	 * @param l
	 *            - the listener
	 * 
	 */
	public synchronized void removePayRateListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener("payRate", l);
	}

	/**
	 * Adds a vetoable change listener.
	 * 
	 * @param l
	 *            - the listener
	 */
	public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
		vceListeners.addVetoableChangeListener(l);

	}

	/**
	 * Removes a vetoable change listener.
	 * 
	 * @param l
	 *            - the listener
	 */
	public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
		vceListeners.removeVetoableChangeListener(l);

	}

	/**
	 * Get the available sick leave.
	 * 
	 * @return the available sick leave hours
	 * 
	 */
	public int getSickLeaveHours() {
		return this.sickLeave;

	}

	/**
	 * Set the sick leave hours. Fires the ProperrtyChange event.
	 * 
	 * @param sickLeaveHours
	 *            - the available sick leave hours
	 * 
	 */
	public synchronized void setSickLeaveHours(int sickLeaveHours) {
		pcs.firePropertyChange("sickLeaveHours", this.sickLeave, sickLeaveHours);
		this.sickLeave = sickLeaveHours;
	}

	/**
	 * Adds a sickLeaveHours property change listener.
	 * 
	 * @param l
	 *            - the listener
	 */
	public synchronized void addSickLeaveHoursListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener("sickLeaveHours", l);
	}

	/**
	 * Removes a sickLeaveHours property change listener.
	 * 
	 * @param l
	 *            - the listener
	 * 
	 */
	public synchronized void removeSickLeaveHoursListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener("sickLeaveHours", l);
	}

	/**
	 * Get the available vacation hours.
	 * 
	 * @return the available vacation hours
	 * 
	 */
	public int getVacationHours() {
		return this.vacation;

	}

	/**
	 * Set the vacation hours. Fires the ProperrtyChange event.
	 * 
	 * @param vacationHours
	 *            - the vacation hours
	 * 
	 */
	public synchronized void setVacationHours(int vacationHours) {
		pcs.firePropertyChange("vacationHours", this.vacation, vacationHours);
		this.vacation = vacationHours;
	}

	/**
	 * Adds a vacationHours property change listener.
	 * 
	 * @param l
	 *            - the listener
	 */
	public synchronized void addVacationHoursListener(PropertyChangeListener l) {
		listenerList.add(PropertyChangeListener.class, l);
		pcs.addPropertyChangeListener("vacationHours", l);
	}

	/**
	 * Removes a vacationHours property change listener.
	 * 
	 * @param l
	 *            - the listener
	 * 
	 */
	public synchronized void removeVacationHoursListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener("vacationHours", l);

	}

}
