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
 * $Id: Charts.java,v 1.3 2009-04-28 06:59:43 u633d Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:38  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2005/05/31 15:52:18  u633z
 * - Charts und ChartDataSets nach utils.charts verschoben
 * - Neue Klasse APosDataItem um eine Analyseposition als Punkt einer
 *   TimeSeries Datenreihe zu repräsentieren
 * - Bessere Tooltips im Auswertungs-Diagramm
 *
 * Revision 1.4  2005/05/19 06:48:37  u633z
 * Farben angepasst (weißer Hintergrund, hellgraue Linien, für besseren S/W-Ausdruch)
 *
 * Revision 1.3  2005/05/18 15:31:59  u633z
 * Diagramm-Erzeugung verbessert, Funktionalität der Auswertung hinzugefügt
 *
 * Revision 1.2  2005/05/12 08:57:23  u633z
 * JavaDoc / Kommentare verbessert
 *
 * Revision 1.1  2005/05/11 15:38:39  u633z
 * Erster Test mit Diagrammen (aus echten Daten)
 *
 *
 * Erstellt am 09.05.2005 von u633z (David Klotz)
 */
package de.bielefeld.umweltamt.aui.utils.charts;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;

/**
 * Eine Factory-Klasse um oft benutzte Diagramme zu erzeugen
 * @author David Klotz
 */
public class Charts {

    /*
     * Private, da nie eine Instanz dieser Klasse erzeugt werden soll.
     */
    private Charts() {
    }

    /**
     * Erzeugt ein neues Zeit/Wert-Liniendiagramm.
     * Das Diagramm hat keinen Untertitel und der Name der Y-Achse wird aus dem Dataset entnommen.
     * @param titel Die Überschrift
     * @param dataset Die Daten, die geplottet werden sollen.
     * @return Das neue Diagramm
     */
    public static JFreeChart createDefaultTimeSeriesChart(String titel, TimeSeriesCollection dataset) {
        String tmp = null;
        return createDefaultTimeSeriesChart(titel, tmp, dataset);
    }

    /**
     * Erzeugt ein neues Zeit/Wert-Liniendiagramm.
     * Der Name der Y-Achse wird aus dem Dataset entnommen.
     * @param titel Die Überschrift
     * @param unterTitel Ein Untertitel (kann auch <code>null</code> sein)
     * @param dataset Die Daten, die geplottet werden sollen.
     * @return Das neue Diagramm
     */
    public static JFreeChart createDefaultTimeSeriesChart(String titel, String unterTitel, TimeSeriesCollection dataset) {
        String tmp = null;
        return createDefaultTimeSeriesChart(titel, unterTitel, tmp, dataset);
    }

    /**
     * Erzeugt ein neues Zeit/Wert-Liniendiagramm.
     * @param titel Die Überschrift
     * @param unterTitel Ein Untertitel (kann auch <code>null</code> sein)
     * @param yTitel Der Titel der Y-Achse (wenn er <code>null</code> ist, wird der Titel aus dem dataset genommen)
     * @param dataset Die Daten, die geplottet werden sollen.
     * @return Das neue Diagramm
     */
    public static JFreeChart createDefaultTimeSeriesChart(String titel, String unterTitel, String yTitel, TimeSeriesCollection dataset) {
        // Das Diagramm erzeugen
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                titel,        // Chart-Titel
                "Datum",     // X-Achsenbeschriftung
                yTitel,     // Y-Achsenbeschriftung
                dataset,     // Die Daten
                true,         // Legende zeigen
                true,         // Tooltips zeigen
                false        // Urls? (vermutlich nur für Servlets...)
        );

        // Die Hintergrundfarbe für das ganze Diagramm setzen
        chart.setBackgroundPaint(Color.WHITE);
        // Dem Diagramm einen sichtbaren Rand geben
        chart.setBorderVisible(true);

        // Den Untertitel (falls vorhanden) setzen
        if (unterTitel != null && !unterTitel.equals("")) {
            TextTitle subTitle = new TextTitle(unterTitel);
            subTitle.setPosition(RectangleEdge.TOP);
            chart.addSubtitle(subTitle);
        }

