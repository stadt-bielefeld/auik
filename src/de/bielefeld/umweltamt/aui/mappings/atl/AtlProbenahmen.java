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

/*
 * Created Wed Feb 16 15:12:02 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.DatabaseManager;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.JRMapDataSource;
import de.bielefeld.umweltamt.aui.utils.GermanDouble;

/**
 * A class that represents a row in the 'ATL_PROBENAHMEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class AtlProbenahmen
    extends AbstractAtlProbenahmen
    implements Serializable
{
    /** Database manager */
    private static final DatabaseManager dbManager = DatabaseManager.getInstance();
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static final String[] COLUMNS_AUFTRAG = {
        "auswahl", "Parameter", "Kennzeichnung", "Konservierung", "Zusatz"
    };

    public static final String[] COLUMNS_BESCHEID = {
        "Pos", "Parameter", "Grenzwert_Wert", "Grenzwert_Einheit",
        "Ergebnis_Wert", "Ergebnis_Einheit", "Gebühr", "Gr_Kl", "Fett",
        "inGruppe"
    };


    /**
     * Simple constructor of AtlProbenahmen instances.
     */
    public AtlProbenahmen()
    {
    }

    /**
     * Constructor of AtlProbenahmen instances given a simple primary key.
     * @param kennummer
     */
    public AtlProbenahmen(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */

    /**
     * Liefert alle Probenahmen einer bestimmten Art von einer
     * bestimmten Kläranlage.
     */
    public static List getKSProbenahmen(AtlProbeart art, AtlKlaeranlagen ka) {
        // Evtl. Version mit gegebener Session bauen?
        List proben;
        try {
            Session session = HibernateSessionFactory.currentSession();
            proben = session.createQuery(
                    "from AtlProbenahmen as probenahme where " +
                    "probenahme.atlProbepkt.atlProbeart = ? " +
                    "and probenahme.atlProbepkt.atlKlaeranlagen = ? " +
                    "order by probenahme.datumDerEntnahme desc, probenahme.kennummer desc")
                    .setEntity(0, art)
                    .setEntity(1, ka)
                    .list();

            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }
        return proben;
    }

    /**
     * Liefert alle Probenahmen eines bestimmten Probepunktes.
     * @param pkt Der Probepunkt.
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     */
    public static List getProbenahmen(AtlProbepkt pkt, boolean loadPos, int limit) {
        List proben;
        if (pkt.getObjektid() != null) {
            try {
                Session session = HibernateSessionFactory.currentSession();
                Query query = session.createQuery("from AtlProbenahmen as probenahme where " +
                        "probenahme.atlProbepkt = ? " +
                        "order by probenahme.datumDerEntnahme desc, probenahme.kennummer desc");
                query.setParameter(0, pkt, Hibernate.entity(AtlProbepkt.class));
                if (limit != -1) {
                    query.setMaxResults(5);
                }
                proben = query.list();
//                proben = session.find(
//                        ,
//                        pkt,
//                        Hibernate.entity(AtlProbepkt.class)
//                );

                if (loadPos) {
                    for (int i = 0; i < proben.size(); i++) {
                        AtlProbenahmen probe = (AtlProbenahmen)proben.get(i);
                        Hibernate.initialize(probe.getAtlAnalysepositionen());
                    }
                }
            } catch (HibernateException e) {
                throw new RuntimeException("Datenbank-Fehler", e);
            } finally {
                HibernateSessionFactory.closeSession();
            }
        } else {
            proben = new ArrayList();
        }

        return proben;
    }

//    /**
//     * Liefert alle Probenahmen eines bestimmten Probepunktes.
//     * @param pkt Der Probepunkt.
//     * @param loadPos Sollen die Analysepositionen auch geholt werden?
//     */
//    public static List getProbenahmenAndPos(AtlProbepkt pkt, AtlParameter[] params) {
//        List proben;
//        if (pkt.getPktId() != null) {
//            try {
//                Session session = HibernateSessionFactory.currentSession();
//                proben = session.find(
//                        "from AtlProbenahmen as probenahme where " +
//                        "probenahme.atlProbepkt = ? " +
//                        "order by probenahme.datumDerEntnahme desc, probenahme.kennummer desc",
//                        pkt,
//                        Hibernate.entity(AtlProbepkt.class)
//                );
//            } catch (HibernateException e) {
//                throw new RuntimeException("Datenbank-Fehler", e);
//            } finally {
//                HibernateSessionFactory.closeSession();
//            }
//        } else {
//            proben = new ArrayList();
//        }
//
//        return proben;
//    }

    public static List findProbenahmen(String suche, String property) {
        String suche2 = suche.toLowerCase().trim() + "%";
        log.debug("Suche nach '" + suche2 + "' (" + property + ").");
        List proben;
        try {
            Session session = HibernateSessionFactory.currentSession();
            proben = session.createQuery(
                    "from AtlProbenahmen as probenahme where " +
                    "lower(probenahme."+property+") like ? " +
                    "order by probenahme.datumDerEntnahme desc, probenahme.kennummer desc")
                    .setString(0, suche2)
                    .list();
            HibernateSessionFactory.closeSession();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }
        return proben;
    }

    public static List findBescheiddruck() {
        List proben;
        try {
            Session session = HibernateSessionFactory.currentSession();
            proben = session.createQuery(
                    "from AtlProbenahmen as pn where " +
                    "pn.atlStatus.id = 11 " +
                    "order by pn.datumDerEntnahme desc, pn.kennummer desc")
                    .list();
            HibernateSessionFactory.closeSession();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }
        return proben;
    }

    public static List findEingetragen() {
        List proben;
        try {
            Session session = HibernateSessionFactory.currentSession();
            proben = session.createQuery(
                    "from AtlProbenahmen as pn where " +
                    "pn.atlStatus.id = 10 " +
                    "order by pn.datumDerEntnahme desc, pn.kennummer desc")
                    .list();
            HibernateSessionFactory.closeSession();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }
        return proben;
    }

    public static List findErgaenzt() {
        List proben;
        try {
            Session session = HibernateSessionFactory.currentSession();
            proben = session.createQuery(
                    "from AtlProbenahmen as pn where " +
                    "pn.atlStatus.id = 9 " +
                    "order by pn.datumDerEntnahme desc, pn.kennummer desc")
                    .list();
            HibernateSessionFactory.closeSession();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }
        return proben;
    }

    public static List findAngelegt() {
        List proben;
        try {
            Session session = HibernateSessionFactory.currentSession();
            proben = session.createQuery(
                    "from AtlProbenahmen as pn where " +
                    "pn.atlStatus.id = 8 " +
                    "order by pn.datumDerEntnahme desc, pn.kennummer desc")
                    .list();
            HibernateSessionFactory.closeSession();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }
        return proben;
    }

    /**
     * überprüft ob eine Probenahme mit einer bestimmten Kennnummer existiert.
     * @param kennnummer Die Kennnummer.
     * @return <code>true</code>, falls bereits eine Probenahme mit dieser Kennung existiert, sonst <code>false</code>.
     */
    public static boolean probenahmeExists(String kennnummer) {
        String Kennummer = kennnummer;
        int count;
        try {
            Session session = HibernateSessionFactory.currentSession();

            List liste;
            liste = session.createQuery("from AtlProbenahmen pn where pn.kennummer = ?")
            .setString(0, kennnummer)
            .list();

            count = liste.size();
            HibernateSessionFactory.closeSession();


        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }

        return (count >= 1);



    }

    /**
     * Liefert eine bestimmte Probenahme.
     * @param id Die ID der Probenahme
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese nicht existiert
     */
    public static AtlProbenahmen getProbenahme(Integer id) {
        return getProbenahme(id, false);
    }

    /**
     * Liefert eine bestimmte Probenahme.
     * @param id Die ID der Probenahme
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese nicht existiert
     */
    public static AtlProbenahmen getProbenahme(Integer id, boolean loadPos) {
        AtlProbenahmen probe;
        try {
            Session session = HibernateSessionFactory.currentSession();
            probe = (AtlProbenahmen) session.get(AtlProbenahmen.class, id);
            if (loadPos && probe != null) {
                Hibernate.initialize(probe.getAtlAnalysepositionen());
                //Collection sorted = session.filter(probe.getAtlAnalysepositionen(), "order by this.atlParameter.ordnungsbegriff desc");
                //AUIKataster.debugOutput("Sorted:\n " + sorted);
                //probe.setAtlAnalysepositionen(new HashSet(sorted));
                log.debug("APos geladen:\n " + probe.getAtlAnalysepositionen());
            }
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (AtlProbenahmen)", e);
        }

        return probe;
    }

    /**
     * Liefert eine bestimmte Probenahme.
     * @param kennummer Die Kennummer der Probenahme
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese nicht existiert
     */
    public static AtlProbenahmen getProbenahme(String kennummer, boolean loadPos) {
        AtlProbenahmen probe;
        try {
            Session session = HibernateSessionFactory.currentSession();
            probe = (AtlProbenahmen) session.createQuery("from AtlProbenahmen where kennummer = ?")
                .setString(0, kennummer)
                .uniqueResult();
            if (loadPos && probe != null) {
                Hibernate.initialize(probe.getAtlAnalysepositionen());
                //Collection sorted = session.filter(probe.getAtlAnalysepositionen(), "order by this.atlParameter.ordnungsbegriff desc");
                //AUIKataster.debugOutput("Sorted:\n " + sorted);
                //probe.setAtlAnalysepositionen(new HashSet(sorted));
                log.debug("APos geladen:\n " + probe.getAtlAnalysepositionen());
            }
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (AtlProbenahmen)", e);
        }

        return probe;
    }

    public static boolean saveProbenahme(AtlProbenahmen probe) {
        boolean success;

        Transaction tx = null;

        try {
            Session session = HibernateSessionFactory.currentSession();

            tx = session.beginTransaction();

            session.save(probe);

            tx.commit();

            success = true;

        } catch (HibernateException e) {
            success = false;
            e.printStackTrace();
            // Falls während der Änderungen ein Hibernate Fehler auftritt
            if (tx != null) {
                try {
                    // Alle Ünderungen rückgängig machen
                    tx.rollback();
                } catch (HibernateException e1) {
                    throw new RuntimeException("Datenbank-Fehler (Anh49Ortstermine)", e);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return success;
    }

    public static boolean updateProbenahme(AtlProbenahmen probe) {
        boolean success;

        Transaction tx = null;

        try {
            Session session = HibernateSessionFactory.currentSession();

            tx = session.beginTransaction();

            session.saveOrUpdate(probe);

            tx.commit();

            success = true;

        } catch (HibernateException e) {
            success = false;
            e.printStackTrace();
            // Falls während der Änderungen ein Hibernate Fehler auftritt
            if (tx != null) {
                try {
                    // Alle Änderungen rückgängig machen
                    tx.rollback();
                } catch (HibernateException e1) {
                    throw new RuntimeException("Datenbank-Fehler (Anh49Ortstermine)", e);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return success;
    }

    public static boolean removeProbenahme(AtlProbenahmen probe) {
        boolean success;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.delete(probe);
            tx.commit();
            success = true;
        } catch (HibernateException e) {
            success = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    dbManager.handleDBException(e1, "ProbenahmenModel.objectRemoved", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return success;
    }

    public static List sortAnalysepositionen(AtlProbenahmen probe) {
        List sortedPositionen;

        try {
            Session session = HibernateSessionFactory.currentSession();
            AtlProbenahmen newProbe = (AtlProbenahmen) session.get(AtlProbenahmen.class, probe.getId());
            //Hibernate.initialize(probe.getAtlAnalysepositionen());
            sortedPositionen = session.createFilter(newProbe.getAtlAnalysepositionen(),
            		"order by this.atlParameter.reihenfolge")
                .list();

            //AUIKataster.debugOutput("Sorted (Klasse: "+sortedPositionen.getClass()+"):\n " + sortedPositionen);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (AtlAnalysepos.)", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return sortedPositionen;
    }

    public static List sortExterneAnalysepositionen(AtlProbenahmen probe) {
        List sortedPositionen;

        try {
            Session session = HibernateSessionFactory.currentSession();
            AtlProbenahmen newProbe = (AtlProbenahmen) session.get(AtlProbenahmen.class, probe.getId());
            //Hibernate.initialize(probe.getAtlAnalysepositionen());
            sortedPositionen = session.createFilter(newProbe.getAtlAnalysepositionen(),
            		"where this.atlParameter.bezeichnung not like ? " +
            		"order by this.atlParameter.reihenfolge")
            	.setString(0, new String("%bei Probenahme"))
                .list();

            //AUIKataster.debugOutput("Sorted (Klasse: "+sortedPositionen.getClass()+"):\n " + sortedPositionen);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (AtlAnalysepos.)", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return sortedPositionen;
    }


    public static JRMapDataSource getAuftragDataSource(AtlProbenahmen probe) {
        List sorted   = sortExterneAnalysepositionen(probe);
        int  elements = sorted.size();

        Object[][] values  = new Object[elements][];
        Object[]   columns;

        for (int i = 0; i < elements; i++) {
            columns = new Object[5];

            AtlAnalyseposition pos   = (AtlAnalyseposition) sorted.get(i);
            AtlParameter parameter   = pos.getAtlParameter();
            String       bezeichnung = parameter.getBezeichnung();
            String       kennzeichnung = parameter.getKennzeichnung();
            String       konservierung = parameter.getKonservierung();
            String       zusatz = parameter.getAnalyseverfahren();

            columns[0] = true; // this value is always true
            columns[1] = bezeichnung;
            columns[2] = kennzeichnung;
            columns[3] = konservierung;
            columns[4] = zusatz;

            values[i] = columns;
        }

        return new JRMapDataSource(COLUMNS_AUFTRAG, values);
    }


    public static JRMapDataSource getBescheidDataSource(AtlProbenahmen probe) {
        List sorted   = sortAnalysepositionen(probe);
        List params   = probe.getAtlParameter();
        int  elements = sorted.size();

        Object[][] values  = new Object[elements][];
        Object[]   columns;

        Map groups = new HashMap(1);

        for (int i = 0; i < elements; i++) {
            columns = new Object[COLUMNS_BESCHEID.length];

            AtlAnalyseposition pos  = (AtlAnalyseposition) sorted.get(i);
            AtlParameter parameter  = pos.getAtlParameter();
            String einheit          = pos.getAtlEinheiten().getBezeichnung();
            String grenzwert        = "";
            if (parameter.getGrenzwert() != null && parameter.getGrenzwert() <= 10){
                grenzwert = parameter.getGrenzwert().toString().replace(".", ",");
            }
            else if (parameter.getGrenzwert() != null && parameter.getGrenzwert() > 10){
                grenzwert = parameter.getGrenzwert().toString().replace(".0", "");
            }
            String wert = "";
            if (pos.getWert() == 0.0){
            	wert = pos.getWert().toString().replace(".", ",");
            }
            else if (pos.getWert() < 0.009){
            	wert = pos.getWert().toString().substring(0, 5);
                wert = wert.replace(".", ",");
            }
            else if (pos.getWert() < 100){
            	wert = pos.getWert().toString().replace(".", ",");
            }
            else {
                wert = pos.getWert().toString().replace(".0", "");
            }
            Boolean fett = false;
            if (pos.getWert() != null && parameter.getGrenzwert() != null && pos.getWert() > parameter.getGrenzwert()){
                fett = true;
            }
            String gebuehr    = "0,00 €";
            if (parameter.getPreisfueranalyse() != 0 )
                gebuehr = new GermanDouble(
                        parameter.getPreisfueranalyse()).toString() + " €";

            AtlParameterGruppen gr = parameter.getAtlParameterGruppe();
            int groupId            = gr != null ? gr.getId() : -1;
            boolean inGroup = AtlParameterGruppen.isGroupComplete(groupId, params);

//            if (inGroup) {
//                groups.put(groupId, gr);
//            }
            String einh = "";
            if (!pos.getAtlEinheiten().getBezeichnung().equals("ohne")){
            	einh = pos.getAtlEinheiten().getBezeichnung();
            }

            columns[0] = i+1;
            columns[1] = parameter.getBezeichnung();
            columns[2] = grenzwert;
            columns[3] = einh;
            columns[4] = wert;
            columns[5] = einh;
            columns[6] = inGroup ? "0,00 €" : gebuehr;
            columns[7] = pos.getGrkl();
            columns[8] = fett;

            values[i] = columns;
        }

        int numGroups = groups.size();

        if (numGroups == 0) {
            return new JRMapDataSource(COLUMNS_BESCHEID, values);
        }

        Object[][] newValues = new Object[elements+numGroups][];
        int i;

        for (i = 0; i < elements; i++) {
            newValues[i] = values[i];
        }

        Collection theGroups = groups.values();
        for (Object obj: theGroups) {
            AtlParameterGruppen apg = (AtlParameterGruppen) obj;

            String gebuehr = new GermanDouble(
                apg.getPreisfueranalyse()).toString() + " €";

            columns = new Object[COLUMNS_BESCHEID.length];
            columns[0] = i+1;
            columns[1] = apg.getName();
            columns[2] = "";
            columns[3] = "";
            columns[4] = "";
            columns[5] = "";
            columns[6] = gebuehr;
            columns[7] = "";
            columns[8] = "";

            newValues[i] = columns;
        }

        return new JRMapDataSource(COLUMNS_BESCHEID, newValues);
    }


    public List getAtlParameter() {
        List sorted = sortAnalysepositionen(this);

        int num = sorted != null ? sorted.size() : 0;

        List params = new ArrayList(num);

        for (int i = 0; i < num; i++) {
            params.add(((AtlAnalyseposition) sorted.get(i)).getAtlParameter());
        }

        return params;
    }

    /**
     * @return Einen String der Form "[Probe: Kennummer, Probeart, Datum, Anz.Positionen]"
     * bzw. "[Probe: Kennummer, Probeart, Datum, N/A]" falls die Positionen noch nicht aus
     * der Datenbank geholt wurden.
     */
    @Override
    public String toString() {
        String tmp = "[Probe: " + getKennummer() + ", " + getProbeArt() + ", " + AuikUtils.getStringFromDate(getDatumDerEntnahme());

        if (getZeitDerEntnahmen() != null) {
            tmp += " " + getZeitDerEntnahmen();
        }
        tmp += ", ";

        if (Hibernate.isInitialized(getAtlAnalysepositionen())) {
            tmp += getAtlAnalysepositionen().size();
        } else {
            tmp += "N/A";
        }

        tmp +=  "]";
        return tmp;
    }

    /**
     * @return Die ID der Probeart des Probepunktes dieser Probe
     */
    public AtlProbeart getProbeArt() {
        return this.getAtlProbepkt().getAtlProbeart();
    }

    /**
     * @return <code>true</code>, wenn die Probeart des Probepunktes dieser Probe Rohschlamm oder Faulschlamm ist, sonst <code>false</code>
     */
    public boolean isKlaerschlammProbe() {
        if (this.getProbeArt().isFaulschlamm()) {
            return true;
        } else if (this.getProbeArt().isRohschlamm()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAnalysepositionenInitialized() {
        return Hibernate.isInitialized(getAtlAnalysepositionen());
    }

    /**
     * Fügt dieser Probenahme eine Analyseposition hinzu (und sorgt
     * für die richtigen Fremdschlüssel in den Tabellen).
     * @param pos Die neue Analyseposition
     */
    public void addAnalyseposition(AtlAnalyseposition pos) {
        pos.setAtlProbenahmen(this);
        this.getAtlAnalysepositionen().add(pos);
    }


    /**
     * Suche eine bestimmte AtlAnalyseposition anhand des Ordnungsbegriffs eines
     * Parameters.
     *
     * @param ordnungsbegriff Der Ordnungsbegriff eines AtlParameter der zu der
     * AtlAnalyseposition geh&ouml;rt.
     *
     * @return eine bereits existente {@link AtlAnalyseposition} oder eine neue
     * {@link AtlAnalyseposition}.
     */
    public AtlAnalyseposition findAtlAnalyseposition(
        AtlParameter parameter, AtlEinheiten einheit, boolean createNew)
    {
        String ordnungsbegriff = parameter.getOrdnungsbegriff();
        Set positionen         = getAtlAnalysepositionen();
        Iterator iter          = positionen.iterator();

        while (iter.hasNext()) {
            AtlAnalyseposition tmp = (AtlAnalyseposition) iter.next();
            AtlParameter     param = tmp.getAtlParameter();

            if (ordnungsbegriff.equals(param.getOrdnungsbegriff())) {
                return tmp;
            }
        }

        if (!createNew) {
            return null;
        }

        AtlAnalyseposition neu = new AtlAnalyseposition(this);
        neu.setAtlParameter(parameter);
        neu.setAtlEinheiten(einheit);

        addAnalyseposition(neu);

        return neu;
    }


    /**
     * Diese Methode ruft {@link findAtlAnalyseposition(AtlParameter,
     * AtlEinheiten, boolean)} mit einem gesetzten <code>createNew</code>
     * Parameter auf.
     */
    public AtlAnalyseposition findAtlAnalyseposition(
        AtlParameter parameter, AtlEinheiten einheit)
    {
        return findAtlAnalyseposition(parameter, einheit, true);
    }


    public BasisBetreiber getBasisBetreiber() {
        AtlProbepkt pkt = getAtlProbepkt();

        return pkt != null ? pkt.getBasisBetreiber() : null;
    }


    public BasisObjekt getBasisObjekt() {
        AtlProbepkt pkt = getAtlProbepkt();

        return pkt != null ? pkt.getBasisObjekt() : null;
    }
}
