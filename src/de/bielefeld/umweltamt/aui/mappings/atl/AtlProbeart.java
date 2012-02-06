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
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ATL_PROBEART' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AtlProbeart extends AbstractAtlProbeart implements Serializable {
    private static final long serialVersionUID = -4946349358783685742L;
    /** Die ID von Abwasser-Proben */
    final public static Integer ABWASSER = new Integer(1);
    /** Die ID von UWB Abwasser-Proben */
    final public static Integer ABWASSER_UWB = new Integer(2);
    /** Die ID von E-Satzungs Abwasser-Proben */
    final public static Integer ABWASSER_ES = new Integer(3);
    /** Die ID von Anlieferungs-Proben */
    final public static Integer ANLIEFERUNG = new Integer(4);
    /** Die ID von Faulschlamm-Proben */
    final public static Integer FAULSCHLAMM = new Integer(5);
    /** Die ID von Rohschlamm-Proben */
    final public static Integer ROHSCHLAMM = new Integer(6);
    /** Die ID von SielhautBearbeiten-Proben */
    final public static Integer SIELHAUT = new Integer(7);
    /** Die ID von sonstigen Proben */
    final public static Integer SONSTIGE = new Integer(8);
    /** Die ID von Zulauf-Proben */
    final public static Integer ZULAUF = new Integer(9);

    /**
     * Simple constructor of AtlProbeart instances.
     */
    public AtlProbeart() {
        // This place is intentionally left blank.
    }

    /**
     * Constructor of AtlProbeart instances given a simple primary key.
     * @param artId
     */
    public AtlProbeart(java.lang.Integer artId) {
        super(artId);
    }

    /* Add customized code below */

    @Override
    public String toString() {
        return getArt();
    }

    public boolean isAbwasser() {
        return ABWASSER.equals(this.getArtId());
    }

    public boolean isAbwasserUWB() {
        return ABWASSER_UWB.equals(this.getArtId());
    }

    public boolean isAbwasserES() {
        return ABWASSER_ES.equals(this.getArtId());
    }

    public boolean isAnlieferung() {
        return ANLIEFERUNG.equals(this.getArtId());
    }

    public boolean isFaulschlamm() {
        return FAULSCHLAMM.equals(this.getArtId());
    }

    public boolean isRohschlamm() {
        return ROHSCHLAMM.equals(this.getArtId());
    }

    public boolean isSielhaut() {
        return SIELHAUT.equals(this.getArtId());
    }

    public boolean isSonstige() {
        return SONSTIGE.equals(this.getArtId());
    }

    public boolean isZulauf() {
        return ZULAUF.equals(this.getArtId());
    }

    /**
     * Liefert alle vorhandenen Probearten.
     * @return Alle vorhandenen Probearten
     */
    public static AtlProbeart[] getProbearten() {
        List<?> list = null;
        AtlProbeart[] result = null;

        String suchString = "from AtlProbeart art order by art.artId";

        // TODO: Clear what to do with setCacheable and stuff
        list = new DatabaseAccess().createQuery(suchString)
//                .setCacheable(true);
//                .setCacheRegion("probeartliste");
                .list();

        result = new AtlProbeart[list.size()];
        result = (AtlProbeart[]) list.toArray(result);

        return result;
    }

    /**
     * Liefert eine bestimmte Probeart.
     * @param id Die ID der Probeart
     * @return Die Probeart mit der gegebenen ID oder <code>null</code> falls
     *         diese nicht existiert
     */
    public static AtlProbeart getProbeart(Integer id) {
        AtlProbeart art = null;
        art = (AtlProbeart) new DatabaseAccess().get(AtlProbeart.class, id);
        return art;
    }
}
