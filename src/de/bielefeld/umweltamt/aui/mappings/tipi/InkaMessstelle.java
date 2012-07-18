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
import de.nrw.lds.tipi.inka.Inka_Messstelle;

/**
 * Home object for domain model class InkaMessstelle.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaMessstelle
 * @author Hibernate Tools
 */
public class InkaMessstelle extends AbstractInkaMessstelle {

    private static final long serialVersionUID = 372501824975808090L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static InkaMessstelle merge(InkaMessstelle detachedInstance) {
        return (InkaMessstelle) new DatabaseAccess().merge(detachedInstance);
    }

    public static boolean delete(InkaMessstelle detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }

    public InkaMessstelle findById(java.lang.Integer id) {
        log.debug("getting InkaMessstelle instance with id: " + id);
        InkaMessstelle instance = (InkaMessstelle) new DatabaseAccess().get(
            InkaMessstelle.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<InkaMessstelle> getAll() {
        String query = "FROM InkaMessstelle";
        List<?> objectList = new DatabaseAccess().createQuery(query).list();
        List<InkaMessstelle> resultList = new ArrayList<InkaMessstelle>();
        InkaMessstelle result = null;
        for (Object object : objectList) {
            result = (InkaMessstelle) object;
            resultList.add(result);
        }
        return resultList;
    }

    // TODO: Fix this! This is generated - costumize!
    // TODO: Add some "_" into the return type!
    public Inka_Messstelle toServiceType() {
		// TODO: Add some "_" into the type!
        Inka_Messstelle serviceInstance = new Inka_Messstelle(
        	// TODO: Resort the fields to fit the service class!
            this.getAenderungsDatum(),
            this.getErfassungsDatum(),
            this.getGueltigVon(),
            this.getGueltigBis(),
            this.getIstAktuellJn(),
//            this.getHistorienNr(),
            this.getBeschrMesspunkt(),
            this.getGemeindeVer(),
            this.getGemeindekennzahl(),
            this.getGenehmigungNr(),
            this.getGenehmigungVer(),
            this.getMessstelleLfdNr(),
            this.getMessstelleTyp(),
            this.getMessstelleVer(),
            this.getRelevantSumFrachtJn(),
        	this.getUebergabestelleLfdNr(),
        	this.getUebergabestelleVer()
        );
        return serviceInstance;
	}
}
