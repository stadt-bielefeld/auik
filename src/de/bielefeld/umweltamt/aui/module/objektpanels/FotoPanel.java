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
 * $Id: FotoPanel.java,v 1.1.2.1 2016-07-12 10:25:57 u633d Exp $
 *
 * Erstellt am 12.07.2016 von Gerd Genuit
 *
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Color;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.ModulManager;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Das "Objekt-Chronologie"-Panel des Objekt-Bearbeiten-Moduls.
 * @author Gerd Genuit
 */
public class FotoPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9086697143957142239L;
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    protected JPanel panel = null;
    private String name;
    private BasisObjektBearbeiten hauptModul;
    private JPanel imagePanel;
    protected ModulManager manager;
  
    // Widgets für Fotopanel
    private JLabel fotoLabel;

    /**
     * Erzeugt das Foto-Panel für das BasisObjektBearbeiten-Modul.
     * @param hauptModul Das BasisObjektBearbeiten-Hauptmodul.
     */
    public FotoPanel(BasisObjektBearbeiten hauptModul) {
    	
    	this.name = "Foto";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "pref");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();
        

        builder.append(getImagePanel());

    	
    }

    // Foto
    private JPanel getImagePanel() {
        	imagePanel = new JPanel();

            imagePanel.setBackground(Color.WHITE);
            imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    if (hauptModul.getObjekt() != null) {
                        String imgPath = "X:/Applikationen/Anlagenkataster/Fotos/" + 
                        		hauptModul.getObjekt().getId() + ".jpg";
                        File imgFile = new File(imgPath);
                        if (imgFile.canRead()) {
                            ImageIcon imgIcon = new ImageIcon(
                                imgFile.getAbsolutePath());
                            int panelWidth = 1000;
                            if (imgIcon.getIconWidth() > panelWidth) {
                                imgIcon.setImage(imgIcon.getImage()
                                    .getScaledInstance(panelWidth, -1,
                                        Image.SCALE_FAST));
                            }
                            getFotoLabel().setIcon(null);
                            getFotoLabel().setIcon(imgIcon);
                            getFotoLabel().setText(null);
                        } else {
                            getFotoLabel().setIcon(null);
                            getFotoLabel().setText(
                                "<html><b>-  Foto " + 
                        		hauptModul.getObjekt().getId() + ".jpg nicht gefunden!  -</b></html>");
                        }
                    }

                    imagePanel.add(getFotoLabel());
                
			
        return this.imagePanel;
    }

    private JLabel getFotoLabel() {
        if (this.fotoLabel == null) {
            this.fotoLabel = new JLabel(
                "<html><b>- Kein Foto verfügbar! -</b></html>");
        }

        return this.fotoLabel;
    }

    // Funktionalität:

    /**
     * Holt die Liste mit Fachdatensätzen aus der Datenbank.
     */
//    public void getFoto() {
//        this.getFotoRtPanel();
//    }

    @Override
    public String getName() {
        return this.name;
    }


}
