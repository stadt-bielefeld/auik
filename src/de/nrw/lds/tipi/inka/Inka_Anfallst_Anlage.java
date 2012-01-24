/**
 * Inka_Anfallst_Anlage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Anfallst_Anlage  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer anfallst_anlage_ver;

    private java.lang.Integer anfallstelle_nr;

    private java.lang.Integer anfallstelle_ver;

    private java.lang.Integer anlage_nr;

    private java.lang.Integer anlage_ver;

    public Inka_Anfallst_Anlage() {
    }

    public Inka_Anfallst_Anlage(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer anfallst_anlage_ver,
           java.lang.Integer anfallstelle_nr,
           java.lang.Integer anfallstelle_ver,
           java.lang.Integer anlage_nr,
           java.lang.Integer anlage_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.anfallst_anlage_ver = anfallst_anlage_ver;
        this.anfallstelle_nr = anfallstelle_nr;
        this.anfallstelle_ver = anfallstelle_ver;
        this.anlage_nr = anlage_nr;
        this.anlage_ver = anlage_ver;
    }


    /**
     * Gets the anfallst_anlage_ver value for this Inka_Anfallst_Anlage.
     * 
     * @return anfallst_anlage_ver
     */
    public java.lang.Integer getAnfallst_anlage_ver() {
        return anfallst_anlage_ver;
    }


    /**
     * Sets the anfallst_anlage_ver value for this Inka_Anfallst_Anlage.
     * 
     * @param anfallst_anlage_ver
     */
    public void setAnfallst_anlage_ver(java.lang.Integer anfallst_anlage_ver) {
        this.anfallst_anlage_ver = anfallst_anlage_ver;
    }


    /**
     * Gets the anfallstelle_nr value for this Inka_Anfallst_Anlage.
     * 
     * @return anfallstelle_nr
     */
    public java.lang.Integer getAnfallstelle_nr() {
        return anfallstelle_nr;
    }


    /**
     * Sets the anfallstelle_nr value for this Inka_Anfallst_Anlage.
     * 
     * @param anfallstelle_nr
     */
    public void setAnfallstelle_nr(java.lang.Integer anfallstelle_nr) {
        this.anfallstelle_nr = anfallstelle_nr;
    }


    /**
     * Gets the anfallstelle_ver value for this Inka_Anfallst_Anlage.
     * 
     * @return anfallstelle_ver
     */
    public java.lang.Integer getAnfallstelle_ver() {
        return anfallstelle_ver;
    }


    /**
     * Sets the anfallstelle_ver value for this Inka_Anfallst_Anlage.
     * 
     * @param anfallstelle_ver
     */
    public void setAnfallstelle_ver(java.lang.Integer anfallstelle_ver) {
        this.anfallstelle_ver = anfallstelle_ver;
    }


    /**
     * Gets the anlage_nr value for this Inka_Anfallst_Anlage.
     * 
     * @return anlage_nr
     */
    public java.lang.Integer getAnlage_nr() {
        return anlage_nr;
    }


    /**
     * Sets the anlage_nr value for this Inka_Anfallst_Anlage.
     * 
     * @param anlage_nr
     */
    public void setAnlage_nr(java.lang.Integer anlage_nr) {
        this.anlage_nr = anlage_nr;
    }


    /**
     * Gets the anlage_ver value for this Inka_Anfallst_Anlage.
     * 
     * @return anlage_ver
     */
    public java.lang.Integer getAnlage_ver() {
        return anlage_ver;
    }


    /**
     * Sets the anlage_ver value for this Inka_Anfallst_Anlage.
     * 
     * @param anlage_ver
     */
    public void setAnlage_ver(java.lang.Integer anlage_ver) {
        this.anlage_ver = anlage_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Anfallst_Anlage)) return false;
        Inka_Anfallst_Anlage other = (Inka_Anfallst_Anlage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.anfallst_anlage_ver==null && other.getAnfallst_anlage_ver()==null) || 
             (this.anfallst_anlage_ver!=null &&
              this.anfallst_anlage_ver.equals(other.getAnfallst_anlage_ver()))) &&
            ((this.anfallstelle_nr==null && other.getAnfallstelle_nr()==null) || 
             (this.anfallstelle_nr!=null &&
              this.anfallstelle_nr.equals(other.getAnfallstelle_nr()))) &&
            ((this.anfallstelle_ver==null && other.getAnfallstelle_ver()==null) || 
             (this.anfallstelle_ver!=null &&
              this.anfallstelle_ver.equals(other.getAnfallstelle_ver()))) &&
            ((this.anlage_nr==null && other.getAnlage_nr()==null) || 
             (this.anlage_nr!=null &&
              this.anlage_nr.equals(other.getAnlage_nr()))) &&
            ((this.anlage_ver==null && other.getAnlage_ver()==null) || 
             (this.anlage_ver!=null &&
              this.anlage_ver.equals(other.getAnlage_ver())));
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
        if (getAnfallst_anlage_ver() != null) {
            _hashCode += getAnfallst_anlage_ver().hashCode();
        }
        if (getAnfallstelle_nr() != null) {
            _hashCode += getAnfallstelle_nr().hashCode();
        }
        if (getAnfallstelle_ver() != null) {
            _hashCode += getAnfallstelle_ver().hashCode();
        }
        if (getAnlage_nr() != null) {
            _hashCode += getAnlage_nr().hashCode();
        }
        if (getAnlage_ver() != null) {
            _hashCode += getAnlage_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Anfallst_Anlage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Anlage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anfallst_anlage_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anfallst_anlage_ver"));
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
