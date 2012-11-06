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

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;

/**
 * Diese Klasse representiert eine Parametergruppe.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class AtlParametergruppen
    extends      AbstractAtlParameterGruppen
    implements   Serializable
{
    private static final long serialVersionUID = 2622823822795338854L;

    public AtlParametergruppen() {
        super();
    }

    public AtlParametergruppen(String name, Double preisfueranalyse) {
        super(name, preisfueranalyse);
    }

    /**
     * Diese Funktion liefert ein {@link AtlParametergruppen} Objekt anhand
     * einer <i>id</i>.
     *
     * @param id Die ID einer Parametergruppe.
     *
     * @return die Parametergruppe mit der ID <i>id</i>.
     */
    public static AtlParametergruppen findById(int id) {
        AtlParametergruppen result = null;

        result = (AtlParametergruppen) new DatabaseAccess()
                .get(AtlParametergruppen.class, id);

        return result;
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
