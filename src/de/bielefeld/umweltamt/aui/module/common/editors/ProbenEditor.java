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

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import net.sf.jasperreports.engine.JRDataSource;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlStatus;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.ComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.DoubleRenderer;
import de.bielefeld.umweltamt.aui.utils.JRMapDataSource;
import de.bielefeld.umweltamt.aui.utils.KommaDouble;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.PDFExporter;
import de.bielefeld.umweltamt.aui.utils.SelectTable;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;
import de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein Dialog um eine Probenahme mit ihren Analysepositionen zu bearbeiten.
 * (Momentan ist vieles noch auf Klärschlamm-Proben ausgerichtet)
 * @author David Klotz
 */
public class ProbenEditor extends AbstractApplyEditor {

    private class ParameterModel extends EditableListTableModel {
        private AtlProbenahmen probe;
        private boolean isNew;

        public ParameterModel(AtlProbenahmen probe, boolean isNew) {
            super(new String[]{
                    "Parameter",
                    "GrKl",
                    "Messwert",
                    "Einheit",
                    "Analyse von",
                    "Grenzwert",
                    "% Normwert"}, false, true);
            this.probe = probe;
            this.isNew = isNew;

            updateList();
        }

        public void updateList() {
            //Set positionen;
            if (!isNew) {
                //AUIKataster.debugOutput("Bearbeite alte Probe: " + probe);
                //if (!probe.isAnalysepositionenInitialized()) {
                    //probe = AtlProbenahmen.getProbenahme(probe.getKennummer(), true);
                    //AUIKataster.debugOutput("Analysepositionen gefetcht! Probe neu geholt: " + probe);
                //}
                //positionen = probe.getAtlAnalysepositionen();
                probe = AtlProbenahmen.getProbenahme(probe.getId(), true);
                setList(AtlProbenahmen.sortAnalysepositionen(probe));
            } else {
                //AUIKataster.debugOutput("Bearbeite neue Probe! Positionen leer.");
                //positionen = new HashSet();
                setList(new ArrayList());
            }

            fireTableDataChanged();
        }

        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            Object value;
            AtlAnalyseposition pos = (AtlAnalyseposition) objectAtRow;
            switch(columnIndex) {
                // Parameter
                case 0:
                    value = pos.getAtlParameter();
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
                    value = pos.getAtlEinheiten();
                    break;
                // Analyse von
                case 4:
                    value = pos.getAnalyseVon();
                    break;
                // Grenzwert
                case 5:
                    value = null;
                    Double grenzWert = null;
                    if (probe.isKlaerschlammProbe()) {
                        grenzWert = pos.getAtlParameter().getKlaerschlammGw();
                    } else if (probe.getProbeArt().isSielhaut()) {
                        grenzWert = pos.getAtlParameter().getSielhautGw();
                    }

                    if (grenzWert != null && !grenzWert.equals(new Double(0.0))) {
                        value = grenzWert;
                    }
                    break;
                // % Normwert
                case 6:
                    value = "-";
                    if (pos.getAtlEinheiten().getId().equals(AtlEinheiten.MG_KG_ID)) {
                        double tmpVal = -1;
                        if (probe.isKlaerschlammProbe()) {
                            if (pos.getAtlParameter().getKlaerschlammGw() != null) {
                                if (!pos.getAtlParameter().getKlaerschlammGw().equals(new Double(0.0))) {
                                    tmpVal = pos.getWert().doubleValue() / pos.getAtlParameter().getKlaerschlammGw().doubleValue();
                                }
                            }
                        } else if (probe.getProbeArt().isSielhaut()) {
                            if (pos.getAtlParameter().getSielhautGw() != null) {
                                if (!pos.getAtlParameter().getSielhautGw().equals(new Double(0.0))) {
                                    tmpVal = pos.getWert().doubleValue() / pos.getAtlParameter().getSielhautGw().doubleValue();
                                }
                            }
                        }
                        if (tmpVal != -1) {
                            NumberFormat percentFormat = NumberFormat.getPercentInstance();
                            percentFormat.setMinimumFractionDigits(1);
                            if (tmpVal > 1.0) {
                                value = "<html><font color=#ff0000>" + percentFormat.format(tmpVal) + "</font></html>";
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

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == 5 || columnIndex == 6) {
                return false;
            } else {
                return true;
            }
        }
        public void editObject(Object objectAtRow, int columnIndex,
                Object newValue) {
            AtlAnalyseposition tmp = (AtlAnalyseposition) objectAtRow;

            switch (columnIndex) {
                case 0:
                    if (newValue instanceof AtlParameter) {
                        AtlParameter tmpPara = (AtlParameter) newValue;
                        tmp.setAtlParameter(tmpPara);
                    }
                    break;

                case 1:
                    String tmpGrKl = (String) newValue;
                    if (tmpGrKl != null) {
                        tmpGrKl = tmpGrKl.trim();
                        if (tmpGrKl.equals("")) {
                            tmp.setGrkl(null);
                        } else {
                            tmpGrKl = tmpGrKl.substring(0,1);
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
                        tmpWert = ((KommaDouble)newValue).getValue().floatValue();
                    }
                    tmp.setWert(tmpWert);
                    break;

                case 3:
                    AtlEinheiten tmpEinheit = (AtlEinheiten) newValue;
                    tmp.setAtlEinheiten(tmpEinheit);
                    break;

                case 4:
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

            AUIKataster.debugOutput("EDIT: " + tmp, "ParameterModel.editObject");
        }

        public Object newObject() {
            AtlAnalyseposition tmp = new AtlAnalyseposition(probe);
            if (probe.isKlaerschlammProbe() || probe.getProbeArt().isSielhaut()) {
                tmp.setAtlEinheiten(AtlEinheiten.getEinheit(AtlEinheiten.MG_KG_ID));
            } else {
                tmp.setAtlEinheiten(AtlEinheiten.getEinheit(AtlEinheiten.MG_L_ID));
            }
            tmp.setAtlParameter((AtlParameter) parameterBox.getSelectedItem());
            tmp.setAtlEinheiten(AtlEinheiten.getEinheit(tmp.getAtlParameter().getWirdgemessenineinheit()));
            //tmp.setAnalyseVon("");
            return tmp;
        }

        public boolean objectRemoved(Object objectAtRow) {
            //AtlAnalyseposition tmp = (AtlAnalyseposition) objectAtRow;
            //getList().remove(tmp);

            return true;//probe.getAtlAnalysepositionen().remove(tmp);
        }

        public Class getColumnClass(int columnIndex) {
            Class tmp;
            switch (columnIndex) {
                case 0:
                    tmp = AtlParameter.class;
                    break;

                case 2:
                    tmp = KommaDouble.class;
                    break;

                case 3:
                    tmp = AtlEinheiten.class;
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

    private Dimension            minimumSize;

    private JComboBox            vorgangsstatus;
    private JButton              statusHoch;
    private TextFieldDateChooser datum;
    private JFormattedTextField  uhrzeitVon;
    private JFormattedTextField  uhrzeitBis;
    private JFormattedTextField  fahrtzeit;
    private JTextField           bezug;
    private JTextField           beteiligte;
    private JTextField           probenummer;
    private TextFieldDateChooser rechnungsDatum;
    private DoubleField          rechnungsBetrag;
    private JTextArea            bemerkungsArea;

    private JTextField           sachbearbeiter;
    private JTextField           bescheidDatei;
    private JButton              bescheidWahl;
    private JButton              bescheidDrucken;
    private JTextField           auftragDatei;
    private JButton              auftragWahl;
    private JButton              auftragDrucken;
    private JFileChooser         dateiChooser;
    private JLabel               betrieb;
    private JLabel               entnahmepunkt;
    private JFormattedTextField  icpEinwaageFeld;
    private TextFieldDateChooser icpDatum;
    private JTable               parameterTabelle;

    private JComboBox            parameterBox;
    private JComboBox            einheitenBox;

    private ParameterModel       parameterModel;
    private boolean isNew;


    public ProbenEditor(AtlProbenahmen probe, HauptFrame owner, boolean isNew) {
        super("Probenahme " + probe.getKennummer(), probe, owner);
        this.isNew = isNew;

        if (!isNew /*probe.isAnalysepositionenInitialized()*/) {
            setEditedObject(AtlProbenahmen.getProbenahme(probe.getId(), true));
        }

        parameterModel = new ParameterModel(getProbe(), isNew);
        parameterTabelle.setModel(parameterModel);

        initColumns();
    }

    protected JComponent buildContentArea() {
        NumberFormat     nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        SimpleDateFormat zf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat f  = new SimpleDateFormat ("HH:mm");

        entnahmepunkt    = new JLabel();
        datum            = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        rechnungsDatum   = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        uhrzeitVon       = new JFormattedTextField(f);
        uhrzeitBis       = new JFormattedTextField(f);
        fahrtzeit        = new JFormattedTextField(zf);
        rechnungsBetrag  = new DoubleField(0,2);
        bezug            = new JTextField();
        beteiligte       = new JTextField();
        probenummer      = new JTextField();
        vorgangsstatus   = new JComboBox();
        statusHoch       = new JButton("erhöhen");
        sachbearbeiter   = new JTextField();
        bescheidDatei    = new JTextField();
        bescheidWahl     = new JButton("Auswählen");
        bescheidDrucken  = new JButton("Drucken");
        auftragDatei     = new JTextField();
        auftragWahl      = new JButton("Auswählen");
        auftragDrucken   = new JButton("Drucken");
        dateiChooser     = new JFileChooser();
        icpEinwaageFeld  = new DoubleField(0);
        icpDatum         = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        bemerkungsArea   = new LimitedTextArea(255);
        betrieb          = new JLabel();
        parameterTabelle = new SelectTable();

        // wir nehmen hier nur die Strings um die ComboBox zu füllen, da die Box
        // nicht mehr auf Änderungen reagiert, wenn man sie mit AtlStatus
        // Objekten befüllt
        String[] bezeichnungen   = AtlStatus.getStatusAsString();
        ComboBoxModel comboModel = new DefaultComboBoxModel(bezeichnungen);
        vorgangsstatus.setModel(comboModel);

        bescheidDatei.setEnabled(false);
        auftragDatei.setEnabled(false);

        statusHoch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.showInfoMessage(
                    "Diese Funktion ist derzeit noch nicht implementiert.",
                    "NOT IMPLEMENTED");
            }
        });

        auftragWahl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFileChooser(auftragDatei);
            }
        });

        bescheidWahl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFileChooser(bescheidDatei);
            }
        });

        // triggert das Erzeugen eines PDFs und einen Druck-Job an
        auftragDrucken.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AtlProbenahmen probe = getProbe();
                Map params           = getAuftragDruckMap(probe);

                String path = auftragDatei.getText();
                if (path == null || path.equals("")) {
                    frame.showErrorMessage(
                        "Bitte geben Sie den Pfad zum Speichern des PDFs an.",
                        "Pfad zum Speichern fehlt");
                    return;
                }

                params.put("localFile", path);

                try {
                    JRDataSource subdata = AtlProbenahmen.getDataSource(probe);

                    AUIKataster.debugOutput(
                        getClass().getName(),
                        "Fülle Probenahmeauftrag mit " +
                        ((JRMapDataSource) subdata).size() + " Zeilen.");

                    PDFExporter.getInstance().exportAuftrag(
                        params, subdata, path, true);
                    String gedruckt = updateVorgangsstatus(
                        "Probenahmeauftrag gedruckt");

                    probe.setAtlStatus(AtlStatus.getStatus(gedruckt));
                    AtlProbenahmen.updateProbenahme(probe);

                    frame.showInfoMessage(
                        "Der Probenahmeauftrag wurde erfolgreich gedruckt " +
                        "und \nunter '" + path + "' gespeichert.",
                        "Probenahmeauftrag erfolgreich");
                }
                catch (Exception ex) {
                    AUIKataster.errorOutput(
                        "Druck schlug fehlt: " + ex.getMessage(),
                        getClass().getName());

                    frame.showErrorMessage(
                        "Der Druck des Probenahmeauftrags ist fehlgeschlagen." +
                        "\n" + ex.getLocalizedMessage(),
                        "Probenahmeauftrag-Druck fehlgeschlagen");
                }
            }
        });

        // trigger das Erzeugen eines PDFs und einen Druck-Job an
        bescheidDrucken.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AtlProbenahmen probe = getProbe();
                Map params           = getBescheidDruckMap(probe);

                String path = bescheidDatei.getText();
                if (path == null || path.equals("")) {
                    frame.showErrorMessage(
                        "Bitte geben Sie den Pfad zum Speichern des PDFs an.",
                        "Pfad zum Speichern fehlt");
                    return;
                }

                try {
                    PDFExporter.getInstance().exportBescheid(params, path,true);

                    String gedruckt = updateVorgangsstatus(
                        "Bescheid gedruckt");

                    probe.setAtlStatus(AtlStatus.getStatus(gedruckt));
                    AtlProbenahmen.updateProbenahme(probe);

                    frame.showInfoMessage(
                        "Der Gebührenbescheid wurde erfolgreich gedruckt " +
                        "und \nunter '" + path + "' gespeichert.",
                        "Gebührenbescheid erfolgreich");
                }
                catch (Exception ex) {
                    AUIKataster.errorOutput(
                        "Druck schlug fehlt: " + ex.getMessage(),
                        getClass().getName());

                    frame.showErrorMessage(
                        "Der Druck des Gebührenbescheids ist fehlgeschlagen." +
                        "\n" + ex.getLocalizedMessage(),
                        "Gebührenbescheid-Druck fehlgeschlagen");

                }
            }
        });

        bemerkungsArea.setLineWrap(true);
        bemerkungsArea.setWrapStyleWord(true);

        JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bemerkungsScroller.setPreferredSize(new Dimension(0, 50));

        parameterTabelle.setRowHeight(20);

        Action aposRemoveAction = new AbstractAction("Analyseposition löschen") {
            public void actionPerformed(ActionEvent e) {
                int row = parameterTabelle.getSelectedRow();
                if (row != -1 && parameterTabelle.getEditingRow() == -1) {
                    parameterModel.removeRow(row);
                }
            }
        };

        KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(
            KeyEvent.VK_DELETE, 0, false);

        aposRemoveAction.putValue(Action.ACCELERATOR_KEY, deleteKeyStroke);

        parameterTabelle.getInputMap().put(
            deleteKeyStroke, aposRemoveAction.getValue(Action.NAME));

        parameterTabelle.getActionMap().put(
            aposRemoveAction.getValue(Action.NAME), aposRemoveAction);

        JScrollPane parameterScroller = new JScrollPane(
            parameterTabelle,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        FormLayout layout = new FormLayout(
            "70dlu, 50dlu, 30dlu, 5dlu, 30dlu, 5dlu, 60dlu, 5dlu, 60dlu, 50dlu, 5dlu, 50dlu",
            "pref, 8dlu, pref, 8dlu, pref, 8dlu, pref, 8dlu, pref, 8dlu," +
            "pref, 8dlu, pref, 8dlu, pref, 8dlu, pref, 8dlu, pref, 8dlu, " +
            "pref, 16dlu,pref, 8dlu, pref, 16dlu, pref, 8dlu, pref, 16dlu, pref, 8dlu, 150dlu");

        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        int row = 1;
        builder.addSeparator("Probe", cc.xyw(1, row, 12));

        row += 2;
        builder.addLabel("Probenummer:", cc.xyw(1, row, 1));
        builder.add(probenummer, cc.xyw(2, row, 4));

        row += 2;
        builder.add(new JLabel("Vorgangsstatus:"), cc.xyw(1, row, 1));
        builder.add(vorgangsstatus, cc.xyw(2, row, 4));
        builder.addLabel("", cc.xyw(1, row, 1));
        builder.add(statusHoch, cc.xyw(7, row, 1));

        row += 2;
        builder.addLabel("Sachbearbeiter:", cc.xyw(1, row, 1));
        builder.add(sachbearbeiter, cc.xyw(2, row, 4));

        row += 2;
        builder.addLabel("Name des Betriebs:", cc.xyw(1, row, 1));
        builder.add(betrieb, cc.xyw(2, row, 8));

        row += 2;
        builder.addLabel("Entnahmepunkt:", cc.xyw(1, row, 1));
        builder.add(entnahmepunkt, cc.xyw(2, row, 8));

        row += 2;
        builder.add(new JLabel("Datum:"), cc.xyw(1, row, 1));
        builder.add(datum, cc.xyw(2, row, 1));
        builder.addLabel("von:", cc.xyw(3, row, 1, CellConstraints.RIGHT, CellConstraints.CENTER));
        builder.addLabel("", cc.xyw(4, row, 1)); // just to create a small gap
        builder.add(uhrzeitVon, cc.xyw(5, row, 1));
        builder.addLabel("", cc.xyw(6, row, 1));
        builder.addLabel("bis:", cc.xyw(7, row, 1, CellConstraints.RIGHT, CellConstraints.CENTER));
        builder.addLabel("", cc.xyw(8, row, 1)); // just to create a small gap
        builder.add(uhrzeitBis, cc.xyw(9, row, 1));
        builder.addLabel("Fahrtzeit:", cc.xyw(10, row, 1, CellConstraints.RIGHT, CellConstraints.CENTER));
        builder.addLabel("", cc.xyw(11, row, 1)); // just to create a small gap
        builder.add(fahrtzeit, cc.xyw(12, row, 1));

        row += 2;
        builder.addLabel("Bezug:", cc.xyw(1, row, 1));
        builder.add(bezug, cc.xyw(2, row, 4));
        builder.addLabel("", cc.xyw(6, row, 1));
        builder.addLabel("Beteiligte:", cc.xyw(7, row, 1, CellConstraints.RIGHT, CellConstraints.CENTER));
        builder.addLabel("", cc.xyw(8, row, 1)); // just to create a small gap
        builder.add(beteiligte, cc.xyw(9, row, 1));

        row += 2;
        builder.addLabel("Rechnungsdatum:", cc.xyw(1, row, 1));
        builder.add(rechnungsDatum, cc.xyw(2, row, 1));
        builder.addLabel("", cc.xyw(3, row, 1));
        builder.addLabel("Rechnungsbetrag:", cc.xyw(4, row, 4, CellConstraints.RIGHT, CellConstraints.CENTER));
        builder.addLabel("", cc.xyw(8, row, 1)); // just to create a small gap
        builder.add(rechnungsBetrag, cc.xyw(9, row, 1));

        row += 2;
        builder.addLabel("Probenahmeauftrag:", cc.xyw(1, row, 1));
        builder.add(auftragDatei, cc.xyw(2, row, 4));
        builder.addLabel("", cc.xyw(1, row, 1));
        builder.add(auftragWahl, cc.xyw(7, row, 1));
        builder.addLabel("", cc.xyw(8, row, 1));
        builder.add(auftragDrucken, cc.xyw(9, row, 1));

        row += 2;
        builder.addLabel("Gebührenbescheid:", cc.xyw(1, row, 1));
        builder.add(bescheidDatei, cc.xyw(2, row, 4));
        builder.addLabel("", cc.xyw(1, row, 1));
        builder.add(bescheidWahl, cc.xyw(7, row, 1));
        builder.addLabel("", cc.xyw(8, row, 1));
        builder.add(bescheidDrucken, cc.xyw(9, row, 1));

        row += 2;
        builder.addSeparator("ICP", cc.xyw(1, row, 12));

        row += 2;
        builder.addLabel("Einwaage:", cc.xyw(1, row, 1));
        builder.add(icpEinwaageFeld, cc.xyw(2, row, 1));
        builder.addLabel("Datum:", cc.xyw(3, row, 5, CellConstraints.RIGHT, CellConstraints.CENTER));
        builder.addLabel("", cc.xyw(8, row, 1)); // just to create a small gap
        builder.add(icpDatum, cc.xyw(9, row, 1));

        row += 2;
        builder.addSeparator("Bemerkung", cc.xyw(1, row, 12));

        row += 2;
        builder.add(bemerkungsScroller, cc.xyw(1, row, 12));

        row += 2;
        builder.addSeparator("Parameter", cc.xyw(1, row, 12));

        row += 2;
        builder.add(parameterScroller, cc.xyw(1, row, 12));

        return builder.getPanel();
    }


    /**
     * Diese Methode &ouml;ffnet einen Dateidialog. Falls eine Datei ausgew
     */
    protected void openFileChooser(JTextField datei) {
        dateiChooser.showSaveDialog(this);

        File file = dateiChooser.getSelectedFile();

        if (file != null) {
            AUIKataster.debugOutput(
                "Speichere Datei unter: " + file.getAbsolutePath(),
                getClass().getName());

            datei.setText(file.getAbsolutePath());
        }
    }

    protected void fillForm() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);

        this.minimumSize = new Dimension(this.getSize());

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(ComponentEvent event) {
                ProbenEditor.this.setSize(
                        Math.max(minimumSize.width, ProbenEditor.this.getWidth()),
                        Math.max(minimumSize.height, ProbenEditor.this.getHeight()));
            }
        });

        AtlProbenahmen probe     = getProbe();
        BasisBetreiber basisBetr = probe.getBasisBetreiber();

        probenummer.setText(probe.getKennummer());
        sachbearbeiter.setText(probe.getSachbearbeiter());
        entnahmepunkt.setText(
            probe.getAtlProbepkt().getBasisObjekt().getBeschreibung());
        Date entnahmeDatum = probe.getDatumDerEntnahme();
        datum.setDate(entnahmeDatum);
        uhrzeitVon.setText(probe.getUhrzeitbeginn());
        uhrzeitBis.setText(probe.getUhrzeitende());
        fahrtzeit.setText(probe.getFahrtzeit());

        if (basisBetr != null) {
            String name = basisBetr.getBetrname();
            String addr = basisBetr.getBetriebsgrundstueck();

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
            beteiligte.setText(Integer.toString(probe.getAnzahlbeteiligte()));
        }

        if (probe.getBescheid() != null) {
            rechnungsDatum.setDate(probe.getBescheid());
        }

        if (probe.getKosten() != null) {
            rechnungsBetrag.setText(nf.format(probe.getKosten()));
        }

        // TODO Datei

        fillVorgangsstatus();

        if (getProbe().isKlaerschlammProbe()) {
            icpEinwaageFeld.setValue(getProbe().getEinwaage());
        } else {
            icpEinwaageFeld.setEditable(false);
            icpEinwaageFeld.setEnabled(false);
        }
        Date icpDate = getProbe().getDatumIcp();
        icpDatum.setDate(icpDate);

        bemerkungsArea.setText(getProbe().getBemerkung());
    }

    /** Initialisiert die Spalten der Analysepositionen-Tabelle */
    private void initColumns() {
        // Parameter
        TableColumn parameterColumn = parameterTabelle.getColumnModel().getColumn(0);
        parameterColumn.setPreferredWidth(150);

        AtlParameter[] parameter = AtlParameter.getRelevanteParameter();

        parameterBox = new JComboBox(parameter);
        parameterBox.setEditable(false);
        // Funktioniert leider beides nicht, geht evtl.
        // mit ComboBoxCelleditor und Java 1.5
        //parameterBox.setKeySelectionManager(new MyKeySelectionManager());
        //AutoCompletion.enable(parameterBox);
        parameterBox.setBorder(BorderFactory.createEmptyBorder());
        parameterBox.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                parameterBox.showPopup();
            }
        });

        parameterColumn.setCellEditor(new DefaultCellEditor(parameterBox));
        parameterColumn.setCellRenderer(new ComboBoxRenderer());

        // Messwert... (alle Doubles)
        parameterTabelle.setDefaultRenderer(Double.class, new DoubleRenderer());
        parameterTabelle.setDefaultRenderer(KommaDouble.class, new DoubleRenderer());

        // Einheit
        TableColumn einheitenColumn = parameterTabelle.getColumnModel().getColumn(3);
        einheitenColumn.setPreferredWidth(100);

        AtlEinheiten[] einheiten = AtlEinheiten.getEinheiten();

        einheitenBox = new JComboBox(einheiten);
        einheitenBox.setEditable(false);
        einheitenBox.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                einheitenBox.showPopup();
            }
        });
        einheitenBox.setBorder(BorderFactory.createEmptyBorder());

        einheitenColumn.setCellEditor(new DefaultCellEditor(einheitenBox));
        //einheitenColumn.setCellEditor(new ComboBoxCellEditor(einheitenBox));
        einheitenColumn.setCellRenderer(new ComboBoxRenderer());

        // Analyse von
        TableColumn analyseColumn = parameterTabelle.getColumnModel().getColumn(4);
        analyseColumn.setPreferredWidth(100);

        // Normwert
        TableColumn normwertColumn = parameterTabelle.getColumnModel().getColumn(6);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.TRAILING);
        normwertColumn.setCellRenderer(renderer);
    }


    /**
     * Diese Methode setzt den initialen Wert der Combobox des Parameters
     * <i>Vorgangsstatus</i>. Handelt es sich bei der aktuellen Ansicht um eine
     * neue Probenahme, ist der initiale Wert <i>erstellt</i>, ansonsten wird
     * der Status ausgew&auml;hlt, der am {@link AtlProbenahmen} Objekt
     * gespeichert ist.
     */
    protected void fillVorgangsstatus() {
        AtlProbenahmen probe = getProbe();

        if (probe == null || probe.getAtlStatus() == null)
            return;

        AtlStatus status = probe.getAtlStatus();
        AUIKataster.debugOutput(
            "Aktueller Status: " + status.getBezeichnung(),
            getClass().getName());

        String bezeichnung = status.getBezeichnung();

        updateVorgangsstatus(bezeichnung);
    }


    protected String updateVorgangsstatus(String bezeichnung) {
        DefaultComboBoxModel model =
            (DefaultComboBoxModel) vorgangsstatus.getModel();

        Object selection = null;
        int size         = model.getSize();

        bezeichnung = bezeichnung.trim().toLowerCase();

        for (int i = 0; i < size; i++) {
            String tmp = (String) model.getElementAt(i);

            if (bezeichnung.equals(tmp.trim().toLowerCase())) {
                selection = tmp;
                break;
            }
        }

        if (selection != null) {
            model.setSelectedItem(selection);
        }

        return (String) selection;
    }


    protected boolean canSave() {
        return true;
    }

    protected boolean doSave() {
        AUIKataster.debugOutput(
            "Speichere Probenahmedetails",
            getClass().getName());

        AtlProbenahmen probe = getProbe();

        // Vorgangsstatus
        String status = (String) vorgangsstatus.getSelectedItem();
        if (status != null) {
            probe.setAtlStatus(AtlStatus.getStatus(status));
        }

        // Von
        String uhrzeitVonVal = uhrzeitVon.getText();
        if (uhrzeitVonVal != null) {
            probe.setUhrzeitbeginn(uhrzeitVonVal);
        }

        // Bis
        String uhrzeitBisVal = uhrzeitBis.getText();
        if (uhrzeitBisVal != null) {
            probe.setUhrzeitende(uhrzeitBisVal);
        }

        // Datum der Entnahme
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String datumVal      = df.format(datum.getDate());
        String timestring    = (String) datumVal.subSequence(0, 11) + uhrzeitVonVal;
        Date convertedDate   = new Date();
        try {
            convertedDate = df.parse(timestring);
            probe.setDatumDerEntnahme(new Timestamp(convertedDate.getTime()));
        } catch (ParseException e) {
            AUIKataster.errorOutput(
                "Fehler beim Speichern von 'datumDerEntnahme'.",
                getClass().getName());
        }

        // Fahrtzeit
        String fahrtzeitVal = fahrtzeit.getText();
        if (fahrtzeitVal != null) {
            probe.setFahrtzeit(fahrtzeitVal);
        }

        // Beteiligte
        try {
            int anzahl = Integer.parseInt(beteiligte.getText());
            probe.setAnzahlbeteiligte(anzahl);
        }
        catch (NumberFormatException nfe) {
            AUIKataster.errorOutput(
                "Fehler beim Speichern von 'beteiligte'.",
                getClass().getName());
        }

        // Rechnungsdatum
        Date bescheid = rechnungsDatum.getDate();
        if (bescheid != null) {
            probe.setBescheid(bescheid);
        }

        // Rechnungsbetrag
        probe.setKosten(rechnungsBetrag.getDoubleValue());

        String sachbearb = sachbearbeiter.getText();
        if (sachbearbeiter != null) {
            probe.setSachbearbeiter(sachbearb);
        }

        // Kennnummer
        String kennnummer = probenummer.getText();
        if (kennnummer != null) {
            probe.setKennummer(kennnummer);
        }

        // Bemerkung
        String newBemerkung = bemerkungsArea.getText();
        if (newBemerkung != null) {
            newBemerkung = newBemerkung.trim();
        }

        if (!"".equals(newBemerkung)) {
            getProbe().setBemerkung(newBemerkung);
        } else {
            getProbe().setBemerkung(null);
        }

        // Analysepositionen
        Set newPositionen = new HashSet(parameterModel.getList());
        getProbe().getAtlAnalysepositionen().clear();
        getProbe().getAtlAnalysepositionen().addAll(newPositionen);
        // getProbe().setAtlAnalysepositionen(newPositionen);
        AUIKataster.debugOutput("Analysepositionen geändert: "
                + getProbe().getAtlAnalysepositionen(), "ProbenEditor.doSave");

        boolean success;

        if (isNew) {
            success = AtlProbenahmen.saveProbenahme(probe);
        }
        else {
            success = AtlProbenahmen.updateProbenahme(probe);
        }

        return success;
    }


    public Map getAuftragDruckMap(AtlProbenahmen probe) {
        BasisBetreiber betr = probe.getBasisBetreiber();
        AtlProbeart    art  = probe.getAtlProbepkt().getAtlProbeart();

        HashMap params = new HashMap();
        params.put("kennnummer", probenummer.getText());
        params.put("name", betr.toString());
        params.put("art", art.getArt());
        params.put("betriebsgrundstueck", betr.getBetriebsgrundstueck());

        try {
            Integer anzahl = Integer.parseInt(beteiligte.getText());
            params.put("anzahlMitarbeiter", anzahl.toString());
        }
        catch (NumberFormatException nfe) {
            // do nothing
        }

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        params.put("entnahmeDatum", df.format(datum.getDate()));
        params.put("beginn", uhrzeitVon.getText());
        params.put("ende", uhrzeitBis.getText());
        params.put("fahrtzeit", fahrtzeit.getText());
        params.put("entnahmestelle", entnahmepunkt.getText());
        params.put("sachbearbeiterInfo", sachbearbeiter.getText());

        // TODO fill in the correct values if they exist
        params.put("anzahlEntnahmestellen", "");
        params.put("boolEntwaesserungssatzung", "");

        return params;
    }


    public Map getBescheidDruckMap(AtlProbenahmen probe) {
        BasisBetreiber betr = probe.getBasisBetreiber();

        HashMap params = new HashMap();

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        params.put("datum", df.format(new Date()));
        params.put("entnahmedatum", df.format(datum.getDate()));
        params.put("maxdatum", df.format(rechnungsDatum.getDate()));

        try {
            Integer anzahl = Integer.parseInt(beteiligte.getText());
            params.put("probenehmer", anzahl.toString());
        }
        catch (NumberFormatException nfe) {
            params.put("probenehmer", "1");
        }

        try {
            SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
            String beginn = uhrzeitVon.getText();
            String ende   = uhrzeitBis.getText();

            Date beginnDate = tf.parse(beginn);
            Date endeDate   = tf.parse(ende);
            Date dauer      = new Date(beginnDate.getTime()-endeDate.getTime());

            params.put("dauer", tf.format(dauer));
        }
        catch (NumberFormatException nfe) {
            params.put("dauer", "hh:mm");
        }
        catch (ParseException pe) {
            params.put("dauer", "hh:mm");
        }

        params.put("gesamtkosten", rechnungsBetrag.getText());
        params.put("entnahmeort", betr.getBetriebsgrundstueck());

        // TODO
        params.put("kassenzeichen", "53656.100046.5"); // woher?
        params.put("entnahmestellen", "1"); // immer 1 ?
        params.put("kosten", ""); // woher?

        return params;
    }


    protected void doApply() {
        ParameterChooser chooser = new ParameterChooser(frame);
        chooser.setVisible(true);
    }

    public AtlProbenahmen getProbe() {
        return (AtlProbenahmen) getEditedObject();
    }
}

    class ParameterChooser extends OkCancelDialog {
    private JTable ergebnisTabelle;

    private ParameterAuswahlModel parameterAuswahlModel;
    private AtlParameter chosenParameter = null;

    public ParameterChooser(HauptFrame owner) {
        super("Parameter auswählen", owner);

        parameterAuswahlModel = new ParameterAuswahlModel();
        getErgebnisTabelle().setModel(parameterAuswahlModel);
        ergebnisTabelle.setColumnSelectionInterval(0, 0);

        ergebnisTabelle.getColumnModel().getColumn(0).setPreferredWidth(20);
        ergebnisTabelle.getColumnModel().getColumn(1).setPreferredWidth(230);

        setResizable(true);

        parameterAuswahlModel.filterList();
        parameterAuswahlModel.fireTableDataChanged();
    }

    /*
     * (non-Javadoc)
     *
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog#doOk()
     */
    protected void doOk() {
        int row = getErgebnisTabelle().getSelectedRow();
        choose(row);
    }


    private void choose(int row) {
        if (row != -1) {
            chosenParameter = (AtlParameter) parameterAuswahlModel.getObjectAtRow(row);
            parameterAuswahlModel.setValueAt(true, row, 0);
            parameterAuswahlModel.fireTableDataChanged();
            dispose();
        }
    }

    public AtlParameter getChosenParameter() {
        return chosenParameter;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog#buildContentArea
     * ()
     */
    protected JComponent buildContentArea() {
        JScrollPane tabellenScroller = new JScrollPane(getErgebnisTabelle(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        TabAction ta = new TabAction();
        ta.addComp(ergebnisTabelle);

        FormLayout layout = new FormLayout("180dlu:g, 3dlu, min(16dlu;p)", // spalten
                "20dlu, 3dlu, 300dlu:g"); // zeilen
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        builder.add(tabellenScroller, cc.xyw(1, 3, 3));

        return builder.getPanel();
    }

    private JTable getErgebnisTabelle() {
        if (ergebnisTabelle == null) {
            ergebnisTabelle = new SelectTable();

            Action submitAction = new AbstractAction("Auswählen") {
                public void actionPerformed(ActionEvent e) {
                    doOk();
                }
            };
            submitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke
                    .getKeyStroke(KeyEvent.VK_ENTER, 0, false));

            ergebnisTabelle.getInputMap().put(
                    (KeyStroke) submitAction.getValue(Action.ACCELERATOR_KEY),
                    submitAction.getValue(Action.NAME));
            ergebnisTabelle.getActionMap().put(
                    submitAction.getValue(Action.NAME), submitAction);

            ergebnisTabelle.addFocusListener(TableFocusListener.getInstance());
            ergebnisTabelle.addMouseListener(new MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = ergebnisTabelle.rowAtPoint(origin);
                        choose(row);
                    }
                }
            });

        }

        return ergebnisTabelle;
    }

}

