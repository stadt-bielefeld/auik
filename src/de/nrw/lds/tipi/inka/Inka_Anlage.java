/**
 * Inka_Anlage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Anlage  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Boolean abfuhr_unbeh_jn;

    private java.lang.Boolean abscheider_jn;

    private java.lang.Boolean absorption_jn;

    private java.lang.Integer adresse_anspr_nr;

    private java.lang.Integer adresse_anspr_ver;

    private java.lang.String aktenzeichen;

    private java.lang.Boolean anaerobe_vorb_jn;

    private java.lang.Boolean anl_bauaufs_zul_jn;

    private java.lang.Boolean anl_baurecht_pr_jn;

    private java.lang.Boolean anl_din_ce_jn;

    private java.lang.Boolean anl_einzelabn_jn;

    private java.lang.Boolean anl_ohne_zul_jn;

    private java.lang.Integer anlage_nr;

    private java.lang.Integer anlage_ver;

    private java.lang.Boolean ausgleichsbecken_jn;

    private java.util.Calendar befristung_bis;

    private java.lang.Boolean befristung_jn;

    private java.lang.String behoerden_id;

    private java.lang.Integer behoerden_ver;

    private java.lang.Boolean bel_c_eli_jn;

    private java.lang.Boolean bel_n_eli_jn;

    private java.lang.Integer betriebseinrichtung_nr;

    private java.lang.Integer betriebseinrichtung_ver;

    private java.lang.Boolean biolog_p_eli_jn;

    private java.util.Calendar dat_genehmigung;

    private java.util.Calendar dat_inbetrieb;

    private java.lang.Boolean emulsionsspalt_jn;

    private java.lang.Boolean extraktion_jn;

    private java.lang.Boolean filtration_jn;

    private java.lang.Boolean flockung_jn;

    private java.lang.Boolean flotation_jn;

    private java.lang.Integer gemeinde_ver;

    private java.lang.String gemeindekennzahl;

    private java.lang.Integer gen_art;

    private java.lang.Boolean gen_pflichtig_jn;

    private java.lang.Integer genehmigung_nr;

    private java.lang.Integer genehmigung_ver;

    private java.lang.Boolean ionenaustausch_jn;

    private java.lang.Boolean kammerfilterp_jn;

    private java.lang.Boolean masch_entw_jn;

    private java.lang.Boolean membranfiltration_jn;

    private java.lang.Boolean nachklaerung_jn;

    private java.lang.Boolean nat_entw_jn;

    private java.lang.Boolean neutralisation_jn;

    private java.lang.Boolean schlammfang_jn;

    private java.lang.Boolean sieb_rechen_jn;

    private java.lang.Boolean siebbandp_jn;

    private java.lang.Boolean sonst_abscheider_jn;

    private java.lang.String sonstige_biol_verf;

    private java.lang.String sonstige_mech_verf;

    private java.lang.String sonstige_schlamm_beh;

    private java.lang.Boolean stat_entw_jn;

    private java.lang.Boolean stripp_anlage_jn;

    private java.lang.Integer stua_bezirk;

    private java.lang.Integer stua_ver;

    private java.lang.Boolean tropfkoerper_jn;

    private java.lang.Integer uebergabestelle_lfd_nr;

    private java.lang.Integer uebergabestelle_ver;

    private java.lang.Boolean vakuumfilter_jn;

    private java.lang.Boolean vorklaerung_absetz_jn;

    private java.lang.String was_behoerden_id;

    private java.lang.Integer was_behoerden_ver;

    private java.lang.Boolean zentrifuge_jn;

    public Inka_Anlage() {
    }

    public Inka_Anlage(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Boolean abfuhr_unbeh_jn,
           java.lang.Boolean abscheider_jn,
           java.lang.Boolean absorption_jn,
           java.lang.Integer adresse_anspr_nr,
           java.lang.Integer adresse_anspr_ver,
           java.lang.String aktenzeichen,
           java.lang.Boolean anaerobe_vorb_jn,
           java.lang.Boolean anl_bauaufs_zul_jn,
           java.lang.Boolean anl_baurecht_pr_jn,
           java.lang.Boolean anl_din_ce_jn,
           java.lang.Boolean anl_einzelabn_jn,
           java.lang.Boolean anl_ohne_zul_jn,
           java.lang.Integer anlage_nr,
           java.lang.Integer anlage_ver,
           java.lang.Boolean ausgleichsbecken_jn,
           java.util.Calendar befristung_bis,
           java.lang.Boolean befristung_jn,
           java.lang.String behoerden_id,
           java.lang.Integer behoerden_ver,
           java.lang.Boolean bel_c_eli_jn,
           java.lang.Boolean bel_n_eli_jn,
           java.lang.Integer betriebseinrichtung_nr,
           java.lang.Integer betriebseinrichtung_ver,
           java.lang.Boolean biolog_p_eli_jn,
           java.util.Calendar dat_genehmigung,
           java.util.Calendar dat_inbetrieb,
           java.lang.Boolean emulsionsspalt_jn,
           java.lang.Boolean extraktion_jn,
           java.lang.Boolean filtration_jn,
           java.lang.Boolean flockung_jn,
           java.lang.Boolean flotation_jn,
           java.lang.Integer gemeinde_ver,
           java.lang.String gemeindekennzahl,
           java.lang.Integer gen_art,
           java.lang.Boolean gen_pflichtig_jn,
           java.lang.Integer genehmigung_nr,
           java.lang.Integer genehmigung_ver,
           java.lang.Boolean ionenaustausch_jn,
           java.lang.Boolean kammerfilterp_jn,
           java.lang.Boolean masch_entw_jn,
           java.lang.Boolean membranfiltration_jn,
           java.lang.Boolean nachklaerung_jn,
           java.lang.Boolean nat_entw_jn,
           java.lang.Boolean neutralisation_jn,
           java.lang.Boolean schlammfang_jn,
           java.lang.Boolean sieb_rechen_jn,
           java.lang.Boolean siebbandp_jn,
           java.lang.Boolean sonst_abscheider_jn,
           java.lang.String sonstige_biol_verf,
           java.lang.String sonstige_mech_verf,
           java.lang.String sonstige_schlamm_beh,
           java.lang.Boolean stat_entw_jn,
           java.lang.Boolean stripp_anlage_jn,
           java.lang.Integer stua_bezirk,
           java.lang.Integer stua_ver,
           java.lang.Boolean tropfkoerper_jn,
           java.lang.Integer uebergabestelle_lfd_nr,
           java.lang.Integer uebergabestelle_ver,
           java.lang.Boolean vakuumfilter_jn,
           java.lang.Boolean vorklaerung_absetz_jn,
           java.lang.String was_behoerden_id,
           java.lang.Integer was_behoerden_ver,
           java.lang.Boolean zentrifuge_jn) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.abfuhr_unbeh_jn = abfuhr_unbeh_jn;
        this.abscheider_jn = abscheider_jn;
        this.absorption_jn = absorption_jn;
        this.adresse_anspr_nr = adresse_anspr_nr;
        this.adresse_anspr_ver = adresse_anspr_ver;
        this.aktenzeichen = aktenzeichen;
        this.anaerobe_vorb_jn = anaerobe_vorb_jn;
        this.anl_bauaufs_zul_jn = anl_bauaufs_zul_jn;
        this.anl_baurecht_pr_jn = anl_baurecht_pr_jn;
        this.anl_din_ce_jn = anl_din_ce_jn;
        this.anl_einzelabn_jn = anl_einzelabn_jn;
        this.anl_ohne_zul_jn = anl_ohne_zul_jn;
        this.anlage_nr = anlage_nr;
        this.anlage_ver = anlage_ver;
        this.ausgleichsbecken_jn = ausgleichsbecken_jn;
        this.befristung_bis = befristung_bis;
        this.befristung_jn = befristung_jn;
        this.behoerden_id = behoerden_id;
        this.behoerden_ver = behoerden_ver;
        this.bel_c_eli_jn = bel_c_eli_jn;
        this.bel_n_eli_jn = bel_n_eli_jn;
        this.betriebseinrichtung_nr = betriebseinrichtung_nr;
        this.betriebseinrichtung_ver = betriebseinrichtung_ver;
        this.biolog_p_eli_jn = biolog_p_eli_jn;
        this.dat_genehmigung = dat_genehmigung;
        this.dat_inbetrieb = dat_inbetrieb;
        this.emulsionsspalt_jn = emulsionsspalt_jn;
        this.extraktion_jn = extraktion_jn;
        this.filtration_jn = filtration_jn;
        this.flockung_jn = flockung_jn;
        this.flotation_jn = flotation_jn;
        this.gemeinde_ver = gemeinde_ver;
        this.gemeindekennzahl = gemeindekennzahl;
        this.gen_art = gen_art;
        this.gen_pflichtig_jn = gen_pflichtig_jn;
        this.genehmigung_nr = genehmigung_nr;
        this.genehmigung_ver = genehmigung_ver;
        this.ionenaustausch_jn = ionenaustausch_jn;
        this.kammerfilterp_jn = kammerfilterp_jn;
        this.masch_entw_jn = masch_entw_jn;
        this.membranfiltration_jn = membranfiltration_jn;
        this.nachklaerung_jn = nachklaerung_jn;
        this.nat_entw_jn = nat_entw_jn;
        this.neutralisation_jn = neutralisation_jn;
        this.schlammfang_jn = schlammfang_jn;
        this.sieb_rechen_jn = sieb_rechen_jn;
        this.siebbandp_jn = siebbandp_jn;
        this.sonst_abscheider_jn = sonst_abscheider_jn;
        this.sonstige_biol_verf = sonstige_biol_verf;
        this.sonstige_mech_verf = sonstige_mech_verf;
        this.sonstige_schlamm_beh = sonstige_schlamm_beh;
        this.stat_entw_jn = stat_entw_jn;
        this.stripp_anlage_jn = stripp_anlage_jn;
        this.stua_bezirk = stua_bezirk;
        this.stua_ver = stua_ver;
        this.tropfkoerper_jn = tropfkoerper_jn;
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
        this.uebergabestelle_ver = uebergabestelle_ver;
        this.vakuumfilter_jn = vakuumfilter_jn;
        this.vorklaerung_absetz_jn = vorklaerung_absetz_jn;
        this.was_behoerden_id = was_behoerden_id;
        this.was_behoerden_ver = was_behoerden_ver;
        this.zentrifuge_jn = zentrifuge_jn;
    }


    /**
     * Gets the abfuhr_unbeh_jn value for this Inka_Anlage.
     * 
     * @return abfuhr_unbeh_jn
     */
    public java.lang.Boolean getAbfuhr_unbeh_jn() {
        return abfuhr_unbeh_jn;
    }


    /**
     * Sets the abfuhr_unbeh_jn value for this Inka_Anlage.
     * 
     * @param abfuhr_unbeh_jn
     */
    public void setAbfuhr_unbeh_jn(java.lang.Boolean abfuhr_unbeh_jn) {
        this.abfuhr_unbeh_jn = abfuhr_unbeh_jn;
    }


    /**
     * Gets the abscheider_jn value for this Inka_Anlage.
     * 
     * @return abscheider_jn
     */
    public java.lang.Boolean getAbscheider_jn() {
        return abscheider_jn;
    }


    /**
     * Sets the abscheider_jn value for this Inka_Anlage.
     * 
     * @param abscheider_jn
     */
    public void setAbscheider_jn(java.lang.Boolean abscheider_jn) {
        this.abscheider_jn = abscheider_jn;
    }


    /**
     * Gets the absorption_jn value for this Inka_Anlage.
     * 
     * @return absorption_jn
     */
    public java.lang.Boolean getAbsorption_jn() {
        return absorption_jn;
    }


    /**
     * Sets the absorption_jn value for this Inka_Anlage.
     * 
     * @param absorption_jn
     */
    public void setAbsorption_jn(java.lang.Boolean absorption_jn) {
        this.absorption_jn = absorption_jn;
    }


    /**
     * Gets the adresse_anspr_nr value for this Inka_Anlage.
     * 
     * @return adresse_anspr_nr
     */
    public java.lang.Integer getAdresse_anspr_nr() {
        return adresse_anspr_nr;
    }


    /**
     * Sets the adresse_anspr_nr value for this Inka_Anlage.
     * 
     * @param adresse_anspr_nr
     */
    public void setAdresse_anspr_nr(java.lang.Integer adresse_anspr_nr) {
        this.adresse_anspr_nr = adresse_anspr_nr;
    }


    /**
     * Gets the adresse_anspr_ver value for this Inka_Anlage.
     * 
     * @return adresse_anspr_ver
     */
    public java.lang.Integer getAdresse_anspr_ver() {
        return adresse_anspr_ver;
    }


    /**
     * Sets the adresse_anspr_ver value for this Inka_Anlage.
     * 
     * @param adresse_anspr_ver
     */
    public void setAdresse_anspr_ver(java.lang.Integer adresse_anspr_ver) {
        this.adresse_anspr_ver = adresse_anspr_ver;
    }


    /**
     * Gets the aktenzeichen value for this Inka_Anlage.
     * 
     * @return aktenzeichen
     */
    public java.lang.String getAktenzeichen() {
        return aktenzeichen;
    }


    /**
     * Sets the aktenzeichen value for this Inka_Anlage.
     * 
     * @param aktenzeichen
     */
    public void setAktenzeichen(java.lang.String aktenzeichen) {
        this.aktenzeichen = aktenzeichen;
    }


    /**
     * Gets the anaerobe_vorb_jn value for this Inka_Anlage.
     * 
     * @return anaerobe_vorb_jn
     */
    public java.lang.Boolean getAnaerobe_vorb_jn() {
        return anaerobe_vorb_jn;
    }


    /**
     * Sets the anaerobe_vorb_jn value for this Inka_Anlage.
     * 
     * @param anaerobe_vorb_jn
     */
    public void setAnaerobe_vorb_jn(java.lang.Boolean anaerobe_vorb_jn) {
        this.anaerobe_vorb_jn = anaerobe_vorb_jn;
    }


    /**
     * Gets the anl_bauaufs_zul_jn value for this Inka_Anlage.
     * 
     * @return anl_bauaufs_zul_jn
     */
    public java.lang.Boolean getAnl_bauaufs_zul_jn() {
        return anl_bauaufs_zul_jn;
    }


    /**
     * Sets the anl_bauaufs_zul_jn value for this Inka_Anlage.
     * 
     * @param anl_bauaufs_zul_jn
     */
    public void setAnl_bauaufs_zul_jn(java.lang.Boolean anl_bauaufs_zul_jn) {
        this.anl_bauaufs_zul_jn = anl_bauaufs_zul_jn;
    }


    /**
     * Gets the anl_baurecht_pr_jn value for this Inka_Anlage.
     * 
     * @return anl_baurecht_pr_jn
     */
    public java.lang.Boolean getAnl_baurecht_pr_jn() {
        return anl_baurecht_pr_jn;
    }


    /**
     * Sets the anl_baurecht_pr_jn value for this Inka_Anlage.
     * 
     * @param anl_baurecht_pr_jn
     */
    public void setAnl_baurecht_pr_jn(java.lang.Boolean anl_baurecht_pr_jn) {
        this.anl_baurecht_pr_jn = anl_baurecht_pr_jn;
    }


    /**
     * Gets the anl_din_ce_jn value for this Inka_Anlage.
     * 
     * @return anl_din_ce_jn
     */
    public java.lang.Boolean getAnl_din_ce_jn() {
        return anl_din_ce_jn;
    }


    /**
     * Sets the anl_din_ce_jn value for this Inka_Anlage.
     * 
     * @param anl_din_ce_jn
     */
    public void setAnl_din_ce_jn(java.lang.Boolean anl_din_ce_jn) {
        this.anl_din_ce_jn = anl_din_ce_jn;
    }


    /**
     * Gets the anl_einzelabn_jn value for this Inka_Anlage.
     * 
     * @return anl_einzelabn_jn
     */
    public java.lang.Boolean getAnl_einzelabn_jn() {
        return anl_einzelabn_jn;
    }


    /**
     * Sets the anl_einzelabn_jn value for this Inka_Anlage.
     * 
     * @param anl_einzelabn_jn
     */
    public void setAnl_einzelabn_jn(java.lang.Boolean anl_einzelabn_jn) {
        this.anl_einzelabn_jn = anl_einzelabn_jn;
    }


    /**
     * Gets the anl_ohne_zul_jn value for this Inka_Anlage.
     * 
     * @return anl_ohne_zul_jn
     */
    public java.lang.Boolean getAnl_ohne_zul_jn() {
        return anl_ohne_zul_jn;
    }


    /**
     * Sets the anl_ohne_zul_jn value for this Inka_Anlage.
     * 
     * @param anl_ohne_zul_jn
     */
    public void setAnl_ohne_zul_jn(java.lang.Boolean anl_ohne_zul_jn) {
        this.anl_ohne_zul_jn = anl_ohne_zul_jn;
    }


    /**
     * Gets the anlage_nr value for this Inka_Anlage.
     * 
     * @return anlage_nr
     */
    public java.lang.Integer getAnlage_nr() {
        return anlage_nr;
    }


    /**
     * Sets the anlage_nr value for this Inka_Anlage.
     * 
     * @param anlage_nr
     */
    public void setAnlage_nr(java.lang.Integer anlage_nr) {
        this.anlage_nr = anlage_nr;
    }


    /**
     * Gets the anlage_ver value for this Inka_Anlage.
     * 
     * @return anlage_ver
     */
    public java.lang.Integer getAnlage_ver() {
        return anlage_ver;
    }


    /**
     * Sets the anlage_ver value for this Inka_Anlage.
     * 
     * @param anlage_ver
     */
    public void setAnlage_ver(java.lang.Integer anlage_ver) {
        this.anlage_ver = anlage_ver;
    }


    /**
     * Gets the ausgleichsbecken_jn value for this Inka_Anlage.
     * 
     * @return ausgleichsbecken_jn
     */
    public java.lang.Boolean getAusgleichsbecken_jn() {
        return ausgleichsbecken_jn;
    }


    /**
     * Sets the ausgleichsbecken_jn value for this Inka_Anlage.
     * 
     * @param ausgleichsbecken_jn
     */
    public void setAusgleichsbecken_jn(java.lang.Boolean ausgleichsbecken_jn) {
        this.ausgleichsbecken_jn = ausgleichsbecken_jn;
    }


    /**
     * Gets the befristung_bis value for this Inka_Anlage.
     * 
     * @return befristung_bis
     */
    public java.util.Calendar getBefristung_bis() {
        return befristung_bis;
    }


    /**
     * Sets the befristung_bis value for this Inka_Anlage.
     * 
     * @param befristung_bis
     */
    public void setBefristung_bis(java.util.Calendar befristung_bis) {
        this.befristung_bis = befristung_bis;
    }


    /**
     * Gets the befristung_jn value for this Inka_Anlage.
     * 
     * @return befristung_jn
     */
    public java.lang.Boolean getBefristung_jn() {
        return befristung_jn;
    }


    /**
     * Sets the befristung_jn value for this Inka_Anlage.
     * 
     * @param befristung_jn
     */
    public void setBefristung_jn(java.lang.Boolean befristung_jn) {
        this.befristung_jn = befristung_jn;
    }


    /**
     * Gets the behoerden_id value for this Inka_Anlage.
     * 
     * @return behoerden_id
     */
    public java.lang.String getBehoerden_id() {
        return behoerden_id;
    }


    /**
     * Sets the behoerden_id value for this Inka_Anlage.
     * 
     * @param behoerden_id
     */
    public void setBehoerden_id(java.lang.String behoerden_id) {
        this.behoerden_id = behoerden_id;
    }


    /**
     * Gets the behoerden_ver value for this Inka_Anlage.
     * 
     * @return behoerden_ver
     */
    public java.lang.Integer getBehoerden_ver() {
        return behoerden_ver;
    }


    /**
     * Sets the behoerden_ver value for this Inka_Anlage.
     * 
     * @param behoerden_ver
     */
    public void setBehoerden_ver(java.lang.Integer behoerden_ver) {
        this.behoerden_ver = behoerden_ver;
    }


    /**
     * Gets the bel_c_eli_jn value for this Inka_Anlage.
     * 
     * @return bel_c_eli_jn
     */
    public java.lang.Boolean getBel_c_eli_jn() {
        return bel_c_eli_jn;
    }


    /**
     * Sets the bel_c_eli_jn value for this Inka_Anlage.
     * 
     * @param bel_c_eli_jn
     */
    public void setBel_c_eli_jn(java.lang.Boolean bel_c_eli_jn) {
        this.bel_c_eli_jn = bel_c_eli_jn;
    }


    /**
     * Gets the bel_n_eli_jn value for this Inka_Anlage.
     * 
     * @return bel_n_eli_jn
     */
    public java.lang.Boolean getBel_n_eli_jn() {
        return bel_n_eli_jn;
    }


    /**
     * Sets the bel_n_eli_jn value for this Inka_Anlage.
     * 
     * @param bel_n_eli_jn
     */
    public void setBel_n_eli_jn(java.lang.Boolean bel_n_eli_jn) {
        this.bel_n_eli_jn = bel_n_eli_jn;
    }


    /**
     * Gets the betriebseinrichtung_nr value for this Inka_Anlage.
     * 
     * @return betriebseinrichtung_nr
     */
    public java.lang.Integer getBetriebseinrichtung_nr() {
        return betriebseinrichtung_nr;
    }


    /**
     * Sets the betriebseinrichtung_nr value for this Inka_Anlage.
     * 
     * @param betriebseinrichtung_nr
     */
    public void setBetriebseinrichtung_nr(java.lang.Integer betriebseinrichtung_nr) {
        this.betriebseinrichtung_nr = betriebseinrichtung_nr;
    }


    /**
     * Gets the betriebseinrichtung_ver value for this Inka_Anlage.
     * 
     * @return betriebseinrichtung_ver
     */
    public java.lang.Integer getBetriebseinrichtung_ver() {
        return betriebseinrichtung_ver;
    }


    /**
     * Sets the betriebseinrichtung_ver value for this Inka_Anlage.
     * 
     * @param betriebseinrichtung_ver
     */
    public void setBetriebseinrichtung_ver(java.lang.Integer betriebseinrichtung_ver) {
        this.betriebseinrichtung_ver = betriebseinrichtung_ver;
    }


    /**
     * Gets the biolog_p_eli_jn value for this Inka_Anlage.
     * 
     * @return biolog_p_eli_jn
     */
    public java.lang.Boolean getBiolog_p_eli_jn() {
        return biolog_p_eli_jn;
    }


    /**
     * Sets the biolog_p_eli_jn value for this Inka_Anlage.
     * 
     * @param biolog_p_eli_jn
     */
    public void setBiolog_p_eli_jn(java.lang.Boolean biolog_p_eli_jn) {
        this.biolog_p_eli_jn = biolog_p_eli_jn;
    }


    /**
     * Gets the dat_genehmigung value for this Inka_Anlage.
     * 
     * @return dat_genehmigung
     */
    public java.util.Calendar getDat_genehmigung() {
        return dat_genehmigung;
    }


    /**
     * Sets the dat_genehmigung value for this Inka_Anlage.
     * 
     * @param dat_genehmigung
     */
    public void setDat_genehmigung(java.util.Calendar dat_genehmigung) {
        this.dat_genehmigung = dat_genehmigung;
    }


    /**
     * Gets the dat_inbetrieb value for this Inka_Anlage.
     * 
     * @return dat_inbetrieb
     */
    public java.util.Calendar getDat_inbetrieb() {
        return dat_inbetrieb;
    }


    /**
     * Sets the dat_inbetrieb value for this Inka_Anlage.
     * 
     * @param dat_inbetrieb
     */
    public void setDat_inbetrieb(java.util.Calendar dat_inbetrieb) {
        this.dat_inbetrieb = dat_inbetrieb;
    }


    /**
     * Gets the emulsionsspalt_jn value for this Inka_Anlage.
     * 
     * @return emulsionsspalt_jn
     */
    public java.lang.Boolean getEmulsionsspalt_jn() {
        return emulsionsspalt_jn;
    }


    /**
     * Sets the emulsionsspalt_jn value for this Inka_Anlage.
     * 
     * @param emulsionsspalt_jn
     */
    public void setEmulsionsspalt_jn(java.lang.Boolean emulsionsspalt_jn) {
        this.emulsionsspalt_jn = emulsionsspalt_jn;
    }


    /**
     * Gets the extraktion_jn value for this Inka_Anlage.
     * 
     * @return extraktion_jn
     */
    public java.lang.Boolean getExtraktion_jn() {
        return extraktion_jn;
    }


    /**
     * Sets the extraktion_jn value for this Inka_Anlage.
     * 
     * @param extraktion_jn
     */
    public void setExtraktion_jn(java.lang.Boolean extraktion_jn) {
        this.extraktion_jn = extraktion_jn;
    }


    /**
     * Gets the filtration_jn value for this Inka_Anlage.
     * 
     * @return filtration_jn
     */
    public java.lang.Boolean getFiltration_jn() {
        return filtration_jn;
    }


    /**
     * Sets the filtration_jn value for this Inka_Anlage.
     * 
     * @param filtration_jn
     */
    public void setFiltration_jn(java.lang.Boolean filtration_jn) {
        this.filtration_jn = filtration_jn;
    }


    /**
     * Gets the flockung_jn value for this Inka_Anlage.
     * 
     * @return flockung_jn
     */
    public java.lang.Boolean getFlockung_jn() {
        return flockung_jn;
    }


    /**
     * Sets the flockung_jn value for this Inka_Anlage.
     * 
     * @param flockung_jn
     */
    public void setFlockung_jn(java.lang.Boolean flockung_jn) {
        this.flockung_jn = flockung_jn;
    }


    /**
     * Gets the flotation_jn value for this Inka_Anlage.
     * 
     * @return flotation_jn
     */
    public java.lang.Boolean getFlotation_jn() {
        return flotation_jn;
    }


    /**
     * Sets the flotation_jn value for this Inka_Anlage.
     * 
     * @param flotation_jn
     */
    public void setFlotation_jn(java.lang.Boolean flotation_jn) {
        this.flotation_jn = flotation_jn;
    }


    /**
     * Gets the gemeinde_ver value for this Inka_Anlage.
     * 
     * @return gemeinde_ver
     */
    public java.lang.Integer getGemeinde_ver() {
        return gemeinde_ver;
    }


    /**
     * Sets the gemeinde_ver value for this Inka_Anlage.
     * 
     * @param gemeinde_ver
     */
    public void setGemeinde_ver(java.lang.Integer gemeinde_ver) {
        this.gemeinde_ver = gemeinde_ver;
    }


    /**
     * Gets the gemeindekennzahl value for this Inka_Anlage.
     * 
     * @return gemeindekennzahl
     */
    public java.lang.String getGemeindekennzahl() {
        return gemeindekennzahl;
    }


    /**
     * Sets the gemeindekennzahl value for this Inka_Anlage.
     * 
     * @param gemeindekennzahl
     */
    public void setGemeindekennzahl(java.lang.String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }


    /**
     * Gets the gen_art value for this Inka_Anlage.
     * 
     * @return gen_art
     */
    public java.lang.Integer getGen_art() {
        return gen_art;
    }


    /**
     * Sets the gen_art value for this Inka_Anlage.
     * 
     * @param gen_art
     */
    public void setGen_art(java.lang.Integer gen_art) {
        this.gen_art = gen_art;
    }


    /**
     * Gets the gen_pflichtig_jn value for this Inka_Anlage.
     * 
     * @return gen_pflichtig_jn
     */
    public java.lang.Boolean getGen_pflichtig_jn() {
        return gen_pflichtig_jn;
    }


    /**
     * Sets the gen_pflichtig_jn value for this Inka_Anlage.
     * 
     * @param gen_pflichtig_jn
     */
    public void setGen_pflichtig_jn(java.lang.Boolean gen_pflichtig_jn) {
        this.gen_pflichtig_jn = gen_pflichtig_jn;
    }


    /**
     * Gets the genehmigung_nr value for this Inka_Anlage.
     * 
     * @return genehmigung_nr
     */
    public java.lang.Integer getGenehmigung_nr() {
        return genehmigung_nr;
    }


    /**
     * Sets the genehmigung_nr value for this Inka_Anlage.
     * 
     * @param genehmigung_nr
     */
    public void setGenehmigung_nr(java.lang.Integer genehmigung_nr) {
        this.genehmigung_nr = genehmigung_nr;
    }


    /**
     * Gets the genehmigung_ver value for this Inka_Anlage.
     * 
     * @return genehmigung_ver
     */
    public java.lang.Integer getGenehmigung_ver() {
        return genehmigung_ver;
    }


    /**
     * Sets the genehmigung_ver value for this Inka_Anlage.
     * 
     * @param genehmigung_ver
     */
    public void setGenehmigung_ver(java.lang.Integer genehmigung_ver) {
        this.genehmigung_ver = genehmigung_ver;
    }


    /**
     * Gets the ionenaustausch_jn value for this Inka_Anlage.
     * 
     * @return ionenaustausch_jn
     */
    public java.lang.Boolean getIonenaustausch_jn() {
        return ionenaustausch_jn;
    }


    /**
     * Sets the ionenaustausch_jn value for this Inka_Anlage.
     * 
     * @param ionenaustausch_jn
     */
    public void setIonenaustausch_jn(java.lang.Boolean ionenaustausch_jn) {
        this.ionenaustausch_jn = ionenaustausch_jn;
    }


    /**
     * Gets the kammerfilterp_jn value for this Inka_Anlage.
     * 
     * @return kammerfilterp_jn
     */
    public java.lang.Boolean getKammerfilterp_jn() {
        return kammerfilterp_jn;
    }


    /**
     * Sets the kammerfilterp_jn value for this Inka_Anlage.
     * 
     * @param kammerfilterp_jn
     */
    public void setKammerfilterp_jn(java.lang.Boolean kammerfilterp_jn) {
        this.kammerfilterp_jn = kammerfilterp_jn;
    }


    /**
     * Gets the masch_entw_jn value for this Inka_Anlage.
     * 
     * @return masch_entw_jn
     */
    public java.lang.Boolean getMasch_entw_jn() {
        return masch_entw_jn;
    }


    /**
     * Sets the masch_entw_jn value for this Inka_Anlage.
     * 
     * @param masch_entw_jn
     */
    public void setMasch_entw_jn(java.lang.Boolean masch_entw_jn) {
        this.masch_entw_jn = masch_entw_jn;
    }


    /**
     * Gets the membranfiltration_jn value for this Inka_Anlage.
     * 
     * @return membranfiltration_jn
     */
    public java.lang.Boolean getMembranfiltration_jn() {
        return membranfiltration_jn;
    }


    /**
     * Sets the membranfiltration_jn value for this Inka_Anlage.
     * 
     * @param membranfiltration_jn
     */
    public void setMembranfiltration_jn(java.lang.Boolean membranfiltration_jn) {
        this.membranfiltration_jn = membranfiltration_jn;
    }


    /**
     * Gets the nachklaerung_jn value for this Inka_Anlage.
     * 
     * @return nachklaerung_jn
     */
    public java.lang.Boolean getNachklaerung_jn() {
        return nachklaerung_jn;
    }


    /**
     * Sets the nachklaerung_jn value for this Inka_Anlage.
     * 
     * @param nachklaerung_jn
     */
    public void setNachklaerung_jn(java.lang.Boolean nachklaerung_jn) {
        this.nachklaerung_jn = nachklaerung_jn;
    }


    /**
     * Gets the nat_entw_jn value for this Inka_Anlage.
     * 
     * @return nat_entw_jn
     */
    public java.lang.Boolean getNat_entw_jn() {
        return nat_entw_jn;
    }


    /**
     * Sets the nat_entw_jn value for this Inka_Anlage.
     * 
     * @param nat_entw_jn
     */
    public void setNat_entw_jn(java.lang.Boolean nat_entw_jn) {
        this.nat_entw_jn = nat_entw_jn;
    }


    /**
     * Gets the neutralisation_jn value for this Inka_Anlage.
     * 
     * @return neutralisation_jn
     */
    public java.lang.Boolean getNeutralisation_jn() {
        return neutralisation_jn;
    }


    /**
     * Sets the neutralisation_jn value for this Inka_Anlage.
     * 
     * @param neutralisation_jn
     */
    public void setNeutralisation_jn(java.lang.Boolean neutralisation_jn) {
        this.neutralisation_jn = neutralisation_jn;
    }


    /**
     * Gets the schlammfang_jn value for this Inka_Anlage.
     * 
     * @return schlammfang_jn
     */
    public java.lang.Boolean getSchlammfang_jn() {
        return schlammfang_jn;
    }


    /**
     * Sets the schlammfang_jn value for this Inka_Anlage.
     * 
     * @param schlammfang_jn
     */
    public void setSchlammfang_jn(java.lang.Boolean schlammfang_jn) {
        this.schlammfang_jn = schlammfang_jn;
    }


    /**
     * Gets the sieb_rechen_jn value for this Inka_Anlage.
     * 
     * @return sieb_rechen_jn
     */
    public java.lang.Boolean getSieb_rechen_jn() {
        return sieb_rechen_jn;
    }


    /**
     * Sets the sieb_rechen_jn value for this Inka_Anlage.
     * 
     * @param sieb_rechen_jn
     */
    public void setSieb_rechen_jn(java.lang.Boolean sieb_rechen_jn) {
        this.sieb_rechen_jn = sieb_rechen_jn;
    }


    /**
     * Gets the siebbandp_jn value for this Inka_Anlage.
     * 
     * @return siebbandp_jn
     */
    public java.lang.Boolean getSiebbandp_jn() {
        return siebbandp_jn;
    }


    /**
     * Sets the siebbandp_jn value for this Inka_Anlage.
     * 
     * @param siebbandp_jn
     */
    public void setSiebbandp_jn(java.lang.Boolean siebbandp_jn) {
        this.siebbandp_jn = siebbandp_jn;
    }


    /**
     * Gets the sonst_abscheider_jn value for this Inka_Anlage.
     * 
     * @return sonst_abscheider_jn
     */
    public java.lang.Boolean getSonst_abscheider_jn() {
        return sonst_abscheider_jn;
    }


    /**
     * Sets the sonst_abscheider_jn value for this Inka_Anlage.
     * 
     * @param sonst_abscheider_jn
     */
    public void setSonst_abscheider_jn(java.lang.Boolean sonst_abscheider_jn) {
        this.sonst_abscheider_jn = sonst_abscheider_jn;
    }


    /**
     * Gets the sonstige_biol_verf value for this Inka_Anlage.
     * 
     * @return sonstige_biol_verf
     */
    public java.lang.String getSonstige_biol_verf() {
        return sonstige_biol_verf;
    }


    /**
     * Sets the sonstige_biol_verf value for this Inka_Anlage.
     * 
     * @param sonstige_biol_verf
     */
    public void setSonstige_biol_verf(java.lang.String sonstige_biol_verf) {
        this.sonstige_biol_verf = sonstige_biol_verf;
    }


    /**
     * Gets the sonstige_mech_verf value for this Inka_Anlage.
     * 
     * @return sonstige_mech_verf
     */
    public java.lang.String getSonstige_mech_verf() {
        return sonstige_mech_verf;
    }


    /**
     * Sets the sonstige_mech_verf value for this Inka_Anlage.
     * 
     * @param sonstige_mech_verf
     */
    public void setSonstige_mech_verf(java.lang.String sonstige_mech_verf) {
        this.sonstige_mech_verf = sonstige_mech_verf;
    }


    /**
     * Gets the sonstige_schlamm_beh value for this Inka_Anlage.
     * 
     * @return sonstige_schlamm_beh
     */
    public java.lang.String getSonstige_schlamm_beh() {
        return sonstige_schlamm_beh;
    }


    /**
     * Sets the sonstige_schlamm_beh value for this Inka_Anlage.
     * 
     * @param sonstige_schlamm_beh
     */
    public void setSonstige_schlamm_beh(java.lang.String sonstige_schlamm_beh) {
        this.sonstige_schlamm_beh = sonstige_schlamm_beh;
    }


    /**
     * Gets the stat_entw_jn value for this Inka_Anlage.
     * 
     * @return stat_entw_jn
     */
    public java.lang.Boolean getStat_entw_jn() {
        return stat_entw_jn;
    }


    /**
     * Sets the stat_entw_jn value for this Inka_Anlage.
     * 
     * @param stat_entw_jn
     */
    public void setStat_entw_jn(java.lang.Boolean stat_entw_jn) {
        this.stat_entw_jn = stat_entw_jn;
    }


    /**
     * Gets the stripp_anlage_jn value for this Inka_Anlage.
     * 
     * @return stripp_anlage_jn
     */
    public java.lang.Boolean getStripp_anlage_jn() {
        return stripp_anlage_jn;
    }


    /**
     * Sets the stripp_anlage_jn value for this Inka_Anlage.
     * 
     * @param stripp_anlage_jn
     */
    public void setStripp_anlage_jn(java.lang.Boolean stripp_anlage_jn) {
        this.stripp_anlage_jn = stripp_anlage_jn;
    }


    /**
     * Gets the stua_bezirk value for this Inka_Anlage.
     * 
     * @return stua_bezirk
     */
    public java.lang.Integer getStua_bezirk() {
        return stua_bezirk;
    }


    /**
     * Sets the stua_bezirk value for this Inka_Anlage.
     * 
     * @param stua_bezirk
     */
    public void setStua_bezirk(java.lang.Integer stua_bezirk) {
        this.stua_bezirk = stua_bezirk;
    }


    /**
     * Gets the stua_ver value for this Inka_Anlage.
     * 
     * @return stua_ver
     */
    public java.lang.Integer getStua_ver() {
        return stua_ver;
    }


    /**
     * Sets the stua_ver value for this Inka_Anlage.
     * 
     * @param stua_ver
     */
    public void setStua_ver(java.lang.Integer stua_ver) {
        this.stua_ver = stua_ver;
    }


    /**
     * Gets the tropfkoerper_jn value for this Inka_Anlage.
     * 
     * @return tropfkoerper_jn
     */
    public java.lang.Boolean getTropfkoerper_jn() {
        return tropfkoerper_jn;
    }


    /**
     * Sets the tropfkoerper_jn value for this Inka_Anlage.
     * 
     * @param tropfkoerper_jn
     */
    public void setTropfkoerper_jn(java.lang.Boolean tropfkoerper_jn) {
        this.tropfkoerper_jn = tropfkoerper_jn;
    }


    /**
     * Gets the uebergabestelle_lfd_nr value for this Inka_Anlage.
     * 
     * @return uebergabestelle_lfd_nr
     */
    public java.lang.Integer getUebergabestelle_lfd_nr() {
        return uebergabestelle_lfd_nr;
    }


    /**
     * Sets the uebergabestelle_lfd_nr value for this Inka_Anlage.
     * 
     * @param uebergabestelle_lfd_nr
     */
    public void setUebergabestelle_lfd_nr(java.lang.Integer uebergabestelle_lfd_nr) {
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
    }


    /**
     * Gets the uebergabestelle_ver value for this Inka_Anlage.
     * 
     * @return uebergabestelle_ver
     */
    public java.lang.Integer getUebergabestelle_ver() {
        return uebergabestelle_ver;
    }


    /**
     * Sets the uebergabestelle_ver value for this Inka_Anlage.
     * 
     * @param uebergabestelle_ver
     */
    public void setUebergabestelle_ver(java.lang.Integer uebergabestelle_ver) {
        this.uebergabestelle_ver = uebergabestelle_ver;
    }


    /**
     * Gets the vakuumfilter_jn value for this Inka_Anlage.
     * 
     * @return vakuumfilter_jn
     */
    public java.lang.Boolean getVakuumfilter_jn() {
        return vakuumfilter_jn;
    }


    /**
     * Sets the vakuumfilter_jn value for this Inka_Anlage.
     * 
     * @param vakuumfilter_jn
     */
    public void setVakuumfilter_jn(java.lang.Boolean vakuumfilter_jn) {
        this.vakuumfilter_jn = vakuumfilter_jn;
    }


    /**
     * Gets the vorklaerung_absetz_jn value for this Inka_Anlage.
     * 
     * @return vorklaerung_absetz_jn
     */
    public java.lang.Boolean getVorklaerung_absetz_jn() {
        return vorklaerung_absetz_jn;
    }


    /**
     * Sets the vorklaerung_absetz_jn value for this Inka_Anlage.
     * 
     * @param vorklaerung_absetz_jn
     */
    public void setVorklaerung_absetz_jn(java.lang.Boolean vorklaerung_absetz_jn) {
        this.vorklaerung_absetz_jn = vorklaerung_absetz_jn;
    }


    /**
     * Gets the was_behoerden_id value for this Inka_Anlage.
     * 
     * @return was_behoerden_id
     */
    public java.lang.String getWas_behoerden_id() {
        return was_behoerden_id;
    }


    /**
     * Sets the was_behoerden_id value for this Inka_Anlage.
     * 
     * @param was_behoerden_id
     */
    public void setWas_behoerden_id(java.lang.String was_behoerden_id) {
        this.was_behoerden_id = was_behoerden_id;
    }


    /**
     * Gets the was_behoerden_ver value for this Inka_Anlage.
     * 
     * @return was_behoerden_ver
     */
    public java.lang.Integer getWas_behoerden_ver() {
        return was_behoerden_ver;
    }


    /**
     * Sets the was_behoerden_ver value for this Inka_Anlage.
     * 
     * @param was_behoerden_ver
     */
    public void setWas_behoerden_ver(java.lang.Integer was_behoerden_ver) {
        this.was_behoerden_ver = was_behoerden_ver;
    }


    /**
     * Gets the zentrifuge_jn value for this Inka_Anlage.
     * 
     * @return zentrifuge_jn
     */
    public java.lang.Boolean getZentrifuge_jn() {
        return zentrifuge_jn;
    }


    /**
     * Sets the zentrifuge_jn value for this Inka_Anlage.
     * 
     * @param zentrifuge_jn
     */
    public void setZentrifuge_jn(java.lang.Boolean zentrifuge_jn) {
        this.zentrifuge_jn = zentrifuge_jn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Anlage)) return false;
        Inka_Anlage other = (Inka_Anlage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.abfuhr_unbeh_jn==null && other.getAbfuhr_unbeh_jn()==null) || 
             (this.abfuhr_unbeh_jn!=null &&
              this.abfuhr_unbeh_jn.equals(other.getAbfuhr_unbeh_jn()))) &&
            ((this.abscheider_jn==null && other.getAbscheider_jn()==null) || 
             (this.abscheider_jn!=null &&
              this.abscheider_jn.equals(other.getAbscheider_jn()))) &&
            ((this.absorption_jn==null && other.getAbsorption_jn()==null) || 
             (this.absorption_jn!=null &&
              this.absorption_jn.equals(other.getAbsorption_jn()))) &&
            ((this.adresse_anspr_nr==null && other.getAdresse_anspr_nr()==null) || 
             (this.adresse_anspr_nr!=null &&
              this.adresse_anspr_nr.equals(other.getAdresse_anspr_nr()))) &&
            ((this.adresse_anspr_ver==null && other.getAdresse_anspr_ver()==null) || 
             (this.adresse_anspr_ver!=null &&
              this.adresse_anspr_ver.equals(other.getAdresse_anspr_ver()))) &&
            ((this.aktenzeichen==null && other.getAktenzeichen()==null) || 
             (this.aktenzeichen!=null &&
              this.aktenzeichen.equals(other.getAktenzeichen()))) &&
            ((this.anaerobe_vorb_jn==null && other.getAnaerobe_vorb_jn()==null) || 
             (this.anaerobe_vorb_jn!=null &&
              this.anaerobe_vorb_jn.equals(other.getAnaerobe_vorb_jn()))) &&
            ((this.anl_bauaufs_zul_jn==null && other.getAnl_bauaufs_zul_jn()==null) || 
             (this.anl_bauaufs_zul_jn!=null &&
              this.anl_bauaufs_zul_jn.equals(other.getAnl_bauaufs_zul_jn()))) &&
            ((this.anl_baurecht_pr_jn==null && other.getAnl_baurecht_pr_jn()==null) || 
             (this.anl_baurecht_pr_jn!=null &&
              this.anl_baurecht_pr_jn.equals(other.getAnl_baurecht_pr_jn()))) &&
            ((this.anl_din_ce_jn==null && other.getAnl_din_ce_jn()==null) || 
             (this.anl_din_ce_jn!=null &&
              this.anl_din_ce_jn.equals(other.getAnl_din_ce_jn()))) &&
            ((this.anl_einzelabn_jn==null && other.getAnl_einzelabn_jn()==null) || 
             (this.anl_einzelabn_jn!=null &&
              this.anl_einzelabn_jn.equals(other.getAnl_einzelabn_jn()))) &&
            ((this.anl_ohne_zul_jn==null && other.getAnl_ohne_zul_jn()==null) || 
             (this.anl_ohne_zul_jn!=null &&
              this.anl_ohne_zul_jn.equals(other.getAnl_ohne_zul_jn()))) &&
            ((this.anlage_nr==null && other.getAnlage_nr()==null) || 
             (this.anlage_nr!=null &&
              this.anlage_nr.equals(other.getAnlage_nr()))) &&
            ((this.anlage_ver==null && other.getAnlage_ver()==null) || 
             (this.anlage_ver!=null &&
              this.anlage_ver.equals(other.getAnlage_ver()))) &&
            ((this.ausgleichsbecken_jn==null && other.getAusgleichsbecken_jn()==null) || 
             (this.ausgleichsbecken_jn!=null &&
              this.ausgleichsbecken_jn.equals(other.getAusgleichsbecken_jn()))) &&
            ((this.befristung_bis==null && other.getBefristung_bis()==null) || 
             (this.befristung_bis!=null &&
              this.befristung_bis.equals(other.getBefristung_bis()))) &&
            ((this.befristung_jn==null && other.getBefristung_jn()==null) || 
             (this.befristung_jn!=null &&
              this.befristung_jn.equals(other.getBefristung_jn()))) &&
            ((this.behoerden_id==null && other.getBehoerden_id()==null) || 
             (this.behoerden_id!=null &&
              this.behoerden_id.equals(other.getBehoerden_id()))) &&
            ((this.behoerden_ver==null && other.getBehoerden_ver()==null) || 
             (this.behoerden_ver!=null &&
              this.behoerden_ver.equals(other.getBehoerden_ver()))) &&
            ((this.bel_c_eli_jn==null && other.getBel_c_eli_jn()==null) || 
             (this.bel_c_eli_jn!=null &&
              this.bel_c_eli_jn.equals(other.getBel_c_eli_jn()))) &&
            ((this.bel_n_eli_jn==null && other.getBel_n_eli_jn()==null) || 
             (this.bel_n_eli_jn!=null &&
              this.bel_n_eli_jn.equals(other.getBel_n_eli_jn()))) &&
            ((this.betriebseinrichtung_nr==null && other.getBetriebseinrichtung_nr()==null) || 
             (this.betriebseinrichtung_nr!=null &&
              this.betriebseinrichtung_nr.equals(other.getBetriebseinrichtung_nr()))) &&
            ((this.betriebseinrichtung_ver==null && other.getBetriebseinrichtung_ver()==null) || 
             (this.betriebseinrichtung_ver!=null &&
              this.betriebseinrichtung_ver.equals(other.getBetriebseinrichtung_ver()))) &&
            ((this.biolog_p_eli_jn==null && other.getBiolog_p_eli_jn()==null) || 
             (this.biolog_p_eli_jn!=null &&
              this.biolog_p_eli_jn.equals(other.getBiolog_p_eli_jn()))) &&
            ((this.dat_genehmigung==null && other.getDat_genehmigung()==null) || 
             (this.dat_genehmigung!=null &&
              this.dat_genehmigung.equals(other.getDat_genehmigung()))) &&
            ((this.dat_inbetrieb==null && other.getDat_inbetrieb()==null) || 
             (this.dat_inbetrieb!=null &&
              this.dat_inbetrieb.equals(other.getDat_inbetrieb()))) &&
            ((this.emulsionsspalt_jn==null && other.getEmulsionsspalt_jn()==null) || 
             (this.emulsionsspalt_jn!=null &&
              this.emulsionsspalt_jn.equals(other.getEmulsionsspalt_jn()))) &&
            ((this.extraktion_jn==null && other.getExtraktion_jn()==null) || 
             (this.extraktion_jn!=null &&
              this.extraktion_jn.equals(other.getExtraktion_jn()))) &&
            ((this.filtration_jn==null && other.getFiltration_jn()==null) || 
             (this.filtration_jn!=null &&
              this.filtration_jn.equals(other.getFiltration_jn()))) &&
            ((this.flockung_jn==null && other.getFlockung_jn()==null) || 
             (this.flockung_jn!=null &&
              this.flockung_jn.equals(other.getFlockung_jn()))) &&
            ((this.flotation_jn==null && other.getFlotation_jn()==null) || 
             (this.flotation_jn!=null &&
              this.flotation_jn.equals(other.getFlotation_jn()))) &&
            ((this.gemeinde_ver==null && other.getGemeinde_ver()==null) || 
             (this.gemeinde_ver!=null &&
              this.gemeinde_ver.equals(other.getGemeinde_ver()))) &&
            ((this.gemeindekennzahl==null && other.getGemeindekennzahl()==null) || 
             (this.gemeindekennzahl!=null &&
              this.gemeindekennzahl.equals(other.getGemeindekennzahl()))) &&
            ((this.gen_art==null && other.getGen_art()==null) || 
             (this.gen_art!=null &&
              this.gen_art.equals(other.getGen_art()))) &&
            ((this.gen_pflichtig_jn==null && other.getGen_pflichtig_jn()==null) || 
             (this.gen_pflichtig_jn!=null &&
              this.gen_pflichtig_jn.equals(other.getGen_pflichtig_jn()))) &&
            ((this.genehmigung_nr==null && other.getGenehmigung_nr()==null) || 
             (this.genehmigung_nr!=null &&
              this.genehmigung_nr.equals(other.getGenehmigung_nr()))) &&
            ((this.genehmigung_ver==null && other.getGenehmigung_ver()==null) || 
             (this.genehmigung_ver!=null &&
              this.genehmigung_ver.equals(other.getGenehmigung_ver()))) &&
            ((this.ionenaustausch_jn==null && other.getIonenaustausch_jn()==null) || 
             (this.ionenaustausch_jn!=null &&
              this.ionenaustausch_jn.equals(other.getIonenaustausch_jn()))) &&
            ((this.kammerfilterp_jn==null && other.getKammerfilterp_jn()==null) || 
             (this.kammerfilterp_jn!=null &&
              this.kammerfilterp_jn.equals(other.getKammerfilterp_jn()))) &&
            ((this.masch_entw_jn==null && other.getMasch_entw_jn()==null) || 
             (this.masch_entw_jn!=null &&
              this.masch_entw_jn.equals(other.getMasch_entw_jn()))) &&
            ((this.membranfiltration_jn==null && other.getMembranfiltration_jn()==null) || 
             (this.membranfiltration_jn!=null &&
              this.membranfiltration_jn.equals(other.getMembranfiltration_jn()))) &&
            ((this.nachklaerung_jn==null && other.getNachklaerung_jn()==null) || 
             (this.nachklaerung_jn!=null &&
              this.nachklaerung_jn.equals(other.getNachklaerung_jn()))) &&
            ((this.nat_entw_jn==null && other.getNat_entw_jn()==null) || 
             (this.nat_entw_jn!=null &&
              this.nat_entw_jn.equals(other.getNat_entw_jn()))) &&
            ((this.neutralisation_jn==null && other.getNeutralisation_jn()==null) || 
             (this.neutralisation_jn!=null &&
              this.neutralisation_jn.equals(other.getNeutralisation_jn()))) &&
            ((this.schlammfang_jn==null && other.getSchlammfang_jn()==null) || 
             (this.schlammfang_jn!=null &&
              this.schlammfang_jn.equals(other.getSchlammfang_jn()))) &&
            ((this.sieb_rechen_jn==null && other.getSieb_rechen_jn()==null) || 
             (this.sieb_rechen_jn!=null &&
              this.sieb_rechen_jn.equals(other.getSieb_rechen_jn()))) &&
            ((this.siebbandp_jn==null && other.getSiebbandp_jn()==null) || 
             (this.siebbandp_jn!=null &&
              this.siebbandp_jn.equals(other.getSiebbandp_jn()))) &&
            ((this.sonst_abscheider_jn==null && other.getSonst_abscheider_jn()==null) || 
             (this.sonst_abscheider_jn!=null &&
              this.sonst_abscheider_jn.equals(other.getSonst_abscheider_jn()))) &&
            ((this.sonstige_biol_verf==null && other.getSonstige_biol_verf()==null) || 
             (this.sonstige_biol_verf!=null &&
              this.sonstige_biol_verf.equals(other.getSonstige_biol_verf()))) &&
            ((this.sonstige_mech_verf==null && other.getSonstige_mech_verf()==null) || 
             (this.sonstige_mech_verf!=null &&
              this.sonstige_mech_verf.equals(other.getSonstige_mech_verf()))) &&
            ((this.sonstige_schlamm_beh==null && other.getSonstige_schlamm_beh()==null) || 
             (this.sonstige_schlamm_beh!=null &&
              this.sonstige_schlamm_beh.equals(other.getSonstige_schlamm_beh()))) &&
            ((this.stat_entw_jn==null && other.getStat_entw_jn()==null) || 
             (this.stat_entw_jn!=null &&
              this.stat_entw_jn.equals(other.getStat_entw_jn()))) &&
            ((this.stripp_anlage_jn==null && other.getStripp_anlage_jn()==null) || 
             (this.stripp_anlage_jn!=null &&
              this.stripp_anlage_jn.equals(other.getStripp_anlage_jn()))) &&
            ((this.stua_bezirk==null && other.getStua_bezirk()==null) || 
             (this.stua_bezirk!=null &&
              this.stua_bezirk.equals(other.getStua_bezirk()))) &&
            ((this.stua_ver==null && other.getStua_ver()==null) || 
             (this.stua_ver!=null &&
              this.stua_ver.equals(other.getStua_ver()))) &&
            ((this.tropfkoerper_jn==null && other.getTropfkoerper_jn()==null) || 
             (this.tropfkoerper_jn!=null &&
              this.tropfkoerper_jn.equals(other.getTropfkoerper_jn()))) &&
            ((this.uebergabestelle_lfd_nr==null && other.getUebergabestelle_lfd_nr()==null) || 
             (this.uebergabestelle_lfd_nr!=null &&
              this.uebergabestelle_lfd_nr.equals(other.getUebergabestelle_lfd_nr()))) &&
            ((this.uebergabestelle_ver==null && other.getUebergabestelle_ver()==null) || 
             (this.uebergabestelle_ver!=null &&
              this.uebergabestelle_ver.equals(other.getUebergabestelle_ver()))) &&
            ((this.vakuumfilter_jn==null && other.getVakuumfilter_jn()==null) || 
             (this.vakuumfilter_jn!=null &&
              this.vakuumfilter_jn.equals(other.getVakuumfilter_jn()))) &&
            ((this.vorklaerung_absetz_jn==null && other.getVorklaerung_absetz_jn()==null) || 
             (this.vorklaerung_absetz_jn!=null &&
              this.vorklaerung_absetz_jn.equals(other.getVorklaerung_absetz_jn()))) &&
            ((this.was_behoerden_id==null && other.getWas_behoerden_id()==null) || 
             (this.was_behoerden_id!=null &&
              this.was_behoerden_id.equals(other.getWas_behoerden_id()))) &&
            ((this.was_behoerden_ver==null && other.getWas_behoerden_ver()==null) || 
             (this.was_behoerden_ver!=null &&
              this.was_behoerden_ver.equals(other.getWas_behoerden_ver()))) &&
            ((this.zentrifuge_jn==null && other.getZentrifuge_jn()==null) || 
             (this.zentrifuge_jn!=null &&
              this.zentrifuge_jn.equals(other.getZentrifuge_jn())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAbfuhr_unbeh_jn() != null) {
            _hashCode += getAbfuhr_unbeh_jn().hashCode();
        }
        if (getAbscheider_jn() != null) {
            _hashCode += getAbscheider_jn().hashCode();
        }
        if (getAbsorption_jn() != null) {
            _hashCode += getAbsorption_jn().hashCode();
        }
        if (getAdresse_anspr_nr() != null) {
            _hashCode += getAdresse_anspr_nr().hashCode();
        }
        if (getAdresse_anspr_ver() != null) {
            _hashCode += getAdresse_anspr_ver().hashCode();
        }
        if (getAktenzeichen() != null) {
            _hashCode += getAktenzeichen().hashCode();
        }
        if (getAnaerobe_vorb_jn() != null) {
            _hashCode += getAnaerobe_vorb_jn().hashCode();
        }
        if (getAnl_bauaufs_zul_jn() != null) {
            _hashCode += getAnl_bauaufs_zul_jn().hashCode();
        }
        if (getAnl_baurecht_pr_jn() != null) {
            _hashCode += getAnl_baurecht_pr_jn().hashCode();
        }
        if (getAnl_din_ce_jn() != null) {
            _hashCode += getAnl_din_ce_jn().hashCode();
        }
        if (getAnl_einzelabn_jn() != null) {
            _hashCode += getAnl_einzelabn_jn().hashCode();
        }
        if (getAnl_ohne_zul_jn() != null) {
            _hashCode += getAnl_ohne_zul_jn().hashCode();
        }
        if (getAnlage_nr() != null) {
            _hashCode += getAnlage_nr().hashCode();
        }
        if (getAnlage_ver() != null) {
            _hashCode += getAnlage_ver().hashCode();
        }
        if (getAusgleichsbecken_jn() != null) {
            _hashCode += getAusgleichsbecken_jn().hashCode();
        }
        if (getBefristung_bis() != null) {
            _hashCode += getBefristung_bis().hashCode();
        }
        if (getBefristung_jn() != null) {
            _hashCode += getBefristung_jn().hashCode();
        }
        if (getBehoerden_id() != null) {
            _hashCode += getBehoerden_id().hashCode();
        }
        if (getBehoerden_ver() != null) {
            _hashCode += getBehoerden_ver().hashCode();
        }
        if (getBel_c_eli_jn() != null) {
            _hashCode += getBel_c_eli_jn().hashCode();
        }
        if (getBel_n_eli_jn() != null) {
            _hashCode += getBel_n_eli_jn().hashCode();
        }
        if (getBetriebseinrichtung_nr() != null) {
            _hashCode += getBetriebseinrichtung_nr().hashCode();
        }
        if (getBetriebseinrichtung_ver() != null) {
            _hashCode += getBetriebseinrichtung_ver().hashCode();
        }
        if (getBiolog_p_eli_jn() != null) {
            _hashCode += getBiolog_p_eli_jn().hashCode();
        }
        if (getDat_genehmigung() != null) {
            _hashCode += getDat_genehmigung().hashCode();
        }
        if (getDat_inbetrieb() != null) {
            _hashCode += getDat_inbetrieb().hashCode();
        }
        if (getEmulsionsspalt_jn() != null) {
            _hashCode += getEmulsionsspalt_jn().hashCode();
        }
        if (getExtraktion_jn() != null) {
            _hashCode += getExtraktion_jn().hashCode();
        }
        if (getFiltration_jn() != null) {
            _hashCode += getFiltration_jn().hashCode();
        }
        if (getFlockung_jn() != null) {
            _hashCode += getFlockung_jn().hashCode();
        }
        if (getFlotation_jn() != null) {
            _hashCode += getFlotation_jn().hashCode();
        }
        if (getGemeinde_ver() != null) {
            _hashCode += getGemeinde_ver().hashCode();
        }
        if (getGemeindekennzahl() != null) {
            _hashCode += getGemeindekennzahl().hashCode();
        }
        if (getGen_art() != null) {
            _hashCode += getGen_art().hashCode();
        }
        if (getGen_pflichtig_jn() != null) {
            _hashCode += getGen_pflichtig_jn().hashCode();
        }
        if (getGenehmigung_nr() != null) {
            _hashCode += getGenehmigung_nr().hashCode();
        }
        if (getGenehmigung_ver() != null) {
            _hashCode += getGenehmigung_ver().hashCode();
        }
        if (getIonenaustausch_jn() != null) {
            _hashCode += getIonenaustausch_jn().hashCode();
        }
        if (getKammerfilterp_jn() != null) {
            _hashCode += getKammerfilterp_jn().hashCode();
        }
        if (getMasch_entw_jn() != null) {
            _hashCode += getMasch_entw_jn().hashCode();
        }
        if (getMembranfiltration_jn() != null) {
            _hashCode += getMembranfiltration_jn().hashCode();
        }
        if (getNachklaerung_jn() != null) {
            _hashCode += getNachklaerung_jn().hashCode();
        }
        if (getNat_entw_jn() != null) {
            _hashCode += getNat_entw_jn().hashCode();
        }
        if (getNeutralisation_jn() != null) {
            _hashCode += getNeutralisation_jn().hashCode();
        }
        if (getSchlammfang_jn() != null) {
            _hashCode += getSchlammfang_jn().hashCode();
        }
        if (getSieb_rechen_jn() != null) {
            _hashCode += getSieb_rechen_jn().hashCode();
        }
        if (getSiebbandp_jn() != null) {
            _hashCode += getSiebbandp_jn().hashCode();
        }
        if (getSonst_abscheider_jn() != null) {
            _hashCode += getSonst_abscheider_jn().hashCode();
        }
        if (getSonstige_biol_verf() != null) {
            _hashCode += getSonstige_biol_verf().hashCode();
        }
        if (getSonstige_mech_verf() != null) {
            _hashCode += getSonstige_mech_verf().hashCode();
        }
        if (getSonstige_schlamm_beh() != null) {
            _hashCode += getSonstige_schlamm_beh().hashCode();
        }
        if (getStat_entw_jn() != null) {
            _hashCode += getStat_entw_jn().hashCode();
        }
        if (getStripp_anlage_jn() != null) {
            _hashCode += getStripp_anlage_jn().hashCode();
        }
        if (getStua_bezirk() != null) {
            _hashCode += getStua_bezirk().hashCode();
        }
        if (getStua_ver() != null) {
            _hashCode += getStua_ver().hashCode();
        }
        if (getTropfkoerper_jn() != null) {
            _hashCode += getTropfkoerper_jn().hashCode();
        }
        if (getUebergabestelle_lfd_nr() != null) {
            _hashCode += getUebergabestelle_lfd_nr().hashCode();
        }
        if (getUebergabestelle_ver() != null) {
            _hashCode += getUebergabestelle_ver().hashCode();
        }
        if (getVakuumfilter_jn() != null) {
            _hashCode += getVakuumfilter_jn().hashCode();
        }
        if (getVorklaerung_absetz_jn() != null) {
            _hashCode += getVorklaerung_absetz_jn().hashCode();
        }
        if (getWas_behoerden_id() != null) {
            _hashCode += getWas_behoerden_id().hashCode();
        }
        if (getWas_behoerden_ver() != null) {
            _hashCode += getWas_behoerden_ver().hashCode();
        }
        if (getZentrifuge_jn() != null) {
            _hashCode += getZentrifuge_jn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Anlage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anlage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abfuhr_unbeh_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abfuhr_unbeh_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abscheider_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abscheider_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("absorption_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "absorption_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresse_anspr_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_anspr_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresse_anspr_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_anspr_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aktenzeichen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aktenzeichen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anaerobe_vorb_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anaerobe_vorb_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anl_bauaufs_zul_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anl_bauaufs_zul_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anl_baurecht_pr_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anl_baurecht_pr_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anl_din_ce_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anl_din_ce_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anl_einzelabn_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anl_einzelabn_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anl_ohne_zul_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anl_ohne_zul_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anlage_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anlage_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anlage_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anlage_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ausgleichsbecken_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ausgleichsbecken_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("befristung_bis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "befristung_bis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("befristung_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "befristung_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("behoerden_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "behoerden_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("behoerden_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "behoerden_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bel_c_eli_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bel_c_eli_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bel_n_eli_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bel_n_eli_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("betriebseinrichtung_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "betriebseinrichtung_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("betriebseinrichtung_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "betriebseinrichtung_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("biolog_p_eli_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "biolog_p_eli_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dat_genehmigung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dat_genehmigung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dat_inbetrieb");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dat_inbetrieb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emulsionsspalt_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emulsionsspalt_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraktion_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extraktion_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filtration_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "filtration_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flockung_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flockung_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flotation_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flotation_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gemeinde_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gemeinde_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gemeindekennzahl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gemeindekennzahl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gen_art");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gen_art"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gen_pflichtig_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gen_pflichtig_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genehmigung_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "genehmigung_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genehmigung_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "genehmigung_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ionenaustausch_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ionenaustausch_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kammerfilterp_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kammerfilterp_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masch_entw_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "masch_entw_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("membranfiltration_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "membranfiltration_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nachklaerung_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nachklaerung_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nat_entw_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nat_entw_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("neutralisation_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "neutralisation_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schlammfang_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "schlammfang_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sieb_rechen_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sieb_rechen_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siebbandp_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "siebbandp_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sonst_abscheider_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sonst_abscheider_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sonstige_biol_verf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sonstige_biol_verf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sonstige_mech_verf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sonstige_mech_verf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sonstige_schlamm_beh");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sonstige_schlamm_beh"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stat_entw_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stat_entw_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stripp_anlage_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stripp_anlage_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stua_bezirk");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stua_bezirk"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stua_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stua_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tropfkoerper_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tropfkoerper_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uebergabestelle_lfd_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uebergabestelle_lfd_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uebergabestelle_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uebergabestelle_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vakuumfilter_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vakuumfilter_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vorklaerung_absetz_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vorklaerung_absetz_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("was_behoerden_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "was_behoerden_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("was_behoerden_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "was_behoerden_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zentrifuge_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "zentrifuge_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
