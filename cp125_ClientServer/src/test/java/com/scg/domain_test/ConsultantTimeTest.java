package com.scg.domain_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.Month;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.scg.domain.Account;
import com.scg.domain.ClientAccount;
import com.scg.domain.ConsultantTime;
import com.scg.domain.NonBillableAccount;
import com.scg.domain.Skill;
import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

/**
 * JUnit test for the ConsultantTime class.
 */
public final class ConsultantTimeTest {
	/** Constant for test year. */
	private static final int TEST_YEAR = 2004;
	/** Constant for start day. */
	private static final int START_DAY = 5;
	/** Constant for hours per day. */
	private static final int HOURS_PER_DAY = 8;
	/** String constant for "FooBar Enterprises". */
	private static final String FOOBAR = "FooBar Enterprises";
	/** String constant for "Client". */
	private static final String CLIENT = "Client";
	/** String constant for "J.". */
	private static final String J_DOT = "J.";
	/** String constant for "Random". */
	private static final String RANDOM = "Random";
	/** Test street address. */
	private static final String STREET_NUMBER = "1024 Kilobyte Dr.";
	/** Test city. */
	private static final String CITY = "Silicone Gulch";
	/** Test ZIP code. */
	private static final String ZIP_CODE = "94105";

	/** Error message if an IllegalArgumentException isn't caught. */
	private static final String FAILED_ILLEGAL_ARG_EX_MSG = "Failed to throw IllegalArgumentException.";
	/** ConsultantTime instance for test. */
	private ConsultantTime consultanttime;
	/** Calendar for test start date. */
	private final LocalDate date = LocalDate.of(TEST_YEAR, Month.JANUARY, START_DAY);
	/** Test start date. */
	/** ClientAccount instance for test. */
	private final ClientAccount client = new ClientAccount(FOOBAR, new Name(CLIENT, J_DOT, RANDOM),
			new Address(STREET_NUMBER, CITY, StateCode.CA, ZIP_CODE));

	/**
	 * Perform test setup.
	 */
	@Before
	public void setUp() {
		consultanttime = new ConsultantTime(date, client, Skill.PROJECT_MANAGER, HOURS_PER_DAY);
	}

	/**
	 * Perform test tear down.
	 */
	@After
	public void tearDown() {
		consultanttime = null;
	}

	/**
	 * Test constructor.
	 */
	@Test
	public void testConstructor() {

		try {
			// Test the assertion that hours must be positive.
			consultanttime = new ConsultantTime(date, NonBillableAccount.SICK_LEAVE, Skill.UNKNOWN_SKILL,
					-HOURS_PER_DAY);
			fail(FAILED_ILLEGAL_ARG_EX_MSG);
		} catch (final IllegalArgumentException ex) {
			System.out.println("Expected exception caught");
			// ex.printStackTrace();
		}
	}

	/**
	 * Tests the getDate and setDate methods.
	 */
	@Test
	public void testSetGetDate() {
		final LocalDate[] tests = { LocalDate.now(), LocalDate.ofEpochDay(0), null };

		for (int i = 0; i < tests.length; i++) {
			consultanttime.setDate(tests[i]);
			assertEquals(tests[i], consultanttime.getDate());
		}
	}

	/**
	 * Tests the getAccount and setAccount methods.
	 */
	@Test
	public void testSetGetAccount() {
		final Account[] tests = { client, null };

		for (int i = 0; i < tests.length; i++) {
			consultanttime.setAccount(tests[i]);
			assertEquals(tests[i], consultanttime.getAccount());
		}
	}

	/**
	 * Tests the isBillable method.
	 */
	@Test
	public void testIsBillable() {
		consultanttime = new ConsultantTime(date, client, Skill.PROJECT_MANAGER, HOURS_PER_DAY);
		assertTrue(consultanttime != null);
		assertTrue(consultanttime.isBillable());
		// Test a non-billable account
		consultanttime = new ConsultantTime(date, NonBillableAccount.SICK_LEAVE, Skill.UNKNOWN_SKILL, HOURS_PER_DAY);
		assertTrue(consultanttime != null);
		assertFalse(consultanttime.isBillable());
	}

	/**
	 * Tests the getHours and setHours methods.
	 */
	@Test
	public void testSetGetHours() {
		int[] tests = new int[] { Integer.MIN_VALUE, 0, -HOURS_PER_DAY };
		try {
			// Test the assertion that hours must be positive.
			consultanttime.setHours(-HOURS_PER_DAY);
			fail(FAILED_ILLEGAL_ARG_EX_MSG);
		} catch (final IllegalArgumentException ex) {
			System.out.println("Caught expected exception.");
			// ex.printStackTrace();
		}

		tests = new int[] { 1, Integer.MAX_VALUE };
		for (int i = 0; i < tests.length; i++) {
			consultanttime.setHours(tests[i]);
			assertEquals(tests[i], consultanttime.getHours());

		}
	}

	/**
	 * Tests the getSkill method.
	 */
	@Test
	public void testGetSkill() {
		assertEquals(Skill.PROJECT_MANAGER, consultanttime.getSkill());
	}

	/**
	 * Tests the toString method.
	 */
	@Test
	public void testPrint() {
		consultanttime = new ConsultantTime(date, client, Skill.SOFTWARE_ENGINEER, HOURS_PER_DAY);
		System.out.println(consultanttime.toString());
	}
}
