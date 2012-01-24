/**
 * ResDea_Arbeitsstaetten.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.response;

public class ResDea_Arbeitsstaetten  extends de.nrw.lds.tipi.inka.general.ResKatalog  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten[] arrDea_Arbeitsstaetten;

    public ResDea_Arbeitsstaetten() {
    }

    public ResDea_Arbeitsstaetten(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten[] arrDea_Arbeitsstaetten) {
        super(
            errorObject,
            serverTimestamp,
            numberOfAllObjects,
            numberOfObjects,
            startsFromObject);
        this.arrDea_Arbeitsstaetten = arrDea_Arbeitsstaetten;
    }


    /**
     * Gets the arrDea_Arbeitsstaetten value for this ResDea_Arbeitsstaetten.
     * 
     * @return arrDea_Arbeitsstaetten
     */
    public de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten[] getArrDea_Arbeitsstaetten() {
        return arrDea_Arbeitsstaetten;
    }


    /**
     * Sets the arrDea_Arbeitsstaetten value for this ResDea_Arbeitsstaetten.
     * 
     * @param arrDea_Arbeitsstaetten
     */
    public void setArrDea_Arbeitsstaetten(de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten[] arrDea_Arbeitsstaetten) {
        this.arrDea_Arbeitsstaetten = arrDea_Arbeitsstaetten;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResDea_Arbeitsstaetten)) return false;
        ResDea_Arbeitsstaetten other = (ResDea_Arbeitsstaetten) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrDea_Arbeitsstaetten==null && other.getArrDea_Arbeitsstaetten()==null) || 
             (this.arrDea_Arbeitsstaetten!=null &&
              java.util.Arrays.equals(this.arrDea_Arbeitsstaetten, other.getArrDea_Arbeitsstaetten())));
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
        if (getArrDea_Arbeitsstaetten() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrDea_Arbeitsstaetten());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrDea_Arbeitsstaetten(), i);
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
        new org.apache.axis.description.TypeDesc(ResDea_Arbeitsstaetten.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Arbeitsstaetten"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrDea_Arbeitsstaetten");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrDea_Arbeitsstaetten"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Arbeitsstaetten"));
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
