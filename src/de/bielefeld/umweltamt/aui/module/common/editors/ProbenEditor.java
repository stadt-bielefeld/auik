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
 * Datei:
 * $Id: ProbenEditor.java,v 1.2.2.2 2010-11-23 14:25:23 u633d Exp $
 *
 * Erstellt am 25.5.05 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2.2.1  2010/11/23 10:25:57  u633d
 * start auik_pg branch
 *
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.11  2005/09/14 11:25:36  u633d
 * - Version vom 14.9.
 *
 * Revision 1.10  2005/09/07 10:48:22  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.9  2005/07/06 09:40:13  u633z
 * - Grenzwerte auch für SielhautBearbeiten-Proben
 *
 * Revision 1.8  2005/06/30 11:46:33  u633z
 * *** empty log message ***
 *
 * Revision 1.7  2005/06/14 13:33:39  u633z
 * - Layout korrigiert
 *
 * Revision 1.6  2005/06/10 11:07:21  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.5  2005/06/09 13:45:33  u633z
 * - ProbenEditor auf AbstractBaseEditor umgestellt
 *
 */
package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Gebuehren;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Parametergruppen;
import de.bielefeld.umweltamt.aui.mappings.atl.Probeart;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.atl.Status;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaAnalysemethode;
import de.bielefeld.umweltamt.aui.module.GebuehrenEditor;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.CurrencyDouble;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.DoubleRenderer;
import de.bielefeld.umweltamt.aui.utils.FormHelper;
import de.bielefeld.umweltamt.aui.utils.JRMapDataSource;
import de.bielefeld.umweltamt.aui.utils.KommaDouble;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.PDFExporter;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;
import de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Ein Dialog um eine Probenahme mit ihren Analysepositionen zu bearbeiten.
 * (Momentan ist vieles noch auf Kärschlamm-Proben ausgerichtet)
 * @author David Klotz
 */
public class ProbenEditor extends AbstractApplyEditor {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    private static final long serialVersionUID = 5903518104076020136L;

    public interface OKListener {
        public void onOK(Parameter[] params);
    }

    /**
     * Dieser Wert gibt an, wieviele Stellen das Feld in der Datei kasse.txt
     * besitzen muss, welches das Rechnungsdatum und den Rechnungsbetrag
     * enth&auml;lt.
     **/
    public static final int ZIFFERN_RECHNUNGS_FELD = 19;

    /**
     * Der Dateiname <i>(kasse.txt)</i> der Kassendatei eines
     * Geb&uuml;hrenbescheides.
     **/
    public static final String KASSE_FILENAME = "kasse.txt";

    private double personalkosten = -1;

