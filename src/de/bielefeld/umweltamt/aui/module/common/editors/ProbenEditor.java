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

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import net.sf.jasperreports.engine.JRDataSource;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameterGruppen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlStatus;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.AutoCompletion;
import de.bielefeld.umweltamt.aui.utils.ComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.CurrencyDouble;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.DoubleRenderer;
import de.bielefeld.umweltamt.aui.utils.JRMapDataSource;
import de.bielefeld.umweltamt.aui.utils.GermanDouble;
import de.bielefeld.umweltamt.aui.utils.KommaDouble;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.MyKeySelectionManager;
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
 * (Momentan ist vieles noch auf Kärschlamm-Proben ausgerichtet)
 * @author David Klotz
 */
public class ProbenEditor extends AbstractApplyEditor {

    public interface OKListener {
        public void onOK(AtlParameter[] params);
    }

    /** Dieser Wert stellt den Preis einer Stunde f&uuml;r Personal- und
     * Sachkosten dar. **/
    public static final double PERSONAL_UND_SACHKOSTEN = 50.31;

    /** Dieser Wert gibt an, wieviele Stellen das Feld in der Datei kasse.txt
     * besitzen muss, welches das Rechnungsdatum und den Rechnungsbetrag
     * enth&auml;lt. **/
    public static final int ZIFFERN_RECHNUNGS_FELD = 19;

    /** Der Dateiname <i>(kasse.txt)</i> der Kassendatei eines
     * Geb&uuml;hrenbescheides. **/
    public static final String KASSE_FILENAME = "kasse.txt";

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
            	addParameter(AtlParameter.getParameter("L10821"));
            	addParameter(AtlParameter.getParameter("B00600"));
            	addParameter(AtlParameter.getParameter("L10111"));
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


        /**
         * Diese Methode fügt dem Model einen neuen Parameter hinzu. Falls
         * dieser jedoch schon enthalten ist, wird er verworfen - es kommen also
         * keine doppelten Parameter vor.
         *
         * @param parameter Ein neuer Parameter.
         */
        public void addParameter(AtlParameter parameter) {
            if (isParameterAlreadyThere(parameter)) {
                AUIKataster.debugOutput(
                    getClass().getName(),
                    "Der Parameter wird bereits geprüft.");
                return;
            }

            AtlAnalyseposition pos = new AtlAnalyseposition(probe);

            pos.setAtlParameter(parameter);
            pos.setAtlEinheiten(AtlEinheiten.getEinheit(
                parameter.getWirdgemessenineinheit()));

            getList().add(pos);
            fireTableDataChanged();
        }


