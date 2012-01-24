/**
 * Dea_Tk25.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Tk25  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String kartenbezeichnung;

    private java.lang.Integer kartennummer;

    private java.lang.Integer tk25_ver;

    public Dea_Tk25() {
    }

    public Dea_Tk25(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String kartenbezeichnung,
           java.lang.Integer kartennummer,
           java.lang.Integer tk25_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.kartenbezeichnung = kartenbezeichnung;
        this.kartennummer = kartennummer;
        this.tk25_ver = tk25_ver;
    }


    /**
     * Gets the kartenbezeichnung value for this Dea_Tk25.
     * 
     * @return kartenbezeichnung
     */
    public java.lang.String getKartenbezeichnung() {
        return kartenbezeichnung;
    }


    /**
     * Sets the kartenbezeichnung value for this Dea_Tk25.
     * 
     * @param kartenbezeichnung
     */
    public void setKartenbezeichnung(java.lang.String kartenbezeichnung) {
        this.kartenbezeichnung = kartenbezeichnung;
    }


    /**
     * Gets the kartennummer value for this Dea_Tk25.
     * 
     * @return kartennummer
     */
    public java.lang.Integer getKartennummer() {
        return kartennummer;
    }


    /**
     * Sets the kartennummer value for this Dea_Tk25.
     * 
     * @param kartennummer
     */
    public void setKartennummer(java.lang.Integer kartennummer) {
        this.kartennummer = kartennummer;
    }


    /**
     * Gets the tk25_ver value for this Dea_Tk25.
     * 
     * @return tk25_ver
     */
    public java.lang.Integer getTk25_ver() {
        return tk25_ver;
    }


    /**
     * Sets the tk25_ver value for this Dea_Tk25.
     * 
     * @param tk25_ver
     */
    public void setTk25_ver(java.lang.Integer tk25_ver) {
        this.tk25_ver = tk25_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Tk25)) return false;
        Dea_Tk25 other = (Dea_Tk25) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.kartenbezeichnung==null && other.getKartenbezeichnung()==null) || 
             (this.kartenbezeichnung!=null &&
              this.kartenbezeichnung.equals(other.getKartenbezeichnung()))) &&
            ((this.kartennummer==null && other.getKartennummer()==null) || 
             (this.kartennummer!=null &&
              this.kartennummer.equals(other.getKartennummer()))) &&
            ((this.tk25_ver==null && other.getTk25_ver()==null) || 
             (this.tk25_ver!=null &&
              this.tk25_ver.equals(other.getTk25_ver())));
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
        if (getKartenbezeichnung() != null) {
            _hashCode += getKartenbezeichnung().hashCode();
        }
        if (getKartennummer() != null) {
            _hashCode += getKartennummer().hashCode();
        }
        if (getTk25_ver() != null) {
            _hashCode += getTk25_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Tk25.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Tk25"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kartenbezeichnung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kartenbezeichnung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kartennummer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kartennummer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tk25_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tk25_ver"));
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
