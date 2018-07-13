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
 * $Id: SettingsManager.java,v 1.6.2.1 2010-11-23 10:25:58 u633d Exp $
 *
 * Erstellt am 31.01.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2010/02/24 10:44:51  u633d
 * Gis Pfade ueber Settings
 *
 * Revision 1.5  2009/11/12 06:33:01  u633d
 * Birt aktualisiert
 *
 * Revision 1.4  2009/10/06 09:15:20  u633d
 * hertsellnummerSuche
 *
 * Revision 1.3  2009/03/24 12:35:19  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/06/24 11:24:07  u633d
 * Version 0.3
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.22  2006/05/23 05:29:42  u633d
 * Objektchronologie für alle Objekte verfügbar gemacht
 *
 * Revision 1.21  2006/05/03 09:01:54  u633d
 * Anhang 40 und 56 ergänzt
 *
 * Revision 1.20  2005/11/02 13:45:44  u633d
 * - Version vom 2.11.
 *
 * Revision 1.19  2005/09/28 11:11:13  u633d
 * - Version vom 28.9.
 *
 * Revision 1.18  2005/08/25 14:47:00  u633d
 * - zu viel ;)
 *
 * Revision 1.17  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 * Revision 1.16  2005/07/06 09:27:09  u633z
 * - Default-Masken
 *
 * Revision 1.15  2005/06/30 11:21:28  u633z
 * - Datenbank-URL
 *
 * Revision 1.14  2005/05/23 10:08:08  u633z
 * - LaborProbeSuchen mit in die Liste der Standard-Module aufgenommen
 * - Header-Kommentar angepasst
 *
 */
package de.bielefeld.umweltamt.aui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import de.bielefeld.umweltamt.aui.mappings.basis.Lage;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.SortedProperties;

/**
 * Diese Klasse verwaltet alle Programm-Einstellungen. Alle momentan benutzten
 * Keys: siehe doc/properties.txt
 * 
 * @author David Klotz
 */
public class SettingsManager {
	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();
	/** GUI Manager */
	private static final GUIManager guiManager = GUIManager.getInstance();

	private Lage standort;
	private Properties instanceSettings;
	private SortedProperties appSettings;

	private static SettingsManager _instance;

	/**
	 * Erzeugt einen neuen SettingsManager und initialisiert die Werte der
	 * Einstellungen.
	 */
	private SettingsManager() {
		this.instanceSettings = new Properties();

		initAppSettings();
	}

	/**
	 * Liefert den (einzigen) SettingsManager. Wenn diese Methode zum ersten Mal
	 * aufgerufen wird, wird eine Instanz dieser Klasse erzeugt und
	 * initialisiert.
	 * 
	 * @return Eine (evtl. neu erzeugte) Instanz dieser Klasse.
	 */
	public static synchronized SettingsManager getInstance() {
		if (_instance == null) {
			_instance = new SettingsManager();
		}

		return _instance;
	}