        /**
         * Diese Methode prüft, ob ein Parameter bereits zur Prüfung vorgemerkt
         * ist - also bereits im ParameterModel enthalten ist.
         *
         * @param newParam Ein neu zu probender Parameter.
         *
         * @return true, wenn der Parameter bereits im Model enthalten ist,
         * andernfalls false.
         */
        public boolean isParameterAlreadyThere(AtlParameter newParam) {
            List data = getList();
            int size  = data.size();

            String newOrdnungsbegriff = newParam.getOrdnungsbegriff();

            for (int i = 0; i < size; i++) {
                AtlAnalyseposition pos = (AtlAnalyseposition) data.get(i);
                AtlParameter     param = pos.getAtlParameter();

                if (param.getOrdnungsbegriff().equals(newOrdnungsbegriff)) {
                    return true;
                }
            }

            return false;
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
    private JLabel               rechnungsDatum;
    private JLabel               rechnungsBetrag;
    private JTextArea            bemerkungsArea;

    private JComboBox            sachbearbeiter;
    private JButton              bescheidDrucken;
    private JButton              auftragDrucken;
    private JFileChooser         dateiChooser;
    private JLabel               betrieb;
    private JLabel               entnahmepunkt;
    private DoubleField			 icpEinwaageFeld;
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


    /**
     * Diese Methode erstellt das {@link javax..swing.JPanel} mit {@link
     * javax.swing.JButton}s. Hier werden f&uuml;nf Kn&ouml;pfe hinzugef&uuml;gt,
     * die das Erstellt des Probenahmeauftrags und Geb&uuml;hrenbescheids starten,
     * sowie die Kn&ouml;pfe zum Speichern, Abbrechen und zur Parameterauswahl.
     *
     * @return ein {@link javax.swing.JPanel} mit {@link javax.swing.JButton}s.
     */
    @Override
    protected JPanel createButtonBar() {
        bescheidDrucken  = new JButton("Bescheid erzeugen");
        auftragDrucken   = new JButton("Auftrag erzeugen");

        // triggert das Erzeugen eines PDFs und einen Druck-Job an
        auftragDrucken.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AtlProbenahmen probe = getProbe();

                doSave();

                Map params = getAuftragDruckMap(probe);

                String basePath = SettingsManager.getInstance().getSetting(
                    "auik.probenahme.auftraege");

                String filename = probe.getKennummer();
                filename        = filename.replace(" ", "");
                if (!filename.endsWith(".pdf")) {
                    filename += ".pdf";
                }

                filename = filename.replace("/", "_");
                

                File path = new File(basePath, filename);
                if (path == null) {
                    frame.showErrorMessage(
                        "Kann die Datei nicht speichern, da der Pfad nicht " +
                        "korrekt ist.",
                        "Pfad zum Speichern fehlt");
                    return;
                }

                params.put("localFile", path.getAbsolutePath());

                try {
                    JRDataSource subdata =
                        AtlProbenahmen.getAuftragDataSource(probe);

                    AUIKataster.debugOutput(
                        getClass().getName(),
                        "Fülle Probenahmeauftrag mit " +
                        ((JRMapDataSource) subdata).size() + " Zeilen.");

                    PDFExporter.getInstance().exportAuftrag(
                        params, subdata, path.getAbsolutePath(), true);
                    String gedruckt = updateVorgangsstatus(
                        "Probenahmeauftrag gedruckt");

                    probe.setAtlStatus(AtlStatus.getStatus(gedruckt));
                    AtlProbenahmen.updateProbenahme(probe);

                    frame.showInfoMessage(
                        "Der Probenahmeauftrag wurde erfolgreich unter '" +
                        path.getAbsolutePath() + "' gespeichert.",
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

                String basePath = SettingsManager.getInstance().getSetting(
                    "auik.probenahme.bescheide");

                String filename  = probe.getKennummer();
                String vFilename = filename;

                filename = filename.replace(" ", "");
                if (!filename.endsWith(".pdf")) {
                    filename  += ".pdf";
                    vFilename += "-vfg.pdf";
                }
                else {
                    vFilename = vFilename.substring(0, vFilename.length() - 5);
                    vFilename += "-vfg.pdf";
                }
                filename  = filename.replace("/", "_");
                vFilename = vFilename.replace("/", "_");

                File path = new File(basePath, filename);
                if (path == null) {
                    frame.showErrorMessage(
                        "Kann die Datei nicht speichern, da der Pfad nicht " +
                        "korrekt ist.",
                        "Pfad zum Speichern fehlt");
                    return;
                }

                File vPath = new File(basePath, vFilename);

                try {
                    updateRechnungsdatum(probe);

                    doSave();

                    updateRechnungsbetrag(probe);

                    Map params = getBescheidDruckMap(probe);

                    JRDataSource subdata =
                        AtlProbenahmen.getBescheidDataSource(probe);

                    PDFExporter.getInstance().exportBescheid(
                        params,
                        subdata,
                        PDFExporter.BESCHEID,
                        path.getAbsolutePath(),
                        true);

                    JRDataSource vSubdata =
                        AtlProbenahmen.getBescheidDataSource(probe);

                    PDFExporter.getInstance().exportBescheid(
                        params,
                        vSubdata,
                        PDFExporter.VFG,
                        vPath.getAbsolutePath(),
                        true);

                    AtlStatus currentStatus = getVorgangsstatus();
                    String    currentBez    = currentStatus.getBezeichnung();

                    currentBez = currentBez.trim();

                    if (!currentBez.equals("Bescheid gedruckt")) {
                        // Diese Datei soll nur 1x erzeugt werden. Nachdem der
                        // Status auf "Bescheid gedruckt" erhöht wurde, kann
                        // zwar der Bescheid nochmals gedruckt werden, jedoch
                        // wird keine neue kasse.txt angelegt.
                        AUIKataster.debugOutput(
                            getClass().getName(),
                            "Erstelle kasse.txt für Gebührenbescheid.");
                        try {
                            createKasseFile(path.getAbsolutePath());
                        }
                        catch (ParseException pe) {
                                pe.printStackTrace();

                            frame.showErrorMessage(
                                "Der Druck des Gebührenbescheids ist fehlgeschlagen." +
                                "Die Datei kasse.txt konnte nicht erstellt werden.",
                                "Gebührenbescheid-Druck fehlgeschlagen");

                            return;
                        }
                    }

                    String gedruckt = updateVorgangsstatus(
                        "Bescheid gedruckt");

                    probe.setAtlStatus(AtlStatus.getStatus(gedruckt));

                    AtlProbenahmen.updateProbenahme(probe);

                    frame.showInfoMessage(
                        "Der Gebührenbescheid wurde erfolgreich unter ' " +
                        path.getAbsolutePath() + "' gespeichert.",
                        "Gebührenbescheid erfolgreich");
                }
                catch (Exception ex) {
                    AUIKataster.errorOutput(
                        "Druck schlug fehlt.",
                        getClass().getName());

                    ex.printStackTrace();

                    frame.showErrorMessage(
                        "Der Druck des Gebührenbescheids ist fehlgeschlagen." +
                        "\n" + ex.getLocalizedMessage(),
                        "Gebührenbescheid-Druck fehlgeschlagen");
                }
            }
        });

        return ButtonBarFactory.buildRightAlignedBar(
            new JButton[] {
                button1, button2, button3, auftragDrucken, bescheidDrucken},
            true);
    }


