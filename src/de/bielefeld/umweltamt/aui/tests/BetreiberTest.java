/**
 * Copyright 2005-2011, Stadt Bielefeld
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

/*
Copyright (c) 2005-2007, Robert F. Beeger & Arno Haase & Stefan Roock & Sebastian Sanitz
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
      this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice,
      this list of conditions and the following disclaimer in the documentation and/or
      other materials provided with the distribution.
    * Neither the name of the the authors nor the names of its contributors may be
      used to endorse or promote products derived from this software without specific
      prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
 */
package de.bielefeld.umweltamt.aui.tests;

import java.util.List;

import junit.framework.TestCase;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;

public class BetreiberTest extends TestCase {

    private static final String Name = "Betreibertest";
    private static final String Handz = "Junit";
    private int _id;

    /**
     * Starten einer SessionFactory und erzeugen schon mal einens neuen
     * Betreibers.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testErzeugen() {
        String id = "leer";
        _id = erzeugeBetreiber(Name, Handz);
        if (_id != 0) {
            id = "vorhanden";
        }
        assertEquals(id, "vorhanden");
    }

    /**
     * Und hier versuchen wir ihn über eine Datenbankabfrage zu finden.
     */
    private BasisBetreiber testQuery() {
        BasisBetreiber betreiber;

        List<?> result = BasisBetreiber.findBetreiber(Name, "name");

        betreiber = (BasisBetreiber) result.get(0);

        assertEquals(Name, betreiber.getBetrname());

        return betreiber;
    }

    /**
     * Hier wird getestet ob der Betreiber verändert werden kann
     */
    public void testUpdate() {
        BasisBetreiber betreiber = testQuery();

        betreiber.setBetranrede("neue");
        BasisBetreiber.saveBetreiber(betreiber);

        betreiber = testQuery();
        assertEquals(Name, betreiber.getBetrname());
        assertEquals("neue", betreiber.getBetranrede());
    }

    /**
     * Testet ob der Betreiber gelöscht werden kann
     */
    public void testDelete() {
        BasisBetreiber betreiber = testQuery();

        // session.delete(betreiber);
        BasisBetreiber.removeBetreiber(betreiber);

        List<?> result = BasisBetreiber.findBetreiber(Name, "name");

        assertEquals(0, result.size());
    }

    /**
     * Kleine Hilfsmethode, mit der ein Betreiber erzeugt und in der Datenbank
     * gesichert wird.
     * @param name Der Name des zu erzeugenden Betreibers.
     * @param handz Das Handzeichen
     * @return Gibt die ID des erzeugten Betreibers zurück.
     */
    private int erzeugeBetreiber(String name, String handz) {
        BasisBetreiber betreiber = new BasisBetreiber();
        betreiber.setBetrname(name);
        betreiber.setRevihandz(handz);

        BasisBetreiber.saveBetreiber(betreiber);

        return betreiber.getBetreiberid();
    }
}
