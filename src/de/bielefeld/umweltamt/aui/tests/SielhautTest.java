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
import de.bielefeld.umweltamt.aui.mappings.basis.*;
import de.bielefeld.umweltamt.aui.mappings.indeinl.*;
import de.bielefeld.umweltamt.aui.mappings.atl.*;
import de.bielefeld.umweltamt.aui.mappings.vaws.*;
import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;


public class SielhautTest extends TestCase {
    /**
     * Starten einer SessionFactory und erzeugen schon mal einens neues Sielhaupunkt.
     */
    protected void setUp() throws Exception {
        super.setUp();
        Configuration configuration =
                new Configuration().configure();

        _sessionFactory =
                configuration.buildSessionFactory();


    }

    protected void tearDown() throws Exception {
        super.tearDown();
        _sessionFactory.close();
    }
    public void testErzeugen()
    {
        String id = "leer";
        _id = erzeugeSielhaut(Messstelle);
        if (_id != 0)
        {
            id = "vorhanden";
        }
        assertEquals(id,"vorhanden");
    }

    /**
     * Und hier versuchen wir ihn über eine Datenbankabfrage zu finden.
     */
       private AtlSielhaut testQuery() {
        Session session = null;

    try{
            session = _sessionFactory.openSession();


              List result = AtlSielhaut.findPunkte(Messstelle);

            AtlSielhaut sielhaut = (AtlSielhaut) result.get(0);

            assertEquals(Messstelle, sielhaut.getBezeichnung());


      return sielhaut;
    }
      finally {
          if (session != null && session.isConnected()) {
              session.close();
          }
      }
    }

    /**
     * Hier wird getestet ob der Sielhautpunkt verändert werden kann
     */
    public void testUpdate() {
        Session session = null;
        try {
            session = _sessionFactory.openSession();
            AtlSielhaut sielhaut = testQuery();
            Transaction transaction = session.beginTransaction();
            sielhaut.setBemerkungen("neue");
            AtlSielhaut.saveSielhautPunkt(sielhaut);
            transaction.commit();
            session.close();
            session = _sessionFactory.openSession();

           sielhaut = testQuery();

            assertEquals(Messstelle, sielhaut.getBezeichnung());
            assertEquals("neue", sielhaut.getBemerkungen());
            Delete();
        }
        finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
    }

    /**
     * der Sielhutpunkt wird gelöscht
     */
    public void Delete() {
        Session session = null;
        try {
            session = _sessionFactory.openSession();

            AtlSielhaut sielhaut = testQuery();
            Transaction transaction = session.beginTransaction();
            session.delete(sielhaut);
            transaction.commit();
            session.close();
            session = _sessionFactory.openSession();


        }
        finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
    }

    /**
     * Kleine Hilfsmethode, mit der ein Sielhautpunkt erzeugt und in der Datenbank gesichert wird.
     *
     * @param messtelle Messtellenbezeichnung
     * @return Gibt die ID des Sielhautpunktes zurück.
     */
    private int erzeugeSielhaut(
            String messstelle) {
        AtlSielhaut sielhaut = new AtlSielhaut();
        sielhaut.setBezeichnung(messstelle);



        Session session = null;
        Transaction transaction = null;
        try {
            session = _sessionFactory.openSession();
            transaction =
                    session.beginTransaction();

            AtlSielhaut.saveSielhautPunkt(sielhaut);
            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }


        finally {
            if (session != null) {
                session.close();
            }

        }

        return sielhaut.getId();
    }

    private SessionFactory _sessionFactory;
    private static final String Messstelle = "JUnit";
    private int _id;
}
