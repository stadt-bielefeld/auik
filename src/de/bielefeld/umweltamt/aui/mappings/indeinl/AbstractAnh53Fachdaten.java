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

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the Anh53Fachdaten table. You can customize
 * the behavior of this class by editing the class, {@link Anh50Fachdaten}.
 */
public abstract class AbstractAnh53Fachdaten implements Serializable {
    private static final long serialVersionUID = 2936138294926146485L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer objektid;

    /** The value of the anhEntsorger association. */
    private AnhEntsorger anhEntsorger;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;

    /** The value of the simple branche property. */
    private java.lang.String branche;

    /** The value of the simple verfahren property. */
    private java.lang.String verfahren;

    /** The value of the simple antragsdatum property. */
    private java.util.Date antragsdatum;

    /** The value of the simple bagatell property. */
    private java.lang.Boolean bagatell;

    /** The value of the simple bagatelldatum property. */
    private java.util.Date bagatelldatum;

    /** The value of the simple genehmigungsdatum property. */
    private java.util.Date genehmigungsdatum;

    /** The value of the simple genehmigungaufgehoben property. */
    private java.util.Date genehmigungaufgehoben;

    /** The value of the simple abnahmedatum property. */
    private java.util.Date abnahmedatum;

    /** The value of the simple genehmigungstitel property. */
    private java.lang.String genehmigungstitel;

    /** The value of the simple genehmigung property. */
    private java.lang.Boolean genehmigung;

    /** The value of the simple strassenid property. */
    private java.lang.Integer durchsatz;

    /** The value of the simple hausnr property. */
    private java.lang.Integer gesamtmengeEb;

    /** The value of the simple hausnr property. */
    private java.lang.Boolean onlineentsilberung;

    /** The value of the simple mListe property. */
    private java.lang.Boolean abwasser;

    /** The value of the simple datumantrag property. */
    private java.util.Date abwasserfrei;

    /** The value of the simple datumantrag property. */
    private java.util.Date kleiner200qm;

    /** The value of the simple amalgam property. */
    private java.lang.Boolean betriebAbgemeldet;

    /** The value of the simple genehmigungstitel property. */
    private java.lang.String bemerkungen;

    /** The value of the simple abscheidervor property. */
    private java.lang.Boolean betriebstagebuch;

    /** The value of the simple abscheidervor property. */
    private java.lang.Boolean wasseruhr;

    /** The value of the simple hausnr property. */
    private java.lang.Integer spuelwassermenge;

    /** The value of the simple hausnr property. */
    private java.lang.Integer spuelwasserverbrauch;

    /** The value of the simple drucken property. */
    private java.lang.Boolean wartungsvertrag;

    /** The value of the simple drucken property. */
    private java.lang.Boolean grgen;

    /** The value of the simple gefaehrdungsklasse property. */
    private java.lang.String genart;

    /**
     * Simple constructor of AbstractAnh50Fachdaten instances.
     */
    public AbstractAnh53Fachdaten() {
    }

