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
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ATL_PARAMETER' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AtlParameter extends AbstractAtlParameter implements Serializable {
    private static final long serialVersionUID = 2852105702010364133L;

    /**
     * Simple constructor of AtlParameter instances.
     */
    public AtlParameter() {
    }

    /**
     * Constructor of AtlParameter instances given a simple primary key.
     * @param ordnungsbegriff
     */
    public AtlParameter(java.lang.String ordnungsbegriff) {
        super(ordnungsbegriff);
    }

    /* Add customized code below */

    /**
     * @return Der Name des Parameters.
     */
    @Override
    public String toString() {
        String tmp = getBezeichnung();
        if (tmp != null) {
            tmp = tmp.trim();
        }
        return tmp;
    }

    /**
     * Liefert einen bestimmten Parameter.
     * @param id Die ID des Parameters
     * @return Der Parameter mit der gegebenen ID oder <code>null</code> falls
     *         dieser nicht existiert
     */
    public static AtlParameter getParameter(String id) {
        AtlParameter parameter = null;

        parameter = (AtlParameter) new DatabaseAccess()
                .get(AtlParameter.class, id);

        return parameter;
    }

    /*
     * Liefert alle Parameter, die für Klärschlamm-Probenahmen relevant sind.
     * D.h. alle, deren Klärschlamm-Grenzwert nicht <code>NULL</code> ist.
     * @return Ein Array mit allen für Klärschlamm-Probenahmen relevanten Parametern
     */
    public static AtlParameter[] getKlaerschlammParameter() {
        return (AtlParameter[]) new DatabaseAccess().createQuery(
                "FROM AtlParameter as param WHERE " +
                "param.klaerschlammGw is not null " +
                "ORDER BY param.bezeichnung")
                .array(new AtlParameter[0]);
    }

    /**
     * Liefert alle Parameter, die für SielhautBearbeiten-Probenahmen relevant
     * sind. D.h. alle, deren SielhautBearbeiten-Grenzwert nicht
     * <code>NULL</code> ist.
     * @return Ein Array mit allen für SielhautBearbeiten-Probenahmen relevanten
     * Parametern
     */
    public static List<?> getGroupedParameterAsList() {
        return new DatabaseAccess().createQuery(
                "FROM AtlParameter as param "
                + "WHERE param.atlParameterGruppe.id = 1"
                + "or param.atlParameterGruppe.id = 2"
                + "or param.atlParameterGruppe.id = 3"
                + "ORDER BY param.reihenfolge")
                .list();
    }
    /**
     * AtlParameter.getGroupedParameterAsList als Array
     *
     * @return Ein Array mit allen für Probenahmen relevanten Parametern
     */
    public static AtlParameter[] getGroupedParameter() {
        return (AtlParameter[])
            AtlParameter.getGroupedParameterAsList().toArray(new AtlParameter[0]);
    }

    public static List<?> getAllAsList() {
        return new DatabaseAccess().createQuery(
            "FROM AtlParameter as param ORDER BY param.bezeichnung")
            .list();
    }
    public static AtlParameter[] getAllAsArray() {
        return new DatabaseAccess().createQuery(
            "FROM AtlParameter as param ORDER BY param.bezeichnung")
            .array(new AtlParameter[0]);
    }

    public static AtlParameter[] getParameterGroup(int id) {
        return (AtlParameter[]) new DatabaseAccess().createQuery(
                "FROM AtlParameter as param WHERE "
                + "param.atlParameterGruppe.id = " + id)
                .array(new AtlParameter[0]);
    }
}
