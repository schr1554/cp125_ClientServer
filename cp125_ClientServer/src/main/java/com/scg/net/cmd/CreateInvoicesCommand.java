package com.scg.net.cmd;

/**
 * The command to create invoices for a specified month.
 * 
 * @author chq-alexs
 *
 */
@SuppressWarnings("serial")
public final class CreateInvoicesCommand extends AbstractCommand<java.time.LocalDate> {

	/**
	 * Construct a CreateInvoicesCommand with a target month, which should be
	 * obtained by getting the desired month constant from Calendar.
	 * 
	 * @param target
	 *            - the target month.
	 * 
	 */
	public CreateInvoicesCommand(java.time.LocalDate target) {
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
