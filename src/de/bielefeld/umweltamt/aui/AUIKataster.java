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
 * $Id: AUIKataster.java,v 1.3 2009-03-24 12:35:19 u633d Exp $
 *
 * Erstellt am 07.01.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2008/06/24 11:24:07  u633d
 * Version 0.3
 *
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Das Anlagen- und Indirekteinleiter-Kataster. Diese Klasse stellt den
 * eigentlichen Einstiegspunkt der Anwendung dar. Die Methode {@link main}
 * sollte dazu verwendet werden, das Programm zu starten.
 *
 * @author David Klotz
 */
public class AUIKataster {
    /** Der kurze Name des Programms */
    public static final String SHORT_NAME= "AUI-Kataster";
    /** Der lange Name des Programms */
    public static final String LONG_NAME= "Anlagen- und Indirekteinleiter-Kataster";

    private static HauptFrame runningFrame = null;

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
     * Die Hauptmethode des AUI-Katasters. Hier wird der {@link SettingsManager}
     * sowie das {@link HauptFrame} instanziert. Das {@link HauptFrame} bekommt
     * die Instanz des {@link SettingsManager} als Konstruktor &uuml;bergeben.
     * @param args Kommandozeilenargumente
     */
    public static void main(String[] args) {
        //long time = System.currentTimeMillis();
        SettingsManager settings = SettingsManager.getInstance();
        runningFrame = new HauptFrame(settings);
        //frame.show();
        //debugOutput((System.currentTimeMillis() - time) + " ms", "ZeitDif");
    }


    /**
     * Diese Methode liefert die aktuelle Version der Software.
     *
     * @return die Versionsnummer
     */
    public static final String getVersion() {
        InputStream    is      = null;
        BufferedReader in      = null;
        String         version = null;

        try {
            is      = AUIKataster.class.getResourceAsStream("/de/bielefeld/umweltamt/aui/resources/version.txt");
            in      = new BufferedReader(new InputStreamReader(is));
            version = in.readLine();
        }
        catch (FileNotFoundException fnfe) {
            System.err.println("Could not find version file: 'version.txt'");
        }
        catch (NullPointerException npe) {
            System.err.println("Could not find version file: 'version.txt'");
        }
        catch (IOException ioe) {
            System.err.println("Error while reading version file: 'version.txt'");
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException ioe) { /* do nothing */ }
            }
        }

        return version;
    }


    /**
     * Diese Methode liefert die aktuelle Revision der Software aus dem SCM.
     */
    public static final String getRevision() {
        InputStream    is  = null;
        BufferedReader in  = null;
        String         rev = null;

        try {
            is = AUIKataster.class.getResourceAsStream(
                "/de/bielefeld/umweltamt/aui/resources/revision.txt");
            in  = new BufferedReader(new InputStreamReader(is));
            rev = in.readLine();
        }
        catch (FileNotFoundException fnfe) {
            System.err.println("Could not find revision file: 'revision.txt'");
        }
        catch (NullPointerException npe) {
            System.err.println("Could not find revision file: 'revision.txt'");
        }
        catch (IOException ioe) {
            System.err.println("Error while reading revision file: 'revision.txt'");
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException ioe) { /* do nothing */ }
            }
        }

        return rev;
    }


    /**
     * Diese Methode liefert eine Liste der Autoren des AUIK.
     *
     * @return die Autoren des AUIK als Array.
     */
    private static final String[] getAuthors() {
        ArrayList<String> authors = new ArrayList<String>();
        InputStream    is = null;
        BufferedReader in = null;

        try {
            is = AUIKataster.class.getResourceAsStream(
                "/de/bielefeld/umweltamt/aui/resources/authors.txt");
            in = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while ((line = in.readLine()) != null) {
                authors.add(line);
            }
        }
        catch (FileNotFoundException fnfe) {
            System.err.println("Could not find authors file: 'authors.txt'");
        }
        catch (NullPointerException npe) {
            System.err.println("Could not find authors file: 'authors.txt'");
        }
        catch (IOException ioe) {
            System.err.println("Error while reading authors file: 'authors.txt'");
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException ioe) { /* do nothing */ }
            }
        }

        return (String[]) authors.toArray(new String[authors.size()]);
    }


    /**
     * Diese Methode liefert die Liste der Autoren als HTML Tabelle formatiert.
     *
     * @return die Autorenliste als HTML Tabelle.
     */
    public static final String getAuthorsAsHTML() {
        String[] authors = getAuthors();

        StringBuilder sb = new StringBuilder();
        sb.append("<table>");

        for (String author: authors) {
            sb.append("<tr><td>");
            sb.append(author);
            sb.append("</td></tr>");
        }

        sb.append("</table>");

        return sb.toString();
    }
}
