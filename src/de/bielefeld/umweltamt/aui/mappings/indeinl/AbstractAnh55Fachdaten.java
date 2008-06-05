package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the ANH_55_FACHDATEN table. 
 * You can customize the behavior of this class by editing the class, {@link Anh55Fachdaten()}.
 */
public abstract class AbstractAnh55Fachdaten 
    implements Serializable
{
    /** The cached hash code value for this instance.  Settting to 0 triggers re-calculation. */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer id;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;
    
//    private java.lang.Integer objektId;
    private java.lang.Boolean abgemeldet;
    private java.lang.Boolean putztuecher;
    private java.lang.Boolean teppich;
    private java.lang.Boolean matten;
    private java.lang.Boolean haushaltstex;
    private java.lang.Boolean berufskl;
    private java.lang.Boolean gasthotel;
    private java.lang.Boolean krankenhaus;
    private java.lang.Boolean heimwaesche;
    private java.lang.Integer anteilwaschgut;
    private java.lang.Boolean vlies;
    private java.lang.Boolean fischfleisch;
    private java.lang.Integer anteilgesamtgut;
    private java.lang.Boolean betrwasseraufber;
    private java.lang.Boolean chlor;
    private java.lang.Boolean aktivchlor;
    private java.lang.Boolean loesungsmittel;
    private java.lang.String sachbearbeiter;
    private java.lang.String entgebId;
    private java.lang.String bemerkungen;
    private java.lang.String mengewaesche;
    private java.lang.String sonsttex;
    private java.lang.String monatwasserverb;
    private java.lang.String waschsituation;
    private java.lang.String ansprechpartner;
    private java.lang.String branche;

    /**
     * Simple constructor of AbstractAnh55Fachdaten instances.
     */
    public AbstractAnh55Fachdaten()
    {
    }

    /**
     * Constructor of AbstractAnh55Fachdaten instances given a simple primary key.
     * @param id
     */
    public AbstractAnh55Fachdaten(java.lang.Integer id)
    {
        this.setId(id);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getId()
    {
        return id;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param id
     */
    public void setId(java.lang.Integer id)
    {
        this.hashValue = 0;
        this.id = id;
    }

    /**
     * Return the value of the OBJID column.
     * @return BasisObjekt
     */
    public BasisObjekt getBasisObjekt()
    {
        return this.basisObjekt;
    }

    /**
     * Set the value of the OBJID column.
     * @param basisObjekt
     */
    public void setBasisObjekt(BasisObjekt basisObjekt)
    {
        this.basisObjekt = basisObjekt;
    }


    /**
	 * @return the abgemeldet
	 */
	public java.lang.Boolean getAbgemeldet() {
		return abgemeldet;
	}

	/**
	 * @param abgemeldet the abgemeldet to set
	 */
	public void setAbgemeldet(java.lang.Boolean abgemeldet) {
		this.abgemeldet = abgemeldet;
	}

	/**
	 * @return the aktivchlor
	 */
	public java.lang.Boolean getAktivchlor() {
		return aktivchlor;
	}

	/**
	 * @param aktivchlor the aktivchlor to set
	 */
	public void setAktivchlor(java.lang.Boolean aktivchlor) {
		this.aktivchlor = aktivchlor;
	}

	/**
	 * @return the ansprechpartner
	 */
	public java.lang.String getAnsprechpartner() {
		return ansprechpartner;
	}

	/**
	 * @param ansprechpartner the ansprechpartner to set
	 */
	public void setAnsprechpartner(java.lang.String ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
	}

	/**
	 * @return the anteilgesamtgut
	 */
	public java.lang.Integer getAnteilgesamtgut() {
		return anteilgesamtgut;
	}

	/**
	 * @param anteilgesamtgut the anteilgesamtgut to set
	 */
	public void setAnteilgesamtgut(java.lang.Integer anteilgesamtgut) {
		this.anteilgesamtgut = anteilgesamtgut;
	}

	/**
	 * @return the anteilwaschgut
	 */
	public java.lang.Integer getAnteilwaschgut() {
		return anteilwaschgut;
	}

	/**
	 * @param anteilwaschgut the anteilwaschgut to set
	 */
	public void setAnteilwaschgut(java.lang.Integer anteilwaschgut) {
		this.anteilwaschgut = anteilwaschgut;
	}

	/**
	 * @return the berufskl
	 */
	public java.lang.Boolean getBerufskl() {
		return berufskl;
	}

	/**
	 * @param berufskl the berufskl to set
	 */
	public void setBerufskl(java.lang.Boolean berufskl) {
		this.berufskl = berufskl;
	}

	/**
	 * @return the betrwasseraufber
	 */
	public java.lang.Boolean getBetrwasseraufber() {
		return betrwasseraufber;
	}

	/**
	 * @param betrwasseraufber the betrwasseraufber to set
	 */
	public void setBetrwasseraufber(java.lang.Boolean betrwasseraufber) {
		this.betrwasseraufber = betrwasseraufber;
	}

	/**
	 * @return the branche
	 */
	public java.lang.String getBranche() {
		return branche;
	}

	/**
	 * @param branche the branche to set
	 */
	public void setBranche(java.lang.String branche) {
		this.branche = branche;
	}

	/**
	 * @return the chlor
	 */
	public java.lang.Boolean getChlor() {
		return chlor;
	}

	/**
	 * @param chlor the chlor to set
	 */
	public void setChlor(java.lang.Boolean chlor) {
		this.chlor = chlor;
	}

	/**
	 * @return the entgebId
	 */
	public java.lang.String getEntgebId() {
		return entgebId;
	}

	/**
	 * @param entgebId the entgebId to set
	 */
	public void setEntgebId(java.lang.String entgebId) {
		this.entgebId = entgebId;
	}

	/**
	 * @return the fischfleisch
	 */
	public java.lang.Boolean getFischfleisch() {
		return fischfleisch;
	}

	/**
	 * @param fischfleisch the fischfleisch to set
	 */
	public void setFischfleisch(java.lang.Boolean fischfleisch) {
		this.fischfleisch = fischfleisch;
	}

	/**
	 * @return the gasthotel
	 */
	public java.lang.Boolean getGasthotel() {
		return gasthotel;
	}

	/**
	 * @param gasthotel the gasthotel to set
	 */
	public void setGasthotel(java.lang.Boolean gasthotel) {
		this.gasthotel = gasthotel;
	}

	/**
	 * @return the haushaltstex
	 */
	public java.lang.Boolean getHaushaltstex() {
		return haushaltstex;
	}

	/**
	 * @param haushaltstex the haushaltstex to set
	 */
	public void setHaushaltstex(java.lang.Boolean haushaltstex) {
		this.haushaltstex = haushaltstex;
	}

	/**
	 * @return the heimwaesche
	 */
	public java.lang.Boolean getHeimwaesche() {
		return heimwaesche;
	}

	/**
	 * @param heimwaesche the heimwaesche to set
	 */
	public void setHeimwaesche(java.lang.Boolean heimwaesche) {
		this.heimwaesche = heimwaesche;
	}

	/**
	 * @return the krankenhaus
	 */
	public java.lang.Boolean getKrankenhaus() {
		return krankenhaus;
	}

	/**
	 * @param krankenhaus the krankenhaus to set
	 */
	public void setKrankenhaus(java.lang.Boolean krankenhaus) {
		this.krankenhaus = krankenhaus;
	}

	/**
	 * @return the matten
	 */
	public java.lang.Boolean getMatten() {
		return matten;
	}

	/**
	 * @param matten the matten to set
	 */
	public void setMatten(java.lang.Boolean matten) {
		this.matten = matten;
	}

	/**
	 * @return the mengewaesche
	 */
	public java.lang.String getMengewaesche() {
		return mengewaesche;
	}

	/**
	 * @param mengewaesche the mengewaesche to set
	 */
	public void setMengewaesche(java.lang.String mengewaesche) {
		this.mengewaesche = mengewaesche;
	}

	/**
	 * @return the monatwasserverb
	 */
	public java.lang.String getMonatwasserverb() {
		return monatwasserverb;
	}

	/**
	 * @param monatwasserverb the monatwasserverb to set
	 */
	public void setMonatwasserverb(java.lang.String monatwasserverb) {
		this.monatwasserverb = monatwasserverb;
	}

//	/**
//	 * @return the objektId
//	 */
//	public java.lang.Integer getObjektId() {
//		return objektId;
//	}
//
//	/**
//	 * @param objektId the objektId to set
//	 */
//	public void setObjektId(java.lang.Integer objektId) {
//		this.objektId = objektId;
//	}

	/**
	 * @return the putztuecher
	 */
	public java.lang.Boolean getPutztuecher() {
		return putztuecher;
	}

	/**
	 * @param putztuecher the putztuecher to set
	 */
	public void setPutztuecher(java.lang.Boolean putztuecher) {
		this.putztuecher = putztuecher;
	}

	/**
	 * @return the sachbearbeiter
	 */
	public java.lang.String getSachbearbeiter() {
		return sachbearbeiter;
	}

	/**
	 * @param sachbearbeiter the sachbearbeiter to set
	 */
	public void setSachbearbeiter(java.lang.String sachbearbeiter) {
		this.sachbearbeiter = sachbearbeiter;
	}

	/**
	 * @return the sonsttex
	 */
	public java.lang.String getSonsttex() {
		return sonsttex;
	}

	/**
	 * @param sonsttex the sonsttex to set
	 */
	public void setSonsttex(java.lang.String sonsttex) {
		this.sonsttex = sonsttex;
	}

	/**
	 * @return the teppich
	 */
	public java.lang.Boolean getTeppich() {
		return teppich;
	}

	/**
	 * @param teppich the teppich to set
	 */
	public void setTeppich(java.lang.Boolean teppich) {
		this.teppich = teppich;
	}

	/**
	 * @return the vlies
	 */
	public java.lang.Boolean getVlies() {
		return vlies;
	}

	/**
	 * @param vlies the vlies to set
	 */
	public void setVlies(java.lang.Boolean vlies) {
		this.vlies = vlies;
	}

	/**
	 * @return the waschsituation
	 */
	public java.lang.String getWaschsituation() {
		return waschsituation;
	}

	/**
	 * @param waschsituation the waschsituation to set
	 */
	public void setWaschsituation(java.lang.String waschsituation) {
		this.waschsituation = waschsituation;
	}
	

	public java.lang.Boolean getLoesungsmittel() {
		return loesungsmittel;
	}

	public void setLoesungsmittel(java.lang.Boolean loesungsmittel) {
		this.loesungsmittel = loesungsmittel;
	}

	/**
     * Return the value of the BEMERKUNGEN column.
     * @return java.lang.String
     */
    public java.lang.String getBemerkungen() {
        return this.bemerkungen;
    }

    /**
     * Set the value of the BEMERKUNGEN column.
     * @param bemerkungen
     */
    public void setBemerkungen(java.lang.String bemerkungen)
    {
        this.bemerkungen = bemerkungen;
    }

    /**
     * Implementation of the equals comparison on the basis of equality of the primary key values.
     * @param rhs
     * @return boolean
     */
    public boolean equals(Object rhs)
    {
        if (rhs == null)
            return false;
        if (! (rhs instanceof Anh55Fachdaten))
            return false;
        Anh55Fachdaten that = (Anh55Fachdaten) rhs;
        if (this.getId() != null && that.getId() != null)
        {
            if (! this.getId().equals(that.getId()))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Implementation of the hashCode method conforming to the Bloch pattern with
     * the exception of array properties (these are very unlikely primary key types).
     * @return int
     */
    public int hashCode()
    {
        if (this.hashValue == 0)
        {
            int result = 17;
            int idValue = this.getId() == null ? 0 : this.getId().hashCode();
            result = result * 37 + idValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