    protected JComponent buildContentArea() {
        NumberFormat     nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        SimpleDateFormat f  = new SimpleDateFormat ("HH:mm");

        entnahmepunkt    = new JLabel();
        datum            = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        rechnungsDatum   = new JLabel();
        uhrzeitVon       = new JFormattedTextField(f);
        uhrzeitBis       = new JFormattedTextField(f);
        fahrtzeit        = new JFormattedTextField(f);
        rechnungsBetrag  = new JLabel();
        bezug            = new JTextField();
        beteiligte       = new JTextField();
        probenummer      = new JTextField();
        vorgangsstatus   = new JComboBox();
        statusHoch       = new JButton("erhöhen");
        sachbearbeiter   = new JComboBox();
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

        BasisSachbearbeiter[] bSachbearbeiter =
            BasisSachbearbeiter.getSachbearbeiter();
        sachbearbeiter.setModel(new DefaultComboBoxModel(bSachbearbeiter));

        statusHoch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AtlStatus current = getVorgangsstatus();
                if (current == null) return;

                AtlStatus next = AtlStatus.getStatus(current.getId()+1);
                if (next == null) return;

                updateVorgangsstatus(next.getBezeichnung());
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
            "pref, 16dlu,pref, 16dlu, pref, 16dlu, pref, 8dlu, 150dlu");

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
        builder.add(rechnungsDatum, cc.xyw(2, row, 2));
        builder.addLabel("Rechnungsbetrag:", cc.xyw(4, row, 4, CellConstraints.RIGHT, CellConstraints.CENTER));
        builder.addLabel("", cc.xyw(8, row, 1)); // just to create a small gap
        builder.add(rechnungsBetrag, cc.xyw(9, row, 1));

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


