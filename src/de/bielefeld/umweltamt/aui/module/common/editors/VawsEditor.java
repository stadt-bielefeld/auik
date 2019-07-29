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
 * $Id: VawsEditor.java,v 1.6 2009-10-06 09:17:17 u633d Exp $
 *
 * Erstellt am 03.09.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.4  2008/09/01 07:03:46  u633d
 * *** empty log message ***
 *
 * Revision 1.2  2008/06/12 10:21:42  u633d
 * diverse Bugfixes
 *
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.5  2005/11/02 13:52:39  u633d
 * - Version vom 2.11.
 *
 * Revision 1.4  2005/10/13 13:00:25  u633d
 * Version vom 13.10.
 *
 * Revision 1.3  2005/09/28 11:11:12  u633d
 * - Version vom 28.9.
 *
 * Revision 1.2  2005/09/14 11:25:36  u633d
 * - Version vom 14.9.
 *
 * Revision 1.1  2005/09/07 10:48:22  u633d
 * - Version vom 7.9.05
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.awsv.Abfuellflaeche;
import de.bielefeld.umweltamt.aui.mappings.awsv.Abscheider;
import de.bielefeld.umweltamt.aui.mappings.awsv.Anlagenchrono;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Gebuehrenarten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Jgs;
import de.bielefeld.umweltamt.aui.mappings.awsv.Kontrollen;
import de.bielefeld.umweltamt.aui.mappings.awsv.Verwaltungsgebuehren;
import de.bielefeld.umweltamt.aui.mappings.awsv.Verwaltungsverf;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.KommaDouble;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein Editor für VAWS-Datensätze.
 * @author David Klotz
 */
public class VawsEditor extends AbstractBaseEditor {
    private static final long serialVersionUID = -6239513794286892981L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private Abfuellflaeche abfuellflaeche;
    private Abscheider abscheider;
    private Jgs jgs;

    private JPanel topPanel;
    private JTabbedPane tabbedPane;

    // Für das Kontextmenü in den Tabellen
    private Action tabellenItemLoeschAction;
    private JPopupMenu tabellenPopup;

    // Widgets fürs Top-Panel:
    private JLabel header;
//    private JLabel subHeader;
//    private JLabel hnrLabel;
//    private JButton reportButton;
    private LimitedTextField hnrFeld;
    private JComboBox fluessigkeitBox;
    private JComboBox vbfBox;
    private JComboBox gefStufeBox;
    private JComboBox wgkBox;

    //  Tabs:
    // Widgets für alle Daten-Tabs:
    private IntegerField baujahrFeld;
    private TextFieldDateChooser inbetriebnahmeChooser;
    private TextFieldDateChooser genehmigungChooser;
    private TextFieldDateChooser erfassungChooser;
    private TextFieldDateChooser stillegungChooser;
    private LimitedTextField aktenzeichenField;
    private IntegerField groesseField;
    private DoubleField pruefTurnusFeld;
    private LimitedTextArea bemerkungArea;
    private JTable anlagenChronoTabelle;
    private VawsAnlagenChronoModel anlagenChronoModel;
    private JSplitPane tabellenSplit;
    
    // Benötigt bei Lageranlagen & Rohrleitungen
    private JComboBox behaelterArtBox;
    private JComboBox materialBox;

    // Beschreibung für die Felder bei VAwS-Abscheider ### START
    private JPanel datenVAWSAbscheiderTab;
    private JPanel ausfuehrungVAWSAbscheiderTab;
    private JPanel schutzvorkehrungenVAWSAbscheiderTab;

    // Daten TAB ### START
//    private LimitedTextField flüssigkField;
    private JCheckBox kompSFCheck;
    private JCheckBox kompLFCheck;
    private JCheckBox kompKCheck;
    private JCheckBox kompPSCheck;
    private JCheckBox kompaktCheck;
    // Daten TAB ### END

    // Ausführung TAB ### START
    private LimitedTextField schlammHerstField;
    private LimitedTextField schlammTypField;
    private DoubleField schlammSFVField;
    private LimitedTextField schlammMatField;
    private LimitedTextField schlammBeschField;

    private LimitedTextField abscheiderHerstField;
    private LimitedTextField abscheiderTypField;
    private LimitedTextField abscheiderPruefField;
    private DoubleField abscheiderNSField;
    private LimitedTextField abscheiderMatField;
    private LimitedTextField abscheiderBeschField;
    private DoubleField abscheideroelField;

    private DoubleField zulDNField;
    private LimitedTextField zulMatField;
    private DoubleField zulLField;
    private DoubleField verbDNField;
    private LimitedTextField verbMatField;
    private DoubleField verbLField;
    private DoubleField sonsDNField;
    private LimitedTextField sonsMatField;
    private DoubleField sonsLField;
    // Ausführung TAB ### END

    // Schutzvorkehrungen TAB ### START
    private JCheckBox ueberCheck;
    private JCheckBox waschCheck;
    private JCheckBox abgabeCheck;
    private JCheckBox hochCheck;
    private JCheckBox belueftCheck;
    private JCheckBox rueckCheck;
    // Schutzvorkehrungen TAB ### END
    // Beschreibung für die Felder bei VAwS-Abscheider ### END
    // Daten (Lageranlagen)
    private JPanel datenLageranlagenTab;
    private DoubleField mengeFeld;
    // Schutzvorkehrungen (Lageranlagen)
    private JPanel schutzLageranlagenTab;
    private JCheckBox doppelWandigCheck;
    private JCheckBox leckAnzeigeCheck;
    private JCheckBox auffangRaumCheck;
    private JCheckBox leckSchutzAuskleidungCheck;
    private JCheckBox grenzWertGeberCheck;
    private JCheckBox kellerLagerungCheck;
    private JCheckBox innenBeschichtungCheck;
    private JCheckBox schutzSensorCheck;
    private JCheckBox schutzFolieCheck;
    private JCheckBox schutzAntiheberCheck;
    private LimitedTextArea beschreibungAFeld;
    private TextArea beschreibungSFeld;
    // Leitungen (Lageranlagen)
    private JPanel leitungenLageranlagenTab;
    private JCheckBox oberIrdischCheck;
    private JCheckBox unterIrdischCheck;
    private JCheckBox saugLeitungCheck;
    private JCheckBox rohrKathSchCheck;
    private JCheckBox ausKupferCheck;
    private JCheckBox ausStahlCheck;
    private JCheckBox schutzrohrCheck;
    private JCheckBox ausHdpeCheck;
    private JCheckBox druckleitungCheck;
    private LimitedTextArea beschreibungRFeld;

    // Daten (Rohrleitungen)
    private JPanel datenRohrleitungenTab;
    private JComboBox ausfuehrungBox;

    // Daten (Abfüllflächen)
    private JPanel datenAbfuellflaechenTab;
    private JCheckBox eohCheck;
    private JCheckBox efCheck;
    private JCheckBox svbCheck;
    private ButtonGroup zustandGroup;
    private JRadioButton saniertCheck;
    private JRadioButton neuErstelltCheck;
    private JRadioButton unbktCheck;

    // Daten (JGS)
    private JPanel datenJgsTab;
    private JPanel datenAbfuellflaechenJgsTab;
    private IntegerField lagerflaecheFeld;
    private IntegerField abstandGewFeld;
    private LimitedTextField gewNameFeld;
    private IntegerField abstandBrunnenFeld;
    private LimitedTextField tierhaltungFeld;
    private JCheckBox seitenwandCheck;
    private IntegerField wandhoeheFeld;
    private LimitedTextField bodenplatteFeld;
    private JCheckBox ueberdachungCheck;
    private JComboBox auffangbehBox;
    private DoubleField volumenAuffangbehFeld;
    private JComboBox rohrleitungBox;
    private TextFieldDateChooser dichtheitChooser;
    private JCheckBox drainageCheck;
    private JCheckBox fuellanzeigerCheck;
    private JCheckBox schieberCheck;
    private JCheckBox abdeckungCheck;
    private JCheckBox leitung_geprueftCheck;
    
    // Ausführung (Abfüllflächen)
    private JPanel ausfuehrungAbfuellflaechenTab;
    private JComboBox bodenflaechenAusfBox;
    private DoubleField dickeFeld;
    private LimitedTextField gueteFeld;
    private LimitedTextField fugenMaterialFeld;
    private JComboBox niederschlagSchutzBox;
    private JCheckBox abscheiderVorhandenCheck;
    private LimitedTextArea beschrBodenflaecheArea;
    private LimitedTextArea beschrFugenmaterialArea;
    private LimitedTextArea beschrAblNiederschlArea;

    // Sachverständigenprüfung
    private JPanel svPruefungTab;
    private JTable svPruefungTabelle;
    private JComboBox prueferBox;
    private JComboBox pruefergebnisBox;
    private VawsKontrollenModel svPruefungModel;

    // Verwaltungsverfahren
    private JPanel verwVerfahrenTab;
    private JTable verwVerfahrenTabelle;
    private JComboBox massnahmenBox;
    private VerwVerfahrenModel verwVerfahrenModel;


    // Verwaltungsgebühren
    private JPanel verwGebuehrenTab;
    private JTable verwGebuehrenTabelle;
    private JComboBox gebArtenBox;
    private VerwGebuehrenModel verwGebuehrenModel;

    /**
     * Erzeugt einen neuen Dialog zum Bearbeiten von VAWS-Fachdaten.
     */
    public VawsEditor(Fachdaten fachdaten, HauptFrame owner) {
        //super("VAwS-Einzelanlage (" + fachdaten + ")", fachdaten, owner);
        super("VAwS-Einzelanlage " + (fachdaten.getBehaelterid() == null ? "(Neu)" : fachdaten.getBehaelterid().toString()), fachdaten, owner);
    }

    /**
     * Erzeugt einen neuen Dialog zum Bearbeiten von VAWS-Fachdaten.
     * Schaltet zu einem bestimmten Tab um.
     * @param tab "Sachverständigenprüfung", "Verwaltungsverfahren" oder "Herstellnummer"
     */
    public VawsEditor(Fachdaten fachdaten, HauptFrame owner, String tab) {
        this(fachdaten, owner);
        if ("Sachverständigenprüfung".equals(tab)) {
            tabbedPane.setSelectedComponent(getSvPruefungTab());
        } else if ("Verwaltungsverfahren".equals(tab)) {
            tabbedPane.setSelectedComponent(getVerwVerfahrenTab());
        } else if("Herstellnummer".equals(tab)) {
            // Der Reiter Daten wird aufgerufen
        }

    }

    /*
     * Überschrieben, damit die unterschiedlichen Anlagen-Arten ihre Größen
     * separat speichern, obwohl ja alle von der Klasse VawsFachdaten sind.
     *
     * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#getEditedClassName()
     */
    @Override
    protected String getEditedClassName() {
        String className = super.getEditedClassName();

        if (DatabaseQuery.isLageranlage(getFachdaten())) {
            className += "-Lageranlage";
        } else {
            className += "-" + getFachdaten().getAnlagenart();
        }

        return className;
    }

    /**
     * Liefert das bearbeitete Objekt.
     * @return Ein VawsFachdaten-Objekt.
     */
    public Fachdaten getFachdaten() {
        return (Fachdaten) getEditedObject();
    }

    public Abscheider getAbscheider() {
        if (abscheider == null) {
            abscheider = Abscheider.findByBehaelterid(getFachdaten().getBehaelterid());
            if (abscheider == null) {
                abscheider = new Abscheider();
                abscheider.setFachdaten(getFachdaten());
            }
        }
        return abscheider;
    }

