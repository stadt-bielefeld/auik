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

import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.atl.MetaParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Parametergruppen;
import de.bielefeld.umweltamt.aui.mappings.atl.Probeart;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.atl.Sielhaut;
import de.bielefeld.umweltamt.aui.mappings.atl.Status;
import de.bielefeld.umweltamt.aui.mappings.atl.ViewAtlAnalysepositionAll;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Bezeichnung;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektchrono;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.Onlinekartendienst;
import de.bielefeld.umweltamt.aui.mappings.basis.OnlinekartendienstId;
import de.bielefeld.umweltamt.aui.mappings.basis.Orte;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.Lage;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.mappings.basis.Strassen;
import de.bielefeld.umweltamt.aui.mappings.basis.TabStreets;
import de.bielefeld.umweltamt.aui.mappings.basis.ViewTwoWayObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.ViewTwoWayObjektverknuepfungId;
import de.bielefeld.umweltamt.aui.mappings.basis.Prioritaet;
import de.bielefeld.umweltamt.aui.mappings.basis.PrioritaetId;
import de.bielefeld.umweltamt.aui.mappings.elka.Aba;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Anhang;
import de.bielefeld.umweltamt.aui.mappings.elka.Einleitungsstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaAnhang;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaEinheit;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaStoff;
import de.bielefeld.umweltamt.aui.mappings.elka.Referenz;
import de.bielefeld.umweltamt.aui.mappings.elka.Wasserrecht;
import de.bielefeld.umweltamt.aui.mappings.elka.ZElsWasserrecht;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAbwasserbehandlungsanlage;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAdresse;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAnfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EBetrieb;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EEinleitungsstelle;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EEntwaesserungsgrundstueck;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EMessstelle;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EProbenahme;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EProbenahmeUeberwachungsergeb;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.ESonderbauwerk;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EStandort;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EWasserrecht;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EZElsWasserrecht;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abfuhr;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Analysen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Kontrollen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Ortstermine;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Verwaltungsverf;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh53Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh55Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.BwkFachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Entsorger;
import de.bielefeld.umweltamt.aui.mappings.indeinl.SuevFachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.ViewBwk;
import de.bielefeld.umweltamt.aui.mappings.indeinl.ViewBwkId;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.AfsNiederschlagswasser;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.AfsStoffe;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Entwaesserungsgrundstueck;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Massnahme;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.SbEntlastung;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Versickerungsanlage;
import de.bielefeld.umweltamt.aui.mappings.awsv.Abfuellflaeche;
import de.bielefeld.umweltamt.aui.mappings.awsv.Abscheider;
import de.bielefeld.umweltamt.aui.mappings.awsv.Anlagenarten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Anlagenchrono;
import de.bielefeld.umweltamt.aui.mappings.awsv.Behaelterart;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fluessigkeit;
import de.bielefeld.umweltamt.aui.mappings.awsv.Gebuehrenarten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Gefaehrdungsstufen;
import de.bielefeld.umweltamt.aui.mappings.awsv.Jgs;
import de.bielefeld.umweltamt.aui.mappings.awsv.Kontrollen;
import de.bielefeld.umweltamt.aui.mappings.awsv.Material;
import de.bielefeld.umweltamt.aui.mappings.awsv.Pruefer;
import de.bielefeld.umweltamt.aui.mappings.awsv.Pruefergebniss;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Vbfeinstufung;
import de.bielefeld.umweltamt.aui.mappings.awsv.Verwaltungsgebuehren;
import de.bielefeld.umweltamt.aui.mappings.awsv.Verwaltungsverf;
import de.bielefeld.umweltamt.aui.mappings.awsv.Verwmassnahmen;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.mappings.basis.Wirtschaftszweig;
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
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
public class DatabaseClassToString {

    /* ********************************************************************** */
    /* toStrings for package BASIC                                            */
    /* ********************************************************************** */

