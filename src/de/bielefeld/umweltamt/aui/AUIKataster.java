/*
 * Datei:
 * $Id: AUIKataster.java,v 1.2 2008-06-24 11:24:07 u633d Exp $
 * 
 * Erstellt am 07.01.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.21  2005/11/02 13:45:44  u633d
 * - Version vom 2.11.
 *
 * Revision 1.20  2005/06/14 09:22:53  u633d
 * - Neues 0.2-Jar gebaut
 *
 * Revision 1.19  2005/06/14 06:33:37  u633d
 * Version 0.2
 *
 * Revision 1.18  2005/05/25 15:41:49  u633z
 * - Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui;

import javax.swing.JOptionPane;

/**
 * Das Anlagen- und Indirekteinleiter-Kataster.
 * @author David Klotz
 */
public class AUIKataster {
	/** Der kurze Name des Programms */
	public static final String SHORT_NAME= "AUI-Kataster";
	/** Der lange Name des Programms */
	public static final String LONG_NAME= "Anlagen- und Indirekteinleiter-Kataster";
	/** Die Version des Programms */
	public static final String VERSION = "0.3";
	/** Debug-Ausgaben */
	public static final boolean DEBUG = true;
	
	private static HauptFrame runningFrame = null;
	
	/**
	 * Gibt Debug-Ausgaben/Fehler auf der Konsole aus.
	 * @param msg Die auszugebende Nachricht
	 */
	public static void debugOutput(String msg) {
		debugOutput(msg, "Unknown");
	}
	
	/**
	 * Gibt Debug-Ausgaben/Fehler auf der Konsole aus.
	 * @param msg Die auszugebende Nachricht
	 * @param src Wo trat der Fehler auf
	 */
	public static void debugOutput(String msg, String src) {
		if (DEBUG) {
			System.out.println("DEBUG (" + src + "): " + msg);
		}
	}
	
	/**
	 * Wird benutzt um mit im laufenden Betrieb auftretenden 
	 * Datenbank-Fehlern umzugehen.
	 * @param e Die aufgetretene Exception
	 * @param src Wo trat der Fehler auf
	 * @param fatal Soll das Programm beendet werden?
	 */
	public static void handleDBException(Throwable e, String src, boolean fatal) {
		String errMsg = "%%%% ";
		errMsg += fatal ? "Fataler " : "";
		errMsg += "DB-Fehler: " + e.getMessage() + " %%%%";
		//debugOutput(errMsg, src);
		//e.printStackTrace();
		if (runningFrame != null) {
			runningFrame.changeStatus("Ein Datenbank-Fehler ist aufgetreten!", HauptFrame.ERROR_COLOR);
			if (fatal) {
				JOptionPane.showMessageDialog(runningFrame, "Es ist keine Verbindung mit der Datenbank möglich!", "Fehler", JOptionPane.ERROR_MESSAGE);
				runningFrame.close();
			}
		} else if (fatal) {
			JOptionPane.showMessageDialog(null, "Es ist keine Verbindung mit der Datenbank möglich!", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		throw new RuntimeException(errMsg + " ("+src+")", e);
	}
	
	/**
	 * Die Hauptmethode des AUI-Katasters.
	 * @param args Kommandozeilenargumente
	 */
	public static void main(String[] args) {
		//long time = System.currentTimeMillis();
		SettingsManager settings = SettingsManager.getInstance();
		runningFrame = new HauptFrame(settings);
		//frame.show();
		//debugOutput((System.currentTimeMillis() - time) + " ms", "ZeitDif");
	}
}