/**
 * ResInka_Betriebseinrichtung.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.response;

public class ResInka_Betriebseinrichtung  extends de.nrw.lds.tipi.inka.general.ResKatalog  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung[] arrInka_Betriebseinrichtung;

    public ResInka_Betriebseinrichtung() {
    }

    public ResInka_Betriebseinrichtung(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung[] arrInka_Betriebseinrichtung) {
        super(
            errorObject,
            serverTimestamp,
            numberOfAllObjects,
            numberOfObjects,
            startsFromObject);
        this.arrInka_Betriebseinrichtung = arrInka_Betriebseinrichtung;
    }


    /**
     * Gets the arrInka_Betriebseinrichtung value for this ResInka_Betriebseinrichtung.
     * 
     * @return arrInka_Betriebseinrichtung
     */
    public de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung[] getArrInka_Betriebseinrichtung() {
        return arrInka_Betriebseinrichtung;
    }


    /**
     * Sets the arrInka_Betriebseinrichtung value for this ResInka_Betriebseinrichtung.
     * 
     * @param arrInka_Betriebseinrichtung
     */
    public void setArrInka_Betriebseinrichtung(de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung[] arrInka_Betriebseinrichtung) {
        this.arrInka_Betriebseinrichtung = arrInka_Betriebseinrichtung;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResInka_Betriebseinrichtung)) return false;
        ResInka_Betriebseinrichtung other = (ResInka_Betriebseinrichtung) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrInka_Betriebseinrichtung==null && other.getArrInka_Betriebseinrichtung()==null) || 
             (this.arrInka_Betriebseinrichtung!=null &&
              java.util.Arrays.equals(this.arrInka_Betriebseinrichtung, other.getArrInka_Betriebseinrichtung())));
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
        if (getArrInka_Betriebseinrichtung() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrInka_Betriebseinrichtung());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrInka_Betriebseinrichtung(), i);
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
        new org.apache.axis.description.TypeDesc(ResInka_Betriebseinrichtung.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Betriebseinrichtung"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrInka_Betriebseinrichtung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrInka_Betriebseinrichtung"));
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
