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

package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * Eine Klasse, die eine Zeile der 'BASIS_BETREIBER'-Tabelle repräsentiert.
 */
public class BasisBetreiber extends AbstractBasisBetreiber implements
    Serializable {
    private static final long serialVersionUID = 7154323084416443564L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Durchsucht die Tabelle nach dem Betreiber-Namen */
    public static final String PROPERTY_NAME = "name";
    /** Durchsucht die Tabelle nach der Betreiber-Anrede */
    public static final String PROPERTY_ANREDE = "anrede";
    /** Durchsucht die Tabelle nach dem Betreiber-Namenszusatz */
    public static final String PROPERTY_ZUSATZ = "zusatz";

    /**
     * Simple constructor of BasisBetreiber instances.
     */
    public BasisBetreiber() {
    }

    /**
     * Constructor of BasisBetreiber instances given a simple primary key.
     * @param betreiberid
     */
    public BasisBetreiber(java.lang.Integer betreiberid) {
        super(betreiberid);
    }

    /* Add customized code below */

    /**
     * Liefert einen String der Form "Name, Zusatz" falls ein Zusatz vorhanden
     * ist, sonst nur den Namen.
     */
    @Override
    public String toString() {
        String name = this.getBetrname();
        String zusatz = "";
        if (this.getBetrvorname() != null) {
            zusatz = ", " + this.getBetrvorname();
        } else if (this.getBetrnamezus() != null) {
            zusatz = ", " + this.getBetrnamezus();
        }
        return name + zusatz;
    }

    /**
     * Liefert einen Betreiber mit einer bestimmten ID.
     * @param id Die ID (der Primärschlüssel) des Betreibers.
     * @return Den gesuchten Betreiber oder <code>null</code>, falls kein
     *         Betreiber mit dieser ID existiert.
     */
    public static BasisBetreiber getBetreiber(Integer id) {
        BasisBetreiber betreiber;
        betreiber = (BasisBetreiber) new DatabaseAccess()
            .get(BasisBetreiber.class, id);
        return betreiber;
    }

    /**
     * Speichert einen Betreiber in die Datenbank, bzw. updatet einen schon
     * vorhandenen Betreiber mit neuen Werten.
     * @param betr Der Betreiber, der gespeichert werden soll.
     * @return Der gespeicherte Betreiber, oder <code>null</code>, falls beim
     *         Speichern ein Fehler auftrat.
     */
    public static BasisBetreiber saveBetreiber(BasisBetreiber betr) {
        BasisBetreiber betrRet = null;
        betrRet = (BasisBetreiber) new DatabaseAccess().merge(betr);
        if (betrRet != null) {
            log.debug("Neuer Betr " + betr + " (" + betr.getBetreiberid() + ")"
                + " gespeichert!");
        }
        return betrRet;
    }

    /**
     * Löscht einen vorhandenen Betreiber aus der Datenbank.
     * @param betreiber Der Betreiber, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Betreiber gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. der
     *         Betreiber nicht in der Datenbank existiert).
     */
    public static boolean removeBetreiber(BasisBetreiber betreiber) {
        return new DatabaseAccess().delete(betreiber);
    }

    public String getBetriebsgrundstueck() {
        String strasse = getStrasse();
        Integer hausnr = getHausnr();
        String hausnrZus = getHausnrzus();

        StringBuilder sb = new StringBuilder();

        if (strasse != null) {
            sb.append(strasse);
        }

        if (hausnr != null) {
            sb.append(" ");
            sb.append(hausnr.toString());
        }

        if (hausnr != null && hausnrZus != null) {
            sb.append(hausnrZus);
        }

        return sb.toString();
    }
}
