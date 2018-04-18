package com.scg.domain_test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.Test;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.Invoice;
import com.scg.domain.InvoiceLineItem;
import com.scg.domain.NonBillableAccount;
import com.scg.domain.Skill;
import com.scg.domain.TimeCard;
import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class InvoiceTest {

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

	@Test
	public void invoiceConstructorTest() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

		assertEquals(invoiceMonth, invoice.getInvoiceMonth());
		assertEquals(clientAccount, invoice.getClientAccount());
		;

	}

	@Test
	public void getStartDateTest() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);
		LocalDate startDate = LocalDate.now();

		assertEquals(startDate, invoice.getStartDate());

	}

	@Test
	public void getInvoiceMonthTest() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

		assertEquals(invoiceMonth, invoice.getInvoiceMonth());

	}

	@Test
	public void getClientAccountTest() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

		assertEquals(clientAccount, invoice.getClientAccount());

	}

	@Test
	public void getInvoicingBusinessProps() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

		InputStream input = null;
		try {
			input = new FileInputStream("invoice.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		StringBuilder businessInvoiceBuilder = new StringBuilder();
		businessInvoiceBuilder.append(prop.get("business.name")).toString();

		assertEquals(businessInvoiceBuilder.toString(), invoice.toString());

	}

	@Test
	public void getTotalHoursTest() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

		Consultant programmer = new Consultant(new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"));

		TimeCard tCard = new TimeCard(programmer, LocalDate.of(2017, 4, 4));

		tCard.addConsultantTime(
				new ConsultantTime(LocalDate.of(2017, 4, 3), this.clientAccount, Skill.SOFTWARE_ENGINEER, 8));

		for (ConsultantTime consultingHour : tCard.getConsultingHours()) {
			if (consultingHour.getDate().getMonth() == this.invoiceMonth) {
				if (consultingHour.getAccount().equals(this.clientAccount)) {
					if (consultingHour.isBillable()) {
					}
					invoice.extractLineItems(tCard);
				}
			}
		}

		int totalInvoiceHours = 8;

		assertEquals(totalInvoiceHours, invoice.getTotalHours());

	}

	@Test
	public void getTotalChargesTest() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

		Consultant programmer = new Consultant(new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"));

		TimeCard tCard = new TimeCard(programmer, LocalDate.of(2017, 4, 4));

		tCard.addConsultantTime(
				new ConsultantTime(LocalDate.of(2017, 4, 3), this.clientAccount, Skill.SOFTWARE_ENGINEER, 8));

		for (ConsultantTime consultingHour : tCard.getConsultingHours()) {
			if (consultingHour.getDate().getMonth() == this.invoiceMonth) {
				if (consultingHour.getAccount().equals(this.clientAccount)) {
					if (consultingHour.isBillable()) {
						invoice.extractLineItems(tCard);
					}
				}
			}
		}

		int totalCharges = 1200;

		assertEquals(totalCharges, invoice.getTotalCharges());

	}

	@Test
	public void extractLineItemsTest() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

		Consultant programmer = new Consultant(new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"));

		TimeCard tCard = new TimeCard(programmer, LocalDate.of(2017, 4, 4));

		tCard.addConsultantTime(
				new ConsultantTime(LocalDate.of(2017, 4, 3), this.clientAccount, Skill.SOFTWARE_ENGINEER, 8));

		for (ConsultantTime consultingHour : tCard.getConsultingHours()) {
			if (consultingHour.getDate().getMonth() == this.invoiceMonth) {
				if (consultingHour.getAccount().equals(this.clientAccount)) {
					if (consultingHour.isBillable()) {
						invoice.extractLineItems(tCard);
					}
				}
			}
		}

		int totalCharges = 1200;

		assertEquals(totalCharges, invoice.getTotalCharges());

	}

	@Test
	public void toStringTest() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

		InputStream input = null;
		try {
			input = new FileInputStream("invoice.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		StringBuilder businessInvoiceBuilder = new StringBuilder();
		businessInvoiceBuilder.append(prop.get("business.name")).toString();

		assertEquals(businessInvoiceBuilder.toString(), invoice.toString());

	}

	@Test
	public void toReportTest() {
		Invoice invoice = new Invoice(clientAccount, invoiceMonth, invoiceYear);

		Consultant programmer = new Consultant(new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"));

		TimeCard tCard = new TimeCard(programmer, LocalDate.of(2017, 4, 4));

		tCard.addConsultantTime(
				new ConsultantTime(LocalDate.of(2017, 4, 3), this.clientAccount, Skill.SOFTWARE_ENGINEER, 8));

		for (ConsultantTime consultingHour : tCard.getConsultingHours()) {
			if (consultingHour.getDate().getMonth() == this.invoiceMonth) {
				if (consultingHour.getAccount().equals(this.clientAccount)) {
					if (consultingHour.isBillable()) {
						invoice.extractLineItems(tCard);
					}
				}
			}
		}

		assertEquals(false, (invoice.toReportString() == null));
	}

}
