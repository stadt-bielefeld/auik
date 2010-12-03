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
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class AuswertungTest extends TestCase {
    /**
     * Starten einer SessionFactory
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

    /**
     * Testen ob Anhang 40 Auswertung fehlerfrei funktioniert
     */
   public void testAusAnh40() {
        Session session = null;
        try {
            session = _sessionFactory.openSession();

            List list = Anh40Fachdaten.getAuswertungsListe();


         List listquery;
            String query = "from Anh40Fachdaten as anh40 " +
            "order by anh40.basisObjekt.inaktiv, anh40.id";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
        }

         assertEquals(list.size(), listquery.size());
        }
        finally {
            if (session != null &&
                    session.isConnected()) {
                session.close();
            }
        }
    }
   /**
    * Testen ob Anhang 49 Auswertung fehlerfrei funktioniert
    */
   public void testAusAnh49() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = Anh49Fachdaten.findAlle();


        List listquery;
        String query = "from Anh49Fachdaten anh49 where anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "+
        "order by anh49.basisObjekt.inaktiv, anh49.sachbearbeiterIn";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob Anhang 50 Auswertung fehlerfrei funktioniert
    */
   public void testAusAnh50() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = Anh50Fachdaten.findByWiedervorlage(false);


        List listquery;
        String query = "from Anh50Fachdaten as ah50 where " +
        "ah50.erloschen = 'f' " +
        "order by ah50.basisObjekt.inaktiv, ah50.wiedervorlage, " +
        "ah50.basisObjekt.basisBetreiber.betrname";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob Anhang 52 Auswertung fehlerfrei funktioniert
    */
   public void testAusAnh52() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = Anh52Fachdaten.getAuswertungsListe();


        List listquery;
        String query = "from Anh52Fachdaten as anh52 " +
        "order by anh52.basisObjekt.inaktiv, anh52.id";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob Anhang 53 Auswertung fehlerfrei funktioniert
    */
   public void testAusAnh53() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = Anh53Fachdaten.getAuswertungsListe();


        List listquery;
        String query = "from Anh53Fachdaten as anh53 "
            + "order by anh53.basisObjekt.inaktiv, anh53.basisObjekt.basisObjektarten.objektartid, "
            + "anh53.basisObjekt.basisStandort.strasse, "
            + "anh53.basisObjekt.basisStandort.hausnr";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob Anhang 55 Auswertung fehlerfrei funktioniert
    */
   public void testAusAnh55() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = Anh55Fachdaten.getAuswertungsListe();


        List listquery;
        String query = "from Anh55Fachdaten as anh55 " +
        "order by anh55.basisObjekt.inaktiv, anh55.id";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob Anhang 56 Auswertung fehlerfrei funktioniert
    */
   public void testAusAnh56() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = Anh56Fachdaten.getAuswertungsListe();


        List listquery;
        String query = "from Anh56Fachdaten as anh56 " +
        "order by anh56.basisObjekt.inaktiv, anh56.basisObjekt.basisStandort.strasse, " +
        "anh56.basisObjekt.basisStandort.hausnr";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob Fettabscheider Auswertung fehlerfrei funktioniert
    */
   public void testAusFettabsch() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = Anh49Abscheiderdetails.getFettabschListe();
           Anh49Fachdaten item;

        List fettabsch;
        List fettabsch2;
        String query =  "from Anh49Abscheiderdetails details where details.Anh49Fachdaten.basisObjekt.basisObjektarten.objektart like 'Fettabscheider' "
              +"order by details.Anh49Fachdaten.basisObjekt.inaktiv, details.Anh49Fachdaten.basisObjekt.basisBetreiber.betrname";

    try {
        fettabsch = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }
    String query2 = "from Anh49Fachdaten anh49"+
    " where anh49.basisObjekt.basisObjektarten.objektart like 'Fettabscheider' "
        +"order by anh49.basisObjekt.inaktiv, anh49.basisObjekt.basisBetreiber.betrname";
    fettabsch2 = session.createQuery(query2).list();

    for (int j = 0; j < fettabsch2.size(); j++) {
        item = (Anh49Fachdaten) fettabsch2.get(j);

        if ( Anh49Abscheiderdetails.getAbscheiderDetails(item).size() == 0 )
        {
            fettabsch.add(item);

        }

    }
         assertEquals(list.size(), fettabsch.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob Uebergabe Auswertung fehlerfrei funktioniert
    */
   public void testAusUebergabe() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = IndeinlUebergabestelle.getAuswertungsListe();


        List listquery;
        String query = "from IndeinlUebergabestelle as stelle " +
        "order by stelle.objektid";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob BWK Auswertung fehlerfrei funktioniert
    */
   public void testAusBWK() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = ViewBwk.findByErfassungsjahr(2010);


        List listquery;
        String query = "from ViewBwk as bwk " +
        "where bwk.erfassung = 2010";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob Genehmigung Auswertung fehlerfrei funktioniert
    */
   public void testAusGenehmigung() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list =IndeinlGenehmigung.getAuswertungsListe(true, true);


        List listquery;
        String query = "from IndeinlGenehmigung as gen " +
           "where gen.gen58 = 't' or gen.gen59 = 't' " +
        "order by gen.basisObjekt.inaktiv, gen.basisObjekt.basisBetreiber.betrname";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   /**
    * Testen ob Suev Auswertung fehlerfrei funktioniert
    */
   public void testAusSuev() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list =AnhSuevFachdaten.getAuswertungsListe();


        List listquery;
        String query = "from AnhSuevFachdaten as sv " +
        "order by sv.basisObjekt.inaktiv, sv.objektid";

    try {
        listquery = session.createQuery(query).list();
    } catch (HibernateException e) {
        throw new RuntimeException(e);
       }

         assertEquals(list.size(), listquery.size());
       }
       finally {
           if (session != null &&
                   session.isConnected()) {
               session.close();
           }
       }
   }
   private SessionFactory _sessionFactory;

}
