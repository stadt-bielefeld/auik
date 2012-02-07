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
 * $Id: BasisPanel.java,v 1.1.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 19.04.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2010/02/24 10:46:35  u633d
 * Objektverknuepfung im Objekt-Panel
 *
 * Revision 1.6  2010/02/23 13:28:10  u633d
 * Objektverknuepfung im Objekt-Panel
 *
 * Revision 1.5  2010/02/15 09:24:09  u633d
 * Objektart nicht �nderbar
 *
 * Revision 1.4  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.3  2009/04/28 06:59:43  u633d
 * Anh 50 und Standort Tabelle bearbeitet
 *
 * Revision 1.2  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.10  2005/06/09 13:39:42  u633z
 * - Objektart-Box verbreitert (dadurch entfällt auch eine Layout-Spalte)
 *
 * Revision 1.9  2005/06/08 08:35:47  u633z
 * - überflüssigen ModulManager aus Betreiber- und Standort-Editor entfernt
 *
 * Revision 1.8  2005/06/08 06:47:11  u633z
 * - Tooltips für Betreiber und Standort-Felder verbessert
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.editors.BetreiberEditor;
import de.bielefeld.umweltamt.aui.module.common.editors.StandortEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisBetreiberModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStandortModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.MyKeySelectionManager;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Das "Objekt"-Tab des BasisObjektBearbeiten-Moduls
 * @author David Klotz
 */

public class BasisPanel  extends JPanel {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
	private static final long serialVersionUID = 2520878475016486007L;

	private class ChooseDialog extends JDialog {
		private static final long serialVersionUID = 6320119317944629431L;
		private HauptFrame frame;
        private BasisBetreiber betreiber;
        private BasisStandort standort;

        private BasisBetreiberModel betreiberModel;
        private BasisStandortModel standortModel;

        private JTextField suchFeld;
        private JButton submitButton;
        private JTable ergebnisTabelle;

        private JButton okButton;
        private JButton abbrechenButton;


        public ChooseDialog(Object initial, HauptFrame frame) {
            super(frame, true);
            this.frame = frame;

            List<Object> initialList = new ArrayList<Object>();
            initialList.add(initial);

            if (initial instanceof BasisBetreiber) {
                setTitle("Betreiber auswählen");
                betreiber = (BasisBetreiber) initial;
                betreiberModel = new BasisBetreiberModel(false);
                if (betreiber.getBetreiberid() != null) {
                    betreiberModel.setList(initialList);
                }
            } else if (initial instanceof BasisStandort) {
                setTitle("Standort auswählen");
                standort = (BasisStandort) initial;
                standortModel = new BasisStandortModel();
                if (standort.getStandortid() != null) {
                    standortModel.setList(initialList);
                }
            } else {
                throw new IllegalArgumentException("intial muss ein BasisBetreiber oder BasisStandort sein!");
            }

            setContentPane(initializeContentPane());

            pack();
            setResizable(false);
            setLocationRelativeTo(this.frame);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }




        public BasisBetreiber getChosenBetreiber() {
            if (betreiber.getBetreiberid() != null) {
                return betreiber;
            } else {
                return null;
            }
        }

        public BasisStandort getChosenStandort() {
            if (standort.getStandortid() != null) {
                return standort;
            } else {
                return null;
            }
        }