    private class ParameterModel
        extends EditableListTableModel<Analyseposition> {

        private static final long serialVersionUID = 6042681141925302970L;
        private Probenahme probe;
        private boolean isNew;
        private boolean isSchlamm;
        private boolean isSielhaut;

        public ParameterModel(Probenahme probe, boolean isNew,
            boolean isSchlamm, boolean isSielhaut) {
            super(new String[] {"Parameter", "GrKl", "Messwert", "Einheit", "Analysemethode",
                    "Analyse von", "Grenzwert", "% Normwert"}, false, true);
            this.probe = probe;
            this.isNew = isNew;
            this.isSchlamm = isSchlamm;
            this.isSielhaut = isSielhaut;

            updateList();
        }

        @Override
        public void updateList() {
            // Set positionen;
            if (!isNew) {
                probe = Probenahme.findById(this.probe.getId());
                setList(DatabaseQuery.getSortedAnalysepositionen(this.probe));
            } else { // isNew
                if (isSchlamm) {
                    String[] paramIDs = {
                        DatabaseConstants.ATL_PARAMETER_ID_AOX,
                        DatabaseConstants.ATL_PARAMETER_ID_BLEI,
                        DatabaseConstants.ATL_PARAMETER_ID_CADMIUM,
                        DatabaseConstants.ATL_PARAMETER_ID_CHROM,
                        DatabaseConstants.ATL_PARAMETER_ID_KUPFER,
                        DatabaseConstants.ATL_PARAMETER_ID_NICKEL,
                        DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER,
                        DatabaseConstants.ATL_PARAMETER_ID_ZINK,
                        DatabaseConstants.ATL_PARAMETER_ID_SCHWEFEL
                    };
                    Parameter[] params = new Parameter[paramIDs.length];
                    for (int i = 0; i < paramIDs.length; i++) {
                        params[i] = Parameter.findById(paramIDs[i]);
                    }
                    String analyse_von = "OWL-Umwelt";
                    setList(new ArrayList<Analyseposition>());
                    for (Parameter param : params) {
                        addParameter(param,
                            DatabaseConstants.ATL_EINHEIT_MG_KG,
                            analyse_von);
                    }
                } else if (probe.getKennummer().startsWith("7")) {
                    Parameter[] params = {
                        Parameter.findById(
                            DatabaseConstants.ATL_PARAMETER_ID_PH_WERT),
                        Parameter.findById(
                            DatabaseConstants.ATL_PARAMETER_ID_TEMPERATUR)
                    };
                    String analyse_von = "Betriebslabor";
                    setList(new ArrayList<Analyseposition>());
                    for (Parameter param : params) {
                        addParameter(param,
                            param.getEinheiten(),
                            analyse_von);
                    }
                } else if (!isSielhaut) {
                    Parameter[] params = {
                        Parameter.findById(
                            DatabaseConstants.ATL_PARAMETER_ID_TEMPERATUR),
                        Parameter.findById(
                            DatabaseConstants.ATL_PARAMETER_ID_PH_WERT),
                        Parameter.findById(
                            DatabaseConstants.ATL_PARAMETER_ID_LEITFAEHIGKEIT)
                    };
                    String analyse_von = "360.33";
                    setList(new ArrayList<Analyseposition>());
                    for (Parameter param : params) {
                        addParameter(param,
                            param.getEinheiten(),
                            analyse_von);
                    }
                }
            }
            fireTableDataChanged();
        }

        @Override
        public Object getColumnValue(Analyseposition pos, int columnIndex) {
            Object value;
            switch (columnIndex) {
                // Parameter
                case 0:
                    value = pos.getParameter();
                    break;
                // GrKl
                case 1:
                    value = pos.getGrkl();
                    break;
                // Wert
                case 2:
                    value = new KommaDouble(pos.getWert());
                    break;
                // Einheit
                case 3:
                    value = pos.getEinheiten();
                    break;
                // Methode
                case 4:
                    value = pos.getMapElkaAnalysemethode();
                    break;
                // Analyse von
                case 5:
                    value = pos.getAnalyseVon();
                    break;
                // Grenzwert
                case 6:
                    value = null;
                    Double grenzWert = null;
                    if (pos.getParameter() != null) {
                        if (DatabaseQuery.isKlaerschlammProbe(this.probe)) {
                            grenzWert = pos.getParameter().getKlaerschlammGw();
                        } else if (probe.getMessstelle().getProbeart()
                            .getId().equals(
                            DatabaseConstants.ATL_PROBEART_ID_SIELHAUT)) {
                            grenzWert = pos.getParameter().getSielhautGw();
                        }
                    }

                    if (grenzWert != null
                        && !grenzWert.equals(Double.valueOf(0.0))
                    ) {
                        value = grenzWert;
                    }
                    break;
                // % Normwert
                case 7:
                    value = "-";
                    if (pos.getEinheiten().getId().equals(
                        DatabaseConstants.ATL_EINHEIT_MG_KG.getId())) {
                        double tmpVal = -1;
                        if (DatabaseQuery.isKlaerschlammProbe(this.probe)) {
                            if (pos.getParameter().getKlaerschlammGw() != null) {
                                if (!pos.getParameter().getKlaerschlammGw()
                                    .equals(Double.valueOf(0.0))) {
                                    tmpVal = pos.getWert().doubleValue()
                                        / pos.getParameter()
                                            .getKlaerschlammGw().doubleValue();
                                }
                            }
                        } else if (probe.getMessstelle().getProbeart().getId().equals(
                            DatabaseConstants.ATL_PROBEART_ID_SIELHAUT)) {
                            if (pos.getParameter().getSielhautGw() != null) {
                                if (!pos.getParameter().getSielhautGw()
                                    .equals(Double.valueOf(0.0))) {
                                    tmpVal = pos.getWert().doubleValue()
                                        / pos.getParameter().getSielhautGw()
                                            .doubleValue();
                                }
                            }
                        }
                        if (tmpVal != -1) {
                            NumberFormat percentFormat = NumberFormat
                                .getPercentInstance();
                            percentFormat.setMinimumFractionDigits(1);
                            if (tmpVal > 1.0) {
                                value = "<html><font color=#ff0000>"
                                    + percentFormat.format(tmpVal)
                                    + "</font></html>";
                            } else {
                                value = percentFormat.format(tmpVal);
                            }
                        }
                    }
                    break;
                default:
                    value = null;
            }

            return value;
        }

        /**
         * Keine Leerzeile am Ende: Parameter werden nur noch über
         * "Parameter zusammenstellen" hinzugefügt.
         */
        @Override
        public int getRowCount() {
            return getList() == null ? 0 : getList().size();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            // Parameter (0) nur über den Dialog, Grenzwert/Normwert (6, 7)
            // werden berechnet
            if (columnIndex == 0 || columnIndex == 6 || columnIndex == 7) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public void editObject(
            Analyseposition tmp, int columnIndex, Object newValue
        ) {
            switch (columnIndex) {
                case 0:
                    Parameter tmpPara = (Parameter) newValue;
                    tmp.setParameter(tmpPara);
                    break;

                case 1:
                    String tmpGrKl = (String) newValue;
                    if (tmpGrKl != null) {
                        tmpGrKl = tmpGrKl.trim();
                        if (tmpGrKl.equals("")) {
                            tmp.setGrkl(null);
                        } else {
                            tmpGrKl = tmpGrKl.substring(0, 1);
                            if (tmpGrKl.equals("<") || tmpGrKl.equals(">")) {
                                tmp.setGrkl(tmpGrKl);
                            }
                        }
                    }
                    break;

                case 2:
                    Float tmpWert = null;
                    if (newValue instanceof Float) {
                        tmpWert = (Float) newValue;
                    } else if (newValue instanceof KommaDouble) {
                        tmpWert = ((KommaDouble) newValue).getValue()
                            .floatValue();
                    }
                    tmp.setWert(tmpWert);
                    break;

                case 3:
                    Einheiten tmpEinheit = (Einheiten) newValue;
                    tmp.setEinheiten(tmpEinheit);
                    break;

                case 4:
                	MapElkaAnalysemethode tmpMethode = (MapElkaAnalysemethode) newValue;
                    tmp.setMapElkaAnalysemethode(tmpMethode);
                    break;

                case 5:
                    String tmpAnalyse = (String) newValue;
                    if (tmpAnalyse != null) {
                        tmpAnalyse = tmpAnalyse.trim();
                        if (tmpAnalyse.equals("")) {
                            tmp.setAnalyseVon(null);
                        } else {
                            tmp.setAnalyseVon(tmpAnalyse);
                        }
                    }
                    break;

                default:
                    break;
            }

            log.debug("EDIT: " + tmp);
        }

        @Override
        public Analyseposition newObject() {
            Analyseposition tmp = new Analyseposition();
            tmp.setProbenahme(this.probe);
            if (DatabaseQuery.isKlaerschlammProbe(this.probe)
                || probe.getMessstelle().getProbeart().getId().equals(
                    DatabaseConstants.ATL_PROBEART_ID_SIELHAUT)) {
                tmp.setEinheiten(DatabaseConstants.ATL_EINHEIT_MG_KG);
            } else {
                tmp.setEinheiten(DatabaseConstants.ATL_EINHEIT_MG_L);
            }
            tmp.setParameter(null);
            tmp.setWert(0f);
			if (tmp.getParameter() != null) {
				tmp.setEinheiten(tmp.getParameter().getEinheiten());
			}
            // tmp.setAnalyseVon("");
            return tmp;
        }

        /**
         * Diese Methode fügt dem Model einen neuen Parameter hinzu. Falls
         * dieser jedoch schon enthalten ist, wird er verworfen - es kommen also
         * keine doppelten Parameter vor.
         * @param parameter Ein neuer Parameter.
         */
        public void addParameter(Parameter parameter) {
            if (isParameterAlreadyThere(parameter)) {
                log.debug("Der Parameter wird bereits geprüft.");
                return;
            }

            Analyseposition pos = new Analyseposition();

            pos.setProbenahme(this.probe);
            pos.setParameter(parameter);
            pos.setWert(0f);
            pos.setEinheiten(parameter
                .getEinheiten());
            pos.setMapElkaAnalysemethode(MapElkaAnalysemethode.findById(1));

            List<Analyseposition> list = (List<Analyseposition>) getList();
            list.add(pos);

            fireTableDataChanged();
        }

        /**
         * Diese Methode fügt dem Model einen neuen Parameter hinzu. Falls
         * dieser jedoch schon enthalten ist, wird er verworfen - es kommen also
         * keine doppelten Parameter vor.
         * @param parameter Ein neuer Parameter.
         */
        public void addParameter(Parameter parameter, Einheiten einheit,
            String analysevon) {
            if (isParameterAlreadyThere(parameter)) {
                log.debug("Der Parameter wird bereits geprüft.");
                return;
            }

            Analyseposition pos = new Analyseposition();

            pos.setProbenahme(this.probe);
            pos.setParameter(parameter);
            pos.setWert(0f);
            pos.setEinheiten(einheit);
            pos.setAnalyseVon(analysevon);
            pos.setMapElkaAnalysemethode(MapElkaAnalysemethode.findById(1));

//          getList().add(pos);
            List<Analyseposition> list = (List<Analyseposition>) getList();
            list.add(pos);

            fireTableDataChanged();
        }

        /**
         * Übernimmt den Parameter einer Analyseposition aus einer anderen
         * Probenahme (mit Einheit, Analysemethode und "Analyse von", aber
         * ohne Messwert). Bereits vorhandene Parameter werden übersprungen.
         * @param vorlage Analyseposition der früheren Probenahme
         * @return true, wenn der Parameter neu hinzugefügt wurde
         */
        public boolean uebernehmeParameter(Analyseposition vorlage) {
            Parameter parameter = vorlage.getParameter();
            if (parameter == null || isParameterAlreadyThere(parameter)) {
                return false;
            }

            Analyseposition pos = new Analyseposition();
            pos.setProbenahme(this.probe);
            pos.setParameter(parameter);
            pos.setWert(0f);
            pos.setEinheiten(vorlage.getEinheiten() != null
                ? vorlage.getEinheiten() : parameter.getEinheiten());
            pos.setAnalyseVon(vorlage.getAnalyseVon());
            pos.setMapElkaAnalysemethode(
                vorlage.getMapElkaAnalysemethode() != null
                    ? vorlage.getMapElkaAnalysemethode()
                    : MapElkaAnalysemethode.findById(1));

            List<Analyseposition> list = (List<Analyseposition>) getList();
            list.add(pos);
            return true;
        }

        /**
         * Diese Methode prüft, ob ein Parameter bereits zur Prüfung vorgemerkt
         * ist - also bereits im ParameterModel enthalten ist.
         * @param newParam Ein neu zu probender Parameter.
         * @return true, wenn der Parameter bereits im Model enthalten ist,
         *         andernfalls false.
         */
        public boolean isParameterAlreadyThere(Parameter newParam) {
            List<?> data = getList();
            int size = data.size();

            String newOrdnungsbegriff = newParam.getOrdnungsbegriff();

            for (int i = 0; i < size; i++) {
                Analyseposition pos = (Analyseposition) data.get(i);
                Parameter param = pos.getParameter();

                if (param.getOrdnungsbegriff().equals(newOrdnungsbegriff)) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public boolean objectRemoved(Analyseposition tmp) {
            tmp.setMapElkaAnalysemethode(MapElkaAnalysemethode.findById(1));

            if(tmp.getId() == null) {
            	probe.getAnalysepositions().remove(tmp);
            	}
            else {
                tmp.delete();
            }

            return true;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class<?> tmp;
            switch (columnIndex) {
                case 0:
                    tmp = Parameter.class;
                    break;

                case 2:
                    tmp = KommaDouble.class;
                    break;

                case 3:
                    tmp = Einheiten.class;
                    break;

                case 4:
                	tmp = MapElkaAnalysemethode.class;
                    break;

                case 5:
                    tmp = Double.class;
                    break;

                case 6:
                    tmp = String.class;
                    break;

                default:
                    tmp = super.getColumnClass(columnIndex);
                    break;
            }
            return tmp;
        }
    }

    private Dimension minimumSize;

    private JComboBox<Status> vorgangsstatusBox;
    private JButton statusHoch;
    private TextFieldDateChooser datum;
    private JFormattedTextField uhrzeitVon;
    private JFormattedTextField uhrzeitBis;
    private JFormattedTextField fahrtzeit;
    private JTextField bezug;
    private JTextField beteiligte;
    private JTextField probenummer;
    private JLabel rechnungsDatum;
    private JLabel rechnungsBetrag;
    private JTextArea bemerkungsArea;

    private JComboBox<Sachbearbeiter> sachbearbeiterBox;
    private JButton bescheidDrucken;
    private JButton auftragDrucken;
    private JFileChooser dateiChooser;
    private JLabel betrieb;
    private JLabel entnahmepunkt;
    private DoubleField icpEinwaageFeld;
    private TextFieldDateChooser icpDatum;
    private JTable parameterTabelle;

    private JComboBox<Einheiten> einheitenBox;
    private JComboBox<String> analysevonBox;
    private JComboBox<MapElkaAnalysemethode> methodeBox;

    private ParameterModel parameterModel;
    private boolean isNew;
    private boolean isSchlamm;
    private boolean isSielhaut;

    public ProbenEditor(Probenahme probe, HauptFrame owner, boolean isNew) {
        super("Probenahme " + probe.getKennummer(), probe, owner);
        this.isNew = isNew;
        isSchlamm = false;
        isSielhaut = false;

        if (DatabaseQuery.isKlaerschlammProbe(probe)) {
            isSchlamm = true;
        }

        if (probe.getMessstelle().getProbeart().getId().equals(
            DatabaseConstants.ATL_PROBEART_ID_SIELHAUT)) {
            isSielhaut = true;
        }

        if (!isNew /*probe.isAnalysepositionenInitialized()*/) {
            // TODO: Here we want to get rid of getProbenahmeAndInit
            setEditedObject(Probenahme.findById(probe.getId()));
        }

        if (isNew) {
            sachbearbeiterBox.setSelectedItem(
                DatabaseQuery.getCurrentSachbearbeiter());
            uhrzeitVon.setText("");
            uhrzeitBis.setText("");
        }

        parameterModel = new ParameterModel(getProbe(), isNew,
            isSchlamm, isSielhaut);
        parameterTabelle.setModel(parameterModel);

        initColumns();
    }

    /**
     * Diese Methode erstellt das {@link javax..swing.JComponent} mit
     * {@link javax.swing.JButton}s. Hier werden f&uuml;nf Kn&ouml;pfe
     * hinzugef&uuml;gt, die das Erstellt des Probenahmeauftrags und
     * Geb&uuml;hrenbescheids starten, sowie die Kn&ouml;pfe zum Speichern,
     * Abbrechen und zur Parameterauswahl.
     * @return ein {@link javax.swing.JComponent} mit {@link javax.swing.JButton}s.
     */
    @Override
    protected JComponent createButtonBar() {
        this.bescheidDrucken = new JButton("Bescheid erzeugen");
        this.auftragDrucken = new JButton("Auftrag erzeugen");

        // triggert das Erzeugen eines PDFs und einen Druck-Job an
        this.auftragDrucken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSave();

                Probenahme probe = getProbe();

                Map<String, Object> params = getAuftragDruckMap(probe);

                String basePath = SettingsManager.getInstance().getSetting(
                    "auik.probenahme.auftraege");

                String filename = probe.getKennummer();
                filename = filename.replace(" ", "");
                if (!filename.endsWith(".pdf")) {
                    filename += ".pdf";
                }

                filename = filename.replace("/", "_");

                File path = new File(basePath, filename);

                params.put("localFile", path.getAbsolutePath());

                try {
                    JRDataSource subdata = DatabaseQuery
                        .getAuftragDataSource(probe);

                    log.debug("Fülle Probenahmeauftrag mit "
                        + ((JRMapDataSource) subdata).size() + " Zeilen.");

                    PDFExporter.getInstance().exportAuftrag(params, subdata,
                        path.getAbsolutePath(), true);

                    updateVorgangsstatus(
                        DatabaseConstants.ATL_STATUS_PROBENAHMEAUFTRAG_GEDRUCKT);
                    probe.setStatus(
                        DatabaseConstants.ATL_STATUS_PROBENAHMEAUFTRAG_GEDRUCKT);

                    probe.merge();

                    GUIManager.getInstance().showInfoMessage(
                        "Der Probenahmeauftrag wurde erfolgreich unter '"
                            + path.getAbsolutePath() + "' gespeichert.",
                        "Probenahmeauftrag erfolgreich");

                    close();

                } catch (Exception ex) {
                    log.error("Druck schlug fehlt: " + ex.getMessage());
                    GUIManager.getInstance().showErrorMessage(
                        "Der Druck des Probenahmeauftrags ist fehlgeschlagen."
                            + "\n" + ex.getLocalizedMessage(),
                        "Probenahmeauftrag-Druck fehlgeschlagen");
                }
            }
        });

        // trigger das Erzeugen eines PDFs und einen Druck-Job an
        this.bescheidDrucken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Probenahme probe = getProbe();

                String basePath = SettingsManager.getInstance().getSetting(
                    "auik.probenahme.bescheide");

                String filename = probe.getKennummer();
                String vFilename = filename;

                filename = filename.replace(" ", "");
                if (!filename.endsWith(".pdf")) {
                    filename += ".pdf";
                    vFilename += "-vfg.pdf";
                } else {
                    vFilename = vFilename.substring(0, vFilename.length() - 5);
                    vFilename += "-vfg.pdf";
                }
                filename = filename.replace("/", "_");
                vFilename = vFilename.replace("/", "_");

                File path = new File(basePath, filename);
                // TODO: Check this: (path == null) never happens
//                if (path == null) {
//                    frame.showErrorMessage(
//                        "Kann die Datei nicht speichern, da der Pfad nicht " +
//                        "korrekt ist.",
//                        "Pfad zum Speichern fehlt");
//                    return;
//                }

                File vPath = new File(basePath, vFilename);

                try {
                    updateRechnungsdatum(probe);

                    doSave();

                    updateRechnungsbetrag(probe);

                    Map<String, Object> params = getBescheidDruckMap(probe);

                    JRDataSource subdata = DatabaseQuery
                        .getBescheidDataSource(probe);

                    PDFExporter.getInstance().exportBescheid(params, subdata,
                        PDFExporter.BESCHEID, path.getAbsolutePath());

                    JRDataSource vSubdata = DatabaseQuery
                        .getBescheidDataSource(probe);

                    PDFExporter.getInstance().exportBescheid(params, vSubdata,
                        PDFExporter.VFG, vPath.getAbsolutePath());

                    if (!(getVorgangsstatus().equals(
                        DatabaseConstants.ATL_STATUS_BESCHEID_GEDRUCKT))) {
                        // Diese Datei soll nur 1x erzeugt werden. Nachdem der
                        // Status auf "Bescheid gedruckt" erhöht wurde, kann
                        // zwar der Bescheid nochmals gedruckt werden, jedoch
                        // wird keine neue kasse.txt angelegt.
                        log.debug("Erstelle kasse.txt für Gebührenbescheid.");
                        try {
                            createKasseFile(path.getAbsolutePath());
                        } catch (ParseException pe) {
                            pe.printStackTrace();
                            GUIManager
                                .getInstance()
                                .showErrorMessage(
                                    "Der Druck des Gebührenbescheids ist fehlgeschlagen."
                                        + "Die Datei kasse.txt konnte nicht erstellt werden.",
                                    "Gebührenbescheid-Druck fehlgeschlagen");

                            return;
                        }
                    }

                    updateVorgangsstatus(
                        DatabaseConstants.ATL_STATUS_BESCHEID_GEDRUCKT);

                    probe.setStatus(
                        DatabaseConstants.ATL_STATUS_BESCHEID_GEDRUCKT);

                    probe.merge();

                    GUIManager.getInstance().showInfoMessage(
                        "Der Gebührenbescheid wurde erfolgreich unter ' "
                            + path.getAbsolutePath() + "' gespeichert.",
                        "Gebührenbescheid erfolgreich");
                } catch (Exception ex) {
                    log.error("Druck schlug fehlt.");

                    ex.printStackTrace();

                    GUIManager.getInstance().showErrorMessage(
                        "Der Druck des Gebührenbescheids ist fehlgeschlagen."
                            + "\n" + ex.getLocalizedMessage(),
                        "Gebührenbescheid-Druck fehlgeschlagen");
                }
            }
        });

        return ComponentFactory.buildRightAlignedBar(
                this.button1, this.button2, this.button3, this.auftragDrucken,
                this.bescheidDrucken);
    }

    @Override
    protected JComponent buildContentArea() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");

        entnahmepunkt = new JLabel();
        datum = new TextFieldDateChooser();
        rechnungsDatum = new JLabel();
        uhrzeitVon = new JFormattedTextField(f);
        uhrzeitBis = new JFormattedTextField(f);
        fahrtzeit = new JFormattedTextField(f);
        rechnungsBetrag = new JLabel();
        bezug = new JTextField();
        beteiligte = new JTextField();
        probenummer = new JTextField();
        vorgangsstatusBox = new JComboBox<>();
        statusHoch = new JButton("erhöhen");
        sachbearbeiterBox = new JComboBox<>();
        icpEinwaageFeld = new DoubleField(0);
        icpDatum = new TextFieldDateChooser();
        bemerkungsArea = new LimitedTextArea(255);
        betrieb = new JLabel();
        parameterTabelle = new JTable();

        vorgangsstatusBox.setModel(
            new DefaultComboBoxModel<>(DatabaseQuery.getStatus()));

        sachbearbeiterBox.setModel(new DefaultComboBoxModel<>(
            DatabaseQuery.getEnabledSachbearbeiter()));
        sachbearbeiterBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        sachbearbeiterBox.setEditable(true);

        statusHoch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateVorgangsstatus(
                    DatabaseQuery.getNextStatus(getVorgangsstatus()));
            }
        });

        bemerkungsArea.setLineWrap(true);
        bemerkungsArea.setWrapStyleWord(true);

        JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bemerkungsScroller.setPreferredSize(new Dimension(0, 50));

        parameterTabelle.setRowHeight(25);

        Action aposRemoveAction = new AbstractAction("Analyseposition löschen") {
            private static final long serialVersionUID = -5755536713201543469L;

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = ProbenEditor.this.parameterTabelle.getSelectedRow();
                if (row != -1
                    && ProbenEditor.this.parameterTabelle.getEditingRow() == -1) {
                    ProbenEditor.this.parameterModel.removeRow(row);
                }
            }
        };

        KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
            0, false);

        aposRemoveAction.putValue(Action.ACCELERATOR_KEY, deleteKeyStroke);

        parameterTabelle.getInputMap().put(deleteKeyStroke,
            aposRemoveAction.getValue(Action.NAME));

        parameterTabelle.getActionMap().put(
            aposRemoveAction.getValue(Action.NAME), aposRemoveAction);

        JScrollPane parameterScroller = new JScrollPane(this.parameterTabelle,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Spalten (gridx): 0 Label | 1 Feld | 2 | 3 von | 4 Feld | 5 bis |
        // 6 Feld | 7 Fahrtzeit | 8 Feld | 9 Bemerkung
        JPanel panel = FormHelper.createFormPanel();

        // Feste Breiten wie im alten Layout
        FormHelper.fixWidth(probenummer, 240);
        FormHelper.fixWidth(vorgangsstatusBox, 240);
        FormHelper.fixWidth(sachbearbeiterBox, 240);
        FormHelper.fixWidth(betrieb, 420);
        FormHelper.fixWidth(entnahmepunkt, 420);
        FormHelper.fixWidth(datum, 105);
        FormHelper.fixWidth(uhrzeitVon, 55);
        FormHelper.fixWidth(uhrzeitBis, 55);
        FormHelper.fixWidth(fahrtzeit, 90);
        FormHelper.fixWidth(bezug, 240);
        FormHelper.fixWidth(beteiligte, 55);
        FormHelper.fixWidth(rechnungsDatum, 105);
        FormHelper.fixWidth(rechnungsBetrag, 90);

        int row = 0;
        FormHelper.addSeparator(panel, 0, row, 9, "Probe");
        FormHelper.addSeparator(panel, 9, row, 1, "Bemerkung");

        row++;
        FormHelper.addLabel(panel, 0, row, "Probenummer");
        FormHelper.addField(panel, 1, row, 3, probenummer);
        bemerkungsScroller.setPreferredSize(new Dimension(400, 90));
        FormHelper.addSpan(panel, bemerkungsScroller, 9, row, 1, 3);

        row++;
        FormHelper.addLabel(panel, 0, row, "Vorgangsstatus");
        FormHelper.addField(panel, 1, row, 3, vorgangsstatusBox);
        FormHelper.add(panel, statusHoch, 4, row, 2);

        row++;
        FormHelper.addLabel(panel, 0, row, "Sachbearbeiter:in");
        FormHelper.addField(panel, 1, row, 3, sachbearbeiterBox);

        row++;
        FormHelper.addLabel(panel, 0, row, "Name des Betriebs");
        FormHelper.addField(panel, 1, row, 6, betrieb);

        row++;
        FormHelper.addLabel(panel, 0, row, "Entnahmepunkt");
        FormHelper.addField(panel, 1, row, 6, entnahmepunkt);

        row++;
        FormHelper.addLabel(panel, 0, row, "Datum");
        JPanel zeitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        zeitPanel.add(datum);
        zeitPanel.add(Box.createHorizontalStrut(10));
        zeitPanel.add(new JLabel("von"));
        zeitPanel.add(Box.createHorizontalStrut(5));
        zeitPanel.add(uhrzeitVon);
        zeitPanel.add(Box.createHorizontalStrut(10));
        zeitPanel.add(new JLabel("bis"));
        zeitPanel.add(Box.createHorizontalStrut(5));
        zeitPanel.add(uhrzeitBis);
        zeitPanel.add(Box.createHorizontalStrut(15));
        zeitPanel.add(new JLabel("Fahrtzeit"));
        zeitPanel.add(Box.createHorizontalStrut(5));
        zeitPanel.add(fahrtzeit);
        FormHelper.add(panel, zeitPanel, 1, row, 8);

        row++;
        FormHelper.addLabel(panel, 0, row, "Bezug");
        FormHelper.addField(panel, 1, row, 3, bezug);
        FormHelper.addLabel(panel, 4, row, "Beteiligte");
        FormHelper.addField(panel, 5, row, 1, beteiligte);

        row++;
        FormHelper.addLabel(panel, 0, row, "Rechnungsdatum");
        FormHelper.addField(panel, 1, row, 1, rechnungsDatum);
        FormHelper.add(panel, new JLabel("Rechnungsbetrag"), 2, row, 3,
            GridBagConstraints.NONE, GridBagConstraints.EAST);
        FormHelper.addField(panel, 5, row, 1, rechnungsBetrag);

        row++;
        FormHelper.addSeparator(panel, 0, row, GridBagConstraints.REMAINDER,
            "Parameter");

        row++;
        JButton zusammenstellenButton = new JButton(
            "Parameter zusammenstellen…");
        zusammenstellenButton.setToolTipText("Parameter aus Suche, "
            + "Parametergruppen oder früheren Probenahmen hinzufügen");
        zusammenstellenButton.addActionListener(
            e -> parameterZusammenstellen());
        FormHelper.add(panel, zusammenstellenButton, 0, row,
            GridBagConstraints.REMAINDER);

        row++;
        GridBagConstraints gbc = FormHelper.constraints(0, row,
            GridBagConstraints.REMAINDER);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        parameterScroller.setPreferredSize(new Dimension(800, 300));
        panel.add(parameterScroller, gbc);

        panel.setFocusCycleRoot(true);

        return panel;
    }

    /**
     * Diese Methode &ouml;ffnet einen Dateidialog. Falls eine Datei ausgew
     */
    protected void openFileChooser(JTextField datei) {
        this.dateiChooser.showSaveDialog(this);

        File file = this.dateiChooser.getSelectedFile();

        if (file != null) {
            log.debug("Speichere Datei unter: " + file.getAbsolutePath());

            datei.setText(file.getAbsolutePath());
        }
    }

    /**
     * Diese Methode erzeugt neben dem PDF des Geb&uuml;hrenbescheids eine Datei
     * namens kasse.txt, die Informationen &uuml;ber den Betreiber sowie die
     * H&ouml;he des Rechnungsbetrags und des Rechnungsdatums enth&auml;lt. Eine
     * Zeile in dieser Datei entspricht einem Bescheid und hat folgendes Format:
     * Feldname Länge Kassenzeichen 12 ohne Punkte Ansprechpartner Name 28
     * Ansprechpartner Vorname 28 Ansprechpartner Straße, Hausnummer 28 PLZ 05
     * Ort 23 Kommentar 28 Fälligkeitsdatum 08 Betrag 11 ohne Tausender oder
     * Cent Trennzeichen mit führenden Nullen
     * @param bescheid Der Pfad, an dem das PDF gespeichert wurde.
     */
    protected void createKasseFile(String bescheid) throws ParseException {
        File path = new File(bescheid).getParentFile();
        File kasse = new File(path, KASSE_FILENAME);

        Probenahme probe = getProbe();

        Inhaber basisBetr =
            probe.getMessstelle().getObjekt().getBetreiberid();

        Date rechnungsdatum = DateUtils.getDateOfBill(probe.getBescheid());
        Integer cd = (int) getRechnungsbetrag(probe);


        String kassenzeichen = basisBetr.getKassenzeichen().toString();
        kassenzeichen = kassenzeichen.replace(".", "");

        String rechnungsbetrag = String.valueOf(cd);
        rechnungsbetrag = rechnungsbetrag.replace(".", "");

        String kasseDatum = DateUtils.format(rechnungsdatum,
            DateUtils.FORMAT_KASSE);

        StringBuilder sb = new StringBuilder();

        int fill = ZIFFERN_RECHNUNGS_FELD;
        fill -= kasseDatum.length();
        fill -= rechnungsbetrag.length();

        sb.append(kassenzeichen);
        if (basisBetr.getName().length() > 28) {
            sb.append(basisBetr.getName().substring(0, 28));
        } else
            sb.append(basisBetr.getName());

        for (int i = 1; i <= 28 - basisBetr.getName().length(); i++) {
            sb.append(" ");
        }

        int anrede = 0;
        if (basisBetr.getAnrede() != null) {
            sb.append(basisBetr.getAnrede());
            anrede = basisBetr.getAnrede().length();
        }

        for (int i = 1; i <= 28 - anrede; i++) {
            sb.append(" ");
        }

        String addr = DatabaseQuery.getBetriebsgrundstueck(basisBetr.getAdresse());
        if (addr.length()>28) {
        	addr = addr.substring(0, 28);
        }
        sb.append(addr);
        for (int i = 1; i <= 28 - addr.length(); i++) {
            sb.append(" ");
        }

        sb.append(basisBetr.getAdresse().getPlz().toString());
        sb.append(basisBetr.getAdresse().getOrt().toString());

        for (int i = 1; i <= 23 - basisBetr.getAdresse().getOrt().length(); i++) {
            sb.append(" ");
        }

        sb.append("Gebühr für Abwasserunters.  ");
        sb.append(kasseDatum);

        for (int i = 1; i <= fill; i++) {
            sb.append("0");
        }

        sb.append(rechnungsbetrag);

        String row = sb.toString();

        BufferedWriter writer = null;
        boolean exists = kasse.exists();

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(kasse, true), "cp1252"));

            if (exists) {
                writer.newLine();
            }

            writer.write(row, 0, row.length());
            writer.flush();
        } catch (IOException ioe) {
            GUIManager.getInstance().showErrorMessage(
                ioe.getLocalizedMessage(), "Fehler");
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ioe) { /* do nothing */
            }
        }
    }

    @Override
    protected void fillForm() {
//        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        this.minimumSize = new Dimension(this.getSize());

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                ProbenEditor.this.setSize(Math.max(
                    ProbenEditor.this.minimumSize.width,
                    ProbenEditor.this.getWidth()), Math.max(
                    ProbenEditor.this.minimumSize.height,
                    ProbenEditor.this.getHeight()));
            }
        });

        Probenahme probe = getProbe();
        Inhaber basisBetr =
            probe.getMessstelle().getObjekt().getBetreiberid();

        probenummer.setText(probe.getKennummer());
        probenummer.setEnabled(true);
        entnahmepunkt.setText(probe.getMessstelle().getObjekt()
            .getBeschreibung());
        Date entnahmeDatum = probe.getDatumDerEntnahme();
        datum.setDate(entnahmeDatum);

        if (probe.getUhrzeitbeginn() != null) {
            uhrzeitVon.setText(probe.getUhrzeitbeginn());
        } else {
            uhrzeitVon.setText("00:00");
        }

        if (probe.getUhrzeitende() != null) {
            uhrzeitBis.setText(probe.getUhrzeitende());
        } else {
            uhrzeitBis.setText("00:00");
        }

        if (probe.getFahrtzeit() != null) {
            fahrtzeit.setText(probe.getFahrtzeit());
        } else {
            fahrtzeit.setText("01:00");
        }

        if (basisBetr != null) {
            String name = basisBetr.getName();
            String addr = DatabaseQuery.getBetriebsgrundstueck(basisBetr.getAdresse());

            StringBuilder sb = new StringBuilder();

            if (name != null) {
                sb.append(name);
            }

            if (addr != null) {
                sb.append(", ");
                sb.append(addr);
            }

            betrieb.setText(sb.toString());
        }

        if (probe.getAnzahlbeteiligte() != null) {
            beteiligte.setText(Integer.toString(probe
                .getAnzahlbeteiligte()));
        } else {
            beteiligte.setText("1");
        }

        Date bescheid = probe.getBescheid();
        if (bescheid != null) {
            rechnungsDatum.setText(DateUtils.format(bescheid,
                DateUtils.FORMAT_DATE));
        }

        // For some reason, there occur a NullPointerException when creating a
        // new AtlProbenahmen.
        try {
            double kosten = getProbe().getKosten();
            CurrencyDouble cd = new CurrencyDouble(kosten, Locale.GERMANY);
            rechnungsBetrag.setText(cd.toString());
        } catch (NullPointerException npe) {
            // do nothing
        }

        // TODO Datei

        fillVorgangsstatus();

        if (DatabaseQuery.isKlaerschlammProbe(getProbe())) {
            icpEinwaageFeld.setValue(getProbe().getEinwaage());
        } else {
            icpEinwaageFeld.setEditable(false);
            icpEinwaageFeld.setEnabled(false);
        }
        Date icpDate = getProbe().getDatumIcp();
        icpDatum.setDate(icpDate);

        bemerkungsArea.setText(getProbe().getBemerkung());
        sachbearbeiterBox.setSelectedItem(probe.getSachbearbeiter());
    }

    /** Initialisiert die Spalten der Analysepositionen-Tabelle */
    private void initColumns() {

        // Messwert... (alle Doubles)
        parameterTabelle.setDefaultRenderer(Double.class,
            new DoubleRenderer());
        parameterTabelle.setDefaultRenderer(KommaDouble.class,
            new DoubleRenderer());

        // Parameter
        TableColumn parameterColumn = this.parameterTabelle.getColumnModel()
            .getColumn(0);
        parameterColumn.setPreferredWidth(150);

        // Parameter werden nur über "Parameter zusammenstellen"
        // hinzugefügt, die Spalte ist deshalb nicht editierbar

        //Werte
        TableColumn gkColumn = this.parameterTabelle.getColumnModel()
                .getColumn(1);
        gkColumn.setMaxWidth(50);

        TableColumn wertColumn = this.parameterTabelle.getColumnModel()
                .getColumn(2);
        wertColumn.setPreferredWidth(60);DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        wertColumn.setCellRenderer(rightRenderer);

        // Einheit
        TableColumn einheitenColumn = this.parameterTabelle.getColumnModel()
            .getColumn(3);
        einheitenColumn.setPreferredWidth(40);

        einheitenBox = new JComboBox<>(DatabaseQuery.getEinheiten());
        einheitenBox.setEditable(false);
        einheitenBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                ProbenEditor.this.einheitenBox.showPopup();
            }
        });
        einheitenBox.setBorder(BorderFactory.createEmptyBorder());

        einheitenColumn.setCellEditor(new DefaultCellEditor(einheitenBox));
        einheitenColumn.setCellRenderer(new ComboBoxRenderer<>());

        // Methode
        TableColumn methodeColumn = this.parameterTabelle.getColumnModel()
            .getColumn(4);
        methodeColumn.setPreferredWidth(200);

        methodeBox = new JComboBox<>(DatabaseQuery.getMapElkaAnalysemethode());
        methodeBox.setEditable(false);
        methodeBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                ProbenEditor.this.methodeBox.showPopup();
            }
        });
        methodeBox.setBorder(BorderFactory.createEmptyBorder());

        methodeColumn.setCellEditor(new DefaultCellEditor(methodeBox));
        methodeColumn.setCellRenderer(new ComboBoxRenderer<>());

        // Analyse von
        TableColumn analyseColumn = this.parameterTabelle.getColumnModel()
            .getColumn(5);
        analyseColumn.setPreferredWidth(100);

        String[] analyse_von_auswahl = {"700.44", "360.33", "OWL-Umwelt", "Stadtwerke GT", "AGROLAB", "HBICON",
                "Dr. Kludas", "Fresenius", "E-Satzung"};

        analysevonBox = new JComboBox<>(analyse_von_auswahl);
        analysevonBox.setEditable(true);
        analysevonBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                ProbenEditor.this.analysevonBox.showPopup();
            }
        });
        analysevonBox.setBorder(BorderFactory.createEmptyBorder());

        analyseColumn.setCellEditor(new DefaultCellEditor(this.analysevonBox));
        analyseColumn.setCellRenderer(new ComboBoxRenderer<>());

        //Grenzwert
        TableColumn gwColumn = this.parameterTabelle.getColumnModel()
                .getColumn(6);
        gwColumn.setPreferredWidth(100);

        // Normwert
        TableColumn normwertColumn = this.parameterTabelle.getColumnModel()
            .getColumn(7);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.TRAILING);
        normwertColumn.setCellRenderer(renderer);
        normwertColumn.setPreferredWidth(100);
    }

    /**
     * Diese Methode setzt den initialen Wert der Combobox des Parameters
     * <i>Vorgangsstatus</i>. Handelt es sich bei der aktuellen Ansicht um eine
     * neue Probenahme, ist der initiale Wert <i>erstellt</i>, ansonsten wird
     * der Status ausgew&auml;hlt, der am {@link AtlProbenahmen} Objekt
     * gespeichert ist.
     */
    protected void fillVorgangsstatus() {
        Probenahme probe = getProbe();

        if (probe == null || probe.getStatus() == null)
            return;

        Status status = probe.getStatus();
        log.debug("Aktueller Status: " + status.getBezeichnung());

        updateVorgangsstatus(status);
    }

    protected Status getVorgangsstatus() {
        return (Status) this.vorgangsstatusBox.getSelectedItem();
    }

    protected Sachbearbeiter getSachbearbeiter() {
        ComboBoxModel<Sachbearbeiter> model
            = this.sachbearbeiterBox.getModel();

        return (Sachbearbeiter) model.getSelectedItem();
    }

    protected void updateVorgangsstatus(Status newStatus) {
        vorgangsstatusBox.setSelectedItem(newStatus);
    }

    @Override
    protected boolean canSave() {
        return true;
    }

    @Override
    public boolean doSave() {
        log.debug("Speichere Probenahmedetails");

        Probenahme probe = getProbe();

        // Vorgangsstatus
        probe.setStatus((Status) this.vorgangsstatusBox.getSelectedItem());

        // Von
        String uhrzeitVonVal = this.uhrzeitVon.getText();
        if (uhrzeitVonVal == null || uhrzeitVonVal.equals("")) {
            uhrzeitVonVal = "00:00";
        }
        probe.setUhrzeitbeginn(uhrzeitVonVal);

        // Bis
        String uhrzeitBisVal = this.uhrzeitBis.getText();
        if (uhrzeitBisVal != null) {
            probe.setUhrzeitende(uhrzeitBisVal);
        }

        // Datum der Entnahme
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String datumVal = df.format(this.datum.getDate());
        String timestring = (String) datumVal.subSequence(0, 11) + " "
            + uhrzeitVonVal;
        Date convertedDate = new Date();
        try {
            convertedDate = df.parse(timestring);
            probe.setDatumDerEntnahme(new Timestamp(convertedDate.getTime()));
        } catch (ParseException e) {
            log.error("Fehler beim Speichern von 'datumDerEntnahme'.");
        }

        // Fahrtzeit
        String fahrtzeitVal = this.fahrtzeit.getText();
        if (fahrtzeitVal != null) {
            probe.setFahrtzeit(fahrtzeitVal);
        }

        // Beteiligte
        try {
            int anzahl = Integer.parseInt(this.beteiligte.getText());
            probe.setAnzahlbeteiligte(anzahl);
        } catch (NumberFormatException nfe) {
            log.error("Fehler beim Speichern von 'beteiligte'.");
        }

        // Sachbearbeiter
        Sachbearbeiter b = (Sachbearbeiter) this.sachbearbeiterBox
            .getSelectedItem();
        if (b != null) {
            probe.setSachbearbeiter(b);
        }

        // Kennnummer
        String kennnummer = this.probenummer.getText();
        if (kennnummer != null) {
            probe.setKennummer(kennnummer);
        }

        // Bemerkung
        String newBemerkung = this.bemerkungsArea.getText();
        if (newBemerkung != null) {
            newBemerkung = newBemerkung.trim();
        }

        if (!"".equals(newBemerkung)) {
            probe.setBemerkung(newBemerkung);
        } else {
            probe.setBemerkung(null);
        }

        probe.setEnabled(true);
        probe.setDeleted(false);
        if (probe.getQbAusschliessen() == null)
        	probe.setQbAusschliessen(false);

        boolean success = true;

        probe = Probenahme.merge(probe);
        setProbe(probe);
        success = (probe != null);
        if (this.isNew) {
            this.isNew = false;
        }

//        parameterTabelle.getCellEditor().stopCellEditing();
        List<?> objects = this.parameterModel.getList();
        Analyseposition position;
        for (Object object : objects) {
            position = (Analyseposition) object;
            position.setProbenahme(probe);
            if (position.getParameter().getSielhautGw() != null) {
            	position.setNormwert(position.getWert()/position.getParameter().getSielhautGw());
            }
            if (position.getMapElkaAnalysemethode() == null) {
            	position.setMapElkaAnalysemethode(MapElkaAnalysemethode.findById(1));
            }
            success = success && position.merge();
        }

        return success;
    }

    /**
     * Diese Methode liefert die Parameter-Map für den Druck/Export eines
     * Probenahmeauftrages.
     * @return die Variablen für den Probenahmeauftrag als Map.
     */
    public Map<String, Object> getAuftragDruckMap(Probenahme probe) {
        Inhaber betr = probe.getMessstelle().getObjekt()
            .getBetreiberid();
        Adresse std = probe.getMessstelle().getObjekt()
            .getStandortid().getInhaber().getAdresse();
        Probeart art = probe.getMessstelle().getProbeart();

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("kennnummer", this.probenummer.getText());
        params.put("name", betr.toString());
        params.put("art", art.getArt());
		if (std.getHausnrzus() != null) {
			params.put(
					"betriebsgrundstueck",
					std.getStrasse() + " " + std.getHausnr()
							+ betr.getAdresse().getHausnrzus());
		} else {
			params.put("betriebsgrundstueck",
					std.getStrasse() + " " + std.getHausnr());
		}
        try {
            Integer anzahl = Integer.parseInt(this.beteiligte.getText());
            params.put("anzahlMitarbeiter", anzahl.toString());
        } catch (NumberFormatException nfe) {
            // do nothing
        }

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        params.put("entnahmeDatum", df.format(this.datum.getDate()));
        params.put("beginn", this.uhrzeitVon.getText());
        params.put("ende", this.uhrzeitBis.getText());
        params.put("fahrtzeit", this.fahrtzeit.getText());
        params.put("entnahmestelle", this.entnahmepunkt.getText());
        params.put("bemerkungen", this.bemerkungsArea.getText());

        Sachbearbeiter sb = (Sachbearbeiter) this.sachbearbeiterBox
            .getSelectedItem();

        StringBuilder info = new StringBuilder();
        if (sb != null) {
            info.append(sb.getName());
            info.append(", ");
            info.append(sb.getGehoertzuarbeitsgr());
            info.append(", ");
            info.append("0521/51-" + sb.getTelefon());
        }
        params.put("sachbearbeiterInfo", info.toString());

        // TODO fill in the correct values if they exist
        params.put("anzahlEntnahmestellen", "1");

        params.put("branche", probe.getMessstelle().getBranche());

        return params;
    }

    /**
     * Diese Methode liefert eine Map, mit allen Variablen, die für den
     * Druck/Export des Geb&uuml;hrenbescheid notwendig sind.
     * @return die Variablen des Geb&uuml;hrenbescheids als Map.
     */
    public Map<String, Object> getBescheidDruckMap(Probenahme probe)
        throws IllegalArgumentException {
        Inhaber betr =
            probe.getMessstelle().getObjekt().getBetreiberid();
        Adresse basisStandort =
            probe.getMessstelle().getObjekt().getStandortid().getInhaber().getAdresse();

        HashMap<String, Object> params = new HashMap<String, Object>();

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        String entnahmedatum = DateUtils.format(this.datum.getDate(),
            DateUtils.FORMAT_DATE);
        String entnahmezeit = DateUtils.format(this.datum.getDate(),
            DateUtils.FORMAT_TIME);

        String entnahmezeitpunkt = entnahmedatum + ", " + entnahmezeit + " Uhr";

        Date now = new Date();
        params.put("datum", DateUtils.format(now, DateUtils.FORMAT_DATE));
        params.put("entnahmedatum", entnahmezeitpunkt);
        params.put("entnahmeort", basisStandort.toString());//basisStandort.toString());
        params.put("entnahmestelle", probe.getMessstelle().getObjekt()
            .getBeschreibung());
        params.put("entnahmestellen", "1");
        params.put("maxdatum", DateUtils.format(DateUtils.getDateOfBill(now),
            DateUtils.FORMAT_DATE));
        params.put("kosten",
        		nf.format(getPersonalkosten()/100));
        params.put("kassenzeichen", betr.getKassenzeichen());
        params.put("firmaAnrede", betr.getAnrede());
        if (betr.getVorname() != null)
            params.put("firmaName",
                betr.getVorname() + " " + betr.getName());
        else
            params.put("firmaName", betr.getName());
        params.put("firmaNameZus", betr.getNamezus());
        params.put("firmaStrasse", DatabaseQuery.getBetriebsgrundstueck(betr.getAdresse()));
        params.put("firmaOrt", betr.getAdresse().getPlz() + " " + betr.getAdresse().getOrt());

        try {
            Integer anzahl = Integer.parseInt(this.beteiligte.getText());
            params.put("probenehmer", anzahl.toString());
        } catch (NumberFormatException nfe) {
            params.put("probenehmer", "1");
        }

        try {
            String beginn = this.uhrzeitVon.getText();
            String ende = this.uhrzeitBis.getText();
            String fahrt = this.fahrtzeit.getText();

            Date beginnDate = DateUtils.parse(beginn, DateUtils.FORMAT_TIME);
            Date fahrtDate = DateUtils.parse(fahrt, DateUtils.FORMAT_TIME);
            Date endeDate = DateUtils.parse(ende, DateUtils.FORMAT_TIME);
            String mitFahrt = DateUtils.getAddition(endeDate, fahrtDate);
            Date mitFahrtDate = DateUtils
                .parse(mitFahrt, DateUtils.FORMAT_TIME);

            String dauer = DateUtils.getDuration(beginnDate, mitFahrtDate);
            double kosten = Math.round((calculateSachUndPersonalkosten()) * 100) / 100.0;
            double gesamt = Math
                .round((kosten + getAnalysekosten(probe)) * 100) / 100.0;

            params.put("personalsachkosten", nf.format(kosten) + " €");
            params.put("analysekosten", nf.format(getAnalysekosten(probe))
                + " €");
            params.put("gesamtkosten", nf.format(gesamt) + " €");

            params.put("dauer", dauer);
        } catch (ParseException pe) {
            params.put("dauer", "hh:mm");
            params.put("gesamtkosten", "xx,xx");
        }

        return params;
    }

    /**
     * Return personalkosten or load from db if not set
     * @return personalkosten as double
     * @throws RuntimeException Thrown if no database entry could be found
     */
    private double getPersonalkosten() throws RuntimeException{
        if (this.personalkosten >= 0) {
            return this.personalkosten;
        }
        Gebuehren record = Gebuehren.findByBezeichnung(GebuehrenEditor.PERSONNEL_FEE_BEZEICHNUNG);
        if (record == null) {
            throw new RuntimeException("Can not find personnel fee in database");
        }
        this.personalkosten = (double) record.getWert();
        return this.personalkosten;
    }

    /**
     * Diese Funktion berechnet die Dauer der Probenahme anhand Start- und
     * Endzeit.
     */
    protected double getDauer() throws ParseException {
        String beginn = this.uhrzeitVon.getText();
        String ende = this.uhrzeitBis.getText();
        String fahrt = this.fahrtzeit.getText();

        Date beginnDate = DateUtils.parse(beginn, DateUtils.FORMAT_TIME);
        Date fahrtDate = DateUtils.parse(fahrt, DateUtils.FORMAT_TIME);
        Date endeDate = DateUtils.parse(ende, DateUtils.FORMAT_TIME);
        String mitFahrt = DateUtils.getAddition(endeDate, fahrtDate);
        Date mitFahrtDate = DateUtils.parse(mitFahrt, DateUtils.FORMAT_TIME);

        return DateUtils.getDurationHours(beginnDate, mitFahrtDate);
    }

    /**
     * Diese Funktion liefert die Anzahl der beteiligten Probenehmer.
     */
    protected int getAnzahl() {
        return Integer.parseInt(this.beteiligte.getText());
    }

    /**
     * Diese Methode liefert die Sach- und Personalkosten.
     * @return die Personal- und Sachkosten.
     */
    public double calculateSachUndPersonalkosten() throws ParseException {
    	double kosten = getPersonalkosten() * getDauer() * getAnzahl();
    	double round = Math.round(kosten);
    	return round/100;
    }

    /**
     * Diese Methode liefert die Analysekosten der <i>probe</i>.
     * @return die Analysekosten.
     */
    public double getAnalysekosten(Probenahme probe)
        throws IllegalArgumentException {
        List<Analyseposition> sorted =
            DatabaseQuery.getSortedAnalysepositionen(probe);
        HashMap<Integer, List<Parameter>> gruppen = new HashMap<Integer, List<Parameter>>();
        double single = 0d;
        double group = 0d;

        Analyseposition pos = null;
        Parameter para = null;
        Parametergruppen gruppe = null;
        for (int i = 0; i < sorted.size(); i++) {
            pos = sorted.get(i);
            para = pos.getParameter();
            gruppe = para.getParametergruppen();

            if (gruppe == null) {
                single += para.getPreisfueranalyse();
            } else {
                Integer id = gruppe.getId();

                if (gruppen.containsKey(id)) {
                    gruppen.get(id).add(para);
                } else {
                    List<Parameter> neu = new ArrayList<Parameter>();
                    neu.add(para);

                    gruppen.put(id, neu);
                }
            }
        }

        int anzahlGruppen = gruppen.size();
        if (anzahlGruppen == 0) {
            return single;
        }

        Set<Integer> keys = gruppen.keySet();
        Iterator<Integer> iter = keys.iterator();

        while (iter.hasNext()) {
            Integer id = iter.next();
            group += getGruppierteAnalysekosten(id, gruppen.get(id));
        }

        return single + group;
    }

    /**
     * Diese Methode errechnet den Preis einer Parametergruppe. Wenn alle
     * Parameter einer Parametergruppen in der Probenahme enthalten sind, wird
     * der entsprechende Gruppenpreis zur&uuml;ckgegeben. Sind nicht alle
     * Parameter enthalten, werden die Preise jedes Parameters addiert, und
     * dieser Wert zur&uuml;ckgegeben. Falls eine Gruppe jedoch nicht komplett
     * ist, aber die Parameter nicht einzeln gepr&uuml;ft werden k&ouml;nnen,
     * wird ein Fehler geworfen.
     * @param gruppe Die ID der Paramtergruppe
     * @param params Eine Liste, die die Parameter einer Gruppe enth&auml;lt
     * @return den Preis der Parameter.
     * @throws IllegalArgumentException
     */
    public double getGruppierteAnalysekosten(int gruppe,
        List<Parameter> params) throws IllegalArgumentException {
        double preis = 0d;

        if (DatabaseQuery.isCompleteParameterGroup(gruppe, params)) {
            Parametergruppen g = Parametergruppen.findById(gruppe);

            if (g == null) {
                log.debug("No such group with id: " + gruppe);
                return 0;
            }

            preis += g.getPreisfueranalyse();
        } else {
            int parameter = params.size();

            for (int i = 0; i < parameter; i++) {
                Parameter p = params.get(i);

                if (p.getEinzelnbeauftragbar()) {
                    preis += p.getPreisfueranalyse();
                } else {
                    String msg = "Parameter " + p.getOrdnungsbegriff()
                        + " ist nicht " + "einzeln prüfbar.";

                    log.error(msg);

                    throw new IllegalArgumentException(msg);
                }
            }
        }

        return preis;
    }

    /**
     * Dies Funktion liefert den Rechnungsbetrag der Probenhame bestehend aus
     * Sach- und Personalkosten und der Analysekosten.
     * @return den Rechnungsbetrag.
     */
    public double getRechnungsbetrag(Probenahme probe)
        throws ParseException, IllegalArgumentException {
        return Math.round(calculateSachUndPersonalkosten() * 100.)
            + Math.round(getAnalysekosten(probe) * 100.);
    }

    /**
     * Diese Funktion berechnet den Rechnungsbetrag der <i>probe</i>, setzt den
     * Betrag am Objekt und aktualisiert die GUI.
     */
    protected void updateRechnungsbetrag(Probenahme probe)
        throws ParseException {
        double betrag = getRechnungsbetrag(probe) / 100;

        probe.setKosten(betrag);

        CurrencyDouble cd = new CurrencyDouble(betrag, Locale.GERMANY);
        this.rechnungsBetrag.setText(cd.toString());
    }

    /**
     * Diese Funktion berechnet das Rechnugnsdatum, setzt dies am AtlProbenahmen
     * Objekt und aktualisiert die GUI mit dem aktuellen Datum.
     */
    protected void updateRechnungsdatum(Probenahme probe) {
        Date now = new Date();
        // TODO: Check this: This variable was not used, but should it really
        // not?
//        Date datum = DateUtils.getDateOfBill(now);

        probe.setBescheid(now);

        this.rechnungsDatum.setText(DateUtils
            .format(now, DateUtils.FORMAT_DATE));
    }

    /**
     * Öffnet den Dialog "Parameter zusammenstellen" und fügt die dort
     * gewählten Parameter in die Probenahme ein.
     */
    private void parameterZusammenstellen() {
        if (parameterTabelle.isEditing()) {
            parameterTabelle.getCellEditor().stopCellEditing();
        }
        List<Parameter> vorhanden = new ArrayList<>();
        for (Object o : parameterModel.getList()) {
            Parameter p = ((Analyseposition) o).getParameter();
            if (p != null) {
                vorhanden.add(p);
            }
        }

        ParameterZusammenstellungDialog dialog =
            new ParameterZusammenstellungDialog(this.frame,
                getProbe().getMessstelle(), getProbe().getId(), vorhanden);
        dialog.setVisible(true);

        List<Analyseposition> auswahl = dialog.getErgebnis();
        if (auswahl == null || auswahl.isEmpty()) {
            return;
        }
        int neu = 0;
        for (Analyseposition pos : auswahl) {
            if (parameterModel.uebernehmeParameter(pos)) {
                neu++;
            }
        }
        parameterModel.fireTableDataChanged();
        this.frame.changeStatus(neu + " Parameter hinzugefügt.");
    }

    @Override
    protected void doApply() {
        parameterZusammenstellen();
    }

    public Probenahme getProbe() {
        return (Probenahme) getEditedObject();
    }

    public void setProbe(Probenahme probe) {
        setEditedObject(probe);
    }

}

