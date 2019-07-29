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
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.atl.Probeart;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.atl.Sielhaut;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ProbenahmenModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.PDFExporter;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;

/**
 * Das "Probepunkt"-Tab des BasisObjektBearbeiten-Moduls
 * @author David Klotz
 */
public class ProbepunktPanel extends JPanel {
    private static final long serialVersionUID = 3663375435585578751L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JComboBox probePktArtBox = null;
    private JComboBox probeKABox = null;
    private JComboBox sachbearbeiterBox = null;
    private JTextField brancheFeld = null;
    private JFormattedTextField probePktNrFeld = null;
    private JTextArea probePktBeschreibungsArea = null;
    private JTable probenahmeTabelle = null;
    private JPanel neueProbePanel = null;
    private JTextField kennummerFeld = null;
    private JDateChooser datumsChooser = null;
    private JButton anlegenButton = null;
    private JButton savePktButton = null;

    private ProbenahmenModel probenahmenModel = null;

    // Daten
    private Messstelle probepkt = null;
    private Sielhaut sielhaut = null;
    private Probeart[] probearten = null;
    private Klaeranlage[] klaeranlagen = null;
    private Sachbearbeiter[] sachbearbeiter = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;
    private JButton printDeckblattButton = null;

    public ProbepunktPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Probenahmepunkt";
        this.hauptModul = hauptModul;
        this.probenahmenModel = new ProbenahmenModel();
        JScrollPane beschScroller = new JScrollPane(
                getProbePktBeschreibungsArea(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane tabellenScroller = new JScrollPane(getProbenahmeTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane objektverknuepfungScroller = new JScrollPane(
                getObjektverknuepungTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, true, 1, 0, 1, 1,
        0, 0, 5, 5);

        PanelBuilder eigenschaften = new PanelBuilder(PanelBuilder.NORTHEAST, true, false, 0, 0, 1, 1,
                0, 0, 5, 5);

        eigenschaften.setWrapLabelComponents(false);
        eigenschaften.setPreferedSize(650, 750);
        eigenschaften.addComponent(getProbePktArtBox(), "Art");
        eigenschaften.setWrapLabelComponents(true);
        eigenschaften.setWeightX(1);
        eigenschaften.addComponent(getProbePktNrFeld(), "Nr:", true);
        eigenschaften.setWeightX(0);
        eigenschaften.setWrapLabelComponents(false);
        eigenschaften.addComponent(getProbeKABox(), "Kläranlage:", true, true);
        eigenschaften.addComponent(getSachbearbeiterBox(), "SachbearbeiterIn:", true, true);
        eigenschaften.addComponent(getBrancheFeld(), "Branche:", true, true);

        builder.setMinimumSize(650, 750);
        builder.addSeparator("Fachdaten", true);
        builder.setAnchor(PanelBuilder.NORTHEAST);
        builder.addComponent(eigenschaften.getPanel(), true);
        builder.addComponent(
                PanelBuilder.buildRightAlignedButtonToolbar(getSavePktButton()), true);
        builder.setAnchor(PanelBuilder.NORTHWEST);
        builder.addSeparator("Beschreibung", true);
        builder.setWeightY(0.2);
        builder.addComponent(beschScroller, true);
        builder.setWeightY(0);
        builder.addSeparator("Probenahmen", true);
        builder.setWeightY(0.3);
        builder.addComponent(tabellenScroller, true);
        builder.setWeightY(0);
        builder.addComponent(getNeueProbePanel(), true);
        builder.addSeparator("Veknüpfte Objekte", true);
        builder.setWeightY(0.3);
        builder.addComponent(objektverknuepfungScroller, true);
        builder.setWeight(0, 0);
        builder.fillRow();
        builder.setInsets(0, 0, 0, 5);
        builder.addComponent(PanelBuilder.buildRightAlignedButtonToolbar(
                getPrintDeckblattButton(), getSelectObjektButton()), true);


        PanelBuilder content = new PanelBuilder(PanelBuilder.NORTHWEST, true, true, 0, 0, 1, 1,
                0, 0, 5, 5);
        content.setEmptyBorder(15);
        content.addComponent(builder.getPanel());
        content.fillRow(true);
        content.fillColumn();

        this.setLayout(new BorderLayout());
        this.add(content.getPanel());
    }

    public void fetchFormData() throws RuntimeException {
        this.probepkt = Messstelle.findByObjektId(
            this.hauptModul.getObjekt().getId());
        log.debug("Probepunkt aus DB geholt: " + this.probepkt);

        if (this.probearten == null) {
            this.probearten = DatabaseQuery.getProbearten();
        }
        if (this.klaeranlagen == null) {
            this.klaeranlagen = DatabaseQuery.getKlaeranlage();
        }
        if (this.sachbearbeiter == null) {
            this.sachbearbeiter = DatabaseQuery.getEnabledSachbearbeiter();
        }
    }

    public void updateForm() throws RuntimeException {
        if (this.probearten != null) {
            getProbePktArtBox().setModel(
                new DefaultComboBoxModel(this.probearten));
        }
        if (this.klaeranlagen != null) {
            getProbeKABox().setModel(
                new DefaultComboBoxModel(this.klaeranlagen));
        }
        if (this.sachbearbeiter != null) {
            getSachbearbeiterBox().setModel(
                new DefaultComboBoxModel(this.sachbearbeiter));
            getSachbearbeiterBox().setEditable(true);
        }

        if (this.probepkt != null) {
            getProbePktArtBox().setSelectedItem(this.probepkt.getProbeart());
            getProbeKABox().setSelectedItem(this.probepkt.getKlaeranlage());
            getSachbearbeiterBox().setSelectedItem(
                this.probepkt.getSachbearbeiter());

            if (this.probepkt.getNrProbepkt() != null) {
                getProbePktNrFeld().setValue(this.probepkt.getNrProbepkt());
            }

            getBrancheFeld().setText(this.probepkt.getBranche());
            getProbePktBeschreibungsArea().setText(
                this.hauptModul.getObjekt().getBeschreibung());

            this.probenahmenModel.setProbepunkt(this.probepkt);
            this.probenahmenModel.updateList();

            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
        }
    }

    public void clearForm() {
        getProbePktNrFeld().setText(null);
        getProbePktBeschreibungsArea().setText(null);
        getKennummerFeld().setText("");
        getBrancheFeld().setText("");
    }

    public void enableAll(boolean enabled) {
        getProbePktArtBox().setEnabled(enabled);
        getProbeKABox().setEnabled(enabled);
        getProbePktNrFeld().setEnabled(enabled);
        getProbePktBeschreibungsArea().setEnabled(enabled);
        getBrancheFeld().setEnabled(enabled);

        getProbenahmeTabelle().setEnabled(enabled);
        getKennummerFeld().setEnabled(enabled);
        getDatumsChooser().setEnabled(enabled);
        getAnlegenButton().setEnabled(enabled);

        getSavePktButton().setEnabled(enabled);
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Bearbeitet eine Probenahme.
     */
    public void editProbenahme(Probenahme probe) {
        ProbenEditor editDialog = new ProbenEditor(probe,
            this.hauptModul.getFrame(), false);
        editDialog.setLocationRelativeTo(this.hauptModul.getFrame());

        editDialog.setVisible(true);

        this.probenahmenModel.updateList();
    }

    /**
     * Legt eine neue Probenahme an.
     */
    public void neueProbenahme(String kennNummer, Timestamp datum) {
        if (this.probepkt != null) {
            boolean exists = DatabaseQuery.probenahmeExists(kennNummer);
            if (!exists) {
                Probenahme probe = new Probenahme();
                probe.setKennummer(kennNummer);
                probe.setDatumDerEntnahme(datum);
                probe
                    .setAnalysepositions(new HashSet<Analyseposition>());
                probe.setMessstelle(this.probepkt);

                ProbenEditor editDialog = new ProbenEditor(probe,
                    this.hauptModul.getFrame(), true);
                editDialog.setVisible(true);

                this.probenahmenModel.updateList();
            } else {
                this.hauptModul.getFrame().changeStatus(
                    "Eine Probenahme mit dieser Kennnummer existiert schon!",
                    HauptFrame.ERROR_COLOR);
            }
        } else {
            this.hauptModul.getFrame()
                .changeStatus("Fehler beim Anlegen: Kein Probepunkt!",
                    HauptFrame.ERROR_COLOR);
        }
    }

    private boolean saveProbepunktDaten() {
        boolean success;

        if (this.probepkt != null) {
            // Eingegebene Daten für den Probepunkt �bernehmen
            if (getProbePktArtBox().getSelectedItem() != null) {
                this.probepkt.setProbeart((Probeart) getProbePktArtBox()
                    .getSelectedItem());
            }
            if (getProbeKABox().getSelectedItem() != null) {
                this.probepkt
                    .setKlaeranlage((Klaeranlage) getProbeKABox()
                        .getSelectedItem());
            }
            if (getSachbearbeiterBox().getSelectedItem() != null) {
                this.probepkt
                    .setSachbearbeiter((Sachbearbeiter) getSachbearbeiterBox()
                        .getSelectedItem());
            }

            String branche = this.brancheFeld.getText();
            if ("".equals(branche)) {
                this.probepkt.setBranche(null);
            } else {
                this.probepkt.setBranche(branche);
            }

            if (getProbePktNrFeld().getValue() != null) {
                this.probepkt.setNrProbepkt(((IntegerField) getProbePktNrFeld())
                    .getIntValue());
            }

            success = this.probepkt.merge();

        } else {
            success = false;
        }

        return success;
    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.probepkt == null) {
            // Neuen Probepunkt erzeugen
            this.probepkt = new Messstelle();
            // Objekt_Id setzen
            this.probepkt.setObjekt(this.hauptModul.getObjekt());

            // Probepunkt speichern
            if (this.probepkt.merge()) {
                log.debug("Neuer Probepunkt " + this.probepkt + " gespeichert.");
                if (this.probepkt.getObjekt().getObjektarten().getId() == 40) {
                	this.sielhaut = new Sielhaut();
                	this.sielhaut.setMessstelle(probepkt);
                	this.sielhaut.merge();
                }
            }
        }
    }

    private JComboBox getProbePktArtBox() {
        if (this.probePktArtBox == null) {
            this.probePktArtBox = new JComboBox();
        }
        return this.probePktArtBox;
    }

    private JComboBox getProbeKABox() {
        if (this.probeKABox == null) {
            this.probeKABox = new JComboBox();
        }
        return this.probeKABox;
    }

    private JComboBox getSachbearbeiterBox() {
        if (this.sachbearbeiterBox == null) {
            this.sachbearbeiterBox = new JComboBox();
        }
        return this.sachbearbeiterBox;
    }

    private JTextField getBrancheFeld() {
        if (this.brancheFeld == null) {
            this.brancheFeld = new LimitedTextField(50, "");
        }
        return this.brancheFeld;
    }

    private JFormattedTextField getProbePktNrFeld() {
        if (this.probePktNrFeld == null) {
            this.probePktNrFeld = new IntegerField();
        }
        return this.probePktNrFeld;
    }

    private JTextArea getProbePktBeschreibungsArea() {
        if (this.probePktBeschreibungsArea == null) {
            this.probePktBeschreibungsArea = new JTextArea();
            this.probePktBeschreibungsArea.setLineWrap(true);
            this.probePktBeschreibungsArea.setWrapStyleWord(true);
            this.probePktBeschreibungsArea.setEditable(false);
            this.probePktBeschreibungsArea
                .setToolTipText("Diese Beschreibung kann unter \"Objekt\" geändert werden.");
            this.probePktBeschreibungsArea.setBackground(this.getBackground());
        }
        return this.probePktBeschreibungsArea;
    }

    private JTable getProbenahmeTabelle() {
        if (this.probenahmeTabelle == null) {
            this.probenahmeTabelle = new JTable(this.probenahmenModel);
            // probenahmeTabelle.setBackground(Color.BLUE);
            this.probenahmeTabelle.getColumnModel().getColumn(0)
                .setMaxWidth(75);
            this.probenahmeTabelle.getColumnModel().getColumn(1)
                .setMaxWidth(70);
            this.probenahmeTabelle.getColumnModel().getColumn(2)
                .setMaxWidth(60);
            this.probenahmeTabelle.getColumnModel().getColumn(3)
                .setPreferredWidth(100);
            this.probenahmeTabelle.getColumnModel().getColumn(4)
                .setPreferredWidth(100);
            this.probenahmeTabelle.setGridColor(new Color(230, 230, 230));
            this.probenahmeTabelle
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.probenahmeTabelle.setColumnSelectionAllowed(false);
            this.probenahmeTabelle.setRowSelectionAllowed(true);
            this.probenahmeTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = ProbepunktPanel.this.probenahmeTabelle
                                .rowAtPoint(origin);

                            Probenahme probe = ProbepunktPanel.this.probenahmenModel
                                .getRow(row);
                            editProbenahme(probe);
                        }
                    }
                });

