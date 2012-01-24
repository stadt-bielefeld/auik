/**
 * HistoryObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.general;

public abstract class HistoryObject  implements java.io.Serializable {
    private java.util.Calendar aenderungs_datum;

    private java.util.Calendar erfassungs_datum;

    private java.util.Calendar gueltig_bis;

    private java.util.Calendar gueltig_von;

    private java.lang.Boolean ist_aktuell_jn;

    public HistoryObject() {
    }

    public HistoryObject(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn) {
           this.aenderungs_datum = aenderungs_datum;
           this.erfassungs_datum = erfassungs_datum;
           this.gueltig_bis = gueltig_bis;
           this.gueltig_von = gueltig_von;
           this.ist_aktuell_jn = ist_aktuell_jn;
    }


    /**
     * Gets the aenderungs_datum value for this HistoryObject.
     * 
     * @return aenderungs_datum
     */
    public java.util.Calendar getAenderungs_datum() {
        return aenderungs_datum;
    }


    /**
     * Sets the aenderungs_datum value for this HistoryObject.
     * 
     * @param aenderungs_datum
     */
    public void setAenderungs_datum(java.util.Calendar aenderungs_datum) {
        this.aenderungs_datum = aenderungs_datum;
    }


    /**
     * Gets the erfassungs_datum value for this HistoryObject.
     * 
     * @return erfassungs_datum
     */
    public java.util.Calendar getErfassungs_datum() {
        return erfassungs_datum;
    }


    /**
     * Sets the erfassungs_datum value for this HistoryObject.
     * 
     * @param erfassungs_datum
     */
    public void setErfassungs_datum(java.util.Calendar erfassungs_datum) {
        this.erfassungs_datum = erfassungs_datum;
    }


    /**
     * Gets the gueltig_bis value for this HistoryObject.
     * 
     * @return gueltig_bis
     */
    public java.util.Calendar getGueltig_bis() {
        return gueltig_bis;
    }


    /**
     * Sets the gueltig_bis value for this HistoryObject.
     * 
     * @param gueltig_bis
     */
    public void setGueltig_bis(java.util.Calendar gueltig_bis) {
        this.gueltig_bis = gueltig_bis;
    }


    /**
     * Gets the gueltig_von value for this HistoryObject.
     * 
     * @return gueltig_von
     */
    public java.util.Calendar getGueltig_von() {
        return gueltig_von;
    }


    /**
     * Sets the gueltig_von value for this HistoryObject.
     * 
     * @param gueltig_von
     */
    public void setGueltig_von(java.util.Calendar gueltig_von) {
        this.gueltig_von = gueltig_von;
    }


    /**
     * Gets the ist_aktuell_jn value for this HistoryObject.
     * 
     * @return ist_aktuell_jn
     */
    public java.lang.Boolean getIst_aktuell_jn() {
        return ist_aktuell_jn;
    }


    /**
     * Sets the ist_aktuell_jn value for this HistoryObject.
     * 
     * @param ist_aktuell_jn
     */
    public void setIst_aktuell_jn(java.lang.Boolean ist_aktuell_jn) {
        this.ist_aktuell_jn = ist_aktuell_jn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HistoryObject)) return false;
        HistoryObject other = (HistoryObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.aenderungs_datum==null && other.getAenderungs_datum()==null) || 
             (this.aenderungs_datum!=null &&
              this.aenderungs_datum.equals(other.getAenderungs_datum()))) &&
            ((this.erfassungs_datum==null && other.getErfassungs_datum()==null) || 
             (this.erfassungs_datum!=null &&
              this.erfassungs_datum.equals(other.getErfassungs_datum()))) &&
            ((this.gueltig_bis==null && other.getGueltig_bis()==null) || 
             (this.gueltig_bis!=null &&
              this.gueltig_bis.equals(other.getGueltig_bis()))) &&
            ((this.gueltig_von==null && other.getGueltig_von()==null) || 
             (this.gueltig_von!=null &&
              this.gueltig_von.equals(other.getGueltig_von()))) &&
            ((this.ist_aktuell_jn==null && other.getIst_aktuell_jn()==null) || 
             (this.ist_aktuell_jn!=null &&
              this.ist_aktuell_jn.equals(other.getIst_aktuell_jn())));
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
        if (getAenderungs_datum() != null) {
            _hashCode += getAenderungs_datum().hashCode();
        }
        if (getErfassungs_datum() != null) {
            _hashCode += getErfassungs_datum().hashCode();
        }
        if (getGueltig_bis() != null) {
            _hashCode += getGueltig_bis().hashCode();
        }
        if (getGueltig_von() != null) {
            _hashCode += getGueltig_von().hashCode();
        }
        if (getIst_aktuell_jn() != null) {
            _hashCode += getIst_aktuell_jn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HistoryObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://general.tipi.lds.nrw.de", "HistoryObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aenderungs_datum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aenderungs_datum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("erfassungs_datum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "erfassungs_datum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gueltig_bis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gueltig_bis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gueltig_von");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gueltig_von"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ist_aktuell_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ist_aktuell_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
