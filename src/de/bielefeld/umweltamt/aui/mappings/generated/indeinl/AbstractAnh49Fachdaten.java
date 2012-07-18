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
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link Anh49Fachdaten}.
 */
public abstract class AbstractAnh49Fachdaten  implements java.io.Serializable {

     private int objektid;
     private BasisObjekt basisObjekt;
     private String klaeranlage;
     private String name;
     private String bemerkungen;
     private String planquadrat;
     private Boolean abgemeldet;
     private String technikAnh49;
     private String technikAnh49Nr;
     private String sachkundelfa;
     private Boolean werkstatt;
     private Boolean bodeneinlaeufe;
     private Boolean waschanlagen;
     private String sonstiges;
     private String analysemonat;
     private Boolean abwasserfrei;
     private String anredeantragst;
     private String nameantragst;
     private String zusantragst;
     private String strasseantragst;
     private Integer hausnrantragst;
     private String hausnrzusantragst;
     private String plzantragst;
     private String ortantragst;
     private String sachbearbeiterIn;
     private String ansprechpartnerIn;
     private Date antragvom;
     private Date genehmigung;
     private Date wiedervorlage;
     private Date aenderungsgenehmigung;
     private Date letztesAnschreiben;
     private String anschreiben;
     private Boolean waschanlage;
     private Boolean ESatzung;
     private Date seitwann;
     private String sonstigestechnik;
     private Boolean maengel;
     private Boolean behoben;
     private Date frist;
     private Integer durchgefuehrt;
     private Date dekraTuevDatum;
     private boolean enabled;
     private boolean deleted;
     private Set<Anh49Kontrollen> anh49Kontrollens = new HashSet<Anh49Kontrollen>(0);
     private Set<Anh49Abscheiderdetails> anh49Abscheiderdetailses = new HashSet<Anh49Abscheiderdetails>(0);
     private Set<Anh49Analysen> anh49Analysens = new HashSet<Anh49Analysen>(0);
     private Set<Anh49Verwaltungsverf> anh49Verwaltungsverfs = new HashSet<Anh49Verwaltungsverf>(0);
     private Set<Anh49Ortstermine> anh49Ortstermines = new HashSet<Anh49Ortstermine>(0);

    public AbstractAnh49Fachdaten() {
    }

