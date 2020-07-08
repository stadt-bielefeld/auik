package de.bielefeld.umweltamt.aui.module.common;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisInhaberModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStandortModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.BasicEntryField;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

public class AdresseChooser extends JDialog {
	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private HauptFrame frame;
	private Inhaber betreiberAdresse;
	private Standort standortAdresse;
	private Adresse adresse;
	private String caller;

	private BasisInhaberModel betreiberModel;
	private BasisStandortModel standortModel;
	private BasisAdresseModel adresseModel;

	private JTextField suchFeld;
	private JTextField strasseFeld;
	private JTextField hausnrFeld;
	private JButton submitButton;
	private JButton submitButtonStrassen;
	private JTable ergebnisTabelle;

	private JButton okButton;
	private JButton abbrechenButton;

	private Timer suchTimer;

	public AdresseChooser(Object initial, HauptFrame frame, String caller) {
		super(frame, true);
		this.frame = frame;
		this.caller = caller;

		List<Object> initialList = new ArrayList<Object>();
		initialList.add(initial);

		if (initial instanceof Inhaber && caller == "adresse") {
            setTitle("Adresse auswählen");
            this.betreiberAdresse = (Inhaber) initial;
            this.adresseModel = new BasisAdresseModel();
            if (this.betreiberAdresse.getAdresse().getId() != null) {
                this.adresseModel.setList(initialList);
            }
        } else if (initial instanceof Inhaber && caller == "betreiber") {
            setTitle("Inhaber auswählen");
            this.betreiberAdresse = (Inhaber) initial;
            this.betreiberModel = new BasisInhaberModel(true);
            if (this.betreiberAdresse.getId() != null) {
                this.betreiberModel.setList(initialList);
            }
        } else if (initial instanceof Standort) {
			setTitle("Standort auswählen");
			this.standortAdresse = (Standort) initial;
			this.standortModel = new BasisStandortModel();
			if (this.standortAdresse.getId() != null) {
				this.standortModel.setList(initialList);
			}
		} else {
			throw new IllegalArgumentException("intial muss eine Adresse sein!");
		}

		setContentPane(initializeContentPane());
	
        pack();
		setResizable(true);
		setLocationRelativeTo(this.frame);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public Adresse getChosenAdresse() {
		if (this.adresse.getId() != null) {
			return this.adresse;
		} else {
			return null;
		}
	}

	public Inhaber getChosenBetreiber() {
		if (this.betreiberAdresse.getId() != null) {
			return this.betreiberAdresse;
		} else {
			return null;
		}
	}

	
    public Standort getChosenStandort() {
        if (this.standortAdresse.getId() != null) {
            return this.standortAdresse;
        } else {
            return null;
        }
    }

	private JPanel initializeContentPane() {
		JScrollPane tabellenScroller = new JScrollPane(getErgebnisTabelle(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		TabAction ta = new TabAction();
		ta.addComp(getErgebnisTabelle());
		ta.addComp(getOkButton());
		ta.addComp(getAbbrechenButton());

		JComponent buttonBar = ComponentFactory.buildOKCancelBar(getOkButton(), getAbbrechenButton());

		JToolBar submitToolBar = new JToolBar();
		submitToolBar.setFloatable(false);
		submitToolBar.setRollover(true);
		submitToolBar.add(getSubmitButton());

		FormLayout layout = new FormLayout("pref, 3dlu, 400dlu:g, 3dlu, pref, 3dlu, 30dlu:g, 3dlu, pref", // spalten
				"pref, 3dlu, pref, 3dlu, 320dlu:g, 3dlu, 3dlu, pref"); // zeilen
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		if (!caller.equals("adresse")) {
			builder.addLabel("Name:", cc.xy(1, 1));
			builder.add(getSuchFeld(), cc.xyw(3, 1, 5));
			builder.add(getSubmitButton(), cc.xy(9, 1));
		}
		builder.addLabel("Straße:", cc.xy(1, 3));
		builder.add(getStrassenFeld(), cc.xy(3, 3));
		builder.addLabel("Hausnr.:", cc.xy(5, 3));
		builder.add(getHausnrFeld(), cc.xy(7, 3));
		builder.add(getSubmitButtonStrassen(), cc.xy(9, 3));
		builder.add(tabellenScroller, cc.xywh(1, 5, 9, 2));
		builder.add(buttonBar, cc.xyw(3, 8, 3));

		JPanel panel = builder.getPanel();
		panel.setBorder(Paddings.DIALOG);
		
		return (panel);
	}

	private void choose(int row) {
		if (row != -1) {
			if (this.betreiberAdresse != null && caller == "adresse") {
				this.adresse = this.adresseModel.getRow(row);
			} else if (this.betreiberAdresse != null && caller == "betreiber") {
				Object[] obj = (Object[]) this.betreiberModel.getRow(row);
				this.betreiberAdresse = (Inhaber) obj[0];
			} else if (this.standortAdresse != null) {
				this.standortAdresse = this.standortModel.getRow(row);
			}
			dispose();
		}
	}

	private void doSearchName() {
		final String suche = getSuchFeld().getText();

		if (caller == "betreiber") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					AdresseChooser.this.betreiberModel.filterAllList(suche, null);
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.betreiberModel.fireTableDataChanged();
				}
			};
			worker.start();

			getStrassenFeld().setText("");
			getHausnrFeld().setText("");

		} else if (caller == "standort") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					AdresseChooser.this.standortModel.filterStandort(suche, null);
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.standortModel.fireTableDataChanged();
				}
			};
			worker.start();

			getStrassenFeld().setText("");
			getHausnrFeld().setText("");
		}
	}

	private void doSearchStrasse() {
		final String strasse = getStrassenFeld().getText();
		Integer nr = null;
		if (this.hausnrFeld.getText() == null) {
			nr = Integer.parseInt(getHausnrFeld().getText());
		} else {
			nr = -1;
		}		
		final Integer hausnr = nr;
		
		if (caller == "adresse") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					AdresseChooser.this.adresseModel.filterStandort(strasse, hausnr);
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.adresseModel.fireTableDataChanged();
				}
			};
			worker.start();

			getStrassenFeld().setText("");
			getHausnrFeld().setText("");

		} else if (caller == "betreiber") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					AdresseChooser.this.betreiberModel.filterStandort(strasse, hausnr, null);
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.betreiberModel.fireTableDataChanged();
				}
			};
			worker.start();

			getStrassenFeld().setText("");
			getHausnrFeld().setText("");

		} else if (caller == "standort") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					AdresseChooser.this.standortModel.filterStandort(strasse, hausnr, null);
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.standortModel.fireTableDataChanged();
				}
			};
			worker.start();

			getStrassenFeld().setText("");
			getHausnrFeld().setText("");
		}
	}

	/**
	 * Filtert die Standort-Liste nach Straße und Hausnummer.
	 *
	 * @param focusComp Welche Komponente soll nach der Suche den Fokus bekommen.
	 */
	public void filterBetreiberListe(Component focusComp) {
		log.debug("Start filterStandortListe()");
		int hausnr;
		try {
			hausnr = Integer.parseInt(getHausnrFeld().getText());
		} catch (NumberFormatException e1) {
			hausnr = -1;
		}
		final int fhausnr = hausnr;

		SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {

			@Override
			protected void doNonUILogic() {
				if (SettingsManager.getInstance().getStandort() == null) {
					AdresseChooser.this.betreiberModel.filterStandort(getStrassenFeld().getText(), fhausnr, null);
				}
				getSuchFeld().setText("");
			}

			@Override
			protected void doUIUpdateLogic() {
				getErgebnisTabelle().clearSelection();

				AdresseChooser.this.betreiberModel.fireTableDataChanged();
				String statusMsg = "Suche: " + AdresseChooser.this.betreiberModel.getRowCount() + " Ergebnis";
				if (AdresseChooser.this.betreiberModel.getRowCount() != 1) {
					statusMsg += "se";
				}
				statusMsg += ".";
				AdresseChooser.this.frame.changeStatus(statusMsg);
			}
		};

		this.frame.changeStatus("Suche...");
		worker.start();
		log.debug("End filterStandortListe()");
	}

	private JTextField getSuchFeld() {
		if (this.suchFeld == null) {
			this.suchFeld = new JTextField();
			this.suchFeld.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					doSearchName();
				}
			});
		}

		return this.suchFeld;
	}

	private JTextField getStrassenFeld() {

		if (this.strasseFeld == null) {

			this.strasseFeld = new JTextField();
			this.strasseFeld.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getSuchTimer().stop();
					doSearchStrasse();
				}
			});

			this.strasseFeld.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_TAB) {
						getSuchTimer().stop();
						filterBetreiberListe(getHausnrFeld());
					}
				}

				@Override
				public void keyTyped(KeyEvent e) {
					if (Character.isLetterOrDigit(e.getKeyChar())) {
						if (getSuchTimer().isRunning()) {
							getSuchTimer().restart();
						} else {
							getSuchTimer().start();
						}
					}
				}
			});
		}

		return this.strasseFeld;
	}

	private JTextField getHausnrFeld() {
		if (this.hausnrFeld == null) {
			this.hausnrFeld = new BasicEntryField();

			this.hausnrFeld.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterBetreiberListe(getErgebnisTabelle());
				}
			});
		}
		return this.hausnrFeld;
	}

	private JButton getSubmitButton() {
		if (this.submitButton == null) {
			this.submitButton = new JButton("Name suchen", AuikUtils.getIcon(16, "key_enter.png"));
			this.submitButton.setToolTipText("Suche starten");
			this.submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					doSearchName();
				}
			});
		}

		return this.submitButton;
	}

	private JButton getSubmitButtonStrassen() {
		if (this.submitButtonStrassen == null) {
			this.submitButtonStrassen = new JButton("Standort suchen", AuikUtils.getIcon(16, "key_enter.png"));
			this.submitButtonStrassen.setToolTipText("Suche starten");
			this.submitButtonStrassen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					doSearchStrasse();
				}
			});
		}

		return this.submitButtonStrassen;
	}

	private JTable getErgebnisTabelle() {
		if (this.ergebnisTabelle == null) {
			if (this.betreiberAdresse != null && caller == "adresse") {
				this.ergebnisTabelle = new JTable(this.adresseModel);
			} else if (this.betreiberAdresse != null && caller == "betreiber") {
				this.ergebnisTabelle = new JTable(this.betreiberModel);
			} else if (this.standortAdresse != null) {
				this.ergebnisTabelle = new JTable(this.standortModel);
				// ergebnisTabelle = new JTable(3, 3);
			}

			this.ergebnisTabelle.addFocusListener(TableFocusListener.getInstance());
			this.ergebnisTabelle.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = AdresseChooser.this.ergebnisTabelle.rowAtPoint(origin);

						choose(row);
					}
				}
			});

			this.ergebnisTabelle.getColumnModel().getColumn(0).setPreferredWidth(130);
		}

		return this.ergebnisTabelle;
	}

	private JButton getOkButton() {
		if (this.okButton == null) {
			this.okButton = new JButton("Ok");
			this.okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = getErgebnisTabelle().getSelectedRow();

					choose(row);
				}
			});
		}

		return this.okButton;
	}

	private JButton getAbbrechenButton() {
		if (this.abbrechenButton == null) {
			this.abbrechenButton = new JButton("Abbrechen");
			this.abbrechenButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}

		return this.abbrechenButton;
	}

	private Timer getSuchTimer() {
		if (this.suchTimer == null) {
			this.suchTimer = new Timer(900, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					// Was diese ganze "SwingWorkerVariant"-Geschichte
					// soll, steht unter
					// http://www.javaworld.com/javaworld/jw-06-2003/jw-0606-swingworker.html
					// Ist auch ausgedruckt im Ordner im Regal. -DK
					SwingWorkerVariant worker = new SwingWorkerVariant(getStrassenFeld()) {
						protected String oldText = "";
						private String newText = "";

						@Override
						protected void doNonUILogic() {
                            this.oldText = getStrassenFeld().getText();
                            if (this.oldText.equals(""))
                            {
                                this.newText = "";
                            }
                            else
                            {
                                String suchText = AuikUtils
                                        .sanitizeQueryInput(this.oldText);
								String str = DatabaseQuery
										.getTabStreet(suchText);

                                if (str != null)
                                {
                                    this.newText = str;
                                }
                                else
                                {
                                    this.newText = this.oldText;
                                }
                            }
						}

						@Override
						protected void doUIUpdateLogic() {
							getStrassenFeld().setText(this.newText);
							getStrassenFeld().setSelectionStart(this.oldText.length());
							getStrassenFeld().setSelectionEnd(this.newText.length());
						}
					};
					worker.start();
				}
			});
			this.suchTimer.setRepeats(false);
		}

		return this.suchTimer;
	}
}
