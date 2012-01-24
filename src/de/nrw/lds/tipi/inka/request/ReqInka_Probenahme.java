/**
 * ReqInka_Probenahme.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqInka_Probenahme  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Probenahme objInka_Probenahme;

    public ReqInka_Probenahme() {
    }

    public ReqInka_Probenahme(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Probenahme objInka_Probenahme) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objInka_Probenahme = objInka_Probenahme;
    }


    /**
     * Gets the objInka_Probenahme value for this ReqInka_Probenahme.
     * 
     * @return objInka_Probenahme
     */
    public de.nrw.lds.tipi.inka.Inka_Probenahme getObjInka_Probenahme() {
        return objInka_Probenahme;
    }


    /**
     * Sets the objInka_Probenahme value for this ReqInka_Probenahme.
     * 
     * @param objInka_Probenahme
     */
    public void setObjInka_Probenahme(de.nrw.lds.tipi.inka.Inka_Probenahme objInka_Probenahme) {
        this.objInka_Probenahme = objInka_Probenahme;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqInka_Probenahme)) return false;
        ReqInka_Probenahme other = (ReqInka_Probenahme) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objInka_Probenahme==null && other.getObjInka_Probenahme()==null) || 
             (this.objInka_Probenahme!=null &&
              this.objInka_Probenahme.equals(other.getObjInka_Probenahme())));
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
        if (getObjInka_Probenahme() != null) {
            _hashCode += getObjInka_Probenahme().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqInka_Probenahme.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Probenahme"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objInka_Probenahme");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objInka_Probenahme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Probenahme"));
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
