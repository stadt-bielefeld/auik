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

import java.util.Date;

import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

public class AbstractIndeinlGenehmigung extends
    AbstractVirtuallyDeletableDatabaseTable implements java.io.Serializable {

    // Fields

    private static final long serialVersionUID = 252442364456699862L;

    private int objektid;

    private BasisObjekt basisObjekt;

    private java.lang.String bemerkungen;

    private Date erstellungsDatum;

    private Date aenderungsDatum;

    private Date antragDatum;

    private Boolean befristet;

    private Date befristetBis;

    private Integer anhang;

    private Integer genMenge;

    private Boolean gen58;

    private Boolean gen59;

    private Boolean selbstueberw;

    private Boolean esatzung;

    private Integer uebergabestelleRechtswert;
    private Integer uebergabestelleHochwert;

    // Constructors

    /** default constructor */
    public AbstractIndeinlGenehmigung() {
    }

    /** minimal constructor */
    public AbstractIndeinlGenehmigung(int objektid) {
        this.objektid = objektid;
    }

    /** full constructor */
    public AbstractIndeinlGenehmigung(int objektid, Date erstellungsDatum,
        Date aenderungsDatum) {
        this.objektid = objektid;
        this.erstellungsDatum = erstellungsDatum;
        this.aenderungsDatum = aenderungsDatum;
    }

    // Property accessors
    public int getObjektid() {
        return this.objektid;
    }

    public void setObjektid(int objektid) {
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

    public Date getErstellungsDatum() {
        return this.erstellungsDatum;
    }

    public void setErstellungsDatum(Date erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }

    public Date getAenderungsDatum() {
        return this.aenderungsDatum;
    }

    public void setAenderungsDatum(Date aenderungsDatum) {
        this.aenderungsDatum = aenderungsDatum;
    }

    public Date getAntragDatum() {
        return this.antragDatum;
    }

    public void setAntragDatum(Date antragDatum) {
        this.antragDatum = antragDatum;
    }

    public Boolean getBefristet() {
        return this.befristet;
    }

    public void setBefristet(Boolean befristet) {
        this.befristet = befristet;
    }

    public Date getBefristetBis() {
        return this.befristetBis;
    }

    public void setBefristetBis(Date befristetBis) {
        this.befristetBis = befristetBis;
    }

    public Integer getAnhang() {
        return this.anhang;
    }

    public void setAnhang(Integer anhang) {
        this.anhang = anhang;
    }

    public Integer getGenMenge() {
        return this.genMenge;
    }

    public void setGenMenge(Integer genMenge) {
        this.genMenge = genMenge;
    }

    public Boolean getGen58() {
        return this.gen58;
    }

    public void setGen58(Boolean gen58) {
        this.gen58 = gen58;
    }

    public Boolean getGen59() {
        return this.gen59;
    }

    public void setGen59(Boolean gen59) {
        this.gen59 = gen59;
    }

    public Boolean getSelbstueberw() {
        return this.selbstueberw;
    }

    public void setSelbstueberw(Boolean selbstueberw) {
        this.selbstueberw = selbstueberw;
    }

    public Boolean getEsatzung() {
        return this.esatzung;
    }

    public void setEsatzung(Boolean esatzung) {
        this.esatzung = esatzung;
    }

    public Integer getUebergabestelleRechtswert() {
        return this.uebergabestelleRechtswert;
    }

    public void setUebergabestelleRechtswert(Integer uebergabestelleRechtswert) {
        this.uebergabestelleRechtswert = uebergabestelleRechtswert;
    }

    public Integer getUebergabestelleHochwert() {
        return this.uebergabestelleHochwert;
    }

    public void setUebergabestelleHochwert(Integer uebergabestelleHochwert) {
        this.uebergabestelleHochwert = uebergabestelleHochwert;
    }
}
