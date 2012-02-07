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

import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

public class AbstractIndeinlUebergabestelle implements java.io.Serializable {

    // Fields

    private static final long serialVersionUID = 4384787652460898052L;

    private int objektid;

    private BasisObjekt basisObjekt;

    private java.lang.String bemerkungen;

    private AtlKlaeranlagen atlKlaeranlagen;

    private Date aenderungsDatum;

    private Date erfassungsDatum;

    private Integer kanalart;

    private Integer rechtswert;

    private Integer hochwert;

    // Constructors

    /** default constructor */
    public AbstractIndeinlUebergabestelle() {
    }

    /** minimal constructor */
    public AbstractIndeinlUebergabestelle(int objektid) {
        this.objektid = objektid;
    }

    /** full constructor */
    public AbstractIndeinlUebergabestelle(int objektid,
        AtlKlaeranlagen atlKlaeranlagen, Date aenderungsDatum,
        Date erfassungsDatum, Integer kanalart, Integer rechtswert,
        Integer hochwert) {
        this.objektid = objektid;
        this.atlKlaeranlagen = atlKlaeranlagen;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.kanalart = kanalart;
        this.rechtswert = rechtswert;
        this.hochwert = hochwert;
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

    /**
     * Return the value of the KA_ID column.
     * @return AtlKlaeranlagen
     */
    public AtlKlaeranlagen getAtlKlaeranlagen() {
        return this.atlKlaeranlagen;
    }

    /**
     * Set the value of the KA_ID column.
     * @param atlKlaeranlagen
     */
    public void setAtlKlaeranlagen(AtlKlaeranlagen atlKlaeranlagen) {
        this.atlKlaeranlagen = atlKlaeranlagen;
    }

    public Date getAenderungsDatum() {
        return this.aenderungsDatum;
    }

    public void setAenderungsDatum(Date aenderungsDatum) {
        this.aenderungsDatum = aenderungsDatum;
    }

    public Date getErfassungsDatum() {
        return this.erfassungsDatum;
    }

    public void setErfassungsDatum(Date erfassungsDatum) {
        this.erfassungsDatum = erfassungsDatum;
    }

    public Integer getKanalart() {
        return this.kanalart;
    }

    public void setKanalart(Integer kanalart) {
        this.kanalart = kanalart;
    }

    public Integer getRechtswert() {
        return this.rechtswert;
    }

    public void setRechtswert(Integer rechtswert) {
        this.rechtswert = rechtswert;
    }

    public Integer getHochwert() {
        return this.hochwert;
    }

    public void setHochwert(Integer hochwert) {
        this.hochwert = hochwert;
    }
}
