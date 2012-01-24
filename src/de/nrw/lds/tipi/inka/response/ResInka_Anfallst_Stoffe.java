/**
 * ResInka_Anfallst_Stoffe.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.response;

public class ResInka_Anfallst_Stoffe  extends de.nrw.lds.tipi.inka.general.ResKatalog  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe[] arrInka_Anfallst_Stoffe;

    public ResInka_Anfallst_Stoffe() {
    }

    public ResInka_Anfallst_Stoffe(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe[] arrInka_Anfallst_Stoffe) {
        super(
            errorObject,
            serverTimestamp,
            numberOfAllObjects,
            numberOfObjects,
            startsFromObject);
        this.arrInka_Anfallst_Stoffe = arrInka_Anfallst_Stoffe;
    }


    /**
     * Gets the arrInka_Anfallst_Stoffe value for this ResInka_Anfallst_Stoffe.
     * 
     * @return arrInka_Anfallst_Stoffe
     */
    public de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe[] getArrInka_Anfallst_Stoffe() {
        return arrInka_Anfallst_Stoffe;
    }


    /**
     * Sets the arrInka_Anfallst_Stoffe value for this ResInka_Anfallst_Stoffe.
     * 
     * @param arrInka_Anfallst_Stoffe
     */
    public void setArrInka_Anfallst_Stoffe(de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe[] arrInka_Anfallst_Stoffe) {
        this.arrInka_Anfallst_Stoffe = arrInka_Anfallst_Stoffe;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResInka_Anfallst_Stoffe)) return false;
        ResInka_Anfallst_Stoffe other = (ResInka_Anfallst_Stoffe) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrInka_Anfallst_Stoffe==null && other.getArrInka_Anfallst_Stoffe()==null) || 
             (this.arrInka_Anfallst_Stoffe!=null &&
              java.util.Arrays.equals(this.arrInka_Anfallst_Stoffe, other.getArrInka_Anfallst_Stoffe())));
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
        if (getArrInka_Anfallst_Stoffe() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrInka_Anfallst_Stoffe());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrInka_Anfallst_Stoffe(), i);
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
        new org.apache.axis.description.TypeDesc(ResInka_Anfallst_Stoffe.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anfallst_Stoffe"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrInka_Anfallst_Stoffe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrInka_Anfallst_Stoffe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Stoffe"));
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
