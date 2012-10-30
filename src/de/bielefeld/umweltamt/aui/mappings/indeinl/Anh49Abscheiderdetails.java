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
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the 'ANH_49_ABSCHEIDERDETAILS' table. This
 * class may be customized as it is never re-generated after being created.
 */
public class Anh49Abscheiderdetails extends AbstractAnh49Abscheiderdetails
    implements Serializable {
    private static final long serialVersionUID = -7129534671978623498L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of Anh49Abscheiderdetails instances.
     */
    public Anh49Abscheiderdetails() {
        // Die Bool-Werte sind standardmäßig false
        setTankstelle(false);
        setSchlammfang(false);
        setBenzinOelabscheider(false);
        setKoaleszenzfilter(false);
        setIntegriert(false);
        setEmulsionsspaltanlage(false);
        setSchwimmer(false);
        setWohnhaus(false);
    }

    /**
     * Constructor of Anh49Abscheiderdetails instances given a simple primary
     * key.
     * @param abscheiderid
     */
    public Anh49Abscheiderdetails(java.lang.Integer abscheiderid) {
        super(abscheiderid);
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

    /**
     * Liefert alle Abscheiderdetails eines bestimmten Fachdatenobjekts.
     */
    public static List<?> getAbscheiderDetails(Anh49Fachdaten fd) {
        List<?> details;
        details = new DatabaseAccess()
            .createQuery(
                "FROM Anh49Abscheiderdetails as details "
                    + "WHERE details.Anh49Fachdaten = :fd "
                    + "ORDER BY details.abscheidernr asc")
            .setEntity("fd", fd)
            .list();

        log.debug("Details für " + fd + ", Anzahl: " + details.size());
        return details;
    }

    public static List<?> getFettabschListe() {
        // Liste für Fettabscheider aus Anh49Abscheiderdetails
        String query = "FROM Anh49Abscheiderdetails details "
            + "WHERE details.Anh49Fachdaten.basisObjekt.basisObjektarten.objektart like 'Fettabscheider' "
            + "ORDER BY details.Anh49Fachdaten.basisObjekt.inaktiv, "
            + "details.Anh49Fachdaten.basisObjekt.basisBetreiber.betrname";
        return new DatabaseAccess().createQuery(query).list();
    }

    public static boolean saveAbscheider(Anh49Abscheiderdetails absch) {
        return new DatabaseAccess().saveOrUpdate(absch);
    }

    public static boolean removeAbscheider(Anh49Abscheiderdetails abscheider) {
        return new DatabaseAccess().delete(abscheider);
    }

    public BasisObjekt getBasisObjekt() {
        Anh49Fachdaten fd = getAnh49Fachdaten();

        return fd != null ? fd.getBasisObjekt() : null;
    }
}
