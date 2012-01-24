/**
 * InkaInterfacePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.webservice;

public interface InkaInterfacePortType extends java.rmi.Remote {
    public java.lang.String testServiceEcho(java.lang.String inParameter) throws java.rmi.RemoteException;
    public java.lang.String getServiceVersion() throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Analysemethode getDea_Analysemethode(de.nrw.lds.tipi.inka.request.ReqDea_Analysemethode req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Anhang getDea_Anhang(de.nrw.lds.tipi.inka.request.ReqDea_Anhang req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Arbeitsstaetten getDea_Arbeitsstaetten(de.nrw.lds.tipi.inka.request.ReqDea_Arbeitsstaetten req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Einheiten getDea_Einheiten(de.nrw.lds.tipi.inka.request.ReqDea_Einheiten req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Gemeinde getDea_Gemeinde(de.nrw.lds.tipi.inka.request.ReqDea_Gemeinde req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Klaeranlage getDea_Klaeranlage(de.nrw.lds.tipi.inka.request.ReqDea_Klaeranlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Parameter getDea_Parameter(de.nrw.lds.tipi.inka.request.ReqDea_Parameter req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Probedauer getDea_Probedauer(de.nrw.lds.tipi.inka.request.ReqDea_Probedauer req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Stoffe getDea_Stoffe(de.nrw.lds.tipi.inka.request.ReqDea_Stoffe req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Stua getDea_Stua(de.nrw.lds.tipi.inka.request.ReqDea_Stua req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Tk25 getDea_Tk25(de.nrw.lds.tipi.inka.request.ReqDea_Tk25 req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Wasserbehoerden getDea_Wasserbehoerden(de.nrw.lds.tipi.inka.request.ReqDea_Wasserbehoerden req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Wz_Code getDea_Wz_Code(de.nrw.lds.tipi.inka.request.ReqDea_Wz_Code req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResDea_Adresse getDea_Adresse(de.nrw.lds.tipi.inka.request.ReqDea_Adresse req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Anfallstelle getInka_Anfallstelle(de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Anlage getInka_Anfallst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Messst getInka_Anfallst_Messst(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Stoffe getInka_Anfallst_Stoffe(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Anlage getInka_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Betrieb getInka_Betrieb(de.nrw.lds.tipi.inka.request.ReqInka_Betrieb req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Betriebseinrichtung getInka_Betriebseinrichtung(de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Fliessschema getInka_Fliessschema(de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Genehmigung getInka_Genehmigung(de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Messstelle getInka_Messstelle(de.nrw.lds.tipi.inka.request.ReqInka_Messstelle req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Messst_Anlage getInka_Messst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Probenahme getInka_Probenahme(de.nrw.lds.tipi.inka.request.ReqInka_Probenahme req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Uebergabestelle getInka_Uebergabestelle(de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Ueberwach_Ergebnis getInka_Ueberwach_Ergebnis(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.response.ResInka_Ueberwachungswert getInka_Ueberwachungswert(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setDea_Adresse(de.nrw.lds.tipi.inka.request.ReqDea_Adresse req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anfallstelle(de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anfallst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anfallst_Messst(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anfallst_Stoffe(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Betrieb(de.nrw.lds.tipi.inka.request.ReqInka_Betrieb req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Betriebseinrichtung(de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Fliessschema(de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Genehmigung(de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Messstelle(de.nrw.lds.tipi.inka.request.ReqInka_Messstelle req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Messst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Probenahme(de.nrw.lds.tipi.inka.request.ReqInka_Probenahme req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Uebergabestelle(de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Ueberwach_Ergebnis(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Ueberwachungswert(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteDea_Adresse(de.nrw.lds.tipi.inka.request.ReqDea_Adresse req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anfallstelle(de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anfallst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anfallst_Messst(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anfallst_Stoffe(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Betrieb(de.nrw.lds.tipi.inka.request.ReqInka_Betrieb req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Betriebseinrichtung(de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Fliessschema(de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Genehmigung(de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Messstelle(de.nrw.lds.tipi.inka.request.ReqInka_Messstelle req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Messst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Probenahme(de.nrw.lds.tipi.inka.request.ReqInka_Probenahme req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Uebergabestelle(de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Ueberwach_Ergebnis(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis req) throws java.rmi.RemoteException;
    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Ueberwachungswert(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert req) throws java.rmi.RemoteException;
}
