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
 * Datei:
 * $Id: ChartDataSets.java,v 1.2 2009-03-24 12:35:21 u633d Exp $
 *
 * Erstellt am 11.05.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.5  2005/05/19 11:28:35  u633z
 * Kommentare verbessert
 *
 * Revision 1.4  2005/05/18 15:31:59  u633z
 * Diagramm-Erzeugung verbessert, Funktionalität der Auswertung hinzugefügt
 *
 * Revision 1.3  2005/05/17 11:18:50  u633z
 * Einen Parameter umbenannt
 *
 * Revision 1.2  2005/05/12 08:58:01  u633z
 * JavaDoc / Kommentare verbessert
 *
 * Revision 1.1  2005/05/11 15:38:39  u633z
 * Erster Test mit Diagrammen (aus echten Daten)
 *
 */
package de.bielefeld.umweltamt.aui.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;

/**
 * Eine Factory-Klasse um DataSets etc. für JFreeChart-Diagramme aus
 * verschiedenen Daten zu erzeugen.
 * @author David Klotz
 */
public class ChartDataSets {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /*
     * Private, da nie eine Instanz dieser Klasse erzeugt werden soll.
     */
    private ChartDataSets() {
    }

    /**
     * Erzeugt eine TimeSeriesCollection (eine Sammlung von Zeit/Wert-Datenreihen)
     * mit einer Datenreihe. Die Zeitwerte werden als Zeitpunkte und nicht
     * als Zeitabschnitte behandelt.
     * @param series Die erste Datenreihe (mehr können hinzugefügt werden)
     * @return Eine TimeSeriesCollection mit einer Datenreihe
     */
    public static TimeSeriesCollection createDataset(TimeSeries series) {
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);

        return dataset;
    }

    /**
     * Erzeugt eine TimeSeries (eine Zeit/Wert-Datenreihe) aus einer Liste
     * mit AtlAnalysepositionen. Die Analysepositionen sollten natürlich
     * alle den selben Parameter und die selbe Einheit haben.
     * @param list Die Liste aus <code>AtlAnalyseposition</code>en
     * @param name Der Name des Parameters/der Datenreihe (für die Legende des Diagramms)
     * @param einheit Der Name der Einheit (für die Achsenbeschriftung des Diagramms)
     * @return Eine Analysepositionen-Datenreihe
     */
    public static TimeSeries createAnalysePositionenSeries(List<?> list, String name, String einheit) {
        TimeSeries result = new TimeSeries(name, "Datum", "["+einheit+"]");
        log.debug("Erzeuge TimeSeries: " + name);
        Calendar cal = GregorianCalendar.getInstance();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Analyseposition) {
                    Analyseposition pos = (Analyseposition) list.get(i);

                    int hour = 0;
                    int minute = 0;

                    cal.setTime(pos.getProbenahme().getDatumDerEntnahme());
                    if (pos.getProbenahme().getZeitDerEntnahmen() != null) {
                        String zeit = pos.getProbenahme().getZeitDerEntnahmen();
                        //Zeit ist im Format: "15:30:00"
                        hour = Integer.parseInt(zeit.substring(0,2));
                        minute = Integer.parseInt(zeit.substring(3,5));
                    }

                    Minute min = new Minute(minute, hour, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
                    addPosToMinuteSeries(result, min, pos);
                }
            }
        }

        return result;
    }

    /**
     * Fügt den Wert einer AtlAnalyseposition zu einer TimeSeries an
     * einer bestimmten Position (in Minuten) hinzu.
     * Wenn an dieser Position schon ein Wert existiert, ruft sich diese
     * Methode rekursiv mit einer Position eine Minute später wieder auf.
     * @param series Die Datenreihe
     * @param minute Die Position in der Datenreihe (in Minuten)
     * @param pos Die Analyseposition
     */
    private static void addPosToMinuteSeries(TimeSeries series, Minute minute, Analyseposition pos) {
        if (series.getDataItem(minute) == null) {
            //AUIKataster.debugOutput("  |- Füge " + pos + " bei " + minute + " hinzu.", "ChartDataSets.createAnalysepositionenSeries");
            series.add(minute, pos.getWert());
        } else {
            //AUIKataster.debugOutput("  |- !Bei " + minute + " existiert schon ein Eintrag -> Rekursion!.", "ChartDataSets.createAnalysepositionenSeries");
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(pos.getProbenahme().getDatumDerEntnahme());
            Minute min2 = new Minute(minute.getMinute()+1, minute.getHour().getHour(), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));

            addPosToMinuteSeries(series, min2, pos);
        }
    }
}