    /**
     * Liefert die Abfüllflächen-Fachdaten zum bearbeiteten Objekt.
     * Achtung: Nur bei Abfüllflächen-Objekten aufrufen!
     * @return Ein VawsAbfuellflaechen-Objekt
     */
    private Abfuellflaeche getAbfuellflaeche() {
        if (abfuellflaeche == null) {
            abfuellflaeche = Abfuellflaeche.findByBehaelterid(getFachdaten().getBehaelterid());
            if (abfuellflaeche == null) {
                abfuellflaeche = new Abfuellflaeche();
                abfuellflaeche.setFachdaten(getFachdaten());
            }
        }
        return abfuellflaeche;
    }

    /**
     * Liefert die Abfüllflächen-Fachdaten zum bearbeiteten Objekt.
     * Achtung: Nur bei Abfüllflächen-Objekten aufrufen!
     * @return Ein VawsAbfuellflaechen-Objekt
     */
    private Jgs getJgs() {
        if (jgs == null) {
            jgs = Jgs.findByBehaelterid(getFachdaten().getBehaelterid());
            if (jgs == null) {
                jgs = new Jgs();
                jgs.setFachdaten(getFachdaten());
            }
        }
        return jgs;
    }


    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog#buildContentArea()
     */
    @Override
    protected JComponent buildContentArea() {
        // Widgets initialisieren:
        // Top-Panel
        header = new JLabel(" ", JLabel.LEFT);
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
//        subHeader = new JLabel(" ", JLabel.LEFT);
//        subHeader.setFont(new Font("SansSerif", Font.PLAIN, 14));

        hnrFeld = new LimitedTextField(100);

        fluessigkeitBox = new JComboBox(DatabaseQuery.getFluessigkeiten());
        fluessigkeitBox.setEditable(true);
        //fluessigkeitBox.setPrototypeDisplayValue("Lösungsmittelrückstände");

        vbfBox = new JComboBox(DatabaseQuery.getVawsVbfEinstufungen());
        vbfBox.setEditable(false);
        //vbfBox.setPrototypeDisplayValue(" A III ");

        gefStufeBox = new JComboBox(DatabaseQuery.getGefaehrdungsstufen());
        gefStufeBox.setEditable(false);
        //gefStufeBox.setPrototypeDisplayValue(" A ");

        String[] items = {"1","2","3","awg","nwg"};
        wgkBox = new JComboBox(items);
        wgkBox.setEditable(false);
        //wgkBox.setPrototypeDisplayValue(" 3 ");


        tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        // Widgets für alle Daten-Tabs:
        baujahrFeld = new IntegerField();
        inbetriebnahmeChooser = new TextFieldDateChooser();
        genehmigungChooser = new TextFieldDateChooser();
        erfassungChooser = new TextFieldDateChooser();
        stillegungChooser = new TextFieldDateChooser();
        aktenzeichenField = new LimitedTextField(50);
        pruefTurnusFeld = new DoubleField(0);

        behaelterArtBox = new JComboBox(DatabaseQuery.getBehaelterarten());
        behaelterArtBox.setEditable(false);

        materialBox = new JComboBox(DatabaseQuery.getMaterialien());
        // TODO: Material-Box editable / Tabelleninhalt?
        materialBox.setEditable(true);
        bemerkungArea = new LimitedTextArea(1000);

        // Daten für die Anlagenchronologie-Tabelle:
        anlagenChronoModel = new VawsAnlagenChronoModel();
        anlagenChronoTabelle = new JTable(anlagenChronoModel);
        anlagenChronoTabelle.setGridColor(new Color(230, 230, 230));
        anlagenChronoTabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
        anlagenChronoTabelle.getColumnModel().getColumn(1).setPreferredWidth(250);
        anlagenChronoTabelle.getColumnModel().getColumn(2).setPreferredWidth(50);
        anlagenChronoTabelle.getColumnModel().getColumn(3).setPreferredWidth(50);

        anlagenChronoTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showTabellenPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showTabellenPopup(e);
            }
        });

        anlagenChronoTabelle.getInputMap().put((KeyStroke)getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY), getTabellenItemLoeschAction().getValue(Action.NAME));
        anlagenChronoTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME), getTabellenItemLoeschAction());

        // Daten (Lageranlagen)
        mengeFeld = new DoubleField(0);

        // Daten (Rohrleitungen)
        ausfuehrungBox = new JComboBox(DatabaseQuery.getAusfuehrungen());
        ausfuehrungBox.setEditable(false);
        // TODO: Ausführung aus anderer Tabelle / Vorgaben? Welche noch?

        // Daten (Abfüllflächen)
        groesseField = new IntegerField();
        eohCheck = new JCheckBox("Einfach o. Herkömmlich");
        efCheck = new JCheckBox("Eignungsfeststellung");
        svbCheck = new JCheckBox("Sachverständigenbescheinigung");
        svbCheck.setEnabled(false);
        zustandGroup = new ButtonGroup();
        saniertCheck = new JRadioButton("Saniert");
        neuErstelltCheck = new JRadioButton("Neu Erstellt");
        unbktCheck = new JRadioButton("Unbekannt");
        zustandGroup.add(saniertCheck);
        zustandGroup.add(neuErstelltCheck);
        zustandGroup.add(unbktCheck);

        // Schutzvorkehrungen (Lageranlagen)
        doppelWandigCheck = new JCheckBox("Doppelwandig");
        leckAnzeigeCheck = new JCheckBox("Leckanzeige");
        auffangRaumCheck = new JCheckBox("Auffangraum");
        leckSchutzAuskleidungCheck = new JCheckBox("Leckschutzauskleidung");
        grenzWertGeberCheck = new JCheckBox("Grenzwertgeber");
        kellerLagerungCheck = new JCheckBox("Kellerlagerung");
        innenBeschichtungCheck = new JCheckBox("Innenbeschichtung");
        schutzSensorCheck = new JCheckBox("Sensor");
        schutzFolieCheck = new JCheckBox("Folie");
        schutzAntiheberCheck = new JCheckBox("Antihebersicherung");
        beschreibungAFeld = new LimitedTextArea(255);
        beschreibungSFeld = new TextArea();
        
        //JGS 
        lagerflaecheFeld = new IntegerField();
        abstandGewFeld = new IntegerField();
        gewNameFeld = new LimitedTextField(25);
        abstandBrunnenFeld = new IntegerField();
        tierhaltungFeld = new LimitedTextField(25);
        seitenwandCheck = new JCheckBox("Seitenwand");
        wandhoeheFeld = new IntegerField();
        bodenplatteFeld = new LimitedTextField(25);
        ueberdachungCheck = new JCheckBox("Überdachung");
        auffangbehBox = new JComboBox(DatabaseQuery.getBehaelterarten());
        auffangbehBox.setEditable(false);
        volumenAuffangbehFeld = new DoubleField(0);
        rohrleitungBox = new JComboBox(DatabaseQuery.getBehaelterarten());
        rohrleitungBox.setEditable(false);
        dichtheitChooser = new TextFieldDateChooser();
        drainageCheck = new JCheckBox("Kontrolldrainage");
        fuellanzeigerCheck = new JCheckBox("Füllstandsanzeiger");
        schieberCheck = new JCheckBox("Schieber/Ventile zugänglich");
        abdeckungCheck = new JCheckBox("Abdeckung vorhanden");
        leitung_geprueftCheck = new JCheckBox("Leitungen geprüft");

        // Leitungen (Lageranlagen)
        oberIrdischCheck = new JCheckBox("Oberirdisch");
        unterIrdischCheck = new JCheckBox("Unterirdisch");
        saugLeitungCheck = new JCheckBox("Als Saugleitung");
        rohrKathSchCheck = new JCheckBox("Rohr-Kathodenschutz");
        ausKupferCheck = new JCheckBox("Aus Kupfer");
        ausStahlCheck = new JCheckBox("Aus Stahl");
        schutzrohrCheck = new JCheckBox("Mit Schutzrohr");
        ausHdpeCheck = new JCheckBox("Aus HDPE");
        druckleitungCheck = new JCheckBox("Druckleitung");
        beschreibungRFeld = new LimitedTextArea(255);

        // Ausführung (Abfüllflächen)
        bodenflaechenAusfBox = new JComboBox(
            DatabaseQuery.getBodenflaechenausf());
        bodenflaechenAusfBox.setEditable(true);
        dickeFeld = new DoubleField(0);
        gueteFeld = new LimitedTextField(50);
        fugenMaterialFeld = new LimitedTextField(50);
        niederschlagSchutzBox = new JComboBox(
            DatabaseQuery.getNiederschlagschutz());
        niederschlagSchutzBox.setEditable(false);
        abscheiderVorhandenCheck = new JCheckBox("Abscheider vorhanden?");
        beschrBodenflaecheArea = new LimitedTextArea(255);
        beschrFugenmaterialArea = new LimitedTextArea(255);
        beschrAblNiederschlArea = new LimitedTextArea(255);

        // Daten (VAwS-Abscheider)