    public AbstractAnh49Fachdaten(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnh49Fachdaten(
    	BasisObjekt basisObjekt, String klaeranlage, String name, String bemerkungen, String planquadrat, Boolean abgemeldet, String technikAnh49, String technikAnh49Nr, String sachkundelfa, Boolean werkstatt, Boolean bodeneinlaeufe, Boolean waschanlagen, String sonstiges, String analysemonat, Boolean abwasserfrei, String anredeantragst, String nameantragst, String zusantragst, String strasseantragst, Integer hausnrantragst, String hausnrzusantragst, String plzantragst, String ortantragst, String sachbearbeiterIn, String ansprechpartnerIn, Date antragvom, Date genehmigung, Date wiedervorlage, Date aenderungsgenehmigung, Date letztesAnschreiben, String anschreiben, Boolean waschanlage, Boolean ESatzung, Date seitwann, String sonstigestechnik, Boolean maengel, Boolean behoben, Date frist, Integer durchgefuehrt, Date dekraTuevDatum, boolean enabled, boolean deleted, Set<Anh49Kontrollen> anh49Kontrollens, Set<Anh49Abscheiderdetails> anh49Abscheiderdetailses, Set<Anh49Analysen> anh49Analysens, Set<Anh49Verwaltungsverf> anh49Verwaltungsverfs, Set<Anh49Ortstermine> anh49Ortstermines) {
        this.basisObjekt = basisObjekt;
        this.klaeranlage = klaeranlage;
        this.name = name;
        this.bemerkungen = bemerkungen;
        this.planquadrat = planquadrat;
        this.abgemeldet = abgemeldet;
        this.technikAnh49 = technikAnh49;
        this.technikAnh49Nr = technikAnh49Nr;
        this.sachkundelfa = sachkundelfa;
        this.werkstatt = werkstatt;
        this.bodeneinlaeufe = bodeneinlaeufe;
        this.waschanlagen = waschanlagen;
        this.sonstiges = sonstiges;
        this.analysemonat = analysemonat;
        this.abwasserfrei = abwasserfrei;
        this.anredeantragst = anredeantragst;
        this.nameantragst = nameantragst;
        this.zusantragst = zusantragst;
        this.strasseantragst = strasseantragst;
        this.hausnrantragst = hausnrantragst;
        this.hausnrzusantragst = hausnrzusantragst;
        this.plzantragst = plzantragst;
        this.ortantragst = ortantragst;
        this.sachbearbeiterIn = sachbearbeiterIn;
        this.ansprechpartnerIn = ansprechpartnerIn;
        this.antragvom = antragvom;
        this.genehmigung = genehmigung;
        this.wiedervorlage = wiedervorlage;
        this.aenderungsgenehmigung = aenderungsgenehmigung;
        this.letztesAnschreiben = letztesAnschreiben;
        this.anschreiben = anschreiben;
        this.waschanlage = waschanlage;
        this.ESatzung = ESatzung;
        this.seitwann = seitwann;
        this.sonstigestechnik = sonstigestechnik;
        this.maengel = maengel;
        this.behoben = behoben;
        this.frist = frist;
        this.durchgefuehrt = durchgefuehrt;
        this.dekraTuevDatum = dekraTuevDatum;
        this.enabled = enabled;
        this.deleted = deleted;
        this.anh49Kontrollens = anh49Kontrollens;
        this.anh49Abscheiderdetailses = anh49Abscheiderdetailses;
        this.anh49Analysens = anh49Analysens;
        this.anh49Verwaltungsverfs = anh49Verwaltungsverfs;
        this.anh49Ortstermines = anh49Ortstermines;
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

    public String getKlaeranlage() {
        return this.klaeranlage;
    }
    public void setKlaeranlage(String klaeranlage) {
        this.klaeranlage = klaeranlage;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getPlanquadrat() {
        return this.planquadrat;
    }
    public void setPlanquadrat(String planquadrat) {
        this.planquadrat = planquadrat;
    }

    public Boolean getAbgemeldet() {
        return this.abgemeldet;
    }
    public void setAbgemeldet(Boolean abgemeldet) {
        this.abgemeldet = abgemeldet;
    }

    public String getTechnikAnh49() {
        return this.technikAnh49;
    }
    public void setTechnikAnh49(String technikAnh49) {
        this.technikAnh49 = technikAnh49;
    }

    public String getTechnikAnh49Nr() {
        return this.technikAnh49Nr;
    }
    public void setTechnikAnh49Nr(String technikAnh49Nr) {
        this.technikAnh49Nr = technikAnh49Nr;
    }

    public String getSachkundelfa() {
        return this.sachkundelfa;
    }
    public void setSachkundelfa(String sachkundelfa) {
        this.sachkundelfa = sachkundelfa;
    }

    public Boolean getWerkstatt() {
        return this.werkstatt;
    }
    public void setWerkstatt(Boolean werkstatt) {
        this.werkstatt = werkstatt;
    }

    public Boolean getBodeneinlaeufe() {
        return this.bodeneinlaeufe;
    }
    public void setBodeneinlaeufe(Boolean bodeneinlaeufe) {
        this.bodeneinlaeufe = bodeneinlaeufe;
    }

    public Boolean getWaschanlagen() {
        return this.waschanlagen;
    }
    public void setWaschanlagen(Boolean waschanlagen) {
        this.waschanlagen = waschanlagen;
    }

    public String getSonstiges() {
        return this.sonstiges;
    }
    public void setSonstiges(String sonstiges) {
        this.sonstiges = sonstiges;
    }

    public String getAnalysemonat() {
        return this.analysemonat;
    }
    public void setAnalysemonat(String analysemonat) {
        this.analysemonat = analysemonat;
    }

    public Boolean getAbwasserfrei() {
        return this.abwasserfrei;
    }
    public void setAbwasserfrei(Boolean abwasserfrei) {
        this.abwasserfrei = abwasserfrei;
    }

    public String getAnredeantragst() {
        return this.anredeantragst;
    }
    public void setAnredeantragst(String anredeantragst) {
        this.anredeantragst = anredeantragst;
    }

    public String getNameantragst() {
        return this.nameantragst;
    }
    public void setNameantragst(String nameantragst) {
        this.nameantragst = nameantragst;
    }

    public String getZusantragst() {
        return this.zusantragst;
    }
    public void setZusantragst(String zusantragst) {
        this.zusantragst = zusantragst;
    }

    public String getStrasseantragst() {
        return this.strasseantragst;
    }
    public void setStrasseantragst(String strasseantragst) {
        this.strasseantragst = strasseantragst;
    }

    public Integer getHausnrantragst() {
        return this.hausnrantragst;
    }
    public void setHausnrantragst(Integer hausnrantragst) {
        this.hausnrantragst = hausnrantragst;
    }

    public String getHausnrzusantragst() {
        return this.hausnrzusantragst;
    }
    public void setHausnrzusantragst(String hausnrzusantragst) {
        this.hausnrzusantragst = hausnrzusantragst;
    }

    public String getPlzantragst() {
        return this.plzantragst;
    }
    public void setPlzantragst(String plzantragst) {
        this.plzantragst = plzantragst;
    }

    public String getOrtantragst() {
        return this.ortantragst;
    }
    public void setOrtantragst(String ortantragst) {
        this.ortantragst = ortantragst;
    }

    public String getSachbearbeiterIn() {
        return this.sachbearbeiterIn;
    }
    public void setSachbearbeiterIn(String sachbearbeiterIn) {
        this.sachbearbeiterIn = sachbearbeiterIn;
    }

    public String getAnsprechpartnerIn() {
        return this.ansprechpartnerIn;
    }
    public void setAnsprechpartnerIn(String ansprechpartnerIn) {
        this.ansprechpartnerIn = ansprechpartnerIn;
    }

    public Date getAntragvom() {
        return this.antragvom;
    }
    public void setAntragvom(Date antragvom) {
        this.antragvom = antragvom;
    }

    public Date getGenehmigung() {
        return this.genehmigung;
    }
    public void setGenehmigung(Date genehmigung) {
        this.genehmigung = genehmigung;
    }

    public Date getWiedervorlage() {
        return this.wiedervorlage;
    }
    public void setWiedervorlage(Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    public Date getAenderungsgenehmigung() {
        return this.aenderungsgenehmigung;
    }
    public void setAenderungsgenehmigung(Date aenderungsgenehmigung) {
        this.aenderungsgenehmigung = aenderungsgenehmigung;
    }

    public Date getLetztesAnschreiben() {
        return this.letztesAnschreiben;
    }
    public void setLetztesAnschreiben(Date letztesAnschreiben) {
        this.letztesAnschreiben = letztesAnschreiben;
    }

    public String getAnschreiben() {
        return this.anschreiben;
    }
    public void setAnschreiben(String anschreiben) {
        this.anschreiben = anschreiben;
    }

    public Boolean getWaschanlage() {
        return this.waschanlage;
    }
    public void setWaschanlage(Boolean waschanlage) {
        this.waschanlage = waschanlage;
    }

    public Boolean getESatzung() {
        return this.ESatzung;
    }
    public void setESatzung(Boolean ESatzung) {
        this.ESatzung = ESatzung;
    }

    public Date getSeitwann() {
        return this.seitwann;
    }
    public void setSeitwann(Date seitwann) {
        this.seitwann = seitwann;
    }

    public String getSonstigestechnik() {
        return this.sonstigestechnik;
    }
    public void setSonstigestechnik(String sonstigestechnik) {
        this.sonstigestechnik = sonstigestechnik;
    }

    public Boolean getMaengel() {
        return this.maengel;
    }
    public void setMaengel(Boolean maengel) {
        this.maengel = maengel;
    }

    public Boolean getBehoben() {
        return this.behoben;
    }
    public void setBehoben(Boolean behoben) {
        this.behoben = behoben;
    }

    public Date getFrist() {
        return this.frist;
    }
    public void setFrist(Date frist) {
        this.frist = frist;
    }

    public Integer getDurchgefuehrt() {
        return this.durchgefuehrt;
    }
    public void setDurchgefuehrt(Integer durchgefuehrt) {
        this.durchgefuehrt = durchgefuehrt;
    }

    public Date getDekraTuevDatum() {
        return this.dekraTuevDatum;
    }
    public void setDekraTuevDatum(Date dekraTuevDatum) {
        this.dekraTuevDatum = dekraTuevDatum;
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

    public Set<Anh49Kontrollen> getAnh49Kontrollens() {
        return this.anh49Kontrollens;
    }
    public void setAnh49Kontrollens(Set<Anh49Kontrollen> anh49Kontrollens) {
        this.anh49Kontrollens = anh49Kontrollens;
    }

    public Set<Anh49Abscheiderdetails> getAnh49Abscheiderdetailses() {
        return this.anh49Abscheiderdetailses;
    }
    public void setAnh49Abscheiderdetailses(Set<Anh49Abscheiderdetails> anh49Abscheiderdetailses) {
        this.anh49Abscheiderdetailses = anh49Abscheiderdetailses;
    }

    public Set<Anh49Analysen> getAnh49Analysens() {
        return this.anh49Analysens;
    }
    public void setAnh49Analysens(Set<Anh49Analysen> anh49Analysens) {
        this.anh49Analysens = anh49Analysens;
    }

    public Set<Anh49Verwaltungsverf> getAnh49Verwaltungsverfs() {
        return this.anh49Verwaltungsverfs;
    }
    public void setAnh49Verwaltungsverfs(Set<Anh49Verwaltungsverf> anh49Verwaltungsverfs) {
        this.anh49Verwaltungsverfs = anh49Verwaltungsverfs;
    }

    public Set<Anh49Ortstermine> getAnh49Ortstermines() {
        return this.anh49Ortstermines;
    }
    public void setAnh49Ortstermines(Set<Anh49Ortstermine> anh49Ortstermines) {
        this.anh49Ortstermines = anh49Ortstermines;
    }

}
