/**
 * Copyright 2005-2011, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */

package de.bielefeld.umweltamt.aui.mappings.basis;

public class AbstractBasisObjektverknuepfung implements java.io.Serializable {
    private static final long serialVersionUID = 7508674418350138126L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    private java.lang.Integer id;

    private BasisObjekt basisObjektByIstVerknuepftMit;

    private BasisObjekt basisObjektByObjekt;

    /** default constructor */
    public AbstractBasisObjektverknuepfung() {
    }

    /** minimal constructor */
    public AbstractBasisObjektverknuepfung(java.lang.Integer id) {
        this.hashValue = 0;
        this.id = id;
    }

    /** full constructor */
    public AbstractBasisObjektverknuepfung(java.lang.Integer id,
        BasisObjekt basisObjektByIstVerknuepftMit,
        BasisObjekt basisObjektByObjekt) {
        this.id = id;
        this.basisObjektByIstVerknuepftMit = basisObjektByIstVerknuepftMit;
        this.basisObjektByObjekt = basisObjektByObjekt;
    }

    public java.lang.Integer getId() {
        return this.id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    public BasisObjekt getBasisObjektByIstVerknuepftMit() {
        return this.basisObjektByIstVerknuepftMit;
    }

    public void setBasisObjektByIstVerknuepftMit(
        BasisObjekt basisObjektByIstVerknuepftMit) {
        this.basisObjektByIstVerknuepftMit = basisObjektByIstVerknuepftMit;
    }

    public BasisObjekt getBasisObjektByObjekt() {
        return this.basisObjektByObjekt;
    }

    public void setBasisObjektByObjekt(BasisObjekt basisObjektByObjekt) {
        this.basisObjektByObjekt = basisObjektByObjekt;
    }

    /**
     * Implementation of the equals comparison on the basis of equality of the
     * primary key values.
     * @param rhs
     * @return boolean
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs == null)
            return false;
        if (!(rhs instanceof BasisObjekt))
            return false;
        BasisObjekt that = (BasisObjekt) rhs;
        if (this.getId() != null && that.getObjektid() != null) {
            if (!this.getId().equals(that.getObjektid())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Implementation of the hashCode method conforming to the Bloch pattern
     * with the exception of array properties (these are very unlikely primary
     * key types).
     * @return int
     */
    @Override
    public int hashCode() {
        if (this.hashValue == 0) {
            int result = 17;
            int objektidValue = this.getId() == null ? 0 : this.getId()
                .hashCode();
            result = result * 37 + objektidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
