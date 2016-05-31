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

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.Modul;
import de.bielefeld.umweltamt.aui.ModulManager;
import de.bielefeld.umweltamt.aui.SettingsManager;

public class GUITest extends TestCase {

    private static HauptFrame runningFrame = null;
    private SessionFactory _sessionFactory;
    private ModulManager manager;

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
     * Testen die Funktionen des Modul Managers
     */
    public void testModulManager() {
        Session session = null;

        session = _sessionFactory.openSession();

        // Aufruf des Login-Fensters
        SettingsManager settings = SettingsManager.getInstance();
        runningFrame = new HauptFrame(settings);
        manager = new ModulManager(runningFrame, settings);
        // ... füge Module hinzu
        manager.loadModule();
        // Zeige dieses Fenster an
        runningFrame.setVisible(true);

        // modul wechslen
        manager.switchModul("m_sielhaut1");
        Modul modul = manager.getCurrentModul();
        // wurde das Modul gewechselt?
        assertEquals("m_sielhaut1", modul.getIdentifier());
        // ist es der richtige Kategorie zugeordnet?
        assertEquals("Sielhaut", modul.getCategory());

        manager.switchModul("m_auswertung_anh40");
        modul = manager.getCurrentModul();
        assertEquals("m_auswertung_anh40", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        manager.switchModul("m_auswertung_anh49");
        modul = manager.getCurrentModul();
        assertEquals("m_auswertung_anh49", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        manager.switchModul("m_auswertung_anh52");
        modul = manager.getCurrentModul();
        assertEquals("m_auswertung_anh52", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        manager.switchModul("m_auswertung_anh53");
        modul = manager.getCurrentModul();
        assertEquals("m_auswertung_anh53", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        manager.switchModul("m_auswertung_anh55");
        modul = manager.getCurrentModul();
        assertEquals("m_auswertung_anh55", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        manager.switchModul("m_auswertung_anh56");
        modul = manager.getCurrentModul();
        assertEquals("m_auswertung_anh56", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        manager.switchModul("m_schlaemme_faul");
        modul = manager.getCurrentModul();
        assertEquals("m_schlaemme_faul", modul.getIdentifier());
        assertEquals("Klärschlamm", modul.getCategory());

        manager.switchModul("m_schlaemme_roh");
        modul = manager.getCurrentModul();
        assertEquals("m_schlaemme_roh", modul.getIdentifier());
        assertEquals("Klärschlamm", modul.getCategory());

        manager.switchModul("m_betreiber_neu");
        modul = manager.getCurrentModul();
        assertEquals("m_betreiber_neu", modul.getIdentifier());
        assertEquals("Betriebe", modul.getCategory());

        manager.switchModul("m_betreiber_suchen");
        modul = manager.getCurrentModul();
        assertEquals("m_betreiber_suchen", modul.getIdentifier());
        assertEquals("Betriebe", modul.getCategory());

        manager.switchModul("m_fettabscheider_auswertung");
        modul = manager.getCurrentModul();
        assertEquals("m_fettabscheider_auswertung", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        manager.switchModul("m_auswertung_genehmigung");
        modul = manager.getCurrentModul();
        assertEquals("m_auswertung_genehmigung", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        manager.switchModul("atl_icp_import");
        modul = manager.getCurrentModul();
        assertEquals("atl_icp_import", modul.getIdentifier());
        assertEquals("Labor", modul.getCategory());

        manager.switchModul("m_schlaemme_auswertung");
        modul = manager.getCurrentModul();
        assertEquals("m_schlaemme_auswertung", modul.getIdentifier());
        assertEquals("Klärschlamm", modul.getCategory());

        manager.switchModul("m_objekt_bearbeiten");
        modul = manager.getCurrentModul();
        assertEquals("m_objekt_bearbeiten", modul.getIdentifier());
        assertEquals("Betriebe", modul.getCategory());

        manager.switchModul("m_probe_suchen");
        modul = manager.getCurrentModul();
        assertEquals("m_probe_suchen", modul.getIdentifier());
        assertEquals("Labor", modul.getCategory());

        manager.switchModul("m_sielhaut_import");
        modul = manager.getCurrentModul();
        assertEquals("m_sielhaut_import", modul.getIdentifier());
        assertEquals("Sielhaut", modul.getCategory());

        manager.switchModul("m_standort_neu");
        modul = manager.getCurrentModul();
        assertEquals("m_standort_neu", modul.getIdentifier());
        assertEquals("Betriebe", modul.getCategory());

        manager.switchModul("m_standort_suchen");
        modul = manager.getCurrentModul();
        assertEquals("m_standort_suchen", modul.getIdentifier());
        assertEquals("Betriebe", modul.getCategory());

        manager.switchModul("m_auswertung_uebergabe");
        modul = manager.getCurrentModul();
        assertEquals("m_auswertung_uebergabe", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        manager.back();
        modul = manager.getCurrentModul();
        assertEquals("m_standort_suchen", modul.getIdentifier());
        assertEquals("Betriebe", modul.getCategory());

        manager.forward();
        modul = manager.getCurrentModul();
        assertEquals("m_auswertung_uebergabe", modul.getIdentifier());
        assertEquals("Auswertung", modul.getCategory());

        if (session != null && session.isConnected()) {
            session.close();
        }
    }
}
