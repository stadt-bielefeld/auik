/**
 * Dea_Einheiten.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Einheiten  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String beschreibung;

    private java.lang.String einheiten_zeichen;

    private java.lang.Integer masseinheiten_nr;

    private java.lang.Integer masseinheiten_ver;

    private java.lang.Integer nr_bezugsmasseinheit;

    private java.lang.Float skalenfaktor;

    public Dea_Einheiten() {
    }

    public Dea_Einheiten(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String beschreibung,
           java.lang.String einheiten_zeichen,
           java.lang.Integer masseinheiten_nr,
           java.lang.Integer masseinheiten_ver,
           java.lang.Integer nr_bezugsmasseinheit,
           java.lang.Float skalenfaktor) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.beschreibung = beschreibung;
        this.einheiten_zeichen = einheiten_zeichen;
        this.masseinheiten_nr = masseinheiten_nr;
        this.masseinheiten_ver = masseinheiten_ver;
        this.nr_bezugsmasseinheit = nr_bezugsmasseinheit;
        this.skalenfaktor = skalenfaktor;
    }


    /**
     * Gets the beschreibung value for this Dea_Einheiten.
     * 
     * @return beschreibung
     */
    public java.lang.String getBeschreibung() {
        return beschreibung;
    }


    /**
     * Sets the beschreibung value for this Dea_Einheiten.
     * 
     * @param beschreibung
     */
    public void setBeschreibung(java.lang.String beschreibung) {
        this.beschreibung = beschreibung;
    }


    /**
     * Gets the einheiten_zeichen value for this Dea_Einheiten.
     * 
     * @return einheiten_zeichen
     */
    public java.lang.String getEinheiten_zeichen() {
        return einheiten_zeichen;
    }


    /**
     * Sets the einheiten_zeichen value for this Dea_Einheiten.
     * 
     * @param einheiten_zeichen
     */
    public void setEinheiten_zeichen(java.lang.String einheiten_zeichen) {
        this.einheiten_zeichen = einheiten_zeichen;
    }


    /**
     * Gets the masseinheiten_nr value for this Dea_Einheiten.
     * 
     * @return masseinheiten_nr
     */
    public java.lang.Integer getMasseinheiten_nr() {
        return masseinheiten_nr;
    }


    /**
     * Sets the masseinheiten_nr value for this Dea_Einheiten.
     * 
     * @param masseinheiten_nr
     */
    public void setMasseinheiten_nr(java.lang.Integer masseinheiten_nr) {
        this.masseinheiten_nr = masseinheiten_nr;
    }


    /**
     * Gets the masseinheiten_ver value for this Dea_Einheiten.
     * 
     * @return masseinheiten_ver
     */
    public java.lang.Integer getMasseinheiten_ver() {
        return masseinheiten_ver;
    }


    /**
     * Sets the masseinheiten_ver value for this Dea_Einheiten.
     * 
     * @param masseinheiten_ver
     */
    public void setMasseinheiten_ver(java.lang.Integer masseinheiten_ver) {
        this.masseinheiten_ver = masseinheiten_ver;
    }


    /**
     * Gets the nr_bezugsmasseinheit value for this Dea_Einheiten.
     * 
     * @return nr_bezugsmasseinheit
     */
    public java.lang.Integer getNr_bezugsmasseinheit() {
        return nr_bezugsmasseinheit;
    }


    /**
     * Sets the nr_bezugsmasseinheit value for this Dea_Einheiten.
     * 
     * @param nr_bezugsmasseinheit
     */
    public void setNr_bezugsmasseinheit(java.lang.Integer nr_bezugsmasseinheit) {
        this.nr_bezugsmasseinheit = nr_bezugsmasseinheit;
    }


    /**
     * Gets the skalenfaktor value for this Dea_Einheiten.
     * 
     * @return skalenfaktor
     */
    public java.lang.Float getSkalenfaktor() {
        return skalenfaktor;
    }


    /**
     * Sets the skalenfaktor value for this Dea_Einheiten.
     * 
     * @param skalenfaktor
     */
    public void setSkalenfaktor(java.lang.Float skalenfaktor) {
        this.skalenfaktor = skalenfaktor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Einheiten)) return false;
        Dea_Einheiten other = (Dea_Einheiten) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.beschreibung==null && other.getBeschreibung()==null) || 
             (this.beschreibung!=null &&
              this.beschreibung.equals(other.getBeschreibung()))) &&
            ((this.einheiten_zeichen==null && other.getEinheiten_zeichen()==null) || 
             (this.einheiten_zeichen!=null &&
              this.einheiten_zeichen.equals(other.getEinheiten_zeichen()))) &&
            ((this.masseinheiten_nr==null && other.getMasseinheiten_nr()==null) || 
             (this.masseinheiten_nr!=null &&
              this.masseinheiten_nr.equals(other.getMasseinheiten_nr()))) &&
            ((this.masseinheiten_ver==null && other.getMasseinheiten_ver()==null) || 
             (this.masseinheiten_ver!=null &&
              this.masseinheiten_ver.equals(other.getMasseinheiten_ver()))) &&
            ((this.nr_bezugsmasseinheit==null && other.getNr_bezugsmasseinheit()==null) || 
             (this.nr_bezugsmasseinheit!=null &&
              this.nr_bezugsmasseinheit.equals(other.getNr_bezugsmasseinheit()))) &&
            ((this.skalenfaktor==null && other.getSkalenfaktor()==null) || 
             (this.skalenfaktor!=null &&
              this.skalenfaktor.equals(other.getSkalenfaktor())));
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
        if (getBeschreibung() != null) {
            _hashCode += getBeschreibung().hashCode();
        }
        if (getEinheiten_zeichen() != null) {
            _hashCode += getEinheiten_zeichen().hashCode();
        }
        if (getMasseinheiten_nr() != null) {
            _hashCode += getMasseinheiten_nr().hashCode();
        }
        if (getMasseinheiten_ver() != null) {
            _hashCode += getMasseinheiten_ver().hashCode();
        }
        if (getNr_bezugsmasseinheit() != null) {
            _hashCode += getNr_bezugsmasseinheit().hashCode();
        }
        if (getSkalenfaktor() != null) {
            _hashCode += getSkalenfaktor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Einheiten.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Einheiten"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beschreibung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beschreibung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("einheiten_zeichen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "einheiten_zeichen"));
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
        elemField.setFieldName("masseinheiten_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "masseinheiten_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nr_bezugsmasseinheit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nr_bezugsmasseinheit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("skalenfaktor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "skalenfaktor"));
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
