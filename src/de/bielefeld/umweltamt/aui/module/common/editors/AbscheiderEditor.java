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
 * $Id: AbscheiderEditor.java,v 1.4 2010-01-27 07:51:08 u633d Exp $
 *
 * Erstellt am 01.06.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2010/01/18 10:13:22  u633d
 * Anh 53 und Abscheider ergaenzt
 *
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.8  2005/07/06 09:38:56  u633z
 * - Kommentar verändert
 *
 * Revision 1.7  2005/06/13 13:39:35  u633z
 * - Layout verbessert
 *
 * Revision 1.6  2005/06/09 14:38:00  u633d
 * - Anhang 49 Pflegearbeiten
 *
 * Revision 1.5  2005/06/09 09:22:53  u633z
 * - Border der Content-Area korrigiert
 *
 * Revision 1.4  2005/06/09 09:15:52  u633z
 * - Nur ein bisschen aufgeräumt
 *
 * Revision 1.3  2005/06/08 12:03:47  u633d
 * Abscheidereditor fertig
 *
 * Revision 1.1  2005/06/08 07:10:41  u633d
 * Abscheider Editor angefangen
 *
 */
package de.bielefeld.umweltamt.aui.module.common.editors;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.FormHelper;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;

/**
 * Ein Dialog zum Bearbeiten von Abscheiderdetails.
 * @author Gerd Genuit
 * @author David Klotz
 */
public class AbscheiderEditor extends AbstractBaseEditor{
	private static final long serialVersionUID = -6112634548181223631L;

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private JTextField lageFeld;
    private JTextField typFeld;
    private JTextField herstellerFeld;
    private JFormattedTextField nrFeld;
    private JFormattedTextField nenngroesseFeld;
    private JFormattedTextField vsf1Feld;
    private JFormattedTextField vsf2Feld;
    private JCheckBox rueckhaltCheck; //Rückhaltefunktion gemäß AwSV
    private JCheckBox schlammfangCheck;
    private JCheckBox benzinabscheiderCheck;
    private JCheckBox koaabscheiderCheck;
    private JCheckBox kompaktCheck; //KOmpaktanlage
    private JCheckBox emulsionCheck;
    private JCheckBox schwimmerCheck;
    private JCheckBox kreisCheck;
    private JCheckBox vorschlammCheck;
    private JCheckBox probeCheck;
    private JCheckBox warnanlageCheck;


    private JTextArea bemerkungsArea;

    // Funktionalität:

    /**
     * Erzeugt einen neuen Dialog zum Bearbeiten von Abscheiderdetails.
     */
    public AbscheiderEditor(Anh49Abscheiderdetails details, HauptFrame owner) {
        super("Abscheider", details, owner);
    }

    public Anh49Abscheiderdetails getDetails() {
        return (Anh49Abscheiderdetails) getEditedObject();
    }


