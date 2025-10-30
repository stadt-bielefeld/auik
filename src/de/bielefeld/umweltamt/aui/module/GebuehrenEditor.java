package de.bielefeld.umweltamt.aui.module;

import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.atl.Gebuehren;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Panel allowing to view, edit and save fee values.
 */
public class GebuehrenEditor extends JDialog {
    private static final AuikLogger log = AuikLogger.getLogger();

    private JPanel content = null;

    //Fields, constants and record used for personnel fees editing
    private JLabel personnelFeeLabel = null;
    private JTextField personnelFeeField = null;
    public static final String PERSONNEL_FEE_BEZEICHNUNG = "personalkosten";
    private final String PERSONNEL_FEE_LABEL_TEXT = "Personal- und Sachkosten in Cent";
    private Gebuehren personnelFeeRecord = null;

    private JButton okButton = null;
    private JButton cancelButton = null;

    private KeyEventDispatcher keyEventDispatcher;
    private static final long serialVersionUID = 3466100112259802102L;

    public GebuehrenEditor(JFrame owner) {
        super(owner, "Gebühren bearbeiten", true);
        this.setSize(350, 120);

        this.personnelFeeField = this.initPersonnelFeeField();
        this.personnelFeeField.setPreferredSize(new Dimension(100, 22));
        this.personnelFeeLabel = new JLabel(PERSONNEL_FEE_LABEL_TEXT + ":");

        this.okButton = new JButton("Ok");
        this.cancelButton = new JButton("Abbrechen");

        this.okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    boolean saved = GebuehrenEditor.this.save();
                    if (saved) {
                        GebuehrenEditor.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(GebuehrenEditor.this, "Die Änderungen konnten nicht gespeichert werden!");
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(
                        GebuehrenEditor.this,
                        nfe.getMessage());
                }

            }
        });

        this.cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                GebuehrenEditor.this.dispose();
            }
        });

         //Key handler
         keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() != KeyEvent.KEY_PRESSED) {
                    return false;
                }
                switch (e.getKeyCode()) {
                    //Use ESC to cancel
                    case KeyEvent.VK_ESCAPE:
                        GebuehrenEditor.this.dispose();
                        break;
                }
                return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(keyEventDispatcher);

        this.initContent();
        this.add(this.content);
    }


    /**
     * Init content components
     */
    private void initContent() {
        FormLayout layout = new FormLayout(
            "right:pref, 4dlu, pref:grow, 4dlu, pref", // Spalten
            "pref:grow, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref:grow" // Zeilen
        );

        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        builder.add(personnelFeeLabel, cc.xy(1, 1));
        builder.add(personnelFeeField, cc.xy(3, 1));
        builder.add(buttonPanel, cc.xy(1, 3));

        this.content = builder.build();
        this.content.setBorder(Paddings.DIALOG);
    }

    /**
     * Create and initialize the personnel fee field.
     * Loads the respective record from the database
     * @return Intialized field.
     */
    public JTextField initPersonnelFeeField() {
        this.personnelFeeRecord = Gebuehren.findByBezeichnung(PERSONNEL_FEE_BEZEICHNUNG);
        if (this.personnelFeeRecord == null) {
            log.error("No personnel fee record found");
            return new JTextField();
        }
        JTextField field = new JTextField();
        field.setText(personnelFeeRecord.getWert().toString());
        return field;
    }

    /**
     * Save records in this components
     * @return True if save was successful, else false
     * @throws NumberFormatException Thrown if invalid number values are set
     */
    public boolean save() throws NumberFormatException {
        boolean success = false;
        try {
            this.personnelFeeRecord.setWert(Integer.parseInt(this.personnelFeeField.getText()));
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(String.format("Ungültiger Wert für \"%s\"", PERSONNEL_FEE_LABEL_TEXT));
        }
        success = this.personnelFeeRecord.merge();

        return success;
    }

}
