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
package de.bielefeld.umweltamt.aui.module;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Diese Klasse ist ein eigenst&auml;ndiges Modul. Es hat die Aufgabe, einen
 * Import-Mechanismus bereitzustellen, mit dem sich Analyseergebnisse von
 * Probenahmen importieren lassen. Der Import gliedert sich in folgende Stufen:<br>
 * <ul>
 * <li>Datei w&auml;hlen</li>
 * <li>Pr&uuml;fung der Kennnummer und Parameter</li>
 * <li>Anzeige der importierten Daten in Form einer Liste</li>
 * <li>Speichern der Daten mittels Knopfdruck</li>
 * <li>Plausibilit&auml;tspr&uuml;fung durch den Nutzer</li>
 * <li>Freigabe durch Nutzer mittels Knopfdruck</li>
 * </ul>
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class AnalyseImport extends AbstractModul {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Diese Klasse importiert die Laborergebnisse einer Probenahme in Form
     * einer Textdatei und stellt diese als {@link ListTableModel} zur
     * Verf&uuml;gung.
     * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
     */
    private class AnalyseImporter extends ListTableModel {
        private static final long serialVersionUID = 5564507170122427828L;
        protected File toImport;
        protected boolean[] selection;
        protected int[] status;

        public AnalyseImporter() {
            super(new String[] {"Kennnummer", "Ordnungsbegriff", "Parameter",
                    "GrKl", "Wert", "Einheit","Analysemethode", "Plausibel"}, false);
        }

        @Override
        public Object getValueAt(int row, int col) {
            if (!rowExists(row)) {
                return null;
            }

            String[] columns = (String[]) getObjectAtRow(row);
//            List<?>  data    = getList();

            int status = getRowStatus(row);

            switch (col) {
                case 0:
                    return getColoredColumn(status,
                        AnalyseProcessor.unquote(columns[0]));
                case 1:
                    return getColoredColumn(status,
                        AnalyseProcessor.unquote(columns[2]));
                case 2:
                    return getColoredColumn(status,
                        AnalyseProcessor.unquote(columns[3]));
                case 3:
                    return getColoredColumn(status,
                        AnalyseProcessor.unquote(columns[4]));
                case 4:
                    return getColoredColumn(status,
                        AnalyseProcessor.unquote(columns[5]));
                case 5:
                    return getColoredColumn(status,
                        AnalyseProcessor.unquote(columns[7]));
                case 6:
                    return getColoredColumn(status,
                        AnalyseProcessor.unquote(columns[8]));    
                case 7:
                    return this.selection[row];
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
         * @param row Der Index einer Zeile.
         * @return -1, 1 oder 2.
         */
        protected int getRowStatus(int row) {
            if (this.status[row] == 0) {
                String[] columns = (String[]) getObjectAtRow(row);

                this.status[row] = (columns.length < 8) ? -1 :
                    AnalyseProcessor.importStatus(
                        AnalyseProcessor.unquote(columns[0]),
                        AnalyseProcessor.unquote(columns[2]),
                        AnalyseProcessor.unquote(columns[6]));
            }

            return this.status[row];
        }

        /**
         * Diese Methode liefert <i>txt</i> in einem HTML-String. Der
         * HTML-String dient dazu <i>txt</i> in unterschiedlichen Farben
         * darzustellen. Die Farbe ist vom <i>status</i> abh&auml;ngig.<br>
         * <ul>
         * <li>Status == -1 : rot gef&auml;rbter String</li>
         * <li>Status == 1 : gr&uuml;n gef&auml;rbter String</li>
         * <li>Status == 2 : orange gef&auml;rbter String</li>
         * </ul>
         * @param status Status sollte -1, 1 oder 2 sein.
         * @param txt Der auszugebende Text.
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
                case 1:
                    sb.append("<font color='green'>");
                    sb.append(txt);
                    sb.append("</font>");
                    break;
                case 2:
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
         * @param value Der Wert.
         * @param row Der Index einer Zeile.
         * @param col Der Index einer Spalte.
         */
        @Override
        public void setValueAt(Object value, int row, int col) {
            if (col != 7) {
                return;
            }

            this.selection[row] = Boolean.TRUE.equals(value);
        }

        /**
         * Diese Methode liefert in dieser Klasse <b>immer</b> <i>null</i>. Es
         * muss stattdessen {@link getValueAt(int, int)} verwendet werden.
         * @param row Ein Objekt, welches die Zeile representiert.
         * @param col Der Index einer Spalte.
         * @return <i>null</i>.
         */
        @Override
        public Object getColumnValue(Object row, int col) {
            return null;
        }

        /**
         * Diese Methode liefert eine Liste mit allen selektierten Zeilen.
         * @return Liste mit allen selektierten Zeilen.
         */
        public List<String[]> getSelectedRows() {
            List<String[]> selected = new ArrayList<String[]>();

            for (int i = 0; i < this.selection.length; i++) {
                boolean s = Boolean.TRUE.equals(getValueAt(i, 7));
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
         * @param col Der Index einer Spalte.
         * @return <code>Boolean.class</code> falls <i>col</i>==6 , sonst
         *         <code>String.class</code>
         */
        @Override
        public Class<?> getColumnClass(int col) {
            return col == 7 ? Boolean.class : String.class;
        }

        /**
         * Diese Methode liefert nur für die Spalte 6 <i>true</i> und in jedem
         * anderen Fall <i>false</i>, da ausschlie&szlig;lich die Spalte 6
         * editierbar sein soll.
         * @param row Der Index der Zeile.
         * @param col Der Index der Spalte.
         * @return True, wenn <i>col</i> == 6 and getRowStatus(row) == 1 | 2
         *         sonst False.
         */
        @Override
        public boolean isCellEditable(int row, int col) {
            return (col == 7 && getRowStatus(row) > 0) ? true : false;
        }

        /**
         * Diese Methode liest alle Zeilen der Datei {@link toImport} ein und
         * speichert diese in der von {@link getList()} gelieferten Liste.
         * Anschlie&szlig;end wird {@link selection} initialisiert und mit True
         * Werten gef&uuml;llt, bevor ein {@link fireTableDataChanged()}
         * geworfen wird.
         */
        @Override
        public void updateList() throws Exception {
            BufferedReader in = null;

            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(this.toImport), "UTF-8"));
                List<String[]> dataList = getList();
                String line = null;
                int count = 0;
                int bad = 0;

                while ((line = in.readLine()) != null) {
                    if (line.startsWith("\uFEFF")) {
                    	line = line.substring(1);
                    }
                    if (line.startsWith(".")) {
                        continue;
                    }

                    String[] columns = line.split("','");

                    if (columns == null) {
                        log.error("Fehler beim Lesen einer Analyse-Zeile: "
                            + "Es konnte keine komma-serparierten Spalten "
                            + "gefunden werden!");
                        bad++;
                    } else if (columns.length < 9) {
                        log.error("Fehler beim Lesen einer Analyse-Zeile: "
                            + "Es wurden eine kaputte Analyse-Zeile "
                            + "gefunden!");
                        bad++;
                    } else {
                        dataList.add(columns);
                    }

                    count++;
                }

                if (bad > 0) {
                    GUIManager
                        .getInstance()
                        .showInfoMessage(
                            "Beim Lesen des Analyse-Imports war/en "
                                + bad
                                + " kaputte "
                                + "Zeile/n enthalten. Diese wurde/n ignoriert.\n"
                                + "Weitere Informationen sind im Logfile enthalten.",
                            "Ungültige Zeilen im Analyse-Import");
                }

                log.debug(count + " Zeilen eingelesen.");

                fireTableDataChanged();

                this.status = new int[dataList.size()];
                initSelection();
            } catch (FileNotFoundException fnfe) {
                log.error("Fehler beim Lesen der Probenahme-Analyseergebnisse: "
                    + fnfe.getMessage());
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e) {
                    // do nothing
                }
            }
        }

        protected void initSelection() {
            int rows = getRowCount();
            this.selection = new boolean[rows];

            for (int i = 0; i < rows; i++) {
                this.selection[i] = getRowStatus(i) > 0 ? true : false;
            }
        }

        /**
         * Diese Methode leert die ggf vorhanden Daten, setzt die Datei
         * {@link toImport} zur&uuml;ck und aktiviert den ersten Schritt des
         * {@link AnalyseImporter}s.
         */
        public void reset() {
            setList(new ArrayList<String[]>());
            this.toImport = null;

            activateFileChooser(true, false);
            activateImport(false, false);

            fireTableDataChanged();
        }

        /**
         * Diese Methode sollte verwendet werden, um dieser Klasse die zu
         * lesende Datei zu &uuml;bergeben. Hier findet eine Pr&uuml;fung statt,
         * ob die Datei lesbar ist; anschlie&szlig;end wird {@link updateList()}
         * aufgerufen, um die Daten zu lesen und die Liste zu aktualisieren.
         * @param f Die zu importierende Datei.
         */
        public void parse(File f) {
            if (!f.isFile() || !f.canRead()) {
                GUIManager.getInstance().showErrorMessage(
                    "Konnte die angegebene Datei '" + f.getName()
                        + "' nicht öffnen!", "Fehler beim Öffnen der Datei");
            }

            reset();
            String name = f.getName();

            try {
                this.toImport = f;

                log.debug("Beginne Analyseergebnisse aus '" + name
                    + "' zu lesen.");
                panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                updateList();
                panel.setCursor(Cursor.getDefaultCursor());
                List<?> data = getList();

                if (data.size() > 0) {
                    getDateiLabel().setText("Datei: " + name);

                    activateFileChooser(false, true);
                    activateImport(true, false);
                } else {
                    GUIManager.getInstance().showInfoMessage(
                        "Datei '" + name + "' wurde erfolgreich geladen.\n"
                            + "Es wurden jedoch keine Daten gefunden.",
                        "Keine Daten");
                }
            } catch (Exception e) {
                GUIManager.getInstance().showErrorMessage(
                    "Beim Lesen der Datei " + name
                        + "ist ein Fehler aufgetreten: " + e.getMessage(),
                    "Konnte Datei nicht lesen.");
            }
        }
    } // end of AnalyseImporter

    protected AnalyseImporter importer;

    protected JButton dateiButton;
    protected JButton importButton;
    protected JLabel dateiLabel;
    protected JLabel parseLabel;
    protected JLabel importLabel;
    protected JLabel beschreibungLabel;
    protected JScrollPane listScroller;
    protected JTable table;

    public AnalyseImport() {
        this.importer = new AnalyseImporter();
        this.table = new JTable(this.importer);

        this.dateiButton = new JButton("Datei wählen");
        this.dateiLabel = new JLabel();
        this.parseLabel = new JLabel();

        this.listScroller = new JScrollPane(this.table);

        this.beschreibungLabel = new JLabel("<html><table width='100%'>"
            + "<tr><td style='color: green;'>Grün:</td>"
            + "<td>Import m&ouml;glich: Kennnummer und Parameter "
            + "vorhanden.</td></tr>"
            + "<tr><td style='color: FF8200;'>Orange:</td>"
            + "<td>Import m&ouml;glich: Kennnummer vorhanden, "
            + "Parameter wird angelegt.</td></tr>"
            + "<tr><td style='color: red;'>Rot:</td>"
            + "<td>Zeile nicht importierbar.</td></tr>" + "</table></html>");

        this.importButton = new JButton("Importieren");
        this.importLabel = new JLabel(AuikUtils.getIcon("step3_grey.png",
            "Schritt Drei"));

        activateFileChooser(true, false);
        activateImport(false, false);

        this.dateiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = AnalyseImport.this.frame
                    .openFile(new String[] {"txt"});

                if (file != null) {
                	panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    doImport(file);
                	panel.setCursor(Cursor.getDefaultCursor());
                }
            }
        });

        this.importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSave();
            }
        });
    }

    @Override
    public String getName() {
        return "Import Analyseergebnisse";
    }

    @Override
    public String getIdentifier() {
        return "atl_analyse_import";
    }

    @Override
    public String getCategory() {
        return "Labor";
    }

    @Override
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
    @Override
    public JPanel getPanel() {
        if (this.panel != null) {
            return this.panel;
        }

        FormLayout layout = new FormLayout("40px,5dlu,65dlu,5dlu,175dlu:g", "");
        DefaultFormBuilder b = new DefaultFormBuilder(layout);

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

        this.panel = b.getPanel();
        this.panel.setBorder(Paddings.DIALOG);

        return this.panel;
    }

    public JLabel getParseLabel() {
        return this.parseLabel;
    }

    public JLabel getDateiLabel() {
        return this.dateiLabel;
    }

    public JLabel getBeschreibungLabel() {
        return this.beschreibungLabel;
    }

    public JLabel getImportLabel() {
        return this.importLabel;
    }

    public JButton getDateiButton() {
        return this.dateiButton;
    }

    public JButton getImportButton() {
        return this.importButton;
    }

    public JScrollPane getListScroller() {
        return this.listScroller;
    }

    /**
     * Diese Methode ändert das Aussehen des Labels der Dateiauswahl. Das Label
     * kann drei verschiebene Farben annehmen:<br>
     * <ul>
     * <li><i>active</i> und <i>success</i>gesetzt: Label gr&uuml;n
     * hervorgehoben</li>
     * <li><i>active</i> gesetzt: Label wei&szilg; hervorgehoben</li>
     * <li><i>active</i> nicht gesetzt (<i>success</i> spielt in diesem Fall
     * keine Rolle): Label wird ausgegraut.</li>
     * </ul>
     * @param active True, zum Hervorheben (wei&szilg;) des Labels
     * @param success Falls true und <i>active</i> ebenfalls true, zum
     *            Hervorheben (gr&uuml;n) des Labels
     */
    protected void activateFileChooser(boolean active, boolean success) {
        if (active) {
            this.parseLabel.setIcon(AuikUtils.getIcon("step1_w.png",
                "Schritt Eins"));
        } else {
            if (success) {
                this.parseLabel.setIcon(AuikUtils.getIcon("step1_g.png",
                    "Schritt Eins"));
            } else {
                this.parseLabel.setIcon(AuikUtils.getIcon("step1_grey.png",
                    "Schritt Eins"));
            }
        }
    }

    /**
     * Diese Methode ändert das Aussehen des Labels des Imports und aktiviert
     * bzw deaktiviert den Knopf, der das Persistieren der Daten startet. Wenn
     * <i>active</i> gesetzt ist, wird der Knopf aktiv, ansonsten wird er
     * deaktiviert. Das Label kann drei verschiebene Farben annehmen:<br>
     * <ul>
     * <li><i>active</i> und <i>success</i>gesetzt: Label gr&uuml;n
     * hervorgehoben</li>
     * <li><i>active</i> gesetzt: Label wei&szilg; hervorgehoben</li>
     * <li><i>active</i> nicht gesetzt (<i>success</i> spielt in diesem Fall
     * keine Rolle): Label wird ausgegraut.</li>
     * </ul>
     * @param active True, zum Hervorheben des Labels
     */
    protected void activateImport(boolean active, boolean success) {
        if (active) {
            this.importLabel.setIcon(AuikUtils.getIcon("step2_w.png",
                "Schritt Zwei"));
            this.importButton.setEnabled(true);
        } else {
            this.importButton.setEnabled(false);

            if (success) {
                this.importLabel.setIcon(AuikUtils.getIcon("step2_g.png",
                    "Schritt Zwei"));
            } else {
                this.importLabel.setIcon(AuikUtils.getIcon("step2_grey.png",
                    "Schritt Zwei"));
            }
        }
    }

    protected void doImport(File file) {
        this.importer.parse(file);
    }

    /**
     * Diese Methode wird aufgerufen, nachdem eine Datei mit Analyseergebnissen
     * eingelesen wurde. Es werden alle selektierten Zeilen importiert, die in
     * {@link table} ausgew&auml;hlt sind. Diese Method gibt einzelne Zeilen der
     * Tabelle an die Methode {@link processAnalyseposition} weiter, die
     * letzlich die Daten auswertet und einer Probenahme zuordnet.
     */
    protected void doSave() {
        log.debug("Speichere die importieren Daten.");

        List<?> data = this.importer.getSelectedRows();
        int size = data.size();
        int count = 0;

        for (int i = 0; i < size; i++) {
            String[] row = (String[]) data.get(i);
            if (AnalyseProcessor.process(row)) {
                count++;
            }
        }

        GUIManager.getInstance().showInfoMessage(
            "Es wurden " + count + " Zeilen der Analyseergebnisse erfolgreich"
                + "\nin die Datenbank gespeichert.", "Import erfolgreich");

        activateImport(false, true);
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
