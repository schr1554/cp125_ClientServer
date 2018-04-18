package com.scg.net.cmd;

import com.scg.domain.TimeCard;

/**
 * Command that adds a TimeCard to the server's list of TimeCards.
 * 
 * @author chq-alexs
 *
 */
@SuppressWarnings("serial")
public final class AddTimeCardCommand extends AbstractCommand<TimeCard> {

	/**
	 * Construct an AddTimeCardCommand with a target.
	 * 
	 * @param target
	 *            - the target of this Command.
	 * 
	 */
	public AddTimeCardCommand(TimeCard target) {
		super(target);
	}

	/**
	 * Execute this command by calling receiver.execute(this).
	 * 
	 */
	public void execute() {
		getReceiver().execute(this);
	}

}
