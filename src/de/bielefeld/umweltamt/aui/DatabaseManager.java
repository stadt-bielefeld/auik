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

import javax.swing.JOptionPane;

/**
 * The DatabaseManager handles all the stuff related to the database.<br>
 * It is a Singleton.<br>
 * <br>
 * TODO: Most of the stuff which should be here is still scattered across the
 * project and should move here bit by bit.
 *  
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public final class DatabaseManager {
	
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
		}
		return instance;
	}
	
    /**
     * Wird benutzt um mit im laufenden Betrieb auftretenden
     * Datenbank-Fehlern umzugehen.
     * 
     * @param exception Die aufgetretene Exception
     * @param src Wo trat der Fehler auf
     * @param fatal Soll das Programm beendet werden?
     */
    public void handleDBException(Throwable exception, String src,
    		boolean fatal) {
    	HauptFrame runningFrame = GUIManager.getInstance().getRunningFrame();
        // If we already have a gui, show the error there.
        if (runningFrame != null) {
            runningFrame.changeStatus("Ein Datenbank-Fehler ist aufgetreten!",
            		HauptFrame.ERROR_COLOR);
        }
        // If the error is fatal, also show a message dialog.
        if (fatal) {
            JOptionPane.showMessageDialog(runningFrame,
            		"Es ist keine Verbindung mit der Datenbank möglich!",
            		"Fehler", JOptionPane.ERROR_MESSAGE);
        }
        // If we have a gui and the error is fatal, close the gui.
        if (runningFrame != null && fatal) {
            runningFrame.close();
        }
        // Throw an exception with more information.
        throw new RuntimeException("%%%% " + (fatal ? "Fataler " : "") 
        		+ "DB-Fehler: " + exception.getMessage() + " %%%%"
        		+ " (" + src + ")", exception);
    }
}