            // Den KeyStroke holen, der "Enter" repräsentiert
            KeyStroke enterKeyStroke = KeyStroke.getKeyStroke(
                KeyEvent.VK_ENTER, 0, false);
            // Den "Enter"-KeyStroke in die InputMap der Tabelle einfügen
            this.probenahmeTabelle.getInputMap().put(enterKeyStroke, "ENTER");
            // Eine neue Action fürs editieren erzeugen
            Action editAction = new AbstractAction() {
                private static final long serialVersionUID = -7537228135751378632L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = ProbepunktPanel.this.probenahmeTabelle
                        .getSelectedRow();
                    // Natürlich nur editieren, wenn wirklich eine Zeile
                    // ausgewählt ist
                    if (row != -1) {
                        Probenahme probe = ProbepunktPanel.this.probenahmenModel
                            .getRow(row);
                        editProbenahme(probe);
                    }
                }
            };
            // Diese Action dem "Enter"-KeyStroke zuweisen
            this.probenahmeTabelle.getActionMap().put("ENTER", editAction);

            KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, 0, false);
            this.probenahmeTabelle.getInputMap().put(deleteKeyStroke, "DEL");
            Action removeAction = new AbstractAction() {
                private static final long serialVersionUID = -4910733866626541511L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = ProbepunktPanel.this.probenahmeTabelle
                        .getSelectedRow();
                    if (row != -1
                        && ProbepunktPanel.this.probenahmeTabelle
                            .getEditingRow() == -1) {
                        Probenahme probe = ProbepunktPanel.this.probenahmenModel
                            .getRow(row);
                        if (GUIManager
                            .getInstance()
                            .showQuestion(
                                "Soll die Probenahme "
                                    + probe.getKennummer()
                                    + " wirklich inkl. aller Analysen gelöscht werden?",
                                "Löschen bestätigen")) {
                            ProbepunktPanel.this.probenahmenModel
                                .removeRow(row);
                            log.debug("Probe " + probe.getKennummer()
                                + " wurde gelöscht!");
                        }
                    }
                }
            };
            this.probenahmeTabelle.getActionMap().put("DEL", removeAction);
        }
        return this.probenahmeTabelle;
    }

    private JPanel getNeueProbePanel() {
        if (this.neueProbePanel == null) {
            // neueProbePanel = new JPanel();

            PanelBuilder anlegen = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                    5, 0, 5, 5);
            anlegen.addComponent(getKennummerFeld(), "Kennummer:");
            anlegen.addComponent(getDatumsChooser(), "Datum:");
            anlegen.addComponent(getAnlegenButton(), true, true);

            this.neueProbePanel = anlegen.getPanel();
        }
        return this.neueProbePanel;
    }

    private JTextField getKennummerFeld() {
        if (this.kennummerFeld == null) {
            this.kennummerFeld = new LimitedTextField(50, "");
        }

        return this.kennummerFeld;
    }

    private JDateChooser getDatumsChooser() {
        if (this.datumsChooser == null) {
            this.datumsChooser = new JDateChooser(DateUtils.FORMAT_DEFAULT,
                false);
        }

        return this.datumsChooser;
    }

    private JButton getAnlegenButton() {
        if (this.anlegenButton == null) {
            this.anlegenButton = new JButton("Anlegen");

            this.anlegenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (getKennummerFeld().getText().trim().equals("")) {
                        getKennummerFeld().requestFocus();
                        ProbepunktPanel.this.hauptModul.getFrame()
                            .changeStatus("Leere Kennummer!",
                                HauptFrame.ERROR_COLOR);
                    } else {
                        String kennNummer = getKennummerFeld().getText().trim()
                            .replaceAll(" ", "");
                        Timestamp datum = new Timestamp(getDatumsChooser()
                            .getDate().getTime());
                        neueProbenahme(kennNummer, datum);
                    }
                }
            });
        }

        return this.anlegenButton;
    }

    private JButton getSavePktButton() {
        if (this.savePktButton == null) {
            this.savePktButton = new JButton("Probepunkt speichern");

            this.savePktButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveProbepunktDaten()) {
                        ProbepunktPanel.this.hauptModul.getFrame()
                            .changeStatus(
                                "Probepunkt "
                                    + ProbepunktPanel.this.probepkt
                                        .getId()
                                    + " erfolgreich gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                        // hauptModul.setNew(false);
                    } else {
                        ProbepunktPanel.this.hauptModul.getFrame()
                            .changeStatus(
                                "Fehler beim Speichern von Probepunkt!",
                                HauptFrame.ERROR_COLOR);
                    }

                    ProbepunktPanel.this.hauptModul.fillForm();
                }
            });
        }

        return this.savePktButton;
    }

    private JTable getObjektverknuepungTabelle() {

        if (this.objektVerknuepfungModel == null) {
            this.objektVerknuepfungModel = new ObjektVerknuepfungModel(
                this.hauptModul.getObjekt());

            if (this.objektverknuepfungTabelle == null) {
                this.objektverknuepfungTabelle = new JTable(
                    this.objektVerknuepfungModel);
            } else {
                this.objektverknuepfungTabelle
                    .setModel(this.objektVerknuepfungModel);
            }
            this.objektverknuepfungTabelle.getColumnModel().getColumn(0)
                .setPreferredWidth(5);
            this.objektverknuepfungTabelle.getColumnModel().getColumn(1)
                .setPreferredWidth(100);
            this.objektverknuepfungTabelle.getColumnModel().getColumn(2)
                .setPreferredWidth(250);
            this.objektverknuepfungTabelle.setGridColor(new Color(230, 230, 230));

            this.objektverknuepfungTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getObjektverknuepungTabelle().rowAtPoint(
                                origin);

                            if (row != -1) {
                                Objektverknuepfung obj = ProbepunktPanel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getObjektByIstVerknuepftMit()
                                    .getId().intValue() != ProbepunktPanel.this.hauptModul
                                    .getObjekt().getId().intValue())
                                    ProbepunktPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByIstVerknuepftMit()
                                                .getId().intValue(),
                                            false);
                                else
                                    ProbepunktPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByObjekt()
                                                .getId().intValue(),
                                            false);
                                ProbepunktPanel.this.hauptModul.getManager()
                                    .switchModul("m_objekt_bearbeiten");
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        showVerknuepfungPopup(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        showVerknuepfungPopup(e);
                    }
                });

            this.objektverknuepfungTabelle.getInputMap().put(
                (KeyStroke) getVerknuepfungLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getVerknuepfungLoeschAction().getValue(Action.NAME));
            this.objektverknuepfungTabelle.getActionMap().put(
                getVerknuepfungLoeschAction().getValue(Action.NAME),
                getVerknuepfungLoeschAction());
        }

        return this.objektverknuepfungTabelle;

    }

    private void showVerknuepfungPopup(MouseEvent e) {
        if (this.verknuepfungPopup == null) {
            this.verknuepfungPopup = new JPopupMenu("Objekt");
            JMenuItem loeschItem = new JMenuItem(getVerknuepfungLoeschAction());
            this.verknuepfungPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.objektverknuepfungTabelle.rowAtPoint(origin);

            if (row != -1) {
                this.objektverknuepfungTabelle
                    .setRowSelectionInterval(row, row);
                this.verknuepfungPopup.show(e.getComponent(), e.getX(),
                    e.getY());
            }
        }
    }

    private Action getVerknuepfungLoeschAction() {
        if (this.verknuepfungLoeschAction == null) {
            this.verknuepfungLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 2362803114601855889L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung = ProbepunktPanel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (ProbepunktPanel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                ProbepunktPanel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                ProbepunktPanel.this.hauptModul.getFrame()
                                    .changeStatus(
                                        "Konnte das Objekt nicht löschen!",
                                        HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.verknuepfungLoeschAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_L));
            this.verknuepfungLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.verknuepfungLoeschAction;
    }

    private JButton getSelectObjektButton() {
        if (this.selectObjektButton == null) {
            this.selectObjektButton = new JButton("Objekt auswählen");

            this.selectObjektButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ObjektChooser chooser = new ObjektChooser(
                        ProbepunktPanel.this.hauptModul.getFrame(),
                        ProbepunktPanel.this.probepkt.getObjekt(),
                        ProbepunktPanel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }
    
    private JButton getPrintDeckblattButton() {
        if (this.printDeckblattButton == null) {
            this.printDeckblattButton = new JButton("Deckblatt drucken");

            this.printDeckblattButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        showReport();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                    }
                }
            });
        }
        return this.printDeckblattButton;
    }
    
    public void showReport() {
        if (hauptModul.getObjekt().getId() != null) {
            SettingsManager sm = SettingsManager.getInstance();
            String fotoPath = sm.getSetting("auik.system.probepktpath_fotos");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", hauptModul.getObjekt().getId());
            String file = fotoPath + hauptModul.getObjekt().getId() + ".jpg";

            String id=hauptModul.getObjekt().getId().toString();
            if (id != null
                    && new File(file).canRead()) {
                params.put("foto", new String(fotoPath + id + ".jpg"));
            } else {
                params.put("foto", new String(fotoPath + "kein_foto.jpg"));
            }
            try {
                File pdfFile = File.createTempFile(id, ".pdf");
                pdfFile.deleteOnExit();
                PDFExporter.getInstance().exportReport(params,
                        PDFExporter.HANDAKTE_DECKBLATT, pdfFile.getAbsolutePath());
            } catch (Exception ex) {
                GUIManager.getInstance().showErrorMessage(
                        "PDF generieren fehlgeschlagen."
                        + "\n" + ex.getLocalizedMessage(),
                        "PDF generieren fehlgeschlagen");
            }
        } else {
            log.debug("Dem zu druckenden Probenahmepunkt fehlen Eingaben!");
        }
    }
}
