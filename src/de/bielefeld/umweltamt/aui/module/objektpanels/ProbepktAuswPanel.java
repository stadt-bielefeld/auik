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
 * Created on 19.04.2005 by u633z
 */


package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.SearchBox;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.charts.APosDataItem;
import de.bielefeld.umweltamt.aui.utils.charts.ChartDataSets;
import de.bielefeld.umweltamt.aui.utils.charts.Charts;

/**
 * Das "Auswertungs"-Tab des BasisObjektBearbeiten-Moduls
 * @author Sebastian Geller
 */
public class ProbepktAuswPanel extends JPanel {
    private static final long serialVersionUID =
        -6939004829737520612L;

    /** Logging */
    private static final AuikLogger log =
        AuikLogger.getLogger();

    private JDateChooser vonDateChooser;
    private JDateChooser bisDateChooser;
    private JComboBox<String> analyseVonBox;
    private String name;
    private BasisObjektBearbeiten hauptModul;

    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    // Widgets
    private JList<Parameter> leftList;
    private JList<Parameter> rightList;
    private JButton submitButton;
    private JButton leftDeleteButton;
    private JButton rightDeleteButton;
    private TimeSeriesCollection dataSet1;
    private TimeSeriesCollection dataSet2;
    private ActionListener rlButtonListener;
    private Einheiten[] einheiten;

    // Daten
    private Messstelle pkt;
    private JComboBox<Parameter> parameterBox;
    private JComboBox<Einheiten> leftEinheitenBox;
    private JComboBox<Einheiten> rightEinheitenBox;
    private HauptFrame frame;

    public ProbepktAuswPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Auswertung";
        this.einheiten = DatabaseQuery.getEinheiten();
        this.hauptModul = hauptModul;

        // ── Haupt-Layout ──────────────────────────────────
        this.setLayout(new BorderLayout());

