/**
 * Copyright 2005-2042, Stadt Bielefeld
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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_49_VERWALTUNGSVERF' table.
 * This class may be customized as it is never re-generated
 * after being created.
 * <br><br>
 * Well, not really. I just copied this from a generated class and modified it!
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class Anh49Verwaltungsverfahren
    extends AbstractAnh49Verwaltungsverfahren
    implements Serializable
{
	private static final long serialVersionUID = 7696154300748058009L;

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Simple constructor of Anh49Verwaltungsverfahren instances. */
    public Anh49Verwaltungsverfahren() {
    	super();
    }

    /**
     * Constructor of Anh49Verwaltungsverfahren instances given a simple
     * primary key.
     * @param verwaltungsverfahrenID
     */
    public Anh49Verwaltungsverfahren(java.lang.Integer verwaltungsverfahrenID) {
        super(verwaltungsverfahrenID);
    }

    @Override
    public String toString() {
        return "[Datum: " + AuikUtils.getStringFromDate(this.getDatum())
            + ", Maßnahme: " + this.getMassnahme()
            + " (" + ((this.isAbgeschlossen()!=null && this.isAbgeschlossen())
                        ?"":"nicht ") + "abgeschlossen)"
            + ", SachbearbeiterIn: " + this.getSachbearbeiterIn()
            + (this.getWiedervorlage()==null?"":", Wiedervorlage: "
            + AuikUtils.getStringFromDate(this.getWiedervorlage()))
            + "]";
    }

    /**
     * Get all "Verwaltungsverfahren" for a given "Anhang 49" object
     * @param fachdaten The "Anhang 49" object
     * @return List<Anh49Verwaltungsverfahren>
     */
    public static List<Anh49Verwaltungsverfahren>
    		getVerwaltungsverfahren(Anh49Fachdaten fachdaten) {
        List<Anh49Verwaltungsverfahren> verwaltungsverfahrensListe = null;
        verwaltungsverfahrensListe = (List<Anh49Verwaltungsverfahren>)
            new DatabaseAccess().createQuery(
                    "from Anh49Verwaltungsverfahren as verfahren where " +
                    "verfahren.anh49Fachdaten = :fachdaten " +
                    "order by verfahren.datum")
                .setEntity("fachdaten", fachdaten)
                .list();
        log.debug("Verwaltungsverfahren für " + fachdaten
                + ", Anzahl: " + verwaltungsverfahrensListe.size());
        return verwaltungsverfahrensListe;
    }

    public static void saveOrUpdateVerwaltungsverfahren(
            Anh49Verwaltungsverfahren verwaltungsverfahren) {
        boolean success = false;
        success = new DatabaseAccess().saveOrUpdate(verwaltungsverfahren);
        log.debug("SaveOrUpdate on " + verwaltungsverfahren
                + (success ? "" : "un") + "successful.");
    }

    public static boolean removeVerwaltungsverfahren(
            Anh49Verwaltungsverfahren verwaltungsverfahren) {
        boolean success;
        success = new DatabaseAccess().delete(verwaltungsverfahren);
        log.debug("Delete on " + verwaltungsverfahren
                + (success ? "" : "un") + "successful.");
        return success;
    }
}