//        fluessigkField = new LimitedTextField(25);
        kompKCheck = new JCheckBox("K (Kl.I)");
        kompLFCheck = new JCheckBox("LF (Kl.II)");
        kompPSCheck = new JCheckBox("PS");
        kompSFCheck = new JCheckBox("SF");
        kompaktCheck = new JCheckBox("Kompaktanlage?");

        // Schutzvorkehrungen (VAwS-Abscheider)
        ueberCheck = new JCheckBox("Überhöhung ausreichend?");
        waschCheck = new JCheckBox("Warnanlage vorhanden?");
        abgabeCheck = new JCheckBox("Abgabeeinrichtung?");
        hochCheck = new JCheckBox("Hochleistungszapfanlage?");
        belueftCheck = new JCheckBox("Befüllung von Lagerbehältern?");
        rueckCheck = new JCheckBox("Rückhaltevermögen ausreichend?");

        // Ausführung (VAwS-Abscheider)
        schlammBeschField = new LimitedTextField(25);
        schlammHerstField = new LimitedTextField(25);
        schlammMatField = new LimitedTextField(25);
        schlammSFVField = new DoubleField(0);
        schlammTypField = new LimitedTextField(25);
        abscheiderBeschField = new LimitedTextField(25);
        abscheiderHerstField = new LimitedTextField(25);
        abscheiderMatField = new LimitedTextField(25);
        abscheiderNSField = new DoubleField(0);
        abscheiderPruefField = new LimitedTextField(25);
        abscheiderTypField = new LimitedTextField(25);
        abscheideroelField = new DoubleField(0);
        zulDNField = new DoubleField(0);
        zulLField = new DoubleField(0);
        zulMatField = new LimitedTextField(25);
        verbDNField = new DoubleField(0);
        verbLField = new DoubleField(0);
        verbMatField = new LimitedTextField(25);
        sonsDNField = new DoubleField(0);
        sonsLField = new DoubleField(0);
        sonsMatField = new LimitedTextField(25);


        // Sachverständigenprüfung
        svPruefungModel = new VawsKontrollenModel();
        svPruefungTabelle = new JTable(svPruefungModel);
        svPruefungTabelle.setGridColor(new Color(230, 230, 230));
        svPruefungTabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
        svPruefungTabelle.getColumnModel().getColumn(1).setPreferredWidth(50);
        svPruefungTabelle.getColumnModel().getColumn(2).setPreferredWidth(200);
        svPruefungTabelle.getColumnModel().getColumn(3).setPreferredWidth(50);
        svPruefungTabelle.getColumnModel().getColumn(4).setPreferredWidth(50);

        // Für die ComboBox bei "Prüfer"
        prueferBox = new JComboBox(DatabaseQuery.getPruefer());
        prueferBox.setEditable(false);
        prueferBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                prueferBox.showPopup();
            }
        });
        prueferBox.setBorder(BorderFactory.createEmptyBorder());
        svPruefungTabelle.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(prueferBox));

        // Für die ComboBox bei "Prüfergebnis"
        pruefergebnisBox = new JComboBox(
            DatabaseQuery.getPruefergebniss());
        pruefergebnisBox.setEditable(false);
        pruefergebnisBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                pruefergebnisBox.showPopup();
            }
        });
        pruefergebnisBox.setBorder(BorderFactory.createEmptyBorder());
        svPruefungTabelle.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(pruefergebnisBox));


        svPruefungTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showTabellenPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showTabellenPopup(e);
            }
        });

        svPruefungTabelle.getInputMap().put((KeyStroke)getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY), getTabellenItemLoeschAction().getValue(Action.NAME));
        svPruefungTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME), getTabellenItemLoeschAction());







        // Verwaltungsverfahren
        verwVerfahrenModel = new VerwVerfahrenModel();
        verwVerfahrenTabelle = new JTable(verwVerfahrenModel);
        verwVerfahrenTabelle.setGridColor(new Color(230, 230, 230));
        verwVerfahrenTabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
        verwVerfahrenTabelle.getColumnModel().getColumn(1).setPreferredWidth(200);
        verwVerfahrenTabelle.getColumnModel().getColumn(2).setPreferredWidth(50);
        verwVerfahrenTabelle.getColumnModel().getColumn(3).setPreferredWidth(50);

        // Für die ComboBox bei "Maßnahme"
        massnahmenBox = new JComboBox(
            DatabaseQuery.getVerwaltungsMassnahmen());
        massnahmenBox.setEditable(true);
        massnahmenBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                massnahmenBox.showPopup();
            }
        });
        massnahmenBox.setBorder(BorderFactory.createEmptyBorder());
        verwVerfahrenTabelle.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(massnahmenBox));

        verwVerfahrenTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showTabellenPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showTabellenPopup(e);
            }
        });

        verwVerfahrenTabelle.getInputMap().put((KeyStroke)getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY), getTabellenItemLoeschAction().getValue(Action.NAME));
        verwVerfahrenTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME), getTabellenItemLoeschAction());

        // Verwaltungsgebuehren
        verwGebuehrenModel = new VerwGebuehrenModel();
        verwGebuehrenTabelle = new JTable(verwGebuehrenModel);
        verwGebuehrenTabelle.setGridColor(new Color(230, 230, 230));
        verwGebuehrenTabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
        verwGebuehrenTabelle.getColumnModel().getColumn(1).setPreferredWidth(200);
        verwGebuehrenTabelle.getColumnModel().getColumn(2).setPreferredWidth(50);
        verwGebuehrenTabelle.getColumnModel().getColumn(3).setPreferredWidth(50);
        verwGebuehrenTabelle.getColumnModel().getColumn(4).setPreferredWidth(50);

        // Für die ComboBox bei "Gebührenart"
        gebArtenBox = new JComboBox(DatabaseQuery.getGebuehrenarten());
        gebArtenBox.setEditable(false);
        gebArtenBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                gebArtenBox.showPopup();
            }
        });
        gebArtenBox.setBorder(BorderFactory.createEmptyBorder());
        verwGebuehrenTabelle.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(gebArtenBox));

        verwGebuehrenTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showTabellenPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showTabellenPopup(e);
            }
        });

        verwGebuehrenTabelle.getInputMap().put((KeyStroke)getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY), getTabellenItemLoeschAction().getValue(Action.NAME));
        verwGebuehrenTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME), getTabellenItemLoeschAction());

        PanelBuilder topBuilder = new PanelBuilder();
        topBuilder.setWeight(1, 0);
        topBuilder.setEmptyBorder(10);
        topBuilder.setFill(true, false);
        topBuilder.setAnchor(GridBagConstraints.NORTHWEST);
        topBuilder.setInsets(0, 0, 5, 25);

        topBuilder.addComponent(header, true);
        topBuilder.addComponents(true, new JLabel("Bezeichnung / Zurodnung:"),
                new JLabel("Flüssigkeit:"), new JLabel("VbF:"), new JLabel("Gefährdungsstufe:"),
                new JLabel("WGK:"));
        topBuilder.addComponents(true, hnrFeld, fluessigkeitBox, vbfBox, gefStufeBox, wgkBox);

        topPanel = topBuilder.getPanel();

        // Panel bauen:
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tabbedPane, BorderLayout.CENTER);

        return panel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#fillForm()
     */
    @Override
    protected void fillForm() {
        header.setText(getFachdaten().getAnlagenart());
//        subHeader.setText("bei: " + getFachdaten().getBasisObjekt().getBasisBetreiber() + "; " + getFachdaten().getBasisObjekt().getBasisObjektarten().getObjektart());
        hnrFeld.setText(getFachdaten().getHerstellnr());
        fluessigkeitBox.setSelectedItem(getFachdaten().getFluessigkeit());
        vbfBox.setSelectedItem(getFachdaten().getVbfeinstufung());
        gefStufeBox.setSelectedItem(getFachdaten().getGefaehrdungsstufe());
        wgkBox.setSelectedItem(getFachdaten().getWgk());

        baujahrFeld.setValue(getFachdaten().getBaujahr());
        inbetriebnahmeChooser.setDate(getFachdaten().getDatuminbetriebnahme());
        genehmigungChooser.setDate(getFachdaten().getDatumgenehmigung());
        erfassungChooser.setDate(getFachdaten().getDatumerfassung());
        stillegungChooser.setDate(getFachdaten().getStillegungsdatum());
        pruefTurnusFeld.setValue(getFachdaten().getPruefturnus());
        aktenzeichenField.setText(getFachdaten().getAktenzeichen());

        behaelterArtBox.setSelectedItem(getFachdaten().getBehaelterart());
        materialBox.setSelectedItem(getFachdaten().getMaterial());
        bemerkungArea.setText(getFachdaten().getBemerkungen());

        anlagenChronoModel.setFachdaten(getFachdaten());

        if (getFachdaten().getAnlagenart().equals(
            DatabaseConstants.VAWS_ANLAGENART_VAWS_ABSCHEIDER)) {
            Abscheider abs = this.getAbscheider();
            tabbedPane.addTab("Daten", getDatenVAWSAbscheiderTab());
            tabbedPane.addTab("Ausführung", getAusfuehrungVAWSAbscheiderTab());
            tabbedPane.addTab("Schutzvorkehrungen", getSchutzvorkehrungenVAWSAbscheiderTab());

            mengeFeld.setValue(getFachdaten().getMenge());

            // Daten Tab
//            flüssigkField.setText(getAbscheider().getMedium());

            if(getAbscheider().getKompaktanlage()!=null)
                kompaktCheck.setSelected(getAbscheider().getKompaktanlage());
            else
                kompaktCheck.setSelected(false);

            if(getAbscheider().getKkl1()!=null)
                kompKCheck.setSelected(getAbscheider().getKkl1());
            else
                kompKCheck.setSelected(false);

            if(getAbscheider().getLfkl2()!=null)
                kompLFCheck.setSelected(getAbscheider().getLfkl2());
            else
                kompLFCheck.setSelected(false);

            if(getAbscheider().getPs()!=null)
                kompPSCheck.setSelected(getAbscheider().getPs());
            else
                kompPSCheck.setSelected(false);

            if(getAbscheider().getSf()!=null)
                kompSFCheck.setSelected(getAbscheider().getSf());
            else
                kompSFCheck.setSelected(false);

            // Ausführung Tab
            schlammBeschField.setText(getAbscheider().getSfbeschichtung());
            schlammHerstField.setText(getAbscheider().getSfhersteller());
            schlammMatField.setText(getAbscheider().getSfmaterial());
            schlammSFVField.setText(getAbscheider().getSfvolumen());
            schlammTypField.setText(getAbscheider().getSftyp());
            abscheiderBeschField.setText(getAbscheider().getAbbeschichtung());
            abscheiderHerstField.setText(getAbscheider().getAbhersteller());
            abscheiderMatField.setText(getAbscheider().getAbmaterial());
            abscheiderNSField.setText(getAbscheider().getAbnenngr());
            abscheiderPruefField.setText(getAbscheider().getAbpruefz());
            abscheiderTypField.setText(getAbscheider().getAbtyp());
            abscheideroelField.setText(getAbscheider().getOelspeichervol());
            zulDNField.setText(getAbscheider().getZuldn());
            zulLField.setText(getAbscheider().getZullaenge());
            zulMatField.setText(getAbscheider().getZulmaterial());
            verbDNField.setText(getAbscheider().getVerbdn());
            verbLField.setText(getAbscheider().getVerblaenge());
            verbMatField.setText(getAbscheider().getVerbmaterial());
            sonsDNField.setText(getAbscheider().getSondn());
            sonsLField.setText(getAbscheider().getSonlaenge());
            sonsMatField.setText(getAbscheider().getSonmaterial());

            //Schutzvorkehrungen Tab

            if(getAbscheider().getUeberhausr()!=null)
                ueberCheck.setSelected(getAbscheider().getUeberhausr());
            else
                ueberCheck.setSelected(false);

            if(getAbscheider().getWaschanlvorh()!=null)
                waschCheck.setSelected(getAbscheider().getWaschanlvorh());
            else
                waschCheck.setSelected(false);

            if(getAbscheider().getAbgabe()!=null)
                abgabeCheck.setSelected(getAbscheider().getAbgabe());
            else
                abgabeCheck.setSelected(false);

            if(getAbscheider().getHlzapfanl()!=null)
                hochCheck.setSelected(getAbscheider().getHlzapfanl());
            else
                hochCheck.setSelected(false);

            if(getAbscheider().getBelvonlagerbh()!=null)
                belueftCheck.setSelected(getAbscheider().getBelvonlagerbh());
            else
                belueftCheck.setSelected(false);

            if(getAbscheider().getRueckhalteausr()!=null)
                rueckCheck.setSelected(getAbscheider().getRueckhalteausr());
            else
                rueckCheck.setSelected(false);
        }
        else if (DatabaseQuery.isLageranlage(getFachdaten())) {
            tabbedPane.addTab("Daten", getDatenLageranlagenTab());
            tabbedPane.addTab("Schutzvorkehrungen", getSchutzLageranlagenTab());
            tabbedPane.addTab("Leitungen", getLeitungenLageranlagenTab());

            mengeFeld.setValue(getFachdaten().getMenge());

            if(getFachdaten().getDoppelwandig()!=null)
                doppelWandigCheck.setSelected(getFachdaten().getDoppelwandig());
            else
                doppelWandigCheck.setSelected(false);

            if(getFachdaten().getLeckanzeige()!=null)
                leckAnzeigeCheck.setSelected(getFachdaten().getLeckanzeige());
            else
                leckAnzeigeCheck.setSelected(false);

            if(getFachdaten().getAuffangraum()!=null)
                auffangRaumCheck.setSelected(getFachdaten().getAuffangraum());
            else
                auffangRaumCheck.setSelected(false);

            if(getFachdaten().getLeckschutzauskleidung()!=null)
                leckSchutzAuskleidungCheck.setSelected(getFachdaten().getLeckschutzauskleidung());
            else
                leckSchutzAuskleidungCheck.setSelected(false);

            if(getFachdaten().getGrenzwertgeber()!=null)
                grenzWertGeberCheck.setSelected(getFachdaten().getGrenzwertgeber());
            else
                grenzWertGeberCheck.setSelected(false);

            if(getFachdaten().getKellerlagerung()!=null)
                kellerLagerungCheck.setSelected(getFachdaten().getKellerlagerung());
            else
                kellerLagerungCheck.setSelected(false);

            if(getFachdaten().getInnenbeschichtung()!=null)
                innenBeschichtungCheck.setSelected(getFachdaten().getInnenbeschichtung());
            else
                innenBeschichtungCheck.setSelected(false);

            if(getFachdaten().getSchutzSensor()!=null)
                schutzSensorCheck.setSelected(getFachdaten().getSchutzSensor());
            else
                schutzSensorCheck.setSelected(false);

            if(getFachdaten().getSchutzFolie()!=null)
                schutzFolieCheck.setSelected(getFachdaten().getSchutzFolie());
            else
                schutzFolieCheck.setSelected(false);

            if(getFachdaten().getSchutzAntiheber()!=null)
                schutzAntiheberCheck.setSelected(getFachdaten().getSchutzAntiheber());
            else
                schutzAntiheberCheck.setSelected(false);

            beschreibungAFeld.setText(getFachdaten().getBeschreibungA());
            beschreibungSFeld.setText(getFachdaten().getBeschreibungS());

            if(getFachdaten().getOberirdisch()!=null)
                oberIrdischCheck.setSelected(getFachdaten().getOberirdisch());
            else
                oberIrdischCheck.setSelected(false);

            if(getFachdaten().getUnterirdisch()!=null)
                unterIrdischCheck.setSelected(getFachdaten().getUnterirdisch());
            else
                unterIrdischCheck.setSelected(false);

            if(getFachdaten().getSaugleitung()!=null)
                saugLeitungCheck.setSelected(getFachdaten().getSaugleitung());
            else
                saugLeitungCheck.setSelected(false);

            if(getFachdaten().getRohrKathodenschutz()!=null)
                rohrKathSchCheck.setSelected(getFachdaten().getRohrKathodenschutz());
            else
                rohrKathSchCheck.setSelected(false);

            if(getFachdaten().getAusKupfer()!=null)
                ausKupferCheck.setSelected(getFachdaten().getAusKupfer());
            else
                ausKupferCheck.setSelected(false);

            if(getFachdaten().getAusStahl()!=null)
                ausStahlCheck.setSelected(getFachdaten().getAusStahl());
            else
                ausStahlCheck.setSelected(false);

            if(getFachdaten().getMitSchutzrohr()!=null)
                schutzrohrCheck.setSelected(getFachdaten().getMitSchutzrohr());
            else
                schutzrohrCheck.setSelected(false);

            if(getFachdaten().getAusHdpe()!=null)
                ausHdpeCheck.setSelected(getFachdaten().getAusHdpe());
            else
                ausHdpeCheck.setSelected(false);

            if(getFachdaten().getDruckleitung()!=null)
                druckleitungCheck.setSelected(getFachdaten().getDruckleitung());
            else
                druckleitungCheck.setSelected(false);

            beschreibungRFeld.setText(getFachdaten().getBeschreibungR());

        } else if (getFachdaten().getAnlagenart().equals(
            DatabaseConstants.VAWS_ANLAGENART_ROHRLEITUNG)) {
            tabbedPane.addTab("Daten", getDatenRohrleitungenTab());

            ausfuehrungBox.setSelectedItem(getFachdaten().getAusfuehrung());

        } else if (getFachdaten().getAnlagenart().equals(
            DatabaseConstants.VAWS_ANLAGENART_ABFUELLFLAECHE)) {
            Abfuellflaeche flaeche = this.getAbfuellflaeche();
            tabbedPane.addTab("Daten", getDatenAbfuellflaechenTab());
            tabbedPane.addTab("Ausführung", getAusfuehrungAbfuellflaechenTab());

            if(getAbfuellflaeche().getEoh()!=null)
                eohCheck.setSelected(getAbfuellflaeche().getEoh());
            else
                eohCheck.setSelected(false);

            if(getAbfuellflaeche().getEf()!=null)
                efCheck.setSelected(getAbfuellflaeche().getEf());
            else
                efCheck.setSelected(false);

            if(getAbfuellflaeche().getAbfsaniert()!=null)
                saniertCheck.setSelected(getAbfuellflaeche().getAbfsaniert());
            else
                saniertCheck.setSelected(false);

            if(getAbfuellflaeche().getAbfneuerstellt()!=null)
                neuErstelltCheck.setSelected(getAbfuellflaeche().getAbfneuerstellt());
            else
                neuErstelltCheck.setSelected(false);


            bodenflaechenAusfBox.setSelectedItem(getAbfuellflaeche().getBodenflaechenausf());
            dickeFeld.setValue(getAbfuellflaeche().getDicke());
            gueteFeld.setText(getAbfuellflaeche().getGuete());
            groesseField.setValue(getAbfuellflaeche().getGroesse());
            fugenMaterialFeld.setText(getAbfuellflaeche().getFugenmaterial());
            niederschlagSchutzBox.setSelectedItem(getAbfuellflaeche().getNiederschlagschutz());

            if(getFachdaten().getDoppelwandig()!=null)
                abscheiderVorhandenCheck.setSelected(getFachdaten().getDoppelwandig());
            else
                abscheiderVorhandenCheck.setSelected(false);

            beschrBodenflaecheArea.setText(getAbfuellflaeche().getBeschbodenfl());
            beschrFugenmaterialArea.setText(getAbfuellflaeche().getBeschfugenmat());
            beschrAblNiederschlArea.setText(getAbfuellflaeche().getBeschableitung());
        } else if (
            getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_FS) || 
        	getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_FS) || 
            getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_FS) || 
            getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_GK) || 
            getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_GHB))
        {
            tabbedPane.addTab("Daten", getDatenJgsTab());
            tabbedPane.addTab("Ausführung", getAbfuellflaecheJgsTab());

            mengeFeld.setValue(getFachdaten().getMenge());
            lagerflaecheFeld.setValue(getJgs().getLagerflaeche());
            abstandGewFeld.setValue(getJgs().getGewaesserAbstand());
            gewNameFeld.setText(getJgs().getGewaesserName());
            abstandBrunnenFeld.setValue(getJgs().getBrunnenAbstand());
            tierhaltungFeld.setText(getJgs().getTierhaltung());
                        
            if(getJgs().getSeitenwaende()!=null)
                seitenwandCheck.setSelected(getJgs().getSeitenwaende());
            else
                seitenwandCheck.setSelected(false);
            
            wandhoeheFeld.setValue(getJgs().getWandhoehe());
            bodenflaechenAusfBox.setSelectedItem(getJgs().getBodenplatte());

            if (getJgs().getUeberdachung() != null)
                ueberdachungCheck.setSelected(getJgs().getUeberdachung());
            else
                ueberdachungCheck.setSelected(false);    
				ueberdachungCheck.setSelected(false);    
                ueberdachungCheck.setSelected(false);    
            
            auffangbehBox.setSelectedItem(getJgs().getAuffangbeh());
            volumenAuffangbehFeld.setValue(getJgs().getVolumenAuffangbeh());
            rohrleitungBox.setSelectedItem(getJgs().getRohrleitung());
            dichtheitChooser.setDate(getJgs().getDichtheitspruefung());

            if (getJgs().getDrainage() != null)
                drainageCheck.setSelected(getJgs().getDrainage());
            else
                drainageCheck.setSelected(false);  
				drainageCheck.setSelected(false);  
                drainageCheck.setSelected(false);  

            if (getJgs().getFuellanzeiger() != null)
                fuellanzeigerCheck.setSelected(getJgs().getFuellanzeiger());
            else
                fuellanzeigerCheck.setSelected(false);  
				fuellanzeigerCheck.setSelected(false);  
                fuellanzeigerCheck.setSelected(false);  

            if (getJgs().getSchieber() != null)
                schieberCheck.setSelected(getJgs().getSchieber());
            else
                schieberCheck.setSelected(false);  
				schieberCheck.setSelected(false);  
                schieberCheck.setSelected(false);  

            if (getJgs().getAbdeckung() != null)
                abdeckungCheck.setSelected(getJgs().getAbdeckung());
            else
                abdeckungCheck.setSelected(false);  
				abdeckungCheck.setSelected(false);  
                abdeckungCheck.setSelected(false);  

            if (getJgs().getLeitungGeprueft() != null)
                leitung_geprueftCheck.setSelected(getJgs().getLeitungGeprueft());
            else
                leitung_geprueftCheck.setSelected(false);  
				leitung_geprueftCheck.setSelected(false);  
                leitung_geprueftCheck.setSelected(false);  
            
        }
        tabbedPane.addTab("Sachverständigenprüfung", getSvPruefungTab());
        svPruefungModel.setFachdaten(getFachdaten());
        tabbedPane.addTab("Verwaltungsverfahren", getVerwVerfahrenTab());
        verwVerfahrenModel.setFachdaten(getFachdaten());
        tabbedPane.addTab("Verwaltungsgebühren", getVerwGebuehrenTab());
        verwGebuehrenModel.setFachdaten(getFachdaten());

        this.pack();
        this.setLocationRelativeTo(frame);
    }


    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#canSave()
     */
    @Override
    protected boolean canSave() {
        // TODO: Irgendwelche Überprüfungen nötig?
//        String hnr = hnrFeld.getText();
//
//        if (hnr == null || "".equals(hnr.trim())) {
//            hnrLabel.setForeground(Color.RED);
//            hnrFeld.requestFocus();
//            return false;
//        } else {
            return true;
//        }
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#doSave()
     */
    @Override
    protected boolean doSave() {

        if (svPruefungTabelle.getCellEditor() != null) {
            svPruefungTabelle.getCellEditor().stopCellEditing();
        }
        if (verwVerfahrenTabelle.getCellEditor() != null) {
            verwVerfahrenTabelle.getCellEditor().stopCellEditing();
        }
        if (anlagenChronoTabelle.getCellEditor() != null) {
            anlagenChronoTabelle.getCellEditor().stopCellEditing();
        }
        if (verwGebuehrenTabelle.getCellEditor() != null) {
            verwGebuehrenTabelle.getCellEditor().stopCellEditing();
        }

        String tmp = hnrFeld.getText();
        if (tmp != null) {
            tmp = tmp.trim();
        }
        getFachdaten().setHerstellnr(tmp);

        tmp = (String) fluessigkeitBox.getSelectedItem();
        if (tmp != null) {
            tmp = tmp.trim();
        }
        getFachdaten().setFluessigkeit(tmp);
        getFachdaten().setVbfeinstufung((String)vbfBox.getSelectedItem());
        getFachdaten().setGefaehrdungsstufe((String)gefStufeBox.getSelectedItem());
        getFachdaten().setWgk((String)wgkBox.getSelectedItem());
        getFachdaten().setMenge(mengeFeld.getDoubleValue());

        getFachdaten().setBaujahr(baujahrFeld.getIntValue());
        getFachdaten().setDatuminbetriebnahme(inbetriebnahmeChooser.getDate());
        getFachdaten().setDatumgenehmigung(genehmigungChooser.getDate());
        getFachdaten().setDatumerfassung(erfassungChooser.getDate());
        getFachdaten().setStillegungsdatum(stillegungChooser.getDate());
        getFachdaten().setPruefturnus(pruefTurnusFeld.getDoubleValue());
        getFachdaten().setAktenzeichen(aktenzeichenField.getText());

        getFachdaten().setBehaelterart(
            (String)behaelterArtBox.getSelectedItem());
        getFachdaten().setMaterial((String)materialBox.getSelectedItem());
        getFachdaten().setBemerkungen(bemerkungArea.getText());

        boolean success = true;

        if (DatabaseQuery.isLageranlage(getFachdaten())) {

            getFachdaten().setDoppelwandig(doppelWandigCheck.isSelected());
            getFachdaten().setLeckanzeige(leckAnzeigeCheck.isSelected());
            getFachdaten().setAuffangraum(auffangRaumCheck.isSelected());
            getFachdaten().setLeckschutzauskleidung(leckSchutzAuskleidungCheck.isSelected());
            getFachdaten().setGrenzwertgeber(grenzWertGeberCheck.isSelected());
            getFachdaten().setKellerlagerung(kellerLagerungCheck.isSelected());
            getFachdaten().setInnenbeschichtung(innenBeschichtungCheck.isSelected());
            getFachdaten().setSchutzSensor(schutzSensorCheck.isSelected());
            getFachdaten().setSchutzFolie(schutzFolieCheck.isSelected());
            getFachdaten().setSchutzAntiheber(schutzAntiheberCheck.isSelected());
            getFachdaten().setBeschreibungA(beschreibungAFeld.getText());
            getFachdaten().setBeschreibungS(beschreibungSFeld.getText());

            getFachdaten().setOberirdisch(oberIrdischCheck.isSelected());
            getFachdaten().setUnterirdisch(unterIrdischCheck.isSelected());
            getFachdaten().setSaugleitung(saugLeitungCheck.isSelected());
            getFachdaten().setRohrKathodenschutz(rohrKathSchCheck.isSelected());
            getFachdaten().setAusKupfer(ausKupferCheck.isSelected());
            getFachdaten().setAusStahl(ausStahlCheck.isSelected());
            getFachdaten().setMitSchutzrohr(schutzrohrCheck.isSelected());
            getFachdaten().setAusHdpe(ausHdpeCheck.isSelected());
            getFachdaten().setDruckleitung(druckleitungCheck.isSelected());
            getFachdaten().setBeschreibungR(beschreibungRFeld.getText());

        } else if (getFachdaten().getAnlagenart().equals(
            DatabaseConstants.VAWS_ANLAGENART_ROHRLEITUNG)) {
            getFachdaten().setAusfuehrung((String)ausfuehrungBox.getSelectedItem());
        }

        success = success && getFachdaten().merge();
        setEditedObject(Fachdaten.findById(getFachdaten().getBehaelterid()));

        // Für Abfüllflächen (wg. dem VawsAbfuellflaechen-Objekt)
        if (getFachdaten().getAnlagenart().equals(
            DatabaseConstants.VAWS_ANLAGENART_ABFUELLFLAECHE)) {
            getAbfuellflaeche().setEoh(eohCheck.isSelected());
            getAbfuellflaeche().setEf(efCheck.isSelected());
            if (unbktCheck.isSelected()) {
                getAbfuellflaeche().setAbfneuerstellt(null);
                getAbfuellflaeche().setAbfsaniert(null);
            } else {
                getAbfuellflaeche().setAbfneuerstellt(neuErstelltCheck.isSelected());
                getAbfuellflaeche().setAbfsaniert(saniertCheck.isSelected());
            }

            getAbfuellflaeche().setBodenflaechenausf((String)bodenflaechenAusfBox.getSelectedItem());
            getAbfuellflaeche().setDicke(dickeFeld.getFloatValue());
            getAbfuellflaeche().setGuete(gueteFeld.getText());
            getAbfuellflaeche().setGroesse(groesseField.getIntValue());
            getAbfuellflaeche().setFugenmaterial(fugenMaterialFeld.getText());
            getAbfuellflaeche().setNiederschlagschutz((String)niederschlagSchutzBox.getSelectedItem());
            getAbfuellflaeche().setAbscheidervorh(abscheiderVorhandenCheck.isSelected());
            getAbfuellflaeche().setBeschbodenfl(beschrBodenflaecheArea.getText());
            getAbfuellflaeche().setBeschfugenmat(beschrFugenmaterialArea.getText());
            getAbfuellflaeche().setBeschableitung(beschrAblNiederschlArea.getText());

            success = success && getAbfuellflaeche().merge();
        }
        if (getFachdaten().getAnlagenart().equals(
            DatabaseConstants.VAWS_ANLAGENART_VAWS_ABSCHEIDER)) {
            getAbscheider().setAbgabe(abgabeCheck.isSelected());
            getAbscheider().setBelvonlagerbh(belueftCheck.isSelected());
            getAbscheider().setHlzapfanl(hochCheck.isSelected());
            getAbscheider().setKompaktanlage(kompaktCheck.isSelected());
            getAbscheider().setKkl1(kompKCheck.isSelected());
            getAbscheider().setLfkl2(kompLFCheck.isSelected());
            getAbscheider().setPs(kompPSCheck.isSelected());
            getAbscheider().setSf(kompSFCheck.isSelected());
            getAbscheider().setRueckhalteausr(rueckCheck.isSelected());
            getAbscheider().setWaschanlvorh(waschCheck.isSelected());
            getAbscheider().setUeberhausr(ueberCheck.isSelected());
//            getAbscheider().setMedium(flüssigkField.getText());
            getAbscheider().setAbbeschichtung(abscheiderBeschField.getText());
            getAbscheider().setAbhersteller(abscheiderHerstField.getText());
            getAbscheider().setAbmaterial(abscheiderMatField.getText());
            getAbscheider().setAbnenngr(abscheiderNSField.getText());
            getAbscheider().setAbpruefz(abscheiderPruefField.getText());
            getAbscheider().setAbtyp(abscheiderTypField.getText());
            getAbscheider().setOelspeichervol(abscheideroelField.getText());
            getAbscheider().setSfbeschichtung(schlammBeschField.getText());
            getAbscheider().setSfhersteller(schlammHerstField.getText());
            getAbscheider().setSfmaterial(schlammMatField.getText());
            getAbscheider().setSftyp(schlammTypField.getText());
            getAbscheider().setSfvolumen(schlammSFVField.getText());
            getAbscheider().setZuldn(zulDNField.getText());
            getAbscheider().setZullaenge(zulLField.getText());
            getAbscheider().setZulmaterial(zulMatField.getText());
            getAbscheider().setVerbdn(verbDNField.getText());
            getAbscheider().setVerblaenge(verbLField.getText());
            getAbscheider().setVerbmaterial(verbMatField.getText());
            getAbscheider().setSondn(sonsDNField.getText());
            getAbscheider().setSonlaenge(sonsLField.getText());
            getAbscheider().setSonmaterial(sonsMatField.getText());

            success = success && getAbscheider().merge();

        }
        if (getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_FS) ||
            getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_GK) || 
            getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_GHB)) {
         
            getJgs().setBodenplatte((String)bodenflaechenAusfBox.getSelectedItem());
            getJgs().setBrunnenAbstand(abstandBrunnenFeld.getIntValue());
            getJgs().setGewaesserAbstand(abstandGewFeld.getIntValue());
            getJgs().setGewaesserName(gewNameFeld.getText());
            getJgs().setLagerflaeche(lagerflaecheFeld.getIntValue());   
        	getJgs().setLagerflaeche(lagerflaecheFeld.getIntValue());   
            getJgs().setLagerflaeche(lagerflaecheFeld.getIntValue());   
            getJgs().setSeitenwaende(seitenwandCheck.isSelected());
            getJgs().setTierhaltung(tierhaltungFeld.getText());
            getJgs().setWandhoehe(wandhoeheFeld.getIntValue());
            getJgs().setAuffangbeh((String)auffangbehBox.getSelectedItem());
            getJgs().setVolumenAuffangbeh(volumenAuffangbehFeld.getDoubleValue());
            getJgs().setRohrleitung((String)rohrleitungBox.getSelectedItem());
            getJgs().setDichtheitspruefung(dichtheitChooser.getDate());
            getJgs().setDrainage(drainageCheck.isSelected());
            getJgs().setFuellanzeiger(fuellanzeigerCheck.isSelected());
            getJgs().setSchieber(schieberCheck.isSelected());
            getJgs().setAbdeckung(abdeckungCheck.isSelected());
            getJgs().setLeitungGeprueft(leitung_geprueftCheck.isSelected());
            
            

            success = success && getJgs().merge();
        }
        // Anlagenchronologie speichern:
        for (Iterator<?> it = anlagenChronoModel.getList().iterator(); it.hasNext();) {
            success = success && ((Anlagenchrono) it.next()).merge();
        }
        for (Iterator<Anlagenchrono> it = anlagenChronoModel.getGeloeschte().iterator(); it.hasNext();) {
            success = success && it.next().delete();
        }
        log.debug(anlagenChronoModel.getList().size()
                + " AnlagenChrono-Einträge neu/behalten, "
                + anlagenChronoModel.getGeloeschte().size()
                + " Einträge gelöscht.");

        // Sachverständigenprüfung speichern:
        for (Iterator<?> it = svPruefungModel.getList().iterator(); it.hasNext();) {
            success = success && ((Kontrollen) it.next()).merge();
        }
        for (Iterator<Kontrollen> it = svPruefungModel.getGeloeschte().iterator(); it.hasNext();) {
            success = success && it.next().delete();
        }
        log.debug(svPruefungModel.getList().size()
                + " Sachverständigenprüfungs-Einträge neu/behalten, "
                + svPruefungModel.getGeloeschte().size()
                + " Einträge gelöscht.");

        // Verwaltungsverfahren speichern:
        for (Iterator<?> it = verwVerfahrenModel.getList().iterator(); it.hasNext();) {
            success = success && ((Verwaltungsverf) it.next()).merge();
        }
        for (Iterator<Verwaltungsverf> it = verwVerfahrenModel.getGeloeschte().iterator(); it.hasNext();) {
            success = success && it.next().delete();
        }
        log.debug(verwVerfahrenModel.getList().size()
                + " Verwaltungsverfahren-Einträge neu/behalten, "
                + verwVerfahrenModel.getGeloeschte().size()
                + " Einträge gelöscht.");

        // Verwaltunggebühren speichern:
        for (Iterator<?> it = verwGebuehrenModel.getList().iterator(); it.hasNext();) {
            success = success && ((Verwaltungsgebuehren) it.next()).merge();
        }
        for (Iterator<Verwaltungsgebuehren> it = verwGebuehrenModel.getGeloeschte().iterator(); it.hasNext();) {
            success = success && it.next().delete();
        }
        log.debug(verwGebuehrenModel.getList().size()
                + " Verwaltungsgebühren-Einträge neu/behalten, "
                + verwGebuehrenModel.getGeloeschte().size()
                + " Einträge gelöscht.");

        return success;
    }

    // Kontextmenü für die Tabellen:

    /**
     * Zeigt ein Kontextmenü an, wenn ein entsprechendes
     * MouseEvent vorliegt.
     * @param e Das MouseEvent.
     */
    private void showTabellenPopup(MouseEvent e) {
        if (tabellenPopup == null) {
            tabellenPopup = new JPopupMenu();
            JMenuItem loeschItem = new JMenuItem(getTabellenItemLoeschAction());

            tabellenPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            JTable table = (JTable) e.getSource();
            int row = table.rowAtPoint(origin);

            if (row != -1) {
                table.setRowSelectionInterval(row, row);
                tabellenPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private Action getTabellenItemLoeschAction() {
        if (tabellenItemLoeschAction == null) {
            tabellenItemLoeschAction = new AbstractAction("Eintrag löschen") {
                private static final long serialVersionUID = 2465229964078090463L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = tabbedPane.getSelectedIndex();
                    log.debug("index: " + index);
                    if (index == 0) {
                        removeRowFromTable(anlagenChronoTabelle, anlagenChronoModel);
                    } else if (index == (tabbedPane.getTabCount()-3)) {
                        removeRowFromTable(svPruefungTabelle, svPruefungModel);
                    } else if (index == (tabbedPane.getTabCount()-2)) {
                        removeRowFromTable(verwVerfahrenTabelle, verwVerfahrenModel);
                    } else if (index == (tabbedPane.getTabCount()-1)) {
                        removeRowFromTable(verwGebuehrenTabelle, verwGebuehrenModel);
                    }
                }
            };
            tabellenItemLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
            tabellenItemLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }
        return tabellenItemLoeschAction;
    }

    private void removeRowFromTable(JTable table, EditableListTableModel model) {
        int row = table.getSelectedRow();

        // Natürlich nur, wenn wirklich eine Zeile ausgewählt ist
        if (row != -1) {
            model.removeRow(row);
        }
    }

    // Tabs:

    private JPanel getDatenVAWSAbscheiderTab() {
        if (datenVAWSAbscheiderTab == null) {

            JScrollPane bemerkungScroller = new JScrollPane(
                    bemerkungArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            this.tabellenSplit = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, bemerkungScroller,
                    chronoScroller);
            this.tabellenSplit.setDividerLocation(0.3);

            PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);

            //Stammdaten
            PanelBuilder stammdaten = new PanelBuilder(builder);
            stammdaten.addComponent(baujahrFeld, "Baujahr:");
            stammdaten.addComponent(inbetriebnahmeChooser, "Inbetriebnahme:", true);
            stammdaten.addComponent(erfassungChooser, "Erfassung:");
            stammdaten.addComponent(pruefTurnusFeld, "Prüfturnus [Jahre]", true);
            stammdaten.addComponent(stillegungChooser, "Stillegung:", true);
            
            PanelBuilder komponenten = new PanelBuilder(builder);
            komponenten.addComponent(kompaktCheck, true);
            komponenten.addComponents(true, kompSFCheck, kompPSCheck);
            komponenten.addComponents(true, kompKCheck, kompLFCheck);

            builder.addSeparator("Stammdaten:", true);
            builder.addComponent(stammdaten.getPanel(), true);
            builder.addSeparator("Komponenten:", true);
            builder.addComponent(komponenten.getPanel(), true);
            builder.addSeparator("Bemerkungen und Anlagenchronologie", true);
            builder.setWeight(1, 1);
            builder.setFill(true, true);
            builder.addComponent(this.tabellenSplit, true);

            datenVAWSAbscheiderTab = builder.getPanel();
        }
        return datenVAWSAbscheiderTab;
    }

    private JPanel getAusfuehrungVAWSAbscheiderTab()
    {
        if (ausfuehrungVAWSAbscheiderTab == null) {
            PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);

            PanelBuilder schlammfang = new PanelBuilder(builder);
            schlammfang.addComponent(schlammHerstField, "Hersteller:");
            schlammfang.addComponent(schlammTypField, "Typ:");
            schlammfang.addComponent(schlammSFVField, "SF-Volumen [Liter]:", true);
            schlammfang.addComponent(schlammMatField, "Material:");
            schlammfang.addComponent(schlammBeschField, "Beschichtung:", true, true);

            PanelBuilder absch = new PanelBuilder(builder);
            absch.addComponent(abscheiderHerstField, "Hersteller");
            absch.addComponent(abscheiderTypField, "Typ:");
            absch.addComponent(abscheiderPruefField, "Prüfzeichen:", true);
            absch.addComponent(abscheiderMatField, "Material:");
            absch.addComponent(abscheiderBeschField, "Beschichtung:");
            absch.addComponent(abscheiderNSField, "Nenngröße (NS):", true);
            absch.addComponent(abscheideroelField, "Ölspeichervolumen [Liter]:", true, true);

            PanelBuilder zul = new PanelBuilder(builder);
            zul.addComponent(zulDNField, "DN:");
            zul.addComponent(zulMatField, "Material:");
            zul.addComponent(zulLField, "Länge [m]:");

            PanelBuilder vbl = new PanelBuilder(builder);
            vbl.addComponent(verbDNField, "DN:");
            vbl.addComponent(verbMatField, "Material:");
            vbl.addComponent(verbLField, "Länge [m]:");

            PanelBuilder sonst = new PanelBuilder(builder);
            sonst.addComponent(sonsDNField, "DN:");
            sonst.addComponent(sonsMatField, "Material:");
            sonst.addComponent(sonsLField, "Länge [m]:");

            builder.addSeparator("Schlammfang", true);
            builder.addComponent(schlammfang.getPanel(), true);
            builder.addSeparator("Abscheider", true);
            builder.addComponent(absch.getPanel(), true);
            builder.addSeparator("Rohrleitungen: Zuleitungen", true);
            builder.addComponent(zul.getPanel(), true);
            builder.addSeparator("Rohrleitungen: Verbindungsleitungen", true);
            builder.addComponent(vbl.getPanel(), true);
            builder.addSeparator("Rohrleitungen: Sonstige", true);
            builder.addComponent(sonst.getPanel(), true);
            builder.fillColumn();

            ausfuehrungVAWSAbscheiderTab = builder.getPanel();
        }
        return ausfuehrungVAWSAbscheiderTab;
    }

    private JPanel getSchutzvorkehrungenVAWSAbscheiderTab()
    {
        if (schutzvorkehrungenVAWSAbscheiderTab == null) {
            PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);
            PanelBuilder stammdaten = new PanelBuilder(builder);
            stammdaten.addComponent(ueberCheck, true);
            stammdaten.addComponent(waschCheck, true);

            PanelBuilder rueckhalte = new PanelBuilder(builder);
            rueckhalte.addComponent(abgabeCheck, true);
            rueckhalte.addComponent(hochCheck, true);
            rueckhalte.addComponent(belueftCheck, true);

            builder.addSeparator("Stammdaten", true);
            builder.addComponent(stammdaten.getPanel(), true);
            builder.addSeparator("Rückhaltevermögen", true);
            builder.addComponent(rueckhalte.getPanel(), true);
            builder.fillColumn();
            schutzvorkehrungenVAWSAbscheiderTab = builder.getPanel();
        }
        return schutzvorkehrungenVAWSAbscheiderTab;
    }

    private JPanel getDatenLageranlagenTab() {
        if (datenLageranlagenTab == null) {

            JScrollPane bemerkungScroller = new JScrollPane(
                    bemerkungArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            this.tabellenSplit = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, bemerkungScroller,
                    chronoScroller);
            this.tabellenSplit.setResizeWeight(0.3);

            PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);
            PanelBuilder stammdaten = new PanelBuilder(builder);
            stammdaten.addComponent(baujahrFeld, "Baujahr:");
            stammdaten.addComponent(mengeFeld, "Menge [m³]:", true);
            stammdaten.addComponent(inbetriebnahmeChooser, "Inbetriebnahme:");
            stammdaten.addComponent(pruefTurnusFeld, "Prüfturnus[Jahre]:", true);
            stammdaten.addComponent(genehmigungChooser, "Genehmigung:");
            stammdaten.addComponent(behaelterArtBox, "Behälterart:", true);
            stammdaten.addComponent(aktenzeichenField, "Aktenzeichen:");
            stammdaten.addComponent(materialBox, "Material:", true);
            stammdaten.addComponent(erfassungChooser, "Erfassung:");
            stammdaten.addComponent(stillegungChooser, "Stillegung:", true);

            builder.addSeparator("Stammdaten", true);
            builder.addComponent(stammdaten.getPanel(), true);
            builder.addSeparator("Bemerkungen und Anlagenchronologie", true);
            builder.setFill(true, true);
            builder.setGridHeight(6);
            builder.setWeightY(0.5);
            builder.addComponent(this.tabellenSplit, true);
            datenLageranlagenTab = builder.getPanel();
        }
        return datenLageranlagenTab;
    }

    private JPanel getSchutzLageranlagenTab() {
        if (schutzLageranlagenTab == null) {
            PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);
            builder.addComponents(true, doppelWandigCheck, leckAnzeigeCheck, leckSchutzAuskleidungCheck, schutzSensorCheck);
            builder.addComponents(true, auffangRaumCheck, kellerLagerungCheck, innenBeschichtungCheck, schutzFolieCheck);
            builder.addComponents(true, grenzWertGeberCheck, schutzAntiheberCheck);
            builder.fillRow(true);
            builder.addSeparator("Beschreibung: Schutzvorkehrungen", true);
            builder.setFill(true, true);
            builder.setWeightY(0.20);
            builder.addComponent(new JScrollPane(beschreibungSFeld), true);
            builder.setWeightY(0);
            builder.addSeparator("Beschreibung: Auffangraum", true);
            builder.setFill(true, true);
            builder.setWeightY(0.25);
            builder.addComponent(new JScrollPane(beschreibungAFeld), true);
            schutzLageranlagenTab = builder.getPanel();
        }
        return schutzLageranlagenTab;
    }

    private JPanel getLeitungenLageranlagenTab() {
        if (leitungenLageranlagenTab == null) {
            PanelBuilder builder = new PanelBuilder(GridBagConstraints.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);
            builder.addComponents(true, oberIrdischCheck, ausKupferCheck, saugLeitungCheck);
            builder.addComponents(true, unterIrdischCheck, ausStahlCheck, rohrKathSchCheck);
            builder.addComponents(true, schutzrohrCheck, ausHdpeCheck, druckleitungCheck);
            builder.addSeparator("Beschreibung: Rohrleitung", true);
            builder.setFill(true, true);
            builder.setWeightY(0.5);
            builder.addComponent(new JScrollPane(beschreibungRFeld), true);
            leitungenLageranlagenTab = builder.getPanel();
        }
        return leitungenLageranlagenTab;
    }

    private JPanel getDatenRohrleitungenTab() {
        if (datenRohrleitungenTab == null) {

            JScrollPane bemerkungScroller = new JScrollPane(
                    bemerkungArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            this.tabellenSplit = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, bemerkungScroller,
                    chronoScroller);
            this.tabellenSplit.setResizeWeight(0.3);

            PanelBuilder builder = new PanelBuilder(GridBagConstraints.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);

            PanelBuilder stammdaten = new PanelBuilder(builder);
            stammdaten.addComponent(baujahrFeld, "Baujahr:");
            stammdaten.addComponent(ausfuehrungBox, "Ausführung:", true);
            stammdaten.addComponent(inbetriebnahmeChooser, "Inbetriebnahme:");
            stammdaten.addComponent(pruefTurnusFeld, "Prüfturnus[Jahre]", true);
            stammdaten.addComponent(genehmigungChooser, "Genehmigung:");
            stammdaten.addComponent(behaelterArtBox, "Bauart:", true);
            stammdaten.addComponent(aktenzeichenField, "Aktenzeichen:");
            stammdaten.addComponent(materialBox, "Material:", true);
            stammdaten.addComponent(erfassungChooser, "Erfassung:");
            stammdaten.addComponent(stillegungChooser, "Stillegung:", true);

            builder.addSeparator("Stammdaten", true);
            builder.addComponent(stammdaten.getPanel(), true);
            builder.addSeparator("Bemerkungen und Anlagenchronologie", true);
            builder.setFill(true, true);
            builder.setWeightY(0.5);
            builder.addComponent(this.tabellenSplit, true);

            datenRohrleitungenTab = builder.getPanel();
        }
        return datenRohrleitungenTab;
    }

    private JPanel getDatenAbfuellflaechenTab() {
        if (datenAbfuellflaechenTab == null) {

            JScrollPane bemerkungScroller = new JScrollPane(
                    bemerkungArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            this.tabellenSplit = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, bemerkungScroller,
                    chronoScroller);
            this.tabellenSplit.setResizeWeight(0.35);

            PanelBuilder builder = new PanelBuilder(GridBagConstraints.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);

            PanelBuilder stammdaten = new PanelBuilder(builder);
            stammdaten.addComponent(baujahrFeld, "Baujahr:");
            stammdaten.addComponent(pruefTurnusFeld, "Prüfturnus[Jahre]:", true);
            stammdaten.addComponent(inbetriebnahmeChooser, "Inbetriebnahme:");
            stammdaten.addComponent(erfassungChooser, "Erfassung:", true);
            stammdaten.addComponent(genehmigungChooser, "Genehmigung:");
            stammdaten.addComponent(stillegungChooser, "Stillegung:", true);
            stammdaten.addComponent(aktenzeichenField, "Aktenzeichen:");
            stammdaten.addComponent(groesseField, "Größe[m²]:", true);

            PanelBuilder abfl = new PanelBuilder(builder);
            abfl.addComponents(true, neuErstelltCheck, eohCheck);
            abfl.addComponents(true, saniertCheck, efCheck);
            abfl.addComponents(true, unbktCheck, svbCheck);

            builder.addSeparator("Stammdaten", true);
            builder.addComponent(stammdaten.getPanel(), true);
            builder.addSeparator("Abfüllfläche", true);
            builder.addComponent(abfl.getPanel(), true);
            builder.addSeparator("Bemerkungen und Anlagenchronologie", true);
            builder.setFill(true, true);
            builder.setWeightY(1);
            builder.addComponent(this.tabellenSplit, true);
            datenAbfuellflaechenTab = builder.getPanel();
        }
        return datenAbfuellflaechenTab;
    }

    private JPanel getAbfuellflaecheJgsTab() {
        if (datenAbfuellflaechenJgsTab == null) {

            
            JScrollPane bemerkungScroller = new JScrollPane(
                    bemerkungArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            PanelBuilder builder = new PanelBuilder(GridBagConstraints.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);

            PanelBuilder daten = new PanelBuilder(builder);
            daten.addComponent(bodenflaechenAusfBox, "Bodenplatte", true, true);
            daten.addComponents(true, seitenwandCheck, drainageCheck);
            daten.addComponents(true, fuellanzeigerCheck, schieberCheck);
            daten.addComponents(true, abdeckungCheck, leitung_geprueftCheck);

            PanelBuilder auffang = new PanelBuilder(builder);
            auffang.addComponent(auffangbehBox, "Auffangbehälter");
            auffang.addComponent(volumenAuffangbehFeld, "Behältervolumen[m³]", true);
            auffang.addComponent(rohrleitungBox, "Rohrleitung");
            auffang.addComponent(wandhoeheFeld, "Wandhöhe[m]", true);

            builder.addSeparator("Ausführung", true);
            builder.addComponent(daten.getPanel(), true);
            builder.addSeparator("Auffangvorrichtung", true);
            builder.addComponent(auffang.getPanel(), true);
            builder.addSeparator("Bemerkungen", true);
            builder.setFill(true, true);
            builder.setWeightY(0.3);
            builder.addComponent(bemerkungScroller, true);

            datenAbfuellflaechenJgsTab = builder.getPanel();
        }
        return datenAbfuellflaechenJgsTab;
    }

    private JPanel getDatenJgsTab() {
        if (datenJgsTab == null) {

            JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            PanelBuilder builder = new PanelBuilder(GridBagConstraints.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);

            PanelBuilder stammdaten = new PanelBuilder(builder);
            stammdaten.addComponent(baujahrFeld, "Baujahr:");
            stammdaten.addComponent(aktenzeichenField, "Aktenzeichen:", true);
            stammdaten.addComponent(lagerflaecheFeld, "Lagerfläche[m²]:");
            stammdaten.addComponent(mengeFeld, "Lagervolumen[m³]:", true);
            stammdaten.addComponent(abstandGewFeld, "Abstand zu Gewässer[m]:");
            stammdaten.addComponent(gewNameFeld, "Gewässername:", true);
            stammdaten.addComponent(abstandBrunnenFeld, "Abstand zu Brunnen[m]:");
            stammdaten.addComponent(tierhaltungFeld, "Tierhaltung:", true);
            stammdaten.addComponent(inbetriebnahmeChooser, "Inbetriebnahme:");
            stammdaten.addComponent(erfassungChooser, "Erfassung:", true);
            stammdaten.addComponent(genehmigungChooser, "Genehmigung:");
            stammdaten.addComponent(stillegungChooser, "Stillegung:", true);

            builder.addSeparator("Stammdaten", true);
            builder.addComponent(stammdaten.getPanel(), true);
            builder.addSeparator("Anlagenchronologie", true);
            builder.setFill(true, true);
            builder.setWeightY(1);
            builder.addComponent(chronoScroller, true);

            datenJgsTab = builder.getPanel();
        }
        return datenJgsTab;
    }

    private JPanel getAusfuehrungAbfuellflaechenTab() {
        if (ausfuehrungAbfuellflaechenTab == null) {
            PanelBuilder builder = new PanelBuilder(GridBagConstraints.NORTHWEST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);

            PanelBuilder daten = new PanelBuilder(builder);
            daten.addComponent(bodenflaechenAusfBox, "Bodenflächenausführung:");
            daten.addComponent(fugenMaterialFeld, "Fugenmaterial:", true);
            daten.addComponent(dickeFeld, "Dicke:");
            daten.addComponent(niederschlagSchutzBox, "Niederschlagsschutz:", true);
            daten.addComponent(gueteFeld, "Güte:");
            daten.addComponent(abscheiderVorhandenCheck, true);

            builder.addSeparator("Daten", true);
            builder.addComponent(daten.getPanel(), true);
            builder.addSeparator("Beschreibung Bodenfläche", true);
            builder.setFill(true, true);
            builder.setWeightY(1);
            builder.addComponent(new JScrollPane(beschrBodenflaecheArea), true);
            builder.setWeightY(0);
            builder.addSeparator("Beschreibung Fugenmaterial", true);
            builder.setWeightY(1);
            builder.addComponent(new JScrollPane(beschrFugenmaterialArea), true);
            builder.setWeightY(0);
            builder.addSeparator("Beschreibung Ableitung/Niederschlagswasser", true);
            builder.setWeightY(1);
            builder.addComponent(new JScrollPane(beschrAblNiederschlArea), true);
            builder.fillColumn();
            ausfuehrungAbfuellflaechenTab = builder.getPanel();
        }
        return ausfuehrungAbfuellflaechenTab;
    }

    private JPanel getSvPruefungTab() {
        if (svPruefungTab == null) {
            PanelBuilder builder = new PanelBuilder(GridBagConstraints.NORTHWEST, true, true, 1, 1, 1, 1,
            0, 0, 5, 5);
            builder.setEmptyBorder(5);
            builder.addComponent(new JScrollPane(svPruefungTabelle));
            svPruefungTab = builder.getPanel();
        }
        return svPruefungTab;
    }

    private JPanel getVerwVerfahrenTab() {
        if (verwVerfahrenTab == null) {
            PanelBuilder builder = new PanelBuilder(GridBagConstraints.NORTHWEST, true, true, 1, 1, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);
            builder.addComponent(new JScrollPane(verwVerfahrenTabelle));
            verwVerfahrenTab = builder.getPanel();
        }
        return verwVerfahrenTab;
    }

    private JPanel getVerwGebuehrenTab() {
        if (verwGebuehrenTab == null) {
            PanelBuilder builder = new PanelBuilder(GridBagConstraints.NORTHWEST, true, true, 1, 1, 1, 1,
                    0, 0, 5, 5);
            builder.setEmptyBorder(5);
            builder.addComponent(new JScrollPane(verwGebuehrenTabelle));
            verwGebuehrenTab = builder.getPanel();
        }
        return verwGebuehrenTab;
    }
}


