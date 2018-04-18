package com.scg.domain_test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.scg.util.Address;
import com.scg.util.StateCode;

public class AddressTest {

	@Test
	public void constructorTest() {
		String streetNumber = "TEST_STREET";
		String city = "TEST_CITY";
		StateCode state = StateCode.CO;
		String postalCode = "TEST POSTAL CODE";

		Address address = new Address(streetNumber, city, state, postalCode);
		assertEquals(streetNumber, address.getStreetNumber());
		assertEquals(city, address.getCity());
		assertEquals(state, address.getState());
		assertEquals(postalCode, address.getPostalCode());

	}

	@Test
	public void getStreetNumberTest() {
		String streetNumber = "TEST_STREET";
		String city = "TEST_CITY";
		StateCode state = StateCode.CO;
		String postalCode = "TEST POSTAL CODE";

		Address address = new Address(streetNumber, city, state, postalCode);
		assertEquals(streetNumber, address.getStreetNumber());

	}

	@Test
	public void getCityTest() {
		String streetNumber = "TEST_STREET";
		String city = "TEST_CITY";
		StateCode state = StateCode.CO;
		String postalCode = "TEST POSTAL CODE";

		Address address = new Address(streetNumber, city, state, postalCode);
		assertEquals(city, address.getCity());

	}

	@Test
	public void getStateTest() {
		String streetNumber = "TEST_STREET";
		String city = "TEST_CITY";
		StateCode state = StateCode.CO;
		String postalCode = "TEST POSTAL CODE";

		Address address = new Address(streetNumber, city, state, postalCode);
		assertEquals(state, address.getState());

	}

	@Test
	public void hashCodeTest() {
		String streetNumber = "TEST_STREET";
		String city = "TEST_CITY";
		StateCode state = StateCode.CO;
		String postalCode = "TEST POSTAL CODE";

		int addressHash = city.hashCode();
		addressHash += postalCode.hashCode();
		addressHash += state.hashCode();
		addressHash += streetNumber.hashCode();

		Address address = new Address(streetNumber, city, state, postalCode);
		assertEquals(addressHash, address.hashCode());

	}

	@Test
	public void equalsTest() {
		String streetNumber = "TEST_STREET";
		String city = "TEST_CITY";
		StateCode state = StateCode.CO;
		String postalCode = "TEST POSTAL CODE";

		Address address = new Address(streetNumber, city, state, postalCode);
		Address otherAddress = new Address(streetNumber, city, state, postalCode);
		Address otherAddressFalse = new Address("Test Wrong", city, state, postalCode);
		assertEquals(true, address.equals(otherAddress));
		assertEquals(false, address.equals(otherAddressFalse));
	}

	@Test
	public void toStringTest() {
		String streetNumber = "TEST_STREET";
		String city = "TEST_CITY";
		StateCode state = StateCode.CO;
		String postalCode = "TEST POSTAL CODE";

		StringBuilder addressString = new StringBuilder();
		addressString.append(streetNumber + "\n");
		addressString.append(city + ", " + state.name() + " " + postalCode);

		Address address = new Address(streetNumber, city, state, postalCode);

		assertEquals(addressString.toString(), address.toString());
	}

}
