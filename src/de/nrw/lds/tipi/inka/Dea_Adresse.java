/**
 * Dea_Adresse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka;

public class Dea_Adresse  extends de.nrw.lds.tipi.general.HistoryObject  implements java.io.Serializable {
    private java.lang.Integer adresse_nr;

    private java.lang.Integer adresse_ver;

    private java.lang.String email;

    private java.lang.String fax;

    private java.lang.String hausnr;

    private java.lang.String name1;

    private java.lang.String name2;

    private java.lang.String ort;

    private java.lang.String plz;

    private java.lang.String staatskennung;

    private java.lang.String strasse;

    private java.lang.String telefon;

    private java.lang.String telefon_mobil;

    public Dea_Adresse() {
    }

    public Dea_Adresse(
           java.util.Calendar aenderungs_datum,
           java.util.Calendar erfassungs_datum,
           java.util.Calendar gueltig_bis,
           java.util.Calendar gueltig_von,
           java.lang.Boolean ist_aktuell_jn,
           java.lang.Integer adresse_nr,
           java.lang.Integer adresse_ver,
           java.lang.String email,
           java.lang.String fax,
           java.lang.String hausnr,
           java.lang.String name1,
           java.lang.String name2,
           java.lang.String ort,
           java.lang.String plz,
           java.lang.String staatskennung,
           java.lang.String strasse,
           java.lang.String telefon,
           java.lang.String telefon_mobil) {
        super(
            aenderungs_datum,
            erfassungs_datum,
            gueltig_bis,
            gueltig_von,
            ist_aktuell_jn);
        this.adresse_nr = adresse_nr;
        this.adresse_ver = adresse_ver;
        this.email = email;
        this.fax = fax;
        this.hausnr = hausnr;
        this.name1 = name1;
        this.name2 = name2;
        this.ort = ort;
        this.plz = plz;
        this.staatskennung = staatskennung;
        this.strasse = strasse;
        this.telefon = telefon;
        this.telefon_mobil = telefon_mobil;
    }


    /**
     * Gets the adresse_nr value for this Dea_Adresse.
     * 
     * @return adresse_nr
     */
    public java.lang.Integer getAdresse_nr() {
        return adresse_nr;
    }


    /**
     * Sets the adresse_nr value for this Dea_Adresse.
     * 
     * @param adresse_nr
     */
    public void setAdresse_nr(java.lang.Integer adresse_nr) {
        this.adresse_nr = adresse_nr;
    }


    /**
     * Gets the adresse_ver value for this Dea_Adresse.
     * 
     * @return adresse_ver
     */
    public java.lang.Integer getAdresse_ver() {
        return adresse_ver;
    }


    /**
     * Sets the adresse_ver value for this Dea_Adresse.
     * 
     * @param adresse_ver
     */
    public void setAdresse_ver(java.lang.Integer adresse_ver) {
        this.adresse_ver = adresse_ver;
    }


    /**
     * Gets the email value for this Dea_Adresse.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Dea_Adresse.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the fax value for this Dea_Adresse.
     * 
     * @return fax
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this Dea_Adresse.
     * 
     * @param fax
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the hausnr value for this Dea_Adresse.
     * 
     * @return hausnr
     */
    public java.lang.String getHausnr() {
        return hausnr;
    }


    /**
     * Sets the hausnr value for this Dea_Adresse.
     * 
     * @param hausnr
     */
    public void setHausnr(java.lang.String hausnr) {
        this.hausnr = hausnr;
    }


    /**
     * Gets the name1 value for this Dea_Adresse.
     * 
     * @return name1
     */
    public java.lang.String getName1() {
        return name1;
    }


    /**
     * Sets the name1 value for this Dea_Adresse.
     * 
     * @param name1
     */
    public void setName1(java.lang.String name1) {
        this.name1 = name1;
    }


    /**
     * Gets the name2 value for this Dea_Adresse.
     * 
     * @return name2
     */
    public java.lang.String getName2() {
        return name2;
    }


    /**
     * Sets the name2 value for this Dea_Adresse.
     * 
     * @param name2
     */
    public void setName2(java.lang.String name2) {
        this.name2 = name2;
    }


    /**
     * Gets the ort value for this Dea_Adresse.
     * 
     * @return ort
     */
    public java.lang.String getOrt() {
        return ort;
    }


    /**
     * Sets the ort value for this Dea_Adresse.
     * 
     * @param ort
     */
    public void setOrt(java.lang.String ort) {
        this.ort = ort;
    }


    /**
     * Gets the plz value for this Dea_Adresse.
     * 
     * @return plz
     */
    public java.lang.String getPlz() {
        return plz;
    }


    /**
     * Sets the plz value for this Dea_Adresse.
     * 
     * @param plz
     */
    public void setPlz(java.lang.String plz) {
        this.plz = plz;
    }


    /**
     * Gets the staatskennung value for this Dea_Adresse.
     * 
     * @return staatskennung
     */
    public java.lang.String getStaatskennung() {
        return staatskennung;
    }


    /**
     * Sets the staatskennung value for this Dea_Adresse.
     * 
     * @param staatskennung
     */
    public void setStaatskennung(java.lang.String staatskennung) {
        this.staatskennung = staatskennung;
    }


    /**
     * Gets the strasse value for this Dea_Adresse.
     * 
     * @return strasse
     */
    public java.lang.String getStrasse() {
        return strasse;
    }


    /**
     * Sets the strasse value for this Dea_Adresse.
     * 
     * @param strasse
     */
    public void setStrasse(java.lang.String strasse) {
        this.strasse = strasse;
    }


    /**
     * Gets the telefon value for this Dea_Adresse.
     * 
     * @return telefon
     */
    public java.lang.String getTelefon() {
        return telefon;
    }


    /**
     * Sets the telefon value for this Dea_Adresse.
     * 
     * @param telefon
     */
    public void setTelefon(java.lang.String telefon) {
        this.telefon = telefon;
    }


    /**
     * Gets the telefon_mobil value for this Dea_Adresse.
     * 
     * @return telefon_mobil
     */
    public java.lang.String getTelefon_mobil() {
        return telefon_mobil;
    }


    /**
     * Sets the telefon_mobil value for this Dea_Adresse.
     * 
     * @param telefon_mobil
     */
    public void setTelefon_mobil(java.lang.String telefon_mobil) {
        this.telefon_mobil = telefon_mobil;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dea_Adresse)) return false;
        Dea_Adresse other = (Dea_Adresse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.adresse_nr==null && other.getAdresse_nr()==null) || 
             (this.adresse_nr!=null &&
              this.adresse_nr.equals(other.getAdresse_nr()))) &&
            ((this.adresse_ver==null && other.getAdresse_ver()==null) || 
             (this.adresse_ver!=null &&
              this.adresse_ver.equals(other.getAdresse_ver()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.hausnr==null && other.getHausnr()==null) || 
             (this.hausnr!=null &&
              this.hausnr.equals(other.getHausnr()))) &&
            ((this.name1==null && other.getName1()==null) || 
             (this.name1!=null &&
              this.name1.equals(other.getName1()))) &&
            ((this.name2==null && other.getName2()==null) || 
             (this.name2!=null &&
              this.name2.equals(other.getName2()))) &&
            ((this.ort==null && other.getOrt()==null) || 
             (this.ort!=null &&
              this.ort.equals(other.getOrt()))) &&
            ((this.plz==null && other.getPlz()==null) || 
             (this.plz!=null &&
              this.plz.equals(other.getPlz()))) &&
            ((this.staatskennung==null && other.getStaatskennung()==null) || 
             (this.staatskennung!=null &&
              this.staatskennung.equals(other.getStaatskennung()))) &&
            ((this.strasse==null && other.getStrasse()==null) || 
             (this.strasse!=null &&
              this.strasse.equals(other.getStrasse()))) &&
            ((this.telefon==null && other.getTelefon()==null) || 
             (this.telefon!=null &&
              this.telefon.equals(other.getTelefon()))) &&
            ((this.telefon_mobil==null && other.getTelefon_mobil()==null) || 
             (this.telefon_mobil!=null &&
              this.telefon_mobil.equals(other.getTelefon_mobil())));
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
        if (getAdresse_nr() != null) {
            _hashCode += getAdresse_nr().hashCode();
        }
        if (getAdresse_ver() != null) {
            _hashCode += getAdresse_ver().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getHausnr() != null) {
            _hashCode += getHausnr().hashCode();
        }
        if (getName1() != null) {
            _hashCode += getName1().hashCode();
        }
        if (getName2() != null) {
            _hashCode += getName2().hashCode();
        }
        if (getOrt() != null) {
            _hashCode += getOrt().hashCode();
        }
        if (getPlz() != null) {
            _hashCode += getPlz().hashCode();
        }
        if (getStaatskennung() != null) {
            _hashCode += getStaatskennung().hashCode();
        }
        if (getStrasse() != null) {
            _hashCode += getStrasse().hashCode();
        }
        if (getTelefon() != null) {
            _hashCode += getTelefon().hashCode();
        }
        if (getTelefon_mobil() != null) {
            _hashCode += getTelefon_mobil().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dea_Adresse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Adresse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresse_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresse_ver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse_ver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hausnr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hausnr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "plz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("staatskennung");
        elemField.setXmlName(new javax.xml.namespace.QName("", "staatskennung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strasse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "strasse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefon");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefon_mobil");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefon_mobil"));
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
