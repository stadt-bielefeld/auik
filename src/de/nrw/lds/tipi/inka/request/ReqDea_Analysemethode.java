/**
 * ReqDea_Analysemethode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqDea_Analysemethode  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Dea_Analysemethode objDea_Analysemethode;

    public ReqDea_Analysemethode() {
    }

    public ReqDea_Analysemethode(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Dea_Analysemethode objDea_Analysemethode) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objDea_Analysemethode = objDea_Analysemethode;
    }


    /**
     * Gets the objDea_Analysemethode value for this ReqDea_Analysemethode.
     * 
     * @return objDea_Analysemethode
     */
    public de.nrw.lds.tipi.inka.Dea_Analysemethode getObjDea_Analysemethode() {
        return objDea_Analysemethode;
    }


    /**
     * Sets the objDea_Analysemethode value for this ReqDea_Analysemethode.
     * 
     * @param objDea_Analysemethode
     */
    public void setObjDea_Analysemethode(de.nrw.lds.tipi.inka.Dea_Analysemethode objDea_Analysemethode) {
        this.objDea_Analysemethode = objDea_Analysemethode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqDea_Analysemethode)) return false;
        ReqDea_Analysemethode other = (ReqDea_Analysemethode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objDea_Analysemethode==null && other.getObjDea_Analysemethode()==null) || 
             (this.objDea_Analysemethode!=null &&
              this.objDea_Analysemethode.equals(other.getObjDea_Analysemethode())));
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
        if (getObjDea_Analysemethode() != null) {
            _hashCode += getObjDea_Analysemethode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqDea_Analysemethode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Analysemethode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objDea_Analysemethode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objDea_Analysemethode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Analysemethode"));
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