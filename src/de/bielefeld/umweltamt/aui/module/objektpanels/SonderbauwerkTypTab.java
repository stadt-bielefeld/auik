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

package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

public class SonderbauwerkTypTab extends JPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Panel to show as content
     */
    private AbstractSonderbauwerkTypPanel content;

    /**
     * Parent module
     */
    private BasisObjektBearbeiten parentModule;

    /**
     * Record to use for content panels
     */
    private Sonderbauwerk sonderbauwerk;

    //Type panels
    private RRBPanel rrbPanel;
    private RKBPanel rkbPanel;
    private RBFPanel rbfPanel;
    private BFPanel bfPanel;
    private RUTPanel rutPanel;
    private RSTPanel rstPanel;
    private ALPanel alPanel;

    /**
     * Constructor
     */
    public SonderbauwerkTypTab (BasisObjektBearbeiten parentModule) {
        this.parentModule = parentModule;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    /**
     * Switch the panel content according to the given type string
     * @param type New panel content type
     */
    public void switchTypDetailPanel(String type) {
        if (type == null) {
            this.parentModule.setSonderbauwerkTypPanelEnabled(false);
            return;
        }

        int typePanelIndex = this.parentModule.indexOfComponent(this);
        if (typePanelIndex == -1) {
            this.parentModule.setSonderbauwerkTypPanelEnabled(true);
            typePanelIndex = this.parentModule.indexOfComponent(this);
        }
        switch ((String) type) {
            case "RRB":
                this.setContentPanel(getRRBPanel());
                getRRBPanel().setRecord(this.sonderbauwerk);
                this.parentModule.setTitleAt(typePanelIndex, getContentName());
                break;
            case "RKB":
                this.setContentPanel(getRKBPanel());
                getRKBPanel().setRecord(this.sonderbauwerk);
                this.parentModule.setTitleAt(typePanelIndex, getContentName());
                break;
            case "RBF":
                this.setContentPanel(getRBFPanel());
                getRBFPanel().setRecord(this.sonderbauwerk);
                this.parentModule.setTitleAt(typePanelIndex, getContentName());
                break;
            case "BF":
                this.setContentPanel(getBFPanel());
                getBFPanel().setRecord(this.sonderbauwerk);
                this.parentModule.setTitleAt(typePanelIndex, getContentName());
                break;
            case "RÜT":
                this.setContentPanel(getRUTPanel());
                getRUTPanel().setRecord(this.sonderbauwerk);
                this.parentModule.setTitleAt(typePanelIndex, getContentName());
                break;
            case "RST":
                this.setContentPanel(getRSTPanel());
                getRSTPanel().setRecord(this.sonderbauwerk);
                this.parentModule.setTitleAt(typePanelIndex, getContentName());
                break;
            case "AL":
                this.setContentPanel(getALPanel());
                getALPanel().setRecord(this.sonderbauwerk);
                this.parentModule.setTitleAt(typePanelIndex, getContentName());
                break;
            default:
                this.parentModule.setSonderbauwerkTypPanelEnabled(false);
        }
        if (this.getContent() != null) {
            this.getContent().fetchFormData();
        }
    }

    /**
     * Set data record
     * @param sonderbauwerk Record
     */
    public void setData(Sonderbauwerk sonderbauwerk) {
        this.sonderbauwerk = sonderbauwerk;
        this.getRRBPanel().setRecord(sonderbauwerk);
        this.getRKBPanel().setRecord(sonderbauwerk);
        this.getRBFPanel().setRecord(sonderbauwerk);
        this.getBFPanel().setRecord(sonderbauwerk);
        this.getRUTPanel().setRecord(sonderbauwerk);
        this.getRSTPanel().setRecord(sonderbauwerk);
        this.getALPanel().setRecord(sonderbauwerk);
    }

    /**
     * Get the RBBPanel
     * @return The RBBPanel
     */
    public RRBPanel getRRBPanel() {
        if (this.rrbPanel == null) {
            this.rrbPanel = new RRBPanel(this.parentModule);
            this.rrbPanel.setRecord(sonderbauwerk);
        }
        return this.rrbPanel;
    }

    /**
     * Get the RKBPanel
     * @return The RKBPanel
     */
    public RKBPanel getRKBPanel() {
        if (this.rkbPanel == null) {
            this.rkbPanel = new RKBPanel(this.parentModule);
            this.rkbPanel.setRecord(sonderbauwerk);
        }
        return this.rkbPanel;
    }

    /**
     * Get the RBFPanel
     * @return The RBFPanel
     */
    public RBFPanel getRBFPanel() {
        if (this.rbfPanel == null) {
            this.rbfPanel = new RBFPanel(this.parentModule);
            this.rbfPanel.setRecord(sonderbauwerk);
        }
        return this.rbfPanel;
    }

    /**
     * Get the BFPanel
     * @return The BFPanel
     */
    public BFPanel getBFPanel() {
        if (this.bfPanel == null) {
            this.bfPanel = new BFPanel(this.parentModule);
            this.bfPanel.setRecord(sonderbauwerk);
        }
        return this.bfPanel;
    }

    /**
     * Get the RUTPanel
     * @return The RUTPanel
     */
    public RUTPanel getRUTPanel() {
        if (this.rutPanel == null) {
            this.rutPanel = new RUTPanel(this.parentModule);
            this.rutPanel.setRecord(sonderbauwerk);
        }
        return this.rutPanel;
    }

    /**
     * Get the RSTPanel
     * @return The RSTPanel
     */
    public RSTPanel getRSTPanel() {
        if (this.rstPanel == null) {
            this.rstPanel = new RSTPanel(this.parentModule);
            this.rstPanel.setRecord(sonderbauwerk);
        }
        return this.rstPanel;
    }

    /**
     * Get the ALPanel
     * @return The ALPanel
     */
    public ALPanel getALPanel() {
        if (this.alPanel == null) {
            this.alPanel = new ALPanel(this.parentModule);
            this.alPanel.setRecord(sonderbauwerk);
        }
        return this.alPanel;
    }

    /**
     * Get the content panel
     * @return The content panel
     */
    public AbstractSonderbauwerkTypPanel getContent() {
        return this.content;
    }

    /**
     * Set the content panel
     * @param comp The new content panel
     */
    public void setContentPanel(AbstractSonderbauwerkTypPanel comp) {
        this.removeAll();
        this.content = comp;
        this.add(comp);
    }

    /**
     * Get the content panel name
     * @return The name as string
     */
    public String getContentName() {
        if (this.content != null) {
            return this.content.getName();
        } else {
            return "";
        }
    }

    /**
     * Set the content panels name
     * @param name The new name
     */
    public void setContentName(String name) {
        this.content.setName(name);
    }
}