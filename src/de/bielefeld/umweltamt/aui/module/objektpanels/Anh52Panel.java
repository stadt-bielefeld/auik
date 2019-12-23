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
 * $Id: Anh52Panel.java,v 1.1.2.1 2010-11-23 10:25:57 u633d Exp $
 *
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.4  2006/10/17 09:10:59  u633d
 * small changeZ!
 *
 * Revision 1.3  2006/10/17 09:09:13  u633d
 * small changeZ!
 *
 * Revision 1.2  2006/10/17 09:00:36  u633d
 * Anhang 52
 *
 * Revision 1.1  2006/10/17 07:54:29  u633d
 * Anhang 52 (Chemische Wäschereien) haben nun einen eigenen Tab.
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das Panel zum Bearbeiten von Druckereien
 * @author u633d
 */
public class Anh52Panel extends JPanel{
    private static final long serialVersionUID = -1240254202213465142L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JFormattedTextField nrbetriebsstaetteFeld = null;
    private JTextField firmennameFeld = null;
    private JTextField telefonFeld = null;
    private JTextField telefaxFeld = null;
    private JTextField ansprechpartnerFeld = null;
    private TextFieldDateChooser datumGenChooser = null;
    private JTextArea BemerkungenArea = null;

    private JButton saveAnh52Button = null;

    // Daten
    private Anh52Fachdaten fachdaten = null;

    public Anh52Panel(BasisObjektBearbeiten hauptModul) {
        name = "Chemische Wäscherei";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout (
                "r:90dlu, 5dlu, 95dlu, 5dlu, r:0dlu, 0dlu, 90dlu", // Spalten
                "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

        builder.appendSeparator("Fachdaten");
        builder.append("Nr. der Betriebsstätte:", getNrBetriebsstaette());
        builder.append("");
        builder.nextLine();
        builder.append("Firmenname:", getFirmennameFeld());
        builder.append("");
        builder.nextLine();
        builder.append("Ansprechpartner:",  getAnsprechpartnerFeld());
        builder.append("");
        builder.nextLine();
        builder.append("Telefon:", getTelefonFeld());
        builder.append("");
        builder.nextLine();
        builder.append("Telefax:", getTelefaxFeld());
        builder.append("");
        builder.nextLine();
        builder.append("Genehmigung:", getGenehmigungDatum());
        builder.append("");
        builder.nextLine();

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungenArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);

        JComponent buttonBar = ComponentFactory.buildOKBar(getSaveAnh52Button());
        builder.append(buttonBar, 7);

    }

    public void completeObjekt() {
        if (hauptModul.isNew() || fachdaten == null) {
            // Neues Anhang 52 Objekt erzeugen
            fachdaten = new Anh52Fachdaten();
            // Objekt_Id setzen
            fachdaten.setObjekt(hauptModul.getObjekt());

            // Anhang 52 Objekt speichern
            fachdaten.merge();
            log.debug("Neues Anh 52 Objekt " + fachdaten + " gespeichert.");
        }
    }