    /**
     * Custom Adresse.toString()
     * Liefert einen String der Form "Name, Zusatz, Straße, Hausnummer und Hausnrzusatz"
     * falls ein Zusatz vorhanden ist.
     */
    public static String toStringForClass(Adresse clazz) {
        String zusatz = "";
        String vorname = "";
        String hausnrzus = "";
        
        if (clazz.getBetrvorname() != null) {
        	vorname = clazz.getBetrvorname() + " ";
        } else if (clazz.getBetrnamezus() != null) {
            zusatz = ", " + clazz.getBetrnamezus();
        } else if (clazz.getHausnrzus() != null) {
        	hausnrzus = clazz.getHausnrzus();
        }       
        return vorname + clazz.getBetrname() + zusatz + ", " + clazz.getStrasse()
        	+ " " + clazz.getHausnr() + hausnrzus;
    }
    
    /**
     * Custom Standort.toString()
     * Liefert einen String der Form "Straße, Hausnummer, Zusatz" falls 
     * der Standort eine Adresse besitzt.
     */
	public static String toStringForClass(Standort clazz) {
		String hausnrzus = "";

		if (clazz.getAdresse() != null) {
			if (clazz.getAdresse().getHausnrzus() != null) {
				hausnrzus = clazz.getAdresse().getHausnrzus();
			}
			return clazz.getAdresse().getStrasse() + " " + clazz.getAdresse().getHausnr() + hausnrzus;
		} else {
			return "Dieser Standort hat keine Adresse";
		}
	}

	/** @return Gemarkung.toGuiString() */
    public static String toStringForClass(Gemarkung clazz) {
        return clazz.toGuiString();
    }


    /**
     * Custom Objekt.toString()
     * Liefert einen String, der dieses Objekt beschreibt.
     * @return Einen String in der Form
     *         "[ID:Objekt-ID, Betr.:Adresse, Stdort:BasisStandort, Art:Objektart]"
     */
    public static String toStringForClass(Objekt clazz) {
        return "[ID:" + clazz.getId() +
            ", Betr.:" + clazz.getBetreiberid() +
            ", Stdort:" + clazz.getStandortid() +
            ", Art:" + clazz.getObjektarten() + "]";
    }

    /** Custom Objektarten.toString() */
    public static String toStringForClass(Objektarten clazz) {
        return clazz.getObjektart()
            + (clazz.getAbteilung() != null ?
                " (" + clazz.getAbteilung() + ")" : "");
    }
	
	/** Custom Bezeichnung.toString() */
	public static String toStringForClass(Bezeichnung clazz) {
		return "[ID:" + clazz.getId()
				+ ", Gruppe:" + clazz.getGruppe()
				+ ", Bezeichnung:" + clazz.getBezeichnung();
	}

    /**
     * Custom Objektchrono.toString()
     * Liefert einen String der Form "BehaelterID: Anlagenart Herstellnr".
     */
    public static String toStringForClass(Objektchrono clazz) {
        return clazz.getId() + ": "
            + (clazz.getDatum() != null ? clazz.getDatum() + " " : "")
            + (clazz.getSachverhalt() != null ? clazz.getSachverhalt() : "");
    }

    /**
     * Custom Objektverknuepfung.toString()
     * Liefert einen String mit der ID.
     */
    public static String toStringForClass(Objektverknuepfung clazz) {
        return clazz.getId() + ": ";
    }

    /** @return Custom Sachbearbeiter.toString() */
    public static String toStringForClass(Sachbearbeiter clazz) {
        String name = clazz.getName();
        String kennnummer = clazz.getKennummer();

        return ((name != null) ? name + " (" + kennnummer + ")" : kennnummer);
    }

