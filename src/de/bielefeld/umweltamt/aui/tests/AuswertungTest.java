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

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;

public class AuswertungTest extends TestCase {

    private SessionFactory _sessionFactory;

    /**
     * Starten einer SessionFactory
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Configuration configuration = new Configuration().configure();

        _sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        _sessionFactory.close();
    }

    /**
     * Testen ob Anhang 40 Auswertung fehlerfrei funktioniert
     */
    public void testAusAnh40() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = DatabaseQuery.getAnhang40Auswertung();

        List<?> listquery;
        String query = "from Anh40Fachdaten as anh40 "
            + "order by anh40.basisObjekt.inaktiv, anh40.id";

        try {
            listquery = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    /**
     * Testen ob Anhang 50 Auswertung fehlerfrei funktioniert
     */
    public void testAusAnh50() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = DatabaseQuery.getAnhang50ByWiedervorlage(false);

        List<?> listquery;
        String query = "from Anh50Fachdaten as ah50 where "
            + "ah50.erloschen = 'f' "
            + "order by ah50.basisObjekt.inaktiv, ah50.wiedervorlage, "
            + "ah50.basisObjekt.basisBetreiber.betrname";

        try {
            listquery = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    /**
     * Testen ob Anhang 52 Auswertung fehlerfrei funktioniert
     */
    public void testAusAnh52() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = DatabaseQuery.getAnhang52();

        List<?> listquery;
        String query = "from Anh52Fachdaten as anh52 "
            + "order by anh52.basisObjekt.inaktiv, anh52.id";

        try {
            listquery = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    /**
     * Testen ob Anhang 53 Auswertung fehlerfrei funktioniert
     */
    public void testAusAnh53() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = DatabaseQuery.getAnhang53();

        List<?> listquery;
        String query = "from Anh53Fachdaten as anh53 "
            + "order by anh53.basisObjekt.inaktiv, "
            + "anh53.basisObjekt.basisObjektarten.id, "
            + "anh53.basisObjekt.basisStandort.strasse, "
            + "anh53.basisObjekt.basisStandort.hausnr";

        try {
            listquery = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    /**
     * Testen ob Anhang 55 Auswertung fehlerfrei funktioniert
     */
    public void testAusAnh55() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = DatabaseQuery.getAnhang55();

        List<?> listquery;
        String query = "from Anh55Fachdaten as anh55 "
            + "order by anh55.basisObjekt.inaktiv, anh55.id";

        try {
            listquery = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    /**
     * Testen ob Anhang 56 Auswertung fehlerfrei funktioniert
     */
    public void testAusAnh56() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = DatabaseQuery.getAnhang56(null, null);

        List<?> listquery;
        String query = "from Anh56Fachdaten as anh56 "
            + "order by anh56.basisObjekt.inaktiv, anh56.basisObjekt.basisStandort.strasse, "
            + "anh56.basisObjekt.basisStandort.hausnr";

        try {
            listquery = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());
        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    /**
     * Testen ob BWK Auswertung fehlerfrei funktioniert
     */
    public void testAusBWK() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = DatabaseQuery.getBwkByYear(2010);

        List<?> listquery;
        String query = "from ViewBwk as bwk "
            + "where bwk.erfassung = 2010";

        try {
            listquery = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    /**
     * Testen ob Genehmigung Auswertung fehlerfrei funktioniert
     */
    public void testAusGenehmigung() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = DatabaseQuery.getGenehmigungen(-1, false, true, true);

        List<?> listquery;
        String query = "from IndeinlGenehmigung as gen "
            + "where (gen.gen58 = 't' or gen.gen59 = 't') "
    		    + "and gen.basisObjekt.inaktiv = 'f' "
            + "order by gen.basisObjekt.basisBetreiber.betrname";

        try {
            listquery = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    /**
     * Testen ob Suev Auswertung fehlerfrei funktioniert
     */
    public void testAusSuev() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = DatabaseQuery.getAnhangSuev();

        List<?> listquery;
        String query = "from AnhSuevFachdaten as sv "
            + "order by sv.basisObjekt.inaktiv, sv.objektid";

        try {
            listquery = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }
}
