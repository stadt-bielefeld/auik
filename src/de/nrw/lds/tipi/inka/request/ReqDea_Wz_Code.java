/**
 * ReqDea_Wz_Code.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqDea_Wz_Code  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Dea_Wz_Code objDea_Wz_Code;

    public ReqDea_Wz_Code() {
    }

    public ReqDea_Wz_Code(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Dea_Wz_Code objDea_Wz_Code) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objDea_Wz_Code = objDea_Wz_Code;
    }


    /**
     * Gets the objDea_Wz_Code value for this ReqDea_Wz_Code.
     * 
     * @return objDea_Wz_Code
     */
    public de.nrw.lds.tipi.inka.Dea_Wz_Code getObjDea_Wz_Code() {
        return objDea_Wz_Code;
    }


    /**
     * Sets the objDea_Wz_Code value for this ReqDea_Wz_Code.
     * 
     * @param objDea_Wz_Code
     */
    public void setObjDea_Wz_Code(de.nrw.lds.tipi.inka.Dea_Wz_Code objDea_Wz_Code) {
        this.objDea_Wz_Code = objDea_Wz_Code;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqDea_Wz_Code)) return false;
        ReqDea_Wz_Code other = (ReqDea_Wz_Code) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objDea_Wz_Code==null && other.getObjDea_Wz_Code()==null) || 
             (this.objDea_Wz_Code!=null &&
              this.objDea_Wz_Code.equals(other.getObjDea_Wz_Code())));
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
        if (getObjDea_Wz_Code() != null) {
            _hashCode += getObjDea_Wz_Code().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqDea_Wz_Code.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Wz_Code"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objDea_Wz_Code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objDea_Wz_Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Wz_Code"));
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
