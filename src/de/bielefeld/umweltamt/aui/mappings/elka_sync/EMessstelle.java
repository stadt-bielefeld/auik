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
package de.bielefeld.umweltamt.aui.mappings.elka_sync;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.MsstBerichtspflicht;

// Generated 22.10.2015 16:17:13 by Hibernate Tools 3.4.0.CR1

/**
 * EMessstelle generated by hbm2java
 */
public class EMessstelle implements java.io.Serializable {

    private EStandort standort;
    private Integer nr;
    private Integer origNr;
	private Float e32;
	private Float n32;
    private String bezeichnung;
    private Integer typOpt;
    private Integer artMessungOpt;
    private String bemerkung;
    private Date aktualDat;
    private Date erstellDat;
    private String herkunft;
    private Set<EProbenahme> probenahmes = new HashSet<EProbenahme>(0);

    private Set<MsstBerichtspflicht> zuordnBerichtspflichts;

    public EMessstelle() {
    }

    public EMessstelle(Integer standortNr) {
        this.nr = nr;
    }

    public EMessstelle(Integer nr, EStandort standort, Integer origNr, Float e32, Float n32,
            String bezeichnung, Integer typOpt, Integer artMessungOpt,
            String bemerkung, Date aktualDat, Date erstellDat, String herkunft) {
        this.standort = standort;
        this.nr = nr;
        this.origNr = origNr;
		this.e32 = e32;
		this.n32 = n32;
        this.bezeichnung = bezeichnung;
        this.typOpt = typOpt;
        this.artMessungOpt = artMessungOpt;
        this.bemerkung = bemerkung;
        this.aktualDat = aktualDat;
        this.erstellDat = erstellDat;
        this.herkunft = herkunft;
    }

    public EStandort getStandort() {
        return this.standort;
    }

    public void setStandort(EStandort standort) {
        this.standort = standort;
    }

    public Integer getNr() {
        return this.nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    @JsonIgnore
    public Integer getOrigNr() {
        return this.origNr;
    }

    @JsonIgnore
    public void setOrigNr(Integer origNr) {
        this.origNr = origNr;
    }

	public Integer getE32() {
		return Math.round(this.e32);
	}

	public void setE32(Float e32) {
		this.e32 = e32;
	}

	public Integer getN32() {
		return Math.round(this.n32);
	}

	public void setN32(Float n32) {
		this.n32 = n32;
	}

    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Integer getTypOpt() {
        return this.typOpt;
    }

    public void setTypOpt(Integer typOpt) {
        this.typOpt = typOpt;
    }

    public Integer getArtMessungOpt() {
        return this.artMessungOpt;
    }

    public void setArtMessungOpt(Integer artMessungOpt) {
        this.artMessungOpt = artMessungOpt;
    }

    public String getBemerkung() {
        return this.bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public Date getAktualDat() {
        return this.aktualDat;
    }

    public void setAktualDat(Date aktualDat) {
        this.aktualDat = aktualDat;
    }

    public Date getErstellDat() {
        return this.erstellDat;
    }

    public void setErstellDat(Date erstellDat) {
        this.erstellDat = erstellDat;
    }

    public String getHerkunft() {
        return this.herkunft;
    }

    public void setHerkunft(String herkunft) {
        this.herkunft = herkunft;
    }

    /**
     * Update this EMessstelle with its new values.<br>
     * This is meant to be used after merging!
     * @param copy EMessstelle
     */
    private void copy(EMessstelle copy) {
    	this.e32 = copy.getE32().floatValue();
    	this.n32 = copy.getN32().floatValue();
        this.artMessungOpt = copy.getArtMessungOpt();
        this.bemerkung = copy.getBemerkung();
        this.bezeichnung = copy.getBezeichnung();
        this.nr = copy.getNr();
        this.standort = copy.getStandort();
        this.typOpt = copy.getTypOpt();
        this.aktualDat = copy.getAktualDat();
        this.erstellDat = copy.getErstellDat();
        this.herkunft = copy.getHerkunft();
        this.probenahmes = copy.getProbenahmes();
    }

    /**
     * Find an <code>EMessstelle</code> instance by its primary key
     * @param id the primary key value
     * @return <code>EMessstelle</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static EMessstelle findById(java.lang.Integer id) {
        return (EMessstelle)
            new DatabaseAccess().get(EMessstelle.class, id);
    }

    /**
     * Get a list of all <code>EMessstelle</code>
     * @return <code>List&lt;EMessstelle&gt;</code>
     *         all <code>EMessstelle</code>
     */
    public static List<EMessstelle> getAll() {
        return DatabaseQuery.getAll(new EMessstelle());
    }

    public Set<EProbenahme> getProbenahmes() {
        return probenahmes;
    }

    public void setProbenahmes(Set<EProbenahme> probenahme) {
        this.probenahmes = probenahme;

    }

    /* Custom code goes below here! */

    /**
     * Returns all MsstBerichtspflich instances of the Messstelle table row
     * on which this instance is based on.
     * @return The instances as set
     */
    public Set<MsstBerichtspflicht> getZuordnungMsstBerichtspflichts() {
        if (zuordnBerichtspflichts == null) {
            Integer origId = this.getOrigNr() != null ? this.getOrigNr() : this.getNr();
            zuordnBerichtspflichts = new HashSet<MsstBerichtspflicht>(
                HibernateSessionFactory.currentSession().createQuery(
                    "from MsstBerichtspflicht where msst_nr=" + origId
                ).list()
            );
        }
        return zuordnBerichtspflichts;
    }

}
