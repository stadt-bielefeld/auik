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
package de.bielefeld.umweltamt.aui.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import org.hibernate.jdbc.Work;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.SettingsManager;

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

	private static final String DEFAULTPATHTOJASPER = "resources/reports/";
	private static String CONFIGPATHTOJASPER = "";
	private boolean externalTemplates = false;
	private Connection connection = null;
	/** Die Geb&uuml;hrenbescheid. */
	public static final String BESCHEID = "gebuehrenbescheid.jasper";

	/** Die Verf&uuml;gung. */
	public static final String VFG = "verfuegung.jasper";

	/** Objekt-Chronologie. */
	public static final String OBJEKTCHRONOLOGIE = "objekt_chronologie.jasper";

	/** Sielhaut Bearbeiten. */
	public static final String SIELHAUT_BEARBEITEN = "sielhaut_bearbeiten.jasper";

	/** VAwS-Anlagen. */
	public static final String VAWS_ANLAGEN = "vaws_anlagen.jasper";

	/** VAwS-Liste. */
	public static final String VAWS_LISTE = "vaws_liste.jasper";

	/** VAwS-StandortListe. */
	public static final String VAWS_STANDORTLISTE = "vaws_standortliste.jasper";

	/** VAwS-StandortListe. */
	public static final String SUEV_LISTE = "suevkan.jasper";

	/** VAwS-StandortListe. */
	public static final String HANDAKTE_DECKBLATT = "deckblatt_handakte.jasper";

	/**
	 * Dieser Konstruktor soll nicht aufgerufen werden, um zu verhindern, dass
	 * mehr als nur eine Instanz dieser Klasse erstellt wird. Stattdessen soll
	 * {@link getInstance()} verwendet werden, um an eine Instanz zu gelangen.
	 */
	private PDFExporter() {

		SettingsManager settings = SettingsManager.getInstance();

		CONFIGPATHTOJASPER = settings.getTemplatepath();
		if (CONFIGPATHTOJASPER != null) {
			if (!CONFIGPATHTOJASPER.isEmpty()) {
				externalTemplates = true;
				if (!CONFIGPATHTOJASPER.endsWith(File.separator)) {
					CONFIGPATHTOJASPER = CONFIGPATHTOJASPER
							.concat(File.separator);
				}
			}
		}
	}

	/**
	 * Methode liefert den Vorlagenpfad für die Jasper-Vorlagen
	 * 
	 * @return
	 */
	private String getPathToJasper() {

		if (CONFIGPATHTOJASPER == null) {
			return DEFAULTPATHTOJASPER;
		}

		if (CONFIGPATHTOJASPER.isEmpty()) {
			return DEFAULTPATHTOJASPER;
		}

		return CONFIGPATHTOJASPER;
	}

	/*
	 * Methode lädt die angegebene Resource entweder aus dem Classpath oder von
	 * einem angegeben Templatepath
	 */
	private InputStream getResourceAsStream(String template) {

		String path = getPathToJasper() + template;
		InputStream inputStream;

		if (externalTemplates) {
			File f = new File(path);
			try {
				inputStream = new FileInputStream(f);
				return inputStream;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		inputStream = AUIKataster.class.getResourceAsStream(path);
		return inputStream;
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
	 * @param fields
	 *            Ein Map Objekt dessen Werte in das Template einzuf&uuml;llen
	 *            sind.
	 * @param reportFile
	 *            Der Name des <i>JasperReport</i> Templates <b>ohne</b> Endung.
	 * @param dest
	 *            Der Pfad zu einem Ort an dem das PDF gespeichert werden soll.
	 * @param connection
	 *            SQL connection fuer den Report
	 *
	 * @return ein bef&uuml;lltes {@link JasperPrint} Objekt.
	 */
	protected static JasperPrint export(Map<String, Object> fields,
			InputStream reportFile, String dest, Connection connection)
			throws JRException {
		JasperPrint print = JasperFillManager.fillReport(reportFile, fields,
				connection);

		JasperExportManager.exportReportToPdfFile(print, dest);
		AuikUtils.spawnFileProg(new File(dest));

		return print;
	}

	/**
	 * Diese Funktion erzeugt ein PDF mit Hilfe von <i>JasperReport</i>. Die
	 * dazu ben&ouml;tigten Templates m&uuml;ssen sich in <i>build/reports/</i>
	 * befinden und bereits compiliert sein.
	 *
	 * @param fields
	 *            Ein Map Objekt dessen Werte in das Template einzuf&uuml;llen
	 *            sind.
	 * @param reportFile
	 *            Der Name des <i>JasperReport</i> Templates <b>ohne</b> Endung.
	 * @param dest
	 *            Der Pfad zu einem Ort an dem das PDF gespeichert werden soll.
	 *
	 * @return ein bef&uuml;lltes {@link JasperPrint} Objekt.
	 */
	protected static JasperPrint export(Map<String, Object> fields,
			InputStream reportFile, String dest) throws JRException {
		JasperPrint print = JasperFillManager.fillReport(reportFile, fields,
				new JREmptyDataSource(1));

		JasperExportManager.exportReportToPdfFile(print, dest);
		AuikUtils.spawnFileProg(new File(dest));

		return print;
	}

	/**
	 * Diese Funktion erzeugt ein PDF eines Probenahmeauftrags. Der eigentliche
	 * Export wird von {@link export(Map, String, String)} get&auml;tigt. Das
	 * verwendete Template hei&szilg;t <i>probenahmeauftrag</i> und muss sich in
	 * <i>build/reports</i> in compilierter Form befinden.
	 *
	 * @param fields
	 *            Ein Map Objekt dessen Werte in das Template einzuf&uuml;llen
	 *            sind.
	 * @param destination
	 *            Der Pfad zu einem Ort, an dem das PDF gespeichert werden soll.
	 * @param printPDF
	 *            Wenn das erzeugte PDF gedruckt werden soll, muss diese Flagge
	 *            gesetzt werden.
	 *
	 * @return ein bef&uuml;lltes {@link JasperPrint} Objekt.
	 */
	public JasperPrint exportAuftrag(Map<String, Object> fields,
			JRDataSource subdata, String dest, boolean printPDF)
			throws Exception {
		InputStream inputStream = getResourceAsStream("probenahmeauftrag.jasper");

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
		} catch (JRException jre) {
			throw new Exception("Druck des Probenahmeauftrag schlug fehl: "
					+ jre.getMessage());
		}
	}

	/**
	 * Diese Funktion erzeugt ein PDF eines Geb&uuml;hrenbescheids. Der
	 * eigentliche Export wird von {@link export(Map, String, String)}
	 * get&auml;tigt. Das verwendete Template hei&szilg;t
	 * <i>gebuehrenbescheid</i> und muss sich in <i>build/reports</i> in
	 * compilierter Form befinden.
	 *
	 * @param fields
	 *            Ein Map Objekt dessen Werte in das Template einzuf&uuml;llen
	 *            sind.
	 * @param destination
	 *            Der Pfad zu einem Ort, an dem das PDF gespeichert werden soll.
	 * @param printPDF
	 *            Wenn das erzeugte PDF gedruckt werden soll, muss diese Flagge
	 *            gesetzt werden.
	 *
	 * @return ein bef&uuml;lltes {@link JasperPrint} Objekt.
	 */
	public JasperPrint exportBescheid(Map<String, Object> fields,
			JRDataSource subdata, String report, String dest, boolean printPDF)
			throws Exception {
		InputStream inputStream = getResourceAsStream(report);

		if (inputStream == null) {
			throw new Exception("Konnte Template '" + report + "' oder "
					+ "'verfuegung.jasper' nicht finden.");
		}

		fields.put("SUBDATA", subdata);

		try {
			JasperPrint jprint = export(fields, inputStream, dest);

			if (printPDF) {
				// print(jprint);
			}

			return jprint;
		} catch (JRException jre) {
			throw new Exception("Druck des Reports schlug fehl: "
					+ jre.getMessage());
		}
	}

	/**
	 * Diese Funktion erzeugt ein PDF aus einer Jasper Report Vorlage. Der
	 * eigentliche Export wird von {@link export(Map, String, String)}
	 * get&auml;tigt. Das verwendete Template hei&szilg;t wird als parameter
	 * <i>report</i> &uuml;bergeben und muss sich in <i>build/reports</i> in
	 * compilierter Form befinden. Als Datenquelle wird die jdbc verbindung
	 * verwendet.
	 *
	 * @param fields
	 *            Ein Map Objekt dessen Werte in das Template einzuf&uuml;llen
	 *            sind.
	 * @param report
	 *            Der Name des Jasper Report templates.
	 * @param destination
	 *            Der Pfad zu einem Ort, an dem das PDF gespeichert werden soll.
	 *
	 * @return ein bef&uuml;lltes {@link JasperPrint} Objekt.
	 */
	@SuppressWarnings("deprecation")
	// See comment below for hibernate4
	public JasperPrint exportReport(Map<String, Object> fields, String report,
			String destination) throws Exception {
		InputStream inputStream = getResourceAsStream(report);

		if (inputStream == null) {
			throw new Exception("Konnte Template '" + report
					+ "' nicht finden.");
		}

		try {
			JasperPrint jprint = null;
			//Deprecated
			//Connection con = HibernateSessionFactory.currentSession()
			//		.connection(); 
			//jprint = export(fields, inputStream, destination, con);
			
			// With Hibernate 4:
			HibernateSessionFactory.currentSession().doWork( new Work() {
			public void execute(Connection con) throws SQLException {
			//jprint = export(fields, inputStream, destination, connection); }
			connection = con;}
			} );
			 
			jprint = export(fields, inputStream, destination, connection);
			
			return jprint;
		} catch (JRException jre) {
			jre.printStackTrace();
			throw new Exception("Export des Reports schlug fehl: "
					+ jre.getMessage());
		}
	}

	/**
	 * Diese Funktion erlaubt es, ein JasperPrint Objekt an einen Drucker zu
	 * schicken.
	 *
	 * @param jprint
	 *            Ein JasperPrint Objekt, welches zB bei {@link
	 *            exportAuftrag(Map,String,boolean)} oder {@link
	 *            exportBescheid(Map,String,boolean)} zur&uuml;ckgeliefert wird.
	 */
	public static void print(JasperPrint jprint) throws JRException {
		JasperPrintManager.printReport(jprint, true);
	}
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
