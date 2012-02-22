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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.Set;

import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the ANH_49_FACHDATEN table. You can
 * customize the behavior of this class by editing the class, {@link
 * Anh49Fachdaten()}.
 */
public abstract class AbstractAnh49Fachdaten extends
    AbstractVirtuallyDeletableDatabaseTable implements Serializable {
    private static final long serialVersionUID = 3299399797902809292L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private BasisObjekt basisObjekt;

    /** The value of the simple nr property. */
    private java.lang.Integer objektid;

    /** The value of the simple klaeranlage property. */
    private java.lang.String klaeranlage;

    /** The value of the simple name property. */
    private java.lang.String name;

    /** The value of the simple bemerkungen property. */
    private java.lang.String bemerkungen;

    /** The value of the simple planquadrat property. */
    private java.lang.String planquadrat;

    /** The value of the simple abgemeldet property. */
    private java.lang.Boolean abgemeldet;

    /** The value of the simple technikAnh49 property. */
    private java.lang.String technikAnh49;

    /** The value of the simple technikAnh49Nr property. */
    private java.lang.String technikAnh49Nr;

    /** The value of the simple sachkundelfa property. */
    private java.lang.String sachkundelfa;

    /** The value of the simple werkstatt property. */
    private java.lang.Boolean werkstatt;

    /** The value of the simple bodeneinlaeufe property. */
    private java.lang.Boolean bodeneinlaeufe;

    /** The value of the simple waschanlagen property. */
    private java.lang.Boolean waschanlagen;

    /** The value of the simple sonstiges property. */
    private java.lang.String sonstiges;

    /** The value of the simple analysemonat property. */
    private java.lang.String analysemonat;

    /** The value of the simple abwasserfrei property. */
    private java.lang.Boolean abwasserfrei;

    /** The value of the simple dekraTuevTermin property. */
    private java.lang.Integer dekraTuevTermin;

    /** The value of the simple anredeantragst property. */
    private java.lang.String anredeantragst;

    /** The value of the simple nameantragst property. */
    private java.lang.String nameantragst;

    /** The value of the simple zusantragst property. */
    private java.lang.String zusantragst;

    /** The value of the simple strasseantragst property. */
    private java.lang.String strasseantragst;

    /** The value of the simple hausnrantragst property. */
    private java.lang.Integer hausnrantragst;

    /** The value of the simple hausnrzusantragst property. */
    private java.lang.String hausnrzusantragst;

    /** The value of the simple plzantragst property. */
    private java.lang.String plzantragst;

    /** The value of the simple ortantragst property. */
    private java.lang.String ortantragst;

    /** The value of the simple sachbearbeiterIn property. */
    private java.lang.String sachbearbeiterIn;

    /** The value of the simple ansprechpartnerIn property. */
    private java.lang.String ansprechpartnerIn;

    /** The value of the simple antragvom property. */
    private java.util.Date antragvom;

    /** The value of the simple genehmigung property. */
    private java.util.Date genehmigung;

    /** The value of the simple wiedervorlage property. */
    private java.util.Date wiedervorlage;

    /** The value of the simple aenderungsgenehmigung property. */
    private java.util.Date aenderungsgenehmigung;

    /** The value of the simple letztesAnschreiben property. */
    private java.util.Date letztesAnschreiben;

    /** The value of the simple anschreiben property. */
    private java.lang.String anschreiben;

    /** The value of the simple waschanlage property. */
    private java.lang.Boolean waschanlage;

    /** The value of the simple eSatzung property. */
    private java.lang.Boolean eSatzung;

    /** The value of the simple seitwann property. */
    private java.util.Date seitwann;

    /** The value of the simple sonstigestechnik property. */
    private java.lang.String sonstigestechnik;

    /** The value of the simple maengel property. */
    private java.lang.Boolean maengel;

    /** The value of the simple behoben property. */
    private java.lang.Boolean behoben;

    /** The value of the simple frist property. */
    private java.util.Date frist;

    /** The value of the simple durchgefuehrt property. */
    private java.lang.Integer durchgefuehrt;

    /** The value of the simple antragvom property. */
    private java.util.Date dekraTuevDatum;
    /** The value of the Anh49Analyse association. */
    private Set<?> anh49Analysen;

    /**
     * Simple constructor of AbstractAnh49Fachdaten instances.
     */
    public AbstractAnh49Fachdaten() {
    }

    /**
     * Constructor of AbstractAnh49Fachdaten instances given a simple primary
     * key.
     * @param basisObjekt
     */
    public AbstractAnh49Fachdaten(java.lang.Integer getObjektid) {
        this.setObjektid(objektid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return BasisObjekt
     */
    public BasisObjekt getBasisObjekt() {
        return basisObjekt;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param basisObjekt
     */
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.hashValue = 0;
        this.basisObjekt = basisObjekt;
    }

    /**
     * Return the Anh49Analyse
     * @return Set
     */

    public Set<?> getAnh49Analysen() {
        return anh49Analysen;
    }

    /**
     * Set the Anh49Analyse
     * @param Anh49Analyse
     */

    public void setAnh49Analysen(Set<?> anh49Analysen) {
        this.anh49Analysen = anh49Analysen;
    }

    /**
     * Return the value of the dekraTuevDatum column.
     * @return java.util.Date
     */

    public java.util.Date getDekraTuevDatum() {
        return dekraTuevDatum;
    }

    /**
     * Set the value of the dekraTuevDatum column.
     * @param dekraTuevDatum
     */

    public void setDekraTuevDatum(java.util.Date dekraTuevDatum) {
        this.dekraTuevDatum = dekraTuevDatum;
    }

    /**
     * Return the value of the NR column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getObjektid() {
        return this.objektid;
    }

    /**
     * Set the value of the NR column.
     * @param nr
     */
    public void setObjektid(java.lang.Integer objektid) {
        this.objektid = objektid;
    }

    /**
     * Return the value of the KLAERANLAGE column.
     * @return java.lang.String
     */
    public java.lang.String getKlaeranlage() {
        return this.klaeranlage;
    }

    /**
     * Set the value of the KLAERANLAGE column.
     * @param klaeranlage
     */
    public void setKlaeranlage(java.lang.String klaeranlage) {
        this.klaeranlage = klaeranlage;
    }

    /**
     * Return the value of the NAME column.
     * @return java.lang.String
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * Set the value of the NAME column.
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * Return the value of the BEMERKUNGEN column.
     * @return java.lang.String
     */
    public java.lang.String getBemerkungen() {
        return this.bemerkungen;
    }

    /**
     * Set the value of the BEMERKUNGEN column.
     * @param bemerkungen
     */
    public void setBemerkungen(java.lang.String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    /**
     * Return the value of the PLANQUADRAT column.
     * @return java.lang.String
     */
    public java.lang.String getPlanquadrat() {
        return this.planquadrat;
    }

    /**
     * Set the value of the PLANQUADRAT column.
     * @param planquadrat
     */
    public void setPlanquadrat(java.lang.String planquadrat) {
        this.planquadrat = planquadrat;
    }

    /**
     * Return the value of the ABGEMELDET column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getAbgemeldet() {
        return this.abgemeldet;
    }

    /**
     * Set the value of the ABGEMELDET column.
     * @param abgemeldet
     */
    public void setAbgemeldet(java.lang.Boolean abgemeldet) {
        this.abgemeldet = abgemeldet;
    }

    /**
     * Return the value of the TECHNIK_ANH49 column.
     * @return java.lang.String
     */
    public java.lang.String getTechnikAnh49() {
        return this.technikAnh49;
    }

    /**
     * Set the value of the TECHNIK_ANH49 column.
     * @param technikAnh49
     */
    public void setTechnikAnh49(java.lang.String technikAnh49) {
        this.technikAnh49 = technikAnh49;
    }

    /**
     * Return the value of the TECHNIK_ANH49_NR column.
     * @return java.lang.String
     */
    public java.lang.String getTechnikAnh49Nr() {
        return this.technikAnh49Nr;
    }

    /**
     * Set the value of the TECHNIK_ANH49_NR column.
     * @param technikAnh49Nr
     */
    public void setTechnikAnh49Nr(java.lang.String technikAnh49Nr) {
        this.technikAnh49Nr = technikAnh49Nr;
    }

    /**
     * Return the value of the SACHKUNDELFA column.
     * @return java.lang.String
     */
    public java.lang.String getSachkundelfa() {
        return this.sachkundelfa;
    }

    /**
     * Set the value of the SACHKUNDELFA column.
     * @param sachkundelfa
     */
    public void setSachkundelfa(java.lang.String sachkundelfa) {
        this.sachkundelfa = sachkundelfa;
    }

    /**
     * Return the value of the WERKSTATT column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getWerkstatt() {
        return this.werkstatt;
    }

    /**
     * Set the value of the WERKSTATT column.
     * @param werkstatt
     */
    public void setWerkstatt(java.lang.Boolean werkstatt) {
        this.werkstatt = werkstatt;
    }

    /**
     * Return the value of the BODENEINLAEUFE column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getBodeneinlaeufe() {
        return this.bodeneinlaeufe;
    }

    /**
     * Set the value of the BODENEINLAEUFE column.
     * @param bodeneinlaeufe
     */
    public void setBodeneinlaeufe(java.lang.Boolean bodeneinlaeufe) {
        this.bodeneinlaeufe = bodeneinlaeufe;
    }

    /**
     * Return the value of the WASCHANLAGEN column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getWaschanlagen() {
        return this.waschanlagen;
    }

    /**
     * Set the value of the WASCHANLAGEN column.
     * @param waschanlagen
     */
    public void setWaschanlagen(java.lang.Boolean waschanlagen) {
        this.waschanlagen = waschanlagen;
    }

    /**
     * Return the value of the SONSTIGES column.
     * @return java.lang.String
     */
    public java.lang.String getSonstiges() {
        return this.sonstiges;
    }

    /**
     * Set the value of the SONSTIGES column.
     * @param sonstiges
     */
    public void setSonstiges(java.lang.String sonstiges) {
        this.sonstiges = sonstiges;
    }

    /**
     * Return the value of the ANALYSEMONAT column.
     * @return java.lang.String
     */
    public java.lang.String getAnalysemonat() {
        return this.analysemonat;
    }

    /**
     * Set the value of the ANALYSEMONAT column.
     * @param analysemonat
     */
    public void setAnalysemonat(java.lang.String analysemonat) {
        this.analysemonat = analysemonat;
    }

    /**
     * Return the value of the ABWASSERFREI column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getAbwasserfrei() {
        return this.abwasserfrei;
    }

    /**
     * Set the value of the ABWASSERFREI column.
     * @param abwasserfrei
     */
    public void setAbwasserfrei(java.lang.Boolean abwasserfrei) {
        this.abwasserfrei = abwasserfrei;
    }

    /**
     * Return the value of the DEKRA_TUEV_TERMIN column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getDekraTuevTermin() {
        return this.dekraTuevTermin;
    }

    /**
     * Set the value of the DEKRA_TUEV_TERMIN column.
     * @param dekraTuevTermin
     */
    public void setDekraTuevTermin(java.lang.Integer dekraTuevTermin) {
        this.dekraTuevTermin = dekraTuevTermin;
    }

    /**
     * Return the value of the ANREDEANTRAGST column.
     * @return java.lang.String
     */
    public java.lang.String getAnredeantragst() {
        return this.anredeantragst;
    }

    /**
     * Set the value of the ANREDEANTRAGST column.
     * @param anredeantragst
     */
    public void setAnredeantragst(java.lang.String anredeantragst) {
        this.anredeantragst = anredeantragst;
    }

    /**
     * Return the value of the NAMEANTRAGST column.
     * @return java.lang.String
     */
    public java.lang.String getNameantragst() {
        return this.nameantragst;
    }

    /**
     * Set the value of the NAMEANTRAGST column.
     * @param nameantragst
     */
    public void setNameantragst(java.lang.String nameantragst) {
        this.nameantragst = nameantragst;
    }

    /**
     * Return the value of the ZUSANTRAGST column.
     * @return java.lang.String
     */
    public java.lang.String getZusantragst() {
        return this.zusantragst;
    }

    /**
     * Set the value of the ZUSANTRAGST column.
     * @param zusantragst
     */
    public void setZusantragst(java.lang.String zusantragst) {
        this.zusantragst = zusantragst;
    }

    /**
     * Return the value of the STRASSEANTRAGST column.
     * @return java.lang.String
     */
    public java.lang.String getStrasseantragst() {
        return this.strasseantragst;
    }

    /**
     * Set the value of the STRASSEANTRAGST column.
     * @param strasseantragst
     */
    public void setStrasseantragst(java.lang.String strasseantragst) {
        this.strasseantragst = strasseantragst;
    }

    /**
     * Return the value of the HAUSNRANTRAGST column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getHausnrantragst() {
        return this.hausnrantragst;
    }

    /**
     * Set the value of the HAUSNRANTRAGST column.
     * @param hausnrantragst
     */
    public void setHausnrantragst(java.lang.Integer hausnrantragst) {
        this.hausnrantragst = hausnrantragst;
    }

    /**
     * Return the value of the HAUSNRZUSANTRAGST column.
     * @return java.lang.String
     */
    public java.lang.String getHausnrzusantragst() {
        return this.hausnrzusantragst;
    }

    /**
     * Set the value of the HAUSNRZUSANTRAGST column.
     * @param hausnrzusantragst
     */
    public void setHausnrzusantragst(java.lang.String hausnrzusantragst) {
        this.hausnrzusantragst = hausnrzusantragst;
    }

    /**
     * Return the value of the PLZANTRAGST column.
     * @return java.lang.String
     */
    public java.lang.String getPlzantragst() {
        return this.plzantragst;
    }

    /**
     * Set the value of the PLZANTRAGST column.
     * @param plzantragst
     */
    public void setPlzantragst(java.lang.String plzantragst) {
        this.plzantragst = plzantragst;
    }

    /**
     * Return the value of the ORTANTRAGST column.
     * @return java.lang.String
     */
    public java.lang.String getOrtantragst() {
        return this.ortantragst;
    }

    /**
     * Set the value of the ORTANTRAGST column.
     * @param ortantragst
     */
    public void setOrtantragst(java.lang.String ortantragst) {
        this.ortantragst = ortantragst;
    }

    /**
     * Return the value of the SACHBEARBEITER_IN column.
     * @return java.lang.String
     */
    public java.lang.String getSachbearbeiterIn() {
        return this.sachbearbeiterIn;
    }

    /**
     * Set the value of the SACHBEARBEITER_IN column.
     * @param sachbearbeiterIn
     */
    public void setSachbearbeiterIn(java.lang.String sachbearbeiterIn) {
        this.sachbearbeiterIn = sachbearbeiterIn;
    }

    /**
     * Return the value of the ANSPRECHPARTNER_IN column.
     * @return java.lang.String
     */
    public java.lang.String getAnsprechpartnerIn() {
        return this.ansprechpartnerIn;
    }

    /**
     * Set the value of the ANSPRECHPARTNER_IN column.
     * @param ansprechpartnerIn
     */
    public void setAnsprechpartnerIn(java.lang.String ansprechpartnerIn) {
        this.ansprechpartnerIn = ansprechpartnerIn;
    }

    /**
     * Return the value of the ANTRAGVOM column.
     * @return java.util.Date
     */
    public java.util.Date getAntragvom() {
        return this.antragvom;
    }

    /**
     * Set the value of the ANTRAGVOM column.
     * @param antragvom
     */
    public void setAntragvom(java.util.Date antragvom) {
        this.antragvom = antragvom;
    }

    /**
     * Return the value of the GENEHMIGUNG column.
     * @return java.util.Date
     */
    public java.util.Date getGenehmigung() {
        return this.genehmigung;
    }

    /**
     * Set the value of the GENEHMIGUNG column.
     * @param genehmigung
     */
    public void setGenehmigung(java.util.Date genehmigung) {
        this.genehmigung = genehmigung;
    }

    /**
     * Return the value of the WIEDERVORLAGE column.
     * @return java.util.Date
     */
    public java.util.Date getWiedervorlage() {
        return this.wiedervorlage;
    }

    /**
     * Set the value of the WIEDERVORLAGE column.
     * @param wiedervorlage
     */
    public void setWiedervorlage(java.util.Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    /**
     * Return the value of the AENDERUNGSGENEHMIGUNG column.
     * @return java.util.Date
     */
    public java.util.Date getAenderungsgenehmigung() {
        return this.aenderungsgenehmigung;
    }

    /**
     * Set the value of the AENDERUNGSGENEHMIGUNG column.
     * @param aenderungsgenehmigung
     */
    public void setAenderungsgenehmigung(java.util.Date aenderungsgenehmigung) {
        this.aenderungsgenehmigung = aenderungsgenehmigung;
    }

    /**
     * Return the value of the LETZTES_ANSCHREIBEN column.
     * @return java.util.Date
     */
    public java.util.Date getLetztesAnschreiben() {
        return this.letztesAnschreiben;
    }

    /**
     * Set the value of the LETZTES_ANSCHREIBEN column.
     * @param letztesAnschreiben
     */
    public void setLetztesAnschreiben(java.util.Date letztesAnschreiben) {
        this.letztesAnschreiben = letztesAnschreiben;
    }

    /**
     * Return the value of the ANSCHREIBEN column.
     * @return java.lang.String
     */
    public java.lang.String getAnschreiben() {
        return this.anschreiben;
    }

    /**
     * Set the value of the ANSCHREIBEN column.
     * @param anschreiben
     */
    public void setAnschreiben(java.lang.String anschreiben) {
        this.anschreiben = anschreiben;
    }

    /**
     * Return the value of the WASCHANLAGE column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getWaschanlage() {
        return this.waschanlage;
    }

    /**
     * Set the value of the WASCHANLAGE column.
     * @param waschanlage
     */
    public void setWaschanlage(java.lang.Boolean waschanlage) {
        this.waschanlage = waschanlage;
    }

    /**
     * Return the value of the E_SATZUNG column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getESatzung() {
        return this.eSatzung;
    }

    /**
     * Set the value of the E_SATZUNG column.
     * @param eSatzung
     */
    public void setESatzung(java.lang.Boolean eSatzung) {
        this.eSatzung = eSatzung;
    }

    /**
     * Return the value of the SEITWANN column.
     * @return java.util.Date
     */
    public java.util.Date getSeitwann() {
        return this.seitwann;
    }

    /**
     * Set the value of the SEITWANN column.
     * @param seitwann
     */
    public void setSeitwann(java.util.Date seitwann) {
        this.seitwann = seitwann;
    }

    /**
     * Return the value of the SONSTIGESTECHNIK column.
     * @return java.lang.String
     */
    public java.lang.String getSonstigestechnik() {
        return this.sonstigestechnik;
    }

    /**
     * Set the value of the SONSTIGESTECHNIK column.
     * @param sonstigestechnik
     */
    public void setSonstigestechnik(java.lang.String sonstigestechnik) {
        this.sonstigestechnik = sonstigestechnik;
    }

    /**
     * Return the value of the MAENGEL column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getMaengel() {
        return this.maengel;
    }

    /**
     * Set the value of the MAENGEL column.
     * @param maengel
     */
    public void setMaengel(java.lang.Boolean maengel) {
        this.maengel = maengel;
    }

    /**
     * Return the value of the BEHOBEN column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getBehoben() {
        return this.behoben;
    }

    /**
     * Set the value of the BEHOBEN column.
     * @param behoben
     */
    public void setBehoben(java.lang.Boolean behoben) {
        this.behoben = behoben;
    }

    /**
     * Return the value of the FRIST column.
     * @return java.util.Date
     */
    public java.util.Date getFrist() {
        return this.frist;
    }

    /**
     * Set the value of the FRIST column.
     * @param frist
     */
    public void setFrist(java.util.Date frist) {
        this.frist = frist;
    }

    /**
     * Return the value of the DURCHGEFUEHRT column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getDurchgefuehrt() {
        return this.durchgefuehrt;
    }

    /**
     * Set the value of the DURCHGEFUEHRT column.
     * @param durchgefuehrt
     */
    public void setDurchgefuehrt(java.lang.Integer durchgefuehrt) {
        this.durchgefuehrt = durchgefuehrt;
    }

    /**
     * Implementation of the equals comparison on the basis of equality of the
     * primary key values.
     * @param rhs
     * @return boolean
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs == null)
            return false;
        if (!(rhs instanceof Anh49Fachdaten))
            return false;
        Anh49Fachdaten that = (Anh49Fachdaten) rhs;
        if (this.getObjektid() != null && that.getObjektid() != null) {
            if (!this.getObjektid().equals(that.getObjektid())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Implementation of the hashCode method conforming to the Bloch pattern
     * with the exception of array properties (these are very unlikely primary
     * key types).
     * @return int
     */
    @Override
    public int hashCode() {
        if (this.hashValue == 0) {
            int result = 17;
            int basisObjektValue = this.getObjektid() == null ? 0 : this
                .getObjektid().hashCode();
            result = result * 37 + basisObjektValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