        // Den Plot anpassen...
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);        // Hintergrundfarbe
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);        // Farbe der X-Gitterlinien
        plot.setDomainGridlinesVisible(true);            // X-Gitterlinen sichtbar
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);        // Farbe der Y-Gitterlinien

        // Eine Referenz auf die Werte-Achse holen
        NumberAxis axis = (NumberAxis) plot.getRangeAxis();

        // Wenn kein Titel übergeben wird, benutzen wir den der ersten Datenreihe
        if (yTitel == null) {
            if (dataset.getSeriesCount() > 0) {
                TimeSeries series = dataset.getSeries(0);
                axis.setLabel(series.getRangeDescription());
            }
        }

        /*XYPointerAnnotation anno = new XYPointerAnnotation("14.08.03", new Day(14, 8, 2003).getFirstMillisecond(), 3.9, Math.PI / 2);
        anno.setLabelOffset(8.0);
        anno.setTipRadius(8.0);
        anno.setBaseRadius(25.0);
        anno.setPaint(Color.YELLOW);
        anno.setArrowPaint(Color.YELLOW);
        //anno.setRotationAnchor(TextAnchor.CENTER_LEFT);
        //anno.setRotationAngle(Math.PI);
        plot.addAnnotation(anno);*/

        // Tooltip-Generator, der den Namen der Messreihe, das Datum und den Wert anzeigt.
        //XYToolTipGenerator ttgen = new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, new SimpleDateFormat("dd.MM.yy HH:mm"), NumberFormat.getInstance());
        XYToolTipGenerator ttgen = new APosToolTipGenerator();

        // Den Renderer für die Messwerte/-linien anpassen
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        //TODO: defaultShapes == baseShapes?
		renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setToolTipGenerator(ttgen);

        // Die Datumsachse anpassen
        DateAxis datumsAchse = (DateAxis) plot.getDomainAxis();
        datumsAchse.setVerticalTickLabels(true);
        //datumsAchse.setTickLabelsVisible(false);
        //datumsAchse.setTickMarksVisible(false);
        datumsAchse.setDateFormatOverride(new SimpleDateFormat("dd.MM.yy"));
        return chart;
    }

    /**
     * Erzeugt ein neues Zeit/Wert-Liniendiagramm mit zwei Y-Achsen.
     * Das Diagramm hat keinen Untertitel und der Name der Y-Achsen wird aus den Datasets entnommen.
     * @param titel Die Überschrift
     * @param dataset1 Die Daten, die gegen die erste Achse geplottet werden sollen.
     * @param dataset2 Die Daten, die gegen die zweite Achse geplottet werden sollen.
     * @return Das neue Diagramm
     */
    public static JFreeChart createDefaultTimeSeriesChart(String titel, TimeSeriesCollection dataset1, TimeSeriesCollection dataset2) {
        return createDefaultTimeSeriesChart(titel, null, null, null, dataset1, dataset2);
    }

    /**
     * Erzeugt ein neues Zeit/Wert-Liniendiagramm mit zwei Y-Achsen.
     * Der Name der Y-Achsen wird aus den Datasets entnommen.
     * @param titel Die Überschrift
     * @param unterTitel Ein Untertitel (kann auch <code>null</code> sein)
     * @param dataset1 Die Daten, die gegen die erste Achse geplottet werden sollen.
     * @param dataset2 Die Daten, die gegen die zweite Achse geplottet werden sollen.
     * @return Das neue Diagramm
     */
    public static JFreeChart createDefaultTimeSeriesChart(String titel, String unterTitel, TimeSeriesCollection dataset1, TimeSeriesCollection dataset2) {
        return createDefaultTimeSeriesChart(titel, unterTitel, null, null, dataset1, dataset2);
    }

    /**
     * Erzeugt ein neues Zeit/Wert-Liniendiagramm mit zwei Y-Achsen.
     * @param titel Die Überschrift
     * @param unterTitel Ein Untertitel (kann auch <code>null</code> sein)
     * @param yTitel1 Der Titel der ersten Y-Achse (wenn er <code>null</code> ist, wird der Titel aus dem dataset1 genommen)
     * @param yTitel2 Der Titel der zweiten Y-Achse (wenn er <code>null</code> ist, wird der Titel aus dem dataset2 genommen)
     * @param dataset1 Die Daten, die gegen die erste Achse geplottet werden sollen.
     * @param dataset2 Die Daten, die gegen die zweite Achse geplottet werden sollen.
     * @return Das neue Diagramm
     */
    public static JFreeChart createDefaultTimeSeriesChart(String titel, String unterTitel, String yTitel1, String yTitel2, TimeSeriesCollection dataset1, TimeSeriesCollection dataset2) {
        // Das Diagramm erzeugen
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                titel,        // Chart-Titel
                "Datum",     // X-Achsenbeschriftung
                yTitel1,     // Y-Achsenbeschriftung
                dataset1,     // Die Daten
                false,         // Legende zeigen
                true,         // Tooltips zeigen
                false        // Urls? (vermutlich nur für Servlets...)
        );

        // Die Hintergrundfarbe für das ganze Diagramm setzen
        chart.setBackgroundPaint(Color.WHITE);
        // Dem Diagramm einen sichtbaren Rand geben
        chart.setBorderVisible(true);

        // Den Untertitel (falls vorhanden) setzen
        if (unterTitel != null && !unterTitel.equals("")) {
            TextTitle subTitle = new TextTitle(unterTitel);
            subTitle.setPosition(RectangleEdge.TOP);
            chart.addSubtitle(subTitle);
        }

        // Den Plot anpassen...
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);        // Hintergrundfarbe
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);        // Farbe der X-Gitterlinien
        plot.setDomainGridlinesVisible(true);            // X-Gitterlinen sichtbar
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);        // Farbe der Y-Gitterlinien

        // Eine Referenz auf die erste Werte-Achse holen
        NumberAxis axis1 = (NumberAxis) plot.getRangeAxis();

        // Wenn kein Titel übergeben wird, benutzen wir den der ersten Datenreihe
        if (yTitel1 == null) {
            if (dataset1.getSeriesCount() > 0) {
                TimeSeries series = dataset1.getSeries(0);
                axis1.setLabel(series.getRangeDescription());
            }
        }

        // Die zweite Achse erzeugen
        NumberAxis axis2 = new NumberAxis(yTitel2);
        axis2.setAutoRangeIncludesZero(false);

        // Wenn kein Titel übergeben wird, benutzen wir den der zweiten Datenreihe
        if (yTitel2 == null) {
            if (dataset2.getSeriesCount() > 0) {
                TimeSeries series = dataset2.getSeries(0);
                axis2.setLabel(series.getRangeDescription());
            }
        }

        // Die zweite Achse dem Plot hinzufügen und mit dem zweiten Datensatz verknüpfen
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, new XYLineAndShapeRenderer());
        plot.mapDatasetToRangeAxis(1, 1);

        // Die Datumsachse anpassen
        DateAxis datumsAchse = (DateAxis) plot.getDomainAxis();
        datumsAchse.setVerticalTickLabels(true);

        // Tooltip-Generator, der den Namen der Messreihe, das Datum und den Wert anzeigt.
        //XYToolTipGenerator ttgen = new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, new SimpleDateFormat("dd.MM.yy hh:mm"), NumberFormat.getInstance());
        XYToolTipGenerator ttgen = new APosToolTipGenerator();

        // Den Renderer für die Messwerte/-linien der ersten Achse anpassen
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        //TODO: defaultShapes == baseShapes?
		renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setToolTipGenerator(ttgen);
