package app;

import java.util.ArrayList;
import java.util.List;

import com.scg.domain.TimeCard;
import com.scg.net.client.InvoiceClient;

/**
 * The client application for Assignment08.
 * 
 * @author chq-alexs
 *
 */
public final class Assignment08 {

	/**
	 * Instantiates an InvoiceClient, provides it with a set of timecards to
	 * server the server and starts it running.
	 * 
	 * @param args
	 *            - Command line parameters - not used.
	 * @throws Exception
	 *             - if anything goes awry
	 */
	public static void main(String[] args) throws Exception {

		final List<TimeCard> timeCards = new ArrayList<TimeCard>();
		final InvoiceClient netClient = new InvoiceClient("127.0.0.1", 10888, timeCards);

		netClient.run();

	}

}
