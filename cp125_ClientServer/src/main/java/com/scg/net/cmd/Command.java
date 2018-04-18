package com.scg.net.cmd;

import java.io.Serializable;

import com.scg.net.server.CommandProcessor;

/**
 * The superclass of all Command objects, implements the command role in the
 * Command design pattern.
 * 
 * @author chq-alexs
 *
 * @param <T>
 */
public interface Command<T> extends Serializable {

	/**
	 * Gets the CommandProcessor receiver for this Command.
	 * 
	 * @return the receiver for this Command.
	 * 
	 */
	CommandProcessor getReceiver();

	/**
	 * Set the CommandProcessor that will execute this Command.
	 * 
	 * @param receiver
	 *            - the receiver for this Command.
	 * 
	 */
	void setReceiver(CommandProcessor receiver);

	/**
	 * Return the target of this Command.
	 * 
	 * @return the target.
	 * 
	 */
	T getTarget();

	/**
	 * The method called by the receiver. This method must be implemented by
	 * subclasses to send a reference to themselves to the receiver by calling
	 * receiver.execute(this).
	 * 
	 */
	void execute();

}
