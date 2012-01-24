/**
 * ReqDea_Klaeranlage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqDea_Klaeranlage  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Dea_Klaeranlage objDea_Klaeranlage;

    public ReqDea_Klaeranlage() {
    }

    public ReqDea_Klaeranlage(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Dea_Klaeranlage objDea_Klaeranlage) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objDea_Klaeranlage = objDea_Klaeranlage;
    }


    /**
     * Gets the objDea_Klaeranlage value for this ReqDea_Klaeranlage.
     * 
     * @return objDea_Klaeranlage
     */
    public de.nrw.lds.tipi.inka.Dea_Klaeranlage getObjDea_Klaeranlage() {
        return objDea_Klaeranlage;
    }


    /**
     * Sets the objDea_Klaeranlage value for this ReqDea_Klaeranlage.
     * 
     * @param objDea_Klaeranlage
     */
    public void setObjDea_Klaeranlage(de.nrw.lds.tipi.inka.Dea_Klaeranlage objDea_Klaeranlage) {
        this.objDea_Klaeranlage = objDea_Klaeranlage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqDea_Klaeranlage)) return false;
        ReqDea_Klaeranlage other = (ReqDea_Klaeranlage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objDea_Klaeranlage==null && other.getObjDea_Klaeranlage()==null) || 
             (this.objDea_Klaeranlage!=null &&
              this.objDea_Klaeranlage.equals(other.getObjDea_Klaeranlage())));
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
        if (getObjDea_Klaeranlage() != null) {
            _hashCode += getObjDea_Klaeranlage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqDea_Klaeranlage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Klaeranlage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objDea_Klaeranlage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objDea_Klaeranlage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Klaeranlage"));
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
