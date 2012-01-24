/**
 * ReqInka_Messst_Anlage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqInka_Messst_Anlage  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Messst_Anlage objInka_Messst_Anlage;

    public ReqInka_Messst_Anlage() {
    }

    public ReqInka_Messst_Anlage(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Messst_Anlage objInka_Messst_Anlage) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objInka_Messst_Anlage = objInka_Messst_Anlage;
    }


    /**
     * Gets the objInka_Messst_Anlage value for this ReqInka_Messst_Anlage.
     * 
     * @return objInka_Messst_Anlage
     */
    public de.nrw.lds.tipi.inka.Inka_Messst_Anlage getObjInka_Messst_Anlage() {
        return objInka_Messst_Anlage;
    }


    /**
     * Sets the objInka_Messst_Anlage value for this ReqInka_Messst_Anlage.
     * 
     * @param objInka_Messst_Anlage
     */
    public void setObjInka_Messst_Anlage(de.nrw.lds.tipi.inka.Inka_Messst_Anlage objInka_Messst_Anlage) {
        this.objInka_Messst_Anlage = objInka_Messst_Anlage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqInka_Messst_Anlage)) return false;
        ReqInka_Messst_Anlage other = (ReqInka_Messst_Anlage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objInka_Messst_Anlage==null && other.getObjInka_Messst_Anlage()==null) || 
             (this.objInka_Messst_Anlage!=null &&
              this.objInka_Messst_Anlage.equals(other.getObjInka_Messst_Anlage())));
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
        if (getObjInka_Messst_Anlage() != null) {
            _hashCode += getObjInka_Messst_Anlage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqInka_Messst_Anlage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Messst_Anlage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objInka_Messst_Anlage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objInka_Messst_Anlage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Messst_Anlage"));
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
