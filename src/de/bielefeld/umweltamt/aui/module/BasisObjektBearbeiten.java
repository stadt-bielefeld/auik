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
 * $Id: ObjektBearbeiten.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
 *
 * Erstellt am 15.02.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2010/02/23 13:29:11  u633d
 * Objektverknuepfung im Objekt-Panel
 *
 * Revision 1.7  2010/02/02 12:24:00  u633d
 * Fettabscheider-Analysen entfernen
 *
 * Revision 1.6  2010/01/26 09:37:58  u633d
 * Fettabscheider-Analysen
 *
 * Revision 1.5  2009/09/21 11:14:51  u633d
 * GIS oeffnen
 *
 * Revision 1.4  2009/08/27 14:40:23  u633d
 * Anlegen neuer Probepunkte korrigiert
 *
 * Revision 1.3  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg nzungen
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:32  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.43  2006/10/17 09:00:36  u633d
 * Anhang 52
 *
 * Revision 1.42  2006/10/11 11:42:21  u633d
 * Big Font
 *
 * Revision 1.41  2006/09/25 12:27:50  u633d
 * Wäscherei funzt
 *
 * Revision 1.40  2006/09/20 10:03:12  u633d
 * *** empty log message ***
 *
 * Revision 1.39  2006/05/30 12:14:47  u633d
 * *** empty log message ***
 *
 * Revision 1.38  2006/05/23 05:29:41  u633d
 * Objektchronologie für alle Objekte verfügbar gemacht
 *
 * Revision 1.37  2005/10/13 13:00:26  u633d
 * Version vom 13.10.
 *
 * Revision 1.36  2005/09/28 11:11:14  u633d
 * - Version vom 28.9.
 *
 * Revision 1.35  2005/09/07 10:48:22  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.34  2005/08/17 05:46:00  u633d
 * - Anhang 56 erstellt
 *
 * Revision 1.33  2005/08/03 05:53:25  u633d
 * Suev und Anh 40 Panels
 *
 * Revision 1.32  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.plaf.Options;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.ModulManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Lage;
import de.bielefeld.umweltamt.aui.module.objektpanels.AbaPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.AnfallstellePanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh40Panel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh40Panel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh49AnalysenPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh49AbfuhrenPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh49DetailsPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh49Panel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh49VerwaltungsverfahrenPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh50Panel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh52Panel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh53Panel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh55Panel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh56Panel;
import de.bielefeld.umweltamt.aui.module.objektpanels.BWKPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.BasisPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.ChronoPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.EinleitungsstellePanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.FotoPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.GenehmigungPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.ProbepktAuswPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.ProbepunktPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.SuevPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.VawsPanel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Modul um Objekte zu bearbeiten.
 * @author David Klotz
 */
public class BasisObjektBearbeiten extends AbstractModul {

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    // Widgets für Registerbereich
    private JPanel topPanel;
    private JTabbedPane tabbedPane;
    private JLabel headerLabel;

    // Tabs
    private BasisPanel basisTab;
    private ProbepunktPanel probepunktTab;
    private ProbepktAuswPanel probeauswTab;
    private BWKPanel bwkTab;
    private Anh50Panel zahnarztTab;
    private SuevPanel suevTab;
    private AbaPanel abaTab;
    private Anh40Panel anhang40Tab;
    private Anh49Panel anhang49Tab;
    private Anh49AbfuhrenPanel anh49abfuhrTab;
    private Anh49DetailsPanel anh49detailTab;
    private Anh49AnalysenPanel anh49analyseTab;
    private Anh49VerwaltungsverfahrenPanel anh49VerwaltungsverfahrenTab;
    private Anh52Panel anhang52Tab;
    private Anh53Panel anhang53Tab;
    private Anh55Panel anhang55Tab;
    private Anh56Panel anhang56Tab;
    private ChronoPanel chronoTab;
    private FotoPanel fotoTab;
    private GenehmigungPanel genehmigungTab;
    private VawsPanel vawsTab;
    private EinleitungsstellePanel einleitungsstelleTab;
    private AnfallstellePanel anfallstelleTab;

    // Daten
    private Objekt objekt;
    private boolean isNew = true;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
     */
    @Override
    public Icon getIcon() {
        return super.getIcon("edit32.png");
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Objekt neu / bearbeiten";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "m_objekt_bearbeiten";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    @Override
    public String getCategory() {
        return "Betriebe";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    @Override
    public JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel(new BorderLayout());

            panel.add(getTopPanel(), BorderLayout.NORTH);

            panel.add(getTabbedPane(), BorderLayout.CENTER);
        }
        return panel;
    }

