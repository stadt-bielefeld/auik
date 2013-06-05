package de.bielefeld.umweltamt.aui;

import java.awt.Frame;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Vector;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

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
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
public final class GUIManager {

    /* Constant values */
    /** Der kurze Name des Programms */
    public static final String SHORT_NAME = "AUI-Kataster";
    /** Der lange Name des Programms */
    public static final String LONG_NAME = "Anlagen- und Indirekteinleiter-Kataster";
    /** Program version */
    private static final String VERSION = null;
    /** Program authors */
    private static final Vector<String> AUTHORS = new Vector<String>();
    /** Program authors as a HTML table */
    private static final String AUTHORS_AS_HTML_TABLE = null;
    private static final String SPLASH_IMAGE = "gui/images/splash.png";

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

    /** End the application */
    public void endGUI() {
        this.runningFrame.close();
        log.debug("Ending everything here. Good bye!");
    }

    /** Show the splash frame */
    private void showSplashFrame() {
        this.splashFrame = SplashWindow.splash(Toolkit.getDefaultToolkit()
            .createImage(AUIKataster.class.getResource(SPLASH_IMAGE)));
    }

    /** Dispose the splash frame */
    private void disposeSplashFrame() {
        if (this.splashFrame != null) {
            this.splashFrame.dispose();
        }
    }

    /**
     * Set the status in the running frame
     * @param txt The new status text
     */
    public void setInfoStatus(String txt) {
        if (this.runningFrame != null) {
            this.runningFrame.changeStatus(txt);
        }
    }

    /**
     * Set the status in the running frame as error message
     * @param txt The error status text
     */
    public void setErrorStatus(String txt) {
        if (this.runningFrame != null) {
            this.runningFrame.changeStatus(txt, HauptFrame.ERROR_COLOR);
        }
    }

    /**
     * Show a question dialog
     * @param question The question to ask
     * @param title The title of the dialog
     * @return <code>boolean</code> The choosen answer
     */
    public boolean showQuestion(String question, String title) {
        return (JOptionPane.showConfirmDialog(this.runningFrame, question,
            title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    public boolean showQuestion(String question) {
        return (JOptionPane.showConfirmDialog(this.runningFrame, question,
            "Frage", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }

    /**
     * Show an info message
     * @param message The message
     * @param title The title of the message
     */
    public void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(this.runningFrame, message, title,
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show an error message
     * @param message The message
     * @param title The title of the message
     */
    public void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(this.runningFrame, message, title,
            JOptionPane.ERROR_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this.runningFrame, message, "Fehler",
            JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Diese Methode liefert die aktuelle Version der Software.
     * @return die Versionsnummer
     */
    public final String getVersion() {
        /* If we already have the version return it */
        if (GUIManager.VERSION != null) {
            return GUIManager.VERSION;
        }

        String version = "0.3";

        // Get an url to an arbitrary class
        String urlToClass = this
            .getClass()
            .getResource(
                "/" + this.getClass().getName().replaceAll("\\.", "/")
                    + ".class").toString();
        // Get the type of the url (file or jar)
        String type = urlToClass.substring(0, urlToClass.indexOf(":"));

        if (type.equals("file")) {
            log.debug("Could not get the version, because AUIK was not run "
                + "from the jar file.");
            version = "unknown";
        } else if (type.equals("jar")) {
            try {
                version = new JarFile(new File(
                    new URL(urlToClass.substring(urlToClass.indexOf(":") + 1,
                        urlToClass.indexOf("!"))).toURI())).getManifest()
                    .getMainAttributes().getValue("Implementation-Version");
            } catch (URISyntaxException use) {
                log.debug("This (URISyntaxException) should never happen.");
            } catch (IOException ioe) {
                log.debug("This (IOException) should never happen.");
            } catch (NullPointerException npe) {
                log.debug("This (NullPointerException) should never happen.");
            }
        } else { // neither file nor jar...
            log.debug("This should never happen.");
        }

        return version;
    }

    /**
     * Diese Methode liefert eine Liste der Autoren des AUIK.
     * @return die Autoren des AUIK als Array.
     */
    private final Vector<String> getAuthors() {
        /* If we already have the version return them */
        if (!GUIManager.AUTHORS.isEmpty()) {
            return GUIManager.AUTHORS;
        }

        InputStream is = null;
        BufferedReader in = null;

        try {
            is = AUIKataster.class
                .getResourceAsStream("/de/bielefeld/umweltamt/aui/resources/authors.txt");
            in = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while ((line = in.readLine()) != null) {
                GUIManager.AUTHORS.add(line);
            }
        } catch (FileNotFoundException fnfe) {
            log.debug("Could not find authors file: 'authors.txt'");
        } catch (NullPointerException npe) {
            log.debug("Could not find authors file: 'authors.txt'");
        } catch (IOException ioe) {
            log.debug("Error while reading authors file: 'authors.txt'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ioe) {
                    /* do nothing */
                }
            }
        }

        return GUIManager.AUTHORS;
    }

    /**
     * Diese Methode liefert die Liste der Autoren als HTML Tabelle formatiert.
     * @return die Autorenliste als HTML Tabelle.
     */
    /* For some reason this does not work with line separators. */
    public final String getAuthorsAsHTML() {
        /* If we already have the authors as HTML table return it */
        if (GUIManager.AUTHORS_AS_HTML_TABLE != null) {
            return GUIManager.AUTHORS_AS_HTML_TABLE;
        }

        Vector<String> authors = this.getAuthors();

        StringBuilder sb = new StringBuilder();
        sb.append("<table>");

        for (String author : authors) {
            sb.append("<tr><td>");
            sb.append(author);
            sb.append("</td></tr>");
        }

        sb.append("</table>");

        return sb.toString();
    }

	public final HauptFrame getRunningFrame() {
		// TODO Auto-generated method stub
		return this.runningFrame;
	}
}