    /**
     * Constructor of AbstractAnh50Fachdaten instances given a simple primary
     * key.
     * @param firmenid
     */
    public AbstractAnh53Fachdaten(java.lang.Integer objektid) {
        this.setObjektid(objektid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getObjektid() {
        return objektid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param firmenid
     */
    public void setObjektid(java.lang.Integer objektid) {
        this.hashValue = 0;
        this.objektid = objektid;
    }

    /**
     * Return the value of the OBJEKTID column.
     * @return BasisObjekt
     */
    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }

    /**
     * Set the value of the OBJEKTID column.
     * @param basisObjekt
     */
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    /**
     * Return the value of the ID column.
     * @return Anh53Entsorgungsbetriebe
     */
    public AnhEntsorger getAnhEntsorger() {
        return this.anhEntsorger;
    }

    /**
     * Set the value of the ID column.
     * @param AnhEntsorger
     */
    public void setAnhEntsorger(AnhEntsorger anhEntsorger) {
        this.anhEntsorger = anhEntsorger;
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
        if (!(rhs instanceof Anh50Fachdaten))
            return false;
        Anh50Fachdaten that = (Anh50Fachdaten) rhs;
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
            int firmenidValue = this.getObjektid() == null ? 0 : this
                .getObjektid().hashCode();
            result = result * 37 + firmenidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }

    public java.lang.String getBranche() {
        return branche;
    }

    public void setBranche(java.lang.String branche) {
        this.branche = branche;
    }

    public java.lang.String getVerfahren() {
        return verfahren;
    }

    public void setVerfahren(java.lang.String verfahren) {
        this.verfahren = verfahren;
    }

    public java.util.Date getAntragsdatum() {
        return antragsdatum;
    }

    public void setAntragsdatum(java.util.Date antragsdatum) {
        this.antragsdatum = antragsdatum;
    }

    public java.lang.Boolean getBagatell() {
        return bagatell;
    }

    public void setBagatell(java.lang.Boolean bagatell) {
        this.bagatell = bagatell;
    }

    public java.util.Date getBagatelldatum() {
        return bagatelldatum;
    }

    public void setBagatelldatum(java.util.Date bagatelldatum) {
        this.bagatelldatum = bagatelldatum;
    }

    public java.util.Date getGenehmigungsdatum() {
        return genehmigungsdatum;
    }

    public void setGenehmigungsdatum(java.util.Date genehmigungsdatum) {
        this.genehmigungsdatum = genehmigungsdatum;
    }

    public java.util.Date getGenehmigungaufgehoben() {
        return genehmigungaufgehoben;
    }

    public void setGenehmigungaufgehoben(java.util.Date genehmigungaufgehoben) {
        this.genehmigungaufgehoben = genehmigungaufgehoben;
    }

    public java.util.Date getAbnahmedatum() {
        return abnahmedatum;
    }

    public void setAbnahmedatum(java.util.Date abnahmedatum) {
        this.abnahmedatum = abnahmedatum;
    }

    public java.lang.String getGenehmigungstitel() {
        return genehmigungstitel;
    }

    public void setGenehmigungstitel(java.lang.String genehmigungstitel) {
        this.genehmigungstitel = genehmigungstitel;
    }

    public java.lang.Boolean getGenehmigung() {
        return genehmigung;
    }

    public void setGenehmigung(java.lang.Boolean genehmigung) {
        this.genehmigung = genehmigung;
    }

    public java.lang.Integer getDurchsatz() {
        return durchsatz;
    }

    public void setDurchsatz(java.lang.Integer durchsatz) {
        this.durchsatz = durchsatz;
    }

    public java.lang.Integer getGesamtmengeEb() {
        return gesamtmengeEb;
    }

    public void setGesamtmengeEb(java.lang.Integer gesamtmengeEb) {
        this.gesamtmengeEb = gesamtmengeEb;
    }

    public java.lang.Boolean getOnlineentsilberung() {
        return onlineentsilberung;
    }

    public void setOnlineentsilberung(java.lang.Boolean onlineentsilberung) {
        this.onlineentsilberung = onlineentsilberung;
    }

    public java.lang.Boolean getAbwasser() {
        return abwasser;
    }

    public void setAbwasser(java.lang.Boolean abwasser) {
        this.abwasser = abwasser;
    }

    public java.util.Date getAbwasserfrei() {
        return abwasserfrei;
    }

    public void setAbwasserfrei(java.util.Date abwasserfrei) {
        this.abwasserfrei = abwasserfrei;
    }

    public java.util.Date getKleiner200qm() {
        return kleiner200qm;
    }

    public void setKleiner200qm(java.util.Date kleiner200qm) {
        this.kleiner200qm = kleiner200qm;
    }

    public java.lang.Boolean getBetriebAbgemeldet() {
        return betriebAbgemeldet;
    }

    public void setBetriebAbgemeldet(java.lang.Boolean betriebAbgemeldet) {
        this.betriebAbgemeldet = betriebAbgemeldet;
    }

    public java.lang.Boolean getBetriebstagebuch() {
        return betriebstagebuch;
    }

    public void setBetriebstagebuch(java.lang.Boolean betriebstagebuch) {
        this.betriebstagebuch = betriebstagebuch;
    }

    public java.lang.Boolean getWasseruhr() {
        return wasseruhr;
    }

    public void setWasseruhr(java.lang.Boolean wasseruhr) {
        this.wasseruhr = wasseruhr;
    }

    public java.lang.Integer getSpuelwassermenge() {
        return spuelwassermenge;
    }

    public void setSpuelwassermenge(java.lang.Integer spuelwassermenge) {
        this.spuelwassermenge = spuelwassermenge;
    }

    public java.lang.Integer getSpuelwasserverbrauch() {
        return spuelwasserverbrauch;
    }

    public void setSpuelwasserverbrauch(java.lang.Integer spuelwasserverbrauch) {
        this.spuelwasserverbrauch = spuelwasserverbrauch;
    }

    public java.lang.Boolean getWartungsvertrag() {
        return wartungsvertrag;
    }

    public void setWartungsvertrag(java.lang.Boolean wartungsvertrag) {
        this.wartungsvertrag = wartungsvertrag;
    }

    public java.lang.Boolean getGrgen() {
        return grgen;
    }

    public void setGrgen(java.lang.Boolean grgen) {
        this.grgen = grgen;
    }

    public java.lang.String getGenart() {
        return genart;
    }

    public void setGenart(java.lang.String genart) {
        this.genart = genart;
    }

    public java.lang.String getBemerkungen() {
        return bemerkungen;
    }

    public void setBemerkungen(java.lang.String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }
}
