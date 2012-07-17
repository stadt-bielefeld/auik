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

// Generated 17.07.2012 18:33:28 by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link Anh49Abscheiderdetails}.
 */
public abstract class AbstractAnh49Abscheiderdetails  implements java.io.Serializable {

     private Integer id;
     private Anh49Fachdaten anh49Fachdaten;
     private Integer abscheidernr;
     private Integer von;
     private String lage;
     private Integer nenngroesse;
     private Boolean tankstelle;
     private Boolean waschplatzHalle;
     private Boolean kfzBetrieb;
     private Boolean lebensmittelbetrieb;
     private Boolean wohnhaus;
     private Boolean oberflaechenentwaesserung;
     private Boolean produktionsabwasser;
     private Boolean schlammfang;
     private Boolean benzinOelabscheider;
     private Boolean koaleszenzfilter;
     private Boolean integriert;
     private Boolean emulsionsspaltanlage;
     private Boolean fettabscheider;
     private String baujahr;
     private Boolean din1999;
     private String bauartzulassungsnummer;
     private Integer ngSf;
     private Integer ngBa;
     private Integer ngKa;
     private Boolean schwimmer;
     private Boolean warnsignal;
     private Boolean wartungsvertrag;
     private String vertragspartner;
     private String letzteWartung;
     private String letzteLeerung;
     private String hersteller;
     private Boolean schmutzwasserkanal;
     private Boolean regenwasserkanal;
     private Boolean mischwasserkanal;
     private Boolean direkteinleiter;
     private String entsorgungnachweis;
     private String entsorgungsvertrag;
     private String entsorgungsintervalle;
     private Integer flaeche;
     private String bemerkung;
     private Date entsorgungsnachweis;
     private String entsorgungsnachweisDurch;
     private boolean enabled;
     private boolean deleted;

    public AbstractAnh49Abscheiderdetails() {
    }

