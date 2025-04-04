/**
 * Copyright 2005-2042, Stadt Bielefeld
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

// Generated by Hibernate Tools 5.0.0.Final

package de.bielefeld.umweltamt.aui.mappings.awsv;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.awsv.Abscheider;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a row in the Abscheider database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Abscheider  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAbscheider;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Fachdaten fachdaten;
    private String sfhersteller;
    private String sftyp;
    private String sfvolumen;
    private String sfmaterial;
    private String sfbeschichtung;
    private String abhersteller;
    private String abtyp;
    private String abpruefz;
    private String abmaterial;
    private String abbeschichtung;
    private String abnenngr;
    private String zuldn;
    private String zulmaterial;
    private String zullaenge;
    private String verbdn;
    private String verbmaterial;
    private String verblaenge;
    private String sondn;
    private String sonmaterial;
    private String sonlaenge;
    private String oelspeichervol;
    private Boolean kompaktanlage;
    private Boolean sf;
    private Boolean kkl1;
    private Boolean lfkl2;
    private Boolean ps;
    private Boolean ueberhausr;
    private Boolean waschanlvorh;
    private Boolean abgabe;
    private Boolean hlzapfanl;
    private Boolean belvonlagerbh;
    private Boolean rueckhalteausr;
    private boolean enabled;
    private boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Abscheider() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Abscheider(
        Integer id, Fachdaten fachdaten, boolean enabled, boolean deleted) {
        this.id = id;
        this.fachdaten = fachdaten;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Abscheider(
        Integer id, Fachdaten fachdaten, String sfhersteller, String sftyp, String sfvolumen, String sfmaterial, String sfbeschichtung, String abhersteller, String abtyp, String abpruefz, String abmaterial, String abbeschichtung, String abnenngr, String zuldn, String zulmaterial, String zullaenge, String verbdn, String verbmaterial, String verblaenge, String sondn, String sonmaterial, String sonlaenge, String oelspeichervol, Boolean kompaktanlage, Boolean sf, Boolean kkl1, Boolean lfkl2, Boolean ps, Boolean ueberhausr, Boolean waschanlvorh, Boolean abgabe, Boolean hlzapfanl, Boolean belvonlagerbh, Boolean rueckhalteausr, boolean enabled, boolean deleted) {
        this.id = id;
        this.fachdaten = fachdaten;
        this.sfhersteller = sfhersteller;
        this.sftyp = sftyp;
        this.sfvolumen = sfvolumen;
        this.sfmaterial = sfmaterial;
        this.sfbeschichtung = sfbeschichtung;
        this.abhersteller = abhersteller;
        this.abtyp = abtyp;
        this.abpruefz = abpruefz;
        this.abmaterial = abmaterial;
        this.abbeschichtung = abbeschichtung;
        this.abnenngr = abnenngr;
        this.zuldn = zuldn;
        this.zulmaterial = zulmaterial;
        this.zullaenge = zullaenge;
        this.verbdn = verbdn;
        this.verbmaterial = verbmaterial;
        this.verblaenge = verblaenge;
        this.sondn = sondn;
        this.sonmaterial = sonmaterial;
        this.sonlaenge = sonlaenge;
        this.oelspeichervol = oelspeichervol;
        this.kompaktanlage = kompaktanlage;
        this.sf = sf;
        this.kkl1 = kkl1;
        this.lfkl2 = lfkl2;
        this.ps = ps;
        this.ueberhausr = ueberhausr;
        this.waschanlvorh = waschanlvorh;
        this.abgabe = abgabe;
        this.hlzapfanl = hlzapfanl;
        this.belvonlagerbh = belvonlagerbh;
        this.rueckhalteausr = rueckhalteausr;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fachdaten getFachdaten() {
        return this.fachdaten;
    }

    public void setFachdaten(Fachdaten fachdaten) {
        this.fachdaten = fachdaten;
    }

    public String getSfhersteller() {
        return this.sfhersteller;
    }

    public void setSfhersteller(String sfhersteller) {
        this.sfhersteller = sfhersteller;
    }

    public String getSftyp() {
        return this.sftyp;
    }

    public void setSftyp(String sftyp) {
        this.sftyp = sftyp;
    }

    public String getSfvolumen() {
        return this.sfvolumen;
    }

    public void setSfvolumen(String sfvolumen) {
        this.sfvolumen = sfvolumen;
    }

    public String getSfmaterial() {
        return this.sfmaterial;
    }

    public void setSfmaterial(String sfmaterial) {
        this.sfmaterial = sfmaterial;
    }

    public String getSfbeschichtung() {
        return this.sfbeschichtung;
    }

    public void setSfbeschichtung(String sfbeschichtung) {
        this.sfbeschichtung = sfbeschichtung;
    }

    public String getAbhersteller() {
        return this.abhersteller;
    }

    public void setAbhersteller(String abhersteller) {
        this.abhersteller = abhersteller;
    }

    public String getAbtyp() {
        return this.abtyp;
    }

    public void setAbtyp(String abtyp) {
        this.abtyp = abtyp;
    }

    public String getAbpruefz() {
        return this.abpruefz;
    }

    public void setAbpruefz(String abpruefz) {
        this.abpruefz = abpruefz;
    }

    public String getAbmaterial() {
        return this.abmaterial;
    }

    public void setAbmaterial(String abmaterial) {
        this.abmaterial = abmaterial;
    }

    public String getAbbeschichtung() {
        return this.abbeschichtung;
    }

    public void setAbbeschichtung(String abbeschichtung) {
        this.abbeschichtung = abbeschichtung;
    }

    public String getAbnenngr() {
        return this.abnenngr;
    }

    public void setAbnenngr(String abnenngr) {
        this.abnenngr = abnenngr;
    }

    public String getZuldn() {
        return this.zuldn;
    }

    public void setZuldn(String zuldn) {
        this.zuldn = zuldn;
    }

    public String getZulmaterial() {
        return this.zulmaterial;
    }

    public void setZulmaterial(String zulmaterial) {
        this.zulmaterial = zulmaterial;
    }

    public String getZullaenge() {
        return this.zullaenge;
    }

    public void setZullaenge(String zullaenge) {
        this.zullaenge = zullaenge;
    }

    public String getVerbdn() {
        return this.verbdn;
    }

    public void setVerbdn(String verbdn) {
        this.verbdn = verbdn;
    }

    public String getVerbmaterial() {
        return this.verbmaterial;
    }

    public void setVerbmaterial(String verbmaterial) {
        this.verbmaterial = verbmaterial;
    }

    public String getVerblaenge() {
        return this.verblaenge;
    }

    public void setVerblaenge(String verblaenge) {
        this.verblaenge = verblaenge;
    }

    public String getSondn() {
        return this.sondn;
    }

    public void setSondn(String sondn) {
        this.sondn = sondn;
    }

    public String getSonmaterial() {
        return this.sonmaterial;
    }

    public void setSonmaterial(String sonmaterial) {
        this.sonmaterial = sonmaterial;
    }

    public String getSonlaenge() {
        return this.sonlaenge;
    }

    public void setSonlaenge(String sonlaenge) {
        this.sonlaenge = sonlaenge;
    }

    public String getOelspeichervol() {
        return this.oelspeichervol;
    }

    public void setOelspeichervol(String oelspeichervol) {
        this.oelspeichervol = oelspeichervol;
    }

    public Boolean getKompaktanlage() {
        return this.kompaktanlage;
    }

    public void setKompaktanlage(Boolean kompaktanlage) {
        this.kompaktanlage = kompaktanlage;
    }

    public Boolean getSf() {
        return this.sf;
    }

    public void setSf(Boolean sf) {
        this.sf = sf;
    }

    public Boolean getKkl1() {
        return this.kkl1;
    }

    public void setKkl1(Boolean kkl1) {
        this.kkl1 = kkl1;
    }

    public Boolean getLfkl2() {
        return this.lfkl2;
    }

    public void setLfkl2(Boolean lfkl2) {
        this.lfkl2 = lfkl2;
    }

    public Boolean getPs() {
        return this.ps;
    }

    public void setPs(Boolean ps) {
        this.ps = ps;
    }

    public Boolean getUeberhausr() {
        return this.ueberhausr;
    }

    public void setUeberhausr(Boolean ueberhausr) {
        this.ueberhausr = ueberhausr;
    }

    public Boolean getWaschanlvorh() {
        return this.waschanlvorh;
    }

    public void setWaschanlvorh(Boolean waschanlvorh) {
        this.waschanlvorh = waschanlvorh;
    }

    public Boolean getAbgabe() {
        return this.abgabe;
    }

    public void setAbgabe(Boolean abgabe) {
        this.abgabe = abgabe;
    }

    public Boolean getHlzapfanl() {
        return this.hlzapfanl;
    }

    public void setHlzapfanl(Boolean hlzapfanl) {
        this.hlzapfanl = hlzapfanl;
    }

    public Boolean getBelvonlagerbh() {
        return this.belvonlagerbh;
    }

    public void setBelvonlagerbh(Boolean belvonlagerbh) {
        this.belvonlagerbh = belvonlagerbh;
    }

    public Boolean getRueckhalteausr() {
        return this.rueckhalteausr;
    }

    public void setRueckhalteausr(Boolean rueckhalteausr) {
        this.rueckhalteausr = rueckhalteausr;
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

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this);
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("id").append("='").append(getId()).append("' ");
        buffer.append("fachdaten").append("='").append(getFachdaten()).append("' ");
        buffer.append("sfhersteller").append("='").append(getSfhersteller()).append("' ");
        buffer.append("sftyp").append("='").append(getSftyp()).append("' ");
        buffer.append("sfvolumen").append("='").append(getSfvolumen()).append("' ");
        buffer.append("sfmaterial").append("='").append(getSfmaterial()).append("' ");
        buffer.append("sfbeschichtung").append("='").append(getSfbeschichtung()).append("' ");
        buffer.append("abhersteller").append("='").append(getAbhersteller()).append("' ");
        buffer.append("abtyp").append("='").append(getAbtyp()).append("' ");
        buffer.append("abpruefz").append("='").append(getAbpruefz()).append("' ");
        buffer.append("abmaterial").append("='").append(getAbmaterial()).append("' ");
        buffer.append("abbeschichtung").append("='").append(getAbbeschichtung()).append("' ");
        buffer.append("abnenngr").append("='").append(getAbnenngr()).append("' ");
        buffer.append("zuldn").append("='").append(getZuldn()).append("' ");
        buffer.append("zulmaterial").append("='").append(getZulmaterial()).append("' ");
        buffer.append("zullaenge").append("='").append(getZullaenge()).append("' ");
        buffer.append("verbdn").append("='").append(getVerbdn()).append("' ");
        buffer.append("verbmaterial").append("='").append(getVerbmaterial()).append("' ");
        buffer.append("verblaenge").append("='").append(getVerblaenge()).append("' ");
        buffer.append("sondn").append("='").append(getSondn()).append("' ");
        buffer.append("sonmaterial").append("='").append(getSonmaterial()).append("' ");
        buffer.append("sonlaenge").append("='").append(getSonlaenge()).append("' ");
        buffer.append("oelspeichervol").append("='").append(getOelspeichervol()).append("' ");
        buffer.append("kompaktanlage").append("='").append(getKompaktanlage()).append("' ");
        buffer.append("sf").append("='").append(getSf()).append("' ");
        buffer.append("kkl1").append("='").append(getKkl1()).append("' ");
        buffer.append("lfkl2").append("='").append(getLfkl2()).append("' ");
        buffer.append("ps").append("='").append(getPs()).append("' ");
        buffer.append("ueberhausr").append("='").append(getUeberhausr()).append("' ");
        buffer.append("waschanlvorh").append("='").append(getWaschanlvorh()).append("' ");
        buffer.append("abgabe").append("='").append(getAbgabe()).append("' ");
        buffer.append("hlzapfanl").append("='").append(getHlzapfanl()).append("' ");
        buffer.append("belvonlagerbh").append("='").append(getBelvonlagerbh()).append("' ");
        buffer.append("rueckhalteausr").append("='").append(getRueckhalteausr()).append("' ");
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    /**
     * @param other
     * @return <code>true</code>, if this and other are equal,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof Abscheider)) return false;
        return (this.getId().equals(
            ((Abscheider) other).getId()));
    }

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.getId() == null ?
            0 : this.getId().hashCode();
        result = result * 37 + idValue;
        return result;
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>Abscheider</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Abscheider merge(Abscheider detachedInstance) {
        log.debug("Merging Abscheider instance " + detachedInstance);
        return (Abscheider) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Abscheider saved = Abscheider.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Abscheider with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Abscheider
     */
    private void copy(Abscheider copy) {
        this.id = copy.getId();
        this.fachdaten = copy.getFachdaten();
        this.sfhersteller = copy.getSfhersteller();
        this.sftyp = copy.getSftyp();
        this.sfvolumen = copy.getSfvolumen();
        this.sfmaterial = copy.getSfmaterial();
        this.sfbeschichtung = copy.getSfbeschichtung();
        this.abhersteller = copy.getAbhersteller();
        this.abtyp = copy.getAbtyp();
        this.abpruefz = copy.getAbpruefz();
        this.abmaterial = copy.getAbmaterial();
        this.abbeschichtung = copy.getAbbeschichtung();
        this.abnenngr = copy.getAbnenngr();
        this.zuldn = copy.getZuldn();
        this.zulmaterial = copy.getZulmaterial();
        this.zullaenge = copy.getZullaenge();
        this.verbdn = copy.getVerbdn();
        this.verbmaterial = copy.getVerbmaterial();
        this.verblaenge = copy.getVerblaenge();
        this.sondn = copy.getSondn();
        this.sonmaterial = copy.getSonmaterial();
        this.sonlaenge = copy.getSonlaenge();
        this.oelspeichervol = copy.getOelspeichervol();
        this.kompaktanlage = copy.getKompaktanlage();
        this.sf = copy.getSf();
        this.kkl1 = copy.getKkl1();
        this.lfkl2 = copy.getLfkl2();
        this.ps = copy.getPs();
        this.ueberhausr = copy.getUeberhausr();
        this.waschanlvorh = copy.getWaschanlvorh();
        this.abgabe = copy.getAbgabe();
        this.hlzapfanl = copy.getHlzapfanl();
        this.belvonlagerbh = copy.getBelvonlagerbh();
        this.rueckhalteausr = copy.getRueckhalteausr();
        this.enabled = copy.isEnabled();
        this.deleted = copy.isDeleted();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Abscheider detachedInstance) {
        log.debug("Deleting Abscheider instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Abscheider.delete(this);
    }

    /**
     * Find an <code>Abscheider</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Abscheider</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Abscheider findById(java.lang.Integer id) {
        log.debug("Getting Abscheider instance with id: " + id);
        return (Abscheider)
            new DatabaseAccess().get(Abscheider.class, id);
    }

    /**
     * Get a list of all <code>Abscheider</code>
     * @return <code>List&lt;Abscheider&gt;</code>
     *         all <code>Abscheider</code>
     */
    public static List<Abscheider> getAll() {
        return DatabaseQuery.getAll(new Abscheider());
    }

    /* Custom code goes below here! */

    public static Abscheider findByBehaelterid(java.lang.Integer id){
        Fachdaten fd = (Fachdaten) HibernateSessionFactory.currentSession().createQuery("from Fachdaten where behaelterid= " + id).list().get(0);
        Set<Abscheider> list = fd.getAbscheiders();
		if (list.size() != 0) {
			return list.iterator().next();
		}
		return null;
    }
}
