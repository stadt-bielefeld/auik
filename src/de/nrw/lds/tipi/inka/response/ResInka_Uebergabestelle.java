/**
 * ResInka_Uebergabestelle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.response;

public class ResInka_Uebergabestelle  extends de.nrw.lds.tipi.inka.general.ResKatalog  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Uebergabestelle[] arrInka_Uebergabestelle;

    public ResInka_Uebergabestelle() {
    }

    public ResInka_Uebergabestelle(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Uebergabestelle[] arrInka_Uebergabestelle) {
        super(
            errorObject,
            serverTimestamp,
            numberOfAllObjects,
            numberOfObjects,
            startsFromObject);
        this.arrInka_Uebergabestelle = arrInka_Uebergabestelle;
    }


    /**
     * Gets the arrInka_Uebergabestelle value for this ResInka_Uebergabestelle.
     * 
     * @return arrInka_Uebergabestelle
     */
    public de.nrw.lds.tipi.inka.Inka_Uebergabestelle[] getArrInka_Uebergabestelle() {
        return arrInka_Uebergabestelle;
    }


    /**
     * Sets the arrInka_Uebergabestelle value for this ResInka_Uebergabestelle.
     * 
     * @param arrInka_Uebergabestelle
     */
    public void setArrInka_Uebergabestelle(de.nrw.lds.tipi.inka.Inka_Uebergabestelle[] arrInka_Uebergabestelle) {
        this.arrInka_Uebergabestelle = arrInka_Uebergabestelle;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResInka_Uebergabestelle)) return false;
        ResInka_Uebergabestelle other = (ResInka_Uebergabestelle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrInka_Uebergabestelle==null && other.getArrInka_Uebergabestelle()==null) || 
             (this.arrInka_Uebergabestelle!=null &&
              java.util.Arrays.equals(this.arrInka_Uebergabestelle, other.getArrInka_Uebergabestelle())));
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
        if (getArrInka_Uebergabestelle() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrInka_Uebergabestelle());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrInka_Uebergabestelle(), i);
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
        new org.apache.axis.description.TypeDesc(ResInka_Uebergabestelle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Uebergabestelle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrInka_Uebergabestelle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrInka_Uebergabestelle"));
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
