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
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisGemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektchrono;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisPrioritaet;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
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

    /**
     * Custom BasisBetreiber.toString()
     * Liefert einen String der Form "Name, Zusatz" falls ein Zusatz vorhanden
     * ist, sonst nur den Namen.
     */
    public static String toStringForClass(BasisBetreiber clazz) {
        String zusatz = "";
        if (clazz.getBetrvorname() != null) {
            zusatz = ", " + clazz.getBetrvorname();
        } else if (clazz.getBetrnamezus() != null) {
            zusatz = ", " + clazz.getBetrnamezus();
        }
        return clazz.getBetrname() + zusatz;
    }

    /** @return BasisGemarkung.toGuiString() */
    public static String toStringForClass(BasisGemarkung clazz) {
        return clazz.toGuiString();
    }

    /**
     * Custom BasisObjekt.toString()
     * Liefert einen String, der dieses BasisObjekt beschreibt.
     * @return Einen String in der Form
     *         "[ID:Objekt-ID, Betr.:BasisBetreiber, Stdort:BasisStandort, Art:BasisObjektart]"
     */
    public static String toStringForClass(BasisObjekt clazz) {
        return "[ID:" + clazz.getObjektid() +
            ", Betr.:" + clazz.getBasisBetreiber() +
            ", Stdort:" + clazz.getBasisStandort() +
            ", Art:" + clazz.getBasisObjektarten() + "]";
    }

    /** Custom BasisObjektarten.toString() */
    public static String toStringForClass(BasisObjektarten clazz) {
        String abteilung = "";
        if (clazz.getAbteilung() != null) {
            abteilung = " (" + clazz.getAbteilung() + ")";
        }
        return clazz.getObjektart() + abteilung;
    }

    /**
     * Custom BasisObjektchrono.toString()
     * Liefert einen String der Form "BehaelterID: Anlagenart Herstellnr".
     */
    public static String toStringForClass(BasisObjektchrono clazz) {
        String tmp = clazz.getId() + ": ";
        if (clazz.getDatum() != null) {
            tmp += clazz.getDatum() + " ";
        }
        if (clazz.getSachverhalt() != null) {
            tmp += clazz.getSachverhalt();
        }
        return tmp;
    }

    /**
     * Custom BasisObjektverknuepfung.toString()
     * Liefert einen String mit der ID.
     */
    public static String toStringForClass(BasisObjektverknuepfung clazz) {
        return clazz.getId() + ": ";
    }

    /** @return Custom BasisPrioritaet.toString() */
    public static String toStringForClass(BasisPrioritaet clazz) {
        return clazz.getPrioritaet().toString();
    }

    /** @return Custom BasisSachbearbeiter.toString() */
    public static String toStringForClass(BasisSachbearbeiter clazz) {
        String name = clazz.getName();
        String kennnummer = clazz.getKennummer();

        return ((name != null) ?
            name + " (" + kennnummer + ")" : kennnummer);
    }

    /**
     * Custom BasisStandort.toString()<br>
     * Liefert die komplette Strasse, wenn vorhanden inklusive der Hausnummer
     * und deren Zusatz.<br>
     * <br>
     * Formatierung: &quot;&lt;Strasse&gt; &lt;HausNr&gt;&lt;HausNrzus&gt;&quot;<br>
     * <br>
     * Beispiele: &quot;Ravensberger Straße 77&quot;, &quot;Apfelstraße
     * 23b&quot;, &quot;Jahnplatz 41-42&quot;
     * @return Komplette, formatierte Strasse inkl. Hausnr
     */
    public static String toStringForClass(BasisStandort clazz) {
        String formatierteStrasse = clazz.getStrasse();
        if (clazz.getHausnr() != null) {
            formatierteStrasse += (" " + clazz.getHausnr());
        }
        if (clazz.getHausnrzus() != null) {
            formatierteStrasse += clazz.getHausnrzus();
        }
        return formatierteStrasse;
    }

    /** @return BasisStrassen.toGuiString() */
    public static String toStringForClass(BasisStrassen clazz) {
        return clazz.toGuiString();
    }

    /* ********************************************************************** */
    /* toStrings for package ATL                                              */
    /* ********************************************************************** */

    /**
     * Custom AtlAnalyseposition.toString()
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
     * Custom AtlProbenahmen.toString()
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
