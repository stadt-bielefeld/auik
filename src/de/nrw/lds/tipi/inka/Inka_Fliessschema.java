/**
 * Inka_Fliessschema.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Fliessschema  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer anfallstelle_nr;

    private java.lang.Integer anfallstelle_ver;

    private java.lang.Integer anlage_nr;

    private java.lang.Integer anlage_ver;

    private java.lang.Integer fliessschema_ver;

    private java.lang.Integer fs_fliessschema_ver;

    private java.lang.Integer fs_satz_nr;

    private java.lang.Integer m_gemeinde_ver;

    private java.lang.String m_gemeindekennzahl;

    private java.lang.Integer m_messstelle_lfd_nr;

    private java.lang.Integer m_messstelle_ver;

    private java.lang.Integer m_uebergabestelle_lfd_nr;

    private java.lang.Integer m_uebergabestelle_ver;

    private java.lang.Integer satz_nr;

    private java.lang.Integer u_gemeinde_ver;

    private java.lang.String u_gemeindekennzahl;

    private java.lang.Integer u_uebergabestelle_lfd_nr;

    private java.lang.Integer u_uebergabestelle_ver;

    public Inka_Fliessschema() {
    }

    public Inka_Fliessschema(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer anfallstelle_nr,
           java.lang.Integer anfallstelle_ver,
           java.lang.Integer anlage_nr,
           java.lang.Integer anlage_ver,
           java.lang.Integer fliessschema_ver,
           java.lang.Integer fs_fliessschema_ver,
           java.lang.Integer fs_satz_nr,
           java.lang.Integer m_gemeinde_ver,
           java.lang.String m_gemeindekennzahl,
           java.lang.Integer m_messstelle_lfd_nr,
           java.lang.Integer m_messstelle_ver,
           java.lang.Integer m_uebergabestelle_lfd_nr,
           java.lang.Integer m_uebergabestelle_ver,
           java.lang.Integer satz_nr,
           java.lang.Integer u_gemeinde_ver,
           java.lang.String u_gemeindekennzahl,
           java.lang.Integer u_uebergabestelle_lfd_nr,
           java.lang.Integer u_uebergabestelle_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.anfallstelle_nr = anfallstelle_nr;
        this.anfallstelle_ver = anfallstelle_ver;
        this.anlage_nr = anlage_nr;
        this.anlage_ver = anlage_ver;
        this.fliessschema_ver = fliessschema_ver;
        this.fs_fliessschema_ver = fs_fliessschema_ver;
        this.fs_satz_nr = fs_satz_nr;
        this.m_gemeinde_ver = m_gemeinde_ver;
        this.m_gemeindekennzahl = m_gemeindekennzahl;
        this.m_messstelle_lfd_nr = m_messstelle_lfd_nr;
        this.m_messstelle_ver = m_messstelle_ver;
        this.m_uebergabestelle_lfd_nr = m_uebergabestelle_lfd_nr;
        this.m_uebergabestelle_ver = m_uebergabestelle_ver;
        this.satz_nr = satz_nr;
        this.u_gemeinde_ver = u_gemeinde_ver;
        this.u_gemeindekennzahl = u_gemeindekennzahl;
        this.u_uebergabestelle_lfd_nr = u_uebergabestelle_lfd_nr;
        this.u_uebergabestelle_ver = u_uebergabestelle_ver;
    }


    /**
     * Gets the anfallstelle_nr value for this Inka_Fliessschema.
     * 
     * @return anfallstelle_nr
     */
    public java.lang.Integer getAnfallstelle_nr() {
        return anfallstelle_nr;
    }


    /**
     * Sets the anfallstelle_nr value for this Inka_Fliessschema.
     * 
     * @param anfallstelle_nr
     */
    public void setAnfallstelle_nr(java.lang.Integer anfallstelle_nr) {
        this.anfallstelle_nr = anfallstelle_nr;
    }


    /**
     * Gets the anfallstelle_ver value for this Inka_Fliessschema.
     * 
     * @return anfallstelle_ver
     */
    public java.lang.Integer getAnfallstelle_ver() {
        return anfallstelle_ver;
    }


    /**
     * Sets the anfallstelle_ver value for this Inka_Fliessschema.
     * 
     * @param anfallstelle_ver
     */
    public void setAnfallstelle_ver(java.lang.Integer anfallstelle_ver) {
        this.anfallstelle_ver = anfallstelle_ver;
    }


    /**
     * Gets the anlage_nr value for this Inka_Fliessschema.
     * 
     * @return anlage_nr
     */
    public java.lang.Integer getAnlage_nr() {
        return anlage_nr;
    }


    /**
     * Sets the anlage_nr value for this Inka_Fliessschema.
     * 
     * @param anlage_nr
     */
    public void setAnlage_nr(java.lang.Integer anlage_nr) {
        this.anlage_nr = anlage_nr;
    }


    /**
     * Gets the anlage_ver value for this Inka_Fliessschema.
     * 
     * @return anlage_ver
     */
    public java.lang.Integer getAnlage_ver() {
        return anlage_ver;
    }


    /**
     * Sets the anlage_ver value for this Inka_Fliessschema.
     * 
     * @param anlage_ver
     */
    public void setAnlage_ver(java.lang.Integer anlage_ver) {
        this.anlage_ver = anlage_ver;
    }


    /**
     * Gets the fliessschema_ver value for this Inka_Fliessschema.
     * 
     * @return fliessschema_ver
     */
    public java.lang.Integer getFliessschema_ver() {
        return fliessschema_ver;
    }


    /**
     * Sets the fliessschema_ver value for this Inka_Fliessschema.
     * 
     * @param fliessschema_ver
     */
    public void setFliessschema_ver(java.lang.Integer fliessschema_ver) {
        this.fliessschema_ver = fliessschema_ver;
    }


    /**
     * Gets the fs_fliessschema_ver value for this Inka_Fliessschema.
     * 
     * @return fs_fliessschema_ver
     */
    public java.lang.Integer getFs_fliessschema_ver() {
        return fs_fliessschema_ver;
    }


    /**
     * Sets the fs_fliessschema_ver value for this Inka_Fliessschema.
     * 
     * @param fs_fliessschema_ver
     */
    public void setFs_fliessschema_ver(java.lang.Integer fs_fliessschema_ver) {
        this.fs_fliessschema_ver = fs_fliessschema_ver;
    }


    /**
     * Gets the fs_satz_nr value for this Inka_Fliessschema.
     * 
     * @return fs_satz_nr
     */
    public java.lang.Integer getFs_satz_nr() {
        return fs_satz_nr;
    }


    /**
     * Sets the fs_satz_nr value for this Inka_Fliessschema.
     * 
     * @param fs_satz_nr
     */
    public void setFs_satz_nr(java.lang.Integer fs_satz_nr) {
        this.fs_satz_nr = fs_satz_nr;
    }


    /**
     * Gets the m_gemeinde_ver value for this Inka_Fliessschema.
     * 
     * @return m_gemeinde_ver
     */
    public java.lang.Integer getM_gemeinde_ver() {
        return m_gemeinde_ver;
    }


    /**
     * Sets the m_gemeinde_ver value for this Inka_Fliessschema.
     * 
     * @param m_gemeinde_ver
     */
    public void setM_gemeinde_ver(java.lang.Integer m_gemeinde_ver) {
        this.m_gemeinde_ver = m_gemeinde_ver;
    }


    /**
     * Gets the m_gemeindekennzahl value for this Inka_Fliessschema.
     * 
     * @return m_gemeindekennzahl
     */
    public java.lang.String getM_gemeindekennzahl() {
        return m_gemeindekennzahl;
    }


    /**
     * Sets the m_gemeindekennzahl value for this Inka_Fliessschema.
     * 
     * @param m_gemeindekennzahl
     */
    public void setM_gemeindekennzahl(java.lang.String m_gemeindekennzahl) {
        this.m_gemeindekennzahl = m_gemeindekennzahl;
    }


    /**
     * Gets the m_messstelle_lfd_nr value for this Inka_Fliessschema.
     * 
     * @return m_messstelle_lfd_nr
     */
    public java.lang.Integer getM_messstelle_lfd_nr() {
        return m_messstelle_lfd_nr;
    }


    /**
     * Sets the m_messstelle_lfd_nr value for this Inka_Fliessschema.
     * 
     * @param m_messstelle_lfd_nr
     */
    public void setM_messstelle_lfd_nr(java.lang.Integer m_messstelle_lfd_nr) {
        this.m_messstelle_lfd_nr = m_messstelle_lfd_nr;
    }


    /**
     * Gets the m_messstelle_ver value for this Inka_Fliessschema.
     * 
     * @return m_messstelle_ver
     */
    public java.lang.Integer getM_messstelle_ver() {
        return m_messstelle_ver;
    }


    /**
     * Sets the m_messstelle_ver value for this Inka_Fliessschema.
     * 
     * @param m_messstelle_ver
     */
    public void setM_messstelle_ver(java.lang.Integer m_messstelle_ver) {
        this.m_messstelle_ver = m_messstelle_ver;
    }


    /**
     * Gets the m_uebergabestelle_lfd_nr value for this Inka_Fliessschema.
     * 
     * @return m_uebergabestelle_lfd_nr
     */
    public java.lang.Integer getM_uebergabestelle_lfd_nr() {
        return m_uebergabestelle_lfd_nr;
    }


    /**
     * Sets the m_uebergabestelle_lfd_nr value for this Inka_Fliessschema.
     * 
     * @param m_uebergabestelle_lfd_nr
     */
    public void setM_uebergabestelle_lfd_nr(java.lang.Integer m_uebergabestelle_lfd_nr) {
        this.m_uebergabestelle_lfd_nr = m_uebergabestelle_lfd_nr;
    }


    /**
     * Gets the m_uebergabestelle_ver value for this Inka_Fliessschema.
     * 
     * @return m_uebergabestelle_ver
     */
    public java.lang.Integer getM_uebergabestelle_ver() {
        return m_uebergabestelle_ver;
    }


    /**
     * Sets the m_uebergabestelle_ver value for this Inka_Fliessschema.
     * 
     * @param m_uebergabestelle_ver
     */
    public void setM_uebergabestelle_ver(java.lang.Integer m_uebergabestelle_ver) {
        this.m_uebergabestelle_ver = m_uebergabestelle_ver;
    }


    /**
     * Gets the satz_nr value for this Inka_Fliessschema.
     * 
     * @return satz_nr
     */
    public java.lang.Integer getSatz_nr() {
        return satz_nr;
    }


    /**
     * Sets the satz_nr value for this Inka_Fliessschema.
     * 
     * @param satz_nr
     */
    public void setSatz_nr(java.lang.Integer satz_nr) {
        this.satz_nr = satz_nr;
    }


    /**
     * Gets the u_gemeinde_ver value for this Inka_Fliessschema.
     * 
     * @return u_gemeinde_ver
     */
    public java.lang.Integer getU_gemeinde_ver() {
        return u_gemeinde_ver;
    }


    /**
     * Sets the u_gemeinde_ver value for this Inka_Fliessschema.
     * 
     * @param u_gemeinde_ver
     */
    public void setU_gemeinde_ver(java.lang.Integer u_gemeinde_ver) {
        this.u_gemeinde_ver = u_gemeinde_ver;
    }


    /**
     * Gets the u_gemeindekennzahl value for this Inka_Fliessschema.
     * 
     * @return u_gemeindekennzahl
     */
    public java.lang.String getU_gemeindekennzahl() {
        return u_gemeindekennzahl;
    }


    /**
     * Sets the u_gemeindekennzahl value for this Inka_Fliessschema.
     * 
     * @param u_gemeindekennzahl
     */
    public void setU_gemeindekennzahl(java.lang.String u_gemeindekennzahl) {
        this.u_gemeindekennzahl = u_gemeindekennzahl;
    }


    /**
     * Gets the u_uebergabestelle_lfd_nr value for this Inka_Fliessschema.
     * 
     * @return u_uebergabestelle_lfd_nr
     */
    public java.lang.Integer getU_uebergabestelle_lfd_nr() {
        return u_uebergabestelle_lfd_nr;
    }


    /**
     * Sets the u_uebergabestelle_lfd_nr value for this Inka_Fliessschema.
     * 
     * @param u_uebergabestelle_lfd_nr
     */
    public void setU_uebergabestelle_lfd_nr(java.lang.Integer u_uebergabestelle_lfd_nr) {
        this.u_uebergabestelle_lfd_nr = u_uebergabestelle_lfd_nr;
    }


    /**
     * Gets the u_uebergabestelle_ver value for this Inka_Fliessschema.
     * 
     * @return u_uebergabestelle_ver
     */
    public java.lang.Integer getU_uebergabestelle_ver() {
        return u_uebergabestelle_ver;
    }


    /**
     * Sets the u_uebergabestelle_ver value for this Inka_Fliessschema.
     * 
     * @param u_uebergabestelle_ver
     */
    public void setU_uebergabestelle_ver(java.lang.Integer u_uebergabestelle_ver) {
        this.u_uebergabestelle_ver = u_uebergabestelle_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Fliessschema)) return false;
        Inka_Fliessschema other = (Inka_Fliessschema) obj;
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
            ((this.anlage_nr==null && other.getAnlage_nr()==null) || 
             (this.anlage_nr!=null &&
              this.anlage_nr.equals(other.getAnlage_nr()))) &&
            ((this.anlage_ver==null && other.getAnlage_ver()==null) || 
             (this.anlage_ver!=null &&
              this.anlage_ver.equals(other.getAnlage_ver()))) &&
            ((this.fliessschema_ver==null && other.getFliessschema_ver()==null) || 
             (this.fliessschema_ver!=null &&
              this.fliessschema_ver.equals(other.getFliessschema_ver()))) &&
            ((this.fs_fliessschema_ver==null && other.getFs_fliessschema_ver()==null) || 
             (this.fs_fliessschema_ver!=null &&
              this.fs_fliessschema_ver.equals(other.getFs_fliessschema_ver()))) &&
            ((this.fs_satz_nr==null && other.getFs_satz_nr()==null) || 
             (this.fs_satz_nr!=null &&
              this.fs_satz_nr.equals(other.getFs_satz_nr()))) &&
            ((this.m_gemeinde_ver==null && other.getM_gemeinde_ver()==null) || 
             (this.m_gemeinde_ver!=null &&
              this.m_gemeinde_ver.equals(other.getM_gemeinde_ver()))) &&
            ((this.m_gemeindekennzahl==null && other.getM_gemeindekennzahl()==null) || 
             (this.m_gemeindekennzahl!=null &&
              this.m_gemeindekennzahl.equals(other.getM_gemeindekennzahl()))) &&
            ((this.m_messstelle_lfd_nr==null && other.getM_messstelle_lfd_nr()==null) || 
             (this.m_messstelle_lfd_nr!=null &&
              this.m_messstelle_lfd_nr.equals(other.getM_messstelle_lfd_nr()))) &&
            ((this.m_messstelle_ver==null && other.getM_messstelle_ver()==null) || 
             (this.m_messstelle_ver!=null &&
              this.m_messstelle_ver.equals(other.getM_messstelle_ver()))) &&
            ((this.m_uebergabestelle_lfd_nr==null && other.getM_uebergabestelle_lfd_nr()==null) || 
             (this.m_uebergabestelle_lfd_nr!=null &&
              this.m_uebergabestelle_lfd_nr.equals(other.getM_uebergabestelle_lfd_nr()))) &&
            ((this.m_uebergabestelle_ver==null && other.getM_uebergabestelle_ver()==null) || 
             (this.m_uebergabestelle_ver!=null &&
              this.m_uebergabestelle_ver.equals(other.getM_uebergabestelle_ver()))) &&
            ((this.satz_nr==null && other.getSatz_nr()==null) || 
             (this.satz_nr!=null &&
              this.satz_nr.equals(other.getSatz_nr()))) &&
            ((this.u_gemeinde_ver==null && other.getU_gemeinde_ver()==null) || 
             (this.u_gemeinde_ver!=null &&
              this.u_gemeinde_ver.equals(other.getU_gemeinde_ver()))) &&
            ((this.u_gemeindekennzahl==null && other.getU_gemeindekennzahl()==null) || 
             (this.u_gemeindekennzahl!=null &&
              this.u_gemeindekennzahl.equals(other.getU_gemeindekennzahl()))) &&
            ((this.u_uebergabestelle_lfd_nr==null && other.getU_uebergabestelle_lfd_nr()==null) || 
             (this.u_uebergabestelle_lfd_nr!=null &&
              this.u_uebergabestelle_lfd_nr.equals(other.getU_uebergabestelle_lfd_nr()))) &&
            ((this.u_uebergabestelle_ver==null && other.getU_uebergabestelle_ver()==null) || 
             (this.u_uebergabestelle_ver!=null &&
              this.u_uebergabestelle_ver.equals(other.getU_uebergabestelle_ver())));
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
        if (getAnlage_nr() != null) {
            _hashCode += getAnlage_nr().hashCode();
        }
        if (getAnlage_ver() != null) {
            _hashCode += getAnlage_ver().hashCode();
        }
        if (getFliessschema_ver() != null) {
            _hashCode += getFliessschema_ver().hashCode();
        }
        if (getFs_fliessschema_ver() != null) {
            _hashCode += getFs_fliessschema_ver().hashCode();
        }
        if (getFs_satz_nr() != null) {
            _hashCode += getFs_satz_nr().hashCode();
        }
        if (getM_gemeinde_ver() != null) {
            _hashCode += getM_gemeinde_ver().hashCode();
        }
        if (getM_gemeindekennzahl() != null) {
            _hashCode += getM_gemeindekennzahl().hashCode();
        }
        if (getM_messstelle_lfd_nr() != null) {
            _hashCode += getM_messstelle_lfd_nr().hashCode();
        }
        if (getM_messstelle_ver() != null) {
            _hashCode += getM_messstelle_ver().hashCode();
        }
        if (getM_uebergabestelle_lfd_nr() != null) {
            _hashCode += getM_uebergabestelle_lfd_nr().hashCode();
        }
        if (getM_uebergabestelle_ver() != null) {
            _hashCode += getM_uebergabestelle_ver().hashCode();
        }
        if (getSatz_nr() != null) {
            _hashCode += getSatz_nr().hashCode();
        }
        if (getU_gemeinde_ver() != null) {
            _hashCode += getU_gemeinde_ver().hashCode();
        }
        if (getU_gemeindekennzahl() != null) {
            _hashCode += getU_gemeindekennzahl().hashCode();
        }
        if (getU_uebergabestelle_lfd_nr() != null) {
            _hashCode += getU_uebergabestelle_lfd_nr().hashCode();
        }
        if (getU_uebergabestelle_ver() != null) {
            _hashCode += getU_uebergabestelle_ver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Inka_Fliessschema.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Fliessschema"));
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
        elemField.setFieldName("anlage_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anlage_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anlage_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anlage_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fliessschema_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fliessschema_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fs_fliessschema_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fs_fliessschema_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fs_satz_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fs_satz_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_gemeinde_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "m_gemeinde_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_gemeindekennzahl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "m_gemeindekennzahl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_messstelle_lfd_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "m_messstelle_lfd_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_messstelle_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "m_messstelle_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_uebergabestelle_lfd_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "m_uebergabestelle_lfd_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_uebergabestelle_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "m_uebergabestelle_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("satz_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "satz_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_gemeinde_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_gemeinde_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_gemeindekennzahl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_gemeindekennzahl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_uebergabestelle_lfd_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_uebergabestelle_lfd_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("u_uebergabestelle_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "u_uebergabestelle_ver"));
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
