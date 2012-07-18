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
import de.nrw.lds.tipi.inka.Inka_Uebergabestelle;

/**
 * Home object for domain model class InkaUebergabestelle.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaUebergabestelle
 * @author Hibernate Tools
 */
public class InkaUebergabestelle extends AbstractInkaUebergabestelle {

    private static final long serialVersionUID = -320900909149879431L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static InkaUebergabestelle merge(InkaUebergabestelle detachedInstance) {
        return (InkaUebergabestelle) new DatabaseAccess()
            .merge(detachedInstance);
    }

    public static boolean delete(InkaUebergabestelle detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }

    public InkaUebergabestelle findById(java.lang.Integer id) {
        log.debug("getting InkaUebergabestelle instance with id: " + id);
        InkaUebergabestelle instance = (InkaUebergabestelle) new DatabaseAccess()
            .get(InkaUebergabestelle.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<InkaUebergabestelle> getAll() {
        String query = "FROM InkaUebergabestelle";
        List<?> objectList = new DatabaseAccess().createQuery(query).list();
        List<InkaUebergabestelle> resultList = new ArrayList<InkaUebergabestelle>();
        InkaUebergabestelle result = null;
        for (Object object : objectList) {
            result = (InkaUebergabestelle) object;
            resultList.add(result);
        }
        return resultList;
    }

    public Inka_Uebergabestelle toServiceType() {
        Inka_Uebergabestelle serviceInstance = new Inka_Uebergabestelle(
            this.getAenderungsDatum(),
            this.getErfassungsDatum(),
            this.getGueltigVon(),
            this.getGueltigBis(),
            this.getIstAktuellJn(),
//          this.getHistorienNr(),
            this.getAnlagenNr(),
            this.getBetriebNr(),
            this.getBetriebVer(),
            this.getGemeindekennzahl(),
        	this.getGemeindeVer(),
            this.getGenehmigungNr(),
            this.getGenehmigungVer(),
            this.getHochwert(),
            this.getKanalArt(),
            this.getKartennummer(),
            this.getKlaeranlagenVer(),
            this.getRechtswert(),
            this.getTk25Ver(),
            this.getUebergabestelleLfdNr(),
        	this.getUebergabestelleVer()
        );
        return serviceInstance;
	}
}
