/**
 * Copyright 2005-2042, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */

package de.bielefeld.umweltamt.aui.utils;

import static org.junit.Assert.assertEquals;

import java.util.Locale;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ibm.icu.text.NumberFormat;

/**
 * As this is my first ever JUnit test, I have picked out a simple class, which
 * actually does not really need a test. 
 * 
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class KommaDoubleTest {

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
	/* Some tool classes */
	private static Random randomNumberGenerator;
	private static NumberFormat numberFormatter;
	/** For testing use a random Double value */
	private Double randomNumber;
	/** An instance of the class we want to test */
	private KommaDouble instance;
	
	/**
	 * This is run once at the start of the test.
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/* Set up the random number generator */
		KommaDoubleTest.randomNumberGenerator = new Random();
		/* Get a number formatter and set it to German ('.'=>',')
		 * and switch off grouping. */
		KommaDoubleTest.numberFormatter =
			NumberFormat.getInstance(Locale.GERMAN);
		KommaDoubleTest.numberFormatter.setGroupingUsed(false);
	}

	/**
	 * This is run once at the end of the test.
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Nothing to do here
	}

	/**
	 * This is run at the start of every method test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// get a random Double between 0.0 and 1.0
		this.randomNumber = KommaDoubleTest.randomNumberGenerator.nextDouble();
		// multiply it with a random integer
		this.randomNumber *= KommaDoubleTest.randomNumberGenerator.nextInt();
		// randomize sign
		this.randomNumber *=
			KommaDoubleTest.randomNumberGenerator.nextBoolean() ? 1.0 : -1.0;
		this.instance = new KommaDouble(randomNumber);
		log.debug("Testing with random number: " + this.randomNumber);
	}

	/**
	 * This is run at the end of every method test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.randomNumber = 0.0;
		this.instance = null;
	}

	/**
	 * Test method for {@link de.bielefeld.umweltamt.aui.utils.KommaDouble#KommaDouble(java.lang.Double)}.
	 */
	@Test
	public void testKommaDoubleDouble() {
		assertEquals(new KommaDouble(this.randomNumber).getValue(),
				this.randomNumber, 0.0);
	}

	/**
	 * Test method for {@link de.bielefeld.umweltamt.aui.utils.KommaDouble#KommaDouble(double)}.
	 */
	@Test
	public void testKommaDoubleDouble1() {
		assertEquals(new KommaDouble(((double) this.randomNumber)).getValue(),
				((double) this.randomNumber), 0.0);
	}

	/**
	 * Test method for {@link de.bielefeld.umweltamt.aui.utils.KommaDouble#KommaDouble(java.lang.String)}.
	 */
	@Test
	public void testKommaDoubleString() {
		String randomNumberString = this.randomNumber.toString();
		randomNumberString.replace('.', ',');
		assertEquals(new KommaDouble(randomNumberString).getValue(),
				this.randomNumber, 0.0);
	}

	/**
	 * Test method for {@link de.bielefeld.umweltamt.aui.utils.KommaDouble#getValue()}.
	 */
	@Test
	public void testGetValue() {
		assertEquals(instance.getValue(), this.randomNumber, 0.0);
	}

	/**
	 * Test method for {@link de.bielefeld.umweltamt.aui.utils.KommaDouble#setValue(java.lang.Double)}.
	 */
	@Test
	public void testSetValue() {
		instance.setValue(this.randomNumber);
		assertEquals(instance.getValue(), this.randomNumber, 0.0);
	}

	/**
	 * Test method for {@link de.bielefeld.umweltamt.aui.utils.KommaDouble#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals(instance.toString(),
				KommaDoubleTest.numberFormatter.format(this.randomNumber));
	}
}
