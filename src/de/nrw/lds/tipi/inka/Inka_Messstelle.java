/**
 * Inka_Messstelle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Messstelle  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String beschr_messpunkt;

    private java.lang.Integer gemeinde_ver;

    private java.lang.String gemeindekennzahl;

    private java.lang.Integer genehmigung_nr;

    private java.lang.Integer genehmigung_ver;

    private java.lang.Integer messstelle_lfd_nr;

    private java.lang.Integer messstelle_typ;

    private java.lang.Integer messstelle_ver;

    private java.lang.Boolean relevant_sum_fracht_jn;

    private java.lang.Integer uebergabestelle_lfd_nr;

    private java.lang.Integer uebergabestelle_ver;

    public Inka_Messstelle() {
    }

    public Inka_Messstelle(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String beschr_messpunkt,
           java.lang.Integer gemeinde_ver,
           java.lang.String gemeindekennzahl,
           java.lang.Integer genehmigung_nr,
           java.lang.Integer genehmigung_ver,
           java.lang.Integer messstelle_lfd_nr,
           java.lang.Integer messstelle_typ,
           java.lang.Integer messstelle_ver,
           java.lang.Boolean relevant_sum_fracht_jn,
           java.lang.Integer uebergabestelle_lfd_nr,
           java.lang.Integer uebergabestelle_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.beschr_messpunkt = beschr_messpunkt;
        this.gemeinde_ver = gemeinde_ver;
        this.gemeindekennzahl = gemeindekennzahl;
        this.genehmigung_nr = genehmigung_nr;
        this.genehmigung_ver = genehmigung_ver;
        this.messstelle_lfd_nr = messstelle_lfd_nr;
        this.messstelle_typ = messstelle_typ;
        this.messstelle_ver = messstelle_ver;
        this.relevant_sum_fracht_jn = relevant_sum_fracht_jn;
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
        this.uebergabestelle_ver = uebergabestelle_ver;
    }


    /**
     * Gets the beschr_messpunkt value for this Inka_Messstelle.
     * 
     * @return beschr_messpunkt
     */
    public java.lang.String getBeschr_messpunkt() {
        return beschr_messpunkt;
    }


    /**
     * Sets the beschr_messpunkt value for this Inka_Messstelle.
     * 
     * @param beschr_messpunkt
     */
    public void setBeschr_messpunkt(java.lang.String beschr_messpunkt) {
        this.beschr_messpunkt = beschr_messpunkt;
    }


    /**
     * Gets the gemeinde_ver value for this Inka_Messstelle.
     * 
     * @return gemeinde_ver
     */
    public java.lang.Integer getGemeinde_ver() {
        return gemeinde_ver;
    }


    /**
     * Sets the gemeinde_ver value for this Inka_Messstelle.
     * 
     * @param gemeinde_ver
     */
    public void setGemeinde_ver(java.lang.Integer gemeinde_ver) {
        this.gemeinde_ver = gemeinde_ver;
    }


    /**
     * Gets the gemeindekennzahl value for this Inka_Messstelle.
     * 
     * @return gemeindekennzahl
     */
    public java.lang.String getGemeindekennzahl() {
        return gemeindekennzahl;
    }


    /**
     * Sets the gemeindekennzahl value for this Inka_Messstelle.
     * 
     * @param gemeindekennzahl
     */
    public void setGemeindekennzahl(java.lang.String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }


    /**
     * Gets the genehmigung_nr value for this Inka_Messstelle.
     * 
     * @return genehmigung_nr
     */
    public java.lang.Integer getGenehmigung_nr() {
        return genehmigung_nr;
    }


    /**
     * Sets the genehmigung_nr value for this Inka_Messstelle.
     * 
     * @param genehmigung_nr
     */
    public void setGenehmigung_nr(java.lang.Integer genehmigung_nr) {
        this.genehmigung_nr = genehmigung_nr;
    }


    /**
     * Gets the genehmigung_ver value for this Inka_Messstelle.
     * 
     * @return genehmigung_ver
     */
    public java.lang.Integer getGenehmigung_ver() {
        return genehmigung_ver;
    }


    /**
     * Sets the genehmigung_ver value for this Inka_Messstelle.
     * 
     * @param genehmigung_ver
     */
    public void setGenehmigung_ver(java.lang.Integer genehmigung_ver) {
        this.genehmigung_ver = genehmigung_ver;
    }


    /**
     * Gets the messstelle_lfd_nr value for this Inka_Messstelle.
     * 
     * @return messstelle_lfd_nr
     */
    public java.lang.Integer getMessstelle_lfd_nr() {
        return messstelle_lfd_nr;
    }


    /**
     * Sets the messstelle_lfd_nr value for this Inka_Messstelle.
     * 
     * @param messstelle_lfd_nr
     */
    public void setMessstelle_lfd_nr(java.lang.Integer messstelle_lfd_nr) {
        this.messstelle_lfd_nr = messstelle_lfd_nr;
    }


    /**
     * Gets the messstelle_typ value for this Inka_Messstelle.
     * 
     * @return messstelle_typ
     */
    public java.lang.Integer getMessstelle_typ() {
        return messstelle_typ;
    }


    /**
     * Sets the messstelle_typ value for this Inka_Messstelle.
     * 
     * @param messstelle_typ
     */
    public void setMessstelle_typ(java.lang.Integer messstelle_typ) {
        this.messstelle_typ = messstelle_typ;
    }


    /**
     * Gets the messstelle_ver value for this Inka_Messstelle.
     * 
     * @return messstelle_ver
     */
    public java.lang.Integer getMessstelle_ver() {
        return messstelle_ver;
    }


    /**
     * Sets the messstelle_ver value for this Inka_Messstelle.
     * 
     * @param messstelle_ver
     */
    public void setMessstelle_ver(java.lang.Integer messstelle_ver) {
        this.messstelle_ver = messstelle_ver;
    }


    /**
     * Gets the relevant_sum_fracht_jn value for this Inka_Messstelle.
     * 
     * @return relevant_sum_fracht_jn
     */
    public java.lang.Boolean getRelevant_sum_fracht_jn() {
        return relevant_sum_fracht_jn;
    }


    /**
     * Sets the relevant_sum_fracht_jn value for this Inka_Messstelle.
     * 
     * @param relevant_sum_fracht_jn
     */
    public void setRelevant_sum_fracht_jn(java.lang.Boolean relevant_sum_fracht_jn) {
        this.relevant_sum_fracht_jn = relevant_sum_fracht_jn;
    }


    /**
     * Gets the uebergabestelle_lfd_nr value for this Inka_Messstelle.
     * 
     * @return uebergabestelle_lfd_nr
     */
    public java.lang.Integer getUebergabestelle_lfd_nr() {
        return uebergabestelle_lfd_nr;
    }


    /**
     * Sets the uebergabestelle_lfd_nr value for this Inka_Messstelle.
     * 
     * @param uebergabestelle_lfd_nr
     */
    public void setUebergabestelle_lfd_nr(java.lang.Integer uebergabestelle_lfd_nr) {
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
    }


    /**
     * Gets the uebergabestelle_ver value for this Inka_Messstelle.
     * 
     * @return uebergabestelle_ver
     */
    public java.lang.Integer getUebergabestelle_ver() {
        return uebergabestelle_ver;
    }


    /**
     * Sets the uebergabestelle_ver value for this Inka_Messstelle.
     * 
     * @param uebergabestelle_ver
     */
    public void setUebergabestelle_ver(java.lang.Integer uebergabestelle_ver) {
        this.uebergabestelle_ver = uebergabestelle_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Messstelle)) return false;
        Inka_Messstelle other = (Inka_Messstelle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.beschr_messpunkt==null && other.getBeschr_messpunkt()==null) || 
             (this.beschr_messpunkt!=null &&
              this.beschr_messpunkt.equals(other.getBeschr_messpunkt()))) &&
            ((this.gemeinde_ver==null && other.getGemeinde_ver()==null) || 
             (this.gemeinde_ver!=null &&
              this.gemeinde_ver.equals(other.getGemeinde_ver()))) &&
            ((this.gemeindekennzahl==null && other.getGemeindekennzahl()==null) || 
             (this.gemeindekennzahl!=null &&
              this.gemeindekennzahl.equals(other.getGemeindekennzahl()))) &&
            ((this.genehmigung_nr==null && other.getGenehmigung_nr()==null) || 
             (this.genehmigung_nr!=null &&
              this.genehmigung_nr.equals(other.getGenehmigung_nr()))) &&
            ((this.genehmigung_ver==null && other.getGenehmigung_ver()==null) || 
             (this.genehmigung_ver!=null &&
              this.genehmigung_ver.equals(other.getGenehmigung_ver()))) &&
            ((this.messstelle_lfd_nr==null && other.getMessstelle_lfd_nr()==null) || 
             (this.messstelle_lfd_nr!=null &&
              this.messstelle_lfd_nr.equals(other.getMessstelle_lfd_nr()))) &&
            ((this.messstelle_typ==null && other.getMessstelle_typ()==null) || 
             (this.messstelle_typ!=null &&
              this.messstelle_typ.equals(other.getMessstelle_typ()))) &&
            ((this.messstelle_ver==null && other.getMessstelle_ver()==null) || 
             (this.messstelle_ver!=null &&
              this.messstelle_ver.equals(other.getMessstelle_ver()))) &&
            ((this.relevant_sum_fracht_jn==null && other.getRelevant_sum_fracht_jn()==null) || 
             (this.relevant_sum_fracht_jn!=null &&
              this.relevant_sum_fracht_jn.equals(other.getRelevant_sum_fracht_jn()))) &&
            ((this.uebergabestelle_lfd_nr==null && other.getUebergabestelle_lfd_nr()==null) || 
             (this.uebergabestelle_lfd_nr!=null &&
              this.uebergabestelle_lfd_nr.equals(other.getUebergabestelle_lfd_nr()))) &&
            ((this.uebergabestelle_ver==null && other.getUebergabestelle_ver()==null) || 
             (this.uebergabestelle_ver!=null &&
              this.uebergabestelle_ver.equals(other.getUebergabestelle_ver())));
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
        if (getBeschr_messpunkt() != null) {
            _hashCode += getBeschr_messpunkt().hashCode();
        }
        if (getGemeinde_ver() != null) {
            _hashCode += getGemeinde_ver().hashCode();
        }
        if (getGemeindekennzahl() != null) {
            _hashCode += getGemeindekennzahl().hashCode();
        }
        if (getGenehmigung_nr() != null) {
            _hashCode += getGenehmigung_nr().hashCode();
        }
        if (getGenehmigung_ver() != null) {
            _hashCode += getGenehmigung_ver().hashCode();
        }
        if (getMessstelle_lfd_nr() != null) {
            _hashCode += getMessstelle_lfd_nr().hashCode();
        }
        if (getMessstelle_typ() != null) {
            _hashCode += getMessstelle_typ().hashCode();
        }
        if (getMessstelle_ver() != null) {
            _hashCode += getMessstelle_ver().hashCode();
        }
        if (getRelevant_sum_fracht_jn() != null) {
            _hashCode += getRelevant_sum_fracht_jn().hashCode();
        }
        if (getUebergabestelle_lfd_nr() != null) {
            _hashCode += getUebergabestelle_lfd_nr().hashCode();
        }
        if (getUebergabestelle_ver() != null) {
            _hashCode += getUebergabestelle_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Messstelle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Messstelle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beschr_messpunkt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beschr_messpunkt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gemeinde_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gemeinde_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gemeindekennzahl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gemeindekennzahl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genehmigung_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "genehmigung_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genehmigung_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "genehmigung_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messstelle_lfd_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messstelle_lfd_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messstelle_typ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messstelle_typ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messstelle_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messstelle_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relevant_sum_fracht_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relevant_sum_fracht_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uebergabestelle_lfd_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uebergabestelle_lfd_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uebergabestelle_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uebergabestelle_ver"));
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
