package com.scg.domain_test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.Test;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.Invoice;
import com.scg.domain.InvoiceHeader;
import com.scg.domain.InvoiceLineItem;
import com.scg.domain.Skill;
import com.scg.domain.TimeCard;
import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class InvoiceHeaderTest {

	String name = "TEST";
	Name client = new Name("Coyote", "Wiley");

	String streetNumber = "TEST_STREET";
	String city = "TEST_CITY";
	StateCode state = StateCode.CO;
	String postalCode = "TEST POSTAL CODE";

	Address address = new Address(streetNumber, city, state, postalCode);

	public ClientAccount clientAccount = new ClientAccount(name, client, address);
	public Month invoiceMonth = Month.APRIL;
	public int invoiceYear = 2017;
	public int totalInvoiceHours;
	public int totalCharges;
	public ArrayList<InvoiceLineItem> invoiceLineItemList = new ArrayList<InvoiceLineItem>();
	Properties prop = new Properties();

	Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

	@Test
	public void invoiceHeaderConstructor() {

		InvoiceHeader header = new InvoiceHeader(name, address, clientAccount, invoice.getStartDate(), LocalDate.now());

		assertEquals(false, (header.toString() == null));
	}

	@Test
	public void invoiceHeaderToString() {

		InvoiceHeader header = new InvoiceHeader(name, address, clientAccount, invoice.getStartDate(), LocalDate.now());

		assertEquals(false, (header.toString() == null));
	}

}