    /**
     * Custom BasisLage.toString()<br>
     * Liefert die komplette Strassen, wenn vorhanden inklusive der Hausnummer
     * und deren Zusatz.<br>
     * <br>
     * Formatierung: &quot;&lt;Strassen&gt; &lt;HausNr&gt;&lt;HausNrzus&gt;&quot;<br>
     * <br>
     * Beispiele: &quot;Ravensberger Straße 77&quot;, &quot;Apfelstraße
     * 23b&quot;, &quot;Jahnplatz 41-42&quot;
     * @return Komplette, formatierte Strassen inkl. Hausnr
     */
    public static String toStringForClass(Lage clazz) {
        return "" 
            + (clazz.getEntgebid() != null ? "" + clazz.getEntgebid() : "")
            + (clazz.getWassereinzugsgebiet() != null ? ", " + clazz.getWassereinzugsgebiet() : "")
            + (clazz.getE32() != null ? ", " + clazz.getE32() : "")
            + (clazz.getN32() != null ? ", " + clazz.getN32() : "");
    }

    /** @return Strassen.toGuiString() */
    public static String toStringForClass(Strassen clazz) {
        return clazz.toGuiString();
    }



    /* ********************************************************************** */
    /* toStrings for package ATL                                              */
    /* ********************************************************************** */

    /**
     * Custom Analyseposition.toString()
     * @return Einen String der Form
     *         "[Position: Parameter: Wert Einheit, Analyse_Von, [Probenahme], ID:Id]"
     */
    public static String toStringForClass(Analyseposition clazz) {
        return "[Position: " + clazz.getParameter() + ": "
            + clazz.getWert() + " " + clazz.getEinheiten() + ", "
            + clazz.getAnalyseVon() + ", "
            + (clazz.getProbenahme() != null ?
                clazz.getProbenahme() + ", " : "")
            + (clazz.getId() != null ?
                "ID:" + clazz.getId() : "UNSAVED")
            + "]";
    }

    /** @return Einheiten.toGuiString() */
    public static String toStringForClass(Einheiten clazz) {
        return clazz.toGuiString();
    }

    /** @return Klaeranlage.toGuiString() */
    public static String toStringForClass(Klaeranlage clazz) {
        return clazz.toGuiString();
    }

    /** @return Parameter.toGuiString() */
    public static String toStringForClass(Parameter clazz) {
        return clazz.toGuiString();
    }

    /** @return MetaParameter.toDebugString() */
    public static String toStringForClass(MetaParameter clazz) {
        return clazz.toDebugString();
    }

    /** Not used so far */
    public static String toStringForClass(Parametergruppen clazz) {
        return null;
    }

    /** @return Probeart.toGuiString() */
    public static String toStringForClass(Probeart clazz) {
        return clazz.toGuiString();
    }

    /**
     * Custom Probenahme.toString()
     * @return Einen String der Form
     *         "[Probe: Kennummer, Probeart, Datum, Anz.Positionen]" bzw.
     *         "[Probe: Kennummer, Probeart, Datum, N/A]" falls die Positionen
     *         noch nicht aus der Datenbank geholt wurden.
     */
    public static String toStringForClass(Probenahme clazz) {
        return "[Probe: " + clazz.getKennummer() + ", "
            + clazz.getMessstelle().getProbeart() + ", "
            + AuikUtils.getStringFromDate(clazz.getDatumDerEntnahme())
            + (clazz.getZeitDerEntnahmen() != null ?
                " " + clazz.getZeitDerEntnahmen() : "")
            + ", "
            + (DatabaseAccess.isInitialized(
                clazz.getAnalysepositions()) ?
                    clazz.getAnalysepositions().size() : "N/A")
            + "]";
    }

    /** @return Custom Messstelle.toString() */
    public static String toStringForClass(Messstelle clazz) {
        return "[Probepunkt:" + clazz.getId() +
            ", Art:" + clazz.getProbeart() +
            ", Nr:" + clazz.getNrProbepkt() + "]";
    }

    /** @return Custom Sielhaut.toString() */
    public static String toStringForClass(Sielhaut clazz) {
        return "[SielhautBearbeiten:" + clazz.getId() + ", "
            + clazz.getBezeichnung() + "]";
    }

    /** @return Status.toGuiString() */
    public static String toStringForClass(Status clazz) {
        return clazz.toGuiString();
    }

    /* ********************************************************************** */
    /* toStrings for package INDEINL                                          */
    /* ********************************************************************** */

