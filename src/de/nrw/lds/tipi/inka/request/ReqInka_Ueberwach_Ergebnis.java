/**
 * ReqInka_Ueberwach_Ergebnis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqInka_Ueberwach_Ergebnis  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis objInka_Ueberwach_Ergebnis;

    public ReqInka_Ueberwach_Ergebnis() {
    }

    public ReqInka_Ueberwach_Ergebnis(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis objInka_Ueberwach_Ergebnis) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objInka_Ueberwach_Ergebnis = objInka_Ueberwach_Ergebnis;
    }


    /**
     * Gets the objInka_Ueberwach_Ergebnis value for this ReqInka_Ueberwach_Ergebnis.
     * 
     * @return objInka_Ueberwach_Ergebnis
     */
    public de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis getObjInka_Ueberwach_Ergebnis() {
        return objInka_Ueberwach_Ergebnis;
    }


    /**
     * Sets the objInka_Ueberwach_Ergebnis value for this ReqInka_Ueberwach_Ergebnis.
     * 
     * @param objInka_Ueberwach_Ergebnis
     */
    public void setObjInka_Ueberwach_Ergebnis(de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis objInka_Ueberwach_Ergebnis) {
        this.objInka_Ueberwach_Ergebnis = objInka_Ueberwach_Ergebnis;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqInka_Ueberwach_Ergebnis)) return false;
        ReqInka_Ueberwach_Ergebnis other = (ReqInka_Ueberwach_Ergebnis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objInka_Ueberwach_Ergebnis==null && other.getObjInka_Ueberwach_Ergebnis()==null) || 
             (this.objInka_Ueberwach_Ergebnis!=null &&
              this.objInka_Ueberwach_Ergebnis.equals(other.getObjInka_Ueberwach_Ergebnis())));
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
        if (getObjInka_Ueberwach_Ergebnis() != null) {
            _hashCode += getObjInka_Ueberwach_Ergebnis().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqInka_Ueberwach_Ergebnis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwach_Ergebnis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objInka_Ueberwach_Ergebnis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objInka_Ueberwach_Ergebnis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Ueberwach_Ergebnis"));
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
