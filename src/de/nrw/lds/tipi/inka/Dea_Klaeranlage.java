/**
 * Dea_Klaeranlage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Klaeranlage  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String anl_kurzbez;

    private java.lang.String anl_name;

    private java.lang.Integer anlagentyp_opt;

    private java.lang.Integer behoerden_ver;

    private java.lang.Integer gemeinde_ver;

    private java.lang.String gemeindekennzahl;

    private java.lang.Integer hochwert;

    private java.lang.Integer klaeranlage_nr;

    private java.lang.Integer klaeranlage_ver;

    private java.lang.Integer rechtswert;

    private java.util.Calendar stilllegungs_datum;

    private java.lang.Integer stua_bezirk;

    private java.lang.Integer stua_ver;

    private java.lang.Integer tk25_nr;

    private java.lang.Integer tk25_ver;

    private java.lang.String wasserbehoerde;

    public Dea_Klaeranlage() {
    }

    public Dea_Klaeranlage(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String anl_kurzbez,
           java.lang.String anl_name,
           java.lang.Integer anlagentyp_opt,
           java.lang.Integer behoerden_ver,
           java.lang.Integer gemeinde_ver,
           java.lang.String gemeindekennzahl,
           java.lang.Integer hochwert,
           java.lang.Integer klaeranlage_nr,
           java.lang.Integer klaeranlage_ver,
           java.lang.Integer rechtswert,
           java.util.Calendar stilllegungs_datum,
           java.lang.Integer stua_bezirk,
           java.lang.Integer stua_ver,
           java.lang.Integer tk25_nr,
           java.lang.Integer tk25_ver,
           java.lang.String wasserbehoerde) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.anl_kurzbez = anl_kurzbez;
        this.anl_name = anl_name;
        this.anlagentyp_opt = anlagentyp_opt;
        this.behoerden_ver = behoerden_ver;
        this.gemeinde_ver = gemeinde_ver;
        this.gemeindekennzahl = gemeindekennzahl;
        this.hochwert = hochwert;
        this.klaeranlage_nr = klaeranlage_nr;
        this.klaeranlage_ver = klaeranlage_ver;
        this.rechtswert = rechtswert;
        this.stilllegungs_datum = stilllegungs_datum;
        this.stua_bezirk = stua_bezirk;
        this.stua_ver = stua_ver;
        this.tk25_nr = tk25_nr;
        this.tk25_ver = tk25_ver;
        this.wasserbehoerde = wasserbehoerde;
    }


    /**
     * Gets the anl_kurzbez value for this Dea_Klaeranlage.
     * 
     * @return anl_kurzbez
     */
    public java.lang.String getAnl_kurzbez() {
        return anl_kurzbez;
    }


    /**
     * Sets the anl_kurzbez value for this Dea_Klaeranlage.
     * 
     * @param anl_kurzbez
     */
    public void setAnl_kurzbez(java.lang.String anl_kurzbez) {
        this.anl_kurzbez = anl_kurzbez;
    }


    /**
     * Gets the anl_name value for this Dea_Klaeranlage.
     * 
     * @return anl_name
     */
    public java.lang.String getAnl_name() {
        return anl_name;
    }


    /**
     * Sets the anl_name value for this Dea_Klaeranlage.
     * 
     * @param anl_name
     */
    public void setAnl_name(java.lang.String anl_name) {
        this.anl_name = anl_name;
    }


    /**
     * Gets the anlagentyp_opt value for this Dea_Klaeranlage.
     * 
     * @return anlagentyp_opt
     */
    public java.lang.Integer getAnlagentyp_opt() {
        return anlagentyp_opt;
    }


    /**
     * Sets the anlagentyp_opt value for this Dea_Klaeranlage.
     * 
     * @param anlagentyp_opt
     */
    public void setAnlagentyp_opt(java.lang.Integer anlagentyp_opt) {
        this.anlagentyp_opt = anlagentyp_opt;
    }


    /**
     * Gets the behoerden_ver value for this Dea_Klaeranlage.
     * 
     * @return behoerden_ver
     */
    public java.lang.Integer getBehoerden_ver() {
        return behoerden_ver;
    }


    /**
     * Sets the behoerden_ver value for this Dea_Klaeranlage.
     * 
     * @param behoerden_ver
     */
    public void setBehoerden_ver(java.lang.Integer behoerden_ver) {
        this.behoerden_ver = behoerden_ver;
    }


    /**
     * Gets the gemeinde_ver value for this Dea_Klaeranlage.
     * 
     * @return gemeinde_ver
     */
    public java.lang.Integer getGemeinde_ver() {
        return gemeinde_ver;
    }


    /**
     * Sets the gemeinde_ver value for this Dea_Klaeranlage.
     * 
     * @param gemeinde_ver
     */
    public void setGemeinde_ver(java.lang.Integer gemeinde_ver) {
        this.gemeinde_ver = gemeinde_ver;
    }


    /**
     * Gets the gemeindekennzahl value for this Dea_Klaeranlage.
     * 
     * @return gemeindekennzahl
     */
    public java.lang.String getGemeindekennzahl() {
        return gemeindekennzahl;
    }


    /**
     * Sets the gemeindekennzahl value for this Dea_Klaeranlage.
     * 
     * @param gemeindekennzahl
     */
    public void setGemeindekennzahl(java.lang.String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }


    /**
     * Gets the hochwert value for this Dea_Klaeranlage.
     * 
     * @return hochwert
     */
    public java.lang.Integer getHochwert() {
        return hochwert;
    }


    /**
     * Sets the hochwert value for this Dea_Klaeranlage.
     * 
     * @param hochwert
     */
    public void setHochwert(java.lang.Integer hochwert) {
        this.hochwert = hochwert;
    }


    /**
     * Gets the klaeranlage_nr value for this Dea_Klaeranlage.
     * 
     * @return klaeranlage_nr
     */
    public java.lang.Integer getKlaeranlage_nr() {
        return klaeranlage_nr;
    }


    /**
     * Sets the klaeranlage_nr value for this Dea_Klaeranlage.
     * 
     * @param klaeranlage_nr
     */
    public void setKlaeranlage_nr(java.lang.Integer klaeranlage_nr) {
        this.klaeranlage_nr = klaeranlage_nr;
    }


    /**
     * Gets the klaeranlage_ver value for this Dea_Klaeranlage.
     * 
     * @return klaeranlage_ver
     */
    public java.lang.Integer getKlaeranlage_ver() {
        return klaeranlage_ver;
    }


    /**
     * Sets the klaeranlage_ver value for this Dea_Klaeranlage.
     * 
     * @param klaeranlage_ver
     */
    public void setKlaeranlage_ver(java.lang.Integer klaeranlage_ver) {
        this.klaeranlage_ver = klaeranlage_ver;
    }


    /**
     * Gets the rechtswert value for this Dea_Klaeranlage.
     * 
     * @return rechtswert
     */
    public java.lang.Integer getRechtswert() {
        return rechtswert;
    }


    /**
     * Sets the rechtswert value for this Dea_Klaeranlage.
     * 
     * @param rechtswert
     */
    public void setRechtswert(java.lang.Integer rechtswert) {
        this.rechtswert = rechtswert;
    }


    /**
     * Gets the stilllegungs_datum value for this Dea_Klaeranlage.
     * 
     * @return stilllegungs_datum
     */
    public java.util.Calendar getStilllegungs_datum() {
        return stilllegungs_datum;
    }


    /**
     * Sets the stilllegungs_datum value for this Dea_Klaeranlage.
     * 
     * @param stilllegungs_datum
     */
    public void setStilllegungs_datum(java.util.Calendar stilllegungs_datum) {
        this.stilllegungs_datum = stilllegungs_datum;
    }


    /**
     * Gets the stua_bezirk value for this Dea_Klaeranlage.
     * 
     * @return stua_bezirk
     */
    public java.lang.Integer getStua_bezirk() {
        return stua_bezirk;
    }


    /**
     * Sets the stua_bezirk value for this Dea_Klaeranlage.
     * 
     * @param stua_bezirk
     */
    public void setStua_bezirk(java.lang.Integer stua_bezirk) {
        this.stua_bezirk = stua_bezirk;
    }


    /**
     * Gets the stua_ver value for this Dea_Klaeranlage.
     * 
     * @return stua_ver
     */
    public java.lang.Integer getStua_ver() {
        return stua_ver;
    }


    /**
     * Sets the stua_ver value for this Dea_Klaeranlage.
     * 
     * @param stua_ver
     */
    public void setStua_ver(java.lang.Integer stua_ver) {
        this.stua_ver = stua_ver;
    }


    /**
     * Gets the tk25_nr value for this Dea_Klaeranlage.
     * 
     * @return tk25_nr
     */
    public java.lang.Integer getTk25_nr() {
        return tk25_nr;
    }


    /**
     * Sets the tk25_nr value for this Dea_Klaeranlage.
     * 
     * @param tk25_nr
     */
    public void setTk25_nr(java.lang.Integer tk25_nr) {
        this.tk25_nr = tk25_nr;
    }


    /**
     * Gets the tk25_ver value for this Dea_Klaeranlage.
     * 
     * @return tk25_ver
     */
    public java.lang.Integer getTk25_ver() {
        return tk25_ver;
    }


    /**
     * Sets the tk25_ver value for this Dea_Klaeranlage.
     * 
     * @param tk25_ver
     */
    public void setTk25_ver(java.lang.Integer tk25_ver) {
        this.tk25_ver = tk25_ver;
    }


    /**
     * Gets the wasserbehoerde value for this Dea_Klaeranlage.
     * 
     * @return wasserbehoerde
     */
    public java.lang.String getWasserbehoerde() {
        return wasserbehoerde;
    }


    /**
     * Sets the wasserbehoerde value for this Dea_Klaeranlage.
     * 
     * @param wasserbehoerde
     */
    public void setWasserbehoerde(java.lang.String wasserbehoerde) {
        this.wasserbehoerde = wasserbehoerde;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Klaeranlage)) return false;
        Dea_Klaeranlage other = (Dea_Klaeranlage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.anl_kurzbez==null && other.getAnl_kurzbez()==null) || 
             (this.anl_kurzbez!=null &&
              this.anl_kurzbez.equals(other.getAnl_kurzbez()))) &&
            ((this.anl_name==null && other.getAnl_name()==null) || 
             (this.anl_name!=null &&
              this.anl_name.equals(other.getAnl_name()))) &&
            ((this.anlagentyp_opt==null && other.getAnlagentyp_opt()==null) || 
             (this.anlagentyp_opt!=null &&
              this.anlagentyp_opt.equals(other.getAnlagentyp_opt()))) &&
            ((this.behoerden_ver==null && other.getBehoerden_ver()==null) || 
             (this.behoerden_ver!=null &&
              this.behoerden_ver.equals(other.getBehoerden_ver()))) &&
            ((this.gemeinde_ver==null && other.getGemeinde_ver()==null) || 
             (this.gemeinde_ver!=null &&
              this.gemeinde_ver.equals(other.getGemeinde_ver()))) &&
            ((this.gemeindekennzahl==null && other.getGemeindekennzahl()==null) || 
             (this.gemeindekennzahl!=null &&
              this.gemeindekennzahl.equals(other.getGemeindekennzahl()))) &&
            ((this.hochwert==null && other.getHochwert()==null) || 
             (this.hochwert!=null &&
              this.hochwert.equals(other.getHochwert()))) &&
            ((this.klaeranlage_nr==null && other.getKlaeranlage_nr()==null) || 
             (this.klaeranlage_nr!=null &&
              this.klaeranlage_nr.equals(other.getKlaeranlage_nr()))) &&
            ((this.klaeranlage_ver==null && other.getKlaeranlage_ver()==null) || 
             (this.klaeranlage_ver!=null &&
              this.klaeranlage_ver.equals(other.getKlaeranlage_ver()))) &&
            ((this.rechtswert==null && other.getRechtswert()==null) || 
             (this.rechtswert!=null &&
              this.rechtswert.equals(other.getRechtswert()))) &&
            ((this.stilllegungs_datum==null && other.getStilllegungs_datum()==null) || 
             (this.stilllegungs_datum!=null &&
              this.stilllegungs_datum.equals(other.getStilllegungs_datum()))) &&
            ((this.stua_bezirk==null && other.getStua_bezirk()==null) || 
             (this.stua_bezirk!=null &&
              this.stua_bezirk.equals(other.getStua_bezirk()))) &&
            ((this.stua_ver==null && other.getStua_ver()==null) || 
             (this.stua_ver!=null &&
              this.stua_ver.equals(other.getStua_ver()))) &&
            ((this.tk25_nr==null && other.getTk25_nr()==null) || 
             (this.tk25_nr!=null &&
              this.tk25_nr.equals(other.getTk25_nr()))) &&
            ((this.tk25_ver==null && other.getTk25_ver()==null) || 
             (this.tk25_ver!=null &&
              this.tk25_ver.equals(other.getTk25_ver()))) &&
            ((this.wasserbehoerde==null && other.getWasserbehoerde()==null) || 
             (this.wasserbehoerde!=null &&
              this.wasserbehoerde.equals(other.getWasserbehoerde())));
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
        if (getAnl_kurzbez() != null) {
            _hashCode += getAnl_kurzbez().hashCode();
        }
        if (getAnl_name() != null) {
            _hashCode += getAnl_name().hashCode();
        }
        if (getAnlagentyp_opt() != null) {
            _hashCode += getAnlagentyp_opt().hashCode();
        }
        if (getBehoerden_ver() != null) {
            _hashCode += getBehoerden_ver().hashCode();
        }
        if (getGemeinde_ver() != null) {
            _hashCode += getGemeinde_ver().hashCode();
        }
        if (getGemeindekennzahl() != null) {
            _hashCode += getGemeindekennzahl().hashCode();
        }
        if (getHochwert() != null) {
            _hashCode += getHochwert().hashCode();
        }
        if (getKlaeranlage_nr() != null) {
            _hashCode += getKlaeranlage_nr().hashCode();
        }
        if (getKlaeranlage_ver() != null) {
            _hashCode += getKlaeranlage_ver().hashCode();
        }
        if (getRechtswert() != null) {
            _hashCode += getRechtswert().hashCode();
        }
        if (getStilllegungs_datum() != null) {
            _hashCode += getStilllegungs_datum().hashCode();
        }
        if (getStua_bezirk() != null) {
            _hashCode += getStua_bezirk().hashCode();
        }
        if (getStua_ver() != null) {
            _hashCode += getStua_ver().hashCode();
        }
        if (getTk25_nr() != null) {
            _hashCode += getTk25_nr().hashCode();
        }
        if (getTk25_ver() != null) {
            _hashCode += getTk25_ver().hashCode();
        }
        if (getWasserbehoerde() != null) {
            _hashCode += getWasserbehoerde().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Klaeranlage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Klaeranlage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anl_kurzbez");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anl_kurzbez"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anl_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anl_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anlagentyp_opt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anlagentyp_opt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("behoerden_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "behoerden_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("hochwert");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hochwert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("klaeranlage_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "klaeranlage_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("klaeranlage_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "klaeranlage_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rechtswert");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rechtswert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stilllegungs_datum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stilllegungs_datum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stua_bezirk");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stua_bezirk"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stua_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stua_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tk25_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tk25_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tk25_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tk25_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wasserbehoerde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "wasserbehoerde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
