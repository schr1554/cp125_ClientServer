package com.scg.net.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;
import com.scg.net.cmd.AddClientCommand;
import com.scg.net.cmd.AddConsultantCommand;
import com.scg.net.cmd.AddTimeCardCommand;
import com.scg.net.cmd.CreateInvoicesCommand;
import com.scg.net.cmd.DisconnectCommand;
import com.scg.net.cmd.ShutdownCommand;

/**
 * The command processor for the invoice server. Implements the receiver role in
 * the Command design pattern.
 * 
 * @author chq-alexs
 *
 */
public final class CommandProcessor {
	private static final Logger log = Logger.getLogger(InvoiceServer.class.getName());

	/**
	 * Socket connection for the server.
	 */
	private Socket connection;

	/**
	 * List of clients.
	 */
	private List<ClientAccount> clientList;

	/**
	 * Lis of consultants.
	 */
	private List<Consultant> consultantList;

	/**
	 * Server to be used.
	 */
	private InvoiceServer server;

	/**
	 * Output directory for the invoices
	 */
	private String outPutDirectoryName;

	/**
	 * List of timecards used to create invoices.
	 */
	private List<TimeCard> timeCards = new ArrayList<TimeCard>();

	/**
	 * Construct a CommandProcessor.
	 * 
	 * @param connection
	 *            - the Socket connecting the server to the client.
	 * 
	 * @param clientList
	 *            - the ClientList to add Clients to.
	 * 
	 * @param consultantList
	 *            - the ConsultantList to add Consultants to.
	 * 
	 * @param server
	 *            - the server that created this command processor
	 * 
	 */
	public CommandProcessor(Socket connection, List<ClientAccount> clientList, List<Consultant> consultantList,
			InvoiceServer server) {

		this.connection = connection;
		this.clientList = clientList;
		this.consultantList = consultantList;
		this.server = server;

	}

	/**
	 * Set the output directory name.
	 * 
	 * @param outPutDirectoryName
	 *            - the output directory name.
	 * 
	 */
	public void setOutPutDirectoryName(String outPutDirectoryName) {
		this.outPutDirectoryName = outPutDirectoryName;
	}

	/**
	 * Execute and AddTimeCardCommand.
	 * 
	 * @param command
	 *            - the command to execute.
	 * 
	 */
	public void execute(AddTimeCardCommand command) {
		log.info("ADDING TIMECARD DATE STARTING: " + command.getTarget().getWeekStartingDay());
		timeCards.add(command.getTarget());
	}

	/**
	 * Execute an AddClientCommand.
	 * 
	 * @param command
	 *            - the command to execute.
	 * 
	 */
	public void execute(AddClientCommand command) {
		log.info("ADDING CLIENT " + command.getTarget());
		clientList.add(command.getTarget());
	}

	/**
	 * Execute and AddConsultantCommand.
	 * 
	 * @param command
	 *            - the command to execute.
	 * 
	 */
	public void execute(AddConsultantCommand command) {
		log.info("ADDING CONSULTANT " + command.getTarget());
		consultantList.add(command.getTarget());
	}

	/**
	 * Execute a CreateInvoicesCommand.
	 * 
	 * @param command
	 *            - the command to execute.
	 * 
	 */
	public void execute(CreateInvoicesCommand command) {

		log.info("WRITING INVOICE");

		for (final ClientAccount account : clientList) {
			final Invoice invoice = new Invoice(account, command.getTarget().getMonth(), command.getTarget().getYear());
			for (final TimeCard currentTimeCard : timeCards) {

				invoice.extractLineItems(currentTimeCard);

			}

			if (invoice.getTotalHours() > 0) {
				String filePath = outPutDirectoryName + account.getName() + command.getTarget().getMonth() + ".txt";

				File theDir = new File(outPutDirectoryName);

				if (!theDir.exists()) {
					try {
						theDir.mkdir();
					} catch (SecurityException se) {
					}
				}

				File file = new File(filePath);
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				try {
					PrintStream ps = new PrintStream(file);
					ps.append(invoice.toReportString());
					ps.close();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				try {
					FileWriter fw = new FileWriter(filePath);

					fw.write(invoice.toReportString());
					fw.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Execute a DisconnectCommand.
	 * 
	 * @param command
	 *            - the input DisconnectCommand.
	 * 
	 */
	public void execute(DisconnectCommand command) {

		log.info("DISCONNECTING FROM SERVER");

		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute a ShutdownCommand. Closes any current connections, stops
	 * listening for connections and then terminates the server, without calling
	 * System.exit.
	 * 
	 * @param command
	 *            - the input ShutdownCommand.
	 * 
	 */
	public void execute(ShutdownCommand command) {

		log.info("SERVER SHUTDOWN");
		try {
			connection.close();
			server.shutdown();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
