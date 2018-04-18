package com.scg.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import com.scg.util.Address;

/**
 * Header for Small Consulting Group Invoices.
 * 
 * @author chq-alexs
 *
 */
public final class InvoiceHeader {

	private final String businessName;
	private final Address businessAddress;
	private final ClientAccount client;
	private final LocalDate invoiceDate;
	private final LocalDate invoiceStartDate;

	/**
	 * Construct an InvoiceHeader.
	 * 
	 * @param businessName
	 *            - name of business issuing invoice
	 * @param businessAddress
	 *            - address of business issuing invoice
	 * @param client
	 *            - client for the invoice with this header.
	 * @param invoiceDate
	 *            - date of the invoice with this header.
	 * @param invoiceForMonth
	 *            - month of billable charges for invoice with this header.
	 */
	public InvoiceHeader(String businessName, Address businessAddress, ClientAccount client, LocalDate invoiceDate,
			LocalDate invoiceForMonth) {
		this.businessName = businessName;
		this.businessAddress = businessAddress;
		this.client = client;
		this.invoiceDate = invoiceDate;
		this.invoiceStartDate = invoiceForMonth;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String consultant = "Consultant";
		String consultantSplitter = "---------------------------";
		String dateHeader = "Date";
		String dateSplitter = "----------";
		String hourHeader = "Hours";
		String hourSplitter = "-----";
		String skillHeader = "Skill";
		String skillSplitter = "------------------";
		String chargeHeader = "Charge";
		String chargeSplitter = "----------";

		StringBuilder report = new StringBuilder();
		report.append(this.businessName + "\n");
		report.append(this.businessAddress.toString() + "\n\n");
		report.append("Invoice for: \n");
		report.append(this.client.getName() + "\n");
		report.append(this.client.getAddress().toString() + "\n");
		report.append(this.client.getContact().toString() + "\n\n");
		report.append("Invoice For Month of: " + invoiceStartDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US)
				+ " " + invoiceStartDate.getYear() + "\n");
		report.append("Invoice Date: " + this.invoiceDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyy")) + "\n\n");
		report.append(String.format("%-11s %-28s %-20s %-6s %-8s %n", dateHeader, consultant, skillHeader, hourHeader,
				chargeHeader));
		report.append(String.format("%-11s %-28s %-20s %-6s %-8s %n", dateSplitter, consultantSplitter, skillSplitter,
				hourSplitter, chargeSplitter));

		// this.invoiceStartDate.getMonth().getDisplayName(TextStyle.FULL,
		// Locale.US)
		// + " " + this.invoiceStartDate.getDayOfMonth() + " " +
		// this.invoiceStartDate.getYear() + "\n\n");
		return report.toString();

	}

}
