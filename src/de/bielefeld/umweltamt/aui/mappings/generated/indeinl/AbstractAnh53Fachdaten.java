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


import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjekt;
import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link Anh53Fachdaten}.
 */
public abstract class AbstractAnh53Fachdaten  implements java.io.Serializable {

     private int objektid;
     private BasisObjekt basisObjekt;
     private String branche;
     private String verfahren;
     private Date antragsdatum;
     private Boolean bagatell;
     private Date bagatelldatum;
     private Date genehmigungsdatum;
     private Date genehmigungaufgehoben;
     private Date abnahmedatum;
     private String genehmigungstitel;
     private Boolean genehmigung;
     private Integer durchsatz;
     private Integer gesamtmengeEb;
     private Boolean onlineentsilberung;
     private Boolean abwasser;
     private Date abwasserfrei;
     private Date kleiner200qm;
     private Boolean betriebAbgemeldet;
     private String bemerkungen;
     private Boolean betriebstagebuch;
     private Boolean wasseruhr;
     private Integer spuelwassermenge;
     private Integer spuelwasserverbrauch;
     private Boolean wartungsvertrag;
     private Boolean grgen;
     private String genart;
     private boolean enabled;
     private boolean deleted;

    public AbstractAnh53Fachdaten() {
    }

    public AbstractAnh53Fachdaten(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnh53Fachdaten(
    	BasisObjekt basisObjekt, String branche, String verfahren, Date antragsdatum, Boolean bagatell, Date bagatelldatum, Date genehmigungsdatum, Date genehmigungaufgehoben, Date abnahmedatum, String genehmigungstitel, Boolean genehmigung, Integer durchsatz, Integer gesamtmengeEb, Boolean onlineentsilberung, Boolean abwasser, Date abwasserfrei, Date kleiner200qm, Boolean betriebAbgemeldet, String bemerkungen, Boolean betriebstagebuch, Boolean wasseruhr, Integer spuelwassermenge, Integer spuelwasserverbrauch, Boolean wartungsvertrag, Boolean grgen, String genart, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.branche = branche;
        this.verfahren = verfahren;
        this.antragsdatum = antragsdatum;
        this.bagatell = bagatell;
        this.bagatelldatum = bagatelldatum;
        this.genehmigungsdatum = genehmigungsdatum;
        this.genehmigungaufgehoben = genehmigungaufgehoben;
        this.abnahmedatum = abnahmedatum;
        this.genehmigungstitel = genehmigungstitel;
        this.genehmigung = genehmigung;
        this.durchsatz = durchsatz;
        this.gesamtmengeEb = gesamtmengeEb;
        this.onlineentsilberung = onlineentsilberung;
        this.abwasser = abwasser;
        this.abwasserfrei = abwasserfrei;
        this.kleiner200qm = kleiner200qm;
        this.betriebAbgemeldet = betriebAbgemeldet;
        this.bemerkungen = bemerkungen;
        this.betriebstagebuch = betriebstagebuch;
        this.wasseruhr = wasseruhr;
        this.spuelwassermenge = spuelwassermenge;
        this.spuelwasserverbrauch = spuelwasserverbrauch;
        this.wartungsvertrag = wartungsvertrag;
        this.grgen = grgen;
        this.genart = genart;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public int getObjektid() {
        return this.objektid;
    }
    public void setObjektid(int objektid) {
        this.objektid = objektid;
    }

    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    public String getBranche() {
        return this.branche;
    }
    public void setBranche(String branche) {
        this.branche = branche;
    }

    public String getVerfahren() {
        return this.verfahren;
    }
    public void setVerfahren(String verfahren) {
        this.verfahren = verfahren;
    }

    public Date getAntragsdatum() {
        return this.antragsdatum;
    }
    public void setAntragsdatum(Date antragsdatum) {
        this.antragsdatum = antragsdatum;
    }

    public Boolean getBagatell() {
        return this.bagatell;
    }
    public void setBagatell(Boolean bagatell) {
        this.bagatell = bagatell;
    }

    public Date getBagatelldatum() {
        return this.bagatelldatum;
    }
    public void setBagatelldatum(Date bagatelldatum) {
        this.bagatelldatum = bagatelldatum;
    }

    public Date getGenehmigungsdatum() {
        return this.genehmigungsdatum;
    }
    public void setGenehmigungsdatum(Date genehmigungsdatum) {
        this.genehmigungsdatum = genehmigungsdatum;
    }

    public Date getGenehmigungaufgehoben() {
        return this.genehmigungaufgehoben;
    }
    public void setGenehmigungaufgehoben(Date genehmigungaufgehoben) {
        this.genehmigungaufgehoben = genehmigungaufgehoben;
    }

    public Date getAbnahmedatum() {
        return this.abnahmedatum;
    }
    public void setAbnahmedatum(Date abnahmedatum) {
        this.abnahmedatum = abnahmedatum;
    }

    public String getGenehmigungstitel() {
        return this.genehmigungstitel;
    }
    public void setGenehmigungstitel(String genehmigungstitel) {
        this.genehmigungstitel = genehmigungstitel;
    }

    public Boolean getGenehmigung() {
        return this.genehmigung;
    }
    public void setGenehmigung(Boolean genehmigung) {
        this.genehmigung = genehmigung;
    }

    public Integer getDurchsatz() {
        return this.durchsatz;
    }
    public void setDurchsatz(Integer durchsatz) {
        this.durchsatz = durchsatz;
    }

    public Integer getGesamtmengeEb() {
        return this.gesamtmengeEb;
    }
    public void setGesamtmengeEb(Integer gesamtmengeEb) {
        this.gesamtmengeEb = gesamtmengeEb;
    }

    public Boolean getOnlineentsilberung() {
        return this.onlineentsilberung;
    }
    public void setOnlineentsilberung(Boolean onlineentsilberung) {
        this.onlineentsilberung = onlineentsilberung;
    }

    public Boolean getAbwasser() {
        return this.abwasser;
    }
    public void setAbwasser(Boolean abwasser) {
        this.abwasser = abwasser;
    }

    public Date getAbwasserfrei() {
        return this.abwasserfrei;
    }
    public void setAbwasserfrei(Date abwasserfrei) {
        this.abwasserfrei = abwasserfrei;
    }

    public Date getKleiner200qm() {
        return this.kleiner200qm;
    }
    public void setKleiner200qm(Date kleiner200qm) {
        this.kleiner200qm = kleiner200qm;
    }

    public Boolean getBetriebAbgemeldet() {
        return this.betriebAbgemeldet;
    }
    public void setBetriebAbgemeldet(Boolean betriebAbgemeldet) {
        this.betriebAbgemeldet = betriebAbgemeldet;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public Boolean getBetriebstagebuch() {
        return this.betriebstagebuch;
    }
    public void setBetriebstagebuch(Boolean betriebstagebuch) {
        this.betriebstagebuch = betriebstagebuch;
    }

    public Boolean getWasseruhr() {
        return this.wasseruhr;
    }
    public void setWasseruhr(Boolean wasseruhr) {
        this.wasseruhr = wasseruhr;
    }

    public Integer getSpuelwassermenge() {
        return this.spuelwassermenge;
    }
    public void setSpuelwassermenge(Integer spuelwassermenge) {
        this.spuelwassermenge = spuelwassermenge;
    }

    public Integer getSpuelwasserverbrauch() {
        return this.spuelwasserverbrauch;
    }
    public void setSpuelwasserverbrauch(Integer spuelwasserverbrauch) {
        this.spuelwasserverbrauch = spuelwasserverbrauch;
    }

    public Boolean getWartungsvertrag() {
        return this.wartungsvertrag;
    }
    public void setWartungsvertrag(Boolean wartungsvertrag) {
        this.wartungsvertrag = wartungsvertrag;
    }

    public Boolean getGrgen() {
        return this.grgen;
    }
    public void setGrgen(Boolean grgen) {
        this.grgen = grgen;
    }

    public String getGenart() {
        return this.genart;
    }
    public void setGenart(String genart) {
        this.genart = genart;
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

