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

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ProbenahmenModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;

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
    private AtlProbepkt probepkt = null;
    private AtlProbeart[] probearten = null;
    private AtlKlaeranlagen[] klaeranlagen = null;
    private BasisSachbearbeiter[] sachbearbeiter = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    public ProbepunktPanel(BasisObjektBearbeiten hauptModul) {
        name = "Probenahmepunkt";
        this.hauptModul = hauptModul;
        this.probenahmenModel = new ProbenahmenModel();

        FormLayout layout = new FormLayout (
                "r:50dlu, 5dlu, 250dlu, r:45dlu, 5dlu, 25dlu", // Spalten
                "pref, " +    //1
                "3dlu, " +    //2
                "pref, " +    //3
                "3dlu, " +    //4
                "pref, " +    //5
                "3dlu, " +    //6
                "pref, " +    //7
                "5dlu, " +    //8
                "pref, " +    //9
                "3dlu, " +    //10
                "pref, "+     //11
                "3dlu, " +    //12
                "pref, " +    //13
                "3dlu, " +    //14
                "pref, "+     //15
                "3dlu, " +    //16
                "pref, " +    //17
                "3dlu, " +    //18
                "fill:40dlu:g, "+ //19
                "3dlu, " +    //20
                "pref, " +     // 21
                "3dlu, " +    //22
                "pref, " +    //23
                "3dlu, " +    //24
                "fill:40dlu:g, " +    //25
                "3dlu, " +    //26
                "pref");    //27

        PanelBuilder builder = new PanelBuilder(layout, this);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        builder.addSeparator("Eigenschaften", cc.xyw( 1, 1, 6));
        builder.addLabel("Art:", cc.xy( 1, 3));
        builder.add(getProbePktArtBox(), cc.xy( 3, 3));
        builder.addLabel("Nr:", cc.xy( 4, 3));
        builder.add(getProbePktNrFeld(), cc.xy( 6, 3));
        builder.addLabel("Kläranlage:", cc.xy( 1, 5));
        builder.add(getProbeKABox(), cc.xy( 3, 5));
        builder.addLabel("Sachbearbeiter:", cc.xy( 1, 7));
        builder.add(getSachbearbeiterBox(), cc.xy( 3, 7));
        builder.addLabel("Branche:", cc.xy( 1, 9));
        builder.add(getBrancheFeld(), cc.xy( 3, 9));

        JPanel buttonBar = ButtonBarFactory.buildOKBar(getSavePktButton());
        builder.add(buttonBar, cc.xyw( 1, 11, 6));

        builder.addSeparator("Beschreibung", cc.xyw( 1, 13, 6));
        JScrollPane beschScroller = new JScrollPane(getProbePktBeschreibungsArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        beschScroller.setBorder(null);
        builder.add(beschScroller, cc.xyw( 1, 15, 6));

        builder.addSeparator("Probenahmen", cc.xyw( 1, 17, 6));
        JScrollPane tabellenScroller = new JScrollPane(getProbenahmeTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.add(tabellenScroller, cc.xyw( 1, 19, 6));
        builder.add(getNeueProbePanel(), cc.xyw( 1, 21, 6));


        builder.addSeparator("Verknüpfte Objekte", cc.xyw( 1, 23, 6));
        JScrollPane objektverknuepfungScroller = new JScrollPane(
                getObjektverknuepungTabelle(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.add(objektverknuepfungScroller, cc.xyw( 1, 25, 6));

        JPanel buttonBarOv = ButtonBarFactory.buildRightAlignedBar(
                getSelectObjektButton());

        builder.add(buttonBarOv, cc.xyw( 1, 27, 6));
    }


    public void fetchFormData() throws RuntimeException {
        probepkt = AtlProbepkt.getProbepunktByObjekt(hauptModul.getObjekt());
        log.debug("Probepunkt aus DB geholt: " + probepkt);

        if (probearten == null) {
            probearten = AtlProbeart.getProbearten();
        }
        if (klaeranlagen == null) {
            klaeranlagen = AtlKlaeranlagen.getKlaeranlagen();
        }
        if (sachbearbeiter == null) {
        	sachbearbeiter = BasisSachbearbeiter.getEnabledSachbearbeiter();
        }
    }

    public void updateForm() throws RuntimeException {
        if (probearten != null) {
            getProbePktArtBox().setModel(new DefaultComboBoxModel(probearten));
        }
        if (klaeranlagen != null) {
            getProbeKABox().setModel(new DefaultComboBoxModel(klaeranlagen));
        }
        if (sachbearbeiter != null) {
            getSachbearbeiterBox().setModel(
                new DefaultComboBoxModel(sachbearbeiter));
            getSachbearbeiterBox().setEditable(true);
        }

        if (probepkt != null) {
            getProbePktArtBox().setSelectedItem(probepkt.getAtlProbeart());
            getProbeKABox().setSelectedItem(probepkt.getAtlKlaeranlagen());
            getSachbearbeiterBox().setSelectedItem(
                probepkt.getBasisSachbearbeiter());

            if (probepkt.getNummer() != null) {
                getProbePktNrFeld().setValue(probepkt.getNummer());
            }

            getBrancheFeld().setText(probepkt.getBranche());
            getProbePktBeschreibungsArea().setText(
                hauptModul.getObjekt().getBeschreibung());

            probenahmenModel.setProbepunkt(probepkt);
            probenahmenModel.updateList();

            objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());
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
        return name;
    }

    /**
     * Bearbeitet eine Probenahme.
     */
    public void editProbenahme(AtlProbenahmen probe) {
        ProbenEditor editDialog = new ProbenEditor(probe, hauptModul.getFrame(), false);
        editDialog.setLocationRelativeTo(hauptModul.getFrame());

        editDialog.setVisible(true);

        probenahmenModel.updateList();
    }

    /**
     * Legt eine neue Probenahme an.
     */
    public void neueProbenahme(String kennNummer, Timestamp datum) {
        if (probepkt != null) {
            boolean exists = AtlProbenahmen.probenahmeExists(kennNummer);
            if (!exists) {
                AtlProbenahmen probe = new AtlProbenahmen();
                probe.setKennummer(kennNummer);
                probe.setDatumDerEntnahme(datum);
                probe.setAtlAnalysepositionen(new HashSet<AtlAnalyseposition>());
                probe.setAtlProbepkt(probepkt);

                ProbenEditor editDialog = new ProbenEditor(probe, hauptModul.getFrame(), true);
                editDialog.setVisible(true);

                probenahmenModel.updateList();
            } else {
                hauptModul.getFrame().changeStatus("Eine Probenahme mit dieser Kennnummer existiert schon!", HauptFrame.ERROR_COLOR);
            }
        } else {
            hauptModul.getFrame().changeStatus("Fehler beim Anlegen: Kein Probepunkt!", HauptFrame.ERROR_COLOR);
        }
    }

    private boolean saveProbepunktDaten() {
        boolean success;

        if (probepkt != null) {
            // Eingegebene Daten für den Probepunkt �bernehmen
            if (getProbePktArtBox().getSelectedItem() != null) {
                probepkt.setAtlProbeart((AtlProbeart) getProbePktArtBox().getSelectedItem());
            }
            if (getProbeKABox().getSelectedItem() != null) {
                probepkt.setAtlKlaeranlagen((AtlKlaeranlagen) getProbeKABox().getSelectedItem());
            }
            if (getSachbearbeiterBox().getSelectedItem() != null) {
                probepkt.setBasisSachbearbeiter((BasisSachbearbeiter) getSachbearbeiterBox().getSelectedItem());
            }

            String branche = brancheFeld.getText();
            if ("".equals(branche)) {
            	probepkt.setBranche(null);
            } else {
            	probepkt.setBranche(branche);
            }

            if (getProbePktNrFeld().getValue() != null) {
                probepkt.setNummer(((IntegerField) getProbePktNrFeld()).getIntValue());
            }

            success = AtlProbepkt.saveProbepunkt(probepkt);

        } else {
            success = false;
        }

        return success;
    }

    public void completeObjekt() {
        if (hauptModul.isNew() || probepkt == null) {
            // Neuen Probepunkt erzeugen
            probepkt = new AtlProbepkt();
            // Objekt_Id setzen
            probepkt.setBasisObjekt(hauptModul.getObjekt());

            // Probepunkt speichern
            if (AtlProbepkt.saveProbepunkt(probepkt)) {
                log.debug("Neuer Probepunkt " + probepkt + " gespeichert.");
            }
        }
    }


    private JComboBox getProbePktArtBox() {
        if (probePktArtBox == null) {
            probePktArtBox = new JComboBox();
        }
        return probePktArtBox;
    }
    private JComboBox getProbeKABox() {
        if (probeKABox == null) {
            probeKABox = new JComboBox();
        }
        return probeKABox;
    }
    private JComboBox getSachbearbeiterBox() {
        if (sachbearbeiterBox == null) {
        	sachbearbeiterBox = new JComboBox();
        }
        return sachbearbeiterBox;
    }
    private JTextField getBrancheFeld() {
        if (brancheFeld == null) {
        	brancheFeld = new LimitedTextField(50, "");
        }
        return brancheFeld;
    }
    private JFormattedTextField getProbePktNrFeld() {
        if (probePktNrFeld == null) {
            probePktNrFeld = new IntegerField();
        }
        return probePktNrFeld;
    }
    private JTextArea getProbePktBeschreibungsArea() {
        if (probePktBeschreibungsArea == null) {
            probePktBeschreibungsArea = new JTextArea();
            probePktBeschreibungsArea.setLineWrap(true);
            probePktBeschreibungsArea.setWrapStyleWord(true);
            probePktBeschreibungsArea.setEditable(false);
            probePktBeschreibungsArea.setToolTipText("Diese Beschreibung kann unter \"Objekt\" geändert werden.");
            probePktBeschreibungsArea.setBackground(this.getBackground());
        }
        return probePktBeschreibungsArea;
    }

    private JTable getProbenahmeTabelle() {
        if (probenahmeTabelle == null) {
            probenahmeTabelle = new JTable(probenahmenModel);
            //probenahmeTabelle.setBackground(Color.BLUE);
            probenahmeTabelle.getColumnModel().getColumn(0).setMaxWidth(75);
            probenahmeTabelle.getColumnModel().getColumn(1).setMaxWidth(70);
            probenahmeTabelle.getColumnModel().getColumn(2).setMaxWidth(60);
            probenahmeTabelle.getColumnModel().getColumn(3).setPreferredWidth(100);
            probenahmeTabelle.getColumnModel().getColumn(4).setPreferredWidth(100);
            probenahmeTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            probenahmeTabelle.setColumnSelectionAllowed(false);
            probenahmeTabelle.setRowSelectionAllowed(true);
            probenahmeTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = probenahmeTabelle.rowAtPoint(origin);

                        AtlProbenahmen probe = probenahmenModel.getRow(row);
                        editProbenahme(probe);
                    }
                }
            });

            // Den KeyStroke holen, der "Enter" repräsentiert
            KeyStroke enterKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
            // Den "Enter"-KeyStroke in die InputMap der Tabelle einfügen
            probenahmeTabelle.getInputMap().put(enterKeyStroke, "ENTER");
            // Eine neue Action fürs editieren erzeugen
            Action editAction = new AbstractAction() {
                private static final long serialVersionUID = -7537228135751378632L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = probenahmeTabelle.getSelectedRow();
                    // Natürlich nur editieren, wenn wirklich eine Zeile ausgewählt ist
                    if (row != -1) {
                        AtlProbenahmen probe = probenahmenModel.getRow(row);
                        editProbenahme(probe);
                    }
                }
            };
            // Diese Action dem "Enter"-KeyStroke zuweisen
            probenahmeTabelle.getActionMap().put("ENTER", editAction);

            KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false);
            probenahmeTabelle.getInputMap().put(deleteKeyStroke, "DEL");
            Action removeAction = new AbstractAction() {
                private static final long serialVersionUID = -4910733866626541511L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = probenahmeTabelle.getSelectedRow();
                    if (row != -1 && probenahmeTabelle.getEditingRow() == -1) {
                        AtlProbenahmen probe = probenahmenModel.getRow(row);
                        int answer = JOptionPane.showConfirmDialog(ProbepunktPanel.this, "Soll die Probenahme "+ probe.getKennummer() +" wirklich inkl. aller Analysen gelöscht werden?", "Löschen bestätigen", JOptionPane.YES_NO_OPTION);
                        if (answer == JOptionPane.YES_OPTION) {
                            probenahmenModel.removeRow(row);
                            log.debug("Probe " + probe.getKennummer()
                            		+ " wurde gelöscht!");
                        }
                    }
                }
            };
            probenahmeTabelle.getActionMap().put("DEL", removeAction);
        }
        return probenahmeTabelle;
    }

    private JPanel getNeueProbePanel() {
        if (neueProbePanel == null) {
            //neueProbePanel = new JPanel();

            FormLayout anlegenLayout = new FormLayout(
                    "pref, 4dlu, max(60dlu;pref), 7dlu, pref, 4dlu, max(60dlu;pref), 7dlu, max(60dlu;pref)"); // spalten, nur eine zeile

            DefaultFormBuilder builder = new DefaultFormBuilder(anlegenLayout);

            //builder.appendSeparator("Neue Probenahme");
            builder.append("Kennummer:",getKennummerFeld());
            builder.append("Datum:",    getDatumsChooser());
            builder.append(getAnlegenButton());
            neueProbePanel = builder.getPanel();
        }
        return neueProbePanel;
    }

    private JTextField getKennummerFeld() {
        if (kennummerFeld == null) {
            kennummerFeld = new LimitedTextField(50, "");
        }

        return kennummerFeld;
    }
    private JDateChooser getDatumsChooser() {
        if (datumsChooser == null) {
            datumsChooser = new JDateChooser(DateUtils.FORMAT_DEFAULT, false);
        }

        return datumsChooser;
    }
    private JButton getAnlegenButton() {
        if (anlegenButton == null) {
            anlegenButton = new JButton("Anlegen");

            anlegenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (getKennummerFeld().getText().trim().equals("")) {
                        getKennummerFeld().requestFocus();
                        hauptModul.getFrame().changeStatus("Leere Kennummer!", HauptFrame.ERROR_COLOR);
                    } else {
                        String kennNummer = getKennummerFeld().getText().trim().replaceAll(" ", "");
                        Timestamp datum = new Timestamp(getDatumsChooser().getDate().getTime());
                        neueProbenahme(kennNummer, datum);
                    }
                }
            });
        }

        return anlegenButton;
    }
    private JButton getSavePktButton() {
        if (savePktButton == null) {
            savePktButton = new JButton("Probepunkt speichern");

            savePktButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveProbepunktDaten()) {
                        hauptModul.getFrame().changeStatus("Probepunkt "+probepkt.getObjektid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
                        //hauptModul.setNew(false);
                    } else {
                        hauptModul.getFrame().changeStatus("Fehler beim Speichern von Probepunkt!", HauptFrame.ERROR_COLOR);
                    }

                    hauptModul.fillForm();
                }
            });
        }

        return savePktButton;
    }

    private JTable getObjektverknuepungTabelle() {

        if (objektVerknuepfungModel == null) {
            objektVerknuepfungModel = new ObjektVerknuepfungModel(hauptModul
                    .getObjekt());

            if (objektverknuepfungTabelle == null) {
                objektverknuepfungTabelle = new JTable(objektVerknuepfungModel);
            } else {
                objektverknuepfungTabelle.setModel(objektVerknuepfungModel);
            }
            objektverknuepfungTabelle.getColumnModel().getColumn(0)
                    .setPreferredWidth(5);
            objektverknuepfungTabelle.getColumnModel().getColumn(1)
                    .setPreferredWidth(100);
            objektverknuepfungTabelle.getColumnModel().getColumn(2)
                    .setPreferredWidth(250);

            objektverknuepfungTabelle
                    .addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                            if ((e.getClickCount() == 2)
                                    && (e.getButton() == 1)) {
                                Point origin = e.getPoint();
                                int row = getObjektverknuepungTabelle()
                                        .rowAtPoint(origin);

                                if (row != -1) {
                                    BasisObjektverknuepfung obj = objektVerknuepfungModel
                                            .getRow(row);
                                    if (obj.getBasisObjektByIstVerknuepftMit().getObjektid().intValue() != hauptModul
                                            .getObjekt().getObjektid().intValue())
                                        hauptModul
                                                .getManager()
                                                .getSettingsManager()
                                                .setSetting(
                                                        "auik.imc.edit_object",
                                                        obj
                                                                .getBasisObjektByIstVerknuepftMit()
                                                                .getObjektid()
                                                                .intValue(),
                                                        false);
                                    else
                                        hauptModul
                                                .getManager()
                                                .getSettingsManager()
                                                .setSetting(
                                                        "auik.imc.edit_object",
                                                        obj
                                                                .getBasisObjektByObjekt()
                                                                .getObjektid()
                                                                .intValue(),
                                                        false);
                                    hauptModul.getManager().switchModul(
                                            "m_objekt_bearbeiten");
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

            objektverknuepfungTabelle.getInputMap().put(
                    (KeyStroke) getVerknuepfungLoeschAction().getValue(
                            Action.ACCELERATOR_KEY),
                    getVerknuepfungLoeschAction().getValue(Action.NAME));
            objektverknuepfungTabelle.getActionMap().put(
                    getVerknuepfungLoeschAction().getValue(Action.NAME),
                    getVerknuepfungLoeschAction());
        }

        return objektverknuepfungTabelle;

    }

    private void showVerknuepfungPopup(MouseEvent e) {
        if (verknuepfungPopup == null) {
            verknuepfungPopup = new JPopupMenu("Objekt");
            JMenuItem loeschItem = new JMenuItem(getVerknuepfungLoeschAction());
            verknuepfungPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = objektverknuepfungTabelle.rowAtPoint(origin);

            if (row != -1) {
                objektverknuepfungTabelle.setRowSelectionInterval(row, row);
                verknuepfungPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private Action getVerknuepfungLoeschAction() {
        if (verknuepfungLoeschAction == null) {
            verknuepfungLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 2362803114601855889L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                            && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        BasisObjektverknuepfung verknuepfung = objektVerknuepfungModel
                                .getRow(row);
                        int answer = JOptionPane
                                .showConfirmDialog(
                                        hauptModul.getPanel(),
                                        "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                                + "Hinweis: Die Aktion betrifft nur die Verknüpfung, die Objekte bleiben erhalten und können jederzeit neu verknüpft werden.",
                                        "Löschen bestätigen",
                                        JOptionPane.YES_NO_OPTION);
                        if (answer == JOptionPane.YES_OPTION) {
                            if (objektVerknuepfungModel.removeRow(row)) {
                                hauptModul.getFrame().changeStatus(
                                        "Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                        + " wurde gelöscht!");
                            } else {
                                hauptModul.getFrame().changeStatus(
                                        "Konnte das Objekt nicht löschen!",
                                        HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            verknuepfungLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(
                    KeyEvent.VK_L));
            verknuepfungLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke
                    .getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return verknuepfungLoeschAction;
    }

    private JButton getSelectObjektButton() {
        if (selectObjektButton == null) {
            selectObjektButton = new JButton("Objekt auswählen");

            selectObjektButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ObjektChooser chooser = new ObjektChooser(hauptModul
                            .getFrame(), probepkt.getBasisObjekt(),
                            objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return selectObjektButton;
    }
}