/**
 * Ein editierbares TableModel für die Vaws-Anlagenchronologie.
 * @author David Klotz
 */
class VawsAnlagenChronoModel extends EditableListTableModel {
    private static final long serialVersionUID = -2520120636324926275L;
    private List<Anlagenchrono> geloeschte;
    private Fachdaten fachdaten;

    /**
     * Erzeugt ein einfaches TableModel für die Vaws-Anlagenchronologie.
     */
    public VawsAnlagenChronoModel() {
        super(new String[]{
                "Datum",
                "Sachverhalt",
                "Wiedervorlage",
                "abgeschl."
        },
        false, true);
        geloeschte = new ArrayList<Anlagenchrono>();
    }

    /**
     * Setzt das Fachdaten-Objekt und aktualisiert die Tabelle.
     * @param fachdaten Das Fachdaten-Objekt
     */
    public void setFachdaten(Fachdaten fachdaten) {
        this.fachdaten = fachdaten;

        if (fachdaten != null) {
            setList(DatabaseQuery.getAnlagenChronos(fachdaten));
            fireTableDataChanged();
        }
    }

    @Override
    public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
        Anlagenchrono chrono = (Anlagenchrono) objectAtRow;
        String tmp = "";
        if (newValue instanceof String) {
            tmp = (String) newValue;
        }

        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);

        switch (columnIndex) {
        case 0:
            try {
                Date tmpDate = format.parse(tmp);
                chrono.setDatum(tmpDate);
            } catch (ParseException e) {
                //.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
            }
            break;
        case 1:
            // Auf 255 Zeichen kürzen, da die Datenbank-Spalte nur 255 Zeichen breit ist
            if (tmp.length() > 255) {
                tmp = tmp.substring(0,255);
            }
            chrono.setSachverhalt(tmp);
            break;
        case 2:
            if ("".equals(tmp)) {
                chrono.setWv(null);
            } else {
                try {
                    Date tmpDate = format.parse(tmp);
                    chrono.setWv(tmpDate);
                } catch (ParseException e) {
                    //.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
                }
            }
            break;
        case 3:
            Boolean tmpB = (Boolean) newValue;
            chrono.setAbgeschlossen(tmpB.booleanValue());
            break;

        default:
            break;
        }
    }

    @Override
    public Object newObject() {
        Anlagenchrono chr = new Anlagenchrono();
        chr.setFachdaten(fachdaten);
        chr.setDatum(new Date());
        return chr;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        Anlagenchrono chrono = (Anlagenchrono) objectAtRow;
        if (chrono.getId() != null) {
            geloeschte.add(chrono);
        }
        return true;
    }

    public List<Anlagenchrono> getGeloeschte() {
        return geloeschte;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Anlagenchrono ac = (Anlagenchrono) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        // Datum:
        case 0:
            tmp = AuikUtils.getStringFromDate(ac.getDatum());
            break;
        // Sachverhalt:
        case 1:
            tmp = ac.getSachverhalt();
            break;
        // Wv:
        case 2:
            tmp = AuikUtils.getStringFromDate(ac.getWv());
            break;
        // Verfahren abgeschlossen?:
        case 3:
            tmp = ac.getAbgeschlossen();
            break;

        // Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
        default:
            tmp = "ERROR";
            break;
        }

        return tmp;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    /**
     * Liefert einen Datensatz in einer bestimmten Zeile.
     * @param row Die Zeile der Tabelle.
     * @return Den Datensatz, der in dieser Zeile angezeigt wird.
     */
    public Anlagenchrono getDatenSatz(int row) {
        return (Anlagenchrono) getObjectAtRow(row);
    }

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     * Die Liste wird direkt mittels setList "befüllt".
     */
    @Override
    public void updateList() {
    }
}

