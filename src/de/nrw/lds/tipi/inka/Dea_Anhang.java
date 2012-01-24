/**
 * Dea_Anhang.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Anhang  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String anh_id;

    private java.lang.String anh_regelwerk;

    private java.lang.String anh_text;

    private java.lang.Integer anh_ver;

    public Dea_Anhang() {
    }

    public Dea_Anhang(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String anh_id,
           java.lang.String anh_regelwerk,
           java.lang.String anh_text,
           java.lang.Integer anh_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.anh_id = anh_id;
        this.anh_regelwerk = anh_regelwerk;
        this.anh_text = anh_text;
        this.anh_ver = anh_ver;
    }


    /**
     * Gets the anh_id value for this Dea_Anhang.
     * 
     * @return anh_id
     */
    public java.lang.String getAnh_id() {
        return anh_id;
    }


    /**
     * Sets the anh_id value for this Dea_Anhang.
     * 
     * @param anh_id
     */
    public void setAnh_id(java.lang.String anh_id) {
        this.anh_id = anh_id;
    }


    /**
     * Gets the anh_regelwerk value for this Dea_Anhang.
     * 
     * @return anh_regelwerk
     */
    public java.lang.String getAnh_regelwerk() {
        return anh_regelwerk;
    }


    /**
     * Sets the anh_regelwerk value for this Dea_Anhang.
     * 
     * @param anh_regelwerk
     */
    public void setAnh_regelwerk(java.lang.String anh_regelwerk) {
        this.anh_regelwerk = anh_regelwerk;
    }


    /**
     * Gets the anh_text value for this Dea_Anhang.
     * 
     * @return anh_text
     */
    public java.lang.String getAnh_text() {
        return anh_text;
    }


    /**
     * Sets the anh_text value for this Dea_Anhang.
     * 
     * @param anh_text
     */
    public void setAnh_text(java.lang.String anh_text) {
        this.anh_text = anh_text;
    }


    /**
     * Gets the anh_ver value for this Dea_Anhang.
     * 
     * @return anh_ver
     */
    public java.lang.Integer getAnh_ver() {
        return anh_ver;
    }


    /**
     * Sets the anh_ver value for this Dea_Anhang.
     * 
     * @param anh_ver
     */
    public void setAnh_ver(java.lang.Integer anh_ver) {
        this.anh_ver = anh_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Anhang)) return false;
        Dea_Anhang other = (Dea_Anhang) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.anh_id==null && other.getAnh_id()==null) || 
             (this.anh_id!=null &&
              this.anh_id.equals(other.getAnh_id()))) &&
            ((this.anh_regelwerk==null && other.getAnh_regelwerk()==null) || 
             (this.anh_regelwerk!=null &&
              this.anh_regelwerk.equals(other.getAnh_regelwerk()))) &&
            ((this.anh_text==null && other.getAnh_text()==null) || 
             (this.anh_text!=null &&
              this.anh_text.equals(other.getAnh_text()))) &&
            ((this.anh_ver==null && other.getAnh_ver()==null) || 
             (this.anh_ver!=null &&
              this.anh_ver.equals(other.getAnh_ver())));
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
        if (getAnh_id() != null) {
            _hashCode += getAnh_id().hashCode();
        }
        if (getAnh_regelwerk() != null) {
            _hashCode += getAnh_regelwerk().hashCode();
        }
        if (getAnh_text() != null) {
            _hashCode += getAnh_text().hashCode();
        }
        if (getAnh_ver() != null) {
            _hashCode += getAnh_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Anhang.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Anhang"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anh_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anh_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anh_regelwerk");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anh_regelwerk"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anh_text");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anh_text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anh_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anh_ver"));
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
