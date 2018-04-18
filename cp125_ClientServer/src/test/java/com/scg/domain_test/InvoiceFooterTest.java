package com.scg.domain_test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.Test;

import com.scg.domain.ClientAccount;
import com.scg.domain.Invoice;
import com.scg.domain.InvoiceFooter;
import com.scg.domain.InvoiceHeader;
import com.scg.domain.InvoiceLineItem;
import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class InvoiceFooterTest {

	String name = "TEST";

	StringBuilder invoiceFooterString = new StringBuilder();
	int pageNumber = 2;

	@Test
	public void invoiceFooterConstructor() {

		InvoiceFooter footer = new InvoiceFooter(name);

		assertEquals(false, (footer.toString() == null));
	}

	@Test
	public void invoiceFooterIncrementPageNumberTest() {

		invoiceFooterString.append(String.format("%-69s %-7s %-5s  %n", name, "Page:", pageNumber));
		invoiceFooterString
				.append("===============================================================================" + "\n\n\n");

		InvoiceFooter footer = new InvoiceFooter(name);
		footer.incrementPageNumber();

		assertEquals(invoiceFooterString.toString(), footer.toString());
	}

	@Test
	public void invoiceFooterToStringTest() {

		invoiceFooterString.append(String.format("%-69s %-7s %-5s  %n", name, "Page:", pageNumber));
		invoiceFooterString
				.append("===============================================================================" + "\n\n\n");

		InvoiceFooter footer = new InvoiceFooter(name);
		footer.incrementPageNumber();

		assertEquals(invoiceFooterString.toString(), footer.toString());
	}
}
