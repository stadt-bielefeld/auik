/**
 * ResError.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.general;

public class ResError  implements java.io.Serializable {
    private java.lang.String errorCategory;

    private java.lang.String errorCause;

    private java.lang.String errorDescription;

    private java.lang.Long errorID;

    public ResError() {
    }

    public ResError(
           java.lang.String errorCategory,
           java.lang.String errorCause,
           java.lang.String errorDescription,
           java.lang.Long errorID) {
           this.errorCategory = errorCategory;
           this.errorCause = errorCause;
           this.errorDescription = errorDescription;
           this.errorID = errorID;
    }


    /**
     * Gets the errorCategory value for this ResError.
     * 
     * @return errorCategory
     */
    public java.lang.String getErrorCategory() {
        return errorCategory;
    }


    /**
     * Sets the errorCategory value for this ResError.
     * 
     * @param errorCategory
     */
    public void setErrorCategory(java.lang.String errorCategory) {
        this.errorCategory = errorCategory;
    }


    /**
     * Gets the errorCause value for this ResError.
     * 
     * @return errorCause
     */
    public java.lang.String getErrorCause() {
        return errorCause;
    }


    /**
     * Sets the errorCause value for this ResError.
     * 
     * @param errorCause
     */
    public void setErrorCause(java.lang.String errorCause) {
        this.errorCause = errorCause;
    }


    /**
     * Gets the errorDescription value for this ResError.
     * 
     * @return errorDescription
     */
    public java.lang.String getErrorDescription() {
        return errorDescription;
    }


    /**
     * Sets the errorDescription value for this ResError.
     * 
     * @param errorDescription
     */
    public void setErrorDescription(java.lang.String errorDescription) {
        this.errorDescription = errorDescription;
    }


    /**
     * Gets the errorID value for this ResError.
     * 
     * @return errorID
     */
    public java.lang.Long getErrorID() {
        return errorID;
    }


    /**
     * Sets the errorID value for this ResError.
     * 
     * @param errorID
     */
    public void setErrorID(java.lang.Long errorID) {
        this.errorID = errorID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResError)) return false;
        ResError other = (ResError) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorCategory==null && other.getErrorCategory()==null) || 
             (this.errorCategory!=null &&
              this.errorCategory.equals(other.getErrorCategory()))) &&
            ((this.errorCause==null && other.getErrorCause()==null) || 
             (this.errorCause!=null &&
              this.errorCause.equals(other.getErrorCause()))) &&
            ((this.errorDescription==null && other.getErrorDescription()==null) || 
             (this.errorDescription!=null &&
              this.errorDescription.equals(other.getErrorDescription()))) &&
            ((this.errorID==null && other.getErrorID()==null) || 
             (this.errorID!=null &&
              this.errorID.equals(other.getErrorID())));
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
        if (getErrorCategory() != null) {
            _hashCode += getErrorCategory().hashCode();
        }
        if (getErrorCause() != null) {
            _hashCode += getErrorCause().hashCode();
        }
        if (getErrorDescription() != null) {
            _hashCode += getErrorDescription().hashCode();
        }
        if (getErrorID() != null) {
            _hashCode += getErrorID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResError.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResError"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCause");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorCause"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
