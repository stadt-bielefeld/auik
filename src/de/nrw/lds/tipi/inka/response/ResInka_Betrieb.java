/**
 * ResInka_Betrieb.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.response;

public class ResInka_Betrieb  extends de.nrw.lds.tipi.inka.general.ResKatalog  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Betrieb[] arrInka_Betrieb;

    public ResInka_Betrieb() {
    }

    public ResInka_Betrieb(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Betrieb[] arrInka_Betrieb) {
        super(
            errorObject,
            serverTimestamp,
            numberOfAllObjects,
            numberOfObjects,
            startsFromObject);
        this.arrInka_Betrieb = arrInka_Betrieb;
    }


    /**
     * Gets the arrInka_Betrieb value for this ResInka_Betrieb.
     * 
     * @return arrInka_Betrieb
     */
    public de.nrw.lds.tipi.inka.Inka_Betrieb[] getArrInka_Betrieb() {
        return arrInka_Betrieb;
    }


    /**
     * Sets the arrInka_Betrieb value for this ResInka_Betrieb.
     * 
     * @param arrInka_Betrieb
     */
    public void setArrInka_Betrieb(de.nrw.lds.tipi.inka.Inka_Betrieb[] arrInka_Betrieb) {
        this.arrInka_Betrieb = arrInka_Betrieb;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResInka_Betrieb)) return false;
        ResInka_Betrieb other = (ResInka_Betrieb) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrInka_Betrieb==null && other.getArrInka_Betrieb()==null) || 
             (this.arrInka_Betrieb!=null &&
              java.util.Arrays.equals(this.arrInka_Betrieb, other.getArrInka_Betrieb())));
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
        if (getArrInka_Betrieb() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrInka_Betrieb());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrInka_Betrieb(), i);
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
        new org.apache.axis.description.TypeDesc(ResInka_Betrieb.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Betrieb"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrInka_Betrieb");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrInka_Betrieb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Betrieb"));
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
