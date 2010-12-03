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
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.ComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.DoubleRenderer;
import de.bielefeld.umweltamt.aui.utils.KommaDouble;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
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

    private Dimension minimumSize;

    private JLabel entnahmepunktFeld;
    private TextFieldDateChooser datumFeld;
    private JFormattedTextField uhrzeitFeld;
    private JFormattedTextField icpEinwaageFeld;
    private TextFieldDateChooser icpDatumFeld;
    private JTextArea bemerkungsArea;
    private JTable parameterTabelle;

    private JComboBox parameterBox;
    private JComboBox einheitenBox;

    private ParameterModel parameterModel;
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
        entnahmepunktFeld = new JLabel();
        datumFeld = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);

        SimpleDateFormat formatter = new SimpleDateFormat ("HH:mm");
        uhrzeitFeld = new JFormattedTextField(formatter);

        //if (probe.isKlaerschlammProbe()) {
            icpEinwaageFeld = new DoubleField(0);
        //}

        icpDatumFeld = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);


        bemerkungsArea = new LimitedTextArea(255);
        bemerkungsArea.setLineWrap(true);
        bemerkungsArea.setWrapStyleWord(true);
        JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bemerkungsScroller.setPreferredSize(new Dimension(0, 50));

        parameterTabelle = new SelectTable();
        parameterTabelle.setRowHeight(20);

        Action aposRemoveAction = new AbstractAction("Analyseposition löschen") {
            public void actionPerformed(ActionEvent e) {
                int row = parameterTabelle.getSelectedRow();
                if (row != -1 && parameterTabelle.getEditingRow() == -1) {
                    parameterModel.removeRow(row);
                }
            }
        };
        KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false);
        aposRemoveAction.putValue(Action.ACCELERATOR_KEY, deleteKeyStroke);
        parameterTabelle.getInputMap().put(deleteKeyStroke, aposRemoveAction.getValue(Action.NAME));
        parameterTabelle.getActionMap().put(aposRemoveAction.getValue(Action.NAME), aposRemoveAction);

        JScrollPane parameterScroller = new JScrollPane(parameterTabelle, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //parameterScroller.setPreferredSize(new Dimension(600, 150));

        FormLayout layout = new FormLayout(
                "r:p, 3dlu, max(70dlu;p), 5dlu, r:p, 3dlu, max(70dlu;p), 0dlu:grow");    // Spalten, Zeilen werden dynamisch erzeugt
        layout.setColumnGroups(new int[][]{{1,5}, {3,7}});
        DefaultFormBuilder builder = new DefaultFormBuilder(layout);

        builder.appendSeparator("Probe");//+ probe.getKennummer() +" - "+probe.getAtlProbepkt().getAtlProbeart());
        builder.append("Entnahmepunkt:", entnahmepunktFeld, 6);
        builder.nextLine();
        builder.append("Datum:", datumFeld);
        builder.append("Uhrzeit:", uhrzeitFeld);
        builder.nextLine();

        builder.appendSeparator("ICP");
        //if (probe.isKlaerschlammProbe()) {
            builder.append("Einwaage:", icpEinwaageFeld);
        //}
        builder.append("Datum:", icpDatumFeld);
        builder.nextLine();

        builder.appendSeparator("Bemerkung");
        builder.append(bemerkungsScroller, 7);
        builder.nextLine();

        builder.appendSeparator("Parameter");
        builder.appendRow("3dlu");
        builder.appendRow("fill:60dlu:grow");
        builder.nextLine(2);
        builder.append(parameterScroller, 8);

        return builder.getPanel();
    }

    protected void fillForm() {
        this.minimumSize = new Dimension(this.getSize());

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(ComponentEvent event) {
                ProbenEditor.this.setSize(
                        Math.max(minimumSize.width, ProbenEditor.this.getWidth()),
                        Math.max(minimumSize.height, ProbenEditor.this.getHeight()));
            }
        });

//        parameterModel = new ParameterModel(getProbe(), isNew);
//        parameterTabelle.setModel(parameterModel);

//        initColumns();

        entnahmepunktFeld.setText(getProbe().getAtlProbepkt().getBasisObjekt().getBasisStandort() + ", "
                                + getProbe().getAtlProbepkt().getBasisObjekt().getBasisBetreiber());
        Date entnahmeDatum = getProbe().getDatumDerEntnahme();
        datumFeld.setDate(entnahmeDatum);
        uhrzeitFeld.setText(entnahmeDatum.toString().substring(11, 16));

        if (getProbe().isKlaerschlammProbe()) {
            icpEinwaageFeld.setValue(getProbe().getEinwaage());
        } else {
            icpEinwaageFeld.setEditable(false);
            icpEinwaageFeld.setEnabled(false);
        }
        Date icpDatum = getProbe().getDatumIcp();
        icpDatumFeld.setDate(icpDatum);

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

    protected boolean canSave() {
        return true;
    }

    protected boolean doSave() {
        // Datum der Entnahme
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String uhrzeit = uhrzeitFeld.getText();
        String datum = df.format(datumFeld.getDate());
        String timestring = (String) datum.subSequence(0, 11) + uhrzeit;

        Date convertedDate = new Date();
        try {
            convertedDate = df.parse(timestring);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Timestamp newDate = new Timestamp(convertedDate.getTime());
        if (newDate != null) {
            getProbe().setDatumDerEntnahme(newDate);
        }

        if (getProbe().isKlaerschlammProbe()) {
            // ICP-Einwaage
            if (icpEinwaageFeld.getValue() != null) {
                Float newEinwaage = ((DoubleField)icpEinwaageFeld).getFloatValue();
                getProbe().setEinwaage(newEinwaage);
            }
        }

        // ICP-Datum
        Date newIcpDate = icpDatumFeld.getDate();
        if (newIcpDate != null) {
            getProbe().setDatumIcp(newIcpDate);
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
            success = AtlProbenahmen.saveProbenahme(getProbe());
        } else {
            success = AtlProbenahmen.updateProbenahme(getProbe());
        }

        return success;
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
    public ParameterAuswahlModel() {
        super(new String[] { "wählen", "Parameter" }, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue
     * (java.lang.Object, int)
     */
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        AtlParameter para = (AtlParameter) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = new Boolean(false);
            break;
        case 1:
            tmp = para.getBezeichnung();
            break;

        default:
            tmp = "FEHLER!";
            break;
        }

        return tmp;
    }

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class;
        } else {
            return super.getColumnClass(columnIndex);
        }
    }

    public boolean isCellEditable(EventObject anEvent) {

            return true;
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
