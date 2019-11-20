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

import javax.swing.JComponent;
import javax.swing.JPanel;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

public class SonderbauwerkTypTab extends JPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private SonderbauwerkTypPanel content;
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

    public SonderbauwerkTypTab (BasisObjektBearbeiten parentModule) {
        this.parentModule = parentModule;
    }

    public void switchTypDetailPanel(String type) {
        int typePanelIndex = this.parentModule.getTabbedPane().indexOfComponent(this);
        if (typePanelIndex == -1) {
            log.error("SonderbauwerkTypPanel index not set");
            return;
        }
        this.setVisible(true);
        switch ((String) type) {
            case "RRB":
                this.setContentPanel(getRRBPanel());
                getRRBPanel().setData(this.sonderbauwerk);
                break;
            case "RKB":
                this.setContentPanel(getRKBPanel());
                getRKBPanel().setData(this.sonderbauwerk);
                break;
            case "RBF":
                this.setContentPanel(getRBFPanel());
                getRBFPanel().setData(this.sonderbauwerk);
                break;
            case "BF":
                this.setContentPanel(getBFPanel());
                getBFPanel().setData(this.sonderbauwerk);
                break;
            case "RÜT":
                this.setContentPanel(getRUTPanel());
                getRUTPanel().setData(this.sonderbauwerk);
                break;
            case "RST":
                this.setContentPanel(getRSTPanel());
                getRSTPanel().setData(this.sonderbauwerk);
                break;
            case "AL":
                this.setContentPanel(getALPanel());
                getALPanel().setData(this.sonderbauwerk);
                break;
            default:
                this.parentModule.getSonderbauwerkTypTab().setVisible(false);
        }
        this.parentModule.getTabbedPane().setTitleAt(typePanelIndex, getContentName());
    }

    public void setData(Sonderbauwerk sonderbauwerk) {
        this.sonderbauwerk = sonderbauwerk;
    }

    public RRBPanel getRRBPanel() {
        if (this.rrbPanel == null) {
            this.rrbPanel = new RRBPanel(this.parentModule);
        }
        return this.rrbPanel;
    }

    public RKBPanel getRKBPanel() {
        if (this.rkbPanel == null) {
            this.rkbPanel = new RKBPanel(this.parentModule);
        }
        return this.rkbPanel;
    }

    public RBFPanel getRBFPanel() {
        if (this.rbfPanel == null) {
            this.rbfPanel = new RBFPanel(this.parentModule);
        }
        return this.rbfPanel;
    }

    public BFPanel getBFPanel() {
        if (this.bfPanel == null) {
            this.bfPanel = new BFPanel(this.parentModule);
        }
        return this.bfPanel;
    }

    public RUTPanel getRUTPanel() {
        if (this.rutPanel == null) {
            this.rutPanel = new RUTPanel(this.parentModule);
        }
        return this.rutPanel;
    }

    public RSTPanel getRSTPanel() {
        if (this.rstPanel == null) {
            this.rstPanel = new RSTPanel(this.parentModule);
        }
        return this.rstPanel;
    }

    public ALPanel getALPanel() {
        if (this.alPanel == null) {
            this.alPanel = new ALPanel(this.parentModule);
        }
        return this.alPanel;
    }

    public SonderbauwerkTypPanel getContent() {
        return this.content;
    }

    public void setContentPanel(SonderbauwerkTypPanel comp) {
        this.removeAll();
        this.content = comp;
        this.add(comp);
    }

    public String getContentName() {
        if (this.content != null) {
            return this.content.getName();
        } else {
            return "";
        }
    }

    public void setContentName(String name) {
        this.content.setName(name);
    }
}