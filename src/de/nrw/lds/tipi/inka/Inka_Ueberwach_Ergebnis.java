/**
 * Inka_Ueberwach_Ergebnis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Ueberwach_Ergebnis  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer ein_masseinheit_nr;

    private java.lang.Integer ein_masseinheit_ver;

    private java.lang.Float messergebnis;

    private java.lang.String messergebnis_text;

    private java.lang.Integer parameter_nr;

    private java.lang.Integer parameter_ver;

    private java.lang.Integer probenahme_nr;

    private java.lang.Integer probenahme_ver;

    private java.lang.Integer ueberwach_ergebnis_ver;

    public Inka_Ueberwach_Ergebnis() {
    }

    public Inka_Ueberwach_Ergebnis(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer ein_masseinheit_nr,
           java.lang.Integer ein_masseinheit_ver,
           java.lang.Float messergebnis,
           java.lang.String messergebnis_text,
           java.lang.Integer parameter_nr,
           java.lang.Integer parameter_ver,
           java.lang.Integer probenahme_nr,
           java.lang.Integer probenahme_ver,
           java.lang.Integer ueberwach_ergebnis_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.ein_masseinheit_nr = ein_masseinheit_nr;
        this.ein_masseinheit_ver = ein_masseinheit_ver;
        this.messergebnis = messergebnis;
        this.messergebnis_text = messergebnis_text;
        this.parameter_nr = parameter_nr;
        this.parameter_ver = parameter_ver;
        this.probenahme_nr = probenahme_nr;
        this.probenahme_ver = probenahme_ver;
        this.ueberwach_ergebnis_ver = ueberwach_ergebnis_ver;
    }


    /**
     * Gets the ein_masseinheit_nr value for this Inka_Ueberwach_Ergebnis.
     * 
     * @return ein_masseinheit_nr
     */
    public java.lang.Integer getEin_masseinheit_nr() {
        return ein_masseinheit_nr;
    }


    /**
     * Sets the ein_masseinheit_nr value for this Inka_Ueberwach_Ergebnis.
     * 
     * @param ein_masseinheit_nr
     */
    public void setEin_masseinheit_nr(java.lang.Integer ein_masseinheit_nr) {
        this.ein_masseinheit_nr = ein_masseinheit_nr;
    }


    /**
     * Gets the ein_masseinheit_ver value for this Inka_Ueberwach_Ergebnis.
     * 
     * @return ein_masseinheit_ver
     */
    public java.lang.Integer getEin_masseinheit_ver() {
        return ein_masseinheit_ver;
    }


    /**
     * Sets the ein_masseinheit_ver value for this Inka_Ueberwach_Ergebnis.
     * 
     * @param ein_masseinheit_ver
     */
    public void setEin_masseinheit_ver(java.lang.Integer ein_masseinheit_ver) {
        this.ein_masseinheit_ver = ein_masseinheit_ver;
    }


    /**
     * Gets the messergebnis value for this Inka_Ueberwach_Ergebnis.
     * 
     * @return messergebnis
     */
    public java.lang.Float getMessergebnis() {
        return messergebnis;
    }


    /**
     * Sets the messergebnis value for this Inka_Ueberwach_Ergebnis.
     * 
     * @param messergebnis
     */
    public void setMessergebnis(java.lang.Float messergebnis) {
        this.messergebnis = messergebnis;
    }


    /**
     * Gets the messergebnis_text value for this Inka_Ueberwach_Ergebnis.
     * 
     * @return messergebnis_text
     */
    public java.lang.String getMessergebnis_text() {
        return messergebnis_text;
    }


    /**
     * Sets the messergebnis_text value for this Inka_Ueberwach_Ergebnis.
     * 
     * @param messergebnis_text
     */
    public void setMessergebnis_text(java.lang.String messergebnis_text) {
        this.messergebnis_text = messergebnis_text;
    }


    /**
     * Gets the parameter_nr value for this Inka_Ueberwach_Ergebnis.
     * 
     * @return parameter_nr
     */
    public java.lang.Integer getParameter_nr() {
        return parameter_nr;
    }


    /**
     * Sets the parameter_nr value for this Inka_Ueberwach_Ergebnis.
     * 
     * @param parameter_nr
     */
    public void setParameter_nr(java.lang.Integer parameter_nr) {
        this.parameter_nr = parameter_nr;
    }


    /**
     * Gets the parameter_ver value for this Inka_Ueberwach_Ergebnis.
     * 
     * @return parameter_ver
     */
    public java.lang.Integer getParameter_ver() {
        return parameter_ver;
    }


    /**
     * Sets the parameter_ver value for this Inka_Ueberwach_Ergebnis.
     * 
     * @param parameter_ver
     */
    public void setParameter_ver(java.lang.Integer parameter_ver) {
        this.parameter_ver = parameter_ver;
    }


    /**
     * Gets the probenahme_nr value for this Inka_Ueberwach_Ergebnis.
     * 
     * @return probenahme_nr
     */
    public java.lang.Integer getProbenahme_nr() {
        return probenahme_nr;
    }


    /**
     * Sets the probenahme_nr value for this Inka_Ueberwach_Ergebnis.
     * 
     * @param probenahme_nr
     */
    public void setProbenahme_nr(java.lang.Integer probenahme_nr) {
        this.probenahme_nr = probenahme_nr;
    }


    /**
     * Gets the probenahme_ver value for this Inka_Ueberwach_Ergebnis.
     * 
     * @return probenahme_ver
     */
    public java.lang.Integer getProbenahme_ver() {
        return probenahme_ver;
    }


    /**
     * Sets the probenahme_ver value for this Inka_Ueberwach_Ergebnis.
     * 
     * @param probenahme_ver
     */
    public void setProbenahme_ver(java.lang.Integer probenahme_ver) {
        this.probenahme_ver = probenahme_ver;
    }


    /**
     * Gets the ueberwach_ergebnis_ver value for this Inka_Ueberwach_Ergebnis.
     * 
     * @return ueberwach_ergebnis_ver
     */
    public java.lang.Integer getUeberwach_ergebnis_ver() {
        return ueberwach_ergebnis_ver;
    }


    /**
     * Sets the ueberwach_ergebnis_ver value for this Inka_Ueberwach_Ergebnis.
     * 
     * @param ueberwach_ergebnis_ver
     */
    public void setUeberwach_ergebnis_ver(java.lang.Integer ueberwach_ergebnis_ver) {
        this.ueberwach_ergebnis_ver = ueberwach_ergebnis_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Ueberwach_Ergebnis)) return false;
        Inka_Ueberwach_Ergebnis other = (Inka_Ueberwach_Ergebnis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ein_masseinheit_nr==null && other.getEin_masseinheit_nr()==null) || 
             (this.ein_masseinheit_nr!=null &&
              this.ein_masseinheit_nr.equals(other.getEin_masseinheit_nr()))) &&
            ((this.ein_masseinheit_ver==null && other.getEin_masseinheit_ver()==null) || 
             (this.ein_masseinheit_ver!=null &&
              this.ein_masseinheit_ver.equals(other.getEin_masseinheit_ver()))) &&
            ((this.messergebnis==null && other.getMessergebnis()==null) || 
             (this.messergebnis!=null &&
              this.messergebnis.equals(other.getMessergebnis()))) &&
            ((this.messergebnis_text==null && other.getMessergebnis_text()==null) || 
             (this.messergebnis_text!=null &&
              this.messergebnis_text.equals(other.getMessergebnis_text()))) &&
            ((this.parameter_nr==null && other.getParameter_nr()==null) || 
             (this.parameter_nr!=null &&
              this.parameter_nr.equals(other.getParameter_nr()))) &&
            ((this.parameter_ver==null && other.getParameter_ver()==null) || 
             (this.parameter_ver!=null &&
              this.parameter_ver.equals(other.getParameter_ver()))) &&
            ((this.probenahme_nr==null && other.getProbenahme_nr()==null) || 
             (this.probenahme_nr!=null &&
              this.probenahme_nr.equals(other.getProbenahme_nr()))) &&
            ((this.probenahme_ver==null && other.getProbenahme_ver()==null) || 
             (this.probenahme_ver!=null &&
              this.probenahme_ver.equals(other.getProbenahme_ver()))) &&
            ((this.ueberwach_ergebnis_ver==null && other.getUeberwach_ergebnis_ver()==null) || 
             (this.ueberwach_ergebnis_ver!=null &&
              this.ueberwach_ergebnis_ver.equals(other.getUeberwach_ergebnis_ver())));
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
        if (getEin_masseinheit_nr() != null) {
            _hashCode += getEin_masseinheit_nr().hashCode();
        }
        if (getEin_masseinheit_ver() != null) {
            _hashCode += getEin_masseinheit_ver().hashCode();
        }
        if (getMessergebnis() != null) {
            _hashCode += getMessergebnis().hashCode();
        }
        if (getMessergebnis_text() != null) {
            _hashCode += getMessergebnis_text().hashCode();
        }
        if (getParameter_nr() != null) {
            _hashCode += getParameter_nr().hashCode();
        }
        if (getParameter_ver() != null) {
            _hashCode += getParameter_ver().hashCode();
        }
        if (getProbenahme_nr() != null) {
            _hashCode += getProbenahme_nr().hashCode();
        }
        if (getProbenahme_ver() != null) {
            _hashCode += getProbenahme_ver().hashCode();
        }
        if (getUeberwach_ergebnis_ver() != null) {
            _hashCode += getUeberwach_ergebnis_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Ueberwach_Ergebnis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Ueberwach_Ergebnis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ein_masseinheit_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ein_masseinheit_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ein_masseinheit_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ein_masseinheit_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messergebnis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messergebnis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messergebnis_text");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messergebnis_text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameter_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parameter_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameter_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parameter_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("probenahme_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "probenahme_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("probenahme_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "probenahme_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ueberwach_ergebnis_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ueberwach_ergebnis_ver"));
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