    /**
     * Diese Methode erzeugt neben dem PDF des Geb&uuml;hrenbescheids eine
     * Datei namens kasse.txt, die Informationen &uuml;ber den Betreiber sowie
     * die H&ouml;he des Rechnungsbetrags und des Rechnungsdatums enth&auml;lt.
     * 
     * Eine Zeile in dieser Datei entspricht einem Bescheid und hat folgendes Format:
     * 
     * Feldname                             Länge
	 * 
	 * Kassenzeichen                          12        ohne Punkte
	 * Ansprechpartner Name                   28
	 * Ansprechpartner Vorname                28
	 * Ansprechpartner Straße, Hausnummer     28
	 * PLZ                                    05
	 * Ort                                    23
	 * Kommentar                              28
	 * Fälligkeitsdatum                       08
	 * Betrag                                 11        ohne Tausender oder Cent Trennzeichen mit führenden Nullen
     *
     * @param bescheid Der Pfad, an dem das PDF gespeichert wurde.
     */
    protected void createKasseFile(String bescheid)
    throws ParseException
    {
        File path  = new File(bescheid).getParentFile();
        File kasse = new File(path, KASSE_FILENAME);

        AtlProbenahmen probe = getProbe();

        BasisBetreiber basisBetr = probe.getBasisBetreiber();

        Date rechnungsdatum = DateUtils.getDateOfBill(probe.getBescheid());
        CurrencyDouble cd   = new CurrencyDouble(
            getRechnungsbetrag(probe), Locale.GERMANY);
        
        String kassenzeichen   = basisBetr.getKassenzeichen().toString();
        kassenzeichen		   = kassenzeichen.replace(".", "");

        String rechnungsbetrag = cd.toString();
        rechnungsbetrag        = rechnungsbetrag.replace("€", "");
        rechnungsbetrag        = rechnungsbetrag.replace(",", "");
        rechnungsbetrag        = rechnungsbetrag.trim();

        String kasseDatum = DateUtils.format(
            rechnungsdatum, DateUtils.FORMAT_KASSE);

        StringBuilder sb = new StringBuilder();

        int fill = ZIFFERN_RECHNUNGS_FELD;
        fill -= kasseDatum.length();
        fill -= rechnungsbetrag.length();

        sb.append(kassenzeichen);
        sb.append(basisBetr.getBetrname());
        
        for (int i = 1; i <= 28 - basisBetr.getBetrname().length(); i++) {
            sb.append(" ");
        }
               
        sb.append(basisBetr.getBetranrede());
        
        int anrede = 0;
        if (basisBetr.getBetranrede() != null){
        	anrede = basisBetr.getBetranrede().length();
        }
        
        for (int i = 1; i <= 28 - anrede; i++) {
            sb.append(" ");
        }

        sb.append(basisBetr.getBetriebsgrundstueck());
        
        for (int i = 1; i <= 28 - basisBetr.getBetriebsgrundstueck().length(); i++) {
            sb.append(" ");
        }
       
        sb.append(basisBetr.getPlz().toString());
        sb.append(basisBetr.getOrt().toString());

        
        for (int i = 1; i <= 23 - basisBetr.getOrt().length(); i++) {
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
            writer =
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(kasse, true),
                        "cp1252"));

            if (exists) {
                writer.newLine();
            }

