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
 * $Id: Anh55Panel.java,v 1.1.2.1 2010-11-23 10:25:57 u633d Exp $
 *
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2010/02/04 13:34:29  u633d
 * Verkn�pfte Objekte
 *
 * Revision 1.3  2010/02/04 13:09:38  u633d
 * Verkn�pfte Objekte
 *
 * Revision 1.2  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.10  2006/10/17 07:54:29  u633d
 * Anhang 52 (Chemische Wäschereien) haben nun einen eigenen Tab.
 *
 * Revision 1.9  2006/09/28 07:31:34  u633d
 * Bei dem Verwaltungsverfahren eine Bemerkungs-spalte hinzugefuegt
 *
 * Revision 1.8  2006/09/26 09:48:38  u633d
 * Anh 55 bereinigt; settings wieder auf UTEDB2 gesetzt
 *
 * Revision 1.7  2006/09/25 12:17:17  u633d
 * Wäscherei funzt
 *
 * Revision 1.6  2006/09/25 09:36:59  u633d
 * Waescherei Panel
 *
 * Revision 1.5  2006/09/25 08:22:25  u633d
 * *** empty log message ***
 *
 * Revision 1.4  2006/09/21 11:52:56  u633d
 * *** empty log message ***
 *
 * Revision 1.3  2006/09/21 07:47:30  u633d
 * Oberfläche erstellt
 *
 * Revision 1.2  2006/09/20 06:45:42  u633d
 * *** empty log message ***
 *
 * Revision 1.1  2006/09/13 11:06:21  u633d
 * *** empty log message ***
 *
 * Revision 1.1  2005/08/17 05:45:31  u633d
 * - Anhang 55 erstellt
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh55Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;

/**
 * Das Panel zum Bearbeiten von Druckereien
 * @author u633d
 */
public class Anh55Panel extends ObjectPanel {
    private static final long serialVersionUID = 3345458422378912073L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private Anfallstelle anfallstelle;

    // Widgets
    private JTextField sachbearbeiterFeld = null;
    private JTextField entgebIdFeld = null;//
    private JTextField mengewaescheFeld = null;//
    private JTextField sonsttexFeld = null;//
    private DoubleField monatwasserverbFeld = null;//

    private JTextArea waschsituationArea = null;

    private JTextField ansprechpartnerFeld = null;//
    private JTextField brancheFeld = null;//

    private JTextArea BemerkungenArea = null;//

    private JCheckBox abgemeldetCheck = null;//
    private JCheckBox putztuecherCheck = null;//
    private JCheckBox teppichCheck = null;//
    private JCheckBox mattenCheck = null;//
    private JCheckBox haushaltstexCheck = null;//
    private JCheckBox berufsklCheck = null;//
    private JCheckBox gasthotelCheck = null;//
    private JCheckBox krankenhausCheck = null;
    private JCheckBox heimwaescheCheck = null;
    private JFormattedTextField anteilwaschgutFeld = null;
    private JFormattedTextField anteilgesamtwaschgutFeld = null;
    private JCheckBox betrwasseraufberCheck = null;
    private JCheckBox chlorCheck = null;
    private JCheckBox aktivchlorCheck = null;
    private JCheckBox vliesCheck = null;
    private JCheckBox fischCheck = null;
    private JCheckBox loesungsmittelCheck = null;
    private JButton saveAnh55Button = null;

    // Daten
    private Anh55Fachdaten fachdaten = null;

