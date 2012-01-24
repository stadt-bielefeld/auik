/**
 * ReqInka_Anfallst_Stoffe.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.request;

public class ReqInka_Anfallst_Stoffe  extends de.nrw.lds.tipi.inka.general.ReqStandard  implements java.io.Serializable {
    private de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe objInka_Anfallst_Stoffe;

    public ReqInka_Anfallst_Stoffe() {
    }

    public ReqInka_Anfallst_Stoffe(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject,
           de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe objInka_Anfallst_Stoffe) {
        super(
            clientTimestamp,
            clientVendor,
            clientVersion,
            kennung,
            numberOfObjects,
            password,
            startsFromObject);
        this.objInka_Anfallst_Stoffe = objInka_Anfallst_Stoffe;
    }


    /**
     * Gets the objInka_Anfallst_Stoffe value for this ReqInka_Anfallst_Stoffe.
     * 
     * @return objInka_Anfallst_Stoffe
     */
    public de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe getObjInka_Anfallst_Stoffe() {
        return objInka_Anfallst_Stoffe;
    }


    /**
     * Sets the objInka_Anfallst_Stoffe value for this ReqInka_Anfallst_Stoffe.
     * 
     * @param objInka_Anfallst_Stoffe
     */
    public void setObjInka_Anfallst_Stoffe(de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe objInka_Anfallst_Stoffe) {
        this.objInka_Anfallst_Stoffe = objInka_Anfallst_Stoffe;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqInka_Anfallst_Stoffe)) return false;
        ReqInka_Anfallst_Stoffe other = (ReqInka_Anfallst_Stoffe) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.objInka_Anfallst_Stoffe==null && other.getObjInka_Anfallst_Stoffe()==null) || 
             (this.objInka_Anfallst_Stoffe!=null &&
              this.objInka_Anfallst_Stoffe.equals(other.getObjInka_Anfallst_Stoffe())));
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
        if (getObjInka_Anfallst_Stoffe() != null) {
            _hashCode += getObjInka_Anfallst_Stoffe().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqInka_Anfallst_Stoffe.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Stoffe"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objInka_Anfallst_Stoffe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objInka_Anfallst_Stoffe"));
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
