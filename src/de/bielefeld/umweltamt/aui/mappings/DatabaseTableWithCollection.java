/**
 * Copyright 2005-2042, Stadt Bielefeld
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

package de.bielefeld.umweltamt.aui.mappings;

import java.util.Collection;
import java.util.Vector;

/**
 * Interface for all database table classes which have Collections which
 * need to be initialized.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public interface DatabaseTableWithCollection {

    /**
     * Return all Collections which need to be initialized.
     * @return An array of all Collections
     */
    public Vector<Collection<?>> getToInitCollections();
}
