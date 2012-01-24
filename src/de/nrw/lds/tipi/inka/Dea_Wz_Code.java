/**
 * Dea_Wz_Code.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Wz_Code  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String abschnitt_id;

    private java.lang.String abteilung_id;

    private java.lang.String bezeichnung;

    private java.lang.Integer ebene;

    private java.lang.String grp_id;

    private java.lang.String kla_id;

    private java.lang.String u_abschnitt_id;

    private java.lang.String u_kla_id;

    private java.lang.String wz_code;

    private java.lang.Integer wz_code_ver;

    public Dea_Wz_Code() {
    }

    public Dea_Wz_Code(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String abschnitt_id,
           java.lang.String abteilung_id,
           java.lang.String bezeichnung,
           java.lang.Integer ebene,
           java.lang.String grp_id,
           java.lang.String kla_id,
           java.lang.String u_abschnitt_id,
           java.lang.String u_kla_id,
           java.lang.String wz_code,
           java.lang.Integer wz_code_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.abschnitt_id = abschnitt_id;
        this.abteilung_id = abteilung_id;
        this.bezeichnung = bezeichnung;
        this.ebene = ebene;
        this.grp_id = grp_id;
        this.kla_id = kla_id;
        this.u_abschnitt_id = u_abschnitt_id;
        this.u_kla_id = u_kla_id;
        this.wz_code = wz_code;
        this.wz_code_ver = wz_code_ver;
    }


    /**
     * Gets the abschnitt_id value for this Dea_Wz_Code.
     * 
     * @return abschnitt_id
     */
    public java.lang.String getAbschnitt_id() {
        return abschnitt_id;
    }


    /**
     * Sets the abschnitt_id value for this Dea_Wz_Code.
     * 
     * @param abschnitt_id
     */
    public void setAbschnitt_id(java.lang.String abschnitt_id) {
        this.abschnitt_id = abschnitt_id;
    }


    /**
     * Gets the abteilung_id value for this Dea_Wz_Code.
     * 
     * @return abteilung_id
     */
    public java.lang.String getAbteilung_id() {
        return abteilung_id;
    }


    /**
     * Sets the abteilung_id value for this Dea_Wz_Code.
     * 
     * @param abteilung_id
     */
    public void setAbteilung_id(java.lang.String abteilung_id) {
        this.abteilung_id = abteilung_id;
    }


    /**
     * Gets the bezeichnung value for this Dea_Wz_Code.
     * 
     * @return bezeichnung
     */
    public java.lang.String getBezeichnung() {
        return bezeichnung;
    }


    /**
     * Sets the bezeichnung value for this Dea_Wz_Code.
     * 
     * @param bezeichnung
     */
    public void setBezeichnung(java.lang.String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }


    /**
     * Gets the ebene value for this Dea_Wz_Code.
     * 
     * @return ebene
     */
    public java.lang.Integer getEbene() {
        return ebene;
    }


    /**
     * Sets the ebene value for this Dea_Wz_Code.
     * 
     * @param ebene
     */
    public void setEbene(java.lang.Integer ebene) {
        this.ebene = ebene;
    }


    /**
     * Gets the grp_id value for this Dea_Wz_Code.
     * 
     * @return grp_id
     */
    public java.lang.String getGrp_id() {
        return grp_id;
    }


    /**
     * Sets the grp_id value for this Dea_Wz_Code.
     * 
     * @param grp_id
     */
    public void setGrp_id(java.lang.String grp_id) {
        this.grp_id = grp_id;
    }


    /**
     * Gets the kla_id value for this Dea_Wz_Code.
     * 
     * @return kla_id
     */
    public java.lang.String getKla_id() {
        return kla_id;
    }


    /**
     * Sets the kla_id value for this Dea_Wz_Code.
     * 
     * @param kla_id
     */
    public void setKla_id(java.lang.String kla_id) {
        this.kla_id = kla_id;
    }


    /**
     * Gets the u_abschnitt_id value for this Dea_Wz_Code.
     * 
     * @return u_abschnitt_id
     */
    public java.lang.String getU_abschnitt_id() {
        return u_abschnitt_id;
    }


    /**
     * Sets the u_abschnitt_id value for this Dea_Wz_Code.
     * 
     * @param u_abschnitt_id
     */
    public void setU_abschnitt_id(java.lang.String u_abschnitt_id) {
        this.u_abschnitt_id = u_abschnitt_id;
    }


    /**
     * Gets the u_kla_id value for this Dea_Wz_Code.
     * 
     * @return u_kla_id
     */
    public java.lang.String getU_kla_id() {
        return u_kla_id;
    }


    /**
     * Sets the u_kla_id value for this Dea_Wz_Code.
     * 
     * @param u_kla_id
     */
    public void setU_kla_id(java.lang.String u_kla_id) {
        this.u_kla_id = u_kla_id;
    }


    /**
     * Gets the wz_code value for this Dea_Wz_Code.
     * 
     * @return wz_code
     */
    public java.lang.String getWz_code() {
        return wz_code;
    }


    /**
     * Sets the wz_code value for this Dea_Wz_Code.
     * 
     * @param wz_code
     */
    public void setWz_code(java.lang.String wz_code) {
        this.wz_code = wz_code;
    }


    /**
     * Gets the wz_code_ver value for this Dea_Wz_Code.
     * 
     * @return wz_code_ver
     */
    public java.lang.Integer getWz_code_ver() {
        return wz_code_ver;
    }


    /**
     * Sets the wz_code_ver value for this Dea_Wz_Code.
     * 
     * @param wz_code_ver
     */
    public void setWz_code_ver(java.lang.Integer wz_code_ver) {
        this.wz_code_ver = wz_code_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Wz_Code)) return false;
        Dea_Wz_Code other = (Dea_Wz_Code) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.abschnitt_id==null && other.getAbschnitt_id()==null) || 
             (this.abschnitt_id!=null &&
              this.abschnitt_id.equals(other.getAbschnitt_id()))) &&
            ((this.abteilung_id==null && other.getAbteilung_id()==null) || 
             (this.abteilung_id!=null &&
              this.abteilung_id.equals(other.getAbteilung_id()))) &&
            ((this.bezeichnung==null && other.getBezeichnung()==null) || 
             (this.bezeichnung!=null &&
              this.bezeichnung.equals(other.getBezeichnung()))) &&
            ((this.ebene==null && other.getEbene()==null) || 
             (this.ebene!=null &&
              this.ebene.equals(other.getEbene()))) &&
            ((this.grp_id==null && other.getGrp_id()==null) || 
             (this.grp_id!=null &&
              this.grp_id.equals(other.getGrp_id()))) &&
            ((this.kla_id==null && other.getKla_id()==null) || 
             (this.kla_id!=null &&
              this.kla_id.equals(other.getKla_id()))) &&
            ((this.u_abschnitt_id==null && other.getU_abschnitt_id()==null) || 
             (this.u_abschnitt_id!=null &&
              this.u_abschnitt_id.equals(other.getU_abschnitt_id()))) &&
            ((this.u_kla_id==null && other.getU_kla_id()==null) || 
             (this.u_kla_id!=null &&
              this.u_kla_id.equals(other.getU_kla_id()))) &&
            ((this.wz_code==null && other.getWz_code()==null) || 
             (this.wz_code!=null &&
              this.wz_code.equals(other.getWz_code()))) &&
            ((this.wz_code_ver==null && other.getWz_code_ver()==null) || 
             (this.wz_code_ver!=null &&
              this.wz_code_ver.equals(other.getWz_code_ver())));
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
        if (getAbschnitt_id() != null) {
            _hashCode += getAbschnitt_id().hashCode();
        }
        if (getAbteilung_id() != null) {
            _hashCode += getAbteilung_id().hashCode();
        }
        if (getBezeichnung() != null) {
            _hashCode += getBezeichnung().hashCode();
        }
        if (getEbene() != null) {
            _hashCode += getEbene().hashCode();
        }
        if (getGrp_id() != null) {
            _hashCode += getGrp_id().hashCode();
        }
        if (getKla_id() != null) {
            _hashCode += getKla_id().hashCode();
        }
        if (getU_abschnitt_id() != null) {
            _hashCode += getU_abschnitt_id().hashCode();
        }
        if (getU_kla_id() != null) {
            _hashCode += getU_kla_id().hashCode();
        }
        if (getWz_code() != null) {
            _hashCode += getWz_code().hashCode();
        }
        if (getWz_code_ver() != null) {
            _hashCode += getWz_code_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Wz_Code.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Wz_Code"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abschnitt_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abschnitt_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abteilung_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abteilung_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bezeichnung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bezeichnung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ebene");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ebene"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grp_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grp_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kla_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kla_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_abschnitt_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_abschnitt_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_kla_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_kla_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wz_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "wz_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wz_code_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "wz_code_ver"));
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
