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
package de.bielefeld.umweltamt.aui.tipi;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import de.nrw.lds.tipi.inka.webservice.InkaInterface;
import de.nrw.lds.tipi.inka.webservice.InkaInterfaceLocator;
import de.nrw.lds.tipi.inka.webservice.InkaInterfacePortType;
import de.nrw.lds.tipi.inka.request.ReqDea_Adresse;
import de.nrw.lds.tipi.inka.Dea_Adresse;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Dies ist ein Schnittstellen Objekt für web services. Momentan wird hierüber
 * der tipi Inka-Webservice angeboten.
 *
 * @author <a href="mailto:raimund.renkert@intevation.de">Raimund Renkert</a>
 */
public final class ServiceManager {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** The ServiceManager instance */
    private static ServiceManager instance;

    /** The Inka service */
    private InkaInterfaceLocator inkaService;

    /** Trivial constructor */
    private ServiceManager () {}

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }


    public void setInkaEndpointAdress(String address) {
        getInkaInterface().setInkaInterfaceEndpointAddress(address);
    }


    public Dea_Adresse[] getDea_Adressen() {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Dea_Adresse[0];
            }
            ReqDea_Adresse req = new ReqDea_Adresse();
            return iip.getDea_Adresse(req).getArrDea_Adresse();
        }
        catch(RemoteException re) {
            log.error("Error while requesting dea_adressen.");
        }
        return new Dea_Adresse[0];
    }


    public boolean setDea_Adressen(Dea_Adresse[] address) {
        try {
            for (int i = 0; i < address.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqDea_Adresse addr = new ReqDea_Adresse();
                addr.setObjDea_Adresse(address[i]);

                iip.setDea_Adresse(addr);
            }
        }
        catch(RemoteException re) {
            log.error("Error while sending dea_adress.");
            re.printStackTrace();
            return false;
        }
        return true;
    }

    protected InkaInterfaceLocator getInkaInterface() {
        if (inkaService == null) {
            inkaService = new InkaInterfaceLocator();
        }
        return inkaService;
    }


    protected InkaInterfacePortType getInkaInterfacePortType() {
        try {
            return getInkaInterface().getInkaInterface();
        }
        catch (ServiceException se) {
            log.error("Error getting the remote service.");
            se.printStackTrace();
        }
        return null;
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