    public HauptFrame getFrame() {
        return frame;
    }

    public ModulManager getManager() {
        return manager;
    }

    public Objekt getObjekt() {
        return objekt;
    }

    public void setObjekt(Objekt objekt) {
        this.objekt = objekt;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }



    private JPanel getTopPanel() {
        if (topPanel == null) {
            FormLayout layout = new FormLayout("100dlu:g");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            builder.setDefaultDialogBorder();

            builder.append(getHeaderLabel());

            topPanel = builder.getPanel();
        }

        return topPanel;
    }

    private JLabel getHeaderLabel() {
        if (headerLabel == null) {
            headerLabel = new JLabel(" ", JLabel.CENTER);
            headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        }
        return headerLabel;
    }

    public BasisPanel getBasisTab() {
        if (basisTab == null) {
            basisTab = new BasisPanel(this);
        }
        return basisTab;
    }

    public ProbepunktPanel getProbepunktTab() {
        if (probepunktTab == null) {
            probepunktTab = new ProbepunktPanel(this);
        }
        return probepunktTab;
    }

    public ProbepktAuswPanel getProbepktAuswTab() {
        if (probeauswTab == null) {
            probeauswTab = new ProbepktAuswPanel(this);
        }
        return probeauswTab;
    }

    public BWKPanel getBWKTab() {
        if (bwkTab == null) {
            bwkTab = new BWKPanel(this);
        }
        return bwkTab;
    }

    public Anh50Panel getZahnarztTab() {
        if (zahnarztTab == null) {
            zahnarztTab = new Anh50Panel(this);
        }
        return zahnarztTab;
    }

    public Anh49Panel getAnhang49Tab() {
        if (anhang49Tab == null) {
            anhang49Tab = new Anh49Panel(this);
        }
        return anhang49Tab;
    }

    public Anh49AnalysenPanel getAnh49AnalyseTab() {
        if (anh49analyseTab == null) {
        	anh49analyseTab = new Anh49AnalysenPanel(this);
        }
        return anh49analyseTab;
    }

    public Anh49DetailsPanel getAnh49DetailTab() {
        if (anh49detailTab == null) {
            anh49detailTab = new Anh49DetailsPanel(this);
        }
        return anh49detailTab;
    }

    public Anh49AbfuhrenPanel getAnh49AbfuhrTab() {
        if (anh49abfuhrTab == null) {
            anh49abfuhrTab = new Anh49AbfuhrenPanel(this);
        }
        return anh49abfuhrTab;
    }

    public Anh49VerwaltungsverfahrenPanel getAnh49VerwaltungsverfahrenTab() {
        if (anh49VerwaltungsverfahrenTab == null) {
            anh49VerwaltungsverfahrenTab =
                new Anh49VerwaltungsverfahrenPanel();
        }
        return anh49VerwaltungsverfahrenTab;
    }

    public SuevPanel getSuevTab() {
        if (suevTab == null) {
            suevTab = new SuevPanel(this);
        }
        return suevTab;
    }

    public AbaPanel getAbaTab() {
        if (abaTab == null) {
        	abaTab = new AbaPanel(this);
        }
        return abaTab;
    }

    public Anh40Panel getAnh40Tab() {
        if (anhang40Tab == null) {
            anhang40Tab = new Anh40Panel(this);
        }
        return anhang40Tab;
    }

    public Anh55Panel getAnh55Tab() {
        if (anhang55Tab == null) {
            anhang55Tab = new Anh55Panel(this);
        }
        return anhang55Tab;
    }

    public Anh56Panel getAnh56Tab() {
        if (anhang56Tab == null) {
            anhang56Tab = new Anh56Panel(this);
        }
        return anhang56Tab;
    }

    public Anh52Panel getAnh52Tab() {
        if (anhang52Tab == null) {
            anhang52Tab = new Anh52Panel(this);
        }
        return anhang52Tab;
    }

    public Anh53Panel getAnh53Tab() {
        if (anhang53Tab == null) {
            anhang53Tab = new Anh53Panel(this);
        }
        return anhang53Tab;
    }

    public ChronoPanel getChronoTab() {
        if (chronoTab == null) {
            chronoTab = new ChronoPanel(this);
        }
        return chronoTab;
    }

    public FotoPanel getFotoTab(){
        fotoTab = new FotoPanel(this);

        return fotoTab;
    }

