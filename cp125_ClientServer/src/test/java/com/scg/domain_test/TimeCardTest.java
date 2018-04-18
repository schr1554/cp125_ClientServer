package com.scg.domain_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.NonBillableAccount;
import com.scg.domain.Skill;
import com.scg.domain.TimeCard;
import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

/**
 * JUnit test for TimeCard class.
 */
public final class TimeCardTest {
	/** The test year. */
	private static final int TEST_YEAR = 2004;
	/** The test start day. */
	private static final int START_DAY = 6;
	/** String constant for "Acme Industries'. */
	private static final String ACME = "Acme Industries";
	/** String constant for "Contact'. */
	private static final String CONTACT = "Contact";
	/** String constant for "Guy'. */
	private static final String GUY = "Guy";
	/** Street address. */
	private static final String STREET = "1616 Index Ct.";
	/** String constant for "XX'. */
	private static final String CITY = "Redmond";
	/** Address ZIP code. */
	private static final String ZIP = "98055";
	/** String constant for "Programmer'. */
	private static final String PROGRAMMER = "Programmer";
	/** String constant for "J.'. */
	private static final String J_DOT = "J.";
	/** String constant for "Random'. */
	private static final String RANDOM = "Random";
	/** Constant for work hours per day. */
	private static final int WORK_DAY_HOURS = 8;
	/** Consulting hours. */
	private static final int CONSULTING_HOURS = 2;
	/** Tital hours. */
	private static final int TOTAL_HOURS = 16;

	/** TimeCard instance for test. */
	private TimeCard timecard;
	/** ClientAccount instance for test. */
	private ClientAccount client;
	/** Test start date. */
	private final LocalDate date = LocalDate.of(TEST_YEAR, Month.JANUARY, START_DAY);

	/**
	 * Perform test setup.
	 */
	@Before
	public void setUp() {
		client = new ClientAccount(ACME, new Name(CONTACT, GUY), new Address(STREET, CITY, StateCode.WA, ZIP));
		final NonBillableAccount nonbillableaccount = NonBillableAccount.VACATION;
		timecard = new TimeCard(new Consultant(new Name(PROGRAMMER, J_DOT, RANDOM)), date);
		ConsultantTime consultantTime = new ConsultantTime(date, client, Skill.SYSTEM_ARCHITECT, WORK_DAY_HOURS);
		timecard.addConsultantTime(consultantTime);
		date.plusDays(1);
		consultantTime = new ConsultantTime(date, nonbillableaccount, Skill.SYSTEM_ARCHITECT, WORK_DAY_HOURS);
		timecard.addConsultantTime(consultantTime);
	}

	/**
	 * Test the constructors.
	 */
	public void testConstructor() {
		assertNotNull("TimeCard constructor failed", timecard);
		assertNotNull("TimeCard constructor didn't set consultant", timecard.getConsultant());
		assertNotNull("TimeCard constructor didn't set consultingHours", timecard.getConsultingHours());
		assertNotNull("TimeCard constructor didn't set weekStartingDay", timecard.getWeekStartingDay());
		assertEquals("TimeCard constructor didn't set totalBillableHours", 0, timecard.getTotalBillableHours());
		assertEquals("TimeCard constructor didn't set totalNonBillableHours", 0, timecard.getTotalNonBillableHours());
		assertEquals("TimeCard constructor didn't set totalHours", 0, timecard.getTotalHours(), Double.MIN_VALUE);
	}

	/**
	 * Tests the getConsultant method.
	 */
	@Test
	public void testGetConsultant() {
		assertNotNull("getConsultant() failed", timecard.getConsultant());
	}

	/**
	 * Tests the getTotalBillableHours method.
	 */
	@Test
	public void testGetTotalBillableHours() {
		assertEquals(WORK_DAY_HOURS, timecard.getTotalBillableHours());
	}

	/**
	 * Tests the getTotalNonBillableHours method.
	 */
	@Test
	public void testGetTotalNonBillableHours() {
		assertEquals(WORK_DAY_HOURS, timecard.getTotalNonBillableHours());
	}

	/**
	 * Tests the getConsultingHours method.
	 */
	@Test
	public void testGetConsultingHours() {
		assertEquals(CONSULTING_HOURS, timecard.getConsultingHours().size());
	}

	/**
	 * Tests the getTotalHours method.
	 */
	@Test
	public void testGetTotalHours() {
		assertEquals(TOTAL_HOURS, timecard.getTotalHours());
	}

	/**
	 * Tests the getBillableHoursForClient method.
	 */
	@Test
	public void testGetBillableHoursForClient() {
		assertEquals(1, timecard.getBillableHoursForClient(client.getName()).size());
	}

	/**
	 * Tests the getBillableHoursForClient method.
	 */
	@Test
	public void testCompareTo() {

		assertEquals(0, timecard.compareTo(timecard));
	}

}