            writer.write(row, 0, row.length());
            writer.flush();
        }
        catch (IOException ioe) {
            frame.showErrorMessage(ioe.getLocalizedMessage());
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }
            catch (IOException ioe) { /* do nothing */ }
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
        entnahmepunkt.setText(
            probe.getAtlProbepkt().getBasisObjekt().getBeschreibung());
        Date entnahmeDatum = probe.getDatumDerEntnahme();
        datum.setDate(entnahmeDatum);
        
        if (probe.getUhrzeitbeginn() != null) {
        	uhrzeitVon.setText(probe.getUhrzeitbeginn());
        }
        else {
        	uhrzeitVon.setText("00:00");
        }
        
        if (probe.getUhrzeitende() != null) {
        	uhrzeitBis.setText(probe.getUhrzeitende());
        }
        else {
        	uhrzeitBis.setText("00:00");
        }
        
        if (probe.getFahrtzeit() != null) {
        	fahrtzeit.setText(probe.getFahrtzeit());
        }
        else {
        	fahrtzeit.setText("01:00");
        }

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
        else {
        	beteiligte.setText("1");
        }

        Date bescheid = probe.getBescheid();
        if (bescheid != null) {
            rechnungsDatum.setText(
                DateUtils.format(bescheid, DateUtils.FORMAT_DATE));
        }

        // For some reason, there occur a NullPointerException when creating a
        // new AtlProbenahmen.
        try {
            double kosten = getProbe().getKosten();
            CurrencyDouble cd = new CurrencyDouble(kosten, Locale.GERMANY);
            rechnungsBetrag.setText(cd.toString());
        }
        catch (NullPointerException npe) {
            // do nothing
        }

        // TODO Datei

        fillVorgangsstatus();
        fillSachbearbeiter();

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
        //parameterBox.setBorder(BorderFactory.createEmptyBorder());
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


    protected void fillSachbearbeiter() {
        AtlProbenahmen probe = getProbe();

        if (probe == null)
            return;

        String sb = probe.getSachbearbeiter();

        ComboBoxModel model = sachbearbeiter.getModel();
        int          anzahl = model.getSize();

        for (int i = 0; i < anzahl; i++) {
            BasisSachbearbeiter b = (BasisSachbearbeiter) model.getElementAt(i);

            if (b.getKennummer().equals(sb)) {
                sachbearbeiter.setSelectedItem(b);
                return;
            }
            else sachbearbeiter.setSelectedIndex(-1);
        }
    }


    protected AtlStatus getVorgangsstatus() {
        String    selection = (String) vorgangsstatus.getSelectedItem();
        AtlStatus current   = AtlStatus.getStatus(selection);

        return current;
    }


    protected BasisSachbearbeiter getSachbearbeiter() {
        ComboBoxModel model = sachbearbeiter.getModel();

        return (BasisSachbearbeiter) model.getSelectedItem();
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
        if (probe.getUhrzeitbeginn() != null || probe.getUhrzeitbeginn() != "") {
            probe.setUhrzeitbeginn(uhrzeitVonVal);
        }
        else {
        	uhrzeitVonVal = "00:00";
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

        // Sachbearbeiter
        BasisSachbearbeiter b = (BasisSachbearbeiter)
            sachbearbeiter.getSelectedItem();
        if (b != null) {
            probe.setSachbearbeiter(b.getKennummer());;
        }

        // Kennnummer
        String kennnummer = probenummer.getText();
        if (kennnummer != null) {
            probe.setKennummer(kennnummer);
        }

        // ICP
        Date  icpDate = icpDatum.getDate();
        if (icpDate != null) {
            probe.setDatumIcp(icpDate);
        }
        
        Double einwaage = icpEinwaageFeld.getDoubleValue();
        if (einwaage != null) {
            probe.setEinwaage(new Float(einwaage));
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
            isNew = false;
            success = AtlProbenahmen.saveProbenahme(probe);
        }
        else {
            success = AtlProbenahmen.updateProbenahme(probe);
        }

        return success;
    }


    /**
     * Diese Methode liefert die Parameter-Map für den Druck/Export eines
     * Probenahmeauftrages.
     *
     * @return die Variablen für den Probenahmeauftrag als Map.
     */
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
        params.put("bemerkungen", bemerkungsArea.getText());

        BasisSachbearbeiter sb = (BasisSachbearbeiter)
            sachbearbeiter.getSelectedItem();

        StringBuilder info = new StringBuilder();
        if (sb != null) {
            info.append(sb.getName());
            info.append(", ");
            info.append(sb.getGehoertzuarbeitsgr());
            info.append(", ");
            info.append(sb.getTelefon());
        }
        params.put("sachbearbeiterInfo", info.toString());

        // TODO fill in the correct values if they exist
        params.put("anzahlEntnahmestellen", "1");

        return params;
    }


    /**
     * Diese Methode liefert eine Map, mit allen Variablen, die für den
     * Druck/Export des Geb&uuml;hrenbescheid notwendig sind.
     *
     * @return die Variablen des Geb&uuml;hrenbescheids als Map.
     */
    public Map getBescheidDruckMap(AtlProbenahmen probe)
    throws IllegalArgumentException
    {
        BasisBetreiber betr = probe.getBasisBetreiber();
        BasisStandort basisStandort = probe.getBasisObjekt().getBasisStandort();

        HashMap params = new HashMap();

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        String entnahmedatum = DateUtils.format(
            datum.getDate(),
            DateUtils.FORMAT_DATE);
        String entnahmezeit = DateUtils.format(
            datum.getDate(),
            DateUtils.FORMAT_TIME);

        String entnahmezeitpunkt = entnahmedatum + ", " + entnahmezeit + " Uhr";

        Date now = new Date();
        params.put("datum", DateUtils.format(now, DateUtils.FORMAT_DATE));
        params.put("entnahmedatum", entnahmezeitpunkt);
        params.put("entnahmeort", basisStandort.getAdresse());
        params.put("entnahmestelle",
            probe.getAtlProbepkt().getBasisObjekt().getBeschreibung());
        params.put("entnahmestellen", "1");
        params.put("maxdatum", DateUtils.format(
            DateUtils.getDateOfBill(now),
            DateUtils.FORMAT_DATE));
        params.put("kosten", Double.toString(PERSONAL_UND_SACHKOSTEN).replace(".", ","));
        params.put("kassenzeichen", betr.getKassenzeichen());
        params.put("firmaAnrede", betr.getBetranrede());
        params.put("firmaName", betr.getBetrname());
        params.put("firmaNameZus", betr.getBetrnamezus());
        params.put("firmaStrasse", betr.getBetriebsgrundstueck());
        params.put("firmaOrt", betr.getPlz() + " " + betr.getOrt());

        try {
            Integer anzahl = Integer.parseInt(beteiligte.getText());
            params.put("probenehmer", anzahl.toString());
        }
        catch (NumberFormatException nfe) {
            params.put("probenehmer", "1");
        }

        try {
            String beginn = uhrzeitVon.getText();
            String ende   = uhrzeitBis.getText();
            String fahrt  = fahrtzeit.getText();

            Date beginnDate = DateUtils.parse(beginn, DateUtils.FORMAT_TIME);
            Date fahrtDate  = DateUtils.parse(fahrt, DateUtils.FORMAT_TIME);
            Date endeDate   = DateUtils.parse(ende, DateUtils.FORMAT_TIME);
            String mitFahrt	= DateUtils.getAddition(endeDate, fahrtDate);
            Date mitFahrtDate = DateUtils.parse(mitFahrt, DateUtils.FORMAT_TIME);

            String dauer  = DateUtils.getDuration(beginnDate, mitFahrtDate);
            double kosten = Math.round((getSachUndPersonalkosten()) * 100) / 100.0;
            double gesamt = Math.round((kosten + getAnalysekosten(probe)) * 100) / 100.0;

            params.put("personalsachkosten", nf.format(kosten) +" €");
            params.put("analysekosten",
                nf.format(getAnalysekosten(probe)) +" €");
            params.put("gesamtkosten",
            	nf.format(gesamt) + " €");

            params.put("dauer", dauer);
        }
        catch (ParseException pe) {
            params.put("dauer", "hh:mm");
            params.put("gesamtkosten", "xx,xx");
        }

        return params;
    }


    /**
     * Diese Funktion berechnet die Dauer der Probenahme anhand Start- und
     * Endzeit.
     */
    protected double getDauer()
    throws ParseException
    {
        String beginn = uhrzeitVon.getText();
        String ende   = uhrzeitBis.getText();
        String fahrt  = fahrtzeit.getText();

        Date beginnDate 	= DateUtils.parse(beginn, DateUtils.FORMAT_TIME);
        Date fahrtDate  	= DateUtils.parse(fahrt, DateUtils.FORMAT_TIME);
        Date endeDate   	= DateUtils.parse(ende, DateUtils.FORMAT_TIME);
        String mitFahrt		= DateUtils.getAddition(endeDate, fahrtDate);
        Date mitFahrtDate 	= DateUtils.parse(mitFahrt, DateUtils.FORMAT_TIME);

        return DateUtils.getDurationHours(beginnDate, mitFahrtDate);
    }


    /**
     * Diese Funktion liefert die Anzahl der beteiligten Probenehmer.
     */
    protected Integer getAnzahl()
    throws ParseException
    {
        Integer anzahl = new Integer(beteiligte.getText());
    	
        return anzahl;
    }


    /**
     * Diese Methode liefert die Sach- und Personalkosten.
     *
     * @return die Personal- und Sachkosten.
     */
    public double getSachUndPersonalkosten()
    throws ParseException
    {
        return PERSONAL_UND_SACHKOSTEN * getDauer() * getAnzahl();
    }


    /**
     * Diese Methode liefert die Analysekosten der <i>probe</i>.
     *
     * @return die Analysekosten.
     */
    public double getAnalysekosten(AtlProbenahmen probe)
    throws IllegalArgumentException
    {
        List sorted  = AtlProbenahmen.sortAnalysepositionen(probe);
        Map  gruppen = new HashMap();
        double single = 0d;
        double group  = 0d;

        for (int i = 0; i < sorted.size(); i++) {
            AtlAnalyseposition  pos    = (AtlAnalyseposition) sorted.get(i);
            AtlParameter        para   = pos.getAtlParameter();
            AtlParameterGruppen gruppe = para.getAtlParameterGruppe();

            if (gruppe == null) {
                single += para.getPreisfueranalyse();
            }
            else {
                Integer id = gruppe.getId();

                if (gruppen.containsKey(id)) {
                    ((List) gruppen.get(id)).add(para);
                }
                else {
                    List neu = new ArrayList();
                    neu.add(para);

                    gruppen.put(id, neu);
                }
            }
        }

        int anzahlGruppen = gruppen.size();
        if (anzahlGruppen == 0) {
            return single;
        }

        Set      keys = gruppen.keySet();
        Iterator iter = keys.iterator();

        while (iter.hasNext()) {
            Integer id = (Integer) iter.next();
            group     += getGruppierteAnalysekosten(id, (List) gruppen.get(id));
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
     *
     * @param gruppe Die ID der Paramtergruppe
     * @param params Eine Liste, die die Parameter einer Gruppe enth&auml;lt
     *
     * @return den Preis der Parameter.
     *
     * @throws IllegalArgumentException
     */
    public double getGruppierteAnalysekosten(int gruppe, List params)
    throws IllegalArgumentException
    {
        double preis = 0d;

        if (AtlParameterGruppen.isGroupComplete(gruppe, params)) {
            AtlParameterGruppen g = AtlParameterGruppen.getParameterGroup(gruppe);

            if (g == null) {
                System.out.println("No such group with id: " + gruppe);
                return 0;
            }

            preis += g.getPreisfueranalyse();
        }
        else {
            int parameter = params.size();

            for (int i = 0; i < parameter; i++) {
                AtlParameter p = (AtlParameter) params.get(i);

                if (p.getEinzelnBeauftragbar()) {
                    preis += p.getPreisfueranalyse();
                }
                else {
                    String msg =
                        "Parameter " + p.getOrdnungsbegriff() + " ist nicht " +
                        "einzeln prüfbar.";

                    AUIKataster.errorOutput(
                        msg, getClass().getName());

                    throw new IllegalArgumentException(msg);
                }
            }
        }

        return preis;
    }


    /**
     * Dies Funktion liefert den Rechnungsbetrag der Probenhame bestehend aus
     * Sach- und Personalkosten und der Analysekosten.
     *
     * @return den Rechnungsbetrag.
     */
    public double getRechnungsbetrag(AtlProbenahmen probe)
    throws ParseException, IllegalArgumentException
    {
        return getSachUndPersonalkosten() + getAnalysekosten(probe);
    }


    /**
     * Diese Funktion berechnet den Rechnungsbetrag der <i>probe</i>, setzt den
     * Betrag am Objekt und aktualisiert die GUI.
     */
    protected void updateRechnungsbetrag(AtlProbenahmen probe)
    throws ParseException
    {
        double betrag = getRechnungsbetrag(probe);

        probe.setKosten(betrag);

        CurrencyDouble cd = new CurrencyDouble(betrag, Locale.GERMANY);
        rechnungsBetrag.setText(cd.toString());
    }


    /**
     * Diese Funktion berechnet das Rechnugnsdatum, setzt dies am AtlProbenahmen
     * Objekt und aktualisiert die GUI mit dem aktuellen Datum.
     */
    protected void updateRechnungsdatum(AtlProbenahmen probe) {
        Date now   = new Date();
        Date datum = DateUtils.getDateOfBill(now);

        probe.setBescheid(now);

        rechnungsDatum.setText(DateUtils.format(now, DateUtils.FORMAT_DATE));
    }


    protected void doApply() {
        ParameterChooser chooser = new ParameterChooser(frame);
        chooser.addOKListener(new OKListener() {
            public void onOK(AtlParameter[] params) {
                for (AtlParameter param: params) {
                    parameterModel.addParameter(param);
                }
            }
        });
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
    private ProbenEditor.OKListener oklistener;

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

    public void addOKListener(ProbenEditor.OKListener listener) {
        this.oklistener = listener;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog#doOk()
     */
    protected void doOk() {
        AtlParameter[] selected = parameterAuswahlModel.getSelectedParameter();
        fireOKEvent(selected);

        dispose();
    }

    protected void fireOKEvent(AtlParameter[] parameter) {
        if (oklistener == null) {
            return;
        }

        oklistener.onOK(parameter);
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


    public AtlParameter[] getSelectedParameter() {
        List params = new ArrayList();
        List data   = getList();
        int  rows   = getRowCount();

        for (int idx = 0; idx < rows; idx++) {
            if (selection[idx]) {
                params.add(data.get(idx));
            }
        }

        return (AtlParameter[]) params.toArray(new AtlParameter[params.size()]);
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