    @SuppressWarnings("deprecation")
	@Override
    protected JComponent buildContentArea() {

        JPanel panel = FormHelper.createFormPanel();
        // Stamdaten -------------------------- ----------
        FormHelper.addSeparator(panel, 0, 0, 4, "Stammdaten");
        FormHelper.addLabel(panel, 0, 1, "Nr.");
        FormHelper.fixWidth(getNrFeld(), 70);
        FormHelper.addField(panel, 1, 1, 1, getNrFeld());
        FormHelper.fixWidth(getLageFeld(), 203);
        FormHelper.addField(panel, 1, 2, 3, getLageFeld());
        FormHelper.addLabel(panel, 0, 3, "Hersteller");
        FormHelper.fixWidth(getHerstellerFeld(), 70);
        FormHelper.addField(panel, 1, 3, 1, getHerstellerFeld());
        FormHelper.addLabel(panel, 2, 3, "Typ");
        FormHelper.fixWidth(getTypFeld(), 70);
        FormHelper.addField(panel, 3, 3, 1, getTypFeld());
        // Komponenten
        FormHelper.addSeparator(panel, 0, 4, 4, "Komponenten");
        FormHelper.addField(panel, 0, 5, 2, getKompaktCheck());
        FormHelper.addField(panel, 0, 6, 2, getVorschlammCheck());
        FormHelper.addLabel(panel, 2, 6, "Volumen");
        FormHelper.fixWidth(getVsf1Feld(), 70);
        FormHelper.addField(panel, 3, 6, 1, getVsf1Feld());
        FormHelper.addLabel(panel, 4, 6, "L");
        FormHelper.addField(panel, 0, 7, 1, getSchlammfangCheck());
        FormHelper.addLabel(panel, 2, 7, "Volumen");
        FormHelper.fixWidth(getVsf2Feld(), 70);
        FormHelper.addField(panel, 3, 7, 1, getVsf2Feld());
        FormHelper.addLabel(panel, 4, 7, "L");
        FormHelper.addLabel(panel, 0, 8, "Abscheider");
        FormHelper.addField(panel, 0, 9, 1, getBenzinabscheiderCheck());
        FormHelper.addField(panel, 1, 9, 1, getKoaabscheiderCheck());
        FormHelper.addLabel(panel, 2, 9, "NG");
        FormHelper.fixWidth(getNenngroesseFeld(), 70);
        FormHelper.addField(panel, 3, 9, 1, getNenngroesseFeld());
        FormHelper.addField(panel, 0, 10, 4, getProbeCheck());
        FormHelper.addField(panel, 0, 11, 2, getWarnanlageCheck());
        FormHelper.addField(panel, 0, 12, 2, getKreisCheck());
        FormHelper.addField(panel, 0, 13, 2, getEmulsionCheck());
        FormHelper.addField(panel, 0, 14, 2, getSchwimmerCheck());
        FormHelper.addField(panel, 0, 15, 3, getRueckhaltCheck());
        // Bemerkungen:
        FormHelper.addSeparator(panel, 0, 16, 4, "Bemerkungen");
        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungsArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        FormHelper.addFull(panel, 17, bemerkungsScroller, 80);


        return panel;

    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#fillForm()
     */
    @Override
    protected void fillForm() {

        Anh49Abscheiderdetails details = this.getDetails();
    	// Nur für vorhandene Abscheider Werte laden.

if (details.getId() != null) {
            getLageFeld().setText(details.getLage());
            getHerstellerFeld().setText(details.getHersteller());
            getNrFeld().setValue(details.getAbscheidernr());
            getVsf1Feld().setValue(details.getVsf1());
            getVsf2Feld().setValue(details.getVsf2());
            getNenngroesseFeld().setValue(details.getNenngroesse());
            getBemerkungsArea().setText(details.getBemerkung());
            getRueckhaltCheck().setSelected(details.getRueckhalt());
            getSchlammfangCheck().setSelected(details.getSchlammfang());
            getBenzinabscheiderCheck().setSelected(details.getBenzinabscheider());
            getKoaabscheiderCheck().setSelected(details.getKoaabscheider());
            getKompaktCheck().setSelected(details.getKompakt());
            getEmulsionCheck().setSelected(details.getEmulsionsspaltanlage());
            getSchwimmerCheck().setSelected(details.getSchwimmer());
            getTypFeld().setText(details.getTyp());
            getKreisCheck().setSelected(details.getKreis());
            getProbeCheck().setSelected(details.getProbe());
            getWarnanlageCheck().setSelected(details.getWarnanlage());
            getVorschlammCheck().setSelected(details.getVorschlamm());

    	}
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#canSave()
     */
    @Override
    protected boolean canSave() {
        return true;
    }

    /**
     * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
     */
    @Override
    protected boolean doSave() {
    	Anh49Abscheiderdetails details = this.getDetails();

    	// Lage:
        details.setLage(lageFeld.getText());

        //Typ:
        details.setTyp(typFeld.getText());

        // Hersteller:
        details.setHersteller(herstellerFeld.getText());

        // TODO: (MVC) We are managing database related formating here in the gui
        // Abscheidernummer:
        Integer nr = ((IntegerField)nrFeld).getIntValue();
        details.setAbscheidernr(nr);

        //Volumen vorgeschaltetet Schlammfang:
        Integer vsf1 = ((IntegerField)vsf1Feld).getIntValue();
        details.setVsf1(vsf1);

        // Volumen Schlammfang:
        Integer vsf2 = ((IntegerField)vsf2Feld).getIntValue();
        details.setVsf2(vsf2);

        // Nenngroesse Abscheider:
        Integer nenngroesse = ((IntegerField)nenngroesseFeld).getIntValue();
        details.setNenngroesse(nenngroesse);

        //Tankstelle ja/nein
        details.setRueckhalt(getRueckhaltCheck().isSelected());

        //vorgeschalteter Schlammfang ja/nein
        details.setVorschlamm(getVorschlammCheck().isSelected());

        //Schlammfang ja/nein
        details.setSchlammfang(getSchlammfangCheck().isSelected());

        //Probenahmemöglichkeit ja/nein
        details.setProbe(getProbeCheck().isSelected());

      //Warnanlage ja/nein
        details.setWarnanlage(getWarnanlageCheck().isSelected());

        //Benzinabscheider ja/nein
        details.setBenzinabscheider(getBenzinabscheiderCheck().isSelected());

        //Koaleszenzabscheider ja/nein
        details.setKoaabscheider(getKoaabscheiderCheck().isSelected());

        //Kompaktanlage ja/nein
        details.setKompakt(getKompaktCheck().isSelected());

        //Emulsionsspaltung ja/nein
        details.setEmulsionsspaltanlage(getEmulsionCheck().isSelected());

        //Schwimmer ja/nein
        details.setSchwimmer(getSchwimmerCheck().isSelected());

        //Kreislaufanlage ja/nein
        details.setKreis(getKreisCheck().isSelected());

        // Bemerkungen:
        details.setBemerkung(bemerkungsArea.getText());

        boolean save = details.merge();

        if (save) { // Args, this was "save = true" FAIL!
            log.debug("Änderungen gespeichert!");
        }

        return save;
    }


    // Getter für Widgets:

    private JCheckBox getBenzinabscheiderCheck() {
        if (benzinabscheiderCheck == null) {
            benzinabscheiderCheck = new JCheckBox("Kl. II");
        }
        return benzinabscheiderCheck;
    }
    private JCheckBox getEmulsionCheck() {
        if (emulsionCheck == null) {
            emulsionCheck = new JCheckBox("Emulsionsspaltanlage");
        }
        return emulsionCheck;
    }
    private JTextField getHerstellerFeld() {
        if (herstellerFeld == null) {
            herstellerFeld = new LimitedTextField(50);
        }
        return herstellerFeld;
    }
    private JCheckBox getKompaktCheck() {
        if (kompaktCheck == null) {
            kompaktCheck = new JCheckBox("Kompaktanlage");
        }
        return kompaktCheck;
    }
    private JCheckBox getKoaabscheiderCheck() {
        if (koaabscheiderCheck == null) {
            koaabscheiderCheck = new JCheckBox("Kl. I");
        }
        return koaabscheiderCheck;
    }
    private JTextField getLageFeld() {
        if (lageFeld == null) {
            lageFeld = new LimitedTextField(50);
        }
        return lageFeld;

    }
      private JTextField getTypFeld() {
            if (typFeld == null) {
                typFeld = new LimitedTextField(50);
            }
            return typFeld;
    }
    private JFormattedTextField getNenngroesseFeld() {
        if (nenngroesseFeld == null) {
            nenngroesseFeld = new IntegerField();
        }
        return nenngroesseFeld;

    }
    private JFormattedTextField getVsf2Feld() {
        if (vsf2Feld == null) {
            vsf2Feld = new IntegerField();
        }
        return vsf2Feld;
    }

    private JFormattedTextField getVsf1Feld() {
        if (vsf1Feld == null) {
            vsf1Feld = new IntegerField();
        }
        return vsf1Feld;
    }
    private JFormattedTextField getNrFeld() {
        if (nrFeld == null) {
            nrFeld = new IntegerField();
        }
        return nrFeld;
    }
    private JCheckBox getSchlammfangCheck() {
        if (schlammfangCheck == null) {
            schlammfangCheck = new JCheckBox("Schlammfang");
        }
        return schlammfangCheck;
    }
    private JCheckBox getVorschlammCheck() {
        if (vorschlammCheck == null) {
        	vorschlammCheck = new JCheckBox("vorg.Schlammfang");
        }
        return vorschlammCheck;

    }
    private JCheckBox getSchwimmerCheck() {
        if (schwimmerCheck == null) {
            schwimmerCheck = new JCheckBox("Schwimmer");
        }
        return schwimmerCheck;
    }
    private JCheckBox getRueckhaltCheck() {
        if (rueckhaltCheck == null) {
            rueckhaltCheck = new JCheckBox("Rückhaltefunktion gemäß AwSV");
        }
        return rueckhaltCheck;
    }
    private JCheckBox getProbeCheck() {
    	if (probeCheck == null) {
    		probeCheck = new JCheckBox("Probenahmestelle/-möglichkeit");
       }
    return probeCheck;

    }
    private JCheckBox getWarnanlageCheck() {
    	if (warnanlageCheck == null) {
    		warnanlageCheck = new JCheckBox("Warnanlage");
    }
    return warnanlageCheck;


    }
    private JCheckBox getKreisCheck() {
        if (kreisCheck == null) {
        	kreisCheck = new JCheckBox("Kreislaufanlage");
        }
        return kreisCheck;

    }
    private JTextArea getBemerkungsArea() {
        if (bemerkungsArea == null) {
            bemerkungsArea = new LimitedTextArea(255);
            bemerkungsArea.setLineWrap(true);
            bemerkungsArea.setWrapStyleWord(true);
        }
        return bemerkungsArea;
	}




    }



