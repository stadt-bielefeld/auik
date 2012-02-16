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

package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'VAWS_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class VawsFachdaten extends AbstractVawsFachdaten implements
    Serializable {
    private static final long serialVersionUID = -1602704238970146965L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of VawsFachdaten instances.
     */
    public VawsFachdaten() {
    }

    /**
     * Constructor of VawsFachdaten instances given a simple primary key.
     * @param behaelterId
     */
    public VawsFachdaten(Integer behaelterId) {
        super(behaelterId);
    }

    /**
     * Liefert einen String der Form "BehaelterID: Anlagenart Herstellnr".
     */
    @Override
    public String toString() {
        String tmp = getBehaelterId() + ": ";
        // = "[Vaws:" + getAnlagenart() + "," + getBasisObjekt() + "]";
        if (getAnlagenart() != null) {
            tmp += getAnlagenart() + " ";
        }
        if (getHerstellnr() != null) {
            tmp += getHerstellnr();
        }
        return tmp;
    }

    public String getStillegungsDatumString() {
        if (getStillegungsdatum() != null) {
            DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
            return f.format(getStillegungsdatum());
        } else {
            return null;
        }
//        return AuikUtils.getStringFromDate(getStillegungsdatum());
    }

    // Anlagenart:

    public boolean isAbfuellflaeche() {
        return ("Abfüllfläche".equals(getAnlagenart()));
    }

    public boolean isVAWSAbscheider() {
        return ("VAwS-Abscheider".equals(getAnlagenart()));
    }

    public boolean isLageranlage() {
        return (!(isAbfuellflaeche() || isRohrleitung()));
    }

    public boolean isRohrleitung() {
        return ("Rohrleitung".equals(getAnlagenart()));
    }

    /**
     * Liefert alle VAWS-Fachdatensätze zu einem bestimmten BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit VawsFachdaten.
     */
    public static List<?> getVawsByObjekt(BasisObjekt objekt)
        throws IllegalArgumentException {

        if (!objekt.getBasisObjektarten().isAbteilung34()) {
            throw new IllegalArgumentException(
            "Zu diesem BasisObjekt existieren keine VAWS-Fachdaten-Objekte!");
        }

        List<?> vaws;
        vaws = new DatabaseAccess()
            .createQuery(
                "FROM VawsFachdaten as v "
                    + "WHERE v.basisObjekt = :objekt "
                    + "ORDER BY v.stillegungsdatum desc, v.anlagenart asc, "
                    + "v.herstellnr asc")
            .setEntity("objekt", objekt)
            .list();

        log.debug(vaws.size() + " VawsFachdatensätze für BO " + objekt
            + " gefunden!");

        return vaws;
    }

    /**
     * Liefert alle VAWS-Fachdatensätze zu einem bestimmten BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit VawsFachdaten.
     */
    public static VawsFachdaten getVawsByBehaelterId(Integer id) {
        VawsFachdaten fachdaten = null;
        fachdaten = (VawsFachdaten) new DatabaseAccess()
            .get(VawsFachdaten.class, id);
        return fachdaten;
    }

    /**
     * Speichert einen VAWS-Fachdatensatz in der Datenbank.
     * @param fachdaten Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt,
     *         sonst <code>false</code>.
     */
    public static boolean saveFachdaten(VawsFachdaten fachdaten) {
        return new DatabaseAccess().saveOrUpdate(fachdaten);
    }

    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param fachdaten Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. der
     *         Datensatz nicht in der Datenbank existiert).
     */
    public static boolean removeFachdaten(VawsFachdaten fachdaten) {
        return new DatabaseAccess().delete(fachdaten);
    }

    /**
     * Liefert alle VAWS-Ausführungen. <br>
     * <b>ACHTUNG:</b> Liefert nicht alle VawsFachdaten, sondern alle in der
     * Spalte "AUSFUEHRUNG" benutzten Werte!
     * @return Ein Array mit den Namen aller Ausführungen.
     */
    public static String[] getAusfuehrungen() {
        // FIXME: SELECT distinct nicht die beste Lösung
        return (String[]) new DatabaseAccess()
            .createQuery(
                "SELECT distinct fd.ausfuehrung "
                + "FROM VawsFachdaten fd "
                + "ORDER BY fd.ausfuehrung")
            .setCacheable(true)
            .setCacheRegion("vawsausfliste")
            .array(new String[0]);
    }

    /* Durchsucht VawsFachdaten nach einer bestimmten Herstellnummer und gibt
     * das Ergebnis als List zurück */
    public static List<?> findherstellnr(String herstellnr) {
        return new DatabaseAccess()
            .createQuery(
                "FROM VawsFachdaten vaws "
                + "WHERE vaws.herstellnr like :herstellnr")
            .setString("herstellnr", herstellnr)
            .list();
    }
}
