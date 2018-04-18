package com.scg.domain;

/**
 * Footer for Small Consulting Group Invoices.
 * 
 * @author chq-alexs
 *
 */
public final class InvoiceFooter {

	private final String businessName;
	private int pageNumber = 1;

	/**
	 * Construct an InvoiceFooter.
	 * 
	 * @param businessName
	 *            - name of buisness to include in footer
	 * 
	 */
	public InvoiceFooter(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * Increment the current page number by one.
	 * 
	 */
	public void incrementPageNumber() {
		pageNumber++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		StringBuilder invoiceFooterString = new StringBuilder();

		invoiceFooterString.append(String.format("%-69s %-7s %-5s  %n", this.businessName, "Page:", this.pageNumber));
		invoiceFooterString
				.append("===============================================================================" + "\n\n\n");

		return invoiceFooterString.toString();

	}

}
