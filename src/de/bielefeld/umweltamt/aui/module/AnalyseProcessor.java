/*
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.module;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;


/**
 * Diese Klasse stellt ein paar Hilfsfunktionen zur Verfügung, die das Arbeiten
 * mit Analyseimports erleichtern.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class AnalyseProcessor {

    /**
     * Diese Funktion verarbeitet eine Zeile eines Analyseergebnis-Imports.
     *
     * @param columns Spalten einer Zeile eines Analyseergebnis-Imports.
     */
    public static void process(String[] columns) {
        if (columns == null || columns.length < 8) {
            AUIKataster.errorOutput(
                "Analyseimport: AtlAnalyseoption nicht vollständig.",
                "AnalyseProcessor");
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

        AUIKataster.debugOutput(
            "Verarbeite Analyseposition für Parameter: " +
            parameterBezeichnung);

        AtlProbenahmen probe = AtlProbenahmen.getProbenahme(kennnummer, true);

        if (probe == null) {
            AUIKataster.errorOutput(
                "Probenahme mit folgender Kennung konnte nicht " +
                "gefunden werden: " + kennnummer,
                "AnalyseProcessor");

            return;
        }

        AtlParameter parameter = AtlParameter.getParameter(ordnungsbegriff);
        AtlEinheiten einheit   = AtlEinheiten.getEinheit(id);
        AtlAnalyseposition pos = probe.findAtlAnalyseposition(
            parameter, einheit);

        if (pos != null) {
            pos.setWert(Float.parseFloat(wert));
            pos.setGrkl(grkl);

            AtlProbenahmen.updateProbenahme(probe);
        }
    }


    /**
     * Diese Funktion liefert einen Teilstring einer Eingabe.
     *
     * @param raw Ein String, der als erstes und letztes Zeichen ein einfaches
     * Anf&uuml;hrungsstrich besitzt.
     *
     * @return Den Eingabestring ohne erstes und letztes Zeichen.
     */
    public static String unquote(String raw) {
        return raw.substring(1, raw.length()-1);
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
        AtlProbenahmen probe = AtlProbenahmen.getProbenahme(kenn, true);

        if (probe == null) {
            return -1;
        }

        if (param == null || einheit == null) {
            return 2;
        }

        int               id   = Integer.parseInt(einheit);
        AtlParameter       p   = AtlParameter.getParameter(param);
        AtlEinheiten       e   = AtlEinheiten.getEinheit(id);

        if (p == null || e == null) {
            return -1;
        }

        AtlAnalyseposition pos = probe.findAtlAnalyseposition(p, e, false);

        return pos != null ? 1 : 2;
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
