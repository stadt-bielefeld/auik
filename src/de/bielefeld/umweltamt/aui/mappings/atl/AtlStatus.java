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
 * A class that represents a row in the 'ATL_STATUS' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AtlStatus extends AbstractAtlEinheiten implements Serializable {
    private static final long serialVersionUID = 8102132119175012262L;

    /**
     * Simple constructor of AtlStatus instances.
     */
    public AtlStatus() {
    }

    /**
     * Constructor of AtlStatus instances given a simple primary key.
     * @param id
     */
    public AtlStatus(java.lang.Integer id) {
        super(id);
    }

    /* Add customized code below */

    /**
     * @return Der Name der Einheit.
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
     * Liefert alle in der Einheiten-Tabelle gespeicherten Einheiten.
     * @return Ein Array mit allen Einheiten
     */
    private static AtlStatus[] getStatus() {
        return (AtlStatus[]) new DatabaseAccess()
                .createQuery("FROM AtlStatus as status")
                .array(new AtlStatus[0]);
    }

    /**
     * Liefert das AtlStatus Objekt mit der passenden Bezeichnung.
     * @return das Objekt passend zu <i>bezeichnung</i>, oder <i>null</i> falls
     *         kein Objekt mit dieser Bezeichnung existiert.
     */
    public static AtlStatus getStatus(String bezeichnung) {
        AtlStatus[] status = getStatus();
        for (AtlStatus s : status) {
            if (bezeichnung.equals(s.getBezeichnung())) {
                return s;
            }
        }
        return null;
    }

    public static AtlStatus getStatus(int id) {
        AtlStatus[] status = getStatus();
        for (AtlStatus s : status) {
            if (id == s.getId()) {
                return s;
            }
        }
        return null;
    }

    public static String[] getStatusAsString() {
        AtlStatus[] status = getStatus();
        String[] str = new String[status.length];
        int idx = 0;
        for (AtlStatus s : status) {
            str[idx++] = s.getBezeichnung();
        }
        return str;
    }
}
