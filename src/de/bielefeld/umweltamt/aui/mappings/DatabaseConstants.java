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
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;

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

    /* ********************************************************************** */
    /* Constants for package ATL                                              */
    /* ********************************************************************** */

    /* AtlEinheiten */
    public static final AtlEinheiten ATL_EINHEIT_MG_L =
        AtlEinheiten.findById(42);
    public static final AtlEinheiten ATL_EINHEIT_MG_KG =
        AtlEinheiten.findById(43);

    /* AtlParameter */
    public static final AtlParameter ATL_PARAMETER_BLEI =
        AtlParameter.findById("L11380");
    public static final AtlParameter ATL_PARAMETER_CADMIUM =
        AtlParameter.findById("L11650");
    public static final AtlParameter ATL_PARAMETER_CHROM =
        AtlParameter.findById("L11510");
    public static final AtlParameter ATL_PARAMETER_KUPFER =
        AtlParameter.findById("L11610");
    public static final AtlParameter ATL_PARAMETER_NICKEL =
        AtlParameter.findById("L11880");
    public static final AtlParameter ATL_PARAMETER_QUECKSILBER =
        AtlParameter.findById("L11660");
    public static final AtlParameter ATL_PARAMETER_ZINK =
        AtlParameter.findById("L11640");
    public static final AtlParameter ATL_PARAMETER_AOX =
        AtlParameter.findById("L13430");

    public static final AtlParameter ATL_PARAMETER_TOC =
        AtlParameter.findById("L15230");
    public static final AtlParameter ATL_PARAMETER_ABWASSERMENGE =
        AtlParameter.findById("P00013");

    public static final AtlParameter ATL_PARAMETER_TEMPERATUR =
        AtlParameter.findById("L10111");
    public static final AtlParameter ATL_PARAMETER_PH_WERT =
        AtlParameter.findById("B00600");
    public static final AtlParameter ATL_PARAMETER_LEITFAEHIGKEIT =
        AtlParameter.findById("L10821");

    /* AtlProbeart */
    public static final AtlProbeart ATL_PROBEART_ANLIEFERUNG =
        AtlProbeart.findById(4);
    public static final AtlProbeart ATL_PROBEART_FAULSCHLAMM =
        AtlProbeart.findById(5);
    public static final AtlProbeart ATL_PROBEART_ROHRSCHLAMM =
        AtlProbeart.findById(6);
    public static final AtlProbeart ATL_PROBEART_SIELHAUT =
        AtlProbeart.findById(7);
    public static final AtlProbeart ATL_PROBEART_ZULAUF =
        AtlProbeart.findById(9);

    /* AtlKläranlage */
    public static final AtlKlaeranlagen ATL_KLAERANLAGE_HEEPEN =
        AtlKlaeranlagen.findById(1);
    public static final AtlKlaeranlagen ATL_KLAERANLAGE_BRAKE =
        AtlKlaeranlagen.findById(4);
    public static final AtlKlaeranlagen ATL_KLAERANLAGE_OBERE_LUTTER =
        AtlKlaeranlagen.findById(5);
    public static final AtlKlaeranlagen ATL_KLAERANLAGE_SENNESTADT =
        AtlKlaeranlagen.findById(8);
    public static final AtlKlaeranlagen ATL_KLAERANLAGE_VERL_SENDE =
        AtlKlaeranlagen.findById(9);

    /* ********************************************************************** */
    /* Constants for package BASIS                                            */
    /* ********************************************************************** */

    /* BasisObjektarten */
    public static final BasisObjektarten BASIS_OBJEKTART_PROBEPUNKT =
        BasisObjektarten.getObjektart(32);
}
