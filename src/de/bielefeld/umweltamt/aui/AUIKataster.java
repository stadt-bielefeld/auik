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

import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Das Anlagen- und Indirekteinleiter-Kataster. Diese Klasse stellt den
 * eigentlichen Einstiegspunkt der Anwendung dar. Die Methode {@link main}
 * sollte dazu verwendet werden, das Programm zu starten.
 *
 * @author David Klotz
 */
public class AUIKataster {

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Die Hauptmethode des AUI-Katasters.
     * @param args Kommandozeilenargumente
     */
    public static void main(String[] args) {
    	log.debug("Starting everything here.");

    	/* Command line arguments */
    	int i = 0;
    	for (String arg:args) {
    		log.debug("args[" + i++ + "]: " + arg);
    	}

    	/* Start the GUI */
    	GUIManager.getInstance().startGUI();

		return;
    }
}
