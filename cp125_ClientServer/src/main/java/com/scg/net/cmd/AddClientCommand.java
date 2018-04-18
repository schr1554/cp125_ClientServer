package com.scg.net.cmd;

import com.scg.domain.ClientAccount;

/**
 * The command to add a Client to a list maintained by the server.
 * 
 * @author chq-alexs
 *
 */
@SuppressWarnings("serial")
public final class AddClientCommand extends AbstractCommand<ClientAccount> {

	/**
	 * Construct an AddClientCommand with a target.
	 * 
	 * @param target
	 *            - The target of this Command.
	 * 
	 */
	public AddClientCommand(ClientAccount target) {
		super(target);
	}

	/**
	 * Execute this Command by calling receiver.execute(this).
	 * 
	 */
	public void execute() {
		getReceiver().execute(this);
	}

}
