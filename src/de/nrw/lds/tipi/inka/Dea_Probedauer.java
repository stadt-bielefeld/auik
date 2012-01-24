/**
 * Dea_Probedauer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Probedauer  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String bez_probe;

    private java.lang.String prob_schluessel;

    private java.lang.Integer prob_ver;

    public Dea_Probedauer() {
    }

    public Dea_Probedauer(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String bez_probe,
           java.lang.String prob_schluessel,
           java.lang.Integer prob_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.bez_probe = bez_probe;
        this.prob_schluessel = prob_schluessel;
        this.prob_ver = prob_ver;
    }


    /**
     * Gets the bez_probe value for this Dea_Probedauer.
     * 
     * @return bez_probe
     */
    public java.lang.String getBez_probe() {
        return bez_probe;
    }


    /**
     * Sets the bez_probe value for this Dea_Probedauer.
     * 
     * @param bez_probe
     */
    public void setBez_probe(java.lang.String bez_probe) {
        this.bez_probe = bez_probe;
    }


    /**
     * Gets the prob_schluessel value for this Dea_Probedauer.
     * 
     * @return prob_schluessel
     */
    public java.lang.String getProb_schluessel() {
        return prob_schluessel;
    }


    /**
     * Sets the prob_schluessel value for this Dea_Probedauer.
     * 
     * @param prob_schluessel
     */
    public void setProb_schluessel(java.lang.String prob_schluessel) {
        this.prob_schluessel = prob_schluessel;
    }


    /**
     * Gets the prob_ver value for this Dea_Probedauer.
     * 
     * @return prob_ver
     */
    public java.lang.Integer getProb_ver() {
        return prob_ver;
    }


    /**
     * Sets the prob_ver value for this Dea_Probedauer.
     * 
     * @param prob_ver
     */
    public void setProb_ver(java.lang.Integer prob_ver) {
        this.prob_ver = prob_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Probedauer)) return false;
        Dea_Probedauer other = (Dea_Probedauer) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.bez_probe==null && other.getBez_probe()==null) || 
             (this.bez_probe!=null &&
              this.bez_probe.equals(other.getBez_probe()))) &&
            ((this.prob_schluessel==null && other.getProb_schluessel()==null) || 
             (this.prob_schluessel!=null &&
              this.prob_schluessel.equals(other.getProb_schluessel()))) &&
            ((this.prob_ver==null && other.getProb_ver()==null) || 
             (this.prob_ver!=null &&
              this.prob_ver.equals(other.getProb_ver())));
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
        if (getBez_probe() != null) {
            _hashCode += getBez_probe().hashCode();
        }
        if (getProb_schluessel() != null) {
            _hashCode += getProb_schluessel().hashCode();
        }
        if (getProb_ver() != null) {
            _hashCode += getProb_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Probedauer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Probedauer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bez_probe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bez_probe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prob_schluessel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prob_schluessel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prob_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prob_ver"));
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
