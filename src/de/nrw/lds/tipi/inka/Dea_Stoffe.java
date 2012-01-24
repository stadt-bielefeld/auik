/**
 * Dea_Stoffe.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Stoffe  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.String beschreibung;

    private java.lang.String cas_nr;

    private java.lang.Integer igs_nr;

    private java.lang.String iupac;

    private java.lang.String kurzname;

    private java.lang.Integer stoff_nr;

    private java.lang.Integer stoff_ver;

    private java.lang.String summenformel;

    private java.lang.String synonym1;

    private java.lang.String synonym2;

    private java.lang.String synonym3;

    private java.lang.String synonym4;

    private java.lang.String synonym5;

    private java.lang.String trivialname_rsto;

    public Dea_Stoffe() {
    }

    public Dea_Stoffe(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.String beschreibung,
           java.lang.String cas_nr,
           java.lang.Integer igs_nr,
           java.lang.String iupac,
           java.lang.String kurzname,
           java.lang.Integer stoff_nr,
           java.lang.Integer stoff_ver,
           java.lang.String summenformel,
           java.lang.String synonym1,
           java.lang.String synonym2,
           java.lang.String synonym3,
           java.lang.String synonym4,
           java.lang.String synonym5,
           java.lang.String trivialname_rsto) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.beschreibung = beschreibung;
        this.cas_nr = cas_nr;
        this.igs_nr = igs_nr;
        this.iupac = iupac;
        this.kurzname = kurzname;
        this.stoff_nr = stoff_nr;
        this.stoff_ver = stoff_ver;
        this.summenformel = summenformel;
        this.synonym1 = synonym1;
        this.synonym2 = synonym2;
        this.synonym3 = synonym3;
        this.synonym4 = synonym4;
        this.synonym5 = synonym5;
        this.trivialname_rsto = trivialname_rsto;
    }


    /**
     * Gets the beschreibung value for this Dea_Stoffe.
     * 
     * @return beschreibung
     */
    public java.lang.String getBeschreibung() {
        return beschreibung;
    }


    /**
     * Sets the beschreibung value for this Dea_Stoffe.
     * 
     * @param beschreibung
     */
    public void setBeschreibung(java.lang.String beschreibung) {
        this.beschreibung = beschreibung;
    }


    /**
     * Gets the cas_nr value for this Dea_Stoffe.
     * 
     * @return cas_nr
     */
    public java.lang.String getCas_nr() {
        return cas_nr;
    }


    /**
     * Sets the cas_nr value for this Dea_Stoffe.
     * 
     * @param cas_nr
     */
    public void setCas_nr(java.lang.String cas_nr) {
        this.cas_nr = cas_nr;
    }


    /**
     * Gets the igs_nr value for this Dea_Stoffe.
     * 
     * @return igs_nr
     */
    public java.lang.Integer getIgs_nr() {
        return igs_nr;
    }


    /**
     * Sets the igs_nr value for this Dea_Stoffe.
     * 
     * @param igs_nr
     */
    public void setIgs_nr(java.lang.Integer igs_nr) {
        this.igs_nr = igs_nr;
    }


    /**
     * Gets the iupac value for this Dea_Stoffe.
     * 
     * @return iupac
     */
    public java.lang.String getIupac() {
        return iupac;
    }


    /**
     * Sets the iupac value for this Dea_Stoffe.
     * 
     * @param iupac
     */
    public void setIupac(java.lang.String iupac) {
        this.iupac = iupac;
    }


    /**
     * Gets the kurzname value for this Dea_Stoffe.
     * 
     * @return kurzname
     */
    public java.lang.String getKurzname() {
        return kurzname;
    }


    /**
     * Sets the kurzname value for this Dea_Stoffe.
     * 
     * @param kurzname
     */
    public void setKurzname(java.lang.String kurzname) {
        this.kurzname = kurzname;
    }


    /**
     * Gets the stoff_nr value for this Dea_Stoffe.
     * 
     * @return stoff_nr
     */
    public java.lang.Integer getStoff_nr() {
        return stoff_nr;
    }


    /**
     * Sets the stoff_nr value for this Dea_Stoffe.
     * 
     * @param stoff_nr
     */
    public void setStoff_nr(java.lang.Integer stoff_nr) {
        this.stoff_nr = stoff_nr;
    }


    /**
     * Gets the stoff_ver value for this Dea_Stoffe.
     * 
     * @return stoff_ver
     */
    public java.lang.Integer getStoff_ver() {
        return stoff_ver;
    }


    /**
     * Sets the stoff_ver value for this Dea_Stoffe.
     * 
     * @param stoff_ver
     */
    public void setStoff_ver(java.lang.Integer stoff_ver) {
        this.stoff_ver = stoff_ver;
    }


    /**
     * Gets the summenformel value for this Dea_Stoffe.
     * 
     * @return summenformel
     */
    public java.lang.String getSummenformel() {
        return summenformel;
    }


    /**
     * Sets the summenformel value for this Dea_Stoffe.
     * 
     * @param summenformel
     */
    public void setSummenformel(java.lang.String summenformel) {
        this.summenformel = summenformel;
    }


    /**
     * Gets the synonym1 value for this Dea_Stoffe.
     * 
     * @return synonym1
     */
    public java.lang.String getSynonym1() {
        return synonym1;
    }


    /**
     * Sets the synonym1 value for this Dea_Stoffe.
     * 
     * @param synonym1
     */
    public void setSynonym1(java.lang.String synonym1) {
        this.synonym1 = synonym1;
    }


    /**
     * Gets the synonym2 value for this Dea_Stoffe.
     * 
     * @return synonym2
     */
    public java.lang.String getSynonym2() {
        return synonym2;
    }


    /**
     * Sets the synonym2 value for this Dea_Stoffe.
     * 
     * @param synonym2
     */
    public void setSynonym2(java.lang.String synonym2) {
        this.synonym2 = synonym2;
    }


    /**
     * Gets the synonym3 value for this Dea_Stoffe.
     * 
     * @return synonym3
     */
    public java.lang.String getSynonym3() {
        return synonym3;
    }


    /**
     * Sets the synonym3 value for this Dea_Stoffe.
     * 
     * @param synonym3
     */
    public void setSynonym3(java.lang.String synonym3) {
        this.synonym3 = synonym3;
    }


    /**
     * Gets the synonym4 value for this Dea_Stoffe.
     * 
     * @return synonym4
     */
    public java.lang.String getSynonym4() {
        return synonym4;
    }


    /**
     * Sets the synonym4 value for this Dea_Stoffe.
     * 
     * @param synonym4
     */
    public void setSynonym4(java.lang.String synonym4) {
        this.synonym4 = synonym4;
    }


    /**
     * Gets the synonym5 value for this Dea_Stoffe.
     * 
     * @return synonym5
     */
    public java.lang.String getSynonym5() {
        return synonym5;
    }


    /**
     * Sets the synonym5 value for this Dea_Stoffe.
     * 
     * @param synonym5
     */
    public void setSynonym5(java.lang.String synonym5) {
        this.synonym5 = synonym5;
    }


    /**
     * Gets the trivialname_rsto value for this Dea_Stoffe.
     * 
     * @return trivialname_rsto
     */
    public java.lang.String getTrivialname_rsto() {
        return trivialname_rsto;
    }


    /**
     * Sets the trivialname_rsto value for this Dea_Stoffe.
     * 
     * @param trivialname_rsto
     */
    public void setTrivialname_rsto(java.lang.String trivialname_rsto) {
        this.trivialname_rsto = trivialname_rsto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Stoffe)) return false;
        Dea_Stoffe other = (Dea_Stoffe) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.beschreibung==null && other.getBeschreibung()==null) || 
             (this.beschreibung!=null &&
              this.beschreibung.equals(other.getBeschreibung()))) &&
            ((this.cas_nr==null && other.getCas_nr()==null) || 
             (this.cas_nr!=null &&
              this.cas_nr.equals(other.getCas_nr()))) &&
            ((this.igs_nr==null && other.getIgs_nr()==null) || 
             (this.igs_nr!=null &&
              this.igs_nr.equals(other.getIgs_nr()))) &&
            ((this.iupac==null && other.getIupac()==null) || 
             (this.iupac!=null &&
              this.iupac.equals(other.getIupac()))) &&
            ((this.kurzname==null && other.getKurzname()==null) || 
             (this.kurzname!=null &&
              this.kurzname.equals(other.getKurzname()))) &&
            ((this.stoff_nr==null && other.getStoff_nr()==null) || 
             (this.stoff_nr!=null &&
              this.stoff_nr.equals(other.getStoff_nr()))) &&
            ((this.stoff_ver==null && other.getStoff_ver()==null) || 
             (this.stoff_ver!=null &&
              this.stoff_ver.equals(other.getStoff_ver()))) &&
            ((this.summenformel==null && other.getSummenformel()==null) || 
             (this.summenformel!=null &&
              this.summenformel.equals(other.getSummenformel()))) &&
            ((this.synonym1==null && other.getSynonym1()==null) || 
             (this.synonym1!=null &&
              this.synonym1.equals(other.getSynonym1()))) &&
            ((this.synonym2==null && other.getSynonym2()==null) || 
             (this.synonym2!=null &&
              this.synonym2.equals(other.getSynonym2()))) &&
            ((this.synonym3==null && other.getSynonym3()==null) || 
             (this.synonym3!=null &&
              this.synonym3.equals(other.getSynonym3()))) &&
            ((this.synonym4==null && other.getSynonym4()==null) || 
             (this.synonym4!=null &&
              this.synonym4.equals(other.getSynonym4()))) &&
            ((this.synonym5==null && other.getSynonym5()==null) || 
             (this.synonym5!=null &&
              this.synonym5.equals(other.getSynonym5()))) &&
            ((this.trivialname_rsto==null && other.getTrivialname_rsto()==null) || 
             (this.trivialname_rsto!=null &&
              this.trivialname_rsto.equals(other.getTrivialname_rsto())));
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
        if (getBeschreibung() != null) {
            _hashCode += getBeschreibung().hashCode();
        }
        if (getCas_nr() != null) {
            _hashCode += getCas_nr().hashCode();
        }
        if (getIgs_nr() != null) {
            _hashCode += getIgs_nr().hashCode();
        }
        if (getIupac() != null) {
            _hashCode += getIupac().hashCode();
        }
        if (getKurzname() != null) {
            _hashCode += getKurzname().hashCode();
        }
        if (getStoff_nr() != null) {
            _hashCode += getStoff_nr().hashCode();
        }
        if (getStoff_ver() != null) {
            _hashCode += getStoff_ver().hashCode();
        }
        if (getSummenformel() != null) {
            _hashCode += getSummenformel().hashCode();
        }
        if (getSynonym1() != null) {
            _hashCode += getSynonym1().hashCode();
        }
        if (getSynonym2() != null) {
            _hashCode += getSynonym2().hashCode();
        }
        if (getSynonym3() != null) {
            _hashCode += getSynonym3().hashCode();
        }
        if (getSynonym4() != null) {
            _hashCode += getSynonym4().hashCode();
        }
        if (getSynonym5() != null) {
            _hashCode += getSynonym5().hashCode();
        }
        if (getTrivialname_rsto() != null) {
            _hashCode += getTrivialname_rsto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Stoffe.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Stoffe"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beschreibung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beschreibung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cas_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cas_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("igs_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "igs_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iupac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iupac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kurzname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kurzname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stoff_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stoff_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stoff_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stoff_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summenformel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "summenformel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("synonym1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "synonym1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("synonym2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "synonym2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("synonym3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "synonym3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("synonym4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "synonym4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("synonym5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "synonym5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trivialname_rsto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trivialname_rsto"));
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