	/**
	 * Überschreibt die Settings mit den Werten aus dem EinstellungenEditor
	 * 
	 * @param instance
	 *            Eine Settingsmanager Instanz.
	 * @return boolean True, wenn das Speichern erfolgreich war.
	 */
	public static Boolean setInstance(SettingsManager instance) {
		try {
			if (_instance != null) {
				_instance.appSettings = instance.appSettings;
				_instance.saveSettings();
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Initialisiert die Standard-Werte der Einstellungen und liest die Werte
	 * der Datei auik.properties im aktuellen Verzeichnis ein.
	 */
	private void initAppSettings() {
		appSettings = new SortedProperties();
		// SortedProperties defaults = new SortedProperties();
		appSettings.setProperty("auik.elka.identifier", "");
        appSettings.setProperty("auik.prefs.res_x", "700");
		appSettings.setProperty("auik.prefs.res_y", "525");
		appSettings.setProperty("auik.prefs.maximized", "false");
		appSettings.setProperty("auik.prefs.save_size", "true");

		appSettings
				.setProperty(
						"auik.system.module",

						"LageSuchen, BasisAdresseSuchen, LageNeu, "
								+ "BasisAdresseNeu, BasisObjektBearbeiten, EinleiterAnh31Auswertung, "
								+ "EinleiterAnh40Auswertung, EinleiterAnh49Auswertung, "
								+ "EinleiterAnh50Auswertung, EinleiterAnh52Auswertung, "
								+ "EinleiterAnh53Auswertung, EinleiterAnh55Auswertung, "
								+ "EinleiterAnh56Auswertung, EinleiterBescheidAuswertung, "
								+ "EinleiterBrennwertAuswertung, EinleiterFettabscheiderAuswertung, "
								+ "EinleiterGenehmigungAuswertung, EinleiterPrioritaetAuswertung, "
								+ "EinleiterSuevkanAuswertung, EinleiterUebergabestellenAuswertung, "
								+ "KlaerschlammAuswertung, KlaerschlammFaulschlammproben, "
								+ "KlaerschlammRohschlammproben, LaborProbeSuchen, LaborIcpImport, "
								+ "ProbepunkteAuswertung, SielhautBearbeiten, SielhautImport, "
								+ "VawsHerstellernummerSuchen, VawsKontrollenAuswertung, "
								+ "VawsVerwaltungsverfAuswertung, EditorEinheiten, "
								+ "EditorParameter, EditorStrassen, EditorObjektarten, "
								+ "AnalyseImport, DBSyncModul");

		appSettings.setProperty("auik.prefs.status_time", "40");
		appSettings.setProperty("auik.prefs.sielhaut_labor", "Eurofins");
		appSettings.setProperty("auik.system.spath_fotos",
				"X:/Applikationen/Anlagenkataster/Sielhaut/fotos/");
		appSettings.setProperty("auik.system.spath_karten",
				"X:/Applikationen/Anlagenkataster/Sielhaut/karten/");
		appSettings.setProperty("auik.system.probepktpath_fotos",
				"X:/Applikationen/Anlagenkataster/Fotos/");
		appSettings.setProperty("auik.gis.programmpath",
				"C:\\appz\\qgis\\bin\\qgis.bat.lnk");
		appSettings.setProperty("auik.gis.projectpath",
				"D:\\data\\qgis\\MyProject.qgs");
		appSettings.setProperty("auik.probenahme.auftraege",
				"D:\\Data\\auik\\auftraege");
		appSettings
				.setProperty("auik.probenahme.bescheide",
						"X:\\Orga\\360\\360-1\\360-1-1\\Alle\\Texte\\Indirekteinleiter");
		appSettings.setProperty("auik.templatepath", "");

		// appSettings = new SortedProperties();

		try {
			appSettings.load(new FileInputStream("auik.properties"));
		} catch (FileNotFoundException e) {
			// Wir tun hier nichts. Wenn die Datei nicht gefunden
			// wird, wird sie halt nicht benutzt
			// e.printStackTrace();
		} catch (IOException e) {
			// Bin mir noch nicht ganz sicher, wann das hier
			// auftreten kann und ob uns das interessieren muss.
			// Die Defaults werden ja auch so benutzt...
			log.debug("Fehler beim laden der Benutzer-Einstellungen");
			e.printStackTrace();
		}
		getSettingList();
	}

	/**
	 * Alle persistenten Settings speichern.
	 */
	public void saveSettings() {
		try {

			appSettings.store(new FileOutputStream("auik.properties"),
					"Allgemeine Einstellungen für " + GUIManager.SHORT_NAME
							+ " v" + guiManager.getVersion());
		} catch (IOException e) {
			// Tritt auf, wenn aus irgend einem Grund keine
			// Datei gespeichert werden kann.
			log.debug("Konnte Einstellungen nicht speichern!");
			e.printStackTrace();
		}
	}

	/**
	 * Setzt eine Einstellung.
	 * 
	 * @param setting
	 *            Den Key der Einstellung.
	 * @param value
	 *            Den Wert der Einstellung.
	 * @param persist
	 *            Soll die Einstellung beim Programm-Ende gespeichert werden.
	 */
	public void setSetting(String setting, String value, boolean persist) {
		if (persist) {
			appSettings.setProperty(setting, value);
		} else {
			instanceSettings.setProperty(setting, value);
		}
	}

	/**
	 * Setzt eine Einstellung. Wrapper-Methode für setSetting(String, String,
	 * boolean).
	 * 
	 * @param setting
	 *            Den Key der Einstellung.
	 * @param value
	 *            Den Wert der Einstellung.
	 * @param persist
	 *            Soll die Einstellung beim Programm-Ende gespeichert werden.
	 */
	public void setSetting(String setting, int value, boolean persist) {
		setSetting(setting, Integer.toString(value), persist);
	}

	/**
	 * Setzt eine Einstellung. Wrapper-Methode für setSetting(String, String,
	 * boolean).
	 * 
	 * @param setting
	 *            Den Key der Einstellung.
	 * @param value
	 *            Den Wert der Einstellung.
	 * @param persist
	 *            Soll die Einstellung beim Programm-Ende gespeichert werden.
	 */
	public void setSetting(String setting, boolean value, boolean persist) {
		setSetting(setting, Boolean.toString(value), persist);
	}

	/**
	 * Setzt eine Einstellung. Wrapper-Methode für setSetting(String,
	 * Lage, boolean).
	 * 
	 * @param setting
	 *            Den Key der Einstellung.
	 * @param value
	 *            Den Wert der Einstellung.
	 * @param persist
	 *            Soll die Einstellung beim Programm-Ende gespeichert werden.
	 */
	public void setStandort(Lage std) {
		this.standort = std;
	}

	/**
	 * Liefert alle Einstellungen.
	 * 
	 * @return Liste aller Einstellungen.
	 */
	public String[][] getSettingList() {

		String[][] result;
		Enumeration<String> keys = appSettings.keys();
		ArrayList<String> temp = new ArrayList<String>();

		for (Enumeration<String> e = keys; keys.hasMoreElements();) {
			String key = e.nextElement();
			temp.add(key);
		}
		result = new String[temp.size()][2];

		for (int i = 0; i < temp.size(); i++) {
			result[i][0] = temp.get(i);
			result[i][1] = appSettings.getProperty(temp.get(i));
		}

		return result;
	}

	/**
	 * Liefert den aktuellen Wert einer Einstellung.
	 * 
	 * @param setting
	 *            Den Key der Einstellung.
	 * @return Den Wert der Einstellung oder <code>null</code>, falls diese
	 *         nicht existiert.
	 */
	public String getSetting(String setting) {
		if (instanceSettings.containsKey(setting)) {
			return instanceSettings.getProperty(setting);
		} else {
			return appSettings.getProperty(setting);
		}
	}

	/**
	 * Liefert den aktuellen Wert einer Einstellung. Wrapper-Methode.
	 * 
	 * @param setting
	 *            Den Key der Einstellung.
	 * @return Den Wert der Einstellung oder -1, falls diese nicht existiert.
	 */
	public int getIntSetting(String setting) {
		String tmp = getSetting(setting);
		if (tmp != null) {
			try {
				return Integer.parseInt(tmp);
			} catch (NumberFormatException e) {
				return -1;
			}
		} else {
			return -1;
		}
	}

	/**
	 * Liefert den aktuellen Wert einer Einstellung. Wrapper-Methode.
	 * 
	 * @param setting
	 *            Den Key der Einstellung.
	 * @return Den Wert der Einstellung oder <code>false</code>, falls diese
	 *         nicht existiert.
	 */
	public boolean getBoolSetting(String setting) {
		String tmp = getSetting(setting);
		if (tmp != null) {
			return Boolean.valueOf(tmp).booleanValue();
		} else {
			return false;
		}
	}

	/**
	 * Liefert den aktuellen Wert einer Einstellung. Wrapper-Methode.
	 * 
	 * @param setting
	 *            Den Key der Einstellung.
	 * @return Den Wert der Einstellung oder <code>false</code>, falls diese
	 *         nicht existiert.
	 */
	public Lage getStandort() {

		return standort;

	}

	/**
	 * Methode liefert den konfigurierten Pfad zu den Jasper-Vorlagen
	 */
	public String getTemplatepath() {

		return getSetting("auik.templatepath");

	}

	/**
	 * Entfernt eine Einstellung komplett.
	 * 
	 * @param setting
	 *            Den Key der Einstellung.
	 */
	public void removeSetting(String setting) {
		if (instanceSettings.containsKey(setting)) {
			instanceSettings.remove(setting);
		}

		if (appSettings.containsKey(setting)) {
			appSettings.remove(setting);
		}
	}
}
