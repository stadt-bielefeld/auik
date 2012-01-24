/**
 * Inka_Genehmigung.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Genehmigung  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.util.Calendar befristet_bis;

    private java.lang.Boolean befristet_jn;

    private java.lang.String behoerden_id;

    private java.lang.Integer behoerden_ver;

    private java.lang.Integer betrieb_nr;

    private java.lang.Integer betrieb_ver;

    private java.util.Calendar genehmigung_datum;

    private java.lang.Integer genehmigung_nr;

    private java.lang.Integer genehmigung_ver;

    public Inka_Genehmigung() {
    }

    public Inka_Genehmigung(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.util.Calendar befristet_bis,
           java.lang.Boolean befristet_jn,
           java.lang.String behoerden_id,
           java.lang.Integer behoerden_ver,
           java.lang.Integer betrieb_nr,
           java.lang.Integer betrieb_ver,
           java.util.Calendar genehmigung_datum,
           java.lang.Integer genehmigung_nr,
           java.lang.Integer genehmigung_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.befristet_bis = befristet_bis;
        this.befristet_jn = befristet_jn;
        this.behoerden_id = behoerden_id;
        this.behoerden_ver = behoerden_ver;
        this.betrieb_nr = betrieb_nr;
        this.betrieb_ver = betrieb_ver;
        this.genehmigung_datum = genehmigung_datum;
        this.genehmigung_nr = genehmigung_nr;
        this.genehmigung_ver = genehmigung_ver;
    }


    /**
     * Gets the befristet_bis value for this Inka_Genehmigung.
     * 
     * @return befristet_bis
     */
    public java.util.Calendar getBefristet_bis() {
        return befristet_bis;
    }


    /**
     * Sets the befristet_bis value for this Inka_Genehmigung.
     * 
     * @param befristet_bis
     */
    public void setBefristet_bis(java.util.Calendar befristet_bis) {
        this.befristet_bis = befristet_bis;
    }


    /**
     * Gets the befristet_jn value for this Inka_Genehmigung.
     * 
     * @return befristet_jn
     */
    public java.lang.Boolean getBefristet_jn() {
        return befristet_jn;
    }


    /**
     * Sets the befristet_jn value for this Inka_Genehmigung.
     * 
     * @param befristet_jn
     */
    public void setBefristet_jn(java.lang.Boolean befristet_jn) {
        this.befristet_jn = befristet_jn;
    }


    /**
     * Gets the behoerden_id value for this Inka_Genehmigung.
     * 
     * @return behoerden_id
     */
    public java.lang.String getBehoerden_id() {
        return behoerden_id;
    }


    /**
     * Sets the behoerden_id value for this Inka_Genehmigung.
     * 
     * @param behoerden_id
     */
    public void setBehoerden_id(java.lang.String behoerden_id) {
        this.behoerden_id = behoerden_id;
    }


    /**
     * Gets the behoerden_ver value for this Inka_Genehmigung.
     * 
     * @return behoerden_ver
     */
    public java.lang.Integer getBehoerden_ver() {
        return behoerden_ver;
    }


    /**
     * Sets the behoerden_ver value for this Inka_Genehmigung.
     * 
     * @param behoerden_ver
     */
    public void setBehoerden_ver(java.lang.Integer behoerden_ver) {
        this.behoerden_ver = behoerden_ver;
    }


    /**
     * Gets the betrieb_nr value for this Inka_Genehmigung.
     * 
     * @return betrieb_nr
     */
    public java.lang.Integer getBetrieb_nr() {
        return betrieb_nr;
    }


    /**
     * Sets the betrieb_nr value for this Inka_Genehmigung.
     * 
     * @param betrieb_nr
     */
    public void setBetrieb_nr(java.lang.Integer betrieb_nr) {
        this.betrieb_nr = betrieb_nr;
    }


    /**
     * Gets the betrieb_ver value for this Inka_Genehmigung.
     * 
     * @return betrieb_ver
     */
    public java.lang.Integer getBetrieb_ver() {
        return betrieb_ver;
    }


    /**
     * Sets the betrieb_ver value for this Inka_Genehmigung.
     * 
     * @param betrieb_ver
     */
    public void setBetrieb_ver(java.lang.Integer betrieb_ver) {
        this.betrieb_ver = betrieb_ver;
    }


    /**
     * Gets the genehmigung_datum value for this Inka_Genehmigung.
     * 
     * @return genehmigung_datum
     */
    public java.util.Calendar getGenehmigung_datum() {
        return genehmigung_datum;
    }


    /**
     * Sets the genehmigung_datum value for this Inka_Genehmigung.
     * 
     * @param genehmigung_datum
     */
    public void setGenehmigung_datum(java.util.Calendar genehmigung_datum) {
        this.genehmigung_datum = genehmigung_datum;
    }


    /**
     * Gets the genehmigung_nr value for this Inka_Genehmigung.
     * 
     * @return genehmigung_nr
     */
    public java.lang.Integer getGenehmigung_nr() {
        return genehmigung_nr;
    }


    /**
     * Sets the genehmigung_nr value for this Inka_Genehmigung.
     * 
     * @param genehmigung_nr
     */
    public void setGenehmigung_nr(java.lang.Integer genehmigung_nr) {
        this.genehmigung_nr = genehmigung_nr;
    }


    /**
     * Gets the genehmigung_ver value for this Inka_Genehmigung.
     * 
     * @return genehmigung_ver
     */
    public java.lang.Integer getGenehmigung_ver() {
        return genehmigung_ver;
    }


    /**
     * Sets the genehmigung_ver value for this Inka_Genehmigung.
     * 
     * @param genehmigung_ver
     */
    public void setGenehmigung_ver(java.lang.Integer genehmigung_ver) {
        this.genehmigung_ver = genehmigung_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Genehmigung)) return false;
        Inka_Genehmigung other = (Inka_Genehmigung) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.befristet_bis==null && other.getBefristet_bis()==null) || 
             (this.befristet_bis!=null &&
              this.befristet_bis.equals(other.getBefristet_bis()))) &&
            ((this.befristet_jn==null && other.getBefristet_jn()==null) || 
             (this.befristet_jn!=null &&
              this.befristet_jn.equals(other.getBefristet_jn()))) &&
            ((this.behoerden_id==null && other.getBehoerden_id()==null) || 
             (this.behoerden_id!=null &&
              this.behoerden_id.equals(other.getBehoerden_id()))) &&
            ((this.behoerden_ver==null && other.getBehoerden_ver()==null) || 
             (this.behoerden_ver!=null &&
              this.behoerden_ver.equals(other.getBehoerden_ver()))) &&
            ((this.betrieb_nr==null && other.getBetrieb_nr()==null) || 
             (this.betrieb_nr!=null &&
              this.betrieb_nr.equals(other.getBetrieb_nr()))) &&
            ((this.betrieb_ver==null && other.getBetrieb_ver()==null) || 
             (this.betrieb_ver!=null &&
              this.betrieb_ver.equals(other.getBetrieb_ver()))) &&
            ((this.genehmigung_datum==null && other.getGenehmigung_datum()==null) || 
             (this.genehmigung_datum!=null &&
              this.genehmigung_datum.equals(other.getGenehmigung_datum()))) &&
            ((this.genehmigung_nr==null && other.getGenehmigung_nr()==null) || 
             (this.genehmigung_nr!=null &&
              this.genehmigung_nr.equals(other.getGenehmigung_nr()))) &&
            ((this.genehmigung_ver==null && other.getGenehmigung_ver()==null) || 
             (this.genehmigung_ver!=null &&
              this.genehmigung_ver.equals(other.getGenehmigung_ver())));
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
        if (getBefristet_bis() != null) {
            _hashCode += getBefristet_bis().hashCode();
        }
        if (getBefristet_jn() != null) {
            _hashCode += getBefristet_jn().hashCode();
        }
        if (getBehoerden_id() != null) {
            _hashCode += getBehoerden_id().hashCode();
        }
        if (getBehoerden_ver() != null) {
            _hashCode += getBehoerden_ver().hashCode();
        }
        if (getBetrieb_nr() != null) {
            _hashCode += getBetrieb_nr().hashCode();
        }
        if (getBetrieb_ver() != null) {
            _hashCode += getBetrieb_ver().hashCode();
        }
        if (getGenehmigung_datum() != null) {
            _hashCode += getGenehmigung_datum().hashCode();
        }
        if (getGenehmigung_nr() != null) {
            _hashCode += getGenehmigung_nr().hashCode();
        }
        if (getGenehmigung_ver() != null) {
            _hashCode += getGenehmigung_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Genehmigung.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Genehmigung"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("befristet_bis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "befristet_bis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("befristet_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "befristet_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("betrieb_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "betrieb_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("betrieb_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "betrieb_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genehmigung_datum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "genehmigung_datum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
