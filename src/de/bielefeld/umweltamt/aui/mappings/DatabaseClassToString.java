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

import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameterGruppen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlSielhaut;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlStatus;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * This is a service class for the toString method of the database classes.<br>
 * <br>
 * This is not in its respective classes because we want to keep them as clean
 * of not-generated code as possible.
 * This may not be the best solution... :-/<br><br>
 *
 * TODO: These were mostly just copied out of the mapping classes and
 * should be revised in time
 * (e.g. choosing between toDebugString() and toGuiString())!
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class DatabaseClassToString {

    /* ********************************************************************** */
    /* toStrings for package BASIC                                            */
    /* ********************************************************************** */

    /* ********************************************************************** */
    /* toStrings for package ATL                                              */
    /* ********************************************************************** */

    /**
     * @return Einen String der Form
     *         "[Position: Parameter: Wert Einheit, Analyse_Von, [Probenahme], ID:Id]"
     */
    public static String toStringForClass(AtlAnalyseposition clazz) {
        String tmp = "[Position: " +
            clazz.getAtlParameter() + ": " + clazz.getWert() + " " +
            clazz.getAtlEinheiten() + ", " +
            clazz.getAnalyseVon() + ", ";
        if (clazz.getAtlProbenahmen() != null) {
            tmp += clazz.getAtlProbenahmen() + ", ";
        }
        if (clazz.getId() != null) {
            tmp += "ID:" + clazz.getId();
        } else {
            tmp += "UNSAVED";
        }
        tmp += "]";
        return tmp;
    }

    /** @return AtlEinheiten.toGuiString() */
    public static String toStringForClass(AtlEinheiten clazz) {
        return clazz.toGuiString();
    }

    /** @return AtlKlaeranlagen.toGuiString() */
    public static String toStringForClass(AtlKlaeranlagen clazz) {
        return clazz.toGuiString();
    }

    /** @return AtlParameter.toGuiString() */
    public static String toStringForClass(AtlParameter clazz) {
        return clazz.toGuiString();
    }

    /** Not used so far */
    public static String toStringForClass(AtlParameterGruppen clazz) {
        return null;
    }

    /** @return AtlProbeart.toGuiString() */
    public static String toStringForClass(AtlProbeart clazz) {
        return clazz.toGuiString();
    }

    /**
     * @return Einen String der Form
     *         "[Probe: Kennummer, Probeart, Datum, Anz.Positionen]" bzw.
     *         "[Probe: Kennummer, Probeart, Datum, N/A]" falls die Positionen
     *         noch nicht aus der Datenbank geholt wurden.
     */
    public static String toStringForClass(AtlProbenahmen clazz) {
        String tmp = "[Probe: " + clazz.getKennummer() + ", "
            + clazz.getProbeArt() + ", "
            + AuikUtils.getStringFromDate(clazz.getDatumDerEntnahme());

        if (clazz.getZeitDerEntnahmen() != null) {
            tmp += " " + clazz.getZeitDerEntnahmen();
        }
        tmp += ", ";

        if (new DatabaseAccess().isInitialized(
            clazz.getAtlAnalysepositionen())) {
            tmp += clazz.getAtlAnalysepositionen().size();
        } else {
            tmp += "N/A";
        }

        tmp += "]";
        return tmp;
    }

    /** @return Custom AtlProbepkt.toString() */
    public static String toStringForClass(AtlProbepkt clazz) {
        return "[Probepunkt:" + clazz.getObjektid() +
            ", Art:" + clazz.getAtlProbeart() +
            ", Nr:" + clazz.getNummer() + "]";
    }

    /** @return Custom AtlSielhaut.toString() */
    public static String toStringForClass(AtlSielhaut clazz) {
        return "[SielhautBearbeiten:" + clazz.getId() + ", "
            + clazz.getBezeichnung() + "]";
    }

    /** @return AtlStatus.toGuiString() */
    public static String toStringForClass(AtlStatus clazz) {
        return clazz.toGuiString();
    }

    /* ********************************************************************** */
    /* toStrings for package INDEINL                                          */
    /* ********************************************************************** */
    /* ********************************************************************** */
    /* toStrings for package VAWS                                             */
    /* ********************************************************************** */
    /* ********************************************************************** */
    /* toStrings for package TIPI                                             */
    /* ********************************************************************** */
}
