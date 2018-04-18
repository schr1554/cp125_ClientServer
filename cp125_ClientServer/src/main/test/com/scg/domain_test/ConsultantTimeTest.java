package com.scg.domain_test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.scg.domain.Account;
import com.scg.domain.ClientAccount;
import com.scg.domain.ConsultantTime;
import com.scg.domain.Skill;
import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class ConsultantTimeTest {

	String streetNumber = "TEST_STREET";
	String city = "TEST_CITY";
	StateCode state = StateCode.CO;
	String postalCode = "TEST POSTAL CODE";

	Address address = new Address(streetNumber, city, state, postalCode);

	LocalDate date = LocalDate.of(2016, 1, 1);
	Account account = new ClientAccount("Acme Industries", new Name("Coyote", "Wiley"), address);
	Skill skillType = Skill.SOFTWARE_ENGINEER;
	int hours = 8;

	@Test
	public void consultantTimeTest() {

		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);

		String outTest = date.format(DateTimeFormatter.ofPattern("yyy MM dd")) + " " + account.getName() + " "
				+ skillType.toString() + " " + hours;

		assertEquals(outTest, cTime.toString());
	}

	@Test
	public void getDateTest() {

		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		assertEquals(date, cTime.getDate());
	}

	@Test
	public void setDateTest() {

		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		cTime.setDate(LocalDate.of(2016, 2, 1));
		assertEquals(LocalDate.of(2016, 2, 1), cTime.getDate());
	}

	@Test
	public void getAccountTest() {

		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		assertEquals(account, cTime.getAccount());
	}

	@Test
	public void setAccountTest() {

		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		cTime.setAccount(new ClientAccount("Test Industries", new Name("Test", "Wiley"), address));
		assertEquals(new ClientAccount("Test Industries", new Name("Test", "Wiley"), address).getName(),
				cTime.getAccount().getName());
	}

	@Test
	public void isBillableTest() {
		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		assertEquals(true, cTime.isBillable());
	}

	@Test
	public void getHours() {
		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		assertEquals(hours, cTime.getHours());
	}

	@Test
	public void setHours() {
		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		cTime.setHours(10);
		assertEquals(10, cTime.getHours());
	}

	@Test
	public void getSkillTest() {
		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		assertEquals(skillType, cTime.getSkill());
	}

	@Test
	public void hashCodeTest() {
		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);

		assertEquals(new ConsultantTime(date, account, skillType, hours), cTime.hashCode());
	}

	@Test
	public void equals() {
		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		assertEquals(true, cTime.equals(new ConsultantTime(date, account, skillType, hours)));
	}

	@Test
	public void toStringTest() {
		ConsultantTime cTime = new ConsultantTime(date, account, skillType, hours);
		assertEquals(this.date.format(DateTimeFormatter.ofPattern("yyy MM dd")) + " " + this.account.getName() + " "
				+ this.skillType.toString() + " " + this.hours, cTime.toString());
	}

}