    @SuppressWarnings("deprecation")
    public Anh55Panel(BasisObjektBearbeiten hauptModul, Anfallstelle anfallstelle) {
        this.name = "Wäscherei";
        this.hauptModul = hauptModul;
        this.anfallstelle = anfallstelle;

        FormLayout layout = new FormLayout(
            "r:90dlu, 5dlu, 95dlu, 5dlu, r:0dlu, 0dlu, 90dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

        builder.appendSeparator("Fachdaten");
        builder.append("Entwässerungsgebiet:", getEntgebIdFeld());
        builder.append("", getAbgemeldetCheck());
        builder.nextLine();
        builder.append("Branche:", getBrancheFeld());
        builder.append("", getPutztuecherCheck());
        builder.nextLine();
        builder.append("Ansprechpartner:", getAnsprechpartnerFeld());
        builder.append("", getMattenCheck());
        builder.nextLine();
        builder.append("Menge:", getMengeFeld());
        builder.append("", getTeppichCheck());
        builder.nextLine();
        builder.append("Sonstige Textilien:", getSonsttexFeld());
        builder.append("", getBerufsklCheck());
        builder.nextLine();
        builder.append("Monatl. Wasserverbrauch:", getMonatwasserverbFeld());
        builder.append("", getHaushaltstexCheck());
        builder.nextLine();
        builder.append("Anteil am Waschgut:", getAnteilwaschgutFeld());
        builder.append("", getGasthotelCheck());
        builder.nextLine();
        builder.append("Anteil am Gesamtwaschgut:", getGesamtwaschgutFeld());
        builder.append("", getKrankenhausCheck());
        builder.nextLine();
        builder.append("Sachbearbeiter/in:", getSachbearbeiterFeld());
        builder.append("", getHeimwaescheCheck());
        builder.nextLine();
        builder.append("", getVliesCheck());
        builder.append("", getFischCheck());
        builder.nextLine();
        builder.append("", getLoesungsmittelCheck());
        builder.append("", getBetrwasseraufberCheck());
        builder.nextLine();
        builder.append("", getChlorCheck());
        builder.append("", getAktivchlorCheck());
        builder.nextLine();

        builder.appendSeparator("Wasch-Situation");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane waschsituationScroller = new JScrollPane(
            getWaschsituationArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(waschsituationScroller, 7);

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungenArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);

        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
            getSaveAnh55Button());

        // JComponent buttonBar = ComponentFactory.buildOKBar(getSaveAnh55Button());
        builder.append(buttonBar, 7);
        addChangeListeners(getAbgemeldetCheck(), getSachbearbeiterFeld(),
            getEntgebIdFeld(), getMengeFeld(), getSonsttexFeld(),
            getMonatwasserverbFeld(), getWaschsituationArea(), getAnsprechpartnerFeld(),
            getBrancheFeld(), getPutztuecherCheck(), getTeppichCheck(), getMattenCheck(),
            getHaushaltstexCheck(), getBerufsklCheck(), getGasthotelCheck(), getKrankenhausCheck(),
            getHeimwaescheCheck(), getAnteilwaschgutFeld(), getGesamtwaschgutFeld(), getBetrwasseraufberCheck(),
            getChlorCheck(), getAktivchlorCheck(), getVliesCheck(), getFischCheck(), getBemerkungenArea(),
            getAbgemeldetCheck());

    }

    public void completeObjekt(Anfallstelle anfallstelle) {
        if (this.fachdaten == null) {
            // Neues Anhang 55 Objekt erzeugen
            this.fachdaten = new Anh55Fachdaten();
        }
        // Anfallstelle setzen
            this.anfallstelle = anfallstelle;
            this.fachdaten.setAnfallstelle(this.anfallstelle);
    }