    private boolean saveAnh52Daten() {
        boolean success;

        String bemerkungen = BemerkungenArea.getText();
        if ("".equals(bemerkungen)) {
            fachdaten.setBemerkungen(null);
        } else {
            fachdaten.setBemerkungen(bemerkungen);
        }

        Integer nrbetriebst = ((IntegerField)nrbetriebsstaetteFeld).getIntValue();
        fachdaten.setNrbetriebsstaette(nrbetriebst);

        Date genehmigung = datumGenChooser.getDate();
        fachdaten.setDatumgenehmigung(genehmigung);

        String ansprechpartnerString = ansprechpartnerFeld.getText();
        if ("".equals(ansprechpartnerString)) {
            fachdaten.setAnsprechpartner(null);
        } else {
            fachdaten.setAnsprechpartner(ansprechpartnerString);
        }

        String telefonString = telefonFeld.getText();
        if ("".equals(telefonString)) {
            fachdaten.setTelefon(null);
        } else {
            fachdaten.setTelefon(telefonString);
        }

        String telefaxString = telefaxFeld.getText();
        if ("".equals(telefaxString)) {
            fachdaten.setTelefax(null);
        } else {
            fachdaten.setTelefax(telefaxString);
        }

        String firmennameString = firmennameFeld.getText();
        if ("".equals(firmennameString)) {
            fachdaten.setFirmenname(null);
        } else {
            fachdaten.setFirmenname(firmennameString);
        }

        success = fachdaten.merge();
        if (success) {
            log.debug("Anh 52 Objekt " + fachdaten.getId()
            		+ " gespeichert.");
        } else {
            log.debug("Anh 52 Objekt " + fachdaten
            		+ " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void enableAll(boolean enabled) {

        getBemerkungenArea().setEnabled(enabled);
        getNrBetriebsstaette().setEnabled(enabled);
        getFirmennameFeld().setEnabled(enabled);
        getTelefonFeld().setEnabled(enabled);
        getTelefaxFeld().setEnabled(enabled);
        getAnsprechpartnerFeld().setEnabled(enabled);
        getGenehmigungDatum().setEnabled(enabled);
    }

    public void clearForm() {
        getNrBetriebsstaette().setText(null);
        getFirmennameFeld().setText(null);
        getTelefonFeld().setText(null);
        getTelefaxFeld().setText(null);
        getAnsprechpartnerFeld().setText(null);
        getGenehmigungDatum().setDate(null);
        getBemerkungenArea().setText(null);
    }

    public void updateForm() throws RuntimeException {

    if (fachdaten != null) {
        if (fachdaten.getBemerkungen() != null) {
            getBemerkungenArea().setText(fachdaten.getBemerkungen());
        }

        if (fachdaten.getNrbetriebsstaette() != null) {
            getNrBetriebsstaette().setText(fachdaten.getNrbetriebsstaette().toString());
        }

        if (fachdaten.getFirmenname() != null) {
            getFirmennameFeld().setText(fachdaten.getFirmenname());
        }

        if (fachdaten.getTelefon() != null) {
            getTelefonFeld().setText(fachdaten.getTelefon());
        }

        if (fachdaten.getTelefax() != null) {
            getTelefaxFeld().setText(fachdaten.getTelefax());
        }

        if (fachdaten.getAnsprechpartner() != null) {
            getAnsprechpartnerFeld().setText(fachdaten.getAnsprechpartner());
        }

        if (fachdaten.getDatumgenehmigung() != null) {
            getGenehmigungDatum().setDate(fachdaten.getDatumgenehmigung());
        }
    }

    }



    public void fetchFormData() throws RuntimeException {
    	Set<Anfallstelle> list = this.hauptModul.getObjekt().getAnfallstelles();
		this.fachdaten = Anh52Fachdaten.findByAnfallstelleId(
				list.iterator().next().getId());
        this.log.debug("Anhang 52 Objekt aus DB geholt: " + this.fachdaten);
    }

    @Override
    public String getName() {
        return name;
    }

    private JButton getSaveAnh52Button() {
        if (saveAnh52Button == null) {
            saveAnh52Button = new JButton("Speichern");

            saveAnh52Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh52Daten()) {
                        hauptModul.getFrame().changeStatus("Anh 52 Objekt "+fachdaten.getId()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
                    } else {
                        hauptModul.getFrame().changeStatus("Fehler beim Speichern des Anh 40 Objekt!", HauptFrame.ERROR_COLOR);
                    }

                    hauptModul.fillForm();
                }
            });
        }
        return saveAnh52Button;
    }

    private JFormattedTextField getNrBetriebsstaette() {
        if (nrbetriebsstaetteFeld == null) {
            nrbetriebsstaetteFeld = new IntegerField();
        }
        return nrbetriebsstaetteFeld;
    }

    private JTextField getFirmennameFeld() {
        if (firmennameFeld == null) {
            firmennameFeld = new LimitedTextField(50);
        }
        return firmennameFeld;
    }

    private JTextField getTelefonFeld() {
        if (telefonFeld == null) {
            telefonFeld = new LimitedTextField(50);
        }
        return telefonFeld;
    }

    private JTextField getTelefaxFeld() {
        if (telefaxFeld == null) {
            telefaxFeld = new LimitedTextField(50);
        }
        return telefaxFeld;
    }

    private JTextField getAnsprechpartnerFeld() {
        if (ansprechpartnerFeld == null) {
            ansprechpartnerFeld = new LimitedTextField(50);
        }
        return ansprechpartnerFeld;
    }


    private TextFieldDateChooser getGenehmigungDatum() {
        if (datumGenChooser == null) {
            datumGenChooser = new TextFieldDateChooser();
        }
        return datumGenChooser;
    }

    private JTextArea getBemerkungenArea() {
        if (BemerkungenArea == null) {
            BemerkungenArea = new LimitedTextArea(252);
            BemerkungenArea.setLineWrap(true);
            BemerkungenArea.setWrapStyleWord(true);
        }
        return BemerkungenArea;
    }
}
