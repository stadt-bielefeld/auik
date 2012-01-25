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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
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
    private JTextField herstellerFeld;
    private JFormattedTextField nrFeld;
    private JFormattedTextField vonFeld;
    private JFormattedTextField ngsfFeld;
    private JFormattedTextField ngbaFeld;
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


    protected JComponent buildContentArea() {

        String columnString = "right:pref, 3dlu, 30dlu, 10dlu:g, left:max(pref;60dlu):grow";
        FormLayout layout = new FormLayout(
                // Spalten
                columnString,
                // Zeilen:
                "pref, 3dlu, " +    //1 Stammdaten    - Abscheider
                "pref, 3dlu, " +    //3
                "pref, 3dlu, " +    // 5
                "pref, 3dlu, " +    //7
                "pref, 3dlu, " +    //9
                "pref, 3dlu, " +    //11
                "pref, 3dlu, " +    //13
                "pref, 3dlu, " +    //15
                "pref, 3dlu, " +    //17
                "pref, 3dlu, " +    //19
                "pref, 3dlu, " +    //21
                "pref, 3dlu, " +    //23
                "40dlu:g");            //25
        //layout.setRowGroups(new int[][]{{1,3,5,7,9,}});

        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        // Stamdaten ------------------------------------
        builder.addSeparator("Stammdaten",    cc.xyw(1, 1, 5));
        builder.addLabel("Lage:",            cc.xy( 1, 3));
        builder.add(getLageFeld(),            cc.xyw(3, 3, 3));
        builder.addLabel("Hersteller:",        cc.xy( 1, 5));
        builder.add(getHerstellerFeld(),    cc.xyw(3, 5, 3));
        builder.addLabel("Nr.:",            cc.xy( 1, 7));
        builder.add(getNrFeld(),             cc.xy( 3, 7));
        builder.add(getTankstelleCheck(),    cc.xy( 5, 7));
        builder.addLabel("Von:",            cc.xy( 1, 9));
        builder.add(getVonFeld(),             cc.xy( 3, 9));
        builder.add(getSchlammfangCheck(),    cc.xy( 5, 9));
        builder.add(getBenzinabscheiderCheck(),    cc.xy( 5,11));
        builder.addLabel("NG SF:",            cc.xy( 1,13));
        builder.add(getNgsfFeld(),             cc.xy( 3,13));
        builder.add(getKoalenszenzfilterCheck(), cc.xy( 5,13));
        builder.addLabel("NG BA:",            cc.xy( 1,15));
        builder.add(getNgbaFeld(),            cc.xy( 3,15));
        builder.add(getIntegriertCheck(),    cc.xy( 5,15));
        builder.addLabel("NG KA:",            cc.xy( 1,17));
        builder.add(getNgkaFeld(),            cc.xy( 3,17));
        builder.add(getEmulsionCheck(),        cc.xy(5,17));
        builder.addLabel("NG FA:",            cc.xy( 1,19));
        builder.add(getNgfaFeld(),            cc.xy( 3,19));
        builder.add(getSchwimmerCheck(),    cc.xy(5,19));
        builder.add(getWohnhausCheck(),        cc.xy(5,21));
        builder.addSeparator("Bemerkungen",    cc.xyw(1,23,5));
        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungsArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.add(bemerkungsScroller,        cc.xyw(1,25,5, "fill, fill"));

        return builder.getPanel();
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#fillForm()
     */
    protected void fillForm() {
    	Anh49Abscheiderdetails details = this.getDetails();
        getLageFeld().setText(details.getLage());
        getHerstellerFeld().setText(details.getHersteller());
        getNrFeld().setValue(details.getAbscheidernr());
        getVonFeld().setValue(details.getVon());
        getNgsfFeld().setValue(details.getNgSf());
        getNgbaFeld().setValue(details.getNgBa());
        getNgkaFeld().setValue(details.getNgKa());
        getNgfaFeld().setValue(details.getNenngroesse());
        // TODO: Do we need this field coming from other views?
        getNgfaFeld().setEnabled(false);
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

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#canSave()
     */
    protected boolean canSave() {
        return true;
    }

    /**
     * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
     */
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

        // Nenngroesse Benzinabscheider:
        Integer ngba = ((IntegerField)ngbaFeld).getIntValue();
        details.setNgBa(ngba);

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

        boolean save = Anh49Abscheiderdetails.saveAbscheider(details);
        
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
    private JFormattedTextField getNgbaFeld() {
        if (ngbaFeld == null) {
            ngbaFeld = new IntegerField();
        }
        return ngbaFeld;
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

