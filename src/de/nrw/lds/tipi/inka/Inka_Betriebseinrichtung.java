/**
 * Inka_Betriebseinrichtung.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Betriebseinrichtung  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer adresse_anspr_nr;

    private java.lang.Integer adresse_anspr_ver;

    private java.lang.Integer adresse_betreib_nr;

    private java.lang.Integer adresse_betreib_ver;

    private java.lang.Integer arbeitsstaette_seq_nr;

    private java.lang.Integer arbeitsstaette_ver;

    private java.lang.Integer betrieb_nr;

    private java.lang.Integer betrieb_ver;

    private java.lang.Integer betriebseinrichtung_nr;

    private java.lang.Integer betriebseinrichtung_ver;

    private java.lang.Integer genehmigung_nr;

    private java.lang.Integer genehmigung_ver;

    private java.util.Calendar stilllegung_datum;

    private java.lang.Boolean stilllegung_jn;

    private java.lang.String wz_code;

    private java.lang.Integer wz_code_ver;

    public Inka_Betriebseinrichtung() {
    }

    public Inka_Betriebseinrichtung(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer adresse_anspr_nr,
           java.lang.Integer adresse_anspr_ver,
           java.lang.Integer adresse_betreib_nr,
           java.lang.Integer adresse_betreib_ver,
           java.lang.Integer arbeitsstaette_seq_nr,
           java.lang.Integer arbeitsstaette_ver,
           java.lang.Integer betrieb_nr,
           java.lang.Integer betrieb_ver,
           java.lang.Integer betriebseinrichtung_nr,
           java.lang.Integer betriebseinrichtung_ver,
           java.lang.Integer genehmigung_nr,
           java.lang.Integer genehmigung_ver,
           java.util.Calendar stilllegung_datum,
           java.lang.Boolean stilllegung_jn,
           java.lang.String wz_code,
           java.lang.Integer wz_code_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.adresse_anspr_nr = adresse_anspr_nr;
        this.adresse_anspr_ver = adresse_anspr_ver;
        this.adresse_betreib_nr = adresse_betreib_nr;
        this.adresse_betreib_ver = adresse_betreib_ver;
        this.arbeitsstaette_seq_nr = arbeitsstaette_seq_nr;
        this.arbeitsstaette_ver = arbeitsstaette_ver;
        this.betrieb_nr = betrieb_nr;
        this.betrieb_ver = betrieb_ver;
        this.betriebseinrichtung_nr = betriebseinrichtung_nr;
        this.betriebseinrichtung_ver = betriebseinrichtung_ver;
        this.genehmigung_nr = genehmigung_nr;
        this.genehmigung_ver = genehmigung_ver;
        this.stilllegung_datum = stilllegung_datum;
        this.stilllegung_jn = stilllegung_jn;
        this.wz_code = wz_code;
        this.wz_code_ver = wz_code_ver;
    }


    /**
     * Gets the adresse_anspr_nr value for this Inka_Betriebseinrichtung.
     * 
     * @return adresse_anspr_nr
     */
    public java.lang.Integer getAdresse_anspr_nr() {
        return adresse_anspr_nr;
    }


    /**
     * Sets the adresse_anspr_nr value for this Inka_Betriebseinrichtung.
     * 
     * @param adresse_anspr_nr
     */
    public void setAdresse_anspr_nr(java.lang.Integer adresse_anspr_nr) {
        this.adresse_anspr_nr = adresse_anspr_nr;
    }


    /**
     * Gets the adresse_anspr_ver value for this Inka_Betriebseinrichtung.
     * 
     * @return adresse_anspr_ver
     */
    public java.lang.Integer getAdresse_anspr_ver() {
        return adresse_anspr_ver;
    }


    /**
     * Sets the adresse_anspr_ver value for this Inka_Betriebseinrichtung.
     * 
     * @param adresse_anspr_ver
     */
    public void setAdresse_anspr_ver(java.lang.Integer adresse_anspr_ver) {
        this.adresse_anspr_ver = adresse_anspr_ver;
    }


    /**
     * Gets the adresse_betreib_nr value for this Inka_Betriebseinrichtung.
     * 
     * @return adresse_betreib_nr
     */
    public java.lang.Integer getAdresse_betreib_nr() {
        return adresse_betreib_nr;
    }


    /**
     * Sets the adresse_betreib_nr value for this Inka_Betriebseinrichtung.
     * 
     * @param adresse_betreib_nr
     */
    public void setAdresse_betreib_nr(java.lang.Integer adresse_betreib_nr) {
        this.adresse_betreib_nr = adresse_betreib_nr;
    }


    /**
     * Gets the adresse_betreib_ver value for this Inka_Betriebseinrichtung.
     * 
     * @return adresse_betreib_ver
     */
    public java.lang.Integer getAdresse_betreib_ver() {
        return adresse_betreib_ver;
    }


    /**
     * Sets the adresse_betreib_ver value for this Inka_Betriebseinrichtung.
     * 
     * @param adresse_betreib_ver
     */
    public void setAdresse_betreib_ver(java.lang.Integer adresse_betreib_ver) {
        this.adresse_betreib_ver = adresse_betreib_ver;
    }


    /**
     * Gets the arbeitsstaette_seq_nr value for this Inka_Betriebseinrichtung.
     * 
     * @return arbeitsstaette_seq_nr
     */
    public java.lang.Integer getArbeitsstaette_seq_nr() {
        return arbeitsstaette_seq_nr;
    }


    /**
     * Sets the arbeitsstaette_seq_nr value for this Inka_Betriebseinrichtung.
     * 
     * @param arbeitsstaette_seq_nr
     */
    public void setArbeitsstaette_seq_nr(java.lang.Integer arbeitsstaette_seq_nr) {
        this.arbeitsstaette_seq_nr = arbeitsstaette_seq_nr;
    }


    /**
     * Gets the arbeitsstaette_ver value for this Inka_Betriebseinrichtung.
     * 
     * @return arbeitsstaette_ver
     */
    public java.lang.Integer getArbeitsstaette_ver() {
        return arbeitsstaette_ver;
    }


    /**
     * Sets the arbeitsstaette_ver value for this Inka_Betriebseinrichtung.
     * 
     * @param arbeitsstaette_ver
     */
    public void setArbeitsstaette_ver(java.lang.Integer arbeitsstaette_ver) {
        this.arbeitsstaette_ver = arbeitsstaette_ver;
    }


    /**
     * Gets the betrieb_nr value for this Inka_Betriebseinrichtung.
     * 
     * @return betrieb_nr
     */
    public java.lang.Integer getBetrieb_nr() {
        return betrieb_nr;
    }


    /**
     * Sets the betrieb_nr value for this Inka_Betriebseinrichtung.
     * 
     * @param betrieb_nr
     */
    public void setBetrieb_nr(java.lang.Integer betrieb_nr) {
        this.betrieb_nr = betrieb_nr;
    }


    /**
     * Gets the betrieb_ver value for this Inka_Betriebseinrichtung.
     * 
     * @return betrieb_ver
     */
    public java.lang.Integer getBetrieb_ver() {
        return betrieb_ver;
    }


    /**
     * Sets the betrieb_ver value for this Inka_Betriebseinrichtung.
     * 
     * @param betrieb_ver
     */
    public void setBetrieb_ver(java.lang.Integer betrieb_ver) {
        this.betrieb_ver = betrieb_ver;
    }


    /**
     * Gets the betriebseinrichtung_nr value for this Inka_Betriebseinrichtung.
     * 
     * @return betriebseinrichtung_nr
     */
    public java.lang.Integer getBetriebseinrichtung_nr() {
        return betriebseinrichtung_nr;
    }


    /**
     * Sets the betriebseinrichtung_nr value for this Inka_Betriebseinrichtung.
     * 
     * @param betriebseinrichtung_nr
     */
    public void setBetriebseinrichtung_nr(java.lang.Integer betriebseinrichtung_nr) {
        this.betriebseinrichtung_nr = betriebseinrichtung_nr;
    }


    /**
     * Gets the betriebseinrichtung_ver value for this Inka_Betriebseinrichtung.
     * 
     * @return betriebseinrichtung_ver
     */
    public java.lang.Integer getBetriebseinrichtung_ver() {
        return betriebseinrichtung_ver;
    }


    /**
     * Sets the betriebseinrichtung_ver value for this Inka_Betriebseinrichtung.
     * 
     * @param betriebseinrichtung_ver
     */
    public void setBetriebseinrichtung_ver(java.lang.Integer betriebseinrichtung_ver) {
        this.betriebseinrichtung_ver = betriebseinrichtung_ver;
    }


    /**
     * Gets the genehmigung_nr value for this Inka_Betriebseinrichtung.
     * 
     * @return genehmigung_nr
     */
    public java.lang.Integer getGenehmigung_nr() {
        return genehmigung_nr;
    }


    /**
     * Sets the genehmigung_nr value for this Inka_Betriebseinrichtung.
     * 
     * @param genehmigung_nr
     */
    public void setGenehmigung_nr(java.lang.Integer genehmigung_nr) {
        this.genehmigung_nr = genehmigung_nr;
    }


    /**
     * Gets the genehmigung_ver value for this Inka_Betriebseinrichtung.
     * 
     * @return genehmigung_ver
     */
    public java.lang.Integer getGenehmigung_ver() {
        return genehmigung_ver;
    }


    /**
     * Sets the genehmigung_ver value for this Inka_Betriebseinrichtung.
     * 
     * @param genehmigung_ver
     */
    public void setGenehmigung_ver(java.lang.Integer genehmigung_ver) {
        this.genehmigung_ver = genehmigung_ver;
    }


    /**
     * Gets the stilllegung_datum value for this Inka_Betriebseinrichtung.
     * 
     * @return stilllegung_datum
     */
    public java.util.Calendar getStilllegung_datum() {
        return stilllegung_datum;
    }


    /**
     * Sets the stilllegung_datum value for this Inka_Betriebseinrichtung.
     * 
     * @param stilllegung_datum
     */
    public void setStilllegung_datum(java.util.Calendar stilllegung_datum) {
        this.stilllegung_datum = stilllegung_datum;
    }


    /**
     * Gets the stilllegung_jn value for this Inka_Betriebseinrichtung.
     * 
     * @return stilllegung_jn
     */
    public java.lang.Boolean getStilllegung_jn() {
        return stilllegung_jn;
    }


    /**
     * Sets the stilllegung_jn value for this Inka_Betriebseinrichtung.
     * 
     * @param stilllegung_jn
     */
    public void setStilllegung_jn(java.lang.Boolean stilllegung_jn) {
        this.stilllegung_jn = stilllegung_jn;
    }


    /**
     * Gets the wz_code value for this Inka_Betriebseinrichtung.
     * 
     * @return wz_code
     */
    public java.lang.String getWz_code() {
        return wz_code;
    }


    /**
     * Sets the wz_code value for this Inka_Betriebseinrichtung.
     * 
     * @param wz_code
     */
    public void setWz_code(java.lang.String wz_code) {
        this.wz_code = wz_code;
    }


    /**
     * Gets the wz_code_ver value for this Inka_Betriebseinrichtung.
     * 
     * @return wz_code_ver
     */
    public java.lang.Integer getWz_code_ver() {
        return wz_code_ver;
    }


    /**
     * Sets the wz_code_ver value for this Inka_Betriebseinrichtung.
     * 
     * @param wz_code_ver
     */
    public void setWz_code_ver(java.lang.Integer wz_code_ver) {
        this.wz_code_ver = wz_code_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Betriebseinrichtung)) return false;
        Inka_Betriebseinrichtung other = (Inka_Betriebseinrichtung) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.adresse_anspr_nr==null && other.getAdresse_anspr_nr()==null) || 
             (this.adresse_anspr_nr!=null &&
              this.adresse_anspr_nr.equals(other.getAdresse_anspr_nr()))) &&
            ((this.adresse_anspr_ver==null && other.getAdresse_anspr_ver()==null) || 
             (this.adresse_anspr_ver!=null &&
              this.adresse_anspr_ver.equals(other.getAdresse_anspr_ver()))) &&
            ((this.adresse_betreib_nr==null && other.getAdresse_betreib_nr()==null) || 
             (this.adresse_betreib_nr!=null &&
              this.adresse_betreib_nr.equals(other.getAdresse_betreib_nr()))) &&
            ((this.adresse_betreib_ver==null && other.getAdresse_betreib_ver()==null) || 
             (this.adresse_betreib_ver!=null &&
              this.adresse_betreib_ver.equals(other.getAdresse_betreib_ver()))) &&
            ((this.arbeitsstaette_seq_nr==null && other.getArbeitsstaette_seq_nr()==null) || 
             (this.arbeitsstaette_seq_nr!=null &&
              this.arbeitsstaette_seq_nr.equals(other.getArbeitsstaette_seq_nr()))) &&
            ((this.arbeitsstaette_ver==null && other.getArbeitsstaette_ver()==null) || 
             (this.arbeitsstaette_ver!=null &&
              this.arbeitsstaette_ver.equals(other.getArbeitsstaette_ver()))) &&
            ((this.betrieb_nr==null && other.getBetrieb_nr()==null) || 
             (this.betrieb_nr!=null &&
              this.betrieb_nr.equals(other.getBetrieb_nr()))) &&
            ((this.betrieb_ver==null && other.getBetrieb_ver()==null) || 
             (this.betrieb_ver!=null &&
              this.betrieb_ver.equals(other.getBetrieb_ver()))) &&
            ((this.betriebseinrichtung_nr==null && other.getBetriebseinrichtung_nr()==null) || 
             (this.betriebseinrichtung_nr!=null &&
              this.betriebseinrichtung_nr.equals(other.getBetriebseinrichtung_nr()))) &&
            ((this.betriebseinrichtung_ver==null && other.getBetriebseinrichtung_ver()==null) || 
             (this.betriebseinrichtung_ver!=null &&
              this.betriebseinrichtung_ver.equals(other.getBetriebseinrichtung_ver()))) &&
            ((this.genehmigung_nr==null && other.getGenehmigung_nr()==null) || 
             (this.genehmigung_nr!=null &&
              this.genehmigung_nr.equals(other.getGenehmigung_nr()))) &&
            ((this.genehmigung_ver==null && other.getGenehmigung_ver()==null) || 
             (this.genehmigung_ver!=null &&
              this.genehmigung_ver.equals(other.getGenehmigung_ver()))) &&
            ((this.stilllegung_datum==null && other.getStilllegung_datum()==null) || 
             (this.stilllegung_datum!=null &&
              this.stilllegung_datum.equals(other.getStilllegung_datum()))) &&
            ((this.stilllegung_jn==null && other.getStilllegung_jn()==null) || 
             (this.stilllegung_jn!=null &&
              this.stilllegung_jn.equals(other.getStilllegung_jn()))) &&
            ((this.wz_code==null && other.getWz_code()==null) || 
             (this.wz_code!=null &&
              this.wz_code.equals(other.getWz_code()))) &&
            ((this.wz_code_ver==null && other.getWz_code_ver()==null) || 
             (this.wz_code_ver!=null &&
              this.wz_code_ver.equals(other.getWz_code_ver())));
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
        if (getAdresse_anspr_nr() != null) {
            _hashCode += getAdresse_anspr_nr().hashCode();
        }
        if (getAdresse_anspr_ver() != null) {
            _hashCode += getAdresse_anspr_ver().hashCode();
        }
        if (getAdresse_betreib_nr() != null) {
            _hashCode += getAdresse_betreib_nr().hashCode();
        }
        if (getAdresse_betreib_ver() != null) {
            _hashCode += getAdresse_betreib_ver().hashCode();
        }
        if (getArbeitsstaette_seq_nr() != null) {
            _hashCode += getArbeitsstaette_seq_nr().hashCode();
        }
        if (getArbeitsstaette_ver() != null) {
            _hashCode += getArbeitsstaette_ver().hashCode();
        }
        if (getBetrieb_nr() != null) {
            _hashCode += getBetrieb_nr().hashCode();
        }
        if (getBetrieb_ver() != null) {
            _hashCode += getBetrieb_ver().hashCode();
        }
        if (getBetriebseinrichtung_nr() != null) {
            _hashCode += getBetriebseinrichtung_nr().hashCode();
        }
        if (getBetriebseinrichtung_ver() != null) {
            _hashCode += getBetriebseinrichtung_ver().hashCode();
        }
        if (getGenehmigung_nr() != null) {
            _hashCode += getGenehmigung_nr().hashCode();
        }
        if (getGenehmigung_ver() != null) {
            _hashCode += getGenehmigung_ver().hashCode();
        }
        if (getStilllegung_datum() != null) {
            _hashCode += getStilllegung_datum().hashCode();
        }
        if (getStilllegung_jn() != null) {
            _hashCode += getStilllegung_jn().hashCode();
        }
        if (getWz_code() != null) {
            _hashCode += getWz_code().hashCode();
        }
        if (getWz_code_ver() != null) {
            _hashCode += getWz_code_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Betriebseinrichtung.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Betriebseinrichtung"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("adresse_betreib_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_betreib_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresse_betreib_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_betreib_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("betrieb_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "betrieb_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("betrieb_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "betrieb_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("stilllegung_datum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stilllegung_datum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stilllegung_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stilllegung_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wz_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "wz_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wz_code_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "wz_code_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
