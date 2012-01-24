/**
 * ReqInka_Uebergabestelle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqInka_Uebergabestelle  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Uebergabestelle objInka_Uebergabestelle;

    public ReqInka_Uebergabestelle() {
    }

    public ReqInka_Uebergabestelle(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Uebergabestelle objInka_Uebergabestelle) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objInka_Uebergabestelle = objInka_Uebergabestelle;
    }


    /**
     * Gets the objInka_Uebergabestelle value for this ReqInka_Uebergabestelle.
     * 
     * @return objInka_Uebergabestelle
     */
    public de.nrw.lds.tipi.inka.Inka_Uebergabestelle getObjInka_Uebergabestelle() {
        return objInka_Uebergabestelle;
    }


    /**
     * Sets the objInka_Uebergabestelle value for this ReqInka_Uebergabestelle.
     * 
     * @param objInka_Uebergabestelle
     */
    public void setObjInka_Uebergabestelle(de.nrw.lds.tipi.inka.Inka_Uebergabestelle objInka_Uebergabestelle) {
        this.objInka_Uebergabestelle = objInka_Uebergabestelle;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqInka_Uebergabestelle)) return false;
        ReqInka_Uebergabestelle other = (ReqInka_Uebergabestelle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objInka_Uebergabestelle==null && other.getObjInka_Uebergabestelle()==null) || 
             (this.objInka_Uebergabestelle!=null &&
              this.objInka_Uebergabestelle.equals(other.getObjInka_Uebergabestelle())));
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
        if (getObjInka_Uebergabestelle() != null) {
            _hashCode += getObjInka_Uebergabestelle().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqInka_Uebergabestelle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Uebergabestelle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objInka_Uebergabestelle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objInka_Uebergabestelle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Uebergabestelle"));
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
