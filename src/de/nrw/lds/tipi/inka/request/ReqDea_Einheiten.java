/**
 * ReqDea_Einheiten.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqDea_Einheiten  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Dea_Einheiten objDea_Einheiten;

    public ReqDea_Einheiten() {
    }

    public ReqDea_Einheiten(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Dea_Einheiten objDea_Einheiten) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objDea_Einheiten = objDea_Einheiten;
    }


    /**
     * Gets the objDea_Einheiten value for this ReqDea_Einheiten.
     * 
     * @return objDea_Einheiten
     */
    public de.nrw.lds.tipi.inka.Dea_Einheiten getObjDea_Einheiten() {
        return objDea_Einheiten;
    }


    /**
     * Sets the objDea_Einheiten value for this ReqDea_Einheiten.
     * 
     * @param objDea_Einheiten
     */
    public void setObjDea_Einheiten(de.nrw.lds.tipi.inka.Dea_Einheiten objDea_Einheiten) {
        this.objDea_Einheiten = objDea_Einheiten;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqDea_Einheiten)) return false;
        ReqDea_Einheiten other = (ReqDea_Einheiten) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objDea_Einheiten==null && other.getObjDea_Einheiten()==null) || 
             (this.objDea_Einheiten!=null &&
              this.objDea_Einheiten.equals(other.getObjDea_Einheiten())));
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
        if (getObjDea_Einheiten() != null) {
            _hashCode += getObjDea_Einheiten().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqDea_Einheiten.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Einheiten"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objDea_Einheiten");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objDea_Einheiten"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Einheiten"));
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
