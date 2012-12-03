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

package de.bielefeld.umweltamt.aui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Unit test for the GUI Manager
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
public class GUIManagerTest {

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
	/** GUI Manager */
	private static final GUIManager guiManager = GUIManager.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// This is intentionally left blank.
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// This is intentionally left blank.
	}

	@Before
	public void setUp() throws Exception {
		// This is intentionally left blank.
	}

	@After
	public void tearDown() throws Exception {
		// This is intentionally left blank.
	}

	@Test
	public void testGetVersion() {
		log.debug(guiManager.getVersion());
	}

    @Test
    public void testSetInfoStatus() {
        guiManager.setInfoStatus("Foo");
    }

    @Test
    public void testSetErrorStatus() {
        guiManager.setErrorStatus("Bar");
    }

	@Test
    public void testShowQuestion() {
        log.debug("The answer is " + guiManager.showQuestion("w00t?", "Frage"));
    }

	@Test
	public void testShowInfoMessage() {
	    guiManager.showInfoMessage("Boo", "Message");
    }

	@Test
    public void testShowErrorMessage() {
        guiManager.showErrorMessage("Huu", "Error");
    }
}
