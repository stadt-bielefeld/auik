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

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import de.bielefeld.umweltamt.aui.mappings.vaws.VawsKontrollen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsVerwaltungsverf;

public class WiedervorlageVawsTest extends TestCase {

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
     * Testen ob Wiedervorlage Sachverständigenprüfung fehlerfrei funktioniert
     */
    public void testWiederSach() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = VawsKontrollen.getAuswertung();

        List<?> listquery;
        String query = "from VawsKontrollen vk where "
            + "vk.naechstepruefung < ? "
            + "and vk.pruefungabgeschlossen = ? "
            + "order by vk.naechstepruefung, vk.vawsFachdaten";

        try {
            listquery = session.createQuery(query).setDate(0, new Date())
                .setBoolean(1, false).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }

    /**
     * Testen ob Wiedervorlage Verwaltungsverfahren fehlerfrei funktioniert
     */
    public void testWiederVerwalt() {
        Session session = null;
        session = _sessionFactory.openSession();

        List<?> list = VawsVerwaltungsverf.getAuswertung();

        List<?> listquery;
        String query = "from VawsVerwaltungsverf vf where "
            + "vf.wiedervorlage < ? " + "and "
            + "(vf.wvverwverf = ? or vf.wvverwverf is NULL) "
            + "order by vf.wiedervorlage, vf.vawsFachdaten";

        try {
            listquery = session.createQuery(query).setDate(0, new Date())
                .setBoolean(1, false).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        assertEquals(list.size(), listquery.size());
        if (session != null && session.isConnected()) {
            session.close();
        }
    }
}
