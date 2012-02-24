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

import java.util.Calendar;

import de.nrw.lds.tipi.inka.webservice.InkaInterface;
import de.nrw.lds.tipi.inka.webservice.InkaInterfaceLocator;
import de.nrw.lds.tipi.inka.webservice.InkaInterfacePortType;
import de.nrw.lds.tipi.inka.request.ReqDea_Adresse;
import de.nrw.lds.tipi.inka.request.ReqInka_Betrieb;
import de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung;
import de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung;

import de.nrw.lds.tipi.inka.Dea_Adresse;
import de.nrw.lds.tipi.inka.Inka_Betrieb;
import de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung;
import de.nrw.lds.tipi.inka.Inka_Genehmigung;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Dies ist ein Schnittstellen Objekt für web services. Momentan wird hierüber
 * der tipi Inka-Webservice angeboten.
 *
 * @author <a href="mailto:raimund.renkert@intevation.de">Raimund Renkert</a>
 */
public final class ServiceManager {

    /** Logging */
    private static final AuikLogger logger = AuikLogger.getLogger();

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


    public Dea_Adresse[] getDea_Adressen(String user, String passw) {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Dea_Adresse[0];
            }
            ReqDea_Adresse req = new ReqDea_Adresse();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getDea_Adresse(req).getArrDea_Adresse();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting dea_adressen.");
        }
        return new Dea_Adresse[0];
    }


    public boolean setDea_Adressen(
        String user,
        String passw,
        Dea_Adresse[] address)
    {
        try {
            for (int i = 0; i < address.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqDea_Adresse addr = new ReqDea_Adresse();
                addr.setClientTimestamp(Calendar.getInstance());
                addr.setKennung(user);
                addr.setPassword(passw);
                addr.setObjDea_Adresse(address[i]);
                iip.setDea_Adresse(addr);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending dea_adress.");
            re.printStackTrace();
            return false;
        }
        return true;
    }


    public Inka_Betrieb[] getInka_Betriebe(String user, String passw) {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Betrieb[0];
            }
            ReqInka_Betrieb req = new ReqInka_Betrieb();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Betrieb(req).getArrInka_Betrieb();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting inka_betrieb.");
        }
        return new Inka_Betrieb[0];

    }


    public boolean setInka_Betriebe(
        String user,
        String passw,
        Inka_Betrieb[] betriebe)
    {
        try {
            for (int i = 0; i < betriebe.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Betrieb betrieb = new ReqInka_Betrieb();
                betrieb.setObjInka_Betrieb(betriebe[i]);
                betrieb.setClientTimestamp(Calendar.getInstance());
                betrieb.setKennung(user);
                betrieb.setPassword(passw);
                iip.setInka_Betrieb(betrieb);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending inka_betrieb.");
            re.printStackTrace();
            return false;
        }
        return true;
    }


    public Inka_Betriebseinrichtung[] getInka_Betriebseinrichtungen(
        String user,
        String passw)
    {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Betriebseinrichtung[0];
            }
            ReqInka_Betriebseinrichtung req =
                new ReqInka_Betriebseinrichtung();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Betriebseinrichtung(req)
                      .getArrInka_Betriebseinrichtung();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting inka_betriebseinrichtung.");
        }
        return new Inka_Betriebseinrichtung[0];

    }


    public boolean setInka_Betriebseinrichtungen(
        String user,
        String passw,
        Inka_Betriebseinrichtung[] betriebseinrichtungen)
    {
        try {
            for (int i = 0; i < betriebseinrichtungen.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Betriebseinrichtung betrieb =
                    new ReqInka_Betriebseinrichtung();
                betrieb.setObjInka_Betriebseinrichtung(betriebseinrichtungen[i]);
                betrieb.setClientTimestamp(Calendar.getInstance());
                betrieb.setKennung(user);
                betrieb.setPassword(passw);
                iip.setInka_Betriebseinrichtung(betrieb);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending inka_betriebseinrichtung.");
            re.printStackTrace();
            return false;
        }
        return true;
    }


    public Inka_Genehmigung[] getInka_Genehmigungen(
        String user,
        String passw)
    {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Genehmigung[0];
            }
            ReqInka_Genehmigung req =
                new ReqInka_Genehmigung();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Genehmigung(req)
                      .getArrInka_Genehmigung();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting inka_genehmigung.");
        }
        return new Inka_Genehmigung[0];

    }


    public boolean setInka_Genehmigungen(
        String user,
        String passw,
        Inka_Genehmigung[] genehmigungen)
    {
        try {
            for (int i = 0; i < genehmigungen.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Genehmigung genehmigung =
                    new ReqInka_Genehmigung();
                genehmigung.setObjInka_Genehmigung(genehmigungen[i]);
                genehmigung.setClientTimestamp(Calendar.getInstance());
                genehmigung.setKennung(user);
                genehmigung.setPassword(passw);
                iip.setInka_Genehmigung(genehmigung);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending inka_genehmigung.");
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
            logger.error("Error getting the remote service.");
            se.printStackTrace();
        }
        return null;
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
