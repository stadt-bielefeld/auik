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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_49_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class Anh49Fachdaten extends AbstractAnh49Fachdaten implements
    Serializable {
    private static final long serialVersionUID = 1996484061739446871L;

    /**
     * Simple constructor of Anh49Fachdaten instances.
     */
    public Anh49Fachdaten() {
    }

    @Override
    public String toString() {
        String tmp = "[Anh49:" + getBasisObjekt() + "]";
        return tmp;
    }

    // es werden alle Datensätze aus Anh49Fachdaten ausser Fettabscheidern
    // ausgewählt
    public static List<?> findAlle() {
        List<?> anhang49;
        String query = "from Anh49Fachdaten anh49 "
            + "where anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
            + "and anh49.basisObjekt.inaktiv = false "
            + "order by anh49.sachbearbeiterIn";
        anhang49 = new DatabaseAccess().createQuery(query).list();
        return anhang49;
    }

    // es werden alle Datensätze aus Anh49Fachdaten ausser Fettabscheidern
    // ausgewählt
    public static List<?> findInaktive() {
        List<?> anhang49;
        String query = "from Anh49Fachdaten anh49 "
            + "where anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
            + "and anh49.basisObjekt.inaktiv = true "
            + "order by anh49.sachbearbeiterIn";
        anhang49 = new DatabaseAccess().createQuery(query).list();
        return anhang49;
    }

    /**
     * Sucht Anhang49-Fachdatensätze nach bestimmten Kriterien.
     * @param sachbearbeiter Welche(r) SachbearbeiterIn (oder "")?
     * @param abgemeldet param abgerissen
     * @param abwasserfrei
     * @param dekratuev
     * @param nurWiedervorlageAbgelaufen Sollen nur Datensätze angezeigt werden,
     *            deren Wiedervorlage in der Vergangenheit liegt?
     * @return Eine Liste mit den entstprechenden Anh49Fachdaten.
     */
    public static List<?> findAuswertung(Boolean abgemeldet, String abgerissen,
        Boolean abwasserfrei, boolean nurWiedervorlageAbgelaufen) {
        List<?> anhang49;

        String abgr = abgerissen.toLowerCase().trim() + "%";

        String query = "from Anh49Fachdaten ah49 where "
            + "ah49.abgemeldet = :abgemeldet and "
            + "lower(ah49.sonstigestechnik) like :abgr and "
            + "ah49.abwasserfrei = :abwasserfrei "
            + "and ah49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' ";

        // TODO: Herausfinden, ob es schaden würde benannte Parameter zu setzen,
        // die gar nicht im QueryString vorkommen.
        if (nurWiedervorlageAbgelaufen) {
            query += "and ah49.wiedervorlage <= :today "
                + "and ah49.basisObjekt.inaktiv = false "
                + "order by ah49.sachbearbeiterIn, "
                + "ah49.basisObjekt.basisBetreiber.betrname ";
            anhang49 = new DatabaseAccess().createQuery(query)
                .setBoolean("abgemeldet", abgemeldet)
                .setString("abgr", abgr)
                .setBoolean("abwasserfrei", abwasserfrei)
                .setDate("today", new Date())
                .list();
        } else {
            query += "and ah49.basisObjekt.inaktiv = false "
                + "order by ah49.sachbearbeiterIn, "
                + "ah49.basisObjekt.basisBetreiber.betrname";
            anhang49 = new DatabaseAccess().createQuery(query)
                .setBoolean("abgemeldet", abgemeldet)
                .setString("abgr", abgr)
                .setBoolean("abwasserfrei", abwasserfrei)
                .list();
        }

        return anhang49;
    }

    public static Anh49Fachdaten getAnh49ByObjekt(BasisObjekt objekt) {
        Anh49Fachdaten fachdaten = null;
        if (objekt.getBasisObjektarten().isAnh49()) {
            List<?> anhang49 = new DatabaseAccess().createQuery(
                "from Anh49Fachdaten as ah49 where "
                    + "ah49.basisObjekt = :objekt")
                .setEntity("objekt", objekt)
                .list();

            if (anhang49.size() > 0) {
                fachdaten = (Anh49Fachdaten) anhang49.get(0);
            }
        }
        return fachdaten;
    }

    public static boolean saveFachdaten(Anh49Fachdaten fachdaten) {
        boolean saved = false;
        saved = new DatabaseAccess().saveOrUpdate(fachdaten);
        return saved;
    }

    public static List<?> findTuev(Integer tuev) {
        List<?> anhang49 = null;
        String query = "from Anh49Fachdaten anh49 "
            + "where anh49.dekraTuevTermin = :tuev "
            + "and anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
            + "and anh49.basisObjekt.inaktiv = false "
            + "order by anh49.sachbearbeiterIn";
        anhang49 = new DatabaseAccess().createQuery(query)
            .setInteger("tuev", tuev)
            .list();
        return anhang49;
    }

    public static List<?> findSachbearbeiter(String sachbearb) {
        List<?> anhang49;
        String query = "from Anh49Fachdaten anh49 "
            + "where anh49.sachbearbeiterIn = :sachbearb "
            + "and anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
            + "and anh49.basisObjekt.inaktiv = false";
        anhang49 = new DatabaseAccess().createQuery(query)
            .setString("sachbearb", sachbearb)
            .list();
        return anhang49;
    }

    public static String[] getSachbearbeiter() {
        List<?> list = null;
        String suchString = "SELECT DISTINCT fd.sachbearbeiterIn FROM de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten fd "
            + "where fd.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider'";
        list = new DatabaseAccess().createQuery(suchString)
            .setCacheable(true)
            .setCacheRegion("sachbearbeiter")
            .list();
        String[] result = new String[list.size()];
        result = (String[]) list.toArray(result);
        return result;
    }

    public Date getLetzteAnalyse(Anh49Fachdaten fd) {
        Date la = null;
        List<?> list = null;
        String suchstring = "select max(fd.anh49Analysen.datum) "
            + "from de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten fd "
            + "where fd = :fd";
        list = new DatabaseAccess().createQuery(suchstring)
            .setEntity("fd", fd)
            .list();
        Timestamp ta = (Timestamp) list.get(0);
        if (ta != null) {
            la = ta;
        }
        return la;
    }
}
