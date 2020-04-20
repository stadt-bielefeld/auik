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

import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.atl.Status;

/**
 * This is a service class for all database constants.<br>
 * <br>
 * These constants are not in their respective classes because we want to keep
 * them as clean of not-generated code as possible. As most of the constants
 * were static anyway, they can just as fine live here.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
// TODO: Only save the IDs here and not the objects!
public class DatabaseConstants {

    /* ********************************************************************** */
    /* Constants for package ATL                                              */
    /* ********************************************************************** */

    /* AtlEinheiten */
    public static final Einheiten ATL_EINHEIT_MG_L =
        Einheiten.findById(42);
    public static final Einheiten ATL_EINHEIT_MG_KG =
        Einheiten.findById(43);

    /* AtlParameter */
    public static final String ATL_PARAMETER_ID_AMMONIUM_STICKSTOFF = "L12490";
    public static final String ATL_PARAMETER_ID_AOX = "L13430";
    public static final String ATL_PARAMETER_ID_ARSEN = "L11420";
    public static final String ATL_PARAMETER_ID_BASISCH_WIRKSAME_SUBSTANZ = "N00002";
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
    public static final String ATL_PARAMETER_ID_PFT = "P00002";
    public static final String ATL_PARAMETER_ID_TOC = "L15230";
    public static final String ATL_PARAMETER_ID_ABWASSERMENGE = "P00013";
    public static final String ATL_PARAMETER_ID_TEMPERATUR = "L10111";
    public static final String ATL_PARAMETER_ID_PH_WERT = "B00600";
    public static final String ATL_PARAMETER_ID_LEITFAEHIGKEIT = "L10821";

    /* AtlProbeart */
    public static final Integer ATL_PROBEART_ID_ABWASSER_UWB = 2;
    public static final Integer ATL_PROBEART_ID_ABWASSER_E_SATZUNG = 3;
    public static final Integer ATL_PROBEART_ID_ANLIEFERUNG = 4;
    public static final Integer ATL_PROBEART_ID_FAULSCHLAMM = 5;
    public static final Integer ATL_PROBEART_ID_ROHRSCHLAMM = 6;
    public static final Integer ATL_PROBEART_ID_SIELHAUT = 7;
    public static final Integer ATL_PROBEART_ID_ZULAUF = 9;

    /* AtlStatus */
    public static final Status ATL_STATUS_PROBENAHMEAUFTRAG_GEDRUCKT =
        Status.findById(8);
    public static final Status ATL_STATUS_ERGAENZT_UND_FREIGEGEBEN =
    	Status.findById(9);
    public static final Status ATL_STATUS_DATEN_EINGETRAGEN =
    	Status.findById(10);
    public static final Status ATL_STATUS_FREIGEGEBEN_FUER_BESCHEIDDRUCK =
    	Status.findById(11);
    public static final Status ATL_STATUS_BESCHEID_GEDRUCKT =
    	Status.findById(3);

    /* ********************************************************************** */
    /* Constants for package BASIS                                            */
    /* ********************************************************************** */

    /* BasisBetreiber */
    public static final int BASIS_BETREIBER_ID_Umweltamt_360x33 = 3;

    /* BasisStandort */
    public static final int BASIS_STANDORT_KEIN_STANDORT = 3;

    /* BasisObjektarten */
    public static final int BASIS_OBJEKTART_ID_SUEV = 13;
    public static final int BASIS_OBJEKTART_ID_PROBEPUNKT = 32;
    public static final int BASIS_OBJEKTART_ID_ABA = 34;
    public static final int BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE = 40;
    public static final int BASIS_OBJEKTART_ID_GENEHMIGUNG = 42;
    public static final int BASIS_OBJEKTART_ID_ABSCHEIDER34 = 58;
    public static final int BASIS_OBJEKTART_ID_EINLEITUNGSTELLE = 66;
    public static final int BASIS_OBJEKTART_ID_ANFALLSTELLE = 65;
    public static final int BASIS_OBJEKTART_ID_ENTWAESSERUNGSGRUNDSTUECK = 69;
    public static final int BASIS_OBJEKTART_ID_SONDERBAUWERK = 68;
    public static final String BASIS_OBJEKTART_ABTEILUNG_INDIREKT = "Indirekt-Einl.";
    public static final String BASIS_OBJEKTART_ABTEILUNG_DIREKT = "Direkt-Einl.";
    public static final String BASIS_OBJEKTART_ABTEILUNG_AWSV = "AwSV";

    /* Anfallstellen */

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
    public static final int BASIS_OBJEKTART_ID_ANHANG_52 = 35;
    public static final int BASIS_OBJEKTART_ID_BHKW = 36;
    public static final int BASIS_OBJEKTART_ID_ABSCHEIDER_ES = 60;

    /* ********************************************************************** */
    /* Constants for package INDEINL                                          */
    /* ********************************************************************** */

    /* AnhEntsorger */
    public static final int ANH_ENTSORGER_ID_UNBEKANNT = 1;

    /* ********************************************************************** */
    /* Constants for package VAWS                                             */
    /* ********************************************************************** */

    /* VawsAnlagenart(en) */
    public static final String VAWS_ANLAGENART_ABFUELLFLAECHE = "Abfüllfläche";
    public static final String VAWS_ANLAGENART_ROHRLEITUNG = "Rohrleitung";
    public static final String VAWS_ANLAGENART_VAWS_ABSCHEIDER = "VAwS-Abscheider";
    public static final String VAWS_ANLAGENART_FS = "Fahrsilo";
    public static final String VAWS_ANLAGENART_GK = "Güllekeller";
    public static final String VAWS_ANLAGENART_GHB = "Güllehochbehälter";

}