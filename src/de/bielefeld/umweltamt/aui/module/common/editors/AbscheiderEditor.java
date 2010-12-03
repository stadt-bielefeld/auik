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

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;

/**
 * Ein Dialog zum Bearbeiten von Abscheiderdetails.
 * @author Gerd Genuit
 * @author David Klotz
 */
public class AbscheiderEditor extends AbstractBaseEditor{
    private JTextField lageFeld;
    private JTextField herstellerFeld;
    private JFormattedTextField nrFeld;
    private JFormattedTextField vonFeld;
    private JTextField baujahrFeld;
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
        getLageFeld().setText(getDetails().getLage());
        getHerstellerFeld().setText(getDetails().getHersteller());
        getNrFeld().setValue(getDetails().getAbscheidernr());
        getVonFeld().setValue(getDetails().getVon());
        getNgsfFeld().setValue(getDetails().getNgSf());
        getNgbaFeld().setValue(getDetails().getNgBa());
        getNgkaFeld().setValue(getDetails().getNgKa());
        getNgfaFeld().setValue(getDetails().getNenngroesse());
        getBemerkungsArea().setText(getDetails().getBemerkung());

        if (getDetails().getTankstelle() == true) {
            getTankstelleCheck().setSelected(true);
        }
        else {
            getTankstelleCheck().setSelected(false);
        }

        if (getDetails().getSchlammfang() == true) {
            getSchlammfangCheck().setSelected(true);
        }
        else {
            getSchlammfangCheck().setSelected(false);
        }

        if (getDetails().getBenzinOelabscheider() == true) {
            getBenzinabscheiderCheck().setSelected(true);
        }
        else {
            getBenzinabscheiderCheck().setSelected(false);
        }

        if (getDetails().getKoaleszenzfilter() == true) {
            getKoalenszenzfilterCheck().setSelected(true);
        }
        else {
            getKoalenszenzfilterCheck().setSelected(false);
        }

        if (getDetails().getIntegriert() == true) {
            getIntegriertCheck().setSelected(true);
        }
        else {
            getIntegriertCheck().setSelected(false);
        }

        if (getDetails().getEmulsionsspaltanlage() == true) {
            getEmulsionCheck().setSelected(true);
        }
        else {
            getEmulsionCheck().setSelected(false);
        }

        if (getDetails().getSchwimmer() == true) {
            getSchwimmerCheck().setSelected(true);
        }
        else {
            getSchwimmerCheck().setSelected(false);
        }

        if (getDetails().getWohnhaus() == true) {
            getWohnhausCheck().setSelected(true);
        }
        else {
            getWohnhausCheck().setSelected(false);
        }
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
        // Lage:
        String lage = (String) lageFeld.getText();
        getDetails().setLage(lage);

        // Hersteller:
        String herst = (String) herstellerFeld.getText();
        getDetails().setHersteller(herst);

        // Abscheidernummer:
        Integer nr = ((IntegerField)nrFeld).getIntValue();
        getDetails().setAbscheidernr(nr);

        // Von:
        Integer von = ((IntegerField)vonFeld).getIntValue();
        getDetails().setVon(von);

        // Nenngroesse Sandfang:
        Integer ngsf = ((IntegerField)ngsfFeld).getIntValue();
        getDetails().setNgSf(ngsf);

        // Nenngroesse Benzinabscheider:
        Integer ngba = ((IntegerField)ngbaFeld).getIntValue();
        getDetails().setNgBa(ngba);

        // Nenngroesse Koaleszenzabscheider:
        Integer ngka = ((IntegerField)ngkaFeld).getIntValue();
        getDetails().setNgKa(ngka);

        // Nenngroesse Fettabscheider:
        Integer ngfa = ((IntegerField)ngfaFeld).getIntValue();
        getDetails().setNenngroesse(ngfa);

        //Tankstelle ja/nein
        if (getTankstelleCheck().isSelected())  {
            getDetails().setTankstelle(true);
        } else {
            getDetails().setTankstelle(false);
        }

        //Schlammfang ja/nein
        if (getSchlammfangCheck().isSelected())  {
            getDetails().setSchlammfang(true);
        } else {
            getDetails().setSchlammfang(false);
        }

        //Benzinabscheider ja/nein
        if (getBenzinabscheiderCheck().isSelected())  {
            getDetails().setBenzinOelabscheider(true);
        } else {
            getDetails().setBenzinOelabscheider(false);
        }

        //Koaleszenzabscheider ja/nein
        if (getKoalenszenzfilterCheck().isSelected())  {
            getDetails().setKoaleszenzfilter(true);
        } else {
            getDetails().setKoaleszenzfilter(false);
        }

        //Integriert ja/nein
        if (getIntegriertCheck().isSelected())  {
            getDetails().setIntegriert(true);
        } else {
            getDetails().setIntegriert(false);
        }

        //Emulsionsspaltung ja/nein
        if (getEmulsionCheck().isSelected())  {
            getDetails().setEmulsionsspaltanlage(true);
        } else {
            getDetails().setEmulsionsspaltanlage(false);
        }

        //Schwimmer ja/nein
        if (getSchwimmerCheck().isSelected())  {
            getDetails().setSchwimmer(true);
        } else {
            getDetails().setSchwimmer(false);
        }

        //Wohnhaus ja/nein
        if (getWohnhausCheck().isSelected())  {
            getDetails().setWohnhaus(true);
        } else {
            getDetails().setWohnhaus(false);
        }

        // Bemerkungen:
        String bem = (String) bemerkungsArea.getText();
        getDetails().setBemerkung(bem);

        boolean save = Anh49Abscheiderdetails.saveAbscheider(getDetails());
        if (save = true) {
            AUIKataster.debugOutput("Änderungen gespeichert!", "editStandort");
            return true;
        } else {
            return false;
        }
    }


    // Getter für Widgets:

    private JTextField getBaujahrFeld() {
        if (baujahrFeld == null) {
            baujahrFeld = new LimitedTextField(50);
        }
        return baujahrFeld;
    }
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