class ParameterAuswahlModel extends ListTableModel {

    protected boolean[] selection;

    public ParameterAuswahlModel() {
        super(new String[] { "wählen", "Parameter" }, false);
    }

    public void setList(List newList) {
        super.setList(newList);

        selection = new boolean[newList.size()];
        Arrays.fill(selection, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue
     * (java.lang.Object, int)
     */
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        // we don't need this method
        return null;
    }


    public Object getValueAt(int row, int col) {
        if (rowExists(row)) {
            if (col < columns.length) {

                switch (col) {
                    case 0:
                        return new Boolean(selection[row]);
                    case 1:
                        AtlParameter p = (AtlParameter) getObjectAtRow(row);
                        return p.getBezeichnung();
                    default:
                        return "FEHLER!";
                }
            }
        }

        return null;
    }


    public void setValueAt(Object value, int row, int col) {
        AUIKataster.debugOutput(
            "ParameterAuswahlModel - setValueAt(" + row + ", " + col +")");

        if (rowExists(row)) {
            if (col < columns.length) {
                switch (col) {
                    case 0:
                        boolean cur = selection[row];
                        selection[row] = cur ? false : true;
                }
            }
        }
    }

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class;
        } else {
            return super.getColumnClass(columnIndex);
        }
    }

    public boolean isCellEditable(int row, int col) {
        return col == 0 ? true : false;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#updateList
     * ()
     */
    public void updateList() {
    }

    public void filterList() {
        setList(AtlParameter.getParameter());
        AUIKataster.debugOutput("Suche nach '"
                + getList().size() + " Ergebnisse)",
                "ParameterAuswahlModel.filterList()");
    }
}