        private JPanel initializeContentPane() {
            JScrollPane tabellenScroller = new JScrollPane(getErgebnisTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            TabAction ta = new TabAction();
            ta.addComp(getErgebnisTabelle());
            ta.addComp(getOkButton());
            ta.addComp(getAbbrechenButton());

            JPanel buttonBar = ButtonBarFactory.buildOKCancelBar(
                    getOkButton(),
                    getAbbrechenButton()
            );

            JToolBar submitToolBar = new JToolBar();
            submitToolBar.setFloatable(false);
            submitToolBar.setRollover(true);
            submitToolBar.add(getSubmitButton());

            FormLayout layout = new FormLayout(
                    "200dlu, 3dlu, min(16dlu;p)",        // spalten
                    "pref, 3dlu, 100dlu, 3dlu, pref");     // zeilen
            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();

            builder.add(getSuchFeld(),        cc.xy(1, 1));
            builder.add(submitToolBar,        cc.xy(3, 1));
            builder.add(tabellenScroller,    cc.xyw(1, 3, 3));
            builder.add(buttonBar,             cc.xyw(1, 5, 3));

            return(builder.getPanel());
        }

        private void choose(int row) {
            if (row != -1) {
                if (betreiber != null) {
                    betreiber = betreiberModel.getRow(row);
                } else if (standort != null) {
                    standort = standortModel.getRow(row);
                }
                dispose();
            }
        }

        private void doSearch() {
            final String suche = getSuchFeld().getText();

            if (betreiber != null) {
                SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
                    @Override
                    protected void doNonUILogic() throws RuntimeException {
                        betreiberModel.filterList(suche, null);
                    }

                    @Override
                    protected void doUIUpdateLogic() throws RuntimeException {
                        betreiberModel.fireTableDataChanged();
                    }
                };
                worker.start();
            } else if (standort != null) {
                SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
                    @Override
                    protected void doNonUILogic() throws RuntimeException {
                        String[] test = suche.split(" ");
                        String last = test[test.length-1];
                        int nr;
                        String first;
                        try {
                            nr = Integer.parseInt(last);
                            first = suche.replaceAll(last, "");
                        } catch (NumberFormatException e) {
                            first = suche;
                            nr = -1;
                        }

                        standortModel.filterList(first, nr);
                    }

                    @Override
                    protected void doUIUpdateLogic() throws RuntimeException {
                        standortModel.fireTableDataChanged();
                    }
                };
                worker.start();
            }
        }

        private JTextField getSuchFeld() {
            if (suchFeld == null) {
                suchFeld = new JTextField();
                suchFeld.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doSearch();
                    }
                });
            }

            return suchFeld;
        }

        private JButton getSubmitButton() {
            if (submitButton == null) {
                submitButton = new JButton(AuikUtils.getIcon(16, "key_enter.png"));
                submitButton.setToolTipText("Suche starten");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doSearch();
                    }
                });
            }

            return submitButton;
        }

        private JTable getErgebnisTabelle() {
            if (ergebnisTabelle == null) {
                if (betreiber != null) {
                    ergebnisTabelle = new JTable(betreiberModel);
                } else if (standort != null) {
                    ergebnisTabelle = new JTable(standortModel);
                    //ergebnisTabelle = new JTable(3, 3);
                }

                ergebnisTabelle.addFocusListener(TableFocusListener.getInstance());
                ergebnisTabelle.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = ergebnisTabelle.rowAtPoint(origin);

                            choose(row);
                        }
                    }
                });

                ergebnisTabelle.getColumnModel().getColumn(0).setPreferredWidth(130);
            }

            return ergebnisTabelle;
        }

        private JButton getOkButton() {
            if (okButton == null) {
                okButton = new JButton("Ok");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row = getErgebnisTabelle().getSelectedRow();

                        choose(row);
                    }
                });
            }

            return okButton;
        }

        private JButton getAbbrechenButton() {
            if (abbrechenButton == null) {
                abbrechenButton = new JButton("Abbrechen");
                abbrechenButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
            }

            return abbrechenButton;
        }
    }

    // Widgets
    //   Betreiber
    private JTextField betreiberFeld;
    private JToolBar betreiberToolBar;
    private JButton betreiberChooseButton;
    private JButton betreiberEditButton;
    private JButton betreiberNewButton;
//    private JButton betreiberGotoButton;

    //   Standort
    private JTextField standortFeld;
    private JToolBar standortToolBar;
    private JButton standortChooseButton;
    private JButton standortEditButton;
    private JButton standortNewButton;
//    private JButton standortGotoButton;

    //   Art, Sachbearbeiter, Inaktiv, Priorität, Beschreibung, Speichern
    private JComboBox artBox;
    private JComboBox sachbearbeiterBox;
    private JCheckBox inaktivBox;
    private JFormattedTextField prioritaetFeld;
    private JTextArea beschreibungsArea;
    private JButton saveButton;

    private ActionListener editButtonListener;