//        if (dataset1.getSeriesCount() == 1) {
//            axis1.setLabelPaint(renderer.getSeriesPaint(0));
//        }

        // Den Renderer für die Messwerte/-linien der zweiten Achse anpassen
        XYLineAndShapeRenderer renderer2 = (XYLineAndShapeRenderer) plot.getRenderer(1);
        //TODO: defaultShapes == baseShapes?
		renderer2.setBaseShapesVisible(true);
        renderer2.setBaseShapesFilled(true);
//        renderer2.setToolTipGenerator(ttgen);
//        if (dataset2.getSeriesCount() == 1) {
//            axis2.setLabelPaint(renderer2.getSeriesPaint(0));
//        }

        // Die Legende anpassen
        LegendTitle l1 = new LegendTitle(renderer);
        l1.setMargin(new RectangleInsets(1.0, 1.0, 1.0, 5.0));
        l1.setBorder(new BlockBorder());

        LegendTitle l2 = new LegendTitle(renderer2);
        l2.setMargin(new RectangleInsets(1.0, 1.0, 1.0, 5.0));
        l2.setBorder(new BlockBorder());

        BlockContainer cont = new BlockContainer();
        cont.add(l1, RectangleEdge.LEFT);
        cont.add(l2, RectangleEdge.RIGHT);

        CompositeTitle compLegend = new CompositeTitle(cont);
        compLegend.setMargin(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        compLegend.setPosition(RectangleEdge.BOTTOM);

        chart.addSubtitle(compLegend);

        // Das fertige Diagramm zurückliefern
        return chart;
    }
}

/**
 * Ein Tooltip-Generator für Diagramme mit Analyseposition-Datenreihen.
 * @author David Klotz
 */
class APosToolTipGenerator implements XYToolTipGenerator {
    /**
     * Erzeugt einen Tooltip der Form:
     * <br><b>23.5 mg/kg</b><br>
     * Kupfer (Cu), Heepen<br>
     * Probe: <b>2342/05</b>, 23.04.05 00:00<br>
     */
    public String generateToolTip(XYDataset dataset, int series, int item) {
    	final DecimalFormat df = new DecimalFormat("0.00");
        TimeSeriesCollection col = (TimeSeriesCollection) dataset;
        TimeSeries ser = col.getSeries(series);
        APosDataItem it = (APosDataItem) ser.getItems().get(item);
        Analyseposition pos = it.getAnalysePosition();
        Probenahme probe = pos.getProbenahme();
        //TODO: TimeSeries key == name?
        if (probe.getArt() != null && probe.getArt().equals("Sielhaut")) {
			return "<html>" +
	                "<b>"+"Verhältnis zum Hintergrundwert "+df.format(it.getValue())+"</b><br>" +
	                ser.getKey().toString() + "<br>" +
	                "Probe: <b>"+probe.getKennummer()+"</b>, "+AuikUtils.getStringFromDate(probe.getDatumDerEntnahme())+((probe.getZeitDerEntnahmen() != null) ?  " "+probe.getZeitDerEntnahmen().substring(0,5) : "")+"<br>" +
	                "</html>";
        } else {
    		return "<html>" +
                    "<b>"+it.getValue()+" "+pos.getEinheiten()+"</b><br>" +
                    ser.getKey().toString() + "<br>" +
                    "Probe: <b>"+probe.getKennummer()+"</b>, "+AuikUtils.getStringFromDate(probe.getDatumDerEntnahme())+((probe.getZeitDerEntnahmen() != null) ?  " "+probe.getZeitDerEntnahmen().substring(0,5) : "")+"<br>" +
                    "</html>";
        }
    }
}
