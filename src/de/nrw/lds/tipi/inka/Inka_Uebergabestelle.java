/**
 * Inka_Uebergabestelle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Uebergabestelle  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer betrieb_nr;

    private java.lang.Integer betrieb_ver;

    private java.lang.Integer gemeinde_ver;

    private java.lang.String gemeindekennzahl;

    private java.lang.Integer genehmigung_nr;

    private java.lang.Integer genehmigung_ver;

    private java.lang.Integer n32;

    private java.lang.Integer kanal_art;

    private java.lang.Integer kartennummer;

    private java.lang.Integer klaeranlage_nr;

    private java.lang.Integer klaeranlage_ver;

    private java.lang.Integer e32;

    private java.lang.Integer tk25_ver;

    private java.lang.Integer uebergabestelle_lfd_nr;

    private java.lang.Integer uebergabestelle_ver;

    public Inka_Uebergabestelle() {
    }

    public Inka_Uebergabestelle(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer betrieb_nr,
           java.lang.Integer betrieb_ver,
           java.lang.Integer gemeinde_ver,
           java.lang.String gemeindekennzahl,
           java.lang.Integer genehmigung_nr,
           java.lang.Integer genehmigung_ver,
           java.lang.Integer n32,
           java.lang.Integer kanal_art,
           java.lang.Integer kartennummer,
           java.lang.Integer klaeranlage_nr,
           java.lang.Integer klaeranlage_ver,
           java.lang.Integer e32,
           java.lang.Integer tk25_ver,
           java.lang.Integer uebergabestelle_lfd_nr,
           java.lang.Integer uebergabestelle_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.betrieb_nr = betrieb_nr;
        this.betrieb_ver = betrieb_ver;
        this.gemeinde_ver = gemeinde_ver;
        this.gemeindekennzahl = gemeindekennzahl;
        this.genehmigung_nr = genehmigung_nr;
        this.genehmigung_ver = genehmigung_ver;
        this.n32 = n32;
        this.kanal_art = kanal_art;
        this.kartennummer = kartennummer;
        this.klaeranlage_nr = klaeranlage_nr;
        this.klaeranlage_ver = klaeranlage_ver;
        this.e32 = e32;
        this.tk25_ver = tk25_ver;
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
        this.uebergabestelle_ver = uebergabestelle_ver;
    }


    /**
     * Gets the betrieb_nr value for this Inka_Uebergabestelle.
     * 
     * @return betrieb_nr
     */
    public java.lang.Integer getBetrieb_nr() {
        return betrieb_nr;
    }


    /**
     * Sets the betrieb_nr value for this Inka_Uebergabestelle.
     * 
     * @param betrieb_nr
     */
    public void setBetrieb_nr(java.lang.Integer betrieb_nr) {
        this.betrieb_nr = betrieb_nr;
    }


    /**
     * Gets the betrieb_ver value for this Inka_Uebergabestelle.
     * 
     * @return betrieb_ver
     */
    public java.lang.Integer getBetrieb_ver() {
        return betrieb_ver;
    }


    /**
     * Sets the betrieb_ver value for this Inka_Uebergabestelle.
     * 
     * @param betrieb_ver
     */
    public void setBetrieb_ver(java.lang.Integer betrieb_ver) {
        this.betrieb_ver = betrieb_ver;
    }


    /**
     * Gets the gemeinde_ver value for this Inka_Uebergabestelle.
     * 
     * @return gemeinde_ver
     */
    public java.lang.Integer getGemeinde_ver() {
        return gemeinde_ver;
    }


    /**
     * Sets the gemeinde_ver value for this Inka_Uebergabestelle.
     * 
     * @param gemeinde_ver
     */
    public void setGemeinde_ver(java.lang.Integer gemeinde_ver) {
        this.gemeinde_ver = gemeinde_ver;
    }


    /**
     * Gets the gemeindekennzahl value for this Inka_Uebergabestelle.
     * 
     * @return gemeindekennzahl
     */
    public java.lang.String getGemeindekennzahl() {
        return gemeindekennzahl;
    }


    /**
     * Sets the gemeindekennzahl value for this Inka_Uebergabestelle.
     * 
     * @param gemeindekennzahl
     */
    public void setGemeindekennzahl(java.lang.String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }


    /**
     * Gets the genehmigung_nr value for this Inka_Uebergabestelle.
     * 
     * @return genehmigung_nr
     */
    public java.lang.Integer getGenehmigung_nr() {
        return genehmigung_nr;
    }


    /**
     * Sets the genehmigung_nr value for this Inka_Uebergabestelle.
     * 
     * @param genehmigung_nr
     */
    public void setGenehmigung_nr(java.lang.Integer genehmigung_nr) {
        this.genehmigung_nr = genehmigung_nr;
    }


    /**
     * Gets the genehmigung_ver value for this Inka_Uebergabestelle.
     * 
     * @return genehmigung_ver
     */
    public java.lang.Integer getGenehmigung_ver() {
        return genehmigung_ver;
    }


    /**
     * Sets the genehmigung_ver value for this Inka_Uebergabestelle.
     * 
     * @param genehmigung_ver
     */
    public void setGenehmigung_ver(java.lang.Integer genehmigung_ver) {
        this.genehmigung_ver = genehmigung_ver;
    }


    /**
     * Gets the n32 value for this Inka_Uebergabestelle.
     * 
     * @return n32
     */
    public java.lang.Integer getN32() {
        return n32;
    }


    /**
     * Sets the n32 value for this Inka_Uebergabestelle.
     * 
     * @param hochwert
     */
    public void setN32(java.lang.Integer n32) {
        this.n32 = n32;
    }


    /**
     * Gets the kanal_art value for this Inka_Uebergabestelle.
     * 
     * @return kanal_art
     */
    public java.lang.Integer getKanal_art() {
        return kanal_art;
    }


    /**
     * Sets the kanal_art value for this Inka_Uebergabestelle.
     * 
     * @param kanal_art
     */
    public void setKanal_art(java.lang.Integer kanal_art) {
        this.kanal_art = kanal_art;
    }


    /**
     * Gets the kartennummer value for this Inka_Uebergabestelle.
     * 
     * @return kartennummer
     */
    public java.lang.Integer getKartennummer() {
        return kartennummer;
    }


    /**
     * Sets the kartennummer value for this Inka_Uebergabestelle.
     * 
     * @param kartennummer
     */
    public void setKartennummer(java.lang.Integer kartennummer) {
        this.kartennummer = kartennummer;
    }


    /**
     * Gets the klaeranlage_nr value for this Inka_Uebergabestelle.
     * 
     * @return klaeranlage_nr
     */
    public java.lang.Integer getKlaeranlage_nr() {
        return klaeranlage_nr;
    }


    /**
     * Sets the klaeranlage_nr value for this Inka_Uebergabestelle.
     * 
     * @param klaeranlage_nr
     */
    public void setKlaeranlage_nr(java.lang.Integer klaeranlage_nr) {
        this.klaeranlage_nr = klaeranlage_nr;
    }


    /**
     * Gets the klaeranlage_ver value for this Inka_Uebergabestelle.
     * 
     * @return klaeranlage_ver
     */
    public java.lang.Integer getKlaeranlage_ver() {
        return klaeranlage_ver;
    }


    /**
     * Sets the klaeranlage_ver value for this Inka_Uebergabestelle.
     * 
     * @param klaeranlage_ver
     */
    public void setKlaeranlage_ver(java.lang.Integer klaeranlage_ver) {
        this.klaeranlage_ver = klaeranlage_ver;
    }


    /**
     * Gets the e32 value for this Inka_Uebergabestelle.
     * 
     * @return e32
     */
    public java.lang.Integer getE32() {
        return e32;
    }


    /**
     * Sets the e32 value for this Inka_Uebergabestelle.
     * 
     * @param e32
     */
    public void setE32(java.lang.Integer e32) {
        this.e32 = e32;
    }


    /**
     * Gets the tk25_ver value for this Inka_Uebergabestelle.
     * 
     * @return tk25_ver
     */
    public java.lang.Integer getTk25_ver() {
        return tk25_ver;
    }


    /**
     * Sets the tk25_ver value for this Inka_Uebergabestelle.
     * 
     * @param tk25_ver
     */
    public void setTk25_ver(java.lang.Integer tk25_ver) {
        this.tk25_ver = tk25_ver;
    }


    /**
     * Gets the uebergabestelle_lfd_nr value for this Inka_Uebergabestelle.
     * 
     * @return uebergabestelle_lfd_nr
     */
    public java.lang.Integer getUebergabestelle_lfd_nr() {
        return uebergabestelle_lfd_nr;
    }


    /**
     * Sets the uebergabestelle_lfd_nr value for this Inka_Uebergabestelle.
     * 
     * @param uebergabestelle_lfd_nr
     */
    public void setUebergabestelle_lfd_nr(java.lang.Integer uebergabestelle_lfd_nr) {
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
    }


    /**
     * Gets the uebergabestelle_ver value for this Inka_Uebergabestelle.
     * 
     * @return uebergabestelle_ver
     */
    public java.lang.Integer getUebergabestelle_ver() {
        return uebergabestelle_ver;
    }


    /**
     * Sets the uebergabestelle_ver value for this Inka_Uebergabestelle.
     * 
     * @param uebergabestelle_ver
     */
    public void setUebergabestelle_ver(java.lang.Integer uebergabestelle_ver) {
        this.uebergabestelle_ver = uebergabestelle_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Uebergabestelle)) return false;
        Inka_Uebergabestelle other = (Inka_Uebergabestelle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.betrieb_nr==null && other.getBetrieb_nr()==null) || 
             (this.betrieb_nr!=null &&
              this.betrieb_nr.equals(other.getBetrieb_nr()))) &&
            ((this.betrieb_ver==null && other.getBetrieb_ver()==null) || 
             (this.betrieb_ver!=null &&
              this.betrieb_ver.equals(other.getBetrieb_ver()))) &&
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
            ((this.n32==null && other.getN32()==null) || 
             (this.n32!=null &&
              this.n32.equals(other.getN32()))) &&
            ((this.kanal_art==null && other.getKanal_art()==null) || 
             (this.kanal_art!=null &&
              this.kanal_art.equals(other.getKanal_art()))) &&
            ((this.kartennummer==null && other.getKartennummer()==null) || 
             (this.kartennummer!=null &&
              this.kartennummer.equals(other.getKartennummer()))) &&
            ((this.klaeranlage_nr==null && other.getKlaeranlage_nr()==null) || 
             (this.klaeranlage_nr!=null &&
              this.klaeranlage_nr.equals(other.getKlaeranlage_nr()))) &&
            ((this.klaeranlage_ver==null && other.getKlaeranlage_ver()==null) || 
             (this.klaeranlage_ver!=null &&
              this.klaeranlage_ver.equals(other.getKlaeranlage_ver()))) &&
            ((this.e32==null && other.getE32()==null) || 
             (this.e32!=null &&
              this.e32.equals(other.getE32()))) &&
            ((this.tk25_ver==null && other.getTk25_ver()==null) || 
             (this.tk25_ver!=null &&
              this.tk25_ver.equals(other.getTk25_ver()))) &&
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
        if (getBetrieb_nr() != null) {
            _hashCode += getBetrieb_nr().hashCode();
        }
        if (getBetrieb_ver() != null) {
            _hashCode += getBetrieb_ver().hashCode();
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
        if (getN32() != null) {
            _hashCode += getN32().hashCode();
        }
        if (getKanal_art() != null) {
            _hashCode += getKanal_art().hashCode();
        }
        if (getKartennummer() != null) {
            _hashCode += getKartennummer().hashCode();
        }
        if (getKlaeranlage_nr() != null) {
            _hashCode += getKlaeranlage_nr().hashCode();
        }
        if (getKlaeranlage_ver() != null) {
            _hashCode += getKlaeranlage_ver().hashCode();
        }
        if (getE32() != null) {
            _hashCode += getE32().hashCode();
        }
        if (getTk25_ver() != null) {
            _hashCode += getTk25_ver().hashCode();
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
        new org.apache.axis.description.TypeDesc(Inka_Uebergabestelle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Uebergabestelle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("hochwert");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hochwert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kanal_art");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kanal_art"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kartennummer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kartennummer"));
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
        elemField.setFieldName("tk25_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tk25_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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