/**
 * Inka_Anfallstelle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Anfallstelle  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer anfallstelle_nr;

    private java.lang.Integer anfallstelle_ver;

    private java.lang.String anh_id;

    private java.lang.Integer anh_ver;

    private java.lang.String beschreibung;

    private java.lang.Integer betriebseinrichtung_nr;

    private java.lang.Integer betriebseinrichtung_ver;

    private java.lang.Boolean chargenbetrieb_jn;

    private java.lang.Boolean dauerbetrieb_jn;

    private java.lang.Integer gemeinde_ver;

    private java.lang.String gemeindekennzahl;

    private java.lang.Integer genehmigung_nr;

    private java.lang.Integer genehmigung_ver;

    private java.lang.Float max_vol_tag;

    private java.lang.Integer uebergabestelle_lfd_nr;

    private java.lang.Integer uebergabestelle_ver;

    private java.lang.Integer vol_jahr;

    public Inka_Anfallstelle() {
    }

    public Inka_Anfallstelle(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer anfallstelle_nr,
           java.lang.Integer anfallstelle_ver,
           java.lang.String anh_id,
           java.lang.Integer anh_ver,
           java.lang.String beschreibung,
           java.lang.Integer betriebseinrichtung_nr,
           java.lang.Integer betriebseinrichtung_ver,
           java.lang.Boolean chargenbetrieb_jn,
           java.lang.Boolean dauerbetrieb_jn,
           java.lang.Integer gemeinde_ver,
           java.lang.String gemeindekennzahl,
           java.lang.Integer genehmigung_nr,
           java.lang.Integer genehmigung_ver,
           java.lang.Float max_vol_tag,
           java.lang.Integer uebergabestelle_lfd_nr,
           java.lang.Integer uebergabestelle_ver,
           java.lang.Integer vol_jahr) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.anfallstelle_nr = anfallstelle_nr;
        this.anfallstelle_ver = anfallstelle_ver;
        this.anh_id = anh_id;
        this.anh_ver = anh_ver;
        this.beschreibung = beschreibung;
        this.betriebseinrichtung_nr = betriebseinrichtung_nr;
        this.betriebseinrichtung_ver = betriebseinrichtung_ver;
        this.chargenbetrieb_jn = chargenbetrieb_jn;
        this.dauerbetrieb_jn = dauerbetrieb_jn;
        this.gemeinde_ver = gemeinde_ver;
        this.gemeindekennzahl = gemeindekennzahl;
        this.genehmigung_nr = genehmigung_nr;
        this.genehmigung_ver = genehmigung_ver;
        this.max_vol_tag = max_vol_tag;
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
        this.uebergabestelle_ver = uebergabestelle_ver;
        this.vol_jahr = vol_jahr;
    }


    /**
     * Gets the anfallstelle_nr value for this Inka_Anfallstelle.
     * 
     * @return anfallstelle_nr
     */
    public java.lang.Integer getAnfallstelle_nr() {
        return anfallstelle_nr;
    }


    /**
     * Sets the anfallstelle_nr value for this Inka_Anfallstelle.
     * 
     * @param anfallstelle_nr
     */
    public void setAnfallstelle_nr(java.lang.Integer anfallstelle_nr) {
        this.anfallstelle_nr = anfallstelle_nr;
    }


    /**
     * Gets the anfallstelle_ver value for this Inka_Anfallstelle.
     * 
     * @return anfallstelle_ver
     */
    public java.lang.Integer getAnfallstelle_ver() {
        return anfallstelle_ver;
    }


    /**
     * Sets the anfallstelle_ver value for this Inka_Anfallstelle.
     * 
     * @param anfallstelle_ver
     */
    public void setAnfallstelle_ver(java.lang.Integer anfallstelle_ver) {
        this.anfallstelle_ver = anfallstelle_ver;
    }


    /**
     * Gets the anh_id value for this Inka_Anfallstelle.
     * 
     * @return anh_id
     */
    public java.lang.String getAnh_id() {
        return anh_id;
    }


    /**
     * Sets the anh_id value for this Inka_Anfallstelle.
     * 
     * @param anh_id
     */
    public void setAnh_id(java.lang.String anh_id) {
        this.anh_id = anh_id;
    }


    /**
     * Gets the anh_ver value for this Inka_Anfallstelle.
     * 
     * @return anh_ver
     */
    public java.lang.Integer getAnh_ver() {
        return anh_ver;
    }


    /**
     * Sets the anh_ver value for this Inka_Anfallstelle.
     * 
     * @param anh_ver
     */
    public void setAnh_ver(java.lang.Integer anh_ver) {
        this.anh_ver = anh_ver;
    }


    /**
     * Gets the beschreibung value for this Inka_Anfallstelle.
     * 
     * @return beschreibung
     */
    public java.lang.String getBeschreibung() {
        return beschreibung;
    }


    /**
     * Sets the beschreibung value for this Inka_Anfallstelle.
     * 
     * @param beschreibung
     */
    public void setBeschreibung(java.lang.String beschreibung) {
        this.beschreibung = beschreibung;
    }


    /**
     * Gets the betriebseinrichtung_nr value for this Inka_Anfallstelle.
     * 
     * @return betriebseinrichtung_nr
     */
    public java.lang.Integer getBetriebseinrichtung_nr() {
        return betriebseinrichtung_nr;
    }


    /**
     * Sets the betriebseinrichtung_nr value for this Inka_Anfallstelle.
     * 
     * @param betriebseinrichtung_nr
     */
    public void setBetriebseinrichtung_nr(java.lang.Integer betriebseinrichtung_nr) {
        this.betriebseinrichtung_nr = betriebseinrichtung_nr;
    }


    /**
     * Gets the betriebseinrichtung_ver value for this Inka_Anfallstelle.
     * 
     * @return betriebseinrichtung_ver
     */
    public java.lang.Integer getBetriebseinrichtung_ver() {
        return betriebseinrichtung_ver;
    }


    /**
     * Sets the betriebseinrichtung_ver value for this Inka_Anfallstelle.
     * 
     * @param betriebseinrichtung_ver
     */
    public void setBetriebseinrichtung_ver(java.lang.Integer betriebseinrichtung_ver) {
        this.betriebseinrichtung_ver = betriebseinrichtung_ver;
    }


    /**
     * Gets the chargenbetrieb_jn value for this Inka_Anfallstelle.
     * 
     * @return chargenbetrieb_jn
     */
    public java.lang.Boolean getChargenbetrieb_jn() {
        return chargenbetrieb_jn;
    }


    /**
     * Sets the chargenbetrieb_jn value for this Inka_Anfallstelle.
     * 
     * @param chargenbetrieb_jn
     */
    public void setChargenbetrieb_jn(java.lang.Boolean chargenbetrieb_jn) {
        this.chargenbetrieb_jn = chargenbetrieb_jn;
    }


    /**
     * Gets the dauerbetrieb_jn value for this Inka_Anfallstelle.
     * 
     * @return dauerbetrieb_jn
     */
    public java.lang.Boolean getDauerbetrieb_jn() {
        return dauerbetrieb_jn;
    }


    /**
     * Sets the dauerbetrieb_jn value for this Inka_Anfallstelle.
     * 
     * @param dauerbetrieb_jn
     */
    public void setDauerbetrieb_jn(java.lang.Boolean dauerbetrieb_jn) {
        this.dauerbetrieb_jn = dauerbetrieb_jn;
    }


    /**
     * Gets the gemeinde_ver value for this Inka_Anfallstelle.
     * 
     * @return gemeinde_ver
     */
    public java.lang.Integer getGemeinde_ver() {
        return gemeinde_ver;
    }


    /**
     * Sets the gemeinde_ver value for this Inka_Anfallstelle.
     * 
     * @param gemeinde_ver
     */
    public void setGemeinde_ver(java.lang.Integer gemeinde_ver) {
        this.gemeinde_ver = gemeinde_ver;
    }


    /**
     * Gets the gemeindekennzahl value for this Inka_Anfallstelle.
     * 
     * @return gemeindekennzahl
     */
    public java.lang.String getGemeindekennzahl() {
        return gemeindekennzahl;
    }


    /**
     * Sets the gemeindekennzahl value for this Inka_Anfallstelle.
     * 
     * @param gemeindekennzahl
     */
    public void setGemeindekennzahl(java.lang.String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }


    /**
     * Gets the genehmigung_nr value for this Inka_Anfallstelle.
     * 
     * @return genehmigung_nr
     */
    public java.lang.Integer getGenehmigung_nr() {
        return genehmigung_nr;
    }


    /**
     * Sets the genehmigung_nr value for this Inka_Anfallstelle.
     * 
     * @param genehmigung_nr
     */
    public void setGenehmigung_nr(java.lang.Integer genehmigung_nr) {
        this.genehmigung_nr = genehmigung_nr;
    }


    /**
     * Gets the genehmigung_ver value for this Inka_Anfallstelle.
     * 
     * @return genehmigung_ver
     */
    public java.lang.Integer getGenehmigung_ver() {
        return genehmigung_ver;
    }


    /**
     * Sets the genehmigung_ver value for this Inka_Anfallstelle.
     * 
     * @param genehmigung_ver
     */
    public void setGenehmigung_ver(java.lang.Integer genehmigung_ver) {
        this.genehmigung_ver = genehmigung_ver;
    }


    /**
     * Gets the max_vol_tag value for this Inka_Anfallstelle.
     * 
     * @return max_vol_tag
     */
    public java.lang.Float getMax_vol_tag() {
        return max_vol_tag;
    }


    /**
     * Sets the max_vol_tag value for this Inka_Anfallstelle.
     * 
     * @param max_vol_tag
     */
    public void setMax_vol_tag(java.lang.Float max_vol_tag) {
        this.max_vol_tag = max_vol_tag;
    }


    /**
     * Gets the uebergabestelle_lfd_nr value for this Inka_Anfallstelle.
     * 
     * @return uebergabestelle_lfd_nr
     */
    public java.lang.Integer getUebergabestelle_lfd_nr() {
        return uebergabestelle_lfd_nr;
    }


    /**
     * Sets the uebergabestelle_lfd_nr value for this Inka_Anfallstelle.
     * 
     * @param uebergabestelle_lfd_nr
     */
    public void setUebergabestelle_lfd_nr(java.lang.Integer uebergabestelle_lfd_nr) {
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
    }


    /**
     * Gets the uebergabestelle_ver value for this Inka_Anfallstelle.
     * 
     * @return uebergabestelle_ver
     */
    public java.lang.Integer getUebergabestelle_ver() {
        return uebergabestelle_ver;
    }


    /**
     * Sets the uebergabestelle_ver value for this Inka_Anfallstelle.
     * 
     * @param uebergabestelle_ver
     */
    public void setUebergabestelle_ver(java.lang.Integer uebergabestelle_ver) {
        this.uebergabestelle_ver = uebergabestelle_ver;
    }


    /**
     * Gets the vol_jahr value for this Inka_Anfallstelle.
     * 
     * @return vol_jahr
     */
    public java.lang.Integer getVol_jahr() {
        return vol_jahr;
    }


    /**
     * Sets the vol_jahr value for this Inka_Anfallstelle.
     * 
     * @param vol_jahr
     */
    public void setVol_jahr(java.lang.Integer vol_jahr) {
        this.vol_jahr = vol_jahr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Anfallstelle)) return false;
        Inka_Anfallstelle other = (Inka_Anfallstelle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.anfallstelle_nr==null && other.getAnfallstelle_nr()==null) || 
             (this.anfallstelle_nr!=null &&
              this.anfallstelle_nr.equals(other.getAnfallstelle_nr()))) &&
            ((this.anfallstelle_ver==null && other.getAnfallstelle_ver()==null) || 
             (this.anfallstelle_ver!=null &&
              this.anfallstelle_ver.equals(other.getAnfallstelle_ver()))) &&
            ((this.anh_id==null && other.getAnh_id()==null) || 
             (this.anh_id!=null &&
              this.anh_id.equals(other.getAnh_id()))) &&
            ((this.anh_ver==null && other.getAnh_ver()==null) || 
             (this.anh_ver!=null &&
              this.anh_ver.equals(other.getAnh_ver()))) &&
            ((this.beschreibung==null && other.getBeschreibung()==null) || 
             (this.beschreibung!=null &&
              this.beschreibung.equals(other.getBeschreibung()))) &&
            ((this.betriebseinrichtung_nr==null && other.getBetriebseinrichtung_nr()==null) || 
             (this.betriebseinrichtung_nr!=null &&
              this.betriebseinrichtung_nr.equals(other.getBetriebseinrichtung_nr()))) &&
            ((this.betriebseinrichtung_ver==null && other.getBetriebseinrichtung_ver()==null) || 
             (this.betriebseinrichtung_ver!=null &&
              this.betriebseinrichtung_ver.equals(other.getBetriebseinrichtung_ver()))) &&
            ((this.chargenbetrieb_jn==null && other.getChargenbetrieb_jn()==null) || 
             (this.chargenbetrieb_jn!=null &&
              this.chargenbetrieb_jn.equals(other.getChargenbetrieb_jn()))) &&
            ((this.dauerbetrieb_jn==null && other.getDauerbetrieb_jn()==null) || 
             (this.dauerbetrieb_jn!=null &&
              this.dauerbetrieb_jn.equals(other.getDauerbetrieb_jn()))) &&
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
            ((this.max_vol_tag==null && other.getMax_vol_tag()==null) || 
             (this.max_vol_tag!=null &&
              this.max_vol_tag.equals(other.getMax_vol_tag()))) &&
            ((this.uebergabestelle_lfd_nr==null && other.getUebergabestelle_lfd_nr()==null) || 
             (this.uebergabestelle_lfd_nr!=null &&
              this.uebergabestelle_lfd_nr.equals(other.getUebergabestelle_lfd_nr()))) &&
            ((this.uebergabestelle_ver==null && other.getUebergabestelle_ver()==null) || 
             (this.uebergabestelle_ver!=null &&
              this.uebergabestelle_ver.equals(other.getUebergabestelle_ver()))) &&
            ((this.vol_jahr==null && other.getVol_jahr()==null) || 
             (this.vol_jahr!=null &&
              this.vol_jahr.equals(other.getVol_jahr())));
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
        if (getAnfallstelle_nr() != null) {
            _hashCode += getAnfallstelle_nr().hashCode();
        }
        if (getAnfallstelle_ver() != null) {
            _hashCode += getAnfallstelle_ver().hashCode();
        }
        if (getAnh_id() != null) {
            _hashCode += getAnh_id().hashCode();
        }
        if (getAnh_ver() != null) {
            _hashCode += getAnh_ver().hashCode();
        }
        if (getBeschreibung() != null) {
            _hashCode += getBeschreibung().hashCode();
        }
        if (getBetriebseinrichtung_nr() != null) {
            _hashCode += getBetriebseinrichtung_nr().hashCode();
        }
        if (getBetriebseinrichtung_ver() != null) {
            _hashCode += getBetriebseinrichtung_ver().hashCode();
        }
        if (getChargenbetrieb_jn() != null) {
            _hashCode += getChargenbetrieb_jn().hashCode();
        }
        if (getDauerbetrieb_jn() != null) {
            _hashCode += getDauerbetrieb_jn().hashCode();
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
        if (getMax_vol_tag() != null) {
            _hashCode += getMax_vol_tag().hashCode();
        }
        if (getUebergabestelle_lfd_nr() != null) {
            _hashCode += getUebergabestelle_lfd_nr().hashCode();
        }
        if (getUebergabestelle_ver() != null) {
            _hashCode += getUebergabestelle_ver().hashCode();
        }
        if (getVol_jahr() != null) {
            _hashCode += getVol_jahr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Anfallstelle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallstelle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("anh_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anh_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anh_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anh_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beschreibung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beschreibung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("betriebseinrichtung_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "betriebseinrichtung_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("betriebseinrichtung_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "betriebseinrichtung_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chargenbetrieb_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chargenbetrieb_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dauerbetrieb_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dauerbetrieb_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("max_vol_tag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "max_vol_tag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vol_jahr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vol_jahr"));
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
