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
import java.util.List;

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
     * Durchsucht die Betreiber-Tabelle. Mit <code>property</code> wird
     * festgelegt, welche Eigenschaft (im Endeffekt also welche Tabellen-Spalte)
     * der Betreiber nach dem Suchwort durchsucht wird. Wenn
     * <code>property</code> <code>null</code> ist, werden alle drei möglichen
     * Spalten (Name, Anrede und Namens-Zusatz) durchsucht. Beim Suchwort wird
     * Groß-/Kleinschreibung ignoriert und automatisch ein '%' angehängt.
     * @param suche Wonach soll gesucht werden?
     * @param property PROPERTY_NAME, PROPERTY_ANREDE, PROPERTY_ZUSATZ oder
     *            <code>null</code> um in allen dreien zu suchen.
     * @return Eine Liste mit allen gefundenen Betreibern.
     */
    public static List<?> findBetreiber(String suche, String property) {
        String suche2 = suche.toLowerCase().trim() + "%";
        log.debug("Suche nach '" + suche2 + "' (" + property + ").");

        String queryString;
        if (PROPERTY_NAME.equals(property)) {
            queryString = "FROM BasisBetreiber as betr WHERE "
                + "lower(betr.betrname) like :suche "
                + "ORDER BY betr.betrname, betr.betrnamezus";
        } else if (PROPERTY_ANREDE.equals(property)) {
            queryString = "FROM BasisBetreiber as betr WHERE "
                + "lower(betr.betranrede) like :suche "
                + "ORDER BY betr.betrname, betr.betrnamezus";
        } else if (PROPERTY_ZUSATZ.equals(property)) {
            queryString = "FROM BasisBetreiber as betr WHERE "
                + "lower(betr.betrnamezus) like :suche "
                + "ORDER BY betr.betrname, betr.betrnamezus";
        } else {
            queryString = "FROM BasisBetreiber as betr WHERE "
                + "lower(betr.betrname) like :suche "
                + "or lower(betr.betranrede) like :suche "
                + "or lower(betr.betrnamezus) like :suche "
                + "ORDER BY betr.betrname, betr.betrnamezus";
        }

        return new DatabaseAccess().createQuery(queryString)
            .setString("suche", suche2)
            .list();
    }

    /**
     * Speichert einen Betreiber in die Datenbank, bzw. updatet einen schon
     * vorhandenen Betreiber mit neuen Werten.
     * @param betr Der Betreiber, der gespeichert werden soll.
     * @return Der gespeicherte Betreiber, oder <code>null</code>, falls beim
     *         Speichern ein Fehler auftrat.
     */
    public static boolean saveBetreiber(BasisBetreiber betr) {
        // TODO: Do we really need the returned object?
//      BasisBetreiber betrRet = null;
//      betrRet = (BasisBetreiber) session.merge(betr);
        boolean success = false;
        success = new DatabaseAccess().merge(betr);
        if (success) {
            log.debug("Neuer Betr " + betr + " gespeichert!");
        }
//        return betrRet;
        return success;
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

        StringBuilder sb = new StringBuilder(strasse);

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
