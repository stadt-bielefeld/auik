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
import de.nrw.lds.tipi.inka.Inka_Anfallstelle;

/**
 * Home object for domain model class InkaAnfallstelle.
 * @see de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnfallstelle
 * @author Hibernate Tools
 */
public class InkaAnfallstelle extends AbstractInkaAnfallstelle {

    private static final long serialVersionUID = -4040286360315789465L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static InkaAnfallstelle merge(InkaAnfallstelle detachedInstance) {
        return (InkaAnfallstelle) new DatabaseAccess().merge(detachedInstance);
    }

    public static boolean delete(InkaAnfallstelle detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }

    public InkaAnfallstelle findById(java.lang.Integer id) {
        log.debug("getting InkaAnfallstelle instance with id: " + id);
        InkaAnfallstelle instance = (InkaAnfallstelle) new DatabaseAccess()
            .get(InkaAnfallstelle.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<InkaAnfallstelle> getAll() {
        String query = "FROM InkaAnfallstelle";
        List<?> objectList = new DatabaseAccess().createQuery(query).list();
        List<InkaAnfallstelle> resultList = new ArrayList<InkaAnfallstelle>();
        InkaAnfallstelle result = null;
        for (Object object : objectList) {
            result = (InkaAnfallstelle) object;
            resultList.add(result);
        }
        return resultList;
    }

    public Inka_Anfallstelle toServiceType() {
        Inka_Anfallstelle serviceInstance = new Inka_Anfallstelle(
            this.getAenderungsDatum(),
            this.getErfassungsDatum(),
            this.getGueltigBis(),
            this.getGueltigVon(),
            this.getIstAktuellJn(),
//          this.getHistorienNr(),
        	this.getAnfallstelleNr(),
        	this.getAnfallstelleVer(),
        	this.getAnhId(),
        	this.getAnhVer(),
            this.getBeschreibung(),
        	this.getBetriebseinrichtungNr(),
        	this.getBetriebseinrichtungVer(),
            this.getChargenbetriebJn(),
            this.getDauerbetriebJn(),
            this.getGemeindeVer(),
        	this.getGemeindekennzahl(),
        	this.getGenehmigungNr(),
        	this.getGenehmigungVer(),
            new Float(this.getMaxVolTag()),
        	this.getUebergabestelleLfdNr(),
        	this.getUebergabestelleVer(),
            this.getVolJahr()
        );
        return serviceInstance;
	}
}