    public AbstractAnh49Abscheiderdetails(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnh49Abscheiderdetails(
    	Anh49Fachdaten anh49Fachdaten, Integer abscheidernr, Integer von, String lage, Integer nenngroesse, Boolean tankstelle, Boolean waschplatzHalle, Boolean kfzBetrieb, Boolean lebensmittelbetrieb, Boolean wohnhaus, Boolean oberflaechenentwaesserung, Boolean produktionsabwasser, Boolean schlammfang, Boolean benzinOelabscheider, Boolean koaleszenzfilter, Boolean integriert, Boolean emulsionsspaltanlage, Boolean fettabscheider, String baujahr, Boolean din1999, String bauartzulassungsnummer, Integer ngSf, Integer ngBa, Integer ngKa, Boolean schwimmer, Boolean warnsignal, Boolean wartungsvertrag, String vertragspartner, String letzteWartung, String letzteLeerung, String hersteller, Boolean schmutzwasserkanal, Boolean regenwasserkanal, Boolean mischwasserkanal, Boolean direkteinleiter, String entsorgungnachweis, String entsorgungsvertrag, String entsorgungsintervalle, Integer flaeche, String bemerkung, Date entsorgungsnachweis, String entsorgungsnachweisDurch, boolean enabled, boolean deleted) {
        this.anh49Fachdaten = anh49Fachdaten;
        this.abscheidernr = abscheidernr;
        this.von = von;
        this.lage = lage;
        this.nenngroesse = nenngroesse;
        this.tankstelle = tankstelle;
        this.waschplatzHalle = waschplatzHalle;
        this.kfzBetrieb = kfzBetrieb;
        this.lebensmittelbetrieb = lebensmittelbetrieb;
        this.wohnhaus = wohnhaus;
        this.oberflaechenentwaesserung = oberflaechenentwaesserung;
        this.produktionsabwasser = produktionsabwasser;
        this.schlammfang = schlammfang;
        this.benzinOelabscheider = benzinOelabscheider;
        this.koaleszenzfilter = koaleszenzfilter;
        this.integriert = integriert;
        this.emulsionsspaltanlage = emulsionsspaltanlage;
        this.fettabscheider = fettabscheider;
        this.baujahr = baujahr;
        this.din1999 = din1999;
        this.bauartzulassungsnummer = bauartzulassungsnummer;
        this.ngSf = ngSf;
        this.ngBa = ngBa;
        this.ngKa = ngKa;
        this.schwimmer = schwimmer;
        this.warnsignal = warnsignal;
        this.wartungsvertrag = wartungsvertrag;
        this.vertragspartner = vertragspartner;
        this.letzteWartung = letzteWartung;
        this.letzteLeerung = letzteLeerung;
        this.hersteller = hersteller;
        this.schmutzwasserkanal = schmutzwasserkanal;
        this.regenwasserkanal = regenwasserkanal;
        this.mischwasserkanal = mischwasserkanal;
        this.direkteinleiter = direkteinleiter;
        this.entsorgungnachweis = entsorgungnachweis;
        this.entsorgungsvertrag = entsorgungsvertrag;
        this.entsorgungsintervalle = entsorgungsintervalle;
        this.flaeche = flaeche;
        this.bemerkung = bemerkung;
        this.entsorgungsnachweis = entsorgungsnachweis;
        this.entsorgungsnachweisDurch = entsorgungsnachweisDurch;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Anh49Fachdaten getAnh49Fachdaten() {
        return this.anh49Fachdaten;
    }
    public void setAnh49Fachdaten(Anh49Fachdaten anh49Fachdaten) {
        this.anh49Fachdaten = anh49Fachdaten;
    }

    public Integer getAbscheidernr() {
        return this.abscheidernr;
    }
    public void setAbscheidernr(Integer abscheidernr) {
        this.abscheidernr = abscheidernr;
    }

    public Integer getVon() {
        return this.von;
    }
    public void setVon(Integer von) {
        this.von = von;
    }

    public String getLage() {
        return this.lage;
    }
    public void setLage(String lage) {
        this.lage = lage;
    }

    public Integer getNenngroesse() {
        return this.nenngroesse;
    }
    public void setNenngroesse(Integer nenngroesse) {
        this.nenngroesse = nenngroesse;
    }

    public Boolean getTankstelle() {
        return this.tankstelle;
    }
    public void setTankstelle(Boolean tankstelle) {
        this.tankstelle = tankstelle;
    }

    public Boolean getWaschplatzHalle() {
        return this.waschplatzHalle;
    }
    public void setWaschplatzHalle(Boolean waschplatzHalle) {
        this.waschplatzHalle = waschplatzHalle;
    }

    public Boolean getKfzBetrieb() {
        return this.kfzBetrieb;
    }
    public void setKfzBetrieb(Boolean kfzBetrieb) {
        this.kfzBetrieb = kfzBetrieb;
    }

    public Boolean getLebensmittelbetrieb() {
        return this.lebensmittelbetrieb;
    }
    public void setLebensmittelbetrieb(Boolean lebensmittelbetrieb) {
        this.lebensmittelbetrieb = lebensmittelbetrieb;
    }

    public Boolean getWohnhaus() {
        return this.wohnhaus;
    }
    public void setWohnhaus(Boolean wohnhaus) {
        this.wohnhaus = wohnhaus;
    }

    public Boolean getOberflaechenentwaesserung() {
        return this.oberflaechenentwaesserung;
    }
    public void setOberflaechenentwaesserung(Boolean oberflaechenentwaesserung) {
        this.oberflaechenentwaesserung = oberflaechenentwaesserung;
    }

    public Boolean getProduktionsabwasser() {
        return this.produktionsabwasser;
    }
    public void setProduktionsabwasser(Boolean produktionsabwasser) {
        this.produktionsabwasser = produktionsabwasser;
    }

    public Boolean getSchlammfang() {
        return this.schlammfang;
    }
    public void setSchlammfang(Boolean schlammfang) {
        this.schlammfang = schlammfang;
    }

    public Boolean getBenzinOelabscheider() {
        return this.benzinOelabscheider;
    }
    public void setBenzinOelabscheider(Boolean benzinOelabscheider) {
        this.benzinOelabscheider = benzinOelabscheider;
    }

    public Boolean getKoaleszenzfilter() {
        return this.koaleszenzfilter;
    }
    public void setKoaleszenzfilter(Boolean koaleszenzfilter) {
        this.koaleszenzfilter = koaleszenzfilter;
    }

    public Boolean getIntegriert() {
        return this.integriert;
    }
    public void setIntegriert(Boolean integriert) {
        this.integriert = integriert;
    }

    public Boolean getEmulsionsspaltanlage() {
        return this.emulsionsspaltanlage;
    }
    public void setEmulsionsspaltanlage(Boolean emulsionsspaltanlage) {
        this.emulsionsspaltanlage = emulsionsspaltanlage;
    }

    public Boolean getFettabscheider() {
        return this.fettabscheider;
    }
    public void setFettabscheider(Boolean fettabscheider) {
        this.fettabscheider = fettabscheider;
    }

    public String getBaujahr() {
        return this.baujahr;
    }
    public void setBaujahr(String baujahr) {
        this.baujahr = baujahr;
    }

    public Boolean getDin1999() {
        return this.din1999;
    }
    public void setDin1999(Boolean din1999) {
        this.din1999 = din1999;
    }

    public String getBauartzulassungsnummer() {
        return this.bauartzulassungsnummer;
    }
    public void setBauartzulassungsnummer(String bauartzulassungsnummer) {
        this.bauartzulassungsnummer = bauartzulassungsnummer;
    }

    public Integer getNgSf() {
        return this.ngSf;
    }
    public void setNgSf(Integer ngSf) {
        this.ngSf = ngSf;
    }

    public Integer getNgBa() {
        return this.ngBa;
    }
    public void setNgBa(Integer ngBa) {
        this.ngBa = ngBa;
    }

    public Integer getNgKa() {
        return this.ngKa;
    }
    public void setNgKa(Integer ngKa) {
        this.ngKa = ngKa;
    }

    public Boolean getSchwimmer() {
        return this.schwimmer;
    }
    public void setSchwimmer(Boolean schwimmer) {
        this.schwimmer = schwimmer;
    }

    public Boolean getWarnsignal() {
        return this.warnsignal;
    }
    public void setWarnsignal(Boolean warnsignal) {
        this.warnsignal = warnsignal;
    }

    public Boolean getWartungsvertrag() {
        return this.wartungsvertrag;
    }
    public void setWartungsvertrag(Boolean wartungsvertrag) {
        this.wartungsvertrag = wartungsvertrag;
    }

    public String getVertragspartner() {
        return this.vertragspartner;
    }
    public void setVertragspartner(String vertragspartner) {
        this.vertragspartner = vertragspartner;
    }

    public String getLetzteWartung() {
        return this.letzteWartung;
    }
    public void setLetzteWartung(String letzteWartung) {
        this.letzteWartung = letzteWartung;
    }

    public String getLetzteLeerung() {
        return this.letzteLeerung;
    }
    public void setLetzteLeerung(String letzteLeerung) {
        this.letzteLeerung = letzteLeerung;
    }

    public String getHersteller() {
        return this.hersteller;
    }
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public Boolean getSchmutzwasserkanal() {
        return this.schmutzwasserkanal;
    }
    public void setSchmutzwasserkanal(Boolean schmutzwasserkanal) {
        this.schmutzwasserkanal = schmutzwasserkanal;
    }

    public Boolean getRegenwasserkanal() {
        return this.regenwasserkanal;
    }
    public void setRegenwasserkanal(Boolean regenwasserkanal) {
        this.regenwasserkanal = regenwasserkanal;
    }

    public Boolean getMischwasserkanal() {
        return this.mischwasserkanal;
    }
    public void setMischwasserkanal(Boolean mischwasserkanal) {
        this.mischwasserkanal = mischwasserkanal;
    }

    public Boolean getDirekteinleiter() {
        return this.direkteinleiter;
    }
    public void setDirekteinleiter(Boolean direkteinleiter) {
        this.direkteinleiter = direkteinleiter;
    }

    public String getEntsorgungnachweis() {
        return this.entsorgungnachweis;
    }
    public void setEntsorgungnachweis(String entsorgungnachweis) {
        this.entsorgungnachweis = entsorgungnachweis;
    }

    public String getEntsorgungsvertrag() {
        return this.entsorgungsvertrag;
    }
    public void setEntsorgungsvertrag(String entsorgungsvertrag) {
        this.entsorgungsvertrag = entsorgungsvertrag;
    }

    public String getEntsorgungsintervalle() {
        return this.entsorgungsintervalle;
    }
    public void setEntsorgungsintervalle(String entsorgungsintervalle) {
        this.entsorgungsintervalle = entsorgungsintervalle;
    }

    public Integer getFlaeche() {
        return this.flaeche;
    }
    public void setFlaeche(Integer flaeche) {
        this.flaeche = flaeche;
    }

    public String getBemerkung() {
        return this.bemerkung;
    }
    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public Date getEntsorgungsnachweis() {
        return this.entsorgungsnachweis;
    }
    public void setEntsorgungsnachweis(Date entsorgungsnachweis) {
        this.entsorgungsnachweis = entsorgungsnachweis;
    }

    public String getEntsorgungsnachweisDurch() {
        return this.entsorgungsnachweisDurch;
    }
    public void setEntsorgungsnachweisDurch(String entsorgungsnachweisDurch) {
        this.entsorgungsnachweisDurch = entsorgungsnachweisDurch;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}

