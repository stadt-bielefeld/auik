/**
 * ResInka_Probenahme.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.response;

public class ResInka_Probenahme  extends de.nrw.lds.tipi.inka.general.ResKatalog  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Probenahme[] arrInka_Probenahme;

    public ResInka_Probenahme() {
    }

    public ResInka_Probenahme(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Probenahme[] arrInka_Probenahme) {
        super(
            errorObject,
            serverTimestamp,
            numberOfAllObjects,
            numberOfObjects,
            startsFromObject);
        this.arrInka_Probenahme = arrInka_Probenahme;
    }


    /**
     * Gets the arrInka_Probenahme value for this ResInka_Probenahme.
     * 
     * @return arrInka_Probenahme
     */
    public de.nrw.lds.tipi.inka.Inka_Probenahme[] getArrInka_Probenahme() {
        return arrInka_Probenahme;
    }


    /**
     * Sets the arrInka_Probenahme value for this ResInka_Probenahme.
     * 
     * @param arrInka_Probenahme
     */
    public void setArrInka_Probenahme(de.nrw.lds.tipi.inka.Inka_Probenahme[] arrInka_Probenahme) {
        this.arrInka_Probenahme = arrInka_Probenahme;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResInka_Probenahme)) return false;
        ResInka_Probenahme other = (ResInka_Probenahme) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrInka_Probenahme==null && other.getArrInka_Probenahme()==null) || 
             (this.arrInka_Probenahme!=null &&
              java.util.Arrays.equals(this.arrInka_Probenahme, other.getArrInka_Probenahme())));
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
        if (getArrInka_Probenahme() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrInka_Probenahme());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrInka_Probenahme(), i);
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
        new org.apache.axis.description.TypeDesc(ResInka_Probenahme.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Probenahme"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrInka_Probenahme");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrInka_Probenahme"));
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