    public GenehmigungPanel getGenehmigungTab() {
        if (genehmigungTab == null) {
            genehmigungTab = new GenehmigungPanel(this);
        }
        return genehmigungTab;
    }

    public VawsPanel getVawsTab() {
        if (vawsTab == null) {
            vawsTab = new VawsPanel(this);
        }
        return vawsTab;
    }

    public EinleitungsstellePanel getEinleitungsstelleTab() {
        if (einleitungsstelleTab == null) {
        	einleitungsstelleTab = new EinleitungsstellePanel(this);
        }
        return einleitungsstelleTab;
    }
    
    public AnfallstellePanel getAnfallstelleTab() {
    	if (anfallstelleTab == null) {
    		anfallstelleTab = new AnfallstellePanel(this);
    	}
    	return anfallstelleTab;
    }


    //Erzeuge einen Registerbereich
    public JTabbedPane getTabbedPane() {
        if (tabbedPane == null) {
            tabbedPane = new JTabbedPane();

            tabbedPane.putClientProperty(Options.NO_CONTENT_BORDER_KEY, Boolean.TRUE);
            tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

            tabbedPane.addTab(getBasisTab().getName(), getBasisTab());
        }
        return tabbedPane;
    }

    @Override
    public void show() {

        super.show();

        if (manager.getSettingsManager().getSetting("auik.imc.edit_object") != null) {
            isNew = false;
            objekt = Objekt.findById(new Integer(
                manager.getSettingsManager().getIntSetting("auik.imc.edit_object")));
            manager.getSettingsManager().removeSetting("auik.imc.edit_object");
        } else {
            isNew = true;
            objekt = new Objekt();
            if (manager.getSettingsManager().getSetting("auik.imc.use_standort") != null) {
                Adresse sta = Adresse.findById(new Integer(manager.getSettingsManager().getIntSetting("auik.imc.use_standort")));
                log.debug("Standort: " + sta.getStrasse() + " " + sta.getHausnr() + ", " +sta.getId());
                Lage lage = Lage.findById(new Integer(manager.getSettingsManager().getIntSetting("auik.imc.use_lage")));
                log.debug("Creating new Objekt " + lage + sta);
                objekt.setAdresseByStandortid(sta);
                objekt.setLage(lage);
                manager.getSettingsManager().removeSetting("auik.imc.use_standort");
                manager.getSettingsManager().removeSetting("auik.imc.use_lage");
            }
            if (manager.getSettingsManager().getSetting("auik.imc.use_betreiber") != null) {
                Adresse betr = Adresse.findById(new Integer(manager.getSettingsManager().getIntSetting("auik.imc.use_betreiber")));
                objekt.setAdresseByBetreiberid(betr);
                manager.getSettingsManager().removeSetting("auik.imc.use_betreiber");
            }
        }

        fillForm();
    }

