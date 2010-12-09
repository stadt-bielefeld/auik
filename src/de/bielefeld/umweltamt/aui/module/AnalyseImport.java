/*
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.module;

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
        protected File toImport;

        public AnalyseImporter() {
            super(
                new String[] {
                    "Kennnummer",
                    "TODO",
                    "TODO",
                    "Parameter",
                    "TODO",
                    "TODO",
                    "TODO",
                    "TODO",
                    "TODO"
                },
                false);
        }

        /**
         * TODO IMPLEMENT ME
         */
        public Object getColumnValue(Object row, int col) {
            return "TODO";
        }

        public void updateList() throws Exception {
            BufferedReader in = null;

            try {
                in            = new BufferedReader(new FileReader(toImport));
                List dataList = getList();
                String   line = null;
                int     count = 0;

                while ((line = in.readLine()) != null) {
                    String[] columns = line.split(",");
                    dataList.add(columns);

                    count++;
                }

                AUIKataster.debugOutput(
                    count + " Zeilen eingelesen.",
                    getClass().getName());

                fireTableDataChanged();
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

        public void reset() {
            setList(new ArrayList());
            toImport = null;

            fireTableDataChanged();
        }

        public void parse(File file) {
            if (!file.isFile() || !file.canRead()) {
                frame.showErrorMessage(
                    "Konnte die angegebene Datei '" + file.getName() +
                    "' nicht öffnen!",
                    "Fehler beim Öffnen der Datei");
            }
        }
    } // end of AnalyseImporter


    protected AnalyseImporter importer;

    protected JButton     dateiButton;
    protected JButton     importButton;
    protected JLabel      dateiLabel;
    protected JLabel      parseLabel;
    protected JLabel      importLabel;
    protected JLabel      listLabel;
    protected JLabel      beschreibungLabel;
    protected JScrollPane listScroller;
    protected JTable      table;

    public AnalyseImport() {
        importer = new AnalyseImporter();
        table    = new JTable(importer);

        dateiButton = new JButton("Datei wählen");
        dateiLabel  = new JLabel();
        parseLabel  = new JLabel(AuikUtils.getIcon(
            "step1_grey.png",
            "Schritt Eins"));

        listScroller = new JScrollPane(table);
        listLabel    = new JLabel(AuikUtils.getIcon(
            "step2_grey.png",
            "Schritt Zwei"));

        beschreibungLabel = new JLabel(
            "<html><table width='100%'>" +
            "<tr><td style='color: green;'>Grün:</td>" +
            "<td>Probenummer und Kennnummer vorhanden.</td></tr>" +
            "<tr><td style='color: blue;'>Blau:</td>" +
            "<td>Importierbar und für den Import ausgewählt.</td></tr>" +
            "<tr><td style='color: red;'>Rot:</td>" +
            "<td>Zeile nicht importierbar.</td></tr>" +
            "</table></html>");

        importButton = new JButton("Importieren");
        importLabel  = new JLabel(AuikUtils.getIcon(
            "step3_grey.png",
            "Schritt Drei"));

        // TODO add action listener to both buttons
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

        b.append(getListLabel());
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


    public JLabel getListLabel() {
        return listLabel;
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
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
