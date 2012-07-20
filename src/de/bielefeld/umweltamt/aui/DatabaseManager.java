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

package de.bielefeld.umweltamt.aui;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * The DatabaseManager handles all the stuff related to the database.<br>
 * It is a Singleton.<br>
 * <br>
 * TODO: Most of the stuff which should be here is still scattered across the
 * project and should move here bit by bit.
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public final class DatabaseManager {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Singleton instance of the DatabaseManager */
    private static DatabaseManager instance = null;

    /** Private Constructor */
    private DatabaseManager() {
        // This is intentionally left blank.
    }

    /** Get the DatabaseManager instance */
    public synchronized static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
            log.debug("Instanciated the DatabaseManager.");
        }
        return instance;
    }

    /**
     * Wird benutzt um mit im laufenden Betrieb auftretenden Datenbank-Fehlern
     * umzugehen.
     * @param exception Die aufgetretene Exception
     * @param src Wo trat der Fehler auf
     * @param fatal Soll das Programm beendet werden?
     */
    public void handleDBException(Throwable exception, String src,
    		boolean fatal) {
        // Close the session as its state may be invalid after an error
        HibernateSessionFactory.closeSession();
        // If we already have a GUI, show the error there.
        GUIManager.getInstance().setErrorStatus(
            "Ein Datenbank-Fehler ist aufgetreten!");
        // If the error is fatal, also show a message dialog and close the gui
        if (fatal) {
            GUIManager.getInstance().showErrorMessage(
                "Es ist keine Verbindung mit der Datenbank möglich!",
                "DB-Fehler");
            GUIManager.getInstance().endGUI();
        }
        // Throw an exception with more information.
        throw new RuntimeException("%%%% " + (fatal ? "Fataler " : "")
        		+ "DB-Fehler: " + exception.getMessage() + " %%%%"
        		+ " (" + src + ")", exception);
    }
}