/**
 * Dialog "Parameter zusammenstellen". Links die Quellen:
 * <ul>
 * <li>Reiter "Suche und Gruppen": Parameter nach Parametergruppe
 * (labor.parametergruppen) und/oder Suchtext eingrenzen</li>
 * <li>Reiter "Frühere Probenahme": Parameter einer früheren Probenahme
 * desselben Probenahmepunkts</li>
 * </ul>
 * Rechts die Zusammenstellung, die mit "Übernehmen" in die Probenahme
 * eingefügt wird. Parameter, die schon in der Probenahme sind, werden
 * nicht doppelt aufgenommen.
 */
class ParameterZusammenstellungDialog extends OkCancelDialog {
    private static final long serialVersionUID = 1L;

    // Ohne Initialisierer: werden in buildContentArea() angelegt, das
    // schon im Konstruktor der Oberklasse aufgerufen wird
    private JComboBox<Object> gruppenBox;
    private JTextField suchFeld;
    private JList<Parameter> parameterListe;
    private DefaultListModel<Parameter> parameterListModel;
    private JLabel parameterLabel;

    private JTable probenTabelle;
    private DefaultTableModel probenModel;
    private JList<Analyseposition> vorlageListe;
    private DefaultListModel<Analyseposition> vorlageModel;
    private JLabel vorlageLabel;

