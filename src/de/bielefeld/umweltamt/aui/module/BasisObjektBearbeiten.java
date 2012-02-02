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
 * $Id: BasisObjektBearbeiten.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
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
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
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


import org.hibernate.HibernateException;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.plaf.Options;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.ModulManager;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh49AnalysenPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh49DetailsPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.Anh40Panel;
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
import de.bielefeld.umweltamt.aui.module.objektpanels.GenehmigungPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.ProbepktAuswPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.ProbepunktPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.SuevPanel;
import de.bielefeld.umweltamt.aui.module.objektpanels.UebergabePanel;
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
    private Anh40Panel anhang40Tab;
    private Anh49Panel anhang49Tab;
    private Anh49DetailsPanel anh49detailTab;
    private Anh49AnalysenPanel anh49analyseTab;
    private Anh49VerwaltungsverfahrenPanel anh49VerwaltungsverfahrenTab;
    private Anh52Panel anhang52Tab;
    private Anh53Panel anhang53Tab;
    private Anh55Panel anhang55Tab;
    private Anh56Panel anhang56Tab;
    private ChronoPanel chronoTab;
    private UebergabePanel uebergabeTab;
    private GenehmigungPanel genehmigungTab;
    private VawsPanel vawsTab;

    // Daten
    private BasisObjekt objekt;
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

    public BasisObjekt getObjekt() {
        return objekt;
    }

    public void setObjekt(BasisObjekt objekt) {
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

    public Anh49DetailsPanel getAnh49DetailTab() {
        if (anh49detailTab == null) {
            anh49detailTab = new Anh49DetailsPanel(this);
        }
        return anh49detailTab;
    }

    public Anh49AnalysenPanel getAnh49AnalyseTab() {
        if (anh49analyseTab == null) {
            anh49analyseTab = new Anh49AnalysenPanel(this);
        }
        return anh49analyseTab;
    }

    public Anh49VerwaltungsverfahrenPanel getAnh49VerwaltungsverfahrenTab() {
        if (anh49VerwaltungsverfahrenTab == null) {
            anh49VerwaltungsverfahrenTab =
                new Anh49VerwaltungsverfahrenPanel(this);
        }
        return anh49VerwaltungsverfahrenTab;
    }

    public SuevPanel getSuevTab() {
        if (suevTab == null) {
            suevTab = new SuevPanel(this);
        }
        return suevTab;
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

    public UebergabePanel getUebergabeTab() {
        if (uebergabeTab == null) {
            uebergabeTab = new UebergabePanel(this);
        }
        return uebergabeTab;
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
            objekt = BasisObjekt.getObjekt(new Integer(manager.getSettingsManager().getIntSetting("auik.imc.edit_object")));
            manager.getSettingsManager().removeSetting("auik.imc.edit_object");
        } else {
            isNew = true;
            objekt = new BasisObjekt();
            if (manager.getSettingsManager().getSetting("auik.imc.use_standort") != null) {
                BasisStandort sta = BasisStandort.getStandort(new Integer(manager.getSettingsManager().getIntSetting("auik.imc.use_standort")));
                objekt.setBasisStandort(sta);
                manager.getSettingsManager().removeSetting("auik.imc.use_standort");
            }
            if (manager.getSettingsManager().getSetting("auik.imc.use_betreiber") != null) {
                BasisBetreiber betr = BasisBetreiber.getBetreiber(new Integer(manager.getSettingsManager().getIntSetting("auik.imc.use_betreiber")));
                objekt.setBasisBetreiber(betr);
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
                if (objekt.getBasisObjektarten() != null) {
                    if (objekt.getBasisObjektarten().isProbepunkt()) {
                        getChronoTab().fetchFormData();
                        getProbepunktTab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isBWK()) {
                        getChronoTab().fetchFormData();
                        getBWKTab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isAnh50()) {
                        getChronoTab().fetchFormData();
                        getZahnarztTab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isAnh49()) {
                        getChronoTab().fetchFormData();
                        getAnhang49Tab().fetchFormData();
                        getAnh49DetailTab().setFachdaten(getAnhang49Tab().getFachdaten());

                        if (objekt.getBasisObjektarten().isFettabscheider()== true)
                        {

                        }

                        if (objekt.getBasisObjektarten().isFettabscheider() == false)
                        {
                            getAnh49AnalyseTab().setFachdaten(getAnhang49Tab().getFachdaten());
                        }

                        getAnh49VerwaltungsverfahrenTab().setFachdaten(getAnhang49Tab().getFachdaten());

                    } else if (objekt.getBasisObjektarten().isSuev()) {
                        getChronoTab().fetchFormData();
                        getSuevTab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isAnh40()) {
                        getChronoTab().fetchFormData();
                        getAnh40Tab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isAnh55()) {
                        getChronoTab().fetchFormData();
                        getAnh55Tab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isAnh56()) {
                        getChronoTab().fetchFormData();
                        getAnh56Tab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isAnh52()) {
                        getChronoTab().fetchFormData();
                        getAnh52Tab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isAnh53Kl()) {
                        getChronoTab().fetchFormData();
                        getAnh53Tab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isAnh53Gr()) {
                        getChronoTab().fetchFormData();
                        getAnh53Tab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isUebergabestelle()) {
                        getChronoTab().fetchFormData();
                        getUebergabeTab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().isGenehmigung()) {
                        getChronoTab().fetchFormData();
                        getGenehmigungTab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().abteilungIs34()) {
                        getChronoTab().fetchFormData();
                        getVawsTab().fetchFormData();
                    } else if (objekt.getBasisObjektarten().abteilungIs33()) {
                        getChronoTab().fetchFormData();
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
                    getHeaderLabel().setText(objekt.getBasisStandort()+"; "+objekt.getBasisBetreiber()+"; "+objekt.getBasisObjektarten().getObjektart());
                }

                if (objekt != null) {
                    // Alle vorhandenen Tabs entfernen
                    getTabbedPane().removeAll();
                    // Den "Objekt"-Tab anzeigen
                    getTabbedPane().addTab(getBasisTab().getName(), getBasisTab());

                    // Einzelne Objektarten behandeln
                    if (objekt.getBasisObjektarten() != null) {
                        if (objekt.getBasisObjektarten().isProbepunkt()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getProbepunktTab().getName(), getProbepunktTab());
                            getTabbedPane().addTab(getProbepktAuswTab().getName(), getProbepktAuswTab());
                            getChronoTab().updateForm();
                            getProbepunktTab().updateForm();
                            getTabbedPane().setSelectedComponent(getProbepunktTab());
                        } else if (objekt.getBasisObjektarten().isBWK()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getBWKTab().getName(), getBWKTab());
                            getChronoTab().updateForm();
                            getBWKTab().updateForm();
                            getTabbedPane().setSelectedComponent(getBWKTab());
                        } else if (objekt.getBasisObjektarten().isAnh50()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getZahnarztTab().getName(), getZahnarztTab());
                            getChronoTab().updateForm();
                            getZahnarztTab().updateForm();
                            getTabbedPane().setSelectedComponent(getZahnarztTab());
                        } else if (objekt.getBasisObjektarten().isAnh49()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());

                            if (objekt.getBasisObjektarten().isFettabscheider() == false)
                            {
                                getTabbedPane().addTab(getAnhang49Tab().getName(), getAnhang49Tab());
                            }

                            if ( objekt.getBasisObjektarten().isFettabscheider()== true)
                            {
                                getTabbedPane().addTab("Fettabscheider", getAnhang49Tab());
                            }

                            getTabbedPane().addTab(getAnh49DetailTab().getName(), getAnh49DetailTab());

                            if (objekt.getBasisObjektarten().isFettabscheider() == false)
                            {
                                getTabbedPane().addTab(getAnh49AnalyseTab().getName(), getAnh49AnalyseTab());
                            }

                            getTabbedPane().addTab(getAnh49VerwaltungsverfahrenTab().getName(), getAnh49VerwaltungsverfahrenTab());

                            if ( objekt.getBasisObjektarten().isFettabscheider()== true)
                            {

                            }

                            getChronoTab().updateForm();
                            getAnhang49Tab().updateForm();
                            getAnh49DetailTab().updateForm();
                            if ( objekt.getBasisObjektarten().isFettabscheider()== true)
                            {

                            }
                            if (objekt.getBasisObjektarten().isFettabscheider() == false)
                            {
                                getAnh49AnalyseTab().updateForm();
                            }
                            getAnh49VerwaltungsverfahrenTab().updateForm();
                            getTabbedPane().setSelectedComponent(getAnhang49Tab());
                        } else if (objekt.getBasisObjektarten().isSuev()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getSuevTab().getName(), getSuevTab());
                            getChronoTab().updateForm();
                            getSuevTab().updateForm();
                            getTabbedPane().setSelectedComponent(getSuevTab());
                        } else if (objekt.getBasisObjektarten().isAnh40()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getAnh40Tab().getName(), getAnh40Tab());
                            getChronoTab().updateForm();
                            getAnh40Tab().updateForm();
                            getTabbedPane().setSelectedComponent(getAnh40Tab());
                        } else if (objekt.getBasisObjektarten().isAnh55()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getAnh55Tab().getName(), getAnh55Tab());
                            getChronoTab().updateForm();
                            getAnh55Tab().updateForm();
                            getTabbedPane().setSelectedComponent(getAnh55Tab());
                        } else if (objekt.getBasisObjektarten().isAnh56()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getAnh56Tab().getName(), getAnh56Tab());
                            getChronoTab().updateForm();
                            getAnh56Tab().updateForm();
                            getTabbedPane().setSelectedComponent(getAnh56Tab());
                        } else if (objekt.getBasisObjektarten().isAnh52()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getAnh52Tab().getName(), getAnh52Tab());
                            getChronoTab().updateForm();
                            getAnh52Tab().updateForm();
                            getTabbedPane().setSelectedComponent(getAnh52Tab());
                        } else if (objekt.getBasisObjektarten().isAnh53Kl()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getAnh53Tab().getName(), getAnh53Tab());
                            getChronoTab().updateForm();
                            getAnh53Tab().updateForm();
                            getTabbedPane().setSelectedComponent(getAnh53Tab());
                        } else if (objekt.getBasisObjektarten().isAnh53Gr()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getAnh53Tab().getName(), getAnh53Tab());
                            getChronoTab().updateForm();
                            getAnh53Tab().updateForm();
                            getTabbedPane().setSelectedComponent(getAnh53Tab());
                        } else if (objekt.getBasisObjektarten().isUebergabestelle()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getUebergabeTab().getName(), getUebergabeTab());
                            getChronoTab().updateForm();
                            getUebergabeTab().updateForm();
                            getTabbedPane().setSelectedComponent(getUebergabeTab());
                        } else if (objekt.getBasisObjektarten().isGenehmigung()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getGenehmigungTab().getName(), getGenehmigungTab());
                            getChronoTab().updateForm();
                            getGenehmigungTab().updateForm();
                            getTabbedPane().setSelectedComponent(getGenehmigungTab());
                        } else if (objekt.getBasisObjektarten().abteilungIs34()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getTabbedPane().addTab(getVawsTab().getName(), getVawsTab());
                            getChronoTab().updateForm();
                            getVawsTab().updateForm();
                            getTabbedPane().setSelectedComponent(getVawsTab());
                        } else if (objekt.getBasisObjektarten().abteilungIs33()) {
                            getTabbedPane().addTab(getChronoTab().getName(), getChronoTab());
                            getChronoTab().updateForm();
                            getTabbedPane().setSelectedComponent(getBasisTab());
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

        if (objekt.getBasisObjektarten() != null) {
            if (objekt.getBasisObjektarten().isProbepunkt()) {
                getProbepunktTab().clearForm();
                getProbepktAuswTab().clearForm();
            } else if (objekt.getBasisObjektarten().isBWK()) {
                getBWKTab().clearForm();
            } else if (objekt.getBasisObjektarten().isAnh50()) {
                getZahnarztTab().clearForm();
            } else if (objekt.getBasisObjektarten().isAnh49()) {
                getAnhang49Tab().clearForm();
                getAnh49DetailTab().clearForm();
                getAnh49AnalyseTab().clearForm();
                getAnh49VerwaltungsverfahrenTab().clearForm();
            } else if (objekt.getBasisObjektarten().isSuev()) {
                getSuevTab().clearForm();
            } else if (objekt.getBasisObjektarten().isAnh40()) {
                getAnh40Tab().clearForm();
            } else if (objekt.getBasisObjektarten().isAnh55()) {
                getAnh55Tab().clearForm();
            } else if (objekt.getBasisObjektarten().isUebergabestelle()) {
                getUebergabeTab().clearForm();
            } else if (objekt.getBasisObjektarten().isGenehmigung()) {
                getGenehmigungTab().clearForm();
            } else if (objekt.getBasisObjektarten().isAnh56()) {
                getAnh56Tab().clearForm();
            } else if (objekt.getBasisObjektarten().isAnh52()) {
                getAnh52Tab().clearForm();
            } else if (objekt.getBasisObjektarten().isAnh53Gr()) {
                getAnh53Tab().clearForm();
            } else if (objekt.getBasisObjektarten().isAnh53Kl()) {
                getAnh53Tab().clearForm();
            }
        }
    }

    private void enableAll(boolean enabled) {

        getBasisTab().enableAll(enabled);

        if (objekt.getBasisObjektarten() != null) {
            if (objekt.getBasisObjektarten().isProbepunkt()) {
                getProbepunktTab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isBWK()) {
                getBWKTab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isAnh50()) {
                getZahnarztTab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isAnh49()) {
                getAnhang49Tab().enableAll(enabled);
                getAnh49DetailTab().enableAll(enabled);
                getAnh49AnalyseTab().enableAll(enabled);
                getAnh49VerwaltungsverfahrenTab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isSuev()) {
                getSuevTab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isAnh40()) {
                getAnh40Tab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isAnh55()) {
                getAnh55Tab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isAnh56()) {
                getAnh56Tab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isAnh52()) {
                getAnh52Tab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isAnh53Gr()) {
                getAnh53Tab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isAnh53Kl()) {
                getAnh53Tab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isUebergabestelle()) {
                getUebergabeTab().enableAll(enabled);
            } else if (objekt.getBasisObjektarten().isGenehmigung()) {
                getGenehmigungTab().enableAll(enabled);
            }
        }
    }

    /**
     * Wird benutzt, um bestimmte Objektarten beim Speichern besonders zu behandeln.
     * Wird aufgerufen, wenn das Objekt bereits gespeichert wurde.
     * @param session Die Hibernate-Session.
     * @throws HibernateException Wenn ein Fehler auftritt,
     * der einen kompletten Rollback der Speicher-Transaktion erfordert.
     */
    public void completeObjekt() {
        // TODO: Fachdaten beim Objektart-Wechsel löschen?
        if (objekt.getBasisObjektarten() != null) {
            // Verschiedene Fachdaten bei neuem Objekt neu anlegen
            if (objekt.getBasisObjektarten().isProbepunkt()) {
                getProbepunktTab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isBWK()) {
                getBWKTab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isAnh50()) {
                getZahnarztTab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isAnh49()) {
                getAnhang49Tab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isSuev()) {
                getSuevTab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isAnh40()) {
                getAnh40Tab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isAnh55()) {
                getAnh55Tab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isAnh56()) {
                getAnh56Tab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isAnh52()) {
                getAnh52Tab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isAnh53Gr()) {
                getAnh53Tab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isAnh53Kl()) {
                getAnh53Tab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isUebergabestelle()) {
                getUebergabeTab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isGenehmigung()) {
                getGenehmigungTab().completeObjekt();
            } else if (objekt.getBasisObjektarten().isProbepunkt()) {
                getProbepunktTab().completeObjekt();
            }
        }
    }

}