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
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaAnalysemethode;
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
        String methodeNr            = unquote(columns[7]);

        int id = -1;
        if (einheitId != null && !einheitId.equals("")) {
            id = Integer.parseInt(einheitId);
        }

        log.debug(
            "Verarbeite Analyseposition für Parameter: " +
            parameterBezeichnung);

        Probenahme probe = DatabaseQuery.findProbenahme(kennnummer);

        if (probe == null) {
            log.error(
                "Probenahme mit folgender Kennung konnte nicht " +
                "gefunden werden: " + kennnummer);

            return false;
        }

        Parameter parameter = Parameter.findById(ordnungsbegriff);
        Einheiten einheit   = Einheiten.findById(id);
        MapElkaAnalysemethode mapAna = MapElkaAnalysemethode.findByMethodenNr(methodeNr);

        Analyseposition position = DatabaseQuery.findAnalyseposition(
            probe, parameter, einheit, true);

        if (position != null) {

            position.setWert(Float.parseFloat(wert));
            position.setGrkl(grkl);
            position.setEinheiten(einheit);
            position.setAnalyseVon("E-Satzung");
            if (mapAna != null) {
            	position.setMapElkaAnalysemethode(mapAna);
            } else {
            	mapAna = new MapElkaAnalysemethode();
            	mapAna.setMethodenNr(methodeNr);
            	mapAna.setGruppeDevId("000");
            	mapAna.setRegelwerkId("00");
            	mapAna.setVariantenId("0");
            	mapAna.merge();
            	position.setMapElkaAnalysemethode(mapAna);
            }
            
            position.merge();

            probe.setStatus(DatabaseConstants.ATL_STATUS_DATEN_EINGETRAGEN);
            probe.merge();
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
     * @param einh Die ID einer {@link AtlEinheiten}.
     *
     * @return <b>-1</b>, falls es keine {@link AtlProbenahmen} mit einer
     * Kennnummer <i>kenn</i> existiert oder kein {@link AtlParameter} oder
     * {@link AtlEinheiten} gibt. <b>1</b> falls eine {@link AtlProbenahmen}
     * mit entsprechendem {@link AtlParameter} gibt, <b>2</b> falls es eine
     * {@link AtlProbenahmen} gibt, die jedoch keinen passenden
     * {@link AtlParameter} besitzt.
     */
    public static int importStatus(String kenn, String param, String einh) {
        Probenahme probe = DatabaseQuery.findProbenahme(kenn);

        if (probe == null) {
            return -1;
        }

        if (param == null || einh == null) {
            return 2;
        }

        Parameter parameter = Parameter.findById(param);
        Einheiten   einheit = Einheiten.findById(Integer.parseInt(einh));

        if (parameter == null || einheit == null) {
            return -1;
        }

        Analyseposition position = DatabaseQuery.findAnalyseposition(
            probe, parameter, einheit, false);

        return position != null ? 1 : 2;
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
