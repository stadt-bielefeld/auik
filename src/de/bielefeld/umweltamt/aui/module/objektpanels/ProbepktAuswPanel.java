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
import java.util.Collections;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
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
    private static final long serialVersionUID = -6939004829737520612L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private JDateChooser vonDateChooser;
    private JDateChooser bisDateChooser;
    private JComboBox analyseVonBox;

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    // Widgets
    private JList leftList;
    private JList rightList;
    private JButton submitButton;

    private JButton leftDeleteButton;
    private JButton rightDeleteButton;
    private TimeSeriesCollection dataSet1;
    private TimeSeriesCollection dataSet2;
    private ActionListener rlButtonListener;
    private Einheiten[] einheiten;

    // Daten
    private Messstelle pkt;
    private JComboBox parameterBox;
    private JComboBox leftEinheitenBox;
    private JComboBox rightEinheitenBox;

    private HauptFrame frame;

    public ProbepktAuswPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Auswertung";

        this.einheiten = DatabaseQuery.getEinheiten();
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "20dlu, 5dlu, 70dlu, 5dlu, 20dlu, 5dlu, 140dlu, 5dlu, r:16px, 5dlu, c:70dlu:g(0.1), 5dlu, l:16px, 10dlu, 300dlu", // Spalten
            "pref, 3dlu" + ", " + // 1
                "pref, 3dlu " + ", " + // 2
                "pref, 3dlu" + ", " + // 3
                "pref, 3dlu" + ", " + // 4
                "pref, 3dlu" + ", " + // 5
                "pref, 3dlu" + ", " + // 6
                "pref, 3dlu" + ", " + // 7
                "pref, 3dlu" + ", " + // 8
                "pref, 3dlu" + ", " + // 9
                "pref, 3dlu" + ", " + // 10
                "pref, 3dlu" + ", " + // 11
                "pref, 3dlu" + ", " + // 12
                "pref, 3dlu" + ", " + // 13
                "pref, 3dlu" + ", " + // 14
                "pref, 3dlu" + ", " + // 15
                "pref, 3dlu" + ", " + // 16
                "pref, 3dlu" + ", " + // 17
                "pref, 3dlu" + ", " + // 18
                "pref, 3dlu" + ", " + // 19
                "pref, 3dlu" + ", " + // 20
                "pref, 3dlu" + ", " + // 21
                "pref, 3dlu" + ", " + // 22
                "pref, 3dlu" + ", " + // 23
                "pref, 3dlu" + ", " + // 24
                "pref, 3dlu" + ", " + // 25
                "pref, 3dlu" + ", " + // 26
                "pref"); // 27

        PanelBuilder builder = new PanelBuilder(layout, this);
        CellConstraints cc = new CellConstraints();
        CellConstraints cc2 = (CellConstraints) cc.clone();

        builder.addSeparator("Zeitraum", cc.xyw(1, 1, 11));
        builder.addSeparator("Analyse von...", cc.xyw(15, 1, 1));

        builder.add(new JLabel("Von:"), cc.xy(1, 3, "r,d"),
            getVonDateChooser(), cc2.xy(3, 3, "l,d"));
        builder.add(new JLabel("Bis:"), cc.xy(5, 3, "r,d"),
            getBisDateChooser(), cc2.xy(7, 3, "l,d"));
        builder.add(getAnalyseVonBox(), cc2.xyw(15, 3, 1, "l,d"));

        builder.addSeparator("Parameter", cc.xyw(1, 5, 15));

        builder.add(new JLabel("Erste Y-Achse"), cc.xyw(1, 7, 7));
        builder.add(new JLabel("Zweite Y-Achse"), cc.xy(15, 7));

        JList lList = getLeftList();
        JList rList = getRightList();
        builder.add(new JScrollPane(lList), cc.xywh(1, 9, 7, 15, "fill, fill"));
        builder
            .add(new JScrollPane(rList), cc.xywh(15, 9, 1, 15, "fill, fill"));

        String[] paramIDs = {
            DatabaseConstants.ATL_PARAMETER_ID_CADMIUM,
            DatabaseConstants.ATL_PARAMETER_ID_CHROM,
            DatabaseConstants.ATL_PARAMETER_ID_KUPFER,
            DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER,
            DatabaseConstants.ATL_PARAMETER_ID_NICKEL,
            DatabaseConstants.ATL_PARAMETER_ID_BLEI,
            DatabaseConstants.ATL_PARAMETER_ID_ZINK
        };
        int y = 9;
        for (String paramID : paramIDs) {
            builder.add(createRLButton(true, paramID), cc.xy(9, y));
            builder.add(new JLabel(
                Parameter.findById(paramID).getBezeichnung(), JLabel.CENTER),
                cc.xy(11, y, "f,d"));
            builder.add(createRLButton(false, paramID), cc.xy(13, y));
            y += 2;
        }

        builder.add(createRLButton(true, "box"), cc.xy(9, 23));
        builder.add(getParameterBox(), cc.xy(11, 23, "f,d"));
        builder.add(createRLButton(false, "box"), cc.xy(13, 23));

        builder.add(getLeftEinheitenBox(), cc.xyw(1, 25, 7, "c,d"));
        builder.add(new JLabel("<  Einheit  >", JLabel.CENTER),
            cc.xy(11, 25, "f,d"));
        builder.add(getRightEinheitenBox(), cc.xy(15, 25, "c,d"));

        builder.add(getLeftDeleteButton(), cc.xyw(1, 27, 7, "c,d"));
        builder.add(getRightDeleteButton(), cc.xy(15, 27, "c,d"));
        builder.add(getSubmitButton(), cc.xy(11, 27));
    }

    private class AuswertungsDialog extends JDialog {
        private static final long serialVersionUID = -4652005913711822389L;

        /**
         * Ein Listener für die Events des Dialogs.
         * @author David Klotz
         */
        private class DialogListener extends WindowAdapter implements
            ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == AuswertungsDialog.this.abbrechenButton) {
                    doAbbrechen();
                } else if (e.getSource() == AuswertungsDialog.this.speichernButton) {
                    doSpeichern();
                } else if (e.getSource() == AuswertungsDialog.this.printButton) {
                    doPrint();
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // Wenn der Dialog geschlossen wird, wird das Bearbeiten
                // abgebrochen
                doAbbrechen();
            }
        }

        /**
         * Ein Tablemodel für die
         * @author David Klotz
         */
        private class ExportTableModel extends AbstractTableModel {
            private static final long serialVersionUID = -7115812453677348579L;
            private TimeSeriesCollection col1, col2;
            private List<Minute> dateList;

            public ExportTableModel(TimeSeriesCollection col1,
                TimeSeriesCollection col2) {
                this.col1 = col1;
                this.col2 = col2;
                this.dateList = new ArrayList<Minute>();

                initializeData();
            }

            private void initializeData() {
                TimeSeries series;
                APosDataItem item;

				for (int i = 0; i < this.col1.getSeriesCount(); i++) {
					series = this.col1.getSeries(i);
					for (int j = 0; j < series.getItemCount(); j++) {
						item = (APosDataItem) series.getDataItem(j);

						int n = this.dateList.size();
						boolean add = true;
						if (n == 0) {
							this.dateList.add(item.getMinute());
						} else
							for (int k = 0; k < n; k++) {
								if (this.dateList.get(k).toString().equals(item
										.getMinute().toString())) {
									add = false;
								}
							}
						if(add == true && n > 0){
							this.dateList.add(item.getMinute());
						}
					}
				}

                if (this.col2 != null) {
                    for (int i = 0; i < this.col2.getSeriesCount(); i++) {
                        series = this.col2.getSeries(i);
                        for (int j = 0; j < series.getItemCount(); j++) {
                            item = (APosDataItem) series.getDataItem(j);
                            // count++;
                            int n = this.dateList.size();
    						boolean add = true;
    						if (n == 0) {
    							this.dateList.add(item.getMinute());
    						} else
    							for (int k = 0; k < n; k++) {
    								if (this.dateList.get(k).toString().equals(item
    										.getMinute().toString())) {
    									add = false;
    								}
    							}
    						if(add == true && n > 0){
    							this.dateList.add(item.getMinute());
    						}
                        }
                    }
                }

                Collections.sort(this.dateList);
            }

            @Override
            public int getColumnCount() {
                return this.col1.getSeriesCount()
                    + ((this.col2 != null) ? this.col2.getSeriesCount() : 0)
                    + 1;// 2;
            }

            @Override
            public int getRowCount() {
                return this.dateList.size();// + 1;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                String tmp = "!OOB!";

                NumberFormat kommaFormat = NumberFormat.getNumberInstance();
                kommaFormat.setGroupingUsed(false);
                kommaFormat.setMinimumFractionDigits(1);

                int seriesIndex = columnIndex - 1;
                int series2Index = seriesIndex - this.col1.getSeriesCount();
                int itemIndex = rowIndex;// - 1;

                Minute min = (Minute) this.dateList.get(itemIndex);
                if (columnIndex == 0) {
                    Date date = new Date(min.getFirstMillisecond());
                    tmp = AuikUtils.getDayTimeStringFromDate(date);
                } else {
                    APosDataItem item = null;
                    if (seriesIndex < this.col1.getSeriesCount()) {
                        item = (APosDataItem) this.col1.getSeries(seriesIndex)
                            .getDataItem(min);
                    } else if (this.col2 != null) {
                        item = (APosDataItem) this.col2.getSeries(series2Index)
                            .getDataItem(min);
                    }
                    if (item != null) {
                        tmp = kommaFormat.format(item.getValue());
                    } else {
                        tmp = "";
                    }
                }

                return tmp;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public String getColumnName(int column) {
                String tmp = "!OOB!";

                int seriesIndex = column - 1;
                int series2Index = seriesIndex - this.col1.getSeriesCount();

                if (column == 0) {
                    tmp = "Datum";
                } else {
                    if (seriesIndex < this.col1.getSeriesCount()) {
                            //TODO: key == name?
			    			//tmp = this.col1.getSeriesName(seriesIndex)
                            tmp = this.col1.getSeries(seriesIndex).getKey().toString()
			    			+ ", "
                            + this.col1.getSeries(seriesIndex)
                                .getRangeDescription();
                    } else if (this.col2 != null) {
                        //tmp = this.col2.getSeriesName(series2Index)
                            tmp = this.col2.getSeries(series2Index).getKey().toString()
							+ ", "
                            + this.col2.getSeries(series2Index)
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
            TimeSeriesCollection rightDataset, HauptFrame owner) {
            super(owner, title + "-Auswertung", true);
            this.owner = owner;
            this.title = title;

            this.leftDataset = leftDataset;
            this.rightDataset = rightDataset;

            this.listener = new DialogListener();

            this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(this.listener);

            this.speichernButton = new JButton("Speichern");
            this.speichernButton.addActionListener(this.listener);
            this.printButton = new JButton("Drucken");
            this.printButton.addActionListener(this.listener);
            this.abbrechenButton = new JButton("Schließen");
            this.abbrechenButton.addActionListener(this.listener);

            JPanel tmp = new JPanel(new BorderLayout(0, 7));

            tmp.add(initializeContent(), BorderLayout.CENTER);
            JComponent buttonBar = ComponentFactory.buildOKCancelApplyBar(
                this.speichernButton, this.printButton, this.abbrechenButton);
            tmp.add(buttonBar, BorderLayout.SOUTH);
            tmp.setBorder(Paddings.TABBED_DIALOG);

            this.setContentPane(tmp);
            this.pack();
            this.setLocationRelativeTo(owner);
        }

        private JComponent initializeContent() {
            this.tabbedPane = new JTabbedPane();

            this.tabbedPane.addTab("Diagramm", createDiagrammPanel());
            this.tabbedPane.addTab("Tabelle", createTabellenPanel());

            return this.tabbedPane;
        }

        private JPanel createDiagrammPanel() {
            JFreeChart chart;
            if (this.rightDataset == null) {
                chart = Charts.createDefaultTimeSeriesChart(this.title,
                    this.leftDataset);
            } else {
                chart = Charts.createDefaultTimeSeriesChart(this.title,
                    this.leftDataset, this.rightDataset);
            }

            this.chartPanel = new ChartPanel(chart, false);
            this.chartPanel.setBorder(Paddings.DIALOG);

            return this.chartPanel;
        }

        private JComponent createTabellenPanel() {
            this.exportTable = new JTable(new ExportTableModel(
                this.leftDataset, this.rightDataset));
            this.exportTable.setBorder(BorderFactory
                .createBevelBorder(BevelBorder.RAISED));
            this.exportTable.setColumnSelectionAllowed(true);
            this.exportTable.setRowSelectionAllowed(true);
            this.exportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            this.exportTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    showTabellenPopup(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    showTabellenPopup(e);
                }
            });

            DefaultTableCellRenderer zentrierterRenderer = new DefaultTableCellRenderer();
            zentrierterRenderer
                .setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            DefaultTableCellRenderer rechtsBuendigRenderer = new DefaultTableCellRenderer();
            rechtsBuendigRenderer
                .setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

            TableColumn column = null;
            for (int i = 0; i < this.exportTable.getColumnCount(); i++) {
                column = this.exportTable.getColumnModel().getColumn(i);
                if (i == 0) {// || i == 1) {
                    column.setCellRenderer(zentrierterRenderer);
                    column.setPreferredWidth(100);
                } else {
                    column.setCellRenderer(rechtsBuendigRenderer);
                    column.setPreferredWidth(90);
                }
            }

            JScrollPane tabellenScroller = new JScrollPane(this.exportTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            tabellenScroller.setBorder(Paddings.DIALOG);

            return tabellenScroller;
        }

        public void saveTabelle() {
            File exportDatei;
            String[] csv = new String[] {"csv"};
            this.owner = ProbepktAuswPanel.this.hauptModul.getFrame();

            exportDatei = this.owner.saveFile(csv);

            if (exportDatei != null) {
                String ext = AuikUtils.getExtension(exportDatei);

                if (ext == null) {
                    String newExt;
                    if (exportDatei.getName().endsWith(".")) {
                        newExt = "csv";
                    } else {
                        newExt = ".csv";
                    }
                    exportDatei = new File(exportDatei.getParent(),
                        exportDatei.getName() + newExt);
                }

                boolean doIt = false;
                if (exportDatei.exists()) {
                    boolean answer = GUIManager.getInstance().showQuestion(
                        "Soll die vorhandene Datei " + exportDatei.getName()
                            + " wirklich überschrieben werden?",
                        "Datei bereits vorhanden!");
                    if (answer && exportDatei.canWrite()) {
                        doIt = true;
                    }
                } else if (exportDatei.getParentFile().canWrite()) {
                    doIt = true;
                }

                if (doIt) {
                    log.debug("Speichere nach '" + exportDatei.getName()
                        + "' (Ext: '" + ext + "') in '"
                        + exportDatei.getParent() + "' !");
                    if (AuikUtils.exportTableDataToCVS(this.exportTable,
                        exportDatei)) {
                        GUIManager.getInstance().showInfoMessage(
                            "Speichern der CSV-Datei erfolgreich!",
                            "Speichern erfolgreich");
                    } else {
                        log.debug("Beim Speichern der Datei '" + exportDatei
                            + "' trat ein Fehler auf!");
                        GUIManager.getInstance().showErrorMessage(
                            "Beim Speichern der Datei '" + exportDatei
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
                    "Beim Drucken der Tabelle trat ein Fehler auf!");
                e.printStackTrace();
            }
        }

        private void showTabellenPopup(MouseEvent e) {
            if (this.tabellenMenu == null) {
                this.tabellenMenu = new JPopupMenu("Tabelle");
                JMenuItem speichernItem = new JMenuItem(new AbstractAction(
                    "Speichern") {
                    private static final long serialVersionUID = -6201611660887956188L;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveTabelle();
                    }
                });
                this.tabellenMenu.add(speichernItem);
                this.tabellenMenu.add(new JMenuItem(
                    new AbstractAction("Drucken") {
                    private static final long serialVersionUID = -553069817163L;
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        printTable();
                    }
                }));
            }

            if (e.isPopupTrigger()) {
                Point origin = e.getPoint();
                int row = this.exportTable.rowAtPoint(origin);
                int col = this.exportTable.columnAtPoint(origin);

                if (row != -1) {
                    this.exportTable.setRowSelectionInterval(row, row);
                    this.exportTable.setColumnSelectionInterval(col, col);
                    this.tabellenMenu
                        .show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }

        public void doAbbrechen() {
            this.dispose();
        }

        public void doSpeichern() {
            if (this.tabbedPane.getSelectedIndex() == 0) {
                try {
                    this.chartPanel.doSaveAs();
                } catch (IOException e) {
                    log.debug("Konnte Datei nicht speichern!");
                }
            } else if (this.tabbedPane.getSelectedIndex() == 1) {
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

    private JComboBox getParameterBox() {
        if (this.parameterBox == null) {
            this.parameterBox = new SearchBox(DatabaseQuery.getAllParameterAsArray());
        }

        return this.parameterBox;
    }

    private JButton getSubmitButton() {
        if (this.submitButton == null) {
            this.submitButton = new JButton("Abschicken");

            this.submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (getRightList().getModel().getSize() == 0) {
                        showResultOneAxis(LEFT);
                    } else if (getLeftList().getModel().getSize() == 0) {
                        showResultOneAxis(RIGHT);
                    } else {
                        showResultDualAxis();
                    }
                }
            });
        }

        return this.submitButton;
    }

    private JComboBox getLeftEinheitenBox() {
        if (this.leftEinheitenBox == null) {
            this.leftEinheitenBox = new SearchBox(this.einheiten);
            this.leftEinheitenBox.setSelectedItem(
                DatabaseConstants.ATL_EINHEIT_MG_L);
        }

        return this.leftEinheitenBox;
    }

    private JComboBox getRightEinheitenBox() {
        if (this.rightEinheitenBox == null) {
            this.rightEinheitenBox = new SearchBox(this.einheiten);
            this.rightEinheitenBox.setSelectedItem(
                DatabaseConstants.ATL_EINHEIT_MG_L);

        }

        return this.rightEinheitenBox;
    }

    public void showResultOneAxis(final String axis) {
        SwingWorkerVariant worker = new SwingWorkerVariant(getSubmitButton()) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                ProbepktAuswPanel.this.dataSet1 = createDataset(axis);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                if (ProbepktAuswPanel.this.dataSet1.getSeriesCount() > 0) {

                    AuswertungsDialog dialog = new AuswertungsDialog(
                        ProbepktAuswPanel.this.pkt.getObjekt()
                            .getBetreiberid().getName(),
                        ProbepktAuswPanel.this.dataSet1, null,
                        ProbepktAuswPanel.this.frame);

                    dialog.setVisible(true);
                } else {
                    log.debug("(showResultOneAxis) "
                        + "Keine Parameter ausgewählt!");
                }
            }
        };

        log.debug("(showResultOneAxis) " + "Bereite Auswertung vor...");
        worker.start();
    }

    public void showResultDualAxis() {
        SwingWorkerVariant worker = new SwingWorkerVariant(getSubmitButton()) {
            private int seriesCount = 0;

            @Override
            protected void doNonUILogic() throws RuntimeException {
                ProbepktAuswPanel.this.dataSet1 = createDataset(LEFT);
                ProbepktAuswPanel.this.dataSet2 = createDataset(RIGHT);

                this.seriesCount = ProbepktAuswPanel.this.dataSet1
                    .getSeriesCount()
                    + ProbepktAuswPanel.this.dataSet2.getSeriesCount();
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                if (this.seriesCount > 0) {

                    AuswertungsDialog dialog = new AuswertungsDialog(
                        ProbepktAuswPanel.this.pkt.getObjekt()
                            .getBetreiberid().getName(),
                        ProbepktAuswPanel.this.dataSet1,
                        ProbepktAuswPanel.this.dataSet2,
                        ProbepktAuswPanel.this.frame);
                    dialog.setVisible(true);
                } else {
                    log.debug("(showResultDualAxis) "
                        + "Keine Parameter ausgewählt!");
                }
            }
        };

        log.debug("(showResultDualAxis) " + "Bereite Auswertung vor...");
        worker.start();
    }

    private TimeSeriesCollection createDataset(String axis) {
        TimeSeriesCollection col = new TimeSeriesCollection();

//        int parameterAnzahl;
        Einheiten einheit;
        JList paramList;
        if (axis.equals(LEFT)) {
//            parameterAnzahl = getLeftList().getModel().getSize();
            einheit = (Einheiten) getLeftEinheitenBox().getSelectedItem();
            paramList = getLeftList();

        } else {
//            parameterAnzahl = getRightList().getModel().getSize();
            einheit = (Einheiten) getRightEinheitenBox().getSelectedItem();
            paramList = getRightList();

        }

        Date vonDate = getVonDateChooser().getDate();
        Date bisDate = getBisDateChooser().getDate();

        String analyeVon = "";
        if (this.analyseVonBox.getSelectedItem() != null) {
            analyeVon = this.analyseVonBox.getSelectedItem().toString();
        }

        this.pkt = Messstelle.findByObjektId(
            this.hauptModul.getObjekt().getId());

        createSeries(paramList, this.pkt, einheit, vonDate, bisDate, analyeVon,
            col);

        return col;
    }

    private void createSeries(JList paramList, Messstelle pkt,
        Einheiten einheit, Date vonDate, Date bisDate,
        String analyseVon, TimeSeriesCollection col) {

        if (pkt != null) {

            for (int i = 0; i < paramList.getModel().getSize(); i++) {
            	Parameter param = (Parameter) paramList.getModel()
                    .getElementAt(i);

                List<Analyseposition> list =
                    DatabaseQuery.getAnalysepositionFromView(
                        param, einheit, pkt, vonDate, bisDate, analyseVon);

                TimeSeries series = ChartDataSets
                    .createAnalysePositionenSeries(list, param + " ",
                        einheit.toString());
                col.addSeries(series);
            }
        }
    }

    private JDateChooser getVonDateChooser() {
        if (this.vonDateChooser == null) {
            this.vonDateChooser = new JDateChooser(null, DateUtils.FORMAT_DEFAULT);
        }

        return this.vonDateChooser;
    }

    private JDateChooser getBisDateChooser() {
        if (this.bisDateChooser == null) {
            this.bisDateChooser = new JDateChooser(new Date(), DateUtils.FORMAT_DEFAULT);
        }

        return this.bisDateChooser;
    }

    private JComboBox getAnalyseVonBox() {
    	List<String> list1 = Arrays.asList(DatabaseQuery.getAnalysierer());
    	List<String> list2 = new ArrayList<String>();
    	list2.add(new String("Selbstüberwachung"));
    	list2.addAll(1, list1);
    	String[] array = new String[list2.size()];
    	int i=0;
    	for(String s: list2){
    	  array[i++] = s;
    	}
        if (this.analyseVonBox == null) {
            this.analyseVonBox = new JComboBox();
            this.analyseVonBox.setModel(
                new DefaultComboBoxModel(array));
            this.analyseVonBox.setSelectedIndex(-1);
        }

        return this.analyseVonBox;
    }

    private JList getLeftList() {
        if (this.leftList == null) {
            DefaultListModel listModel = new DefaultListModel();
            this.leftList = new JList(listModel);
            this.leftList.setPrototypeCellValue("Abcdefghij (Ab)");

            this.leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        return this.leftList;
    }

    private JList getRightList() {
        if (this.rightList == null) {
            DefaultListModel listModel = new DefaultListModel();
            this.rightList = new JList(listModel);
            this.rightList.setPrototypeCellValue("Abcdefghij (Ab)");

            this.rightList
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        return this.rightList;
    }

    private JButton createRLButton(boolean left, String paramId) {
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

        button.setActionCommand(direction + "_" + paramId);
        button.addActionListener(getRLButtonListener());

        return button;
    }

    private JButton getLeftDeleteButton() {
        if (this.leftDeleteButton == null) {
            this.leftDeleteButton = new JButton("Löschen");
            this.leftDeleteButton.setEnabled(false);

            this.leftDeleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = getLeftList().getSelectedIndex();
                    DefaultListModel leftModel = ((DefaultListModel) getLeftList()
                        .getModel());

                    if (index != -1) {
                        leftModel.remove(index);

                        int size = leftModel.getSize();

                        if (size == 0) {
                            ProbepktAuswPanel.this.leftDeleteButton
                                .setEnabled(false);
                        } else {
                            if (index == size) {
                                index--;
                            }

                            getLeftList().setSelectedIndex(index);
                            getLeftList().ensureIndexIsVisible(index);
                        }
                    }
                }
            });
        }

        return this.leftDeleteButton;
    }

    private JButton getRightDeleteButton() {
        if (this.rightDeleteButton == null) {
            this.rightDeleteButton = new JButton("Löschen");
            this.rightDeleteButton.setEnabled(false);

            this.rightDeleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = getRightList().getSelectedIndex();
                    DefaultListModel rightModel = ((DefaultListModel) getRightList()
                        .getModel());

                    if (index != -1) {
                        rightModel.remove(index);

                        int size = rightModel.getSize();

                        if (size == 0) {
                            ProbepktAuswPanel.this.rightDeleteButton
                                .setEnabled(false);
                        } else {
                            if (index == size) {
                                index--;
                            }

                            getRightList().setSelectedIndex(index);
                            getRightList().ensureIndexIsVisible(index);
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
                    String direction = e.getActionCommand().replaceFirst("_.*",
                        "");
                    String paramId = e.getActionCommand().replaceFirst(".*_",
                        "");
                    Parameter param = null;

                    if (!paramId.equals("")) {
                        if (paramId.equals("box")) {
                            param = (Parameter) getParameterBox()
                                .getSelectedItem();
                        } else {
                            param = Parameter.findById(paramId);
                        }
                    }

                    if (param != null) {
                        DefaultListModel leftModel = (DefaultListModel) getLeftList()
                            .getModel();
                        DefaultListModel rightModel = (DefaultListModel) getRightList()
                            .getModel();

                        if (direction.equals(LEFT)) {
                            if (!leftModel.contains(param)) {
                                if (rightModel.contains(param)) {
                                    rightModel.removeElement(param);
                                    if (rightModel.getSize() == 0) {
                                        getRightDeleteButton()
                                            .setEnabled(false);
                                    }
                                }
                                leftModel.addElement(param);
                                getLeftDeleteButton().setEnabled(true);
                            }
                        } else if (direction.equals(RIGHT)) {
                            if (!rightModel.contains(param)) {
                                if (leftModel.contains(param)) {
                                    leftModel.removeElement(param);
                                    if (leftModel.getSize() == 0) {
                                        getLeftDeleteButton().setEnabled(false);
                                    }
                                }
                                rightModel.addElement(param);
                                getRightDeleteButton().setEnabled(true);
                            }
                        }
                    }
                }
            };
        }

        return this.rlButtonListener;
    }

    public void fetchFormData() throws RuntimeException {
    }

    public void updateForm() throws RuntimeException {
    }

    public void clearForm() {
    }

    public void enableAll(boolean enabled) {
    }

    @Override
    public String getName() {
        return this.name;
    }
}
