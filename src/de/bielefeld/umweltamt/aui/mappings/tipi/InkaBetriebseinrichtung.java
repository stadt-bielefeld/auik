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
import de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung;

/**
 * Home object for domain model class InkaBetriebseinrichtung.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaBetriebseinrichtung
 * @author Hibernate Tools
 */
public class InkaBetriebseinrichtung extends AbstractInkaBetriebseinrichtung {

    private static final long serialVersionUID = -9033780000126398986L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static InkaBetriebseinrichtung merge(
        InkaBetriebseinrichtung detachedInstance) {
        return (InkaBetriebseinrichtung) new DatabaseAccess()
            .merge(detachedInstance);
    }

    public static boolean delete(InkaBetriebseinrichtung detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }

    public InkaBetriebseinrichtung findById(java.lang.Integer id) {
        log.debug("getting InkaBetriebseinrichtung instance with id: " + id);
        InkaBetriebseinrichtung instance = (InkaBetriebseinrichtung) new DatabaseAccess()
            .get(InkaBetriebseinrichtung.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<InkaBetriebseinrichtung> getAll() {
        String query = "FROM InkaBetriebseinrichtung";
        List<?> objectList = new DatabaseAccess().createQuery(query).list();
        List<InkaBetriebseinrichtung> resultList = new ArrayList<InkaBetriebseinrichtung>();
        InkaBetriebseinrichtung result = null;
        for (Object object : objectList) {
            result = (InkaBetriebseinrichtung) object;
            resultList.add(result);
        }
        return resultList;
    }

    public Inka_Betriebseinrichtung toServiceType() {
        Inka_Betriebseinrichtung serviceInstance = new Inka_Betriebseinrichtung(
            this.getAenderungsDatum(),
            this.getErfassungsDatum(),
            this.getGueltigBis(),
            this.getGueltigVon(),
            this.getIstAktuellJn(),
//            this.getHistorienNr(),
            this.getAdresseAnsprNr(),
            this.getAdresseAnsprVer(),
            this.getAdresseBetreibNr(),
            this.getAdresseBetreibVer(),
            new Integer(42), // TODO: Fix this!
            this.getArbeitsstaetteVer(),
//            this.getAstnr(),
            this.getBetriebNr(),
            this.getBetriebVer(),
            this.getBetriebseinrichtungNr(),
        	this.getBetriebseinrichtungVer(),
//            this.getGaaNr(),
        	this.getGenehmigungNr(),
        	this.getGenehmigungVer(),
            this.getStilllegungDatum(),
            this.getStilllegungJn(),
        	this.getWzCode(),
        	this.getWzCodeVer()
//            this.getZusatz1(),
//            this.getZusatz2(),
        );
        return serviceInstance;
	}
}