//    private ActionListener gotoButtonListener;

    // Daten
    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Fachdaten
    private BasisObjektarten[] objektarten;

    //Sachbearbeiter
    private BasisSachbearbeiter[] sachbearbeiter;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    public BasisPanel(BasisObjektBearbeiten hauptModul) {

        name = "Objekt";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout (
                "r:70dlu, 5dlu, 15dlu, 3dlu, 165dlu, 3dlu, l:min(55dlu;p)",
                ""        // Zeilen werden dynamisch erzeugt
        );

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();



        builder.appendSeparator("Eigenschaften");
        builder.append("Betreiber:", getBetreiberFeld(), 3);
        builder.append(getBetreiberToolBar());
        builder.nextLine();

        builder.append("Standort:", getStandortFeld(), 3);
        builder.append(getStandortToolBar());
        builder.nextLine();

        builder.append("Art:", getArtBox(), 3);
        builder.nextLine();

        builder.append("Sachbearbeiter:", getSachbearbeiterBox(), 3);
        builder.nextLine();

        builder.append("Inaktiv:", getInaktivBox());
        builder.nextLine();

        builder.append("Priorität:", getPrioritaetFeld());
        builder.nextLine();

        builder.appendSeparator("Beschreibung");
        builder.appendRow("3dlu");
        builder.nextLine(2);

        JScrollPane beschreibungsScroller = new JScrollPane(getBeschreibungsArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:25dlu");
        builder.append(beschreibungsScroller, 5);

        builder.appendRelatedComponentsGapRow();
        builder.nextLine(2);

        builder.appendSeparator("Verknüpfte Objekte");
        builder.appendRow("3dlu");
        builder.nextLine(2);
                JScrollPane objektverknuepfungScroller = new JScrollPane(
                getObjektverknuepungTabelle(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:100dlu");
        builder.append(objektverknuepfungScroller, 5);
        builder.nextLine();

        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(
                getSelectObjektButton(), getSaveButton());

        builder.append(buttonBar, 5);
    }


    public void fetchFormData() {
        if (objektarten == null)
        {
            objektarten = BasisObjektarten.getObjektarten();
        }

        if (sachbearbeiter == null)
        {
        	sachbearbeiter = BasisSachbearbeiter.getSachbearbeiter();
        }
    }

    public void updateForm() {
        boolean neu = false;
        getSachbearbeiterBox().setModel(new DefaultComboBoxModel(sachbearbeiter));
        getSachbearbeiterBox().setSelectedIndex(-1);


		try {
			// Nur wenn Objekte neu angelegt werden stehen alle Objektarten zur
			// Auswahl.
			// Sobald eine Objet gespeichert wurde ist die Objektart nicht mehr
			// veränderbar
			if (hauptModul.getObjekt().getObjektid() == null)
				neu = true;
		} catch (NullPointerException e) {
			neu = true;
		}

		if (neu == true) {
			if (objektarten != null
					&& (objektarten.length != getArtBox().getItemCount())) {
				getArtBox().setModel(new DefaultComboBoxModel(objektarten));
			}
			// hauptModul.getObjekt().setPrioritaet(0);
		}

		else {
			getArtBox().removeAllItems();
			// Ändern der Objektart von Anhang 53 (<3000) in Anhang 53 (>3000)
			// und umgekehrt ist weiterhin möglich
			if (hauptModul.getObjekt().getBasisObjektarten().isAnh53Kl()
					| hauptModul.getObjekt().getBasisObjektarten().isAnh53Gr()) {
				getArtBox().addItem(BasisObjektarten.getObjektart(17)); // Anhang
																		// 53
																		// (<3000)
																		// (360.33)
				getArtBox().addItem(BasisObjektarten.getObjektart(18)); // Anhang
																		// 53
																		// (>3000)
																		// (360.33)
			}
			// Ändern der Objektarten Anhang 49, Abscheider und Fettabscheider
			// ist ebenfalls möglich
			else if (hauptModul.getObjekt().getBasisObjektarten().isAnh49()
					|| hauptModul.getObjekt().getBasisObjektarten()
							.isFettabscheider()
					|| hauptModul.getObjekt().getBasisObjektarten()
							.isAbscheider()) {
				getArtBox().addItem(BasisObjektarten.getObjektart(14)); // Anhang
																		// 49
																		// (360.33)
				getArtBox().addItem(BasisObjektarten.getObjektart(19)); // Abscheider
																		// (360.33)
				getArtBox().addItem(BasisObjektarten.getObjektart(15)); // Fettabscheider
																		// (360.33)
			}

			else {
				getArtBox().addItem(
						hauptModul.getObjekt().getBasisObjektarten());
			}
		}

        if (hauptModul.getObjekt() != null) {
            if (hauptModul.getObjekt().getBasisBetreiber() != null) {
                BasisBetreiber betr = hauptModul.getObjekt().getBasisBetreiber();
                getBetreiberFeld().setText(betr.toString());
                String toolTip =
                    "<html><b>Anrede:</b> "+((betr.getBetranrede() != null) ? betr.getBetranrede() : "")+"<br>" +
                    "<b>Name:</b> "+betr.getBetrname()+"<br>";
                if (betr.getBetrnamezus() != null) {
                    toolTip += "<b>Zusatz:</b> "+betr.getBetrnamezus()+"<br><br>";
                }
                if (betr.getStrasse() != null) {
                    toolTip += "<b>Adresse:</b><br>"+ betr.getStrasse() +" "+ betr.getHausnr();
                    if (betr.getHausnrzus() != null) {
                        toolTip += betr.getHausnrzus();
                    }
                    toolTip += "<br>";
                }
                toolTip += ((betr.getPlzzs() != null) ? betr.getPlzzs().trim() + " - " : "") + ((betr.getPlz() != null) ? betr.getPlz() + " " : "") + ((betr.getOrt() != null) ? betr.getOrt() : "");
                if (betr.getTelefon() != null) {
                    toolTip += "<br><br><b>Telefon:</b> " + betr.getTelefon();
                }
                toolTip += "</html>";
                getBetreiberFeld().setToolTipText(toolTip);
            }
            if (hauptModul.getObjekt().getBasisStandort() != null) {
                BasisStandort sta = hauptModul.getObjekt().getBasisStandort();
                String toolTip = "<html>"+ sta +"<br>";
                if (sta.getPlz() != null) {
                    toolTip += "<b>PLZ:</b> "+sta.getPlz()+"<br>";
                }
                toolTip +=
                    "<b>Gemarkung:</b> "+sta.getBasisGemarkung() +
                    ((sta.getEntgebid() != null) ? "<br><b>Entw.gebiet:</b> "+sta.getEntgebid() : "") +
                    "</html>";
                getStandortFeld().setToolTipText(toolTip);
                getStandortFeld().setText(sta.getFormatierteStrasse());
            }

            if (hauptModul.getObjekt().getBasisObjektarten() != null) {
                getArtBox().setSelectedItem(hauptModul.getObjekt().getBasisObjektarten());
            }

            if (hauptModul.getObjekt().getBasisSachbearbeiter() != null) {
                getSachbearbeiterBox().setSelectedItem(hauptModul.getObjekt().getBasisSachbearbeiter());
            }
            if (hauptModul.getObjekt().getInaktiv() != null) {
                if (hauptModul.getObjekt().getInaktiv() == true) {
                    getInaktivBox().setSelected(true);
                } else {
                    getInaktivBox().setSelected(false);
                }
            }

			if (!neu) {
				if (hauptModul.getObjekt().getPrioritaet() != null) {
					getPrioritaetFeld().setText(
							hauptModul.getObjekt().getPrioritaet().toString());
				}
			}


            if (hauptModul.getObjekt().getBeschreibung() != null) {
                getBeschreibungsArea().setText(hauptModul.getObjekt().getBeschreibung());
            }

            if (hauptModul.getObjekt().getObjektid() != null) {
                objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());
            } else {
                objektVerknuepfungModel.clearList();
            }
        }
    }

    public void clearForm() {
        getBetreiberFeld().setText("");
        getBetreiberFeld().setToolTipText(null);
        getStandortFeld().setText("");
        getStandortFeld().setToolTipText(null);
        if (getArtBox().getItemCount() > 0) {
            getArtBox().setSelectedIndex(0);
        }
        if (getSachbearbeiterBox().getItemCount() > 0) {
            getSachbearbeiterBox().setSelectedIndex(0);
        }
        getInaktivBox().setSelected(false);
        getPrioritaetFeld().setText("");
        getBeschreibungsArea().setText(null);
    }

    public void enableAll(boolean enabled) {
        getSaveButton().setEnabled(enabled);
        getBetreiberToolBar().setEnabled(enabled);
        getStandortToolBar().setEnabled(enabled);
        getArtBox().setEnabled(enabled);
        getSachbearbeiterBox().setEnabled(enabled);
        getInaktivBox().setEnabled(enabled);
        getPrioritaetFeld().setEnabled(enabled);
        getBeschreibungsArea().setEnabled(enabled);
    }

    @Override
    public String getName() {
        return name;
    }

    private boolean saveObjektDaten() {
        boolean success;

        Integer prio = null;

        if (!getPrioritaetFeld().getText().equals("")){
        	prio = Integer.parseInt(getPrioritaetFeld().getText());
        }

        // Eingegebene Daten für das Objekt übernehmen
        // Betreiber / Standort werden schon nach der Auswahl durch die chooseButtons gesetzt
        hauptModul.getObjekt().setBasisObjektarten((BasisObjektarten)getArtBox().getSelectedItem());
        hauptModul.getObjekt().setBeschreibung(getBeschreibungsArea().getText());
        hauptModul.getObjekt().setBasisSachbearbeiter((BasisSachbearbeiter) getSachbearbeiterBox().getSelectedItem());
        hauptModul.getObjekt().setInaktiv(getInaktivBox().isSelected());


        BasisObjekt tmp = BasisObjekt.saveBasisObjekt(hauptModul.getObjekt(),
        		prio);

        if (tmp != null) {
            hauptModul.setObjekt(tmp);
            hauptModul.completeObjekt();
            success = true;
            log.debug("Objekt " + hauptModul.getObjekt() + " gespeichert.");
        } else {
            success = false;
            log.debug("Objekt " + hauptModul.getObjekt()
            		+ " konnte nicht gespeichert werden!");
        }

        return success;
    }

    private ActionListener getEditButtonListener() {
        if (editButtonListener == null) {
            editButtonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String action = e.getActionCommand();

                    BasisBetreiber betreiber = hauptModul.getObjekt().getBasisBetreiber();
                    BasisStandort standort = hauptModul.getObjekt().getBasisStandort();

                    if ("betreiber_edit".equals(action) && betreiber != null) {
                        BetreiberEditor editDialog = new BetreiberEditor(betreiber, hauptModul.getFrame());
                        editDialog.setLocationRelativeTo(hauptModul.getFrame());

                        editDialog.setVisible(true);

                        hauptModul.getObjekt().setBasisBetreiber(editDialog.getBetreiber());
                    } else if ("standort_edit".equals(action) && standort != null) {
                        StandortEditor editDialog = new StandortEditor(standort, hauptModul.getFrame());
                        editDialog.setLocationRelativeTo(hauptModul.getFrame());

                        editDialog.setVisible(true);

                        hauptModul.getObjekt().setBasisStandort(editDialog.getStandort());
                    }

                    updateForm();
                }
            };
        }

        return editButtonListener;
    }

    // TODO: Check this: This method was private and never used locally
