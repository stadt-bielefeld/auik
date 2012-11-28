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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.rpc.ServiceException;


import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.nrw.lds.tipi.general.HistoryObject;
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
import de.nrw.lds.tipi.inka.Inka_Probenahme;
import de.nrw.lds.tipi.inka.Inka_Uebergabestelle;
import de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis;
import de.nrw.lds.tipi.inka.Inka_Ueberwachungswert;
import de.nrw.lds.tipi.inka.general.ReqStandard;
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
import de.nrw.lds.tipi.inka.request.ReqInka_Probenahme;
import de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle;
import de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis;
import de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert;
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

    /**
     * Get all remote inka data records
     * @param <T> Type of the inka records
     * @param user
     * @param password
     * @param type A HistoryObject merely used for its type
     * @return List<T> List of remote inka data records
     */
    public <T extends HistoryObject> List<T> getInkaDataRecords(
        String user, String password, T type) {
        T[] resultArray = null;
        List<T> resultList = new ArrayList<T>();
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return resultList;
            }
            ReqStandard request = this.getNewRequest(type);
            request.setClientTimestamp(Calendar.getInstance());
            request.setKennung(user);
            request.setPassword(password);
            resultArray = this.getResponseArrayFromIIP(type, request, iip);
            log.debug(request.toString());
        } catch(RemoteException re) {
            log.error("Error while requesting "
                + type.getClass().getSimpleName() +  ".");
            re.printStackTrace();
        }
        for (T result : resultArray) {
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * Set remote inka data records
     * @param <T> Type of the inka records
     * @param user
     * @param password
     * @param recordList List of records to set
     * @return true, if roughly everything went ok, false otherwise
     */
    public <T extends HistoryObject> boolean setInkaDataRecords(
        String user, String password, List<T> recordList) {
        try {
            InkaInterfacePortType iip = getInkaInterfacePortType();
            if (iip == null) {
                return false;
            }
            for (T record : recordList) {
                ReqStandard request = this.getNewRequest(record);
                request.setClientTimestamp(Calendar.getInstance());
                request.setKennung(user);
                request.setPassword(password);
                this.setRecordToRequestToIIP(record, request, iip);
                log.debug(request.toString());
            }
        } catch(RemoteException re) {
            log.error("Error while sending "
                + recordList.get(0).getClass().getSimpleName() + ".");
            re.printStackTrace();
            return false;
        }
        return true;
    }

    /* Some helper methods to generalize the generics */
    /**
     * Get the respective ReqStandard object for a given type
     * @param <T extends HistoryObject> Type of the request
     * @param type A HistoryObject merely used for its type
     * @return a new ReqStandard object
     */
    private <T extends HistoryObject> ReqStandard getNewRequest(T type) {
        if (type instanceof Dea_Adresse)
            return new ReqDea_Adresse();
        if (type instanceof Inka_Betrieb)
            return new ReqInka_Betrieb();
        if (type instanceof Inka_Genehmigung)
            return new ReqInka_Genehmigung();
        if (type instanceof Inka_Betriebseinrichtung)
            return new ReqInka_Betriebseinrichtung();
        if (type instanceof Inka_Uebergabestelle)
            return new ReqInka_Uebergabestelle();
        if (type instanceof Inka_Messstelle)
            return new ReqInka_Messstelle();
        if (type instanceof Inka_Anfallstelle)
            return new ReqInka_Anfallstelle();
        if (type instanceof Inka_Anlage)
            return new ReqInka_Anlage();
        if (type instanceof Inka_Messst_Anlage)
            return new ReqInka_Messst_Anlage();
        if (type instanceof Inka_Anfallst_Messst)
            return new ReqInka_Anfallst_Messst();
        if (type instanceof Inka_Anfallst_Anlage)
            return new ReqInka_Anfallst_Anlage();
        if (type instanceof Inka_Anfallst_Stoffe)
            return new ReqInka_Anfallst_Stoffe();
        if (type instanceof Inka_Probenahme)
            return new ReqInka_Probenahme();
        if (type instanceof Inka_Ueberwach_Ergebnis)
            return new ReqInka_Ueberwach_Ergebnis();
        if (type instanceof Inka_Ueberwachungswert)
            return new ReqInka_Ueberwachungswert();
        // TODO: Add new DEA/INKA-Table here
        return null;
    }

    /**
     * Get a response object array for a given request object (HistoryObject)
     * @param <T> Type of the request (HistoryObject)
     * @param type A HistoryObject merely used for its type
     * @param request ReqStandard
     * @param iip InkaInterfacePortType
     * @return HistoryObject[] A response array to the request
     * @throws RemoteException
     */
    /* Uh-uh, second suppress warnings from me :( */
    @SuppressWarnings("unchecked")
    private <T extends HistoryObject> T[] getResponseArrayFromIIP(
        T type, ReqStandard request, InkaInterfacePortType iip)
    throws RemoteException {
        if (type instanceof Dea_Adresse)
            return (T[]) iip.getDea_Adresse((ReqDea_Adresse) request)
                .getArrDea_Adresse();
        if (type instanceof Inka_Betrieb)
            return (T[]) iip.getInka_Betrieb((ReqInka_Betrieb) request)
                .getArrInka_Betrieb();
        if (type instanceof Inka_Genehmigung)
            return (T[]) iip.getInka_Genehmigung((ReqInka_Genehmigung) request)
                .getArrInka_Genehmigung();
        if (type instanceof Inka_Betriebseinrichtung)
            return (T[]) iip.getInka_Betriebseinrichtung(
                (ReqInka_Betriebseinrichtung) request)
                .getArrInka_Betriebseinrichtung();
        if (type instanceof Inka_Uebergabestelle)
            return (T[]) iip
                .getInka_Uebergabestelle((ReqInka_Uebergabestelle) request)
                .getArrInka_Uebergabestelle();
        if (type instanceof Inka_Messstelle)
            return (T[]) iip.getInka_Messstelle((ReqInka_Messstelle) request)
                .getArrInka_Messstelle();
        if (type instanceof Inka_Anfallstelle)
            return (T[]) iip
                .getInka_Anfallstelle((ReqInka_Anfallstelle) request)
                .getArrInka_Anfallstelle();
        if (type instanceof Inka_Anlage)
            return (T[]) iip.getInka_Anlage((ReqInka_Anlage) request)
                .getArrInka_Anlage();
        if (type instanceof Inka_Messst_Anlage)
            return (T[]) iip
                .getInka_Messst_Anlage((ReqInka_Messst_Anlage) request)
                .getArrInka_Messst_Anlage();
        if (type instanceof Inka_Anfallst_Messst)
            return (T[]) iip
                .getInka_Anfallst_Messst((ReqInka_Anfallst_Messst) request)
                .getArrInka_Anfallst_Messst();
        if (type instanceof Inka_Anfallst_Anlage)
            return (T[]) iip
                .getInka_Anfallst_Anlage((ReqInka_Anfallst_Anlage) request)
                .getArrInka_Anfallst_Anlage();
        if (type instanceof Inka_Anfallst_Stoffe)
            return (T[]) iip
                .getInka_Anfallst_Stoffe((ReqInka_Anfallst_Stoffe) request)
                .getArrInka_Anfallst_Stoffe();
        if (type instanceof Inka_Probenahme)
            return (T[]) iip
                .getInka_Probenahme((ReqInka_Probenahme) request)
                .getArrInka_Probenahme();
        if (type instanceof Inka_Ueberwach_Ergebnis)
            return (T[]) iip
                .getInka_Ueberwach_Ergebnis((ReqInka_Ueberwach_Ergebnis) request)
                .getArrInka_Ueberwach_Ergebnis();
        if (type instanceof Inka_Ueberwachungswert)
            return (T[]) iip
                .getInka_Ueberwachungswert((ReqInka_Ueberwachungswert) request)
                .getArrInka_Ueberwachungswert();
        // TODO: Add new DEA/INKA-Table here
        return null;
    }


    /**
     * Set a record (HistoryObject) to a given request and then set that to the
     * InkaInterfacePortType
     * @param <T> type
     * @param record HistoryObject
     * @param request ReqStandard
     * @param iip InkaInterfacePortType
     * @throws RemoteException
     */
    private <T extends HistoryObject> void setRecordToRequestToIIP(
        T record, ReqStandard request, InkaInterfacePortType iip)
    throws RemoteException {
        if (record instanceof Dea_Adresse) {
            ((ReqDea_Adresse) request).setObjDea_Adresse((Dea_Adresse) record);
            iip.setDea_Adresse((ReqDea_Adresse) request);
            return;
        }
        if (record instanceof Inka_Betrieb) {
            ((ReqInka_Betrieb) request).setObjInka_Betrieb(
                (Inka_Betrieb) record);
            iip.setInka_Betrieb((ReqInka_Betrieb) request);
            return;
        }
        if (record instanceof Inka_Genehmigung) {
            ((ReqInka_Genehmigung) request).setObjInka_Genehmigung(
                (Inka_Genehmigung) record);
            iip.setInka_Genehmigung((ReqInka_Genehmigung) request);
            return;
        }
        if (record instanceof Inka_Betriebseinrichtung) {
            ((ReqInka_Betriebseinrichtung) request)
                .setObjInka_Betriebseinrichtung(
                    (Inka_Betriebseinrichtung) record);
            iip.setInka_Betriebseinrichtung((ReqInka_Betriebseinrichtung) request);
            return;
        }
        if (record instanceof Inka_Uebergabestelle) {
            ((ReqInka_Uebergabestelle) request).setObjInka_Uebergabestelle(
                (Inka_Uebergabestelle) record);
            iip.setInka_Uebergabestelle((ReqInka_Uebergabestelle) request);
            return;
        }
        if (record instanceof Inka_Messstelle) {
            ((ReqInka_Messstelle) request).setObjInka_Messstelle(
                (Inka_Messstelle) record);
            iip.setInka_Messstelle((ReqInka_Messstelle) request);
            return;
        }
        if (record instanceof Inka_Anfallstelle) {
            ((ReqInka_Anfallstelle) request).setObjInka_Anfallstelle(
                (Inka_Anfallstelle) record);
            iip.setInka_Anfallstelle((ReqInka_Anfallstelle) request);
            return;
        }
        if (record instanceof Inka_Anlage) {
            ((ReqInka_Anlage) request).setObjInka_Anlage((Inka_Anlage) record);
            iip.setInka_Anlage((ReqInka_Anlage) request);
            return;
        }
        if (record instanceof Inka_Messst_Anlage) {
            ((ReqInka_Messst_Anlage) request).setObjInka_Messst_Anlage(
                (Inka_Messst_Anlage) record);
            iip.setInka_Messst_Anlage((ReqInka_Messst_Anlage) request);
            return;
        }
        if (record instanceof Inka_Anfallst_Messst) {
            ((ReqInka_Anfallst_Messst) request).setObjInka_Anfallst_Messst(
                (Inka_Anfallst_Messst) record);
            iip.setInka_Anfallst_Messst((ReqInka_Anfallst_Messst) request);
            return;
        }
        if (record instanceof Inka_Anfallst_Anlage) {
            ((ReqInka_Anfallst_Anlage) request).setObjInka_Anfallst_Anlage(
                (Inka_Anfallst_Anlage) record);
            iip.setInka_Anfallst_Anlage((ReqInka_Anfallst_Anlage) request);
            return;
        }
        if (record instanceof Inka_Anfallst_Stoffe) {
            ((ReqInka_Anfallst_Stoffe) request).setObjInka_Anfallst_Stoffe(
                (Inka_Anfallst_Stoffe) record);
            iip.setInka_Anfallst_Stoffe((ReqInka_Anfallst_Stoffe) request);
            return;
        }
        if (record instanceof Inka_Probenahme) {
            ((ReqInka_Probenahme) request).setObjInka_Probenahme(
                (Inka_Probenahme) record);
            iip.setInka_Probenahme((ReqInka_Probenahme) request);
            return;
        }
        if (record instanceof Inka_Ueberwach_Ergebnis) {
            ((ReqInka_Ueberwach_Ergebnis) request).setObjInka_Ueberwach_Ergebnis(
                (Inka_Ueberwach_Ergebnis) record);
            iip.setInka_Ueberwach_Ergebnis((ReqInka_Ueberwach_Ergebnis) request);
            return;
        }
        if (record instanceof Inka_Ueberwachungswert) {
            ((ReqInka_Ueberwachungswert) request).setObjInka_Ueberwachungswert(
                (Inka_Ueberwachungswert) record);
            iip.setInka_Ueberwachungswert((ReqInka_Ueberwachungswert) request);
            return;
        }
        // TODO: Add new DEA/INKA-Table here
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
