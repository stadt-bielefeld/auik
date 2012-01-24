/**
 * Dea_Analysemethode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Analysemethode  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer analyse_ver;

    private java.lang.String beschreibung_analysemet;

    private java.lang.String bezeichnung_methode;

    private java.lang.String bezeichnung_variante;

    private java.lang.String datum_norm;

    private java.lang.Integer ergebnistyp_opt;

    private java.lang.String gruppe_dev;

    private java.lang.String methoden_nr;

    private java.lang.Integer phase_opt;

    private java.lang.String regelwerk_nr;

    private java.lang.String varianten_nr;

    public Dea_Analysemethode() {
    }

    public Dea_Analysemethode(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer analyse_ver,
           java.lang.String beschreibung_analysemet,
           java.lang.String bezeichnung_methode,
           java.lang.String bezeichnung_variante,
           java.lang.String datum_norm,
           java.lang.Integer ergebnistyp_opt,
           java.lang.String gruppe_dev,
           java.lang.String methoden_nr,
           java.lang.Integer phase_opt,
           java.lang.String regelwerk_nr,
           java.lang.String varianten_nr) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.analyse_ver = analyse_ver;
        this.beschreibung_analysemet = beschreibung_analysemet;
        this.bezeichnung_methode = bezeichnung_methode;
        this.bezeichnung_variante = bezeichnung_variante;
        this.datum_norm = datum_norm;
        this.ergebnistyp_opt = ergebnistyp_opt;
        this.gruppe_dev = gruppe_dev;
        this.methoden_nr = methoden_nr;
        this.phase_opt = phase_opt;
        this.regelwerk_nr = regelwerk_nr;
        this.varianten_nr = varianten_nr;
    }


    /**
     * Gets the analyse_ver value for this Dea_Analysemethode.
     * 
     * @return analyse_ver
     */
    public java.lang.Integer getAnalyse_ver() {
        return analyse_ver;
    }


    /**
     * Sets the analyse_ver value for this Dea_Analysemethode.
     * 
     * @param analyse_ver
     */
    public void setAnalyse_ver(java.lang.Integer analyse_ver) {
        this.analyse_ver = analyse_ver;
    }


    /**
     * Gets the beschreibung_analysemet value for this Dea_Analysemethode.
     * 
     * @return beschreibung_analysemet
     */
    public java.lang.String getBeschreibung_analysemet() {
        return beschreibung_analysemet;
    }


    /**
     * Sets the beschreibung_analysemet value for this Dea_Analysemethode.
     * 
     * @param beschreibung_analysemet
     */
    public void setBeschreibung_analysemet(java.lang.String beschreibung_analysemet) {
        this.beschreibung_analysemet = beschreibung_analysemet;
    }


    /**
     * Gets the bezeichnung_methode value for this Dea_Analysemethode.
     * 
     * @return bezeichnung_methode
     */
    public java.lang.String getBezeichnung_methode() {
        return bezeichnung_methode;
    }


    /**
     * Sets the bezeichnung_methode value for this Dea_Analysemethode.
     * 
     * @param bezeichnung_methode
     */
    public void setBezeichnung_methode(java.lang.String bezeichnung_methode) {
        this.bezeichnung_methode = bezeichnung_methode;
    }


    /**
     * Gets the bezeichnung_variante value for this Dea_Analysemethode.
     * 
     * @return bezeichnung_variante
     */
    public java.lang.String getBezeichnung_variante() {
        return bezeichnung_variante;
    }


    /**
     * Sets the bezeichnung_variante value for this Dea_Analysemethode.
     * 
     * @param bezeichnung_variante
     */
    public void setBezeichnung_variante(java.lang.String bezeichnung_variante) {
        this.bezeichnung_variante = bezeichnung_variante;
    }


    /**
     * Gets the datum_norm value for this Dea_Analysemethode.
     * 
     * @return datum_norm
     */
    public java.lang.String getDatum_norm() {
        return datum_norm;
    }


    /**
     * Sets the datum_norm value for this Dea_Analysemethode.
     * 
     * @param datum_norm
     */
    public void setDatum_norm(java.lang.String datum_norm) {
        this.datum_norm = datum_norm;
    }


    /**
     * Gets the ergebnistyp_opt value for this Dea_Analysemethode.
     * 
     * @return ergebnistyp_opt
     */
    public java.lang.Integer getErgebnistyp_opt() {
        return ergebnistyp_opt;
    }


    /**
     * Sets the ergebnistyp_opt value for this Dea_Analysemethode.
     * 
     * @param ergebnistyp_opt
     */
    public void setErgebnistyp_opt(java.lang.Integer ergebnistyp_opt) {
        this.ergebnistyp_opt = ergebnistyp_opt;
    }


    /**
     * Gets the gruppe_dev value for this Dea_Analysemethode.
     * 
     * @return gruppe_dev
     */
    public java.lang.String getGruppe_dev() {
        return gruppe_dev;
    }


    /**
     * Sets the gruppe_dev value for this Dea_Analysemethode.
     * 
     * @param gruppe_dev
     */
    public void setGruppe_dev(java.lang.String gruppe_dev) {
        this.gruppe_dev = gruppe_dev;
    }


    /**
     * Gets the methoden_nr value for this Dea_Analysemethode.
     * 
     * @return methoden_nr
     */
    public java.lang.String getMethoden_nr() {
        return methoden_nr;
    }


    /**
     * Sets the methoden_nr value for this Dea_Analysemethode.
     * 
     * @param methoden_nr
     */
    public void setMethoden_nr(java.lang.String methoden_nr) {
        this.methoden_nr = methoden_nr;
    }


    /**
     * Gets the phase_opt value for this Dea_Analysemethode.
     * 
     * @return phase_opt
     */
    public java.lang.Integer getPhase_opt() {
        return phase_opt;
    }


    /**
     * Sets the phase_opt value for this Dea_Analysemethode.
     * 
     * @param phase_opt
     */
    public void setPhase_opt(java.lang.Integer phase_opt) {
        this.phase_opt = phase_opt;
    }


    /**
     * Gets the regelwerk_nr value for this Dea_Analysemethode.
     * 
     * @return regelwerk_nr
     */
    public java.lang.String getRegelwerk_nr() {
        return regelwerk_nr;
    }


    /**
     * Sets the regelwerk_nr value for this Dea_Analysemethode.
     * 
     * @param regelwerk_nr
     */
    public void setRegelwerk_nr(java.lang.String regelwerk_nr) {
        this.regelwerk_nr = regelwerk_nr;
    }


    /**
     * Gets the varianten_nr value for this Dea_Analysemethode.
     * 
     * @return varianten_nr
     */
    public java.lang.String getVarianten_nr() {
        return varianten_nr;
    }


    /**
     * Sets the varianten_nr value for this Dea_Analysemethode.
     * 
     * @param varianten_nr
     */
    public void setVarianten_nr(java.lang.String varianten_nr) {
        this.varianten_nr = varianten_nr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Analysemethode)) return false;
        Dea_Analysemethode other = (Dea_Analysemethode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.analyse_ver==null && other.getAnalyse_ver()==null) || 
             (this.analyse_ver!=null &&
              this.analyse_ver.equals(other.getAnalyse_ver()))) &&
            ((this.beschreibung_analysemet==null && other.getBeschreibung_analysemet()==null) || 
             (this.beschreibung_analysemet!=null &&
              this.beschreibung_analysemet.equals(other.getBeschreibung_analysemet()))) &&
            ((this.bezeichnung_methode==null && other.getBezeichnung_methode()==null) || 
             (this.bezeichnung_methode!=null &&
              this.bezeichnung_methode.equals(other.getBezeichnung_methode()))) &&
            ((this.bezeichnung_variante==null && other.getBezeichnung_variante()==null) || 
             (this.bezeichnung_variante!=null &&
              this.bezeichnung_variante.equals(other.getBezeichnung_variante()))) &&
            ((this.datum_norm==null && other.getDatum_norm()==null) || 
             (this.datum_norm!=null &&
              this.datum_norm.equals(other.getDatum_norm()))) &&
            ((this.ergebnistyp_opt==null && other.getErgebnistyp_opt()==null) || 
             (this.ergebnistyp_opt!=null &&
              this.ergebnistyp_opt.equals(other.getErgebnistyp_opt()))) &&
            ((this.gruppe_dev==null && other.getGruppe_dev()==null) || 
             (this.gruppe_dev!=null &&
              this.gruppe_dev.equals(other.getGruppe_dev()))) &&
            ((this.methoden_nr==null && other.getMethoden_nr()==null) || 
             (this.methoden_nr!=null &&
              this.methoden_nr.equals(other.getMethoden_nr()))) &&
            ((this.phase_opt==null && other.getPhase_opt()==null) || 
             (this.phase_opt!=null &&
              this.phase_opt.equals(other.getPhase_opt()))) &&
            ((this.regelwerk_nr==null && other.getRegelwerk_nr()==null) || 
             (this.regelwerk_nr!=null &&
              this.regelwerk_nr.equals(other.getRegelwerk_nr()))) &&
            ((this.varianten_nr==null && other.getVarianten_nr()==null) || 
             (this.varianten_nr!=null &&
              this.varianten_nr.equals(other.getVarianten_nr())));
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
        if (getAnalyse_ver() != null) {
            _hashCode += getAnalyse_ver().hashCode();
        }
        if (getBeschreibung_analysemet() != null) {
            _hashCode += getBeschreibung_analysemet().hashCode();
        }
        if (getBezeichnung_methode() != null) {
            _hashCode += getBezeichnung_methode().hashCode();
        }
        if (getBezeichnung_variante() != null) {
            _hashCode += getBezeichnung_variante().hashCode();
        }
        if (getDatum_norm() != null) {
            _hashCode += getDatum_norm().hashCode();
        }
        if (getErgebnistyp_opt() != null) {
            _hashCode += getErgebnistyp_opt().hashCode();
        }
        if (getGruppe_dev() != null) {
            _hashCode += getGruppe_dev().hashCode();
        }
        if (getMethoden_nr() != null) {
            _hashCode += getMethoden_nr().hashCode();
        }
        if (getPhase_opt() != null) {
            _hashCode += getPhase_opt().hashCode();
        }
        if (getRegelwerk_nr() != null) {
            _hashCode += getRegelwerk_nr().hashCode();
        }
        if (getVarianten_nr() != null) {
            _hashCode += getVarianten_nr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Analysemethode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Analysemethode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("analyse_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "analyse_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beschreibung_analysemet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beschreibung_analysemet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bezeichnung_methode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bezeichnung_methode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bezeichnung_variante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bezeichnung_variante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datum_norm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datum_norm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ergebnistyp_opt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ergebnistyp_opt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gruppe_dev");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gruppe_dev"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("methoden_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "methoden_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phase_opt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phase_opt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regelwerk_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "regelwerk_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("varianten_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "varianten_nr"));
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