/**
 * Ein editierbares TableModel für die Vaws-Kontrollen
 * (Sachverständigenprüfung).
 * @author David Klotz
 */
class VawsKontrollenModel extends EditableListTableModel {
    private static final long serialVersionUID = 1747805482011126348L;
    private List<Kontrollen> geloeschte;
    private Fachdaten fachdaten;

    /**
     * Erzeugt ein einfaches TableModel für die Vaws-Kontrollen.
     */
    public VawsKontrollenModel() {
        super(new String[]{
                "Prüfdatum",
                "Prüfer",
                "Prüfergebnis",
                "Nächste Prüfung",
                "Prfg. abgeschl."
        },
        false, true);
        geloeschte = new ArrayList<Kontrollen>();
    }

    /**
     * Setzt das Fachdaten-Objekt und aktualisiert die Tabelle.
     * @param fachdaten Das Fachdaten-Objekt
     */
    public void setFachdaten(Fachdaten fachdaten) {
        this.fachdaten = fachdaten;

        if (fachdaten != null) {
            setList(DatabaseQuery.getKontrollen(fachdaten));
            fireTableDataChanged();
        }
    }

    @Override
    public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
        Kontrollen ktrl = (Kontrollen) objectAtRow;
        String tmp = "";
        if (newValue instanceof String) {
            tmp = (String) newValue;
        }

        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);

        switch (columnIndex) {
        case 0:
            try {
                Date tmpDate = format.parse(tmp);
                ktrl.setPruefdatum(tmpDate);
            } catch (ParseException e) {
                //.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
            }
            break;
        case 1:
            ktrl.setPruefer(tmp);
            break;
        case 2:
            ktrl.setPruefergebnis(tmp);
            break;
        case 3:
            if ("".equals(tmp)) {
                ktrl.setNaechstepruefung(null);
            } else {
                try {
                    Date tmpDate = format.parse(tmp);
                    ktrl.setNaechstepruefung(tmpDate);
                } catch (ParseException e) {
                    //.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
                }
            }
            break;
        case 4:
            Boolean tmpB = (Boolean) newValue;
            ktrl.setPruefungabgeschlossen(tmpB.booleanValue());
            break;

        default:
            break;
        }
    }

    @Override
    public Object newObject() {
        Kontrollen ktr = new Kontrollen();
        ktr.setFachdaten(fachdaten);
        ktr.setPruefdatum(new Date());
        ktr.setPruefungabgeschlossen(false);
        return ktr;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        Kontrollen ktr = (Kontrollen) objectAtRow;
        if (ktr.getId() != null) {
            geloeschte.add(ktr);
        }
        return true;
    }

    public List<Kontrollen> getGeloeschte() {
        return geloeschte;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Kontrollen ac = (Kontrollen) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        // Prüfdatum:
        case 0:
            tmp = AuikUtils.getStringFromDate(ac.getPruefdatum());
            break;
        // Prüfer:
        case 1:
            tmp = ac.getPruefer();
            break;
        // Prüfergebnis:
        case 2:
            tmp = ac.getPruefergebnis();
            break;
        // Nächste Prüfung:
        case 3:
            tmp = AuikUtils.getStringFromDate(ac.getNaechstepruefung());
            break;
        // Prüfung abgeschlossen?:
        case 4:
            tmp = ac.getPruefungabgeschlossen();
            break;

        // Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
        default:
            tmp = "ERROR";
            break;
        }

        return tmp;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    /**
     * Liefert einen Datensatz in einer bestimmten Zeile.
     * @param row Die Zeile der Tabelle.
     * @return Den Datensatz, der in dieser Zeile angezeigt wird.
     */
    public Kontrollen getDatenSatz(int row) {
        return (Kontrollen) getObjectAtRow(row);
    }

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     * Die Liste wird direkt mittels setList "befüllt".
     */
    @Override
    public void updateList() {
    }
}

