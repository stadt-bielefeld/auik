/**
 * Dea_Parameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Parameter  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Boolean abgaberelevant_jn;

    private java.lang.Integer analyse_ver;

    private java.lang.String analyseverf_1_para;

    private java.lang.String analyseverf_para;

    private java.lang.String berechnung_para;

    private java.lang.Boolean frachtpar_jn;

    private java.lang.String gruppe_dev;

    private java.lang.Integer masseinheiten_nr;

    private java.lang.Integer masseinheiten_nr_1;

    private java.lang.Integer masseinheiten_ver;

    private java.lang.Integer par_bedingung;

    private java.lang.Integer par_bereich;

    private java.lang.String par_verwendung;

    private java.lang.Integer parameter_nr;

    private java.lang.Integer parameter_ver;

    private java.lang.String regelwerk_nr;

    private java.lang.Integer stoff_nr;

    private java.lang.Integer stoff_nr_1;

    private java.lang.Integer stoff_ver;

    private java.lang.Integer trenn_nr_1;

    private java.lang.Integer trenn_nr_opt;

    private java.lang.Boolean umrech_jn;

    private java.lang.String varianten_nr;

    private java.util.Calendar veran_dat_ab;

    private java.util.Calendar veran_dat_bis;

    public Dea_Parameter() {
    }

    public Dea_Parameter(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Boolean abgaberelevant_jn,
           java.lang.Integer analyse_ver,
           java.lang.String analyseverf_1_para,
           java.lang.String analyseverf_para,
           java.lang.String berechnung_para,
           java.lang.Boolean frachtpar_jn,
           java.lang.String gruppe_dev,
           java.lang.Integer masseinheiten_nr,
           java.lang.Integer masseinheiten_nr_1,
           java.lang.Integer masseinheiten_ver,
           java.lang.Integer par_bedingung,
           java.lang.Integer par_bereich,
           java.lang.String par_verwendung,
           java.lang.Integer parameter_nr,
           java.lang.Integer parameter_ver,
           java.lang.String regelwerk_nr,
           java.lang.Integer stoff_nr,
           java.lang.Integer stoff_nr_1,
           java.lang.Integer stoff_ver,
           java.lang.Integer trenn_nr_1,
           java.lang.Integer trenn_nr_opt,
           java.lang.Boolean umrech_jn,
           java.lang.String varianten_nr,
           java.util.Calendar veran_dat_ab,
           java.util.Calendar veran_dat_bis) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.abgaberelevant_jn = abgaberelevant_jn;
        this.analyse_ver = analyse_ver;
        this.analyseverf_1_para = analyseverf_1_para;
        this.analyseverf_para = analyseverf_para;
        this.berechnung_para = berechnung_para;
        this.frachtpar_jn = frachtpar_jn;
        this.gruppe_dev = gruppe_dev;
        this.masseinheiten_nr = masseinheiten_nr;
        this.masseinheiten_nr_1 = masseinheiten_nr_1;
        this.masseinheiten_ver = masseinheiten_ver;
        this.par_bedingung = par_bedingung;
        this.par_bereich = par_bereich;
        this.par_verwendung = par_verwendung;
        this.parameter_nr = parameter_nr;
        this.parameter_ver = parameter_ver;
        this.regelwerk_nr = regelwerk_nr;
        this.stoff_nr = stoff_nr;
        this.stoff_nr_1 = stoff_nr_1;
        this.stoff_ver = stoff_ver;
        this.trenn_nr_1 = trenn_nr_1;
        this.trenn_nr_opt = trenn_nr_opt;
        this.umrech_jn = umrech_jn;
        this.varianten_nr = varianten_nr;
        this.veran_dat_ab = veran_dat_ab;
        this.veran_dat_bis = veran_dat_bis;
    }


    /**
     * Gets the abgaberelevant_jn value for this Dea_Parameter.
     * 
     * @return abgaberelevant_jn
     */
    public java.lang.Boolean getAbgaberelevant_jn() {
        return abgaberelevant_jn;
    }


    /**
     * Sets the abgaberelevant_jn value for this Dea_Parameter.
     * 
     * @param abgaberelevant_jn
     */
    public void setAbgaberelevant_jn(java.lang.Boolean abgaberelevant_jn) {
        this.abgaberelevant_jn = abgaberelevant_jn;
    }


    /**
     * Gets the analyse_ver value for this Dea_Parameter.
     * 
     * @return analyse_ver
     */
    public java.lang.Integer getAnalyse_ver() {
        return analyse_ver;
    }


    /**
     * Sets the analyse_ver value for this Dea_Parameter.
     * 
     * @param analyse_ver
     */
    public void setAnalyse_ver(java.lang.Integer analyse_ver) {
        this.analyse_ver = analyse_ver;
    }


    /**
     * Gets the analyseverf_1_para value for this Dea_Parameter.
     * 
     * @return analyseverf_1_para
     */
    public java.lang.String getAnalyseverf_1_para() {
        return analyseverf_1_para;
    }


    /**
     * Sets the analyseverf_1_para value for this Dea_Parameter.
     * 
     * @param analyseverf_1_para
     */
    public void setAnalyseverf_1_para(java.lang.String analyseverf_1_para) {
        this.analyseverf_1_para = analyseverf_1_para;
    }


    /**
     * Gets the analyseverf_para value for this Dea_Parameter.
     * 
     * @return analyseverf_para
     */
    public java.lang.String getAnalyseverf_para() {
        return analyseverf_para;
    }


    /**
     * Sets the analyseverf_para value for this Dea_Parameter.
     * 
     * @param analyseverf_para
     */
    public void setAnalyseverf_para(java.lang.String analyseverf_para) {
        this.analyseverf_para = analyseverf_para;
    }


    /**
     * Gets the berechnung_para value for this Dea_Parameter.
     * 
     * @return berechnung_para
     */
    public java.lang.String getBerechnung_para() {
        return berechnung_para;
    }


    /**
     * Sets the berechnung_para value for this Dea_Parameter.
     * 
     * @param berechnung_para
     */
    public void setBerechnung_para(java.lang.String berechnung_para) {
        this.berechnung_para = berechnung_para;
    }


    /**
     * Gets the frachtpar_jn value for this Dea_Parameter.
     * 
     * @return frachtpar_jn
     */
    public java.lang.Boolean getFrachtpar_jn() {
        return frachtpar_jn;
    }


    /**
     * Sets the frachtpar_jn value for this Dea_Parameter.
     * 
     * @param frachtpar_jn
     */
    public void setFrachtpar_jn(java.lang.Boolean frachtpar_jn) {
        this.frachtpar_jn = frachtpar_jn;
    }


    /**
     * Gets the gruppe_dev value for this Dea_Parameter.
     * 
     * @return gruppe_dev
     */
    public java.lang.String getGruppe_dev() {
        return gruppe_dev;
    }


    /**
     * Sets the gruppe_dev value for this Dea_Parameter.
     * 
     * @param gruppe_dev
     */
    public void setGruppe_dev(java.lang.String gruppe_dev) {
        this.gruppe_dev = gruppe_dev;
    }


    /**
     * Gets the masseinheiten_nr value for this Dea_Parameter.
     * 
     * @return masseinheiten_nr
     */
    public java.lang.Integer getMasseinheiten_nr() {
        return masseinheiten_nr;
    }


    /**
     * Sets the masseinheiten_nr value for this Dea_Parameter.
     * 
     * @param masseinheiten_nr
     */
    public void setMasseinheiten_nr(java.lang.Integer masseinheiten_nr) {
        this.masseinheiten_nr = masseinheiten_nr;
    }


    /**
     * Gets the masseinheiten_nr_1 value for this Dea_Parameter.
     * 
     * @return masseinheiten_nr_1
     */
    public java.lang.Integer getMasseinheiten_nr_1() {
        return masseinheiten_nr_1;
    }


    /**
     * Sets the masseinheiten_nr_1 value for this Dea_Parameter.
     * 
     * @param masseinheiten_nr_1
     */
    public void setMasseinheiten_nr_1(java.lang.Integer masseinheiten_nr_1) {
        this.masseinheiten_nr_1 = masseinheiten_nr_1;
    }


    /**
     * Gets the masseinheiten_ver value for this Dea_Parameter.
     * 
     * @return masseinheiten_ver
     */
    public java.lang.Integer getMasseinheiten_ver() {
        return masseinheiten_ver;
    }


    /**
     * Sets the masseinheiten_ver value for this Dea_Parameter.
     * 
     * @param masseinheiten_ver
     */
    public void setMasseinheiten_ver(java.lang.Integer masseinheiten_ver) {
        this.masseinheiten_ver = masseinheiten_ver;
    }


    /**
     * Gets the par_bedingung value for this Dea_Parameter.
     * 
     * @return par_bedingung
     */
    public java.lang.Integer getPar_bedingung() {
        return par_bedingung;
    }


    /**
     * Sets the par_bedingung value for this Dea_Parameter.
     * 
     * @param par_bedingung
     */
    public void setPar_bedingung(java.lang.Integer par_bedingung) {
        this.par_bedingung = par_bedingung;
    }


    /**
     * Gets the par_bereich value for this Dea_Parameter.
     * 
     * @return par_bereich
     */
    public java.lang.Integer getPar_bereich() {
        return par_bereich;
    }


    /**
     * Sets the par_bereich value for this Dea_Parameter.
     * 
     * @param par_bereich
     */
    public void setPar_bereich(java.lang.Integer par_bereich) {
        this.par_bereich = par_bereich;
    }


    /**
     * Gets the par_verwendung value for this Dea_Parameter.
     * 
     * @return par_verwendung
     */
    public java.lang.String getPar_verwendung() {
        return par_verwendung;
    }


    /**
     * Sets the par_verwendung value for this Dea_Parameter.
     * 
     * @param par_verwendung
     */
    public void setPar_verwendung(java.lang.String par_verwendung) {
        this.par_verwendung = par_verwendung;
    }


    /**
     * Gets the parameter_nr value for this Dea_Parameter.
     * 
     * @return parameter_nr
     */
    public java.lang.Integer getParameter_nr() {
        return parameter_nr;
    }


    /**
     * Sets the parameter_nr value for this Dea_Parameter.
     * 
     * @param parameter_nr
     */
    public void setParameter_nr(java.lang.Integer parameter_nr) {
        this.parameter_nr = parameter_nr;
    }


    /**
     * Gets the parameter_ver value for this Dea_Parameter.
     * 
     * @return parameter_ver
     */
    public java.lang.Integer getParameter_ver() {
        return parameter_ver;
    }


    /**
     * Sets the parameter_ver value for this Dea_Parameter.
     * 
     * @param parameter_ver
     */
    public void setParameter_ver(java.lang.Integer parameter_ver) {
        this.parameter_ver = parameter_ver;
    }


    /**
     * Gets the regelwerk_nr value for this Dea_Parameter.
     * 
     * @return regelwerk_nr
     */
    public java.lang.String getRegelwerk_nr() {
        return regelwerk_nr;
    }


    /**
     * Sets the regelwerk_nr value for this Dea_Parameter.
     * 
     * @param regelwerk_nr
     */
    public void setRegelwerk_nr(java.lang.String regelwerk_nr) {
        this.regelwerk_nr = regelwerk_nr;
    }


    /**
     * Gets the stoff_nr value for this Dea_Parameter.
     * 
     * @return stoff_nr
     */
    public java.lang.Integer getStoff_nr() {
        return stoff_nr;
    }


    /**
     * Sets the stoff_nr value for this Dea_Parameter.
     * 
     * @param stoff_nr
     */
    public void setStoff_nr(java.lang.Integer stoff_nr) {
        this.stoff_nr = stoff_nr;
    }


    /**
     * Gets the stoff_nr_1 value for this Dea_Parameter.
     * 
     * @return stoff_nr_1
     */
    public java.lang.Integer getStoff_nr_1() {
        return stoff_nr_1;
    }


    /**
     * Sets the stoff_nr_1 value for this Dea_Parameter.
     * 
     * @param stoff_nr_1
     */
    public void setStoff_nr_1(java.lang.Integer stoff_nr_1) {
        this.stoff_nr_1 = stoff_nr_1;
    }


    /**
     * Gets the stoff_ver value for this Dea_Parameter.
     * 
     * @return stoff_ver
     */
    public java.lang.Integer getStoff_ver() {
        return stoff_ver;
    }


    /**
     * Sets the stoff_ver value for this Dea_Parameter.
     * 
     * @param stoff_ver
     */
    public void setStoff_ver(java.lang.Integer stoff_ver) {
        this.stoff_ver = stoff_ver;
    }


    /**
     * Gets the trenn_nr_1 value for this Dea_Parameter.
     * 
     * @return trenn_nr_1
     */
    public java.lang.Integer getTrenn_nr_1() {
        return trenn_nr_1;
    }


    /**
     * Sets the trenn_nr_1 value for this Dea_Parameter.
     * 
     * @param trenn_nr_1
     */
    public void setTrenn_nr_1(java.lang.Integer trenn_nr_1) {
        this.trenn_nr_1 = trenn_nr_1;
    }


    /**
     * Gets the trenn_nr_opt value for this Dea_Parameter.
     * 
     * @return trenn_nr_opt
     */
    public java.lang.Integer getTrenn_nr_opt() {
        return trenn_nr_opt;
    }


    /**
     * Sets the trenn_nr_opt value for this Dea_Parameter.
     * 
     * @param trenn_nr_opt
     */
    public void setTrenn_nr_opt(java.lang.Integer trenn_nr_opt) {
        this.trenn_nr_opt = trenn_nr_opt;
    }


    /**
     * Gets the umrech_jn value for this Dea_Parameter.
     * 
     * @return umrech_jn
     */
    public java.lang.Boolean getUmrech_jn() {
        return umrech_jn;
    }


    /**
     * Sets the umrech_jn value for this Dea_Parameter.
     * 
     * @param umrech_jn
     */
    public void setUmrech_jn(java.lang.Boolean umrech_jn) {
        this.umrech_jn = umrech_jn;
    }


    /**
     * Gets the varianten_nr value for this Dea_Parameter.
     * 
     * @return varianten_nr
     */
    public java.lang.String getVarianten_nr() {
        return varianten_nr;
    }


    /**
     * Sets the varianten_nr value for this Dea_Parameter.
     * 
     * @param varianten_nr
     */
    public void setVarianten_nr(java.lang.String varianten_nr) {
        this.varianten_nr = varianten_nr;
    }


    /**
     * Gets the veran_dat_ab value for this Dea_Parameter.
     * 
     * @return veran_dat_ab
     */
    public java.util.Calendar getVeran_dat_ab() {
        return veran_dat_ab;
    }


    /**
     * Sets the veran_dat_ab value for this Dea_Parameter.
     * 
     * @param veran_dat_ab
     */
    public void setVeran_dat_ab(java.util.Calendar veran_dat_ab) {
        this.veran_dat_ab = veran_dat_ab;
    }


    /**
     * Gets the veran_dat_bis value for this Dea_Parameter.
     * 
     * @return veran_dat_bis
     */
    public java.util.Calendar getVeran_dat_bis() {
        return veran_dat_bis;
    }


    /**
     * Sets the veran_dat_bis value for this Dea_Parameter.
     * 
     * @param veran_dat_bis
     */
    public void setVeran_dat_bis(java.util.Calendar veran_dat_bis) {
        this.veran_dat_bis = veran_dat_bis;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Parameter)) return false;
        Dea_Parameter other = (Dea_Parameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.abgaberelevant_jn==null && other.getAbgaberelevant_jn()==null) || 
             (this.abgaberelevant_jn!=null &&
              this.abgaberelevant_jn.equals(other.getAbgaberelevant_jn()))) &&
            ((this.analyse_ver==null && other.getAnalyse_ver()==null) || 
             (this.analyse_ver!=null &&
              this.analyse_ver.equals(other.getAnalyse_ver()))) &&
            ((this.analyseverf_1_para==null && other.getAnalyseverf_1_para()==null) || 
             (this.analyseverf_1_para!=null &&
              this.analyseverf_1_para.equals(other.getAnalyseverf_1_para()))) &&
            ((this.analyseverf_para==null && other.getAnalyseverf_para()==null) || 
             (this.analyseverf_para!=null &&
              this.analyseverf_para.equals(other.getAnalyseverf_para()))) &&
            ((this.berechnung_para==null && other.getBerechnung_para()==null) || 
             (this.berechnung_para!=null &&
              this.berechnung_para.equals(other.getBerechnung_para()))) &&
            ((this.frachtpar_jn==null && other.getFrachtpar_jn()==null) || 
             (this.frachtpar_jn!=null &&
              this.frachtpar_jn.equals(other.getFrachtpar_jn()))) &&
            ((this.gruppe_dev==null && other.getGruppe_dev()==null) || 
             (this.gruppe_dev!=null &&
              this.gruppe_dev.equals(other.getGruppe_dev()))) &&
            ((this.masseinheiten_nr==null && other.getMasseinheiten_nr()==null) || 
             (this.masseinheiten_nr!=null &&
              this.masseinheiten_nr.equals(other.getMasseinheiten_nr()))) &&
            ((this.masseinheiten_nr_1==null && other.getMasseinheiten_nr_1()==null) || 
             (this.masseinheiten_nr_1!=null &&
              this.masseinheiten_nr_1.equals(other.getMasseinheiten_nr_1()))) &&
            ((this.masseinheiten_ver==null && other.getMasseinheiten_ver()==null) || 
             (this.masseinheiten_ver!=null &&
              this.masseinheiten_ver.equals(other.getMasseinheiten_ver()))) &&
            ((this.par_bedingung==null && other.getPar_bedingung()==null) || 
             (this.par_bedingung!=null &&
              this.par_bedingung.equals(other.getPar_bedingung()))) &&
            ((this.par_bereich==null && other.getPar_bereich()==null) || 
             (this.par_bereich!=null &&
              this.par_bereich.equals(other.getPar_bereich()))) &&
            ((this.par_verwendung==null && other.getPar_verwendung()==null) || 
             (this.par_verwendung!=null &&
              this.par_verwendung.equals(other.getPar_verwendung()))) &&
            ((this.parameter_nr==null && other.getParameter_nr()==null) || 
             (this.parameter_nr!=null &&
              this.parameter_nr.equals(other.getParameter_nr()))) &&
            ((this.parameter_ver==null && other.getParameter_ver()==null) || 
             (this.parameter_ver!=null &&
              this.parameter_ver.equals(other.getParameter_ver()))) &&
            ((this.regelwerk_nr==null && other.getRegelwerk_nr()==null) || 
             (this.regelwerk_nr!=null &&
              this.regelwerk_nr.equals(other.getRegelwerk_nr()))) &&
            ((this.stoff_nr==null && other.getStoff_nr()==null) || 
             (this.stoff_nr!=null &&
              this.stoff_nr.equals(other.getStoff_nr()))) &&
            ((this.stoff_nr_1==null && other.getStoff_nr_1()==null) || 
             (this.stoff_nr_1!=null &&
              this.stoff_nr_1.equals(other.getStoff_nr_1()))) &&
            ((this.stoff_ver==null && other.getStoff_ver()==null) || 
             (this.stoff_ver!=null &&
              this.stoff_ver.equals(other.getStoff_ver()))) &&
            ((this.trenn_nr_1==null && other.getTrenn_nr_1()==null) || 
             (this.trenn_nr_1!=null &&
              this.trenn_nr_1.equals(other.getTrenn_nr_1()))) &&
            ((this.trenn_nr_opt==null && other.getTrenn_nr_opt()==null) || 
             (this.trenn_nr_opt!=null &&
              this.trenn_nr_opt.equals(other.getTrenn_nr_opt()))) &&
            ((this.umrech_jn==null && other.getUmrech_jn()==null) || 
             (this.umrech_jn!=null &&
              this.umrech_jn.equals(other.getUmrech_jn()))) &&
            ((this.varianten_nr==null && other.getVarianten_nr()==null) || 
             (this.varianten_nr!=null &&
              this.varianten_nr.equals(other.getVarianten_nr()))) &&
            ((this.veran_dat_ab==null && other.getVeran_dat_ab()==null) || 
             (this.veran_dat_ab!=null &&
              this.veran_dat_ab.equals(other.getVeran_dat_ab()))) &&
            ((this.veran_dat_bis==null && other.getVeran_dat_bis()==null) || 
             (this.veran_dat_bis!=null &&
              this.veran_dat_bis.equals(other.getVeran_dat_bis())));
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
        if (getAbgaberelevant_jn() != null) {
            _hashCode += getAbgaberelevant_jn().hashCode();
        }
        if (getAnalyse_ver() != null) {
            _hashCode += getAnalyse_ver().hashCode();
        }
        if (getAnalyseverf_1_para() != null) {
            _hashCode += getAnalyseverf_1_para().hashCode();
        }
        if (getAnalyseverf_para() != null) {
            _hashCode += getAnalyseverf_para().hashCode();
        }
        if (getBerechnung_para() != null) {
            _hashCode += getBerechnung_para().hashCode();
        }
        if (getFrachtpar_jn() != null) {
            _hashCode += getFrachtpar_jn().hashCode();
        }
        if (getGruppe_dev() != null) {
            _hashCode += getGruppe_dev().hashCode();
        }
        if (getMasseinheiten_nr() != null) {
            _hashCode += getMasseinheiten_nr().hashCode();
        }
        if (getMasseinheiten_nr_1() != null) {
            _hashCode += getMasseinheiten_nr_1().hashCode();
        }
        if (getMasseinheiten_ver() != null) {
            _hashCode += getMasseinheiten_ver().hashCode();
        }
        if (getPar_bedingung() != null) {
            _hashCode += getPar_bedingung().hashCode();
        }
        if (getPar_bereich() != null) {
            _hashCode += getPar_bereich().hashCode();
        }
        if (getPar_verwendung() != null) {
            _hashCode += getPar_verwendung().hashCode();
        }
        if (getParameter_nr() != null) {
            _hashCode += getParameter_nr().hashCode();
        }
        if (getParameter_ver() != null) {
            _hashCode += getParameter_ver().hashCode();
        }
        if (getRegelwerk_nr() != null) {
            _hashCode += getRegelwerk_nr().hashCode();
        }
        if (getStoff_nr() != null) {
            _hashCode += getStoff_nr().hashCode();
        }
        if (getStoff_nr_1() != null) {
            _hashCode += getStoff_nr_1().hashCode();
        }
        if (getStoff_ver() != null) {
            _hashCode += getStoff_ver().hashCode();
        }
        if (getTrenn_nr_1() != null) {
            _hashCode += getTrenn_nr_1().hashCode();
        }
        if (getTrenn_nr_opt() != null) {
            _hashCode += getTrenn_nr_opt().hashCode();
        }
        if (getUmrech_jn() != null) {
            _hashCode += getUmrech_jn().hashCode();
        }
        if (getVarianten_nr() != null) {
            _hashCode += getVarianten_nr().hashCode();
        }
        if (getVeran_dat_ab() != null) {
            _hashCode += getVeran_dat_ab().hashCode();
        }
        if (getVeran_dat_bis() != null) {
            _hashCode += getVeran_dat_bis().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Parameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Parameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abgaberelevant_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abgaberelevant_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("analyse_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "analyse_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("analyseverf_1_para");
        elemField.setXmlName(new javax.xml.namespace.QName("", "analyseverf_1_para"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("analyseverf_para");
        elemField.setXmlName(new javax.xml.namespace.QName("", "analyseverf_para"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("berechnung_para");
        elemField.setXmlName(new javax.xml.namespace.QName("", "berechnung_para"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frachtpar_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "frachtpar_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gruppe_dev");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gruppe_dev"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masseinheiten_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "masseinheiten_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masseinheiten_nr_1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "masseinheiten_nr_1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masseinheiten_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "masseinheiten_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("par_bedingung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "par_bedingung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("par_bereich");
        elemField.setXmlName(new javax.xml.namespace.QName("", "par_bereich"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("par_verwendung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "par_verwendung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameter_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parameter_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameter_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parameter_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regelwerk_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "regelwerk_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stoff_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stoff_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stoff_nr_1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stoff_nr_1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stoff_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stoff_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trenn_nr_1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trenn_nr_1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trenn_nr_opt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trenn_nr_opt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("umrech_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "umrech_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("varianten_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "varianten_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("veran_dat_ab");
        elemField.setXmlName(new javax.xml.namespace.QName("", "veran_dat_ab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("veran_dat_bis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "veran_dat_bis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