//    private ActionListener getGotoButtonListener() {
//        if (gotoButtonListener == null) {
//            gotoButtonListener = new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    String action = e.getActionCommand();
//
//                    BasisBetreiber betreiber = hauptModul.getObjekt().getBasisBetreiber();
//                    BasisStandort standort = hauptModul.getObjekt().getBasisStandort();
//
//                    if ("betreiber_goto".equals(action) && betreiber != null) {
//
//                        hauptModul.getManager().switchModul("m_betreiber_suchen");
//
//                    } else if ("standort_goto".equals(action) && standort != null) {
//
//                        hauptModul.getManager().switchModul("m_standort_suchen");
//
//                    }
//
//                    updateForm();
//                }
//            };
//        }
//
//        return gotoButtonListener;
//    }

    public JTextField getBetreiberFeld() {
        if (betreiberFeld == null) {
            betreiberFeld = new JTextField("");
            betreiberFeld.setEditable(false);
        }
        return betreiberFeld;
    }

    private JToolBar getBetreiberToolBar() {
        if (betreiberToolBar == null) {
            betreiberToolBar = new JToolBar();
            betreiberToolBar.setFloatable(false);
            betreiberToolBar.setRollover(true);

            betreiberToolBar.add(getBetreiberChooseButton());
            betreiberToolBar.add(getBetreiberNewButton());
            betreiberToolBar.add(getBetreiberEditButton());
//            betreiberToolBar.add(getBetreiberGotoButton());
        }
        return betreiberToolBar;
    }

    private JButton getBetreiberChooseButton() {
        if (betreiberChooseButton == null) {
            betreiberChooseButton = new JButton(AuikUtils.getIcon(16, "reload.png", ""));
            betreiberChooseButton.setHorizontalAlignment(JButton.CENTER);
            betreiberChooseButton.setToolTipText("Betreiber auswählen");

            betreiberChooseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BasisBetreiber betreiber = hauptModul.getObjekt().getBasisBetreiber();
                    if (betreiber == null) {
                        betreiber = new BasisBetreiber();
                    }
                    ChooseDialog chooser = new ChooseDialog(betreiber, hauptModul.getFrame());
                    chooser.setVisible(true);

                    hauptModul.getObjekt().setBasisBetreiber(chooser.getChosenBetreiber());
                    updateForm();
                }
            });
        }

        return betreiberChooseButton;
    }

    private JButton getBetreiberEditButton() {
        if (betreiberEditButton == null) {
            betreiberEditButton = new JButton(AuikUtils.getIcon(16, "edit.png", ""));
            betreiberEditButton.setHorizontalAlignment(JButton.CENTER);
            betreiberEditButton.setToolTipText("Betreiber bearbeiten");
            betreiberEditButton.setActionCommand("betreiber_edit");

            betreiberEditButton.addActionListener(getEditButtonListener());
        }

        return betreiberEditButton;
    }

