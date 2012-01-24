/**
 * Dea_Wasserbehoerden.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Wasserbehoerden  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String behoerden_id;

    private java.lang.Integer behoerden_ver;

    private java.lang.String bezeichnung;

    private java.lang.Integer typ;

    public Dea_Wasserbehoerden() {
    }

    public Dea_Wasserbehoerden(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String behoerden_id,
           java.lang.Integer behoerden_ver,
           java.lang.String bezeichnung,
           java.lang.Integer typ) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.behoerden_id = behoerden_id;
        this.behoerden_ver = behoerden_ver;
        this.bezeichnung = bezeichnung;
        this.typ = typ;
    }


    /**
     * Gets the behoerden_id value for this Dea_Wasserbehoerden.
     * 
     * @return behoerden_id
     */
    public java.lang.String getBehoerden_id() {
        return behoerden_id;
    }


    /**
     * Sets the behoerden_id value for this Dea_Wasserbehoerden.
     * 
     * @param behoerden_id
     */
    public void setBehoerden_id(java.lang.String behoerden_id) {
        this.behoerden_id = behoerden_id;
    }


    /**
     * Gets the behoerden_ver value for this Dea_Wasserbehoerden.
     * 
     * @return behoerden_ver
     */
    public java.lang.Integer getBehoerden_ver() {
        return behoerden_ver;
    }


    /**
     * Sets the behoerden_ver value for this Dea_Wasserbehoerden.
     * 
     * @param behoerden_ver
     */
    public void setBehoerden_ver(java.lang.Integer behoerden_ver) {
        this.behoerden_ver = behoerden_ver;
    }


    /**
     * Gets the bezeichnung value for this Dea_Wasserbehoerden.
     * 
     * @return bezeichnung
     */
    public java.lang.String getBezeichnung() {
        return bezeichnung;
    }


    /**
     * Sets the bezeichnung value for this Dea_Wasserbehoerden.
     * 
     * @param bezeichnung
     */
    public void setBezeichnung(java.lang.String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }


    /**
     * Gets the typ value for this Dea_Wasserbehoerden.
     * 
     * @return typ
     */
    public java.lang.Integer getTyp() {
        return typ;
    }


    /**
     * Sets the typ value for this Dea_Wasserbehoerden.
     * 
     * @param typ
     */
    public void setTyp(java.lang.Integer typ) {
        this.typ = typ;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Wasserbehoerden)) return false;
        Dea_Wasserbehoerden other = (Dea_Wasserbehoerden) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.behoerden_id==null && other.getBehoerden_id()==null) || 
             (this.behoerden_id!=null &&
              this.behoerden_id.equals(other.getBehoerden_id()))) &&
            ((this.behoerden_ver==null && other.getBehoerden_ver()==null) || 
             (this.behoerden_ver!=null &&
              this.behoerden_ver.equals(other.getBehoerden_ver()))) &&
            ((this.bezeichnung==null && other.getBezeichnung()==null) || 
             (this.bezeichnung!=null &&
              this.bezeichnung.equals(other.getBezeichnung()))) &&
            ((this.typ==null && other.getTyp()==null) || 
             (this.typ!=null &&
              this.typ.equals(other.getTyp())));
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
        if (getBehoerden_id() != null) {
            _hashCode += getBehoerden_id().hashCode();
        }
        if (getBehoerden_ver() != null) {
            _hashCode += getBehoerden_ver().hashCode();
        }
        if (getBezeichnung() != null) {
            _hashCode += getBezeichnung().hashCode();
        }
        if (getTyp() != null) {
            _hashCode += getTyp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Wasserbehoerden.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Wasserbehoerden"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("behoerden_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "behoerden_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("behoerden_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "behoerden_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bezeichnung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bezeichnung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "typ"));
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
