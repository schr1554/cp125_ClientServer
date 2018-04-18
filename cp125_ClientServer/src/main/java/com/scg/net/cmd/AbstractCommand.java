package com.scg.net.cmd;

import com.scg.net.server.CommandProcessor;

/**
 * The superclass of all Command objects, implements the command role in the
 * Command design pattern.
 * 
 * @author chq-alexs
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public abstract class AbstractCommand<T> extends Object implements Command<T> {
	/**
	 * This keeps the command processer transient.
	 */
	private transient CommandProcessor reveiver;

	/**
	 * The target from each command.
	 */
	private T target;

	/**
	 * Construct an AbstractCommand without a target; called from subclasses.
	 */
	public AbstractCommand() {

	}

	/**
	 * Construct an AbstractCommand with a target; called from subclasses.
	 * 
	 * @param target
	 *            - the target
	 * 
	 */
	public AbstractCommand(T target) {
		this.target = target;
	}

	/**
	 * Gets the CommandProcessor receiver for this Command.
	 * 
	 * @return the receiver for this Command.
	 * 
	 */
	public final CommandProcessor getReceiver() {
		return this.reveiver;

	}

	/**
	 * Set the CommandProcessor that will execute this Command.
	 * 
	 * @param receiver
	 *            - the receiver for this Command.
	 * 
	 */
	public final void setReceiver(CommandProcessor receiver) {
		this.reveiver = receiver;
	}

	/**
	 * Return the target of this Command.
	 * 
	 * @return the target.
	 * 
	 */
	public final T getTarget() {
		return this.target;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return null;

	}

}
