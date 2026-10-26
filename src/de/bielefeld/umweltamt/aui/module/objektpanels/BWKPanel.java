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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.BwkFachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.FormHelper;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "BWK"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class BWKPanel extends ObjectPanel {
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

        JPanel innerPanel = FormHelper.createFormPanel();
        // linke Seite
        FormHelper.addSeparator(innerPanel, 0, 0, 2, "Kessel");
        FormHelper.addLabel(innerPanel, 0, 1, "Hersteller");
        FormHelper.fixWidth(getHerstellerFeld(), 158);
        FormHelper.addField(innerPanel, 1, 1, 1, getHerstellerFeld());
        FormHelper.addLabel(innerPanel, 0, 2, "Typ");
        FormHelper.fixWidth(getTypFeld(), 158);
        FormHelper.addField(innerPanel, 1, 2, 1, getTypFeld());
        FormHelper.addLabel(innerPanel, 0, 3, "Brennmittel");
        FormHelper.fixWidth(getBrennmittelFeld(), 158);
        FormHelper.addField(innerPanel, 1, 3, 1, getBrennmittelFeld());
        FormHelper.addLabel(innerPanel, 0, 4, "Leistung");
        FormHelper.fixWidth(getLeistungFeld(), 158);
        FormHelper.addField(innerPanel, 1, 4, 1, getLeistungFeld());
        FormHelper.addSeparator(innerPanel, 0, 7, 2, "Werkstoffe");
        FormHelper.addLabel(innerPanel, 0, 8, "Brenner");
        FormHelper.fixWidth(getBrennerFeld(), 158);
        FormHelper.addField(innerPanel, 1, 8, 1, getBrennerFeld());
        FormHelper.addLabel(innerPanel, 0, 9, "Tauscher");
        FormHelper.fixWidth(getWaermetauscherFeld(), 158);
        FormHelper.addField(innerPanel, 1, 9, 1, getWaermetauscherFeld());
        FormHelper.addLabel(innerPanel, 0, 10, "Abgasleitung");
        FormHelper.fixWidth(getAbgasleitungFeld(), 158);
        FormHelper.addField(innerPanel, 1, 10, 1, getAbgasleitungFeld());
        FormHelper.addLabel(innerPanel, 0, 11, "Kondensatabl.");
        FormHelper.fixWidth(getKondensatltgFeld(), 158);
        FormHelper.addField(innerPanel, 1, 11, 1, getKondensatltgFeld());
        // rechte Seite
        FormHelper.addSeparator(innerPanel, 2, 0, 2, "Erfassung");
        FormHelper.addLabel(innerPanel, 2, 1, "Jahrgang");
        FormHelper.fixWidth(getJahrgangFeld(), 122);
        FormHelper.addField(innerPanel, 3, 1, 1, getJahrgangFeld());
        FormHelper.addLabel(innerPanel, 2, 2, "Abnahme");
        FormHelper.fixWidth(getAbnahmeFeld(), 122);
        FormHelper.addField(innerPanel, 3, 2, 1, getAbnahmeFeld());
        FormHelper.addLabel(innerPanel, 2, 3, "Anschreiben");
        FormHelper.fixWidth(getAnschreibenFeld(), 122);
        FormHelper.addField(innerPanel, 3, 3, 1, getAnschreibenFeld());
        FormHelper.addLabel(innerPanel, 2, 4, "Genehmigung");
        FormHelper.fixWidth(getGenehmigungDatum(), 122);
        FormHelper.addField(innerPanel, 3, 4, 1, getGenehmigungDatum());
        // builder.addLabel("ABA", cc.xy( 5, 11));
        FormHelper.addField(innerPanel, 2, 5, 2, getgenehmpflichtCheck());
        FormHelper.addField(innerPanel, 2, 6, 2, getAbaCheck());
        FormHelper.addSeparator(innerPanel, 2, 7, 2, "Bemerkungen");
        FormHelper.addSpan(innerPanel, new JScrollPane(getBwkBeschreibungsArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), 2, 8, 2, 4);
        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
            getSaveBwkButton());
        FormHelper.addField(innerPanel, 0, 12, 4, buttonBar);
        this.setLayout(new BorderLayout());
        this.add(FormHelper.wrapNorthWest(innerPanel), BorderLayout.NORTH);
        addChangeListeners(getHerstellerFeld(), getTypFeld(),
            getBrennmittelFeld(), getLeistungFeld(), getBrennerFeld(),
            getWaermetauscherFeld(), getAbgasleitungFeld(), getKondensatltgFeld(),
            getJahrgangFeld(), getAbnahmeFeld(), getAnschreibenFeld(),
            getGenehmigungDatum(), getAbaCheck(), getgenehmpflichtCheck(),
            getBwkBeschreibungsArea());
    }

    public void updateForm(Anfallstelle anfallstelle) throws RuntimeException {

    	this.anfallstelle = anfallstelle;
    	this.bwk = this.anfallstelle.getBwkFachdatens().iterator().next();

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
        setDirty(false);
    }

	public void clearForm() {
		bwk = null;
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

    public void completeObjekt(Anfallstelle anfallstelle) {

        if (this.bwk == null) {
            // Neuen Brennwertkessel erzeugen
            this.bwk = new BwkFachdaten();
            // Anfallstelle setzen
        	this.anfallstelle = anfallstelle;
        	this.bwk.setAnfallstelle(this.anfallstelle);
        }

    }

    @Override
    protected boolean doSavePanelData() {
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

	    this.bwk.setAba(getAbaCheck().isSelected());

	    this.bwk.setGenehmigungspflicht(getgenehmpflichtCheck().isSelected());

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
                    if (hauptModul.saveAllTabs()) {
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
