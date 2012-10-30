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
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;

/**
 * Diese Klasse representiert eine Parametergruppe.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class AtlParameterGruppen
    extends      AbstractAtlParameterGruppen
    implements   Serializable
{
    private static final long serialVersionUID = 2622823822795338854L;

    public AtlParameterGruppen() {
        super();
    }

    public AtlParameterGruppen(String name, Double preisfueranalyse) {
        super(name, preisfueranalyse);
    }

    /**
     * Diese Funktion liefert ein {@link AtlParameterGruppen} Objekt anhand
     * einer <i>id</i>.
     *
     * @param id Die ID einer Parametergruppe.
     *
     * @return die Parametergruppe mit der ID <i>id</i>.
     */
    public static AtlParameterGruppen getParameterGroup(int id) {
        AtlParameterGruppen result = null;

        result = (AtlParameterGruppen) new DatabaseAccess()
                .get(AtlParameterGruppen.class, id);

        return result;
    }


    /**
     * Diese Funktion pr&uuml;ft, ob die {@link AtlParameter}, die in
     * <i>group</i> enthalten sind, vollst&auml;ndig sind.
     *
     * @param id Die ID der Parametergruppe.
     * @param group Die Liste mit den Parametern.
     *
     * @return true, wenn alle Parameter der Gruppen enthalten sind, sonst
     * false.
     */
    public static boolean isGroupComplete(int id, List<?> group) {
        AtlParameter[] complete = AtlParameter.getParameterGroup(id);

        int completeSize = complete.length;
        int groupSize    = group.size();

        if (completeSize > groupSize) {
            return false;
        }

//        int contains = 0;

        for (AtlParameter parameter: complete) {
            String  paramId  = parameter.getOrdnungsbegriff();
            boolean isInList = false;

            for (int i = 0; i < groupSize; i++) {
                AtlParameter tmp = (AtlParameter) group.get(i);

                if (paramId.equals(tmp.getOrdnungsbegriff())) {
                    isInList = true;
                    break;
                }
            }

            if (!isInList) {
                return false;
            }
        }

        return true;
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
