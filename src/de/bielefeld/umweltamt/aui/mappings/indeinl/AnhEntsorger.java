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

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_ENTSORGER' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AnhEntsorger extends AbstractAnhEntsorger implements Serializable {
    private static final long serialVersionUID = 1013064009674741549L;

    /**
     * Simple constructor of Anh50Entsorger instances.
     */
    public AnhEntsorger() {
    }

    /**
     * Constructor of Anh50Entsorger instances given a simple primary key.
     * @param entsorgerid
     */
    public AnhEntsorger(java.lang.Integer entsorgerid) {
        super(entsorgerid);
    }

    @Override
    public String toString() {
        return getEntsorger();
    }

    /**
     * Liefert alle vorhandenen Entsorger.
     * @return Alle vorhandenen Entsorger
     */
    public static AnhEntsorger[] getEntsorg() {
        List<?> list = null;

        String suchString = "from AnhEntsorger ahe order by ahe.entsorgerid";

        list = new DatabaseAccess().createQuery(suchString)
            .setCacheable(true)
            .setCacheRegion("entsorgerliste")
            .list();

        AnhEntsorger[] result = new AnhEntsorger[list.size()];
        result = (AnhEntsorger[]) list.toArray(result);

        return result;
    }

    public static boolean saveEntsorger(AnhEntsorger ents) {
        boolean success = false;
//        AnhEntsorger entsRet = null;
        success = new DatabaseAccess().merge(ents);
//        if (success) { // TODO: Do we really need this?
//            entsRet = ents;
//        }
//        return entsRet;
        return success;
    }
}
