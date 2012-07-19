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
import de.nrw.lds.tipi.inka.Inka_Genehmigung;

/**
 * Home object for domain model class InkaGenehmigung.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaGenehmigung
 * @author Hibernate Tools
 */
public class InkaGenehmigung extends AbstractInkaGenehmigung {

    private static final long serialVersionUID = 7101871299852558632L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static InkaGenehmigung merge(InkaGenehmigung detachedInstance) {
        return (InkaGenehmigung) new DatabaseAccess().merge(detachedInstance);
    }

    public static boolean delete(InkaGenehmigung detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }

    public InkaGenehmigung findById(java.lang.Integer id) {
        log.debug("getting InkaGenehmigung instance with id: " + id);
        InkaGenehmigung instance = (InkaGenehmigung) new DatabaseAccess().get(
            InkaGenehmigung.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<InkaGenehmigung> getAll() {
        String query = "FROM InkaGenehmigung";
        List<?> objectList = new DatabaseAccess().createQuery(query).list();
        List<InkaGenehmigung> resultList = new ArrayList<InkaGenehmigung>();
        InkaGenehmigung result = null;
        for (Object object : objectList) {
            result = (InkaGenehmigung) object;
            resultList.add(result);
        }
        return resultList;
    }

    public Inka_Genehmigung toServiceType() {
        Inka_Genehmigung serviceInstance = new Inka_Genehmigung(
            this.getAenderungsDatum(),
            this.getErfassungsDatum(),
            this.getGueltigBis(),
            this.getGueltigVon(),
            this.getIstAktuellJn(),
//            this.getHistorienNr(),
            this.getBefristetBis(),
            this.getBefristetJn(),
            this.getBehoerdenId(),
            this.getBehoerdenVer(),
            this.getBetriebNr(),
            this.getBetriebVer(),
            this.getGenehmigungDatum(),
        	this.getGenehmigungNr(),
        	this.getGenehmigungVer()
        );
        return serviceInstance;
	}
}
