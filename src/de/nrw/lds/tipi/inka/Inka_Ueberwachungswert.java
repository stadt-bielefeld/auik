/**
 * Inka_Ueberwachungswert.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Ueberwachungswert  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer anz_jahr;

    private java.lang.Integer gemeinde_ver;

    private java.lang.String gemeindekennzahl;

    private java.lang.Integer genehmigung_nr;

    private java.lang.Integer genehmigung_ver;

    private java.lang.Float jahresfracht;

    private java.lang.Integer messstelle_lfd_nr;

    private java.lang.Integer messstelle_ver;

    private java.lang.Integer parameter_nr;

    private java.lang.Integer parameter_ver;

    private java.lang.Boolean selbst_amtl_jn;

    private java.lang.Integer uebergabestelle_lfd_nr;

    private java.lang.Integer uebergabestelle_ver;

    private java.lang.Integer ueberwachungswert_ver;

    private java.lang.Float uewert;

    private java.lang.Float uewert_obergr;

    private java.lang.Float uewert_untergr;

    public Inka_Ueberwachungswert() {
    }

    public Inka_Ueberwachungswert(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer anz_jahr,
           java.lang.Integer gemeinde_ver,
           java.lang.String gemeindekennzahl,
           java.lang.Integer genehmigung_nr,
           java.lang.Integer genehmigung_ver,
           java.lang.Float jahresfracht,
           java.lang.Integer messstelle_lfd_nr,
           java.lang.Integer messstelle_ver,
           java.lang.Integer parameter_nr,
           java.lang.Integer parameter_ver,
           java.lang.Boolean selbst_amtl_jn,
           java.lang.Integer uebergabestelle_lfd_nr,
           java.lang.Integer uebergabestelle_ver,
           java.lang.Integer ueberwachungswert_ver,
           java.lang.Float uewert,
           java.lang.Float uewert_obergr,
           java.lang.Float uewert_untergr) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.anz_jahr = anz_jahr;
        this.gemeinde_ver = gemeinde_ver;
        this.gemeindekennzahl = gemeindekennzahl;
        this.genehmigung_nr = genehmigung_nr;
        this.genehmigung_ver = genehmigung_ver;
        this.jahresfracht = jahresfracht;
        this.messstelle_lfd_nr = messstelle_lfd_nr;
        this.messstelle_ver = messstelle_ver;
        this.parameter_nr = parameter_nr;
        this.parameter_ver = parameter_ver;
        this.selbst_amtl_jn = selbst_amtl_jn;
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
        this.uebergabestelle_ver = uebergabestelle_ver;
        this.ueberwachungswert_ver = ueberwachungswert_ver;
        this.uewert = uewert;
        this.uewert_obergr = uewert_obergr;
        this.uewert_untergr = uewert_untergr;
    }


    /**
     * Gets the anz_jahr value for this Inka_Ueberwachungswert.
     * 
     * @return anz_jahr
     */
    public java.lang.Integer getAnz_jahr() {
        return anz_jahr;
    }


    /**
     * Sets the anz_jahr value for this Inka_Ueberwachungswert.
     * 
     * @param anz_jahr
     */
    public void setAnz_jahr(java.lang.Integer anz_jahr) {
        this.anz_jahr = anz_jahr;
    }


    /**
     * Gets the gemeinde_ver value for this Inka_Ueberwachungswert.
     * 
     * @return gemeinde_ver
     */
    public java.lang.Integer getGemeinde_ver() {
        return gemeinde_ver;
    }


    /**
     * Sets the gemeinde_ver value for this Inka_Ueberwachungswert.
     * 
     * @param gemeinde_ver
     */
    public void setGemeinde_ver(java.lang.Integer gemeinde_ver) {
        this.gemeinde_ver = gemeinde_ver;
    }


    /**
     * Gets the gemeindekennzahl value for this Inka_Ueberwachungswert.
     * 
     * @return gemeindekennzahl
     */
    public java.lang.String getGemeindekennzahl() {
        return gemeindekennzahl;
    }


    /**
     * Sets the gemeindekennzahl value for this Inka_Ueberwachungswert.
     * 
     * @param gemeindekennzahl
     */
    public void setGemeindekennzahl(java.lang.String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }


    /**
     * Gets the genehmigung_nr value for this Inka_Ueberwachungswert.
     * 
     * @return genehmigung_nr
     */
    public java.lang.Integer getGenehmigung_nr() {
        return genehmigung_nr;
    }


    /**
     * Sets the genehmigung_nr value for this Inka_Ueberwachungswert.
     * 
     * @param genehmigung_nr
     */
    public void setGenehmigung_nr(java.lang.Integer genehmigung_nr) {
        this.genehmigung_nr = genehmigung_nr;
    }


    /**
     * Gets the genehmigung_ver value for this Inka_Ueberwachungswert.
     * 
     * @return genehmigung_ver
     */
    public java.lang.Integer getGenehmigung_ver() {
        return genehmigung_ver;
    }


    /**
     * Sets the genehmigung_ver value for this Inka_Ueberwachungswert.
     * 
     * @param genehmigung_ver
     */
    public void setGenehmigung_ver(java.lang.Integer genehmigung_ver) {
        this.genehmigung_ver = genehmigung_ver;
    }


    /**
     * Gets the jahresfracht value for this Inka_Ueberwachungswert.
     * 
     * @return jahresfracht
     */
    public java.lang.Float getJahresfracht() {
        return jahresfracht;
    }


    /**
     * Sets the jahresfracht value for this Inka_Ueberwachungswert.
     * 
     * @param jahresfracht
     */
    public void setJahresfracht(java.lang.Float jahresfracht) {
        this.jahresfracht = jahresfracht;
    }


    /**
     * Gets the messstelle_lfd_nr value for this Inka_Ueberwachungswert.
     * 
     * @return messstelle_lfd_nr
     */
    public java.lang.Integer getMessstelle_lfd_nr() {
        return messstelle_lfd_nr;
    }


    /**
     * Sets the messstelle_lfd_nr value for this Inka_Ueberwachungswert.
     * 
     * @param messstelle_lfd_nr
     */
    public void setMessstelle_lfd_nr(java.lang.Integer messstelle_lfd_nr) {
        this.messstelle_lfd_nr = messstelle_lfd_nr;
    }


    /**
     * Gets the messstelle_ver value for this Inka_Ueberwachungswert.
     * 
     * @return messstelle_ver
     */
    public java.lang.Integer getMessstelle_ver() {
        return messstelle_ver;
    }


    /**
     * Sets the messstelle_ver value for this Inka_Ueberwachungswert.
     * 
     * @param messstelle_ver
     */
    public void setMessstelle_ver(java.lang.Integer messstelle_ver) {
        this.messstelle_ver = messstelle_ver;
    }


    /**
     * Gets the parameter_nr value for this Inka_Ueberwachungswert.
     * 
     * @return parameter_nr
     */
    public java.lang.Integer getParameter_nr() {
        return parameter_nr;
    }


    /**
     * Sets the parameter_nr value for this Inka_Ueberwachungswert.
     * 
     * @param parameter_nr
     */
    public void setParameter_nr(java.lang.Integer parameter_nr) {
        this.parameter_nr = parameter_nr;
    }


    /**
     * Gets the parameter_ver value for this Inka_Ueberwachungswert.
     * 
     * @return parameter_ver
     */
    public java.lang.Integer getParameter_ver() {
        return parameter_ver;
    }


    /**
     * Sets the parameter_ver value for this Inka_Ueberwachungswert.
     * 
     * @param parameter_ver
     */
    public void setParameter_ver(java.lang.Integer parameter_ver) {
        this.parameter_ver = parameter_ver;
    }


    /**
     * Gets the selbst_amtl_jn value for this Inka_Ueberwachungswert.
     * 
     * @return selbst_amtl_jn
     */
    public java.lang.Boolean getSelbst_amtl_jn() {
        return selbst_amtl_jn;
    }


    /**
     * Sets the selbst_amtl_jn value for this Inka_Ueberwachungswert.
     * 
     * @param selbst_amtl_jn
     */
    public void setSelbst_amtl_jn(java.lang.Boolean selbst_amtl_jn) {
        this.selbst_amtl_jn = selbst_amtl_jn;
    }


    /**
     * Gets the uebergabestelle_lfd_nr value for this Inka_Ueberwachungswert.
     * 
     * @return uebergabestelle_lfd_nr
     */
    public java.lang.Integer getUebergabestelle_lfd_nr() {
        return uebergabestelle_lfd_nr;
    }


    /**
     * Sets the uebergabestelle_lfd_nr value for this Inka_Ueberwachungswert.
     * 
     * @param uebergabestelle_lfd_nr
     */
    public void setUebergabestelle_lfd_nr(java.lang.Integer uebergabestelle_lfd_nr) {
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
    }


    /**
     * Gets the uebergabestelle_ver value for this Inka_Ueberwachungswert.
     * 
     * @return uebergabestelle_ver
     */
    public java.lang.Integer getUebergabestelle_ver() {
        return uebergabestelle_ver;
    }


    /**
     * Sets the uebergabestelle_ver value for this Inka_Ueberwachungswert.
     * 
     * @param uebergabestelle_ver
     */
    public void setUebergabestelle_ver(java.lang.Integer uebergabestelle_ver) {
        this.uebergabestelle_ver = uebergabestelle_ver;
    }


    /**
     * Gets the ueberwachungswert_ver value for this Inka_Ueberwachungswert.
     * 
     * @return ueberwachungswert_ver
     */
    public java.lang.Integer getUeberwachungswert_ver() {
        return ueberwachungswert_ver;
    }


    /**
     * Sets the ueberwachungswert_ver value for this Inka_Ueberwachungswert.
     * 
     * @param ueberwachungswert_ver
     */
    public void setUeberwachungswert_ver(java.lang.Integer ueberwachungswert_ver) {
        this.ueberwachungswert_ver = ueberwachungswert_ver;
    }


    /**
     * Gets the uewert value for this Inka_Ueberwachungswert.
     * 
     * @return uewert
     */
    public java.lang.Float getUewert() {
        return uewert;
    }


    /**
     * Sets the uewert value for this Inka_Ueberwachungswert.
     * 
     * @param uewert
     */
    public void setUewert(java.lang.Float uewert) {
        this.uewert = uewert;
    }


    /**
     * Gets the uewert_obergr value for this Inka_Ueberwachungswert.
     * 
     * @return uewert_obergr
     */
    public java.lang.Float getUewert_obergr() {
        return uewert_obergr;
    }


    /**
     * Sets the uewert_obergr value for this Inka_Ueberwachungswert.
     * 
     * @param uewert_obergr
     */
    public void setUewert_obergr(java.lang.Float uewert_obergr) {
        this.uewert_obergr = uewert_obergr;
    }


    /**
     * Gets the uewert_untergr value for this Inka_Ueberwachungswert.
     * 
     * @return uewert_untergr
     */
    public java.lang.Float getUewert_untergr() {
        return uewert_untergr;
    }


    /**
     * Sets the uewert_untergr value for this Inka_Ueberwachungswert.
     * 
     * @param uewert_untergr
     */
    public void setUewert_untergr(java.lang.Float uewert_untergr) {
        this.uewert_untergr = uewert_untergr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Ueberwachungswert)) return false;
        Inka_Ueberwachungswert other = (Inka_Ueberwachungswert) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.anz_jahr==null && other.getAnz_jahr()==null) || 
             (this.anz_jahr!=null &&
              this.anz_jahr.equals(other.getAnz_jahr()))) &&
            ((this.gemeinde_ver==null && other.getGemeinde_ver()==null) || 
             (this.gemeinde_ver!=null &&
              this.gemeinde_ver.equals(other.getGemeinde_ver()))) &&
            ((this.gemeindekennzahl==null && other.getGemeindekennzahl()==null) || 
             (this.gemeindekennzahl!=null &&
              this.gemeindekennzahl.equals(other.getGemeindekennzahl()))) &&
            ((this.genehmigung_nr==null && other.getGenehmigung_nr()==null) || 
             (this.genehmigung_nr!=null &&
              this.genehmigung_nr.equals(other.getGenehmigung_nr()))) &&
            ((this.genehmigung_ver==null && other.getGenehmigung_ver()==null) || 
             (this.genehmigung_ver!=null &&
              this.genehmigung_ver.equals(other.getGenehmigung_ver()))) &&
            ((this.jahresfracht==null && other.getJahresfracht()==null) || 
             (this.jahresfracht!=null &&
              this.jahresfracht.equals(other.getJahresfracht()))) &&
            ((this.messstelle_lfd_nr==null && other.getMessstelle_lfd_nr()==null) || 
             (this.messstelle_lfd_nr!=null &&
              this.messstelle_lfd_nr.equals(other.getMessstelle_lfd_nr()))) &&
            ((this.messstelle_ver==null && other.getMessstelle_ver()==null) || 
             (this.messstelle_ver!=null &&
              this.messstelle_ver.equals(other.getMessstelle_ver()))) &&
            ((this.parameter_nr==null && other.getParameter_nr()==null) || 
             (this.parameter_nr!=null &&
              this.parameter_nr.equals(other.getParameter_nr()))) &&
            ((this.parameter_ver==null && other.getParameter_ver()==null) || 
             (this.parameter_ver!=null &&
              this.parameter_ver.equals(other.getParameter_ver()))) &&
            ((this.selbst_amtl_jn==null && other.getSelbst_amtl_jn()==null) || 
             (this.selbst_amtl_jn!=null &&
              this.selbst_amtl_jn.equals(other.getSelbst_amtl_jn()))) &&
            ((this.uebergabestelle_lfd_nr==null && other.getUebergabestelle_lfd_nr()==null) || 
             (this.uebergabestelle_lfd_nr!=null &&
              this.uebergabestelle_lfd_nr.equals(other.getUebergabestelle_lfd_nr()))) &&
            ((this.uebergabestelle_ver==null && other.getUebergabestelle_ver()==null) || 
             (this.uebergabestelle_ver!=null &&
              this.uebergabestelle_ver.equals(other.getUebergabestelle_ver()))) &&
            ((this.ueberwachungswert_ver==null && other.getUeberwachungswert_ver()==null) || 
             (this.ueberwachungswert_ver!=null &&
              this.ueberwachungswert_ver.equals(other.getUeberwachungswert_ver()))) &&
            ((this.uewert==null && other.getUewert()==null) || 
             (this.uewert!=null &&
              this.uewert.equals(other.getUewert()))) &&
            ((this.uewert_obergr==null && other.getUewert_obergr()==null) || 
             (this.uewert_obergr!=null &&
              this.uewert_obergr.equals(other.getUewert_obergr()))) &&
            ((this.uewert_untergr==null && other.getUewert_untergr()==null) || 
             (this.uewert_untergr!=null &&
              this.uewert_untergr.equals(other.getUewert_untergr())));
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
        if (getAnz_jahr() != null) {
            _hashCode += getAnz_jahr().hashCode();
        }
        if (getGemeinde_ver() != null) {
            _hashCode += getGemeinde_ver().hashCode();
        }
        if (getGemeindekennzahl() != null) {
            _hashCode += getGemeindekennzahl().hashCode();
        }
        if (getGenehmigung_nr() != null) {
            _hashCode += getGenehmigung_nr().hashCode();
        }
        if (getGenehmigung_ver() != null) {
            _hashCode += getGenehmigung_ver().hashCode();
        }
        if (getJahresfracht() != null) {
            _hashCode += getJahresfracht().hashCode();
        }
        if (getMessstelle_lfd_nr() != null) {
            _hashCode += getMessstelle_lfd_nr().hashCode();
        }
        if (getMessstelle_ver() != null) {
            _hashCode += getMessstelle_ver().hashCode();
        }
        if (getParameter_nr() != null) {
            _hashCode += getParameter_nr().hashCode();
        }
        if (getParameter_ver() != null) {
            _hashCode += getParameter_ver().hashCode();
        }
        if (getSelbst_amtl_jn() != null) {
            _hashCode += getSelbst_amtl_jn().hashCode();
        }
        if (getUebergabestelle_lfd_nr() != null) {
            _hashCode += getUebergabestelle_lfd_nr().hashCode();
        }
        if (getUebergabestelle_ver() != null) {
            _hashCode += getUebergabestelle_ver().hashCode();
        }
        if (getUeberwachungswert_ver() != null) {
            _hashCode += getUeberwachungswert_ver().hashCode();
        }
        if (getUewert() != null) {
            _hashCode += getUewert().hashCode();
        }
        if (getUewert_obergr() != null) {
            _hashCode += getUewert_obergr().hashCode();
        }
        if (getUewert_untergr() != null) {
            _hashCode += getUewert_untergr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Ueberwachungswert.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Ueberwachungswert"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anz_jahr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anz_jahr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("jahresfracht");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jahresfracht"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messstelle_lfd_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messstelle_lfd_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messstelle_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messstelle_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("selbst_amtl_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "selbst_amtl_jn"));
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
        elemField.setFieldName("ueberwachungswert_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ueberwachungswert_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uewert");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uewert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uewert_obergr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uewert_obergr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uewert_untergr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uewert_untergr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
