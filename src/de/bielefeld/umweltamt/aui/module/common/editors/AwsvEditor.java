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
import java.awt.Point;
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

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.FormLayout;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;

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
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein Editor für VAWS-Datensätze.
 * @author David Klotz
 */
public class AwsvEditor extends AbstractBaseEditor {
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
	private LimitedTextField hnrFeld;
	private JComboBox fluessigkeitBox;
	private JComboBox vbfBox;
	private JComboBox gefStufeBox;
	private JComboBox wgkBox;

	// Tabs:
	// Widgets für alle Daten-Tabs:
	private IntegerField baujahrFeld;
	private TextFieldDateChooser inbetriebnahmeChooser;
	private TextFieldDateChooser genehmigungChooser;
	private TextFieldDateChooser erfassungChooser;
	private TextFieldDateChooser stilllegungChooser;
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
	private LimitedTextArea beschreibungSFeld;
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
	private DoubleField wandhoeheFeld;
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
	public AwsvEditor(Fachdaten fachdaten, HauptFrame owner) {
		// super("VAwS-Einzelanlage (" + fachdaten + ")", fachdaten, owner);
		super("VAwS-Einzelanlage "
				+ (fachdaten.getBehaelterid() == null ? "(Neu)" : fachdaten.getBehaelterid().toString()), fachdaten,
				owner);
	}

	/**
	 * Erzeugt einen neuen Dialog zum Bearbeiten von VAWS-Fachdaten. Schaltet zu
	 * einem bestimmten Tab um.
	 * @param tab "Sachverständigenprüfung", "Verwaltungsverfahren" oder
	 *            "Herstellnummer"
	 */
	public AwsvEditor(Fachdaten fachdaten, HauptFrame owner, String tab) {
		this(fachdaten, owner);
		if ("Sachverständigenprüfung".equals(tab)) {
			tabbedPane.setSelectedComponent(getSvPruefungTab());
		} else if ("Verwaltungsverfahren".equals(tab)) {
			tabbedPane.setSelectedComponent(getVerwVerfahrenTab());
            //} else if ("Herstellnummer".equals(tab)) {
			// Der Reiter Daten wird aufgerufen
		}

	}

	/*
	 * Überschrieben, damit die unterschiedlichen Anlagen-Arten ihre Größen separat
	 * speichern, obwohl ja alle von der Klasse VawsFachdaten sind.
	 *
	 * @see de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#
	 * getEditedClassName()
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
	 * Liefert die Abfüllflächen-Fachdaten zum bearbeiteten Objekt. Achtung: Nur bei
	 * Abfüllflächen-Objekten aufrufen!
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
	 * Liefert die Abfüllflächen-Fachdaten zum bearbeiteten Objekt. Achtung: Nur bei
	 * Abfüllflächen-Objekten aufrufen!
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

	/*
	 * (non-Javadoc)
	 * @see
	 * de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog#buildContentArea()
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
		fluessigkeitBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		// fluessigkeitBox.setPrototypeDisplayValue("Lösungsmittelrückstände");

		vbfBox = new JComboBox(DatabaseQuery.getVawsVbfEinstufungen());
		vbfBox.setEditable(false);
		// vbfBox.setPrototypeDisplayValue(" A III ");

		gefStufeBox = new JComboBox(DatabaseQuery.getGefaehrdungsstufen());
		gefStufeBox.setEditable(false);
		// gefStufeBox.setPrototypeDisplayValue(" A ");

		String[] items = { "1", "2", "3", "awg", "nwg" };
		wgkBox = new JComboBox(items);
		wgkBox.setEditable(false);
		// wgkBox.setPrototypeDisplayValue(" 3 ");

		tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		// Widgets für alle Daten-Tabs:
		baujahrFeld = new IntegerField();
		inbetriebnahmeChooser = new TextFieldDateChooser();
		genehmigungChooser = new TextFieldDateChooser();
		erfassungChooser = new TextFieldDateChooser();
		stilllegungChooser = new TextFieldDateChooser();
		aktenzeichenField = new LimitedTextField(50);
		pruefTurnusFeld = new DoubleField(0);

		behaelterArtBox = new JComboBox(DatabaseQuery.getBehaelterarten());
		behaelterArtBox.setEditable(false);

		materialBox = new JComboBox(DatabaseQuery.getMaterialien());
		materialBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		// TODO: Material-Box editable / Tabelleninhalt?
		materialBox.setEditable(true);
		bemerkungArea = new LimitedTextArea(1000);

		// Daten für die Anlagenchronologie-Tabelle:
		anlagenChronoModel = new VawsAnlagenChronoModel();
		anlagenChronoTabelle = new JTable(anlagenChronoModel);
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

		anlagenChronoTabelle.getInputMap().put(
				(KeyStroke) getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY),
				getTabellenItemLoeschAction().getValue(Action.NAME));
		anlagenChronoTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME),
				getTabellenItemLoeschAction());

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
		beschreibungAFeld = new LimitedTextArea(1250);
		beschreibungSFeld = new LimitedTextArea(1250);

		// JGS
		lagerflaecheFeld = new IntegerField();
		abstandGewFeld = new IntegerField();
		gewNameFeld = new LimitedTextField(25);
		abstandBrunnenFeld = new IntegerField();
		tierhaltungFeld = new LimitedTextField(25);
		seitenwandCheck = new JCheckBox("Seitenwand");
		wandhoeheFeld = new DoubleField(0);
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
		beschreibungRFeld = new LimitedTextArea(1250);

		// Ausführung (Abfüllflächen)
		bodenflaechenAusfBox = new JComboBox(DatabaseQuery.getBodenflaechenausf());
		bodenflaechenAusfBox.setEditable(true);
		dickeFeld = new DoubleField(0);
		gueteFeld = new LimitedTextField(50);
		fugenMaterialFeld = new LimitedTextField(50);
		niederschlagSchutzBox = new JComboBox(DatabaseQuery.getNiederschlagschutz());
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
		pruefergebnisBox = new JComboBox(DatabaseQuery.getPruefergebniss());
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

		svPruefungTabelle.getInputMap().put((KeyStroke) getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY),
				getTabellenItemLoeschAction().getValue(Action.NAME));
		svPruefungTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME),
				getTabellenItemLoeschAction());

		// Verwaltungsverfahren
		verwVerfahrenModel = new VerwVerfahrenModel();
		verwVerfahrenTabelle = new JTable(verwVerfahrenModel);
		verwVerfahrenTabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
		verwVerfahrenTabelle.getColumnModel().getColumn(1).setPreferredWidth(200);
		verwVerfahrenTabelle.getColumnModel().getColumn(2).setPreferredWidth(50);
		verwVerfahrenTabelle.getColumnModel().getColumn(3).setPreferredWidth(50);

		// Für die ComboBox bei "Maßnahme"
		massnahmenBox = new JComboBox(DatabaseQuery.getVerwaltungsMassnahmen());
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

		verwVerfahrenTabelle.getInputMap().put(
				(KeyStroke) getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY),
				getTabellenItemLoeschAction().getValue(Action.NAME));
		verwVerfahrenTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME),
				getTabellenItemLoeschAction());

		// Verwaltungsgebuehren
		verwGebuehrenModel = new VerwGebuehrenModel();
		verwGebuehrenTabelle = new JTable(verwGebuehrenModel);
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

		verwGebuehrenTabelle.getInputMap().put(
				(KeyStroke) getTabellenItemLoeschAction().getValue(Action.ACCELERATOR_KEY),
				getTabellenItemLoeschAction().getValue(Action.NAME));
		verwGebuehrenTabelle.getActionMap().put(getTabellenItemLoeschAction().getValue(Action.NAME),
				getTabellenItemLoeschAction());

		// Layout topPanel:
		FormLayout topLayout = new FormLayout("p:g, 3dlu:g, p:g, 3dlu:g, p:g, 3dlu:g, p:g, 3dlu:g, p:g");
		DefaultFormBuilder topBuilder = new DefaultFormBuilder(topLayout);

		topBuilder.append(header, 9);
//        topBuilder.append(ComponentFactory.buildRightAlignedBar(reportButton), 7);
//        topBuilder.append(subHeader, 9);
		/* hnrLabel = */ topBuilder.append("Bezeichnung / Zuordnung:");
		topBuilder.append("Flüssigkeit:");
		topBuilder.append("VbF:");
		topBuilder.append("Gefährdungsstufe:");
		topBuilder.append("WGK:");
		topBuilder.append(hnrFeld, fluessigkeitBox, vbfBox);
		topBuilder.append(gefStufeBox, wgkBox);

