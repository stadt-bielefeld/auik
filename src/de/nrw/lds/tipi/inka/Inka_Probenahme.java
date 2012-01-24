/**
 * Inka_Probenahme.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Inka_Probenahme  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.util.Calendar datum_analyse;

    private java.lang.Boolean durchflussmessung_jn;

    private java.lang.Integer gemeinde_ver;

    private java.lang.String gemeindekennzahl;

    private java.lang.Integer messstelle_lfd_nr;

    private java.lang.Integer messstelle_ver;

    private java.lang.String prob_schluessel;

    private java.lang.Integer prob_ver;

    private java.lang.String probe_nr;

    private java.lang.Integer probenahme_nr;

    private java.lang.Integer probenahme_ver;

    private java.lang.Float q_05H;

    private java.lang.Float q_2H;

    private java.lang.Boolean registrierung_jn;

    private java.lang.Boolean selbstueberw_jn;

    private java.lang.Integer uebergabestelle_lfd_nr;

    private java.lang.Integer uebergabestelle_ver;

    public Inka_Probenahme() {
    }

    public Inka_Probenahme(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.util.Calendar datum_analyse,
           java.lang.Boolean durchflussmessung_jn,
           java.lang.Integer gemeinde_ver,
           java.lang.String gemeindekennzahl,
           java.lang.Integer messstelle_lfd_nr,
           java.lang.Integer messstelle_ver,
           java.lang.String prob_schluessel,
           java.lang.Integer prob_ver,
           java.lang.String probe_nr,
           java.lang.Integer probenahme_nr,
           java.lang.Integer probenahme_ver,
           java.lang.Float q_05H,
           java.lang.Float q_2H,
           java.lang.Boolean registrierung_jn,
           java.lang.Boolean selbstueberw_jn,
           java.lang.Integer uebergabestelle_lfd_nr,
           java.lang.Integer uebergabestelle_ver) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.datum_analyse = datum_analyse;
        this.durchflussmessung_jn = durchflussmessung_jn;
        this.gemeinde_ver = gemeinde_ver;
        this.gemeindekennzahl = gemeindekennzahl;
        this.messstelle_lfd_nr = messstelle_lfd_nr;
        this.messstelle_ver = messstelle_ver;
        this.prob_schluessel = prob_schluessel;
        this.prob_ver = prob_ver;
        this.probe_nr = probe_nr;
        this.probenahme_nr = probenahme_nr;
        this.probenahme_ver = probenahme_ver;
        this.q_05H = q_05H;
        this.q_2H = q_2H;
        this.registrierung_jn = registrierung_jn;
        this.selbstueberw_jn = selbstueberw_jn;
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
        this.uebergabestelle_ver = uebergabestelle_ver;
    }


    /**
     * Gets the datum_analyse value for this Inka_Probenahme.
     * 
     * @return datum_analyse
     */
    public java.util.Calendar getDatum_analyse() {
        return datum_analyse;
    }


    /**
     * Sets the datum_analyse value for this Inka_Probenahme.
     * 
     * @param datum_analyse
     */
    public void setDatum_analyse(java.util.Calendar datum_analyse) {
        this.datum_analyse = datum_analyse;
    }


    /**
     * Gets the durchflussmessung_jn value for this Inka_Probenahme.
     * 
     * @return durchflussmessung_jn
     */
    public java.lang.Boolean getDurchflussmessung_jn() {
        return durchflussmessung_jn;
    }


    /**
     * Sets the durchflussmessung_jn value for this Inka_Probenahme.
     * 
     * @param durchflussmessung_jn
     */
    public void setDurchflussmessung_jn(java.lang.Boolean durchflussmessung_jn) {
        this.durchflussmessung_jn = durchflussmessung_jn;
    }


    /**
     * Gets the gemeinde_ver value for this Inka_Probenahme.
     * 
     * @return gemeinde_ver
     */
    public java.lang.Integer getGemeinde_ver() {
        return gemeinde_ver;
    }


    /**
     * Sets the gemeinde_ver value for this Inka_Probenahme.
     * 
     * @param gemeinde_ver
     */
    public void setGemeinde_ver(java.lang.Integer gemeinde_ver) {
        this.gemeinde_ver = gemeinde_ver;
    }


    /**
     * Gets the gemeindekennzahl value for this Inka_Probenahme.
     * 
     * @return gemeindekennzahl
     */
    public java.lang.String getGemeindekennzahl() {
        return gemeindekennzahl;
    }


    /**
     * Sets the gemeindekennzahl value for this Inka_Probenahme.
     * 
     * @param gemeindekennzahl
     */
    public void setGemeindekennzahl(java.lang.String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }


    /**
     * Gets the messstelle_lfd_nr value for this Inka_Probenahme.
     * 
     * @return messstelle_lfd_nr
     */
    public java.lang.Integer getMessstelle_lfd_nr() {
        return messstelle_lfd_nr;
    }


    /**
     * Sets the messstelle_lfd_nr value for this Inka_Probenahme.
     * 
     * @param messstelle_lfd_nr
     */
    public void setMessstelle_lfd_nr(java.lang.Integer messstelle_lfd_nr) {
        this.messstelle_lfd_nr = messstelle_lfd_nr;
    }


    /**
     * Gets the messstelle_ver value for this Inka_Probenahme.
     * 
     * @return messstelle_ver
     */
    public java.lang.Integer getMessstelle_ver() {
        return messstelle_ver;
    }


    /**
     * Sets the messstelle_ver value for this Inka_Probenahme.
     * 
     * @param messstelle_ver
     */
    public void setMessstelle_ver(java.lang.Integer messstelle_ver) {
        this.messstelle_ver = messstelle_ver;
    }


    /**
     * Gets the prob_schluessel value for this Inka_Probenahme.
     * 
     * @return prob_schluessel
     */
    public java.lang.String getProb_schluessel() {
        return prob_schluessel;
    }


    /**
     * Sets the prob_schluessel value for this Inka_Probenahme.
     * 
     * @param prob_schluessel
     */
    public void setProb_schluessel(java.lang.String prob_schluessel) {
        this.prob_schluessel = prob_schluessel;
    }


    /**
     * Gets the prob_ver value for this Inka_Probenahme.
     * 
     * @return prob_ver
     */
    public java.lang.Integer getProb_ver() {
        return prob_ver;
    }


    /**
     * Sets the prob_ver value for this Inka_Probenahme.
     * 
     * @param prob_ver
     */
    public void setProb_ver(java.lang.Integer prob_ver) {
        this.prob_ver = prob_ver;
    }


    /**
     * Gets the probe_nr value for this Inka_Probenahme.
     * 
     * @return probe_nr
     */
    public java.lang.String getProbe_nr() {
        return probe_nr;
    }


    /**
     * Sets the probe_nr value for this Inka_Probenahme.
     * 
     * @param probe_nr
     */
    public void setProbe_nr(java.lang.String probe_nr) {
        this.probe_nr = probe_nr;
    }


    /**
     * Gets the probenahme_nr value for this Inka_Probenahme.
     * 
     * @return probenahme_nr
     */
    public java.lang.Integer getProbenahme_nr() {
        return probenahme_nr;
    }


    /**
     * Sets the probenahme_nr value for this Inka_Probenahme.
     * 
     * @param probenahme_nr
     */
    public void setProbenahme_nr(java.lang.Integer probenahme_nr) {
        this.probenahme_nr = probenahme_nr;
    }


    /**
     * Gets the probenahme_ver value for this Inka_Probenahme.
     * 
     * @return probenahme_ver
     */
    public java.lang.Integer getProbenahme_ver() {
        return probenahme_ver;
    }


    /**
     * Sets the probenahme_ver value for this Inka_Probenahme.
     * 
     * @param probenahme_ver
     */
    public void setProbenahme_ver(java.lang.Integer probenahme_ver) {
        this.probenahme_ver = probenahme_ver;
    }


    /**
     * Gets the q_05H value for this Inka_Probenahme.
     * 
     * @return q_05H
     */
    public java.lang.Float getQ_05H() {
        return q_05H;
    }


    /**
     * Sets the q_05H value for this Inka_Probenahme.
     * 
     * @param q_05H
     */
    public void setQ_05H(java.lang.Float q_05H) {
        this.q_05H = q_05H;
    }


    /**
     * Gets the q_2H value for this Inka_Probenahme.
     * 
     * @return q_2H
     */
    public java.lang.Float getQ_2H() {
        return q_2H;
    }


    /**
     * Sets the q_2H value for this Inka_Probenahme.
     * 
     * @param q_2H
     */
    public void setQ_2H(java.lang.Float q_2H) {
        this.q_2H = q_2H;
    }


    /**
     * Gets the registrierung_jn value for this Inka_Probenahme.
     * 
     * @return registrierung_jn
     */
    public java.lang.Boolean getRegistrierung_jn() {
        return registrierung_jn;
    }


    /**
     * Sets the registrierung_jn value for this Inka_Probenahme.
     * 
     * @param registrierung_jn
     */
    public void setRegistrierung_jn(java.lang.Boolean registrierung_jn) {
        this.registrierung_jn = registrierung_jn;
    }


    /**
     * Gets the selbstueberw_jn value for this Inka_Probenahme.
     * 
     * @return selbstueberw_jn
     */
    public java.lang.Boolean getSelbstueberw_jn() {
        return selbstueberw_jn;
    }


    /**
     * Sets the selbstueberw_jn value for this Inka_Probenahme.
     * 
     * @param selbstueberw_jn
     */
    public void setSelbstueberw_jn(java.lang.Boolean selbstueberw_jn) {
        this.selbstueberw_jn = selbstueberw_jn;
    }


    /**
     * Gets the uebergabestelle_lfd_nr value for this Inka_Probenahme.
     * 
     * @return uebergabestelle_lfd_nr
     */
    public java.lang.Integer getUebergabestelle_lfd_nr() {
        return uebergabestelle_lfd_nr;
    }


    /**
     * Sets the uebergabestelle_lfd_nr value for this Inka_Probenahme.
     * 
     * @param uebergabestelle_lfd_nr
     */
    public void setUebergabestelle_lfd_nr(java.lang.Integer uebergabestelle_lfd_nr) {
        this.uebergabestelle_lfd_nr = uebergabestelle_lfd_nr;
    }


    /**
     * Gets the uebergabestelle_ver value for this Inka_Probenahme.
     * 
     * @return uebergabestelle_ver
     */
    public java.lang.Integer getUebergabestelle_ver() {
        return uebergabestelle_ver;
    }


    /**
     * Sets the uebergabestelle_ver value for this Inka_Probenahme.
     * 
     * @param uebergabestelle_ver
     */
    public void setUebergabestelle_ver(java.lang.Integer uebergabestelle_ver) {
        this.uebergabestelle_ver = uebergabestelle_ver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Inka_Probenahme)) return false;
        Inka_Probenahme other = (Inka_Probenahme) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.datum_analyse==null && other.getDatum_analyse()==null) || 
             (this.datum_analyse!=null &&
              this.datum_analyse.equals(other.getDatum_analyse()))) &&
            ((this.durchflussmessung_jn==null && other.getDurchflussmessung_jn()==null) || 
             (this.durchflussmessung_jn!=null &&
              this.durchflussmessung_jn.equals(other.getDurchflussmessung_jn()))) &&
            ((this.gemeinde_ver==null && other.getGemeinde_ver()==null) || 
             (this.gemeinde_ver!=null &&
              this.gemeinde_ver.equals(other.getGemeinde_ver()))) &&
            ((this.gemeindekennzahl==null && other.getGemeindekennzahl()==null) || 
             (this.gemeindekennzahl!=null &&
              this.gemeindekennzahl.equals(other.getGemeindekennzahl()))) &&
            ((this.messstelle_lfd_nr==null && other.getMessstelle_lfd_nr()==null) || 
             (this.messstelle_lfd_nr!=null &&
              this.messstelle_lfd_nr.equals(other.getMessstelle_lfd_nr()))) &&
            ((this.messstelle_ver==null && other.getMessstelle_ver()==null) || 
             (this.messstelle_ver!=null &&
              this.messstelle_ver.equals(other.getMessstelle_ver()))) &&
            ((this.prob_schluessel==null && other.getProb_schluessel()==null) || 
             (this.prob_schluessel!=null &&
              this.prob_schluessel.equals(other.getProb_schluessel()))) &&
            ((this.prob_ver==null && other.getProb_ver()==null) || 
             (this.prob_ver!=null &&
              this.prob_ver.equals(other.getProb_ver()))) &&
            ((this.probe_nr==null && other.getProbe_nr()==null) || 
             (this.probe_nr!=null &&
              this.probe_nr.equals(other.getProbe_nr()))) &&
            ((this.probenahme_nr==null && other.getProbenahme_nr()==null) || 
             (this.probenahme_nr!=null &&
              this.probenahme_nr.equals(other.getProbenahme_nr()))) &&
            ((this.probenahme_ver==null && other.getProbenahme_ver()==null) || 
             (this.probenahme_ver!=null &&
              this.probenahme_ver.equals(other.getProbenahme_ver()))) &&
            ((this.q_05H==null && other.getQ_05H()==null) || 
             (this.q_05H!=null &&
              this.q_05H.equals(other.getQ_05H()))) &&
            ((this.q_2H==null && other.getQ_2H()==null) || 
             (this.q_2H!=null &&
              this.q_2H.equals(other.getQ_2H()))) &&
            ((this.registrierung_jn==null && other.getRegistrierung_jn()==null) || 
             (this.registrierung_jn!=null &&
              this.registrierung_jn.equals(other.getRegistrierung_jn()))) &&
            ((this.selbstueberw_jn==null && other.getSelbstueberw_jn()==null) || 
             (this.selbstueberw_jn!=null &&
              this.selbstueberw_jn.equals(other.getSelbstueberw_jn()))) &&
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
        if (getDatum_analyse() != null) {
            _hashCode += getDatum_analyse().hashCode();
        }
        if (getDurchflussmessung_jn() != null) {
            _hashCode += getDurchflussmessung_jn().hashCode();
        }
        if (getGemeinde_ver() != null) {
            _hashCode += getGemeinde_ver().hashCode();
        }
        if (getGemeindekennzahl() != null) {
            _hashCode += getGemeindekennzahl().hashCode();
        }
        if (getMessstelle_lfd_nr() != null) {
            _hashCode += getMessstelle_lfd_nr().hashCode();
        }
        if (getMessstelle_ver() != null) {
            _hashCode += getMessstelle_ver().hashCode();
        }
        if (getProb_schluessel() != null) {
            _hashCode += getProb_schluessel().hashCode();
        }
        if (getProb_ver() != null) {
            _hashCode += getProb_ver().hashCode();
        }
        if (getProbe_nr() != null) {
            _hashCode += getProbe_nr().hashCode();
        }
        if (getProbenahme_nr() != null) {
            _hashCode += getProbenahme_nr().hashCode();
        }
        if (getProbenahme_ver() != null) {
            _hashCode += getProbenahme_ver().hashCode();
        }
        if (getQ_05H() != null) {
            _hashCode += getQ_05H().hashCode();
        }
        if (getQ_2H() != null) {
            _hashCode += getQ_2H().hashCode();
        }
        if (getRegistrierung_jn() != null) {
            _hashCode += getRegistrierung_jn().hashCode();
        }
        if (getSelbstueberw_jn() != null) {
            _hashCode += getSelbstueberw_jn().hashCode();
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
        new org.apache.axis.description.TypeDesc(Inka_Probenahme.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Probenahme"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datum_analyse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datum_analyse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("durchflussmessung_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "durchflussmessung_jn"));
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
        elemField.setFieldName("messstelle_lfd_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messstelle_lfd_nr"));
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
        elemField.setFieldName("prob_schluessel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prob_schluessel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prob_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prob_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("probe_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "probe_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("q_05H");
        elemField.setXmlName(new javax.xml.namespace.QName("", "q_05h"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("q_2H");
        elemField.setXmlName(new javax.xml.namespace.QName("", "q_2h"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrierung_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registrierung_jn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selbstueberw_jn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "selbstueberw_jn"));
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
