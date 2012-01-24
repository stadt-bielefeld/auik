/**
 * Dea_Arbeitsstaetten.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Arbeitsstaetten  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer arbeitsstaette_seq_nr;

    private java.lang.Integer arbeitsstaette_ver;

    private java.lang.String astnr;

    private java.lang.String b_art;

    private java.lang.String b_firma1;

    private java.lang.String b_firma2;

    private java.lang.String b_gkz;

    private java.lang.String b_lk;

    private java.lang.String b_nr;

    private java.lang.String b_ort;

    private java.lang.String b_ortsteil;

    private java.lang.String b_plz;

    private java.lang.String b_plz_postfach;

    private java.lang.String b_postfach;

    private java.lang.String b_strasse;

    private java.lang.String b_tel_nr;

    private java.lang.String b_tfx_nr;

    private java.util.Calendar datum1;

    private java.util.Calendar datum2;

    private java.lang.String gaa_nr;

    private java.lang.String ggr;

    private java.lang.String temp;

    private java.lang.String wkl;

    public Dea_Arbeitsstaetten() {
    }

    public Dea_Arbeitsstaetten(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer arbeitsstaette_seq_nr,
           java.lang.Integer arbeitsstaette_ver,
           java.lang.String astnr,
           java.lang.String b_art,
           java.lang.String b_firma1,
           java.lang.String b_firma2,
           java.lang.String b_gkz,
           java.lang.String b_lk,
           java.lang.String b_nr,
           java.lang.String b_ort,
           java.lang.String b_ortsteil,
           java.lang.String b_plz,
           java.lang.String b_plz_postfach,
           java.lang.String b_postfach,
           java.lang.String b_strasse,
           java.lang.String b_tel_nr,
           java.lang.String b_tfx_nr,
           java.util.Calendar datum1,
           java.util.Calendar datum2,
           java.lang.String gaa_nr,
           java.lang.String ggr,
           java.lang.String temp,
           java.lang.String wkl) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.arbeitsstaette_seq_nr = arbeitsstaette_seq_nr;
        this.arbeitsstaette_ver = arbeitsstaette_ver;
        this.astnr = astnr;
        this.b_art = b_art;
        this.b_firma1 = b_firma1;
        this.b_firma2 = b_firma2;
        this.b_gkz = b_gkz;
        this.b_lk = b_lk;
        this.b_nr = b_nr;
        this.b_ort = b_ort;
        this.b_ortsteil = b_ortsteil;
        this.b_plz = b_plz;
        this.b_plz_postfach = b_plz_postfach;
        this.b_postfach = b_postfach;
        this.b_strasse = b_strasse;
        this.b_tel_nr = b_tel_nr;
        this.b_tfx_nr = b_tfx_nr;
        this.datum1 = datum1;
        this.datum2 = datum2;
        this.gaa_nr = gaa_nr;
        this.ggr = ggr;
        this.temp = temp;
        this.wkl = wkl;
    }


    /**
     * Gets the arbeitsstaette_seq_nr value for this Dea_Arbeitsstaetten.
     * 
     * @return arbeitsstaette_seq_nr
     */
    public java.lang.Integer getArbeitsstaette_seq_nr() {
        return arbeitsstaette_seq_nr;
    }


    /**
     * Sets the arbeitsstaette_seq_nr value for this Dea_Arbeitsstaetten.
     * 
     * @param arbeitsstaette_seq_nr
     */
    public void setArbeitsstaette_seq_nr(java.lang.Integer arbeitsstaette_seq_nr) {
        this.arbeitsstaette_seq_nr = arbeitsstaette_seq_nr;
    }


    /**
     * Gets the arbeitsstaette_ver value for this Dea_Arbeitsstaetten.
     * 
     * @return arbeitsstaette_ver
     */
    public java.lang.Integer getArbeitsstaette_ver() {
        return arbeitsstaette_ver;
    }


    /**
     * Sets the arbeitsstaette_ver value for this Dea_Arbeitsstaetten.
     * 
     * @param arbeitsstaette_ver
     */
    public void setArbeitsstaette_ver(java.lang.Integer arbeitsstaette_ver) {
        this.arbeitsstaette_ver = arbeitsstaette_ver;
    }


    /**
     * Gets the astnr value for this Dea_Arbeitsstaetten.
     * 
     * @return astnr
     */
    public java.lang.String getAstnr() {
        return astnr;
    }


    /**
     * Sets the astnr value for this Dea_Arbeitsstaetten.
     * 
     * @param astnr
     */
    public void setAstnr(java.lang.String astnr) {
        this.astnr = astnr;
    }


    /**
     * Gets the b_art value for this Dea_Arbeitsstaetten.
     * 
     * @return b_art
     */
    public java.lang.String getB_art() {
        return b_art;
    }


    /**
     * Sets the b_art value for this Dea_Arbeitsstaetten.
     * 
     * @param b_art
     */
    public void setB_art(java.lang.String b_art) {
        this.b_art = b_art;
    }


    /**
     * Gets the b_firma1 value for this Dea_Arbeitsstaetten.
     * 
     * @return b_firma1
     */
    public java.lang.String getB_firma1() {
        return b_firma1;
    }


    /**
     * Sets the b_firma1 value for this Dea_Arbeitsstaetten.
     * 
     * @param b_firma1
     */
    public void setB_firma1(java.lang.String b_firma1) {
        this.b_firma1 = b_firma1;
    }


    /**
     * Gets the b_firma2 value for this Dea_Arbeitsstaetten.
     * 
     * @return b_firma2
     */
    public java.lang.String getB_firma2() {
        return b_firma2;
    }


    /**
     * Sets the b_firma2 value for this Dea_Arbeitsstaetten.
     * 
     * @param b_firma2
     */
    public void setB_firma2(java.lang.String b_firma2) {
        this.b_firma2 = b_firma2;
    }


    /**
     * Gets the b_gkz value for this Dea_Arbeitsstaetten.
     * 
     * @return b_gkz
     */
    public java.lang.String getB_gkz() {
        return b_gkz;
    }


    /**
     * Sets the b_gkz value for this Dea_Arbeitsstaetten.
     * 
     * @param b_gkz
     */
    public void setB_gkz(java.lang.String b_gkz) {
        this.b_gkz = b_gkz;
    }


    /**
     * Gets the b_lk value for this Dea_Arbeitsstaetten.
     * 
     * @return b_lk
     */
    public java.lang.String getB_lk() {
        return b_lk;
    }


    /**
     * Sets the b_lk value for this Dea_Arbeitsstaetten.
     * 
     * @param b_lk
     */
    public void setB_lk(java.lang.String b_lk) {
        this.b_lk = b_lk;
    }


    /**
     * Gets the b_nr value for this Dea_Arbeitsstaetten.
     * 
     * @return b_nr
     */
    public java.lang.String getB_nr() {
        return b_nr;
    }


    /**
     * Sets the b_nr value for this Dea_Arbeitsstaetten.
     * 
     * @param b_nr
     */
    public void setB_nr(java.lang.String b_nr) {
        this.b_nr = b_nr;
    }


    /**
     * Gets the b_ort value for this Dea_Arbeitsstaetten.
     * 
     * @return b_ort
     */
    public java.lang.String getB_ort() {
        return b_ort;
    }


    /**
     * Sets the b_ort value for this Dea_Arbeitsstaetten.
     * 
     * @param b_ort
     */
    public void setB_ort(java.lang.String b_ort) {
        this.b_ort = b_ort;
    }


    /**
     * Gets the b_ortsteil value for this Dea_Arbeitsstaetten.
     * 
     * @return b_ortsteil
     */
    public java.lang.String getB_ortsteil() {
        return b_ortsteil;
    }


    /**
     * Sets the b_ortsteil value for this Dea_Arbeitsstaetten.
     * 
     * @param b_ortsteil
     */
    public void setB_ortsteil(java.lang.String b_ortsteil) {
        this.b_ortsteil = b_ortsteil;
    }


    /**
     * Gets the b_plz value for this Dea_Arbeitsstaetten.
     * 
     * @return b_plz
     */
    public java.lang.String getB_plz() {
        return b_plz;
    }


    /**
     * Sets the b_plz value for this Dea_Arbeitsstaetten.
     * 
     * @param b_plz
     */
    public void setB_plz(java.lang.String b_plz) {
        this.b_plz = b_plz;
    }


    /**
     * Gets the b_plz_postfach value for this Dea_Arbeitsstaetten.
     * 
     * @return b_plz_postfach
     */
    public java.lang.String getB_plz_postfach() {
        return b_plz_postfach;
    }


    /**
     * Sets the b_plz_postfach value for this Dea_Arbeitsstaetten.
     * 
     * @param b_plz_postfach
     */
    public void setB_plz_postfach(java.lang.String b_plz_postfach) {
        this.b_plz_postfach = b_plz_postfach;
    }


    /**
     * Gets the b_postfach value for this Dea_Arbeitsstaetten.
     * 
     * @return b_postfach
     */
    public java.lang.String getB_postfach() {
        return b_postfach;
    }


    /**
     * Sets the b_postfach value for this Dea_Arbeitsstaetten.
     * 
     * @param b_postfach
     */
    public void setB_postfach(java.lang.String b_postfach) {
        this.b_postfach = b_postfach;
    }


    /**
     * Gets the b_strasse value for this Dea_Arbeitsstaetten.
     * 
     * @return b_strasse
     */
    public java.lang.String getB_strasse() {
        return b_strasse;
    }


    /**
     * Sets the b_strasse value for this Dea_Arbeitsstaetten.
     * 
     * @param b_strasse
     */
    public void setB_strasse(java.lang.String b_strasse) {
        this.b_strasse = b_strasse;
    }


    /**
     * Gets the b_tel_nr value for this Dea_Arbeitsstaetten.
     * 
     * @return b_tel_nr
     */
    public java.lang.String getB_tel_nr() {
        return b_tel_nr;
    }


    /**
     * Sets the b_tel_nr value for this Dea_Arbeitsstaetten.
     * 
     * @param b_tel_nr
     */
    public void setB_tel_nr(java.lang.String b_tel_nr) {
        this.b_tel_nr = b_tel_nr;
    }


    /**
     * Gets the b_tfx_nr value for this Dea_Arbeitsstaetten.
     * 
     * @return b_tfx_nr
     */
    public java.lang.String getB_tfx_nr() {
        return b_tfx_nr;
    }


    /**
     * Sets the b_tfx_nr value for this Dea_Arbeitsstaetten.
     * 
     * @param b_tfx_nr
     */
    public void setB_tfx_nr(java.lang.String b_tfx_nr) {
        this.b_tfx_nr = b_tfx_nr;
    }


    /**
     * Gets the datum1 value for this Dea_Arbeitsstaetten.
     * 
     * @return datum1
     */
    public java.util.Calendar getDatum1() {
        return datum1;
    }


    /**
     * Sets the datum1 value for this Dea_Arbeitsstaetten.
     * 
     * @param datum1
     */
    public void setDatum1(java.util.Calendar datum1) {
        this.datum1 = datum1;
    }


    /**
     * Gets the datum2 value for this Dea_Arbeitsstaetten.
     * 
     * @return datum2
     */
    public java.util.Calendar getDatum2() {
        return datum2;
    }


    /**
     * Sets the datum2 value for this Dea_Arbeitsstaetten.
     * 
     * @param datum2
     */
    public void setDatum2(java.util.Calendar datum2) {
        this.datum2 = datum2;
    }


    /**
     * Gets the gaa_nr value for this Dea_Arbeitsstaetten.
     * 
     * @return gaa_nr
     */
    public java.lang.String getGaa_nr() {
        return gaa_nr;
    }


    /**
     * Sets the gaa_nr value for this Dea_Arbeitsstaetten.
     * 
     * @param gaa_nr
     */
    public void setGaa_nr(java.lang.String gaa_nr) {
        this.gaa_nr = gaa_nr;
    }


    /**
     * Gets the ggr value for this Dea_Arbeitsstaetten.
     * 
     * @return ggr
     */
    public java.lang.String getGgr() {
        return ggr;
    }


    /**
     * Sets the ggr value for this Dea_Arbeitsstaetten.
     * 
     * @param ggr
     */
    public void setGgr(java.lang.String ggr) {
        this.ggr = ggr;
    }


    /**
     * Gets the temp value for this Dea_Arbeitsstaetten.
     * 
     * @return temp
     */
    public java.lang.String getTemp() {
        return temp;
    }


    /**
     * Sets the temp value for this Dea_Arbeitsstaetten.
     * 
     * @param temp
     */
    public void setTemp(java.lang.String temp) {
        this.temp = temp;
    }


    /**
     * Gets the wkl value for this Dea_Arbeitsstaetten.
     * 
     * @return wkl
     */
    public java.lang.String getWkl() {
        return wkl;
    }


    /**
     * Sets the wkl value for this Dea_Arbeitsstaetten.
     * 
     * @param wkl
     */
    public void setWkl(java.lang.String wkl) {
        this.wkl = wkl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Arbeitsstaetten)) return false;
        Dea_Arbeitsstaetten other = (Dea_Arbeitsstaetten) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arbeitsstaette_seq_nr==null && other.getArbeitsstaette_seq_nr()==null) || 
             (this.arbeitsstaette_seq_nr!=null &&
              this.arbeitsstaette_seq_nr.equals(other.getArbeitsstaette_seq_nr()))) &&
            ((this.arbeitsstaette_ver==null && other.getArbeitsstaette_ver()==null) || 
             (this.arbeitsstaette_ver!=null &&
              this.arbeitsstaette_ver.equals(other.getArbeitsstaette_ver()))) &&
            ((this.astnr==null && other.getAstnr()==null) || 
             (this.astnr!=null &&
              this.astnr.equals(other.getAstnr()))) &&
            ((this.b_art==null && other.getB_art()==null) || 
             (this.b_art!=null &&
              this.b_art.equals(other.getB_art()))) &&
            ((this.b_firma1==null && other.getB_firma1()==null) || 
             (this.b_firma1!=null &&
              this.b_firma1.equals(other.getB_firma1()))) &&
            ((this.b_firma2==null && other.getB_firma2()==null) || 
             (this.b_firma2!=null &&
              this.b_firma2.equals(other.getB_firma2()))) &&
            ((this.b_gkz==null && other.getB_gkz()==null) || 
             (this.b_gkz!=null &&
              this.b_gkz.equals(other.getB_gkz()))) &&
            ((this.b_lk==null && other.getB_lk()==null) || 
             (this.b_lk!=null &&
              this.b_lk.equals(other.getB_lk()))) &&
            ((this.b_nr==null && other.getB_nr()==null) || 
             (this.b_nr!=null &&
              this.b_nr.equals(other.getB_nr()))) &&
            ((this.b_ort==null && other.getB_ort()==null) || 
             (this.b_ort!=null &&
              this.b_ort.equals(other.getB_ort()))) &&
            ((this.b_ortsteil==null && other.getB_ortsteil()==null) || 
             (this.b_ortsteil!=null &&
              this.b_ortsteil.equals(other.getB_ortsteil()))) &&
            ((this.b_plz==null && other.getB_plz()==null) || 
             (this.b_plz!=null &&
              this.b_plz.equals(other.getB_plz()))) &&
            ((this.b_plz_postfach==null && other.getB_plz_postfach()==null) || 
             (this.b_plz_postfach!=null &&
              this.b_plz_postfach.equals(other.getB_plz_postfach()))) &&
            ((this.b_postfach==null && other.getB_postfach()==null) || 
             (this.b_postfach!=null &&
              this.b_postfach.equals(other.getB_postfach()))) &&
            ((this.b_strasse==null && other.getB_strasse()==null) || 
             (this.b_strasse!=null &&
              this.b_strasse.equals(other.getB_strasse()))) &&
            ((this.b_tel_nr==null && other.getB_tel_nr()==null) || 
             (this.b_tel_nr!=null &&
              this.b_tel_nr.equals(other.getB_tel_nr()))) &&
            ((this.b_tfx_nr==null && other.getB_tfx_nr()==null) || 
             (this.b_tfx_nr!=null &&
              this.b_tfx_nr.equals(other.getB_tfx_nr()))) &&
            ((this.datum1==null && other.getDatum1()==null) || 
             (this.datum1!=null &&
              this.datum1.equals(other.getDatum1()))) &&
            ((this.datum2==null && other.getDatum2()==null) || 
             (this.datum2!=null &&
              this.datum2.equals(other.getDatum2()))) &&
            ((this.gaa_nr==null && other.getGaa_nr()==null) || 
             (this.gaa_nr!=null &&
              this.gaa_nr.equals(other.getGaa_nr()))) &&
            ((this.ggr==null && other.getGgr()==null) || 
             (this.ggr!=null &&
              this.ggr.equals(other.getGgr()))) &&
            ((this.temp==null && other.getTemp()==null) || 
             (this.temp!=null &&
              this.temp.equals(other.getTemp()))) &&
            ((this.wkl==null && other.getWkl()==null) || 
             (this.wkl!=null &&
              this.wkl.equals(other.getWkl())));
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
        if (getArbeitsstaette_seq_nr() != null) {
            _hashCode += getArbeitsstaette_seq_nr().hashCode();
        }
        if (getArbeitsstaette_ver() != null) {
            _hashCode += getArbeitsstaette_ver().hashCode();
        }
        if (getAstnr() != null) {
            _hashCode += getAstnr().hashCode();
        }
        if (getB_art() != null) {
            _hashCode += getB_art().hashCode();
        }
        if (getB_firma1() != null) {
            _hashCode += getB_firma1().hashCode();
        }
        if (getB_firma2() != null) {
            _hashCode += getB_firma2().hashCode();
        }
        if (getB_gkz() != null) {
            _hashCode += getB_gkz().hashCode();
        }
        if (getB_lk() != null) {
            _hashCode += getB_lk().hashCode();
        }
        if (getB_nr() != null) {
            _hashCode += getB_nr().hashCode();
        }
        if (getB_ort() != null) {
            _hashCode += getB_ort().hashCode();
        }
        if (getB_ortsteil() != null) {
            _hashCode += getB_ortsteil().hashCode();
        }
        if (getB_plz() != null) {
            _hashCode += getB_plz().hashCode();
        }
        if (getB_plz_postfach() != null) {
            _hashCode += getB_plz_postfach().hashCode();
        }
        if (getB_postfach() != null) {
            _hashCode += getB_postfach().hashCode();
        }
        if (getB_strasse() != null) {
            _hashCode += getB_strasse().hashCode();
        }
        if (getB_tel_nr() != null) {
            _hashCode += getB_tel_nr().hashCode();
        }
        if (getB_tfx_nr() != null) {
            _hashCode += getB_tfx_nr().hashCode();
        }
        if (getDatum1() != null) {
            _hashCode += getDatum1().hashCode();
        }
        if (getDatum2() != null) {
            _hashCode += getDatum2().hashCode();
        }
        if (getGaa_nr() != null) {
            _hashCode += getGaa_nr().hashCode();
        }
        if (getGgr() != null) {
            _hashCode += getGgr().hashCode();
        }
        if (getTemp() != null) {
            _hashCode += getTemp().hashCode();
        }
        if (getWkl() != null) {
            _hashCode += getWkl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Arbeitsstaetten.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Arbeitsstaetten"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arbeitsstaette_seq_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arbeitsstaette_seq_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arbeitsstaette_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arbeitsstaette_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("astnr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "astnr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_art");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_art"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_firma1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_firma1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_firma2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_firma2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_gkz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_gkz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_lk");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_lk"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_ort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_ort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_ortsteil");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_ortsteil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_plz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_plz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_plz_postfach");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_plz_postfach"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_postfach");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_postfach"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_strasse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_strasse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_tel_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_tel_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("b_tfx_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "b_tfx_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datum1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datum1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datum2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datum2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gaa_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gaa_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ggr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ggr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "temp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wkl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "wkl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
