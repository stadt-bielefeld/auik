/**
 * Inka_Betrieb.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Betrieb  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer adresse_anspr_nr;

    private java.lang.Integer adresse_anspr_ver;

    private java.lang.Integer adresse_einleit_nr;

    private java.lang.Integer adresse_einleit_ver;

    private java.lang.Integer adresse_stand_nr;

    private java.lang.Integer adresse_stand_ver;

    private java.lang.Integer betrieb_nr;

    private java.lang.Integer betrieb_ver;

    private java.lang.Integer gemeinde_ver;

    private java.lang.String gemeindekennzahl;

    public Inka_Betrieb() {
    }

    public Inka_Betrieb(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer adresse_anspr_nr,
           java.lang.Integer adresse_anspr_ver,
           java.lang.Integer adresse_einleit_nr,
           java.lang.Integer adresse_einleit_ver,
           java.lang.Integer adresse_stand_nr,
           java.lang.Integer adresse_stand_ver,
           java.lang.Integer betrieb_nr,
           java.lang.Integer betrieb_ver,
           java.lang.Integer gemeinde_ver,
           java.lang.String gemeindekennzahl) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.adresse_anspr_nr = adresse_anspr_nr;
        this.adresse_anspr_ver = adresse_anspr_ver;
        this.adresse_einleit_nr = adresse_einleit_nr;
        this.adresse_einleit_ver = adresse_einleit_ver;
        this.adresse_stand_nr = adresse_stand_nr;
        this.adresse_stand_ver = adresse_stand_ver;
        this.betrieb_nr = betrieb_nr;
        this.betrieb_ver = betrieb_ver;
        this.gemeinde_ver = gemeinde_ver;
        this.gemeindekennzahl = gemeindekennzahl;
    }


    /**
     * Gets the adresse_anspr_nr value for this Inka_Betrieb.
     * 
     * @return adresse_anspr_nr
     */
    public java.lang.Integer getAdresse_anspr_nr() {
        return adresse_anspr_nr;
    }


    /**
     * Sets the adresse_anspr_nr value for this Inka_Betrieb.
     * 
     * @param adresse_anspr_nr
     */
    public void setAdresse_anspr_nr(java.lang.Integer adresse_anspr_nr) {
        this.adresse_anspr_nr = adresse_anspr_nr;
    }


    /**
     * Gets the adresse_anspr_ver value for this Inka_Betrieb.
     * 
     * @return adresse_anspr_ver
     */
    public java.lang.Integer getAdresse_anspr_ver() {
        return adresse_anspr_ver;
    }


    /**
     * Sets the adresse_anspr_ver value for this Inka_Betrieb.
     * 
     * @param adresse_anspr_ver
     */
    public void setAdresse_anspr_ver(java.lang.Integer adresse_anspr_ver) {
        this.adresse_anspr_ver = adresse_anspr_ver;
    }


    /**
     * Gets the adresse_einleit_nr value for this Inka_Betrieb.
     * 
     * @return adresse_einleit_nr
     */
    public java.lang.Integer getAdresse_einleit_nr() {
        return adresse_einleit_nr;
    }


    /**
     * Sets the adresse_einleit_nr value for this Inka_Betrieb.
     * 
     * @param adresse_einleit_nr
     */
    public void setAdresse_einleit_nr(java.lang.Integer adresse_einleit_nr) {
        this.adresse_einleit_nr = adresse_einleit_nr;
    }


    /**
     * Gets the adresse_einleit_ver value for this Inka_Betrieb.
     * 
     * @return adresse_einleit_ver
     */
    public java.lang.Integer getAdresse_einleit_ver() {
        return adresse_einleit_ver;
    }


    /**
     * Sets the adresse_einleit_ver value for this Inka_Betrieb.
     * 
     * @param adresse_einleit_ver
     */
    public void setAdresse_einleit_ver(java.lang.Integer adresse_einleit_ver) {
        this.adresse_einleit_ver = adresse_einleit_ver;
    }


    /**
     * Gets the adresse_stand_nr value for this Inka_Betrieb.
     * 
     * @return adresse_stand_nr
     */
    public java.lang.Integer getAdresse_stand_nr() {
        return adresse_stand_nr;
    }


    /**
     * Sets the adresse_stand_nr value for this Inka_Betrieb.
     * 
     * @param adresse_stand_nr
     */
    public void setAdresse_stand_nr(java.lang.Integer adresse_stand_nr) {
        this.adresse_stand_nr = adresse_stand_nr;
    }


    /**
     * Gets the adresse_stand_ver value for this Inka_Betrieb.
     * 
     * @return adresse_stand_ver
     */
    public java.lang.Integer getAdresse_stand_ver() {
        return adresse_stand_ver;
    }


    /**
     * Sets the adresse_stand_ver value for this Inka_Betrieb.
     * 
     * @param adresse_stand_ver
     */
    public void setAdresse_stand_ver(java.lang.Integer adresse_stand_ver) {
        this.adresse_stand_ver = adresse_stand_ver;
    }


    /**
     * Gets the betrieb_nr value for this Inka_Betrieb.
     * 
     * @return betrieb_nr
     */
    public java.lang.Integer getBetrieb_nr() {
        return betrieb_nr;
    }


    /**
     * Sets the betrieb_nr value for this Inka_Betrieb.
     * 
     * @param betrieb_nr
     */
    public void setBetrieb_nr(java.lang.Integer betrieb_nr) {
        this.betrieb_nr = betrieb_nr;
    }


    /**
     * Gets the betrieb_ver value for this Inka_Betrieb.
     * 
     * @return betrieb_ver
     */
    public java.lang.Integer getBetrieb_ver() {
        return betrieb_ver;
    }


    /**
     * Sets the betrieb_ver value for this Inka_Betrieb.
     * 
     * @param betrieb_ver
     */
    public void setBetrieb_ver(java.lang.Integer betrieb_ver) {
        this.betrieb_ver = betrieb_ver;
    }


    /**
     * Gets the gemeinde_ver value for this Inka_Betrieb.
     * 
     * @return gemeinde_ver
     */
    public java.lang.Integer getGemeinde_ver() {
        return gemeinde_ver;
    }


    /**
     * Sets the gemeinde_ver value for this Inka_Betrieb.
     * 
     * @param gemeinde_ver
     */
    public void setGemeinde_ver(java.lang.Integer gemeinde_ver) {
        this.gemeinde_ver = gemeinde_ver;
    }


    /**
     * Gets the gemeindekennzahl value for this Inka_Betrieb.
     * 
     * @return gemeindekennzahl
     */
    public java.lang.String getGemeindekennzahl() {
        return gemeindekennzahl;
    }


    /**
     * Sets the gemeindekennzahl value for this Inka_Betrieb.
     * 
     * @param gemeindekennzahl
     */
    public void setGemeindekennzahl(java.lang.String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Betrieb)) return false;
        Inka_Betrieb other = (Inka_Betrieb) obj;
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
            ((this.adresse_einleit_nr==null && other.getAdresse_einleit_nr()==null) || 
             (this.adresse_einleit_nr!=null &&
              this.adresse_einleit_nr.equals(other.getAdresse_einleit_nr()))) &&
            ((this.adresse_einleit_ver==null && other.getAdresse_einleit_ver()==null) || 
             (this.adresse_einleit_ver!=null &&
              this.adresse_einleit_ver.equals(other.getAdresse_einleit_ver()))) &&
            ((this.adresse_stand_nr==null && other.getAdresse_stand_nr()==null) || 
             (this.adresse_stand_nr!=null &&
              this.adresse_stand_nr.equals(other.getAdresse_stand_nr()))) &&
            ((this.adresse_stand_ver==null && other.getAdresse_stand_ver()==null) || 
             (this.adresse_stand_ver!=null &&
              this.adresse_stand_ver.equals(other.getAdresse_stand_ver()))) &&
            ((this.betrieb_nr==null && other.getBetrieb_nr()==null) || 
             (this.betrieb_nr!=null &&
              this.betrieb_nr.equals(other.getBetrieb_nr()))) &&
            ((this.betrieb_ver==null && other.getBetrieb_ver()==null) || 
             (this.betrieb_ver!=null &&
              this.betrieb_ver.equals(other.getBetrieb_ver()))) &&
            ((this.gemeinde_ver==null && other.getGemeinde_ver()==null) || 
             (this.gemeinde_ver!=null &&
              this.gemeinde_ver.equals(other.getGemeinde_ver()))) &&
            ((this.gemeindekennzahl==null && other.getGemeindekennzahl()==null) || 
             (this.gemeindekennzahl!=null &&
              this.gemeindekennzahl.equals(other.getGemeindekennzahl())));
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
        if (getAdresse_einleit_nr() != null) {
            _hashCode += getAdresse_einleit_nr().hashCode();
        }
        if (getAdresse_einleit_ver() != null) {
            _hashCode += getAdresse_einleit_ver().hashCode();
        }
        if (getAdresse_stand_nr() != null) {
            _hashCode += getAdresse_stand_nr().hashCode();
        }
        if (getAdresse_stand_ver() != null) {
            _hashCode += getAdresse_stand_ver().hashCode();
        }
        if (getBetrieb_nr() != null) {
            _hashCode += getBetrieb_nr().hashCode();
        }
        if (getBetrieb_ver() != null) {
            _hashCode += getBetrieb_ver().hashCode();
        }
        if (getGemeinde_ver() != null) {
            _hashCode += getGemeinde_ver().hashCode();
        }
        if (getGemeindekennzahl() != null) {
            _hashCode += getGemeindekennzahl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Betrieb.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Betrieb"));
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
        elemField.setFieldName("adresse_einleit_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_einleit_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresse_einleit_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_einleit_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresse_stand_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_stand_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresse_stand_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_stand_ver"));
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
