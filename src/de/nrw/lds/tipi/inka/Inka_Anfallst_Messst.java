/**
 * Inka_Anfallst_Messst.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Anfallst_Messst  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer anfallst_messst_ver;

    private java.lang.Integer anfallstelle_nr;

    private java.lang.Integer anfallstelle_ver;

    private java.lang.Integer gemeinde_ver;

    private java.lang.String gemeindekennzahl;

    private java.lang.Integer messstelle_lfd_nr;

    private java.lang.Integer messstelle_ver;

    private java.lang.Integer uebergabestelle_lfd_nr;

    private java.lang.Integer uebergabestelle_ver;

    public Inka_Anfallst_Messst() {
    }

    public Inka_Anfallst_Messst(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer anfallst_messst_ver,
           java.lang.Integer anfallstelle_nr,
           java.lang.Integer anfallstelle_ver,
           java.lang.Integer gemeinde_ver,
           java.lang.String gemeindekennzahl,
           java.lang.Integer messstelle_lfd_nr,
           java.lang.Integer messstelle_ver,
           java.lang.Integer uebergabestelle_lfd_nr,
           java.lang.Integer uebergabestelle_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.anfallst_messst_ver = anfallst_messst_ver;
        this.anfallstelle_nr = anfallstelle_nr;
        this.anfallstelle_ver = anfallstelle_ver;
        this.gemeinde_ver = gemeinde_ver;
        this.gemeindekennzahl = gemeindekennzahl;
        this.messstelle_lfd_nr = messstelle_lfd_nr;
        this.messstelle_ver = messstelle_ver;
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
        this.uebergabestelle_ver = uebergabestelle_ver;
    }


    /**
     * Gets the anfallst_messst_ver value for this Inka_Anfallst_Messst.
     * 
     * @return anfallst_messst_ver
     */
    public java.lang.Integer getAnfallst_messst_ver() {
        return anfallst_messst_ver;
    }


    /**
     * Sets the anfallst_messst_ver value for this Inka_Anfallst_Messst.
     * 
     * @param anfallst_messst_ver
     */
    public void setAnfallst_messst_ver(java.lang.Integer anfallst_messst_ver) {
        this.anfallst_messst_ver = anfallst_messst_ver;
    }


    /**
     * Gets the anfallstelle_nr value for this Inka_Anfallst_Messst.
     * 
     * @return anfallstelle_nr
     */
    public java.lang.Integer getAnfallstelle_nr() {
        return anfallstelle_nr;
    }


    /**
     * Sets the anfallstelle_nr value for this Inka_Anfallst_Messst.
     * 
     * @param anfallstelle_nr
     */
    public void setAnfallstelle_nr(java.lang.Integer anfallstelle_nr) {
        this.anfallstelle_nr = anfallstelle_nr;
    }


    /**
     * Gets the anfallstelle_ver value for this Inka_Anfallst_Messst.
     * 
     * @return anfallstelle_ver
     */
    public java.lang.Integer getAnfallstelle_ver() {
        return anfallstelle_ver;
    }


    /**
     * Sets the anfallstelle_ver value for this Inka_Anfallst_Messst.
     * 
     * @param anfallstelle_ver
     */
    public void setAnfallstelle_ver(java.lang.Integer anfallstelle_ver) {
        this.anfallstelle_ver = anfallstelle_ver;
    }


    /**
     * Gets the gemeinde_ver value for this Inka_Anfallst_Messst.
     * 
     * @return gemeinde_ver
     */
    public java.lang.Integer getGemeinde_ver() {
        return gemeinde_ver;
    }


    /**
     * Sets the gemeinde_ver value for this Inka_Anfallst_Messst.
     * 
     * @param gemeinde_ver
     */
    public void setGemeinde_ver(java.lang.Integer gemeinde_ver) {
        this.gemeinde_ver = gemeinde_ver;
    }


    /**
     * Gets the gemeindekennzahl value for this Inka_Anfallst_Messst.
     * 
     * @return gemeindekennzahl
     */
    public java.lang.String getGemeindekennzahl() {
        return gemeindekennzahl;
    }


    /**
     * Sets the gemeindekennzahl value for this Inka_Anfallst_Messst.
     * 
     * @param gemeindekennzahl
     */
    public void setGemeindekennzahl(java.lang.String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }


    /**
     * Gets the messstelle_lfd_nr value for this Inka_Anfallst_Messst.
     * 
     * @return messstelle_lfd_nr
     */
    public java.lang.Integer getMessstelle_lfd_nr() {
        return messstelle_lfd_nr;
    }


    /**
     * Sets the messstelle_lfd_nr value for this Inka_Anfallst_Messst.
     * 
     * @param messstelle_lfd_nr
     */
    public void setMessstelle_lfd_nr(java.lang.Integer messstelle_lfd_nr) {
        this.messstelle_lfd_nr = messstelle_lfd_nr;
    }


    /**
     * Gets the messstelle_ver value for this Inka_Anfallst_Messst.
     * 
     * @return messstelle_ver
     */
    public java.lang.Integer getMessstelle_ver() {
        return messstelle_ver;
    }


    /**
     * Sets the messstelle_ver value for this Inka_Anfallst_Messst.
     * 
     * @param messstelle_ver
     */
    public void setMessstelle_ver(java.lang.Integer messstelle_ver) {
        this.messstelle_ver = messstelle_ver;
    }


    /**
     * Gets the uebergabestelle_lfd_nr value for this Inka_Anfallst_Messst.
     * 
     * @return uebergabestelle_lfd_nr
     */
    public java.lang.Integer getUebergabestelle_lfd_nr() {
        return uebergabestelle_lfd_nr;
    }


    /**
     * Sets the uebergabestelle_lfd_nr value for this Inka_Anfallst_Messst.
     * 
     * @param uebergabestelle_lfd_nr
     */
    public void setUebergabestelle_lfd_nr(java.lang.Integer uebergabestelle_lfd_nr) {
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
    }


    /**
     * Gets the uebergabestelle_ver value for this Inka_Anfallst_Messst.
     * 
     * @return uebergabestelle_ver
     */
    public java.lang.Integer getUebergabestelle_ver() {
        return uebergabestelle_ver;
    }


    /**
     * Sets the uebergabestelle_ver value for this Inka_Anfallst_Messst.
     * 
     * @param uebergabestelle_ver
     */
    public void setUebergabestelle_ver(java.lang.Integer uebergabestelle_ver) {
        this.uebergabestelle_ver = uebergabestelle_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Anfallst_Messst)) return false;
        Inka_Anfallst_Messst other = (Inka_Anfallst_Messst) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.anfallst_messst_ver==null && other.getAnfallst_messst_ver()==null) || 
             (this.anfallst_messst_ver!=null &&
              this.anfallst_messst_ver.equals(other.getAnfallst_messst_ver()))) &&
            ((this.anfallstelle_nr==null && other.getAnfallstelle_nr()==null) || 
             (this.anfallstelle_nr!=null &&
              this.anfallstelle_nr.equals(other.getAnfallstelle_nr()))) &&
            ((this.anfallstelle_ver==null && other.getAnfallstelle_ver()==null) || 
             (this.anfallstelle_ver!=null &&
              this.anfallstelle_ver.equals(other.getAnfallstelle_ver()))) &&
            ((this.gemeinde_ver==null && other.getGemeinde_ver()==null) || 
             (this.gemeinde_ver!=null &&
              this.gemeinde_ver.equals(other.getGemeinde_ver()))) &&
            ((this.gemeindekennzahl==null && other.getGemeindekennzahl()==null) || 
             (this.gemeindekennzahl!=null &&
              this.gemeindekennzahl.equals(other.getGemeindekennzahl()))) &&
            ((this.messstelle_lfd_nr==null && other.getMessstelle_lfd_nr()==null) || 
             (this.messstelle_lfd_nr!=null &&
              this.messstelle_lfd_nr.equals(other.getMessstelle_lfd_nr()))) &&
            ((this.messstelle_ver==null && other.getMessstelle_ver()==null) || 
             (this.messstelle_ver!=null &&
              this.messstelle_ver.equals(other.getMessstelle_ver()))) &&
            ((this.uebergabestelle_lfd_nr==null && other.getUebergabestelle_lfd_nr()==null) || 
             (this.uebergabestelle_lfd_nr!=null &&
              this.uebergabestelle_lfd_nr.equals(other.getUebergabestelle_lfd_nr()))) &&
            ((this.uebergabestelle_ver==null && other.getUebergabestelle_ver()==null) || 
             (this.uebergabestelle_ver!=null &&
              this.uebergabestelle_ver.equals(other.getUebergabestelle_ver())));
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
        if (getAnfallst_messst_ver() != null) {
            _hashCode += getAnfallst_messst_ver().hashCode();
        }
        if (getAnfallstelle_nr() != null) {
            _hashCode += getAnfallstelle_nr().hashCode();
        }
        if (getAnfallstelle_ver() != null) {
            _hashCode += getAnfallstelle_ver().hashCode();
        }
        if (getGemeinde_ver() != null) {
            _hashCode += getGemeinde_ver().hashCode();
        }
        if (getGemeindekennzahl() != null) {
            _hashCode += getGemeindekennzahl().hashCode();
        }
        if (getMessstelle_lfd_nr() != null) {
            _hashCode += getMessstelle_lfd_nr().hashCode();
        }
        if (getMessstelle_ver() != null) {
            _hashCode += getMessstelle_ver().hashCode();
        }
        if (getUebergabestelle_lfd_nr() != null) {
            _hashCode += getUebergabestelle_lfd_nr().hashCode();
        }
        if (getUebergabestelle_ver() != null) {
            _hashCode += getUebergabestelle_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Anfallst_Messst.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Messst"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anfallst_messst_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anfallst_messst_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anfallstelle_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anfallstelle_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anfallstelle_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anfallstelle_ver"));
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
