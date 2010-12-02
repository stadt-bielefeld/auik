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

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
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
import org.hibernate.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class StandortTest extends TestCase {
    /**
     * Starten einer SessionFactory und erzeugen schon mal einens neuen Standorts.
     */
    protected void setUp() throws Exception {
        super.setUp();
        
    }
    public void testErzeugen()
    {
    	String id = "leer";
    	_id = erzeugeStandort(Strasse, Hausnr);
    	if(_id != 0)
    	{
    		id = "vorhanden";
    	}
    	assertEquals(id,"vorhanden");
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();

    }

    /**
     * Und hier versuchen wir ihn über eine Datenbankabfrage zu finden.
     */
   	public BasisStandort testQuery() {
       
        try {

            List result = BasisStandort.findStandorte(Strasse, Hausnr);
              
            assertEquals(1, result.size());

            BasisStandort standort = (BasisStandort) result.get(0);

            assertEquals(Strasse, standort.getStrasse());
          return standort;  
        }
        finally {

        }
    }

    /**
     * Hier wird getestet ob der Standort verändert werden kann
     */
    public void testUpdate() {

        try {

            BasisStandort standort = testQuery();

            standort.setPlz("neue");
            BasisStandort.saveStandort(standort);

            standort = testQuery();

            assertEquals(Strasse, standort.getStrasse());
            assertEquals("neue", standort.getPlz());
            Delete(standort);
        }
        finally {

        }
    }

    /**
     *  der Standort wird gelöscht 
     */
    public void Delete(BasisStandort standort) {
    	
    	Transaction tx = null;
        try {

			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.delete(standort);
			tx.commit();


        }
        finally {
        	HibernateSessionFactory.closeSession();
        }
    }

    /**
     * Kleine Hilfsmethode, mit der ein Standort erzeugt und in der Datenbank gesichert wird.
     * 
     * @param Strasse 
     * @param Hausnr Die Hausnummer
     * @return Gibt die ID des erzeugten Standorts zurück.
     */
    private int erzeugeStandort(String Strasse, int Hausnr) {
		BasisStandort standort = new BasisStandort();
		standort.setStrasse(Strasse);
		standort.setHausnr(Hausnr);

		try {

			standort = BasisStandort.saveStandort(standort);

		} catch (HibernateException e) {

			throw e;

		}

		return standort.getStandortid();
	}

    private static final String Strasse = "Standorttest";
    private static final int Hausnr = 10;
    private int _id;
}
