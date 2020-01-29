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
 * Created on 21.04.2005 by u633d
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.BwkFachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "BWK"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class BWKPanel extends JPanel {
    private static final long serialVersionUID = -6831726331391740934L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private Anfallstelle anfallstelle;

    // Widgets
    private JTextField herstellerFeld = null;
    private JTextField typFeld = null;
    private JTextField brennmittelFeld = null;
    private JFormattedTextField leistungFeld = null;
    private JTextField brennerFeld = null;
    private JTextField waermetauscherFeld = null;
    private JTextField abgasleitungFeld = null;
    private JTextField kondensatltgFeld = null;
    private JFormattedTextField jahrgangFeld = null;
    private JTextField abnahmeFeld = null;
    private TextFieldDateChooser anschreibenFeld = null;
    private TextFieldDateChooser genehmigungDatum = null;
    private JCheckBox abaCheck = null;
    private JCheckBox genehmpflichtCheck = null;
    private JTextArea bwkBeschreibungsArea = null;
    private JButton saveBwkButton = null;

    // Daten
    private BwkFachdaten bwk = null;


    public BWKPanel(BasisObjektBearbeiten hauptModul, Anfallstelle anfallstelle) {
        this.name = "Brennwertkessel";
        this.hauptModul = hauptModul;
        this.anfallstelle = anfallstelle;

        FormLayout layout = new FormLayout(
            "r:50dlu, 5dlu, 90dlu, 10dlu, r:50dlu, 5dlu, 70dlu, 5dlu, 70dlu, 70dlu", // Spalten
            "pref, " + // 1
                "3dlu, " + // 2
                "pref, " + // 3
                "3dlu, " + // 4
                "pref, " + // 5
                "3dlu, " + // 6
                "pref, " + // 7
                "3dlu, " + // 8
                "pref, " + // 9
                "3dlu, " + // 10
                "pref, " + // 11
                "3dlu, " + // 12
                "pref, " + // 13
                "3dlu, " + // 14
                "pref, " + // 15
                "3dlu, " + // 16
                "pref, " + // 17
                "3dlu, " + // 18
                "pref, " + // 19
                "3dlu, " + // 20
                "pref, " + // 21
                "3dlu, " + // 22
                "pref, " + // 23
                "3dlu, " + // 24
                "pref"); // 25

        PanelBuilder builder = new PanelBuilder(layout, this);
        CellConstraints cc = new CellConstraints();

        // linke Seite
        builder.addSeparator("Kessel", cc.xyw(1, 1, 3));
        builder.addLabel("Hersteller:", cc.xy(1, 3));
        builder.add(getHerstellerFeld(), cc.xy(3, 3));
        builder.addLabel("Typ:", cc.xy(1, 5));
        builder.add(getTypFeld(), cc.xy(3, 5));
        builder.addLabel("Brennmittel:", cc.xy(1, 7));
        builder.add(getBrennmittelFeld(), cc.xy(3, 7));
        builder.addLabel("Leistung:", cc.xy(1, 9));
        builder.add(getLeistungFeld(), cc.xy(3, 9));
        builder.addSeparator("Werkstoffe", cc.xyw(1, 15, 3));
        builder.addLabel("Brenner:", cc.xy(1, 17));
        builder.add(getBrennerFeld(), cc.xy(3, 17));
        builder.addLabel("Tauscher:", cc.xy(1, 19));
        builder.add(getWaermetauscherFeld(), cc.xy(3, 19));
        builder.addLabel("Abgasleitung:", cc.xy(1, 21));
        builder.add(getAbgasleitungFeld(), cc.xy(3, 21));
        builder.addLabel("Kondensatabl.:", cc.xy(1, 23));
        builder.add(getKondensatltgFeld(), cc.xy(3, 23));
       

        // rechte Seite
        builder.addSeparator("Erfassung", cc.xyw(5, 1, 3));
        builder.addLabel("Jahrgang:", cc.xy(5, 3));
        builder.add(getJahrgangFeld(), cc.xy(7, 3));
        builder.addLabel("Abnahme:", cc.xy(5, 5));
        builder.add(getAbnahmeFeld(), cc.xy(7, 5));
        builder.addLabel("Anschreiben:", cc.xy(5, 7));
        builder.add(getAnschreibenFeld(), cc.xy(7, 7));
        builder.addLabel("Genehmigung:", cc.xy(5, 9));
        builder.add(getGenehmigungDatum(), cc.xy(7, 9));
        // builder.addLabel("ABA:", cc.xy( 5, 11));
        builder.add(getgenehmpflichtCheck(), cc.xyw(5, 11, 3));
        builder.add(getAbaCheck(), cc.xyw(5, 13, 3));
        builder.addSeparator("Bemerkungen", cc.xyw(5, 15, 3));
        builder.add(new JScrollPane(getBwkBeschreibungsArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), cc.xywh(5, 17, 3,
            7));

        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
            getSaveBwkButton());
        builder.add(buttonBar, cc.xyw(1, 25, 7));

    }

    public void fetchFormData() throws RuntimeException {
    	Set<Anfallstelle> list = this.hauptModul.getObjekt().getAnfallstelles();
		this.bwk = BwkFachdaten.findByAnfallstelleId(
				list.iterator().next().getId());
        log.debug("Brennwertkessel aus DB geholt: " + this.bwk);
    }

    public void updateForm() throws RuntimeException {
    	
    	fetchFormData();
        if (this.bwk != null) {
            if (this.bwk.getKHersteller() != null) {
                getHerstellerFeld().setText(this.bwk.getKHersteller());
            }
            if (this.bwk.getKTyp() != null) {
                getTypFeld().setText(this.bwk.getKTyp());
            }
            if (this.bwk.getKBrennmittel() != null) {
                getBrennmittelFeld().setText(this.bwk.getKBrennmittel());
            }
            if (this.bwk.getKLeistung() != null) {
                getLeistungFeld().setText(this.bwk.getKLeistung().toString());
            }
            if (this.bwk.getWBrenner() != null) {
                getBrennerFeld().setText(this.bwk.getWBrenner());
            }
            if (this.bwk.getWWaermetauscher() != null) {
                getWaermetauscherFeld().setText(this.bwk.getWWaermetauscher());
            }
            if (this.bwk.getWAbgasleitung() != null) {
                getAbgasleitungFeld().setText(this.bwk.getWAbgasleitung());
            }
            if (this.bwk.getWKondensableitung() != null) {
                getKondensatltgFeld().setText(this.bwk.getWKondensableitung());
            }
            if (this.bwk.getErfassung() != null) {
                getJahrgangFeld().setText(this.bwk.getErfassung().toString());
            }
            if (this.bwk.getBemerkungen() != null) {
                getBwkBeschreibungsArea().setText(this.bwk.getBemerkungen());
            }
            if (this.bwk.getAbnahme() != null) {
                getAbnahmeFeld().setText(this.bwk.getAbnahme());
            }
            if (this.bwk.getAnschreiben() != null) {
                getAnschreibenFeld().setDate(this.bwk.getAnschreiben());
            }
            if (this.bwk.getDatumG() != null) {
                getGenehmigungDatum().setDate(this.bwk.getDatumG());
            }
            if (this.bwk.getAba() != null) {
                if (this.bwk.getAba().booleanValue() == true) {
                    getAbaCheck().setSelected(true);
                } else {
                    getAbaCheck().setSelected(false);
                }
            }
            if (this.bwk.getGenehmigungspflicht() != null) {
                if (this.bwk.getGenehmigungspflicht().booleanValue() == true) {
                    getgenehmpflichtCheck().setSelected(true);
                } else {
                    getgenehmpflichtCheck().setSelected(false);
                }
            }
        }

    }

    public void clearForm() {
        getHerstellerFeld().setText(null);
        getTypFeld().setText(null);
        getBrennmittelFeld().setText(null);
        getLeistungFeld().setText(null);
        getBrennerFeld().setText(null);
        getWaermetauscherFeld().setText(null);
        getAbgasleitungFeld().setText(null);
        getKondensatltgFeld().setText(null);
        getJahrgangFeld().setText(null);
        getBwkBeschreibungsArea().setText(null);
        getAbnahmeFeld().setText(null);
        getAnschreibenFeld().setDate(null);
        getGenehmigungDatum().setDate(null);
        getAbaCheck().setSelected(false);
        getgenehmpflichtCheck().setSelected(false);

    }

    public void enableAll(boolean enabled) {
        getAbaCheck().setEnabled(enabled);
        getgenehmpflichtCheck().setEnabled(enabled);
        getAbgasleitungFeld().setEnabled(enabled);
        getAbnahmeFeld().setEnabled(enabled);
        getAnschreibenFeld().setEnabled(enabled);
        getBrennerFeld().setEnabled(enabled);
        getBrennmittelFeld().setEnabled(enabled);
        getBwkBeschreibungsArea().setEnabled(enabled);
        getGenehmigungDatum().setEnabled(enabled);
        getHerstellerFeld().setEnabled(enabled);
        getJahrgangFeld().setEnabled(enabled);
        getKondensatltgFeld().setEnabled(enabled);
        getLeistungFeld().setEnabled(enabled);
        getTypFeld().setEnabled(enabled);
        getWaermetauscherFeld().setEnabled(enabled);
        getSaveBwkButton().setEnabled(enabled);
    }

    @Override
    public String getName() {
        return this.name;
    }

    private boolean saveBwkDaten() {
        boolean success;

        String hersteller = this.herstellerFeld.getText();
        if ("".equals(hersteller)) {
            this.bwk.setKHersteller(null);
        } else {
            this.bwk.setKHersteller(hersteller);
        }

        String typ = this.typFeld.getText();
        if ("".equals(typ)) {
            this.bwk.setKTyp(null);
        } else {
            this.bwk.setKTyp(typ);
        }

        String brennmittel = this.brennmittelFeld.getText();
        if ("".equals(brennmittel)) {
            this.bwk.setKBrennmittel(null);
        } else {
            this.bwk.setKBrennmittel(brennmittel);
        }

        Integer leistung = ((IntegerField) this.leistungFeld).getIntValue();
        this.bwk.setKLeistung(leistung);

        String brenner = this.brennerFeld.getText();
        if ("".equals(brenner)) {
            this.bwk.setWBrenner(null);
        } else {
            this.bwk.setWBrenner(brenner);
        }

        String tauscher = this.waermetauscherFeld.getText();
        if ("".equals(tauscher)) {
            this.bwk.setWWaermetauscher(null);
        } else {
            this.bwk.setWWaermetauscher(tauscher);
        }

        String abgasleitung = this.abgasleitungFeld.getText();
        if ("".equals(abgasleitung)) {
            this.bwk.setWAbgasleitung(null);
        } else {
            this.bwk.setWAbgasleitung(abgasleitung);
        }

        String kondensatabl = this.kondensatltgFeld.getText();
        if ("".equals(kondensatabl)) {
            this.bwk.setWKondensableitung(null);
        } else {
            this.bwk.setWKondensableitung(kondensatabl);
        }

        Integer jahrgang = ((IntegerField) this.jahrgangFeld).getIntValue();
        this.bwk.setErfassung(jahrgang);

        String abnahme = this.abnahmeFeld.getText();
        if ("".equals(abnahme)) {
            this.bwk.setAbnahme(null);
        } else {
            this.bwk.setAbnahme(abnahme);
        }

        Date anschreiben = this.anschreibenFeld.getDate();
        if ("".equals(anschreiben)) {
            this.bwk.setAnschreiben(null);
        } else {
            this.bwk.setAnschreiben(anschreiben);
        }

        Date genehmigung = this.genehmigungDatum.getDate();
        this.bwk.setDatumG(genehmigung);

        Boolean aba;
        if (getAbaCheck().isSelected()) {
            aba = true;
        } else {
            aba = false;
        }
        this.bwk.setAba(new Boolean(aba));

        Boolean genehmpflicht;
        if (getgenehmpflichtCheck().isSelected()) {
            genehmpflicht = true;
        } else {
            genehmpflicht = false;
        }
        this.bwk.setGenehmigungspflicht(new Boolean(genehmpflicht));

        String beschreibung = this.bwkBeschreibungsArea.getText();
        if ("".equals(beschreibung)) {
            this.bwk.setBemerkungen(null);
        } else {
            this.bwk.setBemerkungen(beschreibung);
        }

        Anfallstelle.merge(this.anfallstelle);
        success = this.bwk.merge();

        if (success) {
            log.debug("Brennwertkessel " + this.bwk + " gespeichert.");
        } else {
            log.debug("Brennwertkessel " + this.bwk
                + " konnte nicht gespeichert werden!");
        }

        return success;
    }

    public void completeObjekt(Anfallstelle anfallstelle) {
        if (this.hauptModul.isNew() || this.bwk == null) {
            // Neuen Brennwertkessel erzeugen
            this.bwk = new BwkFachdaten();
        }
        // Anfallstelle setzen
        	this.anfallstelle = anfallstelle;
        	this.bwk.setAnfallstelle(this.anfallstelle);
    }

    private JCheckBox getAbaCheck() {
        if (this.abaCheck == null) {
            this.abaCheck = new JCheckBox("Abwasserbehandlungsanlage");
        }
        return this.abaCheck;
    }

    private JCheckBox getgenehmpflichtCheck() {
        if (this.genehmpflichtCheck == null) {
            this.genehmpflichtCheck = new JCheckBox("Genehmigungspflicht");
        }
        return this.genehmpflichtCheck;
    }

    private JTextField getAbgasleitungFeld() {
        if (this.abgasleitungFeld == null) {
            this.abgasleitungFeld = new LimitedTextField(50);
        }
        return this.abgasleitungFeld;
    }

    private JTextField getAbnahmeFeld() {
        if (this.abnahmeFeld == null) {
            this.abnahmeFeld = new LimitedTextField(50);
        }
        return this.abnahmeFeld;
    }

    private TextFieldDateChooser getAnschreibenFeld() {
        if (this.anschreibenFeld == null) {
            this.anschreibenFeld = new TextFieldDateChooser();
        }
        return this.anschreibenFeld;
    }

    private JTextField getBrennerFeld() {
        if (this.brennerFeld == null) {
            this.brennerFeld = new LimitedTextField(50);
        }
        return this.brennerFeld;
    }

    private JTextField getBrennmittelFeld() {
        if (this.brennmittelFeld == null) {
            this.brennmittelFeld = new LimitedTextField(50);
        }
        return this.brennmittelFeld;
    }

    private JTextArea getBwkBeschreibungsArea() {
        if (this.bwkBeschreibungsArea == null) {
            this.bwkBeschreibungsArea = new LimitedTextArea(150);
            this.bwkBeschreibungsArea.setLineWrap(true);
            this.bwkBeschreibungsArea.setWrapStyleWord(true);
        }
        return this.bwkBeschreibungsArea;
    }

    private TextFieldDateChooser getGenehmigungDatum() {
        if (this.genehmigungDatum == null) {
            this.genehmigungDatum = new TextFieldDateChooser();
        }
        return this.genehmigungDatum;
    }

    private JTextField getHerstellerFeld() {
        if (this.herstellerFeld == null) {
            this.herstellerFeld = new LimitedTextField(50);
        }
        return this.herstellerFeld;
    }

    private JTextField getJahrgangFeld() {
        if (this.jahrgangFeld == null) {
            this.jahrgangFeld = new IntegerField();
        }
        return this.jahrgangFeld;
    }

    private JTextField getKondensatltgFeld() {
        if (this.kondensatltgFeld == null) {
            this.kondensatltgFeld = new LimitedTextField(50);
        }
        return this.kondensatltgFeld;
    }

    private JTextField getLeistungFeld() {
        if (this.leistungFeld == null) {
            this.leistungFeld = new IntegerField();
        }
        return this.leistungFeld;
    }

    private JTextField getTypFeld() {
        if (this.typFeld == null) {
            this.typFeld = new LimitedTextField(50);
        }
        return this.typFeld;
    }

    private JTextField getWaermetauscherFeld() {
        if (this.waermetauscherFeld == null) {
            this.waermetauscherFeld = new LimitedTextField(50);
        }
        return this.waermetauscherFeld;
    }

    private JButton getSaveBwkButton() {
        if (this.saveBwkButton == null) {
            this.saveBwkButton = new JButton("Speichern");

            this.saveBwkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    enableAll(false);
                    if (saveBwkDaten()) {
                        BWKPanel.this.hauptModul.getFrame().changeStatus(
                            "Brennwertkessel " + BWKPanel.this.bwk.getId()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        BWKPanel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Brennwertkessels!",
                            HauptFrame.ERROR_COLOR);
                    }

                    BWKPanel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveBwkButton;
    }
}
