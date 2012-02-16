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

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'VAWS_WASSEREINZUGSGEBIETE' table. This
 * class may be customized as it is never re-generated after being created.
 */
public class VawsWassereinzugsgebiete extends AbstractVawsWassereinzugsgebiete
    implements Serializable {
    private static final long serialVersionUID = 4045355929811811488L;

    /**
     * Simple constructor of VawsWassereinzugsgebiete instances.
     */
    public VawsWassereinzugsgebiete() {
    }

    /**
     * Constructor of VawsWassereinzugsgebiete instances given a simple primary
     * key.
     * @param wasserezgbid
     */
    public VawsWassereinzugsgebiete(java.lang.Integer wasserezgbid) {
        super(wasserezgbid);
    }

    /* Add customized code below */

    /**
     * Liefert den Namen des Einzugsgebietes.
     */
    @Override
    public String toString() {
        return super.getEzgbname();
    }

    /**
     * Liefert alle vorhandenen VAWS-Wassereinzugsgebiete.
     * @return Alle vorhandenen Wassereinzugsgebiete
     */
    public static VawsWassereinzugsgebiete[] getWEinzugsgebiete() {
        return (VawsWassereinzugsgebiete[]) new DatabaseAccess()
            .createQuery(
                "FROM VawsWassereinzugsgebiete vwezg "
                + "ORDER BY vwezg.wasserezgbid")
            .setCacheable(true)
            .setCacheRegion("wezgbliste")
            .array(new VawsWassereinzugsgebiete[0]);
    }
}
