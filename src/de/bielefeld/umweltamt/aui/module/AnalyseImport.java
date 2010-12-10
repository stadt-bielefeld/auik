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

            activateFileChooser(true, false);
            activateImport(false, false);

            fireTableDataChanged();
        }

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

        // TODO just take the selected rows of JTable instead of all rows
        List data = importer.getList();
        int  size = data.size();

        for (int i = 0; i < size; i++) {
            String[] row = (String[]) data.get(i);
            AnalyseProcessor.process(row);
        }

        activateImport(false, true);
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
