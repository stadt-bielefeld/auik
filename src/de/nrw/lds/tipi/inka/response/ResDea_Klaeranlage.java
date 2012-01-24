/**
 * ResDea_Klaeranlage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.response;

public class ResDea_Klaeranlage  extends de.nrw.lds.tipi.inka.general.ResKatalog  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Dea_Klaeranlage[] arrDea_Klaeranlage;

    public ResDea_Klaeranlage() {
    }

    public ResDea_Klaeranlage(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Dea_Klaeranlage[] arrDea_Klaeranlage) {
        super(
            errorObject,
            serverTimestamp,
            numberOfAllObjects,
            numberOfObjects,
            startsFromObject);
        this.arrDea_Klaeranlage = arrDea_Klaeranlage;
    }


    /**
     * Gets the arrDea_Klaeranlage value for this ResDea_Klaeranlage.
     * 
     * @return arrDea_Klaeranlage
     */
    public de.nrw.lds.tipi.inka.Dea_Klaeranlage[] getArrDea_Klaeranlage() {
        return arrDea_Klaeranlage;
    }


    /**
     * Sets the arrDea_Klaeranlage value for this ResDea_Klaeranlage.
     * 
     * @param arrDea_Klaeranlage
     */
    public void setArrDea_Klaeranlage(de.nrw.lds.tipi.inka.Dea_Klaeranlage[] arrDea_Klaeranlage) {
        this.arrDea_Klaeranlage = arrDea_Klaeranlage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResDea_Klaeranlage)) return false;
        ResDea_Klaeranlage other = (ResDea_Klaeranlage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrDea_Klaeranlage==null && other.getArrDea_Klaeranlage()==null) || 
             (this.arrDea_Klaeranlage!=null &&
              java.util.Arrays.equals(this.arrDea_Klaeranlage, other.getArrDea_Klaeranlage())));
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
        if (getArrDea_Klaeranlage() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrDea_Klaeranlage());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrDea_Klaeranlage(), i);
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
        new org.apache.axis.description.TypeDesc(ResDea_Klaeranlage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Klaeranlage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrDea_Klaeranlage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrDea_Klaeranlage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Klaeranlage"));
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
