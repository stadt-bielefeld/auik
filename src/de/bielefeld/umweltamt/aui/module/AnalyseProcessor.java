/**
 * Copyright 2005-2042, Stadt Bielefeld
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
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.module;

import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;


/**
 * Diese Klasse stellt ein paar Hilfsfunktionen zur Verfügung, die das Arbeiten
 * mit Analyseimports erleichtern.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class AnalyseProcessor {

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Diese Funktion verarbeitet eine Zeile eines Analyseergebnis-Imports.
     *
     * @param columns Spalten einer Zeile eines Analyseergebnis-Imports.
     */
    public static boolean process(String[] columns) {
        if (columns == null || columns.length < 8) {
            log.error("Analyseimport: AtlAnalyseoption nicht vollständig.");
        }

        String kennnummer           = unquote(columns[0]);
        String ordnungsbegriff      = unquote(columns[2]);
        String parameterBezeichnung = unquote(columns[3]);
        String grkl                 = unquote(columns[4]);
        String wert                 = unquote(columns[5]);
        String einheitId            = unquote(columns[6]);

        int id = -1;
        if (einheitId != null && !einheitId.equals("")) {
            id = Integer.parseInt(einheitId);
        }

        log.debug(
            "Verarbeite Analyseposition für Parameter: " +
            parameterBezeichnung);

        AtlProbenahmen probe = DatabaseQuery.findProbenahme(kennnummer);

        if (probe == null) {
            log.error(
                "Probenahme mit folgender Kennung konnte nicht " +
                "gefunden werden: " + kennnummer);

            return false;
        }

        AtlParameter parameter = AtlParameter.findById(ordnungsbegriff);
        AtlEinheiten einheit   = AtlEinheiten.findById(id);
        AtlAnalyseposition pos = probe.findAtlAnalyseposition(
            parameter, einheit);

        if (pos != null) {

            pos.setWert(Float.parseFloat(wert));
            pos.setGrkl(grkl);
            pos.setAtlEinheiten(einheit);
            pos.setAnalyseVon("E-Satzung");

            probe.setAtlStatus(DatabaseConstants.ATL_STATUS_DATEN_EINGETRAGEN);
            AtlProbenahmen.merge(probe);
        }

        return true;
    }


    /**
     * Diese Funktion liefert einen Teilstring einer Eingabe.
     *
     * @param raw Ein String, der als erstes oder letztes Zeichen einen einfachen
     * Anf&uuml;hrungsstrich besitzt.
     *
     * @return Den Eingabestring ohne einfachen Anf&uuml;hrungsstrich.
     */
    public static String unquote(String raw) {
        return raw.replace("'", "");
    }


    /**
     * Diese Methode liefert den Status eines Analyseimports.
     *
     * @param param Der Ordnungsbegriff eines {@link AtlParameter}.
     * @param einheit Die ID einer {@link AtlEinheiten}.
     *
     * @return <b>-1</b>, falls es keine {@link AtlProbenahmen} mit einer
     * Kennnummer <i>kenn</i> existiert oder kein {@link AtlParameter} oder
     * {@link AtlEinheiten} gibt. <b>1</b> falls eine {@link AtlProbenahmen}
     * mit entsprechendem {@link AtlParameter} gibt, <b>2</b> falls es eine
     * {@link AtlProbenahmen} gibt, die jedoch keinen passenden
     * {@link AtlParameter} besitzt.
     */
    public static int importStatus(String kenn, String param, String einheit) {
        AtlProbenahmen probe = DatabaseQuery.findProbenahme(kenn);

        if (probe == null) {
            return -1;
        }

        if (param == null || einheit == null) {
            return 2;
        }

        int               id   = Integer.parseInt(einheit);
        AtlParameter       p   = AtlParameter.findById(param);
        AtlEinheiten       e   = AtlEinheiten.findById(id);

        if (p == null || e == null) {
            return -1;
        }

        AtlAnalyseposition pos = probe.findAtlAnalyseposition(p, e, false);

        return pos != null ? 1 : 2;
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
