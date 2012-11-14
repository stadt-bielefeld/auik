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
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektchrono;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

public class ObjektTest extends TestCase {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private int _id;
    private int chronoid;
    private BasisStandort standort;
    private BasisBetreiber betreiber;
    private BasisObjektarten objektart;

    /**
     * Starten einer SessionFactory und erzeugen schon mal einens neues Objekt.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        betreiber = findeBetreiber();
        standort = findeStandort();
        objektart = findeObjektart();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testErzeugen() {
        String id = "leer";
        _id = erzeugeObjekt(betreiber, standort, objektart);
        if (_id != 0) {
            id = "vorhanden";
        }
        assertEquals(id, "vorhanden");
    }

    /**
     * Das erstellet Objekt wird in der Datenbank gesucht
     */
    public BasisObjekt testQuery() {
        List<?> result = BasisObjekt.getObjekteByStandort(
            standort, null, 16);

        BasisObjekt objekt = (BasisObjekt) result.get(0);

        assertEquals(betreiber, objekt.getBasisBetreiber());
        return objekt;
    }

    /**
     * Hier wird getestet ob das Objekt verändert werden kann
     */
    public void testUpdate() {
        BasisObjekt objekt = testQuery();

        objekt.setBeschreibung("neue");
        BasisObjekt.saveBasisObjekt(objekt);

        objekt = testQuery();

        assertEquals(betreiber, objekt.getBasisBetreiber());
        assertEquals("neue", objekt.getBeschreibung());
    }

    /**
     * Testet ob die Objektchronologie vorhanden ist
     */
    public void testChrono() {
        BasisObjekt objekt = testQuery();
        List<?> chronolist = BasisObjektchrono.getChronoByObjekt(objekt);

        BasisObjektchrono chrono = (BasisObjektchrono) chronolist.get(0);

        assertEquals(1, chronolist.size());

        assertEquals("JUNIT", chrono.getSachbearbeiter());
    }

    /**
     * Testet ob das Objekt und die Objektchrono gelöscht werden kann
     */
    public void testDelete() {
        BasisObjekt objekt = testQuery();
        List<?> chronolist = BasisObjektchrono.getChronoByObjekt(objekt);

        BasisObjektchrono chrono = (BasisObjektchrono) chronolist.get(0);

        BasisObjektchrono.removeObjektChrono(chrono);
        BasisObjekt.removeBasisObjekt(objekt);

        List<?> result = BasisObjekt.getObjekteByStandort(standort, 1);

        assertEquals(0, result.size());
    }

    /**
     * Kleine Hilfsmethode, mit der ein Objekt mit Objektchronologie erzeugt und
     * in der Datenbank gesichert wird.
     * @param betreiber Betreiber des Objekts.
     * @param standort Standort des Objekts
     * @param objektart Objektart des Objekts
     * @return Gibt die ID des erzeugten Objekts zurück.
     */
    private int erzeugeObjekt(BasisBetreiber betreiber, BasisStandort standort,
        BasisObjektarten objektart) {
        BasisObjekt objekt = new BasisObjekt();
        objekt.setBasisBetreiber(betreiber);
        objekt.setBasisObjektarten(objektart);
        objekt.setBasisStandort(standort);
        BasisObjektchrono chrono = new BasisObjektchrono();

        objekt = BasisObjekt.saveBasisObjekt(objekt);
        chrono.setBasisObjekt(objekt);
        chrono.setSachbearbeiter("JUNIT");
        BasisObjektchrono.saveObjektChrono(chrono);
        this.chronoid = chrono.getId();
        log.debug("ChronoID: " + this.chronoid);

        return objekt.getObjektid();
    }

    private BasisStandort findeStandort() {
        BasisStandort standort = BasisStandort.getStandort(1);
        return standort;
    }

    private BasisBetreiber findeBetreiber() {
        BasisBetreiber betreiber = BasisBetreiber.findById(1);
        return betreiber;
    }

    private BasisObjektarten findeObjektart() {
        BasisObjektarten objektart = BasisObjektarten.findById(1);
        return objektart;
    }
}
