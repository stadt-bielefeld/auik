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
import de.bielefeld.umweltamt.aui.mappings.atl.AtlMetaParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParametergruppen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlSielhaut;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlStatus;
import de.bielefeld.umweltamt.aui.mappings.atl.ViewAtlAnalysepositionAll;
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
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Analysen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Kontrollen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Ortstermine;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Verwaltungsverfahren;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh53Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh55Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhBwkFachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhEntsorger;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhSuevFachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.IndeinlGenehmigung;
import de.bielefeld.umweltamt.aui.mappings.tipi.AuikWzCode;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsAbfuellflaeche;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsAbscheider;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsAnlagenarten;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsBehaelterart;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFachdaten;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFluessigkeit;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsGebuehrenarten;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsGefaehrdungsstufen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsMaterial;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsStandortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsVbfeinstufung;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWassereinzugsgebiete;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWgk;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWirtschaftszweige;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;

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
        return clazz.getObjektart()
            + (clazz.getAbteilung() != null ?
                " (" + clazz.getAbteilung() + ")" : "");
    }

    /**
     * Custom BasisObjektchrono.toString()
     * Liefert einen String der Form "BehaelterID: Anlagenart Herstellnr".
     */
    public static String toStringForClass(BasisObjektchrono clazz) {
        return clazz.getId() + ": "
            + (clazz.getDatum() != null ? clazz.getDatum() + " " : "")
            + (clazz.getSachverhalt() != null ? clazz.getSachverhalt() : "");
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

        return ((name != null) ? name + " (" + kennnummer + ")" : kennnummer);
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
        return clazz.getStrasse()
            + (clazz.getHausnr() != null ? " " + clazz.getHausnr() : "")
            + (clazz.getHausnrzus() != null ? clazz.getHausnrzus() : "");
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
        return "[Position: " + clazz.getAtlParameter() + ": "
            + clazz.getWert() + " " + clazz.getAtlEinheiten() + ", "
            + clazz.getAnalyseVon() + ", "
            + (clazz.getAtlProbenahmen() != null ?
                clazz.getAtlProbenahmen() + ", " : "")
            + (clazz.getId() != null ?
                "ID:" + clazz.getId() : "UNSAVED")
            + "]";
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

    /** @return AtlMetaParameter.toDebugString() */
    public static String toStringForClass(AtlMetaParameter clazz) {
        return clazz.toDebugString();
    }

    /** Not used so far */
    public static String toStringForClass(AtlParametergruppen clazz) {
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
        return "[Probe: " + clazz.getKennummer() + ", "
            + clazz.getProbeArt() + ", "
            + AuikUtils.getStringFromDate(clazz.getDatumDerEntnahme())
            + (clazz.getZeitDerEntnahmen() != null ?
                " " + clazz.getZeitDerEntnahmen() : "")
            + ", "
            + (DatabaseAccess.isInitialized(
                clazz.getAtlAnalysepositionen()) ?
                    clazz.getAtlAnalysepositionen().size() : "N/A")
            + "]";
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

    /** Custom ViewAtlAnalysepositionAll.toString() */
    public static String toStringForClass(ViewAtlAnalysepositionAll clazz) {
        return clazz.getAtlAnalyseposition().toString();
    }

    /* ********************************************************************** */
    /* toStrings for package INDEINL                                          */
    /* ********************************************************************** */

    /**
     * @return Custom Anh40Fachdaten.toString()
     * Liefert einen String der Form "[Anhang 40:ID]"
     */
    public static String toStringForClass(Anh40Fachdaten clazz) {
        return "[Anhang 40:" + clazz.getBasisObjekt() + "]";
    }

    /**
     * @return Custom Anh49Abscheiderdetails.toString()
     * Liefert einen String der Form "[ID:ID, NR von VON, LAGE]".
     */
    public static String toStringForClass(Anh49Abscheiderdetails clazz) {
        return "[ID:" + clazz.getAbscheiderid() + ", " + clazz.getAbscheidernr()
            + " von " + clazz.getVon() + ", " + clazz.getLage() + "]";
    }

    /**
     * @return Custom Anh49Analysen.toString()
     * Liefert einen String der Form "[Datum: DATUM und ANALYSENID]".
     */
    public static String toStringForClass(Anh49Analysen clazz) {
        return "[Datum:" + clazz.getDatum() + ", "
            + clazz.getAnalysenid() + "]";
    }

    /** @return Custom Anh49Fachdaten.toString() */
    public static String toStringForClass(Anh49Fachdaten clazz) {
        return "[Anh49:" + clazz.getBasisObjekt() + "]";
    }

    /**
     * @return Custom Anh49Kontrollen.toString()
     * Liefert einen String der Form "[Datum: Prüfdatum und Pruefergebnis]".
     */
    public static String toStringForClass(Anh49Kontrollen clazz) {
        return "[Prüfdatum: "
            + AuikUtils.getStringFromDate(clazz.getPruefdatum())
            + ", Ergebnis: " + clazz.getPruefergebnis() + "]";
    }

    /**
     * @return Custom Anh49Ortstermine.toString()
     * Liefert einen String der Form "[Datum: DATUM und SACHBEARBEITER]".
     */
    public static String toStringForClass(Anh49Ortstermine clazz) {
        return "[Datum: " + AuikUtils.getStringFromDate(clazz.getDatum())
            + ", SachbearbeiterIn: " + clazz.getSachbearbeiterIn() + "]";
    }

    /** @return Custom Anh49Verwaltungsverfahren.toString() */
    public static String toStringForClass(Anh49Verwaltungsverfahren clazz) {
        return "[Datum: " + AuikUtils.getStringFromDate(clazz.getDatum())
            + ", Maßnahme: " + clazz.getMassnahme() + " ("
            + ((clazz.isAbgeschlossen() != null && clazz.isAbgeschlossen()) ?
                "" : "nicht ") + "abgeschlossen)"
            + ", SachbearbeiterIn: " + clazz.getSachbearbeiterIn()
            + (clazz.getWiedervorlage() == null ? "" : ", Wiedervorlage: "
            + AuikUtils.getStringFromDate(clazz.getWiedervorlage())) + "]";
    }

    /** @return Custom Anh50Fachdaten.toString() */
    public static String toStringForClass(Anh50Fachdaten clazz) {
        return "[ID:" + clazz.getObjektid() + "]";
    }

    /** @return Custom Anh52Fachdaten.toString() */
    public static String toStringForClass(Anh52Fachdaten clazz) {
        return "[ID:" + clazz.getObjektid() + "]";
    }

    /** @return Custom Anh53Fachdaten.toString() */
    public static String toStringForClass(Anh53Fachdaten clazz) {
        return "[ID:" + clazz.getObjektid() + "]";
    }

    /** @return Custom Anh55Fachdaten.toString() */
    public static String toStringForClass(Anh55Fachdaten clazz) {
        return "[ID:" + clazz.getId() + "]";
    }

    /** @return Custom Anh56Fachdaten.toString() */
    public static String toStringForClass(Anh56Fachdaten clazz) {
        return "[ID:" + clazz.getObjektid() + "]";
    }

    /**
     * @return Custom AnhBwkFachdaten.toString()
     * Liefert einen String der Form "[BWK:ID, Hersteller Typ]"
     */
    public static String toStringForClass(AnhBwkFachdaten clazz) {
        return "[BWK:" + clazz.getBwkId() + ", "
            + clazz.getKHersteller() + " " + clazz.getKTyp() + "]";
    }

    /** @return AnhEntsorger.toGuiString() */
    public static String toStringForClass(AnhEntsorger clazz) {
        return clazz.toGuiString();
    }

    /**
     * @return Custom AnhSuevFachdaten.toString()
     * Liefert einen String der Form "[SuevKan Verfahren:ID]"
     */
    public static String toStringForClass(AnhSuevFachdaten clazz) {
        return "[SuevKan Verfahren:" + clazz.getObjektid() + "]";
    }

    /**
     * @return Custom IndeinlGenehmigung.toString()
     * Liefert einen String der Form "[Genehmigung Verfahren:ID]"
     */
    public static String toStringForClass(IndeinlGenehmigung clazz) {
        return "[Genehmigung Verfahren:" + clazz.getObjektid() + "]";
    }

    /* ********************************************************************** */
    /* toStrings for package VAWS                                             */
    /* ********************************************************************** */

    /** @return Custom VawsAbfuellflaeche.toString() */
    public static String toStringForClass(VawsAbfuellflaeche clazz) {
        return "[VawsAbfuellflaeche: " + clazz.getBehaelterid()
            + ", FD:" + clazz.getVawsFachdaten() + "]";
    }

    /** @return Custom VawsAbscheider.toString() */
    public static String toStringForClass(VawsAbscheider clazz) {
        return "[VawsAbscheider: " + clazz.getBehaelterid()
            + ", FD:" + clazz.getVawsFachdaten() + "]";
    }

    /** @return "VawsAnlagenarten.toGuiString() */
    public static String toStringForClass(VawsAnlagenarten clazz) {
        return clazz.toGuiString();
    }

    /** @return "VawsBehaelterart.toGuiString() */
    public static String toStringForClass(VawsBehaelterart clazz) {
        return clazz.toGuiString();
    }

    /**
     * @return Custom VawsFachdaten.toString()
     * Liefert einen String der Form "BehaelterID: Anlagenart Herstellnr".
     */
    public static String toStringForClass(VawsFachdaten clazz) {
        return clazz.getBehaelterId() + ": "
            + (clazz.getAnlagenart() != null ? clazz.getAnlagenart() + " " : "")
            + (clazz.getHerstellnr() != null ? clazz.getHerstellnr() : "");
    }

    /** @return "VawsFluessigkeit.toGuiString() */
    public static String toStringForClass(VawsFluessigkeit clazz) {
        return clazz.toGuiString();
    }

    /** @return "VawsGebuehrenarten.toGuiString() */
    public static String toStringForClass(VawsGebuehrenarten clazz) {
        return clazz.toGuiString();
    }

    /** @return "VawsGefaehrdungsstufen.toGuiString() */
    public static String toStringForClass(VawsGefaehrdungsstufen clazz) {
        return clazz.toGuiString();
    }

    /** @return "VawsMaterial.toGuiString() */
    public static String toStringForClass(VawsMaterial clazz) {
        return clazz.toGuiString();
    }

    /** @return "VawsStandortgghwsg.toGuiString() */
    public static String toStringForClass(VawsStandortgghwsg clazz) {
        return clazz.toGuiString();
    }

    /** @return "VawsVbfeinstufung.toGuiString() */
    public static String toStringForClass(VawsVbfeinstufung clazz) {
        return clazz.toGuiString();
    }

    /** @return "VawsWassereinzugsgebiete.toGuiString() */
    public static String toStringForClass(VawsWassereinzugsgebiete clazz) {
        return clazz.toGuiString();
    }

    /** @return Custom VawsWgk.toString() */
    public static String toStringForClass(VawsWgk clazz) {
        return clazz.getWassergef().toString();
    }

    /** @return "VawsWirtschaftszweige.toGuiString() */
    public static String toStringForClass(VawsWirtschaftszweige clazz) {
        return clazz.toGuiString();
    }

    /* ********************************************************************** */
    /* toStrings for package TIPI                                             */
    /* ********************************************************************** */

    /** @return AuikWzCode.toGuiString() */
    public static String toStringForClass(AuikWzCode clazz) {
        return clazz.toGuiString();
    }
}
