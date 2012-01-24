/**
 * ResStandard.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.general;

public class ResStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.general.ResError errorObject;

    private java.util.Calendar serverTimestamp;

    public ResStandard() {
    }

    public ResStandard(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp) {
           this.errorObject = errorObject;
           this.serverTimestamp = serverTimestamp;
    }


    /**
     * Gets the errorObject value for this ResStandard.
     * 
     * @return errorObject
     */
    public de.nrw.lds.tipi.inka.general.ResError getErrorObject() {
        return errorObject;
    }


    /**
     * Sets the errorObject value for this ResStandard.
     * 
     * @param errorObject
     */
    public void setErrorObject(de.nrw.lds.tipi.inka.general.ResError errorObject) {
        this.errorObject = errorObject;
    }


    /**
     * Gets the serverTimestamp value for this ResStandard.
     * 
     * @return serverTimestamp
     */
    public java.util.Calendar getServerTimestamp() {
        return serverTimestamp;
    }


    /**
     * Sets the serverTimestamp value for this ResStandard.
     * 
     * @param serverTimestamp
     */
    public void setServerTimestamp(java.util.Calendar serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResStandard)) return false;
        ResStandard other = (ResStandard) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorObject==null && other.getErrorObject()==null) || 
             (this.errorObject!=null &&
              this.errorObject.equals(other.getErrorObject()))) &&
            ((this.serverTimestamp==null && other.getServerTimestamp()==null) || 
             (this.serverTimestamp!=null &&
              this.serverTimestamp.equals(other.getServerTimestamp())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getErrorObject() != null) {
            _hashCode += getErrorObject().hashCode();
        }
        if (getServerTimestamp() != null) {
            _hashCode += getServerTimestamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResStandard.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorObject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResError"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverTimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serverTimestamp"));
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
