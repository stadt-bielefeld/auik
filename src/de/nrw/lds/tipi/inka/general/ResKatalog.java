/**
 * ResKatalog.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.general;

public class ResKatalog  extends de.nrw.lds.tipi.inka.general.ResStandard  implements java.io.Serializable {
    private java.lang.Integer numberOfAllObjects;

    private java.lang.Integer numberOfObjects;

    private java.lang.Integer startsFromObject;

    public ResKatalog() {
    }

    public ResKatalog(
           de.nrw.lds.tipi.inka.general.ResError errorObject,
           java.util.Calendar serverTimestamp,
           java.lang.Integer numberOfAllObjects,
           java.lang.Integer numberOfObjects,
           java.lang.Integer startsFromObject) {
        super(
            errorObject,
            serverTimestamp);
        this.numberOfAllObjects = numberOfAllObjects;
        this.numberOfObjects = numberOfObjects;
        this.startsFromObject = startsFromObject;
    }


    /**
     * Gets the numberOfAllObjects value for this ResKatalog.
     * 
     * @return numberOfAllObjects
     */
    public java.lang.Integer getNumberOfAllObjects() {
        return numberOfAllObjects;
    }


    /**
     * Sets the numberOfAllObjects value for this ResKatalog.
     * 
     * @param numberOfAllObjects
     */
    public void setNumberOfAllObjects(java.lang.Integer numberOfAllObjects) {
        this.numberOfAllObjects = numberOfAllObjects;
    }


    /**
     * Gets the numberOfObjects value for this ResKatalog.
     * 
     * @return numberOfObjects
     */
    public java.lang.Integer getNumberOfObjects() {
        return numberOfObjects;
    }


    /**
     * Sets the numberOfObjects value for this ResKatalog.
     * 
     * @param numberOfObjects
     */
    public void setNumberOfObjects(java.lang.Integer numberOfObjects) {
        this.numberOfObjects = numberOfObjects;
    }


    /**
     * Gets the startsFromObject value for this ResKatalog.
     * 
     * @return startsFromObject
     */
    public java.lang.Integer getStartsFromObject() {
        return startsFromObject;
    }


    /**
     * Sets the startsFromObject value for this ResKatalog.
     * 
     * @param startsFromObject
     */
    public void setStartsFromObject(java.lang.Integer startsFromObject) {
        this.startsFromObject = startsFromObject;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResKatalog)) return false;
        ResKatalog other = (ResKatalog) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.numberOfAllObjects==null && other.getNumberOfAllObjects()==null) || 
             (this.numberOfAllObjects!=null &&
              this.numberOfAllObjects.equals(other.getNumberOfAllObjects()))) &&
            ((this.numberOfObjects==null && other.getNumberOfObjects()==null) || 
             (this.numberOfObjects!=null &&
              this.numberOfObjects.equals(other.getNumberOfObjects()))) &&
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
        int _hashCode = super.hashCode();
        if (getNumberOfAllObjects() != null) {
            _hashCode += getNumberOfAllObjects().hashCode();
        }
        if (getNumberOfObjects() != null) {
            _hashCode += getNumberOfObjects().hashCode();
        }
        if (getStartsFromObject() != null) {
            _hashCode += getStartsFromObject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResKatalog.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResKatalog"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfAllObjects");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfAllObjects"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfObjects");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfObjects"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