//    private JButton getBetreiberGotoButton() {
//        if (betreiberGotoButton == null) {
//            betreiberGotoButton = new JButton(AuikUtils.getIcon(16, "edit.png", ""));
//            betreiberGotoButton.setHorizontalAlignment(JButton.CENTER);
//            betreiberGotoButton.setToolTipText("Betreibersuche aufrufen");
//            betreiberGotoButton.setActionCommand("betreiber_goto");
//
//            betreiberGotoButton.addActionListener(getGotoButtonListener());
//        }
//
//        return betreiberGotoButton;
//    }

    private JButton getBetreiberNewButton() {
        if (betreiberNewButton == null) {
            betreiberNewButton = new JButton(AuikUtils.getIcon(16, "filenew.png", ""));
            betreiberNewButton.setHorizontalAlignment(JButton.CENTER);
            betreiberNewButton.setToolTipText("Neuen Betreiber anlegen");

            betreiberNewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hauptModul.getManager().getSettingsManager().setSetting("auik.imc.return_to_objekt", true, false);
                    if (hauptModul.getObjekt().getBasisBetreiber() != null) {
                        hauptModul.getManager().getSettingsManager().setSetting(
                                "auik.imc.use_betreiber",
                                hauptModul.getObjekt().getBasisBetreiber().getBetreiberid().intValue(), false);
                    }
                    if (hauptModul.getObjekt().getBasisStandort() != null) {
                        hauptModul.getManager().getSettingsManager().setSetting(
                                "auik.imc.use_standort",
                                hauptModul.getObjekt().getBasisStandort().getStandortid().intValue(), false);
                    }
                    hauptModul.getManager().switchModul("m_betreiber_neu");
                }
            });
        }

        return betreiberNewButton;
    }

    public JTextField getStandortFeld() {
        if (standortFeld == null) {
            standortFeld = new JTextField("");
            standortFeld.setEditable(false);
        }
        return standortFeld;
    }

    private JToolBar getStandortToolBar() {
        if (standortToolBar == null) {
            standortToolBar = new JToolBar();
            standortToolBar.setFloatable(false);
            standortToolBar.setRollover(true);

            standortToolBar.add(getStandortChooseButton());
            standortToolBar.add(getStandortNewButton());
            standortToolBar.add(getStandortEditButton());
//            standortToolBar.add(getStandortGotoButton());
        }
        return standortToolBar;
    }

    private JButton getStandortChooseButton() {
        if (standortChooseButton == null) {
            standortChooseButton = new JButton(AuikUtils.getIcon(16, "reload.png", ""));
            standortChooseButton.setHorizontalAlignment(JButton.CENTER);
            standortChooseButton.setToolTipText("Standort auswählen");

            standortChooseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BasisStandort standort = hauptModul.getObjekt().getBasisStandort();
                    if (standort == null) {
                        standort = new BasisStandort();
                    }
                    ChooseDialog chooser = new ChooseDialog(standort, hauptModul.getFrame());
                    chooser.setVisible(true);

                    hauptModul.getObjekt().setBasisStandort(chooser.getChosenStandort());

                    updateForm();
                }
            });
        }

        return standortChooseButton;
    }

    private JButton getStandortEditButton() {
        if (standortEditButton == null) {
            standortEditButton = new JButton(AuikUtils.getIcon(16, "edit.png", ""));
            standortEditButton.setHorizontalAlignment(JButton.CENTER);
            standortEditButton.setToolTipText("Standort bearbeiten");
            standortEditButton.setActionCommand("standort_edit");

            standortEditButton.addActionListener(getEditButtonListener());
        }

        return standortEditButton;
    }

