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

import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;

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
    private JTextField herstellerFeld;
    private JFormattedTextField nrFeld;
    private JFormattedTextField vonFeld;
    private JFormattedTextField ngsfFeld;
    private JFormattedTextField ngkaFeld;
    private JFormattedTextField ngfaFeld;
    private JCheckBox tankstelleCheck;
    private JCheckBox schlammfangCheck;
    private JCheckBox benzinabscheiderCheck;
    private JCheckBox koalenszenzfilterCheck;
    private JCheckBox integriertCheck;
    private JCheckBox emulsionCheck;
    private JCheckBox schwimmerCheck;
    private JCheckBox wohnhausCheck;

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


    @Override
    protected JComponent buildContentArea() {

        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungsArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        PanelBuilder builder = new PanelBuilder();
        builder.setWeight(1, 0);
        builder.setFill(true, false);
        builder.setAnchor(GridBagConstraints.NORTHWEST);
        builder.setBorder(new EmptyBorder(5, 5, 5, 5));
        builder.setInsets(0, 0, 5, 5);

        builder.addSeparator("Stammdaten", true);
        builder.addComponent(getNrFeld(), "Nr.:");
        builder.addComponent(getVonFeld(), "Von:", true);
        builder.addComponent(getLageFeld(), "Lage:", true);
        builder.addComponent(getHerstellerFeld(), "Hersteller:", true);
        builder.addComponent(getNgfaFeld(), "Nenngröße:", true);
        builder.addSeparator("Ölabscheider", true);
        builder.addComponent(getNgsfFeld(), "NG SF:");
        builder.addComponent(getNgkaFeld(), "NG KA:", true);
        builder.addComponents(true, getTankstelleCheck(), getSchlammfangCheck());
        builder.addComponents(true, getBenzinabscheiderCheck(), getKoalenszenzfilterCheck());
        builder.addComponents(true, getIntegriertCheck(), getEmulsionCheck());
        builder.addComponents(true, getSchwimmerCheck(), getWohnhausCheck());
        builder.addSeparator("Bemerkungen", true);
        builder.setFill(true, true);
        builder.setWeight(1, 4);
        builder.setGridHeight(5);
        builder.addComponent(bemerkungsScroller, true);

        return builder.getPanel();
    
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
            getVonFeld().setValue(details.getVon());
            getNgsfFeld().setValue(details.getNgSf());
            getNgkaFeld().setValue(details.getNgKa());
            getNgfaFeld().setValue(details.getNenngroesse());
            getBemerkungsArea().setText(details.getBemerkung());

            getTankstelleCheck().setSelected(details.getTankstelle());
            getSchlammfangCheck().setSelected(details.getSchlammfang());
            getBenzinabscheiderCheck().setSelected(
                    details.getBenzinOelabscheider());
            getKoalenszenzfilterCheck().setSelected(details.getKoaleszenzfilter());
            getIntegriertCheck().setSelected(details.getIntegriert());
            getEmulsionCheck().setSelected(details.getEmulsionsspaltanlage());
            getSchwimmerCheck().setSelected(details.getSchwimmer());
            getWohnhausCheck().setSelected(details.getWohnhaus());
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

        // Hersteller:
        details.setHersteller(herstellerFeld.getText());

        // TODO: (MVC) We are managing database related formating here in the gui
        // Abscheidernummer:
        Integer nr = ((IntegerField)nrFeld).getIntValue();
        details.setAbscheidernr(nr);

        // Von:
        Integer von = ((IntegerField)vonFeld).getIntValue();
        details.setVon(von);

        // Nenngroesse Sandfang:
        Integer ngsf = ((IntegerField)ngsfFeld).getIntValue();
        details.setNgSf(ngsf);

        // Nenngroesse Koaleszenzabscheider:
        Integer ngka = ((IntegerField)ngkaFeld).getIntValue();
        details.setNgKa(ngka);

        // Nenngroesse Fettabscheider:
        Integer ngfa = ((IntegerField)ngfaFeld).getIntValue();
        details.setNenngroesse(ngfa);

        //Tankstelle ja/nein
        details.setTankstelle(getTankstelleCheck().isSelected());

        //Schlammfang ja/nein
        details.setSchlammfang(getSchlammfangCheck().isSelected());

        //Benzinabscheider ja/nein
        details.setBenzinOelabscheider(getBenzinabscheiderCheck().isSelected());

        //Koaleszenzabscheider ja/nein
        details.setKoaleszenzfilter(getKoalenszenzfilterCheck().isSelected());

        //Integriert ja/nein
        details.setIntegriert(getIntegriertCheck().isSelected());

        //Emulsionsspaltung ja/nein
        details.setEmulsionsspaltanlage(getEmulsionCheck().isSelected());

        //Schwimmer ja/nein
        details.setSchwimmer(getSchwimmerCheck().isSelected());

        //Wohnhaus ja/nein
        details.setWohnhaus(getWohnhausCheck().isSelected());

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
            benzinabscheiderCheck = new JCheckBox("Benzinabscheider");
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
    private JCheckBox getIntegriertCheck() {
        if (integriertCheck == null) {
            integriertCheck = new JCheckBox("integriert");
        }
        return integriertCheck;
    }
    private JCheckBox getKoalenszenzfilterCheck() {
        if (koalenszenzfilterCheck == null) {
            koalenszenzfilterCheck = new JCheckBox("Koaleszenzfilter");
        }
        return koalenszenzfilterCheck;
    }
    private JTextField getLageFeld() {
        if (lageFeld == null) {
            lageFeld = new LimitedTextField(50);
        }
        return lageFeld;
    }
    private JFormattedTextField getNgkaFeld() {
        if (ngkaFeld == null) {
            ngkaFeld = new IntegerField();
        }
        return ngkaFeld;
    }
    private JFormattedTextField getNgfaFeld() {
        if (ngfaFeld == null) {
            ngfaFeld = new IntegerField();
        }
        return ngfaFeld;
    }
    private JFormattedTextField getNgsfFeld() {
        if (ngsfFeld == null) {
            ngsfFeld = new IntegerField();
        }
        return ngsfFeld;
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
    private JCheckBox getSchwimmerCheck() {
        if (schwimmerCheck == null) {
            schwimmerCheck = new JCheckBox("Schwimmer");
        }
        return schwimmerCheck;
    }
    private JCheckBox getTankstelleCheck() {
        if (tankstelleCheck == null) {
            tankstelleCheck = new JCheckBox("Tankstelle");
        }
        return tankstelleCheck;
    }
    private JFormattedTextField getVonFeld() {
        if (vonFeld == null) {
            vonFeld = new IntegerField();
        }
        return vonFeld;
    }
    private JCheckBox getWohnhausCheck() {
        if (wohnhausCheck == null) {
            wohnhausCheck = new JCheckBox("Wohnhaus");
        }
        return wohnhausCheck;
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