/**
 * Ein editierbares TableModel für die VawsVerwaltungsverfahren.
 * @author David Klotz
 */
class VerwVerfahrenModel extends EditableListTableModel {
    private static final long serialVersionUID = -7932308301889587228L;
    private List<Verwaltungsverf> geloeschte;
    private Fachdaten fachdaten;

    /**
     * Erzeugt ein einfaches TableModel für die Vaws-Verwaltungsverfahren.
     */
    public VerwVerfahrenModel() {
        super(new String[]{
                "Datum",
                "Maßnahmen der Verwaltung",
                "Wiedervorlage",
                "abgeschl."
        },
        false, true);
        geloeschte = new ArrayList<Verwaltungsverf>();
    }

    /**
     * Setzt das Fachdaten-Objekt und aktualisiert die Tabelle.
     * @param fachdaten Das Fachdaten-Objekt
     */
    public void setFachdaten(Fachdaten fachdaten) {
        this.fachdaten = fachdaten;

        if (fachdaten != null) {
            setList(DatabaseQuery.getVerwaltungsverf(fachdaten));
            fireTableDataChanged();
        }
    }

    @Override
    public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
        Verwaltungsverf verf = (Verwaltungsverf) objectAtRow;
        String tmp = "";
        if (newValue instanceof String) {
            tmp = (String) newValue;
        }

        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);

        switch (columnIndex) {
        case 0:
            try {
                Date tmpDate = format.parse(tmp);
                verf.setDatum(tmpDate);
            } catch (ParseException e) {
                //.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
            }
            break;
        case 1:
            verf.setMassnahme(tmp);
            break;
        case 2:
            if ("".equals(tmp)) {
                verf.setWiedervorlage(null);
            } else {
                try {
                    Date tmpDate = format.parse(tmp);
                    verf.setWiedervorlage(tmpDate);
                } catch (ParseException e) {
                    //.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
                }
            }
            break;
        case 3:
            Boolean tmpB = (Boolean) newValue;
            verf.setWvverwverf(tmpB.booleanValue());
            break;

        default:
            break;
        }
    }

    @Override
    public Object newObject() {
        Verwaltungsverf verf = new Verwaltungsverf();
        verf.setFachdaten(fachdaten);
        verf.setWvverwverf(false);
        verf.setDatum(new Date());
        return verf;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        Verwaltungsverf verf = (Verwaltungsverf) objectAtRow;
        if (verf.getId() != null) {
            geloeschte.add(verf);
        }
        return true;
    }

    public List<Verwaltungsverf> getGeloeschte() {
        return geloeschte;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Verwaltungsverf verf = (Verwaltungsverf) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        // Datum:
        case 0:
            tmp = AuikUtils.getStringFromDate(verf.getDatum());
            break;
        // Maßnahme:
        case 1:
            tmp = verf.getMassnahme();
            break;
        // WV:
        case 2:
            tmp = AuikUtils.getStringFromDate(verf.getWiedervorlage());
            break;
        // Prüfung abgeschlossen?:
        case 3:
            tmp = new Boolean(verf.getWvverwverf());
            break;

        // Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
        default:
            tmp = "ERROR";
            break;
        }

        return tmp;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    /**
     * Liefert einen Datensatz in einer bestimmten Zeile.
     * @param row Die Zeile der Tabelle.
     * @return Den Datensatz, der in dieser Zeile angezeigt wird.
     */
    public Verwaltungsverf getDatenSatz(int row) {
        return (Verwaltungsverf) getObjectAtRow(row);
    }

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     * Die Liste wird direkt mittels setList "befüllt".
     */
    @Override
    public void updateList() {
    }
}

