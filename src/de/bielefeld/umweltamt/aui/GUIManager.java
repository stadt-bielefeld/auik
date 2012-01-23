package de.bielefeld.umweltamt.aui;

import java.awt.Frame;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

import de.bielefeld.umweltamt.aui.gui.SplashWindow;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

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

/**
 * The GUIManager handles all the stuff related to the gui.<br>
 * It is a Singleton.<br>
 * <br>
 * TODO: Most of the stuff which should be here is still scattered across the
 * project and should move here bit by bit.
 *  
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public final class GUIManager {

	/* Constant values */ 
    /** Der kurze Name des Programms */
    public static final String SHORT_NAME = "AUI-Kataster";
    /** Der lange Name des Programms */
    public static final String LONG_NAME = "Anlagen- und Indirekteinleiter-Kataster";
    /** Program version */
    private static final String VERSION = null; // = "v1.1 RC1";
    /** Program revision */
    private static final String REVISION = null; // = "rev162";
    /** Program authors */
    private static final Vector<String> AUTHORS = new Vector<String>();
    /** Program authors as a HTML table */
    private static final String AUTHORS_AS_HTML_TABLE = null;

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /* Manager instance */
	/** Singleton instance of the DatabaseManager */
	private static GUIManager instance = null;
	/** Setting Manager */
    private static SettingsManager settingsManager = null;

	/* Other GUI related stuff*/
    /** Das Hauptfenster bzw. aktive Fenster */
    private HauptFrame runningFrame = null;
    /** The splash frame */
    private Frame splashFrame = null;

	/** Private Constructor */
	private GUIManager() {
		// This is intentionally left blank.
	}
	
	/** Get the GUIManager instance */
	public synchronized static GUIManager getInstance() {
		if (instance == null) {
			instance = new GUIManager();
		}
		return instance;
	}
	
	/** 
	 * Initialize and start everything<br> 
	 * Hier wird der {@link SettingsManager} sowie das {@link HauptFrame}
	 * instanziert. Das {@link HauptFrame} bekommt die Instanz des
	 * {@link SettingsManager} als Konstruktor &uuml;bergeben.
	 */
	public void startGUI() {
		/* Show the splash frame */
		this.showSplashFrame();
		/* Initialize the settings */
		GUIManager.settingsManager = SettingsManager.getInstance();
		/* Create the main frame */
        this.runningFrame = new HauptFrame(GUIManager.settingsManager);
		/* Dispose the splash frame */
		this.disposeSplashFrame();
	}

	/** Getter */
	public HauptFrame getRunningFrame() {
		return this.runningFrame;
	}
	
	/** Show the splash frame */
	private void showSplashFrame() {
        URL imageURL = GUIManager.class.getResource(
        		"/de/bielefeld/umweltamt/aui/resources/splash.png");
        if (imageURL != null) {
            splashFrame = SplashWindow.splash(
                Toolkit.getDefaultToolkit().createImage(imageURL));
        } else {
            log.warn("The splash image was not found.");
        }
	}
	
	/** Dispose the splash frame */
	private void disposeSplashFrame() {
        if (this.splashFrame != null) {
        	this.splashFrame.dispose();
        }
	}
	
    /**
     * Diese Methode liefert die aktuelle Version der Software.
     *
     * @return die Versionsnummer
     */
    public final String getVersion() {
    	/* If we already have the version return it */
    	if (GUIManager.VERSION != null) {
    		return GUIManager.VERSION;
    	}
    	
        InputStream    is      = null;
        BufferedReader in      = null;
        String         version = null;

        try {
            is      = AUIKataster.class.getResourceAsStream("/de/bielefeld/umweltamt/aui/resources/version.txt");
            in      = new BufferedReader(new InputStreamReader(is));
            version = in.readLine();
        }
        catch (FileNotFoundException fnfe) {
            log.error("Could not find version file: 'version.txt'");
        }
        catch (NullPointerException npe) {
            log.error("Could not find version file: 'version.txt'");
        }
        catch (IOException ioe) {
            log.error("Error while reading version file: 'version.txt'");
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
    public final String getRevision() {
    	/* If we already have the revision return it */
    	if (GUIManager.REVISION != null) {
    		return GUIManager.REVISION;
    	}

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
            log.error("Could not find revision file: 'revision.txt'");
        }
        catch (NullPointerException npe) {
            log.error("Could not find revision file: 'revision.txt'");
        }
        catch (IOException ioe) {
            log.error("Error while reading revision file: 'revision.txt'");
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
    private final Vector<String> getAuthors() {
    	/* If we already have the version return them */
    	if (!GUIManager.AUTHORS.isEmpty()) {
    		return GUIManager.AUTHORS;
    	}

        InputStream    is = null;
        BufferedReader in = null;

        try {
            is = AUIKataster.class.getResourceAsStream(
                "/de/bielefeld/umweltamt/aui/resources/authors.txt");
            in = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while ((line = in.readLine()) != null) {
            	GUIManager.AUTHORS.add(line);
            }
        }
        catch (FileNotFoundException fnfe) {
            log.debug("Could not find authors file: 'authors.txt'");
        }
        catch (NullPointerException npe) {
            log.debug("Could not find authors file: 'authors.txt'");
        }
        catch (IOException ioe) {
            log.debug("Error while reading authors file: 'authors.txt'");
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException ioe) {
                	/* do nothing */
            	}
            }
        }

		return GUIManager.AUTHORS;
    }

    /**
     * Diese Methode liefert die Liste der Autoren als HTML Tabelle formatiert.
     *
     * @return die Autorenliste als HTML Tabelle.
     */
    public final String getAuthorsAsHTML() {
    	/* If we already have the authors as HTML table return it */
    	if (GUIManager.AUTHORS_AS_HTML_TABLE != null) {
    		return GUIManager.AUTHORS_AS_HTML_TABLE;
    	}

    	Vector<String> authors = this.getAuthors();

        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");

        for (String author: authors) {
            sb.append("  <tr>\n    <td>\n      ");
            sb.append(author);
            sb.append("\n    </td>\n  </tr>\n");
        }

        sb.append("</table>");

        return sb.toString();
    }
}
