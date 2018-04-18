package com.scg.net.cmd;

/**
 * This Command will cause the CommandProcessor to shutdown the server.
 * 
 * @author chq-alexs
 *
 */
public final class ShutdownCommand extends AbstractCommand<Void> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct an ShutdownCommand.
	 * 
	 */
	public ShutdownCommand() {
		super();
	}

	/**
	 * The method called by the receiver. This method must be implemented by
	 * subclasses to send a reference to themselves to the receiver by calling
	 * receiver.execute(this).
	 * 
	 */
	public void execute() {
		getReceiver().execute(this);

	}

}
