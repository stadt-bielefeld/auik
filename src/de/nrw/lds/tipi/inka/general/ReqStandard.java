/**
 * ReqStandard.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.general;

public class ReqStandard  implements java.io.Serializable {
    private java.util.Calendar clientTimestamp;

    private java.lang.String clientVendor;

    private java.lang.String clientVersion;

    private java.lang.String kennung;

    private java.lang.Integer numberOfObjects;

    private java.lang.String password;

    private java.lang.Integer startsFromObject;

    public ReqStandard() {
    }

    public ReqStandard(
           java.util.Calendar clientTimestamp,
           java.lang.String clientVendor,
           java.lang.String clientVersion,
           java.lang.String kennung,
           java.lang.Integer numberOfObjects,
           java.lang.String password,
           java.lang.Integer startsFromObject) {
           this.clientTimestamp = clientTimestamp;
           this.clientVendor = clientVendor;
           this.clientVersion = clientVersion;
           this.kennung = kennung;
           this.numberOfObjects = numberOfObjects;
           this.password = password;
           this.startsFromObject = startsFromObject;
    }


    /**
     * Gets the clientTimestamp value for this ReqStandard.
     * 
     * @return clientTimestamp
     */
    public java.util.Calendar getClientTimestamp() {
        return clientTimestamp;
    }


    /**
     * Sets the clientTimestamp value for this ReqStandard.
     * 
     * @param clientTimestamp
     */
    public void setClientTimestamp(java.util.Calendar clientTimestamp) {
        this.clientTimestamp = clientTimestamp;
    }


    /**
     * Gets the clientVendor value for this ReqStandard.
     * 
     * @return clientVendor
     */
    public java.lang.String getClientVendor() {
        return clientVendor;
    }


    /**
     * Sets the clientVendor value for this ReqStandard.
     * 
     * @param clientVendor
     */
    public void setClientVendor(java.lang.String clientVendor) {
        this.clientVendor = clientVendor;
    }


    /**
     * Gets the clientVersion value for this ReqStandard.
     * 
     * @return clientVersion
     */
    public java.lang.String getClientVersion() {
        return clientVersion;
    }


    /**
     * Sets the clientVersion value for this ReqStandard.
     * 
     * @param clientVersion
     */
    public void setClientVersion(java.lang.String clientVersion) {
        this.clientVersion = clientVersion;
    }


    /**
     * Gets the kennung value for this ReqStandard.
     * 
     * @return kennung
     */
    public java.lang.String getKennung() {
        return kennung;
    }


    /**
     * Sets the kennung value for this ReqStandard.
     * 
     * @param kennung
     */
    public void setKennung(java.lang.String kennung) {
        this.kennung = kennung;
    }


    /**
     * Gets the numberOfObjects value for this ReqStandard.
     * 
     * @return numberOfObjects
     */
    public java.lang.Integer getNumberOfObjects() {
        return numberOfObjects;
    }


    /**
     * Sets the numberOfObjects value for this ReqStandard.
     * 
     * @param numberOfObjects
     */
    public void setNumberOfObjects(java.lang.Integer numberOfObjects) {
        this.numberOfObjects = numberOfObjects;
    }


    /**
     * Gets the password value for this ReqStandard.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this ReqStandard.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the startsFromObject value for this ReqStandard.
     * 
     * @return startsFromObject
     */
    public java.lang.Integer getStartsFromObject() {
        return startsFromObject;
    }


    /**
     * Sets the startsFromObject value for this ReqStandard.
     * 
     * @param startsFromObject
     */
    public void setStartsFromObject(java.lang.Integer startsFromObject) {
        this.startsFromObject = startsFromObject;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReqStandard)) return false;
        ReqStandard other = (ReqStandard) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clientTimestamp==null && other.getClientTimestamp()==null) || 
             (this.clientTimestamp!=null &&
              this.clientTimestamp.equals(other.getClientTimestamp()))) &&
            ((this.clientVendor==null && other.getClientVendor()==null) || 
             (this.clientVendor!=null &&
              this.clientVendor.equals(other.getClientVendor()))) &&
            ((this.clientVersion==null && other.getClientVersion()==null) || 
             (this.clientVersion!=null &&
              this.clientVersion.equals(other.getClientVersion()))) &&
            ((this.kennung==null && other.getKennung()==null) || 
             (this.kennung!=null &&
              this.kennung.equals(other.getKennung()))) &&
            ((this.numberOfObjects==null && other.getNumberOfObjects()==null) || 
             (this.numberOfObjects!=null &&
              this.numberOfObjects.equals(other.getNumberOfObjects()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.startsFromObject==null && other.getStartsFromObject()==null) || 
             (this.startsFromObject!=null &&
              this.startsFromObject.equals(other.getStartsFromObject())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getClientTimestamp() != null) {
            _hashCode += getClientTimestamp().hashCode();
        }
        if (getClientVendor() != null) {
            _hashCode += getClientVendor().hashCode();
        }
        if (getClientVersion() != null) {
            _hashCode += getClientVersion().hashCode();
        }
        if (getKennung() != null) {
            _hashCode += getKennung().hashCode();
        }
        if (getNumberOfObjects() != null) {
            _hashCode += getNumberOfObjects().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getStartsFromObject() != null) {
            _hashCode += getStartsFromObject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReqStandard.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ReqStandard"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientTimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientTimestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientVendor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientVendor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kennung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kennung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfObjects");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfObjects"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startsFromObject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startsFromObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
