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
 * Created Thu Jan 13 16:52:31 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * Eine Klasse, die eine Zeile der 'BASIS_STANDORT'-Tabelle repräsentiert.
 */
public class BasisStandort extends AbstractBasisStandort implements
    Serializable {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    private static final long serialVersionUID = 2774552431508434460L;

    /**
     * Simple constructor of BasisStandort instances.
     */
    public BasisStandort() {
        // This is intentionally left blank.
    }

    /**
     * Constructor of BasisStandort instances given a simple primary key.
     * @param standortid
     */
    public BasisStandort(java.lang.Integer standortid) {
        super(standortid);
    }

    /* Add customized code below */

    /**
     * TODO: Find all calls of the toString method and switch them
     * @see {@link this.getFormatierteStrasse} for the original method.
     */
    @Override
    public String toString() {
        return this.getFormatierteStrasse();
    }

    /**
     * Liefert die komplette Strasse, wenn vorhanden inklusive der Hausnummer
     * und deren Zusatz.<br>
     * <br>
     * Formatierung: &quot;&lt;Strasse&gt; &lt;HausNr&gt;&lt;HausNrzus&gt;&quot;<br>
     * <br>
     * Beispiele: &quot;Ravensberger Straße 77&quot;, &quot;Apfelstraße
     * 23b&quot;, &quot;Jahnplatz 41-42&quot;
     * @return Komplette, formatierte Strasse inkl. Hausnr
     */
    public String getFormatierteStrasse() {
        String formatierteStrasse;
        formatierteStrasse = this.getStrasse();
        if (this.getHausnr() != null) {
            formatierteStrasse += (" " + this.getHausnr());
        }
        if (this.getHausnrzus() != null) {
            formatierteStrasse += this.getHausnrzus();
        }
        return formatierteStrasse;
    }

    /**
     * Liefert einen Standort mit einer bestimmten ID.
     * @param id Die ID (der Primärschlüssel) des Standorts.
     * @return Den gesuchten Standort oder <code>null</code>, falls kein
     *         Standort mit dieser ID existiert.
     */
    public static BasisStandort getStandort(Integer id) {
        BasisStandort standort = null;
        standort = (BasisStandort) new DatabaseAccess()
            .get(BasisStandort.class, id);
        return standort;
    }

    /**
     * Liefert einen Standort mit einer bestimmten ID.
     * @param id Die ID (der Primärschlüssel) des Standorts.
     * @return Den gesuchten Standort oder <code>null</code>, falls kein
     *         Standort mit dieser ID existiert.
     */
    public static List<?> getStandortList(Integer id) {
        return new DatabaseAccess()
            .createQuery(
                "FROM BasisStandort as bsta WHERE "
                    + "bsta.standortid = :id ")
            .setInteger("id", id)
            .list();
    }

    /**
     * Durchsucht die Standort-Tabelle nach Straße und Hausnummer.. Bei der
     * Straße wird Groß-/Kleinschreibung ignoriert und automatisch ein '%'
     * angehängt. Wenn die Hausnummer -1 ist, wird nur nach der Straße gesucht.
     * @param strasse Der Straßenname (oder sein Anfang).
     * @param hausnr Die Hausnummer (oder -1, falls nicht nach einer bestimmten
     *            Hausnummer gesucht werden soll)
     * @return Eine Liste mit allen gefundenen Standorten.
     */
    public static List<?> findStandorte(String strasse, int hausnr) {
        String strasse2 = strasse.toLowerCase().trim() + "%";
        Integer hausNummer = new Integer(hausnr);

        log.debug("Suche nach '" + strasse2 + "' Nr. " + hausnr);
        List<?> standorte = null;

        String query = "FROM BasisStandort as bsta WHERE "
            + "lower(bsta.strasse) like :strasse ";
        if (hausnr != -1) {
            query += "and bsta.hausnr = :hausnr ";
        }
        query += "ORDER BY bsta.strasse, bsta.hausnr";

        // TODO: Test if it leads to errors if we set a named variable which is
        // not there
        if (hausnr != -1) {
            standorte = new DatabaseAccess()
                .createQuery(query)
                .setString("strasse", strasse2)
                .setInteger("hausnr", hausNummer)
                .list();
        } else {
            standorte = new DatabaseAccess()
                .createQuery(query)
                .setString("strasse", strasse2)
                .list();
        }

        return standorte;
    }

    /**
     * Speichert einen Standort in die Datenbank, bzw. updatet einen schon
     * vorhandenen Standort mit neuen Werten.
     * @param bsta Der Standort, der gespeichert werden soll.
     * @return Der gespeicherte Standort, oder <code>null</code>, falls beim
     *         Speichern ein Fehler auftrat.
     */
    public static BasisStandort saveStandort(BasisStandort bsta) {
        BasisStandort bstaRet = null;
        bstaRet = (BasisStandort) new DatabaseAccess().merge(bsta);
        if (bstaRet != null) {
            log.debug("Neuer Standort " + bstaRet + " gespeichert!");
        }
        return bstaRet;
    }

    /**
     * Liefert alle in der Tabelle benutzten Entwässerungsgebiete als Strings. <br>
     * <b>ACHTUNG:</b> Diese Methode liefert <b>nicht</b> alle Standorte,
     * sondern alle in der Spalte ENTWGEB benutzten Werte!
     * @return Alle zur Zeit benutzten Entwässerungsgebiete
     */
    public static String[] getEntwGebiete() {
        return (String[]) new DatabaseAccess().createQuery(
            "SELECT DISTINCT sta.entgebid "
                + "FROM de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort sta")
            .setCacheable(true)
            .setCacheRegion("ezgbliste")
            .array(new String[0]);
    }

    /**
     * Löscht einen vorhandenen Standort aus der Datenbank.
     * @param standort Der Standort, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Betreiber gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. der
     *         Standort nicht in der Datenbank existiert).
     */
    public static boolean removeStandort(BasisStandort standort) {
        boolean removed = false;
        removed = new DatabaseAccess().delete(standort);
        return removed;
    }

    public String getAdresse() {
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
