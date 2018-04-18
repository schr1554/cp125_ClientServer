package com.scg.domain_test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.scg.domain.Consultant;
import com.scg.util.Name;

public class ConsultantTest {

	Name name = new Name("Coyote", "Wiley");

	@Test
	public void consultantConstructorTest() {
		assertEquals(name.toString(), new Consultant(name).getName().toString());
	}

	@Test
	public void getNameTest() {
		assertEquals(new Name("TEST", "TEST").toString(),
				new Consultant(new Name("TEST", "TEST")).getName().toString());
	}

	@Test
	public void toStringTest() {
		assertEquals(name.toString(), new Consultant(name).toString());
	}

	@Test
	public void testCompareTo() {

		assertEquals(0, name.compareTo(name));
	}

}
