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
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import javax.swing.AbstractAction;
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
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;



import de.bielefeld.umweltamt.aui.utils.AuikUtils;
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


    // Daten
    private AtlProbepkt pkt;
    private JComboBox parameterBox;




    private HauptFrame frame;




    public ProbepktAuswPanel(BasisObjektBearbeiten hauptModul) {
        name = "Auswertung";


        this.hauptModul = hauptModul;


        FormLayout layout = new FormLayout (
                "20dlu, 5dlu, 70dlu, 5dlu, 20dlu, 5dlu, 140dlu, 5dlu, r:16px, 5dlu, c:70dlu:g(0.1), 5dlu, l:16px, 10dlu, 300dlu", // Spalten
                "pref, 3dlu" +", " +  //1
                "pref, 3dlu " +", " +  //2
                "pref, 3dlu" +", " +    //3
                "pref, 3dlu" +", " +    //4
                "pref, 3dlu" +", " +    //5
                "pref, 3dlu" +", " +  //6
                "pref, 3dlu" +", " + //7
                "pref, 3dlu" +", " +//8
                "pref, 3dlu" +", " +//9
                "pref, 3dlu" +", " +    //10
                "pref, 3dlu"+", " +    //11
                "pref, 3dlu" +", " +    //12
                "pref, 3dlu" +", " +//13
                "pref, 3dlu" +", " +    //14
                "pref, 3dlu"+", " + //15
                "pref, 3dlu" +", " +    //16
                "pref, 3dlu" +", " +     // 17
                "pref, 3dlu" +", " +    //18
                "pref, 3dlu" +", " +    //19
                "pref, 3dlu" +", " +    //20
                "pref, 3dlu" +", " +    //21
                "pref, 3dlu" +", " +    //22
                "pref, 3dlu" +", " +    //23
                "pref, 3dlu" +", " +    //24
                "pref");    //25

        PanelBuilder builder = new PanelBuilder(layout, this);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        CellConstraints cc2 = (CellConstraints) cc.clone();


        builder.addSeparator("Zeitraum",    cc.xyw( 1, 1, 11));
        builder.addSeparator("Analyse von...",    cc.xyw( 13, 1, 3));

        builder.add(new JLabel("Von:"),        cc.xy(  1, 3, "r,d"),
                getVonDateChooser(),        cc2.xy( 3, 3, "l,d"));
        builder.add(new JLabel("Bis:"),        cc.xy(  5, 3, "r,d"),
                getBisDateChooser(),        cc2.xy( 7, 3, "l,d"));
        builder.add(getAnalyseVonBox(),            cc2.xyw(  13, 3, 3, "l,d"));

        builder.addSeparator("Parameter",    cc.xyw( 1, 5, 15));

        builder.add(new JLabel("Erste Y-Achse"),    cc.xyw  ( 1, 7, 7));
        builder.add(new JLabel("Zweite Y-Achse"),    cc.xy  ( 15, 7));

        JList lList = getLeftList();
        JList rList = getRightList();
        builder.add(new JScrollPane(lList),        cc.xywh( 1, 9, 7, 15, "fill, fill"));
        builder.add(new JScrollPane(rList),        cc.xywh( 15, 9, 1, 15, "fill, fill"));

        builder.add(createRLButton(true, AtlParameter.CADMIUM_ID),    cc.xy( 9, 9));
        builder.add(new JLabel("Cadmium (Cd)", JLabel.CENTER),         cc.xy( 11, 9, "f,d"));
        builder.add(createRLButton(false, AtlParameter.CADMIUM_ID),    cc.xy( 13, 9));

        builder.add(createRLButton(true, AtlParameter.CHROM_ID),    cc.xy( 9, 11));
        builder.add(new JLabel("Chrom (Cr)", JLabel.CENTER),         cc.xy( 11, 11, "f,d"));
        builder.add(createRLButton(false, AtlParameter.CHROM_ID),    cc.xy( 13, 11));

        builder.add(createRLButton(true, AtlParameter.KUPFER_ID),    cc.xy( 9, 13));
        builder.add(new JLabel("Kupfer (Cu)", JLabel.CENTER),         cc.xy( 11, 13, "f,d"));
        builder.add(createRLButton(false, AtlParameter.KUPFER_ID),    cc.xy( 13, 13));

        builder.add(createRLButton(true, AtlParameter.QUECKSILBER_ID),    cc.xy( 9, 15));
        builder.add(new JLabel("Quecksilber (Hg)", JLabel.CENTER),         cc.xy( 11, 15, "f,d"));
        builder.add(createRLButton(false, AtlParameter.QUECKSILBER_ID),    cc.xy( 13, 15));

        builder.add(createRLButton(true, AtlParameter.NICKEL_ID),    cc.xy( 9, 17));
        builder.add(new JLabel("Nickel (Ni)", JLabel.CENTER),         cc.xy( 11, 17, "f,d"));
        builder.add(createRLButton(false, AtlParameter.NICKEL_ID),    cc.xy( 13, 17));

        builder.add(createRLButton(true, AtlParameter.BLEI_ID),    cc.xy( 9,19));
        builder.add(new JLabel("Blei (Pb)", JLabel.CENTER),     cc.xy( 11,19, "f,d"));
        builder.add(createRLButton(false, AtlParameter.BLEI_ID),    cc.xy( 13,19));

        builder.add(createRLButton(true, AtlParameter.ZINK_ID),    cc.xy( 9,21));
        builder.add(new JLabel("Zink (Zn)", JLabel.CENTER),     cc.xy( 11,21, "f,d"));
        builder.add(createRLButton(false, AtlParameter.ZINK_ID),    cc.xy( 13,21));

        builder.add(createRLButton(true, "box"),    cc.xy( 9,23));
        builder.add(getParameterBox(),                 cc.xy( 11,23, "f,d"));
        builder.add(createRLButton(false, "box"),    cc.xy( 13,23));

        builder.add(getLeftDeleteButton(),        cc.xyw( 1, 25, 7, "c,d"));
        builder.add(getRightDeleteButton(),        cc.xy( 15, 25, "c,d"));
        builder.add(getSubmitButton() ,        cc.xy(11, 25));
    }

    private class AuswertungsDialog extends JDialog {
        /**
         * Ein Listener für die Events des Dialogs.
         * @author David Klotz
         */
        private class DialogListener extends WindowAdapter implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == abbrechenButton) {
                    doAbbrechen();
                } else if (e.getSource() == speichernButton) {
                    doSpeichern();
                }
            }

            public void windowClosing(WindowEvent e) {
                // Wenn der Dialog geschlossen wird, wird das Bearbeiten abgebrochen
                doAbbrechen();
            }
        }
        /**
         * Ein Tablemodel für die
         * @author David Klotz
         */
        private class ExportTableModel extends AbstractTableModel {
            private TimeSeriesCollection col1, col2;
            private List dateList;

            public ExportTableModel(TimeSeriesCollection col1, TimeSeriesCollection col2) {
                this.col1 = col1;
                this.col2 = col2;
                dateList = new ArrayList();

                initializeData();
            }

            private void initializeData() {
                TimeSeries series;
                APosDataItem item;

                for (int i = 0; i < col1.getSeriesCount(); i++) {
                    series = col1.getSeries(i);
                    for (int j = 0; j < series.getItemCount(); j++) {
                        item = (APosDataItem) series.getDataItem(j);

                        if (!dateList.contains(item.getMinute())) {
                            dateList.add(item.getMinute());
                        }
                    }
                }

                if (col2 != null) {
                    for (int i = 0; i < col2.getSeriesCount(); i++) {
                        series = col2.getSeries(i);
                        for (int j = 0; j < series.getItemCount(); j++) {
                            item = (APosDataItem) series.getDataItem(j);
                            //count++;
                            if (!dateList.contains(item.getMinute())) {
                                dateList.add(item.getMinute());
                            }
                        }
                    }
                }

                Collections.sort(dateList);
            }

            public int getColumnCount() {
                return col1.getSeriesCount() + ((col2 != null) ? col2.getSeriesCount() : 0) + 1;//2;
            }

            public int getRowCount() {
                return dateList.size();// + 1;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                String tmp = "!OOB!";

                NumberFormat kommaFormat = NumberFormat.getNumberInstance();
                kommaFormat.setGroupingUsed(false);
                kommaFormat.setMinimumFractionDigits(1);

                int seriesIndex = columnIndex - 1;
                int series2Index = seriesIndex - col1.getSeriesCount();
                int itemIndex = rowIndex;// - 1;

                Minute min = (Minute) dateList.get(itemIndex);
             if (columnIndex == 0) {
                    Date date = new Date(min.getFirstMillisecond());
                    tmp = AuikUtils.getStringFromDate(date);
                } else {
                    APosDataItem item = null;
                    if (seriesIndex < col1.getSeriesCount()) {
                        item = (APosDataItem) col1.getSeries(seriesIndex).getDataItem(min);
                    } else if (col2 != null) {
                        item = (APosDataItem) col2.getSeries(series2Index).getDataItem(min);
                    }
                    if (item != null) {
                        tmp = kommaFormat.format(item.getValue());
                    } else {
                        tmp = "";
                    }
                }

                return tmp;
            }

            public Class getColumnClass(int columnIndex) {
                return String.class;
            }

            public String getColumnName(int column) {
                String tmp = "!OOB!";

                int seriesIndex = column - 1;
                int series2Index = seriesIndex - col1.getSeriesCount();

                 if (column == 0) {
                    tmp = "Datum";
                } else {
                    if (seriesIndex < col1.getSeriesCount()) {
                        tmp = col1.getSeriesName(seriesIndex) + ", " + col1.getSeries(seriesIndex).getRangeDescription();
                    } else if (col2 != null) {
                        tmp = col2.getSeriesName(series2Index) + ", " + col2.getSeries(series2Index).getRangeDescription();
                    }
                }

                return tmp;
            }
        }

        private JButton speichernButton;
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


        public AuswertungsDialog  (String title, TimeSeriesCollection leftDataset, TimeSeriesCollection rightDataset, HauptFrame owner)  {
            super( owner, title + "-Auswertung", true);
            this.owner = owner;
            this.title = title;

            this.leftDataset = leftDataset;
            this.rightDataset = rightDataset;

            listener = new DialogListener();

            this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(listener);

            speichernButton = new JButton("Speichern");
            speichernButton.addActionListener(listener);
            abbrechenButton = new JButton("Schließen");
            abbrechenButton.addActionListener(listener);

            JPanel tmp = new JPanel(new BorderLayout(0,7));

            tmp.add(initializeContent(), BorderLayout.CENTER);
            JPanel buttonBar = ButtonBarFactory.buildOKCancelBar(speichernButton, abbrechenButton);
            tmp.add(buttonBar, BorderLayout.SOUTH);
            tmp.setBorder(Borders.TABBED_DIALOG_BORDER);

            this.setContentPane(tmp);
            this.pack();
            this.setLocationRelativeTo(owner);
        }

        private JComponent initializeContent() {
            tabbedPane = new JTabbedPane();

            tabbedPane.addTab("Diagramm", createDiagrammPanel());
            tabbedPane.addTab("Tabelle", createTabellenPanel());

            return tabbedPane;
        }

        private JPanel createDiagrammPanel() {
            JFreeChart chart;
            if (rightDataset == null) {
                chart = Charts.createDefaultTimeSeriesChart(title, leftDataset);
            } else {
                chart = Charts.createDefaultTimeSeriesChart(title, leftDataset, rightDataset);
            }

            chartPanel = new ChartPanel(chart, false);
            chartPanel.setBorder(Borders.DIALOG_BORDER);

            return chartPanel;
        }

        private JComponent createTabellenPanel() {
            exportTable = new JTable(new ExportTableModel(leftDataset, rightDataset));
            exportTable.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            exportTable.setColumnSelectionAllowed(true);
            exportTable.setRowSelectionAllowed(true);
            exportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            exportTable.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    showTabellenPopup(e);
                }

                public void mouseReleased(MouseEvent e) {
                    showTabellenPopup(e);
                }
            });

            DefaultTableCellRenderer zentrierterRenderer = new DefaultTableCellRenderer();
            zentrierterRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            DefaultTableCellRenderer rechtsBuendigRenderer = new DefaultTableCellRenderer();
            rechtsBuendigRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

            TableColumn column = null;
            for (int i = 0; i < exportTable.getColumnCount(); i++) {
                column = exportTable.getColumnModel().getColumn(i);
                if (i == 0 ) {//|| i == 1) {
                    column.setCellRenderer(zentrierterRenderer);
                    column.setPreferredWidth(75);
                } else {
                    column.setCellRenderer(rechtsBuendigRenderer);
                    column.setPreferredWidth(90);
                }
            }

            JScrollPane tabellenScroller = new JScrollPane(exportTable,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            tabellenScroller.setBorder(Borders.DIALOG_BORDER);

            return tabellenScroller;
        }

        public void saveTabelle() {
        File exportDatei;
        String[] csv = new String []{"csv"};
        owner = hauptModul.getFrame();

         exportDatei = owner.saveFile(csv);


            if (exportDatei != null) {
                String ext = AuikUtils.getExtension(exportDatei);

                if (ext == null) {
                    String newExt;
                    if (exportDatei.getName().endsWith(".")) {
                        newExt = "csv";
                    } else {
                        newExt = ".csv";
                    }
                    exportDatei = new File(exportDatei.getParent(), exportDatei.getName()+newExt);
                }

                boolean doIt = false;
                if (exportDatei.exists()) {
                    boolean answer = owner.showQuestion(
                            "Soll die vorhandene Datei "+exportDatei.getName()+" wirklich überschrieben werden?",
                            "Datei bereits vorhanden!");
                    if (answer && exportDatei.canWrite()) {
                        doIt = true;
                    }
                } else if (exportDatei.getParentFile().canWrite()) {
                    doIt = true;
                }

                if (doIt) {
                    AUIKataster.debugOutput("Speichere nach '" + exportDatei.getName() + "' (Ext: '"+ext+"') in '" + exportDatei.getParent() + "' !");
                    if (AuikUtils.exportTableDataToCVS(exportTable, exportDatei)) {
                        owner.showInfoMessage("Speichern der CSV-Datei erfolgreich!", "Speichern erfolgreich");
                    } else {
                        AUIKataster.debugOutput("Beim Speichern der Datei '"+exportDatei+"' trat ein Fehler auf!");
                        owner.showErrorMessage("Beim Speichern der Datei '"+exportDatei+"' trat ein Fehler auf!");
                    }
                }
            }
        }

        private void showTabellenPopup(MouseEvent e) {
            if (tabellenMenu == null) {
                tabellenMenu = new JPopupMenu("Tabelle");
                JMenuItem speichernItem = new JMenuItem(new AbstractAction("Speichern") {
                    public void actionPerformed(ActionEvent e) {
                        saveTabelle();
                    }
                });
                tabellenMenu.add(speichernItem);
            }

            if (e.isPopupTrigger()) {
                Point origin = e.getPoint();
                int row = exportTable.rowAtPoint(origin);
                int col = exportTable.columnAtPoint(origin);

                if (row != -1) {
                    exportTable.setRowSelectionInterval(row, row);
                    exportTable.setColumnSelectionInterval(col, col);
                    tabellenMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }

        public void doAbbrechen() {
            this.dispose();
        }

        public void doSpeichern() {
         if (tabbedPane.getSelectedIndex() == 0) {
                try {
                    chartPanel.doSaveAs();
                } catch (IOException e) {
                    AUIKataster.debugOutput("Konnte Datei nicht speichern!");
                }
            } else if (tabbedPane.getSelectedIndex() == 1) {
                saveTabelle();
            }
        }
    }




    private JComboBox getParameterBox() {
        if (parameterBox == null) {
            parameterBox = new SearchBox(AtlParameter.getRelevanteParameter());
        }

        return parameterBox;
    }


    private JButton getSubmitButton() {
        if (submitButton == null) {
            submitButton = new JButton("Abschicken");

            submitButton.addActionListener(new ActionListener() {
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

        return submitButton;
    }




    public void showResultOneAxis(final String axis) {
        SwingWorkerVariant worker = new SwingWorkerVariant(getSubmitButton()) {
            protected void doNonUILogic() throws RuntimeException {
                dataSet1 = createDataset(axis);
            }

            protected void doUIUpdateLogic() throws RuntimeException {
                if (dataSet1.getSeriesCount() > 0) {

                    AuswertungsDialog dialog = new AuswertungsDialog("Probenahmen", dataSet1, null, frame );

                    dialog.setVisible(true);
                } else {
                    AUIKataster.debugOutput("Keine Parameter ausgewählt!" ,"showResultOneAxis");
                }
            }
        };

        AUIKataster.debugOutput("Bereite Auswertung vor...", "showResultOneAxis");
        worker.start();
    }

    public void showResultDualAxis() {
        SwingWorkerVariant worker = new SwingWorkerVariant(getSubmitButton()) {
            private int seriesCount = 0;

            protected void doNonUILogic() throws RuntimeException {
                dataSet1 = createDataset(LEFT);
                dataSet2 = createDataset(RIGHT);

                seriesCount = dataSet1.getSeriesCount() + dataSet2.getSeriesCount();
            }

            protected void doUIUpdateLogic() throws RuntimeException {
                if (seriesCount > 0) {

                    AuswertungsDialog dialog = new AuswertungsDialog("Probenahmen", dataSet1, dataSet2, frame);
                    dialog.setVisible(true);
                } else {
                    AUIKataster.debugOutput("Keine Parameter ausgewählt!","showResultDualAxis" );
                }
            }
        };

        AUIKataster.debugOutput("Bereite Auswertung vor...", "showResultDualAxis");
        worker.start();
    }

    private TimeSeriesCollection createDataset(String axis) {
        TimeSeriesCollection col = new TimeSeriesCollection();

        int parameterAnzahl;
        //AtlEinheiten einheit;
        JList paramList;
        if (axis.equals(LEFT)) {
            parameterAnzahl = getLeftList().getModel().getSize();
            paramList = getLeftList();

        } else {
            parameterAnzahl = getRightList().getModel().getSize();
            paramList = getRightList();

        }

        AtlEinheiten einheit = AtlEinheiten.getEinheit(42);
        Timestamp vonDate = new Timestamp(getVonDateChooser().getDate().getTime());
        Timestamp bisDate = new Timestamp(getBisDateChooser().getDate().getTime());
        String analyeVon = analyseVonBox.getSelectedItem().toString();



        pkt = AtlProbepkt.getProbepunktByObjekt(hauptModul.getObjekt());



        createSeries(paramList, pkt, einheit, vonDate, bisDate, analyeVon, col);

        return col;
    }






    private void createSeries(
            JList paramList, AtlProbepkt pkt, AtlEinheiten einheit,
            Date vonDate, Date bisDate, String analyseVon,
            TimeSeriesCollection col){


        if (pkt != null) {

            for (int i = 0; i < paramList.getModel().getSize(); i++) {
                AtlParameter p = (AtlParameter) paramList.getModel()
                        .getElementAt(i);


                List list = AtlAnalyseposition.getAnalysepositionen(p,
                        einheit, pkt, vonDate, bisDate, analyseVon);




                TimeSeries series = ChartDataSets
                        .createAnalysePositionenSeries(list, p+ " ",einheit.toString());
                col.addSeries(series);
            }
        }
    }

    private JDateChooser getVonDateChooser() {
        if (vonDateChooser == null) {
            vonDateChooser = new JDateChooser(AuikUtils.DATUMSFORMAT, false);
        }

        return vonDateChooser;
    }

    private JDateChooser getBisDateChooser() {
        if (bisDateChooser == null) {
            bisDateChooser = new JDateChooser(AuikUtils.DATUMSFORMAT, false);
        }

        return bisDateChooser;
    }

    private JComboBox getAnalyseVonBox() {
        if (analyseVonBox == null) {
            String[]  inst = AtlAnalyseposition.getAnalysierer();
            analyseVonBox = new JComboBox(inst);
            analyseVonBox.setEditable(true);
            analyseVonBox.setModel(new DefaultComboBoxModel(inst));
            analyseVonBox.setPrototypeDisplayValue("Faulschlamm   abc");
        }

        return analyseVonBox;
    }


    private JList getLeftList() {
        if (leftList == null) {
            DefaultListModel listModel = new DefaultListModel();
            leftList = new JList(listModel);
            leftList.setPrototypeCellValue("Abcdefghij (Ab)");

            leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        return leftList;
    }

    private JList getRightList() {
        if (rightList == null) {
            DefaultListModel listModel = new DefaultListModel();
            rightList = new JList(listModel);
            rightList.setPrototypeCellValue("Abcdefghij (Ab)");

            rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        return rightList;
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
        if (leftDeleteButton == null) {
            leftDeleteButton = new JButton("Löschen");
            leftDeleteButton.setEnabled(false);

            leftDeleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int index = getLeftList().getSelectedIndex();
                    DefaultListModel leftModel = ((DefaultListModel)getLeftList().getModel());

                    if (index != -1) {
                        leftModel.remove(index);

                        int size = leftModel.getSize();

                        if (size == 0) {
                            leftDeleteButton.setEnabled(false);
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

        return leftDeleteButton;
    }

    private JButton getRightDeleteButton() {
        if (rightDeleteButton == null) {
            rightDeleteButton = new JButton("Löschen");
            rightDeleteButton.setEnabled(false);

            rightDeleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int index = getRightList().getSelectedIndex();
                    DefaultListModel rightModel = ((DefaultListModel)getRightList().getModel());

                    if (index != -1) {
                        rightModel.remove(index);

                        int size = rightModel.getSize();

                        if (size == 0) {
                            rightDeleteButton.setEnabled(false);
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

        return rightDeleteButton;
    }



    private ActionListener getRLButtonListener() {
        if (rlButtonListener == null) {
            rlButtonListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String direction = e.getActionCommand().replaceFirst("_.*", "");
                    String paramId = e.getActionCommand().replaceFirst(".*_", "");
                    AtlParameter param = null;

                    if (!paramId.equals("")) {
                        if (paramId.equals("box")) {
                            param = (AtlParameter) getParameterBox().getSelectedItem();
                        } else {
                            param = AtlParameter.getParameter(paramId);
                        }
                    }

                    if (param != null) {
                        DefaultListModel leftModel = (DefaultListModel) getLeftList().getModel();
                        DefaultListModel rightModel = (DefaultListModel) getRightList().getModel();

                        if (direction.equals(LEFT)) {
                            if (!leftModel.contains(param)) {
                                if (rightModel.contains(param)) {
                                    rightModel.removeElement(param);
                                    if (rightModel.getSize() == 0) {
                                        getRightDeleteButton().setEnabled(false);
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

        return rlButtonListener;
    }

    public void fetchFormData() throws RuntimeException {

    }

    public void updateForm() throws RuntimeException {

    }

    public void clearForm() {


    }

    public void enableAll(boolean enabled) {

    }


    public String getName() {
        return name;
    }


}
