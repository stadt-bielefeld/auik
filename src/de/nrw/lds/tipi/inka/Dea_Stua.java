/**
 * Dea_Stua.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Stua  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String stua_bezeichnung;

    private java.lang.Integer stua_bezirk;

    private java.lang.Integer stua_ver;

    public Dea_Stua() {
    }

    public Dea_Stua(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String stua_bezeichnung,
           java.lang.Integer stua_bezirk,
           java.lang.Integer stua_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.stua_bezeichnung = stua_bezeichnung;
        this.stua_bezirk = stua_bezirk;
        this.stua_ver = stua_ver;
    }


    /**
     * Gets the stua_bezeichnung value for this Dea_Stua.
     * 
     * @return stua_bezeichnung
     */
    public java.lang.String getStua_bezeichnung() {
        return stua_bezeichnung;
    }


    /**
     * Sets the stua_bezeichnung value for this Dea_Stua.
     * 
     * @param stua_bezeichnung
     */
    public void setStua_bezeichnung(java.lang.String stua_bezeichnung) {
        this.stua_bezeichnung = stua_bezeichnung;
    }


    /**
     * Gets the stua_bezirk value for this Dea_Stua.
     * 
     * @return stua_bezirk
     */
    public java.lang.Integer getStua_bezirk() {
        return stua_bezirk;
    }


    /**
     * Sets the stua_bezirk value for this Dea_Stua.
     * 
     * @param stua_bezirk
     */
    public void setStua_bezirk(java.lang.Integer stua_bezirk) {
        this.stua_bezirk = stua_bezirk;
    }


    /**
     * Gets the stua_ver value for this Dea_Stua.
     * 
     * @return stua_ver
     */
    public java.lang.Integer getStua_ver() {
        return stua_ver;
    }


    /**
     * Sets the stua_ver value for this Dea_Stua.
     * 
     * @param stua_ver
     */
    public void setStua_ver(java.lang.Integer stua_ver) {
        this.stua_ver = stua_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Stua)) return false;
        Dea_Stua other = (Dea_Stua) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.stua_bezeichnung==null && other.getStua_bezeichnung()==null) || 
             (this.stua_bezeichnung!=null &&
              this.stua_bezeichnung.equals(other.getStua_bezeichnung()))) &&
            ((this.stua_bezirk==null && other.getStua_bezirk()==null) || 
             (this.stua_bezirk!=null &&
              this.stua_bezirk.equals(other.getStua_bezirk()))) &&
            ((this.stua_ver==null && other.getStua_ver()==null) || 
             (this.stua_ver!=null &&
              this.stua_ver.equals(other.getStua_ver())));
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
        if (getStua_bezeichnung() != null) {
            _hashCode += getStua_bezeichnung().hashCode();
        }
        if (getStua_bezirk() != null) {
            _hashCode += getStua_bezirk().hashCode();
        }
        if (getStua_ver() != null) {
            _hashCode += getStua_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Stua.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Stua"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stua_bezeichnung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stua_bezeichnung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
