package com.scg.net.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import com.scg.net.cmd.AddClientCommand;
import com.scg.net.cmd.AddConsultantCommand;
import com.scg.net.cmd.AddTimeCardCommand;
import com.scg.net.cmd.Command;
import com.scg.net.cmd.CreateInvoicesCommand;
import com.scg.net.cmd.DisconnectCommand;
import com.scg.net.cmd.ShutdownCommand;
import com.scg.net.server.InvoiceServer;
import com.scg.util.Address;
import com.scg.util.ListFactory;
import com.scg.util.Name;
import com.scg.util.StateCode;

/**
 * The client of the InvoiceServer.
 * 
 * @author chq-alexs
 *
 */
public final class InvoiceClient {

	/**
	 * Server Host
	 */
	private String host;
	/**
	 * Server Port to connect
	 */
	private int port;
	/**
	 * List of accounts
	 */
	private List<ClientAccount> accounts = new ArrayList<ClientAccount>();
	/**
	 * List of consultants
	 */
	private List<Consultant> consultants = new ArrayList<Consultant>();
	/**
	 * List of timecards
	 */
	private List<TimeCard> timeCards = new ArrayList<TimeCard>();
	private static final Logger log = Logger.getLogger(InvoiceServer.class.getName());

	/**
	 * Construct an InvoiceClient with a host and port for the server.
	 * 
	 * @param host
	 *            - the host for the server.
	 * @param port
	 *            - the port for the server.
	 * @param timeCardList
	 *            - the list of timeCards to send to the server
	 */
	public InvoiceClient(String host, int port, List<TimeCard> timeCardList) {
		this.host = host;
		this.port = port;
		this.timeCards = timeCardList;
	}

	/**
	 * Runs this InvoiceClient, sending clients, consultants, and time cards to
	 * the server, then sending the command to create invoices for a specified
	 * month.
	 * 
	 */
	public void run() {
		try (Socket socket = new Socket(host, port);) {

			ListFactory.populateLists(accounts, consultants, timeCards);

			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

			sendClients(oos);
			log.info("SENDING CLIENTS");

			sendConsultants(oos);
			log.info("SENDING CONSULTANTS");

			sendTimeCards(oos);
			log.info("SENDING TIMECARDS");

			createInvoices(oos, Month.MARCH, 2006);
			log.info("CREATE INVOICE COMMAND");

			sendDisconnect(oos, socket);
			log.info("DISCONNECT COMMAND");

			sendShutdown(this.host, this.port);
			log.info("SHUTDOWN COMMAND");

		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Send the clients to the server.
	 * 
	 * @param out
	 *            - the output stream connecting this client to the server.
	 * 
	 */
	public void sendClients(ObjectOutputStream out) {

		accounts.add(new ClientAccount("Expeditors Int", new Name("Mau", "Benedict"),
				new Address("1015 3rd Ave", "Seattle", StateCode.WA, "98101")));
		accounts.add(new ClientAccount("Galialo Int", new Name("Schroeder", "Steve"),
				new Address("5397 E Costilla Ave", "Denver", StateCode.CO, "80122")));

		accounts.forEach(client -> {
			final AddClientCommand command = new AddClientCommand(client);
			sendCommand(out, command);
		});

	}

	/**
	 * Send the consultants to the server.
	 * 
	 * @param out
	 *            - the output stream connecting this client to the server.
	 * 
	 */
	public void sendConsultants(ObjectOutputStream out) {
		consultants.add(new Consultant(new Name("Schroeder", "Alex", "J")));
		consultants.add(new Consultant(new Name("Johnston", "Tyler", "J")));

		consultants.forEach(consultant -> {
			final AddConsultantCommand command = new AddConsultantCommand(consultant);
			sendCommand(out, command);
		});

	}

	/**
	 * Send the time cards to the server.
	 * 
	 * @param out
	 *            - the output stream connecting this client to the server.
	 * 
	 */
	public void sendTimeCards(ObjectOutputStream out) {

		timeCards.forEach(tCards -> {
			final AddTimeCardCommand command = new AddTimeCardCommand(tCards);
			sendCommand(out, command);
		});
	}

	/**
	 * Send the command to the server to create invoices for the specified
	 * month.
	 * 
	 * @param out
	 *            - the output stream connecting this client to the server.
	 * @param month
	 *            - the month to create invoice for
	 * @param year
	 *            - the year to create invoice for
	 */
	public void createInvoices(ObjectOutputStream out, java.time.Month month, int year) {

		LocalDate dateCommand = LocalDate.of(year, month, 1);

		final CreateInvoicesCommand command = new CreateInvoicesCommand(dateCommand);
		sendCommand(out, command);

	}

	/**
	 * Send the disconnect command to the server.
	 * 
	 * @param out
	 *            - the output stream connecting this client to the server.
	 * 
	 * @param server
	 *            - the connection to be closed after sending disconnect
	 * 
	 */
	public void sendDisconnect(ObjectOutputStream out, Socket server) {
		DisconnectCommand disconnectCommand = new DisconnectCommand();

		sendCommand(out, disconnectCommand);
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Send the quit command to the server.
	 * 
	 * @param host
	 *            - the host for the server.
	 * 
	 * @param port
	 *            - the port for the server.
	 * 
	 */
	public static void sendShutdown(String host, int port) {
		ShutdownCommand shutdown = new ShutdownCommand();

		try (Socket socket = new Socket(host, port);) {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

			sendCommand(oos, shutdown);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Sends the command to the server to be acted upon.
	 * 
	 * @param out
	 *            the stream to be sent to the server
	 * @param command
	 *            the command to be accepted by the sever.
	 */
	private static void sendCommand(final ObjectOutputStream out, final Command<?> command) {
		try {
			out.writeObject(command);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
