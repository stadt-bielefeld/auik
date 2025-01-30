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
    private JTextField typFeld;
    private JTextField herstellerFeld;
    private JFormattedTextField nrFeld;
    private JFormattedTextField vonFeld;
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

        String columnString = "right:pref, 3dlu, 40dlu, 3dlu, 30dlu, 3dlu, 40dlu, 3dlu, 30dlu";
        FormLayout layout = new FormLayout(
                // Spalten
                columnString,
                // Zeilen:
                "pref, 3dlu, " +    //1 Stammdaten    - Abscheider
                "pref, 3dlu, " +    //3
                "pref, 3dlu, " +    //5
                "pref, 3dlu, " +    //7
                "pref, 3dlu, " +    //9
                "pref, 3dlu, " +    //11
                "pref, 3dlu, " +    //13
                "pref, 3dlu, " +    //15
                "pref, 3dlu, " +    //17
                "pref, 3dlu, " +    //19
                "pref, 3dlu, " +    //21
                "pref, 3dlu, " +    //23
                "pref, 3dlu, " +    //25
                "pref, 3dlu, " +    //27
                "pref, 3dlu, " +    //29
                "pref, 3dlu, " +    //31
                "pref, 3dlu, " +    //33
                "40dlu:g");         //35
        //layout.setRowGroups(new int[][]{{1,3,5,7,9,}});

        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

    // Stamdaten -------------------------- ----------
        builder.addSeparator("Stammdaten",     		cc.xyw(1, 1, 7));

        builder.addLabel("Nr.:",                	cc.xy(1, 3));
        	builder.add(getNrFeld(),            	cc.xy(3, 3));

        builder.addLabel("Von:",                	cc.xy(5, 3, "right, center"));
        	builder.add(getVonFeld(),           	cc.xy(7, 3));
        builder.addLabel("Lage:",              		cc.xy(1, 5));
        	builder.add(getLageFeld(),          	cc.xyw(3, 5, 5));
        builder.addLabel("Hersteller:",        		cc.xy(1, 7));
        	builder.add(getHerstellerFeld(),    	cc.xy(3, 7));
        builder.addLabel("Typ:", 			    	cc.xy(5, 7, "right, center"));
       	builder.add(getTypFeld(), 					cc.xy(7, 7));
    // Komponenten
       	builder.addSeparator("Komponenten",     	cc.xyw(1, 9, 7));

        builder.add(getKompaktCheck(),       		cc.xyw(1, 11, 3));
        builder.add(getVorschlammCheck(),   		cc.xyw(1, 13, 3));
        builder.addLabel("Volumen:",				cc.xy(5, 13, "right, center"));
        builder.add(getVsf1Feld(), 					cc.xy(7, 13));
        builder.addLabel("L",						cc.xy(9, 13));
        builder.add(getSchlammfangCheck(), 			cc.xy(1, 15));
        builder.addLabel("Volumen:",				cc.xy(5, 15, "right, center"));
        builder.add(getVsf2Feld(), 					cc.xy(7, 15));
        builder.addLabel("L",						cc.xy(9, 15));
        builder.addLabel("Abscheider:", 			cc.xy( 1, 17, "left, center"));
        builder.add(getBenzinabscheiderCheck(),    	cc.xy(1, 19, "left, center"));
        builder.add(getKoaabscheiderCheck(), 		cc.xy( 3 , 19));
        builder.addLabel("NG:",            			cc.xy( 5, 19, "right, center"));
        builder.add(getNenngroesseFeld(),           cc.xy( 7, 19));
        builder.add(getProbeCheck(), 				cc.xyw( 1, 21, 7));
        builder.add(getWarnanlageCheck(),			cc.xyw( 1, 23, 3));
        builder.add(getKreisCheck(),		 		cc.xyw( 1, 25, 3));
        builder.add(getEmulsionCheck(),             cc.xyw( 1, 27, 3));
        builder.add(getSchwimmerCheck(),    		cc.xyw( 1, 29, 3));
        builder.add(getRueckhaltCheck(),			cc.xyw( 1, 31, 5));

    // Bemerkungen:
        builder.addSeparator("Bemerkungen",    cc.xyw(1, 33, 7));
        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungsArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.add(bemerkungsScroller,        cc.xyw(1, 35, 7, "fill, fill"));


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

        // Von:
        Integer von = ((IntegerField)vonFeld).getIntValue();
        details.setVon(von);

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
    private JFormattedTextField getVonFeld() {
        if (vonFeld == null) {
            vonFeld = new IntegerField();
        }
        return vonFeld;
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