//    private JButton getStandortGotoButton() {
//        if (standortGotoButton == null) {
//            standortGotoButton = new JButton(AuikUtils.getIcon(16, "edit.png", ""));
//            standortGotoButton.setHorizontalAlignment(JButton.CENTER);
//            standortGotoButton.setToolTipText("Standortsuche aufrufen");
//            standortGotoButton.setActionCommand("standort_goto");
//
//            standortGotoButton.addActionListener(getGotoButtonListener());
//        }
//
//        return standortGotoButton;
//    }

    private JButton getStandortNewButton() {
        if (standortNewButton == null) {
            standortNewButton = new JButton(AuikUtils.getIcon(16, "filenew.png", ""));
            standortNewButton.setHorizontalAlignment(JButton.CENTER);
            standortNewButton.setToolTipText("Neuen Standort anlegen");

            standortNewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hauptModul.getManager().getSettingsManager().setSetting("auik.imc.return_to_objekt", true, false);

                    if (hauptModul.getObjekt().getBasisBetreiber() != null) {
                        hauptModul.getManager().getSettingsManager().setSetting(
                                "auik.imc.use_betreiber",
                                hauptModul.getObjekt().getBasisBetreiber().getBetreiberid().intValue(), false);
                    }
                    if (hauptModul.getObjekt().getBasisStandort() != null) {
                        hauptModul.getManager().getSettingsManager().setSetting(
                                "auik.imc.use_standort",
                                hauptModul.getObjekt().getBasisStandort().getStandortid().intValue(), false);
                    }
                    hauptModul.getManager().switchModul("m_standort_neu");
                }
            });
        }

        return standortNewButton;
    }

    private JComboBox getArtBox() {
        if (artBox == null) {

                artBox = new JComboBox();
                artBox.setKeySelectionManager(new MyKeySelectionManager());

        }
        return artBox;
    }

    private JComboBox getSachbearbeiterBox() {
        if (sachbearbeiterBox == null) {

        	sachbearbeiterBox = new JComboBox();
        	sachbearbeiterBox.setKeySelectionManager(new MyKeySelectionManager());

        }
        return sachbearbeiterBox;
    }

    private JCheckBox getInaktivBox() {
        if (inaktivBox == null) {
            inaktivBox = new JCheckBox();
        }
        return inaktivBox;
    }

    private JFormattedTextField getPrioritaetFeld() {
        if (prioritaetFeld == null) {
        	prioritaetFeld = new JFormattedTextField();
        }
        return prioritaetFeld;
    }

    public JTextArea getBeschreibungsArea() {
        if (beschreibungsArea == null) {
            beschreibungsArea = new LimitedTextArea(150);
            beschreibungsArea.setLineWrap(true);
            beschreibungsArea.setWrapStyleWord(true);
        }
        return beschreibungsArea;
    }

    private JButton getSaveButton() {
        if (saveButton == null) {
            saveButton = new JButton("Objekt speichern");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ((hauptModul.getObjekt().getBasisBetreiber() != null) && (hauptModul.getObjekt().getBasisStandort() != null)) {
                        enableAll(false);
                        if (saveObjektDaten()) {
                            hauptModul.getFrame().changeStatus("Objekt "+hauptModul.getObjekt().getObjektid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
                            hauptModul.setNew(false);
                        } else {
                            hauptModul.getFrame().changeStatus("Konnte Objekt nicht speichern!", HauptFrame.ERROR_COLOR);
                        }

                        hauptModul.fillForm();
                    } else {
                        hauptModul.getFrame().changeStatus("Kein Betreiber/Standort ausgewählt!", HauptFrame.ERROR_COLOR);
                    }
                }
            });
        }
        return saveButton;
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
				private static final long serialVersionUID = 1214869561793347819L;

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
                            .getFrame(), hauptModul.getObjekt(),
                            objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return selectObjektButton;
    }

}
