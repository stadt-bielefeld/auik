/**
 * ReqInka_Ueberwachungswert.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqInka_Ueberwachungswert  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Ueberwachungswert objInka_Ueberwachungswert;

    public ReqInka_Ueberwachungswert() {
    }

    public ReqInka_Ueberwachungswert(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Ueberwachungswert objInka_Ueberwachungswert) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objInka_Ueberwachungswert = objInka_Ueberwachungswert;
    }


    /**
     * Gets the objInka_Ueberwachungswert value for this ReqInka_Ueberwachungswert.
     * 
     * @return objInka_Ueberwachungswert
     */
    public de.nrw.lds.tipi.inka.Inka_Ueberwachungswert getObjInka_Ueberwachungswert() {
        return objInka_Ueberwachungswert;
    }


    /**
     * Sets the objInka_Ueberwachungswert value for this ReqInka_Ueberwachungswert.
     * 
     * @param objInka_Ueberwachungswert
     */
    public void setObjInka_Ueberwachungswert(de.nrw.lds.tipi.inka.Inka_Ueberwachungswert objInka_Ueberwachungswert) {
        this.objInka_Ueberwachungswert = objInka_Ueberwachungswert;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqInka_Ueberwachungswert)) return false;
        ReqInka_Ueberwachungswert other = (ReqInka_Ueberwachungswert) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objInka_Ueberwachungswert==null && other.getObjInka_Ueberwachungswert()==null) || 
             (this.objInka_Ueberwachungswert!=null &&
              this.objInka_Ueberwachungswert.equals(other.getObjInka_Ueberwachungswert())));
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
        if (getObjInka_Ueberwachungswert() != null) {
            _hashCode += getObjInka_Ueberwachungswert().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqInka_Ueberwachungswert.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwachungswert"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objInka_Ueberwachungswert");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objInka_Ueberwachungswert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Ueberwachungswert"));
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
