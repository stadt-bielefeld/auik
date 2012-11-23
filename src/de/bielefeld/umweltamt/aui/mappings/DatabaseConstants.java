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
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlStatus;

/**
 * This is a service class for all database constants.<br>
 * <br>
 * These constants are not in their respective classes because we want to keep
 * them as clean of not-generated code as possible. As most of the constants
 * were static anyway, they can just as fine live here.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
// TODO: Only save the IDs here and not the objects!
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
    public static final String ATL_PARAMETER_ID_AMMONIUM_STICKSTOFF = "L12490";
    public static final String ATL_PARAMETER_ID_AOX = "L13430";
    public static final String ATL_PARAMETER_ID_ARSEN = "L11420";
    public static final String ATL_PARAMETER_ID_BASISCH_WIRKSAME_SUBSTANZ =
        "N00002";
    public static final String ATL_PARAMETER_ID_BENZO_A_PYREN = "B00106";
    public static final String ATL_PARAMETER_ID_BLEI = "L11380";
    public static final String ATL_PARAMETER_ID_CADMIUM = "L11650";
    public static final String ATL_PARAMETER_ID_CHROM = "L11510";
    public static final String ATL_PARAMETER_ID_CHROMAT = "B00188";
    public static final String ATL_PARAMETER_ID_COBALT = "B00108";
    public static final String ATL_PARAMETER_ID_DEHP = "N00001";
    public static final String ATL_PARAMETER_ID_GLUEHVERLUST = "B00150";
    public static final String ATL_PARAMETER_ID_KALIUM = "L11130";
    public static final String ATL_PARAMETER_ID_KUPFER = "L11610";
    public static final String ATL_PARAMETER_ID_MAGNESIUM = "L11210";
    public static final String ATL_PARAMETER_ID_NICKEL = "L11880";
    public static final String ATL_PARAMETER_ID_PCB_28 = "B00109";
    public static final String ATL_PARAMETER_ID_PCB_52 = "B00110";
    public static final String ATL_PARAMETER_ID_PCB_101 = "B00111";
    public static final String ATL_PARAMETER_ID_PCB_138 = "B00112";
    public static final String ATL_PARAMETER_ID_PCB_153 = "B00113";
    public static final String ATL_PARAMETER_ID_PCB_180 = "B00114";
    public static final String ATL_PARAMETER_ID_PCB_SUMME = "B00145";
    public static final String ATL_PARAMETER_ID_PCDD_PCDF = "B00613";
    public static final String ATL_PARAMETER_ID_PHOSPHOR = "B00273";
    public static final String ATL_PARAMETER_ID_QUECKSILBER = "L11660";
    public static final String ATL_PARAMETER_ID_SCHWEFEL = "N00003";
    public static final String ATL_PARAMETER_ID_STICKSTOFF = "B00125";
    public static final String ATL_PARAMETER_ID_THALLIUM = "B00384";
    public static final String ATL_PARAMETER_ID_TROCKENSUBSTANZ = "B00107";
    public static final String ATL_PARAMETER_ID_ZINK = "L11640";

    public static final String ATL_PARAMETER_ID_TOC = "L15230";
    public static final String ATL_PARAMETER_ID_ABWASSERMENGE = "P00013";

    public static final String ATL_PARAMETER_ID_TEMPERATUR = "L10111";
    public static final String ATL_PARAMETER_ID_PH_WERT = "B00600";
    public static final String ATL_PARAMETER_ID_LEITFAEHIGKEIT = "L10821";

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

    /* AtlStatus */
    public static final AtlStatus ATL_STATUS_PROBENAHMEAUFTRAG_GEDRUCKT =
        AtlStatus.findById(8);
    public static final AtlStatus ATL_STATUS_ERGAENZT_UND_FREIGEGEBEN =
        AtlStatus.findById(9);
    public static final AtlStatus ATL_STATUS_DATEN_EINGETRAGEN =
        AtlStatus.findById(10);
    public static final AtlStatus ATL_STATUS_FREIGEGEBEN_FUER_BESCHEIDDRUCK =
        AtlStatus.findById(11);
    public static final AtlStatus ATL_STATUS_BESCHEID_GEDRUCKT =
        AtlStatus.findById(3);

    /* ********************************************************************** */
    /* Constants for package BASIS                                            */
    /* ********************************************************************** */

    /* BasisBetreiber */
    public static final int BASIS_BETREIBER_ID_KEINE_BETREIBER = 3;

    /* BasisObjektarten */
    public static final int BASIS_OBJEKTART_ID_SUEV = 13;
    public static final int BASIS_OBJEKTART_ID_ANHANG_49 = 14;
    public static final int BASIS_OBJEKTART_ID_FETTABSCHEIDER = 15;
    public static final int BASIS_OBJEKTART_ID_BWK = 16;
    public static final int BASIS_OBJEKTART_ID_ANHANG_53_KLEIN = 17;
    public static final int BASIS_OBJEKTART_ID_ANHANG_53_GROSS = 18;
    public static final int BASIS_OBJEKTART_ID_ABSCHEIDER = 19;
    public static final int BASIS_OBJEKTART_ID_ANHANG_50 = 20;
    public static final int BASIS_OBJEKTART_ID_ANHANG_40 = 25;
    public static final int BASIS_OBJEKTART_ID_ANHANG_55 = 29;
    public static final int BASIS_OBJEKTART_ID_ANHANG_56 = 30;
    public static final int BASIS_OBJEKTART_ID_PROBEPUNKT = 32;
    public static final int BASIS_OBJEKTART_ID_ANHANG_52 = 35;
    public static final int BASIS_OBJEKTART_ID_BHKW = 36;
    public static final int BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE = 40;
    public static final int BASIS_OBJEKTART_ID_GENEHMIGUNG = 42;
    public static final int BASIS_OBJEKTART_ID_ABSCHEIDER34 = 58;
    public static final String BASIS_OBJEKTART_ABTEILUNG_33 = "360.33";
    public static final String BASIS_OBJEKTART_ABTEILUNG_34 = "360.34";

    /* BasisStandort */
    public static final int BASIS_STANDORT_KEIN_STANDORT = 41;

    /* AnhEntsorger */
    public static final int ANH_ENTSORGER_ID_UNBEKANNT = 1;
}
