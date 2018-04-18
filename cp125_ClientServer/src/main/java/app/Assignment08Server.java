package app;

import java.util.ArrayList;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

import com.scg.net.server.InvoiceServer;
import com.scg.util.ListFactory;

/**
 * The server application for assignment 08.
 * 
 * @author chq-alexs
 */
public final class Assignment08Server {

	/**
	 * The port for the server to listen on.
	 */
	public static final int DEFAULT_PORT = 10888;

	/**
	 * Instantiates an InvoiceServer and starts it.
	 * 
	 * @param args
	 *            - Command line parameters.
	 * 
	 * @throws Exception
	 *             - if the server raises any exceptions
	 * 
	 */
	public static void main(String[] args) throws Exception {

		final List<ClientAccount> accounts = new ArrayList<ClientAccount>();
		final List<Consultant> consultants = new ArrayList<Consultant>();
		final List<TimeCard> timeCards = new ArrayList<TimeCard>();


		ListFactory.populateLists(accounts, consultants, timeCards);

		InvoiceServer invSer = new InvoiceServer(DEFAULT_PORT, accounts, consultants,
				"C:\\Personal\\Java 125 A\\cp125_ClientServer\\target\\invoice\\");

		invSer.run();

	}

}
