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

// Generated 18.07.2012 17:02:12 by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.tipi;

import java.util.ArrayList;
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;
import de.nrw.lds.tipi.inka.Dea_Adresse;

/**
 * Home object for domain model class DeaAdresse.
 * @see de.bielefeld.umweltamt.aui.mappings.tipi.DeaAdresse
 * @author Hibernate Tools
 */
public class DeaAdresse extends AbstractDeaAdresse {

    private static final long serialVersionUID = 3461645403869356500L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static DeaAdresse merge(DeaAdresse detachedInstance) {
        return (DeaAdresse) new DatabaseAccess().merge(detachedInstance);
    }

    public static boolean delete(DeaAdresse detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }

    public DeaAdresse findById(java.lang.Integer id) {
        log.debug("getting DeaAdresse instance with id: " + id);
        DeaAdresse instance = (DeaAdresse) new DatabaseAccess().get(
            DeaAdresse.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<DeaAdresse> getAll() {
        String query = "FROM DeaAdresse";
        List<?> objectList = new DatabaseAccess().createQuery(query).list();
        List<DeaAdresse> resultList = new ArrayList<DeaAdresse>();
        DeaAdresse result = null;
        for (Object object : objectList) {
            result = (DeaAdresse) object;
            resultList.add(result);
        }
        return resultList;
    }

    public Dea_Adresse toServiceType() {
        Dea_Adresse serviceInstance = new Dea_Adresse(
            this.getAenderungsDatum(), this.getErfassungsDatum(),
            this.getGueltigBis(),
            this.getGueltigVon(),
            this.getIstAktuellJn(),
//            this.getHistorienNr(),
            this.getAdresseNr(),
            this.getAdresseVer(),
            this.getEmail(),
            this.getFax(),
            this.getHausnr(),
            this.getName1(),
            this.getName2(),
            this.getOrt(),
            this.getPlz(),
            this.getStaatskennung(),
            this.getStrasse(),
            this.getTelefon(),
            this.getTelefonMobil());
        return serviceInstance;
    }
}