/**
 * Ein editierbares TableModel für die VawsVerwaltungsverfahren.
 * @author David Klotz
 */
class VerwGebuehrenModel extends EditableListTableModel {
    private static final long serialVersionUID = 8662150283828728780L;
    private List<Verwaltungsgebuehren> geloeschte;
    private Fachdaten fachdaten;

    /**
     * Erzeugt ein einfaches TableModel für Vaws-Fachdaten.
     */
    public VerwGebuehrenModel() {
        super(new String[]{
                "Datum",
                "Gebührenart",
                "Betrag",
                "Abschnitt",
                "Kassenzeichen"
        },
        false, true);
        geloeschte = new ArrayList<Verwaltungsgebuehren>();
    }

    /**
     * Setzt das Fachdaten-Objekt und aktualisiert die Tabelle.
     * @param fachdaten Das Fachdaten-Objekt
     */
    public void setFachdaten(Fachdaten fachdaten) {
        this.fachdaten = fachdaten;

        if (fachdaten != null) {
            setList(DatabaseQuery.getVerwaltungsgebuehren(fachdaten));
            fireTableDataChanged();
        }
    }

    @Override
    public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
        Verwaltungsgebuehren gebuehr = (Verwaltungsgebuehren) objectAtRow;
        String tmp = "";
        if (newValue instanceof String) {
            tmp = (String) newValue;
        }

        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);

        switch (columnIndex) {
        case 0:
            try {
                Date tmpDate = format.parse(tmp);
                gebuehr.setDatum(tmpDate);
            } catch (ParseException e) {
                //.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
            }
            break;
        case 1:
            gebuehr.setGebuehrenarten((Gebuehrenarten) newValue);
            break;
        case 2:
            Float tmpWert = null;
            if (newValue instanceof Float) {
                tmpWert = (Float) newValue;
            } else if (newValue instanceof KommaDouble) {
                tmpWert = new Float(((KommaDouble)newValue).getValue().doubleValue());
            }
            gebuehr.setBetrag(tmpWert);
            break;
        case 3:
            gebuehr.setAbschnitt(tmp);
            break;
        case 4:
            gebuehr.setKassenzeichen(tmp);
            break;

        default:
            break;
        }
    }

    @Override
    public Object newObject() {
        Verwaltungsgebuehren gebuehr = new Verwaltungsgebuehren();
        gebuehr.setBetrag(new Float(0.0));
        gebuehr.setAbschnitt("360.12 LW");
        gebuehr.setFachdaten(fachdaten);
        gebuehr.setDatum(new Date());
        return gebuehr;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        Verwaltungsgebuehren gebuehr = (Verwaltungsgebuehren) objectAtRow;
        if (gebuehr.getId() != null) {
            geloeschte.add(gebuehr);
        }
        return true;
    }

    public List<Verwaltungsgebuehren> getGeloeschte() {
        return geloeschte;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Verwaltungsgebuehren gebuehr = (Verwaltungsgebuehren) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        // Datum:
        case 0:
            tmp = AuikUtils.getStringFromDate(gebuehr.getDatum());
            break;
        // Gebührenart:
        case 1:
            tmp = gebuehr.getGebuehrenarten();
            break;
        // Betrag:
        case 2:
            tmp = new KommaDouble(gebuehr.getBetrag().doubleValue());
            break;
        // Abschnitt:
        case 3:
            tmp = gebuehr.getAbschnitt();
            break;
        // Kassenzeichen:
        case 4:
            tmp = gebuehr.getKassenzeichen();
            break;

        // Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
        default:
            tmp = "ERROR";
            break;
        }

        return tmp;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1) {
            return Gebuehrenarten.class;
        } else if (columnIndex == 2) {
            return KommaDouble.class;
        } else {
            return String.class;
        }
    }

    /**
     * Liefert einen Datensatz in einer bestimmten Zeile.
     * @param row Die Zeile der Tabelle.
     * @return Den Datensatz, der in dieser Zeile angezeigt wird.
     */
    public Verwaltungsgebuehren getDatenSatz(int row) {
        return (Verwaltungsgebuehren) getObjectAtRow(row);
    }

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     * Die Liste wird direkt mittels setList "befüllt".
     */
    @Override
    public void updateList() {
    }
}