/**
 * ReqDea_Gemeinde.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqDea_Gemeinde  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Dea_Gemeinde objDea_Gemeinde;

    public ReqDea_Gemeinde() {
    }

    public ReqDea_Gemeinde(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Dea_Gemeinde objDea_Gemeinde) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objDea_Gemeinde = objDea_Gemeinde;
    }


    /**
     * Gets the objDea_Gemeinde value for this ReqDea_Gemeinde.
     * 
     * @return objDea_Gemeinde
     */
    public de.nrw.lds.tipi.inka.Dea_Gemeinde getObjDea_Gemeinde() {
        return objDea_Gemeinde;
    }


    /**
     * Sets the objDea_Gemeinde value for this ReqDea_Gemeinde.
     * 
     * @param objDea_Gemeinde
     */
    public void setObjDea_Gemeinde(de.nrw.lds.tipi.inka.Dea_Gemeinde objDea_Gemeinde) {
        this.objDea_Gemeinde = objDea_Gemeinde;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqDea_Gemeinde)) return false;
        ReqDea_Gemeinde other = (ReqDea_Gemeinde) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objDea_Gemeinde==null && other.getObjDea_Gemeinde()==null) || 
             (this.objDea_Gemeinde!=null &&
              this.objDea_Gemeinde.equals(other.getObjDea_Gemeinde())));
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
        if (getObjDea_Gemeinde() != null) {
            _hashCode += getObjDea_Gemeinde().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqDea_Gemeinde.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Gemeinde"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objDea_Gemeinde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objDea_Gemeinde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Gemeinde"));
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
