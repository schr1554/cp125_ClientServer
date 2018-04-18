package com.scg.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Logger;
import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.net.cmd.Command;

/**
 * The server for creation of account invoices based on time cards sent from the
 * client.
 * 
 * @author chq-alexs
 *
 */
public final class InvoiceServer {

	/**
	 * Invoice server port to use.
	 */
	private int port;
	/**
	 * List of clients.
	 */
	private List<ClientAccount> clientList;
	/**
	 * List of consultants
	 */
	private List<Consultant> consultantList;
	/**
	 * Output directory to be used for invoices.
	 */
	private String outputDirectoryName;

	/**
	 * Logger for logging activities by application. 
	 */
	private static final Logger log = Logger.getLogger(InvoiceServer.class.getName());

	/**
	 * Sever socket. 
	 */
	ServerSocket serverSocket;

	/**
	 * Construct an InvoiceServer with a port.
	 * 
	 * @param port
	 *            - The port for this server to listen on
	 * 
	 * @param clientList
	 *            - the initial list of clients
	 * 
	 * @param consultantList
	 *            - the initial list of consultants
	 * 
	 * @param outputDirectoryName
	 *            - the directory to be used for files output by commands
	 * 
	 */
	public InvoiceServer(int port, List<ClientAccount> clientList, List<Consultant> consultantList,
			String outputDirectoryName) {
		this.port = port;
		this.clientList = clientList;
		this.consultantList = consultantList;
		this.outputDirectoryName = outputDirectoryName;
	}

	/**
	 * Run this server, establishing connections, receiving commands, and
	 * sending them to the CommandProcesser.
	 * 
	 */
	public void run() {

		try {
			serverSocket = new ServerSocket(this.port);
			while (!serverSocket.isClosed()) {
				try (Socket client = serverSocket.accept()) {
					serviceConnection(client);
				} catch (final SocketException sx) {
					log.info("Server socket close");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Creates service connection and accepts new objects from stream. 
	 * 
	 * @param client - Socket used to establish connection to client. 
	 */
	protected void serviceConnection(final Socket client) {
		try {
			client.shutdownOutput();

			InputStream is = client.getInputStream();

			ObjectInputStream in = new ObjectInputStream(is);

			final CommandProcessor cmdproc = new CommandProcessor(client, clientList, consultantList, this);
			cmdproc.setOutPutDirectoryName(outputDirectoryName);

			while (!client.isClosed()) {
				Object obj = null;

				try {
					obj = in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (obj == null) {
					client.close();
				} else {
					if (obj instanceof Command<?>) {
						final Command<?> command = (Command<?>) obj;
						command.setReceiver(cmdproc);
						command.execute();
					}
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Shutdown the server.
	 * 
	 */
	void shutdown() {
		if (serverSocket != null && !serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
