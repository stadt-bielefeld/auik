/**
 * Inka_Anfallst_Stoffe.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Anfallst_Stoffe  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer anfallst_stoffe_ver;

    private java.lang.Integer anfallstelle_nr;

    private java.lang.Integer anfallstelle_ver;

    private java.lang.Integer stoff_nr;

    private java.lang.Integer stoff_ver;

    public Inka_Anfallst_Stoffe() {
    }

    public Inka_Anfallst_Stoffe(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer anfallst_stoffe_ver,
           java.lang.Integer anfallstelle_nr,
           java.lang.Integer anfallstelle_ver,
           java.lang.Integer stoff_nr,
           java.lang.Integer stoff_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.anfallst_stoffe_ver = anfallst_stoffe_ver;
        this.anfallstelle_nr = anfallstelle_nr;
        this.anfallstelle_ver = anfallstelle_ver;
        this.stoff_nr = stoff_nr;
        this.stoff_ver = stoff_ver;
    }


    /**
     * Gets the anfallst_stoffe_ver value for this Inka_Anfallst_Stoffe.
     * 
     * @return anfallst_stoffe_ver
     */
    public java.lang.Integer getAnfallst_stoffe_ver() {
        return anfallst_stoffe_ver;
    }


    /**
     * Sets the anfallst_stoffe_ver value for this Inka_Anfallst_Stoffe.
     * 
     * @param anfallst_stoffe_ver
     */
    public void setAnfallst_stoffe_ver(java.lang.Integer anfallst_stoffe_ver) {
        this.anfallst_stoffe_ver = anfallst_stoffe_ver;
    }


    /**
     * Gets the anfallstelle_nr value for this Inka_Anfallst_Stoffe.
     * 
     * @return anfallstelle_nr
     */
    public java.lang.Integer getAnfallstelle_nr() {
        return anfallstelle_nr;
    }


    /**
     * Sets the anfallstelle_nr value for this Inka_Anfallst_Stoffe.
     * 
     * @param anfallstelle_nr
     */
    public void setAnfallstelle_nr(java.lang.Integer anfallstelle_nr) {
        this.anfallstelle_nr = anfallstelle_nr;
    }


    /**
     * Gets the anfallstelle_ver value for this Inka_Anfallst_Stoffe.
     * 
     * @return anfallstelle_ver
     */
    public java.lang.Integer getAnfallstelle_ver() {
        return anfallstelle_ver;
    }


    /**
     * Sets the anfallstelle_ver value for this Inka_Anfallst_Stoffe.
     * 
     * @param anfallstelle_ver
     */
    public void setAnfallstelle_ver(java.lang.Integer anfallstelle_ver) {
        this.anfallstelle_ver = anfallstelle_ver;
    }


    /**
     * Gets the stoff_nr value for this Inka_Anfallst_Stoffe.
     * 
     * @return stoff_nr
     */
    public java.lang.Integer getStoff_nr() {
        return stoff_nr;
    }


    /**
     * Sets the stoff_nr value for this Inka_Anfallst_Stoffe.
     * 
     * @param stoff_nr
     */
    public void setStoff_nr(java.lang.Integer stoff_nr) {
        this.stoff_nr = stoff_nr;
    }


    /**
     * Gets the stoff_ver value for this Inka_Anfallst_Stoffe.
     * 
     * @return stoff_ver
     */
    public java.lang.Integer getStoff_ver() {
        return stoff_ver;
    }


    /**
     * Sets the stoff_ver value for this Inka_Anfallst_Stoffe.
     * 
     * @param stoff_ver
     */
    public void setStoff_ver(java.lang.Integer stoff_ver) {
        this.stoff_ver = stoff_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Anfallst_Stoffe)) return false;
        Inka_Anfallst_Stoffe other = (Inka_Anfallst_Stoffe) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.anfallst_stoffe_ver==null && other.getAnfallst_stoffe_ver()==null) || 
             (this.anfallst_stoffe_ver!=null &&
              this.anfallst_stoffe_ver.equals(other.getAnfallst_stoffe_ver()))) &&
            ((this.anfallstelle_nr==null && other.getAnfallstelle_nr()==null) || 
             (this.anfallstelle_nr!=null &&
              this.anfallstelle_nr.equals(other.getAnfallstelle_nr()))) &&
            ((this.anfallstelle_ver==null && other.getAnfallstelle_ver()==null) || 
             (this.anfallstelle_ver!=null &&
              this.anfallstelle_ver.equals(other.getAnfallstelle_ver()))) &&
            ((this.stoff_nr==null && other.getStoff_nr()==null) || 
             (this.stoff_nr!=null &&
              this.stoff_nr.equals(other.getStoff_nr()))) &&
            ((this.stoff_ver==null && other.getStoff_ver()==null) || 
             (this.stoff_ver!=null &&
              this.stoff_ver.equals(other.getStoff_ver())));
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
        if (getAnfallst_stoffe_ver() != null) {
            _hashCode += getAnfallst_stoffe_ver().hashCode();
        }
        if (getAnfallstelle_nr() != null) {
            _hashCode += getAnfallstelle_nr().hashCode();
        }
        if (getAnfallstelle_ver() != null) {
            _hashCode += getAnfallstelle_ver().hashCode();
        }
        if (getStoff_nr() != null) {
            _hashCode += getStoff_nr().hashCode();
        }
        if (getStoff_ver() != null) {
            _hashCode += getStoff_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Anfallst_Stoffe.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Stoffe"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anfallst_stoffe_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anfallst_stoffe_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anfallstelle_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anfallstelle_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anfallstelle_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anfallstelle_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stoff_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stoff_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stoff_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stoff_ver"));
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
