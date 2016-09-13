package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWassereinzugsgebiete;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsStandortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisLage;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisAdresse;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisGemarkung;

import org.hibernate.Session;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

/**
 * A combined tablemodel consisting of BasisLage and BasisAdresse to replace old
 * BasisStandort
 */
public class BasisLageAdresse{

    private BasisLage lage;
    private BasisAdresse adresse;
    private static final AuikLogger log = AuikLogger.getLogger();

    public BasisLageAdresse(BasisLage lage, BasisAdresse adresse){
        this.lage = lage;
        this.adresse = adresse;
    }

    public BasisLageAdresse(){
        this.lage = BasisLage.merge(new BasisLage());
        this.adresse = BasisAdresse.merge(new BasisAdresse());
        
    }

    /**
      * Create a BasisLageAdresse from a given BasisAdresse
      */
    public BasisLageAdresse(BasisAdresse adresse){
        this.adresse = adresse;
        Session sess = HibernateSessionFactory.currentSession();
        adresse.getBasisObjekts().size();
        BasisObjekt objekt = null;
        if(adresse.getBasisObjektStandort().toArray().length > 0 ){
            objekt = (BasisObjekt) adresse.getBasisObjektStandort().toArray()[0];
            this.lage = objekt.getBasisLage();
            log.debug("Created BasisLageAdresse with attached BasisObjekt");
        }
        else{
            //Adresse belongs to a Betreiber
            log.debug("No BasisObjekt conncted with BasisStandort #" + this.adresse);
            if(adresse.getBasisObjekts().toArray().length>0){
                log.debug(adresse.getBetrname() + " " + adresse.getBetranrede() + " is a Betreiber");
                this.lage = null;
                this.adresse = null;
            }
            else{
                log.debug(adresse.getBetrname() + " " + adresse.getBetranrede() + " "+adresse.getStrasse() + " " + adresse.getStrasse() + " " + "is no Betreiber");
            }
        }
    }

    /**
      * Create a BasisLageAdresse from a given BasisLage
      */
    public BasisLageAdresse(BasisLage lage){
        this.lage = lage;
        Session sess = HibernateSessionFactory.currentSession();
        lage.getBasisObjekts().size();
        BasisObjekt objekt = (BasisObjekt) lage.getBasisObjekts().toArray()[0];
        //BasisObjekt objekt = BasisObjekt.findByLageId(lage.getId());
        this.adresse = objekt.getBasisStandort();
        log.debug("Created LageAdresse from lage " + lage.getId() + " with Adresse " + adresse.getId());
    }



    public void setBasisLage(BasisLage lage){
        this.lage = lage;
    }

    public void setBasisAdresse(BasisAdresse adresse){
        this.adresse = adresse;
    }

    public BasisAdresse getBasisAdresse(){
        return this.adresse;
    }

    public BasisLage getBasisLage(){
        return this.lage;
    }

//Getter

    public Integer getId(){
        return this.adresse.getId();
    }

    public String getStrasse(){
        return adresse.getStrasse();
    }

    public Integer getHausnr(){
        return adresse.getHausnr();
    }

    public String getHausnrzus(){
        return adresse.getHausnrzus();
    }

    public String getPlz(){
        return adresse.getPlz();
    }

    public String getOrt(){
        return adresse.getOrt();
    }

    public BasisGemarkung getBasisGemarkung(){
        return lage.getBasisGemarkung();
    }

    public String getEntgebid(){
        return lage.getEntgebid();
    }

    public Float getE32(){
        return lage.getE32();
    }

    public Float getN32(){
        return lage.getN32();
    }

    public VawsStandortgghwsg getVawsStandortgghwsg(){
        return lage.getVawsStandortgghwsg();
    }

    public VawsWassereinzugsgebiete getVawsWassereinzugsgebiete(){
        return lage.getVawsWassereinzugsgebiete();
    }

    public String getFlur(){
        return lage.getFlur();
    }

