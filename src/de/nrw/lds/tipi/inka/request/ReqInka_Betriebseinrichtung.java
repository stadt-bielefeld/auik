/**
 * ReqInka_Betriebseinrichtung.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqInka_Betriebseinrichtung  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung objInka_Betriebseinrichtung;

    public ReqInka_Betriebseinrichtung() {
    }

    public ReqInka_Betriebseinrichtung(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung objInka_Betriebseinrichtung) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objInka_Betriebseinrichtung = objInka_Betriebseinrichtung;
    }


    /**
     * Gets the objInka_Betriebseinrichtung value for this ReqInka_Betriebseinrichtung.
     * 
     * @return objInka_Betriebseinrichtung
     */
    public de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung getObjInka_Betriebseinrichtung() {
        return objInka_Betriebseinrichtung;
    }


    /**
     * Sets the objInka_Betriebseinrichtung value for this ReqInka_Betriebseinrichtung.
     * 
     * @param objInka_Betriebseinrichtung
     */
    public void setObjInka_Betriebseinrichtung(de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung objInka_Betriebseinrichtung) {
        this.objInka_Betriebseinrichtung = objInka_Betriebseinrichtung;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqInka_Betriebseinrichtung)) return false;
        ReqInka_Betriebseinrichtung other = (ReqInka_Betriebseinrichtung) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objInka_Betriebseinrichtung==null && other.getObjInka_Betriebseinrichtung()==null) || 
             (this.objInka_Betriebseinrichtung!=null &&
              this.objInka_Betriebseinrichtung.equals(other.getObjInka_Betriebseinrichtung())));
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
        if (getObjInka_Betriebseinrichtung() != null) {
            _hashCode += getObjInka_Betriebseinrichtung().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqInka_Betriebseinrichtung.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Betriebseinrichtung"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objInka_Betriebseinrichtung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objInka_Betriebseinrichtung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Betriebseinrichtung"));
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
