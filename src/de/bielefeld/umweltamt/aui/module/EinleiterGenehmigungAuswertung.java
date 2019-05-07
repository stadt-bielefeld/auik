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

package de.bielefeld.umweltamt.aui.module;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.GenehmigungModel;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class EinleiterGenehmigungAuswertung extends AbstractQueryModul {
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JComboBox anhangBox;
    private JCheckBox inaktivCheck;
    private JCheckBox gen58Check;
    private JCheckBox gen59Check;
    private JButton okayButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private GenehmigungModel tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Genehmigung";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     * @return "m_auswertung_genehmigung"
     */
    @Override
    public String getIdentifier() {
        return "m_auswertung_genehmigung";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            PanelBuilder builder = new PanelBuilder();
            builder.setAnchor(GridBagConstraints.WEST);
            builder.setInsets(new Insets(0, 0, 0, 10));
            builder.addComponent(getAnhangBox(), "Anhang:");
            builder.addComponents(getInaktivCheck(), getGen58Check(), getGen59Check(), getOkayButton());
            builder.fillRow(true);
            queryPanel = builder.getPanel();
        }

        return queryPanel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
     */
    @Override
    public ListTableModel getTableModel() {
        if (tmodel == null) {
            tmodel = new GenehmigungModel();
        }
        return tmodel;
    }

    private JComboBox getAnhangBox() {
        if (this.anhangBox == null) {
            Integer[] anhaenge = DatabaseQuery.getAnhangFromGenehmigung();
            String[] comboValues = new String[anhaenge.length+1];
            comboValues[0] = "alle";
            for (int i = 1; i < anhaenge.length; i++) {
                comboValues[i] =
                    (anhaenge[i-1] != null ? anhaenge[i-1].toString() : null);
            }
            comboValues[anhaenge.length] = "keine Angabe (BWK)";
            this.anhangBox = new JComboBox(comboValues);
        }
        return this.anhangBox;
    }

    private Integer getAnhang() {
        String comboValue = (String) this.getAnhangBox().getSelectedItem();
        if (comboValue.equals("alle")) {
            return -1;
        } else if (comboValue.equals("keine Angabe (BWK)")) {
            return null;
        } else {
            return new Integer(comboValue);
        }
    }

    private JCheckBox getInaktivCheck() {
        if (this.inaktivCheck == null) {
            this.inaktivCheck = new JCheckBox("inaktiv", false);
        }
        return this.inaktivCheck;
    }

    private JCheckBox getGen58Check() {
        if (this.gen58Check == null) {
            this.gen58Check = new JCheckBox("nur 58er", false);
        }
        return this.gen58Check;
    }

    private JCheckBox getGen59Check() {
        if (this.gen59Check == null) {
            this.gen59Check = new JCheckBox("nur 59er", false);
        }
        return this.gen59Check;
    }

    private JButton getOkayButton() {
        if (this.okayButton == null) {
            this.okayButton = new JButton("Genehmigungen anzeigen");
            this.okayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(
                        getResultTable()) {
                        @Override
                        protected void doNonUILogic() {
                            getTableModel().setList(
                                DatabaseQuery.getGenehmigungen(
                                    getAnhang(),
                                    getInaktivCheck().isSelected(),
                                    getGen58Check().isSelected(),
                                    getGen59Check().isSelected()));
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            getTableModel().fireTableDataChanged();
                            frame.changeStatus(
                                getTableModel().getRowCount()
                                + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });
        }
        return this.okayButton;
    }
}
