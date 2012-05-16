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

package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ATL_KLAERANLAGEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class AtlKlaeranlagen extends AbstractAtlKlaeranlagen implements
        Serializable {
    private static final long serialVersionUID = 4476838822557197680L;

    /*
     * Warum haben wir überhaupt die Tabelle. wenn hier eh alle Werte stehen?
     */

    /** Die ID der KA Brake */
    final public static Integer BRAKE = new Integer(4);
    /** Die ID der KA Heepen */
    final public static Integer HEEPEN = new Integer(1);
    /** Die ID der KA Sennestadt */
    final public static Integer SENNESTADT = new Integer(8);
    /** Die ID der KA O.Lutter */
    final public static Integer OBERE_LUTTER = new Integer(5);
    /** Die ID der KA Verl-Sende */
    final public static Integer VERL_SENDE = new Integer(9);

    /** Simple constructor of AtlKlaeranlagen instances. */
    public AtlKlaeranlagen() {
    }

    /**
     * Constructor of AtlKlaeranlagen instances given a simple primary key.
     * @param kaId
     */
    public AtlKlaeranlagen(java.lang.Integer kaId) {
        super(kaId);
    }

    /* Add customized code below */

    /**
     * Liefert den Namen der Kläranlage.
     */
    @Override
    public String toString() {
        return getAnlage();
    }

    /**
     * Liefert eine bestimmte Kläranlage.
     * @param id Die Id der Kläranlage
     * @return Die Kläranlage
     */
    public static AtlKlaeranlagen getKlaeranlage(Integer id) {
        AtlKlaeranlagen ka = null;

        ka = (AtlKlaeranlagen) new DatabaseAccess()
                .get(AtlKlaeranlagen.class, id);

        return ka;
    }

    /**
     * Liefert alle vorhandenen Kläranlagen.
     * @return Alle vorhandenen Kläranlagen
     */
    public static AtlKlaeranlagen[] getKlaeranlagen() {
        return (AtlKlaeranlagen[]) new DatabaseAccess().createQuery(
            "FROM AtlKlaeranlagen ka " +
            "WHERE ka.kaId!=7 " + // Well yes, this is not the best way...
            "ORDER BY ka.kaId"
        )
        .setCacheable(true)
        .setCacheRegion("kaliste")
        .array(new AtlKlaeranlagen[0]);
    }
}
