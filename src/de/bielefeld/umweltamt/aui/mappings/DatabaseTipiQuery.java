/**
 * Copyright 2005-2042, Stadt Bielefeld
 *
 * clazz file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
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

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.tipi.AuikWzCode;
import de.bielefeld.umweltamt.aui.mappings.tipi.DeaAdresse;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnfallstAnlage;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnfallstMessst;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnfallstStoffe;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnfallstelle;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnlage;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetrieb;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetriebseinrichtung;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaGenehmigung;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaMessstAnlage;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaMessstelle;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaProbenahme;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaUebergabestelle;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaUeberwachErgebnis;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaUeberwachungswert;
import de.nrw.lds.tipi.general.HistoryObject;
import de.nrw.lds.tipi.inka.Dea_Adresse;
import de.nrw.lds.tipi.inka.Inka_Anfallst_Anlage;
import de.nrw.lds.tipi.inka.Inka_Anfallst_Messst;
import de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe;
import de.nrw.lds.tipi.inka.Inka_Anfallstelle;
import de.nrw.lds.tipi.inka.Inka_Anlage;
import de.nrw.lds.tipi.inka.Inka_Betrieb;
import de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung;
import de.nrw.lds.tipi.inka.Inka_Genehmigung;
import de.nrw.lds.tipi.inka.Inka_Messst_Anlage;
import de.nrw.lds.tipi.inka.Inka_Messstelle;
import de.nrw.lds.tipi.inka.Inka_Probenahme;
import de.nrw.lds.tipi.inka.Inka_Uebergabestelle;
import de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis;
import de.nrw.lds.tipi.inka.Inka_Ueberwachungswert;

/**
 * This is a service class for the toServiceType method of the tipi classes.<br>
 * <br>
 * This is not in its respective classes because we want to keep them as clean
 * of not-generated code as possible.
 * This may not be the best solution... :-/<br><br>
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseTipiQuery {

    /**
     * Get an array of the WZ-Codes which are marked for the Kurzauswahl
     * @return <code>AuikWzCode[]</code>
     */
    public static AuikWzCode[] getAuikWzCodesInKurzAuswahl() {
        return new DatabaseAccess().executeCriteriaToArray(
            DetachedCriteria.forClass(AuikWzCode.class)
                .add(Restrictions.eq("inKurzAuswahl", true))
                .addOrder(Order.asc("bezeichnung")),
            new AuikWzCode[0]);
    }

    /** DeaAdresse => Dea_Adresse */
    public static HistoryObject toServiceTypeForClass(DeaAdresse clazz) {
        return new Dea_Adresse(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getAdresseNr(),
            clazz.getAdresseVer(),
            clazz.getEmail(),
            clazz.getFax(),
            clazz.getHausnr(),
            clazz.getName1(),
            clazz.getName2(),
            clazz.getOrt(),
            clazz.getPlz(),
            clazz.getStaatskennung(),
            clazz.getStrasse(),
            clazz.getTelefon(),
            clazz.getTelefonMobil()
        );
    }

    /** InkaAnfallstAnlage => Inka_Anfallst_Anlage */
    public static HistoryObject toServiceTypeForClass(InkaAnfallstAnlage clazz) {
        return new Inka_Anfallst_Anlage(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getAnfallstAnlageVer(),
            clazz.getId().getAnfallstelleNr(),
            clazz.getAnfallstelleVer(),
            clazz.getId().getAnlageNr(),
            clazz.getAnlageVer()
        );
    }

    /** InkaAnfallstelle => Inka_Anfallstelle */
    public static HistoryObject toServiceTypeForClass(InkaAnfallstelle clazz) {
        return new Inka_Anfallstelle(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//          clazz.geclazztorienNr(),
            clazz.getAnfallstelleNr(),
            clazz.getAnfallstelleVer(),
            clazz.getAnhId(),
            clazz.getAnhVer(),
            clazz.getBeschreibung(),
            clazz.getBetriebseinrichtungNr(),
            clazz.getBetriebseinrichtungVer(),
            clazz.getChargenbetriebJn(),
            clazz.getDauerbetriebJn(),
            clazz.getGemeindeVer(),
            clazz.getGemeindekennzahl(),
            clazz.getGenehmigungNr(),
            clazz.getGenehmigungVer(),
            ((clazz.getMaxVolTag() == null) ?
                null : new Float(clazz.getMaxVolTag())),
            clazz.getUebergabestelleLfdNr(),
            clazz.getUebergabestelleVer(),
            clazz.getVolJahr()
        );
    }

    /** InkaAnfallstMessst => Inka_Anfallst_Messst */
    public static HistoryObject toServiceTypeForClass(InkaAnfallstMessst clazz) {
        return new Inka_Anfallst_Messst(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getAnfallstMessstVer(),
            clazz.getId().getAnfallstelleNr(),
            clazz.getAnfallstelleVer(),
            clazz.getGemeindeVer(),
            clazz.getGemeindekennzahl(),
            clazz.getId().getMessstelleLfdNr(),
            clazz.getMessstelleVer(),
            clazz.getUebergabestelleLfdNr(),
            clazz.getUebergabestelleVer()
        );
    }

    /** InkaAnfallstStoffe => Inka_Anfallst_Stoffe */
    public static HistoryObject toServiceTypeForClass(InkaAnfallstStoffe clazz) {
        return new Inka_Anfallst_Stoffe(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getAnfallstStoffeVer(),
            clazz.getId().getAnfallstelleNr(),
            clazz.getAnfallstelleVer(),
            clazz.getId().getStoffNr(),
            clazz.getStoffVer()
        );
    }

    /** InkaAnlage => Inka_Anlage */
    public static HistoryObject toServiceTypeForClass(InkaAnlage clazz) {
        return new Inka_Anlage(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getAbfuhrUnbehJn(),
            clazz.getAbscheiderJn(),
            clazz.getAbsorptionJn(),
            clazz.getAdresseAnsprNr(),
            clazz.getAdresseAnsprVer(),
            clazz.getAktenzeichen(),
            clazz.getAnaerobeVorbJn(),
            clazz.getAnlBauaufsZulJn(),
            clazz.getAnlBaurechtPrJn(),
            clazz.getAnlDinDeJn(),
            clazz.getAnlEinzelabnJn(),
            clazz.getAnlOhneZulJn(),
            clazz.getAnlageNr(),
            clazz.getAnlageVer(),
            clazz.getAusgleichsbeckenJn(),
            clazz.getBefristungBis(),
            clazz.getBefristungJn(),
            clazz.getBehoerdenId(),
            clazz.getBehoerdenVer(),
            clazz.getBelCEliJn(),
            clazz.getBelNEliJn(),
            clazz.getBetriebseinrichtungNr(),
            clazz.getBetriebseinrichtungVer(),
            clazz.getBiologPEliJn(),
            clazz.getDatGenehmigung(),
            clazz.getDatInbetrieb(),
            clazz.getEmulsionsspaltJn(),
            clazz.getExtraktionJn(),
            clazz.getFiltrationJn(),
            clazz.getFlockungJn(),
            clazz.getFlotationJn(),
            clazz.getGemeindeVer(),
            clazz.getGemeindekennzahl(),
            clazz.getGenArt(),
            clazz.getGenPflichtigJn(),
            clazz.getGenehmigungNr(),
            clazz.getGenehmigungVer(),
            clazz.getIonenaustauschJn(),
            clazz.getKammerfilterpJn(),
            clazz.getMaschEntwJn(),
            clazz.getMembranfiltrationJn(),
            clazz.getNachklaerungJn(),
            clazz.getNatEntwJn(),
            clazz.getNeutralisationJn(),
            clazz.getSchlammfangJn(),
            clazz.getSiebRechenJn(),
            clazz.getSiebbandpJn(),
            clazz.getSonstAbscheiderJn(),
            clazz.getSonstigeBiolVerf(),
            clazz.getSonstigeMechVerf(),
            clazz.getSonstigeSchlammBeh(),
            clazz.getStatEntwJn(),
            clazz.getStrippAnlageJn(),
            clazz.getStuaBezirk(),
            clazz.getStuaVer(),
            clazz.getTropfkoerperJn(),
            clazz.getUebergabestelleLfdNr(),
            clazz.getUebergabestelleVer(),
            clazz.getVakuumfilterJn(),
            clazz.getVorklaerungAbsetzJn(),
            clazz.getWasBehoerdenId(),
            clazz.getWasBehoerdenVer(),
            clazz.getZentrifugeJn()
        );
    }

    /** InkaBetrieb => Inka_Betrieb */
    public static HistoryObject toServiceTypeForClass(InkaBetrieb clazz) {
        return new Inka_Betrieb(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getAdresseAnsprNr(),
            clazz.getAdresseAnsprVer(),
            clazz.getAdresseEinleitNr(),
            clazz.getAdresseEinleitVer(),
            clazz.getAdresseStandNr(),
            clazz.getAdresseStandVer(),
            clazz.getBetriebNr(),
            clazz.getBetriebVer(),
            clazz.getGemeindeVer(),
            clazz.getGemeindekennzahl()
        );
    }

    /** InkaBetriebseinrichtung => Inka_Betriebseinrichtung */
    public static HistoryObject toServiceTypeForClass(InkaBetriebseinrichtung clazz) {
        return new Inka_Betriebseinrichtung(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getAdresseAnsprNr(),
            clazz.getAdresseAnsprVer(),
            clazz.getAdresseBetreibNr(),
            clazz.getAdresseBetreibVer(),
            clazz.getArbeitsstaetteSeqNr(),
            clazz.getArbeitsstaetteVer(),
            clazz.getBetriebNr(),
            clazz.getBetriebVer(),
            clazz.getBetriebseinrichtungNr(),
            clazz.getBetriebseinrichtungVer(),
            clazz.getGenehmigungNr(),
            clazz.getGenehmigungVer(),
            clazz.getStilllegungDatum(),
            clazz.getStilllegungJn(),
            clazz.getWzCode(),
            clazz.getWzCodeVer()
        );
    }

    /** InkaGenehmigung => Inka_Genehmigung */
    public static HistoryObject toServiceTypeForClass(InkaGenehmigung clazz) {
        return new Inka_Genehmigung(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getBefristetBis(),
            clazz.getBefristetJn(),
            clazz.getBehoerdenId(),
            clazz.getBehoerdenVer(),
            clazz.getBetriebNr(),
            clazz.getBetriebVer(),
            clazz.getGenehmigungDatum(),
            clazz.getGenehmigungNr(),
            clazz.getGenehmigungVer()
        );
    }

    /** InkaMessstAnlage => Inka_Messst_Anlage */
    public static HistoryObject toServiceTypeForClass(InkaMessstAnlage clazz) {
        return new Inka_Messst_Anlage(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getId().getAnlageNr(),
            clazz.getAnlageVer(),
            clazz.getGemeindeVer(),
            clazz.getGemeindekennzahl(),
            clazz.getMessstAnlageVer(),
            clazz.getId().getMessstelleLfdNr(),
            clazz.getMessstelleVer(),
            clazz.getUebergabestelleLfdNr(),
            clazz.getUebergabestelleVer()
        );
    }

    /** InkaMessstelle => Inka_Messstelle */
    public static HistoryObject toServiceTypeForClass(InkaMessstelle clazz) {
        return new Inka_Messstelle(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getBeschrMesspunkt(),
            clazz.getGemeindeVer(),
            clazz.getGemeindekennzahl(),
            clazz.getGenehmigungNr(),
            clazz.getGenehmigungVer(),
            clazz.getMessstelleLfdNr(),
            clazz.getMessstelleTyp(),
            clazz.getMessstelleVer(),
            clazz.getRelevantSumFrachtJn(),
            clazz.getUebergabestelleLfdNr(),
            clazz.getUebergabestelleVer()
        );
    }

    /** InkaProbenahme => Inka_Probenahme */
    public static HistoryObject toServiceTypeForClass(InkaProbenahme clazz) {
        return new Inka_Probenahme(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getDatumAnalyse(),
            clazz.getDurchflussmessungJn(),
            clazz.getGemeindeVer(),
            clazz.getGemeindekennzahl(),
            clazz.getMessstelleLfdNr(),
            clazz.getMessstelleVer(),
            clazz.getProbSchluessel(),
            clazz.getProbVer(),
            clazz.getProbeNr(),
            clazz.getProbenahmeNr(),
            clazz.getProbenahmeVer(),
            ((clazz.getQ05h() == null) ? null : new Float(clazz.getQ05h())),
            ((clazz.getQ2h() == null) ?  null : new Float(clazz.getQ2h())),
            clazz.getRegistrierungJn(),
            clazz.getSelbstueberwJn(),
            clazz.getUebergabestelleLfdNr(),
            clazz.getUebergabestelleVer()
        );
    }

    /** InkaUebergabestelle => Inka_Uebergabestelle */
    public static HistoryObject toServiceTypeForClass(InkaUebergabestelle clazz) {
        return new Inka_Uebergabestelle(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//          clazz.geclazztorienNr(),
            clazz.getBetriebNr(),
            clazz.getBetriebVer(),
            clazz.getGemeindeVer(),
            clazz.getGemeindekennzahl(),
            clazz.getGenehmigungNr(),
            clazz.getGenehmigungVer(),
            clazz.getN32(),
            clazz.getKanalArt(),
            clazz.getKartennummer(),
            clazz.getAnlagenNr(),
            clazz.getKlaeranlagenVer(),
            clazz.getE32(),
            clazz.getTk25Ver(),
            clazz.getUebergabestelleLfdNr(),
            clazz.getUebergabestelleVer()
        );
    }

    /** InkaUeberwachErgebnis => Inka_Ueberwach_Ergebnis */
    public static HistoryObject toServiceTypeForClass(InkaUeberwachErgebnis clazz) {
        return new Inka_Ueberwach_Ergebnis(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getEinMasseinheitNr(),
            clazz.getEinMasseinheitVer(),
            ((clazz.getMessergebnis() == null) ?
                null : new Float(clazz.getMessergebnis())),
            clazz.getMessergebnisText(),
            clazz.getId().getParameterNr(),
            clazz.getParameterVer(),
            clazz.getId().getProbenahmeNr(),
            clazz.getProbenahmeVer(),
            clazz.getUeberwachErgebnisVer()
        );
    }

    /** InkaUeberwachungswert => Inka_Ueberwachungswert */
    public static HistoryObject toServiceTypeForClass(InkaUeberwachungswert clazz) {
        return new Inka_Ueberwachungswert(
            clazz.getAenderungsDatum(),
            clazz.getErfassungsDatum(),
            clazz.getGueltigBis(),
            clazz.getGueltigVon(),
            clazz.getIstAktuellJn(),
//            clazz.geclazztorienNr(),
            clazz.getAnzJahr(),
            clazz.getGemeindeVer(),
            clazz.getGemeindekennzahl(),
            clazz.getGenehmigungNr(),
            clazz.getGenehmigungVer(),
            ((clazz.getJahresfracht() == null) ?
                null : new Float(clazz.getJahresfracht())),
            clazz.getId().getMessstelleLfdNr(),
            clazz.getMessstelleVer(),
            clazz.getId().getParameterNr(),
            clazz.getParameterVer(),
            clazz.getSelbstAmtlJn(),
            clazz.getUebergabestelleLfdNr(),
            clazz.getUebergabestelleVer(),
            clazz.getUeberwachungswertVer(),
            ((clazz.getUewert() == null) ? null : new Float(clazz.getUewert())),
            ((clazz.getUewertObergr() == null) ?
                null:new Float(clazz.getUewertObergr())),
            ((clazz.getUewertUntergr() == null) ?
                null:new Float(clazz.getUewertUntergr()))
        );
    }

}
