/*
 * Datei:
 * $Id: EinleiterAnh49Auswertung.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
 *
 * Erstellt am 15.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2010/01/12 09:02:32  u633d
 * Fettabscheider-Auswertung
 *
 * Revision 1.3  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/07/23 06:55:22  u633d
 * Anh49 Auswertung und Sielhautimport neu
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.4  2005/09/14 11:25:38  u633d
 * - Version vom 14.9.
 *
 * Revision 1.3  2005/09/07 05:56:14  u633d
 * - Anh 49 ergänzt und neue Mappings
 *
 * Revision 1.2  2005/08/31 06:25:12  u633d
 * - kleine Ergänzungen
 *
 * Revision 1.1  2005/08/24 08:42:51  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh49Model;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für Anhang 49-Datensätze(ausgenommen Fettabscheider).
 * @author David Klotz
 */
public class EinleiterAnh49Auswertung extends AbstractQueryModul {
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JTextField sachbFeld;
    private IntegerField dekraTuevFeld;
    private JCheckBox abgemeldetCheck;
    private JCheckBox abgerissenCheck;
    private JCheckBox abwasserfreiCheck;
    private JCheckBox wiedervorlageCheck;
    private JButton submitButton;
    private JButton tuevButton;
    private JButton sachbearbeiterButton;
    private JButton alleButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private Anh49Model tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    public String getName() {
        return "Anhang 49";
    }

    public String getIdentifier() {
        return "m_auswertung_anh49";
    }
    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren:
            sachbFeld = new JTextField("", 12);
            dekraTuevFeld = new IntegerField();

            abgemeldetCheck = new JCheckBox("Abgemeldet");

            abgerissenCheck = new JCheckBox("Abgerissen");

            abwasserfreiCheck = new JCheckBox("Abwasserfrei");

            wiedervorlageCheck = new JCheckBox("Nur abgelaufene Wiedervorlage");

            submitButton = new JButton("Suchen");

            alleButton = new JButton("Alle anzeigen");

            tuevButton = new JButton("Suchen");
            tuevButton.setToolTipText("Tuev/Dekra suchen");

            sachbearbeiterButton = new JButton("Suchen");
            sachbearbeiterButton.setToolTipText("SachbearbeiterIn anzeigen");

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean abgem;
                    if (abgemeldetCheck.isSelected()) {
                        abgem = true;
                    } else {
                        abgem = false;
                    }

                    String abgerissen;
                    if (abgerissenCheck.isSelected()) {
                        abgerissen = "%abgerissen";
                    } else {
                        abgerissen = "";
                    }

                    boolean abwfrei;
                    if (abwasserfreiCheck.isSelected()) {
                        abwfrei = true;
                    } else {
                        abwfrei = false;
                    }

                    ((Anh49Model)getTableModel()).setList(
                            Anh49Fachdaten.findAuswertung(
                                    abgem,
                                    abgerissen,
                                    abwfrei,
                                    wiedervorlageCheck.isSelected()));
                    ((Anh49Model)getTableModel()).fireTableDataChanged();
                    frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                }
            });

            alleButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    ((Anh49Model)getTableModel()).setList(
                            Anh49Fachdaten.findAlle());
                    ((Anh49Model)getTableModel()).fireTableDataChanged();
                    frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                }
            });

            tuevButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    Integer tuev = dekraTuevFeld.getIntValue();

                    ((Anh49Model)getTableModel()).setList(
                            Anh49Fachdaten.findTuev(tuev));
                    ((Anh49Model)getTableModel()).fireTableDataChanged();
                    frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                }
            });

            sachbearbeiterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String sachbearb = sachbFeld.getText();

                    ((Anh49Model)getTableModel()).setList(
                            Anh49Fachdaten.findSachbearbeiter(sachbearb));
                    ((Anh49Model)getTableModel()).fireTableDataChanged();
                    frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout(
                    "pref, 3dlu, pref, 3dlu, pref, 20dlu, pref, 3dlu, pref, 3dlu, pref, 20dlu, pref"
                    );
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            builder.append(abgemeldetCheck, abwasserfreiCheck);
            builder.append(abgerissenCheck);
            builder.append("SachbearbeiterIn:", sachbFeld, sachbearbeiterButton);
            builder.nextLine();
            builder.append(wiedervorlageCheck);
            builder.append("");
            builder.append(submitButton);
            builder.append("Dekra-TÜV-T.:", dekraTuevFeld, tuevButton, alleButton);

            queryPanel = builder.getPanel();
        }

        return queryPanel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
     */
    public ListTableModel getTableModel() {
        if (tmodel == null) {
            tmodel = new Anh49Model();
        }
        return tmodel;
    }
}
