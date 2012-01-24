/**
 * ResInka_Ueberwach_Ergebnis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.response;

public class ResInka_Ueberwach_Ergebnis  extends de.nrw.lds.tipi.inka.general.ResKatalog  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis[] arrInka_Ueberwach_Ergebnis;

    public ResInka_Ueberwach_Ergebnis() {
    }

    public ResInka_Ueberwach_Ergebnis(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis[] arrInka_Ueberwach_Ergebnis) {
        super(
            errorObject,
            serverTimestamp,
            numberOfAllObjects,
            numberOfObjects,
            startsFromObject);
        this.arrInka_Ueberwach_Ergebnis = arrInka_Ueberwach_Ergebnis;
    }


    /**
     * Gets the arrInka_Ueberwach_Ergebnis value for this ResInka_Ueberwach_Ergebnis.
     * 
     * @return arrInka_Ueberwach_Ergebnis
     */
    public de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis[] getArrInka_Ueberwach_Ergebnis() {
        return arrInka_Ueberwach_Ergebnis;
    }


    /**
     * Sets the arrInka_Ueberwach_Ergebnis value for this ResInka_Ueberwach_Ergebnis.
     * 
     * @param arrInka_Ueberwach_Ergebnis
     */
    public void setArrInka_Ueberwach_Ergebnis(de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis[] arrInka_Ueberwach_Ergebnis) {
        this.arrInka_Ueberwach_Ergebnis = arrInka_Ueberwach_Ergebnis;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResInka_Ueberwach_Ergebnis)) return false;
        ResInka_Ueberwach_Ergebnis other = (ResInka_Ueberwach_Ergebnis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrInka_Ueberwach_Ergebnis==null && other.getArrInka_Ueberwach_Ergebnis()==null) || 
             (this.arrInka_Ueberwach_Ergebnis!=null &&
              java.util.Arrays.equals(this.arrInka_Ueberwach_Ergebnis, other.getArrInka_Ueberwach_Ergebnis())));
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
        if (getArrInka_Ueberwach_Ergebnis() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrInka_Ueberwach_Ergebnis());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrInka_Ueberwach_Ergebnis(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResInka_Ueberwach_Ergebnis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Ueberwach_Ergebnis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrInka_Ueberwach_Ergebnis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrInka_Ueberwach_Ergebnis"));
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
