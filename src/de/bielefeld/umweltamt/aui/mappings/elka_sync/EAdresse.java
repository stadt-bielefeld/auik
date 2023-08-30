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

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;

// Generated 22.10.2015 16:17:13 by Hibernate Tools 3.4.0.CR1

/**
 * EAdresse generated by hbm2java
 */
public class EAdresse implements java.io.Serializable {

	private BigInteger nr;
	private BigInteger origNr;
	private String anrede;
	private String name1;
	private String name2;
	private String strasse;
	private String hausnr;
	private String plzZst;
	private String ortZst;
	private String staatskennungZst;
	private String telefon;
	private String fax;
	private String email;
	private Boolean personTog;
	private Date aktualDat;
	private Date erstellDat;
	private String herkunft;

	public EAdresse() {
	}

	public EAdresse(BigInteger nr) {
		this.nr = nr;
	}

	public EAdresse(BigInteger nr, BigInteger origNr, String anrede, String name1,
			String name2, String strasse, String hausnr, String plzZst,
			String ortZst, String staatskennungZst, String telefon, String fax,
			String email, Boolean personTog, Date aktualDat, Date erstellDat,
			String herkunft) {
		this.nr = nr;
		this.origNr = origNr;
		this.anrede = anrede;
		this.name1 = name1;
		this.name2 = name2;
		this.strasse = strasse;
		this.hausnr = hausnr;
		this.plzZst = plzZst;
		this.ortZst = ortZst;
		this.staatskennungZst = staatskennungZst;
		this.telefon = telefon;
		this.fax = fax;
		this.email = email;
		this.personTog = personTog;
		this.aktualDat = aktualDat;
		this.erstellDat = erstellDat;
		this.herkunft = herkunft;
	}

	public BigInteger getNr() {
		return this.nr;
	}

	public void setNr(BigInteger nr) {
		this.nr = nr;
	}
	
	@JsonIgnore
	public BigInteger getOrigNr() {
		return this.origNr;
	}
	
	@JsonIgnore
	public void setOrigNr(BigInteger origNr) {
		this.origNr = origNr;
	}

	public String getAnrede() {
		return this.anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

	public String getName1() {
		return this.name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return this.name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getStrasse() {
		return this.strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnr() {
		return this.hausnr;
	}

	public void setHausnr(String hausnr) {
		this.hausnr = hausnr;
	}

	public String getPlzZst() {
		return this.plzZst;
	}

	public void setPlzZst(String plzZst) {
		this.plzZst = plzZst;
	}

	public String getOrtZst() {
		return this.ortZst;
	}

	public void setOrtZst(String ortZst) {
		this.ortZst = ortZst;
	}

	public String getStaatskennungZst() {
		return this.staatskennungZst;
	}

	public void setStaatskennungZst(String staatskennungZst) {
		this.staatskennungZst = staatskennungZst;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getPersonTog() {
		return this.personTog;
	}

	public void setPersonTog(Boolean personTog) {
		this.personTog = personTog;
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
     * Update this EAbwasserbehandlungsanlage with its new values.<br>
     * This is meant to be used after merging!
     * @param copy AtlKlaeranlagen
     */
    private void copy(EAdresse copy) {
    	this.anrede = copy.getAnrede();
    	this.email = copy.getEmail();
    	this.fax = copy.getFax();
    	this.hausnr = copy.getHausnr();
    	this.name1 = copy.getName1();
    	this.name2 = copy.getName2();
    	this.nr = copy.getNr();
    	this.ortZst = copy.getOrtZst();
    	this.plzZst = copy.getPlzZst();
    	this.staatskennungZst = copy.getStaatskennungZst();
    	this.strasse = copy.getStrasse();
    	this.telefon = copy.getTelefon();
    	this.aktualDat = copy.getAktualDat();
    	this.erstellDat = copy.getErstellDat();
    	this.herkunft = copy.getHerkunft();
    	this.personTog = copy.getPersonTog();
    }

    /**
     * Find an <code>AtlKlaeranlagen</code> instance by its primary key
     * @param id the primary key value
     * @return <code>AtlKlaeranlagen</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static EAdresse findById(java.lang.Integer id) {
        return (EAdresse)
            new DatabaseAccess().get(EAdresse.class, id);
    }

    /**
     * Get a list of all <code>AtlKlaeranlagen</code>
     * @return <code>List&lt;AtlKlaeranlagen&gt;</code>
     *         all <code>AtlKlaeranlagen</code>
     */
    public static List<EAdresse> getAll() {
        return DatabaseQuery.getAll(new EAdresse());
    }
}