    /**
     * @return Custom Anh40Fachdaten.toString()
     * Liefert einen String der Form "[Anhang 40:ID]"
     */
    public static String toStringForClass(Anh40Fachdaten clazz) {
        return "[Anhang 40:" + clazz.getObjekt() + "]";
    }

    /**
     * @return Custom Anh49Abscheiderdetails.toString()
     * Liefert einen String der Form "[ID:ID, NR von VON, LAGE]".
     */
    public static String toStringForClass(Anh49Abscheiderdetails clazz) {
        return "[ID:" + clazz.getId() + ", " + clazz.getAbscheidernr()
            + " von " + clazz.getVon() + ", " + clazz.getLage() + "]";
    }

    /**
     * @return Custom Anh49Analysen.toString()
     * Liefert einen String der Form "[Datum: DATUM und ANALYSENID]".
     */
    public static String toStringForClass(Anh49Analysen clazz) {
        return "[Datum:" + clazz.getDatum() + ", " + clazz.getId() + "]";
    }

    /** @return Custom Anh49Fachdaten.toString() */
    public static String toStringForClass(Anh49Fachdaten clazz) {
        return "[Anh49:" + clazz.getObjekt() + "]";
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
     * @return Custom Anh49Abfuhr.toString()
     * Liefert einen String der Form "[Datum: Abfuhrdatum und Entsorger]".
     */
    public static String toStringForClass(Anh49Abfuhr clazz) {
        return "[Abfuhrdatum: "
            + AuikUtils.getStringFromDate(clazz.getAbfuhrdatum())
            + ", Entsorger: " + clazz.getEntsorger() + "]";
    }

    /**
     * @return Custom Anh49Ortstermine.toString()
     * Liefert einen String der Form "[Datum: DATUM und SACHBEARBEITER]".
     */
    public static String toStringForClass(Anh49Ortstermine clazz) {
        return "[Datum: " + AuikUtils.getStringFromDate(clazz.getDatum())
            + ", SachbearbeiterIn: " + clazz.getSachbearbeiterIn() + "]";
    }

    /** @return Custom Anh49Verwaltungsverf.toString() */
    public static String toStringForClass(Anh49Verwaltungsverf clazz) {
        return "[Datum: " + AuikUtils.getStringFromDate(clazz.getDatum())
            + ", Maßnahme: " + clazz.getMassnahme() + " ("
            + ((clazz.getAbgeschlossen() != null && clazz.getAbgeschlossen()) ?
                "" : "nicht ") + "abgeschlossen)"
            + ", SachbearbeiterIn: " + clazz.getSachbearbeiterIn()
            + (clazz.getWiedervorlage() == null ? "" : ", Wiedervorlage: "
            + AuikUtils.getStringFromDate(clazz.getWiedervorlage())) + "]";
    }

    /** @return Custom Anh50Fachdaten.toString() */
    public static String toStringForClass(Anh50Fachdaten clazz) {
        return "[ID:" + clazz.getId() + "]";
    }

    /** @return Custom Anh52Fachdaten.toString() */
    public static String toStringForClass(Anh52Fachdaten clazz) {
        return "[ID:" + clazz.getId() + "]";
    }

    /** @return Custom Anh53Fachdaten.toString() */
    public static String toStringForClass(Anh53Fachdaten clazz) {
        return "[ID:" + clazz.getId() + "]";
    }

    /** @return Custom Anh55Fachdaten.toString() */
    public static String toStringForClass(Anh55Fachdaten clazz) {
        return "[ID:" + clazz.getId() + "]";
    }

    /** @return Custom Anh56Fachdaten.toString() */
    public static String toStringForClass(Anh56Fachdaten clazz) {
        return "[ID:" + clazz.getId() + "]";
    }

    /**
     * @return Custom BwkFachdaten.toString()
     * Liefert einen String der Form "[BWK:ID, Hersteller Typ]"
     */
    public static String toStringForClass(BwkFachdaten clazz) {
        return "[BWK:" + clazz.getId() + ", "
            + clazz.getKHersteller() + " " + clazz.getKTyp() + "]";
    }

    /** @return Entsorger.toGuiString() */
    public static String toStringForClass(Entsorger clazz) {
        return clazz.getEntsorger();
    }

    /**
     * @return Custom SuevFachdaten.toString()
     * Liefert einen String der Form "[SuevKan Verfahren:ID]"
     */
    public static String toStringForClass(SuevFachdaten clazz) {
        return "[SuevKan Verfahren:" + clazz.getId() + "]";
    }

    /**
     * @return Custom Wasserrecht.toString()
     * Liefert einen String der Form "[Wasserrecht:ID]"
     */
    public static String toStringForClass(Wasserrecht clazz) {
        return "[Wasserrecht:" + clazz.getId() + "]";
    }

    /**
     * @return Custom Aba.toString()
     * Liefert einen String der Form "[Abwasserbehandlungsanlage:ID]"
     */
    public static String toStringForClass(Aba clazz) {
        return "[Abwasserbehandlungsanlage:ID:" + clazz.getId() + "]";
    }

    /**
     * @return Custom Abaverfahren.toString()
     * Liefert einen String der Form clazz.toGuiString()
     */
    public static String toStringForClass(Abaverfahren clazz) {
        return clazz.toGuiString();
    }

    /**
     * @return Custom Anfallstelle.toString()
     * Liefert einen String der Form "[Anfallstelle:ID]"
     */
    public static String toStringForClass(Anfallstelle clazz) {
        return "[Anfallstelle:ID:" + clazz.getId() + "]";
    }

    /**
     * @return Custom Einleitungsstelle.toString()
     * Liefert einen String der Form "[Einleitungsstelle:ID]"
     */
    public static String toStringForClass(Einleitungsstelle clazz) {
        return "[Einleitungsstelle:" + clazz.getId() + "]";
    }

    /* ********************************************************************** */
    /* toStrings for package VAWS                                             */
    /* ********************************************************************** */

    /** @return Custom Abfuellflaeche.toString() */
    public static String toStringForClass(Abfuellflaeche clazz) {
        return "[Abfuellflaeche: " + clazz.getId()
            + ", FD:" + clazz.getFachdaten() + "]";
    }

    /** @return Custom Abscheider.toString() */
    public static String toStringForClass(Abscheider clazz) {
        return "[Abscheider: " + clazz.getId()
            + ", FD:" + clazz.getFachdaten() + "]";
    }

    /** @return "Anlagenarten.toGuiString() */
    public static String toStringForClass(Anlagenarten clazz) {
        return clazz.toGuiString();
    }

    /** @return "Anlagenchrono.toDebugString() */
    public static String toStringForClass(Anlagenchrono clazz) {
        return clazz.toDebugString();
    }

    /** @return "Behaelterart.toGuiString() */
    public static String toStringForClass(Behaelterart clazz) {
        return clazz.toGuiString();
    }

    /**
     * @return Custom Fachdaten.toString()
     * Liefert einen String der Form "BehaelterID: Anlagenart Herstellnr".
     */
    public static String toStringForClass(Fachdaten clazz) {
        return clazz.getBehaelterid() + ": "
            + (clazz.getAnlagenart() != null ? clazz.getAnlagenart() + " " : "")
            + (clazz.getHerstellnr() != null ? clazz.getHerstellnr() : "");
    }

    /** @return "Fluessigkeit.toGuiString() */
    public static String toStringForClass(Fluessigkeit clazz) {
        return clazz.toGuiString();
    }

    /** @return "Gebuehrenarten.toGuiString() */
    public static String toStringForClass(Gebuehrenarten clazz) {
        return clazz.getGebuehrenart();
    }

    /** @return "Gefaehrdungsstufen.toGuiString() */
    public static String toStringForClass(Gefaehrdungsstufen clazz) {
        return clazz.toGuiString();
    }

    /** @return Custom Abscheider.toString() */
    public static String toStringForClass(Jgs clazz) {
        return "[Jgs: " + clazz.getId()
            + ", FD:" + clazz.getFachdaten() + "]";
    }

    /** @return "Kontrollen.toDebugString() */
    public static String toStringForClass(Kontrollen clazz) {
        return clazz.toDebugString();
    }

    /** @return "Material.toGuiString() */
    public static String toStringForClass(Material clazz) {
        return clazz.toGuiString();
    }

    /** @return "Pruefer.toGuiString() */
    public static String toStringForClass(Pruefer clazz) {
        return clazz.toGuiString();
    }

    /** @return "Pruefergebniss.toGuiString() */
    public static String toStringForClass(Pruefergebniss clazz) {
        return clazz.toGuiString();
    }

    /** @return "Standortgghwsg.toGuiString() */
    public static String toStringForClass(Standortgghwsg clazz) {
        return clazz.toGuiString();
    }

    /** @return "Vbfeinstufung.toGuiString() */
    public static String toStringForClass(Vbfeinstufung clazz) {
        return clazz.toGuiString();
    }

    /** @return "Verwaltungsgebuehren.toDebugString() */
    public static String toStringForClass(Verwaltungsgebuehren clazz) {
        return clazz.toDebugString();
    }

    /** @return "Verwaltungsverf.toDebugString() */
    public static String toStringForClass(Verwaltungsverf clazz) {
        return clazz.toDebugString();
    }

    /** @return "Verwmassnahmen.toGuiString() */
    public static String toStringForClass(Verwmassnahmen clazz) {
        return clazz.toGuiString();
    }

    /** @return "Wassereinzugsgebiet.toGuiString() */
    public static String toStringForClass(Wassereinzugsgebiet clazz) {
        return clazz.toGuiString();
    }

    /** @return "Wirtschaftszweig.toGuiString() */
    public static String toStringForClass(Wirtschaftszweig clazz) {
        return clazz.toGuiString();
    }

	/** TODO: */
	public static String toStringForClass(Prioritaet clazz) {
		return clazz.getPrioritaet().toString();
	}

	public static String toStringForClass(PrioritaetId clazz) {
		return clazz.getStandortId().toString();
	}

	public static String toStringForClass(Onlinekartendienst onlinekartendienst) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(OnlinekartendienstId onlinekartendienstId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(ViewTwoWayObjektverknuepfung viewTwoWayObjektverknuepfung) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(ViewTwoWayObjektverknuepfungId viewTwoWayObjektverknuepfungId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EAbwasserbehandlungsanlage eAbwasserbehandlungsanlage) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EAdresse eAdresse) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EAnfallstelle eAnfallstelle) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EBetrieb eBetrieb) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EEinleitungsstelle eEinleitungsstelle) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EEntwaesserungsgrundstueck eEntwaesserungsgrundstueck) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String toStringForClass(EMessstelle eMessstelle) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EProbenahme eProbenahme) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EProbenahmeUeberwachungsergeb eProbenahmeUeberwachungsergeb) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(ESonderbauwerk eSonderbauwerk) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EStandort eStandort) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EWasserrecht eWasserrecht) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(MapElkaAnhang mapElkaAnhang) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(MapElkaEinheit mapElkaEinheit) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(MapElkaStoff mapElkaStoff) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(Referenz referenz) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(ViewBwk viewBwk) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(ViewBwkId viewBwkId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(AfsNiederschlagswasser afsNiederschlagswasser) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(AfsStoffe afsStoffe) {
		// TODO Auto-generated method stub
		return null;
	}

    public static String toStringForClass(Entwaesserungsgrundstueck entwaesserungsgrundstueck) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(Massnahme massnahme) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(SbEntlastung sbEntlastung) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(Sonderbauwerk sonderbauwerk) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(Versickerungsanlage versickerungsanlage) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(ViewAtlAnalysepositionAll viewAtlAnalysepositionAll) {
		// TODO Auto-generated method stub
		return null;
	}

    public static String toStringForClass(ZElsWasserrecht zew) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(TabStreets tabStreets) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(Orte orte) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(EZElsWasserrecht ezElsWasserrecht) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String toStringForClass(Anhang clazz) {
        return clazz.toGuiString();
	}
	
}
