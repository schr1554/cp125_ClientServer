package com.scg.domain;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Properties;
import com.scg.util.Address;
import com.scg.util.StateCode;

/**
 * Invoice encapsulates the attributes and behavior to create client invoices
 * for a given time period from time cards. The invoicing business' name and
 * address are obtained from a properties file. The name of the property file is
 * specified by the PROP_FILE_NAME static member.
 * 
 * @author chq-alexs
 *
 */
public final class Invoice {

	private final ClientAccount client;
	private final Month invoiceMonth;
	private final int invoiceYear;
	private int totalInvoiceHours;
	private int totalCharges;
	private ArrayList<InvoiceLineItem> invoiceLineItemList = new ArrayList<InvoiceLineItem>();
	private final static Address address;
	private final static String businessName;
	private LocalDate startDate;
	// Properties prop = new Properties();

	/**
	 * Construct an Invoice for a client. The time period is set from the
	 * beginning to the end of the month specified.
	 * 
	 * @param client
	 *            - Client for this Invoice.
	 * @param invoiceMonth
	 *            - Month for which this Invoice is being created.
	 * @param invoiceYear
	 *            - Year for which this Invoice is being created.
	 */

	// ADD STATIC Intialized Block with a ClassLoader *getResouceAsStream

	static {
		final Properties invoiceProps = new Properties();
		try (InputStream in = Invoice.class.getClassLoader().getResourceAsStream("invoice.properties")) {
			invoiceProps.load(in);
		} catch (final IOException e) {
			// Logger.warn("Unable to load properties");s
		}
		address = new Address(invoiceProps.get("business.street").toString(),
				invoiceProps.get("business.city").toString(),
				StateCode.valueOf(invoiceProps.getProperty("business.state")),
				invoiceProps.get("business.zip").toString());
		businessName = invoiceProps.get("business.name").toString();

	}

	// END OF NEW STATIC BLOCK

	public Invoice(ClientAccount client, Month invoiceMonth, int invoiceYear) {
		this.client = client;
		this.invoiceMonth = invoiceMonth;
		this.invoiceYear = invoiceYear;
		startDate = LocalDate.of(invoiceYear, invoiceMonth, 1);

	}

	/**
	 * Get the start date for this Invoice, this is the earliest date/time a
	 * ConsultantTime instance may have and still be billed on this invoice.
	 * 
	 * @return Start date.
	 */
	public LocalDate getStartDate() {

		return this.startDate;
	}

	/**
	 * Get the client for this Invoice.
	 * 
	 * @return the client.
	 */
	public Month getInvoiceMonth() {
		return this.invoiceMonth;

	}

	private LocalDate getInvoiceDate() {
		LocalDate invoiceDate = LocalDate.now();
		invoiceDate = invoiceDate.withMonth(getInvoiceMonth().getValue());
		invoiceDate = invoiceDate.withYear(this.invoiceYear);
		return invoiceDate;
	}

	/**
	 * Get the client for this Invoice.
	 * 
	 * @return the client.
	 */
	public ClientAccount getClientAccount() {

		return this.client;

	}

	/**
	 * Get the total hours for this Invoice.
	 * 
	 * @return Total hours.
	 */
	public int getTotalHours() {
		return this.totalInvoiceHours;

	}

	/**
	 * Get the total charges for this Invoice.
	 * 
	 * @return Total charges.
	 */
	public int getTotalCharges() {

		return this.totalCharges;

	}

	/**
	 * Add an invoice line item to this Invoice.
	 * 
	 * @param lineItem
	 *            - InvoiceLineItem to add.
	 */
	public void addLineItem(InvoiceLineItem lineItem) {
		invoiceLineItemList.add(lineItem);
		this.totalCharges += lineItem.getCharge();
		this.totalInvoiceHours += lineItem.getHours();
	}

	/**
	 * Extract the billable hours for this Invoice's client from the input
	 * TimeCard and add them to the line items. Only those hours for the client
	 * and month unique to this invoice will be added.
	 * 
	 * @param timeCard
	 *            - the TimeCard potentially containing line items for this
	 *            Invoices client.
	 */
	public void extractLineItems(TimeCard timeCard) {

		timeCard.getBillableHoursForClient(client.getName()).stream()
				.filter(cTime -> this.startDate.getMonth().equals(cTime.getDate().getMonth()))
				.forEach(cTime -> addLineItem(new InvoiceLineItem(cTime.getDate(), timeCard.getConsultant(),
						cTime.getSkill(), cTime.getHours())));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	public String toString() {

		StringBuilder invoiceString = new StringBuilder();
		invoiceString.append(businessName);
		return invoiceString.toString();

	}

	/**
	 * Create a formatted string containing the printable invoice. Includes a
	 * header and footer on each page.
	 * 
	 * @return The formatted invoice as a string.
	 */
	public String toReportString() {

		StringBuilder report = new StringBuilder();
		InvoiceFooter footer = new InvoiceFooter(businessName);
		InvoiceHeader header = new InvoiceHeader(businessName, address, this.client, getStartDate(), getInvoiceDate());

		report.append(header.toString());

		int lineItemsCount = 1;

		for (InvoiceLineItem temp : this.invoiceLineItemList) {
			if (lineItemsCount < 5) {
				report.append(temp.toString());
				lineItemsCount++;
				continue;
			} else {
				lineItemsCount = 1;
				report.append(temp.toString() + "\n");
				report.append(footer.toString());
				footer.incrementPageNumber();
				report.append(header.toString());
			}

		}
		report.append("\n");
		report.append(String.format("%-57s %9s %11s %n %n %n", "Total: ", this.totalInvoiceHours,
				new DecimalFormat("#,###.00").format(this.totalCharges)));
		report.append(footer.toString());
		report.append("\n");
		return report.toString();

	}

}
