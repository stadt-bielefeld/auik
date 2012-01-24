/**
 * ReqDea_Arbeitsstaetten.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqDea_Arbeitsstaetten  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten objDea_Arbeitsstaetten;

    public ReqDea_Arbeitsstaetten() {
    }

    public ReqDea_Arbeitsstaetten(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten objDea_Arbeitsstaetten) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objDea_Arbeitsstaetten = objDea_Arbeitsstaetten;
    }


    /**
     * Gets the objDea_Arbeitsstaetten value for this ReqDea_Arbeitsstaetten.
     * 
     * @return objDea_Arbeitsstaetten
     */
    public de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten getObjDea_Arbeitsstaetten() {
        return objDea_Arbeitsstaetten;
    }


    /**
     * Sets the objDea_Arbeitsstaetten value for this ReqDea_Arbeitsstaetten.
     * 
     * @param objDea_Arbeitsstaetten
     */
    public void setObjDea_Arbeitsstaetten(de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten objDea_Arbeitsstaetten) {
        this.objDea_Arbeitsstaetten = objDea_Arbeitsstaetten;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqDea_Arbeitsstaetten)) return false;
        ReqDea_Arbeitsstaetten other = (ReqDea_Arbeitsstaetten) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objDea_Arbeitsstaetten==null && other.getObjDea_Arbeitsstaetten()==null) || 
             (this.objDea_Arbeitsstaetten!=null &&
              this.objDea_Arbeitsstaetten.equals(other.getObjDea_Arbeitsstaetten())));
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
        if (getObjDea_Arbeitsstaetten() != null) {
            _hashCode += getObjDea_Arbeitsstaetten().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqDea_Arbeitsstaetten.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Arbeitsstaetten"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objDea_Arbeitsstaetten");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objDea_Arbeitsstaetten"));
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
