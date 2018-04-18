package com.scg.domain_test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

public class TimeCardTest {

	Consultant programmer = new Consultant(new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"));
	LocalDate startDate = LocalDate.of(2016, 1, 1);

	String streetNumber = "TEST_STREET";
	String city = "TEST_CITY";
	StateCode state = StateCode.CO;
	String postalCode = "TEST POSTAL CODE";

	Address address = new Address(streetNumber, city, state, postalCode);

	@Test
	public void timeCardConstructorTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		assertEquals(programmer, tCard.getConsultant());
		assertEquals(startDate, tCard.getWeekStartingDay());
	}

	@Test
	public void getConsultantTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		assertEquals(programmer, tCard.getConsultant());
	}

	@Test
	public void getTotalBillableHoursTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		tCard.addConsultantTime(new ConsultantTime(startDate,
				new ClientAccount("TEST_ACCOUNT", new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"), address),
				Skill.SOFTWARE_ENGINEER, 8));
		assertEquals(8, tCard.getTotalBillableHours());
	}

	@Test
	public void getTotalNonBillableHoursTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		tCard.addConsultantTime(new ConsultantTime(startDate, NonBillableAccount.VACATION, Skill.SOFTWARE_ENGINEER, 8));
		assertEquals(8, tCard.getTotalNonBillableHours());
	}

	@Test
	public void getConsultingHoursTest() {

		List<ConsultantTime> consultingHoursTest = new ArrayList<ConsultantTime>();
		consultingHoursTest.add(new ConsultantTime(startDate, NonBillableAccount.VACATION, Skill.SOFTWARE_ENGINEER, 8));
		TimeCard tCard = new TimeCard(programmer, startDate);
		tCard.addConsultantTime(new ConsultantTime(startDate, NonBillableAccount.VACATION, Skill.SOFTWARE_ENGINEER, 8));
		assertEquals(consultingHoursTest.hashCode(), tCard.getConsultingHours().hashCode());
	}

	@Test
	public void addConsultantTimeTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		tCard.addConsultantTime(new ConsultantTime(startDate, NonBillableAccount.VACATION, Skill.SOFTWARE_ENGINEER, 8));
		assertEquals(8, tCard.getTotalNonBillableHours());
	}

	@Test
	public void getTotalHoursTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		tCard.addConsultantTime(new ConsultantTime(startDate,
				new ClientAccount("TEST_ACCOUNT", new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"), address),
				Skill.SOFTWARE_ENGINEER, 8));
		tCard.addConsultantTime(new ConsultantTime(startDate, NonBillableAccount.VACATION, Skill.SOFTWARE_ENGINEER, 8));
		assertEquals(16, tCard.getTotalHours());
	}

	@Test
	public void getWeekStartingDayTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		assertEquals("2016-01-01", tCard.getWeekStartingDay().toString());
	}

	@Test
	public void getBillableHoursForClientTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		List<ConsultantTime> consultingHoursTest = new ArrayList<ConsultantTime>();
		consultingHoursTest.add(new ConsultantTime(startDate,
				new ClientAccount("TEST_ACCOUNT", new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"), address),
				Skill.SOFTWARE_ENGINEER, 8));
		tCard.addConsultantTime(new ConsultantTime(startDate,
				new ClientAccount("TEST_ACCOUNT", new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"), address),
				Skill.SOFTWARE_ENGINEER, 8));
		assertEquals(consultingHoursTest.size(), tCard.getBillableHoursForClient("TEST_ACCOUNT").size());
	}

	@Test
	public void toStringTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		assertEquals(tCard.toString(),
				programmer.getName().toString() + " " + startDate.format(DateTimeFormatter.ofPattern("yyy MM dd")));
	}

	@Test
	public void toReportStringTest() {
		TimeCard tCard = new TimeCard(programmer, startDate);
		tCard.addConsultantTime(new ConsultantTime(startDate,
				new ClientAccount("TEST_ACCOUNT", new Name("TEST_LAST_NAME", "TEST_FIRST_NAME"), address),
				Skill.SOFTWARE_ENGINEER, 8));

		tCard.addConsultantTime(new ConsultantTime(startDate, NonBillableAccount.VACATION, Skill.SOFTWARE_ENGINEER, 8));

		assertTrue(!(tCard.toReportString() == null));
	}

}
