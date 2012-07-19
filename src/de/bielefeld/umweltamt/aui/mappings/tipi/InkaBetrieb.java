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
import de.nrw.lds.tipi.inka.Inka_Betrieb;

/**
 * Home object for domain model class InkaBetrieb.
 * @see de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetrieb
 * @author Hibernate Tools
 */
public class InkaBetrieb extends AbstractInkaBetrieb {

    private static final long serialVersionUID = 3698871024772693854L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static InkaBetrieb merge(InkaBetrieb detachedInstance) {
        return (InkaBetrieb) new DatabaseAccess().merge(detachedInstance);
    }

    public static boolean delete(InkaBetrieb detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }

    public InkaBetrieb findById( java.lang.Integer id) {
        log.debug("getting InkaBetrieb instance with id: " + id);
        InkaBetrieb instance = (InkaBetrieb)
        	new DatabaseAccess().get(InkaBetrieb.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<InkaBetrieb> getAll() {
        String query = "FROM InkaBetrieb";
        List<?> objectList = new DatabaseAccess().createQuery(query).list();
        List<InkaBetrieb> resultList = new ArrayList<InkaBetrieb>();
        InkaBetrieb result = null;
        for (Object object : objectList) {
        	result = (InkaBetrieb) object;
        	resultList.add(result);
        }
        return resultList;
    }

    public Inka_Betrieb toServiceType() {
        Inka_Betrieb serviceInstance = new Inka_Betrieb(
            this.getAenderungsDatum(),
            this.getErfassungsDatum(),
            this.getGueltigBis(),
            this.getGueltigVon(),
            this.getIstAktuellJn(),
//            this.getHistorienNr(),
            this.getAdresseAnsprNr(),
            this.getAdresseAnsprVer(),
            this.getAdresseEinleitNr(),
            this.getAdresseEinleitVer(),
        	this.getAdresseStandNr(),
        	this.getAdresseStandVer(),
            this.getBetriebNr(),
            this.getBetriebVer(),
            this.getGemeindeVer(),
        	this.getGemeindekennzahl()
        );
        return serviceInstance;
	}
}

