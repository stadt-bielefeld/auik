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

// Generated 17.07.2012 18:33:28 by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.generated.atl;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;
import java.util.List;

/**
 * Home object for domain model class AtlProbenahmen.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlProbenahmen
 * @author Hibernate Tools
 */
public class AtlProbenahmen extends AbstractAtlProbenahmen {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static boolean saveOrUpdate(AtlProbenahmen detachedInstance) {
        return new DatabaseAccess().saveOrUpdate(detachedInstance);
    }

    public static AtlProbenahmen merge(AtlProbenahmen detachedInstance) {
        return (AtlProbenahmen) new DatabaseAccess().merge(detachedInstance);
    }

    public static boolean delete(AtlProbenahmen detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }
    
    public AtlProbenahmen findById( java.lang.Integer id) {
        log.debug("getting AtlProbenahmen instance with id: " + id);
        AtlProbenahmen instance = (AtlProbenahmen)
        	new DatabaseAccess().get(AtlProbenahmen.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<?> getAll() {
        String query = "FROM AtlProbenahmen";
        return new DatabaseAccess().createQuery(query).list();
    }

}

