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

import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;

/**
 * This is a service class for all database constants.<br>
 * <br>
 * These constants are not in their respective classes because we want to keep
 * them as clean of not-generated code as possible. As most of the constants
 * were static anyway, they can just as fine live here.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class DatabaseConstants {

    /* AtlEinheiten */
    public static final AtlEinheiten ATL_EINHEIT_MG_L =
        AtlEinheiten.getEinheit(42);
    public static final AtlEinheiten ATL_EINHEIT_MG_KG =
        AtlEinheiten.getEinheit(43);

    /* AtlParameter */
    public static final AtlParameter ATL_PARAMETER_BLEI =
        AtlParameter.getParameter("L11380");
    public static final AtlParameter ATL_PARAMETER_CADMIUM =
        AtlParameter.getParameter("L11650");
    public static final AtlParameter ATL_PARAMETER_CHROM =
        AtlParameter.getParameter("L11510");
    public static final AtlParameter ATL_PARAMETER_KUPFER =
        AtlParameter.getParameter("L11610");
    public static final AtlParameter ATL_PARAMETER_NICKEL =
        AtlParameter.getParameter("L11880");
    public static final AtlParameter ATL_PARAMETER_QUECKSILBER =
        AtlParameter.getParameter("L11660");
    public static final AtlParameter ATL_PARAMETER_ZINK =
        AtlParameter.getParameter("L11640");
    public static final AtlParameter ATL_PARAMETER_AOX =
        AtlParameter.getParameter("L13430");

    public static final AtlParameter ATL_PARAMETER_TOC =
        AtlParameter.getParameter("L15230");
    public static final AtlParameter ATL_PARAMETER_ABWASSERMENGE =
        AtlParameter.getParameter("P00013");

    public static final AtlParameter ATL_PARAMETER_TEMPERATUR =
        AtlParameter.getParameter("L10111");
    public static final AtlParameter ATL_PARAMETER_PH_WERT =
        AtlParameter.getParameter("B00600");
    public static final AtlParameter ATL_PARAMETER_LEITFAEHIGKEIT =
        AtlParameter.getParameter("L10821");

}
