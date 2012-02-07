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
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'VAWS_WGK' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class VawsWgk extends AbstractVawsWgk implements Serializable {
    private static final long serialVersionUID = 815240283815245339L;

    /**
     * Simple constructor of VawsWgk instances.
     */
    public VawsWgk() {
    }

    /* Add customized code below */

    @Override
    public String toString() {
        return super.getWassergef().toString();
    }

    /**
     * Liefer alle VAWS-WGK.
     * @return Ein Array mit den Namen aller WGK.
     */
    public static Integer[] getWgk() {
        String suchString = "select wgk.wassergef " + "from VawsWgk wgk "
            + "order by wgk.wassergef";

        List<?> list;
        list = new DatabaseAccess().createQuery(suchString)
            .setCacheable(true)
            .setCacheRegion("vawswgkliste")
            .list();

        Integer[] result;
        result = new Integer[list.size()];
        result = (Integer[]) list.toArray(result);

        return result;
    }
}
