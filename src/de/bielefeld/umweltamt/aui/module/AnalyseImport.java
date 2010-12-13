/*
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Diese Klasse ist ein eigenst&auml;ndiges Modul. Es hat die Aufgabe, einen
 * Import-Mechanismus bereitzustellen, mit dem sich Analyseergebnisse von
 * Probenahmen importieren lassen. Der Import gliedert sich in folgende
 * Stufen:<br>
 * <ul>
 * <li>Datei w&auml;hlen</li>
 * <li>Pr&uuml;fung der Kennnummer und Parameter</li>
 * <li>Anzeige der importierten Daten in Form einer Liste</li>
 * <li>Speichern der Daten mittels Knopfdruck</li>
 * <li>Plausibilit&auml;tspr&uuml;fung durch den Nutzer</li>
 * <li>Freigabe durch Nutzer mittels Knopfdruck</li>
 * </ul>
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class AnalyseImport extends AbstractModul {

    /**
     * Diese Klasse importiert die Laborergebnisse einer Probenahme in Form
     * einer Textdatei und stellt diese als {@link ListTableModel} zur
     * Verf&uuml;gung.
     *
     * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
     */
    private class AnalyseImporter extends ListTableModel {
        protected File      toImport;
        protected boolean[] selection;
        protected int[]     status;

        public AnalyseImporter() {
            super(
                new String[] {
                    "Kennnummer",
                    "Ordnungsbegriff",
                    "Parameter",
                    "GrKl",
                    "Wert",
                    "Einheit",
                    "Plausibel"
                },
                false);
        }


        @Override
        public Object getValueAt(int row, int col) {
            if (!rowExists(row)) {
                return null;
            }

            String[] columns = (String[]) getObjectAtRow(row);
            List     data    = getList();

            int status = getRowStatus(row);

            switch (col) {
                case 0: return getColoredColumn(
                    status,
                    AnalyseProcessor.unquote(columns[0]));
                case 1: return getColoredColumn(
                    status,
                    AnalyseProcessor.unquote(columns[2]));
                case 2: return getColoredColumn(
                    status,
                    AnalyseProcessor.unquote(columns[3]));
                case 3: return getColoredColumn(
                    status,
                    AnalyseProcessor.unquote(columns[4]));
                case 4: return getColoredColumn(
                    status,
                    AnalyseProcessor.unquote(columns[5]));
                case 5: return getColoredColumn(
                    status,
                    AnalyseProcessor.unquote(columns[7]));
                case 6: return selection[row];
            }

            return null;
        }


        /**
         * Diese Methode liefert den Status einer Zeile. Da der Status einer
         * Zeile davon abh&auml;ngig ist, ob sich eine Probenahme mit
         * entsprechender Kennnummer in der Datenbank befindet und ob es dazu
         * einen passenden Parameter gibt, wird der Status in {@link status}
         * gepuffert um die Perfomance aufrecht zu erhalten und die DB nicht
         * unn&ouml;tig anzufragen.
         *
         * @param row Der Index einer Zeile.
         *
         * @return -1, 1 oder 2.
         */
        protected int getRowStatus(int row) {
            if (status[row] == 0) {
                String[] columns = (String[]) getObjectAtRow(row);

                status[row] = (columns.length < 8)
                    ? -1
                    : AnalyseProcessor.importStatus(
                        AnalyseProcessor.unquote(columns[0]),
                        AnalyseProcessor.unquote(columns[2]),
                        AnalyseProcessor.unquote(columns[6]));
            }

            return status[row];
        }


        /**
         * Diese Methode liefert <i>txt</i> in einem HTML-String. Der
         * HTML-String dient dazu <i>txt</i> in unterschiedlichen Farben
         * darzustellen. Die Farbe ist vom <i>status</i> abh&auml;ngig.<br>
         * <ul>
         *  <li>Status == -1 : rot gef&auml;rbter String</li>
         *  <li>Status ==  1 : gr&uuml;n gef&auml;rbter String</li>
         *  <li>Status ==  2 : orange gef&auml;rbter String</li>
         * </ul>
         *
         * @param status Status sollte -1, 1 oder 2 sein.
         * @param txt Der auszugebende Text.
         *
         * @return liefert einen farbigen HTML-Text mit Inhalt <i>txt</i>.
         */
        protected String getColoredColumn(int status, String txt) {
            StringBuilder sb = new StringBuilder("<html>");

            switch (status) {
                case -1:
                    sb.append("<font color='red'>");
                    sb.append(txt);
                    sb.append("</font>");
                    break;
                case  1:
                    sb.append("<font color='green'>");
                    sb.append(txt);
                    sb.append("</font>");
                    break;
                case  2:
                    sb.append("<font color='FF8200'>");
                    sb.append(txt);
                    sb.append("</font>");
                    break;
                default:
                    sb.append(txt);
                    break;
            }

            sb.append("</html>");

            return sb.toString();
        }


        /**
         * Diese Methode wird nur dann ausgef&uuml;hrt, wenn <i>col</i> auf die
         * boolean Spalte verweist.
         *
         * @param value Der Wert.
         * @param row Der Index einer Zeile.
         * @param col Der Index einer Spalte.
         */
        @Override
        public void setValueAt(Object value, int row, int col) {
            if (col != 6) {
                return;
            }

            selection[row] = Boolean.TRUE.equals(value);
        }


        /**
         * Diese Methode liefert in dieser Klasse <b>immer</b> <i>null</i>. Es
         * muss stattdessen {@link getValueAt(int, int)} verwendet werden.
         *
         * @param row Ein Objekt, welches die Zeile representiert.
         * @param col Der Index einer Spalte.
         *
         * @return <i>null</i>.
         */
        public Object getColumnValue(Object row, int col) {
            return null;
        }


        /**
         * Diese Methode liefert eine Liste mit allen selektierten Zeilen.
         *
         * @return Liste mit allen selektierten Zeilen.
         */
        public List getSelectedRows() {
            List selected = new ArrayList();

            for (int i = 0; i < selection.length; i++) {
                boolean s = Boolean.TRUE.equals(getValueAt(i, 6));
                if (s) {
                    selected.add((String[]) getObjectAtRow(i));
                }
            }

            return selected;
        }


        /**
         * Diese Methode liefert <code>Boolean.class</code>, falls <i>col</i> ==
         * 6. In jedem anderen Fall wird <code>String.class</code>
         * zurückgegeben.
         *
         * @param col Der Index einer Spalte.
         * @return <code>Boolean.class</code> falls <i>col</i>==6 , sonst
         * <code>String.class</code>
         */
        public Class getColumnClass(int col) {
            return col == 6 ? Boolean.class : String.class;
        }


        /**
         * Diese Methode liefert nur für die Spalte 6 <i>true</i> und in jedem
         * anderen Fall <i>false</i>, da ausschlie&szlig;lich die Spalte 6
         * editierbar sein soll.
         *
         * @param row Der Index der Zeile.
         * @param col Der Index der Spalte.
         *
         * @return True, wenn <i>col</i> == 6 and getRowStatus(row) == 1 | 2 sonst False.
         */
        public boolean isCellEditable(int row, int col) {
            return (col == 6 && getRowStatus(row) > 0) ? true : false;
        }


        /**
         * Diese Methode liest alle Zeilen der Datei {@link toImport} ein und
         * speichert diese in der von {@link getList()} gelieferten Liste.
         * Anschlie&szlig;end wird {@link selection} initialisiert und mit True
         * Werten gef&uuml;llt, bevor ein {@link fireTableDataChanged()}
         * geworfen wird.
         */
        public void updateList() throws Exception {
            BufferedReader in = null;

            try {
                in            = new BufferedReader(new FileReader(toImport));
                List dataList = getList();
                String   line = null;
                int     count = -1;

                while ((line = in.readLine()) != null) {
                    if (count >= 0) {
                        String[] columns = line.split(",");
                        dataList.add(columns);
                    }

                    count++;
                }

                AUIKataster.debugOutput(
                    count + " Zeilen eingelesen.",
                    getClass().getName());


                fireTableDataChanged();

                status = new int[dataList.size()];
                initSelection();
            }
            catch (FileNotFoundException fnfe) {
                AUIKataster.errorOutput(
                    "Fehler beim Lesen der Probenahme-Analyseergebnisse: " +
                    fnfe.getMessage(),
                    getClass().getName());
            }
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                }
                catch (Exception e) {
                    // do nothing
                }
            }
        }


        protected void initSelection() {
            int rows  = getRowCount();
            selection = new boolean[rows];

            for (int i = 0; i < rows; i++) {
                selection[i] = getRowStatus(i) > 0 ? true : false;
            }
        }

        /**
         * Diese Methode leert die ggf vorhanden Daten, setzt die Datei {@link
         * toImport} zur&uuml;ck und aktiviert den ersten Schritt des {@link
         * AnalyseImporter}s.
         */
        public void reset() {
            setList(new ArrayList());
            toImport = null;

            activateFileChooser(true, false);
            activateImport(false, false);

            fireTableDataChanged();
        }

        /**
         * Diese Methode sollte verwendet werden, um dieser Klasse die zu
         * lesende Datei zu &uuml;bergeben. Hier findet eine Pr&uuml;fung statt,
         * ob die Datei lesbar ist; anschlie&szlig;end wird {@link updateList()}
         * aufgerufen, um die Daten zu lesen und die Liste zu aktualisieren.
         *
         * @param f Die zu importierende Datei.
         */
        public void parse(File f) {
            if (!f.isFile() || !f.canRead()) {
                frame.showErrorMessage(
                    "Konnte die angegebene Datei '" + f.getName() +
                    "' nicht öffnen!",
                    "Fehler beim Öffnen der Datei");
            }

            reset();
            String name = f.getName();

            try {
                toImport = f;

                AUIKataster.debugOutput(
                    "Beginne Analyseergebnisse aus '" + name + "' zu lesen.",
                    getClass().getName());

                updateList();
                List data = getList();

                if (data.size() > 0) {
                    getDateiLabel().setText("Datei: " + name);

                    activateFileChooser(false, true);
                    activateImport(true, false);
                }
                else {
                    frame.showInfoMessage(
                        "Datei '" + name + "' wurde erfolgreich geladen.\n" +
                        "Es wurden jedoch keine Daten gefunden.",
                        "Keine Daten");
                }
            }
            catch (Exception e) {
                frame.showErrorMessage(
                    "Beim Lesen der Datei " + name +
                    "ist ein Fehler aufgetreten: " + e.getMessage(),
                    "Konnte Datei nicht lesen.");
            }
        }
    } // end of AnalyseImporter


    protected AnalyseImporter importer;

    protected JButton     dateiButton;
    protected JButton     importButton;
    protected JLabel      dateiLabel;
    protected JLabel      parseLabel;
    protected JLabel      importLabel;
    protected JLabel      beschreibungLabel;
    protected JScrollPane listScroller;
    protected JTable      table;

    public AnalyseImport() {
        importer = new AnalyseImporter();
        table    = new JTable(importer);

        dateiButton = new JButton("Datei wählen");
        dateiLabel  = new JLabel();
        parseLabel  = new JLabel();

        listScroller = new JScrollPane(table);

        beschreibungLabel = new JLabel(
            "<html><table width='100%'>" +
            "<tr><td style='color: green;'>Grün:</td>" +
            "<td>Import m&ouml;glich: Kennnummer und Parameter " +
            "vorhanden.</td></tr>" +
            "<tr><td style='color: FF8200;'>Orange:</td>" +
            "<td>Import m&ouml;glich: Kennnummer vorhanden, " +
            "Parameter wird angelegt.</td></tr>" +
            "<tr><td style='color: red;'>Rot:</td>" +
            "<td>Zeile nicht importierbar.</td></tr>" +
            "</table></html>");

        importButton = new JButton("Importieren");
        importLabel  = new JLabel(AuikUtils.getIcon(
            "step3_grey.png",
            "Schritt Drei"));

        activateFileChooser(true, false);
        activateImport(false, false);

        dateiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = frame.openFile(new String[]{"txt"});

                if (file != null) {
                    doImport(file);
                }
            }
        });


        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doSave();
            }
        });
    }


    public String getName() {
        return "Import Analyseergebnisse";
    }


    public String getIdentifier() {
        return "atl_analyse_import";
    }


    public String getCategory() {
        return "Labor";
    }


    public Icon getIcon() {
        return super.getIcon("ksysguard.png");
    }


    /**
     * Diese Methode erstellt das Panel, das in der Oberfl&auml;che angezeigt
     * wird. Es besteht aus folgenden Teilen:<br>
     * <ul>
     * <li>Knopf zur Auswahl einer Datei</li>
     * <li>Liste zur Anzeige der importierten Daten</li>
     * <li>Beschreibung der in der Liste benutzten Farben</li>
     * <li>Knopf zum Importieren/Persistieren der Daten</li>
     * </ul>
     */
    public JPanel getPanel() {
        if (panel != null) {
            return panel;
        }

        FormLayout layout = new FormLayout("40px,5dlu,65dlu,5dlu,175dlu:g", "");
        DefaultFormBuilder b = new DefaultFormBuilder(layout);

        b.setDefaultDialogBorder();

        b.append(getParseLabel(), getDateiButton(), getDateiLabel());
        b.appendRelatedComponentsGapRow();
        b.appendRow("f:50dlu:g");
        b.nextLine(2);

        b.append(""); // no label in front of the list
        b.append(getListScroller(), 3);
        b.appendRelatedComponentsGapRow();
        b.nextLine(2);

        b.append(""); // no label in front of the descriptions
        b.append(getBeschreibungLabel(), 3);
        b.appendRelatedComponentsGapRow();
        b.nextLine(2);

        b.append(getImportLabel(), getImportButton());

        panel = b.getPanel();

        return panel;
    }


    public JLabel getParseLabel() {
        return parseLabel;
    }


    public JLabel getDateiLabel() {
        return dateiLabel;
    }


    public JLabel getBeschreibungLabel() {
        return beschreibungLabel;
    }


    public JLabel getImportLabel() {
        return importLabel;
    }


    public JButton getDateiButton() {
        return dateiButton;
    }


    public JButton getImportButton() {
        return importButton;
    }


    public JScrollPane getListScroller() {
        return listScroller;
    }


    /**
     * Diese Methode ändert das Aussehen des Labels der Dateiauswahl.
     * Das Label kann drei verschiebene Farben annehmen:<br>
     * <ul>
     * <li>
     *  <i>active</i> und <i>success</i>gesetzt: Label gr&uuml;n hervorgehoben
     * </li>
     * <li>
     *  <i>active</i> gesetzt: Label wei&szilg; hervorgehoben
     * </li>
     * <li>
     *  <i>active</i> nicht gesetzt (<i>success</i> spielt in diesem Fall keine
     *  Rolle): Label wird ausgegraut.
     * </li>
     * </ul>
     *
     * @param active True, zum Hervorheben (wei&szilg;) des Labels
     * @param success Falls true und <i>active</i> ebenfalls true, zum
     * Hervorheben (gr&uuml;n) des Labels
     */
    protected void activateFileChooser(boolean active, boolean success) {
        if (active) {
            parseLabel.setIcon(AuikUtils.getIcon(
                "step1_w.png", "Schritt Eins"));
        }
        else {
            if (success) {
                parseLabel.setIcon(AuikUtils.getIcon(
                    "step1_g.png", "Schritt Eins"));
            }
            else {
                parseLabel.setIcon(AuikUtils.getIcon(
                    "step1_grey.png", "Schritt Eins"));
            }
        }
    }


    /**
     * Diese Methode ändert das Aussehen des Labels des Imports und aktiviert
     * bzw deaktiviert den Knopf, der das Persistieren der Daten startet. Wenn
     * <i>active</i> gesetzt ist, wird der Knopf aktiv, ansonsten wird er
     * deaktiviert. Das Label kann drei verschiebene Farben annehmen:<br>
     * <ul>
     * <li>
     *  <i>active</i> und <i>success</i>gesetzt: Label gr&uuml;n hervorgehoben
     * </li>
     * <li>
     *  <i>active</i> gesetzt: Label wei&szilg; hervorgehoben
     * </li>
     * <li>
     *  <i>active</i> nicht gesetzt (<i>success</i> spielt in diesem Fall keine
     *  Rolle): Label wird ausgegraut.
     * </li>
     * </ul>
     *
     * @param active True, zum Hervorheben des Labels
     */
    protected void activateImport(boolean active, boolean success) {
        if (active) {
            importLabel.setIcon(AuikUtils.getIcon(
                "step2_w.png", "Schritt Zwei"));
            importButton.setEnabled(true);
        }
        else {
            importButton.setEnabled(false);

            if (success) {
                importLabel.setIcon(AuikUtils.getIcon(
                    "step2_g.png", "Schritt Zwei"));
            }
            else {
                importLabel.setIcon(AuikUtils.getIcon(
                    "step2_grey.png", "Schritt Zwei"));
            }
        }
    }


    protected void doImport(File file) {
        importer.parse(file);
    }


    /**
     * Diese Methode wird aufgerufen, nachdem eine Datei mit Analyseergebnissen
     * eingelesen wurde. Es werden alle selektierten Zeilen importiert, die in
     * {@link table} ausgew&auml;hlt sind. Diese Method gibt einzelne Zeilen der
     * Tabelle an die Methode {@link processAnalyseposition} weiter, die
     * letzlich die Daten auswertet und einer Probenahme zuordnet.
     */
    protected void doSave() {
        AUIKataster.debugOutput(
            "Speichere die importieren Daten.",
            getClass().getName());

        List data = importer.getSelectedRows();
        int  size = data.size();

        for (int i = 0; i < size; i++) {
            String[] row = (String[]) data.get(i);
            AnalyseProcessor.process(row);
        }

        activateImport(false, true);
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
