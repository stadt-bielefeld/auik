/**
 * ResInka_Messstelle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.response;

public class ResInka_Messstelle  extends de.nrw.lds.tipi.inka.general.ResKatalog  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Messstelle[] arrInka_Messstelle;

    public ResInka_Messstelle() {
    }

    public ResInka_Messstelle(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Messstelle[] arrInka_Messstelle) {
        super(
            errorObject,
            serverTimestamp,
            numberOfAllObjects,
            numberOfObjects,
            startsFromObject);
        this.arrInka_Messstelle = arrInka_Messstelle;
    }


    /**
     * Gets the arrInka_Messstelle value for this ResInka_Messstelle.
     * 
     * @return arrInka_Messstelle
     */
    public de.nrw.lds.tipi.inka.Inka_Messstelle[] getArrInka_Messstelle() {
        return arrInka_Messstelle;
    }


    /**
     * Sets the arrInka_Messstelle value for this ResInka_Messstelle.
     * 
     * @param arrInka_Messstelle
     */
    public void setArrInka_Messstelle(de.nrw.lds.tipi.inka.Inka_Messstelle[] arrInka_Messstelle) {
        this.arrInka_Messstelle = arrInka_Messstelle;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResInka_Messstelle)) return false;
        ResInka_Messstelle other = (ResInka_Messstelle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrInka_Messstelle==null && other.getArrInka_Messstelle()==null) || 
             (this.arrInka_Messstelle!=null &&
              java.util.Arrays.equals(this.arrInka_Messstelle, other.getArrInka_Messstelle())));
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
        if (getArrInka_Messstelle() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrInka_Messstelle());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrInka_Messstelle(), i);
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
        new org.apache.axis.description.TypeDesc(ResInka_Messstelle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Messstelle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrInka_Messstelle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrInka_Messstelle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Messstelle"));
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
