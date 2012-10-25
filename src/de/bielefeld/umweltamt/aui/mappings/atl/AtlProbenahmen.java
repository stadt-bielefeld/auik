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

package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;
import de.bielefeld.umweltamt.aui.utils.GermanDouble;
import de.bielefeld.umweltamt.aui.utils.JRMapDataSource;

/**
 * A class that represents a row in the 'ATL_PROBENAHMEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class AtlProbenahmen extends AbstractAtlProbenahmen implements
    Serializable {
    private static final long serialVersionUID = 950596109574293371L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public static final String[] COLUMNS_AUFTRAG = {"auswahl", "Parameter",
            "Kennzeichnung", "Konservierung", "Zusatz"};

    public static final String[] COLUMNS_BESCHEID = {"Pos", "Parameter",
            "Grenzwert_Wert", "Grenzwert_Einheit", "Ergebnis_Wert",
            "Ergebnis_Einheit", "Gebühr", "Gr_Kl", "Fett", "inGruppe"};

    private boolean loadedAtlAnalysepositionen = false;

    /**
     * Simple constructor of AtlProbenahmen instances.
     */
    public AtlProbenahmen() {
        // This is intentionally left blank.
    }

    /**
     * Constructor of AtlProbenahmen instances given a simple primary key.
     * @param kennummer
     */
    public AtlProbenahmen(java.lang.Integer id) {
        super(id);
    }

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this);
    }

    /* Add customized code below */

    /**
     * Liefert alle Probenahmen einer bestimmten Art von einer bestimmten
     * Kläranlage.
     */
    public static List<?> getKSProbenahmen(AtlProbeart art, AtlKlaeranlagen ka) {
        return new DatabaseAccess()
            .createQuery(
                "FROM AtlProbenahmen as probenahme WHERE "
                    + "probenahme.atlProbepkt.atlProbeart = :art "
                    + "and probenahme.atlProbepkt.atlKlaeranlagen = :ka "
                    + "ORDER BY probenahme.datumDerEntnahme desc, "
                    + "probenahme.kennummer desc")
            .setEntity("art", art)
            .setEntity("ka", ka)
            .list();
    }

    /**
     * Liefert alle Probenahmen eines bestimmten Probepunktes.
     * @param pkt Der Probepunkt.
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     */
    public static List<?> getProbenahmen(AtlProbepkt pkt, boolean loadPos,
        int limit) {
        if (pkt.getObjektid() == null) {
            return new ArrayList<AtlProbenahmen>();
        }
        List<?> proben = null;
        proben = new DatabaseAccess()
            .createQuery(
                "FROM AtlProbenahmen as probenahme "
                    + "WHERE probenahme.atlProbepkt = :pkt "
                    + "ORDER BY probenahme.datumDerEntnahme desc, "
                    + "probenahme.kennummer desc"
                    + ((limit != -1) ? " LIMIT 5" : ""))
            .setEntity("pkt", pkt)
            .list();

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
//                        "FROM AtlProbenahmen as probenahme WHERE " +
//                        "probenahme.atlProbepkt = ? " +
//                        "ORDER BY probenahme.datumDerEntnahme desc, probenahme.kennummer desc",
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

    public static List<?> findProbenahmen(String suche, String property) {
        String suche2 = suche.toLowerCase().trim() + "%";
        log.debug("Suche nach '" + suche2 + "' (" + property + ").");
        return new DatabaseAccess()
            .createQuery(
                "FROM AtlProbenahmen as probenahme WHERE "
                    + "lower(probenahme." + property + ") like :suche "
                    + "ORDER BY probenahme.datumDerEntnahme desc, "
                    + "probenahme.kennummer desc").setString("suche", suche2)
            .list();
    }

    public static List<?> findBescheiddruck() {
        return AtlProbenahmen.findAtlStatus(11);
    }

    public static List<?> findEingetragen() {
        return AtlProbenahmen.findAtlStatus(10);
    }

    public static List<?> findErgaenzt() {
        return AtlProbenahmen.findAtlStatus(9);
    }

    public static List<?> findAngelegt() {
        return AtlProbenahmen.findAtlStatus(8);
    }

    private static List<?> findAtlStatus(int id) {
        return new DatabaseAccess().createQuery(
            "FROM AtlProbenahmen as pn WHERE "
                + "pn.atlStatus.id = :id "
                + "ORDER BY pn.datumDerEntnahme desc, pn.kennummer desc")
            .setInteger("id", id)
            .list();
    }

    /**
     * überprüft ob eine Probenahme mit einer bestimmten Kennnummer existiert.
     * @param kennnummer Die Kennnummer.
     * @return <code>true</code>, falls bereits eine Probenahme mit dieser
     *         Kennung existiert, sonst <code>false</code>.
     */
    public static boolean probenahmeExists(String kennnummer) {
        int count;
        List<?> liste = null;
        liste = new DatabaseAccess()
            .createQuery("FROM AtlProbenahmen pn WHERE pn.kennummer = :kn")
            .setString("kn", kennnummer)
            .list();
        count = liste.size();
        return (count >= 1);
    }

    /**
     * Liefert eine bestimmte Probenahme.
     * @param id Die ID der Probenahme
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese
     *         nicht existiert
     */
    public static AtlProbenahmen getProbenahme(Integer id) {
        return getProbenahme(id, false);
    }

    /**
     * Liefert eine bestimmte Probenahme.
     * @param id Die ID der Probenahme
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese
     *         nicht existiert
     */
    public static AtlProbenahmen getProbenahme(Integer id, boolean loadPos) {
        if (loadPos) {
            return (AtlProbenahmen) new DatabaseAccess()
                .getAndInitCollections(AtlProbenahmen.class, id);
        } else {
            return (AtlProbenahmen) new DatabaseAccess()
                .get(AtlProbenahmen.class, id);
        }
    }

    /**
     * Liefert eine bestimmte Probenahme.
     * @param kennummer Die Kennummer der Probenahme
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese
     *         nicht existiert
     */
    public static AtlProbenahmen getProbenahme(String kennummer, boolean loadPos) {
        return (AtlProbenahmen) new DatabaseAccess()
            .createQuery(
                "FROM AtlProbenahmen " +
                "WHERE kennummer = :kennnummer")
            .setString("kennnummer", kennummer)
            .uniqueResult();
    }

    public static boolean saveOrUpdateProbenahme(AtlProbenahmen probe) {
        return new DatabaseAccess().saveOrUpdate(probe);
    }

    public static boolean removeProbenahme(AtlProbenahmen probe) {
        return new DatabaseAccess().delete(probe);
    }

    public static List<?> sortAnalysepositionen(AtlProbenahmen probe) {
        AtlProbenahmen newProbe = null;
        newProbe = (AtlProbenahmen) new DatabaseAccess().get(
            AtlProbenahmen.class, probe.getId());

        List<?> sortedPositionen = null;
//        sortedPositionen = new DatabaseAccess().createFilter(
//            newProbe.getAtlAnalysepositionen(),
//            "ORDER BY this.atlParameter.reihenfolge")
//            .list();
        sortedPositionen = new DatabaseAccess()
            .createQuery(
                "FROM AtlAnalyseposition "
                    + "WHERE atlProbenahmen = :probe "
                    + "ORDER BY atlParameter.reihenfolge")
            .setEntity("probe", newProbe)
            .list();

        return sortedPositionen;
    }

    public static JRMapDataSource getAuftragDataSource(AtlProbenahmen probe) {
        List<?> sorted = new DatabaseAccess()
            .createQuery(
                "FROM AtlAnalyseposition AS pos "
                + "WHERE pos.atlProbenahmen = :probe "
                    + "AND pos.atlParameter.bezeichnung not like "
                        + "'%bei Probenahme' "
                + "ORDER BY pos.atlParameter.reihenfolge")
            .setEntity("probe", probe)
            .list();

        int elements = sorted.size();

        Object[][] values = new Object[elements][];
        Object[] columns;

        for (int i = 0; i < elements; i++) {
            columns = new Object[5];

            AtlAnalyseposition pos = (AtlAnalyseposition) sorted.get(i);
            AtlParameter parameter = pos.getAtlParameter();
            String bezeichnung = parameter.getBezeichnung();
            String kennzeichnung = parameter.getKennzeichnung();
            String konservierung = parameter.getKonservierung();
            String zusatz = parameter.getAnalyseverfahren();

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
        List<?> sorted = sortAnalysepositionen(probe);
        List<?> params = probe.getAtlParameter();
        int elements = sorted.size();

        Object[][] values = new Object[elements][];
        Object[] columns;

        // TODO: This map is basically never used...
        Map<Integer, AtlParameterGruppen> groups =
            new HashMap<Integer, AtlParameterGruppen>(1);

        for (int i = 0; i < elements; i++) {
            columns = new Object[COLUMNS_BESCHEID.length];

            AtlAnalyseposition pos = (AtlAnalyseposition) sorted.get(i);
            AtlParameter parameter = pos.getAtlParameter();
//            String einheit          = pos.getAtlEinheiten().getBezeichnung();
            String grenzwert = "";
            if (parameter.getGrenzwert() != null
                && parameter.getGrenzwert() <= 10) {
                grenzwert = parameter.getGrenzwert().toString()
                    .replace(".", ",");
            } else if (parameter.getGrenzwert() != null
                && parameter.getGrenzwert() > 10) {
                grenzwert = parameter.getGrenzwert().toString()
                    .replace(".0", "");
            }
            String wert = "";
            if (pos.getWert() == 0.0) {
                wert = pos.getWert().toString().replace(".", ",");
            } else if (pos.getWert() < 0.009) {
                wert = pos.getWert().toString().substring(0, 5);
                wert = wert.replace(".", ",");
            } else if (pos.getWert() < 100) {
                wert = pos.getWert().toString().replace(".", ",");
            } else {
                wert = pos.getWert().toString().replace(".0", "");
            }
            Boolean fett = false;
            if (pos.getWert() != null && parameter.getGrenzwert() != null
                && pos.getWert() > parameter.getGrenzwert()) {
                fett = true;
            }
            String gebuehr = "0,00 €";
            if (parameter.getPreisfueranalyse() != 0)
                gebuehr = new GermanDouble(parameter.getPreisfueranalyse())
                    .toString() + " €";

            AtlParameterGruppen gr = parameter.getAtlParameterGruppe();
            int groupId = gr != null ? gr.getId() : -1;
            boolean inGroup = AtlParameterGruppen.isGroupComplete(groupId,
                params);

//            if (inGroup) {
//                groups.put(groupId, gr);
//            }
            String einh = "";
            if (!pos.getAtlEinheiten().getBezeichnung().equals("ohne")) {
                einh = pos.getAtlEinheiten().getBezeichnung();
            }

            columns[0] = i + 1;
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

        Object[][] newValues = new Object[elements + numGroups][];
        int i;

        for (i = 0; i < elements; i++) {
            newValues[i] = values[i];
        }

        Collection<?> theGroups = groups.values();
        for (Object obj : theGroups) {
            AtlParameterGruppen apg = (AtlParameterGruppen) obj;

            String gebuehr = new GermanDouble(apg.getPreisfueranalyse())
                .toString() + " €";

            columns = new Object[COLUMNS_BESCHEID.length];
            columns[0] = i + 1;
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

    public List<?> getAtlParameter() {
        List<?> sorted = sortAnalysepositionen(this);

        int num = sorted != null ? sorted.size() : 0;

        List<AtlParameter> params = new ArrayList<AtlParameter>(num);

        for (int i = 0; i < num; i++) {
            params.add(((AtlAnalyseposition) sorted.get(i)).getAtlParameter());
        }

        return params;
    }

    /**
     * @return Die ID der Probeart des Probepunktes dieser Probe
     */
    public AtlProbeart getProbeArt() {
        return this.getAtlProbepkt().getAtlProbeart();
    }

    /**
     * @return <code>true</code>, wenn die Probeart des Probepunktes dieser
     *         Probe Rohschlamm oder Faulschlamm ist, sonst <code>false</code>
     */
    public boolean isKlaerschlammProbe() {
        return (
            this.getProbeArt().equals(
                DatabaseConstants.ATL_PROBEART_FAULSCHLAMM) ||
            this.getProbeArt().equals(
                DatabaseConstants.ATL_PROBEART_ROHRSCHLAMM));
    }

    /**
     * @return <code>true</code>, wenn die Probeart des Probepunktes dieser
     *         Probe Rohschlamm oder Faulschlamm ist, sonst <code>false</code>
     */
    public boolean isSielhautProbe() {
        return (this.getProbeArt().equals(
            DatabaseConstants.ATL_PROBEART_SIELHAUT));
    }

    /**
     * Fügt dieser Probenahme eine Analyseposition hinzu (und sorgt für die
     * richtigen Fremdschlüssel in den Tabellen).
     * @param pos Die neue Analyseposition
     */
    public void addAnalyseposition(AtlAnalyseposition pos) {
        pos.setAtlProbenahmen(this);
        this.loadAtlAnalysepositionen().add(pos);
    }

    /**
     * Suche eine bestimmte AtlAnalyseposition anhand des Ordnungsbegriffs eines
     * Parameters.
     * @param ordnungsbegriff Der Ordnungsbegriff eines AtlParameter der zu der
     *            AtlAnalyseposition geh&ouml;rt.
     * @return eine bereits existente {@link AtlAnalyseposition} oder eine neue
     *         {@link AtlAnalyseposition}.
     */
    public AtlAnalyseposition findAtlAnalyseposition(AtlParameter parameter,
        AtlEinheiten einheit, boolean createNew) {
        String ordnungsbegriff = parameter.getOrdnungsbegriff();
        Set<AtlAnalyseposition> positionen =
            (Set<AtlAnalyseposition>) loadAtlAnalysepositionen();
        Iterator<AtlAnalyseposition> iter = positionen.iterator();

        // TODO: Why don't we use a query here?
        while (iter.hasNext()) {
            AtlAnalyseposition tmp = (AtlAnalyseposition) iter.next();
            AtlParameter param = tmp.getAtlParameter();

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
    public AtlAnalyseposition findAtlAnalyseposition(AtlParameter parameter,
        AtlEinheiten einheit) {
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

    /**
     * Load and return AtlAnalysepositionen<br>
     * Will only work on a saved AtlProbenahme.
     * @return
     */
    public Set<AtlAnalyseposition> loadAtlAnalysepositionen() {
      // TODO: This is not an optimal solution...
      if (!loadedAtlAnalysepositionen) {
          super.setAtlAnalysepositionen(
              AtlAnalyseposition.getAnalysepositionen(this));
          loadedAtlAnalysepositionen = true;
      }
//      super.setAtlAnalysepositionen(
//          AtlAnalyseposition.getAnalysepositionen(this));
      return super.getAtlAnalysepositionen();
    }

    /**
     * Return all Collections which need to be initialized.
     * @return An array of all Collections
     */
    @Override
    public Vector<Collection<?>> getToInitCollections() {
        Vector<Collection<?>> collections = new Vector<Collection<?>>();
        collections.add(getAtlAnalysepositionen());
        return collections;
    }
}
