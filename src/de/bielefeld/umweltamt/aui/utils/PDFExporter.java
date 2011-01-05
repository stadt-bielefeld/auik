/*
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.utils;

import java.io.InputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;


/**
 * Diese Klasse stellt einige Hilfsfunktionen zur Verf&uuml;gung, mit denen sich
 * PDFs erstellen lassen.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class PDFExporter {

    /**
     * Die einzige Instanz dieser Klasse wird in dieser Variablen gespeichert.
     */
    private static PDFExporter INSTANCE;


    /**
     * Dieser Konstruktor soll nicht aufgerufen werden, um zu verhindern, dass
     * mehr als nur eine Instanz dieser Klasse erstellt wird. Stattdessen soll
     * {@link getInstance()} verwendet werden, um an eine Instanz zu gelangen.
     */
    private PDFExporter() {
    }


    /**
     * Liefert die Singleton Instanz dieser Klasse.
     *
     * @return die einzige Instanz dieser Klasse.
     */
    public static PDFExporter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PDFExporter();
        }

        return INSTANCE;
    }

    /**
     * Diese Funktion erzeugt ein PDF mit Hilfe von <i>JasperReport</i>. Die
     * dazu ben&ouml;tigten Templates m&uuml;ssen sich in <i>build/reports/</i>
     * befinden und bereits compiliert sein.
     *
     * @param fields Ein Map Objekt dessen Werte in das Template
     * einzuf&uuml;llen sind.
     * @param reportFile Der Name des <i>JasperReport</i> Templates <b>ohne</b>
     * Endung.
     * @param dest Der Pfad zu einem Ort an dem das PDF gespeichert werden soll.
     *
     * @return ein bef&uuml;lltes {@link JasperPrint} Objekt.
     */
    protected static JasperPrint export(
        Map fields, InputStream reportFile, String dest)
    throws Exception
    {
        JasperPrint print = JasperFillManager.fillReport(
            reportFile,
            fields,
            new JREmptyDataSource(1));

        JasperExportManager.exportReportToPdfFile(print, dest);

        return print;
    }


    /**
     * Diese Funktion erzeugt ein PDF eines Probenahmeauftrags. Der eigentliche
     * Export wird von {@link export(Map, String, String)} get&auml;tigt. Das
     * verwendete Template hei&szilg;t <i>probenahmeauftrag</i> und muss sich in
     * <i>build/reports</i> in compilierter Form befinden.
     *
     * @param fields Ein Map Objekt dessen Werte in das Template
     * einzuf&uuml;llen sind.
     * @param destination Der Pfad zu einem Ort, an dem das PDF gespeichert
     * werden soll.
     * @param printPDF Wenn das erzeugte PDF gedruckt werden soll, muss diese
     * Flagge gesetzt werden.
     *
     * @return ein bef&uuml;lltes {@link JasperPrint} Objekt.
     */
    public JasperPrint exportAuftrag(
        Map fields, JRDataSource subdata, String dest, boolean printPDF)
    throws Exception
    {
        InputStream inputStream = getClass().getResourceAsStream(
            "/reports/probenahmeauftrag.jasper");

        if (inputStream == null) {
            throw new Exception(
                "Konnte Template 'probenahmeauftrag.jasper' nicht finden.");
        }

        fields.put("SUBDATA", subdata);

        try {
            JasperPrint jprint = export(fields, inputStream, dest);

            if (printPDF) {
                print(jprint);
            }

            return jprint;
        }
        catch (JRException jre) {
            throw new Exception(
                "Druck des Probenahmeauftrag schlug fehl: " + jre.getMessage());
        }
    }


    /**
     * Diese Funktion erzeugt ein PDF eines Geb&uuml;hrenbescheids. Der
     * eigentliche Export wird von {@link export(Map, String, String)}
     * get&auml;tigt. Das verwendete Template hei&szilg;t
     * <i>gebuehrenbescheid</i> und muss sich in <i>build/reports</i> in
     * compilierter Form befinden.
     *
     * @param fields Ein Map Objekt dessen Werte in das Template
     * einzuf&uuml;llen sind.
     * @param destination Der Pfad zu einem Ort, an dem das PDF gespeichert
     * werden soll.
     * @param printPDF Wenn das erzeugte PDF gedruckt werden soll, muss diese
     * Flagge gesetzt werden.
     *
     * @return ein bef&uuml;lltes {@link JasperPrint} Objekt.
     */
    public JasperPrint exportBescheid(
        Map fields, JRDataSource subdata, String dest, boolean printPDF)
    throws Exception
    {
        InputStream inputStream = getClass().getResourceAsStream(
            "/reports/gebuehrenbescheid.jasper");

        if (inputStream == null) {
            throw new Exception(
                "Konnte Template 'gebuehrenbescheid.jasper' nicht finden.");
        }

        fields.put("SUBDATA", subdata);

        try {
            JasperPrint jprint = export(fields, inputStream, dest);

            if (printPDF) {
                print(jprint);
            }

            return jprint;
        }
        catch (JRException jre) {
            throw new Exception(
                "Druck des Gebührenbescheid schlug fehl: " + jre.getMessage());
        }
    }


    /**
     * Diese Funktion erlaubt es, ein JasperPrint Objekt an einen Drucker zu
     * schicken.
     *
     * @param jprint Ein JasperPrint Objekt, welches zB bei
     * {@link exportAuftrag(Map,String,boolean)} oder {@link
     * exportBescheid(Map,String,boolean)} zurückgeliefert wird.
     */
    public static void print(JasperPrint jprint)
    throws Exception
    {
        JasperPrintManager.printReport(jprint, true);
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