    private JList<Analyseposition> auswahlListe;
    private DefaultListModel<Analyseposition> auswahlModel;
    private JLabel auswahlLabel;
    private JLabel hinweisLabel;

    private List<Parameter> gruppierteParameter;
    private List<Parameter> alleParameter;
    private List<Probenahme> proben;
    private Map<Integer, List<Analyseposition>> vorlagen;
    private Set<Parameter> schonInProbe;
    private List<Analyseposition> ergebnis;

    ParameterZusammenstellungDialog(HauptFrame owner, Messstelle messstelle,
        Integer aktuelleProbeId, List<Parameter> vorhanden) {
        super("Parameter zusammenstellen", owner);

        schonInProbe = new LinkedHashSet<>(vorhanden);
        vorlagen = new HashMap<>();

        // Frühere Probenahmen desselben Probenahmepunkts, neueste zuerst
        proben = new ArrayList<>();
        if (messstelle != null) {
            for (Probenahme p : DatabaseQuery.findProbenahme(messstelle)) {
                if (aktuelleProbeId == null
                    || !aktuelleProbeId.equals(p.getId())) {
                    proben.add(p);
                }
            }
        }
        for (Probenahme p : proben) {
            probenModel.addRow(new Object[] {
                p.getDatumDerEntnahme() != null
                    ? DateUtils.format(p.getDatumDerEntnahme(),
                        DateUtils.FORMAT_DATE)
                    : "",
                p.getKennummer()});
        }
        if (proben.isEmpty()) {
            vorlageLabel.setText("Keine früheren Probenahmen vorhanden.");
        } else {
            probenTabelle.setRowSelectionInterval(0, 0);
        }

        filtern();
        aktualisiereAuswahl();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                suchFeld.requestFocusInWindow();
            }
        });
    }

    @Override
    protected String getOkButtonText() {
        return "Übernehmen";
    }

    /** @return die zusammengestellten Positionen oder null bei Abbruch */
    public List<Analyseposition> getErgebnis() {
        return ergebnis;
    }

    @Override
    protected void doOk() {
        ergebnis = new ArrayList<>();
        for (int i = 0; i < auswahlModel.size(); i++) {
            ergebnis.add(auswahlModel.get(i));
        }
        close();
    }

    // ── Oberfläche ────────────────────────────────────────────

    @Override
    protected JComponent buildContentArea() {
        JTabbedPane quellen = new JTabbedPane();
        quellen.addTab("Suche und Gruppen", baueSuchReiter());
        quellen.addTab("Frühere Probenahme", baueVorlageReiter());

        JPanel inhalt = new JPanel(new GridLayout(1, 2, 10, 0));
        inhalt.add(quellen);
        inhalt.add(baueAuswahlSeite());
        inhalt.setPreferredSize(new Dimension(820, 480));
        return inhalt;
    }

    private JPanel baueSuchReiter() {
        gruppenBox = new JComboBox<>();
        gruppenBox.addItem("Alle Gruppen");
        List<Parametergruppen> gruppen = new ArrayList<>();
        for (Parametergruppen g : Parametergruppen.getAll()) {
            if (!g.isDeleted() && g.getName() != null) {
                gruppen.add(g);
            }
        }
        gruppen.sort(Comparator.comparing(Parametergruppen::getName,
            String.CASE_INSENSITIVE_ORDER));
        for (Parametergruppen g : gruppen) {
            gruppenBox.addItem(g);
        }
        gruppenBox.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> list,
                Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
                Object text = value instanceof Parametergruppen
                    ? ((Parametergruppen) value).getName() : value;
                return super.getListCellRendererComponent(list, text,
                    index, isSelected, cellHasFocus);
            }
        });
        gruppenBox.addActionListener(e -> filtern());

        suchFeld = new JTextField();
        suchFeld.setToolTipText(
            "Wortteile genügen, Groß-/Kleinschreibung egal");
        suchFeld.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtern();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtern();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtern();
            }
        });
        // Pfeil runter / Enter: in die Liste wechseln
        binde(suchFeld, KeyEvent.VK_DOWN, "zurListe", this::zurListe);
        binde(suchFeld, KeyEvent.VK_ENTER, "zurListe", this::zurListe);

        parameterListModel = new DefaultListModel<>();
        parameterListe = new JList<>(parameterListModel);
        parameterListe.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        parameterListe.setCellRenderer(parameterRenderer());
        parameterListe.addMouseListener(doppelklick(
            () -> hinzufuegen(vorlagenFuer(
                parameterListe.getSelectedValuesList()))));
        binde(parameterListe, KeyEvent.VK_ENTER, "hinzufuegen",
            () -> hinzufuegen(vorlagenFuer(
                parameterListe.getSelectedValuesList())));
        parameterLabel = new JLabel(" ");

        JButton markierte = new JButton("Markierte hinzufügen");
        markierte.addActionListener(e -> hinzufuegen(vorlagenFuer(
            parameterListe.getSelectedValuesList())));
        JButton alle = new JButton("Alle angezeigten hinzufügen");
        alle.addActionListener(e -> {
            List<Parameter> angezeigt = new ArrayList<>();
            for (int i = 0; i < parameterListModel.size(); i++) {
                angezeigt.add(parameterListModel.get(i));
            }
            hinzufuegen(vorlagenFuer(angezeigt));
        });

        JPanel kopf = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 3, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        kopf.add(new JLabel("Gruppe"), gbc);
        gbc.gridy = 1;
        kopf.add(new JLabel("Suche"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 3, 0);
        gbc.gridy = 0;
        kopf.add(gruppenBox, gbc);
        gbc.gridy = 1;
        kopf.add(suchFeld, gbc);

        return quellPanel(kopf, new JScrollPane(parameterListe),
            parameterLabel, markierte, alle);
    }

    private JPanel baueVorlageReiter() {
        probenModel = new DefaultTableModel(
            new Object[] {"Entnahme", "Kennnummer"}, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        probenTabelle = new JTable(probenModel);
        probenTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        probenTabelle.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                zeigeVorlage();
            }
        });
        // Doppelklick auf eine Probenahme: alle ihre Parameter hinzufügen
        probenTabelle.addMouseListener(doppelklick(
            () -> hinzufuegen(vorlageDerAuswahl())));

        vorlageModel = new DefaultListModel<>();
        vorlageListe = new JList<>(vorlageModel);
        vorlageListe.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        vorlageListe.setCellRenderer(parameterRenderer());
        vorlageListe.addMouseListener(doppelklick(
            () -> hinzufuegen(vorlageListe.getSelectedValuesList())));
        vorlageLabel = new JLabel(" ");

        JButton markierte = new JButton("Markierte hinzufügen");
        markierte.addActionListener(
            e -> hinzufuegen(vorlageListe.getSelectedValuesList()));
        JButton alle = new JButton("Alle hinzufügen");
        alle.addActionListener(e -> hinzufuegen(vorlageDerAuswahl()));

        JScrollPane probenScroller = new JScrollPane(probenTabelle);
        probenScroller.setPreferredSize(new Dimension(300, 150));

        JPanel kopf = new JPanel(new BorderLayout(0, 3));
        kopf.add(probenScroller, BorderLayout.CENTER);

        return quellPanel(kopf, new JScrollPane(vorlageListe),
            vorlageLabel, markierte, alle);
    }

    private JPanel baueAuswahlSeite() {
        auswahlModel = new DefaultListModel<>();
        auswahlListe = new JList<>(auswahlModel);
        auswahlListe.setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        auswahlListe.setCellRenderer(parameterRenderer());
        binde(auswahlListe, KeyEvent.VK_DELETE, "entfernen",
            this::entferneMarkierte);
        auswahlLabel = new JLabel(" ");
        hinweisLabel = new JLabel(" ");

        JButton entfernen = new JButton("Entfernen");
        entfernen.setToolTipText(
            "Markierte Parameter entfernen (auch mit der Entf-Taste)");
        entfernen.addActionListener(e -> entferneMarkierte());
        JButton leeren = new JButton("Alle entfernen");
        leeren.addActionListener(e -> {
            auswahlModel.clear();
            aktualisiereAuswahl();
        });

        JPanel kopf = new JPanel(new BorderLayout());
        kopf.add(auswahlLabel, BorderLayout.WEST);

        JPanel knoepfe = new JPanel(new GridLayout(1, 2, 5, 0));
        knoepfe.add(entfernen);
        knoepfe.add(leeren);
        JPanel fuss = new JPanel(new BorderLayout(0, 3));
        fuss.add(hinweisLabel, BorderLayout.NORTH);
        fuss.add(knoepfe, BorderLayout.WEST);

        JPanel seite = new JPanel(new BorderLayout(0, 5));
        seite.add(kopf, BorderLayout.NORTH);
        seite.add(new JScrollPane(auswahlListe), BorderLayout.CENTER);
        seite.add(fuss, BorderLayout.SOUTH);
        return seite;
    }

    /** Gemeinsamer Aufbau der beiden Reiter. */
    private static JPanel quellPanel(JComponent kopf, JScrollPane liste,
        JLabel info, JButton markierte, JButton alle) {
        JPanel knoepfe = new JPanel(new GridLayout(1, 2, 5, 0));
        knoepfe.add(markierte);
        knoepfe.add(alle);
        JPanel fuss = new JPanel(new BorderLayout(0, 3));
        fuss.add(info, BorderLayout.NORTH);
        fuss.add(knoepfe, BorderLayout.WEST);

        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(kopf, BorderLayout.NORTH);
        panel.add(liste, BorderLayout.CENTER);
        panel.add(fuss, BorderLayout.SOUTH);
        return panel;
    }

    /** Zeigt bei Parametern und Analysepositionen den Parameternamen. */
    private static DefaultListCellRenderer parameterRenderer() {
        return new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> list,
                Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
                Object text = value instanceof Analyseposition
                    ? ((Analyseposition) value).getParameter() : value;
                return super.getListCellRendererComponent(list,
                    String.valueOf(text), index, isSelected, cellHasFocus);
            }
        };
    }

    private static MouseAdapter doppelklick(Runnable aktion) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    aktion.run();
                }
            }
        };
    }

    private static void binde(JComponent komponente, int taste, String name,
        Runnable aktion) {
        komponente.getInputMap().put(KeyStroke.getKeyStroke(taste, 0), name);
        komponente.getActionMap().put(name, new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                aktion.run();
            }
        });
    }

    // ── Reiter "Suche und Gruppen" ────────────────────────────

    /**
     * Füllt die Parameterliste: ohne Suchtext und Gruppe die
     * Probenahme-Parameter, sonst alle Parameter, eingegrenzt auf die
     * Gruppe und auf Namen, die alle eingegebenen Wortteile enthalten.
     */
    private void filtern() {
        if (parameterListModel == null || suchFeld == null) {
            return;
        }
        String text = suchFeld.getText().trim().toLowerCase(Locale.GERMAN);
        Object auswahl = gruppenBox.getSelectedItem();
        Integer gruppenId = auswahl instanceof Parametergruppen
            ? ((Parametergruppen) auswahl).getId() : null;

        List<Parameter> quelle;
        if (text.isEmpty() && gruppenId == null) {
            if (gruppierteParameter == null) {
                gruppierteParameter =
                    DatabaseQuery.getGroupedParameterAsList();
            }
            quelle = gruppierteParameter;
        } else {
            if (alleParameter == null) {
                alleParameter = DatabaseQuery.getAllParameterAsList();
            }
            quelle = alleParameter;
        }

        String[] woerter = text.isEmpty()
            ? new String[0] : text.split("\\s+");
        List<Parameter> vorne = new ArrayList<>();
        List<Parameter> rest = new ArrayList<>();
        for (Parameter p : quelle) {
            if (gruppenId != null && (p.getParametergruppen() == null
                || !gruppenId.equals(p.getParametergruppen().getId()))) {
                continue;
            }
            String name = String.valueOf(p).toLowerCase(Locale.GERMAN);
            boolean passt = true;
            for (String wort : woerter) {
                if (!name.contains(wort)) {
                    passt = false;
                    break;
                }
            }
            if (passt) {
                // Treffer, die mit dem Suchtext beginnen, zuerst
                if (woerter.length > 0 && name.startsWith(woerter[0])) {
                    vorne.add(p);
                } else {
                    rest.add(p);
                }
            }
        }
        vorne.addAll(rest);

        parameterListModel.clear();
        parameterListModel.addAll(vorne);
        parameterLabel.setText(vorne.size() + " Parameter angezeigt");
    }

    private void zurListe() {
        if (!parameterListModel.isEmpty()) {
            if (parameterListe.isSelectionEmpty()) {
                parameterListe.setSelectedIndex(0);
            }
            parameterListe.requestFocusInWindow();
        }
    }

    /** Macht aus Parametern Vorlagen mit der Standard-Einheit. */
    private static List<Analyseposition> vorlagenFuer(
        List<Parameter> parameter) {
        List<Analyseposition> liste = new ArrayList<>();
        for (Parameter p : parameter) {
            Analyseposition pos = new Analyseposition();
            pos.setParameter(p);
            pos.setEinheiten(p.getEinheiten());
            liste.add(pos);
        }
        return liste;
    }

    // ── Reiter "Frühere Probenahme" ───────────────────────────

    /** Parameter der markierten Probenahme (einmal je Probe geladen). */
    private List<Analyseposition> vorlageDerAuswahl() {
        int row = probenTabelle.getSelectedRow();
        if (proben == null || row < 0 || row >= proben.size()) {
            return new ArrayList<>();
        }
        Probenahme probe = proben.get(row);
        return vorlagen.computeIfAbsent(probe.getId(),
            id -> DatabaseQuery.getSortedAnalysepositionen(probe));
    }

    private void zeigeVorlage() {
        vorlageModel.clear();
        List<Analyseposition> positionen = vorlageDerAuswahl();
        vorlageModel.addAll(positionen);
        vorlageLabel.setText(positionen.size()
            + " Parameter in dieser Probenahme");
    }

    // ── Zusammenstellung ──────────────────────────────────────

    private void hinzufuegen(List<Analyseposition> neue) {
        if (neue == null || neue.isEmpty()) {
            return;
        }
        Set<Parameter> drin = new LinkedHashSet<>();
        for (int i = 0; i < auswahlModel.size(); i++) {
            drin.add(auswahlModel.get(i).getParameter());
        }

        int hinzu = 0;
        int inProbe = 0;
        int doppelt = 0;
        for (Analyseposition pos : neue) {
            Parameter p = pos.getParameter();
            if (p == null) {
                continue;
            }
            if (schonInProbe.contains(p)) {
                inProbe++;
            } else if (!drin.add(p)) {
                doppelt++;
            } else {
                auswahlModel.addElement(pos);
                hinzu++;
            }
        }

        StringBuilder hinweis = new StringBuilder(hinzu + " hinzugefügt");
        if (inProbe > 0) {
            hinweis.append(", ").append(inProbe)
                .append(" schon in der Probenahme");
        }
        if (doppelt > 0) {
            hinweis.append(", ").append(doppelt)
                .append(" schon in der Liste");
        }
        hinweisLabel.setText(hinweis.toString());
        aktualisiereAuswahl();
        if (!auswahlModel.isEmpty()) {
            auswahlListe.ensureIndexIsVisible(auswahlModel.size() - 1);
        }
    }

    private void entferneMarkierte() {
        int[] indizes = auswahlListe.getSelectedIndices();
        if (indizes.length == 0) {
            return;
        }
        for (int i = indizes.length - 1; i >= 0; i--) {
            auswahlModel.remove(indizes[i]);
        }
        // Nächsten Eintrag markieren, damit man weiter entfernen kann
        if (!auswahlModel.isEmpty()) {
            int index = Math.min(indizes[0], auswahlModel.size() - 1);
            auswahlListe.setSelectedIndex(index);
            auswahlListe.ensureIndexIsVisible(index);
        }
        hinweisLabel.setText(indizes.length + " entfernt");
        aktualisiereAuswahl();
        auswahlListe.requestFocusInWindow();
    }

    private void aktualisiereAuswahl() {
        auswahlLabel.setText("Zusammenstellung (" + auswahlModel.size()
            + " Parameter)");
    }
}