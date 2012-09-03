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
import java.util.Calendar;

import javax.xml.rpc.ServiceException;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.nrw.lds.tipi.inka.Dea_Adresse;
import de.nrw.lds.tipi.inka.Inka_Anfallst_Anlage;
import de.nrw.lds.tipi.inka.Inka_Anfallst_Messst;
import de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe;
import de.nrw.lds.tipi.inka.Inka_Anfallstelle;
import de.nrw.lds.tipi.inka.Inka_Anlage;
import de.nrw.lds.tipi.inka.Inka_Betrieb;
import de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung;
import de.nrw.lds.tipi.inka.Inka_Genehmigung;
import de.nrw.lds.tipi.inka.Inka_Messst_Anlage;
import de.nrw.lds.tipi.inka.Inka_Messstelle;
import de.nrw.lds.tipi.inka.Inka_Uebergabestelle;
import de.nrw.lds.tipi.inka.request.ReqDea_Adresse;
import de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage;
import de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst;
import de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe;
import de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle;
import de.nrw.lds.tipi.inka.request.ReqInka_Anlage;
import de.nrw.lds.tipi.inka.request.ReqInka_Betrieb;
import de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung;
import de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung;
import de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage;
import de.nrw.lds.tipi.inka.request.ReqInka_Messstelle;
import de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle;
import de.nrw.lds.tipi.inka.webservice.InkaInterfaceLocator;
import de.nrw.lds.tipi.inka.webservice.InkaInterfacePortType;

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


    public Inka_Anfallstelle[] getInka_Anfallstelle(String user, String passw) {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Anfallstelle[0];
            }
            ReqInka_Anfallstelle req = new ReqInka_Anfallstelle();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Anfallstelle(req).getArrInka_Anfallstelle();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting inka_anfallstelle.");
        }
        return new Inka_Anfallstelle[0];

    }


    public boolean setInka_Anfallstelle(
        String user,
        String passw,
        Inka_Anfallstelle[] anfallsten)
    {
        try {
            for (int i = 0; i < anfallsten.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Anfallstelle anfallst = new ReqInka_Anfallstelle();
                anfallst.setObjInka_Anfallstelle(anfallsten[i]);
                anfallst.setClientTimestamp(Calendar.getInstance());
                anfallst.setKennung(user);
                anfallst.setPassword(passw);
                iip.setInka_Anfallstelle(anfallst);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending inka_anfallstelle.");
            re.printStackTrace();
            return false;
        }
        return true;
    }


    public Inka_Anlage[] getInka_Anlage(String user, String passw) {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Anlage[0];
            }
            ReqInka_Anlage req = new ReqInka_Anlage();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Anlage(req).getArrInka_Anlage();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting inka_anfallstelle.");
        }
        return new Inka_Anlage[0];

    }


    public boolean setInka_Anlage(
        String user,
        String passw,
        Inka_Anlage[] anlagen)
    {
        try {
            for (int i = 0; i < anlagen.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Anlage anlage = new ReqInka_Anlage();
                anlage.setObjInka_Anlage(anlagen[i]);
                anlage.setClientTimestamp(Calendar.getInstance());
                anlage.setKennung(user);
                anlage.setPassword(passw);
                iip.setInka_Anlage(anlage);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending inka_anlage.");
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


    public Inka_Messstelle[] getInka_Messstelle(
        String user,
        String passw)
    {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Messstelle[0];
            }
            ReqInka_Messstelle req =
                new ReqInka_Messstelle();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Messstelle(req)
                      .getArrInka_Messstelle();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting inka_messstelle.");
        }
        return new Inka_Messstelle[0];

    }


    public boolean setInka_Messstelle(
        String user,
        String passw,
        Inka_Messstelle[] messstellen)
    {
        try {
            for (int i = 0; i < messstellen.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Messstelle messstelle =
                    new ReqInka_Messstelle();
                messstelle.setObjInka_Messstelle(messstellen[i]);
                messstelle.setClientTimestamp(Calendar.getInstance());
                messstelle.setKennung(user);
                messstelle.setPassword(passw);
                iip.setInka_Messstelle(messstelle);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending inka_messstelle.");
            re.printStackTrace();
            return false;
        }
        return true;
    }


    public Inka_Uebergabestelle[] getInka_Uebergabestelle(
        String user,
        String passw)
    {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Uebergabestelle[0];
            }
            ReqInka_Uebergabestelle req =
                new ReqInka_Uebergabestelle();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Uebergabestelle(req)
                      .getArrInka_Uebergabestelle();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting inka_uebergabestelle.");
        }
        return new Inka_Uebergabestelle[0];

    }


    public boolean setInka_Uebergabestelle(
        String user,
        String passw,
        Inka_Uebergabestelle[] uebergabestellen)
    {
        try {
            for (int i = 0; i < uebergabestellen.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Uebergabestelle uebergabestelle =
                    new ReqInka_Uebergabestelle();
                uebergabestelle.setObjInka_Uebergabestelle(uebergabestellen[i]);
                uebergabestelle.setClientTimestamp(Calendar.getInstance());
                uebergabestelle.setKennung(user);
                uebergabestelle.setPassword(passw);
                iip.setInka_Uebergabestelle(uebergabestelle);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending inka_uebergabestelle.");
            re.printStackTrace();
            return false;
        }
        return true;
    }

    public Inka_Messst_Anlage[] getInka_Messst_Anlage(
        String user,
        String passw)
    {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Messst_Anlage[0];
            }
            ReqInka_Messst_Anlage req = new ReqInka_Messst_Anlage();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Messst_Anlage(req).getArrInka_Messst_Anlage();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting Inka_Messst_Anlage.");
        }
        return new Inka_Messst_Anlage[0];

    }

    public boolean setInka_Messst_Anlage(
        String user,
        String passw,
        Inka_Messst_Anlage[] inka_list)
    {
        try {
            for (int i = 0; i < inka_list.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Messst_Anlage inka_obj =
                    new ReqInka_Messst_Anlage();
                inka_obj.setObjInka_Messst_Anlage(inka_list[i]);
                inka_obj.setClientTimestamp(Calendar.getInstance());
                inka_obj.setKennung(user);
                inka_obj.setPassword(passw);
                iip.setInka_Messst_Anlage(inka_obj);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending Inka_Messst_Anlage.");
            re.printStackTrace();
            return false;
        }
        return true;
    }

    public Inka_Anfallst_Messst[] getInka_Anfallst_Messst(
        String user,
        String passw)
    {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Anfallst_Messst[0];
            }
            ReqInka_Anfallst_Messst req = new ReqInka_Anfallst_Messst();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Anfallst_Messst(req)
                .getArrInka_Anfallst_Messst();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting Inka_Anfallst_Messst.");
        }
        return new Inka_Anfallst_Messst[0];

    }

    public boolean setInka_Anfallst_Messst(
        String user,
        String passw,
        Inka_Anfallst_Messst[] inka_list)
    {
        try {
            for (int i = 0; i < inka_list.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Anfallst_Messst inka_obj =
                    new ReqInka_Anfallst_Messst();
                inka_obj.setObjInka_Anfallst_Messst(inka_list[i]);
                inka_obj.setClientTimestamp(Calendar.getInstance());
                inka_obj.setKennung(user);
                inka_obj.setPassword(passw);
                iip.setInka_Anfallst_Messst(inka_obj);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending Inka_Anfallst_Messst.");
            re.printStackTrace();
            return false;
        }
        return true;
    }

    public Inka_Anfallst_Anlage[] getInka_Anfallst_Anlage(
        String user,
        String passw)
    {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Anfallst_Anlage[0];
            }
            ReqInka_Anfallst_Anlage req = new ReqInka_Anfallst_Anlage();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Anfallst_Anlage(req)
                .getArrInka_Anfallst_Anlage();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting Inka_Anfallst_Anlage.");
        }
        return new Inka_Anfallst_Anlage[0];

    }

    public boolean setInka_Anfallst_Anlage(
        String user,
        String passw,
        Inka_Anfallst_Anlage[] inka_list)
    {
        try {
            for (int i = 0; i < inka_list.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Anfallst_Anlage inka_obj =
                    new ReqInka_Anfallst_Anlage();
                inka_obj.setObjInka_Anfallst_Anlage(inka_list[i]);
                inka_obj.setClientTimestamp(Calendar.getInstance());
                inka_obj.setKennung(user);
                inka_obj.setPassword(passw);
                iip.setInka_Anfallst_Anlage(inka_obj);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending Inka_Anfallst_Anlage.");
            re.printStackTrace();
            return false;
        }
        return true;
    }

    public Inka_Anfallst_Stoffe[] getInka_Anfallst_Stoffe(
        String user,
        String passw)
    {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return new Inka_Anfallst_Stoffe[0];
            }
            ReqInka_Anfallst_Stoffe req = new ReqInka_Anfallst_Stoffe();
            req.setClientTimestamp(Calendar.getInstance());
            req.setKennung(user);
            req.setPassword(passw);
            return iip.getInka_Anfallst_Stoffe(req)
                .getArrInka_Anfallst_Stoffe();
        }
        catch(RemoteException re) {
            logger.error("Error while requesting Inka_Anfallst_Stoffe.");
        }
        return new Inka_Anfallst_Stoffe[0];

    }

    public boolean setInka_Anfallst_Stoffe(
        String user,
        String passw,
        Inka_Anfallst_Stoffe[] inka_list)
    {
        try {
            for (int i = 0; i < inka_list.length; i++) {
                InkaInterfacePortType iip = getInkaInterfacePortType();
                if (iip == null) {
                    return false;
                }
                ReqInka_Anfallst_Stoffe inka_obj =
                    new ReqInka_Anfallst_Stoffe();
                inka_obj.setObjInka_Anfallst_Stoffe(inka_list[i]);
                inka_obj.setClientTimestamp(Calendar.getInstance());
                inka_obj.setKennung(user);
                inka_obj.setPassword(passw);
                iip.setInka_Anfallst_Stoffe(inka_obj);
            }
        }
        catch(RemoteException re) {
            logger.error("Error while sending Inka_Anfallst_Stoffe.");
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
