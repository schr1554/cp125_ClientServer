package com.scg.domain_test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.scg.domain.ClientAccount;
import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class ClientAccountTest {

	String name = "TEST";
	Name client = new Name("Coyote", "Wiley");

	LocalDate startDate = LocalDate.of(2016, 1, 1);

	String streetNumber = "TEST_STREET";
	String city = "TEST_CITY";
	StateCode state = StateCode.CO;
	String postalCode = "TEST POSTAL CODE";

	Address address = new Address(streetNumber, city, state, postalCode);

	@Test
	public void clientAccountConstructorTest() {
		ClientAccount clientAccount = new ClientAccount(name, client, address);
		assertEquals(name, clientAccount.getName());
		assertEquals(client, clientAccount.getContact());
		assertEquals(address, clientAccount.getAddress());

	}

	@Test
	public void getNameTest() {
		ClientAccount clientAccount = new ClientAccount(name, client, address);
		assertEquals(name, clientAccount.getName());
	}

	@Test
	public void getContactTest() {
		ClientAccount clientAccount = new ClientAccount(name, client, address);
		assertEquals(client, clientAccount.getContact());

	}

	@Test
	public void setContactTest() {
		ClientAccount clientAccount = new ClientAccount(name, client, address);
		Name setName = new Name("TEST", "TEST");
		clientAccount.setContact(setName);
		assertEquals(setName, clientAccount.getContact());
	}

	@Test
	public void getAddress() {
		ClientAccount clientAccount = new ClientAccount(name, client, address);
		assertEquals(address, clientAccount.getAddress());

	}

	@Test
	public void setAddressTest() {
		ClientAccount clientAccount = new ClientAccount(name, client, address);
		String streetNumber = "TEST_STREET_Updated";
		String city = "TEST_CITY";
		StateCode state = StateCode.CO;
		String postalCode = "TEST POSTAL CODE";

		Address address = new Address(streetNumber, city, state, postalCode);
		clientAccount.setAddress(address);
		assertEquals(address, clientAccount.getAddress());
	}

	@Test
	public void isBillableTest() {
		ClientAccount clientAccount = new ClientAccount(name, client, address);
		assertEquals(true, clientAccount.isBillable());
	}

	@Test
	public void toStringTst() {
		ClientAccount clientAccount = new ClientAccount(name, client, address);
		assertEquals(false, clientAccount.toString() == null);
	}

	@Test
	public void testCompareTo() {
		ClientAccount clientAccount = new ClientAccount(name, client, address);

		assertEquals(0, clientAccount.compareTo(clientAccount));
	}
}
