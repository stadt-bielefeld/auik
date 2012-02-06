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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ATL_PARAMETER' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AtlParameter extends AbstractAtlParameter implements Serializable {
    private static final long serialVersionUID = 2852105702010364133L;
    /** Die ID des Parameters "AOX" */
    final public static String AOX_ID = "L13430";
    /** Die ID des Parameters "Silber (Ag)" */
    final public static String SILBER_ID = "B00210";
    /** Die ID des Parameters "Gold" */
    final public static String GOLD_ID = "L11400";
    /** Die ID des Parameters "Aluminium (Al)" */
    final public static String ALUMINIUM_ID = "L11310";
    /** Die ID des Parameters "Arsen (As)" */
    final public static String ARSEN_ID = "L11420";
    /** Die ID des Parameters "Bor (B)" */
    final public static String BOR_ID = "L12110";
    /** Die ID des Parameters "Barium (Ba)" */
    final public static String BARIUM_ID = "B00070";
    /** Die ID des Parameters "Calcium (Ca)" */
    final public static String CALCIUM_ID = "L11220";
    /** Die ID des Parameters "Cadmium (Cd)" */
    final public static String CADMIUM_ID = "L11650";
    /** Die ID des Parameters "Kobalt (Co)" */
    final public static String KOBALT_ID = "B00108";
    /** Die ID des Parameters "Chrom (Cr)" */
    final public static String CHROM_ID = "L11510";
    /** Die ID des Parameters "Kupfer (Cu)" */
    final public static String KUPFER_ID = "L11610";
    /** Die ID des Parameters "Eisen (Fe)" */
    final public static String EISEN_ID = "B00041";
    /** Die ID des Parameters "Quecksilber (Hg)" */
    final public static String QUECKSILBER_ID = "L11660";
    /** Die ID des Parameters "Kalium (K)" */
    final public static String KALIUM_ID = "L11130";
    /** Die ID des Parameters "Magnesium (Mg)" */
    final public static String MAGNESIUM_ID = "L11210";
    /** Die ID des Parameters "Mangan (Mn)" */
    final public static String MANGAN_ID = "L11710";
    /** Die ID des Parameters "Natrium (Na)" */
    final public static String NATRIUM_ID = "L11120";
    /** Die ID des Parameters "Nickel (Ni)" */
    final public static String NICKEL_ID = "L11880";
    /** Die ID des Parameters "Natrium (Na)" */
    final public static String ORTHOPHOSPHAT_ID = "B00127";
    /** Die ID des Parameters "Nickel (Ni)" */
    final public static String PHOSPHAT_ID = "B00018";
    /** Die ID des Parameters "Blei (Pb)" */
    final public static String BLEI_ID = "L11380";
    /** Die ID des Parameters "Strontium (Sr)" */
    final public static String STRONTIUM_ID = "B00190";
    /** Die ID des Parameters "Zink (Zn)" */
    final public static String ZINK_ID = "L11640";
    /** Die ID des Parameters "Zirkonium (Zr)" */
    final public static String ZIRKONIUM_ID = "B00408";

    /** Die ID des Parameters "Trockensubstanz" */
    final public static String TROCKENSUBSTANZ_ID = "B00107";

    /** Die ID des Default-Parameters (Blei) */
    final public static String DEFAULT_ID = BLEI_ID;

    /**
     * Die Map für die beim Import unterstützten Parameter. Sie enthält eine
     * Zuordnung von Element- oder Parameternamen zu den jeweiligen
     * Ordnungsbegriffen der Parameter-Tabelle. Sie wird in initMap() gefüllt.
     */
    private static Map<String, String> sParams = null;

    /**
     * Simple constructor of AtlParameter instances.
     */
    public AtlParameter() {
    }

    /**
     * Constructor of AtlParameter instances given a simple primary key.
     * @param ordnungsbegriff
     */
    public AtlParameter(java.lang.String ordnungsbegriff) {
        super(ordnungsbegriff);
    }

    /* Add customized code below */

    /**
     * @return Der Name des Parameters.
     */
    @Override
    public String toString() {
        String tmp = getBezeichnung();
        if (tmp != null) {
            tmp = tmp.trim();
        }
        return tmp;
    }

    /**
     * Liefert den Ordnungsbegriff für einen Parameter.
     * @param name Die chemische Kurzbezeichnung des Elements oder der
     *            Parameter-Name (bspw. "Cu" oder "Kupfer").
     * @return Den Ordnungsbegriff des Parameters oder <code>null</code>, falls
     *         der Parameter nicht unterstützt wird.
     */
    public static String getOrdnungsbegriff(String name) {
        initMap();
        return sParams.get(name);
    }

    /**
     * Initialisiert die Map für die beim Import unterstützten Parameter. Sie
     * enthält eine Zuordnung von Element- oder Parameternamen zu den jeweiligen
     * Ordnungsbegriffen der Parameter-Tabelle.
     */
    private static void initMap() {
        if (sParams == null) {
            sParams = new HashMap<String, String>();

            // SielhautBearbeiten:
            sParams.put("Blei", AtlParameter.BLEI_ID);
            sParams.put("Cadmium", AtlParameter.CADMIUM_ID);
            sParams.put("Chrom", AtlParameter.CHROM_ID);
            sParams.put("Gold", AtlParameter.GOLD_ID);
            sParams.put("Kupfer", AtlParameter.KUPFER_ID);
            sParams.put("Nickel", AtlParameter.NICKEL_ID);
            sParams.put("Quecksilber", AtlParameter.QUECKSILBER_ID);
            sParams.put("Silber", AtlParameter.SILBER_ID);
            sParams.put("Trockensubstanz", AtlParameter.TROCKENSUBSTANZ_ID);
            sParams.put("Zink", AtlParameter.ZINK_ID);

            // ICP:
            sParams.put("Ag", AtlParameter.SILBER_ID);
            sParams.put("Au", AtlParameter.GOLD_ID);
            sParams.put("Al", AtlParameter.ALUMINIUM_ID);
            sParams.put("As", AtlParameter.ARSEN_ID);
            sParams.put("B",  AtlParameter.BOR_ID);
            sParams.put("Ba", AtlParameter.BARIUM_ID);
            sParams.put("Ca", AtlParameter.CALCIUM_ID);
            sParams.put("Cd", AtlParameter.CADMIUM_ID);
            sParams.put("Co", AtlParameter.KOBALT_ID);
            sParams.put("Cr", AtlParameter.CHROM_ID);
            sParams.put("Cu", AtlParameter.KUPFER_ID);
            sParams.put("Fe", AtlParameter.EISEN_ID);
            sParams.put("Hg", AtlParameter.QUECKSILBER_ID);
            sParams.put("K",  AtlParameter.KALIUM_ID);
            sParams.put("Mg", AtlParameter.MAGNESIUM_ID);
            sParams.put("Mn", AtlParameter.MANGAN_ID);
            sParams.put("Na", AtlParameter.NATRIUM_ID);
            sParams.put("Ni", AtlParameter.NICKEL_ID);
            sParams.put("Pb", AtlParameter.BLEI_ID);
            sParams.put("Sr", AtlParameter.STRONTIUM_ID);
            sParams.put("Zn", AtlParameter.ZINK_ID);
            sParams.put("Zr", AtlParameter.ZIRKONIUM_ID);
        }
    }

    /**
     * überprüft ob ein entsprechender Parameter importierbar ist.
     * @param name Die chemische Kurzbezeichnung des Elements oder der
     *            Parameter-Name
     * @return <code>true</code>, wenn ein entsprechender Parameter bekannt ist,
     *         sonst <code>false</code>.
     */
    public static boolean isParameterSupported(String name) {
        initMap();
        return sParams.containsKey(name);
    }

    /**
     * Liefert einen bestimmten Parameter.
     * @param id Die ID des Parameters
     * @return Der Parameter mit der gegebenen ID oder <code>null</code> falls
     *         dieser nicht existiert
     */
    public static AtlParameter getParameter(String id) {
        AtlParameter parameter = null;

        parameter = (AtlParameter) new DatabaseAccess()
                .get(AtlParameter.class, id);

        return parameter;
    }

    /*
     * Liefert alle Parameter, die für Klärschlamm-Probenahmen relevant sind.
     * D.h. alle, deren Klärschlamm-Grenzwert nicht <code>NULL</code> ist.
     * @return Ein Array mit allen für Klärschlamm-Probenahmen relevanten Parametern
     * @throws HibernateException
     */
    /*public static List getKlaerschlammParameter() throws HibernateException {
        Session session = HibernateSessionFactory.currentSession();
        List parameter = session.find(
                "from AtlParameter as param where " +
                "param.klaerschlammGw is not null " +
                "order by param.bezeichnung");
        HibernateSessionFactory.closeSession();

        //AtlParameter[] tmp = new AtlParameter[]{};
        //tmp = (AtlParameter[]) parameter.toArray(tmp);

        return parameter;
    }*/

    /*
     * Liefert alle Parameter, die für SielhautBearbeiten-Probenahmen relevant
     * sind. D.h. alle, deren SielhautBearbeiten-Grenzwert nicht
     * <code>NULL</code> ist.
     * @return Ein Array mit allen für SielhautBearbeiten-Probenahmen relevanten
     * Parametern
     */
    public static List<?> getParameter() {
        List<?> parameter = null;

        parameter = new DatabaseAccess().createQuery(
                "from AtlParameter as param "
                + "where param.atlParameterGruppe.id = 1"
                + "or param.atlParameterGruppe.id = 2"
                + "or param.atlParameterGruppe.id = 3"
                + "order by param.reihenfolge")
                .list();

        return parameter;
    }

    public static List<?> getAll() {
        List<?> parameter = null;

        parameter = new DatabaseAccess().createQuery(
                "from AtlParameter as param order by param.bezeichnung")
                .list();

        return parameter;
    }

    /**
     * Liefert alle Parameter, die für Probenahmen relevant sind. D.h. alle,
     * deren SielhautBearbeiten- oder Klärschlamm-Grenzwert nicht
     * <code>NULL</code> ist.
     * @return Ein Array mit allen für Probenahmen relevanten Parametern
     */
    public static AtlParameter[] getRelevanteParameter() {
        List<?> list = null;
        AtlParameter[] result = null;

        list = new DatabaseAccess().createQuery(
                "from AtlParameter as param "
                + "where param.atlParameterGruppe.id = 1"
                + "or param.atlParameterGruppe.id = 2"
                + "or param.atlParameterGruppe.id = 3"
                + "order by param.reihenfolge")
                .list();

        result = new AtlParameter[list.size()];
        result = (AtlParameter[]) list.toArray(result);

        return result;
    }

    public static AtlParameter[] getParameterGroup(int id) {
        List<?> list = null;
        AtlParameter[] result = null;

        list = new DatabaseAccess().createQuery(
                "from AtlParameter as param where "
                + "param.atlParameterGruppe.id = " + id)
                .list();

        result = new AtlParameter[list.size()];
        result = (AtlParameter[]) list.toArray(result);

        return result;
    }
}
