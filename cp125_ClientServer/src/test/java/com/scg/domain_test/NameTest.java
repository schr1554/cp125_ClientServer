package com.scg.domain_test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.scg.util.Name;

public class NameTest {

	@Test
	public void nameConstructorNoParamsTest() {
		Name name = new Name();

		assertEquals(null, name.getFirstName());
		assertEquals(null, name.getLastName());
		assertEquals(null, name.getMiddleName());
	}

	@Test
	public void nameConstructorTwoParamsTest() {
		Name name = new Name("Test_Last", "Test_First");

		assertEquals("Test_First", name.getFirstName());
		assertEquals("Test_Last", name.getLastName());
		assertEquals("NMN", name.getMiddleName());
	}

	@Test
	public void nameConstructorThreeParamsTest() {
		Name name = new Name("Test_Last", "Test_First", "Test_Middle");

		assertEquals(new Name("Test_Last", "Test_First", "Test_Middle").toString(), name.toString());
	}

	@Test
	public void getFirstNameTest() {
		Name name = new Name("Test_Last", "Test_First", "Test_Middle");
		String firstName = "Test_First";
		assertEquals(firstName, name.getFirstName());
	}

	@Test
	public void setFirstNameTest() {
		Name name = new Name("Test_First", "Test_Last", "Test_Middle");
		name.setFirstName("Test_First_Update");
		String firstName = "Test_First_Update";
		assertEquals(firstName, name.getFirstName());
	}

	@Test
	public void getLastNameTest() {
		Name name = new Name("Test_Last", "Test_First", "Test_Middle");
		String lastName = "Test_Last";
		assertEquals(lastName, name.getLastName());
	}

	@Test
	public void setLastNameTest() {
		Name name = new Name("Test_First", "Test_Last", "Test_Middle");
		name.setLastName("Test_Last_Update");
		String lastName = "Test_Last_Update";
		assertEquals(lastName, name.getLastName());
	}

	@Test
	public void getMiddleNameTest() {
		Name name = new Name("Test_Last", "Test_First", "Test_Middle");
		String middleName = "Test_Middle";
		assertEquals(middleName, name.getMiddleName());
	}

	@Test
	public void setMiddleNameTest() {
		Name name = new Name("Test_First", "Test_Last", "Test_Middle");
		name.setMiddleName("Test_Middle_Update");
		String middleName = "Test_Middle_Update";
		assertEquals(middleName, name.getMiddleName());
	}

	@Test
	public void hashCodeTest() {
		Name name = new Name("Test_Last", "Test_First", "Test_Middle");

		assertEquals(-868227075, name.hashCode());
	}

	@Test
	public void equalsTest() {
		Name nameX = new Name("Test_Last", "Test_First", "Test_Middle");
		Name nameY = new Name("Test_Last", "Test_First", "Test_Middle");

		assertEquals(true, nameX.equals(nameY));
		// assertTrue(nameX.equals(nameY));
	}

	@Test
	public void toStringTest() {
		Name name = new Name("Test_Last", "Test_First", "Test_Middle");

		String toStringTest = "Test_Last, Test_First Test_Middle";

		assertEquals(toStringTest, name.toString());
	}

}
