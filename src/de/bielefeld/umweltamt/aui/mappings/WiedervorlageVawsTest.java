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
package de.bielefeld.umweltamt.aui.mappings;

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


public class WiedervorlageVawsTest extends TestCase {
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
     * Testen ob Wiedervorlage Sachverständigenprüfung fehlerfrei funktioniert 
     */
   public void testWiederSach() {
        Session session = null;
        try {
            session = _sessionFactory.openSession();

            List list = VawsKontrollen.getAuswertung();
            
            
         List listquery;   
            String query = "from VawsKontrollen vk where " +
			"vk.naechstepruefung < ? " +
			"and vk.pruefungabgeschlossen = ? " +
			"order by vk.naechstepruefung, vk.vawsFachdaten";
	
	try {
		listquery = session.createQuery(
				query)
				.setDate(0, new Date())
				.setString(1, "f")
				.list();
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
    * Testen ob Wiedervorlage Verwaltungsverfahren fehlerfrei funktioniert
    */
   public void testWiederVerwalt() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = VawsVerwaltungsverf.getAuswertung();
           
           
        List listquery;   
        String query = "from VawsVerwaltungsverf vf where " +
		"vf.wiedervorlage < ? " +
		"and " +
		"(vf.wvverwverf = ? or vf.wvverwverf is NULL) " +
		"order by vf.wiedervorlage, vf.vawsFachdaten";
	
	try {
		listquery = session.createQuery(
				query)
				.setDate(0, new Date())
				.setString(1, "f")
				.list();
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
    * Testen ob die Suche nach Herstellnr fehlerfrei funktioniert
    */
   public void testHerstelnr() {
       Session session = null;
       try {
           session = _sessionFactory.openSession();

           List list = VawsFachdaten.findherstellnr("");
           
           
        List listquery;   
        String query = "from VawsFachdaten vaws "+
		"where vaws.herstellnr like ''";
	
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