    public void fillForm() {
        enableAll(false);
        clearAll();

        SwingWorkerVariant worker = new SwingWorkerVariant(getBasisTab()) {

            @Override
            protected void doNonUILogic() throws RuntimeException {
                getBasisTab().fetchFormData();

                // Daten für verschiedene Objektarten holen
                if (objekt.getObjektarten() != null) {
                    switch (objekt.getObjektarten().getId()) {
                        case DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE:
                        case DatabaseConstants.BASIS_OBJEKTART_ID_PROBEPUNKT:
                            getChronoTab().fetchFormData();
                            getProbepunktTab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_BWK:
                        case DatabaseConstants.BASIS_OBJEKTART_ID_BHKW:
                            getChronoTab().fetchFormData();
                            getBWKTab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_50:
                            getChronoTab().fetchFormData();
                            getZahnarztTab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ABA:
                            getChronoTab().fetchFormData();
                            getAbaTab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_49:
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER:
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER34:
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER_ES:
                            getChronoTab().fetchFormData();
                            getAnhang49Tab().fetchFormData();
                            getAnh49DetailTab().setFachdaten(
                                getAnhang49Tab().getFachdaten());
                            getAnh49AnalyseTab().setFachdaten(
                                getAnhang49Tab().getFachdaten());
                            getAnh49VerwaltungsverfahrenTab().setFachdaten(
                                getAnhang49Tab().getFachdaten());
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER:
                            getChronoTab().fetchFormData();
                            getAnhang49Tab().fetchFormData();
                            getAnh49DetailTab().setFachdaten(
                                getAnhang49Tab().getFachdaten());
                            getAnh49AbfuhrTab().setFachdaten(
                                    getAnhang49Tab().getFachdaten());
                            getAnh49VerwaltungsverfahrenTab().setFachdaten(
                                getAnhang49Tab().getFachdaten());
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_SUEV:
                            getChronoTab().fetchFormData();
                            getSuevTab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_40:
                            getChronoTab().fetchFormData();
                            getAnh40Tab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_55:
                            getChronoTab().fetchFormData();
                            getAnh55Tab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_56:
                            getChronoTab().fetchFormData();
                            getAnh56Tab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_52:
                            getChronoTab().fetchFormData();
                            getAnh52Tab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_EINLEITUNGSTELLE:
                            getChronoTab().fetchFormData();
                            getEinleitungsstelleTab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ANFALLSTELLE:
                        	getChronoTab().fetchFormData();
                        	getAnfallstelleTab().fetchFormData();
                        	break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_KLEIN:
                        case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_GROSS:
                            getChronoTab().fetchFormData();
                            getAnh53Tab().fetchFormData();
                            break;
                        case DatabaseConstants.BASIS_OBJEKTART_ID_GENEHMIGUNG:
                            getChronoTab().fetchFormData();
                            getGenehmigungTab().fetchFormData();
                            break;
                        default:
                            log.debug("Unknown Objektart: "
                                + objekt.getObjektarten());
                            if (objekt.getObjektarten().getAbteilung().equals(
                                DatabaseConstants.BASIS_OBJEKTART_ABTEILUNG_34)) {
                                getChronoTab().fetchFormData();
                                getVawsTab().fetchFormData();
                                getFotoTab();
                            } else if (objekt.getObjektarten().getAbteilung().equals(
                                DatabaseConstants.BASIS_OBJEKTART_ABTEILUNG_33)) {
                                getChronoTab().fetchFormData();
                            }
                    }
                }
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                getBasisTab().updateForm();

                if (isNew) {
                    log.debug("Neues Objekt");
                    getHeaderLabel().setForeground(Color.RED);
                    getHeaderLabel().setText("Neues Objekt");
                } else {
                    log.debug("Bearbeite Objekt: " + objekt);
                    getHeaderLabel().setForeground(UIManager.getColor("Label.foreground"));
                    getHeaderLabel().setText(DatabaseQuery.getStandortString(objekt.getAdresseByStandortid()) +
                    		"; " + objekt.getAdresseByStandortid()+"; "+objekt.getObjektarten().getObjektart());
                }

                if (objekt != null) {
                    // Alle vorhandenen Tabs entfernen
                    getTabbedPane().removeAll();
                    // Den "Objekt"-Tab anzeigen
                    getTabbedPane().addTab(getBasisTab().getName(), getBasisTab());

                    // Einzelne Objektarten behandeln
                    if (objekt.getObjektarten() != null) {
                        switch (objekt.getObjektarten().getId()) {
                            case DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE:
                            case DatabaseConstants.BASIS_OBJEKTART_ID_PROBEPUNKT:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getProbepunktTab().getName(), getProbepunktTab());
                                getTabbedPane().addTab(getFotoTab().getName(), getFotoTab());
                                getTabbedPane().addTab(getProbepktAuswTab().getName(), getProbepktAuswTab());
                                getChronoTab().updateForm();
                                getProbepunktTab().updateForm();
                                getTabbedPane().setSelectedComponent(getProbepunktTab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_BWK:
                            case DatabaseConstants.BASIS_OBJEKTART_ID_BHKW:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getBWKTab().getName(), getBWKTab());
                                getChronoTab().updateForm();
                                getBWKTab().updateForm();
                                getTabbedPane().setSelectedComponent(getBWKTab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_50:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getZahnarztTab().getName(), getZahnarztTab());
                                getChronoTab().updateForm();
                                getZahnarztTab().updateForm();
                                getTabbedPane().setSelectedComponent(getZahnarztTab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ABA:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getAbaTab().getName(), getAbaTab());
                                getChronoTab().updateForm();
                                getAbaTab().updateForm();
                                getTabbedPane().setSelectedComponent(getAbaTab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_49:
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER:
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER34:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getAnhang49Tab().getName(), getAnhang49Tab());
                                getTabbedPane().addTab(getAnh49DetailTab().getName(), getAnh49DetailTab());
                                getTabbedPane().addTab(getAnh49AnalyseTab().getName(), getAnh49AnalyseTab());
                                getTabbedPane().addTab(getAnh49VerwaltungsverfahrenTab().getName(), getAnh49VerwaltungsverfahrenTab());
                                getChronoTab().updateForm();
                                getAnhang49Tab().updateForm();
                                getAnh49DetailTab().updateForm();
                                getAnh49AnalyseTab().updateForm();
                                getAnh49VerwaltungsverfahrenTab().updateForm();
                                getTabbedPane().setSelectedComponent(getAnhang49Tab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab("Fettabscheider", getAnhang49Tab());
                                getTabbedPane().addTab(getAnh49DetailTab().getName(), getAnh49DetailTab());
                                getTabbedPane().addTab(getAnh49AbfuhrTab().getName(), getAnh49AbfuhrTab());
                                getTabbedPane().addTab(getAnh49VerwaltungsverfahrenTab().getName(), getAnh49VerwaltungsverfahrenTab());
                                getChronoTab().updateForm();
                                getAnhang49Tab().updateForm();
                                getAnh49DetailTab().updateForm();
                                getAnh49AbfuhrTab().updateForm();
                                getAnh49VerwaltungsverfahrenTab().updateForm();
                                getTabbedPane().setSelectedComponent(getAnhang49Tab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_SUEV:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getSuevTab().getName(), getSuevTab());
                                getChronoTab().updateForm();
                                getSuevTab().updateForm();
                                getTabbedPane().setSelectedComponent(getSuevTab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_40:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getAnh40Tab().getName(), getAnh40Tab());
                                getChronoTab().updateForm();
                                getAnh40Tab().updateForm();
                                getTabbedPane().setSelectedComponent(getAnh40Tab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_55:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getAnh55Tab().getName(), getAnh55Tab());
                                getChronoTab().updateForm();
                                getAnh55Tab().updateForm();
                                getTabbedPane().setSelectedComponent(getAnh55Tab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_56:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getAnh56Tab().getName(), getAnh56Tab());
                                getChronoTab().updateForm();
                                getAnh56Tab().updateForm();
                                getTabbedPane().setSelectedComponent(getAnh56Tab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_52:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getAnh52Tab().getName(), getAnh52Tab());
                                getChronoTab().updateForm();
                                getAnh52Tab().updateForm();
                                getTabbedPane().setSelectedComponent(getAnh52Tab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_KLEIN:
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_GROSS:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getAnh53Tab().getName(), getAnh53Tab());
                                getChronoTab().updateForm();
                                getAnh53Tab().updateForm();
                                getTabbedPane().setSelectedComponent(getAnh53Tab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_GENEHMIGUNG:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getGenehmigungTab().getName(), getGenehmigungTab());
                                getChronoTab().updateForm();
                                getGenehmigungTab().updateForm();
                                getTabbedPane().setSelectedComponent(getGenehmigungTab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_EINLEITUNGSTELLE:
                                getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                getTabbedPane().addTab(getEinleitungsstelleTab().getName(), getEinleitungsstelleTab());
                                getChronoTab().updateForm();
                                getEinleitungsstelleTab().updateForm();
                                getTabbedPane().setSelectedComponent(getEinleitungsstelleTab());
                                break;
                            case DatabaseConstants.BASIS_OBJEKTART_ID_ANFALLSTELLE:
                            	getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            	getTabbedPane().addTab(getAnfallstelleTab().getName(), getAnfallstelleTab());
                            	getChronoTab().updateForm();
                            	getAnfallstelleTab().updateForm();
                            	getTabbedPane().setSelectedComponent(getAnfallstelleTab());
                            	break;
                            default:
                                log.debug("Unknown Objektart: "
                                    + objekt.getObjektarten());
                                if (objekt.getObjektarten().getAbteilung().equals(
                                    DatabaseConstants.BASIS_OBJEKTART_ABTEILUNG_34)) {
                                    getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                    getTabbedPane().addTab(getVawsTab().getName(), getVawsTab());
                                    getTabbedPane().addTab(getFotoTab().getName(), getFotoTab());
                                    getChronoTab().updateForm();
                                    getVawsTab().updateForm();
                                    getTabbedPane().setSelectedComponent(getVawsTab());
                                } else if (objekt.getObjektarten().getAbteilung().equals(
                                    DatabaseConstants.BASIS_OBJEKTART_ABTEILUNG_33)) {
                                    getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                                    getChronoTab().updateForm();
                                    getTabbedPane().setSelectedComponent(getBasisTab());
                                }
                        }
                    }
                }

                enableAll(true);
            }
        };
        worker.start();
    }

    private void clearAll() {
        log.debug("Leere alle Felder");

        getBasisTab().clearForm();

        if (objekt.getObjektarten() != null) {
            switch (objekt.getObjektarten().getId()) {
                case DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE:
                case DatabaseConstants.BASIS_OBJEKTART_ID_PROBEPUNKT:
                    getProbepunktTab().clearForm();
                    getProbepktAuswTab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_BWK:
                case DatabaseConstants.BASIS_OBJEKTART_ID_BHKW:
                    getBWKTab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_50:
                    getZahnarztTab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ABA:
                    getAbaTab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_49:
                case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER:
                case DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER:
                case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER34:
                    getAnhang49Tab().clearForm();
                    getAnh49DetailTab().clearForm();
                    getAnh49AnalyseTab().clearForm();
                    getAnh49VerwaltungsverfahrenTab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_SUEV:
                    getSuevTab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_40:
                    getAnh40Tab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_55:
                    getAnh55Tab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_GENEHMIGUNG:
                    getGenehmigungTab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_56:
                    getAnh56Tab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_52:
                    getAnh52Tab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_EINLEITUNGSTELLE:
                    getEinleitungsstelleTab().clearForm();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANFALLSTELLE:
                	getAnfallstelleTab().clearForm();
                	break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_GROSS:
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_KLEIN:
                    getAnh53Tab().clearForm();
                    break;
                default:
                    log.debug("Unknown Objektart: "
                        + objekt.getObjektarten());
            }
        }
    }

    private void enableAll(boolean enabled) {

        getBasisTab().enableAll(enabled);
        if (objekt.getObjektarten() != null) {

            switch (objekt.getObjektarten().getId()) {
                case DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE:
                case DatabaseConstants.BASIS_OBJEKTART_ID_PROBEPUNKT:
                    getProbepunktTab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_BWK:
                case DatabaseConstants.BASIS_OBJEKTART_ID_BHKW:
                    getBWKTab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_50:
                    getZahnarztTab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ABA:
                    getAbaTab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_49:
                case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER:
                case DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER:
                case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER34:
                    getAnhang49Tab().enableAll(enabled);
                    getAnh49DetailTab().enableAll(enabled);
                    getAnh49AnalyseTab().enableAll(enabled);
                    getAnh49VerwaltungsverfahrenTab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_SUEV:
                    getSuevTab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_40:
                    getAnh40Tab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_55:
                    getAnh55Tab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_56:
                    getAnh56Tab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_52:
                    getAnh52Tab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_EINLEITUNGSTELLE:
                    getEinleitungsstelleTab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANFALLSTELLE:
                	getAnfallstelleTab().enableAll(enabled);
                	break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_GROSS:
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_KLEIN:
                    getAnh53Tab().enableAll(enabled);
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_GENEHMIGUNG:
                    getGenehmigungTab().enableAll(enabled);
                    break;
                default:
                    log.debug("Unknown Objektart: "
                        + objekt.getObjektarten());
            }
        }
    }

    /**
     * Wird benutzt, um bestimmte Objektarten beim Speichern besonders zu behandeln.
     * Wird aufgerufen, wenn das Objekt bereits gespeichert wurde.
     * @param session Die Hibernate-Session.
     * der einen kompletten Rollback der Speicher-Transaktion erfordert.
     */
    public void completeObjekt() {
        // TODO: Fachdaten beim Objektart-Wechsel löschen?
        if (objekt.getObjektarten() != null) {
            // Verschiedene Fachdaten bei neuem Objekt neu anlegen
            switch (objekt.getObjektarten().getId()) {
                case DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE:
                case DatabaseConstants.BASIS_OBJEKTART_ID_PROBEPUNKT:
                    getProbepunktTab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_BWK:
                case DatabaseConstants.BASIS_OBJEKTART_ID_BHKW:
                    getBWKTab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_50:
                    getZahnarztTab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ABA:
                    getAbaTab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_49:
                case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER:
                case DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER:
                case DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER34:
                    getAnhang49Tab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_SUEV:
                    getSuevTab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_40:
                    getAnh40Tab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_55:
                    getAnh55Tab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_GENEHMIGUNG:
                    getGenehmigungTab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_56:
                    getAnh56Tab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_52:
                    getAnh52Tab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_EINLEITUNGSTELLE:
                    getEinleitungsstelleTab().completeObjekt();
                    break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANFALLSTELLE:
                	getAnfallstelleTab().completeObjekt();
                	break;
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_GROSS:
                case DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_KLEIN:
                    getAnh53Tab().completeObjekt();
                    break;
                default:
                    log.debug("Unknown Objektart: "
                        + objekt.getObjektarten());
            }
        }
    }
}

