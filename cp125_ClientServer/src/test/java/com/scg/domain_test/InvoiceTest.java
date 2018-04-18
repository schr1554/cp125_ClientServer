package com.scg.domain_test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.Invoice;
import com.scg.domain.NonBillableAccount;
import com.scg.domain.Skill;
import com.scg.domain.TimeCard;
import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

/**
 * JUnit test for Invoice class.
 */
public final class InvoiceTest {
	/** String constant for "FooBar Enterprises". */
	private static final String FOOBAR = "FooBar.com";
	/** String constant for "Client". */
	private static final String CLIENT = "Client";
	/** String constant for "J.". */
	private static final String J_DOT = "J.";
	/** String constant for "Random". */
	private static final String RANDOM = "Random";
	/** String constant for "Coder". */
	private static final String CODER = "Coder";
	/** String constant for "Carl". */
	private static final String CARL = "Carl";
	/** String constant for street address. */
	private static final String STREET = "1024 Kilobyte Dr.";
	/** String constant for city. */
	private static final String CITY = "Silicone Gulch";
	/** String constant for ZIP code. */
	private static final String ZIP = "94105";

	/** Number of hours in a standard working day. */
	private static final int HOURS_PER_DAY = 8;

	/** The first Monday of the test month. */
	private static final int TEST_START_FIRST_WEEK = 6;

	/** Expected total hours. */
	private static final int TOTAL_HOURS = 32;

	/** Expected total charges. */
	private static final int TOTAL_CHARGES = 4800;

	/** The test year. */
	private static final int TEST_YEAR = 2006;

	/** Invoice instance for test. */
	private Invoice invoice;

	/** Consultant instance for test. */
	private Consultant programmer;

	/** TimeCard instance for test. */
	private TimeCard timecard;

	/** ClientAccount instance for test. */
	private ClientAccount client;

	/** Current date for test. */
	private LocalDate currentDate;

	/** NonBillableAccount instance for test. */
	private NonBillableAccount vacation;

	/**
	 * Perform test setup.
	 */
	@Before
	public void setUp() {

		client = new ClientAccount(FOOBAR, new Name(CLIENT, J_DOT, RANDOM),
				new Address(STREET, CITY, StateCode.CA, ZIP));

		invoice = new Invoice(client, Month.MARCH, TEST_YEAR);
		programmer = new Consultant(new Name(CODER, CARL));
		vacation = NonBillableAccount.VACATION;

		currentDate = LocalDate.of(TEST_YEAR, Month.MARCH, TEST_START_FIRST_WEEK);
		timecard = new TimeCard(programmer, currentDate);
		timecard.addConsultantTime(new ConsultantTime(currentDate, client, Skill.SOFTWARE_ENGINEER, HOURS_PER_DAY));
		currentDate = currentDate.plusDays(1);

		timecard.addConsultantTime(new ConsultantTime(currentDate, client, Skill.SOFTWARE_ENGINEER, HOURS_PER_DAY));
		currentDate = currentDate.plusDays(1);
		timecard.addConsultantTime(new ConsultantTime(currentDate, client, Skill.SOFTWARE_ENGINEER, HOURS_PER_DAY));
		currentDate = currentDate.plusDays(1);
		timecard.addConsultantTime(new ConsultantTime(currentDate, client, Skill.SOFTWARE_ENGINEER, HOURS_PER_DAY));
		currentDate = currentDate.plusDays(1);
		timecard.addConsultantTime(new ConsultantTime(currentDate, vacation, Skill.SOFTWARE_ENGINEER, HOURS_PER_DAY));
		// Add a ConsultantTime that is out of the date range
		currentDate = currentDate.plusMonths(1);

		timecard.addConsultantTime(new ConsultantTime(currentDate, client, Skill.SOFTWARE_ENGINEER, HOURS_PER_DAY));
		invoice.extractLineItems(timecard);
	}

	/**
	 * Test the getStartDate method.
	 */
	@Test
	public void testGetStartDate() {
		LocalDate expectedDate = LocalDate.of(TEST_YEAR, Month.MARCH, 1);

		assertEquals("getStartDate() failed: ", expectedDate, invoice.getStartDate());
	}

	/**
	 * Test the getInvoiceMonth method.
	 */
	@Test
	public void testGetInvoiceMonth() {
		assertEquals("getInvoiceMonth() failed: ", Month.MARCH, invoice.getInvoiceMonth());
	}

	/**
	 * Test the getTotalHours method.
	 */
	@Test
	public void testGetTotalHours() {
		assertEquals("getTotalHours() failed: ", TOTAL_HOURS, invoice.getTotalHours());
	}

	/**
	 * Test the gettotalCharges method.
	 */
	@Test
	public void testGetTotalCharges() {
		assertEquals("getTotalCharges() failed: ", TOTAL_CHARGES, invoice.getTotalCharges());
	}
}
