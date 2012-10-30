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
 * $Id: BasisObjektarten.java,v 1.5.2.1 2010-11-23 10:25:58 u633d Exp $
 *
 * Erstellt am 19.01.05 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2010/01/26 09:20:43  u633d
 * Fettabscheider-Analysen
 *
 * Revision 1.4  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.3  2008/09/01 07:00:29  u633d
 * Anlegen von Sielhautproben ueberabeitet
 *
 * Revision 1.2  2008/06/12 10:21:41  u633d
 * diverse Bugfixes
 *
 * Revision 1.1  2008/06/05 11:38:34  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.20.6.1  2006/07/26 06:46:01  u633d
 * Neuer Zweig
 *
 * Revision 1.20  2005/09/07 10:48:23  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.19  2005/08/31 06:22:56  u633d
 * - kleine Ergänzungen
 *
 * Revision 1.18  2005/08/25 14:47:00  u633d
 * - zu viel ;)
 *
 * Revision 1.17  2005/08/17 05:46:36  u633d
 * - Anhang 56 erstellt
 *
 * Revision 1.16  2005/07/05 08:11:09  u633d
 * Hochkomma in Mappings
 *
 * Revision 1.15  2005/06/30 11:39:16  u633z
 * Datenbank-Umstellung
 *
 * Revision 1.14  2005/06/13 15:30:36  u633z
 * - Anhang 53 IDs und Methoden hinzugefügt
 *
 * Revision 1.13  2005/06/09 13:38:22  u633z
 * - Sortierung wieder auf Namen (alphabetisch) geändert
 * - toString zeigt jetzt die Abteilung mit an
 * - Methoden zur Abfrage der Abteilung hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;

/**
 * A class that represents a row in the 'BasisSachbearbeiter' table. This class
 * may be customized as it is never re-generated after being created.
 */
public class BasisSachbearbeiter extends AbstractBasisSachbearbeiter implements
    Serializable {
    private static final long serialVersionUID = 7587341236085960792L;

    /**
     * Simple constructor of BasisSachbearbeiter instances.
     */
    public BasisSachbearbeiter() {
    }

    /**
     * Constructor of BasisObjektarten instances given a simple primary key.
     * @param kennummer
     */
    public BasisSachbearbeiter(java.lang.String kennummer) {
        super(kennummer);
    }

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this);
    }

    /* Add customized code below */

    public static BasisSachbearbeiter[] getSachbearbeiter() {
        return (BasisSachbearbeiter[])
            new DatabaseAccess().createQuery(
                "FROM BasisSachbearbeiter AS sachbearbeiter "
                    + "ORDER BY sachbearbeiter.enabled DESC, "
                    + "sachbearbeiter.name ASC")
            .array(new BasisSachbearbeiter[0]);
    }

    public static BasisSachbearbeiter getCurrentSachbearbeiter() {
        return (BasisSachbearbeiter)
            new DatabaseAccess().get(BasisSachbearbeiter.class,
                SettingsManager.getInstance().getSetting("auik.prefs.lastuser"));
    }

    public static BasisSachbearbeiter[] getEnabledSachbearbeiter() {
        return (BasisSachbearbeiter[])
            new DatabaseAccess().createQuery(
                "FROM BasisSachbearbeiter AS sachbearbeiter "
                    + "WHERE sachbearbeiter.enabled = true "
                    + "ORDER BY sachbearbeiter.name ASC")
            .array(new BasisSachbearbeiter[0]);
    }
}