    protected boolean doSavePanelData() {
        boolean success;

        String bemerkungen = this.BemerkungenArea.getText();
        if ("".equals(bemerkungen)) {
            this.fachdaten.setBemerkungen(null);
        } else {
            this.fachdaten.setBemerkungen(bemerkungen);
        }

        String mengewaescheString = this.mengewaescheFeld.getText();
        if ("".equals(mengewaescheString)) {
            this.fachdaten.setMengewaesche(null);
        } else {
            this.fachdaten.setMengewaesche(mengewaescheString);
        }

        String sachbearbeiterString = this.sachbearbeiterFeld.getText();
        if ("".equals(sachbearbeiterString)) {
            this.fachdaten.setSachbearbeiter(null);
        } else {
            this.fachdaten.setSachbearbeiter(sachbearbeiterString);
        }

        String entgebIdString = this.entgebIdFeld.getText();
        if ("".equals(entgebIdString)) {
            this.fachdaten.setEntgebId(null);
        } else {
            this.fachdaten.setEntgebId(entgebIdString);
        }

        String monatwasserverbString = this.monatwasserverbFeld.getText();
        if ("".equals(monatwasserverbString)) {
            this.fachdaten.setMonatwasserverb(null);
        } else {
            this.fachdaten.setMonatwasserverb(monatwasserverbString);
        }

        String sonsttextString = this.sonsttexFeld.getText();
        if ("".equals(sonsttextString)) {
            this.fachdaten.setSonsttex(null);
        } else {
            this.fachdaten.setSonsttex(sonsttextString);
        }

        String waschsituationString = this.waschsituationArea.getText();
        if ("".equals(waschsituationString)) {
            this.fachdaten.setWaschsituation(null);
        } else {
            this.fachdaten.setWaschsituation(waschsituationString);
        }

        String ansprechpartnerString = this.ansprechpartnerFeld.getText();
        if ("".equals(ansprechpartnerString)) {
            this.fachdaten.setAnsprechpartner(null);
        } else {
            this.fachdaten.setAnsprechpartner(ansprechpartnerString);
        }

        String brancheString = this.brancheFeld.getText();
        if ("".equals(brancheString)) {
            this.fachdaten.setBranche(null);
        } else {
            this.fachdaten.setBranche(brancheString);
        }

        Integer anteilwaschgut = ((IntegerField) this.anteilwaschgutFeld)
            .getIntValue();
        this.fachdaten.setAnteilwaschgut(anteilwaschgut);

        Integer anteilgesamtwg = ((IntegerField) this.anteilgesamtwaschgutFeld)
            .getIntValue();
        this.fachdaten.setAnteilgesamtgut(anteilgesamtwg);

        if (getAbgemeldetCheck().isSelected()) {
            this.fachdaten.setAbgemeldet(true);
        } else {
            this.fachdaten.setAbgemeldet(false);
        }

        if (getPutztuecherCheck().isSelected()) {
            this.fachdaten.setPutztuecher(true);
        } else {
            this.fachdaten.setPutztuecher(false);
        }

        if (getTeppichCheck().isSelected()) {
            this.fachdaten.setTeppich(true);
        } else {
            this.fachdaten.setTeppich(false);
        }

        if (getMattenCheck().isSelected()) {
            this.fachdaten.setMatten(true);
        } else {
            this.fachdaten.setMatten(false);
        }

        if (getHaushaltstexCheck().isSelected()) {
            this.fachdaten.setHaushaltstex(true);
        } else {
            this.fachdaten.setHaushaltstex(false);
        }

        if (getBerufsklCheck().isSelected()) {
            this.fachdaten.setBerufskl(true);
        } else {
            this.fachdaten.setBerufskl(false);
        }

        if (getGasthotelCheck().isSelected()) {
            this.fachdaten.setGaststhotel(true);
        } else {
            this.fachdaten.setGaststhotel(false);
        }

        if (getKrankenhausCheck().isSelected()) {
            this.fachdaten.setKrankenhaus(true);
        } else {
            this.fachdaten.setKrankenhaus(false);
        }

        if (getHeimwaescheCheck().isSelected()) {
            this.fachdaten.setHeimwaesche(true);
        } else {
            this.fachdaten.setHeimwaesche(false);
        }

        if (getBetrwasseraufberCheck().isSelected()) {
            this.fachdaten.setBetrwasseraufber(true);
        } else {
            this.fachdaten.setBetrwasseraufber(false);
        }

        if (getChlorCheck().isSelected()) {
            this.fachdaten.setChlor(true);
        } else {
            this.fachdaten.setChlor(false);
        }

        if (getAktivchlorCheck().isSelected()) {
            this.fachdaten.setAktivchlor(true);
        } else {
            this.fachdaten.setAktivchlor(false);
        }

        if (getVliesCheck().isSelected()) {
            this.fachdaten.setVlies(true);
        } else {
            this.fachdaten.setVlies(true);
        }

        if (getFischCheck().isSelected()) {
            this.fachdaten.setFischfleisch(true);
        } else {
            this.fachdaten.setFischfleisch(false);
        }

        if (getLoesungsmittelCheck().isSelected()) {
            this.fachdaten.setLoesungsmittel(true);
        } else {
            this.fachdaten.setLoesungsmittel(false);
        }

        success = fachdaten.merge();
        if (success) {
            log.debug("Anh 55 Objekt " + this.fachdaten.getId()
                + " gespeichert.");
        } else {
            log.debug("Anh 55 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void enableAll(boolean enabled) {

        getBemerkungenArea().setEnabled(enabled);
        getAbgemeldetCheck().setEnabled(enabled);
        getSachbearbeiterFeld().setEnabled(enabled);
        getEntgebIdFeld().setEnabled(enabled);
        getMengeFeld().setEnabled(enabled);
        getSonsttexFeld().setEnabled(enabled);
        getMonatwasserverbFeld().setEnabled(enabled);
        getWaschsituationArea().setEnabled(enabled);
        getAnsprechpartnerFeld().setEnabled(enabled);
        getBrancheFeld().setEnabled(enabled);
        getPutztuecherCheck().setEnabled(enabled);
        getTeppichCheck().setEnabled(enabled);
        getMattenCheck().setEnabled(enabled);
        getHaushaltstexCheck().setEnabled(enabled);
        getBerufsklCheck().setEnabled(enabled);
        getGasthotelCheck().setEnabled(enabled);
        getKrankenhausCheck().setEnabled(enabled);
        getHeimwaescheCheck().setEnabled(enabled);
        getAnteilwaschgutFeld().setEnabled(enabled);
        getGesamtwaschgutFeld().setEnabled(enabled);
        getBetrwasseraufberCheck().setEnabled(enabled);
        getChlorCheck().setEnabled(enabled);
        getAktivchlorCheck().setEnabled(enabled);
        getVliesCheck().setEnabled(enabled);
        getFischCheck().setEnabled(enabled);
        getLoesungsmittelCheck().setEnabled(enabled);
    }

    public void clearForm() {
        getAbgemeldetCheck().setSelected(false);
        getSachbearbeiterFeld().setText(null);
        getEntgebIdFeld().setText(null);
        getMengeFeld().setText(null);
        getSonsttexFeld().setText(null);
        getMonatwasserverbFeld().setText(null);
        getWaschsituationArea().setText(null);
        getAnsprechpartnerFeld().setText(null);
        getBrancheFeld().setText(null);
        getPutztuecherCheck().setSelected(false);
        getTeppichCheck().setSelected(false);
        getMattenCheck().setSelected(false);
        getHaushaltstexCheck().setSelected(false);
        getBerufsklCheck().setSelected(false);
        getGasthotelCheck().setSelected(false);
        getKrankenhausCheck().setSelected(false);
        getHeimwaescheCheck().setSelected(false);
        getAnteilwaschgutFeld().setText(null);
        getGesamtwaschgutFeld().setText(null);
        getBetrwasseraufberCheck().setSelected(false);
        getChlorCheck().setSelected(false);
        getAktivchlorCheck().setSelected(false);
        getVliesCheck().setSelected(false);
        getFischCheck().setSelected(false);
        getLoesungsmittelCheck().setSelected(false);
        getBemerkungenArea().setText(null);
        getAbgemeldetCheck().setSelected(false);
    }

    public void updateForm(Anfallstelle anfallstelle) throws RuntimeException {

        this.fachdaten = anfallstelle.getAnh55Fachdatens().iterator().next();
        
        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null) {
                getBemerkungenArea().setText(this.fachdaten.getBemerkungen());
            }

            if (this.fachdaten.getSachbearbeiter() != null) {
                getSachbearbeiterFeld().setText(
                    this.fachdaten.getSachbearbeiter());
            }

            if (this.fachdaten.getEntgebId() != null) {
                getEntgebIdFeld().setText(this.fachdaten.getEntgebId());
            }

            if (this.fachdaten.getMengewaesche() != null) {
                getMengeFeld().setText(this.fachdaten.getMengewaesche());
            }

            if (this.fachdaten.getSonsttex() != null) {
                getSonsttexFeld().setText(this.fachdaten.getSonsttex());
            }

            if (this.fachdaten.getMonatwasserverb() != null) {
                getMonatwasserverbFeld().setText(
                    this.fachdaten.getMonatwasserverb());
            }

            if (this.fachdaten.getWaschsituation() != null) {
                getWaschsituationArea().setText(
                    this.fachdaten.getWaschsituation());
            }

            if (this.fachdaten.getAnsprechpartner() != null) {
                getAnsprechpartnerFeld().setText(
                    this.fachdaten.getAnsprechpartner());
            }

            if (this.fachdaten.getBranche() != null) {
                getBrancheFeld().setText(this.fachdaten.getBranche());
            }

            if (this.fachdaten.getAnteilwaschgut() != null) {
                getAnteilwaschgutFeld().setText(
                    this.fachdaten.getAnteilwaschgut().toString());
            }

            if (this.fachdaten.getAnteilgesamtgut() != null) {
                getGesamtwaschgutFeld().setText(
                    this.fachdaten.getAnteilgesamtgut().toString());
            }

            if (this.fachdaten.getAbgemeldet() != null) {
                if (this.fachdaten.getAbgemeldet() == true) {
                    getAbgemeldetCheck().setSelected(true);
                } else {
                    getAbgemeldetCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getPutztuecher() != null) {
                if (this.fachdaten.getPutztuecher() == true) {
                    getPutztuecherCheck().setSelected(true);
                } else {
                    getPutztuecherCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getTeppich() != null) {
                if (this.fachdaten.getTeppich() == true) {
                    getTeppichCheck().setSelected(true);
                } else {
                    getTeppichCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getMatten() != null) {
                if (this.fachdaten.getMatten() == true) {
                    getMattenCheck().setSelected(true);
                } else {
                    getMattenCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getHaushaltstex() != null) {
                if (this.fachdaten.getHaushaltstex() == true) {
                    getHaushaltstexCheck().setSelected(true);
                } else {
                    getHaushaltstexCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getBerufskl() != null) {
                if (this.fachdaten.getBerufskl() == true) {
                    getBerufsklCheck().setSelected(true);
                } else {
                    getBerufsklCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getGaststhotel() != null) {
                if (this.fachdaten.getGaststhotel() == true) {
                    getGasthotelCheck().setSelected(true);
                } else {
                    getGasthotelCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getKrankenhaus() != null) {
                if (this.fachdaten.getKrankenhaus() == true) {
                    getKrankenhausCheck().setSelected(true);
                } else {
                    getKrankenhausCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getHeimwaesche() != null) {
                if (this.fachdaten.getHeimwaesche() == true) {
                    getHeimwaescheCheck().setSelected(true);
                } else {
                    getHeimwaescheCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getBetrwasseraufber() != null) {
                if (this.fachdaten.getBetrwasseraufber() == true) {
                    getBetrwasseraufberCheck().setSelected(true);
                } else {
                    getBetrwasseraufberCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getChlor() != null) {
                if (this.fachdaten.getChlor() == true) {
                    getChlorCheck().setSelected(true);
                } else {
                    getChlorCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getAktivchlor() != null) {
                if (this.fachdaten.getAktivchlor() == true) {
                    getAktivchlorCheck().setSelected(true);
                } else {
                    getAktivchlorCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getVlies() != null) {
                if (this.fachdaten.getVlies() == true) {
                    getVliesCheck().setSelected(true);
                } else {
                    getVliesCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getFischfleisch() != null) {
                if (this.fachdaten.getFischfleisch() == true) {
                    getFischCheck().setSelected(true);
                } else {
                    getFischCheck().setSelected(false);
                }
            }

            if (this.fachdaten.getLoesungsmittel() != null) {
                if (this.fachdaten.getLoesungsmittel() == true) {
                    getLoesungsmittelCheck().setSelected(true);
                } else {
                    getLoesungsmittelCheck().setSelected(false);
                }
            }
        }
        setDirty(false);
    }

    public void fetchFormData() throws RuntimeException {
        Set<Anfallstelle> list = this.hauptModul.getObjekt().getAnfallstelles();
        this.fachdaten = Anh55Fachdaten.findByAnfallstelleId(
                list.iterator().next().getId());
        log.debug("Anhang 55 Objekt aus DB geholt: ID" + this.fachdaten);
    }

    private JButton getSaveAnh55Button() {
        if (this.saveAnh55Button == null) {
            this.saveAnh55Button = new JButton("Speichern");

            this.saveAnh55Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (hauptModul.saveAllTabs()) {
                        Anh55Panel.this.hauptModul.getFrame().changeStatus(
                            "Anh 55 Objekt "
                                + Anh55Panel.this.fachdaten.getId()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        Anh55Panel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Anh 55 Objekt!",
                            HauptFrame.ERROR_COLOR);
                    }

                    Anh55Panel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAnh55Button;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private JCheckBox getAbgemeldetCheck() {
        if (this.abgemeldetCheck == null) {
            this.abgemeldetCheck = new JCheckBox("Abgemeldet?");
        }
        return this.abgemeldetCheck;
    }

    private JTextField getEntgebIdFeld() {
        if (this.entgebIdFeld == null) {
            this.entgebIdFeld = new LimitedTextField(50);
        }
        return this.entgebIdFeld;
    }

    private JTextField getBrancheFeld() {
        if (this.brancheFeld == null) {
            this.brancheFeld = new LimitedTextField(50);
        }
        return this.brancheFeld;
    }

    private JCheckBox getPutztuecherCheck() {
        if (this.putztuecherCheck == null) {
            this.putztuecherCheck = new JCheckBox("Putztücher?");
        }
        return this.putztuecherCheck;
    }

    private JTextField getAnsprechpartnerFeld() {
        if (this.ansprechpartnerFeld == null) {
            this.ansprechpartnerFeld = new LimitedTextField(50);
        }
        return this.ansprechpartnerFeld;
    }

    private JCheckBox getMattenCheck() {
        if (this.mattenCheck == null) {
            this.mattenCheck = new JCheckBox("Matten?");
        }
        return this.mattenCheck;
    }

    private JTextField getMengeFeld() {
        if (this.mengewaescheFeld == null) {
            this.mengewaescheFeld = new LimitedTextField(50);
        }
        return this.mengewaescheFeld;
    }

    private JCheckBox getTeppichCheck() {
        if (this.teppichCheck == null) {
            this.teppichCheck = new JCheckBox("Teppiche?");
        }
        return this.teppichCheck;
    }

    private JTextField getSonsttexFeld() {
        if (this.sonsttexFeld == null) {
            this.sonsttexFeld = new LimitedTextField(50);
        }
        return this.sonsttexFeld;
    }

    private JCheckBox getBerufsklCheck() {
        if (this.berufsklCheck == null) {
            this.berufsklCheck = new JCheckBox("Berufskleidung?");
        }
        return this.berufsklCheck;
    }

    private DoubleField getMonatwasserverbFeld() {
        if (this.monatwasserverbFeld == null) {
            this.monatwasserverbFeld = new DoubleField(50);
        }
        return this.monatwasserverbFeld;
    }

    private JCheckBox getHaushaltstexCheck() {
        if (this.haushaltstexCheck == null) {
            this.haushaltstexCheck = new JCheckBox("Haushaltstextilien?");
        }
        return this.haushaltstexCheck;
    }

    private JCheckBox getGasthotelCheck() {
        if (this.gasthotelCheck == null) {
            this.gasthotelCheck = new JCheckBox("Gaststätten-/Hoteltextilien?");
        }
        return this.gasthotelCheck;
    }

    private JCheckBox getKrankenhausCheck() {
        if (this.krankenhausCheck == null) {
            this.krankenhausCheck = new JCheckBox("Krankenhaus?");
        }
        return this.krankenhausCheck;
    }

    private JCheckBox getHeimwaescheCheck() {
        if (this.heimwaescheCheck == null) {
            this.heimwaescheCheck = new JCheckBox("Heimwäsche?");
        }
        return this.heimwaescheCheck;
    }

    private JCheckBox getVliesCheck() {
        if (this.vliesCheck == null) {
            this.vliesCheck = new JCheckBox("Vlies?");
        }
        return this.vliesCheck;
    }

    private JCheckBox getFischCheck() {
        if (this.fischCheck == null) {
            this.fischCheck = new JCheckBox("Fisch/Fleisch?");
        }
        return this.fischCheck;
    }

    private JCheckBox getLoesungsmittelCheck() {
        if (this.loesungsmittelCheck == null) {
            this.loesungsmittelCheck = new JCheckBox("Lösungsmittel?");
        }
        return this.loesungsmittelCheck;
    }

    private JFormattedTextField getAnteilwaschgutFeld() {
        if (this.anteilwaschgutFeld == null) {
            this.anteilwaschgutFeld = new IntegerField();
        }
        return this.anteilwaschgutFeld;
    }

    private JFormattedTextField getGesamtwaschgutFeld() {
        if (this.anteilgesamtwaschgutFeld == null) {
            this.anteilgesamtwaschgutFeld = new IntegerField();
        }
        return this.anteilgesamtwaschgutFeld;
    }

    private JTextField getSachbearbeiterFeld() {
        if (this.sachbearbeiterFeld == null) {
            this.sachbearbeiterFeld = new LimitedTextField(50);
        }
        return this.sachbearbeiterFeld;
    }

    private JCheckBox getBetrwasseraufberCheck() {
        if (this.betrwasseraufberCheck == null) {
            this.betrwasseraufberCheck = new JCheckBox(
                "Betriebswasseraufbereitung?");
        }
        return this.betrwasseraufberCheck;
    }

    private JCheckBox getChlorCheck() {
        if (this.chlorCheck == null) {
            this.chlorCheck = new JCheckBox("Chlor?");
        }
        return this.chlorCheck;
    }

    private JCheckBox getAktivchlorCheck() {
        if (this.aktivchlorCheck == null) {
            this.aktivchlorCheck = new JCheckBox("Aktiv-Chlor?");
        }
        return this.aktivchlorCheck;
    }

    private JTextArea getBemerkungenArea() {
        if (this.BemerkungenArea == null) {
            this.BemerkungenArea = new LimitedTextArea(255);
            this.BemerkungenArea.setLineWrap(true);
            this.BemerkungenArea.setWrapStyleWord(true);
        }
        return this.BemerkungenArea;
    }

    private JTextArea getWaschsituationArea() {
        if (this.waschsituationArea == null) {
            this.waschsituationArea = new LimitedTextArea(255);
            this.waschsituationArea.setLineWrap(true);
            this.waschsituationArea.setWrapStyleWord(true);
        }
        return this.waschsituationArea;
    }
}