    public String getFlurstueck(){
        return lage.getFlurstueck();
    }

    public String getRevihandz(){
        if(lage.getRevihandz() != null){
            return lage.getRevihandz();
        }
        else{
            return adresse.getRevihandz();
        }
    }

    public Date getRevidatum(){
        if(lage.getRevidatum() != null){
            return lage.getRevidatum();
        }
        else{
            return adresse.getRevidatum();
        }
    }

    public String getSachbe33rav(){
        return lage.getSachbe33rav();
    }

    public Integer getWassermenge(){
        return lage.getWassermenge();
    }

    

//Setter

    public void setHausnr(Integer hausnr){
        this.adresse.setHausnr(hausnr);
    }

    public void setHausnrzus(String hausnrzus){
        this.adresse.setHausnrzus(hausnrzus);
    }

    public void setStrasse(String strasse){
        this.adresse.setStrasse(strasse);
    }

    public void setPlz(String plz){
        this.adresse.setPlz(plz);
    }

    public void setOrt(String ort){
        this.adresse.setOrt(ort);
    }

    public void setVawsStandortgghwsg(VawsStandortgghwsg vaws){
        this.lage.setVawsStandortgghwsg(vaws);
    }

    public void setEntgebid(String entgebid){
        this.lage.setEntgebid(entgebid);
    }

    public void setVawsWassereinzugsgebiete(VawsWassereinzugsgebiete geb){
        this.lage.setVawsWassereinzugsgebiete(geb);
    }

    public void setBasisGemarkung(BasisGemarkung gemarkung){
        this.lage.setBasisGemarkung(gemarkung);
    }

    public void setFlur(String flur){
        this.lage.setFlur(flur);
    }

    public void setFlurstueck(String flurstueck){
        this.lage.setFlurstueck(flurstueck);
    }

    public void setE32(Float e32){
        this.lage.setE32(e32);
    }

    public void setN32(Float n32){
        this.lage.setN32(n32);
    }

    public void setRevidatum(Date date){
        this.lage.setRevidatum(date);
        this.adresse.setRevidatum(date);
    }

    public void setRevihandz(String handz){
        this.lage.setRevihandz(handz);
        this.adresse.setRevihandz(handz);
    }

    public void setSachbe33rav(String sachbe33rav){
        lage.setSachbe33rav(sachbe33rav);
    }

    public void setWassermenge(Integer wassermenge){
        lage.setWassermenge(wassermenge);
    }

    public boolean merge(){
        return this.adresse.merge() && this.lage.merge();
    }

    public static BasisLageAdresse merge(BasisLageAdresse detached){
        BasisLage lage = BasisLage.merge(detached.getBasisLage());
        BasisAdresse adresse = BasisAdresse.merge(detached.getBasisAdresse());
        if(lage != null && adresse != null){
            return new BasisLageAdresse(lage, adresse);
        }
        else{
            return null;
        }
    }

    public static boolean delete(BasisLageAdresse removedStandort){
        if(BasisLage.delete(removedStandort.getBasisLage()) && BasisAdresse.delete(removedStandort.getBasisAdresse())){
            return true;
        }
        else{
            return false;
        }
    }

    /**
      * Adds a BasisObjekt to attached BasisLage and BasisAdresse
      */
    public void addBasisObjekt(BasisObjekt o){
        log.debug("Adding BasisObjekt #" + o.getId() + " to BasisLage and BasisAdresse");
        this.adresse.setObjektid(o.getId());
        Set<BasisObjekt> temp = this.adresse.getBasisObjekts();
        temp.add(o);
        this.adresse.setBasisObjekts(temp);
        temp = this.lage.getBasisObjekts();
        temp.add(o);
        this.lage.setBasisObjekts(temp);
    }

    public String toString(){
        return this.adresse.getStrasse() + (this.adresse.getHausnr() != null ? " " + this.adresse.getHausnr() : "") + (this.adresse.getHausnrzus() != null ? this.adresse.getHausnrzus() : ""); 
    }

}

