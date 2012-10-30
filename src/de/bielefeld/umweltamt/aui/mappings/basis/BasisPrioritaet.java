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

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;

/**
 * Home object for domain model class BasisPrioritaet.
 * @see AbstractBasisPrioritaet.BasisPrioritaet
 * @author Hibernate Tools
 */
public class BasisPrioritaet extends AbstractBasisPrioritaet implements
    Serializable {
    private static final long serialVersionUID = 7751778530285997527L;

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this);
    }

    public static BasisPrioritaet getPrioritaet(BasisObjekt basisObjekt) {
        String query = "FROM BasisPrioritaet as bp "
            + "WHERE bp.basisStandort.standortid = :standortid "
            + "and bp.basisBetreiber.betreiberid = :betreiberid";

        return (BasisPrioritaet) new DatabaseAccess().createQuery(query)
                .setInteger("standortid",
                    basisObjekt.getBasisStandort().getStandortid())
                .setInteger("betreiberid",
                    basisObjekt.getBasisBetreiber().getBetreiberid())
                .uniqueResult();
    }

    /**
     * Speichert eine Priorität in der Datenbank.
     * @param obj Das Objekt zu dem die Priorität gespeichert werden soll.
     * @param prio Die zu speichernde Priorität
     * @return Das gespeicherte Prioritätsobjekt.
     */
    public static BasisPrioritaet saveBasisPrioritaet(
            BasisObjekt obj, Integer prio) {
        BasisPrioritaet prioritaet = null;
        BasisPrioritaetId prioId = null;

        if (getPrioritaet(obj) != null) {
            prioritaet = getPrioritaet(obj);
            prioritaet.setPrioritaet(prio);
        } else {
            prioritaet = new BasisPrioritaet();
            prioId = new BasisPrioritaetId();

            prioId.setStandortId(obj.getBasisStandort().getStandortid());
            prioId.setBetreiberId(obj.getBasisBetreiber().getBetreiberid());
            prioritaet.setId(prioId);

            prioritaet.setBasisBetreiber(obj.getBasisBetreiber());
            prioritaet.setBasisStandort(obj.getBasisStandort());
            prioritaet.setPrioritaet(prio);
        }

        return (BasisPrioritaet) new DatabaseAccess().merge(prioritaet);
    }
}