        JPanel innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 5, 3, 5);

        // ── ZEILE 0: Zeitraum (links) + Analyse von (rechts)
        // Separator Zeitraum
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 3; gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Zeitraum"), gbc);

        // Separator Analyse von
        gbc.gridx = 3; gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        innerPanel.add(createSeparator("Analyse von..."), gbc);

     // ── ZEILE 1: Von + Bis + AnalyseVonBox ───────────
     JPanel vonBisPanel = new JPanel(
         new FlowLayout(FlowLayout.LEFT, 5, 0));
     vonBisPanel.add(new JLabel("Von"));
     getVonDateChooser().setPreferredSize(
         new java.awt.Dimension(110,
             getVonDateChooser().getPreferredSize().height));
     vonBisPanel.add(getVonDateChooser());
     vonBisPanel.add(new JLabel("Bis"));
     getBisDateChooser().setPreferredSize(
         new java.awt.Dimension(110,
             getBisDateChooser().getPreferredSize().height));
     vonBisPanel.add(getBisDateChooser());

     gbc.gridx = 0; gbc.gridy = 1;
     gbc.gridwidth = 3; gbc.weightx = 0.7;
     gbc.fill = GridBagConstraints.NONE;
     gbc.anchor = GridBagConstraints.WEST;
     innerPanel.add(vonBisPanel, gbc);

     // AnalyseVonBox
     gbc.gridx = 3; gbc.gridwidth = 1;
     gbc.weightx = 0.3;
     gbc.fill = GridBagConstraints.HORIZONTAL;
     gbc.anchor = GridBagConstraints.WEST;
     getAnalyseVonBox().setPreferredSize(
         new java.awt.Dimension(200,
             getAnalyseVonBox().getPreferredSize().height));
     innerPanel.add(getAnalyseVonBox(), gbc);






        // ── ZEILE 2: Separator Parameter ─────────────────
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 4; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Parameter"), gbc);

        // ── ZEILE 3: Labels ───────────────────────────────
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1; gbc.weightx = 0.35;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        innerPanel.add(new JLabel("Erste Y-Achse"), gbc);

        gbc.gridx = 3; gbc.weightx = 0.35;
        innerPanel.add(new JLabel("Zweite Y-Achse"), gbc);

        // ── ZEILE 4+: Listen + Buttons ────────────────────
        // Linke Liste
        JScrollPane leftScroller = new JScrollPane(
            getLeftList());
        leftScroller.setPreferredSize(
            new java.awt.Dimension(200, 220));

        // Rechte Liste
        JScrollPane rightScroller = new JScrollPane(
            getRightList());
        rightScroller.setPreferredSize(
            new java.awt.Dimension(200, 220));

        // Mittleres Panel mit Buttons + Labels
        JPanel middlePanel = new JPanel(
            new GridBagLayout());
        GridBagConstraints mc = new GridBagConstraints();
        mc.insets = new Insets(2, 3, 2, 3);

        String[] paramIDs = {
            DatabaseConstants.ATL_PARAMETER_ID_CADMIUM,
            DatabaseConstants.ATL_PARAMETER_ID_CHROM,
            DatabaseConstants.ATL_PARAMETER_ID_KUPFER,
            DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER,
            DatabaseConstants.ATL_PARAMETER_ID_NICKEL,
            DatabaseConstants.ATL_PARAMETER_ID_BLEI,
            DatabaseConstants.ATL_PARAMETER_ID_ZINK
        };

        int mRow = 0;
        for (String paramID : paramIDs) {
            mc.gridx = 0; mc.gridy = mRow;
            mc.weightx = 0;
            mc.fill = GridBagConstraints.NONE;
            middlePanel.add(
                createRLButton(true, paramID), mc);

            mc.gridx = 1; mc.weightx = 1.0;
            mc.fill = GridBagConstraints.HORIZONTAL;
            middlePanel.add(new JLabel(
                Parameter.findById(paramID)
                    .getBezeichnung(),
                JLabel.CENTER), mc);

            mc.gridx = 2; mc.weightx = 0;
            mc.fill = GridBagConstraints.NONE;
            middlePanel.add(
                createRLButton(false, paramID), mc);
            mRow++;
        }

        // Parameter Box Zeile
        mc.gridx = 0; mc.gridy = mRow;
        mc.weightx = 0;
        mc.fill = GridBagConstraints.NONE;
        middlePanel.add(
            createRLButton(true, "box"), mc);

        mc.gridx = 1; mc.weightx = 1.0;
        mc.fill = GridBagConstraints.HORIZONTAL;
        getParameterBox().setPreferredSize(
            new java.awt.Dimension(180,
                getParameterBox().getPreferredSize().height));
        middlePanel.add(getParameterBox(), mc);

        mc.gridx = 2; mc.weightx = 0;
        mc.fill = GridBagConstraints.NONE;
        middlePanel.add(
            createRLButton(false, "box"), mc);

        // Drei Spalten ins innerPanel
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 1; gbc.weightx = 0.35;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        innerPanel.add(leftScroller, gbc);

        gbc.gridx = 1; gbc.gridwidth = 2;
        gbc.weightx = 0.3; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        innerPanel.add(middlePanel, gbc);

        gbc.gridx = 3; gbc.gridwidth = 1;
        gbc.weightx = 0.35; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        innerPanel.add(rightScroller, gbc);

        // ── ZEILE 5: Einheiten ────────────────────────────
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        getLeftEinheitenBox().setPreferredSize(
            new java.awt.Dimension(120,
                getLeftEinheitenBox()
                    .getPreferredSize().height));
        innerPanel.add(getLeftEinheitenBox(), gbc);

        gbc.gridx = 1; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        innerPanel.add(
            new JLabel("<  Einheit  >", JLabel.CENTER),
            gbc);

        gbc.gridx = 3; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        getRightEinheitenBox().setPreferredSize(
            new java.awt.Dimension(120,
                getRightEinheitenBox()
                    .getPreferredSize().height));
        innerPanel.add(getRightEinheitenBox(), gbc);

        // ── ZEILE 6: Buttons ──────────────────────────────
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        innerPanel.add(getLeftDeleteButton(), gbc);

        gbc.gridx = 1; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        innerPanel.add(getSubmitButton(), gbc);

        gbc.gridx = 3; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        innerPanel.add(getRightDeleteButton(), gbc);

        // ── Wrapper ───────────────────────────────────────
        JPanel wrapperPanel = new JPanel(
            new BorderLayout());
        wrapperPanel.add(innerPanel, BorderLayout.NORTH);
        this.add(wrapperPanel, BorderLayout.CENTER);
    }

    // ── createSeparator() ─────────────────────────────────
    private JPanel createSeparator(String title) {
        JPanel sepPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel lbl = new JLabel(title);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
        sepPanel.add(lbl, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 0, 0);
        sepPanel.add(
            new JSeparator(SwingConstants.HORIZONTAL), gbc);
        return sepPanel;
    }

    private class AuswertungsDialog extends JDialog {
        private static final long serialVersionUID =
            -4652005913711822389L;

        private class DialogListener extends WindowAdapter
            implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() ==
                    AuswertungsDialog.this.abbrechenButton)
                    doAbbrechen();
                else if (e.getSource() ==
                    AuswertungsDialog.this.speichernButton)
                    doSpeichern();
                else if (e.getSource() ==
                    AuswertungsDialog.this.printButton)
                    doPrint();
            }
            @Override
            public void windowClosing(WindowEvent e) {
                doAbbrechen();
            }
        }

        private class ExportTableModel
            extends AbstractTableModel {
            private static final long serialVersionUID =
                -7115812453677348579L;
            private TimeSeriesCollection col1, col2;
            private List<Minute> dateList;

            public ExportTableModel(
                TimeSeriesCollection col1,
                TimeSeriesCollection col2) {
                this.col1 = col1;
                this.col2 = col2;
                this.dateList = new ArrayList<Minute>();
                initializeData();
            }

            private void initializeData() {
                TimeSeries series;
                APosDataItem item;
                for (int i = 0;
                    i < this.col1.getSeriesCount(); i++) {
                    series = this.col1.getSeries(i);
                    for (int j = 0;
                        j < series.getItemCount(); j++) {
                        item = (APosDataItem)
                            series.getDataItem(j);
                        int n = this.dateList.size();
                        boolean add = true;
                        if (n == 0) {
                            this.dateList.add(
                                item.getMinute());
                        } else {
                            for (int k = 0; k < n; k++) {
                                if (this.dateList.get(k)
                                    .toString().equals(
                                    item.getMinute()
                                        .toString()))
                                    add = false;
                            }
                        }
                        if (add && n > 0)
                            this.dateList.add(
                                item.getMinute());
                    }
                }
                if (this.col2 != null) {
                    for (int i = 0;
                        i < this.col2.getSeriesCount();
                        i++) {
                        series = this.col2.getSeries(i);
                        for (int j = 0;
                            j < series.getItemCount();
                            j++) {
                            item = (APosDataItem)
                                series.getDataItem(j);
                            int n = this.dateList.size();
                            boolean add = true;
                            if (n == 0) {
                                this.dateList.add(
                                    item.getMinute());
                            } else {
                                for (int k = 0; k < n;
                                    k++) {
                                    if (this.dateList
                                        .get(k).toString()
                                        .equals(item
                                        .getMinute()
                                        .toString()))
                                        add = false;
                                }
                            }
                            if (add && n > 0)
                                this.dateList.add(
                                    item.getMinute());
                        }
                    }
                }
                this.dateList.sort(null);
            }

            @Override
            public int getColumnCount() {
                return this.col1.getSeriesCount()
                    + ((this.col2 != null)
                        ? this.col2.getSeriesCount() : 0)
                    + 1;
            }

            @Override
            public int getRowCount() {
                return this.dateList.size();
            }

            @Override
            public Object getValueAt(int rowIndex,
                int columnIndex) {
                String tmp = "!OOB!";
                NumberFormat kommaFormat =
                    NumberFormat.getNumberInstance();
                kommaFormat.setGroupingUsed(false);
                kommaFormat.setMinimumFractionDigits(1);
                int seriesIndex = columnIndex - 1;
                int series2Index = seriesIndex
                    - this.col1.getSeriesCount();
                Minute min = (Minute)
                    this.dateList.get(rowIndex);
                if (columnIndex == 0) {
                    Date date = new Date(
                        min.getFirstMillisecond());
                    tmp = AuikUtils
                        .getDayTimeStringFromDate(date);
                } else {
                    APosDataItem item = null;
                    if (seriesIndex <
                        this.col1.getSeriesCount()) {
                        item = (APosDataItem) this.col1
                            .getSeries(seriesIndex)
                            .getDataItem(min);
                    } else if (this.col2 != null) {
                        item = (APosDataItem) this.col2
                            .getSeries(series2Index)
                            .getDataItem(min);
                    }
                    tmp = (item != null)
                        ? kommaFormat.format(
                            item.getValue()) : "";
                }
                return tmp;
            }

            @Override
            public Class<?> getColumnClass(int col) {
                return String.class;
            }

            @Override
            public String getColumnName(int column) {
                String tmp = "!OOB!";
                int seriesIndex = column - 1;
                int series2Index = seriesIndex
                    - this.col1.getSeriesCount();
                if (column == 0) {
                    tmp = "Datum";
                } else {
                    if (seriesIndex <
                        this.col1.getSeriesCount()) {
                        tmp = this.col1.getSeries(
                            seriesIndex).getKey().toString()
                            + ", "
                            + this.col1.getSeries(
                                seriesIndex)
                                .getRangeDescription();
                    } else if (this.col2 != null) {
                        tmp = this.col2.getSeries(
                            series2Index).getKey().toString()
                            + ", "
                            + this.col2.getSeries(
                                series2Index)
                                .getRangeDescription();
                    }
                }
                return tmp;
            }
        }

        private JButton speichernButton;
        private JButton printButton;
        private JButton abbrechenButton;
        private JTable exportTable;
        private JPopupMenu tabellenMenu;
        private JTabbedPane tabbedPane;
        private ChartPanel chartPanel;
        private DialogListener listener;
        private String title;
        private TimeSeriesCollection leftDataset;
        private TimeSeriesCollection rightDataset;
        private HauptFrame owner;

        public AuswertungsDialog(String title,
            TimeSeriesCollection leftDataset,
            TimeSeriesCollection rightDataset,
            HauptFrame owner) {
            super(owner, title + "-Auswertung", true);
            this.owner = owner;
            this.title = title;
            this.leftDataset = leftDataset;
            this.rightDataset = rightDataset;
            this.listener = new DialogListener();
            this.setDefaultCloseOperation(
                JDialog.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(this.listener);

            this.speichernButton =
                new JButton("Speichern");
            this.speichernButton.addActionListener(
                this.listener);
            this.printButton = new JButton("Drucken");
            this.printButton.addActionListener(
                this.listener);
            this.abbrechenButton =
                new JButton("Schließen");
            this.abbrechenButton.addActionListener(
                this.listener);

            JPanel tmp = new JPanel(
                new BorderLayout(0, 7));
            tmp.add(initializeContent(),
                BorderLayout.CENTER);
            JComponent buttonBar =
                ComponentFactory.buildOKCancelApplyBar(
                this.speichernButton,
                this.printButton,
                this.abbrechenButton);
            tmp.add(buttonBar, BorderLayout.SOUTH);
            this.setContentPane(tmp);
            this.pack();
            this.setLocationRelativeTo(owner);
        }

        private JComponent initializeContent() {
            this.tabbedPane = new JTabbedPane();
            this.tabbedPane.addTab("Diagramm",
                createDiagrammPanel());
            this.tabbedPane.addTab("Tabelle",
                createTabellenPanel());
            return this.tabbedPane;
        }

        private JPanel createDiagrammPanel() {
            JFreeChart chart;
            if (this.rightDataset == null) {
                chart = Charts
                    .createDefaultTimeSeriesChart(
                    this.title, this.leftDataset);
            } else {
                chart = Charts
                    .createDefaultTimeSeriesChart(
                    this.title, this.leftDataset,
                    this.rightDataset);
            }
            this.chartPanel =
                new ChartPanel(chart, false);
            return this.chartPanel;
        }

        private JComponent createTabellenPanel() {
            this.exportTable = new JTable(
                new ExportTableModel(
                    this.leftDataset,
                    this.rightDataset));
            this.exportTable.setBorder(
                BorderFactory.createBevelBorder(
                    BevelBorder.RAISED));
            this.exportTable.setColumnSelectionAllowed(
                true);
            this.exportTable.setRowSelectionAllowed(true);
            this.exportTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF);
            this.exportTable.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        showTabellenPopup(e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        showTabellenPopup(e);
                    }
                });

            DefaultTableCellRenderer zentriert =
                new DefaultTableCellRenderer();
            zentriert.setHorizontalAlignment(
                DefaultTableCellRenderer.CENTER);
            DefaultTableCellRenderer rechts =
                new DefaultTableCellRenderer();
            rechts.setHorizontalAlignment(
                DefaultTableCellRenderer.RIGHT);

            TableColumn column = null;
            for (int i = 0;
                i < this.exportTable.getColumnCount();
                i++) {
                column = this.exportTable
                    .getColumnModel().getColumn(i);
                if (i == 0) {
                    column.setCellRenderer(zentriert);
                    column.setPreferredWidth(100);
                } else {
                    column.setCellRenderer(rechts);
                    column.setPreferredWidth(90);
                }
            }

            JScrollPane tabellenScroller =
                new JScrollPane(this.exportTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            return tabellenScroller;
        }

        public void saveTabelle() {
            File exportDatei;
            String[] csv = new String[]{"csv"};
            this.owner = ProbepktAuswPanel.this
                .hauptModul.getFrame();
            exportDatei = this.owner.saveFile(csv);
            if (exportDatei != null) {
                String ext =
                    AuikUtils.getExtension(exportDatei);
                if (ext == null) {
                    String newExt = exportDatei.getName()
                        .endsWith(".") ? "csv" : ".csv";
                    exportDatei = new File(
                        exportDatei.getParent(),
                        exportDatei.getName() + newExt);
                }
                boolean doIt = false;
                if (exportDatei.exists()) {
                    boolean answer =
                        GUIManager.getInstance()
                        .showQuestion(
                        "Soll die vorhandene Datei "
                        + exportDatei.getName()
                        + " wirklich überschrieben"
                        + " werden?",
                        "Datei bereits vorhanden!");
                    if (answer && exportDatei.canWrite())
                        doIt = true;
                } else if (exportDatei.getParentFile()
                    .canWrite()) {
                    doIt = true;
                }
                if (doIt) {
                    if (AuikUtils.exportTableDataToCVS(
                        this.exportTable, exportDatei)) {
                        GUIManager.getInstance()
                            .showInfoMessage(
                            "Speichern der CSV-Datei"
                            + " erfolgreich!",
                            "Speichern erfolgreich");
                    } else {
                        GUIManager.getInstance()
                            .showErrorMessage(
                            "Beim Speichern der Datei '"
                            + exportDatei
                            + "' trat ein Fehler auf!");
                    }
                }
            }
        }

        private void printTable() {
            try {
                exportTable.print();
            } catch (PrinterException e) {
                GUIManager.getInstance().setErrorStatus(
                    "Beim Drucken der Tabelle trat"
                    + " ein Fehler auf!");
                e.printStackTrace();
            }
        }

        private void showTabellenPopup(MouseEvent e) {
            if (this.tabellenMenu == null) {
                this.tabellenMenu =
                    new JPopupMenu("Tabelle");
                this.tabellenMenu.add(new JMenuItem(
                    new AbstractAction("Speichern") {
                    private static final long
                        serialVersionUID =
                            -6201611660887956188L;
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        saveTabelle();
                    }
                }));
                this.tabellenMenu.add(new JMenuItem(
                    new AbstractAction("Drucken") {
                    private static final long
                        serialVersionUID = -553069817163L;
                    @Override
                    public void actionPerformed(
                        ActionEvent arg0) {
                        printTable();
                    }
                }));
            }
            if (e.isPopupTrigger()) {
                Point origin = e.getPoint();
                int row = this.exportTable
                    .rowAtPoint(origin);
                int col = this.exportTable
                    .columnAtPoint(origin);
                if (row != -1) {
                    this.exportTable
                        .setRowSelectionInterval(row, row);
                    this.exportTable
                        .setColumnSelectionInterval(
                            col, col);
                    this.tabellenMenu.show(
                        e.getComponent(),
                        e.getX(), e.getY());
                }
            }
        }

        public void doAbbrechen() { this.dispose(); }

        public void doSpeichern() {
            if (this.tabbedPane.getSelectedIndex() == 0) {
                try {
                    this.chartPanel.doSaveAs();
                } catch (IOException e) {
                    log.debug("Konnte Datei nicht"
                        + " speichern!");
                }
            } else {
                saveTabelle();
            }
        }

        public void doPrint() {
            switch (this.tabbedPane.getSelectedIndex()) {
            case 0:
                this.chartPanel.createChartPrintJob();
                break;
            case 1:
                printTable();
                break;
            }
        }
    }

    // ── Getter Methoden ────────────────────────────────────
    private JComboBox<Parameter> getParameterBox() {
        if (this.parameterBox == null)
            this.parameterBox = new SearchBox<>(
                DatabaseQuery.getAllParameterAsArray());
        return this.parameterBox;
    }

    private JButton getSubmitButton() {
        if (this.submitButton == null) {
            this.submitButton = new JButton("Abschicken");
            this.submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        if (getRightList().getModel()
                            .getSize() == 0)
                            showResultOneAxis(LEFT);
                        else if (getLeftList().getModel()
                            .getSize() == 0)
                            showResultOneAxis(RIGHT);
                        else
                            showResultDualAxis();
                    }
                });
        }
        return this.submitButton;
    }

    private JComboBox<Einheiten> getLeftEinheitenBox() {
        if (this.leftEinheitenBox == null) {
            this.leftEinheitenBox =
                new SearchBox<>(this.einheiten);
            this.leftEinheitenBox.setSelectedItem(
                DatabaseConstants.ATL_EINHEIT_MG_L);
        }
        return this.leftEinheitenBox;
    }

    private JComboBox<Einheiten> getRightEinheitenBox() {
        if (this.rightEinheitenBox == null) {
            this.rightEinheitenBox =
                new SearchBox<>(this.einheiten);
            this.rightEinheitenBox.setSelectedItem(
                DatabaseConstants.ATL_EINHEIT_MG_L);
        }
        return this.rightEinheitenBox;
    }

    public void showResultOneAxis(final String axis) {
        SwingWorkerVariant worker =
            new SwingWorkerVariant(getSubmitButton()) {
            @Override
            protected void doNonUILogic()
                throws RuntimeException {
                ProbepktAuswPanel.this.dataSet1 =
                    createDataset(axis);
            }
            @Override
            protected void doUIUpdateLogic()
                throws RuntimeException {
                if (ProbepktAuswPanel.this.dataSet1
                    .getSeriesCount() > 0) {
                    AuswertungsDialog dialog =
                        new AuswertungsDialog(
                        ProbepktAuswPanel.this.pkt
                            .getObjekt().getBetreiberid()
                            .getName(),
                        ProbepktAuswPanel.this.dataSet1,
                        null,
                        ProbepktAuswPanel.this.frame);
                    dialog.setVisible(true);
                }
            }
        };
        worker.start();
    }

    public void showResultDualAxis() {
        SwingWorkerVariant worker =
            new SwingWorkerVariant(getSubmitButton()) {
            private int seriesCount = 0;
            @Override
            protected void doNonUILogic()
                throws RuntimeException {
                ProbepktAuswPanel.this.dataSet1 =
                    createDataset(LEFT);
                ProbepktAuswPanel.this.dataSet2 =
                    createDataset(RIGHT);
                this.seriesCount =
                    ProbepktAuswPanel.this.dataSet1
                        .getSeriesCount()
                    + ProbepktAuswPanel.this.dataSet2
                        .getSeriesCount();
            }
            @Override
            protected void doUIUpdateLogic()
                throws RuntimeException {
                if (this.seriesCount > 0) {
                    AuswertungsDialog dialog =
                        new AuswertungsDialog(
                        ProbepktAuswPanel.this.pkt
                            .getObjekt().getBetreiberid()
                            .getName(),
                        ProbepktAuswPanel.this.dataSet1,
                        ProbepktAuswPanel.this.dataSet2,
                        ProbepktAuswPanel.this.frame);
                    dialog.setVisible(true);
                }
            }
        };
        worker.start();
    }

    private TimeSeriesCollection createDataset(
        String axis) {
        TimeSeriesCollection col =
            new TimeSeriesCollection();
        Einheiten einheit;
        JList<Parameter> paramList;
        if (axis.equals(LEFT)) {
            einheit = (Einheiten)
                getLeftEinheitenBox().getSelectedItem();
            paramList = getLeftList();
        } else {
            einheit = (Einheiten)
                getRightEinheitenBox().getSelectedItem();
            paramList = getRightList();
        }
        Date vonDate = getVonDateChooser().getDate();
        Date bisDate = getBisDateChooser().getDate();
        String analyeVon = "";
        if (this.analyseVonBox.getSelectedItem() != null)
            analyeVon = this.analyseVonBox
                .getSelectedItem().toString();
        this.pkt = Messstelle.findByObjektId(
            this.hauptModul.getObjekt().getId());
        createSeries(paramList, this.pkt, einheit,
            vonDate, bisDate, analyeVon, col);
        return col;
    }

    private void createSeries(JList<Parameter> paramList,
        Messstelle pkt, Einheiten einheit,
        Date vonDate, Date bisDate,
        String analyseVon, TimeSeriesCollection col) {
        if (pkt != null) {
            for (int i = 0;
                i < paramList.getModel().getSize(); i++) {
                Parameter param = (Parameter)
                    paramList.getModel().getElementAt(i);
                List<Analyseposition> list =
                    DatabaseQuery
                        .getAnalysepositionFromView(
                        param, einheit, pkt,
                        vonDate, bisDate, analyseVon);
                TimeSeries series =
                    ChartDataSets
                        .createAnalysePositionenSeries(
                        list, param + " ",
                        einheit.toString());
                col.addSeries(series);
            }
        }
    }

    private JDateChooser getVonDateChooser() {
        if (this.vonDateChooser == null)
            this.vonDateChooser = new JDateChooser(
                null, DateUtils.FORMAT_DEFAULT);
        return this.vonDateChooser;
    }

    private JDateChooser getBisDateChooser() {
        if (this.bisDateChooser == null)
            this.bisDateChooser = new JDateChooser(
                new Date(), DateUtils.FORMAT_DEFAULT);
        return this.bisDateChooser;
    }

    private JComboBox<String> getAnalyseVonBox() {
        List<String> list1 = Arrays.asList(
            DatabaseQuery.getAnalysierer());
        List<String> list2 = new ArrayList<String>();
        list2.add("Selbstüberwachung");
        list2.addAll(1, list1);
        String[] array = list2.toArray(new String[0]);
        if (this.analyseVonBox == null) {
            this.analyseVonBox = new JComboBox<>();
            this.analyseVonBox.setModel(
                new DefaultComboBoxModel<>(array));
            this.analyseVonBox.setSelectedIndex(-1);
        }
        return this.analyseVonBox;
    }

    private JList<Parameter> getLeftList() {
        if (this.leftList == null) {
            DefaultListModel<Parameter> listModel =
                new DefaultListModel<>();
            this.leftList = new JList<>(listModel);
            this.leftList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        }
        return this.leftList;
    }

    private JList<Parameter> getRightList() {
        if (this.rightList == null) {
            DefaultListModel<Parameter> listModel =
                new DefaultListModel<>();
            this.rightList = new JList<>(listModel);
            this.rightList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        }
        return this.rightList;
    }

    private JButton createRLButton(boolean left,
        String paramId) {
        Icon icon;
        Icon rIcon;
        String direction;
        if (left) {
            icon = AuikUtils.getIcon(16, "left.png");
            rIcon = AuikUtils.getIcon(16, "left_a.png");
            direction = "left";
        } else {
            icon = AuikUtils.getIcon(16, "right.png");
            rIcon = AuikUtils.getIcon(16, "right_a.png");
            direction = "right";
        }
        JButton button = new JButton(icon);
        button.setRolloverIcon(rIcon);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setActionCommand(
            direction + "_" + paramId);
        button.addActionListener(getRLButtonListener());
        return button;
    }

    private JButton getLeftDeleteButton() {
        if (this.leftDeleteButton == null) {
            this.leftDeleteButton =
                new JButton("Löschen");
            this.leftDeleteButton.setEnabled(false);
            this.leftDeleteButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        int index = getLeftList()
                            .getSelectedIndex();
                        DefaultListModel<Parameter>
                            leftModel =
                            (DefaultListModel<Parameter>)
                            getLeftList().getModel();
                        if (index != -1) {
                            leftModel.remove(index);
                            int size =
                                leftModel.getSize();
                            if (size == 0) {
                                ProbepktAuswPanel.this
                                    .leftDeleteButton
                                    .setEnabled(false);
                            } else {
                                if (index == size)
                                    index--;
                                getLeftList()
                                    .setSelectedIndex(
                                        index);
                                getLeftList()
                                    .ensureIndexIsVisible(
                                        index);
                            }
                        }
                    }
                });
        }
        return this.leftDeleteButton;
    }

    private JButton getRightDeleteButton() {
        if (this.rightDeleteButton == null) {
            this.rightDeleteButton =
                new JButton("Löschen");
            this.rightDeleteButton.setEnabled(false);
            this.rightDeleteButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        int index = getRightList()
                            .getSelectedIndex();
                        DefaultListModel<Parameter>
                            rightModel =
                            (DefaultListModel<Parameter>)
                            getRightList().getModel();
                        if (index != -1) {
                            rightModel.remove(index);
                            int size =
                                rightModel.getSize();
                            if (size == 0) {
                                ProbepktAuswPanel.this
                                    .rightDeleteButton
                                    .setEnabled(false);
                            } else {
                                if (index == size)
                                    index--;
                                getRightList()
                                    .setSelectedIndex(
                                        index);
                                getRightList()
                                    .ensureIndexIsVisible(
                                        index);
                            }
                        }
                    }
                });
        }
        return this.rightDeleteButton;
    }

    private ActionListener getRLButtonListener() {
        if (this.rlButtonListener == null) {
            this.rlButtonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String direction = e.getActionCommand()
                        .replaceFirst("_.*", "");
                    String paramId = e.getActionCommand()
                        .replaceFirst(".*_", "");
                    Parameter param = null;
                    if (!paramId.equals("")) {
                        if (paramId.equals("box"))
                            param = (Parameter)
                                getParameterBox()
                                    .getSelectedItem();
                        else
                            param = Parameter
                                .findById(paramId);
                    }
                    if (param != null) {
                        DefaultListModel<Parameter>
                            leftModel =
                            (DefaultListModel<Parameter>)
                            getLeftList().getModel();
                        DefaultListModel<Parameter>
                            rightModel =
                            (DefaultListModel<Parameter>)
                            getRightList().getModel();
                        if (direction.equals(LEFT)) {
                            if (!leftModel
                                .contains(param)) {
                                if (rightModel
                                    .contains(param)) {
                                    rightModel
                                        .removeElement(
                                            param);
                                    if (rightModel
                                        .getSize() == 0)
                                        getRightDeleteButton()
                                            .setEnabled(
                                                false);
                                }
                                leftModel.addElement(
                                    param);
                                getLeftDeleteButton()
                                    .setEnabled(true);
                            }
                        } else if (direction
                            .equals(RIGHT)) {
                            if (!rightModel
                                .contains(param)) {
                                if (leftModel
                                    .contains(param)) {
                                    leftModel
                                        .removeElement(
                                            param);
                                    if (leftModel
                                        .getSize() == 0)
                                        getLeftDeleteButton()
                                            .setEnabled(
                                                false);
                                }
                                rightModel.addElement(
                                    param);
                                getRightDeleteButton()
                                    .setEnabled(true);
                            }
                        }
                    }
                }
            };
        }
        return this.rlButtonListener;
    }

    public void fetchFormData() throws RuntimeException {}
    public void updateForm() throws RuntimeException {}
    public void clearForm() {}
    public void enableAll(boolean enabled) {}

    @Override
    public String getName() {
        return this.name;
    }
}