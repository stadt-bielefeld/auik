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

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;

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

    /**
     * Get a string representation for the gui
     * @return String
     */
    public String toGuiString() {
        return getBezeichnung();
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("ordnungsbegriff").append("='").append(getOrdnungsbegriff()).append("' ");
        buffer.append("atlParametergruppen").append("='").append(getAtlParametergruppen()).append("' ");
        buffer.append("analyseverfahren").append("='").append(getAnalyseverfahren()).append("' ");
        buffer.append("bezeichnung").append("='").append(getBezeichnung()).append("' ");
        buffer.append("wirdgemessenineinheit").append("='").append(getWirdgemessenineinheit()).append("' ");
        buffer.append("grenzwert").append("='").append(getGrenzwert()).append("' ");
        buffer.append("grenzwertname").append("='").append(getGrenzwertname()).append("' ");
        buffer.append("sielhautGw").append("='").append(getSielhautGw()).append("' ");
        buffer.append("klaerschlammGw").append("='").append(getKlaerschlammGw()).append("' ");
        buffer.append("preisfueranalyse").append("='").append(getPreisfueranalyse()).append("' ");
        buffer.append("einzelnbeauftragbar").append("='").append(getEinzelnBeauftragbar()).append("' ");
        buffer.append("kennzeichnung").append("='").append(getKennzeichnung()).append("' ");
        buffer.append("konservierung").append("='").append(getKonservierung()).append("' ");
        buffer.append("reihenfolge").append("='").append(getReihenfolge()).append("' ");
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    /* Add customized code below */

    /**
     * Liefert einen bestimmten Parameter.
     * @param id Die ID des Parameters
     * @return Der Parameter mit der gegebenen ID oder <code>null</code> falls
     *         dieser nicht existiert
     */
    public static AtlParameter findById(String id) {
        AtlParameter parameter = null;

        parameter = (AtlParameter) new DatabaseAccess()
                .get(AtlParameter.class, id);

        return parameter;
    }
}
