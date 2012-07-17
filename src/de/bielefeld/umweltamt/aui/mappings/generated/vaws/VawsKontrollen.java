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

package de.bielefeld.umweltamt.aui.mappings.generated.vaws;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;
import java.util.List;

/**
 * Home object for domain model class VawsKontrollen.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsKontrollen
 * @author Hibernate Tools
 */
public class VawsKontrollen extends AbstractVawsKontrollen {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static boolean saveOrUpdate(VawsKontrollen detachedInstance) {
        return new DatabaseAccess().saveOrUpdate(detachedInstance);
    }

    public static VawsKontrollen merge(VawsKontrollen detachedInstance) {
        return (VawsKontrollen) new DatabaseAccess().merge(detachedInstance);
    }

    public static boolean delete(VawsKontrollen detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }
    
    public VawsKontrollen findById( java.lang.Integer id) {
        log.debug("getting VawsKontrollen instance with id: " + id);
        VawsKontrollen instance = (VawsKontrollen)
        	new DatabaseAccess().get(VawsKontrollen.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<?> getAll() {
        String query = "FROM VawsKontrollen";
        return new DatabaseAccess().createQuery(query).list();
    }

}

