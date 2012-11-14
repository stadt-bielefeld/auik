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

package de.bielefeld.umweltamt.aui.tests;

import junit.framework.TestCase;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;

public class _TabellenErstellenTest extends TestCase {

    private static final String Strasse = "Teststraße";
    private static final int Hausnr = 10;
    private int _idS;
    private static final String Name = "Testbetreiber";
    private static final String Handz = "Junit";
    private int _idB;
    private static final int ObjID = 1;
    private static final String ObjArt = "Testart";
    private int _idO;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Configuration configuration = new Configuration().configure();
        SchemaExport export = new SchemaExport(configuration);
        export.create(false, true);
        testErzeugeDaten();
        tearDown();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testErzeugeDaten() {
        String idS = "leer";
        _idS = erzeugeStandort(Strasse, Hausnr);
        if (_idS != 0) {
            idS = "vorhanden";
        }
        assertEquals(idS, "vorhanden");

        String idB = "leer";
        _idB = erzeugeBetreiber(Name, Handz);
        if (_idB != 0) {
            idB = "vorhanden";
        }
        assertEquals(idB, "vorhanden");

        String idO = "leer";
        _idO = erzeugeObjektart(ObjID, ObjArt);
        if (_idO != 0) {
            idO = "vorhanden";
        }
        assertEquals(idO, "vorhanden");

        BasisBetreiber.delete(BasisBetreiber.findById(_idB));
        BasisStandort.removeStandort(BasisStandort.getStandort(_idS));
        BasisObjektarten.delete(BasisObjektarten.findById(_idO));
    }

    /**
     * Kleine Hilfsmethode, mit der ein Standort erzeugt und in der Datenbank
     * gesichert wird.
     * @param Strasse
     * @param Hausnr Die Hausnummer
     * @return Gibt die ID des erzeugten Standorts zurück.
     */
    private int erzeugeStandort(String Strasse, int Hausnr) {
        BasisStandort standort = new BasisStandort();
        standort.setStrasse(Strasse);
        standort.setHausnr(Hausnr);

        standort = BasisStandort.saveStandort(standort);

        return standort.getStandortid();
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

        BasisBetreiber.merge(betreiber);

        return betreiber.getId();
    }

    /**
     * Kleine Hilfsmethode, mit der eine Objektart erzeugt und in der Datenbank
     * gesichert wird.
     * @param name Der Name des zu erzeugenden Betreibers.
     * @param handz Das Handzeichen
     * @return Gibt die ID des erzeugten Betreibers zurück.
     */
    private int erzeugeObjektart(int ID, String ObjArt) {
        BasisObjektarten art = new BasisObjektarten();
        art.setObjektartid(ID);
        art.setObjektart(ObjArt);

        art = BasisObjektarten.merge(art);

        return art.getObjektartid();
    }
}
