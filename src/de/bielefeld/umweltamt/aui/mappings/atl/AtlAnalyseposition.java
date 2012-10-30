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

package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;

/**
 * A class that represents a row in the 'ATL_ANALYSEPOSITION' table. This class
 * may be customized as it is never re-generated after being created.
 */
public class AtlAnalyseposition extends AbstractAtlAnalyseposition implements
        Serializable {
    private static final long serialVersionUID = 8611630449814009888L;

    /** Simple constructor of AtlAnalyseposition instances. */
    public AtlAnalyseposition() {
        this(new Float(0.0));
    }

    /**
     * Constructor of AtlAnalyseposition instances given a value.
     * @param wert
     */
    public AtlAnalyseposition(Float wert) {
        setWert(wert);
    }

    /**
     * Constructor of AtlAnalyseposition instances given a simple primary key.
     * @param id
     */
    public AtlAnalyseposition(java.lang.Integer id) {
        super(id);
        setWert(new Float(0.0));
    }

    /* Add customized code below */

    /**
     * Vergleich auf Basis der Primary-Keys, wenn diese vorhanden sind, sonst
     * Vergleich von Wert, Parameter, Einheit und "Analyse von".
     * @param rhs
     * @return boolean
     */
    @Override
    public boolean equals(Object rhs) {
        // Wenn das andere Objekt null ist, sind wir nicht gleich
        if (rhs == null) {
            return false;
        }
        // Wenn das andere Objekt auf uns verweist, sind wir natürlich gleich
        if (this == rhs) {
            return true;
        }
        // Wenn das andere Objekt keine AtlAnalyseposition ist, können wir nicht
        // gleich sein
        if (!(rhs instanceof AtlAnalyseposition)) {
            return false;
        }

        // Das andere Objekt ist also eine AtlAnalyseposition.
        AtlAnalyseposition that = (AtlAnalyseposition) rhs;

        // Wenn beide einen Primary-Key haben...
        if (this.getId() != null && that.getId() != null) {
            // ... und diese nicht gleich sind ...
            if (!this.getId().equals(that.getId())) {
                // ... sind die Objekte auch nicht gleich.
                return false;
            } else {
                // ... wenn sie gleich sind, sind auch beide Objekte gleich.
                return true;
            }
        } else if (this.getId() != null || that.getId() != null) {
            // Wenn NUR einer von beiden einen Key hat,
            // können beide auch nicht gleich sein.
            return false;
        }

        // Da mindestens eins der zu vergleichenden Objekte keinen Primary-Key
        // hat müssen wir an Hand von anderen Kriterien entscheiden, ob beide
        // Objekte gleich sind.

        // Vergleich von Wert
        if (this.getWert() != null) {
            if (!this.getWert().equals(that.getWert())) {
                return false;
            }
        } else {
            if (that.getWert() != null) {
                return false;
            }
        }

        // Vergleich von AtlParameter
        if (this.getAtlParameter() != null) {
            if (!this.getAtlParameter().equals(that.getAtlParameter())) {
                return false;
            }
        } else {
            if (that.getAtlParameter() != null) {
                return false;
            }
        }

        // Vergleich von AtlEinheit
        if (this.getAtlEinheiten() != null) {
            if (!this.getAtlEinheiten().equals(that.getAtlEinheiten())) {
                return false;
            }
        } else {
            if (that.getAtlEinheiten() != null) {
                return false;
            }
        }

        // Vergleich von AnalyseVon
        if (this.getAnalyseVon() != null) {
            if (!this.getAnalyseVon().equals(that.getAnalyseVon())) {
                return false;
            }
        } else {
            if (that.getAnalyseVon() != null) {
                return false;
            }
        }

        // Wenn uns bis hier keine "Ungleichheit" aufgefallen ist, sind wir
        // wohl gleich.
        return true;
    }

    /**
     * Hashcode auf Basis des Primary-Keys, wenn dieser existiert, sonst auf
     * Basis von von Wert, Parameter, Einheit und "Analyse von".
     * @return int
     */
    @Override
    public int hashCode() {
        if (this.hashValue == 0) {
            int result = 17;
            int idValue = 0;
            if (getId() != null) {
                idValue = getId().hashCode();
            } else if (getWert() != null) {
                idValue = this.getWert().hashCode();
                if (getAtlParameter() != null) {
                    result += getAtlParameter().hashCode();
                }
                if (getAtlEinheiten() != null) {
                    result += getAtlEinheiten().hashCode();
                }
                if (getAnalyseVon() != null) {
                    result += getAnalyseVon().hashCode();
                }
            }
            result = 37 * result + idValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> or return a
     * String for the gui.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this);
    }

    public static boolean saveAnalyseposition(AtlAnalyseposition pos) {
        return new DatabaseAccess().saveOrUpdate(pos);
    }

    public static boolean removeAnalyseposition(AtlAnalyseposition pos) {
        return new DatabaseAccess().delete(pos);
    }
}
