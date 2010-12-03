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

import java.util.Date;
import java.util.List;
import de.bielefeld.umweltamt.aui.mappings.basis.*;
import de.bielefeld.umweltamt.aui.mappings.indeinl.*;
import de.bielefeld.umweltamt.aui.mappings.atl.*;
import de.bielefeld.umweltamt.aui.mappings.vaws.*;
import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class ObjektTest extends TestCase {
    /**
     * Starten einer SessionFactory und erzeugen schon mal einens neues Objekt.
     */
    protected void setUp() throws Exception {
        super.setUp();

        betreiber = findeBetreiber();
        standort = findeStandort();
        objektart = findeObjektart();


    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testErzeugen()
    {
         String id = "leer";
         _id = erzeugeObjekt(betreiber, standort, objektart);
         if(_id != 0)
         {
             id = "vorhanden";
         }
         assertEquals(id,"vorhanden");
    }

    /**
     * Das erstellet Objekt wird in der Datenbank gesucht
     */
       public BasisObjekt testQuery() {
        Session session = null;

        try {

            List result = BasisObjekt.getObjekteByStandort(standort, null, 16);


            BasisObjekt objekt = (BasisObjekt) result.get(0);

            assertEquals(betreiber, objekt.getBasisBetreiber());
            return objekt;
        }
        finally {

        }

    }

    /**
     * Hier wird getestet ob das Objekt verändert werden kann
     */
    public void testUpdate() {

        try {

            BasisObjekt objekt = testQuery();

            objekt.setBeschreibung("neue");
            BasisObjekt.saveBasisObjekt(objekt);

            objekt = testQuery();

            assertEquals(betreiber, objekt.getBasisBetreiber());
            assertEquals("neue", objekt.getBeschreibung());
        }
        finally {

        }
    }
    /**
     * Testet ob die Objektchronologie vorhanden ist
     */
    public void testChrono() {

        try {

            BasisObjekt objekt = testQuery();
            List chronolist =  BasisObjektchrono.getChronoByObjekt(objekt);

            BasisObjektchrono chrono = (BasisObjektchrono) chronolist.get(0);

           assertEquals(1, chronolist.size());

           assertEquals("JUNIT", chrono.getSachbearbeiter());

        }
        finally {

        }
    }
    /**
     * Testet ob das Objekt und die Objektchrono gelöscht werden kann
     */
    public void testDelete() {

        try {

            BasisObjekt objekt = testQuery();
            List chronolist =  BasisObjektchrono.getChronoByObjekt(objekt);

            BasisObjektchrono chrono = (BasisObjektchrono) chronolist.get(0);

            BasisObjektchrono.removeObjektChrono(chrono);
            BasisObjekt.removeBasisObjekt(objekt);

            List result = BasisObjekt.getObjekteByStandort(standort, 1);


            assertEquals(0, result.size());
        }
        finally {

        }
    }

    /**
     * Kleine Hilfsmethode, mit der ein Objekt mit Objektchronologie erzeugt und in der Datenbank gesichert wird.
     *
     * @param betreiber Betreiber des Objekts.
     * @param standort Standort des Objekts
     * @param objektart Objektart des Objekts
     * @return Gibt die ID des erzeugten Objekts zurück.
     */
    private int erzeugeObjekt(
        BasisBetreiber betreiber, BasisStandort standort, BasisObjektarten objektart) {
        BasisObjekt objekt = new BasisObjekt();
        objekt.setBasisBetreiber(betreiber);
        objekt.setBasisObjektarten(objektart);
        objekt.setBasisStandort(standort);
        BasisObjektchrono chrono = new BasisObjektchrono();

        try {

            objekt = BasisObjekt.saveBasisObjekt(objekt);
            chrono.setBasisObjekt(objekt);
            chrono.setSachbearbeiter("JUNIT");
            chrono.saveObjektChrono(chrono);
            chronoid = chrono.getId();
        }
        catch (HibernateException e) {

                throw e;

        }


        finally {

        }

        return objekt.getObjektid();
    }
    private BasisStandort findeStandort()
    {
       BasisStandort standort = BasisStandort.getStandort(1);
       return standort;
    }
    private BasisBetreiber findeBetreiber()
    {
       BasisBetreiber betreiber = BasisBetreiber.getBetreiber(1);
       return betreiber;
    }
    private BasisObjektarten findeObjektart()
    {
        BasisObjektarten objektart = BasisObjektarten.getObjektart(1);
        return objektart;
    }
    private int _id;
    private int chronoid;
    private BasisStandort standort;
    private BasisBetreiber betreiber;
    private BasisObjektarten objektart;
}
