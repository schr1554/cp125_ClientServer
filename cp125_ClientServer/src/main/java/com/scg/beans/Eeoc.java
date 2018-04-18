package com.scg.beans;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The EEOC monitors SCG's terminations.
 * 
 * @author chq-alexs
 *
 */
public final class Eeoc implements TerminationListener {

	/**
	 * The forced termination count
	 */
	private static int forcedTerminationCount = 0;
	/**
	 * Voluntary Termination
	 */
	private static int voluntaryTerminationCount = 0;

	/**
	 * Logger for logging
	 */
	private static final Logger log = Logger.getLogger(HumanResourceManager.class.getName());

	/**
	 * Constructor
	 */
	public Eeoc() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.scg.beans.TerminationListener#voluntaryTermination(com.scg.beans.
	 * TerminationEvent)
	 */
	@Override
	public void voluntaryTermination(TerminationEvent evt) {

		log.log(Level.INFO, evt.getConsultant().toString() + " quit.");
		voluntaryTerminationCount++;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scg.beans.TerminationListener#forcedTermination(com.scg.beans.
	 * TerminationEvent)
	 */
	@Override
	public void forcedTermination(TerminationEvent evt) {
		log.log(Level.INFO, evt.getConsultant().toString() + " was fired.");
		forcedTerminationCount++;
	}

	/**
	 * Gets the forced termination count.
	 * 
	 * @return the forced termination count
	 * 
	 */
	public int forcedTerminationCount() {
		return forcedTerminationCount;

	}

	/**
	 * Gets the voluntary termination count.
	 * 
	 * @return the voluntary termination count
	 * 
	 */
	public int voluntaryTerminationCount() {
		return voluntaryTerminationCount;

	}

}