		topPanel = topBuilder.getPanel();
        topPanel.setBorder(Paddings.DLU2);
		// Panel bauen:
		JPanel panel = new JPanel(new BorderLayout());

		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(tabbedPane, BorderLayout.CENTER);

		return panel;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#fillForm(
	 * )
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
		stilllegungChooser.setDate(getFachdaten().getStilllegungsdatum());
		pruefTurnusFeld.setValue(getFachdaten().getPruefturnus());
		aktenzeichenField.setText(getFachdaten().getAktenzeichen());

		behaelterArtBox.setSelectedItem(getFachdaten().getBehaelterart());
		materialBox.setSelectedItem(getFachdaten().getMaterial());
		bemerkungArea.setText(getFachdaten().getBemerkungen());

		anlagenChronoModel.setFachdaten(getFachdaten());

		if (getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_VAWS_ABSCHEIDER)) {
			tabbedPane.addTab("Daten", getDatenVAWSAbscheiderTab());
			tabbedPane.addTab("Ausführung", getAusfuehrungVAWSAbscheiderTab());
			tabbedPane.addTab("Schutzvorkehrungen", getSchutzvorkehrungenVAWSAbscheiderTab());

			mengeFeld.setValue(getFachdaten().getMenge());

			// Daten Tab
//            flüssigkField.setText(getAbscheider().getMedium());

			if (getAbscheider().getKompaktanlage() != null)
				kompaktCheck.setSelected(getAbscheider().getKompaktanlage());
			else
				kompaktCheck.setSelected(false);

			if (getAbscheider().getKkl1() != null)
				kompKCheck.setSelected(getAbscheider().getKkl1());
			else
				kompKCheck.setSelected(false);

			if (getAbscheider().getLfkl2() != null)
				kompLFCheck.setSelected(getAbscheider().getLfkl2());
			else
				kompLFCheck.setSelected(false);

			if (getAbscheider().getPs() != null)
				kompPSCheck.setSelected(getAbscheider().getPs());
			else
				kompPSCheck.setSelected(false);

			if (getAbscheider().getSf() != null)
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

			// Schutzvorkehrungen Tab

			if (getAbscheider().getUeberhausr() != null)
				ueberCheck.setSelected(getAbscheider().getUeberhausr());
			else
				ueberCheck.setSelected(false);

			if (getAbscheider().getWaschanlvorh() != null)
				waschCheck.setSelected(getAbscheider().getWaschanlvorh());
			else
				waschCheck.setSelected(false);

			if (getAbscheider().getAbgabe() != null)
				abgabeCheck.setSelected(getAbscheider().getAbgabe());
			else
				abgabeCheck.setSelected(false);

			if (getAbscheider().getHlzapfanl() != null)
				hochCheck.setSelected(getAbscheider().getHlzapfanl());
			else
				hochCheck.setSelected(false);

			if (getAbscheider().getBelvonlagerbh() != null)
				belueftCheck.setSelected(getAbscheider().getBelvonlagerbh());
			else
				belueftCheck.setSelected(false);

			if (getAbscheider().getRueckhalteausr() != null)
				rueckCheck.setSelected(getAbscheider().getRueckhalteausr());
			else
				rueckCheck.setSelected(false);
		} else if (DatabaseQuery.isLageranlage(getFachdaten())) {
			tabbedPane.addTab("Daten", getDatenLageranlagenTab());
			tabbedPane.addTab("Schutzvorkehrungen", getSchutzLageranlagenTab());
			tabbedPane.addTab("Leitungen", getLeitungenLageranlagenTab());

			mengeFeld.setValue(getFachdaten().getMenge());

			if (getFachdaten().getDoppelwandig() != null)
				doppelWandigCheck.setSelected(getFachdaten().getDoppelwandig());
			else
				doppelWandigCheck.setSelected(false);

			if (getFachdaten().getLeckanzeige() != null)
				leckAnzeigeCheck.setSelected(getFachdaten().getLeckanzeige());
			else
				leckAnzeigeCheck.setSelected(false);

			if (getFachdaten().getAuffangraum() != null)
				auffangRaumCheck.setSelected(getFachdaten().getAuffangraum());
			else
				auffangRaumCheck.setSelected(false);

			if (getFachdaten().getLeckschutzauskleidung() != null)
				leckSchutzAuskleidungCheck.setSelected(getFachdaten().getLeckschutzauskleidung());
			else
				leckSchutzAuskleidungCheck.setSelected(false);

			if (getFachdaten().getGrenzwertgeber() != null)
				grenzWertGeberCheck.setSelected(getFachdaten().getGrenzwertgeber());
			else
				grenzWertGeberCheck.setSelected(false);

			if (getFachdaten().getKellerlagerung() != null)
				kellerLagerungCheck.setSelected(getFachdaten().getKellerlagerung());
			else
				kellerLagerungCheck.setSelected(false);

			if (getFachdaten().getInnenbeschichtung() != null)
				innenBeschichtungCheck.setSelected(getFachdaten().getInnenbeschichtung());
			else
				innenBeschichtungCheck.setSelected(false);

			if (getFachdaten().getSchutzSensor() != null)
				schutzSensorCheck.setSelected(getFachdaten().getSchutzSensor());
			else
				schutzSensorCheck.setSelected(false);

			if (getFachdaten().getSchutzFolie() != null)
				schutzFolieCheck.setSelected(getFachdaten().getSchutzFolie());
			else
				schutzFolieCheck.setSelected(false);

			if (getFachdaten().getSchutzAntiheber() != null)
				schutzAntiheberCheck.setSelected(getFachdaten().getSchutzAntiheber());
			else
				schutzAntiheberCheck.setSelected(false);

			beschreibungAFeld.setText(getFachdaten().getBeschreibungA());
			beschreibungSFeld.setText(getFachdaten().getBeschreibungS());

			if (getFachdaten().getOberirdisch() != null)
				oberIrdischCheck.setSelected(getFachdaten().getOberirdisch());
			else
				oberIrdischCheck.setSelected(false);

			if (getFachdaten().getUnterirdisch() != null)
				unterIrdischCheck.setSelected(getFachdaten().getUnterirdisch());
			else
				unterIrdischCheck.setSelected(false);

			if (getFachdaten().getSaugleitung() != null)
				saugLeitungCheck.setSelected(getFachdaten().getSaugleitung());
			else
				saugLeitungCheck.setSelected(false);

			if (getFachdaten().getRohrKathodenschutz() != null)
				rohrKathSchCheck.setSelected(getFachdaten().getRohrKathodenschutz());
			else
				rohrKathSchCheck.setSelected(false);

			if (getFachdaten().getAusKupfer() != null)
				ausKupferCheck.setSelected(getFachdaten().getAusKupfer());
			else
				ausKupferCheck.setSelected(false);

			if (getFachdaten().getAusStahl() != null)
				ausStahlCheck.setSelected(getFachdaten().getAusStahl());
			else
				ausStahlCheck.setSelected(false);

			if (getFachdaten().getMitSchutzrohr() != null)
				schutzrohrCheck.setSelected(getFachdaten().getMitSchutzrohr());
			else
				schutzrohrCheck.setSelected(false);

			if (getFachdaten().getAusHdpe() != null)
				ausHdpeCheck.setSelected(getFachdaten().getAusHdpe());
			else
				ausHdpeCheck.setSelected(false);

			if (getFachdaten().getDruckleitung() != null)
				druckleitungCheck.setSelected(getFachdaten().getDruckleitung());
			else
				druckleitungCheck.setSelected(false);

			beschreibungRFeld.setText(getFachdaten().getBeschreibungR());

		} else if (getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_ROHRLEITUNG)) {
			tabbedPane.addTab("Daten", getDatenRohrleitungenTab());

			ausfuehrungBox.setSelectedItem(getFachdaten().getAusfuehrung());

		} else if (getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_ABFUELLFLAECHE)) {
			tabbedPane.addTab("Daten", getDatenAbfuellflaechenTab());
			tabbedPane.addTab("Ausführung", getAusfuehrungAbfuellflaechenTab());

			if (getAbfuellflaeche().getEoh() != null)
				eohCheck.setSelected(getAbfuellflaeche().getEoh());
			else
				eohCheck.setSelected(false);

			if (getAbfuellflaeche().getEf() != null)
				efCheck.setSelected(getAbfuellflaeche().getEf());
			else
				efCheck.setSelected(false);

			if (getAbfuellflaeche().getAbfsaniert() != null)
				saniertCheck.setSelected(getAbfuellflaeche().getAbfsaniert());
			else
				saniertCheck.setSelected(false);

			if (getAbfuellflaeche().getAbfneuerstellt() != null)
				neuErstelltCheck.setSelected(getAbfuellflaeche().getAbfneuerstellt());
			else
				neuErstelltCheck.setSelected(false);

			bodenflaechenAusfBox.setSelectedItem(getAbfuellflaeche().getBodenflaechenausf());
			dickeFeld.setValue(getAbfuellflaeche().getDicke());
			gueteFeld.setText(getAbfuellflaeche().getGuete());
			groesseField.setValue(getAbfuellflaeche().getGroesse());
			fugenMaterialFeld.setText(getAbfuellflaeche().getFugenmaterial());
			niederschlagSchutzBox.setSelectedItem(getAbfuellflaeche().getNiederschlagschutz());

			if (getFachdaten().getDoppelwandig() != null)
				abscheiderVorhandenCheck.setSelected(getFachdaten().getDoppelwandig());
			else
				abscheiderVorhandenCheck.setSelected(false);

			beschrBodenflaecheArea.setText(getAbfuellflaeche().getBeschbodenfl());
			beschrFugenmaterialArea.setText(getAbfuellflaeche().getBeschfugenmat());
			beschrAblNiederschlArea.setText(getAbfuellflaeche().getBeschableitung());
		} else if (getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_FS)
				|| getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_GK)
				|| getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_GHB)) {
			tabbedPane.addTab("Daten", getDatenJgsTab());
			tabbedPane.addTab("Ausführung", getAbfuellflaecheJgsTab());

			mengeFeld.setValue(getFachdaten().getMenge());
			lagerflaecheFeld.setValue(getJgs().getLagerflaeche());
			abstandGewFeld.setValue(getJgs().getGewaesserAbstand());
			gewNameFeld.setText(getJgs().getGewaesserName());
			abstandBrunnenFeld.setValue(getJgs().getBrunnenAbstand());
			tierhaltungFeld.setText(getJgs().getTierhaltung());

			if (getJgs().getSeitenwaende() != null)
				seitenwandCheck.setSelected(getJgs().getSeitenwaende());
			else
				seitenwandCheck.setSelected(false);

			wandhoeheFeld.setValue(getJgs().getWandhoehe());
			bodenflaechenAusfBox.setSelectedItem(getJgs().getBodenplatte());

			if (getJgs().getUeberdachung() != null)
				ueberdachungCheck.setSelected(getJgs().getUeberdachung());
			else
				ueberdachungCheck.setSelected(false);

			auffangbehBox.setSelectedItem(getJgs().getAuffangbeh());
			volumenAuffangbehFeld.setValue(getJgs().getVolumenAuffangbeh());
			rohrleitungBox.setSelectedItem(getJgs().getRohrleitung());
			dichtheitChooser.setDate(getJgs().getDichtheitspruefung());

			if (getJgs().getDrainage() != null)
				drainageCheck.setSelected(getJgs().getDrainage());
			else
				drainageCheck.setSelected(false);

			if (getJgs().getFuellanzeiger() != null)
				fuellanzeigerCheck.setSelected(getJgs().getFuellanzeiger());
			else
				fuellanzeigerCheck.setSelected(false);

			if (getJgs().getSchieber() != null)
				schieberCheck.setSelected(getJgs().getSchieber());
			else
				schieberCheck.setSelected(false);

			if (getJgs().getAbdeckung() != null)
				abdeckungCheck.setSelected(getJgs().getAbdeckung());
			else
				abdeckungCheck.setSelected(false);

			if (getJgs().getLeitungGeprueft() != null)
				leitung_geprueftCheck.setSelected(getJgs().getLeitungGeprueft());
			else
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

	/*
	 * (non-Javadoc)
	 * @see
	 * de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#canSave()
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

	/*
	 * (non-Javadoc)
	 * @see
	 * de.bielefeld.umweltamt.aui.module.common.editors.AbstractBaseEditor#doSave()
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
		getFachdaten().setVbfeinstufung((String) vbfBox.getSelectedItem());
		getFachdaten().setGefaehrdungsstufe((String) gefStufeBox.getSelectedItem());
		getFachdaten().setWgk((String) wgkBox.getSelectedItem());
		getFachdaten().setMenge(mengeFeld.getDoubleValue());

		getFachdaten().setBaujahr(baujahrFeld.getIntValue());
		getFachdaten().setDatuminbetriebnahme(inbetriebnahmeChooser.getDate());
		getFachdaten().setDatumgenehmigung(genehmigungChooser.getDate());
		getFachdaten().setDatumerfassung(erfassungChooser.getDate());
		getFachdaten().setStilllegungsdatum(stilllegungChooser.getDate());
		getFachdaten().setPruefturnus(pruefTurnusFeld.getDoubleValue());
		getFachdaten().setAktenzeichen(aktenzeichenField.getText());

		getFachdaten().setBehaelterart((String) behaelterArtBox.getSelectedItem());
		getFachdaten().setMaterial((String) materialBox.getSelectedItem());
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

		} else if (getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_ROHRLEITUNG)) {
			getFachdaten().setAusfuehrung((String) ausfuehrungBox.getSelectedItem());
		}

		success = success && getFachdaten().merge();
		setEditedObject(Fachdaten.findById(getFachdaten().getBehaelterid()));

		// Für Abfüllflächen (wg. dem VawsAbfuellflaechen-Objekt)
		if (getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_ABFUELLFLAECHE)) {
			getAbfuellflaeche().setEoh(eohCheck.isSelected());
			getAbfuellflaeche().setEf(efCheck.isSelected());
			if (unbktCheck.isSelected()) {
				getAbfuellflaeche().setAbfneuerstellt(null);
				getAbfuellflaeche().setAbfsaniert(null);
			} else {
				getAbfuellflaeche().setAbfneuerstellt(neuErstelltCheck.isSelected());
				getAbfuellflaeche().setAbfsaniert(saniertCheck.isSelected());
			}

			getAbfuellflaeche().setBodenflaechenausf((String) bodenflaechenAusfBox.getSelectedItem());
			getAbfuellflaeche().setDicke(dickeFeld.getFloatValue());
			getAbfuellflaeche().setGuete(gueteFeld.getText());
			getAbfuellflaeche().setGroesse(groesseField.getIntValue());
			getAbfuellflaeche().setFugenmaterial(fugenMaterialFeld.getText());
			getAbfuellflaeche().setNiederschlagschutz((String) niederschlagSchutzBox.getSelectedItem());
			getAbfuellflaeche().setAbscheidervorh(abscheiderVorhandenCheck.isSelected());
			getAbfuellflaeche().setBeschbodenfl(beschrBodenflaecheArea.getText());
			getAbfuellflaeche().setBeschfugenmat(beschrFugenmaterialArea.getText());
			getAbfuellflaeche().setBeschableitung(beschrAblNiederschlArea.getText());

			success = success && getAbfuellflaeche().merge();
		}
		if (getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_VAWS_ABSCHEIDER)) {
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
		if (getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_FS)
				|| getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_GK)
				|| getFachdaten().getAnlagenart().equals(DatabaseConstants.VAWS_ANLAGENART_GHB)) {

			getJgs().setBodenplatte((String) bodenflaechenAusfBox.getSelectedItem());
			getJgs().setBrunnenAbstand(abstandBrunnenFeld.getIntValue());
			getJgs().setGewaesserAbstand(abstandGewFeld.getIntValue());
			getJgs().setGewaesserName(gewNameFeld.getText());
			getJgs().setLagerflaeche(lagerflaecheFeld.getIntValue());
			getJgs().setSeitenwaende(seitenwandCheck.isSelected());
			getJgs().setTierhaltung(tierhaltungFeld.getText());
			getJgs().setWandhoehe(wandhoeheFeld.getDoubleValue());
			getJgs().setAuffangbeh((String) auffangbehBox.getSelectedItem());
			getJgs().setVolumenAuffangbeh(volumenAuffangbehFeld.getDoubleValue());
			getJgs().setRohrleitung((String) rohrleitungBox.getSelectedItem());
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
		log.debug(anlagenChronoModel.getList().size() + " AnlagenChrono-Einträge neu/behalten, "
				+ anlagenChronoModel.getGeloeschte().size() + " Einträge gelöscht.");

		// Sachverständigenprüfung speichern:
		for (Iterator<?> it = svPruefungModel.getList().iterator(); it.hasNext();) {
			success = success && ((Kontrollen) it.next()).merge();
		}
		for (Iterator<Kontrollen> it = svPruefungModel.getGeloeschte().iterator(); it.hasNext();) {
			success = success && it.next().delete();
		}
		log.debug(svPruefungModel.getList().size() + " Sachverständigenprüfungs-Einträge neu/behalten, "
				+ svPruefungModel.getGeloeschte().size() + " Einträge gelöscht.");

		// Verwaltungsverfahren speichern:
		for (Iterator<?> it = verwVerfahrenModel.getList().iterator(); it.hasNext();) {
			success = success && ((Verwaltungsverf) it.next()).merge();
		}
		for (Iterator<Verwaltungsverf> it = verwVerfahrenModel.getGeloeschte().iterator(); it.hasNext();) {
			success = success && it.next().delete();
		}
		log.debug(verwVerfahrenModel.getList().size() + " Verwaltungsverfahren-Einträge neu/behalten, "
				+ verwVerfahrenModel.getGeloeschte().size() + " Einträge gelöscht.");

		// Verwaltunggebühren speichern:
		for (Iterator<?> it = verwGebuehrenModel.getList().iterator(); it.hasNext();) {
			success = success && ((Verwaltungsgebuehren) it.next()).merge();
		}
		for (Iterator<Verwaltungsgebuehren> it = verwGebuehrenModel.getGeloeschte().iterator(); it.hasNext();) {
			success = success && it.next().delete();
		}
		log.debug(verwGebuehrenModel.getList().size() + " Verwaltungsgebühren-Einträge neu/behalten, "
				+ verwGebuehrenModel.getGeloeschte().size() + " Einträge gelöscht.");

		return success;
	}

	// Kontextmenü für die Tabellen:

	/**
	 * Zeigt ein Kontextmenü an, wenn ein entsprechendes MouseEvent vorliegt.
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
						removeRowFromTable(anlagenChronoTabelle);
					} else if (index == (tabbedPane.getTabCount() - 3)) {
						removeRowFromTable(svPruefungTabelle);
					} else if (index == (tabbedPane.getTabCount() - 2)) {
						removeRowFromTable(verwVerfahrenTabelle);
					} else if (index == (tabbedPane.getTabCount() - 1)) {
						removeRowFromTable(verwGebuehrenTabelle);
					}
				}
			};
			tabellenItemLoeschAction.putValue(Action.MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_L));
			tabellenItemLoeschAction.putValue(Action.ACCELERATOR_KEY,
					KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
		}
		return tabellenItemLoeschAction;
	}

	private void removeRowFromTable(JTable table) {
		int row = table.getSelectedRow();

		// Natürlich nur, wenn wirklich eine Zeile ausgewählt ist
		if (row != -1) {
            ((EditableListTableModel) table.getModel()).removeRow(row);
		}
	}

	// Tabs:

	private JPanel getDatenVAWSAbscheiderTab() {
		if (datenVAWSAbscheiderTab == null) {

			JScrollPane bemerkungScroller = new JScrollPane(bemerkungArea,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			this.tabellenSplit = ComponentFactory.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT, bemerkungScroller,
					chronoScroller, 0.8);
			this.tabellenSplit.setDividerLocation(0.8);

			FormLayout layout = new FormLayout("r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g, 0:g");
			layout.setColumnGroups(new int[][] { { 1, 5 }, { 3, 7 } });
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendSeparator("Stammdaten");
			builder.append("Baujahr:", baujahrFeld);
			builder.append("Inbetriebnahme:", inbetriebnahmeChooser);
			builder.nextLine();

			builder.append("Erfassung:", erfassungChooser);
			builder.append("Prüfturnus [Jahre]:", pruefTurnusFeld);
			builder.nextLine();

			builder.append("Stilllegung:", stilllegungChooser);
			builder.nextLine();

			/*
			 * builder.appendRow("fill:30dlu"); builder.append("Flüssigkeit/Medium",
			 * flüssigkField); builder.nextLine();
			 */
			builder.appendSeparator("Komponenten");
//            builder.appendRow("3dlu");
			// builder.appendColumn("r:p, 3dlu, f:p:g, 10dlu, r:p, r:p, r:p, r:p, 0:g");
//            builder.append("Flüssigkeit/Medium", flüssigkField);
			builder.append("", kompaktCheck);
			builder.nextLine();
			builder.append("", kompSFCheck);
			builder.append("", kompPSCheck);
			builder.nextLine();
			builder.append("", kompKCheck);
			builder.append("", kompLFCheck);
			builder.nextLine();

			builder.appendSeparator("Bemerkungen und Anlagenchronologie");
			builder.appendRow("3dlu");
			builder.appendRow("fill:30dlu:grow");
			builder.nextLine(2);

			builder.append(this.tabellenSplit, 8);

			datenVAWSAbscheiderTab = builder.getPanel();
			datenVAWSAbscheiderTab.setBorder(Paddings.DIALOG);
		}
		return datenVAWSAbscheiderTab;
	}

	private JPanel getAusfuehrungVAWSAbscheiderTab() {
		if (ausfuehrungVAWSAbscheiderTab == null) {
			FormLayout layout = new FormLayout("r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendSeparator("Schlammfang");
			builder.append("Hersteller:", schlammHerstField);
			builder.append("Typ:", schlammTypField);
			builder.append("SF-Volumen [Liter]:", schlammSFVField);
			builder.nextLine();
			builder.append("Material:", schlammMatField);
			builder.append("Beschichtung:", schlammBeschField);
			builder.nextLine();

			builder.appendSeparator("Abscheider");
			builder.append("Hersteller:", abscheiderHerstField);
			builder.append("Typ:", abscheiderTypField);
			builder.append("Prüfzeichen:", abscheiderPruefField);
			builder.nextLine();
			builder.append("Material:", abscheiderMatField);
			builder.append("Beschichtung:", abscheiderBeschField);
			builder.append("Nenngröße (NS):", abscheiderNSField);
			builder.nextLine();
			builder.append("Ölspeichervolumen [Liter]:", abscheideroelField);
			builder.nextLine();

			builder.appendSeparator("Rohrleitungen: Zuleitungen");
			builder.append("DN:", zulDNField);
			builder.append("Material:", zulMatField);
			builder.append("Länge [m]:", zulLField);
			builder.nextLine();

			builder.appendSeparator("Rohrleitungen: Verbindungsleitungen");
			builder.append("DN:", verbDNField);
			builder.append("Material:", verbMatField);
			builder.append("Länge [m]:", verbLField);
			builder.nextLine();

			builder.appendSeparator("Rohrleitungen: Sonstige");
			builder.append("DN:", sonsDNField);
			builder.append("Material:", sonsMatField);
			builder.append("Länge [m]:", sonsLField);
			builder.nextLine();

			ausfuehrungVAWSAbscheiderTab = builder.getPanel();
			ausfuehrungVAWSAbscheiderTab.setBorder(Paddings.DIALOG);
		}
		return ausfuehrungVAWSAbscheiderTab;
	}

	private JPanel getSchutzvorkehrungenVAWSAbscheiderTab() {
		if (schutzvorkehrungenVAWSAbscheiderTab == null) {
			FormLayout layout = new FormLayout("p:g");
			// layout.setColumnGroups(new int[][]{{1}});
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendSeparator("Stammdaten");
			builder.append(ueberCheck);
			builder.nextLine();

			builder.append(waschCheck);
			builder.nextLine();

			builder.appendSeparator("Rückhaltevermögen");
			builder.append(abgabeCheck);
			builder.nextLine();
			builder.append(hochCheck);
			builder.nextLine();
			builder.append(belueftCheck);
			builder.nextLine();
			builder.append(rueckCheck);
			builder.nextLine();

			schutzvorkehrungenVAWSAbscheiderTab = builder.getPanel();
			schutzvorkehrungenVAWSAbscheiderTab.setBorder(Paddings.DIALOG);
		}
		return schutzvorkehrungenVAWSAbscheiderTab;
	}

	private JPanel getDatenLageranlagenTab() {
		if (datenLageranlagenTab == null) {

			JScrollPane bemerkungScroller = new JScrollPane(bemerkungArea,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			this.tabellenSplit = ComponentFactory.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT, bemerkungScroller,
					chronoScroller, 0.8);
			this.tabellenSplit.setDividerLocation(0.8);

			FormLayout layout = new FormLayout("r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g, 0:g");
			layout.setColumnGroups(new int[][] { { 1, 5 }, { 3, 7 } });
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendSeparator("Stammdaten");
			builder.append("Baujahr:", baujahrFeld);
			builder.append("Menge [m³]:", mengeFeld);
			builder.nextLine();

			builder.append("Inbetriebnahme:", inbetriebnahmeChooser);
			builder.append("Prüfturnus [Jahre]:", pruefTurnusFeld);
			builder.nextLine();

			builder.append("Genehmigung:", genehmigungChooser);
			builder.append("Behälterart:", behaelterArtBox);
			builder.nextLine();

			builder.append("Aktenzeichen:", aktenzeichenField);
			builder.append("Material:", materialBox);
			builder.nextLine();

			builder.append("Erfassung:", erfassungChooser);
			builder.append("Stilllegung:", stilllegungChooser);
			builder.nextLine();

			builder.appendSeparator("Bemerkungen und Anlagenchronologie");
			builder.appendRow("3dlu");
			builder.appendRow("fill:30dlu:grow");
			builder.nextLine(2);

			builder.append(this.tabellenSplit, 8);

			datenLageranlagenTab = builder.getPanel();
			datenLageranlagenTab.setBorder(Paddings.DIALOG);
		}
		return datenLageranlagenTab;
	}

	private JPanel getSchutzLageranlagenTab() {
		if (schutzLageranlagenTab == null) {
			FormLayout layout = new FormLayout("p, 3dlu:g, p, 3dlu:g, p, 3dlu:g, p");
			layout.setColumnGroups(new int[][] { { 1, 3, 5, 7 } });
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.append(doppelWandigCheck, leckAnzeigeCheck);
			builder.append(leckSchutzAuskleidungCheck, schutzSensorCheck);
			builder.nextLine();
			builder.append(auffangRaumCheck, kellerLagerungCheck);
			builder.append(innenBeschichtungCheck, schutzFolieCheck);
			builder.nextLine();
			builder.append(grenzWertGeberCheck, schutzAntiheberCheck);
			builder.nextLine();

			builder.appendSeparator("Beschreibung: Schutzvorkehrungen");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschreibungSFeld), 7);

			builder.appendSeparator("Beschreibung: Auffangraum");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschreibungAFeld), 7);

			schutzLageranlagenTab = builder.getPanel();
			schutzLageranlagenTab.setBorder(Paddings.DIALOG);
		}
		return schutzLageranlagenTab;
	}

	private JPanel getLeitungenLageranlagenTab() {
		if (leitungenLageranlagenTab == null) {
			FormLayout layout = new FormLayout("p, 3dlu:g, p, 3dlu:g, p");
			layout.setColumnGroups(new int[][] { { 1, 3, 5 } });
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.append(oberIrdischCheck, ausKupferCheck, saugLeitungCheck);
			builder.append(unterIrdischCheck, ausStahlCheck, rohrKathSchCheck);
			builder.append(schutzrohrCheck, ausHdpeCheck, druckleitungCheck);
			builder.nextLine();

			builder.appendSeparator("Beschreibung: Rohrleitung");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschreibungRFeld), 5);

			leitungenLageranlagenTab = builder.getPanel();
			leitungenLageranlagenTab.setBorder(Paddings.DIALOG);
		}
		return leitungenLageranlagenTab;
	}

	private JPanel getDatenRohrleitungenTab() {
		if (datenRohrleitungenTab == null) {

			JScrollPane bemerkungScroller = new JScrollPane(bemerkungArea,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			this.tabellenSplit = ComponentFactory.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT, bemerkungScroller,
					chronoScroller, 0.8);
			this.tabellenSplit.setDividerLocation(0.8);

			FormLayout layout = new FormLayout("r:p, 3dlu, p, 10dlu, r:p, 3dlu, p, 0:g");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendSeparator("Stammdaten");
			builder.append("Baujahr:", baujahrFeld);
			builder.append("Ausführung:", ausfuehrungBox);
			builder.nextLine();

			builder.append("Inbetriebnahme:", inbetriebnahmeChooser);
			builder.append("Prüfturnus [Jahre]:", pruefTurnusFeld);
			builder.nextLine();

			builder.append("Genehmigung:", genehmigungChooser);
			builder.append("Bauart:", behaelterArtBox);
			builder.nextLine();

			builder.append("Aktenzeichen:", aktenzeichenField);
			builder.append("Material:", materialBox);
			builder.nextLine();

			builder.append("Erfassung:", erfassungChooser);
			builder.append("Stilllegung:", stilllegungChooser);
			builder.nextLine();

			builder.appendSeparator("Bemerkungen und Anlagenchronologie");
			builder.appendRow("3dlu");
			builder.appendRow("fill:30dlu:grow");
			builder.nextLine(2);

			builder.append(this.tabellenSplit, 8);

			datenRohrleitungenTab = builder.getPanel();
			datenRohrleitungenTab.setBorder(Paddings.DIALOG);
		}
		return datenRohrleitungenTab;
	}

	private JPanel getDatenAbfuellflaechenTab() {
		if (datenAbfuellflaechenTab == null) {

			JScrollPane bemerkungScroller = new JScrollPane(bemerkungArea,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			this.tabellenSplit = ComponentFactory.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT, bemerkungScroller,
					chronoScroller, 0.8);
			this.tabellenSplit.setDividerLocation(0.8);

			FormLayout layout = new FormLayout("r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g"// , 10dlu, l:p:g"
			);
			layout.setColumnGroups(new int[][] { { 1, 5 }, { 3, 7 } });
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendSeparator("Stammdaten");
			builder.append("Baujahr:", baujahrFeld);
			builder.append("Prüfturnus [Jahre]:", pruefTurnusFeld);

			builder.append("Inbetriebnahme:", inbetriebnahmeChooser);
			builder.append("Erfassung:", erfassungChooser);

			builder.append("Genehmigung:", genehmigungChooser);
			builder.append("Stilllegung:", stilllegungChooser);

			builder.append("Aktenzeichen:", aktenzeichenField);
			builder.append("Größe [m²]:", groesseField);
			builder.nextLine();

			builder.appendSeparator("Abfüllfläche");

			builder.append("", neuErstelltCheck);
			builder.append("", eohCheck);
			builder.append("", saniertCheck);
			builder.append("", efCheck);
			builder.append("", unbktCheck);
			builder.append("", svbCheck);

			builder.appendSeparator("Bemerkungen und Anlagenchronologie");
			builder.appendRow("3dlu");
			builder.appendRow("fill:30dlu:grow");
			builder.nextLine(2);

			builder.append(this.tabellenSplit, 7);

			datenAbfuellflaechenTab = builder.getPanel();
			datenAbfuellflaechenTab.setBorder(Paddings.DIALOG);
		}
		return datenAbfuellflaechenTab;
	}

	private JPanel getAbfuellflaecheJgsTab() {
		if (datenAbfuellflaechenJgsTab == null) {

			JScrollPane bemerkungScroller = new JScrollPane(bemerkungArea,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			FormLayout layout = new FormLayout("r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g"// , 10dlu, l:p:g"
			);
			layout.setColumnGroups(new int[][] { { 1, 5 }, { 3, 7 } });
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendSeparator("Ausführung");

			builder.append("Bodenplatte", bodenflaechenAusfBox);

			builder.nextLine();

			builder.append("", seitenwandCheck);
			builder.append("", drainageCheck);

			builder.append("", fuellanzeigerCheck);
			builder.append("", schieberCheck);

			builder.append("", abdeckungCheck);
			builder.append("", leitung_geprueftCheck);

			builder.nextLine();

			builder.appendSeparator("Auffangvorrichtung");

			builder.append("Auffangbehälter", auffangbehBox);
			builder.append("Behältervolumen [m³]", volumenAuffangbehFeld);

			builder.append("Rohrleitung", rohrleitungBox);
			builder.append("Wandhöhe [m]", wandhoeheFeld);

			builder.nextLine();

			builder.appendSeparator("Bemerkungen");
			builder.appendRow("3dlu");
			builder.appendRow("fill:30dlu:grow");
			builder.nextLine(2);

			builder.append(bemerkungScroller, 7);

			datenAbfuellflaechenJgsTab = builder.getPanel();
			datenAbfuellflaechenJgsTab.setBorder(Paddings.DIALOG);
		}
		return datenAbfuellflaechenJgsTab;
	}

	private JPanel getDatenJgsTab() {
		if (datenJgsTab == null) {

			JScrollPane chronoScroller = new JScrollPane(anlagenChronoTabelle,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			FormLayout layout = new FormLayout("r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g"// , 10dlu, l:p:g"
			);
			layout.setColumnGroups(new int[][] { { 1, 5 }, { 3, 7 } });
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendSeparator("Stammdaten");
			builder.append("Baujahr:", baujahrFeld);
			builder.append("Aktenzeichen:", aktenzeichenField);

			builder.append("Lagerfläche [m²]:", lagerflaecheFeld);
			builder.append("Lagervolumen [m³]:", mengeFeld);

			builder.append("Abstand zu Gewässer [m]:", abstandGewFeld);
			builder.append("Gewässername:", gewNameFeld);

			builder.append("Abstand zu Brunnen [m]:", abstandBrunnenFeld);
			builder.append("Tierhaltung:", tierhaltungFeld);

			builder.nextLine();

			builder.append("Inbetriebnahme:", inbetriebnahmeChooser);
			builder.append("Erfassung:", erfassungChooser);

			builder.append("Genehmigung:", genehmigungChooser);
			builder.append("Stilllegung:", stilllegungChooser);

			builder.nextLine();

			builder.appendSeparator("Anlagenchronologie");
			builder.appendRow("3dlu");
			builder.appendRow("fill:30dlu:grow");
			builder.nextLine(2);

			builder.append(chronoScroller, 7);

			datenJgsTab = builder.getPanel();
			datenJgsTab.setBorder(Paddings.DIALOG);
		}
		return datenJgsTab;
	}

	private JPanel getAusfuehrungAbfuellflaechenTab() {
		if (ausfuehrungAbfuellflaechenTab == null) {
			FormLayout layout = new FormLayout("r:p, 3dlu, f:p:g, 10dlu, r:p, 3dlu, f:p:g");
			layout.setColumnGroups(new int[][] { { 1, 5 }, { 3, 7 } });
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendSeparator("Daten");
			builder.append("Bodenflächenausführung:", bodenflaechenAusfBox);
			builder.append("Fugenmaterial:", fugenMaterialFeld);

			builder.append("Dicke [cm]:", dickeFeld);
			builder.append("Niederschlagsschutz:", niederschlagSchutzBox);

			builder.append("Güte:", gueteFeld);
			builder.append("", abscheiderVorhandenCheck);

			builder.appendSeparator("Beschreibung Bodenfläche");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschrBodenflaecheArea), 7);
			builder.nextLine();

			builder.appendSeparator("Beschreibung Fugenmaterial");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschrFugenmaterialArea), 7);
			builder.nextLine();

			builder.appendSeparator("Beschreibung Ableitung / Niederschlagswasser");
			builder.appendRow("3dlu");
			builder.appendRow("fill:25dlu:grow");
			builder.nextLine(2);
			builder.append(new JScrollPane(beschrAblNiederschlArea), 7);

			ausfuehrungAbfuellflaechenTab = builder.getPanel();
			ausfuehrungAbfuellflaechenTab.setBorder(Paddings.DIALOG);
		}
		return ausfuehrungAbfuellflaechenTab;
	}

	private JPanel getSvPruefungTab() {
		if (svPruefungTab == null) {
			FormLayout layout = new FormLayout("f:p:g");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendRow("fill:25dlu:grow");
			builder.append(new JScrollPane(svPruefungTabelle));// ,7);

			svPruefungTab = builder.getPanel();
			svPruefungTab.setBorder(Paddings.DIALOG);
		}
		return svPruefungTab;
	}

	private JPanel getVerwVerfahrenTab() {
		if (verwVerfahrenTab == null) {
			FormLayout layout = new FormLayout("f:p:g");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendRow("fill:25dlu:grow");
			builder.append(new JScrollPane(verwVerfahrenTabelle));// ,7);

			verwVerfahrenTab = builder.getPanel();
			verwVerfahrenTab.setBorder(Paddings.DIALOG);
		}
		return verwVerfahrenTab;
	}

	private JPanel getVerwGebuehrenTab() {
		if (verwGebuehrenTab == null) {
			FormLayout layout = new FormLayout("f:p:g");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			builder.appendRow("fill:25dlu:grow");
			builder.append(new JScrollPane(verwGebuehrenTabelle));// ,7);

			verwGebuehrenTab = builder.getPanel();
			verwGebuehrenTab.setBorder(Paddings.DIALOG);
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
		super(new String[] { "Datum", "Sachverhalt", "Wiedervorlage", "abgeschl." }, false, true);
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
				// .changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
				// HauptFrame.ERROR_COLOR);
			}
			break;
		case 1:
			// Auf 255 Zeichen kürzen, da die Datenbank-Spalte nur 255 Zeichen breit ist
			if (tmp.length() > 255) {
				tmp = tmp.substring(0, 255);
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
					// .changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
					// HauptFrame.ERROR_COLOR);
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

	/*
	 * (non-Javadoc)
	 * @see
	 * de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue
	 * (java.lang.Object, int)
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
	 * Leer, da kein Updaten der Liste nötig/möglich. Die Liste wird direkt mittels
	 * setList "befüllt".
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
		super(new String[] { "Prüfdatum", "Prüfer", "Prüfergebnis", "Nächste Prüfung", "Prfg. abgeschl." }, false,
				true);
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
				// .changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
				// HauptFrame.ERROR_COLOR);
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
					// .changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
					// HauptFrame.ERROR_COLOR);
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

	/*
	 * (non-Javadoc)
	 * @see
	 * de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue
	 * (java.lang.Object, int)
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
	 * Leer, da kein Updaten der Liste nötig/möglich. Die Liste wird direkt mittels
	 * setList "befüllt".
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
		super(new String[] { "Datum", "Maßnahmen der Verwaltung", "Wiedervorlage", "abgeschl." }, false, true);
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
				// .changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
				// HauptFrame.ERROR_COLOR);
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
					// .changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
					// HauptFrame.ERROR_COLOR);
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

	/*
	 * (non-Javadoc)
	 * @see
	 * de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue
	 * (java.lang.Object, int)
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
			tmp = Boolean.valueOf(verf.getWvverwverf());
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
	 * Leer, da kein Updaten der Liste nötig/möglich. Die Liste wird direkt mittels
	 * setList "befüllt".
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
		super(new String[] { "Datum", "Gebührenart", "Betrag", "Abschnitt", "Kassenzeichen" }, false, true);
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
				// .changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
				// HauptFrame.ERROR_COLOR);
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
				tmpWert = ((KommaDouble) newValue).getValue().floatValue();
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
		gebuehr.setBetrag(0.0f);
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

	/*
	 * (non-Javadoc)
	 * @see
	 * de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue
	 * (java.lang.Object, int)
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
			if (gebuehr.getBetrag() != null) {
				tmp = new KommaDouble(gebuehr.getBetrag().doubleValue());
			} else {
				tmp = "";
			}
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
	 * Leer, da kein Updaten der Liste nötig/möglich. Die Liste wird direkt mittels
	 * setList "befüllt".
	 */
	@Override
	public void updateList() {
	}
}
